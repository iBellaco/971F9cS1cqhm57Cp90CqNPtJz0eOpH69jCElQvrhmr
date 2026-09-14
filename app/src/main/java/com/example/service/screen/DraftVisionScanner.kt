package com.example.service.screen

import android.graphics.Bitmap
import android.graphics.Rect
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.util.AppLogger
import com.example.util.SummonerSpellDetector
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

data class DraftPickTurn(
    val turnNumber: Int, // 1..10
    val isAlly: Boolean,
    val slotIndex: Int // 0..4
)

data class DraftScanResult(
    val allies: List<Champion>,
    val enemies: List<Champion>,
    val alliesBySlot: Map<Int, Champion> = emptyMap(),
    val enemiesBySlot: Map<Int, Champion> = emptyMap(),
    val alliesByRole: Map<LaneRole, Champion> = emptyMap(),
    val enemiesByRole: Map<LaneRole, Champion> = emptyMap(),
    val enemyConfidencesByRole: Map<LaneRole, Int> = emptyMap(),
    val detectedRole: LaneRole? = null,
    val userExplicitlyDetectedRole: LaneRole? = null,
    val detectedFirstPick: Boolean? = null,
    val isLastPickImageRecognized: Boolean = false,
    val isLastPickConfirmed: Boolean = false,
    val lastPickChampion: Champion? = null,
    val detectedRawWords: List<String> = emptyList(),
    val discrepancies: List<String> = emptyList(),
    val diagnostics: List<SlotDiagnostic> = emptyList(),
    val allySummonerNamesBySlot: Map<Int, String> = emptyMap(),
    val allySpellsBySlot: Map<Int, List<String>> = emptyMap(),
    val enemySpellsBySlot: Map<Int, List<String>> = emptyMap(),
    val allySummonerNamesByRole: Map<LaneRole, String> = emptyMap(),
    val allySpellsByRole: Map<LaneRole, List<String>> = emptyMap(),
    val isLegendaryRanked: Boolean = false,
    val isPreparationPhase: Boolean = false,
    val tenthPickLog: String? = null,
    val isSuccessful: Boolean,
    val statusMessage: String
)

object DraftVisionScanner {
    private const val TAG = "DraftVisionScanner"
    var overlayRect: android.graphics.Rect? = null
    val showCalibrationBoxes = kotlinx.coroutines.flow.MutableStateFlow(false)
    val debugVisualMatches = kotlinx.coroutines.flow.MutableStateFlow<Map<String, String>>(emptyMap())

    
    /**
     * Devuelve la secuencia real de los 10 turnos del Draft de Wild Rift:
     * - Si PRIMERA SELECCIÓN (Aliado elige primero):
     *   A1 -> E1 -> E2 -> A2 -> A3 -> E3 -> E4 -> A4 -> A5 -> E5 (Pick 10: Rival 5)
     * - Si SIN PRIMERA SELECCIÓN (Rival elige primero):
     *   E1 -> A1 -> A2 -> E2 -> E3 -> A3 -> A4 -> E4 -> E5 -> A5 (Pick 10: Aliado 5)
     */
    fun getDraftPickSequence(isFirstPick: Boolean): List<DraftPickTurn> {
        return if (isFirstPick) {
            listOf(
                DraftPickTurn(1, isAlly = true, slotIndex = 0),   // A1
                DraftPickTurn(2, isAlly = false, slotIndex = 0),  // E1
                DraftPickTurn(3, isAlly = false, slotIndex = 1),  // E2
                DraftPickTurn(4, isAlly = true, slotIndex = 1),   // A2
                DraftPickTurn(5, isAlly = true, slotIndex = 2),   // A3
                DraftPickTurn(6, isAlly = false, slotIndex = 2),  // E3
                DraftPickTurn(7, isAlly = false, slotIndex = 3),  // E4
                DraftPickTurn(8, isAlly = true, slotIndex = 3),   // A4
                DraftPickTurn(9, isAlly = true, slotIndex = 4),   // A5
                DraftPickTurn(10, isAlly = false, slotIndex = 4)  // E5
            )
        } else {
            listOf(
                DraftPickTurn(1, isAlly = false, slotIndex = 0),  // E1
                DraftPickTurn(2, isAlly = true, slotIndex = 0),   // A1
                DraftPickTurn(3, isAlly = true, slotIndex = 1),   // A2
                DraftPickTurn(4, isAlly = false, slotIndex = 1),  // E2
                DraftPickTurn(5, isAlly = false, slotIndex = 2),  // E3
                DraftPickTurn(6, isAlly = true, slotIndex = 2),   // A3
                DraftPickTurn(7, isAlly = true, slotIndex = 3),   // A4
                DraftPickTurn(8, isAlly = false, slotIndex = 3),  // E4
                DraftPickTurn(9, isAlly = false, slotIndex = 4),  // E5
                DraftPickTurn(10, isAlly = true, slotIndex = 4)   // A5
            )
        }
    }

    var calibrationConfig = VisionCalibrationConfig()
    val calibrationConfigFlow = kotlinx.coroutines.flow.MutableStateFlow(VisionCalibrationConfig())

    fun initCalibration(context: android.content.Context) {
        calibrationConfig = VisionCalibrationConfig.loadFromPrefs(context)
        calibrationConfigFlow.value = calibrationConfig
    }

    fun updateCalibration(context: android.content.Context, newConfig: VisionCalibrationConfig) {
        calibrationConfig = newConfig
        calibrationConfigFlow.value = newConfig
        newConfig.saveToPrefs(context)
    }

    fun resetCalibration(context: android.content.Context) {
        calibrationConfig = VisionCalibrationConfig()
        calibrationConfigFlow.value = calibrationConfig
        calibrationConfig.saveToPrefs(context)
    }

    private var recognizerInstance: com.google.mlkit.vision.text.TextRecognizer? = null

    // Memoria persistente de los carriles asignados a cada slot aliado (0..4)
    // En Wild Rift, el carril asignado a cada jugador es fijo durante toda la fase de selección
    private val allySlotRolesCache = mutableMapOf<Int, LaneRole>()
    // Memoria persistente de los nombres de invocador aliados (0..4)
    private val allySummonerNamesCache = mutableMapOf<Int, String>()
    // Memoria persistente del slot asignado al usuario
    private var cachedUserSlotIndex: Int? = null

    // Memoria persistente de campeones confirmados por slot para evitar que desaparezcan al terminar o transicionar
    private val allySlotConfirmedChampions = arrayOfNulls<Champion>(5)
    private val enemySlotConfirmedChampions = arrayOfNulls<Champion>(5)

    // Filtros de estabilización temporal (anti-parpadeo y anti-oscilación)
    private class SlotTemporalFilter {
        private var lastConfirmedChampion: Champion? = null

