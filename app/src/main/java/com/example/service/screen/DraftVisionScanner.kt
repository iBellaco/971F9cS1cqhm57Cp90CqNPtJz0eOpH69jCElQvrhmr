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
            val screenCenterX = screenWidth / 2

            val allChamps = WildRiftRepository.champions

            var detectedRole: com.example.model.LaneRole? = null

            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    detectedWords.add(lineText)
                    val lower = lineText.lowercase(Locale.ROOT)

                    // Detección de rol por palabras clave en pantalla
                    if (detectedRole == null) {
                        if (lower.contains("baron") || lower.contains("solo") || lower.contains("top")) {
                            detectedRole = com.example.model.LaneRole.TOP
                        } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg")) {
                            detectedRole = com.example.model.LaneRole.JUNGLE
                        } else if (lower.contains("mid") || lower.contains("central") || lower.contains("medio")) {
                            detectedRole = com.example.model.LaneRole.MID
                        } else if (lower.contains("duo") || lower.contains("dragon") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                            detectedRole = com.example.model.LaneRole.ADC
                        } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("sup")) {
                            detectedRole = com.example.model.LaneRole.SUPPORT
                        }
                    }

                    // Intentar coincidir con el catálogo de campeones
                    val matchedChamps = matchChampions(lineText, allChamps)
                    for (matchedChamp in matchedChamps) {
                        val box = line.boundingBox
                        val centerX = box?.centerX() ?: 0
                        val centerY = box?.centerY() ?: 0
                        val screenHeight = bitmap.height

                        // Ignorar la mitad inferior de la pantalla (Grid de selección de campeones, Chat, etc.)
                        // para evitar que el OCR lea 20 campeones de golpe y llene los slots falsamente.
                        if (centerY > screenHeight * 0.65f) {
                            continue
                        }

                        // En Wild Rift:
                        // La columna izquierda (X < 35% de la pantalla) corresponde a aliados
                        // La columna derecha (X > 65% de la pantalla) corresponde a enemigos
                        // El centro es el hover, lo asignamos por defecto a los aliados si hay espacio
                        if (centerX < screenWidth * 0.35f) {
                            if (foundAllies.none { it.id == matchedChamp.id } && foundAllies.size < 5) {
                                foundAllies.add(matchedChamp)
                                AppLogger.d(TAG, "Aliado detectado (Izquierda): ${matchedChamp.name}")
                            }
                        } else if (centerX > screenWidth * 0.65f) {
                            if (foundEnemies.none { it.id == matchedChamp.id } && foundEnemies.size < 5) {
                                foundEnemies.add(matchedChamp)
                                AppLogger.d(TAG, "Enemigo detectado (Derecha): ${matchedChamp.name}")
                            }
                        } else {
                            // Campeón en el centro (Hover).
                            // Si detectamos un nombre en el centro en tamaño grande, suele ser el pick actual.
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

            // Si no se detectaron suficientes por texto (ej. durante la animación inicial o bloqueo de retratos),
            // el scanner retorna lo encontrado o un reporte claro
            val totalDetected = foundAllies.size + foundEnemies.size
            val status = if (totalDetected > 0) {
                "Escaneo exitoso: $totalDetected campeones identificados en pantalla"
            } else {
                "OCR no encontró nombres de campeones (Leyó: ${detectedWords.take(4).joinToString()}). ¡Recuerda que WR no muestra los nombres en los lados!"
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
            if (normalized.contains(aliasNorm)) {
                val champ = allChamps.firstOrNull { it.id == aliasId }
                if (champ != null && !found.contains(champ)) {
                    found.add(champ)
                }
            }
        }

        // 2. Coincidencia por subcadena o nombre exacto
        for (champ in allChamps) {
            val champNorm = normalizeString(champ.name)
            val idNorm = normalizeString(champ.id)
            if (champNorm.length >= 4 && (normalized.contains(champNorm) || champNorm.contains(normalized))) {
                if (!found.contains(champ)) {
                    found.add(champ)
                }
            } else if (normalized.contains(idNorm) && idNorm.length >= 4) {
                if (!found.contains(champ)) {
                    found.add(champ)
                }
            }
        }

        return found
    }

    private fun normalizeString(input: String): String {
        return input.lowercase(Locale.ROOT)
            .replace("[^a-z0-9]".toRegex(), "")
    }
}
