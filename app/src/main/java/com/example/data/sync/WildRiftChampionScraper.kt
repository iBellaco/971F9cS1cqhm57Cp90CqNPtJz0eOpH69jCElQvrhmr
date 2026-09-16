package com.example.data.sync

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

data class ChampionScraperItem(
    val id: String,
    val name: String,
    val imageUrl: String
)

data class ScraperResultSummary(
    val isRunning: Boolean,
    val progressText: String,
    val totalFound: Int,
    val processedCount: Int,
    val errorCount: Int,
    val duplicateCount: Int,
    val logs: List<String>,
    val isFinished: Boolean,
    val manifestPath: String?
)

object WildRiftChampionScraper {
    private const val TAG = "WildRiftChampionScraper"
    private const val WIKI_URL = "https://wiki.leagueoflegends.com/en-us/WR:Champion"
    private const val EXPECTED_CHAMPIONS = 141

    private val client = OkHttpClient.Builder()
        .connectTimeout(25, TimeUnit.SECONDS)
        .readTimeout(25, TimeUnit.SECONDS)
        .build()

    private val _scraperState = MutableStateFlow(
        ScraperResultSummary(
            isRunning = false,
            progressText = "Listo para iniciar scraping desde la wiki oficial...",
            totalFound = 0,
            processedCount = 0,
            errorCount = 0,
            duplicateCount = 0,
            logs = emptyList(),
            isFinished = false,
            manifestPath = null
        )
    )
    val scraperState: StateFlow<ScraperResultSummary> = _scraperState.asStateFlow()

    fun safeName(name: String): String {
        val allowed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_"
        val result = name.map { if (allowed.indexOf(it) >= 0) it else '_' }.joinToString("")
        return result.trim('_').ifBlank { "unknown" }
    }

    private fun computeSha256(bytes: ByteArray): String {
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }

    private fun normalizeImageUrl(url: String?): String? {
        if (url.isNullOrBlank()) return null
        val trimmed = url.trim()
        if (trimmed.startsWith("//")) {
            return "https:$trimmed"
        }
        if (trimmed.startsWith("/")) {
            return "https://wiki.leagueoflegends.com$trimmed"
        }
        if (!trimmed.startsWith("http")) {
            return "https://wiki.leagueoflegends.com/en-us/$trimmed"
        }
        return trimmed
    }

    private fun extractImageUrl(imgTag: Element): String? {
        val attributes = listOf("data-src", "data-original", "data-image-url", "data-lazy-src", "src")
        val candidates = mutableListOf<String>()

        for (attr in attributes) {
            val value = imgTag.attr(attr)
            if (value.isNotBlank()) {
                candidates.add(value)
            }
        }

        for (attr in listOf("srcset", "data-srcset")) {
            val value = imgTag.attr(attr)
            if (value.isNotBlank()) {
                for (item in value.split(",")) {
                    val candidate = item.trim().split(" ").firstOrNull()
                    if (!candidate.isNullOrBlank()) {
                        candidates.add(candidate)
                    }
                }
            }
        }

        for (candidate in candidates) {
            val normalized = normalizeImageUrl(candidate)
            if (!normalized.isNullOrBlank() && !normalized.startsWith("data:")) {
                return normalized
            }
        }
        return null
    }

    private fun cleanChampionName(name: String): String {
        return name.replace(Regex("\\s+"), " ").trim()
    }

    private fun parseChampionsFromWiki(html: String): List<ChampionScraperItem> {
        val doc = Jsoup.parse(html)
        val champions = mutableListOf<ChampionScraperItem>()
        val seen = mutableSetOf<Pair<String, String>>()

        val tables = doc.select("table")
        for (table in tables) {
            val rows = table.select("tr")
            for (row in rows) {
                val imgTag = row.selectFirst("img") ?: continue
                val imageUrl = extractImageUrl(imgTag) ?: continue

                val cells = row.select("th, td")
                if (cells.isEmpty()) continue

                var name: String? = null
                for (cell in cells) {
                    val text = cleanChampionName(cell.text())
                    if (text.isBlank()) continue
                    val lower = text.lowercase(Locale.ROOT)
                    if (lower in setOf("champion", "name", "role", "difficulty", "release date")) continue
                    if (text.length <= 80) {
                        name = text
                        break
                    }
                }

                if (name.isNullOrBlank()) {
                    name = cleanChampionName(imgTag.attr("alt"))
                }

                if (name.isNullOrBlank()) continue

                val key = Pair(name.lowercase(Locale.ROOT), imageUrl)
                if (key in seen) continue
                seen.add(key)

                champions.add(
                    ChampionScraperItem(
                        id = safeName(name).lowercase(Locale.ROOT),
                        name = name,
                        imageUrl = imageUrl
                    )
                )
            }
        }
        return champions
    }

