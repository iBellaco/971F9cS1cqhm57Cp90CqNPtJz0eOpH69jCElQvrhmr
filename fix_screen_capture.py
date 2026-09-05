import re

with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'r') as f:
    text = f.read()

bad_resize = """                if (currentVirtualDisplay != null) {
                    try {
                        currentVirtualDisplay.surface = newImageReader.surface
                        currentVirtualDisplay.resize(captureWidth, captureHeight, screenDensity)
                        AppLogger.d(TAG, "VirtualDisplay redimensionado exitosamente a ($captureWidth x $captureHeight).")
                    } catch (e: Throwable) {
                        AppLogger.w(TAG, "Error al redimensionar VirtualDisplay: ${e.message}")
                    }
                } else {"""

good_resize = """                if (currentVirtualDisplay != null) {
                    try {
                        currentVirtualDisplay.surface = newImageReader.surface
                        currentVirtualDisplay.resize(captureWidth, captureHeight, screenDensity)
                        AppLogger.d(TAG, "VirtualDisplay redimensionado exitosamente a ($captureWidth x $captureHeight).")
                    } catch (e: Throwable) {
                        AppLogger.w(TAG, "Error al redimensionar VirtualDisplay, recreando: ${e.message}")
                        try {
                            currentVirtualDisplay.release()
                        } catch (_: Throwable) {}
                        
                        try {
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
                        } catch (e2: Throwable) {
                            AppLogger.e(TAG, "No se pudo recrear el VirtualDisplay: ${e2.message}")
                        }
                    }
                } else {"""

text = text.replace(bad_resize, good_resize)

with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'w') as f:
    f.write(text)
