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
    private val textRecognizer by lazy {
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
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
        "renata" to "renataglasc",
        "glasc" to "renataglasc",
        "wukong" to "wukong",
        "monkey" to "wukong"
    )

    /**
     * Escanea el Bitmap de la pantalla capturada e identifica los campeones en selección.
     */
    suspend fun scanDraftFromBitmap(bitmap: Bitmap): DraftScanResult {
        return try {
            val inputImage = InputImage.fromBitmap(bitmap, 0)
            val visionText = textRecognizer.process(inputImage).await()

            val detectedWords = mutableListOf<String>()
            val foundAllies = mutableListOf<Champion>()
            val foundEnemies = mutableListOf<Champion>()

            val screenWidth = bitmap.width
            val screenCenterX = screenWidth / 2

            val allChamps = WildRiftRepository.champions

            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    detectedWords.add(lineText)

                    // Intentar coincidir con el catálogo de campeones
                    val matchedChamp = matchChampion(lineText, allChamps)
                    if (matchedChamp != null) {
                        val box = line.boundingBox
                        val centerX = box?.centerX() ?: (if (foundAllies.size <= foundEnemies.size) 0 else screenWidth)

                        // En Wild Rift:
                        // La columna izquierda (X < 50% de la pantalla) corresponde a aliados (Equipo Azul)
                        // La columna derecha (X >= 50% de la pantalla) corresponde a enemigos (Equipo Rojo)
                        if (centerX < screenCenterX) {
                            if (foundAllies.none { it.id == matchedChamp.id } && foundAllies.size < 5) {
                                foundAllies.add(matchedChamp)
                                AppLogger.d(TAG, "Aliado detectado por Visión OCR: ${matchedChamp.name} en posición $centerX")
                            }
                        } else {
                            if (foundEnemies.none { it.id == matchedChamp.id } && foundEnemies.size < 5) {
                                foundEnemies.add(matchedChamp)
                                AppLogger.d(TAG, "Enemigo detectado por Visión OCR: ${matchedChamp.name} en posición $centerX")
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
                "No se detectaron nombres legibles en pantalla. Asegúrate de estar en Selección de Campeones."
            }

            AppLogger.d(TAG, "Resultado de escaneo: ${foundAllies.map { it.name }} vs ${foundEnemies.map { it.name }}")

            DraftScanResult(
                allies = foundAllies,
                enemies = foundEnemies,
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

    private fun matchChampion(text: String, allChamps: List<Champion>): Champion? {
        val normalized = normalizeString(text)
        if (normalized.length < 2) return null

        // 1. Coincidencia exacta con nombre o ID
        allChamps.firstOrNull {
            normalizeString(it.name) == normalized || normalizeString(it.id) == normalized
        }?.let { return it }

        // 2. Coincidencia mediante tabla de alias
        aliasMap[normalized]?.let { aliasId ->
            allChamps.firstOrNull { it.id == aliasId }?.let { return it }
        }

        // 3. Coincidencia por subcadena si la palabra es suficientemente larga (>= 4 letras)
        if (normalized.length >= 4) {
            allChamps.firstOrNull {
                val champNorm = normalizeString(it.name)
                champNorm.contains(normalized) || normalized.contains(champNorm)
            }?.let { return it }
        }

        return null
    }

    private fun normalizeString(input: String): String {
        return input.lowercase(Locale.ROOT)
            .replace("[^a-z0-9]".toRegex(), "")
    }
}
