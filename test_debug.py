import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """@Composable
fun VisionDebugOverlay() {
    val bitmap by com.example.service.screen.DraftVisionScanner.lastDebugBitmap.collectAsStateWithLifecycle()
    val diagnostics by com.example.service.screen.DraftVisionScanner.lastDiagnostics.collectAsStateWithLifecycle()

    val currentBitmap = bitmap ?: return"""

replacement = """@Composable
fun VisionDebugOverlay() {
    val bitmap by com.example.service.screen.DraftVisionScanner.lastDebugBitmap.collectAsStateWithLifecycle()
    val diagnostics by com.example.service.screen.DraftVisionScanner.lastDiagnostics.collectAsStateWithLifecycle()
    
    // Test box to see if overlay works at all
    androidx.compose.foundation.layout.Box(modifier = Modifier.fillMaxSize().background(Color.Red.copy(alpha=0.3f)))

    val currentBitmap = bitmap ?: return"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