    private fun prepareImage(source: Bitmap): Bitmap {
        val targetSize = 120
        val width = source.width
        val height = source.height
        if (width <= 0 || height <= 0) return source

        val scale = min(targetSize.toFloat() / width, targetSize.toFloat() / height)
        val newWidth = max(1, (width * scale).toInt())
        val newHeight = max(1, (height * scale).toInt())

        val resized = Bitmap.createScaledBitmap(source, newWidth, newHeight, true)
        val canvasBitmap = Bitmap.createBitmap(targetSize, targetSize, Bitmap.Config.RGB_565)
        val canvas = Canvas(canvasBitmap)
        canvas.drawColor(android.graphics.Color.BLACK)

        val x = (targetSize - newWidth) / 2f
        val y = (targetSize - newHeight) / 2f
        canvas.drawBitmap(resized, x, y, null)
        return canvasBitmap
    }

    private fun createNegative(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val output = Bitmap.createBitmap(width, height, bitmap.config ?: Bitmap.Config.RGB_565)
        val canvas = Canvas(output)
        val paint = Paint()
        val colorMatrix = ColorMatrix(
            floatArrayOf(
                -1f, 0f, 0f, 0f, 255f,
                0f, -1f, 0f, 0f, 255f,
                0f, 0f, -1f, 0f, 255f,
                0f, 0f, 0f, 1f, 0f
            )
        )
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return output
    }

