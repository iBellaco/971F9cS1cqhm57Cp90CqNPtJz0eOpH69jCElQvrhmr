import re

with open("app/src/main/java/com/example/data/WildRiftRepository.kt", "r") as f:
    content = f.read()

content = content.replace(
    'directMatchupWarning = "El rival tiene alta iniciación de CC con Sett y Vi. Se aconseja desengage, escudos antimagia o tenacidad."',
    'directMatchupWarning = com.example.util.trStr(lang, "El rival tiene alta iniciación de CC con Sett y Vi. Se aconseja desengage, escudos antimagia o tenacidad.")'
)

content = content.replace(
    'directMatchupWarning = "Caitlyn rival tiene ventaja de rango en carril de Dragón. Prioriza anulación con Viego o agarre con Nautilus/Blitzcrank."',
    'directMatchupWarning = com.example.util.trStr(lang, "Caitlyn rival tiene ventaja de rango en carril de Dragón. Prioriza anulación con Viego o agarre con Nautilus/Blitzcrank.")'
)

content = content.replace(
    'directMatchupWarning = "Peligro de asesinos de burst (${enemyZed.name}). Imprescindible Zhonya/Estasis y CC garantizado (Lulu, Malzahar, Nautilus)."',
    'directMatchupWarning = com.example.util.trStr(lang, "Peligro de asesinos de burst") + " (${enemyZed.name}). " + com.example.util.trStr(lang, "Imprescindible Zhonya/Estasis y CC garantizado (Lulu, Malzahar, Nautilus).")'
)

content = content.replace(
    'directMatchupWarning = "Composición rival pesada (${enemyTanks.joinToString { it.name }}). Requiere daño verdadero y % vida máxima."',
    'directMatchupWarning = com.example.util.trStr(lang, "Composición rival pesada") + " (${enemyTanks.joinToString { it.name }}). " + com.example.util.trStr(lang, "Requiere daño verdadero y % vida máxima.")'
)

with open("app/src/main/java/com/example/data/WildRiftRepository.kt", "w") as f:
    f.write(content)
