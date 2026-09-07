import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                    // 1.1 COLUMNA ALIADA (Extremos ampliados para capturar los nombres, pero evitando el centro >0.33)
                    if (xRatio in 0.01f..0.34f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (X entre 0.66 y 0.99)
                    else if (xRatio in 0.66f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }"""

replacement = """                    // 1.1 COLUMNA ALIADA (Extremos ampliados para capturar los nombres, pero evitando el centro >0.33)
                    if (xRatio in 0.01f..0.24f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (X entre 0.66 y 0.99)
                    else if (xRatio in 0.76f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
print("Patched X bounds")

