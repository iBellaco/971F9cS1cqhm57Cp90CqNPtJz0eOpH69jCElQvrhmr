import os

filepath = 'app/src/main/java/com/example/service/screen/DraftVisionScanner.kt'
with open(filepath, 'r') as f:
    text = f.read()

# Restore the OCR process block
target = """            val useImageMatching = true
            if (useImageMatching) {
                return scanWithImageMatching(scaledBitmap)
            }

            val visionText = try {
                recognizer.process(image).await()
            } catch (e: Exception) {
                AppLogger.e(TAG, "Excepción al procesar imagen OCR: ${e.message}")
                return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "Error interno de lectura")
            }"""

replacement = """            val visionText = try {
                recognizer.process(image).await()
            } catch (e: Exception) {
                AppLogger.e(TAG, "Excepción al procesar imagen OCR: ${e.message}")
                return DraftScanResult(emptyList(), emptyList(), emptyMap(), emptyMap(), null, emptyList(), false, "Error interno de lectura")
            }"""

text = text.replace(target, replacement)

with open(filepath, 'w') as f:
    f.write(text)

