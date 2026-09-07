import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        // Calibración geométrica de precisión HUD Wild Rift:
        // En 695 de alto: allyAvatarCenterX = 111 px (ratio 0.160f)
        // Diámetro avatar: 83 px (ratio 0.120f)
        val avatarDiameter = (height * 0.120f).toInt().coerceAtLeast(32)
        val allyAvatarCenterX = (height * 0.155f).toInt().coerceAtLeast(16)
        // En 1536x695: enemyAvatarCenterX = 1425 px (evita panel lateral Android y barra gestos)
        val enemyAvatarCenterX = (width - (height * 0.160f)).toInt().coerceIn(0, width)"""

replacement = """        // Calibración geométrica de precisión HUD Wild Rift:
        // El HUD suele estar enclavado a los bordes, pero en pantallas ultra anchas (21:9)
        // puede estar limitado por zonas seguras. Utilizamos anclas relativas híbridas (basadas en la altura) 
        // pero relajamos el recorte para atrapar el centro sin importar la deformación leve.
        val avatarDiameter = (height * 0.135f).toInt().coerceAtLeast(32)
        val allyAvatarCenterX = (height * 0.170f).toInt().coerceAtLeast(16) // ~11-12% en pantallas anchas
        val enemyAvatarCenterX = (width - (height * 0.185f)).toInt().coerceIn(0, width) // Evita pisar los hechizos enemigos"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
