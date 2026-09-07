import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """            val ocrChamp = enemyOcrChampions[i]
            var eval = VisualEvaluation(null, 0f, null, 0f, 0f, false, "VACIO", "Error al procesar")"""

replacement = """            val ocrChamp = enemyOcrChampions[i]
            var eval = VisualEvaluation(null, 0f, null, 0f, 0f, false, "VACIO", "Error al procesar")"""

if "enemyOcrChampions[i]" not in content[content.find("3.2 Enemigos"):]:
    print("WARNING: enemyOcrChampions not used in step 3.2")
else:
    print("enemyOcrChampions used in step 3.2")
