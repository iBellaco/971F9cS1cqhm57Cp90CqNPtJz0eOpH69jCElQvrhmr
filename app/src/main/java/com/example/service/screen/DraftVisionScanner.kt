package com.example.service.screen

import android.graphics.Bitmap
import android.graphics.Rect
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.util.AppLogger
import com.example.util.ImageHashMatcher
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await
import java.util.Locale

data class DetectedChampionSlot(
    val champion: Champion,
    val isAlly: Boolean,
    val boundingBox: Rect?,
    val confidence: Float = 0.95f
)

data class ScannedSlotInfo(
    val slotIndex: Int,
    val isAlly: Boolean,
    val champion: Champion?,
    val iconRole: LaneRole?,
    val ocrRole: LaneRole?,
    val confidencePercent: Int = 90,
    var assignedRole: LaneRole? = null,
    var isDiscrepancy: Boolean = false,
    var auditLog: String? = null
)

data class DraftScanResult(
    val allies: List<Champion>,
    val enemies: List<Champion>,
    val alliesByRole: Map<LaneRole, Champion> = emptyMap(),
    val enemiesByRole: Map<LaneRole, Champion> = emptyMap(),
    val enemyConfidencesByRole: Map<LaneRole, Int> = emptyMap(),
    val detectedRole: LaneRole? = null,
    val detectedRawWords: List<String> = emptyList(),
    val discrepancies: List<String> = emptyList(),
    val isSuccessful: Boolean,
    val statusMessage: String
)

object DraftVisionScanner {
    private const val TAG = "DraftVisionScanner"
    
    private var recognizerInstance: com.google.mlkit.vision.text.TextRecognizer? = null

    private fun getRecognizer(): com.google.mlkit.vision.text.TextRecognizer? {
        if (recognizerInstance == null) {
            try {
                recognizerInstance = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            } catch (e: Throwable) {
                AppLogger.e(TAG, "ML Kit TextRecognizer initialization warning", e)
            }
        }
        return recognizerInstance
    }

    suspend fun scanDraftFromBitmap(bitmap: Bitmap, preferredSummonerName: String? = null): DraftScanResult {
        if (bitmap.isRecycled || bitmap.width < bitmap.height) {
            return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "Esperando orientación horizontal...")
        }

