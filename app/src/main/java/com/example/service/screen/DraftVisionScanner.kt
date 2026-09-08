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
    var auditLog: String? = null
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
    
    private var recognizerInstance: com.google.mlkit.vision.text.TextRecognizer? = null

    // Memoria persistente de los carriles asignados a cada slot aliado (0..4)
    // En Wild Rift, el carril asignado a cada jugador es fijo durante toda la fase de selección
    private val allySlotRolesCache = mutableMapOf<Int, LaneRole>()
    // Memoria persistente de los nombres de invocador aliados (0..4)
    private val allySummonerNamesCache = mutableMapOf<Int, String>()

    fun resetSlotMemory() {
        allySlotRolesCache.clear()
        allySummonerNamesCache.clear()
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
                    if (text.contains("[OCR]") || text.contains("VIS:") || text.contains("[VISUAL]")) continue
                    
                    val box = line.boundingBox
                    if (box != null && overlayRect != null) {
                        if (android.graphics.Rect.intersects(box, overlayRect!!)) {
                            continue // Ignorar texto que cae dentro de la ventana flotante
                        }
                    }
                    
                    detectedWords.add(text)
                    val centerY = box?.centerY() ?: 0
                    val centerX = box?.centerX() ?: 0
                    val yRatio = centerY.toFloat() / height.toFloat()
                    val xRatio = centerX.toFloat() / width.toFloat()

                    // Ignorar la barra de bans superior (Y < 0.075) y botones del fondo (Y > 0.85)
                    if (yRatio < 0.075f || yRatio > 0.85f) continue

                    // Determinar el índice de slot vertical (0..4) calibrado a los 5 slots HUD
                    val slotIndex = when {
                        yRatio < 0.262f -> 0
                        yRatio < 0.396f -> 1
                        yRatio < 0.530f -> 2
                        yRatio < 0.675f -> 3
                        else -> 4
                    }

                    // 1.1 COLUMNA ALIADA (Extremos para capturar nombres y roles, evitando el centro)
                    if (xRatio in 0.01f..0.28f) {
                        allySlotTexts[slotIndex].add(Pair(text, box))
                    }
                    // 1.2 COLUMNA ENEMIGA (X entre 0.69 y 0.99)
                    else if (xRatio in 0.69f..0.99f) {
                        enemySlotTexts[slotIndex].add(Pair(text, box))
                        if (box != null) {
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = text,
                                    rect = box,
                                    isAlly = false,
                                    slotIndex = slotIndex,
                                    tag = "RIVAL (OCULTO)",
                                    color = android.graphics.Color.DKGRAY
                                )
                            )
                        }
                    }
                }
            }

            // Procesar textos aliados: Detección de Línea, Nombre de Invocador y Campeón
            for (i in 0..4) {
                val slot = allySlots[i]
                val entries = allySlotTexts[i]

                for ((line, box) in entries) {
                    val safeBox = box ?: Rect(0, 0, 10, 10)

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

                    // B) Texto de campeón detectado por OCR
                    val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                    if (matched != null) {
                        if (allyOcrChampions[i] == null) {
                            allyOcrChampions[i] = matched
                        }
                        // La línea cambia por el nombre del campeón -> ya sabemos qué línea va el campeón
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
                        AppLogger.d(TAG, "OCR Aliado Slot $i -> Campeón: ${matched.name}")
                        continue
                    }

                    // C) Nombre de invocador
                    val low = line.lowercase(Locale.ROOT).trim()
                    val isNoise = low in setOf(
                        "vs", "versus", "draft", "coach", "elegir", "bloquear", "ban", "pick",
                        "buscar", "buscando", "emparejamiento", "listo", "esperando", "cambiar",
                        "seleccionar", "cancelar", "combate", "victoria", "derrota"
                    ) || low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || line.length < 2

                    if (!isNoise) {
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
                
                val isGeneric = entries.isEmpty() || entries.all { (l, _) ->
                    val low = l.lowercase(java.util.Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank() ||
                    low.contains("carril") || low.contains("jungla") || low.contains("central") || low.contains("dúo") || low.contains("soporte") ||
                    low.contains("top") || low.contains("jug") || low.contains("mid") || low.contains("adc") || low.contains("sup") || low.contains("eligiendo") ||
                    low.contains("buscando")
                }
                if (isGeneric && allyOcrChampions[i] == null) {
                    slot.isLikelyUnpicked = true
                } else if (allyOcrChampions[i] != null) {
                    slot.isLikelyUnpicked = false
                }
            }

            // Para el lado rival: En el rival están ocultos la línea y el nombre de invocador.
            // El escaneo rival es ESTRICTAMENTE mediante detección de imagen en el Avatar.
            for (i in 0..4) {
                enemySlots[i].isLikelyUnpicked = false
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
        // Calibración geométrica de precisión HUD Wild Rift:
        // En pantallas ultra-anchas (21:9), hay un margen de zona segura (Safe Area).
        // Los avatares aliados están desplazados hacia la derecha (superando los hechizos).
        // Los avatares enemigos están desplazados hacia la izquierda desde el borde derecho.
        // Volvems al tamaño geométrico correcto para que el ImageHashMatcher pueda hacer su crop interno (0.70f) sin destrozar la escala.
        val avatarDiameter = (height * 0.115f).toInt().coerceAtLeast(32)
        
        // Ajuste dinámico basado en el aspect ratio para soportar 16:9 y 21:9
        val aspectRatio = width.toFloat() / height.toFloat()
        val isUltraWide = aspectRatio > 2.0f
        
        // Ajuste milimétrico de la X: Se desplazan un poco hacia el centro de la pantalla
        // para que no corten los iconos de hechizos/nombres y centren mejor el rostro
        // Ajustes del usuario: Izquierda 1 pixel más a la izq. Derecha más a la izq y más arriba.
        val allyAvatarCenterX = if (isUltraWide) (height * 0.162f).toInt() else (height * 0.155f).toInt()
        // Enemigos 1 píxel más a la izquierda (de 0.088f a 0.091f)
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.091f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales (eje Y): Subimos un poco (~4 pixeles)
        val slotYRatios = floatArrayOf(0.196f, 0.328f, 0.463f, 0.596f, 0.733f)
        val diagnosticsList = mutableListOf<SlotDiagnostic>()

        // 3.1 Aliados
        for (i in 0..4) {
            val slot = allySlots[i]
            val yCenter = (height * slotYRatios[i]).toInt()
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

            var finalChamp: Champion? = null
            var finalConfidence = 0
            var diagStatus = when (eval.status) {
                "CONFIRMADO" -> DiagnosticStatus.CONFIRMADO
                "AMBIGUO" -> DiagnosticStatus.AMBIGUO
                "VACIO" -> DiagnosticStatus.VACIO
                else -> DiagnosticStatus.RECHAZADO
            }
            var diagReason = eval.reason

            // NUEVA LÓGICA V13: TEXTO > IMAGEN SIEMPRE.
            // Si el OCR leyó un nombre, es LEY (porque el nombre solo sale cuando el campeón está seleccionado/preseleccionado).
            // Ignoramos la puntuación de la imagen porque los tintes rojos/azules la arruinan.
            if (ocrChamp != null && !slot.isLikelyUnpicked) {
                finalChamp = ocrChamp
                finalConfidence = 100
                diagStatus = DiagnosticStatus.CONFIRMADO
                if (eval.candidate1?.id == ocrChamp.id) {
                    diagReason = "Confirmado 100% (Visual y OCR coinciden: ${ocrChamp.name})"
                } else {
                    diagReason = "Asignado por TEXTO OCR (${ocrChamp.name}) ignorando visión errónea (${eval.candidate1?.name ?: "Nada"})"
                }
            } else if (eval.isConfirmed && eval.candidate1 != null && (!slot.isLikelyUnpicked || eval.score1 >= 0.40f)) {
                if (ocrChamp == null) {
                    finalChamp = eval.candidate1
                    finalConfidence = ((eval.score1 * 100).toInt()).coerceIn(1, 100)
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Confirmado por imagen (score ${"%.2f".format(Locale.US, eval.score1)}, margen ${"%.2f".format(Locale.US, eval.margin)})"
                } else if (ocrChamp.id == eval.candidate1.id) {
                    finalChamp = eval.candidate1
                    finalConfidence = 100
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Confirmado 100% (Visual y OCR coinciden: ${ocrChamp.name})"
                } else {
                    // Conflicto visual vs OCR
                    // NUNCA sobreescribir el OCR con visión si hay conflicto (porque el OCR detecta el texto real y las skins arruinan la visión)
                    if (true) {
                        finalChamp = ocrChamp
                        finalConfidence = 100
                        diagStatus = DiagnosticStatus.CONFIRMADO
                        diagReason = "Texto OCR (${ocrChamp.name}) SIEMPRE domina a la visión (${eval.candidate1.name}) para evitar problemas con skins."
                    }
                }
            } else {
                if (ocrChamp != null) {
                    finalChamp = ocrChamp
                    finalConfidence = 80
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Recuperado por texto OCR exacto (${ocrChamp.name})"
                } else {
                    finalChamp = null
                    finalConfidence = 0
                    diagReason = eval.reason
                }
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
            val yCenter = (height * slotYRatios[i]).toInt()
            val startX = (enemyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)
            val roiRect = Rect(startX, startY, startX + avatarDiameter, startY + avatarDiameter)

            val ocrChamp: Champion? = null
            var eval = VisualEvaluation(null, 0f, null, 0f, 0f, false, "VACIO", "Error al procesar")

            try {
                val crop = Bitmap.createBitmap(bitmap, startX, startY, avatarDiameter, avatarDiameter)
                eval = ImageHashMatcher.evaluateVisualMatch(crop, allChamps, isAlly = false)
                crop.recycle()
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error comparando avatar enemigo slot $i: ${e.message}")
            }

            var finalChamp: Champion? = null
            var finalConfidence = 0
            var diagStatus = when (eval.status) {
                "CONFIRMADO" -> DiagnosticStatus.CONFIRMADO
                "AMBIGUO" -> DiagnosticStatus.AMBIGUO
                "VACIO" -> DiagnosticStatus.VACIO
                else -> DiagnosticStatus.RECHAZADO
            }
            var diagReason = eval.reason

            // LADO RIVAL: Detección ESTRICTAMENTE visual en el avatar (Línea e Invocador ocultos)
            if (eval.isConfirmed && eval.candidate1 != null && eval.score1 >= 0.28f) {
                finalChamp = eval.candidate1
                finalConfidence = ((eval.score1 * 100).toInt()).coerceIn(1, 100)
                diagStatus = DiagnosticStatus.CONFIRMADO
                diagReason = "Confirmado por imagen en avatar (score ${"%.2f".format(Locale.US, eval.score1)})"
            } else {
                finalChamp = null
                finalConfidence = 0
                diagStatus = DiagnosticStatus.VACIO
                diagReason = eval.reason
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
        // PASO 3.3: ESCANEO DE HECHIZOS DE INVOCADOR (SUMMONER SPELLS)
        // -----------------------------------------------------------------------------------------
        val spellSize = (height * 0.040f).toInt().coerceAtLeast(18)
        val allySpellsMap = mutableMapOf<Int, MutableList<String>>()
        val enemySpellsMap = mutableMapOf<Int, MutableList<String>>()
        val detectedSpellsList = mutableListOf<com.example.util.SummonerSpellDetector.SpellMatch>()

        for (i in 0..4) {
            val yCenter = (height * slotYRatios[i]).toInt()

            // Hechizos Aliados (a la derecha del avatar aliado)
            val allyAvatarRight = allyAvatarCenterX + avatarDiameter / 2
            val allyCandidateRects = listOf(
                // Vertical
                Rect(
                    (allyAvatarRight + (height * 0.008f).toInt()).coerceIn(0, width - spellSize),
                    (yCenter - spellSize - 2).coerceIn(0, height - spellSize),
                    (allyAvatarRight + (height * 0.008f).toInt() + spellSize).coerceIn(spellSize, width),
                    (yCenter - 2).coerceIn(0, height)
                ),
                Rect(
                    (allyAvatarRight + (height * 0.008f).toInt()).coerceIn(0, width - spellSize),
                    (yCenter + 2).coerceIn(0, height - spellSize),
                    (allyAvatarRight + (height * 0.008f).toInt() + spellSize).coerceIn(spellSize, width),
                    (yCenter + spellSize + 2).coerceIn(0, height)
                ),
                // Horizontal
                Rect(
                    (allyAvatarRight + (height * 0.008f).toInt()).coerceIn(0, width - spellSize),
                    (yCenter - spellSize / 2).coerceIn(0, height - spellSize),
                    (allyAvatarRight + (height * 0.008f).toInt() + spellSize).coerceIn(spellSize, width),
                    (yCenter + spellSize / 2).coerceIn(0, height)
                ),
                Rect(
                    (allyAvatarRight + (height * 0.008f).toInt() + spellSize + 4).coerceIn(0, width - spellSize),
                    (yCenter - spellSize / 2).coerceIn(0, height - spellSize),
                    (allyAvatarRight + (height * 0.008f).toInt() + spellSize * 2 + 4).coerceIn(spellSize, width),
                    (yCenter + spellSize / 2).coerceIn(0, height)
                )
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

            // Hechizos Enemigos (a la izquierda del avatar enemigo)
            val enemyAvatarLeft = enemyAvatarCenterX - avatarDiameter / 2
            val enemyCandidateRects = listOf(
                // Vertical
                Rect(
                    (enemyAvatarLeft - (height * 0.008f).toInt() - spellSize).coerceIn(0, width - spellSize),
                    (yCenter - spellSize - 2).coerceIn(0, height - spellSize),
                    (enemyAvatarLeft - (height * 0.008f).toInt()).coerceIn(spellSize, width),
                    (yCenter - 2).coerceIn(0, height)
                ),
                Rect(
                    (enemyAvatarLeft - (height * 0.008f).toInt() - spellSize).coerceIn(0, width - spellSize),
                    (yCenter + 2).coerceIn(0, height - spellSize),
                    (enemyAvatarLeft - (height * 0.008f).toInt()).coerceIn(spellSize, width),
                    (yCenter + spellSize + 2).coerceIn(0, height)
                ),
                // Horizontal
                Rect(
                    (enemyAvatarLeft - (height * 0.008f).toInt() - spellSize).coerceIn(0, width - spellSize),
                    (yCenter - spellSize / 2).coerceIn(0, height - spellSize),
                    (enemyAvatarLeft - (height * 0.008f).toInt()).coerceIn(spellSize, width),
                    (yCenter + spellSize / 2).coerceIn(0, height)
                ),
                Rect(
                    (enemyAvatarLeft - (height * 0.008f).toInt() - spellSize * 2 - 4).coerceIn(0, width - spellSize),
                    (yCenter - spellSize / 2).coerceIn(0, height - spellSize),
                    (enemyAvatarLeft - (height * 0.008f).toInt() - spellSize - 4).coerceIn(spellSize, width),
                    (yCenter + spellSize / 2).coerceIn(0, height)
                )
            )

            val enemySlotSpells = mutableListOf<String>()
            for (r in enemyCandidateRects) {
                if (enemySlotSpells.size >= 2) break
                try {
                    val crop = Bitmap.createBitmap(bitmap, r.left, r.top, r.width(), r.height())
                    val match = SummonerSpellDetector.detectSpell(crop, r)
                    crop.recycle()
                    if (match != null && !enemySlotSpells.contains(match.spellName)) {
                        enemySlotSpells.add(match.spellName)
                        detectedSpellsList.add(match)
                    }
                } catch (_: Exception) {}
            }
            if (enemySlotSpells.isNotEmpty()) {
                enemySpellsMap[i] = enemySlotSpells
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
        val enemyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validEnemySlots, allChamps, auditList, isAllyTeam = false)
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
            enemySpellsBySlot = enemySpellsMap.mapValues { it.value.toList() },
            isSuccessful = total > 0,
            statusMessage = statusMsg
        )
    }
}
