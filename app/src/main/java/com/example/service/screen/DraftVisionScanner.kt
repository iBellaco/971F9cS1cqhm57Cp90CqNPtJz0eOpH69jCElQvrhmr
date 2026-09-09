package com.example.service.screen

import android.graphics.Bitmap
import android.graphics.Rect
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.util.AppLogger
import com.example.util.ImageHashMatcher
import com.example.util.SummonerSpellDetector
import com.example.util.VisualEvaluation
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await
import java.util.Locale

enum class DiagnosticStatus {
    CONFIRMADO,
    RECHAZADO,
    AMBIGUO,
    VACIO
}

data class SlotDiagnostic(
    val slotIndex: Int,
    val isAlly: Boolean,
    val roiRect: Rect,
    val candidate1: Champion?,
    val score1: Float,
    val candidate2: Champion?,
    val score2: Float,
    val margin: Float,
    val ocrChampion: Champion?,
    val finalChampion: Champion?,
    val status: DiagnosticStatus,
    val reason: String
) {
    fun toFormattedString(): String {
        val team = if (isAlly) "Aliado" else "Enemigo"
        return """
            [$team Slot $slotIndex]
            ROI: ${roiRect.left},${roiRect.top} → ${roiRect.right},${roiRect.bottom}
            Candidato #1: ${candidate1?.name ?: "Ninguno"} (Score: ${"%.2f".format(Locale.US, score1)})
            Candidato #2: ${candidate2?.name ?: "Ninguno"} (Score: ${"%.2f".format(Locale.US, score2)})
            Margen: ${"%.2f".format(Locale.US, margin)}
            OCR: ${ocrChampion?.name ?: "Ninguno"}
            Estado: $status
            Razón: $reason
        """.trimIndent()
    }
}

data class ScannedSlotInfo(
    val slotIndex: Int,
    val isAlly: Boolean = true,
    var champion: Champion? = null,
    var explicitRole: LaneRole? = null,
    var assignedRole: LaneRole? = null,
    var confidencePercent: Int = 0,
    var isLikelyUnpicked: Boolean = false,
    var auditLog: String? = null,
    var summonerSpells: List<String> = emptyList()
)

data class TextBlockDiagnostic(
    val text: String,
    val rect: Rect,
    val isAlly: Boolean,
    val slotIndex: Int,
    val tag: String,
    val color: Int
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
    val diagnostics: List<SlotDiagnostic> = emptyList(),
    val allySummonerNamesBySlot: Map<Int, String> = emptyMap(),
    val allySpellsBySlot: Map<Int, List<String>> = emptyMap(),
    val enemySpellsBySlot: Map<Int, List<String>> = emptyMap(),
    val allySummonerNamesByRole: Map<LaneRole, String> = emptyMap(),
    val allySpellsByRole: Map<LaneRole, List<String>> = emptyMap(),
    val isSuccessful: Boolean,
    val statusMessage: String
)

object DraftVisionScanner {
    private const val TAG = "DraftVisionScanner"
    var overlayRect: android.graphics.Rect? = null
    
    var lastDebugBitmap = kotlinx.coroutines.flow.MutableStateFlow<android.graphics.Bitmap?>(null)
    var lastDiagnostics = kotlinx.coroutines.flow.MutableStateFlow<List<SlotDiagnostic>>(emptyList())
    var lastDetectedTexts = kotlinx.coroutines.flow.MutableStateFlow<List<TextBlockDiagnostic>>(emptyList())
    var lastDetectedSpells = kotlinx.coroutines.flow.MutableStateFlow<List<com.example.util.SummonerSpellDetector.SpellMatch>>(emptyList())
    
    // Configuración dinámica e interactiva de coordenadas de calibración (cálculos y ROIs)
    val calibrationConfig = kotlinx.coroutines.flow.MutableStateFlow(
        com.example.WildRiftApp.instance?.let { VisionCalibrationConfig.load(it) } ?: VisionCalibrationConfig()
    )

