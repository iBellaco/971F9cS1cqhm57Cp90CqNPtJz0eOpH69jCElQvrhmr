import re

with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'r') as f:
    text = f.read()

replacement = """                val currentVirtualDisplay = virtualDisplay
                if (currentVirtualDisplay != null) {
                    try {
                        currentVirtualDisplay.release()
                    } catch (e: Exception) {
                        AppLogger.w(TAG, "Error al liberar VirtualDisplay: ${e.message}")
                    }
                }
                
                virtualDisplay = proj.createVirtualDisplay(
                    VIRTUAL_DISPLAY_NAME,
                    captureWidth,
                    captureHeight,
                    screenDensity,
                    DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                    newImageReader.surface,
                    null,
                    handler
                )"""

text = re.sub(r'val currentVirtualDisplay = virtualDisplay.*?handler\n                \}', replacement, text, flags=re.DOTALL)

with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'w') as f:
    f.write(text)
