import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        // Calibración geométrica de precisión HUD Wild Rift:
        // Diámetro más ajustado para ignorar los bordes dorados brillantes
        val avatarDiameter = (height * 0.115f).toInt().coerceAtLeast(32)
        val allyAvatarCenterX = (height * 0.160f).toInt().coerceAtLeast(16)
        // En el HUD, los avatares enemigos están pegados al borde derecho, después del texto.
        val enemyAvatarCenterX = (width - (height * 0.080f)).toInt().coerceIn(0, width)

        // Ratios verticales calibrados de los 5 slots HUD (Ajustados con pitch de 0.135f):
        val slotYRatios = floatArrayOf(0.210f, 0.345f, 0.480f, 0.615f, 0.750f)"""

replacement = """        // Calibración geométrica de precisión HUD Wild Rift:
        // En pantallas ultra-anchas (21:9), hay un margen de zona segura (Safe Area).
        // Los avatares aliados están desplazados hacia la derecha (superando los hechizos).
        // Los avatares enemigos están desplazados hacia la izquierda desde el borde derecho.
        val avatarDiameter = (height * 0.115f).toInt().coerceAtLeast(32)
        
        // Ajuste dinámico basado en el aspect ratio para soportar 16:9 y 21:9
        val aspectRatio = width.toFloat() / height.toFloat()
        val isUltraWide = aspectRatio > 2.0f
        
        val allyAvatarCenterX = if (isUltraWide) (height * 0.195f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.210f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales calibrados de los 5 slots HUD (pitch exacto de 0.140f):
        val slotYRatios = floatArrayOf(0.207f, 0.347f, 0.487f, 0.627f, 0.767f)"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success")
else:
    print("Target not found")
