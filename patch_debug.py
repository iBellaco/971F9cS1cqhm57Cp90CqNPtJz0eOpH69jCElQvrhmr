import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = "                        Spacer(modifier = Modifier.height(12.dp))"

replacement = """                        Spacer(modifier = Modifier.height(12.dp))
                        if (state.lastDraftAnalysisResult != null) {
                            Text(
                                "DEBUG OCR A0:" + (state.lastDraftAnalysisResult?.diagnostics?.getOrNull(0)?.ocrChampion?.name ?: "N") + 
                                " A1:" + (state.lastDraftAnalysisResult?.diagnostics?.getOrNull(1)?.ocrChampion?.name ?: "N"),
                                color = androidx.compose.ui.graphics.Color.White,
                                fontSize = 8.sp
                            )
                        }"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Patched debug text")
else:
    print("Could not find target")
