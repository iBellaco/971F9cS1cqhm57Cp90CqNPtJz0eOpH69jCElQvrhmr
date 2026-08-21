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

object ImageStorageHelper {

    private const val TAG = "ImageStorageHelper"
    private const val ASSETS_FOLDER = "custom_game_assets"

    /**
     * Procesa un URI de imagen seleccionado por el usuario desde la galería/archivos,
     * la redimensiona a un tamaño óptimo para avatares/íconos de MOBA (máx 256x256),
     * la comprime en WebP/PNG y la almacena en el almacenamiento interno privado de la app.
     * Retorna una ruta de archivo local persistente ("file://...").
     */
    suspend fun saveImageFromUri(
        context: Context,
        uri: Uri,
        prefix: String = "img",
        maxDimension: Int = 256
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            val contentResolver = context.contentResolver
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            if (inputStream == null) {
                return@withContext Result.failure(Exception("No se pudo abrir el archivo seleccionado"))
            }

            // Decodificar dimensiones primero para escalar eficientemente
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream.close()

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

            // Crear directorio interno
            val storageDir = File(context.filesDir, ASSETS_FOLDER)
            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }

            val filename = "${prefix}_${System.currentTimeMillis()}_${UUID.randomUUID().toString().take(6)}.png"
            val targetFile = File(storageDir, filename)

            val fos = FileOutputStream(targetFile)
            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 95, fos)
            fos.flush()
            fos.close()

            val localFilePath = "file://${targetFile.absolutePath}"
            Log.d(TAG, "Imagen guardada exitosamente en almacenamiento local: $localFilePath")
            Result.success(localFilePath)
        } catch (e: Exception) {
            Log.e(TAG, "Error guardando imagen desde URI: ${e.message}", e)
            Result.failure(e)
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
