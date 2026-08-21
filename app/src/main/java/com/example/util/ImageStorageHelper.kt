package com.example.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.example.data.supabase.SupabaseClientManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.UUID

object ImageStorageHelper {

    private const val TAG = "ImageStorageHelper"
    private const val ASSETS_FOLDER = "custom_game_assets"
    private const val STORAGE_BUCKET = "assets"

    data class SaveImageResult(
        val url: String,
        val isCloudUrl: Boolean,
        val localBackupPath: String
    )

    /**
     * Procesa un URI de imagen seleccionado por el usuario desde la galería/archivos,
     * la redimensiona a un tamaño óptimo para avatares/íconos de MOBA (máx 256x256),
     * la comprime en PNG, la almacena localmente y la sube automáticamente a Supabase Storage
     * si está configurado. Retorna la URL pública de la nube o la ruta local persistente ("file://...").
     */
    suspend fun saveImageFromUri(
        context: Context,
        uri: Uri,
        prefix: String = "img",
        maxDimension: Int = 256,
        uploadToCloud: Boolean = true
    ): Result<SaveImageResult> = withContext(Dispatchers.IO) {
        try {
            val contentResolver = context.contentResolver
            val inputStream = contentResolver.openInputStream(uri)
                ?: return@withContext Result.failure(Exception("No se pudo abrir el archivo seleccionado"))

            // Decodificar dimensiones primero para escalar eficientemente
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeStream(inputStream, null, options)
            try {
                inputStream.close()
            } catch (_: Exception) {}

            // Calcular inSampleSize
            options.inSampleSize = calculateInSampleSize(options, maxDimension, maxDimension)
            options.inJustDecodeBounds = false

            // Reabrir stream y decodificar el bitmap reducido
            val secondStream = contentResolver.openInputStream(uri)
                ?: return@withContext Result.failure(Exception("No se pudo leer la imagen"))
            val decodedBitmap = BitmapFactory.decodeStream(secondStream, null, options)
            secondStream.close()

            if (decodedBitmap == null) {
                return@withContext Result.failure(Exception("Formato de imagen no soportado"))
            }

            // Escalar exactamente manteniendo proporción
            val scaledBitmap = scaleBitmapToMax(decodedBitmap, maxDimension)

            // Crear directorio interno persistente
            val storageDir = File(context.filesDir, ASSETS_FOLDER)
            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }

            val sanitizedPrefix = prefix.replace("[^a-zA-Z0-9_]".toRegex(), "_").lowercase()
            val filename = "${sanitizedPrefix}_${System.currentTimeMillis()}_${UUID.randomUUID().toString().take(6)}.png"
            val targetFile = File(storageDir, filename)

            // Comprimir a PNG
            val byteArrayOutputStream = ByteArrayOutputStream()
            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 95, byteArrayOutputStream)
            val imageBytes = byteArrayOutputStream.toByteArray()

            val fos = FileOutputStream(targetFile)
            fos.write(imageBytes)
            fos.flush()
            fos.close()

            val localFilePath = "file://${targetFile.absolutePath}"
            Log.d(TAG, "Imagen guardada localmente en: $localFilePath (${imageBytes.size / 1024} KB)")

            // Intentar subir a Supabase Storage si está habilitado
            if (uploadToCloud) {
                val cloudUrl = uploadBytesToSupabaseStorage(imageBytes, filename)
                if (cloudUrl != null) {
                    Log.d(TAG, "Imagen subida exitosamente a Supabase Storage: $cloudUrl")
                    return@withContext Result.success(
                        SaveImageResult(
                            url = cloudUrl,
                            isCloudUrl = true,
                            localBackupPath = localFilePath
                        )
                    )
                }
            }

            // Fallback a almacenamiento local persistente
            Result.success(
                SaveImageResult(
                    url = localFilePath,
                    isCloudUrl = false,
                    localBackupPath = localFilePath
                )
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error procesando imagen: ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Sube un arreglo de bytes directamente a Supabase Storage via REST API
     */
    private fun uploadBytesToSupabaseStorage(bytes: ByteArray, filename: String): String? {
        return try {
            val baseUrl = SupabaseClientManager.getActiveUrl().trimEnd('/')
            val apiKey = SupabaseClientManager.getActiveKey().trim()

            if (baseUrl.isBlank() || apiKey.isBlank() || baseUrl.contains("example.supabase.co")) {
                Log.w(TAG, "Supabase no está configurado, omitiendo subida a la nube.")
                return null
            }

            val uploadEndpoint = "$baseUrl/storage/v1/object/$STORAGE_BUCKET/$filename"
            val url = URL(uploadEndpoint)
            val connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                doOutput = true
                connectTimeout = 8000
                readTimeout = 10000
                setRequestProperty("Authorization", "Bearer $apiKey")
                setRequestProperty("apikey", apiKey)
                setRequestProperty("Content-Type", "image/png")
                setRequestProperty("x-upsert", "true")
                setFixedLengthStreamingMode(bytes.size)
            }

            connection.outputStream.use { os ->
                os.write(bytes)
                os.flush()
            }

            val responseCode = connection.responseCode
            if (responseCode in 200..299) {
                // Generar URL pública estándar de Supabase Storage
                "$baseUrl/storage/v1/object/public/$STORAGE_BUCKET/$filename"
            } else {
                val errorStream = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Log.w(TAG, "Fallo al subir a Supabase Storage (HTTP $responseCode): $errorStream")
                null
            }
        } catch (e: Exception) {
            Log.w(TAG, "Excepción durante la subida a Supabase Storage: ${e.message}")
            null
        }
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize.coerceAtLeast(1)
    }

    private fun scaleBitmapToMax(bitmap: Bitmap, maxDimension: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        if (width <= maxDimension && height <= maxDimension) return bitmap

        val ratio = width.toFloat() / height.toFloat()
        val targetWidth: Int
        val targetHeight: Int

        if (width > height) {
            targetWidth = maxDimension
            targetHeight = (maxDimension / ratio).toInt().coerceAtLeast(1)
        } else {
            targetHeight = maxDimension
            targetWidth = (maxDimension * ratio).toInt().coerceAtLeast(1)
        }

        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true)
    }
}
