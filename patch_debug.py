import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

old_obj = """object DraftVisionScanner {
    private const val TAG = "DraftVisionScanner"
    
    private var recognizerInstance: com.google.mlkit.vision.text.TextRecognizer? = null"""

new_obj = """object DraftVisionScanner {
    private const val TAG = "DraftVisionScanner"
    
    var lastDebugBitmap: android.graphics.Bitmap? = null
    var lastDiagnostics: List<SlotDiagnostic> = emptyList()
    
    private var recognizerInstance: com.google.mlkit.vision.text.TextRecognizer? = null"""

content = content.replace(old_obj, new_obj)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)

