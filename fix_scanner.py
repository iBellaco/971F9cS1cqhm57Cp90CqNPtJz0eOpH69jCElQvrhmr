import re

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

target = """    private fun scanWithImageMatching(bitmap: Bitmap): DraftScanResult {"""

replacement = """    private fun scanWithImageMatching(bitmap: Bitmap): DraftScanResult {
        val allChamps = WildRiftRepository.champions
        val allySlots = arrayOfNulls<Champion>(5)
        val enemySlots = arrayOfNulls<Champion>(5)
        
        val width = bitmap.width
        val height = bitmap.height
        
        // El banner en la selección tiene una relación de aspecto muy diferente a un cuadrado.
        // Aliados: Izquierda (~3% al 25% del ancho)
        // Enemigos: Derecha (~75% al 97% del ancho)
        // Tomaremos un recorte que atrape el rostro del campeón dentro de ese banner horizontal.
        
        // Haremos un recorte más estrecho, solo de la zona donde suele estar el rostro dentro del banner.
        // Usualmente el rostro está del lado exterior (izquierdo para aliados, derecho para enemigos)
        val avatarWidth = (width * 0.08f).toInt() 
        val avatarHeight = (height * 0.12f).toInt()
        
        // Coordenada X para aliados (asumiendo que el retrato está pegado a la izquierda del banner)
        val allyX = (width * 0.04f).toInt()
        // Coordenada X para enemigos (asumiendo que el retrato está pegado a la derecha del banner)
        val enemyX = (width * 0.88f).toInt()
        
        for (i in 0..4) {
            val yCenter = height * (0.1f + (i * 0.2f))
            val startY = (yCenter - avatarHeight / 2).toInt().coerceIn(0, height - avatarHeight)
            
            // Recortar aliado
            try {
                val allyCrop = Bitmap.createBitmap(bitmap, allyX, startY, avatarWidth, avatarHeight)
                val allyMatch = com.example.util.ImageHashMatcher.findBestMatch(allyCrop, allChamps)
                if (allyMatch != null) allySlots[i] = allyMatch
                allyCrop.recycle()
            } catch (e: Exception) {
                AppLogger.e(TAG, "Error recortando aliado $i", e)
            }
            
            // Recortar enemigo
            try {
                val enemyCrop = Bitmap.createBitmap(bitmap, enemyX, startY, avatarWidth, avatarHeight)
                val enemyMatch = com.example.util.ImageHashMatcher.findBestMatch(enemyCrop, allChamps)
                if (enemyMatch != null) enemySlots[i] = enemyMatch
                enemyCrop.recycle()
            } catch (e: Exception) {
                AppLogger.e(TAG, "Error recortando enemigo $i", e)
            }
        }
        
        val defaultAllyRoles = arrayOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val alliesMap = mutableMapOf<LaneRole, Champion>()
        val enemiesMap = mutableMapOf<LaneRole, Champion>()
        
        for (i in 0..4) {
            allySlots[i]?.let { alliesMap[defaultAllyRoles[i]] = it }
            enemySlots[i]?.let { enemiesMap[defaultAllyRoles[i]] = it }
        }
        
        return DraftScanResult(
            allies = allySlots.filterNotNull(),
            enemies = enemySlots.filterNotNull(),
            alliesByRole = alliesMap,
            enemiesByRole = enemiesMap,
            detectedRole = null,
            detectedRawWords = listOf("Image Matching Active"),
            isSuccessful = true,
            statusMessage = "Escaneo visual de banners completado"
        )
    }

    private fun _scanWithImageMatchingOld(bitmap: Bitmap): DraftScanResult {"""

text = text.replace(target, replacement)

# Re-enable the call to scanWithImageMatching
target2 = """            val visionText = try {
                recognizer.process(ocrImage).await()
            } catch (e: Exception) {"""

replacement2 = """            val useImageMatching = true
            if (useImageMatching) {
                return scanWithImageMatching(scaledOcrBmp ?: bitmap)
            }
            
            val visionText = try {
                recognizer.process(ocrImage).await()
            } catch (e: Exception) {"""

text = text.replace(target2, replacement2)

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write(text)

