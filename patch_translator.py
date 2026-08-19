import os

file_path = "app/src/main/java/com/example/util/Translator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

translations = """
    // Meta Sources Info Screen
    "3. Fuentes Web del Meta (Visor en la App)" to mapOf(
        "en" to "3. Meta Web Sources (In-App Viewer)",
        "pt" to "3. Fontes Web do Meta (Visualizador no App)"
    ),
    "Toca cualquier fuente para consultar sus datos directamente dentro de la aplicación:" to mapOf(
        "en" to "Tap any source to query its data directly within the application:",
        "pt" to "Toque em qualquer fonte para consultar seus dados diretamente dentro do aplicativo:"
    ),
    "Abrir" to mapOf(
        "en" to "Open",
        "pt" to "Abrir"
    ),
    "Catálogo Oficial de Campeones y Habilidades" to mapOf(
        "en" to "Official Champion and Skills Catalog",
        "pt" to "Catálogo Oficial de Campeões e Habilidades"
    ),
    "Runas en Español, Parches y Novedades" to mapOf(
        "en" to "Runes, Patch Notes, and News",
        "pt" to "Runas, Notas da Atualização e Notícias"
    ),
    "Builds Óptimas e Ítems Situacionales" to mapOf(
        "en" to "Optimal Builds and Situational Items",
        "pt" to "Builds Otimizadas e Itens Situacionais"
    ),
    "Tier Lists Globales y Sinergias" to mapOf(
        "en" to "Global Tier Lists and Synergies",
        "pt" to "Listas de Tier Globais e Sinergias"
    ),
    "Estadísticas en Tiempo Real y Counters" to mapOf(
        "en" to "Real-time Statistics and Counters",
        "pt" to "Estatísticas em Tempo Real e Counters"
    ),"""

content = content.replace("val dictionary = mapOf<String, Map<String, String>>(", "val dictionary = mapOf<String, Map<String, String>>(" + translations)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
print("Translator patched!")
