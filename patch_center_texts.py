import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                    // 1.3 CENTRO
                    else {
                        centerTexts.add(text)
                    }"""

replacement = """                    // 1.3 CENTRO (Sólo el verdadero centro horizontal para evitar la ventana flotante)
                    else if (xRatio in 0.35f..0.65f) {
                        centerTexts.add(text)
                    }"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Patched center texts")
else:
    print("Target not found")
