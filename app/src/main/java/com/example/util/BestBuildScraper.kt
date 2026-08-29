package com.example.util

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import java.net.URL
import java.util.Locale

object BestBuildScraper {
    private const val BASE_URL = "https://bestbuildwr.com"
    private const val CHAMPIONS_URL = "$BASE_URL/champions"
    
    private val ROLE_PATTERNS = mapOf(
        "Top" to listOf("top", "toplane", "top lane"),
        "Jungle" to listOf("jungle", "jungla", "jungler"),
        "Mid" to listOf("mid", "middle", "medio", "mid lane"),
        "ADC" to listOf("adc", "bot", "bot lane", "tirador"),
        "Support" to listOf("support", "soporte", "sup")
    )

    private fun detectRoles(text: String): List<String> {
        val lowerText = text.lowercase(Locale.getDefault())
        val roles = mutableListOf<String>()
        for ((role, patterns) in ROLE_PATTERNS) {
            for (pattern in patterns) {
                val regex = Regex("\\b$pattern\\b")
                if (regex.containsMatchIn(lowerText)) {
                    roles.add(role)
                    break
                }
            }
        }
        return roles
    }

    private fun normalizeUrl(url: String): String {
        val fullUrl = if (url.startsWith("http")) url else "$BASE_URL$url"
        return try {
            val parsed = URL(fullUrl)
            "${parsed.protocol}://${parsed.host}${parsed.path}".removeSuffix("/")
        } catch (e: Exception) {
            fullUrl
        }
    }
    
    private fun cleanText(text: String?): String {
        return text?.replace(Regex("\\s+"), " ")?.trim() ?: ""
    }

    suspend fun runScraper(context: Context, onProgress: (String) -> Unit): Boolean = withContext(Dispatchers.IO) {
        try {
            onProgress("Iniciando conexión con BestBuildWR...")
            
            val doc = Jsoup.connect(CHAMPIONS_URL)
                .userAgent("Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Mobile Safari/537.36")
                .timeout(25000)
                .get()

            val championsMap = mutableMapOf<String, JSONObject>()

            val links = doc.select("a[href]")
            for (link in links) {
                val href = link.attr("href").trim()
                if (href.isEmpty()) continue
                val fullUrl = normalizeUrl(href)
                val parsed = try { URL(fullUrl) } catch (e: Exception) { continue }
                val path = parsed.path.removeSuffix("/")
                if (!path.startsWith("/champions/")) continue

                val slug = path.removePrefix("/champions/")
                if (slug.isEmpty() || slug.contains("/")) continue
                
                val nameText = cleanText(link.text())
                val name = if (nameText.isNotBlank()) nameText else slug.replace("-", " ").split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
                
                val champObj = JSONObject()
                champObj.put("name", name)
                champObj.put("slug", slug)
                champObj.put("url", "$BASE_URL/champions/$slug")
                championsMap[slug] = champObj
            }
            
            val championsList = championsMap.values.toList().sortedBy { it.getString("name").lowercase() }
            if (championsList.isEmpty()) {
                onProgress("Error: No se encontraron campeones.")
                return@withContext false
            }

            onProgress("Campeones detectados: ${championsList.size}. Iniciando escaneo profundo...")
            
            val semaphore = Semaphore(5)
            var completed = 0
            
            val detailedChampions = championsList.map { champObj ->
                async {
                    semaphore.withPermit {
                        val result = scrapeChampion(champObj.getString("name"), champObj.getString("url"), champObj.getString("slug"))
                        completed++
                        withContext(Dispatchers.Main) {
                            onProgress("Procesado: ${champObj.getString("name")} ($completed/${championsList.size})")
                        }
                        result
                    }
                }
            }.awaitAll()
            
            onProgress("Guardando archivos en el dispositivo...")
            
            val finalJsonArray = JSONArray()
            detailedChampions.forEach { finalJsonArray.put(it) }
            
            saveJsonFile(context, finalJsonArray.toString(2))
            saveCsvFile(context, detailedChampions)
            saveUrlsFile(context, detailedChampions)
            
            onProgress("¡Completado con éxito! Archivos guardados en Descargas.")
            delay(2000)
            return@withContext true

        } catch (e: Exception) {
            e.printStackTrace()
            onProgress("Fallo crítico: ${e.message}")
            delay(3000)
            return@withContext false
        }
    }
    
