import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                    // 1.1 COLUMNA ALIADA (Extremo Izquierdo: X entre 0.01 y 0.35)
                    if (xRatio in 0.01f..0.35f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (Extremo Derecho: X entre 0.65 y 0.99)
                    else if (xRatio in 0.65f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }"""

replacement = """                    // 1.1 COLUMNA ALIADA (Extremo Izquierdo estricto: X entre 0.01 y 0.22)
                    // Evitamos > 0.22 porque podríamos leer el overlay de nuestra propia app
                    if (xRatio in 0.01f..0.22f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (Extremo Derecho estricto: X entre 0.78 y 0.99)
                    else if (xRatio in 0.78f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success OCR Bounds")
else:
    print("Target bounds not found")
