import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """            val right = rect.right * scaleX
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
                style = androidx.compose.graphics.drawscope.Stroke(12f)
            )"""

replacement = """            val right = rect.right * scaleX
            val bottom = rect.bottom * scaleY

            val color = when (diag.status) {
                com.example.service.screen.DiagnosticStatus.CONFIRMADO -> androidx.compose.ui.graphics.Color.Green
                com.example.service.screen.DiagnosticStatus.VACIO -> androidx.compose.ui.graphics.Color.Gray
                else -> androidx.compose.ui.graphics.Color.Red
            }

            // Reducimos visualmente el cuadro dibujado un ~15% (inset de 7.5% por lado)
            // Esto permite que el usuario vea la caja perfectamente ajustada al rostro
            // mientras el tensor de análisis procesa la captura completa de 0.115f
            val insetX = (right - left) * 0.075f
            val insetY = (bottom - top) * 0.075f
            val drawLeft = left + insetX
            val drawTop = top + insetY
            val drawWidth = (right - left) - (insetX * 2)
            val drawHeight = (bottom - top) - (insetY * 2)

            drawRect(
                color = color,
                topLeft = androidx.compose.ui.geometry.Offset(drawLeft, drawTop),
                size = androidx.compose.ui.geometry.Size(drawWidth, drawHeight),
                style = androidx.compose.graphics.drawscope.Stroke(12f)
            )"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Success Debug Box 2")
else:
    print("Target not found")
