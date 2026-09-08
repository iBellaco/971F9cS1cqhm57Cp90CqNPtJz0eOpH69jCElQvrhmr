import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

# Fix yRatios and xRatios
target_yratio = """                    // Determinar a qué slot pertenece
                    var slotIndex = -1
                    if (yRatio in 0.15f..0.29f) slotIndex = 0
                    else if (yRatio in 0.31f..0.45f) slotIndex = 1
                    else if (yRatio in 0.46f..0.60f) slotIndex = 2
                    else if (yRatio in 0.61f..0.76f) slotIndex = 3
                    else if (yRatio in 0.77f..0.95f) slotIndex = 4

                    // 1.1 COLUMNA ALIADA (Extremos ampliados para capturar los nombres, pero evitando el centro >0.33)
                    if (xRatio in 0.01f..0.24f) {"""

replacement_yratio = """                    // Determinar a qué slot pertenece
                    var slotIndex = -1
                    if (yRatio < 0.23f) slotIndex = 0
                    else if (yRatio in 0.23f..0.43f) slotIndex = 1
                    else if (yRatio in 0.43f..0.63f) slotIndex = 2
                    else if (yRatio in 0.63f..0.83f) slotIndex = 3
                    else if (yRatio > 0.83f) slotIndex = 4

                    // 1.1 COLUMNA ALIADA (Extremos ampliados para capturar los nombres, pero evitando el centro >0.33)
                    if (xRatio < 0.22f) {"""

if target_yratio in content:
    content = content.replace(target_yratio, replacement_yratio)
    print("Patched DraftVisionScanner.kt")
else:
    print("Target yratio not found in DraftVisionScanner.kt")

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
