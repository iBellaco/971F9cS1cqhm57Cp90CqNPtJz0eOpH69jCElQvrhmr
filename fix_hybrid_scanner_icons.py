with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

target = """        // 2. IMAGE MATCHING para detectar Campeones por sus avatares en los banners"""

replacement = """        // 2. IMAGE MATCHING para detectar Campeones por sus avatares y ROLES por sus iconos
        // Los iconos de rol están en la esquina superior izquierda del banner del campeón."""

text = text.replace(target, replacement)

target2 = """            try {
                val allyCrop = Bitmap.createBitmap(bitmap, allyX, startY, avatarWidth, avatarHeight)
                allySlots[i] = com.example.util.ImageHashMatcher.findBestMatch(allyCrop, allChamps)
                allyCrop.recycle()
            } catch (e: Exception) { /* ignore */ }"""

replacement2 = """            try {
                val allyCrop = Bitmap.createBitmap(bitmap, allyX, startY, avatarWidth, avatarHeight)
                allySlots[i] = com.example.util.ImageHashMatcher.findBestMatch(allyCrop, allChamps)
                allyCrop.recycle()
                
                // Extraer el icono de rol
                val iconWidth = (width * 0.04f).toInt()
                val iconX = allyX - (width * 0.01f).toInt()
                val roleCrop = Bitmap.createBitmap(bitmap, iconX.coerceIn(0, width - iconWidth), startY, iconWidth, iconWidth)
                val visualRole = com.example.util.ImageHashMatcher.findRoleMatch(roleCrop)
                if (visualRole != null && allySlotRoles[i] == null) {
                    allySlotRoles[i] = visualRole
                }
                roleCrop.recycle()
            } catch (e: Exception) { /* ignore */ }"""

text = text.replace(target2, replacement2)

target3 = """            try {
                val enemyCrop = Bitmap.createBitmap(bitmap, enemyX, startY, avatarWidth, avatarHeight)
                enemySlots[i] = com.example.util.ImageHashMatcher.findBestMatch(enemyCrop, allChamps)
                enemyCrop.recycle()
            } catch (e: Exception) { /* ignore */ }"""

replacement3 = """            try {
                val enemyCrop = Bitmap.createBitmap(bitmap, enemyX, startY, avatarWidth, avatarHeight)
                enemySlots[i] = com.example.util.ImageHashMatcher.findBestMatch(enemyCrop, allChamps)
                enemyCrop.recycle()
                
                val iconWidth = (width * 0.04f).toInt()
                val iconX = enemyX + avatarWidth - iconWidth
                val roleCrop = Bitmap.createBitmap(bitmap, iconX.coerceIn(0, width - iconWidth), startY, iconWidth, iconWidth)
                val visualRole = com.example.util.ImageHashMatcher.findRoleMatch(roleCrop)
                if (visualRole != null && enemySlotRoles[i] == null) {
                    enemySlotRoles[i] = visualRole
                }
                roleCrop.recycle()
            } catch (e: Exception) { /* ignore */ }"""

text = text.replace(target3, replacement3)

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write(text)

