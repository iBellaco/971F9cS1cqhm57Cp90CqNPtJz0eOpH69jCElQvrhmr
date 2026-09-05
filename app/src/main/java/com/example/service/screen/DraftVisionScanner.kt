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

    private fun scanWithImageMatching(bitmap: Bitmap): DraftScanResult {
        val allChamps = WildRiftRepository.champions
        val allySlots = arrayOfNulls<Champion>(5)
        val enemySlots = arrayOfNulls<Champion>(5)
        
        val width = bitmap.width
        val height = bitmap.height
        
        // Coordenadas aproximadas de los avatares circulares en los slots (Landscape)
        // Aliados: Izquierda (~10% al 25% del ancho)
        // Enemigos: Derecha (~75% al 90% del ancho)
        val avatarWidth = (width * 0.12f).toInt()
        val avatarHeight = (height * 0.12f).toInt() // Ajuste proporcional
        
        val allyX = (width * 0.11f).toInt()
        val enemyX = (width * 0.77f).toInt()
        
        for (i in 0..4) {
            val yCenter = height * (0.1f + (i * 0.2f))
            val startY = (yCenter).toInt().coerceIn(0, height - avatarHeight)
            
            // Recortar aliado
            try {
                val allyCrop = Bitmap.createBitmap(bitmap, allyX, startY, avatarWidth, avatarHeight)
                val allyMatch = com.example.util.ImageHashMatcher.findBestMatch(allyCrop, allChamps)
                if (allyMatch != null) allySlots[i] = allyMatch
                allyCrop.recycle()
            } catch (e: Exception) {
                AppLogger.e(TAG, "Error recortando aliado $i", e)
            }
            
            // Recortar enemigo
            try {
                val enemyCrop = Bitmap.createBitmap(bitmap, enemyX, startY, avatarWidth, avatarHeight)
                val enemyMatch = com.example.util.ImageHashMatcher.findBestMatch(enemyCrop, allChamps)
                if (enemyMatch != null) enemySlots[i] = enemyMatch
                enemyCrop.recycle()
            } catch (e: Exception) {
                AppLogger.e(TAG, "Error recortando enemigo $i", e)
            }
        }
        
        val defaultAllyRoles = arrayOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val alliesMap = mutableMapOf<LaneRole, Champion>()
        val enemiesMap = mutableMapOf<LaneRole, Champion>()
        
        for (i in 0..4) {
            allySlots[i]?.let { alliesMap[defaultAllyRoles[i]] = it }
            enemySlots[i]?.let { enemiesMap[defaultAllyRoles[i]] = it }
        }
        
        return DraftScanResult(
            allies = allySlots.filterNotNull(),
            enemies = enemySlots.filterNotNull(),
            alliesByRole = alliesMap,
            enemiesByRole = enemiesMap,
            detectedRole = null, // Requiere otra lógica visual
            detectedRawWords = listOf("Image Matching Active"),
            isSuccessful = true,
            statusMessage = "Escaneo visual completado"
        )
    }


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

    // Mapa de alias comunes para campeones de Wild Rift (coincidencia EXACTA únicamente)
    private val aliasMap = mapOf(
        "tf" to "twisted_fate",
        "twisted fate" to "twisted_fate",
        "mf" to "miss_fortune",
        "miss fortune" to "miss_fortune",
        "mundo" to "dr_mundo",
        "dr mundo" to "dr_mundo",
        "dr. mundo" to "dr_mundo",
        "dr.mundo" to "dr_mundo",
        "yi" to "master_yi",
        "master yi" to "master_yi",
        "aurelion" to "aurelion_sol",
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
        "xin zhao" to "xin_zhao",
        "lee" to "lee_sin",
        "lee sin" to "lee_sin",
        "tahm" to "tahm_kench",
        "tahm kench" to "tahm_kench",
        "tk" to "tahm_kench",
        "renata" to "renata_glasc",
        "renata glasc" to "renata_glasc",
        "wukong" to "wukong",
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
        "tris" to "tristana",
        "naut" to "nautilus",
        "ww" to "warwick",
        "kaisa" to "kai_sa",
        "kai'sa" to "kai_sa",
        "ksante" to "k_sante",
        "k'sante" to "k_sante",
        "chogath" to "cho_gath",
        "cho'gath" to "cho_gath",
        "orn" to "ornn",
        "ornn" to "ornn",
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
        "medio", "cambiar", "intercambio", "esperando", "eligiendo", "orden", "turno",
        "bloqueando", "bloqueado", "tiempo", "restante", "smite", "aplastar", "destello", "flash",
        "ignite", "ignición", "curar", "heal", "exhaust", "extenuación", "barrera", "barrier", "fantasma",
        "ghost", "elije", "elige", "campeon", "campeón", "preparate", "prepárate", "marca", "estelar",
        "eterna", "tarjeta", "aumento", "combatamos", "juntos", "excelente", "composicion", "composición"
    )

    /**
     * Escanea el Bitmap de la pantalla capturada e identifica los campeones en selección y el rol asignado al jugador.
     * La identificación del jugador local ("TÚ") es 100% VISUAL basada en la UI del juego (marco dorado con gema,
     * blasón alado de rol y ausencia de botones de intercambio), sin depender de nombres de cuenta ni invocadores.
     */
    suspend fun scanDraftFromBitmap(bitmap: Bitmap, preferredSummonerName: String? = null): DraftScanResult {
        return try {
            if (bitmap.isRecycled) {
                return DraftScanResult(
                    allies = emptyList(),
                    enemies = emptyList(),
                    detectedRawWords = emptyList(),
                    isSuccessful = false,
                    statusMessage = "Fotograma no disponible."
                )
            }

            // Si el dispositivo está en vertical (Portrait), esperar a que esté en apaisado (Wild Rift)
            if (bitmap.width < bitmap.height) {
                return DraftScanResult(
                    allies = emptyList(),
                    enemies = emptyList(),
                    detectedRawWords = emptyList(),
                    isSuccessful = false,
                    statusMessage = "Esperando pantalla horizontal de Wild Rift..."
                )
            }

            val recognizer = getRecognizer() ?: return DraftScanResult(
                allies = emptyList(),
                enemies = emptyList(),
                detectedRawWords = emptyList(),
                isSuccessful = false,
                statusMessage = "El servicio de visión no se encuentra disponible en este entorno."
            )

            val screenWidth = bitmap.width
            val screenHeight = bitmap.height

            // OPTIMIZACIÓN DE VELOCIDAD: Escalar para OCR si la resolución es superior a 1280px.
            // ML Kit procesa ~10x más rápido en 1280px sin perder precisión de texto.
            var scaledOcrBmp: Bitmap? = null
            val ocrImage = try {
                if (screenWidth > 1280) {
                    val targetWidth = 1280
                    val targetHeight = (screenHeight * (1280f / screenWidth)).toInt()
                    scaledOcrBmp = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true)
                    InputImage.fromBitmap(scaledOcrBmp, 0)
                } else {
                    InputImage.fromBitmap(bitmap, 0)
                }
            } catch (_: Throwable) {
                InputImage.fromBitmap(bitmap, 0)
            }

            val visionText = try {
                recognizer.process(ocrImage).await()
            } finally {
                scaledOcrBmp?.recycle()
            }

            val ocrWidth = if (screenWidth > 1280) 1280 else screenWidth
            val ocrHeight = if (screenWidth > 1280) (screenHeight * (1280f / screenWidth)).toInt() else screenHeight

            val detectedWords = mutableListOf<String>()
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
            val enemySlotTexts = Array(5) { mutableListOf<String>() }
            val enemySlotEmpty = BooleanArray(5)

            val detectedAllyChamps = mutableListOf<Pair<Champion, Float>>()
            val detectedAllyRoles = mutableListOf<Pair<LaneRole, Float>>()
            
            val detectedEnemyChamps = mutableListOf<Pair<Champion, Float>>()
            val detectedEnemyRoles = mutableListOf<Pair<LaneRole, Float>>()

            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    if (lineText.isBlank()) continue
                    detectedWords.add(lineText)

                    val box = line.boundingBox
                    val centerX = box?.centerX() ?: 0
                    val centerY = box?.centerY() ?: 0
                    val xRatio = centerX.toFloat() / ocrWidth.toFloat()
                    val yRatio = centerY.toFloat() / ocrHeight.toFloat()

                    if (yRatio < 0.02f || yRatio > 0.98f) continue

                    val lower = lineText.lowercase(java.util.Locale.ROOT)

                    if (xRatio in 0.06f..0.33f) {
                        val bucket = (yRatio * 5).toInt().coerceIn(0, 4)
                        allySlotTexts[bucket].add(lineText)

                        val role = when {
                            lower.contains("central") || lower.contains("mid") || lower.contains("medio") -> LaneRole.MID
                            lower.contains("baron") || lower.contains("barón") || lower.contains("bar0n") || lower.contains("solo") || lower.contains("superior") || lower.contains("top") -> LaneRole.TOP
                            lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva") -> LaneRole.JUNGLE
                            lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("drag0n") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador") -> LaneRole.ADC
                            lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") || lower.contains("suporte") -> LaneRole.SUPPORT
                            else -> null
                        }
                        if (role != null) detectedAllyRoles.add(role to yRatio)

                        val matchedDirect = matchChampions(lineText, allChamps)
                        val candidate = matchedDirect.firstOrNull() ?: run {
                            val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                            words.firstNotNullOfOrNull { w ->
                                if (!ignoredWords.contains(w.lowercase(java.util.Locale.ROOT))) {
                                    matchChampions(w, allChamps).firstOrNull()
                                } else null
                            }
                        }
                        if (candidate != null && detectedAllyChamps.none { it.first.id == candidate.id }) {
                            detectedAllyChamps.add(candidate to yRatio)
                        }
                    }
                    else if (xRatio in 0.67f..0.94f) {
                        val bucket = (yRatio * 5).toInt().coerceIn(0, 4)
                        enemySlotTexts[bucket].add(lineText)

                        if (lower.contains("jugador") || lower.contains("player")) continue

                        val role = when {
                            lower.contains("central") || lower.contains("mid") || lower.contains("medio") -> LaneRole.MID
                            lower.contains("baron") || lower.contains("barón") || lower.contains("bar0n") || lower.contains("solo") || lower.contains("superior") || lower.contains("top") -> LaneRole.TOP
                            lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva") -> LaneRole.JUNGLE
                            lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("drag0n") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador") -> LaneRole.ADC
                            lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") || lower.contains("suporte") -> LaneRole.SUPPORT
                            else -> null
                        }
                        if (role != null) detectedEnemyRoles.add(role to yRatio)

                        val matchedDirect = matchChampions(lineText, allChamps)
                        val candidate = matchedDirect.firstOrNull() ?: run {
                            val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                            words.firstNotNullOfOrNull { w ->
                                if (!ignoredWords.contains(w.lowercase(java.util.Locale.ROOT))) {
                                    matchChampions(w, allChamps).firstOrNull()
                                } else null
                            }
                        }
                        if (candidate != null && detectedEnemyChamps.none { it.first.id == candidate.id } && detectedAllyChamps.none { it.first.id == candidate.id }) {
                            detectedEnemyChamps.add(candidate to yRatio)
                        }
                    }
                }
            }

            detectedAllyChamps.sortBy { it.second }
            detectedEnemyChamps.sortBy { it.second }
            detectedAllyRoles.sortBy { it.second }
            detectedEnemyRoles.sortBy { it.second }

            // Nueva lógica de asignación proporcional dinámica (elimina el hardcodeo de centros exactos)
            fun getSlotByProportion(yRatio: Float): Int {
                // El 100% de la pantalla se divide en 5 franjas de 20% cada una
                // 0.0 - 0.20 -> Top (0)
                // 0.20 - 0.40 -> Jg (1)
                // 0.40 - 0.60 -> Mid (2)
                // 0.60 - 0.80 -> Duo (3)
                // 0.80 - 1.00 -> Sup (4)
                return (yRatio * 5).toInt().coerceIn(0, 4)
            }

            detectedAllyChamps.forEach { pair ->
                val slot = getSlotByProportion(pair.second)
                if (allySlots[slot] == null) {
                    allySlots[slot] = pair.first
                }
            }
            detectedEnemyChamps.forEach { pair ->
                val slot = getSlotByProportion(pair.second)
                if (enemySlots[slot] == null) {
                    enemySlots[slot] = pair.first
                }
            }
            detectedAllyRoles.forEach { pair ->
                val slot = getSlotByProportion(pair.second)
                if (allySlotRoles[slot] == null) {
                    allySlotRoles[slot] = pair.first
                }
            }
            detectedEnemyRoles.forEach { pair ->
                val slot = getSlotByProportion(pair.second)
                if (enemySlotRoles[slot] == null) {
                    enemySlotRoles[slot] = pair.first
                }
            }

            // 2. Detección Multi-Señal de la ranura del jugador local ("Tú / Yo voy")
            val slotScores = IntArray(5)
            val slotHasSmite = BooleanArray(5)

            for (i in 0 until 5) {
                val yCenter = (screenHeight * allySlotYCenters[i]).toInt()

                // Señal A: Detección de texto de Maestría o 'Marca Estelar Eterna' (exclusivo de la tarjeta del jugador local)
                val hasEternalOrBadge = allySlotTexts[i].any { txt ->
                    val low = txt.lowercase(Locale.ROOT)
                    low.contains("marca") || low.contains("estelar") || low.contains("eterna") ||
                    low.contains("maestria") || low.contains("maestría") || low.contains("eterno")
                }
                if (hasEternalOrBadge) {
                    slotScores[i] += 12000
                    AppLogger.d(TAG, "Texto exclusivo de tarjeta local (Marca Estelar/Eterna) en slot $i (+12000)")
                }

                // Señal B: Marco Dorado Alado, Blasón y Gema Rubí en el lateral izquierdo y marco del avatar (X: 0.030f a 0.150f)
                // En Wild Rift, los aliados tienen un borde cian/azul neutro sin elementos dorados/ámbar.
                // El jugador local ("TÚ") tiene alas de dragón doradas prominentes y aro dorado con núcleo de gema rubí/fuego.
                val sampleXMin = (screenWidth * 0.030f).toInt().coerceAtLeast(0)
                val sampleXMax = (screenWidth * 0.150f).toInt().coerceAtMost(screenWidth - 1)
                val yMin = (yCenter - screenHeight * 0.055f).toInt().coerceAtLeast(0)
                val yMax = (yCenter + screenHeight * 0.055f).toInt().coerceAtMost(screenHeight - 1)

                var goldWingPixels = 0
                var rubyCorePixels = 0
                var orangeWingPixels = 0

                for (y in yMin..yMax step 2) {
                    for (x in sampleXMin..sampleXMax step 2) {
                        val p = bitmap.getPixel(x, y)
                        val r = (p shr 16) and 0xFF
                        val g = (p shr 8) and 0xFF
                        val b = p and 0xFF

                        // Dorado brillante / ámbar (alas del marco y aro dorado de jugador local en Wild Rift)
                        if (r in 140..255 && g in 85..220 && b < 120 && r > b + 30) {
                            goldWingPixels++
                        }
                        // Gema Roja / Rubí (núcleo del blasón del jugador)
                        else if (r in 130..255 && g < 85 && b < 85 && r > g + 40) {
                            rubyCorePixels++
                        }
                        // Naranja fuego / transición de ala
                        else if (r in 160..255 && g in 70..160 && b < 80) {
                            orangeWingPixels++
                        }
                    }
                }
                val wingScore = (goldWingPixels * 8 + rubyCorePixels * 14 + orangeWingPixels * 6)
                slotScores[i] += wingScore
                if (rubyCorePixels >= 2 || (goldWingPixels >= 6 && (orangeWingPixels >= 2 || rubyCorePixels >= 1))) {
                    slotScores[i] += 8000
                    AppLogger.d(TAG, "Marco dorado con gema rubí de jugador local detectado en slot $i (+8000, ruby=$rubyCorePixels, gold=$goldWingPixels)")
                }

                // Señal C: Detección de Hechizo Aplastar (Smite) en el área de hechizos de invocador (X: 0.065 a 0.090)
                val spellsXMin = (screenWidth * 0.065f).toInt().coerceAtLeast(0)
                val spellsXMax = (screenWidth * 0.090f).toInt().coerceAtMost(screenWidth - 1)
                val spellsYMin = (yCenter - screenHeight * 0.035f).toInt().coerceAtLeast(0)
                val spellsYMax = (yCenter + screenHeight * 0.035f).toInt().coerceAtMost(screenHeight - 1)

                var smiteFlameCount = 0
                for (sy in spellsYMin..spellsYMax step 2) {
                    for (sx in spellsXMin..spellsXMax step 2) {
                        val sp = bitmap.getPixel(sx, sy)
                        val sr = (sp shr 16) and 0xFF
                        val sg = (sp shr 8) and 0xFF
                        val sb = sp and 0xFF
                        // Hoja dorada/ámbar brillante de Aplastar (Smite) con energía de rayo
                        if (sr in 195..255 && sg in 140..230 && sb < 85 && sr >= sg) {
                            smiteFlameCount++
                        }
                    }
                }
                if (smiteFlameCount >= 15) {
                    slotHasSmite[i] = true
                    if (allySlotRoles[i] == null) {
                        allySlotRoles[i] = LaneRole.JUNGLE
                    }
                    AppLogger.d(TAG, "Aplastar (Smite) detectado en slot $i (smiteFlame=$smiteFlameCount) -> Asignado a JUNGLE")
                }
            }

            var userSlotIndex: Int? = null
            val maxScoreSlot = slotScores.indices.maxByOrNull { slotScores[it] } ?: -1
            if (maxScoreSlot != -1 && slotScores[maxScoreSlot] >= 100) {
                userSlotIndex = maxScoreSlot
                AppLogger.d(TAG, "Jugador local identificado con alta confianza en ranura $userSlotIndex con score: ${slotScores[userSlotIndex]}")
            }

            // 3. Determinar el rol/línea del jugador local de forma 100% autónoma
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

            // ASIGNACIÓN INTELIGENTE DE ALIADOS:
            // Cada ranura aliada se asigna a su carril correspondiente según OCR explícito, Smite o rol primario del campeón
            for (i in 0 until 5) {
                val champ = allySlots[i] ?: continue
                val explicitRole = allySlotRoles[i]
                    ?: (if (slotHasSmite[i]) LaneRole.JUNGLE else null)
                    ?: champ.primaryRole

                if (!alliesByRole.containsKey(explicitRole)) {
                    alliesByRole[explicitRole] = champ
                }
            }

            // Asignar los campeones aliados restantes que hayan tenido colisión
            for (i in 0 until 5) {
                val champ = allySlots[i] ?: continue
                if (alliesByRole.containsValue(champ)) continue

                val freeRole = champ.secondaryRoles.firstOrNull { !alliesByRole.containsKey(it) }
                    ?: standardOrder.firstOrNull { !alliesByRole.containsKey(it) }
                if (freeRole != null) {
                    alliesByRole[freeRole] = champ
                }
            }

            // Si el jugador local tiene un campeón y rol conocido, asegurar su anclaje
            if (userSlotIndex != null && detectedRole != null) {
                val userChamp = allySlots[userSlotIndex]
                if (userChamp != null && alliesByRole[detectedRole] != userChamp) {
                    alliesByRole[detectedRole] = userChamp
                }
            }

            // ASIGNACIÓN ÓPTIMA DE ENEMIGOS: Asignar según sus roles naturales sin colisiones
            val finalEnemyChamps = enemySlots.filterNotNull()
            val availableEnemyRoles = standardOrder.toMutableList()

            // Asignar primero campeones con rol primario unívoco
            for (champ in finalEnemyChamps) {
                if (availableEnemyRoles.contains(champ.primaryRole) && !enemiesByRole.containsValue(champ)) {
                    val roleMatches = finalEnemyChamps.count { it.primaryRole == champ.primaryRole }
                    if (roleMatches == 1) {
                        enemiesByRole[champ.primaryRole] = champ
                        availableEnemyRoles.remove(champ.primaryRole)
                    }
                }
            }

            // Asignar el resto por rol primario / secundario disponible
            for (champ in finalEnemyChamps) {
                if (!enemiesByRole.containsValue(champ)) {
                    val assignedRole = if (availableEnemyRoles.contains(champ.primaryRole)) {
                        champ.primaryRole
                    } else {
                        champ.secondaryRoles.firstOrNull { availableEnemyRoles.contains(it) }
                            ?: availableEnemyRoles.firstOrNull()
                    }
                    if (assignedRole != null) {
                        enemiesByRole[assignedRole] = champ
                        availableEnemyRoles.remove(assignedRole)
                    }
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

    private fun matchChampions(text: String, allChamps: List<Champion>): List<Champion> {
        val normalized = normalizeString(text)
        if (normalized.length < 2) return emptyList()

        val found = mutableListOf<Champion>()

        // 1. Coincidencia EXACTA mediante tabla de alias
        for ((alias, aliasId) in aliasMap) {
            val aliasNorm = normalizeString(alias)
            if (normalized == aliasNorm) {
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

        // 2. Coincidencia directa exacta o fuzzy de error tipográfico de OCR (distancia <= 1 únicamente)
        for (champ in allChamps) {
            val champNorm = normalizeString(champ.name)
            val idNorm = normalizeString(champ.id)
            
            // Coincidencia exacta estricta
            if (normalized == champNorm || normalized == idNorm) {
                if (!found.contains(champ)) found.add(champ)
            } else if (normalized.length >= 4 && champNorm.length >= 4 && abs(normalized.length - champNorm.length) <= 1) {
                // Fuzzy matching por distancia de Levenshtein para errores de OCR (ej. 'ezrea1' -> 'ezreal', 'vladimlr' -> 'vladimir')
                val distance = calculateLevenshteinDistance(normalized, champNorm)
                if (distance <= 1) {
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
