package com.example.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.tasks.await
import com.google.firebase.storage.FirebaseStorage
import okhttp3.OkHttpClient
import okhttp3.Request
import android.media.MediaMetadataRetriever

object NoticeMediaStorageManager {
    private const val TAG = "NoticeMediaStorage"
    private const val MEDIA_DIR = "notice_media"
    private const val VIDEO_CACHE_DIR = "notice_video_cache"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    /**
     * Normaliza URLs comunes de videos para permitir su descarga y reproducción directa
     */
    fun normalizeVideoUrl(rawUrl: String): String {
        val trimmed = rawUrl.trim()
        if (trimmed.isBlank()) return trimmed

        // Google Drive: convertir enlaces compartidos a descarga directa
        val driveMatch = Regex("drive\\.google\\.com/file/d/([a-zA-Z0-9_-]+)").find(trimmed)
            ?: Regex("drive\\.google\\.com/open\\?id=([a-zA-Z0-9_-]+)").find(trimmed)
        if (driveMatch != null) {
            val fileId = driveMatch.groupValues[1]
            return "https://drive.google.com/uc?export=download&id=$fileId"
        }

        // Dropbox: cambiar dl=0 a dl=1 o raw=1 para obtener el flujo binario
        if (trimmed.contains("dropbox.com", ignoreCase = true)) {
            if (trimmed.contains("dl=0")) {
                return trimmed.replace("dl=0", "dl=1")
            }
            if (!trimmed.contains("dl=1") && !trimmed.contains("raw=1")) {
                val sep = if (trimmed.contains("?")) "&" else "?"
                return "$trimmed${sep}dl=1"
            }
        }

        return trimmed
    }

    /**
     * Obtiene el archivo de video en caché local si ya fue descargado previamente y es válido.
     */
    fun getCachedVideoFile(context: Context, url: String): File? {
        try {
            val normalized = normalizeVideoUrl(url)
            val dir = File(context.cacheDir, VIDEO_CACHE_DIR)
            if (!dir.exists()) return null
            val key = "vid_" + normalized.hashCode().toString().replace("-", "n") + ".mp4"
            val file = File(dir, key)
            if (file.exists() && file.length() > 5000) {
                return file
            }
        } catch (_: Exception) {}
        return null
    }

