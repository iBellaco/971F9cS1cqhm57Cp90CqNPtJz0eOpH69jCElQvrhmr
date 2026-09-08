import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """            val finalChampName = diag.finalChampion?.name ?: (diag.ocrChampion?.name ?: diag.candidate1?.name ?: "Unknown")
            val method = if (diag.finalChampion?.id == diag.ocrChampion?.id && diag.ocrChampion != null) "OCR" else "VISUAL"
            
            drawContext.canvas.nativeCanvas.drawText(
                "${finalChampName} [$method]",
                left,"""

replacement = """            val finalChampName = diag.finalChampion?.name ?: (diag.ocrChampion?.name ?: "Unknown")
            val method = if (diag.finalChampion?.id == diag.ocrChampion?.id && diag.ocrChampion != null) "OCR" else "VISUAL"
            
            drawContext.canvas.nativeCanvas.drawText(
                "${finalChampName} [$method] VIS:${diag.candidate1?.name ?: "-"} (${"%.2f".format(diag.score1)})",
                left,"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Patched VisionDebugOverlay")
else:
    print("Could not find target")

