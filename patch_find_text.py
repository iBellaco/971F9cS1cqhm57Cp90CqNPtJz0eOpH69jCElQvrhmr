import sys

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "r") as f:
    content = f.read()

target = """    // Encuentra el campeón correspondiente a una línea de texto OCR con validación anti-falsos positivos
    fun findChampionInText(text: String, allChampions: List<Champion>): Champion? {
        val trimmed = text.trim()
        if (trimmed.isBlank()) return null"""

replacement = """    // Encuentra el campeón correspondiente a una línea de texto OCR con validación anti-falsos positivos
    fun findChampionInText(text: String, allChampions: List<Champion>): Champion? {
        val trimmed = text.trim()
        if (trimmed.isBlank()) return null
        
        // Anti falsos positivos: si el texto es obviamente el nombre de un jugador, salir rápido.
        if (DraftValidationLayer.isLikelySummonerName(trimmed)) return null"""

if "DraftValidationLayer.isLikelySummonerName" not in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "w") as f:
        f.write(content)
    print("Resolver summoner check added")
else:
    print("Already added")
