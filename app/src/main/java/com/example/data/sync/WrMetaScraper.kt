package com.example.data.sync

import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
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
import org.jsoup.nodes.Element
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
        val publicDirectoryPath: String,
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
 * Extrae URLs con formato https://wr-meta.com/uploads/posts/YYYY-MM/...webp
 * y descarga las imágenes físicas directamente en Download/WR_META_141/imagenes/
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
    private const val KEY_LAST_FOLDER_PATH = "last_avatar_folder_path"
    private const val KEY_LAST_URLS_PATH = "last_avatar_urls_path"

    fun getLastScrapeInfo(context: Context): Triple<Int, String, String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val count = prefs.getInt(KEY_LAST_CHAMPS_COUNT, 0)
        val time = prefs.getString(KEY_LAST_SCRAPE_TIME, "Nunca descargado") ?: "Nunca descargado"
        val folder = prefs.getString(KEY_LAST_FOLDER_PATH, "") ?: ""
        return Triple(count, time, folder)
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
     * Normaliza cualquier URL relativa a https://wr-meta.com/uploads/posts/...
     */
    fun normalizeImageUrl(url: String): String {
        var u = url.trim()
        if (u.isBlank()) return ""
        if (u.startsWith("//")) {
            return "https:$u"
        }
        if (u.startsWith("/")) {
            return "$BASE_URL$u"
        }
        if (!u.startsWith("http://") && !u.startsWith("https://")) {
            return "$BASE_URL/$u"
        }
        return u
    }

    /**
     * Extrae de forma inteligente la URL de imagen del elemento HTML en wr-meta.com
     * priorizando rutas /uploads/posts/
     */
    fun extractImageUrl(elem: Element): String {
        // 1. Buscar en atributos de imágenes
        val imgs = elem.select("img")
        for (img in imgs) {
            val candidates = listOf(
                img.attr("src"),
                img.attr("data-src"),
                img.attr("data-original"),
                img.attr("data-lazy-src"),
                img.attr("data-image")
            )
            for (c in candidates) {
                if (c.contains("/uploads/posts/", ignoreCase = true) || c.endsWith(".webp") || c.endsWith(".png") || c.endsWith(".jpg") || c.endsWith(".jpeg")) {
                    return normalizeImageUrl(c)
                }
            }
            val srcset = img.attr("srcset")
            if (srcset.isNotBlank()) {
                val parts = srcset.split(",")
                for (p in parts) {
                    val candidate = p.trim().split(" ").firstOrNull().orEmpty()
                    if (candidate.contains("/uploads/posts/", ignoreCase = true) || candidate.contains(".webp", ignoreCase = true)) {
                        return normalizeImageUrl(candidate)
                    }
                }
            }
        }

        // 2. Buscar enlaces <a> con imágenes de posts
        val links = elem.select("a[href*='uploads/posts'], a[data-src*='uploads/posts']")
        for (a in links) {
            val href = a.attr("href").ifBlank { a.attr("data-src") }
            if (href.isNotBlank()) return normalizeImageUrl(href)
        }

        // 3. Primer imagen disponible
        val fallbackSrc = imgs.firstOrNull()?.attr("src").orEmpty()
        if (fallbackSrc.isNotBlank()) return normalizeImageUrl(fallbackSrc)

        return ""
    }

    /**
     * Obtiene el directorio de destino accesible públicamente en la carpeta Descargas del teléfono
     */
    fun getTargetOutputDirectories(context: Context): List<File> {
        val dirs = mutableListOf<File>()
        
        // 1. Directorio público de Descargas (/Download/WR_META_141)
        try {
            val publicDownloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val publicWrDir = File(publicDownloads, "WR_META_141")
            if (!publicWrDir.exists()) publicWrDir.mkdirs()
            if (publicWrDir.exists() && publicWrDir.canWrite()) {
                dirs.add(publicWrDir)
            }
        } catch (e: Exception) {
            AppLogger.w(TAG, "No se pudo acceder a public Downloads: ${e.message}")
        }

        // 2. Directorio externo específico de la app (Android/data/.../files/Download/WR_META_141)
        try {
            val appExtDownload = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            if (appExtDownload != null) {
                val appWrDir = File(appExtDownload, "WR_META_141")
                if (!appWrDir.exists()) appWrDir.mkdirs()
                if (appWrDir.exists()) dirs.add(appWrDir)
            }
        } catch (e: Exception) {
            AppLogger.w(TAG, "No se pudo acceder a app external files: ${e.message}")
        }

        // 3. Respaldo interno
        val internalDir = File(context.filesDir, "WR_META_141")
        if (!internalDir.exists()) internalDir.mkdirs()
        dirs.add(internalDir)

        return dirs
    }

    /**
     * Extrae las URLs oficiales (estilo https://wr-meta.com/uploads/posts/...) y descarga los avatares a disco.
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
                val p = (catIdx.toFloat() / CATEGORIES.size.toFloat()) * 0.30f
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
                        val championElements = doc.select(".champ-item, .tier-list-item, tr:has(td), .item, .champion-card, a[href*='/champion/'], article, .post-item")

                        for (elem in championElements) {
                            val rawName = elem.select(".name, .champ-name, .title, strong, a").text().trim()
                            val clean = cleanName(rawName)
                            if (clean.length in 2..25 && !clean.contains("Tier", ignoreCase = true) && !clean.contains("Wild Rift", ignoreCase = true)) {
                                val norm = normalizeName(clean)
                                val imgUrl = extractImageUrl(elem)
                                if (imgUrl.isNotBlank()) {
                                    if (!championsMap.containsKey(norm) || (championsMap[norm]?.second?.contains("uploads/posts") != true && imgUrl.contains("uploads/posts"))) {
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

            // Asegurar que los 141 campeones canónicos estén presentes con sus URLs oficiales de wr-meta
            val canonicalList = WildRiftRepository.champions
            for (champ in canonicalList) {
                val norm = normalizeName(champ.name)
                if (!championsMap.containsKey(norm) || championsMap[norm]?.second.isNullOrBlank()) {
                    var canonicalUrl = champ.avatarUrl
                    if (canonicalUrl.isBlank() || !canonicalUrl.startsWith("http")) {
                        val slug = sanitizeFileName(champ.name)
                        canonicalUrl = "https://wr-meta.com/uploads/posts/2022-10/1665262235_${slug}_10_20_11zon.webp"
                    }
                    championsMap[norm] = Pair(champ.name, normalizeImageUrl(canonicalUrl))
                }
            }

            // Carpetas de destino (incluyendo Descargas públicas del teléfono)
            val targetDirs = getTargetOutputDirectories(context)
            val primaryDir = targetDirs.firstOrNull() ?: File(context.filesDir, "WR_META_141")

            for (dir in targetDirs) {
                val imgDir = File(dir, "imagenes")
                if (!imgDir.exists()) imgDir.mkdirs()
            }

            val totalChamps = championsMap.size
            var downloadedCount = 0
            val avatarRecords = mutableListOf<ChampionAvatarRecord>()
            val filesToScan = mutableListOf<String>()

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
                            else -> "webp"
                        }
                        val fileName = "${sanitizeFileName(displayName)}.$ext"
                        var isSuccess = false
                        var imageBytes: ByteArray? = null

                        if (imgUrl.startsWith("http")) {
                            semaphore.withPermit {
                                try {
                                    val imgRequest = Request.Builder()
                                        .url(imgUrl)
                                        .header("User-Agent", userAgent)
                                        .header("Referer", "$BASE_URL/")
                                        .build()

                                    val imgResponse = httpClient.newCall(imgRequest).execute()
                                    if (imgResponse.isSuccessful) {
                                        imageBytes = imgResponse.body?.bytes()
                                    }
                                } catch (err: Exception) {
                                    AppLogger.w(TAG, "Error al descargar avatar de $displayName ($imgUrl): ${err.message}")
                                }
                            }
                        }

                        // Guardar en todas las carpetas destino (Descargas públicas, App ext, interna)
                        if (imageBytes != null && imageBytes.isNotEmpty()) {
                            for (dir in targetDirs) {
                                try {
                                    val imgDir = File(dir, "imagenes")
                                    val targetFile = File(imgDir, fileName)
                                    FileOutputStream(targetFile).use { it.write(imageBytes) }
                                    if (targetFile.exists() && targetFile.length() > 500) {
                                        isSuccess = true
                                        synchronized(filesToScan) {
                                            filesToScan.add(targetFile.absolutePath)
                                        }
                                    }
                                } catch (e: Exception) {
                                    AppLogger.w(TAG, "Error guardando en ${dir.path}: ${e.message}")
                                }
                            }
                        }

                        synchronized(avatarRecords) {
                            downloadedCount++
                            val progress = 0.30f + ((downloadedCount.toFloat() / totalChamps.toFloat()) * 0.70f)
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

            // Guardar archivos de texto, CSV y JSON en cada directorio destino
            for (dir in targetDirs) {
                try {
                    // 1. urls_imagenes.txt con formato exacto: Nombre | URL
                    val urlsTxtFile = File(dir, "urls_imagenes.txt")
                    val urlsWriter = FileWriter(urlsTxtFile)
                    avatarRecords.sortedBy { it.name }.forEach { record ->
                        urlsWriter.append("${record.name} | ${record.avatarUrl}\n")
                    }
                    urlsWriter.flush()
                    urlsWriter.close()
                    filesToScan.add(urlsTxtFile.absolutePath)

                    // 2. campeones.json
                    val jsonFile = File(dir, "campeones.json")
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
                    filesToScan.add(jsonFile.absolutePath)

                    // 3. campeones.csv
                    val csvFile = File(dir, "campeones.csv")
                    val csvWriter = FileWriter(csvFile)
                    csvWriter.append("name,avatar_url,local_file,downloaded\n")
                    avatarRecords.sortedBy { it.name }.forEach { record ->
                        csvWriter.append("\"${record.name}\",\"${record.avatarUrl}\",\"${record.localFileName}\",${record.isDownloaded}\n")
                    }
                    csvWriter.flush()
                    csvWriter.close()
                    filesToScan.add(csvFile.absolutePath)
                } catch (e: Exception) {
                    AppLogger.w(TAG, "Error guardando meta-archivos en ${dir.path}: ${e.message}")
                }
            }

            // Notificar al indexador del sistema Android (MediaScanner) para que aparezcan en Galería y Gestor de Archivos
            try {
                if (filesToScan.isNotEmpty()) {
                    MediaScannerConnection.scanFile(
                        context,
                        filesToScan.toTypedArray(),
                        null
                    ) { path, uri ->
                        AppLogger.d(TAG, "Archivo escaneado por el sistema: $path -> $uri")
                    }
                }
            } catch (e: Exception) {
                AppLogger.w(TAG, "Error invocando MediaScanner: ${e.message}")
            }

            val urlsTxtPrimary = File(primaryDir, "urls_imagenes.txt")
            val nowTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
            
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_LAST_SCRAPE_TIME, nowTime)
                .putInt(KEY_LAST_CHAMPS_COUNT, avatarRecords.size)
                .putString(KEY_LAST_FOLDER_PATH, primaryDir.absolutePath)
                .putString(KEY_LAST_URLS_PATH, urlsTxtPrimary.absolutePath)
                .apply()

            _scrapingState.value = WrMetaScrapingState.Success(
                championsCount = avatarRecords.size,
                downloadedImagesCount = avatarRecords.count { it.isDownloaded },
                timestamp = nowTime,
                publicDirectoryPath = primaryDir.absolutePath,
                urlsFilePath = urlsTxtPrimary.absolutePath
            )

            AppLogger.d(TAG, "Descarga completada en teléfono: ${avatarRecords.size} avatares guardados en $primaryDir")
            true
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error fatal en descarga de avatares: ${e.message}", e)
            _scrapingState.value = WrMetaScrapingState.Error("Fallo al guardar en celular: ${e.localizedMessage ?: "Error de almacenamiento"}")
            false
        }
    }

    /**
     * Permite compartir o abrir el archivo urls_imagenes.txt con cualquier aplicación (WhatsApp, Drive, Bloc de Notas)
     */
    fun shareUrlsFile(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val path = prefs.getString(KEY_LAST_URLS_PATH, "")
        val file = if (!path.isNullOrBlank()) File(path) else {
            val publicDownloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            File(File(publicDownloads, "WR_META_141"), "urls_imagenes.txt")
        }

        if (!file.exists()) {
            Toast.makeText(context, "El archivo urls_imagenes.txt aún no ha sido descargado", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val uri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, "Wild Rift - URLs de Avatares (141 Campeones)")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(Intent.createChooser(shareIntent, "Compartir urls_imagenes.txt").apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error al compartir archivo: ${e.message}", e)
            Toast.makeText(context, "Error al compartir: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
