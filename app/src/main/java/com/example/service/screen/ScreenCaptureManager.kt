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

            try {
                mediaProjection?.registerCallback(object : MediaProjection.Callback() {
                    override fun onStop() {
                        super.onStop()
                        AppLogger.w(TAG, "MediaProjection detenido por el sistema.")
                        synchronized(frameLock) {
                            lastFrame?.recycle()
                            lastFrame = null
                        }
                        mediaProjection = null
                    }
                }, handler)
            } catch (e: Exception) {
                AppLogger.w(TAG, "No se pudo registrar callback en MediaProjection: ${e.message}")
            }

            updateScreenDimensions()

            val captureWidth = screenWidth.coerceAtLeast(480)
            val captureHeight = screenHeight.coerceAtLeast(480)

            imageReader = ImageReader.newInstance(
                captureWidth,
                captureHeight,
                PixelFormat.RGBA_8888,
                3
            )

            imageReader?.setOnImageAvailableListener({ reader ->
                try {
                    val img = reader.acquireLatestImage() ?: return@setOnImageAvailableListener
                    val planes = img.planes
                    val buffer: ByteBuffer = planes[0].buffer
                    val pixelStride = planes[0].pixelStride
                    val rowStride = planes[0].rowStride
                    val rowPadding = rowStride - pixelStride * img.width

                    val bmp = Bitmap.createBitmap(
                        img.width + rowPadding / pixelStride,
                        img.height,
                        Bitmap.Config.ARGB_8888
                    )
                    bmp.copyPixelsFromBuffer(buffer)

                    val cleanBmp = if (rowPadding != 0) {
                        val cropped = Bitmap.createBitmap(bmp, 0, 0, img.width, img.height)
                        bmp.recycle()
                        cropped
                    } else {
                        bmp
                    }
                    img.close()

                    synchronized(frameLock) {
                        lastFrame?.recycle()
                        lastFrame = cleanBmp
                    }
                } catch (_: Exception) {}
            }, handler)

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

    private val frameLock = Any()
    private var lastFrame: Bitmap? = null

    /**
     * Refresca el VirtualDisplay para adaptarse a cambios de orientación o resolución sin invalidar el token de MediaProjection.
     */
    @SuppressLint("WrongConstant")
    fun refreshProjection() {
        if (mediaProjection == null) return
        try {
            updateScreenDimensions()
            
            val captureWidth = screenWidth.coerceAtLeast(480)
            val captureHeight = screenHeight.coerceAtLeast(480)

            val newImageReader = ImageReader.newInstance(
                captureWidth,
                captureHeight,
                PixelFormat.RGBA_8888,
                3
            )

            newImageReader.setOnImageAvailableListener({ reader ->
                try {
                    val img = reader.acquireLatestImage() ?: return@setOnImageAvailableListener
                    val planes = img.planes
                    val buffer: ByteBuffer = planes[0].buffer
                    val pixelStride = planes[0].pixelStride
                    val rowStride = planes[0].rowStride
                    val rowPadding = rowStride - pixelStride * img.width

                    val bmp = Bitmap.createBitmap(
                        img.width + rowPadding / pixelStride,
                        img.height,
                        Bitmap.Config.ARGB_8888
                    )
                    bmp.copyPixelsFromBuffer(buffer)

                    val cleanBmp = if (rowPadding != 0) {
                        val cropped = Bitmap.createBitmap(bmp, 0, 0, img.width, img.height)
                        bmp.recycle()
                        cropped
                    } else {
                        bmp
                    }
                    img.close()

                    synchronized(frameLock) {
                        lastFrame?.recycle()
                        lastFrame = cleanBmp
                    }
                } catch (_: Exception) {}
            }, handler)

            virtualDisplay?.resize(captureWidth, captureHeight, screenDensity)
            virtualDisplay?.setSurface(newImageReader.surface)

            val oldReader = imageReader
            imageReader = newImageReader
            oldReader?.close()

            AppLogger.d(TAG, "VirtualDisplay redimensionado a ($captureWidth x $captureHeight) sin recrear token.")
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error al redimensionar proyección de pantalla", e)
        }
    }

    /**
     * Captura el frame actual de la pantalla como un Bitmap.
     */
    fun captureCurrentFrame(): Bitmap? {
        try {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getRealMetrics(metrics)
            if (metrics.widthPixels != screenWidth || metrics.heightPixels != screenHeight) {
                refreshProjection()
            }
        } catch (_: Exception) {}

        // Intentar obtener el frame disponible, con breve espera de sincronización si recién inicializa
        var attempts = 0
        while (attempts < 5) {
            synchronized(frameLock) {
                val cached = lastFrame
                if (cached != null && !cached.isRecycled) {
                    return try {
                        cached.copy(Bitmap.Config.ARGB_8888, false)
                    } catch (_: Exception) {
                        null
                    }
                }
            }

            val reader = imageReader ?: return null
            var image: Image? = null
            try {
                image = reader.acquireLatestImage() ?: reader.acquireNextImage()
                if (image != null) {
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

                    val cleanBitmap = if (rowPadding != 0) {
                        val cropped = Bitmap.createBitmap(bitmap, 0, 0, image.width, image.height)
                        bitmap.recycle()
                        cropped
                    } else {
                        bitmap
                    }

                    synchronized(frameLock) {
                        lastFrame?.recycle()
                        lastFrame = cleanBitmap.copy(Bitmap.Config.ARGB_8888, false)
                    }

                    return cleanBitmap
                }
            } catch (e: Exception) {
                AppLogger.e(TAG, "Error al extraer frame de ImageReader", e)
            } finally {
                image?.close()
            }

            attempts++
            try {
                Thread.sleep(60)
            } catch (_: Exception) {}
        }

        return null
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
