import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    data = f.read()

# Find the three items and replace actual newlines with \n
for item in ['relic_shield', 'spectral_sickle', 'ring_of_revelation']:
    pattern = r'(id\s*=\s*"' + item + r'".*?iconUrl\s*=\s*"[^"]+")'
    match = re.search(pattern, data, flags=re.DOTALL)
    if match:
        block = match.group(1)
        # Inside this block, we have passive = "..." and passiveEn = "..."
        # Replace newlines within quotes. 
        # Actually, let's just replace the whole block manually
        pass

# I'll just do simple string replacements
data = data.replace('habilidad.\nTributo', 'habilidad.\\nTributo')
data = data.replace('vida.\nCentinela', 'vida.\\nCentinela')
data = data.replace('guardianes.\nMisión', 'guardianes.\\nMisión')
data = data.replace('Espejismo.",\n            passiveEn', 'Espejismo.",\n            passiveEn')
data = data.replace('(Adaptive).\nTribute', '(Adaptive).\\nTribute')
data = data.replace('seconds.\nQuest', 'seconds.\\nQuest')
data = data.replace('orbe. Esto te otorgará oro y restaurará tu salud y la de tu aliado.\nCentinela', 'orbe. Esto te otorgará oro y restaurará tu salud y la de tu aliado.\\nCentinela')
data = data.replace('revelados.\nMisión', 'revelados.\\nMisión')
data = data.replace('Health.\nQuest', 'Health.\\nQuest')
data = data.replace('curación.\nCentinela', 'curación.\\nCentinela')
data = data.replace('gold.\nQuest', 'gold.\\nQuest')

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w', encoding='utf-8') as f:
    f.write(data)
print("fixed")
