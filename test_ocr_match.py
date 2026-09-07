import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

start = content.find("// Procesar textos aliados")
end = content.find("// 3. Evaluar Avatares", start)
print(content[start:end])