        fun process(candidate: Champion?, isOcr: Boolean, score: Float, persistentCache: Champion?): Champion? {
            if (candidate != null) {
                lastConfirmedChampion = candidate
                return candidate
            }
            if (persistentCache != null) {
                return persistentCache
            }
            return lastConfirmedChampion
        }

        fun reset() {
            lastConfirmedChampion = null
        }
    }

    private var isLegendaryRankedCache = false
    private val allySlotFilters = Array(5) { SlotTemporalFilter() }
    private val enemySlotFilters = Array(5) { SlotTemporalFilter() }

    fun resetSlotMemory() {
        isLegendaryRankedCache = false
        cachedUserSlotIndex = null
        allySlotRolesCache.clear()
        allySummonerNamesCache.clear()
        allySlotConfirmedChampions.fill(null)
        enemySlotConfirmedChampions.fill(null)
        allySlotFilters.forEach { it.reset() }
        enemySlotFilters.forEach { it.reset() }
        LocalVisionAnalyzer.resetTenthPickData()
        AppLogger.d(TAG, "Memoria de roles, invocadores y capturas de 10º pick reiniciada por completo")
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

    suspend fun scanDraftFromBitmap(
        bitmap: Bitmap,
        context: android.content.Context? = null,
        currentIsFirstPick: Boolean? = null,
        currentActiveRole: LaneRole? = null
    ): DraftScanResult {
        if (bitmap.isRecycled || bitmap.width < bitmap.height) {
            return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "Orientación no horizontal")
        }

        return try {
            val recognizer = getRecognizer() ?: return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "OCR no disponible")

        val width = bitmap.width
        val height = bitmap.height
        val allChamps = WildRiftRepository.champions
        val auditList = mutableListOf<String>()
        val defaultRolesList = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)

        val calib = calibrationConfig

        // 5 slots para aliados y 5 slots para enemigos
        val allySlots = (0..4).map { ScannedSlotInfo(slotIndex = it, isAlly = true) }
        val enemySlots = (0..4).map { ScannedSlotInfo(slotIndex = it, isAlly = false) }
        val detectedWords = mutableListOf<String>()
        var userDetectedLane: LaneRole? = null
        var userSlotIndex: Int? = null
        var userExplicitlyConfirmed = false
        var detectedFirstPick: Boolean? = null
        var isLastPickVisualRecognized = false
        var lastPickVisualConfidence = 0f
        var isTenthPickOcrFound = false
        var lastPickVisualChampion: Champion? = null
        val allySlotTexts = Array(5) { mutableListOf<Pair<String, Rect?>>() }
        val enemySlotTexts = Array(5) { mutableListOf<Pair<String, Rect?>>() }
        val allyOcrChampions = Array<Champion?>(5) { null }
        val enemyOcrChampions = Array<Champion?>(5) { null }
        val textDiagnosticsList = mutableListOf<TextBlockDiagnostic>()

