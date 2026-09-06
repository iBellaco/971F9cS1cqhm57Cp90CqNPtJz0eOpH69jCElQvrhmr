package com.example.data.sync

import android.content.Context
import com.example.data.WildRiftRepository
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

sealed class WrMetaScrapingState {
    object Idle : WrMetaScrapingState()
    data class ExtractingUrls(
        val category: String,
        val progressPercent: Float
    ) : WrMetaScrapingState()
    data class DownloadingImages(
        val downloadedCount: Int,
        val totalCount: Int,
        val currentChampion: String,
        val progressPercent: Float
    ) : WrMetaScrapingState()
    data class Success(
        val championsCount: Int,
        val downloadedImagesCount: Int,
        val timestamp: String,
        val outputDirectory: String,
        val urlsFilePath: String
    ) : WrMetaScrapingState()
    data class Error(val message: String) : WrMetaScrapingState()
}

data class ChampionAvatarRecord(
    val name: String,
    val normalizedName: String,
    val avatarUrl: String,
    val localFileName: String,
    val isDownloaded: Boolean
)

/**
 * Descargador y Scraper de Avatares y URLs de Campeones para Wild Rift (wr-meta.com).
 * Extrae las URLs oficiales de cada campeón y descarga físicamente los archivos de imagen
 * en la carpeta WR_META_141/imagenes/ y genera urls_imagenes.txt, campeones.json y campeones.csv.
 */
object WrMetaScraper {
    private const val TAG = "WrMetaScraper"
    const val BASE_URL = "https://wr-meta.com"
    const val EXPECTED_CHAMPIONS = 141

