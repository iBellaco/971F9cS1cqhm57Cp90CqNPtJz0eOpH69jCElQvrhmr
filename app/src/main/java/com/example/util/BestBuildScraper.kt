package com.example.util

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import java.net.URL

/**
 * Scraper nativo de BestBuildWR para el Panel de Administración.
 * Realiza la extracción exacta siguiendo la lógica de:
 * 1. Obtener lista de campeones desde https://bestbuildwr.com/champions
 *    - Filtra enlaces con "/champions/"
 *    - Descarta subrutas
 *    - Obtiene o formatea el nombre canónico
 * 2. Para cada campeón, descarga su HTML y busca enlaces canónicos de builds:
 *    - URLs que inicien con "https://bestbuildwr.com/builds/"
 *    - Extrae el nombre descriptivo de la build
 * 3. Elimina duplicados por build_url.
 * 4. Guarda los 3 archivos en Descargas:
 *    - bestbuildwr_builds.json
 *    - bestbuildwr_builds.csv ("champion", "build_name", "build_url")
 *    - bestbuildwr_builds.txt ("champion | build_url")
 */
object BestBuildScraper {
    private const val BASE_URL = "https://bestbuildwr.com"
    private const val CHAMPIONS_URL = "$BASE_URL/champions"
    private const val USER_AGENT = "Mozilla/5.0 (Linux; Android 13; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Mobile Safari/537.36"

    data class ChampionEntry(
        val nombre: String,
        val url: String
    )

    data class BuildEntry(
        val champion: String,
        val buildName: String,
        val buildUrl: String
    )

    private fun descargar(url: String): String? {
        return try {
            val response = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(30000)
                .ignoreHttpErrors(false)
                .execute()
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun urlJoin(base: String, href: String): String {
        return try {
            val baseUrl = URL(base)
            URL(baseUrl, href).toString().removeSuffix("/")
        } catch (e: Exception) {
            if (href.startsWith("http")) href else "$base/$href".replace("//", "/")
        }
    }

    private fun obtenerCampeones(): List<ChampionEntry> {
        val html = descargar(CHAMPIONS_URL) ?: return emptyList()
        val soup = Jsoup.parse(html, BASE_URL)
        val campeonesMap = mutableMapOf<String, ChampionEntry>()

        val enlaces = soup.select("a[href]")
        for (enlace in enlaces) {
            val href = enlace.attr("href").trim()
            if (href.isEmpty()) continue

            val url = urlJoin(BASE_URL, href)

            // Solo URLs de campeones
            if (!url.contains("/champions/")) continue

            // Evitar subrutas
            val parte = url.substringAfter("/champions/")
            if (parte.contains("/")) continue

            var nombre = enlace.text().replace(Regex("\\s+"), " ").trim()
            if (nombre.isEmpty()) {
                nombre = parte.replace("-", " ")
                    .split(" ")
                    .joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
            }

            campeonesMap[url] = ChampionEntry(
                nombre = nombre,
                url = url
            )
        }

        return campeonesMap.values.toList()
    }

    private fun obtenerBuilds(campeon: ChampionEntry): List<BuildEntry> {
        val html = descargar(campeon.url) ?: return emptyList()
        val soup = Jsoup.parse(html, BASE_URL)
        val buildsMap = mutableMapOf<String, BuildEntry>()

        val enlaces = soup.select("a[href]")
        for (enlace in enlaces) {
            val href = enlace.attr("href").trim()
            if (href.isEmpty()) continue

            val url = urlJoin(BASE_URL, href)

            // Solo queremos: https://bestbuildwr.com/builds/...
            if (!url.startsWith("$BASE_URL/builds/")) continue

            // Evitar duplicados
            if (buildsMap.containsKey(url)) continue

            val nombre = enlace.text().replace(Regex("\\s+"), " ").trim()

            buildsMap[url] = BuildEntry(
                champion = campeon.nombre,
                buildName = if (nombre.isNotEmpty()) nombre else "Build General",
                buildUrl = url
            )
        }

        return buildsMap.values.toList()
    }

    suspend fun runScraper(context: Context, onProgress: (String) -> Unit): Boolean = withContext(Dispatchers.IO) {
        try {
            onProgress("Obteniendo campeones...")

            val campeones = obtenerCampeones()
            if (campeones.isEmpty()) {
                onProgress("Error: No se encontraron campeones en $CHAMPIONS_URL.")
                return@withContext false
            }

            onProgress("Campeones encontrados: ${campeones.size}")

            val todasLasBuilds = mutableListOf<BuildEntry>()

            for ((index, campeon) in campeones.withIndex()) {
                val numero = index + 1
                onProgress("[$numero/${campeones.size}] Procesando ${campeon.nombre}...")

                val builds = obtenerBuilds(campeon)
                todasLasBuilds.addAll(builds)

                // Pausa respetuosa para no saturar
                delay(400)
            }

            // Eliminar duplicados por build_url
            val unicas = mutableMapOf<String, BuildEntry>()
            for (build in todasLasBuilds) {
                unicas[build.buildUrl] = build
            }
            val buildsUnicas = unicas.values.toList()

            onProgress("Total de builds encontradas: ${buildsUnicas.size}. Guardando archivos...")

            // 1. JSON
            saveJsonFile(context, buildsUnicas)

            // 2. CSV
            saveCsvFile(context, buildsUnicas)

            // 3. TXT
            saveTxtFile(context, buildsUnicas)

            onProgress("¡Completado con éxito! Archivos creados en Descargas.")
            delay(1500)
            return@withContext true

        } catch (e: Exception) {
            e.printStackTrace()
            onProgress("Error en ejecución: ${e.message}")
            delay(2500)
            return@withContext false
        }
    }

    private fun saveJsonFile(context: Context, builds: List<BuildEntry>) {
        val jsonArray = JSONArray()
        for (build in builds) {
            val obj = JSONObject()
            obj.put("champion", build.champion)
            obj.put("build_name", build.buildName)
            obj.put("build_url", build.buildUrl)
            jsonArray.put(obj)
        }

        val jsonString = jsonArray.toString(2)
        writeToDownloads(context, "bestbuildwr_builds.json", "application/json", jsonString.toByteArray())
    }

    private fun saveCsvFile(context: Context, builds: List<BuildEntry>) {
        val builder = StringBuilder()
        builder.append("champion,build_name,build_url\n")

        for (build in builds) {
            val cName = escapeCsv(build.champion)
            val bName = escapeCsv(build.buildName)
            val bUrl = escapeCsv(build.buildUrl)
            builder.append("$cName,$bName,$bUrl\n")
        }

        writeToDownloads(context, "bestbuildwr_builds.csv", "text/csv", builder.toString().toByteArray())
    }

    private fun saveTxtFile(context: Context, builds: List<BuildEntry>) {
        val builder = StringBuilder()
        for (build in builds) {
            builder.append("${build.champion} | ${build.buildUrl}\n")
        }

        writeToDownloads(context, "bestbuildwr_builds.txt", "text/plain", builder.toString().toByteArray())
    }

    private fun writeToDownloads(context: Context, fileName: String, mimeType: String, data: ByteArray) {
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }
        val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
        if (uri != null) {
            resolver.openOutputStream(uri)?.use { outputStream ->
                outputStream.write(data)
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
}
