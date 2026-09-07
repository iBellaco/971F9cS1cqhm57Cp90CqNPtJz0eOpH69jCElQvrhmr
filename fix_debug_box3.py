import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """            drawRect(
                color = color,
                topLeft = androidx.compose.ui.geometry.Offset(left, top),
                size = androidx.compose.ui.geometry.Size(right - left, bottom - top),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 12f)
            )"""

replacement = """            val insetX = (right - left) * 0.075f
            val insetY = (bottom - top) * 0.075f
            val drawLeft = left + insetX
            val drawTop = top + insetY
            val drawWidth = (right - left) - (insetX * 2)
            val drawHeight = (bottom - top) - (insetY * 2)

            drawRect(
                color = color,
                topLeft = androidx.compose.ui.geometry.Offset(drawLeft, drawTop),
                size = androidx.compose.ui.geometry.Size(drawWidth, drawHeight),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 12f)
            )"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Success Debug Box 3")
else:
    print("Target not found")
