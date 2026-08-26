import re, json

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt") as f:
    text = f.read()

# Let's extract each WildRiftItem block
item_blocks = text.split("add(WildRiftItem(")
print(f"Total item blocks: {len(item_blocks)-1}")

def translate_stat_to_es(s):
    if not s: return ""
    res = s
    replacements = [
        ("Attack Damage", "Daño de Ataque"),
        ("Ability Power", "Poder de Habilidad"),
        ("Max Health", "Vida Máxima"),
        ("Max Mana", "Maná Máximo"),
        ("Armor Penetration", "Penetración de Armadura"),
        ("Magic Penetration", "Penetración Mágica"),
        ("Magic Resistance", "Resistencia Mágica"),
        ("Magic Resist", "Resistencia Mágica"),
        ("Armor", "Armadura"),
        ("Ability Haste", "Aceleración de Habilidad"),
        ("Attack Speed", "Velocidad de Ataque"),
        ("Move Speed", "Velocidad de Movimiento"),
        ("Movement Speed", "Velocidad de Movimiento"),
        ("Critical Rate", "Probabilidad de Crítico"),
        ("Critical Strike Chance", "Probabilidad de Crítico"),
        ("Physical Vamp", "Vampirismo Físico"),
        ("Omni Vamp", "Omnivampirismo"),
        ("Omnivamp", "Omnivampirismo"),
        ("Mana Regen", "Regeneración de Maná"),
        ("Mana Regeneration", "Regeneración de Maná"),
        ("Base Mana Regen", "Regeneración de Maná Base"),
        ("Health Regen", "Regeneración de Vida"),
        ("Base Health Regen", "Regeneración de Vida Base"),
        ("Heal and Shield Power", "Eficacia de Curación y Escudos"),
        ("Tenacity", "Tenacidad")
    ]
    for eng, esp in replacements:
        res = re.sub(re.escape(eng), esp, res, flags=re.IGNORECASE)
    return res

def translate_stat_to_pt(s):
    if not s: return ""
    res = s
    replacements = [
        ("Attack Damage", "Dano de Ataque"),
        ("Ability Power", "Poder de Habilidade"),
        ("Max Health", "Vida Máxima"),
        ("Max Mana", "Mana Máxima"),
        ("Armor Penetration", "Penetração de Armadura"),
        ("Magic Penetration", "Penetração Mágica"),
        ("Magic Resistance", "Resistência Mágica"),
        ("Magic Resist", "Resistência Mágica"),
        ("Armor", "Armadura"),
        ("Ability Haste", "Aceleração de Habilidade"),
        ("Attack Speed", "Velocidade de Ataque"),
        ("Move Speed", "Velocidade de Movimento"),
        ("Movement Speed", "Velocidade de Movimento"),
        ("Critical Rate", "Chance de Crítico"),
        ("Critical Strike Chance", "Chance de Crítico"),
        ("Physical Vamp", "Vampirismo Físico"),
        ("Omni Vamp", "Onivampirismo"),
        ("Omnivamp", "Onivampirismo"),
        ("Mana Regen", "Regeneração de Mana"),
        ("Mana Regeneration", "Regeneração de Mana"),
        ("Base Mana Regen", "Regeneração de Mana Base"),
        ("Health Regen", "Regeneração de Vida"),
        ("Base Health Regen", "Regeneração de Vida Base"),
        ("Heal and Shield Power", "Eficácia de Cura e Escudo"),
        ("Tenacity", "Tenacidade")
    ]
    for eng, pt in replacements:
        res = re.sub(re.escape(eng), pt, res, flags=re.IGNORECASE)
    return res

def translate_stat_to_en(s):
    if not s: return ""
    res = s
    replacements = [
        ("Daño de Ataque", "Attack Damage"),
        ("Poder de Habilidad", "Ability Power"),
        ("Vida Máxima", "Max Health"),
        ("Maná Máximo", "Max Mana"),
        ("Penetración de Armadura", "Armor Penetration"),
        ("Penetración Mágica", "Magic Penetration"),
        ("Resistencia Mágica", "Magic Resistance"),
        ("Armadura", "Armor"),
        ("Aceleración de Habilidad", "Ability Haste"),
        ("Velocidad de Ataque", "Attack Speed"),
        ("Velocidad de Movimiento", "Move Speed"),
        ("Probabilidad de Crítico", "Critical Rate"),
        ("Probabilidad de Impacto Crítico", "Critical Rate"),
        ("Vampirismo Físico", "Physical Vamp"),
        ("Omnivampirismo", "Omnivamp"),
        ("Regeneración de Maná", "Mana Regen"),
        ("Regeneración de Maná Base", "Base Mana Regen"),
        ("Regeneración de Vida", "Health Regen"),
        ("Regeneración de Vida Base", "Base Health Regen"),
        ("Eficacia de Curación y Escudos", "Heal and Shield Power"),
        ("Tenacidad", "Tenacity")
    ]
    for esp, eng in replacements:
        res = re.sub(re.escape(esp), eng, res, flags=re.IGNORECASE)
    return res

print("Stat translators ready")
