import sys

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "r") as f:
    content = f.read()

target = """        // Anti falsos positivos: si el texto es obviamente el nombre de un jugador, salir rápido.
        if (DraftValidationLayer.isLikelySummonerName(trimmed)) return null"""

replacement = """        // Anti falsos positivos REMOVIDO: Destruía detecciones cuando el OCR juntaba el nombre del campeón y del jugador."""

if target in content:
    content = content.replace(target, replacement)
    
target2 = """        // 4. Si la línea no contiene ningún campeón pero es claramente un apodo de invocador, descartar
        if (DraftValidationLayer.isLikelySummonerName(trimmed)) {
            return null
        }"""
replacement2 = """        // 4. Si la línea no contiene ningún campeón pero es claramente un apodo de invocador, descartar
        // REMOVIDO porque también daba falsos positivos en nombres legítimos concatenados."""
        
if target2 in content:
    content = content.replace(target2, replacement2)

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "w") as f:
    f.write(content)
print("Nuked summoner name aggressive blocking")

