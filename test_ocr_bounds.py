import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

start = content.find("// 1.1 COLUMNA ALIADA")
end = content.find("// Procesar textos aliados", start)
print(content[start:end])

start2 = content.find("val ocrChamp = ")
end2 = content.find("val finalChamp = ", start2)
print(content[start2:end2])

