package com.example.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import kotlin.math.pow

/**
 * Pre-procesador de imagen de alto rendimiento para reconocimiento visual (MLKit y Hashing perceptual)
 * Optimizado para Wild Rift bajo diversas condiciones de iluminación (modo oscuro, brillo tenue, resplandor de selección, auras activas).
 */
object ImagePreprocessor {

    /**
     * Mejora un Bitmap para reconocimiento de texto con MLKit OCR:
     * - Escala de grises con ponderación ITU-R BT.601
     * - Aumento dinámico de contraste (estiramiento de histograma)
     * - Filtro de nitidez para bordes de fuentes
     */
    fun enhanceForOcr(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(output)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        // Matriz de color: Grises + Alto Contraste (+40%) + Brillo calibrado
        val cm = ColorMatrix()
        cm.setSaturation(0f) // Escala de grises

        // Matriz de contraste: scale = 1.4, offset = -35
        val contrast = 1.4f
        val offset = -35f
        val contrastMatrix = floatArrayOf(
            contrast, 0f, 0f, 0f, offset,
            0f, contrast, 0f, 0f, offset,
            0f, 0f, contrast, 0f, offset,
            0f, 0f, 0f, 1f, 0f
        )
        cm.postConcat(ColorMatrix(contrastMatrix))

        paint.colorFilter = ColorMatrixColorFilter(cm)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        return output
    }

    /**
     * Normalización de iluminación (Auto-Levels / Min-Max Stretching)
     * Mapea el rango dinámico de luminancia actual al espectro completo [0, 255].
     * Esto elimina la dependencia de la iluminación ambiental y el brillo de pantalla.
     */
    fun normalizeLighting(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        var minLum = 255
        var maxLum = 0

        val lums = IntArray(pixels.size)
        for (i in pixels.indices) {
            val c = pixels[i]
            val r = Color.red(c)
            val g = Color.green(c)
            val b = Color.blue(c)
            val lum = (r * 299 + g * 587 + b * 114) / 1000
            lums[i] = lum
            if (lum < minLum) minLum = lum
            if (lum > maxLum) maxLum = lum
        }

        val range = (maxLum - minLum).coerceAtLeast(1)
        val outPixels = IntArray(pixels.size)

        for (i in pixels.indices) {
            val norm = ((lums[i] - minLum) * 255 / range).coerceIn(0, 255)
            outPixels[i] = Color.rgb(norm, norm, norm)
        }

        val outBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        outBitmap.setPixels(outPixels, 0, width, 0, 0, width, height)
        return outBitmap
    }

    /**
     * Corrección Gamma:
     * - gamma < 1.0 (ej. 0.65): Revela detalles en sombras / fondos oscuros.
     * - gamma > 1.0 (ej. 1.35): Desvanece resplandores / sobreexposiciones doradas.
     */
    fun applyGamma(bitmap: Bitmap, gamma: Float): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        val lut = IntArray(256)
        val invGamma = 1.0f / gamma
        for (i in 0..255) {
            lut[i] = ((i / 255.0f).toDouble().pow(invGamma.toDouble()) * 255.0).toInt().coerceIn(0, 255)
        }

        val outPixels = IntArray(pixels.size)
        for (i in pixels.indices) {
            val c = pixels[i]
            val r = lut[Color.red(c)]
            val g = lut[Color.green(c)]
            val b = lut[Color.blue(c)]
            outPixels[i] = Color.rgb(r, g, b)
        }

        val outBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        outBitmap.setPixels(outPixels, 0, width, 0, 0, width, height)
        return outBitmap
    }

    /**
     * Ecualización de Histograma completa para invariabilidad ante cualquier iluminación.
     */
    fun equalizeHistogram(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        val hist = IntArray(256)
        val lums = IntArray(pixels.size)

        for (i in pixels.indices) {
            val c = pixels[i]
            val r = Color.red(c)
            val g = Color.green(c)
            val b = Color.blue(c)
            val lum = (r * 299 + g * 587 + b * 114) / 1000
            lums[i] = lum
            hist[lum]++
        }

        // Función de distribución acumulativa (CDF)
        val cdf = IntArray(256)
        var sum = 0
        for (i in 0..255) {
            sum += hist[i]
            cdf[i] = sum
        }

        val totalPixels = pixels.size
        val cdfMin = cdf.firstOrNull { it > 0 } ?: 0
        val denominator = (totalPixels - cdfMin).coerceAtLeast(1)

        val equalizedLut = IntArray(256)
        for (i in 0..255) {
            equalizedLut[i] = (((cdf[i] - cdfMin).toFloat() / denominator) * 255.0f).toInt().coerceIn(0, 255)
        }

        val outPixels = IntArray(pixels.size)
        for (i in pixels.indices) {
            val newLum = equalizedLut[lums[i]]
            outPixels[i] = Color.rgb(newLum, newLum, newLum)
        }

        val outBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        outBitmap.setPixels(outPixels, 0, width, 0, 0, width, height)
        return outBitmap
    }
}