        // -----------------------------------------------------------------------------------------
        // PASO 1: OCR CON AISLAMIENTO ESTRICTO DE COLUMNAS (IGNORA EL OVERLAY CENTRAL 0.28..0.72)
        // -----------------------------------------------------------------------------------------
        var isLegendaryRanked = false
        var isPreparationPhase = false
        try {
            val inputImage = InputImage.fromBitmap(bitmap, 0)
            val visionText = recognizer.process(inputImage).await()

            // Detección proactiva de Clasificatoria Legendaria en pantalla completa
            isLegendaryRanked = DraftValidationLayer.isLegendaryRankedDraft(
                fullOcrText = visionText.text,
                hasRealSummonerNames = allySummonerNamesCache.isNotEmpty()
            )
            isLegendaryRankedCache = isLegendaryRanked
            if (isLegendaryRanked) {
                AppLogger.d(TAG, "Clasificatoria Legendaria detectada en pantalla (Nombres anónimos). Búsqueda de invocadores desactivada.")
                allySummonerNamesCache.clear()
            }

            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val text = line.text.trim()
                    if (text.isBlank()) continue

                    val box = line.boundingBox
                    if (box != null && overlayRect != null) {
                        if (android.graphics.Rect.intersects(box, overlayRect!!)) {
                            continue // Ignorar texto que cae dentro de la ventana flotante
                        }
                    }

                    val centerY = box?.centerY() ?: 0
                    val centerX = box?.centerX() ?: 0
                    val xRatio = if (width > 0) centerX.toFloat() / width.toFloat() else 0.5f
                    val yRatio = if (height > 0) centerY.toFloat() / height.toFloat() else 0.5f

                    val lowerText = text.lowercase()
                    val isAssistantOverlayText = lowerText.contains("campeones confirmados") || 
                                                 lowerText.contains("escaneo manual") || 
                                                 lowerText.contains("modo manual") ||
                                                 lowerText.contains("coach")
                    if (isAssistantOverlayText) continue

                    if (yRatio < 0.2f && (lowerText.contains("fase de preparación") || lowerText.contains("fase de preparacion") || lowerText.contains("preparation phase"))) {
                        isPreparationPhase = true
                    }

                    // EXCLUSIÓN ABSOLUTA DEL CENTRO (0.33f a 0.67f) Y DEL OVERLAY FLOTANTE DEL ASISTENTE
                    if (xRatio in 0.33f..0.67f || (box != null && overlayRect != null && android.graphics.Rect.intersects(box, overlayRect!!))) {
                        continue
                    }

                    // Detección automática de Primera / Segunda Selección por texto y ubicación espacial superior
                    val textNorm = DraftValidationLayer.normalize(text)

                    val hasPrimera = textNorm.contains("primera eleccion") || textNorm.contains("primera seleccion") ||
                                     textNorm.contains("primer pick") || textNorm.contains("first pick") ||
                                     textNorm.contains("1a eleccion") || textNorm.contains("1ª eleccion") ||
                                     textNorm.contains("1.a eleccion") || textNorm.contains("1.ª eleccion") ||
                                     textNorm.contains("1a seleccion") || textNorm.contains("1ª seleccion") ||
                                     textNorm.contains("1.a seleccion") || textNorm.contains("1.ª seleccion") ||
                                     textNorm.contains("primeira escolha") || textNorm.contains("primeira selecao")

                    val hasSegunda = textNorm.contains("segunda eleccion") || textNorm.contains("segunda seleccion") ||
                                     textNorm.contains("segundo pick") || textNorm.contains("second pick") ||
                                     textNorm.contains("2a eleccion") || textNorm.contains("2ª eleccion") ||
                                     textNorm.contains("2.a eleccion") || textNorm.contains("2.ª eleccion") ||
                                     textNorm.contains("2a seleccion") || textNorm.contains("2ª seleccion") ||
                                     textNorm.contains("2.a seleccion") || textNorm.contains("2.ª seleccion") ||
                                     textNorm.contains("segunda escolha") || textNorm.contains("segunda selecao")

                    val hasPreparation = textNorm.contains("fase de preparacion") || textNorm.contains("fase de preparación") || 
                                         textNorm.contains("fase de preparacao") || textNorm.contains("preparation phase")
                    if (hasPreparation) {
                        isPreparationPhase = true
                        AppLogger.d(TAG, "OCR Fase de Preparación detectada.")
                    }

                    // Detectar en la cabecera superior extrema donde aparecen los banners oficiales
                    if (yRatio < 0.25f && (xRatio < 0.40f || xRatio > 0.60f)) {
                        if (hasPrimera) {
                            if (xRatio > 0.50f) {
                                // Insignia en mitad derecha (equipo rival) -> Rival tiene 1º Pick -> Aliados son 2º Pick
                                detectedFirstPick = false
                                AppLogger.d(TAG, "OCR Primera Selección detectada en lado RIVAL (xRatio=$xRatio) -> Aliados = Segunda Selección")
                            } else {
                                // Insignia en mitad izquierda (equipo aliado) -> Aliados tienen 1º Pick
                                detectedFirstPick = true
                                AppLogger.d(TAG, "OCR Primera Selección detectada en lado ALIADO (xRatio=$xRatio) -> Aliados = Primera Selección")
                            }
                        } else if (hasSegunda) {
                            if (xRatio > 0.50f) {
                                // Insignia de 2ª Selección en mitad derecha (rival) -> Rival es 2º Pick -> Aliados son 1º Pick
                                detectedFirstPick = true
                                AppLogger.d(TAG, "OCR Segunda Selección detectada en lado RIVAL (xRatio=$xRatio) -> Aliados = Primera Selección")
                            } else {
                                // Insignia de 2ª Selección en mitad izquierda (aliado) -> Aliados son 2º Pick
                                detectedFirstPick = false
                                AppLogger.d(TAG, "OCR Segunda Selección detectada en lado ALIADO (xRatio=$xRatio) -> Aliados = Segunda Selección")
                            }
                        }
                    }

                    // Si el texto completo es una indicación de primera/segunda selección o ruido de interfaz,
                    // descartarlo de inmediato para que NUNCA se asigne a un slot de invocador o campeón
                    if (hasPrimera || hasSegunda || DraftValidationLayer.isNoiseText(text)) {
                        continue
                    }

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
                    
                    detectedWords.add(text)
                    val leftRatio = (box?.left ?: centerX).toFloat() / width.toFloat()
                    val rightRatio = (box?.right ?: centerX).toFloat() / width.toFloat()

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

                        // Ignorar barra de bans superior y botones inferiores extremos
                        if (subYRatio < 0.080f || subYRatio > 0.920f) continue

                        val lineCenterX = subBox.centerX()
                        val lineXRatio = if (width > 0) lineCenterX.toFloat() / width.toFloat() else xRatio

                        val isAllyCol = lineXRatio in calib.allyOcrMinX..calib.allyOcrMaxX
                        val isEnemyCol = lineXRatio in calib.enemyOcrMinX..calib.enemyOcrMaxX

                        // 1.1 COLUMNA ALIADA (Texto inmediatamente a la derecha del avatar)
                        if (isAllyCol) {
                            var bestSlot = -1
                            var minDiff = 0.090f
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
                        else if (isEnemyCol) {
                            var bestSlot = -1
                            var minDiff = 0.090f
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

            userExplicitlyConfirmed = false
            val currentUserNameClean = if (!isLegendaryRanked) {
                try {
                    val u1 = com.example.util.SubscriptionManager.userName.value.trim().lowercase(Locale.ROOT).replace(" ", "")
                    val u2 = com.example.util.AuthManager.getAuth()?.currentUser?.displayName?.trim()?.lowercase(Locale.ROOT)?.replace(" ", "") ?: ""
                    val u3 = try { if (context != null) com.example.data.AccountProfileManager.getActiveProfile(context).name.trim().lowercase(Locale.ROOT).replace(" ", "") else "" } catch (_: Exception) { "" }
                    listOf(u1, u2, u3, "yo", "tu").filter { it.isNotBlank() }
                } catch (_: Exception) {
                    listOf("yo", "tu")
                }
            } else {
                emptyList()
            }

            // Procesar textos aliados: Detección de Línea, Nombre de Invocador y Campeón
            for (i in 0..4) {
                val slot = allySlots[i]
                val entries = allySlotTexts[i].sortedBy { it.second?.top ?: 0 }

                var detectedRoleInSlot: LaneRole? = null
                var detectedChampInSlot: Champion? = null
                val summonerCandidates = mutableListOf<String>()
                var isUnpickedTextPresent = false

                for ((rawBlock, box) in entries) {
                    val safeBox = box ?: Rect(0, 0, 10, 10)
                    val sublines = rawBlock.split("\n").map { it.trim() }.filter { it.isNotBlank() }

                    for (line in sublines) {
                        if (DraftValidationLayer.isNoiseText(line)) continue
                        
                        val lineLower = line.lowercase(Locale.ROOT)
                        if (lineLower.contains("preselecci") || lineLower.contains("eligiendo") || 
                            lineLower.contains("ayud") || lineLower.contains("bloque") || 
                            lineLower.contains("esperando")) {
                            isUnpickedTextPresent = true
                        }

                        // Comprobar si este slot contiene la etiqueta explícita del usuario "(TÚ)" / "(TU)" / "(YOU)" / "(VOCÊ)" o coincide con su nombre
                        val lineNorm = DraftValidationLayer.normalize(line).lowercase(Locale.ROOT)
                        val lineCompressed = lineNorm.replace(" ", "")
                        val isUserTag = !isLegendaryRanked && (
                            lineNorm == "tu" || lineNorm == "(tu)" || lineNorm == "you" || lineNorm == "(you)" ||
                            lineNorm == "voce" || lineNorm == "(voce)" ||
                            lineNorm.startsWith("(tu) ") || lineNorm.endsWith(" (tu)") ||
                            lineNorm.startsWith("(you) ") || lineNorm.endsWith(" (you)") ||
                            lineNorm.contains(" tú ") || lineNorm.contains("(tú)") ||
                            lineNorm.contains("( tu )") || lineNorm.contains("[tu]") || lineNorm.contains("[tú]") ||
                            lineNorm.contains("( you )") || lineNorm.contains("[you]") ||
                            currentUserNameClean.any { it.length >= 3 && (lineCompressed == it || lineCompressed.contains(it)) }
                        )

                        if (isUserTag) {
                            userSlotIndex = i
                            userExplicitlyConfirmed = true
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = line,
                                    rect = safeBox,
                                    isAlly = true,
                                    slotIndex = i,
                                    tag = "¡TU SLOT!",
                                    color = android.graphics.Color.YELLOW
                                )
                            )
                            AppLogger.d(TAG, "Slot del usuario confirmado explícitamente en Slot Aliado $i ('$line')")
                        }

                        // A) Rol / Línea explícito (ej: "Línea Central", "Carril de Barón", etc.)
                        val role = DraftValidationLayer.parseRoleFromText(line)
                        if (role != null) {
                            detectedRoleInSlot = role
                            slot.explicitRole = role
                            allySlotRolesCache[i] = role
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
                            detectedChampInSlot = matched
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
                            continue
                        }

                        // C) Nombre de Invocador aliado (detección instantánea)
                        if (!isLegendaryRanked && line.length in 2..24 && !line.startsWith("(") && !line.endsWith(")")) {
                            summonerCandidates.add(line)
                        }
                    }
                }

                // Guardar nombre de invocador detectado al instante
                if (summonerCandidates.isNotEmpty() && !isLegendaryRanked) {
                    val candidateName = summonerCandidates.first()
                    if (!candidateName.lowercase(Locale.ROOT).startsWith("jugador en") &&
                        !candidateName.lowercase(Locale.ROOT).startsWith("jogador na")) {
                        isLegendaryRanked = false
                        isLegendaryRankedCache = false
                        allySummonerNamesCache[i] = candidateName
                        textDiagnosticsList.add(
                            TextBlockDiagnostic(
                                text = candidateName,
                                rect = entries.firstOrNull()?.second ?: Rect(0, 0, 10, 10),
                                isAlly = true,
                                slotIndex = i,
                                tag = "INVOCADOR: $candidateName",
                                color = android.graphics.Color.WHITE
                            )
                        )
                    }
                }

                // REGLA DEL USUARIO: En aliados, primero aparece la línea y luego el campeón.
                // Si el slot aliado no tiene un campeón detectado por texto OCR en este frame, pero ya estaba confirmado,
                // se preserva como verdad absoluta inmutable (picks 1-9).
                if (detectedChampInSlot != null) {
                    allySlotConfirmedChampions[i] = detectedChampInSlot
                    allyOcrChampions[i] = detectedChampInSlot
                    slot.champion = detectedChampInSlot
                    slot.confidencePercent = 100
                    slot.isLikelyUnpicked = false
                } else if (allySlotConfirmedChampions[i] != null) {
                    // PRESERVAR VERDAD ABSOLUTA INMUTABLE
                    slot.champion = allySlotConfirmedChampions[i]
                    slot.confidencePercent = 100
                    slot.isLikelyUnpicked = false
                } else {
                    slot.champion = null
                    slot.confidencePercent = 0
                    slot.isLikelyUnpicked = true
                }

                if (allySlotRolesCache[i] != null) {
                    slot.explicitRole = allySlotRolesCache[i]
                }
            }

            // Si se detectó el slot del usuario (marcado con "(TÚ)"), asignar su rol; si no, preservar el rol activo del usuario
            if (userSlotIndex != null) {
                cachedUserSlotIndex = userSlotIndex
            } else if (cachedUserSlotIndex != null) {
                userSlotIndex = cachedUserSlotIndex
                userExplicitlyConfirmed = true
            } else if (currentActiveRole != null) {
                // Si el usuario tiene seleccionado un rol y coincide con el rol de un slot, vincular al instante
                val matchingSlot = allySlots.indexOfFirst { it.explicitRole == currentActiveRole || allySlotRolesCache[it.slotIndex] == currentActiveRole }
                if (matchingSlot != -1) {
                    userSlotIndex = matchingSlot
                    cachedUserSlotIndex = matchingSlot
                    userExplicitlyConfirmed = true
                }
            }

            val uIdx = userSlotIndex
            if (uIdx != null && uIdx in 0..4) {
                val explicitRole = allySlots[uIdx].explicitRole ?: allySlotRolesCache[uIdx]
                if (explicitRole != null) {
                    userDetectedLane = explicitRole
                    AppLogger.d(TAG, "Rol de usuario confirmado explícitamente en Slot $uIdx -> ${userDetectedLane.shortName}")
                } else {
                    // Si el slot aliado tiene Castigo/Smite, asignar Jungla
                    val hasSmite = allySlots[uIdx].summonerSpells.any { it.equals("Castigo", ignoreCase = true) || it.equals("Smite", ignoreCase = true) }
                    if (hasSmite) {
                        userDetectedLane = LaneRole.JUNGLE
                        allySlots[uIdx].explicitRole = LaneRole.JUNGLE
                        allySlotRolesCache[uIdx] = LaneRole.JUNGLE
                    } else {
                        // Preservar el rol previamente seleccionado por el usuario en lugar de forzar TOP/default
                        userDetectedLane = currentActiveRole ?: allySlotRolesCache[uIdx]
                    }
                }
            } else if (currentActiveRole != null) {
                userDetectedLane = currentActiveRole
            }

            // Para el lado rival: Analizamos el texto de cada slot.
            // En Wild Rift, cuando un rival fija o selecciona un campeón, el nombre aparece en texto:
            // "ANNIE", "VOLIBEAR", "SERAPHINE", "ASHE", etc.
            // Mientras no seleccione, muestra "Jugador 1", "Jugador 2", etc.
            for (i in 0..4) {
                var detectedEnemyChamp: Champion? = null
                var isWaitingPick = false
                var isUnpickedTextPresent = false

                for ((rawBlock, box) in enemySlotTexts[i].sortedBy { it.second?.top ?: 0 }) {
                    val safeBox = box ?: Rect(0, 0, 10, 10)
                    val sublines = rawBlock.split("\n").map { it.trim() }.filter { it.isNotBlank() }
                    for (line in sublines) {
                        if (DraftValidationLayer.isNoiseText(line)) continue
                        val low = line.lowercase(Locale.ROOT)
                        if (low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || 
                            low.contains("preselecci") || low.contains("eligiendo") || 
                            low.contains("esperando") || low.contains("bloque") || low.contains("ayud")) {
                            isWaitingPick = true
                            isUnpickedTextPresent = true
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = line,
                                    rect = safeBox,
                                    isAlly = false,
                                    slotIndex = i,
                                    tag = "JUGADOR/HOVER",
                                    color = android.graphics.Color.DKGRAY
                                )
                            )
                            continue
                        }

                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            detectedEnemyChamp = matched
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
                            continue
                        }
                    }
                }

                // REGLA DEL USUARIO: En rivales, solamente aparece el nombre del campeón cuando ya está seleccionado.
                // Si el slot enemigo no tiene un campeón detectado por texto OCR en este frame, pero ya estaba confirmado,
                // se preserva como verdad absoluta inmutable (picks 1-9).
                if (detectedEnemyChamp != null) {
                    enemySlotConfirmedChampions[i] = detectedEnemyChamp
                    enemyOcrChampions[i] = detectedEnemyChamp
                    enemySlots[i].champion = detectedEnemyChamp
                    enemySlots[i].confidencePercent = 100
                    enemySlots[i].isLikelyUnpicked = false
                } else if (enemySlotConfirmedChampions[i] != null) {
                    // PRESERVAR VERDAD ABSOLUTA INMUTABLE
                    enemySlots[i].champion = enemySlotConfirmedChampions[i]
                    enemySlots[i].confidencePercent = 100
                    enemySlots[i].isLikelyUnpicked = false
                } else {
                    enemySlots[i].champion = null
                    enemySlots[i].confidencePercent = 0
                    enemySlots[i].isLikelyUnpicked = true
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
        // PASO 3: EVALUACIÓN DE SLOTS Y DIAGNÓSTICO EN TIEMPO REAL (100% BASADO EN OCR Y ROLES)
        // -----------------------------------------------------------------------------------------
        val avatarDiameter = (height * calib.avatarDiameterRatio).toInt().coerceAtLeast(32)
        val allyAvatarCenterX = (width * calib.allyAvatarCenterX).toInt()
        val enemyAvatarCenterX = (width * calib.enemyAvatarCenterX).toInt()
        val allySlotYRatios = calib.allySlotYRatios
        val enemySlotYRatios = calib.enemySlotYRatios
        val diagnosticsList = mutableListOf<SlotDiagnostic>()

        // 3.1 Aliados: Si se detectó el nombre del campeón en el slot, se asocia directamente a la línea memorizada de ese slot
        for (i in 0..4) {
            val slot = allySlots[i]
            val yCenter = (height * allySlotYRatios[i]).toInt()
            val startX = (allyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)
            val roiRect = Rect(startX, startY, startX + avatarDiameter, startY + avatarDiameter)

            val ocrChamp = allyOcrChampions[i]
            val roleForSlot = allySlotRolesCache[i] ?: defaultRolesList[i]

            val finalChamp = allySlotFilters[i].process(ocrChamp, isOcr = (ocrChamp != null), score = if (ocrChamp != null) 1.0f else 0f, persistentCache = allySlotConfirmedChampions[i])
            
            if (finalChamp != null) {
                allySlotConfirmedChampions[i] = finalChamp
                slot.champion = finalChamp
                slot.confidencePercent = 100
                slot.explicitRole = roleForSlot
                slot.assignedRole = roleForSlot
            } else {
                slot.champion = null
                slot.confidencePercent = 0
                slot.explicitRole = roleForSlot
            }

            val diagStatus = if (finalChamp != null) DiagnosticStatus.CONFIRMADO else DiagnosticStatus.VACIO
            val diagReason = if (finalChamp != null) {
                "Campeón confirmado por nombre OCR: ${finalChamp.name} -> ${roleForSlot.shortName}"
            } else {
                "Esperando selección en carril ${roleForSlot.shortName} (${allySummonerNamesCache[i] ?: "Invocador"})"
            }

            val diagnostic = SlotDiagnostic(
                slotIndex = i,
                isAlly = true,
                roiRect = roiRect,
                candidate1 = finalChamp,
                score1 = if (finalChamp != null) 1.0f else 0.0f,
                candidate2 = null,
                score2 = 0f,
                margin = if (finalChamp != null) 1.0f else 0f,
                ocrChampion = ocrChamp,
                finalChampion = finalChamp,
                status = diagStatus,
                reason = diagReason
            )
            diagnosticsList.add(diagnostic)
            AppLogger.d(TAG, diagnostic.toFormattedString())
        }

        // 3.2 Rivales: Se detecta el nombre del campeón cuando desaparece 'Jugador X'
        for (i in 0..4) {
            val slot = enemySlots[i]
            val yCenter = (height * enemySlotYRatios[i]).toInt()
            val startX = (enemyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)
            val roiRect = Rect(startX, startY, startX + avatarDiameter, startY + avatarDiameter)

            val ocrChamp = enemyOcrChampions[i]

            val finalChamp = enemySlotFilters[i].process(ocrChamp, isOcr = (ocrChamp != null), score = if (ocrChamp != null) 1.0f else 0f, persistentCache = enemySlotConfirmedChampions[i])

            if (finalChamp != null) {
                enemySlotConfirmedChampions[i] = finalChamp
                slot.champion = finalChamp
                slot.confidencePercent = 100
            } else {
                slot.champion = null
                slot.confidencePercent = 0
            }

            val diagStatus = if (finalChamp != null) DiagnosticStatus.CONFIRMADO else DiagnosticStatus.VACIO
            val diagReason = if (finalChamp != null) {
                "Campeón rival confirmado por OCR: ${finalChamp.name}"
            } else {
                "Slot rival esperando selección (Jugador ${i + 1})"
            }

            val diagnostic = SlotDiagnostic(
                slotIndex = i,
                isAlly = false,
                roiRect = roiRect,
                candidate1 = finalChamp,
                score1 = if (finalChamp != null) 1.0f else 0.0f,
                candidate2 = null,
                score2 = 0f,
                margin = if (finalChamp != null) 1.0f else 0f,
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
        // PASO 3.4: RECONOCIMIENTO VISUAL INTELIGENTE DEL 10º PICK (ÚLTIMO PICK DEL DRAFT)
        // En Wild Rift, cuando el último jugador selecciona su campeón, la partida transiciona
        // inmediatamente a la pantalla de carga del juego, por lo que el nombre textual desaparece
        // y el OCR no puede leerlo.
        // La secuencia de selección según el orden de Draft:
        // - Si el Equipo Aliado es Primer Pick (1A -> 2E -> 2A -> 2E -> 2A -> 1E):
        //   El 10º pick es RIVAL (el último campeón enemigo). Se aplica reconocimiento visual al slot rival restante.
        // - Si el Equipo Aliado es Segundo Pick (1E -> 2A -> 2E -> 2A -> 2E -> 1A):
        //   El 10º pick es ALIADO (el último campeón aliado). Se aplica reconocimiento visual al slot aliado restante.
        // -----------------------------------------------------------------------------------------
        val totalAllyOcr = allySlots.count { it.champion != null }
        val totalEnemyOcr = enemySlots.count { it.champion != null }

        // Inferencia determinista de Primera Selección según la regla exacta del usuario:
        // Si el equipo aliado selecciona primero (Slot 0 aliado) -> Primera Selección (true)
        // Si el equipo rival selecciona primero (Slot 0 rival) -> Segunda Selección (false)
        if (allySlots[0].champion != null && enemySlots[0].champion == null) {
            detectedFirstPick = true
            AppLogger.d(TAG, "Inferencia First Pick: Aliados seleccionaron en Slot 0 primero -> Primera Selección (true)")
        } else if (enemySlots[0].champion != null && allySlots[0].champion == null) {
            detectedFirstPick = false
            AppLogger.d(TAG, "Inferencia First Pick: Rival seleccionó en Slot 0 primero -> Segunda Selección (false)")
        } else if (totalAllyOcr > 0 && totalEnemyOcr == 0) {
            detectedFirstPick = true
            AppLogger.d(TAG, "Inferencia First Pick: Aliados tienen $totalAllyOcr picks y Rival 0 -> Primera Selección (true)")
        } else if (totalEnemyOcr > 0 && totalAllyOcr == 0) {
            detectedFirstPick = false
            AppLogger.d(TAG, "Inferencia First Pick: Rival tiene $totalEnemyOcr picks y Aliados 0 -> Segunda Selección (false)")
        } else if (detectedFirstPick == null) {
            when {
                totalAllyOcr == 1 && totalEnemyOcr == 2 -> detectedFirstPick = true
                totalEnemyOcr == 1 && totalAllyOcr == 2 -> detectedFirstPick = false
                totalAllyOcr == 3 && totalEnemyOcr == 2 -> detectedFirstPick = true
                totalEnemyOcr == 3 && totalEnemyOcr == 3 -> detectedFirstPick = true
                totalEnemyOcr == 2 && totalAllyOcr == 3 -> detectedFirstPick = true
                totalAllyOcr == 3 && totalEnemyOcr == 4 -> detectedFirstPick = true
                totalEnemyOcr == 3 && totalAllyOcr == 4 -> detectedFirstPick = false
                totalAllyOcr == 5 && totalEnemyOcr == 4 -> detectedFirstPick = true
                totalEnemyOcr == 4 && totalAllyOcr == 5 -> detectedFirstPick = false
            }
        }

        val effectiveFirstPick = detectedFirstPick ?: currentIsFirstPick ?: false
        val pickSequence = getDraftPickSequence(effectiveFirstPick)

        debugVisualMatches.value = emptyMap()

        // -----------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------
        // PASO 4: EVALUACIÓN DETERMINISTA DE LA 10ª SELECCIÓN (RECONOCIMIENTO DE IMAGEN)
        // REGLAS DEL USUARIO (CRÍTICAS):
        // 1. De la selección 1 hasta la 9 se visualiza el nombre del campeón y dependiendo del nombre
        //    del campeón es que se selecciona (OCR de texto 100%).
        // 2. SOLAMENTE la selección número 10 no aparece su nombre de campeón y es reconocimiento de imagen.
        // 3. Primero escanea en la PARTE INFERIOR dependiendo si es o no es primera selección:
        //    - Si es primera selección -> escanea la parte INFERIOR DERECHA (Rival 5).
        //    - Si NO es primera selección -> escanea la parte INFERIOR IZQUIERDA (Aliado 5).
        // 4. En la parte inferior puede que solamente lo muestre (hover) y luego seleccione otro campeón.
        // 5. Después que desaparezcan los slots de los avatares, quiere decir que ya seleccionó el campeón:
        //    entonces se confirma en la PARTE SUPERIOR, donde se visualiza 100% la selección definitiva.
        // -----------------------------------------------------------------------------------------
        val tenthTargetIsAlly = !effectiveFirstPick
        val targetSlot = if (tenthTargetIsAlly) allySlots[4] else enemySlots[4]

        val totalAllyOcrConfirmed = allySlots.count { it != targetSlot && it.champion != null }
        val totalEnemyOcrConfirmed = enemySlots.count { it != targetSlot && it.champion != null }
        val otherPicksConfirmed = totalAllyOcrConfirmed + totalEnemyOcrConfirmed

        var isTenthConfirmed = false
        var slotsDismissed = false
        val eligibleFor10thPick = otherPicksConfirmed >= 8 || isPreparationPhase

        if (eligibleFor10thPick) {
            val confirmedIds = (allySlots.filter { it != targetSlot }.mapNotNull { it.champion?.id } +
                                enemySlots.filter { it != targetSlot }.mapNotNull { it.champion?.id }).toSet()

            val standardRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
            val (expectedRole, roleExplanation) = if (!tenthTargetIsAlly) {
                // RIVAL: No hay etiquetas de rol en pantalla -> Se deduce por descarte de los picks rivales 1-4
                val validEnemySlots = enemySlots.filter { it != targetSlot && it.champion != null }
                val dummyAudit = mutableListOf<String>()
                val enemyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validEnemySlots, allChamps, dummyAudit, isAllyTeam = false)
                val assignedRoles = enemyResolved.assignments.keys.toSet()
                val remainingRoles = standardRoles.filter { !assignedRoles.contains(it) }
                val deduced = remainingRoles.firstOrNull() ?: defaultRolesList[4]
                val occupiedDesc = enemyResolved.assignments.entries.joinToString(", ") { "${it.key.displayName}: ${it.value.name}" }
                val expl = if (enemyResolved.assignments.isNotEmpty()) {
                    "Línea Rival Deducida por Descarte (Picks Rivales 1-4 ocupan: $occupiedDesc) -> Rol restante para 10º Pick: ${deduced.displayName}"
                } else {
                    "Línea Rival Deducida: ${deduced.displayName} (Aún sin suficientes picks rivales resueltos)"
                }
                Pair(deduced, expl)
            } else {
                // ALIADO: Línea visible directamente en pantalla
                val cachedRole = allySlotRolesCache[4] ?: targetSlot.explicitRole
                if (cachedRole != null) {
                    Pair(cachedRole, "Línea Aliada detectada en pantalla: ${cachedRole.displayName} (100% Certeza)")
                } else {
                    val validAllySlots = allySlots.filter { it != targetSlot && it.champion != null }
                    val dummyAudit = mutableListOf<String>()
                    val allyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validAllySlots, allChamps, dummyAudit, isAllyTeam = true)
                    val assignedRoles = allyResolved.assignments.keys.toSet()
                    val remainingRoles = standardRoles.filter { !assignedRoles.contains(it) }
                    val deduced = remainingRoles.firstOrNull() ?: defaultRolesList[4]
                    Pair(deduced, "Línea Aliada calculada: ${deduced.displayName}")
                }
            }

            slotsDismissed = isPreparationPhase || LocalVisionAnalyzer.areAvatarSlotsDismissed(bitmap, isPreparationPhase)

            if (slotsDismissed) {
                // FASE B: Desaparecieron los slots de avatar de selección -> CONFIRMACIÓN 100% EN PARTE SUPERIOR
                AppLogger.d(TAG, "[10º PICK] Cuadrícula cerrada -> Buscando confirmación definitiva en barra superior...")
                val superiorDecision = LocalVisionAnalyzer.identify10thPickSuperiorDetailed(
                    bitmap = bitmap,
                    isFirstPick = effectiveFirstPick,
                    calib = calib,
                    allChamps = allChamps,
                    confirmedIds = confirmedIds,
                    expectedRole = expectedRole,
                    roleExplanation = roleExplanation,
                    context = context
                )

                if (superiorDecision != null && superiorDecision.selectedChampion != null) {
                    val topPickChamp = superiorDecision.selectedChampion
                    lastPickVisualChampion = topPickChamp
                    lastPickVisualConfidence = 1.0f
                    isLastPickVisualRecognized = true
                    isTenthConfirmed = true

                    targetSlot.champion = topPickChamp
                    targetSlot.confidencePercent = 100
                    targetSlot.isLikelyUnpicked = false

                    if (tenthTargetIsAlly) {
                        allySlotConfirmedChampions[4] = topPickChamp
                        allyOcrChampions[4] = topPickChamp
                        AppLogger.i(TAG, "10º Pick Aliado CONFIRMADO 100% en barra superior: ${topPickChamp.name}")
                    } else {
                        enemySlotConfirmedChampions[4] = topPickChamp
                        enemyOcrChampions[4] = topPickChamp
                        AppLogger.i(TAG, "10º Pick Rival CONFIRMADO 100% en barra superior: ${topPickChamp.name}")
                    }
                } else if (lastPickVisualChampion != null && isTenthConfirmed) {
                    // Si ya se había ratificado previamente en barra superior, mantener con certeza 100%
                    targetSlot.champion = lastPickVisualChampion
                    targetSlot.confidencePercent = 100
                    targetSlot.isLikelyUnpicked = false
                }
            } else {
                // FASE A: Mientras está en selección -> ESCANEO EN LA PARTE INFERIOR (Hover / Preselección)
                AppLogger.d(TAG, "[10º PICK] Cuadrícula activa -> Escaneando preselección (hover) en parte inferior...")
                val inferiorDecision = LocalVisionAnalyzer.identify10thPickInferiorDetailed(
                    bitmap = bitmap,
                    isFirstPick = effectiveFirstPick,
                    calib = calib,
                    allChamps = allChamps,
                    confirmedIds = confirmedIds,
                    expectedRole = expectedRole,
                    roleExplanation = roleExplanation,
                    context = context
                )

                if (inferiorDecision != null && inferiorDecision.selectedChampion != null) {
                    val hoverChamp = inferiorDecision.selectedChampion
                    val confidence = inferiorDecision.confidence
                    lastPickVisualChampion = hoverChamp
                    lastPickVisualConfidence = confidence
                    isLastPickVisualRecognized = true
                    isTenthConfirmed = false // PRESELECCIÓN: AÚN NO CONFIRMADO DEFINITIVAMENTE

                    targetSlot.champion = hoverChamp
                    targetSlot.confidencePercent = (confidence * 100).toInt().coerceIn(75, 95)
                    targetSlot.isLikelyUnpicked = false

                    // Se actualiza en el slot provisionalmente sin sellar definitivamente
                    if (tenthTargetIsAlly) {
                        allySlotConfirmedChampions[4] = hoverChamp
                        allyOcrChampions[4] = hoverChamp
                        AppLogger.i(TAG, "10º Pick Aliado preseleccionado en parte inferior izquierda: ${hoverChamp.name} (${(confidence * 100).toInt()}%) [Auto-Scan permanece activo]")
                    } else {
                        enemySlotConfirmedChampions[4] = hoverChamp
                        enemyOcrChampions[4] = hoverChamp
                        AppLogger.i(TAG, "10º Pick Rival preseleccionado en parte inferior derecha: ${hoverChamp.name} (${(confidence * 100).toInt()}%) [Auto-Scan permanece activo]")
                    }
                }
            }
        } else {
            // Durante las selecciones 1 a 9, NUNCA se ejecuta reconocimiento visual de imagen.
            lastPickVisualChampion = null
            lastPickVisualConfidence = 0.0f
            isLastPickVisualRecognized = false
        }

        // Si estamos en Fase de Preparación, asegurar que ningún slot quede vacío escaneando los círculos superiores
        if (isPreparationPhase) {
            val confirmedIds = (allySlots.mapNotNull { it.champion?.id } + enemySlots.mapNotNull { it.champion?.id }).toMutableSet()
            for (i in 0..4) {
                if (allySlots[i].champion == null) {
                    val expectedRole = allySlots[i].explicitRole ?: allySlotRolesCache[i] ?: defaultRolesList[i]
                    val detected = LocalVisionAnalyzer.identifyTopSlotAvatar(
                        bitmap = bitmap,
                        isAlly = true,
                        slotIndex = i,
                        calib = calib,
                        allChamps = allChamps,
                        confirmedIds = confirmedIds,
                        expectedRole = expectedRole,
                        context = context
                    )
                    if (detected != null) {
                        val champ = detected.first
                        allySlots[i].champion = champ
                        allySlots[i].confidencePercent = 100
                        allySlotConfirmedChampions[i] = champ
                        allyOcrChampions[i] = champ
                        confirmedIds.add(champ.id)
                        AppLogger.i(TAG, "Fase de Preparación: Campeón Aliado Slot $i confirmado de barra superior: ${champ.name}")
                    }
                }
            }
            for (i in 0..4) {
                if (enemySlots[i].champion == null) {
                    val detected = LocalVisionAnalyzer.identifyTopSlotAvatar(
                        bitmap = bitmap,
                        isAlly = false,
                        slotIndex = i,
                        calib = calib,
                        allChamps = allChamps,
                        confirmedIds = confirmedIds,
                        expectedRole = null,
                        context = context
                    )
                    if (detected != null) {
                        val champ = detected.first
                        enemySlots[i].champion = champ
                        enemySlots[i].confidencePercent = 100
                        enemySlotConfirmedChampions[i] = champ
                        enemyOcrChampions[i] = champ
                        confirmedIds.add(champ.id)
                        AppLogger.i(TAG, "Fase de Preparación: Campeón Rival Slot $i confirmado de barra superior: ${champ.name}")
                    }
                }
            }
        }
        
        // 4.1 Aliados: Resolver roles combinando slots explícitos (OCR/Línea)
        val validAllySlots = allySlots.filter { it.champion != null }
        val allyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validAllySlots, allChamps, auditList)
        val alliesMap = allyResolved.assignments.toMutableMap()

        // Garantizar que NINGÚN campeón aliado sea omitido por colisión de rol
        val standardRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val assignedAllyChamps = alliesMap.values.map { it.id }.toSet()
        for (slot in validAllySlots) {
            val champ = slot.champion ?: continue
            if (!assignedAllyChamps.contains(champ.id)) {
                val availableRoles = standardRoles.filter { !alliesMap.containsKey(it) }
                val targetRole = slot.explicitRole ?: slot.assignedRole ?: allySlotRolesCache[slot.slotIndex] ?: availableRoles.firstOrNull() ?: defaultRolesList.getOrNull(slot.slotIndex) ?: LaneRole.MID
                alliesMap[targetRole] = champ
                slot.assignedRole = targetRole
                AppLogger.d(TAG, "Aliado ${champ.name} preservado y asignado a ${targetRole.shortName}")
            }
        }

        // 4.2 Enemigos: Asignación validada por roles primarios y secundarios de los picks seleccionados
        val validEnemySlots = enemySlots.filter { it.champion != null }
        val enemyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validEnemySlots, allChamps, auditList, isAllyTeam = false)
        val enemiesMap = enemyResolved.assignments.toMutableMap()

        val assignedEnemyChamps = enemiesMap.values.map { it.id }.toSet()
        for (slot in validEnemySlots) {
            val champ = slot.champion ?: continue
            if (!assignedEnemyChamps.contains(champ.id)) {
                val availableRoles = standardRoles.filter { !enemiesMap.containsKey(it) }
                val targetRole = availableRoles.firstOrNull() ?: defaultRolesList.getOrNull(slot.slotIndex) ?: LaneRole.MID
                enemiesMap[targetRole] = champ
                slot.assignedRole = targetRole
                AppLogger.d(TAG, "Rival ${champ.name} preservado y asignado a ${targetRole.shortName}")
            }
        }

        // Deduplicación: Un campeón aliado jamás puede aparecer en el equipo enemigo
        val allyChampIds = alliesMap.values.map { it.id }.toSet()
        val finalEnemiesMap = enemiesMap.filterNot { allyChampIds.contains(it.value.id) }
        val enemyConfidences = enemyResolved.confidences.filterKeys { finalEnemiesMap.containsKey(it) }

        // Mapear nombres de invocador y hechizos al rol final asignado (o rol por defecto del slot)
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

        val alliesBySlotMap = allySlots.mapNotNull { s -> s.champion?.let { s.slotIndex to it } }.toMap()
        val enemiesBySlotMap = enemySlots.mapNotNull { s -> s.champion?.let { s.slotIndex to it } }.toMap()

        val allyChampsList = alliesMap.values.toList()
        val enemyChampsList = finalEnemiesMap.values.toList()
        val total = allyChampsList.size + enemyChampsList.size

        val hasDraftActivity = total > 0 || allySummonerNamesCache.isNotEmpty() || userDetectedLane != null || detectedFirstPick != null || isLegendaryRanked || isPreparationPhase

        val statusMsg = when {
            isLegendaryRanked && total == 0 -> "Clasificatoria Legendaria (Nombres anónimos)"
            isLegendaryRanked -> "Clasificatoria Legendaria • $total picks detectados"
            total == 0 && allySummonerNamesCache.isNotEmpty() -> "Invocadores aliados detectados (${allySummonerNamesCache.size}/5)"
            total == 0 -> "Esperando selección en directo..."
            isLastPickVisualRecognized || total == 10 -> "10/10 Completo • 10º Pick detectado (${lastPickVisualChampion?.name ?: "Confirmado"})"
            total == 9 -> "9/10 picks detectados con certeza (esperando último pick)"
            total in 1..8 -> "$total/10 picks detectados con certeza"
            auditList.isNotEmpty() -> "Detectados: $total picks (${auditList.size} adaptaciones)"
            else -> "Detectados: $total picks con certeza"
        }

        val tenthTurn = pickSequence.last()
        val tenthSlot = if (tenthTurn.isAlly) allySlots.getOrNull(tenthTurn.slotIndex) else enemySlots.getOrNull(tenthTurn.slotIndex)

        // El 10º pick sólo se da por 100% confirmado si:
        // 1. Ya estamos en fase de preparación final (isPreparationPhase == true), O
        // 2. Desapareció la cuadrícula de avatares (slotsDismissed == true) Y se confirmó en la barra superior (isTenthConfirmed == true).
        // Mientras la cuadrícula esté visible (!slotsDismissed), el 10º pick es provisional (hover) y el auto-scan NUNCA debe desactivarse.
        val allAlliesConfirmed = allySlots.all { it.champion != null }
        val allEnemiesConfirmed = enemySlots.all { it.champion != null }

        val isLastPickConfirmedValue = if (isPreparationPhase) {
            true
        } else if (slotsDismissed && isTenthConfirmed) {
            true
        } else {
            false
        }

        return DraftScanResult(
            allies = allyChampsList,
            enemies = enemyChampsList,
            alliesBySlot = alliesBySlotMap,
            enemiesBySlot = enemiesBySlotMap,
            alliesByRole = alliesMap,
            enemiesByRole = finalEnemiesMap,
            enemyConfidencesByRole = enemyConfidences,
            detectedRole = userDetectedLane,
            userExplicitlyDetectedRole = userDetectedLane,
            detectedFirstPick = detectedFirstPick,
            isLastPickImageRecognized = isLastPickVisualRecognized,
            isLastPickConfirmed = isLastPickConfirmedValue,
            lastPickChampion = tenthSlot?.champion ?: lastPickVisualChampion,
            detectedRawWords = detectedWords,
            discrepancies = auditList,
            diagnostics = diagnosticsList,
            allySummonerNamesBySlot = allySummonerNamesCache.toMap(),
            allySpellsBySlot = allySpellsMap.mapValues { it.value.toList() },
            enemySpellsBySlot = emptyMap(),
            allySummonerNamesByRole = allySummonerNamesByRole,
            allySpellsByRole = allySpellsByRole,
            isLegendaryRanked = isLegendaryRanked,
            isPreparationPhase = isPreparationPhase,
            tenthPickLog = LocalVisionAnalyzer.lastTenthPickLog?.formattedSummary,
            isSuccessful = hasDraftActivity,
            statusMessage = statusMsg
        )
        } catch (t: Throwable) {
            AppLogger.e(TAG, "Excepción no controlada en scanDraftFromBitmap prevenida", t)
            DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "Error en escaneo")
        }
    }
}
