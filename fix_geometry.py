import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        // Ajuste milimétrico de la X:
        val allyAvatarCenterX = if (isUltraWide) (height * 0.140f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.100f)).toInt() else (width - (height * 0.160f)).toInt()"""

replacement = """        // Ajuste milimétrico de la X: Se desplazan un poco hacia el centro de la pantalla
        // para que no corten los iconos de hechizos/nombres y centren mejor el rostro
        val allyAvatarCenterX = if (isUltraWide) (height * 0.165f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.075f)).toInt() else (width - (height * 0.160f)).toInt()"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success Geometry Fix")
else:
    print("Target not found")
