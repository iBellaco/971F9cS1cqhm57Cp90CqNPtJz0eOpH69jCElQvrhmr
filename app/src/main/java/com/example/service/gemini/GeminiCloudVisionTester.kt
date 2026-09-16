package com.example.service.gemini

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import com.example.BuildConfig
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

data class CloudVisionTestResult(
    val isSuccess: Boolean,
    val championName: String? = null,
    val confidence: Int = 0,
    val latencyMs: Long = 0,
    val details: String = "",
    val errorMessage: String? = null,
    val rawResponse: String? = null
)

/**
 * Servicio de auditoría para pruebas y comparación de rendimiento y precisión
 * entre el Dataset de Archivos Locales vs Reconocimiento Visual por Internet (IA en la nube).
 * "Modo de prueba que no hace nada en la partida; solo evalúa qué es lo que captura".
 */
object GeminiCloudVisionTester {
    private const val TAG = "GeminiCloudVisionTester"
    private const val PREFS_NAME = "coach_cloud_vision_prefs"
    private const val KEY_CUSTOM_API_KEY = "custom_gemini_api_key"

    private val jsonParser = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    fun getEffectiveApiKey(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val customKey = prefs.getString(KEY_CUSTOM_API_KEY, "")?.trim() ?: ""
        if (customKey.isNotBlank()) return customKey
        return BuildConfig.GEMINI_API_KEY.trim()
    }

