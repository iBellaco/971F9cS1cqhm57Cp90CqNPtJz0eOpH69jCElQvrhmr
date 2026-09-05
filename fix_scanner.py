import re

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

target = """            val visionText = try {
                recognizer.process(image).await()
            } catch (e: Exception) {
                AppLogger.e(TAG, "Excepción al procesar imagen OCR: ${e.message}")
                return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "Error interno de lectura")
            }"""

replacement = """            val useImageMatching = true
            if (useImageMatching) {
                return scanWithImageMatching(scaledBitmap)
            }

            val visionText = try {
                recognizer.process(image).await()
            } catch (e: Exception) {
                AppLogger.e(TAG, "Excepción al procesar imagen OCR: ${e.message}")
                return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "Error interno de lectura")
            }"""

text = text.replace(target, replacement)

new_func = """    private fun scanWithImageMatching(bitmap: Bitmap): DraftScanResult {
        val allChamps = WildRiftRepository.champions
        val allySlots = arrayOfNulls<Champion>(5)
        val enemySlots = arrayOfNulls<Champion>(5)
        
        val width = bitmap.width
        val height = bitmap.height
        
        // Coordenadas aproximadas de los avatares circulares en los slots (Landscape)
        // Aliados: Izquierda (~10% al 25% del ancho)
        // Enemigos: Derecha (~75% al 90% del ancho)
        val avatarWidth = (width * 0.12f).toInt()
        val avatarHeight = (height * 0.12f).toInt() // Ajuste proporcional
        
        val allyX = (width * 0.11f).toInt()
        val enemyX = (width * 0.77f).toInt()
        
        for (i in 0..4) {
            val yCenter = height * (0.1f + (i * 0.2f))
            val startY = (yCenter).toInt().coerceIn(0, height - avatarHeight)
            
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
            detectedRole = null, // Requiere otra lógica visual
            detectedRawWords = listOf("Image Matching Active"),
            isSuccessful = true,
            statusMessage = "Escaneo visual completado"
        )
    }
"""

text = text.replace("object DraftVisionScanner {", "object DraftVisionScanner {\n\n" + new_func)

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write(text)

