import os, json, re

def replace_in_file(path, replacements):
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    modified = content
    for old, new in replacements:
        modified = modified.replace(old, new)
        
    if modified != content:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(modified)
        print(f"Updated: {path}")

# 1. Update champions_part1.json and champions_part2.json
def update_champions_json(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        data = json.load(f)
    
    for champ in data:
        adv = champ.get('tacticalAdvice', '')
        # Fix PC letter notations into Wild Rift notations
        adv = re.sub(r'\bde tu Q\b', 'de tu Habilidad 1 (H1)', adv)
        adv = re.sub(r'\bcon tu Q\b', 'con tu Habilidad 1 (H1)', adv)
        adv = re.sub(r'\btu Q\b', 'tu Habilidad 1 (H1)', adv)
        adv = re.sub(r'\bde su Q\b', 'de su Habilidad 1 (H1)', adv)
        adv = re.sub(r'\bsu Q\b', 'su Habilidad 1 (H1)', adv)
        adv = re.sub(r'\bla Q\b', 'la Habilidad 1 (H1)', adv)
        adv = re.sub(r'\bel Q\b', 'la Habilidad 1 (H1)', adv)

        adv = re.sub(r'\bde tu W\b', 'de tu Habilidad 2 (H2)', adv)
        adv = re.sub(r'\bcon tu W\b', 'con tu Habilidad 2 (H2)', adv)
        adv = re.sub(r'\btu W\b', 'tu Habilidad 2 (H2)', adv)
        adv = re.sub(r'\bde su W\b', 'de su Habilidad 2 (H2)', adv)
        adv = re.sub(r'\bsu W\b', 'su Habilidad 2 (H2)', adv)
        adv = re.sub(r'\bla W\b', 'la Habilidad 2 (H2)', adv)
        adv = re.sub(r'\bcon W\b', 'con tu H2', adv)

        adv = re.sub(r'\bde tu E\b', 'de tu Habilidad 3 (H3)', adv)
        adv = re.sub(r'\bcon tu E\b', 'con tu Habilidad 3 (H3)', adv)
        adv = re.sub(r'\btu E\b', 'tu Habilidad 3 (H3)', adv)
        adv = re.sub(r'\bde su E\b', 'de su Habilidad 3 (H3)', adv)
        adv = re.sub(r'\bsu E\b', 'su Habilidad 3 (H3)', adv)
        adv = re.sub(r'\bla E\b', 'la Habilidad 3 (H3)', adv)
        adv = re.sub(r'\bcon E\b', 'con tu H3', adv)

        adv = adv.replace("W+Q", "H2+H1").replace("E+Q", "H3+H1").replace("E+W+Q", "H3+H2+H1").replace("Q+E+R", "H1+H3+R")
        adv = adv.replace("carril superior", "Línea de Barón").replace("carril inferior", "Línea de Dragón")
        adv = adv.replace("nivel 6", "nivel 5 (Definitiva)").replace("lvl 6", "nivel 5")
        
        champ['tacticalAdvice'] = adv

    with open(file_path, 'w', encoding='utf-8') as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
    print(f"Cleaned champion advice in {file_path}")

update_champions_json('app/src/main/res/raw/champions_part1.json')
update_champions_json('app/src/main/res/raw/champions_part2.json')

# 2. Update habilidades.json (Evelynn level 6 -> level 5 in WR)
replace_in_file('app/src/main/assets/habilidades.json', [
    ('a partir del nivel 6', 'a partir del nivel 5 (Definitiva)'),
    ('al nivel 6', 'al nivel 5 (Definitiva)'),
    ('nivel 6', 'nivel 5')
])

# 3. Update translations
replace_in_file('app/src/main/assets/translations_en.json', [
    ('nivel 6', 'level 5'),
    ('level 6', 'level 5 (Ultimate)'),
    ('W+Q', 'Skill 2 + Skill 1'),
    ('Top Lane Alert!', 'Baron Lane Alert!'),
    ('¡Alerta en Top!', '¡Alerta en Línea de Barón!')
])

replace_in_file('app/src/main/assets/translations_pt.json', [
    ('nivel 6', 'nível 5'),
    ('nível 6', 'nível 5 (Ultimate)'),
    ('W+Q', 'H2+H1'),
    ('Rota do Topo', 'Rota do Barão'),
    ('Top', 'Rota do Barão')
])

# 4. Update DraftWomboSynergyCard.kt
replace_in_file('app/src/main/java/com/example/ui/components/DraftWomboSynergyCard.kt', [
    ('La E (Bendición de la Marea)', 'La Habilidad 3 (Bendición de la Marea)'),
    ('con E para iniciar con R', 'con Habilidad 3 para iniciar con Definitiva')
])

# 5. Update WildRiftChampionRunesMeta.kt
replace_in_file('app/src/main/java/com/example/util/WildRiftChampionRunesMeta.kt', [
    ('W+Q+R', 'H2+H1+R'),
    ('Q+E+R', 'H1+H3+R'),
    ('E+Q', 'H3+H1')
])

# 6. Update WildRiftRepository.kt
replace_in_file('app/src/main/java/com/example/data/WildRiftRepository.kt', [
    ('Top Lane Alert!', 'Baron Lane Alert!'),
    ('¡Alerta en Top!', '¡Alerta en Línea de Barón!'),
    ('Protege a $carrier con E para iniciar con R', 'Protege a $carrier con Habilidad 3 para iniciar con Definitiva')
])

# 7. Update CoachingGenerator.kt
replace_in_file('app/src/main/java/com/example/util/CoachingGenerator.kt', [
    ('Fase de Líneas', 'Fase Temprana (Línea de Wild Rift)'),
    ('Laning Phase', 'Wild Rift Early Laning Phase'),
    ('Fase de Rotas', 'Fase Inicial (Rotas de Wild Rift)'),
    ('Fase de Limpieza', 'Ruta de Jungla y Control de Río'),
    ('Clearing Phase', 'Jungle Path & River Control'),
    ('espera a tu jungla', 'espera la rotación de tu jungla o el Fruto de Miel (1:15 min)'),
    ('guardar la visión del río', 'controlar la Flor del Adivino y el Escurridizo (1:15 min)'),
    ('asegurando la visión del río', 'controlando el Escurridizo del río (1:15 min) y la Flor del Adivino')
])
