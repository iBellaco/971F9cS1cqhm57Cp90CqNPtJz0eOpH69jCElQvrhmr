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
        "calle", "del", "carril", "baron", "barón", "central", "jungla", "jungle", "duo", "dúo", "dragon",
        "dragón", "soporte", "support", "apoyo", "tirador", "beta", "fps:", "ms:", "solo", "superior",
        "medio", "cambiar", "intercambio", "esperando", "eligiendo", "intercambiar", "orden", "turno",
        "bloqueando", "bloqueado", "tiempo", "restante", "smite", "aplastar", "destello", "flash",
        "ignite", "ignición", "curar", "heal", "exhaust", "extenuación", "barrera", "barrier", "fantasma",
        "ghost", "elije", "elige", "campeon", "campeón", "preparate", "prepárate"
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
            val enemySlotEmpty = BooleanArray(5)

            // 1. Procesamiento OCR exclusivo por columnas laterales delimitadas
            // Excluir zona de selección de campeones (0.28 a 0.70) para evitar leer la lista desplegable de campeones
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

                    // --- COLUMNA IZQUIERDA: EQUIPO ALIADO ---
                    // Acotado estrictamente entre 11% y 27.5% del ancho de pantalla.
                    // A partir de 28% comienza la lista de selección de campeones (Syndra, Nami, Akali, etc.)
                    if (centerX in (screenWidth * 0.11f).toInt()..(screenWidth * 0.275f).toInt()) {
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
                            if (lower.contains("central") || lower.contains("mid") || lower.contains("medio")) {
                                allySlotRoles[slotIdx] = LaneRole.MID
                            } else if (lower.contains("baron") || lower.contains("barón") || lower.contains("bar0n") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                                allySlotRoles[slotIdx] = LaneRole.TOP
                            } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva")) {
                                allySlotRoles[slotIdx] = LaneRole.JUNGLE
                            } else if (lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("drag0n") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                                allySlotRoles[slotIdx] = LaneRole.ADC
                            } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") || lower.contains("suporte")) {
                                allySlotRoles[slotIdx] = LaneRole.SUPPORT
                            }

                            // Detección de Campeón Aliado por nombre explícito (excluyendo roles e invocadores)
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
                                    if (allySlots.none { it?.id == candidate.id }) {
                                        allySlots[slotIdx] = candidate
                                        AppLogger.d(TAG, "Aliado detectado por texto en slot $slotIdx: ${candidate.name}")
                                    }
                                }
                            }
                        }
                    }

                    // --- COLUMNA DERECHA: EQUIPO ENEMIGO ---
                    // Acotado estrictamente entre 71% y 88% del ancho de pantalla
                    else if (centerX in (screenWidth * 0.71f).toInt()..(screenWidth * 0.88f).toInt()) {
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

                            // Regla Estricta: Si dice "Jugador 1/2/3/4/5" o "Player", el rival NO ha elegido campeón
                            if (lower.contains("jugador") || lower.contains("player")) {
                                enemySlotEmpty[slotIdx] = true
                                continue
                            }

                            if (lower.contains("central") || lower.contains("mid") || lower.contains("medio")) {
                                enemySlotRoles[slotIdx] = LaneRole.MID
                            } else if (lower.contains("baron") || lower.contains("barón") || lower.contains("bar0n") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                                enemySlotRoles[slotIdx] = LaneRole.TOP
                            } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva")) {
                                enemySlotRoles[slotIdx] = LaneRole.JUNGLE
                            } else if (lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("drag0n") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                                enemySlotRoles[slotIdx] = LaneRole.ADC
                            } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") || lower.contains("suporte")) {
                                enemySlotRoles[slotIdx] = LaneRole.SUPPORT
                            }

                            // Detección de Campeón Enemigo por nombre explícito
                            if (enemySlots[slotIdx] == null && !enemySlotEmpty[slotIdx]) {
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
                                    if (enemySlots.none { it?.id == candidate.id } && allySlots.none { it?.id == candidate.id }) {
                                        enemySlots[slotIdx] = candidate
                                        AppLogger.d(TAG, "Enemigo detectado por texto en slot $slotIdx: ${candidate.name}")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 1.5. Reconocimiento visual de avatar SOLO si la ranura tiene un campeón real y NO está vacía
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
                        AppLogger.d(TAG, "Aliado identificado por firma visual en ranura $i: ${visualAlly.name}")
                    }
                }

                // Si la ranura enemiga dice "Jugador X", está confirmada vacía y PROHIBIDO asignar campeón
                if (enemySlots[i] == null && !enemySlotEmpty[i]) {
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
                        AppLogger.d(TAG, "Enemigo identificado por firma visual en ranura $i: ${visualEnemy.name}")
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
            val sampleXMin = (screenWidth * 0.005f).toInt().coerceAtLeast(0)
            val sampleXMax = (screenWidth * 0.050f).toInt().coerceAtMost(screenWidth - 1)

            // Señal A1: Blasón Dorado Alado de Rol del Jugador Local (Wild Rift Marker)
            // Ubicado justo a la izquierda del texto del carril aliado (X: 0.138f..0.168f)
            val roleBadgeXMin = (screenWidth * 0.138f).toInt().coerceAtLeast(0)
            val roleBadgeXMax = (screenWidth * 0.168f).toInt().coerceAtMost(screenWidth - 1)

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
                if (rubyCount >= 5 || (goldCount >= 14 && orangeCount >= 4)) {
                    slotScores[i] += 4500
                    AppLogger.d(TAG, "Marco dorado con gema rubí detectado en slot $i (+4500)")
                }

                // Detección del Blasón Dorado Alado de Rol (Posición Protegida / Preferencia de Rol)
                // Puede tenerlo cualquier compañero con protección de comodín, por lo que aporta un bono moderado
                var goldRoleBadgeCount = 0
                val bYMin = (yCenter - screenHeight * 0.025f).toInt().coerceAtLeast(0)
                val bYMax = (yCenter + screenHeight * 0.025f).toInt().coerceAtMost(screenHeight - 1)
                for (by in bYMin..bYMax step 2) {
                    for (bx in roleBadgeXMin..roleBadgeXMax step 2) {
                        val bp = processBitmap.getPixel(bx, by)
                        val br = (bp shr 16) and 0xFF
                        val bg = (bp shr 8) and 0xFF
                        val bb = bp and 0xFF
                        if (br in 170..255 && bg in 125..225 && bb in 20..115 && br >= bg + 20) {
                            goldRoleBadgeCount++
                        }
                    }
                }
                if (goldRoleBadgeCount >= 14) {
                    slotScores[i] += 250
                    AppLogger.d(TAG, "Blasón dorado de rol detectado en slot $i (count=$goldRoleBadgeCount)")
                }

                // Señal B: Detección de Hechizo Aplastar (Smite) en el área de hechizos de invocador (X: 0.065 a 0.098)
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
                        slotScores[i] += 6000
                        AppLogger.d(TAG, "Bonus de nombre de invocador aplicado a ranura $i")
                    }
                }
                val hasUserHint = textsInSlot.any { txt ->
                    val low = txt.lowercase(Locale.ROOT)
                    low.contains("elchicho") || low.contains("chicho") || low.contains("chicho7")
                }
                if (hasUserHint) {
                    slotScores[i] += 6000
                    AppLogger.d(TAG, "Bonus de invocador local detectado (chicho) en ranura $i (+6000)")
                }
            }

            // Señal D: Detección de Botones de Intercambio de Turno (Flechas ⇄)
            // En Wild Rift, los compañeros aliados tienen el botón de swap en X ≈ 0.242f..0.275f.
            // La ranura del jugador local NUNCA tiene el botón ⇄ (espacio vacío).
            val swapXMin = (screenWidth * 0.242f).toInt().coerceAtLeast(0)
            val swapXMax = (screenWidth * 0.275f).toInt().coerceAtMost(screenWidth - 1)
            val hasSwapButton = BooleanArray(5)

            for (i in 0 until 5) {
                val yCenter = (screenHeight * allySlotYCenters[i]).toInt()
                val swapYMin = (yCenter - screenHeight * 0.022f).toInt().coerceAtLeast(0)
                val swapYMax = (yCenter + screenHeight * 0.022f).toInt().coerceAtMost(screenHeight - 1)
                var swapSilverCount = 0
                for (sy in swapYMin..swapYMax step 2) {
                    for (sx in swapXMin..swapXMax step 2) {
                        val p = processBitmap.getPixel(sx, sy)
                        val r = (p shr 16) and 0xFF
                        val g = (p shr 8) and 0xFF
                        val b = p and 0xFF
                        // Flechas plateadas/blancas de intercambio ⇄
                        if (r in 130..255 && g in 130..255 && b in 130..255 && kotlin.math.abs(r - g) <= 24 && kotlin.math.abs(r - b) <= 24) {
                            swapSilverCount++
                        }
                    }
                }
                if (swapSilverCount >= 10) {
                    hasSwapButton[i] = true
                }
            }
            val swapCount = hasSwapButton.count { it }
            if (swapCount in 3..4) {
                val userSwapCandidate = (0 until 5).firstOrNull { !hasSwapButton[it] }
                if (userSwapCandidate != null) {
                    slotScores[userSwapCandidate] += 7500
                    AppLogger.d(TAG, "Jugador local identificado por ausencia de botón de swap en ranura $userSwapCandidate (+7500)")
                }
            }

            var userSlotIndex: Int? = null
            val maxScoreSlot = slotScores.indices.maxByOrNull { slotScores[it] } ?: -1
            if (maxScoreSlot != -1 && slotScores[maxScoreSlot] >= 20) {
                userSlotIndex = maxScoreSlot
                AppLogger.d(TAG, "Jugador local identificado en ranura $userSlotIndex con score: ${slotScores[userSlotIndex]}")
            }

            // 3. Determinar el rol/línea del jugador local (Prioridad absoluta al texto del carril asignado al slot del usuario)
            val detectedRole: LaneRole? = if (userSlotIndex != null) {
                allySlotRoles[userSlotIndex]
                    ?: (if (slotHasSmite[userSlotIndex]) LaneRole.JUNGLE else null)
                    ?: allySlots[userSlotIndex]?.primaryRole
                    ?: defaultAllyRoles.getOrNull(userSlotIndex)
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

        // En Wild Rift, según la fase de draft:
        // - Si no hay hechizos visibles (fase de bans/primera selección), el avatar aliado está en X ≈ 0.108f.
        // - Si hay hechizos visibles a la izquierda, el avatar aliado está en X ≈ 0.124f.
        // Muestreamos en ambas coordenadas para máxima compatibilidad.
        val candidateXRatios = if (isAlly) floatArrayOf(0.108f, 0.124f) else floatArrayOf(0.875f, 0.890f)

        for (xRatio in candidateXRatios) {
            val champ = sampleAvatarAt(
                bitmap = bitmap,
                isAlly = isAlly,
                slotIdx = slotIdx,
                expectedRole = expectedRole,
                allChamps = allChamps,
                alreadyDetectedIds = alreadyDetectedIds,
                xCenter = (width * xRatio).toInt(),
                yCenter = yCenter,
                height = height
            )
            if (champ != null) return champ
        }
        return null
    }

    private fun sampleAvatarAt(
        bitmap: Bitmap,
        isAlly: Boolean,
        slotIdx: Int,
        expectedRole: LaneRole?,
        allChamps: List<Champion>,
        alreadyDetectedIds: Set<String>,
        xCenter: Int,
        yCenter: Int,
        height: Int
    ): Champion? {
        val width = bitmap.width
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
        var whitePolarFurCount = 0
        var silverHairCount = 0
        var darkinToneCount = 0
        var darkNightBlueCount = 0
        var gladiatorHelmetCount = 0
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

                // Fondo plano azul noche de Icono de Rol vacío en Wild Rift (aliado sin selección)
                if (r < 45 && g < 55 && b in 18..80 && kotlin.math.abs(r - g) <= 15) {
                    darkNightBlueCount++
                }

                // Casco de gladiador genérico en Wild Rift (rival sin selección)
                if ((r in 45..130 && g in 45..130 && b in 45..130 && kotlin.math.abs(r - g) <= 12 && kotlin.math.abs(r - b) <= 12) ||
                    (r in 40..115 && g < 35 && b < 35)) {
                    gladiatorHelmetCount++
                }

                // Pelaje blanco polar níveo / Tormenta de hielo (Volibear)
                if (r in 148..255 && g in 150..255 && b in 160..255 && kotlin.math.abs(r - g) <= 28 && kotlin.math.abs(r - b) <= 32) {
                    whitePolarFurCount++
                }

                // Cabello plateado / blanco ceniza (Varus)
                if (r in 125..235 && g in 120..230 && b in 135..245 && kotlin.math.abs(r - b) <= 30) {
                    silverHairCount++
                }

                // Rasgos Darkin / bufanda púrpura oscura (Varus)
                if (r in 75..170 && g in 35..95 && b in 65..150) {
                    darkinToneCount++
                }

                // Cuernos curvados oscuros de carnero (Ornn)
                if (r < 55 && g < 50 && b < 50) {
                    ramHornsCount++
                }
                // Barba ardiente de la forja (Ornn)
                else if (r in 110..255 && g in 18..110 && b < 85 && r >= g + 28 && r >= b + 28) {
                    ornnRedBeardCount++
                }
                // Ascuas vivas de la forja (Ornn)
                else if (r > 135 && g in 40..140 && b < 70) {
                    forgeEmberCount++
                }
                // Violeta / Púrpura del Vacío (Cho'Gath)
                else if (r in 40..125 && g in 10..75 && b in 65..165 && b >= g + 25 && r >= g + 15) {
                    voidPurpleCount++
                }
                // Fauces rojas / Carmesí interior de Cho'Gath
                else if (r in 140..250 && g in 10..65 && b in 10..75 && r >= g + 55 && r >= b + 45) {
                    crimsonMouthCount++
                }
                // Dientes de hueso marfil / blanco
                else if (r > 175 && g > 165 && b > 155 && kotlin.math.abs(r - g) < 25 && kotlin.math.abs(r - b) < 25) {
                    boneTeethCount++
                }
                // Roca gris / Granito (Malphite)
                else if (r in 55..120 && g in 55..120 && b in 50..115 && kotlin.math.abs(r - g) <= 12 && kotlin.math.abs(r - b) <= 12) {
                    rockGrayCount++
                }
                // Rojo oscuro demoníaco (Aatrox)
                else if (r > 150 && g < 55 && b < 55) {
                    darkRedCount++
                }
                // Verde explorador (Teemo)
                else if (g > 115 && g > r + 35 && g > b + 35) {
                    greenHatCount++
                }
                // Espectral cian / verde hierro (Mordekaiser)
                else if (g > 105 && b > 95 && g > r + 35) {
                    tealGlowCount++
                }
                // Púrpura vivo / químico (Dr. Mundo)
                else if (r > 145 && b > 120 && g < 70) {
                    vividMagentaCount++
                }
            }
        }

        val isTopLane = expectedRole == LaneRole.TOP || slotIdx == 4
        val isDuoLane = expectedRole == LaneRole.ADC || slotIdx == 0

        // 1. Volibear (Gran oso blanco polar con pelaje de tormenta freljordiana en Top)
        if (isTopLane && !alreadyDetectedIds.contains("volibear") && whitePolarFurCount >= 25) {
            val voli = allChamps.firstOrNull { it.id == "volibear" }
            if (voli != null) {
                AppLogger.d(TAG, "Volibear identificado con éxito en slot $slotIdx por pelaje blanco polar (count=$whitePolarFurCount, x=$xCenter)")
                return voli
            }
        }

        // 2. Varus (Flecha del Castigo en carril Dúo con cabello plateado y rasgos Darkin)
        if (isDuoLane && !alreadyDetectedIds.contains("varus") && (silverHairCount >= 20 || darkinToneCount >= 25)) {
            val varus = allChamps.firstOrNull { it.id == "varus" }
            if (varus != null) {
                AppLogger.d(TAG, "Varus identificado con éxito en slot $slotIdx por visión de avatar (silver=$silverHairCount, darkin=$darkinToneCount, x=$xCenter)")
                return varus
            }
        }

        // 3. Ornn (Cuernos de carnero oscuros + barba ardiente de la forja + ascuas de lava)
        if (isTopLane && !alreadyDetectedIds.contains("ornn") &&
            ((ornnRedBeardCount >= 6 && (ramHornsCount >= 8 || forgeEmberCount >= 2)) || ornnRedBeardCount >= 14)) {
            val ornn = allChamps.firstOrNull { it.id == "ornn" }
            if (ornn != null) {
                AppLogger.d(TAG, "Ornn identificado con éxito en slot $slotIdx por firma visual de forja/cuernos (beard=$ornnRedBeardCount, horns=$ramHornsCount, x=$xCenter)")
                return ornn
            }
        }

        // 4. Cho'Gath (Top / Calle del Barón con Vacío predominante y fauces)
        if (isTopLane && !alreadyDetectedIds.contains("cho_gath") &&
            voidPurpleCount >= 60 && crimsonMouthCount >= 15) {
            val cho = allChamps.firstOrNull { it.id == "cho_gath" }
            if (cho != null) {
                AppLogger.d(TAG, "Cho'Gath identificado con éxito en slot $slotIdx por visión de avatar")
                return cho
            }
        }

        // 5. Malphite (Granito gris masivo)
        if (rockGrayCount >= 320 && isTopLane && !alreadyDetectedIds.contains("malphite")) {
            val malph = allChamps.firstOrNull { it.id == "malphite" }
            if (malph != null) return malph
        }

        // 6. Aatrox
        if (darkRedCount >= 160 && isTopLane && !alreadyDetectedIds.contains("aatrox")) {
            val aatrox = allChamps.firstOrNull { it.id == "aatrox" }
            if (aatrox != null) return aatrox
        }

        // 7. Teemo
        if (greenHatCount >= 110 && isTopLane && !alreadyDetectedIds.contains("teemo")) {
            val teemo = allChamps.firstOrNull { it.id == "teemo" }
            if (teemo != null) return teemo
        }

        // 8. Mordekaiser
        if (tealGlowCount >= 180 && isTopLane && !alreadyDetectedIds.contains("mordekaiser")) {
            val morde = allChamps.firstOrNull { it.id == "mordekaiser" }
            if (morde != null) return morde
        }

        // 9. Dr. Mundo
        if (vividMagentaCount >= 120 && isTopLane && !alreadyDetectedIds.contains("dr_mundo")) {
            val mundo = allChamps.firstOrNull { it.id == "dr_mundo" }
            if (mundo != null) return mundo
        }

        // REGLA: Descartar ranuras confirmadas vacías si no hubo coincidencia
        if (isAlly && darkNightBlueCount >= (totalSampled * 0.65f)) {
            return null
        }
        if (!isAlly && gladiatorHelmetCount >= (totalSampled * 0.55f)) {
            return null
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
