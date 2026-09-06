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

        // Asegurar precarga de los 141 avatares locales para comparativa inmediata
        com.example.WildRiftApp.instance?.let {
            com.example.util.ChampionHashes.ensureLoaded(it)
        }

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
        val allyOcrChampions = Array<Champion?>(5) { null }
        val enemyOcrChampions = Array<Champion?>(5) { null }

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

                    // Ignorar la barra de bans superior (Y < 0.075) y botones del fondo (Y > 0.85)
                    if (yRatio < 0.075f || yRatio > 0.85f) continue

                    // Determinar el índice de slot vertical (0..4) con precisión equidistante
                    val slotIndex = when {
                        yRatio < 0.250f -> 0
                        yRatio < 0.395f -> 1
                        yRatio < 0.540f -> 2
                        yRatio < 0.685f -> 3
                        else -> 4
                    }

                    // 1.1 COLUMNA ALIADA (Extremo Izquierdo: X entre 0.01 y 0.35)
                    if (xRatio in 0.01f..0.35f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (Extremo Derecho: X entre 0.65 y 0.99)
                    else if (xRatio in 0.65f..0.99f) {
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

                    // B) Texto de campeón detectado por OCR (secundario)
                    if (allyOcrChampions[i] == null) {
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            allyOcrChampions[i] = matched
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Texto detectado: ${matched.name}")
                        }
                    }
                }
            }

            // Procesar textos enemigos
            for (i in 0..4) {
                val slot = enemySlots[i]
                val lines = enemySlotTexts[i]

                for (line in lines) {
                    if (enemyOcrChampions[i] == null) {
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            enemyOcrChampions[i] = matched
                            AppLogger.d(TAG, "OCR Enemigo Slot $i -> Texto detectado: ${matched.name}")
                        }
                    }
                }

                // Si no hay campeón y solo hay textos genéricos ("Jugador X"), marcar como unpicked
                val isGeneric = lines.isEmpty() || lines.all { l ->
                    val low = l.lowercase(Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank()
                }
                if (isGeneric) {
                    slot.isLikelyUnpicked = true
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error durante el análisis OCR", e)
        }

        // -----------------------------------------------------------------------------------------
        // PASO 2: ASIGNACIÓN DE ROLES ESTÁNDAR POR SLOT (TOP=0, JUNGLE=1, MID=2, ADC=3, SUP=4)
        // -----------------------------------------------------------------------------------------
        for (i in 0..4) {
            val slot = allySlots[i]
            if (slot.explicitRole == null) {
                slot.explicitRole = allySlotRolesCache[i] ?: when (i) {
                    0 -> LaneRole.TOP
                    1 -> LaneRole.JUNGLE
                    2 -> LaneRole.MID
                    3 -> LaneRole.ADC
                    4 -> LaneRole.SUPPORT
                    else -> null
                }
            }
        }
        for (i in 0..4) {
            val slot = enemySlots[i]
            if (slot.explicitRole == null) {
                slot.explicitRole = when (i) {
                    0 -> LaneRole.TOP
                    1 -> LaneRole.JUNGLE
                    2 -> LaneRole.MID
                    3 -> LaneRole.ADC
                    4 -> LaneRole.SUPPORT
                    else -> null
                }
            }
        }

        // -----------------------------------------------------------------------------------------
        // PASO 3: RECONOCIMIENTO VISUAL PRIORITARIO CONTRA LOS 141 AVATARES LOCALES (100% PRECISIÓN)
        // -----------------------------------------------------------------------------------------
        val avatarDiameter = (height * 0.120f).toInt().coerceAtLeast(32)
        val allyAvatarCenterX = (height * 0.131f).toInt().coerceAtLeast(16)
        val enemyAvatarCenterX = (width - (height * 0.074f)).toInt().coerceIn(0, width)

        // 3.1 Aliados
        for (i in 0..4) {
            val slot = allySlots[i]
            val yCenter = (height * (0.178f + (i * 0.145f))).toInt()
            val startX = (allyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)

            var visualMatched = false
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
                    visualMatched = true
                    AppLogger.d(TAG, "Avatar Aliado Slot $i -> ${visualMatch.champion.name} (Confianza: ${slot.confidencePercent}%)")
                }
                crop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error comparando avatar aliado slot $i: ${e.message}")
            }

            // Cross-validación con OCR: si ambos coinciden -> 100% de confianza
            val ocrChamp = allyOcrChampions[i]
            if (ocrChamp != null) {
                if (visualMatched && slot.champion?.id == ocrChamp.id) {
                    slot.confidencePercent = 100
                } else if (!visualMatched) {
                    slot.champion = ocrChamp
                    slot.confidencePercent = 85
                }
            }
        }

        // 3.2 Enemigos (analizar avatares locales; cascos espartanos vacíos se descartan automáticamente)
        for (i in 0..4) {
            val slot = enemySlots[i]
            val yCenter = (height * (0.178f + (i * 0.145f))).toInt()
            val startX = (enemyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)

            var visualMatched = false
            try {
                val crop = Bitmap.createBitmap(bitmap, startX, startY, avatarDiameter, avatarDiameter)
                val visualMatch = ImageHashMatcher.findBestVisualMatch(
                    crop,
                    allChamps,
                    preferredRole = slot.explicitRole,
                    isAlly = false
                )

                if (visualMatch != null) {
                    slot.champion = visualMatch.champion
                    slot.confidencePercent = visualMatch.confidencePercent
                    visualMatched = true
                    AppLogger.d(TAG, "Avatar Enemigo Slot $i -> ${visualMatch.champion.name} (Confianza: ${slot.confidencePercent}%)")
                } else {
                    slot.champion = null
                }
                crop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error comparando avatar enemigo slot $i: ${e.message}")
            }

            // Cross-validación con OCR para enemigos (solo si el slot no está vacío/unpicked)
            val ocrChamp = enemyOcrChampions[i]
            if (ocrChamp != null && !slot.isLikelyUnpicked) {
                if (visualMatched && slot.champion?.id == ocrChamp.id) {
                    slot.confidencePercent = 100
                } else if (!visualMatched) {
                    slot.champion = ocrChamp
                    slot.confidencePercent = 85
                }
            }
        }

        // -----------------------------------------------------------------------------------------
        // PASO 4: RESOLUCIÓN Y ASIGNACIÓN DETERMINISTA DE CARRILES (ZERO-CONFUSION)
        // -----------------------------------------------------------------------------------------
        // 4.1 Aliados: Resolver roles combinando slots explícitos (OCR/Smite) y afinidad de campeones detectados
        val validAllySlots = allySlots.filter { it.champion != null }
        val allyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validAllySlots, allChamps, auditList)
        val alliesMap = allyResolved.assignments.toMutableMap()

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
