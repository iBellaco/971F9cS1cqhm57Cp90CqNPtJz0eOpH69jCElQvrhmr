import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target1 = """    var lastDebugBitmap: android.graphics.Bitmap? = null
    var lastDiagnostics: List<SlotDiagnostic> = emptyList()"""

replacement1 = """    var lastDebugBitmap = kotlinx.coroutines.flow.MutableStateFlow<android.graphics.Bitmap?>(null)
    var lastDiagnostics = kotlinx.coroutines.flow.MutableStateFlow<List<SlotDiagnostic>>(emptyList())"""

content = content.replace(target1, replacement1)

target2 = """            diagnostics = diagnosticsList,
            isSuccessful = total > 0,
            statusMessage = statusMsg
        )"""

replacement2 = """            diagnostics = diagnosticsList,
            isSuccessful = total > 0,
            statusMessage = statusMsg
        )
        
        lastDebugBitmap.value = bitmap.copy(Bitmap.Config.ARGB_8888, false)
        lastDiagnostics.value = diagnosticsList"""

content = content.replace(target2, replacement2)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
