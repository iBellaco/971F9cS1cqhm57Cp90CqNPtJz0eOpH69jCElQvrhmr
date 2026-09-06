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
    val isAlly: Boolean = true,
    var champion: Champion? = null,
    var explicitRole: LaneRole? = null,
    var assignedRole: LaneRole? = null,
    var confidencePercent: Int = 0,
    var isLikelyUnpicked: Boolean = false,
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

    // Memoria persistente de los carriles asignados a cada slot aliado (0..4)
    // En Wild Rift, el carril asignado a cada jugador es fijo durante toda la fase de selección
    private val allySlotRolesCache = mutableMapOf<Int, LaneRole>()

    fun resetSlotMemory() {
        allySlotRolesCache.clear()
        AppLogger.d(TAG, "Memoria de roles de slot reiniciada")
    }

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

        val allySlotTexts = Array(5) { mutableListOf<String>() }
        val enemySlotTexts = Array(5) { mutableListOf<String>() }

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

                    // 1.1 COLUMNA ALIADA (Extremo Izquierdo: X entre 0.02 y 0.32)
                    if (xRatio in 0.02f..0.32f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (Extremo Derecho: X entre 0.70 y 0.98)
                    else if (xRatio in 0.70f..0.98f) {
                        enemySlotTexts[slotIndex].add(text)
                    }
                }
            }

            // Procesar textos aliados
            for (i in 0..4) {
                val slot = allySlots[i]
                val lines = allySlotTexts[i]

                for (line in lines) {
                    // A) Rol explícito
                    val role = DraftValidationLayer.parseRoleFromText(line)
                    if (role != null) {
                        slot.explicitRole = role
                        allySlotRolesCache[i] = role
                        AppLogger.d(TAG, "OCR Aliado Slot $i -> Rol explícito: ${role.shortName}")

                        // Detección precisa de slot del usuario por palabras clave del jugador
                        val containsUserClues = lines.any { l ->
                            val low = l.lowercase(Locale.ROOT)
                            low.contains("diego") || low.contains("porcentaje") || low.contains("victoria") || low.contains("tasa")
                        }
                        if (containsUserClues) {
                            userDetectedLane = role
                            AppLogger.d(TAG, "Slot del usuario confirmado en $i -> ${role.shortName}")
                        }
                    }

                    // B) Campeón bloqueado por OCR
                    if (slot.champion == null) {
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            slot.champion = matched
                            slot.confidencePercent = 100
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Campeón bloqueado: ${matched.name}")
                        }
                    }
                }
            }

            // Procesar textos enemigos
            for (i in 0..4) {
                val slot = enemySlots[i]
                val lines = enemySlotTexts[i]

                for (line in lines) {
                    if (slot.champion == null) {
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            slot.champion = matched
                            slot.confidencePercent = 100
                            AppLogger.d(TAG, "OCR Enemigo Slot $i -> Campeón: ${matched.name}")
                        }
                    }
                }

                // Si no hay campeón y solo hay textos genéricos ("Jugador X"), marcar como unpicked
                if (slot.champion == null) {
                    val isGeneric = lines.isEmpty() || lines.all { l ->
                        val low = l.lowercase(Locale.ROOT)
                        low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank()
                    }
                    if (isGeneric) {
                        slot.isLikelyUnpicked = true
                    }
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error durante el análisis OCR", e)
        }

        // -----------------------------------------------------------------------------------------
        // PASO 2: ANÁLISIS DE ICONOS DE LÍNEA Y HECHIZOS (SMITE) PARA EL EQUIPO ALIADO
        // -----------------------------------------------------------------------------------------
        val avatarDiameter = (height * 0.118f).toInt().coerceAtLeast(32)
        val allyAvatarCenterX = (width * 0.072f).toInt()
        val enemyAvatarCenterX = (width * 0.928f).toInt()

        for (i in 0..4) {
            val slot = allySlots[i]
            val yCenter = (height * (0.185f + (i * 0.140f))).toInt()

            // Si el rol de este slot no se ha determinado aún:
            if (slot.explicitRole == null && allySlotRolesCache[i] != null) {
                slot.explicitRole = allySlotRolesCache[i]
            }

            if (slot.explicitRole == null) {
                // A) Detección de Hechizo Castigo (Smite) en el extremo izquierdo (X 0.005..0.040)
                try {
                    val spellW = (width * 0.035f).toInt().coerceAtLeast(16)
                    val spellH = (height * 0.080f).toInt().coerceAtLeast(16)
                    val spellX = (width * 0.005f).toInt().coerceIn(0, width - spellW)
                    val spellY = (yCenter - spellH / 2).coerceIn(0, height - spellH)
                    val spellCrop = Bitmap.createBitmap(bitmap, spellX, spellY, spellW, spellH)
                    if (ImageHashMatcher.detectSmiteSpell(spellCrop)) {
                        slot.explicitRole = LaneRole.JUNGLE
                        allySlotRolesCache[i] = LaneRole.JUNGLE
                        AppLogger.d(TAG, "Castigo/Smite detectado en slot $i -> Rol: JUNGLA (100% certeza)")
                    }
                    spellCrop.recycle()
                } catch (_: Exception) {}

                // B) Detección por Insignia de Rol en el borde inferior del avatar (a las 6 en punto)
                if (slot.explicitRole == null) {
                    try {
                        val badgeSize = (height * 0.038f).toInt().coerceAtLeast(16)
                        val badgeX = (allyAvatarCenterX - badgeSize / 2).coerceIn(0, width - badgeSize)
                        val badgeY = (yCenter + (avatarDiameter * 0.44f).toInt()).coerceIn(0, height - badgeSize)
                        val badgeCrop = Bitmap.createBitmap(bitmap, badgeX, badgeY, badgeSize, badgeSize)
                        val badgeRole = ImageHashMatcher.findRoleMatch(badgeCrop)
                        if (badgeRole != null) {
                            slot.explicitRole = badgeRole
                            allySlotRolesCache[i] = badgeRole
                            AppLogger.d(TAG, "Insignia de rol detectada en slot $i -> ${badgeRole.shortName}")
                        }
                        badgeCrop.recycle()
                    } catch (_: Exception) {}
                }

                // C) Detección por Cresta Dorada de Rol al costado derecho del avatar (X ~ 0.105..0.125)
                if (slot.explicitRole == null) {
                    try {
                        val crestSize = (height * 0.040f).toInt().coerceAtLeast(16)
                        val crestX = (width * 0.104f).toInt().coerceIn(0, width - crestSize)
                        val crestY = (yCenter - (crestSize * 0.4f).toInt()).coerceIn(0, height - crestSize)
                        val crestCrop = Bitmap.createBitmap(bitmap, crestX, crestY, crestSize, crestSize)
                        val crestRole = ImageHashMatcher.findRoleMatch(crestCrop)
                        if (crestRole != null) {
                            slot.explicitRole = crestRole
                            allySlotRolesCache[i] = crestRole
                            AppLogger.d(TAG, "Cresta dorada detectada en slot $i -> ${crestRole.shortName}")
                        }
                        crestCrop.recycle()
                    } catch (_: Exception) {}
                }
            }
        }

        // B) Si aún quedan slots aliados sin rol determinado, resolverlos por afinidad con los campeones presentes
        val allRolesList = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val assignedRoles = allySlots.mapNotNull { it.explicitRole }.toSet()
        val missingRoles = allRolesList.filterNot { assignedRoles.contains(it) }.toMutableList()

        val unassignedSlotsWithChamp = allySlots.filter { it.explicitRole == null && it.champion != null }
        for (slot in unassignedSlotsWithChamp) {
            val champ = slot.champion ?: continue
            val preferredRole = when {
                missingRoles.contains(champ.primaryRole) -> champ.primaryRole
                champ.secondaryRoles.any { missingRoles.contains(it) } -> champ.secondaryRoles.first { missingRoles.contains(it) }
                else -> null
            }
            if (preferredRole != null) {
                slot.explicitRole = preferredRole
                allySlotRolesCache[slot.slotIndex] = preferredRole
                missingRoles.remove(preferredRole)
                AppLogger.d(TAG, "Slot aliado ${slot.slotIndex} con ${champ.name} asignado por afinidad a ${preferredRole.shortName}")
            }
        }

        // C) Asignación residual para cualquier slot restante
        for (slot in allySlots) {
            if (slot.explicitRole == null && missingRoles.isNotEmpty()) {
                val assigned = missingRoles.removeAt(0)
                slot.explicitRole = assigned
                allySlotRolesCache[slot.slotIndex] = assigned
                AppLogger.d(TAG, "Slot aliado ${slot.slotIndex} asignado por descarte -> ${assigned.shortName}")
            }
        }

        // -----------------------------------------------------------------------------------------
        // PASO 3: RECONOCIMIENTO VISUAL DE ALTA PRECISIÓN (SOLO SI OCR NO DETECTÓ EL CAMPEÓN)
        // -----------------------------------------------------------------------------------------
        // 3.1 Aliados (preselección cuando el jugador aún no bloqueó su campeón)
        for (i in 0..4) {
            val slot = allySlots[i]
            // Si ya fue detectado por OCR, NO TOCAR (100% de certeza)
            if (slot.champion != null) continue

            val yCenter = (height * (0.185f + (i * 0.140f))).toInt()
            val startX = (allyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)

            try {
                val crop = Bitmap.createBitmap(bitmap, startX, startY, avatarDiameter, avatarDiameter)
                val visualMatch = ImageHashMatcher.findBestVisualMatch(
                    crop,
                    allChamps,
                    preferredRole = slot.explicitRole,
                    isAlly = true
                )

                if (visualMatch != null) {
                    slot.champion = visualMatch.champion
                    slot.confidencePercent = visualMatch.confidencePercent
                    AppLogger.d(TAG, "Avatar Aliado Preselección Slot $i -> ${visualMatch.champion.name} en ${slot.explicitRole?.shortName} (Confianza: ${slot.confidencePercent}%)")
                }
                crop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error comparando avatar aliado slot $i: ${e.message}")
            }
        }

        // 3.2 Enemigos (estricto: si no hay nombre en OCR y es unpicked/casco espartano, NO inventar campeón)
        for (i in 0..4) {
            val slot = enemySlots[i]
            // Si ya fue detectado por OCR (ej: Lulu, Varus, Olaf), NO TOCAR (100% de certeza)
            if (slot.champion != null) continue

            // Si el slot solo decía "Jugador X" o estaba vacío, es un casco espartano: mantener en null
            if (slot.isLikelyUnpicked) {
                slot.champion = null
                continue
            }

            val yCenter = (height * (0.185f + (i * 0.140f))).toInt()
            val startX = (enemyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)

            try {
                val crop = Bitmap.createBitmap(bitmap, startX, startY, avatarDiameter, avatarDiameter)
                val visualMatch = ImageHashMatcher.findBestVisualMatch(
                    crop,
                    allChamps,
                    preferredRole = slot.explicitRole,
                    isAlly = false
                )

                if (visualMatch != null && visualMatch.confidencePercent >= 80) {
                    slot.champion = visualMatch.champion
                    slot.confidencePercent = visualMatch.confidencePercent
                    AppLogger.d(TAG, "Avatar Enemigo Slot $i -> ${visualMatch.champion.name} (Confianza: ${slot.confidencePercent}%)")
                } else {
                    slot.champion = null
                }
                crop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error comparando avatar enemigo slot $i: ${e.message}")
            }
        }

        // -----------------------------------------------------------------------------------------
        // PASO 4: ASIGNACIÓN DETERMINISTA DE CARRILES (ZERO-CONFUSION)
        // -----------------------------------------------------------------------------------------
        // 4.1 Aliados: Mapeo directo y autoritativo 1 a 1 por slot detectado
        val alliesMap = mutableMapOf<LaneRole, Champion>()
        for (slot in allySlots) {
            val champ = slot.champion ?: continue
            val role = slot.explicitRole ?: continue
            alliesMap[role] = champ
            slot.assignedRole = role
        }

        // 4.2 Enemigos: Asignación validada por roles primarios y secundarios de los picks seleccionados
        val validEnemySlots = enemySlots.filter { it.champion != null }
        val enemyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validEnemySlots, allChamps, auditList)
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
