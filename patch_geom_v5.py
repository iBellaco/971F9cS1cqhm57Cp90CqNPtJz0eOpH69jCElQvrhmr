import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        val avatarDiameter = (height * 0.115f).toInt().coerceAtLeast(32)
        
        // Ajuste dinámico basado en el aspect ratio para soportar 16:9 y 21:9
        val aspectRatio = width.toFloat() / height.toFloat()
        val isUltraWide = aspectRatio > 2.0f
        
        // En base a la última captura 21:9:
        // Las cajas enemigas se pasaron al centro un poco y quedaron mordiendo el borde izquierdo del texto. 
        // Las aliadas quedaron un poco a la izquierda mordiendo el borde negro.
        val allyAvatarCenterX = if (isUltraWide) (height * 0.138f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.110f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales (eje Y): Se subieron demasiado en la v3. Las cajas quedaron cortando la frente.
        // Hay que bajarlas un punto medio.
        val slotYRatios = floatArrayOf(0.198f, 0.330f, 0.465f, 0.598f, 0.735f)"""

replacement = """        // Reducimos el tamaño de la caja para enfocar el rostro y descartar el anillo exterior
        val avatarDiameter = (height * 0.098f).toInt().coerceAtLeast(32)
        
        // Ajuste dinámico basado en el aspect ratio para soportar 16:9 y 21:9
        val aspectRatio = width.toFloat() / height.toFloat()
        val isUltraWide = aspectRatio > 2.0f
        
        // Ajuste milimétrico de la X: Aliados un poco más a la derecha, enemigos un poco más a la derecha
        val allyAvatarCenterX = if (isUltraWide) (height * 0.145f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.100f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales (eje Y): El pitch actual es excelente, bajamos todos apenas 1 pixel relativo
        val slotYRatios = floatArrayOf(0.201f, 0.333f, 0.468f, 0.601f, 0.738f)"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success")
else:
    print("Target not found")
