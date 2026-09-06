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

data class ScannedSlotInfo(
    val slotIndex: Int,
    val isAlly: Boolean,
    var champion: Champion? = null,
    var explicitRole: LaneRole? = null,
    var assignedRole: LaneRole? = null,
    var confidencePercent: Int = 0,
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
                AppLogger.e(TAG, "ML Kit TextRecognizer init warning", e)
            }
        }
        return recognizerInstance
    }

    suspend fun scanDraftFromBitmap(bitmap: Bitmap): DraftScanResult {
        if (bitmap.isRecycled || bitmap.width < bitmap.height) {
            return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "Orientación no horizontal")
        }

        val recognizer = getRecognizer() ?: return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "OCR no disponible")

        val width = bitmap.width
        val height = bitmap.height
        val allChamps = WildRiftRepository.champions
        val auditList = mutableListOf<String>()

        // 5 slots para aliados y 5 slots para enemigos
        val allySlots = (0..4).map { ScannedSlotInfo(slotIndex = it, isAlly = true) }
        val enemySlots = (0..4).map { ScannedSlotInfo(slotIndex = it, isAlly = false) }
        val detectedWords = mutableListOf<String>()
        var userDetectedLane: LaneRole? = null

        // -----------------------------------------------------------------------------------------
        // PASO 1: OCR CON AISLAMIENTO ESTRICTO DE COLUMNAS (IGNORA EL OVERLAY CENTRAL 0.28..0.72)
        // -----------------------------------------------------------------------------------------
        try {
            val inputImage = InputImage.fromBitmap(bitmap, 0)
            val visionText = recognizer.process(inputImage).await()

            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val text = line.text.trim()
                    if (text.isBlank()) continue
                    detectedWords.add(text)

                    val box = line.boundingBox
                    val centerY = box?.centerY() ?: 0
                    val centerX = box?.centerX() ?: 0
                    val yRatio = centerY.toFloat() / height.toFloat()
                    val xRatio = centerX.toFloat() / width.toFloat()

                    // 1.1 PARSING DEL CHAT IN-GAME (Y > 0.80 y X < 0.40)
                    // El chat se analiza exclusivamente como información contextual; jamás debe sobreescribir slots directos
                    if (yRatio in 0.80f..0.94f && xRatio < 0.40f) {
                        val chatChamp = ChampionNameResolver.findChampionInText(text, allChamps)
                        if (chatChamp != null) {
                            AppLogger.d(TAG, "OCR Chat detectó mención de campeón: ${chatChamp.name} en línea '$text'")
                        }
                        continue
                    }

                    // Ignorar la barra de bans superior (Y < 0.12) y botones del fondo (Y > 0.85)
                    if (yRatio < 0.12f || yRatio > 0.85f) continue

                    // Determinar el índice de slot vertical (0..4)
                    val slotIndex = when {
                        yRatio < 0.26f -> 0
                        yRatio < 0.40f -> 1
                        yRatio < 0.54f -> 2
                        yRatio < 0.68f -> 3
                        else -> 4
                    }

                    // 1.2 COLUMNA ALIADA (Extremo Izquierdo: X entre 0.02 y 0.28)
                    if (xRatio in 0.02f..0.28f) {
                        val slot = allySlots[slotIndex]

                        // Buscar nombre de campeón en el texto del slot
                        val matchedChamp = ChampionNameResolver.findChampionInText(text, allChamps)
                        if (matchedChamp != null) {
                            slot.champion = matchedChamp
                            slot.confidencePercent = 99
                            AppLogger.d(TAG, "OCR Aliado Slot $slotIndex -> Campeón: ${matchedChamp.name}")
                        }

                        // Buscar texto de rol explícito (ej: "CARRIL DE BARÓN", "JUNGLA", etc.)
                        val role = DraftValidationLayer.parseRoleFromText(text)
                        if (role != null) {
                            slot.explicitRole = role
                            if (slotIndex == 4 || userDetectedLane == null) {
                                userDetectedLane = role
                            }
                            AppLogger.d(TAG, "OCR Aliado Slot $slotIndex -> Rol explícito: ${role.shortName}")
                        }
                    }

                    // 1.3 COLUMNA ENEMIGA (Extremo Derecho: X entre 0.72 y 0.98)
                    else if (xRatio in 0.72f..0.98f) {
                        val slot = enemySlots[slotIndex]

                        val matchedChamp = ChampionNameResolver.findChampionInText(text, allChamps)
                        if (matchedChamp != null) {
                            slot.champion = matchedChamp
                            slot.confidencePercent = 95
                            AppLogger.d(TAG, "OCR Enemigo Slot $slotIndex -> Campeón: ${matchedChamp.name}")
                        }

                        val role = DraftValidationLayer.parseRoleFromText(text)
                        if (role != null) {
                            slot.explicitRole = role
                        }
                    }
                    // NOTA: Toda la franja central (X entre 0.28 y 0.72) donde reside el Overlay flotante es TOTALMENTE IGNORADA
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error durante el análisis OCR", e)
        }

        // -----------------------------------------------------------------------------------------
        // PASO 1.5: RECORTE VISUAL DE AVATARES E ICONOS DE ROL (FALLBACK Y MULTIMODAL DETECTION)
        // -----------------------------------------------------------------------------------------
        val avatarSize = (height * 0.115f).toInt().coerceAtLeast(24)
        val allyAvatarCenterX = (width * 0.115f).toInt()
        val enemyAvatarCenterX = (width * 0.915f).toInt()

        // 1.5.1 Aliados
        for (i in 0..4) {
            val slot = allySlots[i]
            val yCenter = (height * (0.185f + (i * 0.140f))).toInt()
            val startX = (allyAvatarCenterX - avatarSize / 2).coerceIn(0, width - avatarSize)
            val startY = (yCenter - avatarSize / 2).coerceIn(0, height - avatarSize)

            try {
                val crop = Bitmap.createBitmap(bitmap, startX, startY, avatarSize, avatarSize)

                // Si no se detectó el campeón por texto OCR, recurrir a la comparación visual del avatar con prioridad por rol
                if (slot.champion == null) {
                    val matchResult = ImageHashMatcher.findBestMatchDetailed(
                        crop,
                        allChamps,
                        maxDistance = 22,
                        preferredRole = slot.explicitRole
                    )
                    if (matchResult != null) {
                        slot.champion = matchResult.champion
                        slot.confidencePercent = matchResult.confidencePercent
                        AppLogger.d(TAG, "Avatar Aliado Slot $i -> Campeón visual: ${matchResult.champion.name} (Conf: ${matchResult.confidencePercent}%)")
                    }
                }
                crop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error recortando avatar aliado slot $i: ${e.message}")
            }
        }

        // 1.5.2 Enemigos
        for (i in 0..4) {
            val slot = enemySlots[i]
            val yCenter = (height * (0.185f + (i * 0.140f))).toInt()
            val startX = (enemyAvatarCenterX - avatarSize / 2).coerceIn(0, width - avatarSize)
            val startY = (yCenter - avatarSize / 2).coerceIn(0, height - avatarSize)

            try {
                val crop = Bitmap.createBitmap(bitmap, startX, startY, avatarSize, avatarSize)

                if (slot.champion == null) {
                    val matchResult = ImageHashMatcher.findBestMatchDetailed(
                        crop,
                        allChamps,
                        maxDistance = 22,
                        preferredRole = slot.explicitRole
                    )
                    if (matchResult != null) {
                        slot.champion = matchResult.champion
                        slot.confidencePercent = matchResult.confidencePercent
                        AppLogger.d(TAG, "Avatar Enemigo Slot $i -> Campeón visual: ${matchResult.champion.name} (Conf: ${matchResult.confidencePercent}%)")
                    }
                }
                crop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error recortando avatar enemigo slot $i: ${e.message}")
            }
        }

        // -----------------------------------------------------------------------------------------
        // PASO 2: ASIGNACIÓN DETERMINISTA DE ROLES CON CAPA DE VALIDACIÓN (ZERO-GUESSING)
        // -----------------------------------------------------------------------------------------
        val allyResolved = DraftValidationLayer.resolveTeamRolesDetailed(allySlots, allChamps, auditList)
        val alliesMap = allyResolved.assignments

        val enemyResolved = DraftValidationLayer.resolveTeamRolesDetailed(enemySlots, allChamps, auditList)
        val enemiesMap = enemyResolved.assignments

        // Deduplicación: Un campeón aliado jamás puede aparecer en el equipo enemigo
        val allyChampIds = alliesMap.values.map { it.id }.toSet()
        val finalEnemiesMap = enemiesMap.filterNot { allyChampIds.contains(it.value.id) }

        val enemyConfidences = enemyResolved.confidences.filterKeys { finalEnemiesMap.containsKey(it) }

        val allyChampsList = alliesMap.values.toList()
        val enemyChampsList = finalEnemiesMap.values.toList()
        val total = allyChampsList.size + enemyChampsList.size

        val statusMsg = when {
            total == 0 -> "Esperando selección en directo..."
            auditList.isNotEmpty() -> "Detectados: $total picks (${auditList.size} adaptaciones)"
            else -> "Detectados: $total picks con certeza"
        }

        return DraftScanResult(
            allies = allyChampsList,
            enemies = enemyChampsList,
            alliesByRole = alliesMap,
            enemiesByRole = finalEnemiesMap,
            enemyConfidencesByRole = enemyConfidences,
            detectedRole = userDetectedLane,
            detectedRawWords = detectedWords,
            discrepancies = auditList,
            isSuccessful = total > 0,
            statusMessage = statusMsg
        )
    }
}