    /**
     * Descarga y almacena en caché un video en segundo plano para reproducción instantánea y offline
     * utilizando OkHttp con redirecciones automáticas y User-Agent de navegador móvil.
     */
    suspend fun cacheVideoFromUrl(context: Context, url: String): File? = withContext(Dispatchers.IO) {
        val normalized = normalizeVideoUrl(url)
        if (!normalized.startsWith("http://", ignoreCase = true) && !normalized.startsWith("https://", ignoreCase = true)) {
            return@withContext null
        }
        try {
            val dir = File(context.cacheDir, VIDEO_CACHE_DIR)
            if (!dir.exists()) dir.mkdirs()
            val key = "vid_" + normalized.hashCode().toString().replace("-", "n") + ".mp4"
            val file = File(dir, key)
            if (file.exists() && file.length() > 5000) {
                return@withContext file
            }

            val tempFile = File(dir, "$key.tmp")
            val request = Request.Builder()
                .url(normalized)
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.6668.70 Mobile Safari/537.36")
                .header("Accept", "*/*")
                .header("Connection", "keep-alive")
                .build()

            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                val body = response.body
                if (body != null) {
                    val contentType = response.header("Content-Type", "") ?: ""
                    // Si el servidor devuelve HTML en vez de archivo de video (ej. página web o error), no guardar como mp4
                    if (contentType.contains("text/html", ignoreCase = true)) {
                        Log.w(TAG, "La URL devolvió una página HTML en lugar de un stream de video: $contentType")
                        response.close()
                        return@withContext null
                    }

                    body.byteStream().use { input ->
                        FileOutputStream(tempFile).use { output ->
                            input.copyTo(output)
                        }
                    }

                    if (tempFile.exists() && tempFile.length() > 5000) {
                        // Verificar que los primeros bytes no sean etiquetas HTML
                        val preview = ByteArray(64)
                        val readBytes = tempFile.inputStream().use { it.read(preview) }
                        val prefix = if (readBytes > 0) String(preview, 0, readBytes).trim().lowercase() else ""
                        if (prefix.startsWith("<!doctype") || prefix.startsWith("<html")) {
                            tempFile.delete()
                            Log.w(TAG, "El archivo descargado contiene HTML, descartando del caché de video.")
                            return@withContext null
                        }

                        tempFile.renameTo(file)
                        Log.d(TAG, "Video descargado y almacenado en caché: ${file.absolutePath} (${file.length() / 1024} KB)")
                        return@withContext file
                    }
                }
            } else {
                Log.w(TAG, "Fallo HTTP al descargar video: ${response.code} en $normalized")
            }
        } catch (e: Exception) {
            Log.w(TAG, "No se pudo almacenar en caché el video: ${e.message}")
        }
        null
    }

    /**
     * Guarda un video codificado en Base64 data:video/... en un archivo temporal de caché para VideoView.
     */
    fun saveBase64VideoToCache(context: Context, dataUri: String): File? {
        return try {
            if (!dataUri.startsWith("data:video/")) return null
            val base64Data = dataUri.substringAfter("base64,")
            val bytes = Base64.decode(base64Data, Base64.DEFAULT)
            val dir = File(context.cacheDir, VIDEO_CACHE_DIR)
            if (!dir.exists()) dir.mkdirs()
            val key = "b64vid_" + dataUri.hashCode().toString().replace("-", "n") + ".mp4"
            val file = File(dir, key)
            if (!file.exists() || file.length() != bytes.size.toLong()) {
                FileOutputStream(file).use { it.write(bytes) }
            }
            file
        } catch (e: Exception) {
            Log.w(TAG, "Error procesando video Base64: ${e.message}")
            null
        }
    }

    /**
     * Guarda el archivo localmente en el almacenamiento privado del app
     * para que jamás caduque ni dependa de permisos de ContentResolver.
     * Retorna la ruta con esquema file://
     */
    suspend fun saveMediaToInternalStorage(context: Context, uri: Uri, isVideo: Boolean): String = withContext(Dispatchers.IO) {
        try {
            val dir = File(context.filesDir, MEDIA_DIR)
            if (!dir.exists()) dir.mkdirs()

            val ext = if (isVideo) "mp4" else "jpg"
            val targetFile = File(dir, "media_${System.currentTimeMillis()}_${UUID.randomUUID().toString().take(6)}.$ext")

            context.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(targetFile).use { output ->
                    input.copyTo(output)
                }
            }
            Log.d(TAG, "Media guardada permanentemente en almacenamiento interno: ${targetFile.absolutePath}")
            "file://${targetFile.absolutePath}"
        } catch (e: Exception) {
            Log.e(TAG, "Error guardando en almacenamiento interno: ${e.message}")
            uri.toString()
        }
    }

    /**
     * Convierte una imagen de la galería o archivo a un Data URL Base64 optimizado (data:image/jpeg;base64,...).
     * Escala la imagen para un peso ligero (~30KB-70KB) que se sincroniza perfectamente
     * en Firebase Firestore a través de TODOS los dispositivos (Multidispositivo) sin volverse negra.
     */
    
    /**
     * Determina con precisión si una URI seleccionada de la galería o explorador es un archivo de video.
     */
    fun isUriVideo(context: Context, uri: Uri): Boolean {
        try {
            val mime = context.contentResolver.getType(uri)
            if (mime?.startsWith("video/", ignoreCase = true) == true) return true
            if (mime?.startsWith("image/", ignoreCase = true) == true) return false
        } catch (_: Exception) {}

        val uriStr = uri.toString().lowercase()
        val videoExtensions = listOf(".mp4", ".mkv", ".webm", ".mov", ".3gp", ".avi", ".m4v", ".ts")
        if (videoExtensions.any { uriStr.endsWith(it) || uriStr.contains(it) }) return true

        return try {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(context, uri)
            val hasVideo = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_VIDEO)
            retriever.release()
            hasVideo == "yes"
        } catch (_: Exception) {
            false
        }
    }

    /**
     * Intenta subir un archivo de video al almacenamiento remoto en la nube.
     * Si no está disponible o falla, retorna null de forma segura sin arrojar excepciones.
     */
    suspend fun tryUploadVideoToCloud(context: Context, uri: Uri): String? = withContext(Dispatchers.IO) {
        try {
            val storage = try {
                FirebaseStorage.getInstance()
            } catch (_: Exception) {
                try {
                    FirebaseStorage.getInstance("gs://wild-rift-drafting.firebasestorage.app")
                } catch (_: Exception) {
                    null
                }
            } ?: return@withContext null

            val storageRef = storage.reference
            val filename = "notice_videos/${System.currentTimeMillis()}_${UUID.randomUUID().toString().take(6)}.mp4"
            val videoRef = storageRef.child(filename)
            videoRef.putFile(uri).await()
            val downloadUrl = videoRef.downloadUrl.await()
            val urlString = downloadUrl.toString()
            Log.d(TAG, "Video subido exitosamente a la nube: $urlString")

            // Guardar copia local inmediata en caché para reproducción instantánea sin esperar red
            try {
                val dir = File(context.cacheDir, VIDEO_CACHE_DIR)
                if (!dir.exists()) dir.mkdirs()
                val key = "vid_" + urlString.hashCode().toString().replace("-", "n") + ".mp4"
                val file = File(dir, key)
                context.contentResolver.openInputStream(uri)?.use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
            } catch (_: Exception) {}

            urlString
        } catch (e: Exception) {
            Log.w(TAG, "Almacenamiento remoto no disponible para video (${e.message}), se utilizará almacenamiento local.")
            null
        }
    }

    /**
     * Guarda el video de forma blindada: primero lo almacena permanentemente en el almacenamiento
     * interno privado de la aplicación para garantizar que NUNCA falle ni se pierda.
     * Luego intenta sincronizarlo en la nube si el servicio remoto está disponible.
     */
    suspend fun uploadOrSaveVideo(context: Context, uri: Uri): String = withContext(Dispatchers.IO) {
        // 1. Guardar siempre en almacenamiento interno local permanente
        val localPath = saveMediaToInternalStorage(context, uri, isVideo = true)

        // 2. Intentar subir al servicio en la nube si está activo
        val cloudUrl = tryUploadVideoToCloud(context, uri)
        if (!cloudUrl.isNullOrBlank()) {
            return@withContext cloudUrl
        }

        // 3. Si la nube no está habilitada o falla, retornar la ruta local permanente file://
        localPath
    }

    suspend fun uploadVideoToCloud(context: Context, uri: Uri): String = withContext(Dispatchers.IO) {
        uploadOrSaveVideo(context, uri)
    }

    suspend fun convertImageToCloudDataUrl(context: Context, uri: Uri): String = withContext(Dispatchers.IO) {
        try {
            // 1. Guardar primero copia permanente en disco local
            val localPath = saveMediaToInternalStorage(context, uri, isVideo = false)

            // 2. Leer los bytes para comprimir y codificar a Base64
            val localFile = File(Uri.parse(localPath).path ?: "")
            val inputStream: InputStream = if (localFile.exists()) {
                localFile.inputStream()
            } else {
                context.contentResolver.openInputStream(uri) ?: return@withContext localPath
            }

            val originalBitmap = inputStream.use { BitmapFactory.decodeStream(it) } ?: return@withContext localPath

            // Escalar proporcionalmente si excede 1000px
            val maxDim = 1000
            val width = originalBitmap.width
            val height = originalBitmap.height
            val scaledBitmap = if (width > maxDim || height > maxDim) {
                val ratio = width.toFloat() / height.toFloat()
                val targetW: Int
                val targetH: Int
                if (width > height) {
                    targetW = maxDim
                    targetH = (maxDim / ratio).toInt().coerceAtLeast(1)
                } else {
                    targetH = maxDim
                    targetW = (maxDim * ratio).toInt().coerceAtLeast(1)
                }
                Bitmap.createScaledBitmap(originalBitmap, targetW, targetH, true)
            } else {
                originalBitmap
            }

            val baos = ByteArrayOutputStream()
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)
            val bytes = baos.toByteArray()
            val base64String = Base64.encodeToString(bytes, Base64.NO_WRAP)
            Log.d(TAG, "Imagen convertida a Data URL Base64 (${bytes.size / 1024} KB) para sincronización multidispositivo")
            "data:image/jpeg;base64,$base64String"
        } catch (e: Exception) {
            Log.e(TAG, "Error convirtiendo imagen a Cloud Data URL: ${e.message}")
            uri.toString()
        }
    }

    /**
     * Decodifica un Data URL Base64 a ByteArray para que Coil o ImageView lo cargue directamente
     */
    fun decodeDataUriToBytes(dataUri: String): ByteArray? {
        return try {
            if (dataUri.startsWith("data:image/")) {
                val base64Data = dataUri.substringAfter("base64,")
                Base64.decode(base64Data, Base64.DEFAULT)
            } else null
        } catch (e: Exception) {
            Log.w(TAG, "Error decodificando Data URL: ${e.message}")
            null
        }
    }
}
