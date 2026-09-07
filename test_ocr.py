import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

start = content.find("val ocrResult =")
end = content.find("val enemySlotTexts", start)
print(content[start:end])

start2 = content.find("// Procesar textos aliados")
end2 = content.find("// 3. Evaluar Avatares", start2)
print(content[start2:end2])

