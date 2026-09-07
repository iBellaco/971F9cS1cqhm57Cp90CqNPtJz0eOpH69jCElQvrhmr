import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        // Calibración geométrica de precisión HUD Wild Rift:
        // El HUD suele estar enclavado a los bordes, pero en pantallas ultra anchas (21:9)
        // puede estar limitado por zonas seguras. Utilizamos anclas relativas híbridas (basadas en la altura) 
        // pero relajamos el recorte para atrapar el centro sin importar la deformación leve.
        val avatarDiameter = (height * 0.135f).toInt().coerceAtLeast(32)
        val allyAvatarCenterX = (height * 0.170f).toInt().coerceAtLeast(16) // ~11-12% en pantallas anchas
        val enemyAvatarCenterX = (width - (height * 0.185f)).toInt().coerceIn(0, width) // Evita pisar los hechizos enemigos

        // Ratios verticales calibrados de los 5 slots HUD:
        // Slot 0: ~135px (0.195), Slot 1: ~225px (0.324), Slot 2: ~319px (0.459),
        // Slot 3: ~413px (0.594), Slot 4: ~506px (0.728)
        val slotYRatios = floatArrayOf(0.195f, 0.324f, 0.459f, 0.594f, 0.728f)"""

replacement = """        // Calibración geométrica de precisión HUD Wild Rift:
        // Diámetro más ajustado para ignorar los bordes dorados brillantes
        val avatarDiameter = (height * 0.115f).toInt().coerceAtLeast(32)
        val allyAvatarCenterX = (height * 0.160f).toInt().coerceAtLeast(16)
        // En el HUD, los avatares enemigos están pegados al borde derecho, después del texto.
        val enemyAvatarCenterX = (width - (height * 0.080f)).toInt().coerceIn(0, width)

        // Ratios verticales calibrados de los 5 slots HUD (Ajustados con pitch de 0.135f):
        val slotYRatios = floatArrayOf(0.210f, 0.345f, 0.480f, 0.615f, 0.750f)"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success")
else:
    print("Target not found")