    private fun createLowQuality(bitmap: Bitmap, quality: Int): Bitmap {
        val stream = java.io.ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
        val bytes = stream.toByteArray()
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size) ?: bitmap
    }

    private fun createBlur(bitmap: Bitmap): Bitmap {
        // Blur aproximado mediante escala intermedia
        val small = Bitmap.createScaledBitmap(bitmap, 60, 60, true)
        return Bitmap.createScaledBitmap(small, 120, 120, true)
    }

    private fun createNoise(bitmap: Bitmap, amount: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val output = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val random = Random(42)
        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = output.getPixel(x, y)
                val r = (android.graphics.Color.red(pixel) + random.nextInt(-amount, amount + 1)).coerceIn(0, 255)
                val g = (android.graphics.Color.green(pixel) + random.nextInt(-amount, amount + 1)).coerceIn(0, 255)
                val b = (android.graphics.Color.blue(pixel) + random.nextInt(-amount, amount + 1)).coerceIn(0, 255)
                output.setPixel(x, y, android.graphics.Color.rgb(r, g, b))
            }
        }
        return output
    }

    private fun createContrast(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val output = Bitmap.createBitmap(width, height, bitmap.config ?: Bitmap.Config.RGB_565)
        val canvas = Canvas(output)
        val paint = Paint()
        val cm = ColorMatrix().apply {
            setScale(0.70f, 0.70f, 0.70f, 1f)
        }
        val brightnessMatrix = ColorMatrix(
            floatArrayOf(
                1f, 0f, 0f, 0f, -38f,
                0f, 1f, 0f, 0f, -38f,
                0f, 0f, 1f, 0f, -38f,
                0f, 0f, 0f, 1f, 0f
            )
        )
        cm.postConcat(brightnessMatrix)
        paint.colorFilter = ColorMatrixColorFilter(cm)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return output
    }

    private fun createPixelated(bitmap: Bitmap): Bitmap {
        val small = Bitmap.createScaledBitmap(bitmap, 30, 30, false)
        return Bitmap.createScaledBitmap(small, 120, 120, false)
    }

    suspend fun runScraper(context: Context) {
        withContext(Dispatchers.IO) {
            val datasetDir = File(context.filesDir, "dataset")
            val variants = listOf("originals", "normal", "negative", "low_quality", "blur", "noise", "contrast", "pixelated")
            for (v in variants) {
                File(datasetDir, v).mkdirs()
            }

            val logs = mutableListOf<String>()
            fun addLog(msg: String) {
                logs.add(msg)
                if (logs.size > 120) logs.removeAt(0)
                _scraperState.value = _scraperState.value.copy(logs = logs.toList())
            }

            _scraperState.value = ScraperResultSummary(
                isRunning = true,
                progressText = "Conectando a la Wiki oficial de Wild Rift...",
                totalFound = 0,
                processedCount = 0,
                errorCount = 0,
                duplicateCount = 0,
                logs = logs,
                isFinished = false,
                manifestPath = null
            )

            try {
                val request = Request.Builder()
                    .url(WIKI_URL)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/140 Safari/537.36")
                    .build()

                val response = client.newCall(request).execute()
                val html = response.body?.string() ?: throw IOException("HTML vacío de la Wiki")

                addLog("Analizando HTML de la Wiki con Jsoup...")
                val championsList = parseChampionsFromWiki(html)

                addLog("Roster obtenido: ${championsList.size} campeones detectados (Esperados: $EXPECTED_CHAMPIONS).")
                _scraperState.value = _scraperState.value.copy(totalFound = championsList.size)

                val manifestChampions = JSONArray()
                val errorsArray = JSONArray()
                val hashesMap = mutableMapOf<String, String>()
                val duplicatesArray = JSONArray()

                var processed = 0
                var errors = 0
                var duplicates = 0

                for ((index, champ) in championsList.withIndex()) {
                    try {
                        addLog("[${index + 1}/${championsList.size}] Procesando a ${champ.name}...")
                        val imgReq = Request.Builder()
                            .url(champ.imageUrl)
                            .header("User-Agent", "Mozilla/5.0")
                            .build()
                        val imgResp = client.newCall(imgReq).execute()
                        val imgBytes = imgResp.body?.bytes() ?: throw IOException("No se pudo descargar imagen de ${champ.imageUrl}")
                        val originalBitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.size) ?: throw IOException("Bitmap inválido")

                        val filename = safeName(champ.name)

                        // Original
                        val origFile = File(datasetDir, "originals/$filename.png")
                        FileOutputStream(origFile).use { out ->
                            originalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                        }

                        // Normal 120x120
                        val normalBitmap = prepareImage(originalBitmap)
                        val normalFile = File(datasetDir, "normal/$filename.png")
                        FileOutputStream(normalFile).use { out ->
                            normalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                        }

                        // Negative
                        val negBitmap = createNegative(normalBitmap)
                        FileOutputStream(File(datasetDir, "negative/$filename.png")).use { out ->
                            negBitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                        }

                        // Low Quality
                        val lq1 = createLowQuality(normalBitmap, 30)
                        val lq2 = createLowQuality(normalBitmap, 15)
                        FileOutputStream(File(datasetDir, "low_quality/${filename}_01.jpg")).use { out -> lq1.compress(Bitmap.CompressFormat.JPEG, 30, out) }
                        FileOutputStream(File(datasetDir, "low_quality/${filename}_02.jpg")).use { out -> lq2.compress(Bitmap.CompressFormat.JPEG, 15, out) }

                        // Blur
                        val blur1 = createBlur(normalBitmap)
                        val blur2 = createBlur(blur1)
                        FileOutputStream(File(datasetDir, "blur/${filename}_01.jpg")).use { out -> blur1.compress(Bitmap.CompressFormat.JPEG, 85, out) }
                        FileOutputStream(File(datasetDir, "blur/${filename}_02.jpg")).use { out -> blur2.compress(Bitmap.CompressFormat.JPEG, 75, out) }

                        // Noise
                        val noise1 = createNoise(normalBitmap, 7)
                        val noise2 = createNoise(normalBitmap, 14)
                        FileOutputStream(File(datasetDir, "noise/${filename}_01.jpg")).use { out -> noise1.compress(Bitmap.CompressFormat.JPEG, 80, out) }
                        FileOutputStream(File(datasetDir, "noise/${filename}_02.jpg")).use { out -> noise2.compress(Bitmap.CompressFormat.JPEG, 65, out) }

                        // Contrast
                        val contrastBitmap = createContrast(normalBitmap)
                        FileOutputStream(File(datasetDir, "contrast/$filename.jpg")).use { out -> contrastBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out) }

                        // Pixelated
                        val pixelatedBitmap = createPixelated(normalBitmap)
                        FileOutputStream(File(datasetDir, "pixelated/$filename.jpg")).use { out -> pixelatedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out) }

                        // Hash
                        val normalStream = java.io.ByteArrayOutputStream()
                        normalBitmap.compress(Bitmap.CompressFormat.PNG, 100, normalStream)
                        val normalHash = computeSha256(normalStream.toByteArray())

                        if (hashesMap.containsKey(normalHash)) {
                            duplicates++
                            val dupObj = JSONObject().apply {
                                put("champion", champ.name)
                                put("same_as", hashesMap[normalHash])
                            }
                            duplicatesArray.put(dupObj)
                        } else {
                            hashesMap[normalHash] = champ.name
                        }

                        val champJson = JSONObject().apply {
                            put("id", champ.id)
                            put("name", champ.name)
                            put("source", champ.imageUrl)
                            put("hash", normalHash)
                            put("files", JSONObject().apply {
                                put("original", "originals/$filename.png")
                                put("normal", "normal/$filename.png")
                                put("negative", "negative/$filename.png")
                                put("low_quality", JSONArray().put("low_quality/${filename}_01.jpg").put("low_quality/${filename}_02.jpg"))
                                put("blur", JSONArray().put("blur/${filename}_01.jpg").put("blur/${filename}_02.jpg"))
                                put("noise", JSONArray().put("noise/${filename}_01.jpg").put("noise/${filename}_02.jpg"))
                                put("contrast", "contrast/$filename.jpg")
                                put("pixelated", "pixelated/$filename.jpg")
                            })
                        }
                        manifestChampions.put(champJson)
                        processed++

                        _scraperState.value = _scraperState.value.copy(
                            processedCount = processed,
                            duplicateCount = duplicates,
                            progressText = "Procesado: ${champ.name} (${index + 1}/${championsList.size})"
                        )
                        delay(100)
                    } catch (e: Exception) {
                        errors++
                        val errObj = JSONObject().apply {
                            put("index", index + 1)
                            put("champion", champ.name)
                            put("error", e.message ?: "Error desconocido")
                        }
                        errorsArray.put(errObj)
                        addLog("[ERROR] ${champ.name}: ${e.message}")
                        _scraperState.value = _scraperState.value.copy(errorCount = errors)
                    }
                }

                val manifestObj = JSONObject().apply {
                    put("dataset_version", "1.0")
                    put("generated_at", SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).apply { timeZone = TimeZone.getTimeZone("UTC") }.format(Date()))
                    put("source_url", WIKI_URL)
                    put("expected_champions", EXPECTED_CHAMPIONS)
                    put("detected_champions", championsList.size)
                    put("image_size", "120x120")
                    put("variants", JSONArray().put("normal").put("negative").put("low_quality").put("blur").put("noise").put("contrast").put("pixelated"))
                    put("champions", manifestChampions)
                    put("duplicates", duplicatesArray)
                }

                val manifestFile = File(datasetDir, "manifest.json")
                manifestFile.writeText(manifestObj.toString(2), Charsets.UTF_8)

                val errorsFile = File(datasetDir, "errors.json")
                errorsFile.writeText(errorsArray.toString(2), Charsets.UTF_8)

                if (championsList.size != EXPECTED_CHAMPIONS) {
                    addLog("[AVISO] La wiki devolvió ${championsList.size} campeones (esperados $EXPECTED_CHAMPIONS).")
                }

                addLog("Dataset generado con éxito en: ${datasetDir.absolutePath}")
                _scraperState.value = _scraperState.value.copy(
                    isRunning = false,
                    isFinished = true,
                    progressText = "Scraping finalizado. $processed campeones procesados.",
                    manifestPath = manifestFile.absolutePath
                )

            } catch (e: Exception) {
                addLog("[FATAL] Error general en el scraping de la Wiki: ${e.message}")
                _scraperState.value = _scraperState.value.copy(
                    isRunning = false,
                    isFinished = true,
                    progressText = "Error en scraping: ${e.message}"
                )
            }
        }
    }
}
