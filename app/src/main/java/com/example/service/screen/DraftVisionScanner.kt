package com.example.service.screen

import android.graphics.Bitmap
import android.graphics.Rect
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.util.AppLogger
import com.example.util.ImageHashMatcher
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
    val isSuccessful: Boolean,
    val statusMessage: String
)

object DraftVisionScanner {
    private const val TAG = "DraftVisionScanner"
    var overlayRect: android.graphics.Rect? = null
    
    var lastDebugBitmap = kotlinx.coroutines.flow.MutableStateFlow<android.graphics.Bitmap?>(null)
    var lastDiagnostics = kotlinx.coroutines.flow.MutableStateFlow<List<SlotDiagnostic>>(emptyList())
    
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
        var userSlotIndex: Int? = null
        val centerTexts = mutableListOf<String>()

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
                        yRatio < 0.260f -> 0
                        yRatio < 0.392f -> 1
                        yRatio < 0.527f -> 2
                        yRatio < 0.661f -> 3
                        else -> 4
                    }

                    // 1.1 COLUMNA ALIADA (Extremos ampliados para capturar los nombres, pero evitando el centro >0.33)
                    if (xRatio in 0.01f..0.24f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (X entre 0.66 y 0.99)
                    else if (xRatio in 0.76f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }
                    // 1.3 CENTRO
                    else {
                        centerTexts.add(text)
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
                        // En Wild Rift, solo el jugador local tiene su carril escrito explícitamente en el HUD
                        userDetectedLane = role
                        userSlotIndex = i
                        AppLogger.d(TAG, "OCR Aliado Slot $i -> Rol explícito: ${role.shortName} -> User Detected Lane!")
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
                
                // C) Fallback al texto central si es el slot del jugador y no tiene campeón (ej: Pre-selección)
                if (allyOcrChampions[i] == null && userSlotIndex == i) {
                    for (centerLine in centerTexts) {
                        val matched = ChampionNameResolver.findChampionInText(centerLine, allChamps)
                        if (matched != null) {
                            allyOcrChampions[i] = matched
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Texto CENTRAL detectado: ${matched.name}")
                            break
                        }
                    }
                }
                
                val isGeneric = lines.isEmpty() || lines.all { l ->
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
                    val low = l.lowercase(java.util.Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank() ||
                    low.contains("carril") || low.contains("jungla") || low.contains("central") || low.contains("dúo") || low.contains("soporte") ||
                    low.contains("top") || low.contains("jug") || low.contains("mid") || low.contains("adc") || low.contains("sup") || low.contains("eligiendo") || low.contains("buscando")
                }
                if (isGeneric && enemyOcrChampions[i] == null) {
                    slot.isLikelyUnpicked = true
                } else if (enemyOcrChampions[i] != null) {
                    slot.isLikelyUnpicked = false
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
                    if (eval.score1 >= 0.85f) {
                        finalChamp = eval.candidate1
                        finalConfidence = ((eval.score1 * 100).toInt()).coerceIn(1, 100)
                        diagStatus = DiagnosticStatus.CONFIRMADO
                        diagReason = "Visual contundente (${eval.candidate1.name} score ${"%.2f".format(Locale.US, eval.score1)}) supera texto OCR (${ocrChamp.name})"
                    } else if (eval.score1 < 0.68f) {
                        finalChamp = ocrChamp
                        finalConfidence = 80
                        diagStatus = DiagnosticStatus.CONFIRMADO
                        diagReason = "Conflicto resuelto por OCR (${ocrChamp.name}) ante baja certeza visual (${eval.candidate1.name} score ${"%.2f".format(Locale.US, eval.score1)})"
                    } else {
                        finalChamp = null
                        finalConfidence = 0
                        diagStatus = DiagnosticStatus.AMBIGUO
                        diagReason = "Conflicto irreconciliable: Visual=${eval.candidate1.name} (${"%.2f".format(Locale.US, eval.score1)}) vs OCR=${ocrChamp.name}. NO ASIGNAR."
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

            val ocrChamp = enemyOcrChampions[i]
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
                    if (eval.score1 >= 0.85f) {
                        finalChamp = eval.candidate1
                        finalConfidence = ((eval.score1 * 100).toInt()).coerceIn(1, 100)
                        diagStatus = DiagnosticStatus.CONFIRMADO
                        diagReason = "Visual contundente (${eval.candidate1.name} score ${"%.2f".format(Locale.US, eval.score1)}) supera texto OCR (${ocrChamp.name})"
                    } else if (eval.score1 < 0.68f) {
                        finalChamp = ocrChamp
                        finalConfidence = 80
                        diagStatus = DiagnosticStatus.CONFIRMADO
                        diagReason = "Conflicto resuelto por OCR (${ocrChamp.name}) ante baja certeza visual (${eval.candidate1.name} score ${"%.2f".format(Locale.US, eval.score1)})"
                    } else {
                        finalChamp = null
                        finalConfidence = 0
                        diagStatus = DiagnosticStatus.AMBIGUO
                        diagReason = "Conflicto irreconciliable: Visual=${eval.candidate1.name} vs OCR=${ocrChamp.name}. NO ASIGNAR."
                    }
                }
            } else {
                if (ocrChamp != null && !slot.isLikelyUnpicked) {
                    finalChamp = ocrChamp
                    finalConfidence = 80
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Recuperado por texto OCR exacto (${ocrChamp.name})"
                } else {
                    finalChamp = null
                    finalConfidence = 0
                    diagReason = if (slot.isLikelyUnpicked) "Slot sin selección (unpicked)" else eval.reason
                }
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

        lastDebugBitmap.value = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, false)
        lastDiagnostics.value = diagnosticsList

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
            isSuccessful = total > 0,
            statusMessage = statusMsg
        )
    }
}
