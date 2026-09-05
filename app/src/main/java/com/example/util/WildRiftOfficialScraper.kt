package com.example.util

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.net.URLDecoder
import java.util.regex.Pattern

/**
 * Scraper nativo de Wild Rift Champions & Image Downloader adaptado para Android y el Panel de Administración.
 * Basado en el script de Python proporcionado por el usuario.
 *
 * Características:
 * 1. Base URL: https://wildrift.leagueoflegends.com/es-es/champions/
 * 2. Descubrimiento doble:
 *    - Tags <a> con regex /es-es/champions/([^/]+)/?$
 *    - Búsqueda alternativa directa por regex en todo el HTML
 * 3. Extracción profunda de imágenes: Meta tags (og:image, twitter:image), <img> (src, srcset, data-src),
 *    <source> (srcset), CSS url(...) inline y expresiones regulares sobre bloques <script>
 * 4. Algoritmo de Scoring / Puntuación:
 *    - Coincidencia con nombre (+100) y slug (+100)
 *    - Indicadores positivos: splash (+90), portrait (+80), champion (+50), tile (+40), loading (+30)
 *    - Penalizaciones: icon (-80), spell/ability/passive (-100), logo/favicon (-150)
 * 5. Descarga de imagen binaria de cada campeón directamente en la carpeta:
 *    - "WildRift_Imagenes/" (en Downloads de Android mediante MediaStore y File Fallback)
 * 6. Generación de informes:
 *    - resultado.csv (campeon, slug, url_imagen, archivo, estado)
 *    - urls_imagenes.txt (campeon | url_imagen)
 *    - resumen.txt (resumen de total, descargadas, errores)
 *    - wildrift_champions_assets.json (para integración nativa en la app)
 */
object WildRiftOfficialScraper {
    private const val BASE_URL = "https://wildrift.leagueoflegends.com"
    private const val CHAMPIONS_URL = "$BASE_URL/es-es/champions/"
    private const val OUTPUT_DIR_NAME = "WildRift_Imagenes"
    private const val DELAY_MS = 350L
    private const val TIMEOUT_MS = 30000

    private const val USER_AGENT = "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Mobile Safari/537.36"

    data class ChampionEntry(
        val nombre: String,
        val slug: String,
        val pagina: String,
        val cardImageUrl: String? = null
    )

    data class ChampionProcessResult(
        val campeon: String,
        val slug: String,
        val urlImagen: String,
        val archivo: String,
        val estado: String // "OK", "ERROR_PAGINA", "SIN_IMAGEN", "ERROR_DESCARGA", "YA_EXISTE"
    )

    private fun limpiarUrl(url: String?): String? {
        if (url.isNullOrBlank()) return null
        var trimmed = url.trim()
        if (trimmed.startsWith("//")) {
            trimmed = "https:$trimmed"
        }
        return try {
            val base = URL(BASE_URL)
            URL(base, trimmed).toString()
        } catch (e: Exception) {
            if (trimmed.startsWith("http")) trimmed else "$BASE_URL/$trimmed".replace("//", "/")
        }
    }

