import os

file_path = "app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Replace hardcoded strings in MetaAndDraftScreen
replacements = {
    '"Conocer los tiempos exactos de aparición en Wild Rift asegura la victoria de tu equipo:"': 'tr("Conocer los tiempos exactos de aparición en Wild Rift asegura la victoria de tu equipo:")',
    '"Reaparición: ${obj.respawnTime}"': 'tr("Reaparición:") + " ${obj.respawnTime}"',
    '"Mejora: ${obj.buffDescription}"': 'tr("Mejora:") + " ${obj.buffDescription}"',
    '"Táctica: ${obj.tactics}"': 'tr("Táctica:") + " ${obj.tactics}"',
    '"Fuentes Oficiales y del Meta"': 'tr("Fuentes Oficiales y del Meta")',
    '"Los datos de campeones, runas, objetos, winrates y parches de Wild Rift se sincronizan con estos portales dentro de la app:"': 'tr("Los datos de campeones, runas, objetos, winrates y parches de Wild Rift se sincronizan con estos portales dentro de la app:")',
    '"Línea:"': 'tr("Línea:")',
    '"Cambiar"': 'tr("Cambiar")',
    '"1er Pick"': 'tr("1er Pick")',
    '"Blind Pick"': 'tr("Blind Pick")',
    '"Counter"': 'tr("Counter")',
    '"Equipo Aliado (${allies.size}/5)"': 'tr("Equipo Aliado") + " (${allies.size}/5)"',
    '"Equipo Rival (${enemies.size}/5)"': 'tr("Equipo Rival") + " (${enemies.size}/5)"',
    'if (team == "ALLY") "Seleccionar Campeón Aliado" else "Seleccionar Campeón Rival"': 'if (team == "ALLY") tr("Seleccionar Campeón Aliado") else tr("Seleccionar Campeón Rival")',
    '"Buscar campeón..."': 'tr("Buscar campeón...")',
    '"Todos"': 'tr("Todos")',
    '"WR: ${champ.winrate}%"': 'tr("WR") + ": ${champ.winrate}%"',
    '"Selecciona tu Línea para esta Partida"': 'tr("Selecciona tu Línea para esta Partida")'
}

for old, new in replacements.items():
    content = content.replace(old, new)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("MetaAndDraftScreen patched!")
