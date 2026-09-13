package com.example.service.gemini

import android.graphics.Bitmap
import android.util.Base64
import com.example.BuildConfig
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

// --- Common Data Classes ---
@Serializable
data class GenerateContentRequest(
    val contents: List<Content>,
    val generationConfig: GenerationConfig? = null,
    val systemInstruction: Content? = null
)

@Serializable
data class Content(
    val parts: List<Part>
)

@Serializable
data class Part(
    val text: String? = null,
    val inlineData: InlineData? = null
)

@Serializable
data class InlineData(
    val mimeType: String,
    val data: String
)

@Serializable
data class GenerationConfig(
    val temperature: Float? = null,
    val topP: Float? = null,
    val topK: Int? = null,
    val responseMimeType: String? = null
)

@Serializable
data class GenerateContentResponse(
    val candidates: List<Candidate>? = null
)

@Serializable
data class Candidate(
    val content: Content? = null
)

@Serializable
data class PreparationScanResponseDto(
    val allies: List<String> = emptyList(),
    val enemies: List<String> = emptyList(),
    val userChampion: String? = null
)

data class PreparationScanResult(
    val allies: List<String> = emptyList(),
    val enemies: List<String> = emptyList(),
    val userChampion: String? = null
)

interface GeminiApiService {
    @POST("v1beta/models/gemini-2.5-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body request: GenerateContentRequest
    ): GenerateContentResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    val service: GeminiApiService by lazy {
        val json = Json { ignoreUnknownKeys = true; isLenient = true }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
        retrofit.create(GeminiApiService::class.java)
    }
}

fun Bitmap.toBase64(): String {
    val outputStream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
    return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP)
}

object GeminiVisionService {
    private const val TAG = "GeminiVisionService"

    suspend fun identify10thPickFromPreparationScreen(bitmap: Bitmap, isEnemySide: Boolean): String? = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isBlank() || apiKey == "dummy_key_for_now") {
            AppLogger.w(TAG, "Gemini API Key no configurada.")
            return@withContext null
        }

        val sideStr = if (isEnemySide) "rightmost" else "rightmost"
        val prompt = "This is a crop of the top bar from the League of Legends Wild Rift 'Preparation Phase' screen. There is a horizontal row of small circular champion avatars. Please identify the 5th champion in the row (the $sideStr one in the group of 5). Respond ONLY with the exact English name of that champion, nothing else. If you cannot identify it, respond with 'UNKNOWN'."

        val requestBody = GenerateContentRequest(
            contents = listOf(
                Content(
                    parts = listOf(
                        Part(text = prompt),
                        Part(inlineData = InlineData(mimeType = "image/jpeg", data = bitmap.toBase64()))
                    )
                )
            ),
            generationConfig = GenerationConfig(
                temperature = 0.0f
            )
        )

        try {
            val response = RetrofitClient.service.generateContent(apiKey, requestBody)
            val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text?.trim()
            if (text == "UNKNOWN" || text.isNullOrEmpty()) {
                null
            } else {
                text
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error en Gemini API: ${e.message}")
            null
        }
    }

    suspend fun scanFullPreparationScreen(bitmap: Bitmap): PreparationScanResult? = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isBlank() || apiKey == "dummy_key_for_now") {
            AppLogger.w(TAG, "Gemini API Key no configurada.")
            return@withContext null
        }

        val scaledBitmap = if (bitmap.width > 1280 || bitmap.height > 1280) {
            val scale = 1280f / maxOf(bitmap.width, bitmap.height)
            Bitmap.createScaledBitmap(bitmap, (bitmap.width * scale).toInt(), (bitmap.height * scale).toInt(), true)
        } else {
            bitmap
        }

        val prompt = """
            Analyze this League of Legends: Wild Rift 'FASE DE PREPARACIÓN' (Preparation Phase / Skin selection) screen:
            1. Top-Left: Group of 5 small circular champion avatars (Ally team). List all 5 champions in left-to-right order.
            2. Top-Right: Group of 5 small circular champion avatars (Enemy team). List all 5 champions in left-to-right order.
            3. Bottom center: The large skin/champion card currently selected in the skin carousel (the champion the player is playing).
            Respond ONLY with a valid JSON object matching this schema:
            {
              "allies": ["Champion1", "Champion2", "Champion3", "Champion4", "Champion5"],
              "enemies": ["Champion1", "Champion2", "Champion3", "Champion4", "Champion5"],
              "userChampion": "ChampionName"
            }
            Use standard official Wild Rift English champion names (e.g., "Sett", "Viktor", "Vi", "Smolder", "Senna", "Jax", "Lux", "Ashe", "Malphite", "Volibear").
        """.trimIndent()

        val requestBody = GenerateContentRequest(
            contents = listOf(
                Content(
                    parts = listOf(
                        Part(text = prompt),
                        Part(inlineData = InlineData(mimeType = "image/jpeg", data = scaledBitmap.toBase64()))
                    )
                )
            ),
            generationConfig = GenerationConfig(
                temperature = 0.0f,
                responseMimeType = "application/json"
            )
        )

        try {
            val response = RetrofitClient.service.generateContent(apiKey, requestBody)
            val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text?.trim()
            if (!text.isNullOrBlank()) {
                val json = Json { ignoreUnknownKeys = true; isLenient = true }
                val parsed = json.decodeFromString<PreparationScanResponseDto>(text)
                PreparationScanResult(
                    allies = parsed.allies,
                    enemies = parsed.enemies,
                    userChampion = parsed.userChampion
                )
            } else null
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error en Gemini API scanFullPreparationScreen: ${e.message}")
            null
        } finally {
            if (scaledBitmap != bitmap) {
                try { scaledBitmap.recycle() } catch (_: Throwable) {}
            }
        }
    }
}
