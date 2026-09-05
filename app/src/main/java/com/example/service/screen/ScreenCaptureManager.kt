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
import android.os.HandlerThread
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

    // Hilo secundario dedicado para procesamiento de fotogramas sin bloquear el hilo principal (UI)
    private val captureThread = HandlerThread("ScreenCaptureThread").apply { start() }
    private val handler = Handler(captureThread.looper)

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

    private val frameLock = Any()
    private val projectionLock = Any()
    private var lastFrame: Bitmap? = null

    private fun processImageToBitmap(img: Image): Bitmap? {
        return try {
            val planes = img.planes
            if (planes.isNullOrEmpty()) return null
            val buffer = planes[0].buffer ?: return null
            val pixelStride = planes[0].pixelStride
            val rowStride = planes[0].rowStride
            val width = img.width
            val height = img.height
            if (width <= 0 || height <= 0 || pixelStride <= 0 || rowStride <= 0) return null

            val rowPadding = rowStride - pixelStride * width
            val bitmapWidth = width + rowPadding / pixelStride
            if (bitmapWidth <= 0) return null

            val requiredBytes = (height - 1) * rowStride + width * pixelStride
            if (buffer.remaining() < requiredBytes) {
                return null
            }

            val bmp = Bitmap.createBitmap(
                bitmapWidth,
                height,
                Bitmap.Config.ARGB_8888
            )
            bmp.copyPixelsFromBuffer(buffer)

            if (rowPadding != 0) {
                val cropped = Bitmap.createBitmap(bmp, 0, 0, width, height)
                bmp.recycle()
                cropped
            } else {
                bmp
            }
        } catch (t: Throwable) {
            AppLogger.w(TAG, "Error seguro procesando imagen a Bitmap: ${t.message}")
            null
        }
    }

    /**
     * Inicializa MediaProjection con los datos de consentimiento de captura otorgados por el usuario.
     */
    @SuppressLint("WrongConstant")
    fun initializeProjection(resultCode: Int, data: Intent): Boolean {
        synchronized(projectionLock) {
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
                ).apply {
                    setOnImageAvailableListener({ reader ->
                        var img: Image? = null
                        try {
                            img = reader.acquireLatestImage() ?: return@setOnImageAvailableListener
                            val cleanBmp = processImageToBitmap(img)
                            if (cleanBmp != null) {
                                synchronized(frameLock) {
                                    val old = lastFrame
                                    lastFrame = cleanBmp
                                    old?.recycle()
                                }
                            }
                        } catch (t: Throwable) {
                            AppLogger.w(TAG, "Error seguro en listener de imagen: ${t.message}")
                        } finally {
                            try {
                                img?.close()
                            } catch (_: Throwable) {}
                        }
                    }, handler)
                }

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
    }

    /**
     * Refresca el VirtualDisplay para adaptarse a cambios de orientación o resolución sin invalidar el token de MediaProjection.
     * En Android 14+, recrear el VirtualDisplay con el mismo token lanza SecurityException; por ello se redimensiona
     * el VirtualDisplay existente usando la API nativa `resize` y actualizando el Surface.
     */
    @SuppressLint("WrongConstant")
    fun refreshProjection() {
        synchronized(projectionLock) {
            val proj = mediaProjection ?: return
            try {
                updateScreenDimensions()
                
                val captureWidth = screenWidth.coerceAtLeast(480)
                val captureHeight = screenHeight.coerceAtLeast(480)

                val newImageReader = ImageReader.newInstance(
                    captureWidth,
                    captureHeight,
                    PixelFormat.RGBA_8888,
                    3
                ).apply {
                    setOnImageAvailableListener({ reader ->
                        var img: Image? = null
                        try {
                            img = reader.acquireLatestImage() ?: return@setOnImageAvailableListener
                            val cleanBmp = processImageToBitmap(img)
                            if (cleanBmp != null) {
                                synchronized(frameLock) {
                                    val old = lastFrame
                                    lastFrame = cleanBmp
                                    old?.recycle()
                                }
                            }
                        } catch (t: Throwable) {
                            AppLogger.w(TAG, "Error seguro en listener tras refresh: ${t.message}")
                        } finally {
                            try {
                                img?.close()
                            } catch (_: Throwable) {}
                        }
                    }, handler)
                }

                val currentVirtualDisplay = virtualDisplay
                if (currentVirtualDisplay != null) {
                    try {
                        currentVirtualDisplay.surface = newImageReader.surface
                        currentVirtualDisplay.resize(captureWidth, captureHeight, screenDensity)
                        AppLogger.d(TAG, "VirtualDisplay redimensionado exitosamente a ($captureWidth x $captureHeight).")
                    } catch (e: Exception) {
                        AppLogger.w(TAG, "Error al redimensionar VirtualDisplay: ${e.message}")
                    }
                } else {
                    virtualDisplay = proj.createVirtualDisplay(
                        VIRTUAL_DISPLAY_NAME,
                        captureWidth,
                        captureHeight,
                        screenDensity,
                        DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                        newImageReader.surface,
                        null,
                        handler
                    )
                }

                val oldReader = imageReader
                imageReader = newImageReader
                try {
                    oldReader?.close()
                } catch (_: Throwable) {}

            } catch (e: Exception) {
                AppLogger.e(TAG, "Error al redimensionar proyección de pantalla", e)
            }
        }
    }

    /**
     * Captura el frame actual de la pantalla como un Bitmap con sincronización protegida.
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
                    } catch (_: Throwable) {
                        null
                    }
                }
            }

            val reader = imageReader ?: return null
            var image: Image? = null
            try {
                image = reader.acquireLatestImage() ?: reader.acquireNextImage()
                if (image != null) {
                    val cleanBitmap = processImageToBitmap(image)
                    if (cleanBitmap != null) {
                        synchronized(frameLock) {
                            val old = lastFrame
                            try {
                                lastFrame = cleanBitmap.copy(Bitmap.Config.ARGB_8888, false)
                            } catch (_: Throwable) {}
                            old?.recycle()
                        }
                        return cleanBitmap
                    }
                }
            } catch (e: Exception) {
                AppLogger.e(TAG, "Error al extraer frame de ImageReader", e)
            } finally {
                try {
                    image?.close()
                } catch (_: Exception) {}
            }

            attempts++
            try {
                Thread.sleep(35)
            } catch (_: Exception) {}
        }

        return null
    }

    fun isReady(): Boolean = mediaProjection != null && imageReader != null

    fun release() {
        try {
            synchronized(frameLock) {
                lastFrame?.recycle()
                lastFrame = null
            }
            virtualDisplay?.release()
            virtualDisplay = null
            imageReader?.close()
            imageReader = null
            mediaProjection?.stop()
            mediaProjection = null
            captureThread.quitSafely()
            AppLogger.d(TAG, "Recursos de MediaProjection y HandlerThread liberados.")
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error liberando MediaProjection", e)
        }
    }
}
