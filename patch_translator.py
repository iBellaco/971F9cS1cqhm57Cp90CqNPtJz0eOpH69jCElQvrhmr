with open('app/src/main/java/com/example/util/Translator.kt', 'r', encoding='utf-8') as f:
    content = f.read()

pt_entry = '        "Este campeón no es idóneo para esta línea. Jugarlo aquí es considerado atípico o desventajoso para el equipo." to "Este campeão não é adequado para esta rota. Jogar aqui é considerado atípico ou desvantajoso para a equipe.",\n'
en_entry = '        "Este campeón no es idóneo para esta línea. Jugarlo aquí es considerado atípico o desventajoso para el equipo." to "This champion is not suited for this lane. Playing them here is considered off-meta or disadvantageous for the team.",\n'
pt_badge = '        "❌ SELECCIÓN ATÍPICA (OFF-META)" to "❌ SELEÇÃO ATÍPICA (OFF-META)",\n'
en_badge = '        "❌ SELECCIÓN ATÍPICA (OFF-META)" to "❌ OFF-META PICK (TROLL)",\n'

# Insert at the beginning of the pt map
content = content.replace(
    'val ptMap = mapOf(',
    'val ptMap = mapOf(\n' + pt_entry + pt_badge
)

# Insert at the beginning of the en map
content = content.replace(
    'val enMap = mapOf(',
    'val enMap = mapOf(\n' + en_entry + en_badge
)

with open('app/src/main/java/com/example/util/Translator.kt', 'w', encoding='utf-8') as f:
    f.write(content)
