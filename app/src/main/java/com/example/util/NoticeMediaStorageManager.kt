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
            Log.d(TAG, "Video subido exitosamente a la nube: $downloadUrl")
            downloadUrl.toString()
        } catch (e: Exception) {
            Log.e(TAG, "Error subiendo video a Firebase Storage: ${e.message}")
            // Si falla, intentamos hacer fallback al almacenamiento local
            saveMediaToInternalStorage(context, uri, isVideo = true)
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
