import re

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

target = """        val avatarWidth = (width * 0.08f).toInt() 
        val avatarHeight = (height * 0.12f).toInt()
        
        // Coordenada X para aliados (asumiendo que el retrato está pegado a la izquierda del banner)
        val allyX = (width * 0.04f).toInt()
        // Coordenada X para enemigos (asumiendo que el retrato está pegado a la derecha del banner)
        val enemyX = (width * 0.88f).toInt()"""

replacement = """        // Los banners son anchos y cortos, pero la cara suele estar en el centro.
        // Haremos un área de búsqueda más ancha para estar seguros.
        val avatarWidth = (width * 0.15f).toInt() 
        val avatarHeight = (height * 0.12f).toInt()
        
        // Los aliados están a la izquierda (empiezan aprox en 4% de la pantalla)
        val allyX = (width * 0.04f).toInt()
        // Los enemigos están a la derecha (empiezan aprox en 81% de la pantalla)
        val enemyX = (width * 0.81f).toInt()"""

text = text.replace(target, replacement)

target2 = """            // Recortar aliado
            try {
                val allyCrop = Bitmap.createBitmap(bitmap, allyX, startY, avatarWidth, avatarHeight)"""

replacement2 = """            // Recortar aliado
            try {
                // Prevenir recortes fuera de los límites
                val safeAllyX = allyX.coerceIn(0, width - avatarWidth)
                val allyCrop = Bitmap.createBitmap(bitmap, safeAllyX, startY, avatarWidth, avatarHeight)"""

text = text.replace(target2, replacement2)

target3 = """            // Recortar enemigo
            try {
                val enemyCrop = Bitmap.createBitmap(bitmap, enemyX, startY, avatarWidth, avatarHeight)"""

replacement3 = """            // Recortar enemigo
            try {
                // Prevenir recortes fuera de los límites
                val safeEnemyX = enemyX.coerceIn(0, width - avatarWidth)
                val enemyCrop = Bitmap.createBitmap(bitmap, safeEnemyX, startY, avatarWidth, avatarHeight)"""

text = text.replace(target3, replacement3)

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write(text)

