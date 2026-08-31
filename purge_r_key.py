import os, json, re

def replace_in_file(path, replacements):
    if not os.path.exists(path):
        return
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    modified = content
    for old, new in replacements:
        modified = modified.replace(old, new)
        
    if modified != content:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(modified)
        print(f"Updated: {path}")

# 1. Update MatchupPreviewDialog.kt
replace_in_file('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', [
    ('Nv. 5 (R)', 'Nv. 5 (Definitiva)'),
    ('gastan su R sin impacto', 'gasta su Definitiva sin impacto'),
    ('falla su definitiva (R)', 'falla su Definitiva'),
    ('enfriamientos de R son cortos', 'enfriamientos de la Definitiva son cortos'),
    ('su R sin impacto', 'su Definitiva sin impacto'),
    ('su R', 'su Definitiva'),
    ('tu R', 'tu Definitiva'),
    ('H1+H3+R', 'Combo Completo'),
    ('H2+H1+R', 'Combo Completo')
])

# 2. Update DraftWomboSynergyCard.kt
replace_in_file('app/src/main/java/com/example/ui/components/DraftWomboSynergyCard.kt', [
    ('iniciar con R', 'iniciar con Definitiva'),
    ('+ R', ' + Definitiva'),
    ('+R', ' + Definitiva')
])

# 3. Update WildRiftChampionRunesMeta.kt
replace_in_file('app/src/main/java/com/example/util/WildRiftChampionRunesMeta.kt', [
    ('H2+H1+R', 'H2+H1+Definitiva'),
    ('H1+H3+R', 'H1+H3+Definitiva'),
    ('W+Q+R', 'H2+H1+Definitiva'),
    ('Q+E+R', 'H1+H3+Definitiva')
])

# 4. Clean champions_part1.json and champions_part2.json
for json_file in ['app/src/main/res/raw/champions_part1.json', 'app/src/main/res/raw/champions_part2.json']:
    if os.path.exists(json_file):
        with open(json_file, 'r', encoding='utf-8') as f:
            data = json.load(f)
        for champ in data:
            adv = champ.get('tacticalAdvice', '')
            adv = re.sub(r'\(R\)', '(Definitiva)', adv)
            adv = re.sub(r'\btu R\b', 'tu Definitiva', adv)
            adv = re.sub(r'\bsu R\b', 'su Definitiva', adv)
            adv = re.sub(r'\bcon R\b', 'con tu Definitiva', adv)
            adv = re.sub(r'\bla R\b', 'la Definitiva', adv)
            adv = adv.replace("Definitiva (Definitiva)", "Definitiva")
            champ['tacticalAdvice'] = adv
        with open(json_file, 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False, indent=2)
        print(f"Purged R mentions in {json_file}")

# 5. Clean WildRiftRepository.kt
replace_in_file('app/src/main/java/com/example/data/WildRiftRepository.kt', [
    ('con R', 'con su Definitiva'),
    ('(R)', '(Definitiva)')
])

# 6. Clean CoachingGenerator.kt
replace_in_file('app/src/main/java/com/example/util/CoachingGenerator.kt', [
    ('(R)', '(Definitiva)')
])

