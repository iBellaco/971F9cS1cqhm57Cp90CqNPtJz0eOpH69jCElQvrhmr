import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        // Reducimos el tamaño de la caja para enfocar el rostro y descartar el anillo exterior
        val avatarDiameter = (height * 0.098f).toInt().coerceAtLeast(32)"""
replacement = """        // Volvems al tamaño geométrico correcto para que el ImageHashMatcher pueda hacer su crop interno (0.70f) sin destrozar la escala.
        val avatarDiameter = (height * 0.115f).toInt().coerceAtLeast(32)"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success Scanner Size Fix")
else:
    print("Target not found")
