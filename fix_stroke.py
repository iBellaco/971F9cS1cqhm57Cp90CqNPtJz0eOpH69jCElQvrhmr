import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

content = content.replace("style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4f)", "style = androidx.compose.ui.graphics.drawscope.Stroke(width = 12f)")

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
