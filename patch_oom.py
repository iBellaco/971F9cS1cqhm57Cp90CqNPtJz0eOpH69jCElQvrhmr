import re

with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'r') as f:
    text = f.read()

# Replace catch (e: Exception) with catch (e: Throwable) in refreshProjection
text = re.sub(r'catch \(e: Exception\) \{\n\s*AppLogger\.e\(TAG, "Error al redimensionar proyección de pantalla"',
              r'catch (e: Throwable) {\n                AppLogger.e(TAG, "Error al redimensionar proyección de pantalla"', text)

# Replace catch (e: Exception) with catch (e: Throwable) in processImageToBitmap
text = re.sub(r'catch \(e: Exception\) \{\n\s*null\n\s*\}\n\s*\}',
              r'catch (e: Throwable) {\n            null\n        }\n    }', text)

# Just in case, replace any catch (e: Exception) that wraps Bitmap operations
text = text.replace('catch (e: Exception) {', 'catch (e: Throwable) {')

with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'w') as f:
    f.write(text)
