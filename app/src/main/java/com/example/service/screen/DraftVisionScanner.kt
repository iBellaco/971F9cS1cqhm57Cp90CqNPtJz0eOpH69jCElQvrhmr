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
    val lastPickChampion: Champion? = null,
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
    
    // Configuración estándar de coordenadas y cálculos
    val calibrationConfig = VisionCalibrationConfig()

    private var recognizerInstance: com.google.mlkit.vision.text.TextRecognizer? = null

    // Memoria persistente de los carriles asignados a cada slot aliado (0..4)
    // En Wild Rift, el carril asignado a cada jugador es fijo durante toda la fase de selección
    private val allySlotRolesCache = mutableMapOf<Int, LaneRole>()
    // Memoria persistente de los nombres de invocador aliados (0..4)
    private val allySummonerNamesCache = mutableMapOf<Int, String>()

    // Filtros de estabilización temporal (anti-parpadeo y anti-oscilación)
    private class SlotTemporalFilter {
        private var lastConfirmedChampion: Champion? = null
        private var emptyCount: Int = 0

        fun process(candidate: Champion?, isOcr: Boolean, score: Float): Champion? {
            if (candidate != null) {
                lastConfirmedChampion = candidate
                emptyCount = 0
                return candidate
            }

            emptyCount++
            if (emptyCount >= 2) {
                lastConfirmedChampion = null
            }
            return lastConfirmedChampion
        }

        fun reset() {
            lastConfirmedChampion = null
            emptyCount = 0
        }
    }

    private val allySlotFilters = Array(5) { SlotTemporalFilter() }
    private val enemySlotFilters = Array(5) { SlotTemporalFilter() }

    fun resetSlotMemory() {
        allySlotRolesCache.clear()
        allySummonerNamesCache.clear()
        allySlotFilters.forEach { it.reset() }
        enemySlotFilters.forEach { it.reset() }
        AppLogger.d(TAG, "Memoria de roles e invocadores reiniciada")
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
        var lastPickVisualChampion: Champion? = null
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

                    // Solo detectar en la cabecera superior extrema donde aparecen los banners oficiales
                    if (yRatio < 0.20f && (xRatio < 0.35f || xRatio > 0.65f)) {
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

                        // Ignorar barra de bans y botones
                        if (subYRatio < 0.100f || subYRatio > 0.850f) continue

                        val isAllyCol = (xRatio in calib.allyOcrMinX..calib.allyOcrMaxX) ||
                                        (leftRatio <= calib.allyOcrMaxX && rightRatio >= calib.allyOcrMinX && xRatio < 0.40f)
                        val isEnemyCol = (xRatio in calib.enemyOcrMinX..calib.enemyOcrMaxX) ||
                                         (leftRatio <= calib.enemyOcrMaxX && rightRatio >= calib.enemyOcrMinX && xRatio > 0.60f)

                        // 1.1 COLUMNA ALIADA (Texto inmediatamente a la derecha del avatar)
                        if (isAllyCol) {
                            var bestSlot = -1
                            var minDiff = 0.120f
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
                            var minDiff = 0.120f
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
            val currentUserNameClean = try {
                val u1 = com.example.util.SubscriptionManager.userName.value.trim().lowercase(Locale.ROOT).replace(" ", "")
                val u2 = com.example.util.AuthManager.getAuth()?.currentUser?.displayName?.trim()?.lowercase(Locale.ROOT)?.replace(" ", "") ?: ""
                val u3 = try { if (context != null) com.example.data.AccountProfileManager.getActiveProfile(context).name.trim().lowercase(Locale.ROOT).replace(" ", "") else "" } catch (_: Exception) { "" }
                listOf(u1, u2, u3, "diego", "yo", "tu").filter { it.isNotBlank() }
            } catch (_: Exception) {
                listOf("diego", "yo", "tu")
            }

            // Procesar textos aliados: Detección de Línea, Nombre de Invocador y Campeón
            for (i in 0..4) {
                val slot = allySlots[i]
                val entries = allySlotTexts[i].sortedBy { it.second?.top ?: 0 }

                var detectedRoleInSlot: LaneRole? = null
                var detectedChampInSlot: Champion? = null
                val summonerCandidates = mutableListOf<String>()

                for ((rawBlock, box) in entries) {
                    val safeBox = box ?: Rect(0, 0, 10, 10)
                    val sublines = rawBlock.split("\n").map { it.trim() }.filter { it.isNotBlank() }

                    // Comprobar si el texto está coloreado en dorado/amarillo característico del slot del usuario en Wild Rift
                    var hasYellowGoldText = false
                    try {
                        val sampleBox = Rect(
                            safeBox.left.coerceIn(0, width - 1),
                            safeBox.top.coerceIn(0, height - 1),
                            safeBox.right.coerceIn(0, width),
                            safeBox.bottom.coerceIn(0, height)
                        )
                        if (sampleBox.width() > 4 && sampleBox.height() > 4) {
                            var yellowHits = 0
                            val stepX = (sampleBox.width() / 6).coerceAtLeast(1)
                            val stepY = (sampleBox.height() / 4).coerceAtLeast(1)
                            for (sy in sampleBox.top until sampleBox.bottom step stepY) {
                                for (sx in sampleBox.left until sampleBox.right step stepX) {
                                    val px = bitmap.getPixel(sx, sy)
                                    val pr = android.graphics.Color.red(px)
                                    val pg = android.graphics.Color.green(px)
                                    val pb = android.graphics.Color.blue(px)
                                    if (pr > 165 && pg > 140 && pb < 115 && pr > pb * 1.5f) {
                                        yellowHits++
                                    }
                                }
                            }
                            if (yellowHits >= 3) {
                                hasYellowGoldText = true
                            }
                        }
                    } catch (_: Exception) {}

                    for (line in sublines) {
                        if (DraftValidationLayer.isNoiseText(line)) continue

                        // Comprobar si este slot contiene la etiqueta del usuario "(TÚ)" / "(TU)" / "(YOU)" / "(VOCÊ)" o coincide con su nombre
                        val lineNorm = DraftValidationLayer.normalize(line).lowercase(Locale.ROOT)
                        val lineCompressed = lineNorm.replace(" ", "")
                        val isUserTag = lineNorm == "tu" || lineNorm == "(tu)" || lineNorm == "you" || lineNorm == "(you)" ||
                                        lineNorm == "voce" || lineNorm == "(voce)" ||
                                        lineNorm.startsWith("(tu) ") || lineNorm.endsWith(" (tu)") ||
                                        lineNorm.startsWith("(you) ") || lineNorm.endsWith(" (you)") ||
                                        lineNorm.contains(" tú ") || lineNorm.contains("(tú)") ||
                                        currentUserNameClean.any { it.length >= 3 && lineCompressed == it }

                        if (isUserTag || hasYellowGoldText) {
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
                            AppLogger.d(TAG, "Slot del usuario confirmado en Slot Aliado $i ('$line') [Yellow=$hasYellowGoldText]")
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

                        // C) Nombre de invocador (siempre que no sea rol ni campeón)
                        if (line.length in 2..28 && !DraftValidationLayer.isNoiseText(line) && !line.matches(Regex("^[0-9\\s:.,%#-]+$"))) {
                            if (ChampionNameResolver.findChampionInText(line, allChamps) == null) {
                                summonerCandidates.add(line)
                            }
                        }
                    }
                }

                // Si en este slot se detectó el texto de la línea (ej: "Línea Central"), significa que está en preselección (sin fijar)
                if (detectedRoleInSlot != null && detectedChampInSlot == null) {
                    allyOcrChampions[i] = null
                    slot.champion = null
                    slot.isLikelyUnpicked = true
                } else if (detectedChampInSlot != null) {
                    allyOcrChampions[i] = detectedChampInSlot
                    slot.champion = detectedChampInSlot
                    slot.confidencePercent = 100
                    slot.isLikelyUnpicked = false
                } else {
                    allyOcrChampions[i] = null
                    slot.champion = null
                    slot.isLikelyUnpicked = true
                }

                if (allySlotRolesCache[i] != null) {
                    slot.explicitRole = allySlotRolesCache[i]
                }

                // Asignar el nombre de invocador más limpio detectado SOLO si este slot no ha sido escaneado aún (bloqueo anti-parpadeo)
                if (allySummonerNamesCache[i].isNullOrBlank()) {
                    val bestSummoner = summonerCandidates.firstOrNull { cand ->
                        !cand.equals(slot.champion?.name, ignoreCase = true) &&
                        ChampionNameResolver.findChampionInText(cand, allChamps) == null
                    }
                    if (!bestSummoner.isNullOrBlank()) {
                        allySummonerNamesCache[i] = bestSummoner
                        textDiagnosticsList.add(
                            TextBlockDiagnostic(
                                text = bestSummoner,
                                rect = Rect(0, 0, 10, 10),
                                isAlly = true,
                                slotIndex = i,
                                tag = "INVOCADOR",
                                color = android.graphics.Color.argb(255, 120, 180, 255)
                            )
                        )
                        AppLogger.d(TAG, "OCR Aliado Slot $i -> Invocador bloqueado: $bestSummoner")
                    }
                }
            }

            // Si se detectó el slot del usuario (marcado con "(TÚ)"), asignar su rol; si no, preservar el rol activo del usuario
            val uIdx = userSlotIndex
            if (uIdx != null && uIdx in 0..4) {
                userDetectedLane = allySlots[uIdx].explicitRole ?: allySlotRolesCache[uIdx] ?: defaultRolesList.getOrNull(uIdx)
                AppLogger.d(TAG, "Rol de usuario confirmado en Slot $uIdx -> ${userDetectedLane?.shortName}")
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

                for ((rawBlock, box) in enemySlotTexts[i].sortedBy { it.second?.top ?: 0 }) {
                    val safeBox = box ?: Rect(0, 0, 10, 10)
                    val sublines = rawBlock.split("\n").map { it.trim() }.filter { it.isNotBlank() }
                    for (line in sublines) {
                        if (DraftValidationLayer.isNoiseText(line)) continue
                        val low = line.lowercase(Locale.ROOT)
                        if (low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador")) {
                            isWaitingPick = true
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

                if (isWaitingPick && detectedEnemyChamp == null) {
                    enemyOcrChampions[i] = null
                    enemySlots[i].champion = null
                    enemySlots[i].isLikelyUnpicked = true
                    enemySlotFilters[i].reset() // Limpiar inmediatamente si el slot es Jugador X
                } else if (detectedEnemyChamp != null) {
                    enemyOcrChampions[i] = detectedEnemyChamp
                    enemySlots[i].champion = detectedEnemyChamp
                    enemySlots[i].confidencePercent = 100
                    enemySlots[i].isLikelyUnpicked = false
                } else {
                    enemyOcrChampions[i] = null
                    enemySlots[i].champion = null
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

            val finalChamp = allySlotFilters[i].process(ocrChamp, isOcr = (ocrChamp != null), score = if (ocrChamp != null) 1.0f else 0f)
            
            if (finalChamp != null) {
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

            val finalChamp = enemySlotFilters[i].process(ocrChamp, isOcr = (ocrChamp != null), score = if (ocrChamp != null) 1.0f else 0f)

            if (finalChamp != null) {
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

        // Inferencia determinista de Primera Selección por progreso si el OCR no leyó el banner superior
        if (detectedFirstPick == null) {
            when {
                // Ronda 1: (1, 0) -> Aliado eligió 1º | (0, 1) -> Rival eligió 1º
                totalAllyOcr == 1 && totalEnemyOcr == 0 -> detectedFirstPick = true
                totalEnemyOcr == 1 && totalAllyOcr == 0 -> detectedFirstPick = false

                // Ronda 2: (1, 2) -> Aliado eligió 1º y Rival eligió 2 | (2, 1) -> Rival eligió 1º y Aliado eligió 2
                totalAllyOcr == 1 && totalEnemyOcr == 2 -> detectedFirstPick = true
                totalEnemyOcr == 1 && totalAllyOcr == 2 -> detectedFirstPick = false

                // Ronda 3: (3, 2) -> Aliado eligió 1º | (2, 3) -> Rival eligió 1º
                totalAllyOcr == 3 && totalEnemyOcr == 2 -> detectedFirstPick = true
                totalEnemyOcr == 3 && totalAllyOcr == 2 -> detectedFirstPick = false

                // Ronda 4: (3, 4) -> Aliado eligió 1º | (4, 3) -> Rival eligió 1º
                totalAllyOcr == 3 && totalEnemyOcr == 4 -> detectedFirstPick = true
                totalEnemyOcr == 3 && totalAllyOcr == 4 -> detectedFirstPick = false

                // Ronda 5: (5, 4) -> Aliado eligió 1º | (4, 5) -> Rival eligió 1º
                totalAllyOcr == 5 && totalEnemyOcr == 4 -> detectedFirstPick = true
                totalEnemyOcr == 5 && totalAllyOcr == 4 -> detectedFirstPick = false
            }
        }

        val effectiveFirstPick = detectedFirstPick ?: currentIsFirstPick ?: true

        // RECONOCIMIENTO VISUAL DE CAMPEÓN POR SIMILITUD DE IMAGEN
        // Solo para el 10º pick (última selección) o slots con retrato real confirmado sin texto OCR
        if (context != null) {
            val totalPickedSoFar = allySlots.count { it.champion != null } + enemySlots.count { it.champion != null }
            val isFinalTenthPick = totalPickedSoFar == 9

            val candidateSlots = if (isFinalTenthPick) {
                // Si faltan exactamente 1 campeón de los 10, probamos el único slot restante
                (allySlots + enemySlots).filter { it.champion == null }
            } else {
                // Si aún se están seleccionando, solo consideramos slots que NO estén explícitamente marcados como esperando selección
                (allySlots + enemySlots).filter { it.champion == null && !it.isLikelyUnpicked }
            }

            if (candidateSlots.isNotEmpty()) {
                val alreadyPickedIds = (allySlots.mapNotNull { it.champion?.id } + enemySlots.mapNotNull { it.champion?.id }).toSet()

                for (targetSlot in candidateSlots) {
                    val yCenter = if (targetSlot.isAlly) {
                        (height * allySlotYRatios[targetSlot.slotIndex]).toInt()
                    } else {
                        (height * enemySlotYRatios[targetSlot.slotIndex]).toInt()
                    }
                    val xCenter = if (targetSlot.isAlly) allyAvatarCenterX else enemyAvatarCenterX
                    // Usar un radio de recorte más amplio (1.35x) para capturar el retrato completo con máxima fidelidad
                    val expandedDiameter = (avatarDiameter * 1.35f).toInt()
                    val startX = (xCenter - expandedDiameter / 2).coerceIn(0, width - expandedDiameter)
                    val startY = (yCenter - expandedDiameter / 2).coerceIn(0, height - expandedDiameter)
                    val roi = Rect(startX, startY, startX + expandedDiameter, startY + expandedDiameter)

                    try {
                        val avatarCrop = Bitmap.createBitmap(bitmap, roi.left, roi.top, roi.width(), roi.height())
                        val threshold = if (isFinalTenthPick) 0.50f else 0.58f
                        val match = ChampionVisualMatcher.matchChampion(
                            context = context,
                            avatarCrop = avatarCrop,
                            candidates = allChamps,
                            excludedChampionIds = alreadyPickedIds,
                            minConfidenceThreshold = threshold
                        )
                        avatarCrop.recycle()

                        if (match != null) {
                            targetSlot.champion = match.champion
                            targetSlot.confidencePercent = (match.confidence * 100).toInt()
                            targetSlot.isLikelyUnpicked = false

                            if (targetSlot.isAlly) {
                                allyOcrChampions[targetSlot.slotIndex] = match.champion
                                allySlotFilters[targetSlot.slotIndex].process(match.champion, isOcr = false, score = match.confidence)
                            } else {
                                enemyOcrChampions[targetSlot.slotIndex] = match.champion
                                enemySlotFilters[targetSlot.slotIndex].process(match.champion, isOcr = false, score = match.confidence)
                            }

                            isLastPickVisualRecognized = true
                            lastPickVisualChampion = match.champion
                            val side = if (targetSlot.isAlly) "Aliado" else "Rival"
                            auditList.add("🎯 Slot $side ${targetSlot.slotIndex} detectado por Similitud Visual: ${match.champion.name} (${(match.confidence * 100).toInt()}%)")
                            AppLogger.d(TAG, "Reconocimiento por similitud en $side ${targetSlot.slotIndex}: ${match.champion.name}")
                        }
                    } catch (e: Exception) {
                        AppLogger.e(TAG, "Error en reconocimiento visual del slot ${targetSlot.slotIndex}", e)
                    }
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

        // Asignar cualquier campeón en slot explícito que no haya entrado en validAllySlots o haya quedado sin rol
        for (i in 0..4) {
            val slot = allySlots[i]
            val champ = slot.champion
            val role = slot.explicitRole ?: allySlotRolesCache[i] ?: defaultRolesList.getOrNull(i)
            if (champ != null && role != null && !alliesMap.containsKey(role)) {
                alliesMap[role] = champ
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

        val hasDraftActivity = total > 0 || allySummonerNamesCache.isNotEmpty() || userDetectedLane != null || detectedFirstPick != null

        val statusMsg = when {
            total == 0 && allySummonerNamesCache.isNotEmpty() -> "Invocadores aliados detectados (${allySummonerNamesCache.size}/5)"
            total == 0 -> "Esperando selección en directo..."
            isLastPickVisualRecognized -> "10/10 Completo • 10º Pick detectado por imagen (${lastPickVisualChampion?.name})"
            auditList.isNotEmpty() -> "Detectados: $total picks (${auditList.size} adaptaciones)"
            else -> "Detectados: $total picks con certeza"
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
            userExplicitlyDetectedRole = if (userExplicitlyConfirmed) userDetectedLane else null,
            detectedFirstPick = detectedFirstPick,
            isLastPickImageRecognized = isLastPickVisualRecognized,
            lastPickChampion = lastPickVisualChampion,
            detectedRawWords = detectedWords,
            discrepancies = auditList,
            diagnostics = diagnosticsList,
            allySummonerNamesBySlot = allySummonerNamesCache.toMap(),
            allySpellsBySlot = allySpellsMap.mapValues { it.value.toList() },
            enemySpellsBySlot = emptyMap(),
            allySummonerNamesByRole = allySummonerNamesByRole,
            allySpellsByRole = allySpellsByRole,
            isSuccessful = hasDraftActivity,
            statusMessage = statusMsg
        )
    }
}
