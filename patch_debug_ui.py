import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target1 = """    val bitmap = com.example.service.screen.DraftVisionScanner.lastDebugBitmap
    val diagnostics = com.example.service.screen.DraftVisionScanner.lastDiagnostics

    if (bitmap == null) return"""

replacement1 = """    val bitmap by com.example.service.screen.DraftVisionScanner.lastDebugBitmap.collectAsStateWithLifecycle()
    val diagnostics by com.example.service.screen.DraftVisionScanner.lastDiagnostics.collectAsStateWithLifecycle()

    val currentBitmap = bitmap ?: return"""

content = content.replace(target1, replacement1)

target2 = """    Canvas(modifier = Modifier.fillMaxSize()) {
        val scaleX = size.width / bitmap.width.toFloat()
        val scaleY = size.height / bitmap.height.toFloat()"""

replacement2 = """    Canvas(modifier = Modifier.fillMaxSize()) {
        val scaleX = size.width / currentBitmap.width.toFloat()
        val scaleY = size.height / currentBitmap.height.toFloat()"""

content = content.replace(target2, replacement2)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
