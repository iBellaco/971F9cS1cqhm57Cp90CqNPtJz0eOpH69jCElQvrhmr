import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        // En base a la captura 21:9 exacta, las coordenadas correctas son:
        // Altura de la caja (avatarDiameter): Mantenemos 0.115f
        val allyAvatarCenterX = if (isUltraWide) (height * 0.125f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.125f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales (eje Y) corregidos milimétricamente hacia arriba.
        // En la última imagen, las cajas estaban demasiado abajo.
        val slotYRatios = floatArrayOf(0.185f, 0.315f, 0.445f, 0.575f, 0.705f)"""

replacement = """        // En base a la última captura 21:9:
        // Las cajas enemigas se pasaron al centro un poco y quedaron mordiendo el borde izquierdo del texto. 
        // Las aliadas quedaron un poco a la izquierda mordiendo el borde negro.
        val allyAvatarCenterX = if (isUltraWide) (height * 0.138f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.110f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales (eje Y): Se subieron demasiado en la v3. Las cajas quedaron cortando la frente.
        // Hay que bajarlas un punto medio.
        val slotYRatios = floatArrayOf(0.198f, 0.330f, 0.465f, 0.598f, 0.735f)"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success")
else:
    print("Target not found")