    private fun descargarHtml(url: String): String? {
        return try {
            val response = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                .header("Accept-Language", "es-ES,es;q=0.9,en;q=0.8")
                .timeout(TIMEOUT_MS)
                .ignoreHttpErrors(true)
                .followRedirects(true)
                .execute()
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun nombreSeguro(nombre: String): String {
        val sinEspeciales = nombre.replace(Regex("[\\\\/:*?\"<>|]"), "")
        val sinEspaciosExtra = sinEspeciales.replace(Regex("\\s+"), " ")
        return sinEspaciosExtra.trim()
    }

    private fun unquote(str: String): String {
        return try {
            URLDecoder.decode(str, "UTF-8")
        } catch (e: Exception) {
            str
        }
    }

    private fun esBannerInvalido(url: String): Boolean {
        val u = unquote(url).lowercase()
        return u.contains("5120x480") ||
               u.contains("76190b66cbd804a79bb1a709498b5af75f36a735") ||
               u.contains("20aeb6046d11ff197c4eeb93150853ddf2ff14c0") ||
               u.contains("content_organization") ||
               u.contains("128x128") ||
               u.contains("96x96") ||
               u.contains("riotbar") ||
               u.contains("footer") ||
               u.contains("favicon")
    }

    private fun esImagen(url: String?): Boolean {
        if (url.isNullOrBlank()) return false
        if (esBannerInvalido(url)) return false
        val u = unquote(url).lowercase()
        val extensiones = listOf(".jpg", ".jpeg", ".png", ".webp", ".avif")
        return extensiones.any { u.contains(it) }
    }

    private fun extensionImagen(url: String): String {
        val path = try {
            URL(url).path.lowercase()
        } catch (e: Exception) {
            url.lowercase()
        }
        return when {
            path.endsWith(".jpeg") -> ".jpeg"
            path.endsWith(".png") -> ".png"
            path.endsWith(".webp") -> ".webp"
            path.endsWith(".avif") -> ".avif"
            else -> ".jpg"
        }
    }

    fun obtenerCampeones(): List<ChampionEntry> {
        val html = descargarHtml(CHAMPIONS_URL) ?: return emptyList()
        val soup = Jsoup.parse(html, BASE_URL)
        val campeonesMap = mutableMapOf<String, ChampionEntry>()

        // 1. Extraer directamente desde __NEXT_DATA__ (Next.js Sanity CMS oficial)
        try {
            val nextDataPattern = Pattern.compile("<script id=\"__NEXT_DATA__\"[^>]*>(.*?)</script>", Pattern.DOTALL)
            val matcherNext = nextDataPattern.matcher(html)
            if (matcherNext.find()) {
                val jsonStr = matcherNext.group(1)
                val root = JSONObject(jsonStr)
                val blades = root.optJSONObject("props")
                    ?.optJSONObject("pageProps")
                    ?.optJSONObject("page")
                    ?.optJSONArray("blades")
                if (blades != null) {
                    for (i in 0 until blades.length()) {
                        val blade = blades.optJSONObject(i) ?: continue
                        if (blade.optString("type") == "characterCardGrid") {
                            val items = blade.optJSONArray("items") ?: continue
                            for (j in 0 until items.length()) {
                                val item = items.optJSONObject(j) ?: continue
                                val title = item.optString("title").trim()
                                val media = item.optJSONObject("media")
                                val cardImg = media?.optString("url")?.ifBlank { null }
                                val actionUrl = item.optJSONObject("action")
                                    ?.optJSONObject("payload")
                                    ?.optString("url") ?: ""
                                val cleanAction = actionUrl.trim('/')
                                val slug = cleanAction.split('/').lastOrNull()?.lowercase() ?: ""
                                if (slug.isNotBlank() && !slug.equals("champions", ignoreCase = true)) {
                                    val fullUrl = if (actionUrl.startsWith("http")) actionUrl else "$BASE_URL/$cleanAction/"
                                    val champName = if (title.isNotBlank()) title else slug.replace("-", " ")
                                        .split(" ")
                                        .filter { it.isNotBlank() }
                                        .joinToString(" ") { part -> part.replaceFirstChar { it.uppercase() } }

                                    campeonesMap[slug] = ChampionEntry(
                                        nombre = champName,
                                        slug = slug,
                                        pagina = fullUrl,
                                        cardImageUrl = cardImg
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 2. Extraer mediante etiquetas <a> como fallback/complemento
        val slugPattern = Pattern.compile("^/es-es/champions/([^/]+)/?$")

        for (enlace in soup.select("a[href]")) {
            val href = limpiarUrl(enlace.attr("href")) ?: continue
            try {
                val path = URL(href).path
                val matcher = slugPattern.matcher(path)
                if (matcher.matches()) {
                    val rawSlug = matcher.group(1) ?: continue
                    val slug = rawSlug.lowercase().trim()
                    if (slug.isBlank() || slug.equals("champions", ignoreCase = true)) continue

                    var nombre = enlace.text().trim()
                    if (nombre.isBlank()) {
                        nombre = slug.replace("-", " ")
                            .split(" ")
                            .filter { it.isNotBlank() }
                            .joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
                    }

                    if (!campeonesMap.containsKey(slug)) {
                        campeonesMap[slug] = ChampionEntry(
                            nombre = nombre,
                            slug = slug,
                            pagina = href
                        )
                    }
                }
            } catch (e: Exception) {
                // Ignore parse errors
            }
        }

        // 3. Método alternativo: Buscar patrones directamente en todo el HTML
        val patternRegex = Pattern.compile("/es-es/champions/([a-zA-Z0-9_-]+)/?")
        val matcherHtml = patternRegex.matcher(html)
        while (matcherHtml.find()) {
            val foundSlug = matcherHtml.group(1)?.lowercase()?.trim() ?: continue
            if (foundSlug.isBlank() || foundSlug.equals("champions", ignoreCase = true)) continue

            if (!campeonesMap.containsKey(foundSlug)) {
                val nombre = foundSlug.replace("-", " ")
                    .split(" ")
                    .filter { it.isNotBlank() }
                    .joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }

                campeonesMap[foundSlug] = ChampionEntry(
                    nombre = nombre,
                    slug = foundSlug,
                    pagina = "$BASE_URL/es-es/champions/$foundSlug/"
                )
            }
        }

        val lista = campeonesMap.values.toMutableList()
        lista.sortBy { it.slug }
        return lista
    }

    private fun extraerImagenes(html: String): List<String> {
        val soup = Jsoup.parse(html, BASE_URL)
        val candidatos = mutableListOf<String>()

        // 1. Meta tags (Descartar og:image genéricos del sitio)
        for (meta in soup.select("meta")) {
            val prop = (meta.attr("property").ifEmpty { meta.attr("name") }).lowercase()
            if (prop in listOf("og:image", "og:image:url", "twitter:image", "twitter:image:src")) {
                val value = meta.attr("content")
                if (value.isNotBlank() && !esBannerInvalido(value)) candidatos.add(value)
            }
        }

        // 2. IMG
        for (img in soup.select("img")) {
            for (attr in listOf("src", "data-src", "data-original", "data-lazy-src", "data-image", "data-url")) {
                val value = img.attr(attr)
                if (value.isNotBlank()) candidatos.add(value)
            }
            val srcset = img.attr("srcset")
            if (srcset.isNotBlank()) {
                for (part in srcset.split(",")) {
                    val t = part.trim()
                    if (t.isNotEmpty()) candidatos.add(t.split(Regex("\\s+"))[0])
                }
            }
        }

        // 3. SOURCE
        for (source in soup.select("source")) {
            for (attr in listOf("src", "data-src", "data-original")) {
                val value = source.attr(attr)
                if (value.isNotBlank()) candidatos.add(value)
            }
            val srcset = source.attr("srcset")
            if (srcset.isNotBlank()) {
                for (part in srcset.split(",")) {
                    val t = part.trim()
                    if (t.isNotEmpty()) candidatos.add(t.split(Regex("\\s+"))[0])
                }
            }
        }

        // 4. CSS inline style
        val cssUrlPattern = Pattern.compile("url\\([\"']?([^\"')]+)", Pattern.CASE_INSENSITIVE)
        for (elem in soup.select("[style]")) {
            val style = elem.attr("style")
            val m = cssUrlPattern.matcher(style)
            while (m.find()) {
                val f = m.group(1)
                if (!f.isNullOrBlank()) candidatos.add(f)
            }
        }

        // 5. JavaScript en <script>
        val absoluteUrlPattern = Pattern.compile("https?://[^\"'<>\\s\\\\]+")
        val relativeImgPattern = Pattern.compile("[\"']([^\"'<>\\s\\\\]+\\.(?:jpg|jpeg|png|webp|avif)(?:\\?[^\"']*)?)[\"']", Pattern.CASE_INSENSITIVE)

        for (script in soup.select("script")) {
            val texto = script.data().ifEmpty { script.html() }
            if (texto.isBlank()) continue

            val mAbs = absoluteUrlPattern.matcher(texto)
            while (mAbs.find()) {
                candidatos.add(mAbs.group(0))
            }

            val mRel = relativeImgPattern.matcher(texto)
            while (mRel.find()) {
                val f = mRel.group(1)
                if (!f.isNullOrBlank()) candidatos.add(f)
            }
        }

        // Normalizar y filtrar banners no deseados
        val resultado = mutableListOf<String>()
        val vistos = mutableSetOf<String>()

        for (cand in candidatos) {
            val trimmed = cand.trim()
            if (trimmed.isEmpty()) continue

            val url = limpiarUrl(trimmed) ?: continue
            if (!esImagen(url)) continue

            val sinFragmento = url.split("#")[0]
            if (sinFragmento !in vistos) {
                vistos.add(sinFragmento)
                resultado.add(sinFragmento)
            }
        }

        return resultado
    }

    private fun puntuacion(url: String, nombre: String, slug: String): Int {
        if (esBannerInvalido(url)) {
            return -10000 // Descarte absoluto para evitar el banner 5120x480 de 22.34 kB
        }

        val texto = unquote(url.lowercase())
        val nombreNorm = nombre.lowercase().replace(Regex("[^a-z0-9]"), "")
        val slugNorm = slug.lowercase().replace(Regex("[^a-z0-9]"), "")
        val textoNorm = texto.replace(Regex("[^a-z0-9]"), "")

        var puntos = 0

        // Prioridad máxima: Imágenes oficiales de Wild Rift por resolución
        if (texto.contains("1280x720") || texto.contains("1920x1080") || texto.contains("1600x900")) {
            puntos += 600 // Splash Art HD de Wild Rift
        }
        if (texto.contains("285x323") || texto.contains("285x328")) {
            puntos += 400 // Card Portrait oficial
        }
        if (texto.contains("game_data") || texto.contains("game_data_live")) {
            puntos += 150 // Assets de juego oficial vs noticias/artículos
        }

        // Coincidencia con nombre
        if (nombreNorm.isNotEmpty() && textoNorm.contains(nombreNorm)) {
            puntos += 100
        }

        // Coincidencia con slug
        if (slugNorm.isNotEmpty() && textoNorm.contains(slugNorm)) {
            puntos += 100
        }

        // Indicadores de imagen principal
        if (texto.contains("splash")) puntos += 90
        if (texto.contains("portrait")) puntos += 80
        if (texto.contains("champion")) puntos += 50
        if (texto.contains("tile")) puntos += 40
        if (texto.contains("loading")) puntos += 30

        // Penalizaciones
        if (texto.contains("96x96")) puntos -= 200
        if (texto.contains("128x128")) puntos -= 250
        if (texto.contains("icon")) puntos -= 80
        if (texto.contains("spell") || texto.contains("ability") || texto.contains("passive")) puntos -= 120
        if (texto.contains("logo") || texto.contains("favicon")) puntos -= 200
        if (texto.contains("banner") || texto.contains("header")) puntos -= 300

        return puntos
    }

    private fun elegirImagen(urls: List<String>, nombre: String, slug: String): String? {
        if (urls.isEmpty()) return null
        val filtradas = urls.filter { !esBannerInvalido(it) }
        val ordenadas = filtradas.sortedByDescending { puntuacion(it, nombre, slug) }
        return ordenadas.firstOrNull()
    }

    /**
     * Extrae con precisión milimétrica la imagen oficial del campeón:
     * 1. Prioriza el Splash Art oficial 1280x720 de landingMediaCarousel en __NEXT_DATA__
     * 2. Si no, busca cualquier Splash Art oficial 1280x720 en el JSON de Next.js
     * 3. Fallback al Card Portrait oficial (285x323) del catálogo de campeones
     * 4. Si aún no hay, examina candidatos válidos descartando iconos y metadatos
     */
    fun extraerMejorImagenDeCampeon(
        html: String,
        nombre: String,
        slug: String,
        fallbackCardUrl: String?
    ): String? {
        try {
            val nextDataPattern = Pattern.compile("<script id=\"__NEXT_DATA__\"[^>]*>(.*?)</script>", Pattern.DOTALL)
            val matcher = nextDataPattern.matcher(html)
            if (matcher.find()) {
                val jsonStr = matcher.group(1)
                val root = JSONObject(jsonStr)
                val blades = root.optJSONObject("props")
                    ?.optJSONObject("pageProps")
                    ?.optJSONObject("page")
                    ?.optJSONArray("blades")

                if (blades != null) {
                    // 1. Buscar landingMediaCarousel: el primer grupo contiene el Splash Art base HD 1280x720
                    for (i in 0 until blades.length()) {
                        val blade = blades.optJSONObject(i) ?: continue
                        if (blade.optString("type") == "landingMediaCarousel") {
                            val groups = blade.optJSONArray("groups")
                            if (groups != null && groups.length() > 0) {
                                val firstGroup = groups.optJSONObject(0)
                                val mediaUrl = firstGroup?.optJSONObject("content")
                                    ?.optJSONObject("media")
                                    ?.optString("url")
                                if (!mediaUrl.isNullOrBlank() && !esBannerInvalido(mediaUrl)) {
                                    return mediaUrl
                                }
                            }
                        }
                    }

                    // 2. Buscar cualquier imagen 1280x720 en el JSON
                    val splashMatcher = Pattern.compile("https?://cmsassets\\.rgpub\\.io/sanity/images/[^\"'<>\\s\\\\]+1280x720\\.(?:jpg|jpeg|png|webp)", Pattern.CASE_INSENSITIVE).matcher(jsonStr)
                    if (splashMatcher.find()) {
                        val candidate = splashMatcher.group(0)
                        if (!esBannerInvalido(candidate)) {
                            return candidate
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 3. Fallback prioritario al Card Portrait 285x323 del catálogo de Wild Rift
        if (!fallbackCardUrl.isNullOrBlank() && !esBannerInvalido(fallbackCardUrl)) {
            return fallbackCardUrl
        }

        // 4. Extracción general con filtro estricto
        val urls = extraerImagenes(html)
        val elegida = elegirImagen(urls, nombre, slug)
        if (elegida != null && puntuacion(elegida, nombre, slug) > 0 && !esBannerInvalido(elegida)) {
            return elegida
        }

        return fallbackCardUrl?.takeIf { !esBannerInvalido(it) } ?: elegida
    }

    /**
     * Descarga la imagen en binario y la guarda en la carpeta "WildRift_Imagenes" dentro de Descargas.
     * Soporta Android 10+ (Scoped Storage / MediaStore) y versiones anteriores / fallback local.
     * Realiza limpieza proactiva de archivos corruptos previos (banners de 22.34 kB).
     */
    private fun guardarImagen(context: Context, nombre: String, url: String): String? {
        val seguro = nombreSeguro(nombre)
        val extension = extensionImagen(url)
        val fileName = "$seguro$extension"
        val relativeSubDir = "${Environment.DIRECTORY_DOWNLOADS}/$OUTPUT_DIR_NAME"

        try {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val outputFolder = File(downloadsDir, OUTPUT_DIR_NAME)
            if (!outputFolder.exists()) {
                outputFolder.mkdirs()
            }

            // LIMPIEZA ACTIVA DE ARCHIVOS CORRUPTOS PREVIOS:
            // Elimina archivos erróneos de ejecuciones anteriores (banner/icono genérico de ~22.34 kB o thumbnails)
            val posiblesArchivosCorruptos = listOf(
                File(outputFolder, "$seguro.png"),
                File(outputFolder, "$seguro.jpg"),
                File(outputFolder, "$seguro.jpeg"),
                File(outputFolder, "$seguro.webp")
            )
            for (f in posiblesArchivosCorruptos) {
                if (f.exists()) {
                    val len = f.length()
                    // Si mide menos de 28 KB o es un thumbnail erróneo, borrarlo para descargar el arte HD
                    if (len < 28000L || (f.name.endsWith(".png") && extension != ".png" && len < 35000L)) {
                        try {
                            f.delete()
                        } catch (_: Exception) {}
                    }
                }
            }

            val localTargetFile = File(outputFolder, fileName)
            if (localTargetFile.exists() && localTargetFile.length() > 28000L) {
                // Ya existe y es una imagen legítima (> 28KB)
                return localTargetFile.absolutePath
            }

            // Descargar bytes de la imagen
            val response = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(TIMEOUT_MS * 2)
                .ignoreContentType(true)
                .maxBodySize(20 * 1024 * 1024) // 20 MB max
                .execute()

            val bytes = response.bodyAsBytes()
            if (bytes.isEmpty()) return null

            // Si tenemos acceso directo de escritura en carpeta pública
            if (outputFolder.canWrite()) {
                FileOutputStream(localTargetFile).use { fos ->
                    fos.write(bytes)
                    fos.flush()
                }
                return localTargetFile.absolutePath
            }

            // En Android 10+ utilizar MediaStore para escribir en Download/WildRift_Imagenes
            val resolver = context.contentResolver
            val mimeType = when (extension) {
                ".png" -> "image/png"
                ".webp" -> "image/webp"
                ".avif" -> "image/avif"
                else -> "image/jpeg"
            }

            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.MediaColumns.RELATIVE_PATH, relativeSubDir)
                }
            }

            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            } else {
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            }

            if (uri != null) {
                resolver.openOutputStream(uri)?.use { os ->
                    os.write(bytes)
                    os.flush()
                }
                return "$relativeSubDir/$fileName"
            }

            // Fallback interno si no se pudo con MediaStore
            val internalImagesDir = File(context.filesDir, OUTPUT_DIR_NAME).apply { mkdirs() }
            val fallbackFile = File(internalImagesDir, fileName)
            FileOutputStream(fallbackFile).use { fos ->
                fos.write(bytes)
                fos.flush()
            }
            return fallbackFile.absolutePath

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    /**
     * Ejecuta el crawler oficial y procesador de imágenes idéntico al script de Python.
     */
    suspend fun runScraper(
        context: Context,
        onProgress: (String) -> Unit
    ): Boolean = withContext(Dispatchers.IO) {
        val startTime = System.currentTimeMillis()
        try {
            onProgress("======================================================================")
            onProgress(" WILD RIFT - IMÁGENES DE CAMPEONES")
            onProgress("======================================================================")
            onProgress("Fuente: $CHAMPIONS_URL")
            onProgress("Carpeta de imágenes: Downloads/$OUTPUT_DIR_NAME/")
            delay(400)

            onProgress("Obteniendo lista oficial de campeones...")
            val campeones = obtenerCampeones()

            if (campeones.isEmpty()) {
                onProgress("ERROR: No se encontraron campeones en la página oficial.")
                return@withContext false
            }

            onProgress("Campeones encontrados: ${campeones.size}")
            delay(600)

            val resultados = mutableListOf<ChampionProcessResult>()

            for ((index, campeon) in campeones.withIndex()) {
                val num = index + 1
                val total = campeones.size
                onProgress("----------------------------------------------------------------------")
                onProgress("[$num/$total] ${campeon.nombre}")
                onProgress("Página: ${campeon.pagina}")

                val html = descargarHtml(campeon.pagina)
                if (html == null) {
                    onProgress("   ERROR: No se pudo descargar la página.")
                    resultados.add(
                        ChampionProcessResult(
                            campeon = campeon.nombre,
                            slug = campeon.slug,
                            urlImagen = "",
                            archivo = "",
                            estado = "ERROR_PAGINA"
                        )
                    )
                    delay(DELAY_MS)
                    continue
                }

                val imagen = extraerMejorImagenDeCampeon(html, campeon.nombre, campeon.slug, campeon.cardImageUrl)
                if (imagen == null) {
                    onProgress("   No se encontró imagen adecuada.")
                    resultados.add(
                        ChampionProcessResult(
                            campeon = campeon.nombre,
                            slug = campeon.slug,
                            urlImagen = "",
                            archivo = "",
                            estado = "SIN_IMAGEN"
                        )
                    )
                    delay(DELAY_MS)
                    continue
                }

                onProgress("   Imagen seleccionada: $imagen")
                val archivoGuardado = guardarImagen(context, campeon.nombre, imagen)

                if (archivoGuardado != null) {
                    onProgress("   Guardada: $archivoGuardado")
                    resultados.add(
                        ChampionProcessResult(
                            campeon = campeon.nombre,
                            slug = campeon.slug,
                            urlImagen = imagen,
                            archivo = archivoGuardado,
                            estado = "OK"
                        )
                    )
                } else {
                    onProgress("   ERROR al guardar la imagen.")
                    resultados.add(
                        ChampionProcessResult(
                            campeon = campeon.nombre,
                            slug = campeon.slug,
                            urlImagen = imagen,
                            archivo = "",
                            estado = "ERROR_DESCARGA"
                        )
                    )
                }

                delay(DELAY_MS)
            }

            // Guardar archivos auxiliares en Download/WildRift_Imagenes
            onProgress("Guardando reportes: resultado.csv, urls_imagenes.txt, resumen.txt...")
            guardarCsv(context, resultados)
            guardarTxt(context, resultados)
            guardarResumen(context, resultados, campeones.size)
            guardarJson(context, resultados)

            val descargadas = resultados.count { it.estado == "OK" }
            val errores = campeones.size - descargadas
            val duracionSec = (System.currentTimeMillis() - startTime) / 1000.0

            onProgress("======================================================================")
            onProgress(" RESULTADO FINAL")
            onProgress("======================================================================")
            onProgress("Campeones encontrados : ${campeones.size}")
            onProgress("Imágenes descargadas  : $descargadas")
            onProgress("Errores               : $errores")
            onProgress(String.format("Tiempo                : %.1f segundos", duracionSec))
            onProgress("Carpeta de imágenes   : Downloads/$OUTPUT_DIR_NAME/")
            onProgress("CSV                   : resultado.csv")
            onProgress("TXT                   : urls_imagenes.txt")
            onProgress("Resumen               : resumen.txt")
            onProgress("======================================================================")

            if (descargadas == campeones.size) {
                onProgress("OK: Se descargó una imagen por cada campeón.")
            } else {
                onProgress("ATENCIÓN: Algunos campeones no pudieron descargarse.")
            }

            delay(1200)
            return@withContext true

        } catch (e: Exception) {
            e.printStackTrace()
            onProgress("ERROR FATAL: ${e.message}")
            delay(2000)
            return@withContext false
        }
    }

    private fun guardarCsv(context: Context, items: List<ChampionProcessResult>) {
        val builder = java.lang.StringBuilder()
        builder.append("campeon,slug,url_imagen,archivo,estado\n")
        for (item in items) {
            val c = escapeCsv(item.campeon)
            val s = escapeCsv(item.slug)
            val u = escapeCsv(item.urlImagen)
            val a = escapeCsv(item.archivo)
            val e = escapeCsv(item.estado)
            builder.append("$c,$s,$u,$a,$e\n")
        }
        writeToOutputDir(context, "resultado.csv", "text/csv", builder.toString().toByteArray(Charsets.UTF_8))
    }

    private fun guardarTxt(context: Context, items: List<ChampionProcessResult>) {
        val builder = java.lang.StringBuilder()
        for (item in items) {
            if (item.urlImagen.isNotBlank()) {
                builder.append("${item.campeon} | ${item.urlImagen}\n")
            }
        }
        writeToOutputDir(context, "urls_imagenes.txt", "text/plain", builder.toString().toByteArray(Charsets.UTF_8))
    }

    private fun guardarResumen(context: Context, items: List<ChampionProcessResult>, totalCampeones: Int) {
        val descargadas = items.count { it.estado == "OK" }
        val errores = totalCampeones - descargadas

        val builder = java.lang.StringBuilder()
        builder.append("WILD RIFT - RESUMEN\n")
        builder.append("==================================================\n\n")
        builder.append("Campeones encontrados: $totalCampeones\n")
        builder.append("Imágenes descargadas: $descargadas\n")
        builder.append("Errores: $errores\n\n")
        builder.append("Carpeta: Downloads/$OUTPUT_DIR_NAME/\n")

        writeToOutputDir(context, "resumen.txt", "text/plain", builder.toString().toByteArray(Charsets.UTF_8))
    }

    private fun guardarJson(context: Context, items: List<ChampionProcessResult>) {
        val array = JSONArray()
        for (item in items) {
            val obj = JSONObject()
            obj.put("campeon", item.campeon)
            obj.put("slug", item.slug)
            obj.put("url_imagen", item.urlImagen)
            obj.put("archivo", item.archivo)
            obj.put("estado", item.estado)
            array.put(obj)
        }
        writeToOutputDir(context, "resultado.json", "application/json", array.toString(2).toByteArray(Charsets.UTF_8))
    }

    private fun writeToOutputDir(context: Context, fileName: String, mimeType: String, data: ByteArray) {
        try {
            val relativePath = "${Environment.DIRECTORY_DOWNLOADS}/$OUTPUT_DIR_NAME"
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val targetDir = File(downloadsDir, OUTPUT_DIR_NAME)
            if (!targetDir.exists()) targetDir.mkdirs()

            val targetFile = File(targetDir, fileName)
            if (targetDir.canWrite()) {
                FileOutputStream(targetFile).use { fos ->
                    fos.write(data)
                    fos.flush()
                }
                return
            }

            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
                }
            }
            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            } else {
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            }
            if (uri != null) {
                resolver.openOutputStream(uri)?.use { os ->
                    os.write(data)
                    os.flush()
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
}
