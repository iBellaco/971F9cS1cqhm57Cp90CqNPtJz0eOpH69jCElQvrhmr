package com.example.service.screen

import android.graphics.Bitmap
import android.graphics.Rect
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.util.AppLogger
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await
import java.util.Locale

data class DetectedChampionSlot(
    val champion: Champion,
    val isAlly: Boolean,
    val boundingBox: Rect?,
    val confidence: Float = 0.95f
)

data class DraftScanResult(
    val allies: List<Champion>,
    val enemies: List<Champion>,
    val detectedRole: com.example.model.LaneRole? = null,
    val detectedRawWords: List<String>,
    val isSuccessful: Boolean,
    val statusMessage: String
)

/**
 * Motor de Visión Computacional y Reconocimiento Óptico (OCR) para la pantalla de selección de campeón en Wild Rift.
 * Detecta campeones aliados (columna izquierda) y enemigos (columna derecha) a partir del frame de captura de pantalla.
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
        "rek'sai" to "rek_sai"
    )

    private val ignoredWords = setOf(
        "fase", "seleccion", "selección", "elegir", "confirmar", "bloquear", "bloqueo", "bloqueos",
        "ban", "bans", "maestria", "maestría", "nivel", "level", "jugador", "player", "miembro", "member",
        "wild", "rift", "ranked", "clasificatoria", "normal", "aram", "pvp", "victoria", "derrota",
        "equipo", "team", "azul", "rojo", "blue", "red", "chat", "mute", "op", "fps", "ms", "ping"
    )

    /**
     * Escanea el Bitmap de la pantalla capturada e identifica los campeones en selección.
     */
    suspend fun scanDraftFromBitmap(bitmap: Bitmap): DraftScanResult {
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
            val foundAllies = mutableListOf<Champion>()
            val foundEnemies = mutableListOf<Champion>()

            val screenWidth = processBitmap.width
            val screenHeight = processBitmap.height

            val allChamps = WildRiftRepository.champions

            var detectedRole: com.example.model.LaneRole? = null

            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    if (lineText.isNotBlank()) {
                        detectedWords.add(lineText)
                    }
                    val lower = lineText.lowercase(Locale.ROOT)

                    // Detección de rol por palabras clave en pantalla (incluye nombres oficiales de Wild Rift en español e inglés)
                    if (detectedRole == null) {
                        if (lower.contains("baron") || lower.contains("barón") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                            detectedRole = com.example.model.LaneRole.TOP
                        } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg")) {
                            detectedRole = com.example.model.LaneRole.JUNGLE
                        } else if (lower.contains("mid") || lower.contains("central") || lower.contains("medio")) {
                            detectedRole = com.example.model.LaneRole.MID
                        } else if (lower.contains("duo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                            detectedRole = com.example.model.LaneRole.ADC
                        } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup")) {
                            detectedRole = com.example.model.LaneRole.SUPPORT
                        }
                    }

                    // Extraer candidatos a partir de la línea completa, tokens individuales y pares de palabras
                    val candidateChamps = mutableListOf<Champion>()
                    candidateChamps.addAll(matchChampions(lineText, allChamps))

                    val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                    for (w in words) {
                        if (!ignoredWords.contains(w.lowercase(Locale.ROOT))) {
                            candidateChamps.addAll(matchChampions(w, allChamps))
                        }
                    }
                    for (i in 0 until words.size - 1) {
                        candidateChamps.addAll(matchChampions("${words[i]} ${words[i + 1]}", allChamps))
                    }

                    val elements = line.elements
                    for (i in elements.indices) {
                        val elemText = elements[i].text.trim()
                        if (!ignoredWords.contains(elemText.lowercase(Locale.ROOT))) {
                            candidateChamps.addAll(matchChampions(elemText, allChamps))
                        }
                        if (i + 1 < elements.size) {
                            val twoWords = "$elemText ${elements[i + 1].text.trim()}"
                            candidateChamps.addAll(matchChampions(twoWords, allChamps))
                        }
                    }

                    val distinctCandidates = candidateChamps.distinctBy { it.id }

                    for (matchedChamp in distinctCandidates) {
                        val box = line.boundingBox
                        val centerX = box?.centerX() ?: 0
                        val centerY = box?.centerY() ?: 0

                        // Ignorar la fila superior de BANS (Y < 8%) para no registrar los baneos como picks jugables
                        if (centerY < screenHeight * 0.08f) {
                            continue
                        }

                        // Ignorar el fondo extremo de la pantalla (Chat/Botonera Y > 93%)
                        if (centerY > screenHeight * 0.93f) {
                            continue
                        }

                        // En Wild Rift en orientación horizontal (Landscape):
                        // Columna Izquierda (X < 45%): Picks del equipo Aliado (Slots 1 al 5)
                        // Columna Derecha (X > 55%): Picks del equipo Enemigo (Slots 1 al 5)
                        // Centro: Hover / Campeón seleccionado actualmente
                        if (centerX < screenWidth * 0.45f) {
                            if (foundAllies.none { it.id == matchedChamp.id } && foundAllies.size < 5) {
                                foundAllies.add(matchedChamp)
                                AppLogger.d(TAG, "Aliado detectado (Izquierda): ${matchedChamp.name} en ($centerX, $centerY)")
                            }
                        } else if (centerX > screenWidth * 0.55f) {
                            if (foundEnemies.none { it.id == matchedChamp.id } && foundEnemies.size < 5) {
                                foundEnemies.add(matchedChamp)
                                AppLogger.d(TAG, "Enemigo detectado (Derecha): ${matchedChamp.name} en ($centerX, $centerY)")
                            }
                        } else {
                            // Campeón en el centro (Hover / Selección activa)
                            if (centerY in (screenHeight * 0.12f).toInt()..(screenHeight * 0.78f).toInt()) {
                                if (foundAllies.size <= foundEnemies.size) {
                                    if (foundAllies.none { it.id == matchedChamp.id } && foundAllies.size < 5) {
                                        foundAllies.add(matchedChamp)
                                        AppLogger.d(TAG, "Aliado detectado (Centro-Hover): ${matchedChamp.name}")
                                    }
                                } else {
                                    if (foundEnemies.none { it.id == matchedChamp.id } && foundEnemies.size < 5) {
                                        foundEnemies.add(matchedChamp)
                                        AppLogger.d(TAG, "Enemigo detectado (Centro-Hover): ${matchedChamp.name}")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            val totalDetected = foundAllies.size + foundEnemies.size
            val status = if (totalDetected > 0) {
                "Escaneo exitoso: $totalDetected campeones identificados en pantalla"
            } else {
                "No se detectaron nombres de campeones en el frame actual."
            }

            AppLogger.d(TAG, "Resultado de escaneo: ${foundAllies.map { it.name }} vs ${foundEnemies.map { it.name }} (Rol: $detectedRole)")

            DraftScanResult(
                allies = foundAllies,
                enemies = foundEnemies,
                detectedRole = detectedRole,
                detectedRawWords = detectedWords,
                isSuccessful = totalDetected > 0,
                statusMessage = status
            )
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error durante el análisis visual del draft", e)
            DraftScanResult(
                allies = emptyList(),
                enemies = emptyList(),
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
                // Fuzzy matching por distancia de Levenshtein (tolerar pequeños errores de OCR como 5->S, 1->I, V->Y)
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
