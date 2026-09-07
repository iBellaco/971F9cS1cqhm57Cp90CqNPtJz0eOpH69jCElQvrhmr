import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """            val left = rect.left * scaleX
            val top = rect.top * scaleY
            val right = rect.right * scaleX
            val bottom = rect.bottom * scaleY

            // Color: Verde si está confirmado, gris si está vacío, rojo si no hay match
            val boxColor = when {
                diag.status == "VACIO" -> Color.Gray
                diag.isConfirmed -> Color.Green
                else -> Color.Red
            }

            drawRect(
                color = boxColor,
                topLeft = androidx.compose.ui.geometry.Offset(left, top),
                size = androidx.compose.ui.geometry.Size(right - left, bottom - top),
                style = androidx.compose.graphics.drawscope.Stroke(width = 12f)
            )"""

replacement = """            val left = rect.left * scaleX
            val top = rect.top * scaleY
            val right = rect.right * scaleX
            val bottom = rect.bottom * scaleY

            // Reducimos visualmente el cuadro dibujado un 15% para que encaje 
            // perfectamente en el rostro interior, tal como el usuario prefiere,
            // mientras el escáner subyacente captura el 100% para el ImageHashMatcher.
            val insetX = (right - left) * 0.075f
            val insetY = (bottom - top) * 0.075f
            val drawLeft = left + insetX
            val drawTop = top + insetY
            val drawWidth = (right - left) - (insetX * 2)
            val drawHeight = (bottom - top) - (insetY * 2)

            // Color: Verde si está confirmado, gris si está vacío, rojo si no hay match
            val boxColor = when {
                diag.status == "VACIO" -> Color.Gray
                diag.isConfirmed -> Color.Green
                else -> Color.Red
            }

            drawRect(
                color = boxColor,
                topLeft = androidx.compose.ui.geometry.Offset(drawLeft, drawTop),
                size = androidx.compose.ui.geometry.Size(drawWidth, drawHeight),
                style = androidx.compose.graphics.drawscope.Stroke(width = 12f)
            )"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Success Debug Box")
else:
    print("Target not found")
