import re

with open('app/src/main/java/com/example/WildRiftApp.kt', 'r') as f:
    content = f.read()

old_block = """        try {
            com.example.data.local.WildRiftLocalCache.loadFromLocalCache(this)
        } catch (e: Exception) {
            AppLogger.e("WildRiftApp", "Error cargando caché inicial", e)
        }"""

new_block = """        try {
            com.example.data.local.WildRiftLocalCache.loadFromLocalCache(this)
        } catch (e: Exception) {
            AppLogger.e("WildRiftApp", "Error cargando caché inicial", e)
        }
        
        // Garantizar que los campeones siempre estén en memoria (si la caché estaba vacía o corrupta)
        if (com.example.data.WildRiftRepository.champions.isEmpty()) {
            com.example.data.WildRiftRepository.initChampions(this)
            AppLogger.d("WildRiftApp", "Campeones inicializados desde JSON de emergencia.")
        }"""

content = content.replace(old_block, new_block)

with open('app/src/main/java/com/example/WildRiftApp.kt', 'w') as f:
    f.write(content)