    private fun scrapeChampion(name: String, url: String, slug: String): JSONObject {
        val result = JSONObject()
        result.put("name", name)
        result.put("slug", slug)
        result.put("url", url)
        
        try {
            val doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Mobile Safari/537.36")
                .timeout(25000)
                .get()
                
            val pageText = cleanText(doc.text())
            val roles = detectRoles(pageText)
            result.put("roles", JSONArray(roles))
            
            val buildsMap = mutableMapOf<String, JSONObject>()
            val links = doc.select("a[href]")
            for (link in links) {
                val href = link.attr("href").trim()
                if (href.isEmpty()) continue
                val fullUrl = normalizeUrl(href)
                if (!fullUrl.contains("/builds/")) continue
                
                val buildSlug = URL(fullUrl).path.removePrefix("/builds/")
                if (buildSlug.isEmpty()) continue
                
                var buildName = cleanText(link.text())
                if (buildName.isEmpty()) {
                    buildName = cleanText(link.parent()?.text())
                }
                if (buildName.isEmpty()) {
                    buildName = buildSlug.replace("-", " ").split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
                }
                
                val buildRoles = detectRoles(buildName)
                
                val buildObj = JSONObject()
                buildObj.put("name", buildName)
                buildObj.put("url", fullUrl)
                buildObj.put("roles", JSONArray(buildRoles))
                buildsMap[fullUrl] = buildObj
            }
            
            val buildsList = buildsMap.values.toList().sortedBy { it.getString("name").lowercase() }
            val buildsArray = JSONArray()
            buildsList.forEach { buildsArray.put(it) }
            
            result.put("builds", buildsArray)
            result.put("build_count", buildsList.size)
            result.put("error", JSONObject.NULL)
            
        } catch (e: Exception) {
            result.put("roles", JSONArray())
            result.put("builds", JSONArray())
            result.put("build_count", 0)
            result.put("error", e.message)
        }
        return result
    }

    private fun saveJsonFile(context: Context, jsonString: String) {
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "bestbuildwr_141.json")
            put(MediaStore.MediaColumns.MIME_TYPE, "application/json")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }
        val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
        if (uri != null) {
            resolver.openOutputStream(uri)?.use { outputStream ->
                outputStream.write(jsonString.toByteArray())
            }
        }
    }
    
    private fun saveCsvFile(context: Context, data: List<JSONObject>) {
        val builder = java.lang.StringBuilder()
        builder.append("champion,champion_slug,champion_url,champion_roles,build_name,build_url,build_roles,author\n")
        
        for (champ in data) {
            val cName = escapeCsv(champ.getString("name"))
            val cSlug = escapeCsv(champ.getString("slug"))
            val cUrl = escapeCsv(champ.getString("url"))
            val cRoles = escapeCsv(joinJsonArray(champ.getJSONArray("roles")))
            
            val builds = champ.getJSONArray("builds")
            if (builds.length() == 0) {
                builder.append("$cName,$cSlug,$cUrl,$cRoles,,,,\n")
            } else {
                for (i in 0 until builds.length()) {
                    val build = builds.getJSONObject(i)
                    val bName = escapeCsv(build.getString("name"))
                    val bUrl = escapeCsv(build.getString("url"))
                    val bRoles = escapeCsv(joinJsonArray(build.getJSONArray("roles")))
                    val bAuthor = "" // Can be added later if needed
                    builder.append("$cName,$cSlug,$cUrl,$cRoles,$bName,$bUrl,$bRoles,$bAuthor\n")
                }
            }
        }
        
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "bestbuildwr_141.csv")
            put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }
        val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
        if (uri != null) {
            resolver.openOutputStream(uri)?.use { outputStream ->
                outputStream.write(builder.toString().toByteArray())
            }
        }
    }
    
    private fun saveUrlsFile(context: Context, data: List<JSONObject>) {
        val urls = mutableSetOf<String>()
        for (champ in data) {
            urls.add(champ.getString("url"))
            val builds = champ.getJSONArray("builds")
            for (i in 0 until builds.length()) {
                val build = builds.getJSONObject(i)
                urls.add(build.getString("url"))
            }
        }
        
        val builder = java.lang.StringBuilder()
        urls.sorted().forEach { builder.append(it).append("\n") }
        
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "bestbuildwr_urls.txt")
            put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }
        val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
        if (uri != null) {
            resolver.openOutputStream(uri)?.use { outputStream ->
                outputStream.write(builder.toString().toByteArray())
            }
        }
    }
    
    private fun escapeCsv(value: String): String {
        var str = value
        if (str.contains("\"")) str = str.replace("\"", "\"\"")
        if (str.contains(",") || str.contains("\n") || str.contains("\"")) {
            str = "\"$str\""
        }
        return str
    }
    
    private fun joinJsonArray(array: JSONArray): String {
        val list = mutableListOf<String>()
        for (i in 0 until array.length()) {
            list.add(array.getString(i))
        }
        return list.joinToString(", ")
    }
}
