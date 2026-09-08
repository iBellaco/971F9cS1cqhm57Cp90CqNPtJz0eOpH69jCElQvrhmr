import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """            val label = if (diag.status == com.example.service.screen.DiagnosticStatus.VACIO) {
                "VACIO"
            } else {
                "${diag.ocrChampion?.name ?: "?"} / ${diag.visualChampion?.name ?: "?"} (${(diag.visualScore * 100).toInt()}%)"
            }"""

replacement = """            val label = if (diag.status == com.example.service.screen.DiagnosticStatus.VACIO) {
                "VACIO"
            } else {
                "${diag.ocrChampion?.name ?: "-"} | ${diag.visualChampion?.name ?: "-"} (${(diag.visualScore * 100).toInt()}%) ${diag.status}"
            }"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Patched debug overlay text")
else:
    print("Could not find target")

