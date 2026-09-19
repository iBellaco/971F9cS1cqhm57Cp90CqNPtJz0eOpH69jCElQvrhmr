package com.example.service.screen

import android.graphics.Bitmap
import android.graphics.Color
import com.example.util.AppLogger
import kotlin.math.max
import kotlin.math.min

/**
 * Preprocesador de imagen optimizado para regiones de interés (ROI) de texto en selección de campeones de Wild Rift.
 *
 * Convierte el recorte a escala de grises y aplica umbralización adaptativa (adaptive thresholding)
 * mediante imagen integral en O(N), aislando con alto contraste las letras de los campeones
 * (blancas/doradas) sobre fondos translúcidos oscuros.
 *
 * Además, para el lado aliado, aísla y neutraliza los artefactos generados por los iconos de maestría
 * que se sitúan al inicio (izquierda) de la cadena de texto antes del nombre del campeón.
 */
object SlotImagePreProcessor {

    private const val TAG = "SlotImagePreProcessor"

    /**
     * Aplica pre-procesamiento sobre un recorte de texto de slot.
     * Devuelve un nuevo Bitmap binarizado de alto contraste optimizado para ML Kit OCR.
     */
    fun preprocessSlotTextRegion(
        srcBitmap: Bitmap,
        isAlly: Boolean,
        suppressLeadingMasteryIcon: Boolean = true
    ): Bitmap? {
        if (srcBitmap.isRecycled || srcBitmap.width < 8 || srcBitmap.height < 8) return null

        return try {
            val width = srcBitmap.width
            val height = srcBitmap.height
            val pixels = IntArray(width * height)
            srcBitmap.getPixels(pixels, 0, width, 0, 0, width, height)

            // 1. Convertir a Escala de Grises (luminancia perceptual estándar ITU-R BT.601)
            val gray = IntArray(width * height)
            for (i in pixels.indices) {
                val c = pixels[i]
                val r = (c shr 16) and 0xFF
                val g = (c shr 8) and 0xFF
                val b = c and 0xFF
                gray[i] = (0.299f * r + 0.587f * g + 0.114f * b).toInt()
            }

            // 2. Construir Imagen Integral para Adaptive Thresholding en O(1) por píxel
            // integral[y+1][x+1] almacena la suma de luminancias desde (0,0) hasta (x,y)
            val integralWidth = width + 1
            val integralHeight = height + 1
            val integral = LongArray(integralWidth * integralHeight)

            for (y in 0 until height) {
                var rowSum = 0L
                val rowOffset = y * width
                val intRowOffset = (y + 1) * integralWidth
                val prevIntRowOffset = y * integralWidth

                for (x in 0 until width) {
                    rowSum += gray[rowOffset + x]
                    integral[intRowOffset + (x + 1)] = integral[prevIntRowOffset + (x + 1)] + rowSum
                }
            }

            // 3. Umbralización Adaptativa (Adaptive Thresholding)
            // Ventana local proporcional a la altura del texto (típicamente 15-20px)
            val windowSize = max(9, min(height / 2, 21)) or 1 // impar
            val halfWin = windowSize / 2
            val constantC = 12 // Margen para destacar texto claro sobre fondo oscuro

            val binary = IntArray(width * height)
            for (y in 0 until height) {
                val y1 = max(0, y - halfWin)
                val y2 = min(height - 1, y + halfWin)
                val rowOffset = y * width

                for (x in 0 until width) {
                    val x1 = max(0, x - halfWin)
                    val x2 = min(width - 1, x + halfWin)

                    // Suma rectangular en la imagen integral
                    val count = (x2 - x1 + 1) * (y2 - y1 + 1)
                    val sum = integral[(y2 + 1) * integralWidth + (x2 + 1)] -
                            integral[y1 * integralWidth + (x2 + 1)] -
                            integral[(y2 + 1) * integralWidth + x1] +
                            integral[y1 * integralWidth + x1]

                    val localMean = (sum / count).toInt()
                    val currentPix = gray[rowOffset + x]

                    // En Wild Rift el texto es blanco/dorado brillante (luminancia alta)
                    if (currentPix > localMean + constantC) {
                        binary[rowOffset + x] = Color.WHITE
                    } else {
                        binary[rowOffset + x] = Color.BLACK
                    }
                }
            }

            // 4. Supresión de Icono de Maestría y Pequeños Contornos Iniciales (Lado Aliado)
            // En Wild Rift, el icono de maestría se dibuja en el primer 18-25% a la izquierda del texto.
            // Para evitar que el OCR genere glifos como "•", "V", "VII", "M7" pegados al nombre,
            // localizamos la separación o limpiamos la zona de impacto inicial si no hay continuidad de texto.
            if (isAlly && suppressLeadingMasteryIcon && width >= 40) {
                val masteryRegionWidth = (width * 0.22f).toInt().coerceIn(12, 60)
                
                // Buscar una columna vertical con muy pocos píxeles blancos (la brecha o espacio entre icono y texto)
                var bestGapCol = -1
                for (x in (masteryRegionWidth * 0.4f).toInt() until masteryRegionWidth) {
                    var whiteCount = 0
                    for (y in 0 until height) {
                        if (binary[y * width + x] == Color.WHITE) whiteCount++
                    }
                    // Si la columna tiene menos del 15% de píxeles activos, es una separación natural
                    if (whiteCount <= (height * 0.15f).toInt()) {
                        bestGapCol = x
                        break
                    }
                }

                val clearBoundary = if (bestGapCol != -1) bestGapCol else (masteryRegionWidth * 0.75f).toInt()

                // Suprimir píxeles activos del icono de maestría previo a la separación
                for (y in 0 until height) {
                    val rowOffset = y * width
                    for (x in 0 until clearBoundary) {
                        binary[rowOffset + x] = Color.BLACK
                    }
                }
            }

            // 5. Crear Bitmap binarizado resultante
            val outBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            outBitmap.setPixels(binary, 0, width, 0, 0, width, height)
            outBitmap
        } catch (t: Throwable) {
            AppLogger.e(TAG, "Error en preprocesamiento de ROI de texto de slot", t)
            null
        }
    }
}