    val CATEGORIES = linkedMapOf(
        "Asesinos" to "https://wr-meta.com/assassins/",
        "Luchadores" to "https://wr-meta.com/fighters/",
        "Magos" to "https://wr-meta.com/mages/",
        "Tiradores" to "https://wr-meta.com/marksmans/",
        "Soportes" to "https://wr-meta.com/supports/",
        "Tanques" to "https://wr-meta.com/tanks/"
    )

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .build()
    }

    private val _scrapingState = MutableStateFlow<WrMetaScrapingState>(WrMetaScrapingState.Idle)
    val scrapingState: StateFlow<WrMetaScrapingState> = _scrapingState.asStateFlow()

    private const val PREFS_NAME = "wr_meta_avatar_scraper_prefs"
    private const val KEY_LAST_SCRAPE_TIME = "last_avatar_scrape_time"
    private const val KEY_LAST_CHAMPS_COUNT = "last_avatar_champs_count"

    fun getLastScrapeInfo(context: Context): Pair<Int, String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val count = prefs.getInt(KEY_LAST_CHAMPS_COUNT, 0)
        val time = prefs.getString(KEY_LAST_SCRAPE_TIME, "Nunca descargado") ?: "Nunca descargado"
        return Pair(count, time)
    }

    fun cleanName(name: String?): String {
        if (name.isNullOrBlank()) return ""
        var cleaned = name.trim()
        val replacements = mapOf(
            "&" to "and",
            "/" to "-",
            "\\" to "-",
            ":" to "-",
            "*" to "",
            "?" to "",
            "\"" to "",
            "<" to "",
            ">" to "",
            "|" to "-"
        )
        for ((old, new) in replacements) {
            cleaned = cleaned.replace(old, new)
        }
        return cleaned.replace(Regex("\\s+"), " ").trim()
    }

    fun normalizeName(name: String): String {
        return name.uppercase(Locale.ROOT)
            .replace("&", "AND")
            .replace(Regex("\\s+"), " ")
            .trim()
    }

    fun sanitizeFileName(name: String): String {
        return name.replace(Regex("[^a-zA-Z0-9._-]"), "_").lowercase(Locale.ROOT)
    }

    /**
     * Extrae las URLs y descarga los avatares directamente a disco.
     */
    suspend fun runScraping(context: Context): Boolean = withContext(Dispatchers.IO) {
        val championsMap = mutableMapOf<String, Pair<String, String>>() // NormalizedName -> Pair(DisplayName, ImageUrl)
        val userAgent = "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/139.0.0.0 Mobile Safari/537.36"

        _scrapingState.value = WrMetaScrapingState.ExtractingUrls(
            category = "Conectando a wr-meta.com...",
            progressPercent = 0.05f
        )

        try {
            var catIdx = 0
            for ((categoryName, categoryUrl) in CATEGORIES) {
                catIdx++
                val p = (catIdx.toFloat() / CATEGORIES.size.toFloat()) * 0.35f
                _scrapingState.value = WrMetaScrapingState.ExtractingUrls(
                    category = "$categoryName ($catIdx/${CATEGORIES.size})",
                    progressPercent = p
                )

                try {
                    val request = Request.Builder()
                        .url(categoryUrl)
                        .header("User-Agent", userAgent)
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                        .header("Accept-Language", "es-ES,es;q=0.9,en;q=0.8")
                        .header("Referer", "$BASE_URL/")
                        .build()

                    val response = httpClient.newCall(request).execute()
                    val html = response.body?.string().orEmpty()

                    if (response.isSuccessful && html.isNotBlank()) {
                        val doc: Document = Jsoup.parse(html, BASE_URL)
                        val championElements = doc.select(".champ-item, .tier-list-item, tr:has(td), .item, .champion-card, a[href*='/champion/']")

                        for (elem in championElements) {
                            val rawName = elem.select(".name, .champ-name, .title, strong, a").text().trim()
                            val clean = cleanName(rawName)
                            if (clean.length in 2..25 && !clean.contains("Tier", ignoreCase = true) && !clean.contains("Wild Rift", ignoreCase = true)) {
                                val norm = normalizeName(clean)
                                var imgUrl = elem.select("img").attr("src")
                                if (imgUrl.isNotBlank()) {
                                    if (!imgUrl.startsWith("http")) {
                                        imgUrl = if (imgUrl.startsWith("/")) "$BASE_URL$imgUrl" else "$BASE_URL/$imgUrl"
                                    }
                                    if (!championsMap.containsKey(norm)) {
                                        championsMap[norm] = Pair(clean, imgUrl)
                                    }
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    AppLogger.w(TAG, "Aviso leyendo $categoryName: ${e.message}")
                }
            }

            // Asegurar que los 141 campeones canónicos estén presentes con sus URLs oficiales
            val canonicalList = WildRiftRepository.champions
            for (champ in canonicalList) {
                val norm = normalizeName(champ.name)
                if (!championsMap.containsKey(norm) || championsMap[norm]?.second.isNullOrBlank()) {
                    championsMap[norm] = Pair(champ.name, champ.avatarUrl)
                }
            }

            // Preparar carpetas de salida: WR_META_141 e imagenes/
            val outputDir = File(context.filesDir, "WR_META_141")
            val imagesDir = File(outputDir, "imagenes")
            if (!imagesDir.exists()) imagesDir.mkdirs()

            val totalChamps = championsMap.size
            var downloadedCount = 0
            val avatarRecords = mutableListOf<ChampionAvatarRecord>()

            // Descarga concurrente controlada (Semaphore de 6 conexiones simultáneas)
            val semaphore = Semaphore(6)
            
            coroutineScope {
                val downloadJobs = championsMap.entries.mapIndexed { index, entry ->
                    async(Dispatchers.IO) {
                        val (normName, data) = entry
                        val (displayName, imgUrl) = data
                        val ext = when {
                            imgUrl.contains(".webp", ignoreCase = true) -> "webp"
                            imgUrl.contains(".png", ignoreCase = true) -> "png"
                            imgUrl.contains(".jpeg", ignoreCase = true) -> "jpeg"
                            else -> "jpg"
                        }
                        val fileName = "${sanitizeFileName(displayName)}.$ext"
                        val targetFile = File(imagesDir, fileName)

                        var isSuccess = targetFile.exists() && targetFile.length() > 500

                        if (!isSuccess && imgUrl.startsWith("http")) {
                            semaphore.withPermit {
                                try {
                                    val imgRequest = Request.Builder()
                                        .url(imgUrl)
                                        .header("User-Agent", userAgent)
                                        .header("Referer", "$BASE_URL/")
                                        .build()

                                    val imgResponse = httpClient.newCall(imgRequest).execute()
                                    if (imgResponse.isSuccessful) {
                                        imgResponse.body?.byteStream()?.use { input ->
                                            FileOutputStream(targetFile).use { output ->
                                                input.copyTo(output)
                                            }
                                        }
                                        isSuccess = targetFile.exists() && targetFile.length() > 500
                                    }
                                } catch (err: Exception) {
                                    AppLogger.w(TAG, "Error al descargar avatar de $displayName ($imgUrl): ${err.message}")
                                }
                            }
                        }

                        synchronized(avatarRecords) {
                            downloadedCount++
                            val progress = 0.35f + ((downloadedCount.toFloat() / totalChamps.toFloat()) * 0.65f)
                            _scrapingState.value = WrMetaScrapingState.DownloadingImages(
                                downloadedCount = downloadedCount,
                                totalCount = totalChamps,
                                currentChampion = displayName,
                                progressPercent = progress
                            )
                            avatarRecords.add(
                                ChampionAvatarRecord(
                                    name = displayName,
                                    normalizedName = normName,
                                    avatarUrl = imgUrl,
                                    localFileName = fileName,
                                    isDownloaded = isSuccess
                                )
                            )
                        }
                    }
                }
                downloadJobs.awaitAll()
            }

            // 1. Guardar urls_imagenes.txt
            val urlsTxtFile = File(outputDir, "urls_imagenes.txt")
            val urlsWriter = FileWriter(urlsTxtFile)
            avatarRecords.sortedBy { it.name }.forEach { record ->
                urlsWriter.append("${record.name} | ${record.avatarUrl}\n")
            }
            urlsWriter.flush()
            urlsWriter.close()

            // 2. Guardar campeones.json
            val jsonFile = File(outputDir, "campeones.json")
            val jsonArray = org.json.JSONArray()
            avatarRecords.sortedBy { it.name }.forEach { record ->
                val obj = org.json.JSONObject()
                obj.put("name", record.name)
                obj.put("avatar_url", record.avatarUrl)
                obj.put("image_file", record.localFileName)
                obj.put("downloaded", record.isDownloaded)
                jsonArray.put(obj)
            }
            jsonFile.writeText(jsonArray.toString(2))

            // 3. Guardar campeones.csv
            val csvFile = File(outputDir, "campeones.csv")
            val csvWriter = FileWriter(csvFile)
            csvWriter.append("name,avatar_url,local_file,downloaded\n")
            avatarRecords.sortedBy { it.name }.forEach { record ->
                csvWriter.append("\"${record.name}\",\"${record.avatarUrl}\",\"${record.localFileName}\",${record.isDownloaded}\n")
            }
            csvWriter.flush()
            csvWriter.close()

            val nowTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_LAST_SCRAPE_TIME, nowTime)
                .putInt(KEY_LAST_CHAMPS_COUNT, avatarRecords.size)
                .apply()

            _scrapingState.value = WrMetaScrapingState.Success(
                championsCount = avatarRecords.size,
                downloadedImagesCount = avatarRecords.count { it.isDownloaded },
                timestamp = nowTime,
                outputDirectory = outputDir.absolutePath,
                urlsFilePath = urlsTxtFile.absolutePath
            )

            AppLogger.d(TAG, "Descarga completada: ${avatarRecords.size} avatares y urls_imagenes.txt generados en $outputDir")
            true
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error fatal en descarga de avatares: ${e.message}", e)
            _scrapingState.value = WrMetaScrapingState.Error("Fallo al descargar avatares: ${e.localizedMessage ?: "Error de red"}")
            false
        }
    }
}