    fun updateCalibration(context: android.content.Context, newConfig: VisionCalibrationConfig) {
        calibrationConfig.value = newConfig
        VisionCalibrationConfig.save(context, newConfig)
        AppLogger.d(TAG, "Configuración de calibración actualizada y guardada")
    }

    fun resetCalibration(context: android.content.Context) {
        val def = VisionCalibrationConfig.reset(context)
        calibrationConfig.value = def
        AppLogger.d(TAG, "Configuración de calibración restablecida a valores por defecto")
    }

    private var recognizerInstance: com.google.mlkit.vision.text.TextRecognizer? = null

    // Memoria persistente de los carriles asignados a cada slot aliado (0..4)
    // En Wild Rift, el carril asignado a cada jugador es fijo durante toda la fase de selección
    private val allySlotRolesCache = mutableMapOf<Int, LaneRole>()
    // Memoria persistente de los nombres de invocador aliados (0..4)
    private val allySummonerNamesCache = mutableMapOf<Int, String>()

    // Filtros de estabilización temporal (anti-parpadeo y anti-oscilación)
    private class SlotTemporalFilter {
        private var lastConfirmedChampion: Champion? = null
        private var candidateCount: Int = 0
        private var currentCandidateId: String? = null
        private var emptyCount: Int = 0
        private var isOcrLocked: Boolean = false

        fun process(candidate: Champion?, isOcr: Boolean, score: Float): Champion? {
            if (isOcr && candidate != null) {
                lastConfirmedChampion = candidate
                isOcrLocked = true
                candidateCount = 5
                emptyCount = 0
                return candidate
            }

            if (candidate == null) {
                emptyCount++
                val maxEmpty = if (isOcrLocked) 4 else 2
                if (emptyCount >= maxEmpty) {
                    lastConfirmedChampion = null
                    isOcrLocked = false
                    candidateCount = 0
                    currentCandidateId = null
                }
                return lastConfirmedChampion
            }

            emptyCount = 0

            // Si coincide con el campeón ya confirmado, mantener estable
            if (candidate.id == lastConfirmedChampion?.id) {
                candidateCount = (candidateCount + 1).coerceAtMost(10)
                return lastConfirmedChampion
            }

            // Si es un candidato nuevo de score muy alto o si se repite en 2 frames seguidos
            if (candidate.id == currentCandidateId) {
                candidateCount++
            } else {
                currentCandidateId = candidate.id
                candidateCount = 1
            }

            if (score >= 0.70f || candidateCount >= 2 || lastConfirmedChampion == null) {
                lastConfirmedChampion = candidate
                isOcrLocked = false
                return candidate
            }

            return lastConfirmedChampion
        }

        fun reset() {
            lastConfirmedChampion = null
            candidateCount = 0
            currentCandidateId = null
            emptyCount = 0
            isOcrLocked = false
        }
    }

    private val allySlotFilters = Array(5) { SlotTemporalFilter() }
    private val enemySlotFilters = Array(5) { SlotTemporalFilter() }

