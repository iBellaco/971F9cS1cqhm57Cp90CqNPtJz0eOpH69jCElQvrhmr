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
                        val slotIdx = allySlotYCenters.indices.minByOrNull {
                            val slotY = (screenHeight * allySlotYCenters[it]).toInt()
                            abs(centerY - slotY)
                        } ?: -1

                        if (slotIdx in 0 until 5) {
                            allySlotTexts[slotIdx].add(lineText)
                            val lower = lineText.lowercase(Locale.ROOT)

                            // Detección de Rol/Línea por texto en la ranura
                            if (lower.contains("central") || lower.contains("mid") || lower.contains("medio")) {
                                allySlotRoles[slotIdx] = LaneRole.MID
                            } else if (lower.contains("baron") || lower.contains("barón") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                                allySlotRoles[slotIdx] = LaneRole.TOP
                            } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg")) {
                                allySlotRoles[slotIdx] = LaneRole.JUNGLE
                            } else if (lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                                allySlotRoles[slotIdx] = LaneRole.ADC
                            } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup")) {
                                allySlotRoles[slotIdx] = LaneRole.SUPPORT
                            }

                            // Detección de Campeón Aliado
                            if (allySlots[slotIdx] == null) {
                                val matchedDirect = matchChampions(lineText, allChamps)
                                if (matchedDirect.isNotEmpty()) {
                                    allySlots[slotIdx] = matchedDirect.first()
                                    AppLogger.d(TAG, "Aliado detectado en slot $slotIdx: ${matchedDirect.first().name}")
                                } else {
                                    val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                                    for (w in words) {
                                        if (!ignoredWords.contains(w.lowercase(Locale.ROOT))) {
                                            val matchedWord = matchChampions(w, allChamps)
                                            if (matchedWord.isNotEmpty() && allySlots[slotIdx] == null) {
                                                allySlots[slotIdx] = matchedWord.first()
                                                AppLogger.d(TAG, "Aliado detectado por palabra en slot $slotIdx: ${matchedWord.first().name}")
                                                break
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // --- COLUMNA DERECHA: EQUIPO ENEMIGO ---
                    else if (centerX > screenWidth * 0.68f) {
                        val slotIdx = enemySlotYCenters.indices.minByOrNull {
                            val slotY = (screenHeight * enemySlotYCenters[it]).toInt()
                            abs(centerY - slotY)
                        } ?: -1

                        if (slotIdx in 0 until 5) {
                            val lower = lineText.lowercase(Locale.ROOT)
                            if (lower.contains("central") || lower.contains("mid") || lower.contains("medio")) {
                                enemySlotRoles[slotIdx] = LaneRole.MID
                            } else if (lower.contains("baron") || lower.contains("barón") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                                enemySlotRoles[slotIdx] = LaneRole.TOP
                            } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg")) {
                                enemySlotRoles[slotIdx] = LaneRole.JUNGLE
                            } else if (lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                                enemySlotRoles[slotIdx] = LaneRole.ADC
                            } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup")) {
                                enemySlotRoles[slotIdx] = LaneRole.SUPPORT
                            }

                            // Detección de Campeón Enemigo
                            if (enemySlots[slotIdx] == null) {
                                val matchedDirect = matchChampions(lineText, allChamps)
                                if (matchedDirect.isNotEmpty()) {
                                    enemySlots[slotIdx] = matchedDirect.first()
                                    AppLogger.d(TAG, "Enemigo detectado en slot $slotIdx: ${matchedDirect.first().name}")
                                } else {
                                    val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                                    for (w in words) {
                                        if (!ignoredWords.contains(w.lowercase(Locale.ROOT))) {
                                            val matchedWord = matchChampions(w, allChamps)
                                            if (matchedWord.isNotEmpty() && enemySlots[slotIdx] == null) {
                                                enemySlots[slotIdx] = matchedWord.first()
                                                AppLogger.d(TAG, "Enemigo detectado por palabra en slot $slotIdx: ${matchedWord.first().name}")
                                                break
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 2. Detección Multi-Señal de la ranura del jugador local ("Tú / Yo voy")
            val slotScores = IntArray(5)

            // Señal A: Ornamento de Dragón Alado Dorado / Gema Rubí o Borde Cian en el lateral izquierdo
            val sampleXMin = (screenWidth * 0.038f).toInt().coerceAtLeast(0)
            val sampleXMax = (screenWidth * 0.088f).toInt().coerceAtMost(screenWidth - 1)

            for (i in 0 until 5) {
                val yCenter = (screenHeight * allySlotYCenters[i]).toInt()
                val yMin = (yCenter - screenHeight * 0.045f).toInt().coerceAtLeast(0)
                val yMax = (yCenter + screenHeight * 0.045f).toInt().coerceAtMost(screenHeight - 1)

                var goldCount = 0
                var rubyCount = 0
                var cyanCount = 0

                for (y in yMin..yMax step 2) {
                    for (x in sampleXMin..sampleXMax step 2) {
                        val p = processBitmap.getPixel(x, y)
                        val r = (p shr 16) and 0xFF
                        val g = (p shr 8) and 0xFF
                        val b = p and 0xFF

                        // Dorado / Ámbar (alas del marco de jugador activo en Wild Rift)
                        if (r > 140 && g > 95 && b < 85 && r > b + 45) {
                            goldCount++
                        }
                        // Gema Roja / Rubí (núcleo del blasón del jugador)
                        else if (r > 150 && g < 75 && b < 75) {
                            rubyCount++
                        }
                        // Resaltado cian alternativo
                        else if (b > 115 && g > 85 && b > r + 25) {
                            cyanCount++
                        }
                    }
                }
                slotScores[i] += (goldCount * 3 + rubyCount * 3 + cyanCount * 2)
            }

            // Señal B: Coincidencia por nombre de invocador
            val normPreferred = preferredSummonerName?.let { normalizeString(it) }
            if (!normPreferred.isNullOrBlank() && normPreferred.length >= 3) {
                for (i in 0 until 5) {
                    val hasName = allySlotTexts[i].any { txt ->
                        val normTxt = normalizeString(txt)
                        normTxt.contains(normPreferred) || normPreferred.contains(normTxt)
                    }
                    if (hasName) {
                        slotScores[i] += 500
                        AppLogger.d(TAG, "Bonus de nombre de invocador aplicado a ranura $i")
                    }
                }
            }

            var userSlotIndex: Int? = null
            val maxScoreSlot = slotScores.indices.maxByOrNull { slotScores[it] } ?: -1
            if (maxScoreSlot != -1 && slotScores[maxScoreSlot] >= 15) {
                userSlotIndex = maxScoreSlot
                AppLogger.d(TAG, "Jugador local identificado en ranura $userSlotIndex con score: ${slotScores[userSlotIndex]}")
            }

            // 3. Determinar el rol/línea del jugador local
            val detectedRole: LaneRole? = if (userSlotIndex != null) {
                allySlotRoles[userSlotIndex]
                    ?: allySlots[userSlotIndex]?.primaryRole
                    ?: defaultAllyRoles.getOrNull(userSlotIndex)
            } else {
                null
            }

            // 4. Estructurar la asignación exacta por rol (alliesByRole y enemiesByRole)
            val alliesByRole = mutableMapOf<LaneRole, Champion>()
            val enemiesByRole = mutableMapOf<LaneRole, Champion>()

            // Asignar aliados detectados a sus roles
            for (i in 0 until 5) {
                val champ = allySlots[i] ?: continue
                val role = allySlotRoles[i]
                    ?: (if (!alliesByRole.containsKey(champ.primaryRole)) champ.primaryRole
                        else champ.secondaryRoles.firstOrNull { !alliesByRole.containsKey(it) }
                        ?: defaultAllyRoles.getOrNull(i)
                        ?: champ.primaryRole)
                alliesByRole[role] = champ
            }

            // Asignar enemigos detectados a sus roles
            for (i in 0 until 5) {
                val champ = enemySlots[i] ?: continue
                val role = enemySlotRoles[i]
                    ?: (if (!enemiesByRole.containsKey(champ.primaryRole)) champ.primaryRole
                        else champ.secondaryRoles.firstOrNull { !enemiesByRole.containsKey(it) }
                        ?: defaultEnemyRoles.getOrNull(i)
                        ?: champ.primaryRole)
                enemiesByRole[role] = champ
            }

            val standardOrder = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
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

        // 1. Coincidencia mediante tabla de alias
        for ((alias, aliasId) in aliasMap) {
            val aliasNorm = normalizeString(alias)
            if (normalized == aliasNorm || (aliasNorm.length >= 3 && normalized == aliasNorm)) {
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

        // 2. Coincidencia directa o por subcadena exacta
        for (champ in allChamps) {
            val champNorm = normalizeString(champ.name)
            val idNorm = normalizeString(champ.id)
            
            if (normalized == champNorm || normalized == idNorm) {
                if (!found.contains(champ)) found.add(champ)
            } else if (champNorm.length >= 3 && (normalized == champNorm || (normalized.length >= 4 && champNorm == normalized))) {
                if (!found.contains(champ)) found.add(champ)
            } else if (idNorm.length >= 3 && (normalized == idNorm || (normalized.length >= 4 && idNorm == normalized))) {
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
