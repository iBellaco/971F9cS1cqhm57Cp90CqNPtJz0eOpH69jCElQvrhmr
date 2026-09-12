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
import kotlinx.coroutines.tasks.await
import com.google.firebase.storage.FirebaseStorage


object NoticeMediaStorageManager {
    private const val TAG = "NoticeMediaStorage"
    private const val MEDIA_DIR = "notice_media"
    private const val VIDEO_CACHE_DIR = "notice_video_cache"

    /**
     * Obtiene el archivo de video en caché local si ya fue descargado previamente.
     */
    fun getCachedVideoFile(context: Context, url: String): File? {
        try {
            val dir = File(context.cacheDir, VIDEO_CACHE_DIR)
            if (!dir.exists()) return null
            val key = "vid_" + url.hashCode().toString().replace("-", "n") + ".mp4"
            val file = File(dir, key)
            if (file.exists() && file.length() > 5000) {
                return file
            }
        } catch (_: Exception) {}
        return null
    }

    /**
     * Descarga y almacena en caché un video en segundo plano para reproducción instantánea y offline.
     */
    suspend fun cacheVideoFromUrl(context: Context, url: String): File? = withContext(Dispatchers.IO) {
        val trimmed = url.trim()
        if (!trimmed.startsWith("http://", ignoreCase = true) && !trimmed.startsWith("https://", ignoreCase = true)) {
            return@withContext null
        }
        try {
            val dir = File(context.cacheDir, VIDEO_CACHE_DIR)
            if (!dir.exists()) dir.mkdirs()
            val key = "vid_" + trimmed.hashCode().toString().replace("-", "n") + ".mp4"
            val file = File(dir, key)
            if (file.exists() && file.length() > 5000) {
                return@withContext file
            }
            val tempFile = File(dir, "$key.tmp")
            val connection = java.net.URL(trimmed).openConnection() as java.net.HttpURLConnection
            connection.connectTimeout = 12000
            connection.readTimeout = 25000
            connection.instanceFollowRedirects = true
            connection.connect()
            if (connection.responseCode in 200..299) {
                connection.inputStream.use { input ->
                    FileOutputStream(tempFile).use { output ->
                        input.copyTo(output)
                    }
                }
                if (tempFile.exists() && tempFile.length() > 5000) {
                    tempFile.renameTo(file)
                    Log.d(TAG, "Video descargado y almacenado en caché: ${file.absolutePath} (${file.length() / 1024} KB)")
                    return@withContext file
                }
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
    
    suspend fun uploadVideoToCloud(context: Context, uri: Uri): String = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Iniciando subida de video a Firebase Storage...")
            val storageRef = FirebaseStorage.getInstance().reference
            val videoRef = storageRef.child("notice_videos/${System.currentTimeMillis()}_${UUID.randomUUID().toString().take(6)}.mp4")
            videoRef.putFile(uri).await()
            val downloadUrl = videoRef.downloadUrl.await()
            val urlString = downloadUrl.toString()
            Log.d(TAG, "Video subido exitosamente a la nube: $urlString")

            // Guardar copia local inmediata en caché para que este dispositivo lo reproduzca al instante sin esperar red
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
            Log.e(TAG, "Error subiendo video a Firebase Storage: ${e.message}")
            throw Exception("Firebase Storage no habilitado o sin reglas. Debes habilitarlo en tu consola de Firebase.")
        }
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
