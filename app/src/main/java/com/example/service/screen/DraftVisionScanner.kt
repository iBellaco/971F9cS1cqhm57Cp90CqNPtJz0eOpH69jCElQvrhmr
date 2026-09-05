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
                    // Formato típico en Wild Rift: "Summoner (ChampionName): Mensaje"
                    if (yRatio in 0.80f..0.94f && xRatio < 0.40f) {
                        val chatChamp = ChampionNameResolver.findChampionInText(text, allChamps)
                        if (chatChamp != null) {
                            AppLogger.d(TAG, "OCR Chat detectó campeón: ${chatChamp.name} en línea '$text'")
                            // Si aún no está asignado a aliados, registrar como respaldo de pick
                            if (allySlots.none { it.champion?.id == chatChamp.id }) {
                                val emptySlot = allySlots.firstOrNull { it.champion == null }
                                if (emptySlot != null) {
                                    emptySlot.champion = chatChamp
                                    emptySlot.confidencePercent = 95
                                }
                            }
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
                        val lower = text.lowercase(Locale.ROOT)
                        val role = parseRoleFromText(lower)
                        if (role != null) {
                            slot.explicitRole = role
                            if (lower.contains("carril") || lower.contains("baron") || lower.contains("barón") || lower.contains("solo")) {
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

                        val lower = text.lowercase(Locale.ROOT)
                        val role = parseRoleFromText(lower)
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
                
                // Si aún no tenemos rol explícito, comprobar si el avatar es un icono de carril
                if (slot.explicitRole == null) {
                    val roleMatch = ImageHashMatcher.findRoleMatchDetailed(crop, maxDistance = 22)
                    if (roleMatch != null) {
                        slot.explicitRole = roleMatch.role
                        AppLogger.d(TAG, "Avatar Aliado Slot $i -> Icono de rol: ${roleMatch.role.shortName}")
                    }
                }

                // Si no se detectó el campeón por texto OCR, recurrir a la comparación visual del avatar
                if (slot.champion == null) {
                    val matchResult = ImageHashMatcher.findBestMatchDetailed(crop, allChamps, maxDistance = 20)
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
                
                if (slot.explicitRole == null) {
                    val roleMatch = ImageHashMatcher.findRoleMatchDetailed(crop, maxDistance = 22)
                    if (roleMatch != null) {
                        slot.explicitRole = roleMatch.role
                    }
                }

                if (slot.champion == null) {
                    val matchResult = ImageHashMatcher.findBestMatchDetailed(crop, allChamps, maxDistance = 20)
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
        // PASO 2: ASIGNACIÓN DETERMINISTA DE ROLES (ZERO-GUESSING)
        // -----------------------------------------------------------------------------------------
        val alliesMap = assignTeamRoles(allySlots, isAlly = true, auditList)
        val enemiesMap = assignTeamRoles(enemySlots, isAlly = false, auditList)

        // Deduplicación: Un campeón aliado jamás puede aparecer en el equipo enemigo
        val allyChampIds = alliesMap.values.map { it.id }.toSet()
        val finalEnemiesMap = enemiesMap.filterNot { allyChampIds.contains(it.value.id) }

        val enemyConfidences = mutableMapOf<LaneRole, Int>()
        enemySlots.forEach { slot ->
            val assigned = slot.assignedRole
            if (assigned != null && finalEnemiesMap.containsKey(assigned)) {
                enemyConfidences[assigned] = slot.confidencePercent.coerceIn(80, 98)
            }
        }

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

    private fun parseRoleFromText(lower: String): LaneRole? {
        return when {
            lower.contains("baron") || lower.contains("barón") || lower.contains("solo") || lower.contains("superior") || lower.contains("top") -> LaneRole.TOP
            lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva") -> LaneRole.JUNGLE
            lower.contains("central") || lower.contains("mid") || lower.contains("medio") -> LaneRole.MID
            lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador") -> LaneRole.ADC
            lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") -> LaneRole.SUPPORT
            else -> null
        }
    }

    private fun assignTeamRoles(
        slots: List<ScannedSlotInfo>,
        isAlly: Boolean,
        auditList: MutableList<String>
    ): Map<LaneRole, Champion> {
        val standardRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val assignedMap = mutableMapOf<LaneRole, Champion>()
        val availableRoles = standardRoles.toMutableList()
        val pendingSlots = mutableListOf<ScannedSlotInfo>()

        // 1. Asignar slots que tienen un rol explícito detectado por texto en pantalla
        for (slot in slots) {
            val champ = slot.champion ?: continue
            val expRole = slot.explicitRole
            if (expRole != null && availableRoles.contains(expRole) && !assignedMap.containsKey(expRole)) {
                assignedMap[expRole] = champ
                availableRoles.remove(expRole)
                slot.assignedRole = expRole
                if (champ.primaryRole != expRole) {
                    auditList.add("Rol explícito: ${champ.name} -> ${expRole.shortName}")
                }
            } else {
                pendingSlots.add(slot)
            }
        }

        // 2. Asignar por rol primario del campeón
        val remainingAfterPrimary = mutableListOf<ScannedSlotInfo>()
        for (slot in pendingSlots) {
            val champ = slot.champion ?: continue
            val primary = champ.primaryRole
            if (availableRoles.contains(primary) && !assignedMap.containsKey(primary)) {
                assignedMap[primary] = champ
                availableRoles.remove(primary)
                slot.assignedRole = primary
            } else {
                remainingAfterPrimary.add(slot)
            }
        }

        // 3. Asignar por rol secundario si hubo colisión (Flex picks)
        val remainingAfterSecondary = mutableListOf<ScannedSlotInfo>()
        for (slot in remainingAfterPrimary) {
            val champ = slot.champion ?: continue
            val secMatch = champ.secondaryRoles.firstOrNull { availableRoles.contains(it) }
            if (secMatch != null) {
                assignedMap[secMatch] = champ
                availableRoles.remove(secMatch)
                slot.assignedRole = secMatch
            } else {
                remainingAfterSecondary.add(slot)
            }
        }

        // 4. Asignar roles restantes a campeones no asignados
        for (slot in remainingAfterSecondary) {
            val champ = slot.champion ?: continue
            val fallback = availableRoles.firstOrNull() ?: continue
            assignedMap[fallback] = champ
            availableRoles.remove(fallback)
            slot.assignedRole = fallback
        }

        return assignedMap
    }
}
