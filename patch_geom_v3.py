import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        val allyAvatarCenterX = if (isUltraWide) (height * 0.195f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.210f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales calibrados de los 5 slots HUD (pitch exacto de 0.140f):
        val slotYRatios = floatArrayOf(0.207f, 0.347f, 0.487f, 0.627f, 0.767f)"""

replacement = """        // En base a la captura 21:9 exacta, las coordenadas correctas son:
        // Altura de la caja (avatarDiameter): Mantenemos 0.115f
        val allyAvatarCenterX = if (isUltraWide) (height * 0.125f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.125f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales (eje Y) corregidos milimétricamente hacia arriba.
        // En la última imagen, las cajas estaban demasiado abajo.
        val slotYRatios = floatArrayOf(0.185f, 0.315f, 0.445f, 0.575f, 0.705f)"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success")
else:
    print("Target not found")
