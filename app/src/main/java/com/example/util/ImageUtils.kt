package com.example.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

object ImageUtils {
    fun uriToBase64(context: Context, uri: Uri, maxWidth: Int = 600): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            if (bitmap == null) return null

            val ratio = bitmap.width.toFloat() / bitmap.height.toFloat()
            val finalWidth = if (bitmap.width > maxWidth) maxWidth else bitmap.width
            val finalHeight = (finalWidth / ratio).toInt()

            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, finalWidth, finalHeight, true)
            var quality = 80
            var outputStream = ByteArrayOutputStream()
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            var byteArray = outputStream.toByteArray()

            // Limit to ~750KB binary size so Base64 is under 1MB
            while (byteArray.size > 750 * 1024 && quality > 10) {
                quality -= 10
                outputStream = ByteArrayOutputStream()
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                byteArray = outputStream.toByteArray()
            }

            Base64.encodeToString(byteArray, Base64.NO_WRAP)
        } catch (e: Exception) {
            AppLogger.e("ImageUtils", "Error converting image to Base64", e)
            null
        }
    }
}
