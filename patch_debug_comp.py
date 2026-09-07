import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "a") as f:
    f.write("""

@Composable
fun VisionDebugOverlay() {
    val bitmap = com.example.service.screen.DraftVisionScanner.lastDebugBitmap
    val diagnostics = com.example.service.screen.DraftVisionScanner.lastDiagnostics

    if (bitmap == null) return

    Canvas(modifier = Modifier.fillMaxSize()) {
        val scaleX = size.width / bitmap.width.toFloat()
        val scaleY = size.height / bitmap.height.toFloat()

        for (diag in diagnostics) {
            val rect = diag.roiRect ?: continue
            val isAlly = diag.isAlly

            val left = rect.left * scaleX
            val top = rect.top * scaleY
            val right = rect.right * scaleX
            val bottom = rect.bottom * scaleY

            val color = when (diag.status) {
                com.example.service.screen.DiagnosticStatus.CONFIRMADO -> androidx.compose.ui.graphics.Color.Green
                com.example.service.screen.DiagnosticStatus.VACIO -> androidx.compose.ui.graphics.Color.Gray
                else -> androidx.compose.ui.graphics.Color.Red
            }

            drawRect(
                color = color,
                topLeft = androidx.compose.ui.geometry.Offset(left, top),
                size = androidx.compose.ui.geometry.Size(right - left, bottom - top),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4f)
            )

            // Draw score
            val champName = diag.candidate1?.name ?: "Unknown"
            val text = "${champName}\\n%.2f".format(diag.score1)
            // It's a bit complicated to draw text on raw Canvas without text measurer, so we'll just draw colored boxes.
            // But we can use native canvas to draw text:
            drawContext.canvas.nativeCanvas.drawText(
                "${champName} (%.2f)".format(diag.score1),
                left,
                top - 10f,
                android.graphics.Paint().apply {
                    this.color = android.graphics.Color.YELLOW
                    this.textSize = 30f
                    this.isAntiAlias = true
                }
            )
        }
    }
}
""")
