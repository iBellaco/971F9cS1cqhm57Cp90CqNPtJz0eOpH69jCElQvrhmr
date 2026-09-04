package com.example.service.screen

import android.graphics.Bitmap
import android.graphics.Rect
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.util.AppLogger
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await
import java.util.Locale
import kotlin.math.abs

data class DetectedChampionSlot(
    val champion: Champion,
    val isAlly: Boolean,
    val boundingBox: Rect?,
    val confidence: Float = 0.95f
)

data class DraftScanResult(
    val allies: List<Champion>,
    val enemies: List<Champion>,
    val alliesByRole: Map<LaneRole, Champion> = emptyMap(),
    val enemiesByRole: Map<LaneRole, Champion> = emptyMap(),
    val detectedRole: LaneRole? = null,
    val detectedRawWords: List<String> = emptyList(),
    val isSuccessful: Boolean,
    val statusMessage: String
)

/**
 * Motor de Visión Computacional y Reconocimiento Óptico (OCR) para la pantalla de selección de campeón en Wild Rift.
 * Detecta campeones aliados (columna izquierda) y enemigos (columna derecha) y el rol activo del jugador local ("yo voy").
 */
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

    // Mapa de alias comunes para campeones de Wild Rift (mapeados tanto por ID canónico como nombre)
    private val aliasMap = mapOf(
        "tf" to "twisted_fate",
        "twisted" to "twisted_fate",
        "twisted fate" to "twisted_fate",
        "mf" to "miss_fortune",
        "fortune" to "miss_fortune",
        "miss fortune" to "miss_fortune",
        "mundo" to "dr_mundo",
        "dr mundo" to "dr_mundo",
        "dr. mundo" to "dr_mundo",
        "dr.mundo" to "dr_mundo",
        "yi" to "master_yi",
        "master" to "master_yi",
        "master yi" to "master_yi",
        "aurelion" to "aurelion_sol",
        "sol" to "aurelion_sol",
        "asol" to "aurelion_sol",
        "aurelion sol" to "aurelion_sol",
        "jarvan" to "jarvan_iv",
        "jarvan 4" to "jarvan_iv",
        "jarvan iv" to "jarvan_iv",
        "j4" to "jarvan_iv",
        "nunu" to "nunu_willump",
        "willump" to "nunu_willump",
        "nunu & willump" to "nunu_willump",
        "nunu y willump" to "nunu_willump",
        "xin" to "xin_zhao",
        "zhao" to "xin_zhao",
        "xin zhao" to "xin_zhao",
        "lee" to "lee_sin",
        "sin" to "lee_sin",
        "lee sin" to "lee_sin",
        "tahm" to "tahm_kench",
        "kench" to "tahm_kench",
        "tahm kench" to "tahm_kench",
        "tk" to "tahm_kench",
        "renata" to "renata_glasc",
        "glasc" to "renata_glasc",
        "renata glasc" to "renata_glasc",
        "wukong" to "wukong",
        "monkey" to "wukong",
        "cait" to "caitlyn",
        "caitlin" to "caitlyn",
        "ez" to "ezreal",
        "eve" to "evelynn",
        "kass" to "kassadin",
        "kata" to "katarina",
        "kz" to "kha_zix",
        "k6" to "kha_zix",
        "kha" to "kha_zix",
        "kha'zix" to "kha_zix",
        "khazix" to "kha_zix",
        "renek" to "renekton",
        "vlad" to "vladimir",
        "voli" to "volibear",
        "yas" to "yasuo",
        "luc" to "lucian",
        "tris" to "tristana",
        "naut" to "nautilus",
        "ww" to "warwick",
        "kaisa" to "kai_sa",
        "kai'sa" to "kai_sa",
        "ksante" to "k_sante",
        "k'sante" to "k_sante",
        "chogath" to "cho_gath",
        "orn" to "ornn",
        "ornn" to "ornn",
        "omn" to "ornn",
        "onn" to "ornn",
        "onm" to "ornn",
        "orm" to "ornn",
        "las llamas de la forja" to "ornn",
        "dios de la forja" to "ornn",
        "forja" to "ornn",
        "cho'gath" to "cho_gath",
        "velkoz" to "vel_koz",
        "vel'koz" to "vel_koz",
        "kogmaw" to "kog_maw",
        "kog'maw" to "kog_maw",
        "reksai" to "rek_sai",
        "rek'sai" to "rek_sai",
        "shiva" to "shyvana",
        "shivana" to "shyvana",
        "morde" to "mordekaiser",
        "panth" to "pantheon",
        "malph" to "malphite",
        "blitz" to "blitzcrank",
        "sera" to "seraphine"
    )

    private val ignoredWords = setOf(
        "fase", "seleccion", "selección", "elegir", "confirmar", "bloquear", "bloqueo", "bloqueos",
        "ban", "bans", "maestria", "maestría", "nivel", "level", "jugador", "player", "miembro", "member",
        "wild", "rift", "ranked", "clasificatoria", "normal", "aram", "pvp", "victoria", "derrota",
        "equipo", "team", "azul", "rojo", "blue", "red", "chat", "mute", "op", "fps", "ms", "ping",
        "calle", "del", "baron", "barón", "central", "jungla", "jungle", "duo", "dúo", "dragon",
        "dragón", "soporte", "support", "apoyo", "tirador", "beta", "fps:", "ms:"
    )

    /**
     * Escanea el Bitmap de la pantalla capturada e identifica los campeones en selección y el rol asignado al jugador.
     */
    suspend fun scanDraftFromBitmap(bitmap: Bitmap, preferredSummonerName: String? = null): DraftScanResult {
        return try {
            val recognizer = getRecognizer() ?: return DraftScanResult(
                allies = emptyList(),
                enemies = emptyList(),
                detectedRawWords = emptyList(),
                isSuccessful = false,
                statusMessage = "El servicio de visión no se encuentra disponible en este entorno."
            )

            // Si el bitmap viene en vertical (portrait), rotarlo a horizontal para alinear la lectura con Wild Rift
            val processBitmap = if (bitmap.width < bitmap.height) {
                val matrix = android.graphics.Matrix().apply { postRotate(90f) }
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            } else {
                bitmap
            }

            val inputImage = InputImage.fromBitmap(processBitmap, 0)
            val visionText = recognizer.process(inputImage).await()

            val detectedWords = mutableListOf<String>()
            val screenWidth = processBitmap.width
            val screenHeight = processBitmap.height
            val allChamps = WildRiftRepository.champions

            // Coordenadas verticales oficiales de las 5 ranuras en Wild Rift (Landscape)
            val allySlotYCenters = floatArrayOf(0.185f, 0.325f, 0.465f, 0.605f, 0.745f)
            val enemySlotYCenters = floatArrayOf(0.170f, 0.305f, 0.445f, 0.585f, 0.725f)

            // Mapeo por defecto de roles según el orden habitual de selección en Wild Rift
            val defaultAllyRoles = arrayOf(LaneRole.ADC, LaneRole.SUPPORT, LaneRole.MID, LaneRole.JUNGLE, LaneRole.TOP)
            val defaultEnemyRoles = arrayOf(LaneRole.ADC, LaneRole.JUNGLE, LaneRole.MID, LaneRole.SUPPORT, LaneRole.TOP)

            val allySlots = arrayOfNulls<Champion>(5)
            val enemySlots = arrayOfNulls<Champion>(5)
            val allySlotRoles = arrayOfNulls<LaneRole>(5)
            val enemySlotRoles = arrayOfNulls<LaneRole>(5)
            val allySlotTexts = Array(5) { mutableListOf<String>() }

            // 1. Procesamiento OCR exclusivo por columnas laterales (EXCLUIR CENTRO PARA EVITAR LEER EL ASISTENTE FLOTANTE)
            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    if (lineText.isBlank()) continue
                    detectedWords.add(lineText)

                    val box = line.boundingBox
                    val centerX = box?.centerX() ?: 0
                    val centerY = box?.centerY() ?: 0

                    // Descartar barra superior (bans < 8%) y extremos inferiores (> 88%)
                    if (centerY < screenHeight * 0.08f || centerY > screenHeight * 0.88f) {
                        continue
                    }

                    // Excluir zona central donde flota el Asistente/Coach (38% a 68% del ancho)
                    if (centerX in (screenWidth * 0.38f).toInt()..(screenWidth * 0.68f).toInt()) {
                        continue
                    }

                    // --- COLUMNA IZQUIERDA: EQUIPO ALIADO ---
                    if (centerX < screenWidth * 0.38f) {
                        val yRatio = centerY.toFloat() / screenHeight.toFloat()
                        val slotIdx = when {
                            yRatio < 0.255f -> 0
                            yRatio < 0.395f -> 1
                            yRatio < 0.535f -> 2
                            yRatio < 0.675f -> 3
                            else -> 4
                        }

                        if (slotIdx in 0 until 5) {
                            allySlotTexts[slotIdx].add(lineText)
                            val lower = lineText.lowercase(Locale.ROOT)

                            // Detección de Rol/Línea por texto en la ranura
                            // En Wild Rift, el orden de ranuras aliadas corresponde a: 0=ADC, 1=SUPPORT, 2=MID, 3=JUNGLE, 4=TOP
                            if (lower.contains("central") || lower.contains("mid") || lower.contains("medio")) {
                                allySlotRoles[slotIdx] = LaneRole.MID
                            } else if (lower.contains("baron") || lower.contains("barón") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                                // "CALLE DEL BARÓN" corresponde exclusivamente a la posición de Top (ranura 4)
                                allySlotRoles[4] = LaneRole.TOP
                            } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg")) {
                                allySlotRoles[3] = LaneRole.JUNGLE
                            } else if (lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                                allySlotRoles[0] = LaneRole.ADC
                            } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup")) {
                                allySlotRoles[1] = LaneRole.SUPPORT
                            }

                            // Detección de Campeón Aliado
                            if (allySlots[slotIdx] == null) {
                                val matchedDirect = matchChampions(lineText, allChamps)
                                val candidate = matchedDirect.firstOrNull() ?: run {
                                    val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                                    words.firstNotNullOfOrNull { w ->
                                        if (!ignoredWords.contains(w.lowercase(Locale.ROOT))) {
                                            matchChampions(w, allChamps).firstOrNull()
                                        } else null
                                    }
                                }
                                if (candidate != null) {
                                    // Regla estricta: No asignar si ya fue detectado en otra ranura aliada
                                    if (allySlots.none { it?.id == candidate.id }) {
                                        allySlots[slotIdx] = candidate
                                        AppLogger.d(TAG, "Aliado detectado en slot $slotIdx: ${candidate.name}")
                                    }
                                }
                            }
                        }
                    }

                    // --- COLUMNA DERECHA: EQUIPO ENEMIGO ---
                    else if (centerX > screenWidth * 0.68f) {
                        val yRatio = centerY.toFloat() / screenHeight.toFloat()
                        val slotIdx = when {
                            yRatio < 0.240f -> 0
                            yRatio < 0.375f -> 1
                            yRatio < 0.515f -> 2
                            yRatio < 0.655f -> 3
                            else -> 4
                        }

                        if (slotIdx in 0 until 5) {
                            val lower = lineText.lowercase(Locale.ROOT)
                            if (lower.contains("central") || lower.contains("mid") || lower.contains("medio")) {
                                enemySlotRoles[slotIdx] = LaneRole.MID
                            } else if (lower.contains("baron") || lower.contains("barón") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                                enemySlotRoles[4] = LaneRole.TOP
                            } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg")) {
                                enemySlotRoles[1] = LaneRole.JUNGLE
                            } else if (lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                                enemySlotRoles[0] = LaneRole.ADC
                            } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup")) {
                                enemySlotRoles[3] = LaneRole.SUPPORT
                            }

                            // Detección de Campeón Enemigo
                            if (enemySlots[slotIdx] == null) {
                                val matchedDirect = matchChampions(lineText, allChamps)
                                val candidate = matchedDirect.firstOrNull() ?: run {
                                    val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                                    words.firstNotNullOfOrNull { w ->
                                        if (!ignoredWords.contains(w.lowercase(Locale.ROOT))) {
                                            matchChampions(w, allChamps).firstOrNull()
                                        } else null
                                    }
                                }
                                if (candidate != null) {
                                    // Regla estricta: No asignar si ya fue detectado en otra ranura enemiga o aliada
                                    if (enemySlots.none { it?.id == candidate.id } && allySlots.none { it?.id == candidate.id }) {
                                        enemySlots[slotIdx] = candidate
                                        AppLogger.d(TAG, "Enemigo detectado en slot $slotIdx: ${candidate.name}")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 1.5. Reconocimiento de campeones por avatar circular para ranuras vacías
            // Obtenemos los IDs ya confirmados por OCR para PROHIBIR que el avatar los duplique
            val ocrConfirmedIds = (allySlots.filterNotNull() + enemySlots.filterNotNull()).map { it.id }.toMutableSet()

            for (i in 0 until 5) {
                if (allySlots[i] == null) {
                    val visualAlly = identifyChampionFromSlotAvatar(
                        bitmap = processBitmap,
                        isAlly = true,
                        slotIdx = i,
                        expectedRole = allySlotRoles[i] ?: defaultAllyRoles.getOrNull(i),
                        allChamps = allChamps,
                        alreadyDetectedIds = ocrConfirmedIds
                    )
                    if (visualAlly != null) {
                        allySlots[i] = visualAlly
                        ocrConfirmedIds.add(visualAlly.id)
                        AppLogger.d(TAG, "Aliado identificado por firma de avatar en ranura $i: ${visualAlly.name}")
                    }
                }

                if (enemySlots[i] == null) {
                    val visualEnemy = identifyChampionFromSlotAvatar(
                        bitmap = processBitmap,
                        isAlly = false,
                        slotIdx = i,
                        expectedRole = enemySlotRoles[i] ?: defaultEnemyRoles.getOrNull(i),
                        allChamps = allChamps,
                        alreadyDetectedIds = ocrConfirmedIds
                    )
                    if (visualEnemy != null) {
                        enemySlots[i] = visualEnemy
                        ocrConfirmedIds.add(visualEnemy.id)
                        AppLogger.d(TAG, "Enemigo identificado por firma de avatar en ranura $i: ${visualEnemy.name}")
                    }
                }
            }

            // 1.6. DEDUPLICACIÓN GLOBAL INICIAL: Asegurar unicidad total en las ranuras físicas
            val seenSlotChampionIds = mutableSetOf<String>()
            for (i in 0 until 5) {
                val champ = allySlots[i] ?: continue
                if (seenSlotChampionIds.contains(champ.id)) {
                    AppLogger.w(TAG, "Eliminando duplicado aliado de ${champ.name} en slot $i")
                    allySlots[i] = null
                } else {
                    seenSlotChampionIds.add(champ.id)
                }
            }
            for (i in 0 until 5) {
                val champ = enemySlots[i] ?: continue
                if (seenSlotChampionIds.contains(champ.id)) {
                    AppLogger.w(TAG, "Eliminando duplicado enemigo de ${champ.name} en slot $i (ya existe en la draft)")
                    enemySlots[i] = null
                } else {
                    seenSlotChampionIds.add(champ.id)
                }
            }

            // 2. Detección Multi-Señal de la ranura del jugador local ("Tú / Yo voy")
            val slotScores = IntArray(5)
            val slotHasSmite = BooleanArray(5)

            // Señal A: Ornamento de Dragón Alado Dorado / Gema Rubí en el lateral izquierdo extremo (0.005f a 0.050f)
            // Aislado cuidadosamente para NO muestrear los hechizos de invocador (Destello/Prender) en 0.065f..0.098f
            val sampleXMin = (screenWidth * 0.005f).toInt().coerceAtLeast(0)
            val sampleXMax = (screenWidth * 0.050f).toInt().coerceAtMost(screenWidth - 1)

            for (i in 0 until 5) {
                val yCenter = (screenHeight * allySlotYCenters[i]).toInt()
                val yMin = (yCenter - screenHeight * 0.055f).toInt().coerceAtLeast(0)
                val yMax = (yCenter + screenHeight * 0.055f).toInt().coerceAtMost(screenHeight - 1)

                var goldCount = 0
                var rubyCount = 0
                var orangeCount = 0
                var cyanCount = 0

                for (y in yMin..yMax step 2) {
                    for (x in sampleXMin..sampleXMax step 2) {
                        val p = processBitmap.getPixel(x, y)
                        val r = (p shr 16) and 0xFF
                        val g = (p shr 8) and 0xFF
                        val b = p and 0xFF

                        // Dorado / Ámbar (alas del marco de jugador activo en Wild Rift)
                        if (r > 135 && g > 80 && b < 100 && r > b + 35) {
                            goldCount++
                        }
                        // Gema Roja / Rubí (núcleo del blasón del jugador)
                        else if (r > 135 && g < 75 && b < 75 && r > g + 50) {
                            rubyCount++
                        }
                        // Naranja fuego / transición de ala
                        else if (r > 165 && g in 70..145 && b < 65) {
                            orangeCount++
                        }
                        // Resaltado cian alternativo
                        else if (b > 115 && g > 85 && b > r + 25) {
                            cyanCount++
                        }
                    }
                }
                val wingScore = (goldCount * 4 + rubyCount * 4 + orangeCount * 3 + cyanCount * 2)
                slotScores[i] += wingScore
                // Bonificación masiva al marco ornamental de dragón con rubí (exclusivo del jugador local en Wild Rift)
                if (rubyCount >= 5 || (goldCount >= 14 && orangeCount >= 4)) {
                    slotScores[i] += 4500
                    AppLogger.d(TAG, "Marco dorado con gema rubí detectado en slot $i (+4500)")
                }
                AppLogger.d(TAG, "Slot $i Dragon Wing Score: $wingScore (gold=$goldCount, ruby=$rubyCount, orange=$orangeCount)")

                // Señal B: Detección de Hechizo Aplastar (Smite) en el área de hechizos de invocador (X: 0.065 a 0.098)
                // Smite en Wild Rift posee una hoja dorada/amarilla viva con rayos (R alto, G medio-alto, B bajo).
                // Prender (Ignite) es rojo fuego (R alto, G bajo). Destello (Flash) es amarillo uniforme.
                // REGLA CRÍTICA: Un campeón cuyo rol nativo NO es Jungla (ej. Syndra, Sona, Varus, Ornn) NUNCA debe marcarse como Jungla por hechizos.
                val champInSlot = allySlots[i]
                val canChampionJungle = champInSlot == null || 
                        champInSlot.primaryRole == LaneRole.JUNGLE || 
                        champInSlot.secondaryRoles.contains(LaneRole.JUNGLE)

                if (canChampionJungle) {
                    val spellsXMin = (screenWidth * 0.065f).toInt().coerceAtLeast(0)
                    val spellsXMax = (screenWidth * 0.098f).toInt().coerceAtMost(screenWidth - 1)
                    val spellsYMin = (yCenter - screenHeight * 0.040f).toInt().coerceAtLeast(0)
                    val spellsYMax = (yCenter + screenHeight * 0.040f).toInt().coerceAtMost(screenHeight - 1)

                    var smiteFlameCount = 0
                    for (sy in spellsYMin..spellsYMax step 2) {
                        for (sx in spellsXMin..spellsXMax step 2) {
                            val sp = processBitmap.getPixel(sx, sy)
                            val sr = (sp shr 16) and 0xFF
                            val sg = (sp shr 8) and 0xFF
                            val sb = sp and 0xFF
                            // Hoja dorada/ámbar brillante de Aplastar (Smite) con energía de rayo
                            if (sr in 195..255 && sg in 140..230 && sb < 85 && sr >= sg) {
                                smiteFlameCount++
                            }
                        }
                    }
                    if (smiteFlameCount >= 22) {
                        slotHasSmite[i] = true
                        if (allySlotRoles[i] == null) {
                            allySlotRoles[i] = LaneRole.JUNGLE
                        }
                        AppLogger.d(TAG, "Aplastar (Smite) detectado en slot $i (smiteFlame=$smiteFlameCount)")
                    }
                }
            }

            // Señal C: Coincidencia por nombre de invocador
            val normPreferred = preferredSummonerName?.let { normalizeString(it) }
            for (i in 0 until 5) {
                val textsInSlot = allySlotTexts[i]
                if (!normPreferred.isNullOrBlank() && normPreferred.length >= 3) {
                    val hasName = textsInSlot.any { txt ->
                        val normTxt = normalizeString(txt)
                        normTxt.contains(normPreferred) || normPreferred.contains(normTxt)
                    }
                    if (hasName) {
                        slotScores[i] += 3000
                        AppLogger.d(TAG, "Bonus de nombre de invocador aplicado a ranura $i")
                    }
                }
                // Si contiene el nombre específico del usuario (ej. elchicho, chicho)
                // NO usar tags genéricos como "xcs" que pueden compartir compañeros de gremio
                val hasUserHint = textsInSlot.any { txt ->
                    val low = txt.lowercase(Locale.ROOT)
                    low.contains("elchicho") || low.contains("chicho") || low.contains("chicho7")
                }
                if (hasUserHint) {
                    slotScores[i] += 5000
                    AppLogger.d(TAG, "Bonus de invocador local detectado (chicho) en ranura $i (+5000)")
                }
            }

            var userSlotIndex: Int? = null
            val maxScoreSlot = slotScores.indices.maxByOrNull { slotScores[it] } ?: -1
            if (maxScoreSlot != -1 && slotScores[maxScoreSlot] >= 20) {
                userSlotIndex = maxScoreSlot
                AppLogger.d(TAG, "Jugador local identificado en ranura $userSlotIndex con score: ${slotScores[userSlotIndex]}")
            }

            // 3. Determinar el rol/línea del jugador local
            val detectedRole: LaneRole? = if (userSlotIndex != null) {
                if (slotHasSmite[userSlotIndex]) {
                    LaneRole.JUNGLE // ¡Smite garantiza Jungle al 100%!
                } else {
                    allySlotRoles[userSlotIndex]
                        ?: allySlots[userSlotIndex]?.primaryRole
                        ?: defaultAllyRoles.getOrNull(userSlotIndex)
                }
            } else {
                null
            }

            // 4. Estructurar la asignación exacta por rol (alliesByRole y enemiesByRole)
            val alliesByRole = mutableMapOf<LaneRole, Champion>()
            val enemiesByRole = mutableMapOf<LaneRole, Champion>()
            val standardOrder = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)

            // ANCLA CRÍTICA 1: El campeón del usuario local ("TÚ") SIEMPRE se asigna a su línea detectada
            if (userSlotIndex != null && detectedRole != null) {
                val userChamp = allySlots[userSlotIndex]
                if (userChamp != null) {
                    alliesByRole[detectedRole] = userChamp
                    AppLogger.d(TAG, "ANCLA USUARIO: Asignando campeón local ${userChamp.name} a su carril $detectedRole")
                }
            }

            // ASIGNACIÓN DIRECTA DE RANURAS ALIADAS:
            // En Wild Rift, el orden físico de las 5 ranuras aliadas en draft es:
            // 0=ADC (Dúo), 1=SUPPORT, 2=MID, 3=JUNGLE, 4=TOP (Barón)
            for (i in 0 until 5) {
                if (i == userSlotIndex) continue
                val champ = allySlots[i] ?: continue
                if (alliesByRole.containsValue(champ)) continue

                // Prioridad 1: Rol asignado a la ranura por posición o texto explícito
                val targetRole = allySlotRoles[i] ?: defaultAllyRoles.getOrNull(i)
                if (targetRole != null && !alliesByRole.containsKey(targetRole)) {
                    alliesByRole[targetRole] = champ
                } else if (!alliesByRole.containsKey(champ.primaryRole)) {
                    alliesByRole[champ.primaryRole] = champ
                }
            }

            // Rellenar vacantes aliadas si algún campeón quedó pendiente
            for (i in 0 until 5) {
                if (i == userSlotIndex) continue
                val champ = allySlots[i] ?: continue
                if (alliesByRole.containsValue(champ)) continue

                val freeRole = champ.secondaryRoles.firstOrNull { !alliesByRole.containsKey(it) }
                    ?: standardOrder.firstOrNull { !alliesByRole.containsKey(it) }
                if (freeRole != null) {
                    alliesByRole[freeRole] = champ
                }
            }

            // ASIGNACIÓN DIRECTA DE RANURAS ENEMIGAS:
            // En Wild Rift, el orden físico de las 5 ranuras enemigas en draft es:
            // 0=ADC (Dúo), 1=JUNGLE, 2=MID, 3=SUPPORT, 4=TOP (Barón)
            for (i in 0 until 5) {
                val champ = enemySlots[i] ?: continue
                if (enemiesByRole.containsValue(champ)) continue

                val targetRole = enemySlotRoles[i] ?: defaultEnemyRoles.getOrNull(i)
                if (targetRole != null && !enemiesByRole.containsKey(targetRole)) {
                    enemiesByRole[targetRole] = champ
                } else if (!enemiesByRole.containsKey(champ.primaryRole)) {
                    enemiesByRole[champ.primaryRole] = champ
                }
            }

            // Rellenar vacantes enemigas si algún campeón quedó pendiente
            for (i in 0 until 5) {
                val champ = enemySlots[i] ?: continue
                if (enemiesByRole.containsValue(champ)) continue

                val freeRole = champ.secondaryRoles.firstOrNull { !enemiesByRole.containsKey(it) }
                    ?: standardOrder.firstOrNull { !enemiesByRole.containsKey(it) }
                if (freeRole != null) {
                    enemiesByRole[freeRole] = champ
                }
            }

            // REGLA CRÍTICA MOBA (UNICIDAD GLOBAL): Ningún campeón puede figurar en ambos equipos
            val allyChampIds = alliesByRole.values.map { it.id }.toSet()
            enemiesByRole.entries.removeIf { entry ->
                val duplicate = allyChampIds.contains(entry.value.id)
                if (duplicate) {
                    AppLogger.w(TAG, "DEDUPLICACIÓN GLOBAL: Removiendo ${entry.value.name} de enemigos porque ya está en aliados")
                }
                duplicate
            }

            val foundAllies = standardOrder.mapNotNull { alliesByRole[it] }
            val foundEnemies = standardOrder.mapNotNull { enemiesByRole[it] }

            val totalDetected = foundAllies.size + foundEnemies.size
            val status = if (totalDetected > 0 || detectedRole != null) {
                "Escaneo exitoso: $totalDetected campeones asignados" + (if (detectedRole != null) " • Tu rol: ${detectedRole.displayName}" else "")
            } else {
                "No se detectaron selecciones de campeones en el frame actual."
            }

            AppLogger.d(TAG, "Resultado: Aliados=${alliesByRole.map { "${it.key.shortName}:${it.value.name}" }} vs Enemigos=${enemiesByRole.map { "${it.key.shortName}:${it.value.name}" }} (Rol detectado: $detectedRole)")

            if (processBitmap != bitmap) {
                try { processBitmap.recycle() } catch (_: Exception) {}
            }

            DraftScanResult(
                allies = foundAllies,
                enemies = foundEnemies,
                alliesByRole = alliesByRole,
                enemiesByRole = enemiesByRole,
                detectedRole = detectedRole,
                detectedRawWords = detectedWords,
                isSuccessful = totalDetected > 0 || detectedRole != null,
                statusMessage = status
            )
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error durante el análisis visual del draft", e)
            DraftScanResult(
                allies = emptyList(),
                enemies = emptyList(),
                alliesByRole = emptyMap(),
                enemiesByRole = emptyMap(),
                detectedRawWords = emptyList(),
                isSuccessful = false,
                statusMessage = "Fallo en motor de visión: ${e.localizedMessage ?: "Error desconocido"}"
            )
        }
    }

    /**
     * Identifica el campeón analizando los píxeles del avatar circular cuando el texto no muestra su nombre
     * (por ejemplo, cuando Wild Rift muestra "CALLE DEL BARÓN", "CENTRAL", etc., durante la fase de elección/hover).
     */
    private fun identifyChampionFromSlotAvatar(
        bitmap: Bitmap,
        isAlly: Boolean,
        slotIdx: Int,
        expectedRole: LaneRole?,
        allChamps: List<Champion>,
        alreadyDetectedIds: Set<String> = emptySet()
    ): Champion? {
        val width = bitmap.width
        val height = bitmap.height
        val slotYCenters = if (isAlly) floatArrayOf(0.185f, 0.325f, 0.465f, 0.605f, 0.745f)
                           else floatArrayOf(0.170f, 0.305f, 0.445f, 0.585f, 0.725f)
        if (slotIdx !in slotYCenters.indices) return null

        val yCenter = (height * slotYCenters[slotIdx]).toInt()
        val xCenter = (width * if (isAlly) 0.125f else 0.875f).toInt()
        val radius = (height * 0.042f).toInt()

        val xMin = (xCenter - radius).coerceAtLeast(0)
        val xMax = (xCenter + radius).coerceAtMost(width - 1)
        val yMin = (yCenter - radius).coerceAtLeast(0)
        val yMax = (yCenter + radius).coerceAtMost(height - 1)
        val radiusSq = radius * radius

        var voidPurpleCount = 0
        var crimsonMouthCount = 0
        var boneTeethCount = 0
        var rockGrayCount = 0
        var darkRedCount = 0
        var greenHatCount = 0
        var ornnRedBeardCount = 0
        var ramHornsCount = 0
        var forgeEmberCount = 0
        var tealGlowCount = 0
        var vividMagentaCount = 0
        var totalSampled = 0

        for (y in yMin..yMax step 2) {
            val dy = y - yCenter
            for (x in xMin..xMax step 2) {
                val dx = x - xCenter
                if (dx * dx + dy * dy > radiusSq) continue
                totalSampled++

                val p = bitmap.getPixel(x, y)
                val r = (p shr 16) and 0xFF
                val g = (p shr 8) and 0xFF
                val b = p and 0xFF

                // Cuernos curvados oscuros de carnero (Ornn)
                if (r < 55 && g < 50 && b < 50) {
                    ramHornsCount++
                }
                // Barba roja ardiente / carmesí de la forja (Ornn)
                if (r in 95..245 && g in 15..95 && b < 85 && r >= g + 25 && r >= b + 25) {
                    ornnRedBeardCount++
                }
                // Ascuas / brillo de la forja (Ornn)
                if (r > 140 && g in 45..140 && b < 65) {
                    forgeEmberCount++
                }
                // Violeta / Púrpura del Vacío (Cho'Gath)
                if (r in 25..125 && g in 10..80 && b in 45..155 && b >= g + 8 && r >= g + 5) {
                    voidPurpleCount++
                }
                // Fauces rojas / Carmesí interior de Cho'Gath
                else if (r in 115..245 && g in 10..75 && b in 10..85 && r >= g + 40 && r >= b + 35) {
                    crimsonMouthCount++
                }
                // Dientes de hueso marfil / blanco
                else if (r > 155 && g > 145 && b > 135 && kotlin.math.abs(r - g) < 30 && kotlin.math.abs(r - b) < 30) {
                    boneTeethCount++
                }
                // Roca gris / Granito (Malphite)
                else if (r in 45..125 && g in 45..125 && b in 40..120 && kotlin.math.abs(r - g) <= 18 && kotlin.math.abs(r - b) <= 18) {
                    rockGrayCount++
                }
                // Rojo oscuro / Oscuro demoníaco (Aatrox)
                else if (r > 130 && g < 65 && b < 65) {
                    darkRedCount++
                }
                // Verde explorador (Teemo)
                else if (g > 95 && g > r + 25 && g > b + 25) {
                    greenHatCount++
                }
                // Espectral cian / verde hierro (Mordekaiser)
                else if (g > 85 && b > 75 && g > r + 25) {
                    tealGlowCount++
                }
                // Púrpura vivo / químico (Dr. Mundo)
                else if (r > 130 && b > 105 && g < 75) {
                    vividMagentaCount++
                }
            }
        }

        AppLogger.d(TAG, "Avatar slot $slotIdx (isAlly=$isAlly, role=$expectedRole): total=$totalSampled, ornnBeard=$ornnRedBeardCount, horns=$ramHornsCount, embers=$forgeEmberCount, purple=$voidPurpleCount, teeth=$boneTeethCount")

        // 1. Ornn (Cuernos de carnero gigantes oscuros + barba ardiente de la forja + ascuas de lava)
        val isTopLane = expectedRole == LaneRole.TOP || slotIdx == 4
        if (isTopLane && !alreadyDetectedIds.contains("ornn") &&
            ((ornnRedBeardCount >= 6 && (ramHornsCount >= 8 || forgeEmberCount >= 2)) || ornnRedBeardCount >= 14)) {
            val ornn = allChamps.firstOrNull { it.id == "ornn" }
            if (ornn != null) {
                AppLogger.d(TAG, "Ornn identificado con éxito en slot $slotIdx por firma visual de forja/cuernos")
                return ornn
            }
        }

        // 2. Cho'Gath (Top / Calle del Barón con Vacío predominante y fauces)
        // IMPORTANTE: Cho'Gath no posee la barba roja de la forja ni cuernos de carnero de Ornn
        if (isTopLane && !alreadyDetectedIds.contains("cho_gath") && ornnRedBeardCount < 6 &&
            ((voidPurpleCount >= 60 && crimsonMouthCount >= 15) || voidPurpleCount >= 120)) {
            val cho = allChamps.firstOrNull { it.id == "cho_gath" }
            if (cho != null) {
                AppLogger.d(TAG, "Cho'Gath identificado con éxito en slot $slotIdx por visión de avatar")
                return cho
            }
        }

        // 3. Malphite
        if (rockGrayCount >= 250 && isTopLane && !alreadyDetectedIds.contains("malphite")) {
            val malph = allChamps.firstOrNull { it.id == "malphite" }
            if (malph != null) return malph
        }

        // 4. Aatrox
        if (darkRedCount >= 160 && isTopLane && !alreadyDetectedIds.contains("aatrox")) {
            val aatrox = allChamps.firstOrNull { it.id == "aatrox" }
            if (aatrox != null) return aatrox
        }

        // 5. Teemo
        if (greenHatCount >= 100 && isTopLane && !alreadyDetectedIds.contains("teemo")) {
            val teemo = allChamps.firstOrNull { it.id == "teemo" }
            if (teemo != null) return teemo
        }

        // 6. Mordekaiser (Requiere alta densidad de brillo espectral y no haber sido detectado ya por OCR)
        if (tealGlowCount >= 220 && isTopLane && !alreadyDetectedIds.contains("mordekaiser")) {
            val morde = allChamps.firstOrNull { it.id == "mordekaiser" }
            if (morde != null) return morde
        }

        // 7. Dr. Mundo
        if (vividMagentaCount >= 130 && isTopLane && !alreadyDetectedIds.contains("dr_mundo")) {
            val mundo = allChamps.firstOrNull { it.id == "dr_mundo" }
            if (mundo != null) return mundo
        }

        return null
    }

    private fun matchChampions(text: String, allChamps: List<Champion>): List<Champion> {
        val normalized = normalizeString(text)
        if (normalized.length < 2) return emptyList()

        val found = mutableListOf<Champion>()

        // 1. Coincidencia mediante tabla de alias
        for ((alias, aliasId) in aliasMap) {
            val aliasNorm = normalizeString(alias)
            if (normalized == aliasNorm || (aliasNorm.length >= 3 && (normalized.startsWith(aliasNorm) || normalized.endsWith(aliasNorm)))) {
                val targetNorm = normalizeString(aliasId)
                val champ = allChamps.firstOrNull { 
                    normalizeString(it.id) == targetNorm || 
                    normalizeString(it.name) == targetNorm ||
                    it.id.equals(aliasId, ignoreCase = true) ||
                    it.name.equals(aliasId, ignoreCase = true)
                }
                if (champ != null && !found.contains(champ)) {
                    found.add(champ)
                }
            }
        }

        // 2. Coincidencia directa, por prefijo/sufijo o subcadena exacta
        for (champ in allChamps) {
            val champNorm = normalizeString(champ.name)
            val idNorm = normalizeString(champ.id)
            
            if (normalized == champNorm || normalized == idNorm) {
                if (!found.contains(champ)) found.add(champ)
            } else if (champNorm.length >= 3 && (normalized.startsWith(champNorm) || normalized.endsWith(champNorm))) {
                if (!found.contains(champ)) found.add(champ)
            } else if (idNorm.length >= 3 && (normalized.startsWith(idNorm) || normalized.endsWith(idNorm))) {
                if (!found.contains(champ)) found.add(champ)
            } else if (champNorm.length >= 4 && normalized.contains(champNorm)) {
                if (!found.contains(champ)) found.add(champ)
            } else if (idNorm.length >= 4 && normalized.contains(idNorm)) {
                if (!found.contains(champ)) found.add(champ)
            } else if (normalized.length >= 4 && champNorm.length >= 4) {
                // Fuzzy matching por distancia de Levenshtein (tolerar errores de OCR como 5->S, 1->I, V->Y)
                val distance = calculateLevenshteinDistance(normalized, champNorm)
                val maxAllowed = if (champNorm.length >= 7) 2 else 1
                if (distance <= maxAllowed) {
                    if (!found.contains(champ)) found.add(champ)
                }
            }
        }

        return found
    }

    private fun calculateLevenshteinDistance(s1: String, s2: String): Int {
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }
        for (i in 0..s1.length) dp[i][0] = i
        for (j in 0..s2.length) dp[0][j] = j

        for (i in 1..s1.length) {
            for (j in 1..s2.length) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,
                    dp[i][j - 1] + 1,
                    dp[i - 1][j - 1] + cost
                )
            }
        }
        return dp[s1.length][s2.length]
    }

    private fun normalizeString(input: String): String {
        return input.lowercase(Locale.ROOT)
            .replace("[^a-z0-9]".toRegex(), "")
    }
}
