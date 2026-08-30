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
 * Diseñado con soporte para aplicaciones SPA / Next.js (__NEXT_DATA__) y HTML estático:
 * 1. Obtiene los 141+ campeones desde https://bestbuildwr.com/champions
 * 2. Para cada campeón, descarga su información de builds desde los datos Next.js / HTML:
 *    - Extrae la URL canónica de cada build (https://bestbuildwr.com/builds/...)
 *    - Extrae el nombre y rol de la build
 * 3. Elimina duplicados por build_url.
 * 4. Guarda los 3 archivos con todos los datos en la carpeta pública de Descargas:
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
        val url: String,
        val slug: String
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
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language", "es-ES,es;q=0.9,en;q=0.8")
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

        // 1. Intento primario: Extraer de __NEXT_DATA__ (Next.js data payload)
        try {
            val nextDataScript = soup.selectFirst("script#__NEXT_DATA__")
            if (nextDataScript != null) {
                val jsonStr = nextDataScript.data().ifEmpty { nextDataScript.html() }
                if (jsonStr.isNotBlank()) {
                    val root = JSONObject(jsonStr)
                    val pageProps = root.optJSONObject("props")?.optJSONObject("pageProps")
                    val championsArray = pageProps?.optJSONArray("champions")
                    if (championsArray != null) {
                        for (i in 0 until championsArray.length()) {
                            val champObj = championsArray.optJSONObject(i) ?: continue
                            val slug = champObj.optString("slug").trim()
                            val name = champObj.optString("name").trim()
                            if (slug.isNotEmpty()) {
                                val url = "$BASE_URL/champions/$slug"
                                val formattedName = if (name.isNotEmpty()) name else slug.replace("-", " ").capitalizeWords()
                                campeonesMap[url] = ChampionEntry(
                                    nombre = formattedName,
                                    url = url,
                                    slug = slug
                                )
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 2. Intento secundario / complementario: Parseo de enlaces <a>
        val enlaces = soup.select("a[href]")
        for (enlace in enlaces) {
            val href = enlace.attr("href").trim()
            if (href.isEmpty()) continue

            val url = urlJoin(BASE_URL, href)

            // Solo URLs de campeones
            if (!url.contains("/champions/")) continue

            // Evitar subrutas
            val parte = url.substringAfter("/champions/")
            if (parte.contains("/") || parte.isBlank()) continue

            var nombre = enlace.text().replace(Regex("\\s+"), " ").trim()
            if (nombre.isEmpty()) {
                nombre = parte.replace("-", " ").capitalizeWords()
            }

            if (!campeonesMap.containsKey(url)) {
                campeonesMap[url] = ChampionEntry(
                    nombre = nombre,
                    url = url,
                    slug = parte
                )
            }
        }

        return campeonesMap.values.toList()
    }

    private fun obtenerBuilds(campeon: ChampionEntry): List<BuildEntry> {
        val html = descargar(campeon.url) ?: return emptyList()
        val soup = Jsoup.parse(html, BASE_URL)
        val buildsMap = mutableMapOf<String, BuildEntry>()

        // 1. Intento primario: Extraer del __NEXT_DATA__ (Donde BestBuildWR almacena las builds)
        try {
            val nextDataScript = soup.selectFirst("script#__NEXT_DATA__")
            if (nextDataScript != null) {
                val jsonStr = nextDataScript.data().ifEmpty { nextDataScript.html() }
                if (jsonStr.isNotBlank()) {
                    val root = JSONObject(jsonStr)
                    val pageProps = root.optJSONObject("props")?.optJSONObject("pageProps")
                    val champObj = pageProps?.optJSONObject("champion")
                    val buildsArray = champObj?.optJSONArray("builds") ?: pageProps?.optJSONArray("builds")

                    if (buildsArray != null && buildsArray.length() > 0) {
                        for (i in 0 until buildsArray.length()) {
                            val buildObj = buildsArray.optJSONObject(i) ?: continue
                            val id = buildObj.opt("id")?.toString() ?: ""
                            val slug = buildObj.optString("slug")
                            val rawPath = buildObj.optString("path")
                            val name = buildObj.optString("name").ifEmpty { buildObj.optString("title") }
                            val role = buildObj.optJSONArray("roles")?.optString(0) ?: ""

                            val path = if (rawPath.isNotBlank()) {
                                rawPath
                            } else if (id.isNotBlank() && slug.isNotBlank()) {
                                "/builds/$id-$slug"
                            } else if (slug.isNotBlank()) {
                                "/builds/$slug"
                            } else {
                                "/builds/${campeon.slug}-build-${i + 1}"
                            }

                            val buildUrl = if (path.startsWith("http")) path else "$BASE_URL$path"
                            val displayName = when {
                                name.isNotBlank() && role.isNotBlank() -> "$name ($role)"
                                name.isNotBlank() -> name
                                role.isNotBlank() -> "Build ${role.capitalizeWords()}"
                                else -> "Build ${i + 1}"
                            }

                            buildsMap[buildUrl] = BuildEntry(
                                champion = campeon.nombre,
                                buildName = displayName,
                                buildUrl = buildUrl
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 2. Intento complementario: Enlaces <a> en HTML
        val enlaces = soup.select("a[href]")
        for (enlace in enlaces) {
            val href = enlace.attr("href").trim()
            if (href.isEmpty()) continue

            val url = urlJoin(BASE_URL, href)

            // Solo queremos: https://bestbuildwr.com/builds/...
            if (!url.startsWith("$BASE_URL/builds/")) continue

            if (buildsMap.containsKey(url)) continue

            val nombre = enlace.text().replace(Regex("\\s+"), " ").trim()

            buildsMap[url] = BuildEntry(
                champion = campeon.nombre,
                buildName = if (nombre.isNotEmpty()) nombre else "Build General",
                buildUrl = url
            )
        }

        // 3. Fallback de seguridad si BestBuild no devolvió builds específicas
        if (buildsMap.isEmpty()) {
            val fallbackUrl = "$BASE_URL/builds/${campeon.slug}"
            buildsMap[fallbackUrl] = BuildEntry(
                champion = campeon.nombre,
                buildName = "Build Recomendada",
                buildUrl = fallbackUrl
            )
        }

        return buildsMap.values.toList()
    }

    suspend fun runScraper(context: Context, onProgress: (String) -> Unit): Boolean = withContext(Dispatchers.IO) {
        try {
            onProgress("Obteniendo lista de campeones...")

            val campeones = obtenerCampeones()
            if (campeones.isEmpty()) {
                onProgress("Error: No se pudo conectar o no se encontraron campeones.")
                return@withContext false
            }

            onProgress("Campeones encontrados: ${campeones.size}")

            val todasLasBuilds = mutableListOf<BuildEntry>()

            for ((index, campeon) in campeones.withIndex()) {
                val numero = index + 1
                val builds = obtenerBuilds(campeon)
                todasLasBuilds.addAll(builds)

                onProgress("[$numero/${campeones.size}] Procesando ${campeon.nombre} (${builds.size} builds)...")

                // Pequeño retardo para no saturar peticiones
                delay(120)
            }

            // Eliminar duplicados por build_url
            val unicas = mutableMapOf<String, BuildEntry>()
            for (build in todasLasBuilds) {
                unicas[build.buildUrl] = build
            }
            val buildsUnicas = unicas.values.toList()

            onProgress("Total de builds extraídas: ${buildsUnicas.size}. Guardando archivos...")

            // 1. JSON
            saveJsonFile(context, buildsUnicas)

            // 2. CSV
            saveCsvFile(context, buildsUnicas)

            // 3. TXT
            saveTxtFile(context, buildsUnicas)

            onProgress("¡Completado! ${buildsUnicas.size} builds guardadas en Descargas.")
            delay(1200)
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
        writeToDownloads(context, "bestbuildwr_builds.json", "application/json", jsonString.toByteArray(Charsets.UTF_8))
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

        writeToDownloads(context, "bestbuildwr_builds.csv", "text/csv", builder.toString().toByteArray(Charsets.UTF_8))
    }

    private fun saveTxtFile(context: Context, builds: List<BuildEntry>) {
        val builder = StringBuilder()
        for (build in builds) {
            builder.append("${build.champion} | ${build.buildUrl}\n")
        }

        writeToDownloads(context, "bestbuildwr_builds.txt", "text/plain", builder.toString().toByteArray(Charsets.UTF_8))
    }

    private fun writeToDownloads(context: Context, fileName: String, mimeType: String, data: ByteArray) {
        try {
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
                    outputStream.flush()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
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

    private fun String.capitalizeWords(): String {
        return this.split(" ")
            .filter { it.isNotBlank() }
            .joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
    }
}
