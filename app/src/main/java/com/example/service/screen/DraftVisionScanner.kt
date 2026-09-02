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

    // Mapa de alias comunes para campeones de Wild Rift
    private val aliasMap = mapOf(
        "tf" to "twistedfate",
        "twisted" to "twistedfate",
        "mf" to "missfortune",
        "fortune" to "missfortune",
        "mundo" to "drmundo",
        "dr mundo" to "drmundo",
        "dr. mundo" to "drmundo",
        "yi" to "masteryi",
        "master" to "masteryi",
        "aurelion" to "aurelionsol",
        "sol" to "aurelionsol",
        "asol" to "aurelionsol",
        "jarvan" to "jarvaniv",
        "jarvan 4" to "jarvaniv",
        "j4" to "jarvaniv",
        "nunu" to "nunu",
        "willump" to "nunu",
        "xin" to "xinzhao",
        "zhao" to "xinzhao",
        "lee" to "leesin",
        "sin" to "leesin",
        "tahm" to "tahmkench",
        "kench" to "tahmkench",
        "tk" to "tahmkench",
        "renata" to "renataglasc",
        "glasc" to "renataglasc",
        "wukong" to "wukong",
        "monkey" to "wukong",
        "cait" to "caitlyn",
        "ez" to "ezreal",
        "eve" to "evelynn",
        "kass" to "kassadin",
        "kata" to "katarina",
        "kz" to "khazix",
        "k6" to "khazix",
        "kha" to "khazix",
        "renek" to "renekton",
        "vlad" to "vladimir",
        "voli" to "volibear",
        "yas" to "yasuo",
        "luc" to "lucian",
        "tris" to "tristana",
        "naut" to "nautilus",
        "ww" to "warwick"
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
            val inputImage = InputImage.fromBitmap(bitmap, 0)
            val visionText = recognizer.process(inputImage).await()

            val detectedWords = mutableListOf<String>()
            val foundAllies = mutableListOf<Champion>()
            val foundEnemies = mutableListOf<Champion>()

            val screenWidth = bitmap.width
            val screenHeight = bitmap.height

            val allChamps = WildRiftRepository.champions

            var detectedRole: com.example.model.LaneRole? = null

            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    if (lineText.isNotBlank()) {
                        detectedWords.add(lineText)
                    }
                    val lower = lineText.lowercase(Locale.ROOT)

                    // Detección de rol por palabras clave en pantalla (incluye nombres en español de Wild Rift)
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

                    // Intentar coincidir línea completa o elementos individuales
                    val candidateChamps = mutableListOf<Champion>()
                    candidateChamps.addAll(matchChampions(lineText, allChamps))

                    // También probar cada elemento/palabra de la línea por si el OCR agrupó varios textos
                    val elements = line.elements
                    for (i in elements.indices) {
                        val elemText = elements[i].text.trim()
                        candidateChamps.addAll(matchChampions(elemText, allChamps))
                        
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

                        // Ignorar la fila superior de BANS (Y < 12%) para no registrar los baneos como picks jugables
                        if (centerY < screenHeight * 0.12f) {
                            continue
                        }

                        // Ignorar el fondo extremo de la pantalla (Chat/Botonera Y > 90%)
                        if (centerY > screenHeight * 0.90f) {
                            continue
                        }

                        // En Wild Rift en orientación horizontal (Landscape):
                        // Columna Izquierda (X < 38%): Picks del equipo Aliado (Slots 1 al 5)
                        // Columna Derecha (X > 62%): Picks del equipo Enemigo (Slots 1 al 5)
                        // Centro: Hover / Campeón seleccionado actualmente
                        if (centerX < screenWidth * 0.38f) {
                            if (foundAllies.none { it.id == matchedChamp.id } && foundAllies.size < 5) {
                                foundAllies.add(matchedChamp)
                                AppLogger.d(TAG, "Aliado detectado (Izquierda): ${matchedChamp.name} en ($centerX, $centerY)")
                            }
                        } else if (centerX > screenWidth * 0.62f) {
                            if (foundEnemies.none { it.id == matchedChamp.id } && foundEnemies.size < 5) {
                                foundEnemies.add(matchedChamp)
                                AppLogger.d(TAG, "Enemigo detectado (Derecha): ${matchedChamp.name} en ($centerX, $centerY)")
                            }
                        } else {
                            // Campeón en el centro (Hover / Selección activa)
                            if (centerY in (screenHeight * 0.15f).toInt()..(screenHeight * 0.65f).toInt()) {
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
            if (normalized == aliasNorm || (aliasNorm.length >= 3 && normalized.contains(aliasNorm))) {
                val champ = allChamps.firstOrNull { it.id == aliasId }
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
            } else if (champNorm.length >= 3 && (normalized.contains(champNorm) || (normalized.length >= 4 && champNorm.contains(normalized)))) {
                if (!found.contains(champ)) found.add(champ)
            } else if (idNorm.length >= 3 && (normalized.contains(idNorm) || (normalized.length >= 4 && idNorm.contains(normalized)))) {
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
