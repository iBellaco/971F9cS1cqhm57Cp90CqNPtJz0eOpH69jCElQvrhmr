import os

file_path = "app/src/main/java/com/example/util/Translator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

translations = """
    "Conocer los tiempos exactos de aparición en Wild Rift asegura la victoria de tu equipo:" to mapOf(
        "en" to "Knowing the exact spawn times in Wild Rift ensures your team's victory:",
        "pt" to "Saber os tempos exatos de surgimento no Wild Rift garante a vitória da sua equipe:"
    ),
    "Reaparición:" to mapOf(
        "en" to "Respawn:",
        "pt" to "Ressurgimento:"
    ),
    "Mejora:" to mapOf(
        "en" to "Buff:",
        "pt" to "Bônus:"
    ),
    "Táctica:" to mapOf(
        "en" to "Tactics:",
        "pt" to "Tática:"
    ),
    "Fuentes Oficiales y del Meta" to mapOf(
        "en" to "Official and Meta Sources",
        "pt" to "Fontes Oficiais e do Meta"
    ),
    "Los datos de campeones, runas, objetos, winrates y parches de Wild Rift se sincronizan con estos portales dentro de la app:" to mapOf(
        "en" to "Wild Rift champion data, runes, items, winrates, and patch notes sync with these portals within the app:",
        "pt" to "Dados de campeões, runas, itens, taxas de vitória e notas da atualização do Wild Rift sincronizam com esses portais dentro do aplicativo:"
    ),
    "Línea:" to mapOf(
        "en" to "Lane:",
        "pt" to "Rota:"
    ),
    "Cambiar" to mapOf(
        "en" to "Change",
        "pt" to "Mudar"
    ),
    "1er Pick" to mapOf(
        "en" to "1st Pick",
        "pt" to "1º Escolha"
    ),
    "Counter" to mapOf(
        "en" to "Counter",
        "pt" to "Counter"
    ),
    "Equipo Aliado" to mapOf(
        "en" to "Ally Team",
        "pt" to "Equipe Aliada"
    ),
    "Equipo Rival" to mapOf(
        "en" to "Enemy Team",
        "pt" to "Equipe Inimiga"
    ),
    "Seleccionar Campeón Aliado" to mapOf(
        "en" to "Select Ally Champion",
        "pt" to "Selecionar Campeão Aliado"
    ),
    "Seleccionar Campeón Rival" to mapOf(
        "en" to "Select Enemy Champion",
        "pt" to "Selecionar Campeão Inimigo"
    ),
    "Buscar campeón..." to mapOf(
        "en" to "Search champion...",
        "pt" to "Procurar campeão..."
    ),
    "Todos" to mapOf(
        "en" to "All",
        "pt" to "Todos"
    ),
    "WR" to mapOf(
        "en" to "WR",
        "pt" to "WR"
    ),
    "Selecciona tu Línea para esta Partida" to mapOf(
        "en" to "Select Your Lane for this Match",
        "pt" to "Selecione sua Rota para esta Partida"
    ),"""

content = content.replace("val dictionary = mapOf<String, Map<String, String>>(", "val dictionary = mapOf<String, Map<String, String>>(" + translations)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Translator patched again!")
