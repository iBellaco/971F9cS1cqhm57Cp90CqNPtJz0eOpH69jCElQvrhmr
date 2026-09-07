import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """            val champName = diag.candidate1?.name ?: "Unknown"
            val text = "${champName}\\n%.2f".format(diag.score1)
            // It's a bit complicated to draw text on raw Canvas without text measurer, so we'll just draw colored boxes.
            // But we can use native canvas to draw text:
            drawContext.canvas.nativeCanvas.drawText(
                "${champName} (%.2f)".format(diag.score1),"""
replacement = """            val finalChampName = diag.finalChampion?.name ?: (diag.ocrChampion?.name ?: diag.candidate1?.name ?: "Unknown")
            val method = if (diag.finalChampion?.id == diag.ocrChampion?.id && diag.ocrChampion != null) "OCR" else "VISUAL"
            
            drawContext.canvas.nativeCanvas.drawText(
                "${finalChampName} [$method]","""
content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
print("Patched FloatingAssistantService.kt")

