import re

with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'r') as f:
    text = f.read()

target = """                val currentVirtualDisplay = virtualDisplay
                if (currentVirtualDisplay != null) {
                    try {
                        currentVirtualDisplay.surface = null
                        currentVirtualDisplay.resize(captureWidth, captureHeight, screenDensity)
                        currentVirtualDisplay.surface = newImageReader.surface
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

replacement = """                val currentVirtualDisplay = virtualDisplay
                if (currentVirtualDisplay != null) {
                    try {
                        // En lugar de resize (que causa crash native fatal en algunos dispositivos Samsung/Xiaomi),
                        // siempre soltamos y recreamos. En API 34+ puede dar error si se excede el uso del token, 
                        // pero es mucho mas seguro que un SIGSEGV native de BufferQueue.
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
                } else {"""

text = text.replace(target, replacement)
with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'w') as f:
    f.write(text)