    fun resetSlotMemory() {
        allySlotRolesCache.clear()
        allySummonerNamesCache.clear()
        allySlotFilters.forEach { it.reset() }
        enemySlotFilters.forEach { it.reset() }
        lastDetectedTexts.value = emptyList()
        lastDetectedSpells.value = emptyList()
        AppLogger.d(TAG, "Memoria de roles, invocadores y diagnósticos reiniciada")
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

        val calib = calibrationConfig.value

        // 5 slots para aliados y 5 slots para enemigos
        val allySlots = (0..4).map { ScannedSlotInfo(slotIndex = it, isAlly = true) }
        val enemySlots = (0..4).map { ScannedSlotInfo(slotIndex = it, isAlly = false) }
        val detectedWords = mutableListOf<String>()
        var userDetectedLane: LaneRole? = null
        var userSlotIndex: Int? = null
        val allySlotTexts = Array(5) { mutableListOf<Pair<String, Rect?>>() }
        val enemySlotTexts = Array(5) { mutableListOf<Pair<String, Rect?>>() }
        val allyOcrChampions = Array<Champion?>(5) { null }
        val enemyOcrChampions = Array<Champion?>(5) { null }
        val textDiagnosticsList = mutableListOf<TextBlockDiagnostic>()

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
                    // Ignorar cualquier texto generado por el overlay de depuración
                    if (text.contains("[") || text.contains("]") ||
                        text.contains("VISUAL", ignoreCase = true) || text.contains("VIS:", ignoreCase = true) ||
                        text.contains("OCR", ignoreCase = true) || text.contains("INVOCADOR", ignoreCase = true) ||
                        text.contains("CAMPEÓN", ignoreCase = true) || text.contains("CAMPEON", ignoreCase = true) ||
                        text.contains("LÍNEA", ignoreCase = true) || text.contains("LINEA", ignoreCase = true) ||
                        text.contains("RIVAL", ignoreCase = true) || text.contains("VACÍO", ignoreCase = true) ||
                        text.contains("VACIO", ignoreCase = true) || text.contains("CONFIRMADO", ignoreCase = true) ||
                        text.contains("AMBIGUO", ignoreCase = true) || text.contains("⚡") || text.contains("🐛") ||
                        text.contains("Diagnóstico", ignoreCase = true) || text.contains("Diagnostico", ignoreCase = true) ||
                        text.contains("Score", ignoreCase = true) || text.matches(Regex(".*\\b\\d+\\.\\d+\\b.*"))) continue
                    
                    val box = line.boundingBox
                    if (box != null && overlayRect != null) {
                        if (android.graphics.Rect.intersects(box, overlayRect!!)) {
                            continue // Ignorar texto que cae dentro de la ventana flotante
                        }
                    }
                    
                    detectedWords.add(text)
                    val centerY = box?.centerY() ?: 0
                    val centerX = box?.centerX() ?: 0
                    val xRatio = centerX.toFloat() / width.toFloat()

                    val linesInText = text.split("\n").map { it.trim() }.filter { it.isNotBlank() }
                    val totalLines = linesInText.size
                    val baseBox = box ?: Rect(0, 0, 10, 10)
                    val lineH = if (totalLines > 0) baseBox.height().toFloat() / totalLines else baseBox.height().toFloat()

                    for ((idx, subline) in linesInText.withIndex()) {
                        if (subline.length < 2) continue
                        val subCenterY = if (totalLines > 1) {
                            (baseBox.top + (idx + 0.5f) * lineH).toInt()
                        } else {
                            centerY
                        }
                        val subYRatio = subCenterY.toFloat() / height.toFloat()
                        val subBox = if (totalLines > 1) {
                            Rect(baseBox.left, (baseBox.top + idx * lineH).toInt(), baseBox.right, (baseBox.top + (idx + 1) * lineH).toInt())
                        } else {
                            baseBox
                        }

                        // Ignorar barra de bans y botones
                        if (subYRatio < 0.110f || subYRatio > 0.820f) continue

                        // 1.1 COLUMNA ALIADA (Texto inmediatamente a la derecha del avatar)
                        if (xRatio in calib.allyOcrMinX..calib.allyOcrMaxX) {
                            var bestSlot = -1
                            var minDiff = 0.085f
                            for (s in 0..4) {
                                val diff = kotlin.math.abs(subYRatio - calib.allySlotYRatios[s])
                                if (diff < minDiff) {
                                    minDiff = diff
                                    bestSlot = s
                                }
                            }
                            if (bestSlot != -1) {
                                allySlotTexts[bestSlot].add(Pair(subline, subBox))
                            }
                        }
                        // 1.2 COLUMNA ENEMIGA (Texto inmediatamente a la izquierda del avatar rival)
                        else if (xRatio in calib.enemyOcrMinX..calib.enemyOcrMaxX) {
                            var bestSlot = -1
                            var minDiff = 0.085f
                            for (s in 0..4) {
                                val diff = kotlin.math.abs(subYRatio - calib.enemySlotYRatios[s])
                                if (diff < minDiff) {
                                    minDiff = diff
                                    bestSlot = s
                                }
                            }
                            if (bestSlot != -1) {
                                enemySlotTexts[bestSlot].add(Pair(subline, subBox))
                            }
                        }
                    }
                }
            }

            // Procesar textos aliados: Detección de Línea, Nombre de Invocador y Campeón
            for (i in 0..4) {
                val slot = allySlots[i]
                val entries = allySlotTexts[i].sortedBy { it.second?.top ?: 0 }

                for ((rawBlock, box) in entries) {
                    val safeBox = box ?: Rect(0, 0, 10, 10)
                    val sublines = rawBlock.split("\n").map { it.trim() }.filter { it.isNotBlank() }

                    for (line in sublines) {
                        if (DraftValidationLayer.isNoiseText(line)) continue

                        // A) Rol / Línea explícito
                        val role = DraftValidationLayer.parseRoleFromText(line)
                        if (role != null) {
                            slot.explicitRole = role
                            allySlotRolesCache[i] = role
                            userDetectedLane = role
                            userSlotIndex = i
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = line,
                                    rect = safeBox,
                                    isAlly = true,
                                    slotIndex = i,
                                    tag = "LÍNEA: ${role.shortName}",
                                    color = android.graphics.Color.CYAN
                                )
                            )
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Línea: ${role.shortName}")
                            continue
                        }

                        // B) Texto de campeón detectado por OCR (100% autoritativo)
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            val isExact = line.trim().equals(matched.name, ignoreCase = true) ||
                                          ChampionNameResolver.normalize(line) == ChampionNameResolver.normalize(matched.name)
                            val existing = allyOcrChampions[i]
                            val existingIsExact = existing != null && allySlotTexts[i].any {
                                it.first.trim().equals(existing.name, ignoreCase = true) ||
                                ChampionNameResolver.normalize(it.first) == ChampionNameResolver.normalize(existing.name)
                            }

                            // No permitir que un nombre de invocador con subpalabra (ej: "WuK0ng Babadei")
                            // sobrescriba una coincidencia exacta de campeón (ej: "THRESH")
                            if (existing == null || (isExact && !existingIsExact)) {
                                allyOcrChampions[i] = matched
                                slot.champion = matched
                                slot.confidencePercent = 100
                                slot.isLikelyUnpicked = false

                                // La línea cambia por el nombre del campeón -> se conserva el carril que tenía asignado
                                if (allySlotRolesCache[i] != null) {
                                    slot.explicitRole = allySlotRolesCache[i]
                                }
                                textDiagnosticsList.add(
                                    TextBlockDiagnostic(
                                        text = line,
                                        rect = safeBox,
                                        isAlly = true,
                                        slotIndex = i,
                                        tag = "CAMPEÓN: ${matched.name}",
                                        color = android.graphics.Color.GREEN
                                    )
                                )
                                AppLogger.d(TAG, "OCR Aliado Slot $i -> Campeón 100%: ${matched.name}")
                            }
                            continue // Un nombre de campeón jamás debe pasar a nombre de invocador
                        }

                        // C) Nombre de invocador (siempre que no sea rol ni campeón)
                        if (line.length in 2..28 && !DraftValidationLayer.isNoiseText(line)) {
                            // Doble verificación: si la línea contiene un campeón, no registrar como invocador
                            if (ChampionNameResolver.findChampionInText(line, allChamps) == null) {
                                allySummonerNamesCache[i] = line
                                textDiagnosticsList.add(
                                    TextBlockDiagnostic(
                                        text = line,
                                        rect = safeBox,
                                        isAlly = true,
                                        slotIndex = i,
                                        tag = "INVOCADOR",
                                        color = android.graphics.Color.argb(255, 120, 180, 255)
                                    )
                                )
                                AppLogger.d(TAG, "OCR Aliado Slot $i -> Invocador: $line")
                            }
                        }
                    }
                }
                
                if (allyOcrChampions[i] != null) {
                    slot.isLikelyUnpicked = false
                }
            }

            // Para el lado rival: Analizamos el texto de cada slot.
            // En Wild Rift, cuando un rival fija o selecciona un campeón, el nombre aparece en texto:
            // "ANNIE", "VOLIBEAR", "KHA'ZIX", "ASHE", etc.
            for (i in 0..4) {
                enemySlots[i].isLikelyUnpicked = false
                for ((rawBlock, box) in enemySlotTexts[i].sortedBy { it.second?.top ?: 0 }) {
                    val safeBox = box ?: Rect(0, 0, 10, 10)
                    val sublines = rawBlock.split("\n").map { it.trim() }.filter { it.isNotBlank() }
                    for (line in sublines) {
                        if (DraftValidationLayer.isNoiseText(line)) continue
                        val low = line.lowercase(Locale.ROOT)
                        if (low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador")) {
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = line,
                                    rect = safeBox,
                                    isAlly = false,
                                    slotIndex = i,
                                    tag = "JUGADOR",
                                    color = android.graphics.Color.DKGRAY
                                )
                            )
                            continue
                        }

                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            val isExact = line.trim().equals(matched.name, ignoreCase = true) ||
                                          ChampionNameResolver.normalize(line) == ChampionNameResolver.normalize(matched.name)
                            val existing = enemyOcrChampions[i]
                            val existingIsExact = existing != null && enemySlotTexts[i].any {
                                it.first.trim().equals(existing.name, ignoreCase = true) ||
                                ChampionNameResolver.normalize(it.first) == ChampionNameResolver.normalize(existing.name)
                            }

                            if (existing == null || (isExact && !existingIsExact)) {
                                enemyOcrChampions[i] = matched
                                enemySlots[i].champion = matched
                                enemySlots[i].confidencePercent = 100
                                enemySlots[i].isLikelyUnpicked = false
                                textDiagnosticsList.add(
                                    TextBlockDiagnostic(
                                        text = line,
                                        rect = safeBox,
                                        isAlly = false,
                                        slotIndex = i,
                                        tag = "RIVAL: ${matched.name}",
                                        color = android.graphics.Color.RED
                                    )
                                )
                                AppLogger.d(TAG, "OCR Rival Slot $i -> Campeón 100%: ${matched.name}")
                            }
                            continue
                        }
                        // Nota: Se omite intencionalmente el escaneo de nombres de invocador rivales, procesando únicamente el nombre de campeón.
                    }
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error durante el análisis OCR", e)
        }

        // -----------------------------------------------------------------------------------------
        // PASO 2: ASIGNACIÓN DE ROLES EXPLÍCITOS (Solo cuando hay texto comprobado)
        // -----------------------------------------------------------------------------------------
        for (i in 0..4) {
            val slot = allySlots[i]
            if (slot.explicitRole == null) {
                slot.explicitRole = allySlotRolesCache[i]
            }
        }
        // No forzamos roles naturales aquí, DraftValidationLayer se encarga de usar el índice del slot si hace falta.

        // -----------------------------------------------------------------------------------------
        // PASO 3: SCANNER V2 CON ROI CALIBRADA Y RECONOCIMIENTO VISUAL PURO
        // -----------------------------------------------------------------------------------------
        // Calibración geométrica precisa del HUD de Wild Rift utilizando la configuración dinámica:
        val avatarDiameter = (height * calib.avatarDiameterRatio).toInt().coerceAtLeast(32)
        
        // Ajuste dinámico basado en el aspect ratio para soportar 16:9 y >= 19:9
        val aspectRatio = width.toFloat() / height.toFloat()
        val isUltraWide = aspectRatio > 2.0f
        
        // Posición horizontal calibrada de los avatares (permite ajuste fino en tiempo real):
        val allyAvatarCenterX = (width * calib.allyAvatarCenterX).toInt()
        val enemyAvatarCenterX = (width * calib.enemyAvatarCenterX).toInt()

        // Ratios verticales (eje Y) independientes para ambos lados:
        val allySlotYRatios = calib.allySlotYRatios
        val enemySlotYRatios = calib.enemySlotYRatios
        val diagnosticsList = mutableListOf<SlotDiagnostic>()

        // 3.1 Aliados
        for (i in 0..4) {
            val slot = allySlots[i]
            val yCenter = (height * allySlotYRatios[i]).toInt()
            val startX = (allyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)
            val roiRect = Rect(startX, startY, startX + avatarDiameter, startY + avatarDiameter)

            val ocrChamp = allyOcrChampions[i]
            var eval = VisualEvaluation(null, 0f, null, 0f, 0f, false, "VACIO", "Error al procesar")

            try {
                val crop = Bitmap.createBitmap(bitmap, startX, startY, avatarDiameter, avatarDiameter)
                eval = ImageHashMatcher.evaluateVisualMatch(crop, allChamps, isAlly = true)
                crop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error comparando avatar aliado slot $i: ${e.message}")
            }

            var rawCandidate: Champion? = null
            var isOcr = false
            var candidateScore = 0f
            var diagReason = eval.reason

            // LÓGICA DE DETECCIÓN ALIADA:
            // 1. TEXTO OCR DE CAMPEÓN: 100% autoritativo.
            // 2. AVATAR (IMAGEN): Si no hay OCR, se utiliza reconocimiento visual de plantilla con estabilidad temporal.
            if (ocrChamp != null) {
                rawCandidate = ocrChamp
                isOcr = true
                candidateScore = 1.0f
                diagReason = if (eval.candidate1?.id == ocrChamp.id) {
                    "Confirmado 100% (Nombre OCR y Avatar coinciden: ${ocrChamp.name})"
                } else {
                    "100% Certeza: Nombre OCR detectado (${ocrChamp.name})"
                }
            } else if (eval.isConfirmed && eval.candidate1 != null && eval.score1 >= 0.45f) {
                rawCandidate = eval.candidate1
                isOcr = false
                candidateScore = eval.score1
                diagReason = "Preselección aliada en avatar (${eval.candidate1.name}, score ${"%.2f".format(Locale.US, eval.score1)})"
            }

            // Aplicar filtro de estabilización temporal para evitar oscilación y parpadeo
            val finalChamp = allySlotFilters[i].process(rawCandidate, isOcr, candidateScore)
            val finalConfidence = when {
                finalChamp == null -> 0
                isOcr && finalChamp.id == ocrChamp?.id -> 100
                else -> ((candidateScore * 100).toInt()).coerceIn(65, 95)
            }
            val diagStatus = when {
                finalChamp != null -> DiagnosticStatus.CONFIRMADO
                eval.status == "AMBIGUO" -> DiagnosticStatus.AMBIGUO
                else -> DiagnosticStatus.VACIO
            }

            slot.champion = finalChamp
            slot.confidencePercent = finalConfidence

            val diagnostic = SlotDiagnostic(
                slotIndex = i,
                isAlly = true,
                roiRect = roiRect,
                candidate1 = eval.candidate1,
                score1 = eval.score1,
                candidate2 = eval.candidate2,
                score2 = eval.score2,
                margin = eval.margin,
                ocrChampion = ocrChamp,
                finalChampion = finalChamp,
                status = diagStatus,
                reason = diagReason
            )
            diagnosticsList.add(diagnostic)
            AppLogger.d(TAG, diagnostic.toFormattedString())
        }

        // 3.2 Enemigos
        for (i in 0..4) {
            val slot = enemySlots[i]
            val yCenter = (height * enemySlotYRatios[i]).toInt()
            val startX = (enemyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)
            val roiRect = Rect(startX, startY, startX + avatarDiameter, startY + avatarDiameter)

            val ocrChamp = enemyOcrChampions[i]
            var eval = VisualEvaluation(null, 0f, null, 0f, 0f, false, "VACIO", "Error al procesar")

            try {
                val crop = Bitmap.createBitmap(bitmap, startX, startY, avatarDiameter, avatarDiameter)
                eval = ImageHashMatcher.evaluateVisualMatch(crop, allChamps, isAlly = false)
                crop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error comparando avatar enemigo slot $i: ${e.message}")
            }

            var rawCandidate: Champion? = null
            var isOcr = false
            var candidateScore = 0f
            var diagReason = eval.reason

            // LADO RIVAL: Prioridad 100% OCR si el nombre del campeón fue detectado en texto.
            // Si aún no hay texto, evaluación visual con filtro de estabilización temporal para eliminar parpadeos.
            if (ocrChamp != null) {
                rawCandidate = ocrChamp
                isOcr = true
                candidateScore = 1.0f
                diagReason = "Confirmado 100% por nombre OCR (${ocrChamp.name})"
            } else if (eval.isConfirmed && eval.candidate1 != null && eval.score1 >= 0.48f && eval.margin >= 0.018f) {
                rawCandidate = eval.candidate1
                isOcr = false
                candidateScore = eval.score1
                diagReason = "Preselección rival en avatar (${eval.candidate1.name}, score ${"%.2f".format(Locale.US, eval.score1)})"
            }

            // Aplicar filtro de estabilización temporal para evitar oscilación y parpadeo
            val finalChamp = enemySlotFilters[i].process(rawCandidate, isOcr, candidateScore)
            val finalConfidence = when {
                finalChamp == null -> 0
                isOcr && finalChamp.id == ocrChamp?.id -> 100
                else -> ((candidateScore * 100).toInt()).coerceIn(65, 95)
            }
            val diagStatus = when {
                finalChamp != null -> DiagnosticStatus.CONFIRMADO
                eval.status == "AMBIGUO" -> DiagnosticStatus.AMBIGUO
                else -> DiagnosticStatus.VACIO
            }

            slot.champion = finalChamp
            slot.confidencePercent = finalConfidence

            val diagnostic = SlotDiagnostic(
                slotIndex = i,
                isAlly = false,
                roiRect = roiRect,
                candidate1 = eval.candidate1,
                score1 = eval.score1,
                candidate2 = eval.candidate2,
                score2 = eval.score2,
                margin = eval.margin,
                ocrChampion = ocrChamp,
                finalChampion = finalChamp,
                status = diagStatus,
                reason = diagReason
            )
            diagnosticsList.add(diagnostic)
            AppLogger.d(TAG, diagnostic.toFormattedString())
        }

        // -----------------------------------------------------------------------------------------
        // PASO 3.3: ESCANEO DE HECHIZOS DE INVOCADOR ALIADOS (SUMMONER SPELLS)
        // -----------------------------------------------------------------------------------------
        // En Wild Rift, los hechizos aliados se sitúan en el extremo izquierdo de la pantalla:
        val spellSize = (height * calib.spellSizeRatio).toInt().coerceAtLeast(18)
        val spellLeft = (width * calib.spellLeftRatio).toInt().coerceIn(0, width - spellSize)
        val spellRight = spellLeft + spellSize

        val allySpellsMap = mutableMapOf<Int, MutableList<String>>()
        val detectedSpellsList = mutableListOf<com.example.util.SummonerSpellDetector.SpellMatch>()

        for (i in 0..4) {
            val yCenter = (height * (allySlotYRatios[i] + calib.spellYOffsetRatio)).toInt()

            val spell1Top = (yCenter - spellSize - (height * 0.003f).toInt()).coerceIn(0, height - spellSize)
            val spell1Bottom = spell1Top + spellSize
            val spell2Top = (yCenter + (height * 0.003f).toInt()).coerceIn(0, height - spellSize)
            val spell2Bottom = spell2Top + spellSize

            val allyCandidateRects = listOf(
                // Hechizo 1 (arriba)
                Rect(spellLeft, spell1Top, spellRight, spell1Bottom),
                // Hechizo 2 (abajo)
                Rect(spellLeft, spell2Top, spellRight, spell2Bottom)
            )

            val allySlotSpells = mutableListOf<String>()
            for (r in allyCandidateRects) {
                if (allySlotSpells.size >= 2) break
                try {
                    val crop = Bitmap.createBitmap(bitmap, r.left, r.top, r.width(), r.height())
                    val match = SummonerSpellDetector.detectSpell(crop, r)
                    crop.recycle()
                    if (match != null && !allySlotSpells.contains(match.spellName)) {
                        allySlotSpells.add(match.spellName)
                        detectedSpellsList.add(match)
                    }
                } catch (_: Exception) {}
            }
            if (allySlotSpells.isNotEmpty()) {
                allySpellsMap[i] = allySlotSpells
            }
            allySlots[i].summonerSpells = allySlotSpells
        }

        // -----------------------------------------------------------------------------------------
        // PASO 4: RESOLUCIÓN Y ASIGNACIÓN DETERMINISTA DE CARRILES (ZERO-CONFUSION)
        // -----------------------------------------------------------------------------------------
        // 4.1 Aliados: Resolver roles combinando slots explícitos (OCR/Smite) y afinidad de campeones detectados
        val validAllySlots = allySlots.filter { it.champion != null }
        val allyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validAllySlots, allChamps, auditList)
        val alliesMap = allyResolved.assignments.toMutableMap()

        // Asignar cualquier campeón en slot explícito que no haya entrado en validAllySlots o haya quedado sin rol
        for (i in 0..4) {
            val slot = allySlots[i]
            val champ = slot.champion
            val explicit = slot.explicitRole
            if (champ != null && explicit != null && !alliesMap.containsKey(explicit)) {
                alliesMap[explicit] = champ
            }
        }

        // 4.2 Enemigos: Asignación validada por roles primarios y secundarios de los picks seleccionados
        val validEnemySlots = enemySlots.filter { it.champion != null }
        val enemyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validEnemySlots, allChamps, auditList, isAllyTeam = false)
        val enemiesMap = enemyResolved.assignments

        // Deduplicación: Un campeón aliado jamás puede aparecer en el equipo enemigo
        val allyChampIds = alliesMap.values.map { it.id }.toSet()
        val finalEnemiesMap = enemiesMap.filterNot { allyChampIds.contains(it.value.id) }
        val enemyConfidences = enemyResolved.confidences.filterKeys { finalEnemiesMap.containsKey(it) }

        // Mapear nombres de invocador y hechizos al rol final asignado (o rol por defecto del slot)
        val defaultRolesList = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val allySummonerNamesByRole = mutableMapOf<LaneRole, String>()
        val allySpellsByRole = mutableMapOf<LaneRole, List<String>>()

        for (i in 0..4) {
            val slot = allySlots[i]
            val role = slot.assignedRole ?: slot.explicitRole ?: defaultRolesList.getOrNull(i)
            if (role != null) {
                val sName = allySummonerNamesCache[i]
                if (!sName.isNullOrBlank()) {
                    allySummonerNamesByRole[role] = sName
                }
                val sp = allySpellsMap[i]
                if (!sp.isNullOrEmpty()) {
                    allySpellsByRole[role] = sp
                }
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

        try {
            lastDebugBitmap.value = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, false)
        } catch (_: Throwable) {}
        lastDiagnostics.value = diagnosticsList
        lastDetectedTexts.value = textDiagnosticsList
        lastDetectedSpells.value = detectedSpellsList

        return DraftScanResult(
            allies = allyChampsList,
            enemies = enemyChampsList,
            alliesByRole = alliesMap,
            enemiesByRole = finalEnemiesMap,
            enemyConfidencesByRole = enemyConfidences,
            detectedRole = userDetectedLane,
            detectedRawWords = detectedWords,
            discrepancies = auditList,
            diagnostics = diagnosticsList,
            allySummonerNamesBySlot = allySummonerNamesCache.toMap(),
            allySpellsBySlot = allySpellsMap.mapValues { it.value.toList() },
            enemySpellsBySlot = emptyMap(),
            allySummonerNamesByRole = allySummonerNamesByRole,
            allySpellsByRole = allySpellsByRole,
            isSuccessful = total > 0,
            statusMessage = statusMsg
        )
    }
}
