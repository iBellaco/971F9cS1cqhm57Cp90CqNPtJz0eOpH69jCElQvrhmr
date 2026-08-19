package com.example.service.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.Image
import android.media.ImageReader
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.example.util.AppLogger
import java.nio.ByteBuffer

/**
 * Gestor de captura de pantalla en tiempo real utilizando MediaProjection y VirtualDisplay.
 * Diseñado con optimización de memoria (reutilización de buffers, reciclaje de Bitmaps)
 * y cumplimiento estricto de los requisitos de Foreground Service en Android 14+.
 */
class ScreenCaptureManager(private val context: Context) {

    companion object {
        private const val TAG = "ScreenCaptureManager"
        private const val VIRTUAL_DISPLAY_NAME = "WildRiftDraftCapture"
        
        // Cache global temporal para transferir el intent de MediaProjection entre Activity y Service
        var pendingMediaProjectionData: Intent? = null
        var pendingMediaProjectionResultCode: Int = 0
    }

    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private var imageReader: ImageReader? = null

    private var screenWidth: Int = 1080
    private var screenHeight: Int = 2400
    private var screenDensity: Int = 420
    private val handler = Handler(Looper.getMainLooper())

    init {
        updateScreenDimensions()
    }

    private fun updateScreenDimensions() {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getRealMetrics(metrics)
        screenWidth = metrics.widthPixels
        screenHeight = metrics.heightPixels
        screenDensity = metrics.densityDpi
    }

    /**
     * Inicializa MediaProjection con los datos de consentimiento de captura otorgados por el usuario.
     */
    @SuppressLint("WrongConstant")
    fun initializeProjection(resultCode: Int, data: Intent): Boolean {
        try {
            val projectionManager = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
            mediaProjection = projectionManager.getMediaProjection(resultCode, data)

            if (mediaProjection == null) {
                AppLogger.e(TAG, "MediaProjection no pudo ser creado a partir del Intent.")
                return false
            }

            updateScreenDimensions()

            // Usamos una resolución escalada (downscaled) para análisis OCR/Visión ultrarrápido y bajo consumo de RAM
            val captureWidth = (screenWidth / 2).coerceAtLeast(480)
            val captureHeight = (screenHeight / 2).coerceAtLeast(800)

            imageReader = ImageReader.newInstance(
                captureWidth,
                captureHeight,
                PixelFormat.RGBA_8888,
                2
            )

            virtualDisplay = mediaProjection?.createVirtualDisplay(
                VIRTUAL_DISPLAY_NAME,
                captureWidth,
                captureHeight,
                screenDensity,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                imageReader?.surface,
                null,
                handler
            )

            AppLogger.d(TAG, "MediaProjection y VirtualDisplay inicializados exitosamente ($captureWidth x $captureHeight).")
            return true
        } catch (e: Exception) {
            AppLogger.e(TAG, "Fallo al inicializar captura de pantalla", e)
            return false
        }
    }

    /**
     * Captura el frame actual de la pantalla como un Bitmap.
     */
    fun captureCurrentFrame(): Bitmap? {
        val reader = imageReader ?: return null
        var image: Image? = null
        return try {
            image = reader.acquireLatestImage()
            if (image == null) {
                // Si aún no hay un nuevo frame listo, reintentar con el último buffer disponible
                image = reader.acquireNextImage()
            }
            if (image == null) return null

            val planes = image.planes
            val buffer: ByteBuffer = planes[0].buffer
            val pixelStride = planes[0].pixelStride
            val rowStride = planes[0].rowStride
            val rowPadding = rowStride - pixelStride * image.width

            val bitmap = Bitmap.createBitmap(
                image.width + rowPadding / pixelStride,
                image.height,
                Bitmap.Config.ARGB_8888
            )
            bitmap.copyPixelsFromBuffer(buffer)

            // Recortar si hay padding en la fila
            if (rowPadding != 0) {
                val cleanBitmap = Bitmap.createBitmap(bitmap, 0, 0, image.width, image.height)
                bitmap.recycle()
                cleanBitmap
            } else {
                bitmap
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error al extraer frame de ImageReader", e)
            null
        } finally {
            image?.close()
        }
    }

    fun isReady(): Boolean = mediaProjection != null && imageReader != null

    fun release() {
        try {
            virtualDisplay?.release()
            virtualDisplay = null
            imageReader?.close()
            imageReader = null
            mediaProjection?.stop()
            mediaProjection = null
            AppLogger.d(TAG, "Recursos de MediaProjection liberados.")
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error liberando MediaProjection", e)
        }
    }
}