        val recognizer = getRecognizer() ?: return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "OCR no disponible")

        val width = bitmap.width
        val height = bitmap.height
        val discrepancyAuditList = mutableListOf<String>()

        // -----------------------------------------------------------------------------------------
        // 1. OCR PASO A: Detección de texto de carriles explícitos
        // -----------------------------------------------------------------------------------------
        val image = InputImage.fromBitmap(bitmap, 0)
        val allySlotOcrRoles = arrayOfNulls<LaneRole>(5)
        val enemySlotOcrRoles = arrayOfNulls<LaneRole>(5)
        val detectedWords = mutableListOf<String>()

        try {
            val visionText = recognizer.process(image).await()
            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    if (lineText.isBlank()) continue
                    detectedWords.add(lineText)

                    val lower = lineText.lowercase(Locale.ROOT)
                    val box = line.boundingBox
                    val centerY = box?.centerY() ?: 0
                    val centerX = box?.centerX() ?: 0
                    val yRatio = centerY.toFloat() / height.toFloat()
                    val xRatio = centerX.toFloat() / width.toFloat()

                    if (yRatio < 0.02f || yRatio > 0.98f) continue

                    val role = when {
                        lower.contains("central") || lower.contains("mid") || lower.contains("medio") -> LaneRole.MID
                        lower.contains("baron") || lower.contains("barón") || lower.contains("solo") || lower.contains("superior") || lower.contains("top") -> LaneRole.TOP
                        lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva") -> LaneRole.JUNGLE
                        lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador") -> LaneRole.ADC
                        lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") -> LaneRole.SUPPORT
                        else -> null
                    }

                    if (role != null) {
                        val bucket = (yRatio * 5).toInt().coerceIn(0, 4)
                        if (xRatio < 0.5f) {
                            allySlotOcrRoles[bucket] = role
                        } else {
                            enemySlotOcrRoles[bucket] = role
                        }
                    }
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error durante el análisis OCR", e)
        }

        // -----------------------------------------------------------------------------------------
        // 2. IMAGE MATCHING PASO B: Recorte y escaneo de Campeón + Icono de Rol
        // -----------------------------------------------------------------------------------------
        val allChamps = WildRiftRepository.champions
        val allySlotInfos = mutableListOf<ScannedSlotInfo>()
        val enemySlotInfos = mutableListOf<ScannedSlotInfo>()

        val avatarWidth = (width * 0.15f).toInt()
        val avatarHeight = (height * 0.15f).toInt()
        val iconSize = (height * 0.08f).toInt().coerceAtLeast(16)
        
        val allyAvatarX = (width * 0.04f).toInt().coerceIn(0, width - avatarWidth)
        val enemyAvatarX = (width * 0.81f).toInt().coerceIn(0, width - avatarWidth)

        // Escaneo slots aliados (0..4)
        for (i in 0..4) {
            val yCenter = height * (0.10f + (i * 0.19f))
            val startY = (yCenter - avatarHeight / 2).toInt().coerceIn(0, height - avatarHeight)
            
            var matchedChamp: Champion? = null
            var matchedIconRole: LaneRole? = null

            // A) Avatar Match
            try {
                val allyCrop = Bitmap.createBitmap(bitmap, allyAvatarX, startY, avatarWidth, avatarHeight)
                matchedChamp = ImageHashMatcher.findBestMatch(allyCrop, allChamps)
                allyCrop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error recortando avatar aliado slot $i: ${e.message}")
            }

            // B) Role Icon Match (Esquina superior izquierda del slot de selección)
            try {
                val iconX = (allyAvatarX - (width * 0.015f).toInt()).coerceIn(0, width - iconSize)
                val iconY = startY.coerceIn(0, height - iconSize)
                val roleCrop = Bitmap.createBitmap(bitmap, iconX, iconY, iconSize, iconSize)
                matchedIconRole = ImageHashMatcher.findRoleMatch(roleCrop)
                roleCrop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error recortando icono rol aliado slot $i: ${e.message}")
            }

            allySlotInfos.add(
                ScannedSlotInfo(
                    slotIndex = i,
                    isAlly = true,
                    champion = matchedChamp,
                    iconRole = matchedIconRole,
                    ocrRole = allySlotOcrRoles[i]
                )
            )
        }

        // Escaneo slots enemigos (0..4)
        for (i in 0..4) {
            val yCenter = height * (0.10f + (i * 0.19f))
            val startY = (yCenter - avatarHeight / 2).toInt().coerceIn(0, height - avatarHeight)
            
            var matchedChamp: Champion? = null
            var matchedConfidence = 85
            var matchedIconRole: LaneRole? = null

            try {
                val enemyCrop = Bitmap.createBitmap(bitmap, enemyAvatarX, startY, avatarWidth, avatarHeight)
                val matchResult = ImageHashMatcher.findBestMatchDetailed(enemyCrop, allChamps)
                matchedChamp = matchResult?.champion
                if (matchResult != null) {
                    matchedConfidence = matchResult.confidencePercent
                }
                enemyCrop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error recortando avatar enemigo slot $i: ${e.message}")
            }

            try {
                val iconX = (enemyAvatarX + avatarWidth - (iconSize * 0.8f).toInt()).coerceIn(0, width - iconSize)
                val iconY = startY.coerceIn(0, height - iconSize)
                val roleCrop = Bitmap.createBitmap(bitmap, iconX, iconY, iconSize, iconSize)
                matchedIconRole = ImageHashMatcher.findRoleMatch(roleCrop)
                roleCrop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error recortando icono rol enemigo slot $i: ${e.message}")
            }

            enemySlotInfos.add(
                ScannedSlotInfo(
                    slotIndex = i,
                    isAlly = false,
                    champion = matchedChamp,
                    iconRole = matchedIconRole,
                    ocrRole = enemySlotOcrRoles[i],
                    confidencePercent = matchedConfidence
                )
            )
        }

        // -----------------------------------------------------------------------------------------
        // 3. CRUCE DE DATOS Y MOTOR DE ASIGNACIÓN CON SOBRESCRITURA POR ICONO (PASO C)
        // -----------------------------------------------------------------------------------------
        val standardRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val alliesMap = mutableMapOf<LaneRole, Champion>()
        val enemiesMap = mutableMapOf<LaneRole, Champion>()

        // 3.1 Procesamiento de Aliados:
        val availableAllyRoles = standardRoles.toMutableList()

        // Fase 1: Asignación por Icono de Rol Detectado (Prioridad Máxima y Sobrescritura de Metadata)
        for (slot in allySlotInfos) {
            val champ = slot.champion ?: continue
            val detectedIconRole = slot.iconRole
            if (detectedIconRole != null && availableAllyRoles.contains(detectedIconRole) && !alliesMap.containsKey(detectedIconRole)) {
                alliesMap[detectedIconRole] = champ
                availableAllyRoles.remove(detectedIconRole)
                slot.assignedRole = detectedIconRole
                
                // Comprobación de discrepancia entre rol detectado por icono y metadata por defecto del campeón
                if (champ.primaryRole != detectedIconRole) {
                    slot.isDiscrepancy = true
                    val logMsg = "⚡ [DISCREPANCIA ALIADO Slot ${slot.slotIndex + 1}]: Campeón '${champ.name}' (Metadata Base: ${champ.primaryRole.shortName}) reasignado a '${detectedIconRole.shortName}' mediante coincidencia visual del ICONO de Rol."
                    slot.auditLog = logMsg
                    discrepancyAuditList.add(logMsg)
                    AppLogger.i(TAG, logMsg)
                } else {
                    AppLogger.d(TAG, "Aliado Slot ${slot.slotIndex + 1}: ${champ.name} asignado a ${detectedIconRole.shortName} por icono visual.")
                }
            }
        }

        // Fase 2: Asignación por OCR de Rol Detectado (Prioridad Secundaria)
        for (slot in allySlotInfos) {
            val champ = slot.champion ?: continue
            if (slot.assignedRole != null) continue // Ya asignado por icono

            val detectedOcrRole = slot.ocrRole
            if (detectedOcrRole != null && availableAllyRoles.contains(detectedOcrRole) && !alliesMap.containsKey(detectedOcrRole)) {
                alliesMap[detectedOcrRole] = champ
                availableAllyRoles.remove(detectedOcrRole)
                slot.assignedRole = detectedOcrRole

                if (champ.primaryRole != detectedOcrRole) {
                    slot.isDiscrepancy = true
                    val logMsg = "📝 [DISCREPANCIA ALIADO Slot ${slot.slotIndex + 1}]: Campeón '${champ.name}' (Metadata Base: ${champ.primaryRole.shortName}) reasignado a '${detectedOcrRole.shortName}' mediante OCR de carril."
                    slot.auditLog = logMsg
                    discrepancyAuditList.add(logMsg)
                    AppLogger.i(TAG, logMsg)
                }
            }
        }

        // Fase 3: Asignación por Metadata de Campeón (Fallback para slots sin icono ni OCR)
        for (slot in allySlotInfos) {
            val champ = slot.champion ?: continue
            if (slot.assignedRole != null || alliesMap.containsValue(champ)) continue

            val assigned = when {
                availableAllyRoles.contains(champ.primaryRole) -> champ.primaryRole
                else -> champ.secondaryRoles.firstOrNull { availableAllyRoles.contains(it) } ?: availableAllyRoles.firstOrNull()
            }

            if (assigned != null) {
                alliesMap[assigned] = champ
                availableAllyRoles.remove(assigned)
                slot.assignedRole = assigned
                AppLogger.d(TAG, "Aliado Slot ${slot.slotIndex + 1}: ${champ.name} asignado a ${assigned.shortName} por fallback de pool/metadata.")
            }
        }

        // 3.2 Procesamiento de Enemigos:
        val availableEnemyRoles = standardRoles.toMutableList()

        // Fase 1 Enemigos: Icono de Rol Detectado
        for (slot in enemySlotInfos) {
            val champ = slot.champion ?: continue
            val detectedIconRole = slot.iconRole
            if (detectedIconRole != null && availableEnemyRoles.contains(detectedIconRole) && !enemiesMap.containsKey(detectedIconRole)) {
                enemiesMap[detectedIconRole] = champ
                availableEnemyRoles.remove(detectedIconRole)
                slot.assignedRole = detectedIconRole

                if (champ.primaryRole != detectedIconRole) {
                    slot.isDiscrepancy = true
                    val logMsg = "⚡ [DISCREPANCIA ENEMIGO Slot ${slot.slotIndex + 1}]: Campeón '${champ.name}' (Metadata Base: ${champ.primaryRole.shortName}) reasignado a '${detectedIconRole.shortName}' mediante coincidencia visual del ICONO de Rol."
                    slot.auditLog = logMsg
                    discrepancyAuditList.add(logMsg)
                    AppLogger.i(TAG, logMsg)
                }
            }
        }

        // Fase 2 Enemigos: OCR de Rol Detectado
        for (slot in enemySlotInfos) {
            val champ = slot.champion ?: continue
            if (slot.assignedRole != null) continue

            val detectedOcrRole = slot.ocrRole
            if (detectedOcrRole != null && availableEnemyRoles.contains(detectedOcrRole) && !enemiesMap.containsKey(detectedOcrRole)) {
                enemiesMap[detectedOcrRole] = champ
                availableEnemyRoles.remove(detectedOcrRole)
                slot.assignedRole = detectedOcrRole

                if (champ.primaryRole != detectedOcrRole) {
                    slot.isDiscrepancy = true
                    val logMsg = "📝 [DISCREPANCIA ENEMIGO Slot ${slot.slotIndex + 1}]: Campeón '${champ.name}' (Metadata Base: ${champ.primaryRole.shortName}) reasignado a '${detectedOcrRole.shortName}' mediante OCR de carril."
                    slot.auditLog = logMsg
                    discrepancyAuditList.add(logMsg)
                    AppLogger.i(TAG, logMsg)
                }
            }
        }

        // Fase 3 Enemigos: Fallback Metadata
        for (slot in enemySlotInfos) {
            val champ = slot.champion ?: continue
            if (slot.assignedRole != null || enemiesMap.containsValue(champ)) continue

            val assigned = when {
                availableEnemyRoles.contains(champ.primaryRole) -> champ.primaryRole
                else -> champ.secondaryRoles.firstOrNull { availableEnemyRoles.contains(it) } ?: availableEnemyRoles.firstOrNull()
            }

            if (assigned != null) {
                enemiesMap[assigned] = champ
                availableEnemyRoles.remove(assigned)
                slot.assignedRole = assigned
            }
        }

        // -----------------------------------------------------------------------------------------
        // 4. Deduplicación global y formateo del resultado final
        // -----------------------------------------------------------------------------------------
        val allyChampIds = alliesMap.values.map { it.id }.toSet()
        val finalEnemiesMap = enemiesMap.filterNot { entry -> allyChampIds.contains(entry.value.id) }

        // Mapear el nivel de certeza/confianza por rol del equipo rival
        val enemyConfidences = mutableMapOf<LaneRole, Int>()
        enemySlotInfos.forEach { slot ->
            val assigned = slot.assignedRole
            if (assigned != null && finalEnemiesMap.containsKey(assigned)) {
                val finalConf = when {
                    slot.iconRole != null -> (slot.confidencePercent + 6).coerceAtMost(98)
                    slot.ocrRole != null -> (slot.confidencePercent + 3).coerceAtMost(95)
                    else -> (slot.confidencePercent - 4).coerceAtLeast(65)
                }
                enemyConfidences[assigned] = finalConf
            }
        }

        val allyChampsList = alliesMap.values.toList()
        val enemyChampsList = finalEnemiesMap.values.toList()
        val total = allyChampsList.size + enemyChampsList.size

        val statusMsg = when {
            total == 0 -> "Esperando campeones..."
            discrepancyAuditList.isNotEmpty() -> "Escaneo Híbrido: $total picks (${discrepancyAuditList.size} roles adaptados por icono)"
            else -> "Escaneo Híbrido: $total detectados"
        }

        return DraftScanResult(
            allies = allyChampsList,
            enemies = enemyChampsList,
            alliesByRole = alliesMap,
            enemiesByRole = finalEnemiesMap,
            enemyConfidencesByRole = enemyConfidences,
            detectedRole = null,
            detectedRawWords = detectedWords,
            discrepancies = discrepancyAuditList,
            isSuccessful = total > 0,
            statusMessage = statusMsg
        )
    }
}