    fun setCustomApiKey(context: Context, key: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_CUSTOM_API_KEY, key.trim()).apply()
    }

    /**
     * Envía el recorte capturado a la API de Visión en la nube para identificar el campeón.
     * Mide con precisión milimétrica la latencia y la rentabilidad del procesamiento.
     */
    suspend fun testVisionWithInternet(
        context: Context,
        crop: Bitmap
    ): CloudVisionTestResult = withContext(Dispatchers.IO) {
        val startTime = System.currentTimeMillis()
        if (crop.isRecycled || crop.width < 10 || crop.height < 10) {
            return@withContext CloudVisionTestResult(
                isSuccess = false,
                errorMessage = "La captura está vacía o corrupta",
                latencyMs = System.currentTimeMillis() - startTime
            )
        }

        val apiKey = getEffectiveApiKey(context)
        if (apiKey.isBlank()) {
            return@withContext CloudVisionTestResult(
                isSuccess = false,
                errorMessage = "Clave de API no configurada. Ingresa tu clave en el campo de configuración para probar.",
                latencyMs = System.currentTimeMillis() - startTime
            )
        }

        try {
            // 1. Convertir Bitmap a Base64 JPEG comprimido (calidad 85% para rapidez de red)
            val outputStream = ByteArrayOutputStream()
            crop.compress(Bitmap.CompressFormat.JPEG, 85, outputStream)
            val imageBytes = outputStream.toByteArray()
            val base64Image = Base64.encodeToString(imageBytes, Base64.NO_WRAP)

            // 2. Modelo de visión oficial
            val model = "gemini-2.5-flash"
            val url = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent?key=$apiKey"

            val prompt = """
                Eres un auditor y árbitro de visión por computadora para el videojuego League of Legends: Wild Rift.
                Se te proporciona el recorte directo capturado de un avatar de campeón de la pantalla de draft o pantalla de carga.
                Identifica con exactitud qué campeón es.
                
                IMPORTANTE: Responde ÚNICAMENTE un objeto JSON válido sin bloques markdown, con esta estructura exacta:
                {"champion": "NombreDelCampeon", "confidence": 95, "details": "Rasgos visuales que lo identifican"}
            """.trimIndent()

            // 3. Construir el cuerpo de la petición REST
            val requestBodyJson = """
                {
                  "contents": [
                    {
                      "parts": [
                        { "text": ${Json.encodeToString(kotlinx.serialization.serializer(), prompt)} },
                        {
                          "inlineData": {
                            "mimeType": "image/jpeg",
                            "data": "$base64Image"
                          }
                        }
                      ]
                    }
                  ],
                  "generationConfig": {
                    "temperature": 0.1,
                    "maxOutputTokens": 300
                  }
                }
            """.trimIndent()

            val request = Request.Builder()
                .url(url)
                .post(requestBodyJson.toRequestBody("application/json".toMediaType()))
                .build()

            val response = httpClient.newCall(request).execute()
            val latency = System.currentTimeMillis() - startTime
            val responseBody = response.body?.string() ?: ""

            if (!response.isSuccessful) {
                val errorDesc = when (response.code) {
                    400 -> "Error 400: Petición o imagen inválida"
                    403 -> "Error 403: Clave de API no autorizada o cuota excedida"
                    429 -> "Error 429: Límite de tasa de peticiones alcanzado"
                    else -> "Error HTTP ${response.code}: $responseBody"
                }
                return@withContext CloudVisionTestResult(
                    isSuccess = false,
                    errorMessage = errorDesc,
                    latencyMs = latency,
                    rawResponse = responseBody
                )
            }

            // 4. Parsear respuesta
            val (champ, conf, det) = parseGeminiResponse(responseBody)
            if (champ != null) {
                CloudVisionTestResult(
                    isSuccess = true,
                    championName = champ,
                    confidence = conf,
                    latencyMs = latency,
                    details = det,
                    rawResponse = responseBody
                )
            } else {
                CloudVisionTestResult(
                    isSuccess = false,
                    errorMessage = "No se pudo extraer el nombre del campeón de la respuesta de la IA",
                    latencyMs = latency,
                    rawResponse = responseBody
                )
            }

        } catch (e: Exception) {
            AppLogger.e(TAG, "Fallo en consulta de visión con Internet", e)
            val latency = System.currentTimeMillis() - startTime
            CloudVisionTestResult(
                isSuccess = false,
                errorMessage = "Fallo de conexión o tiempo de espera: ${e.localizedMessage ?: e.message}",
                latencyMs = latency
            )
        }
    }

    private fun parseGeminiResponse(jsonString: String): Triple<String?, Int, String> {
        return try {
            val root = jsonParser.parseToJsonElement(jsonString).jsonObject
            val candidates = root["candidates"]
            val firstCandidate = candidates?.toString()?.let { jsonParser.parseToJsonElement(it) }
            // Extraer el texto de content.parts[0].text
            val textPart = root["candidates"]
                ?.toString()
                ?.let { jsonParser.parseToJsonElement(it) }
            
            // Búsqueda directa con regex en el texto devuelto
            var text = ""
            val textRegex = Regex("\"text\":\\s*\"(.*?)(?<!\\\\)\"", RegexOption.DOT_MATCHES_ALL)
            val match = textRegex.find(jsonString)
            if (match != null) {
                text = match.groupValues[1]
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"")
                    .replace("\\\\", "\\")
            }

            // Limpiar posibles bloques ```json ... ```
            val cleanedJson = text.replace("```json", "").replace("```", "").trim()
            
            // Intentar parsear el JSON interno
            val champRegex = Regex("\"champion\"\\s*:\\s*\"([^\"]+)\"")
            val confRegex = Regex("\"confidence\"\\s*:\\s*(\\d+)")
            val detRegex = Regex("\"details\"\\s*:\\s*\"([^\"]+)\"")

            val champMatch = champRegex.find(cleanedJson)
            val confMatch = confRegex.find(cleanedJson)
            val detMatch = detRegex.find(cleanedJson)

            val champ = champMatch?.groupValues?.get(1)?.trim()
            val conf = confMatch?.groupValues?.get(1)?.toIntOrNull() ?: 85
            val det = detMatch?.groupValues?.get(1)?.trim() ?: "Reconocido por IA"

            Triple(champ, conf, det)
        } catch (e: Exception) {
            AppLogger.w(TAG, "Error parseando respuesta JSON de Gemini: ${e.message}")
            Triple(null, 0, "")
        }
    }
}
