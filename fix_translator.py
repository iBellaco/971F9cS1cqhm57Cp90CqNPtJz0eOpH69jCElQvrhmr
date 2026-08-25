with open('app/src/main/java/com/example/util/Translator.kt', 'r') as f:
    content = f.read()
    
# Let's see the content
start_idx = content.find('    var replaced = key')
end_idx = content.find('    } else if (effectiveLang == "es") {')

# The original content inside this region
new_content = """    var replaced = key
    
    if (effectiveLang == "en") {
        val itemNameEn = itemNamesEsToEn[key]
        if (itemNameEn != null) return itemNameEn

        // Categories
        replaced = replaced.replace("Daño Físico", "Physical Damage", ignoreCase = true)
        replaced = replaced.replace("Daño Mágico", "Magic Damage", ignoreCase = true)
        replaced = replaced.replace("Defensa", "Defense", ignoreCase = true)
        replaced = replaced.replace("Magia", "Magic", ignoreCase = true)
        replaced = replaced.replace("Botas", "Boots", ignoreCase = true)
        replaced = replaced.replace("Básicos", "Basic", ignoreCase = true)
        replaced = replaced.replace("Nivel Medio", "Mid Tier", ignoreCase = true)
        replaced = replaced.replace("Encantamientos", "Enchants", ignoreCase = true)
        replaced = replaced.replace("Parche", "Patch", ignoreCase = true)

        replaced = replaced.replace("Vida Máxima", "Max Health", ignoreCase = true)
        replaced = replaced.replace("Daño de Ataque", "Attack Damage", ignoreCase = true)
        replaced = replaced.replace("Poder de Habilidad", "Ability Power", ignoreCase = true)
        replaced = replaced.replace("Velocidad de Ataque", "Attack Speed", ignoreCase = true)
        replaced = replaced.replace("Velocidad de Movimiento", "Movement Speed", ignoreCase = true)
        replaced = replaced.replace("Aceleración de Habilidad", "Ability Haste", ignoreCase = true)
        replaced = replaced.replace("Probabilidad de Crítico", "Critical Chance", ignoreCase = true)
        replaced = replaced.replace("Daño Crítico", "Critical Damage", ignoreCase = true)
        replaced = replaced.replace("Penetración de Armadura", "Armor Penetration", ignoreCase = true)
        replaced = replaced.replace("Penetración Mágica", "Magic Penetration", ignoreCase = true)
        replaced = replaced.replace("Resistencia Mágica", "Magic Resist", ignoreCase = true)
        replaced = replaced.replace("Armadura", "Armor", ignoreCase = true)
        replaced = replaced.replace("Robo de Vida", "Life Steal", ignoreCase = true)
        replaced = replaced.replace("Omnivampirismo", "Omnivamp", ignoreCase = true)
        replaced = replaced.replace("Vampirismo", "Vamp", ignoreCase = true)
        replaced = replaced.replace("Daño Verdadero", "True Damage", ignoreCase = true)
        replaced = replaced.replace("Curación", "Healing", ignoreCase = true)
        replaced = replaced.replace("Escudo", "Shield", ignoreCase = true)
        replaced = replaced.replace("Enfriamiento", "Cooldown", ignoreCase = true)
        replaced = replaced.replace("Pasiva", "Passive", ignoreCase = true)
        replaced = replaced.replace("Habilidad", "Ability", ignoreCase = true)
        replaced = replaced.replace("Definitiva", "Ultimate", ignoreCase = true)
        replaced = replaced.replace("Inflige", "Deals", ignoreCase = true)
        replaced = replaced.replace("Aumenta", "Increases", ignoreCase = true)
        replaced = replaced.replace("Reduce", "Reduces", ignoreCase = true)
        replaced = replaced.replace("Otorga", "Grants", ignoreCase = true)
    } else if (effectiveLang == "pt") {
        // Fallback to English names if no direct pt translation
        val itemNameEn = itemNamesEsToEn[key]
        if (itemNameEn != null) return itemNameEn

        // Categories
        replaced = replaced.replace("Physical Damage", "Dano Físico", ignoreCase = true)
        replaced = replaced.replace("Magic Damage", "Dano Mágico", ignoreCase = true)
        replaced = replaced.replace("Defense", "Defesa", ignoreCase = true)
        replaced = replaced.replace("Magic", "Magia", ignoreCase = true)
        replaced = replaced.replace("Boots", "Botas", ignoreCase = true)
        replaced = replaced.replace("Basic", "Básicos", ignoreCase = true)
        replaced = replaced.replace("Mid Tier", "Nível Médio", ignoreCase = true)
        replaced = replaced.replace("Enchants", "Encantamentos", ignoreCase = true)
        replaced = replaced.replace("Patch", "Patch", ignoreCase = true)

        replaced = replaced.replace("Daño Físico", "Dano Físico", ignoreCase = true)
        replaced = replaced.replace("Daño Mágico", "Dano Mágico", ignoreCase = true)
        replaced = replaced.replace("Defensa", "Defesa", ignoreCase = true)
        replaced = replaced.replace("Magia", "Magia", ignoreCase = true)
        replaced = replaced.replace("Botas", "Botas", ignoreCase = true)
        replaced = replaced.replace("Básicos", "Básicos", ignoreCase = true)
        replaced = replaced.replace("Nivel Medio", "Nível Médio", ignoreCase = true)
        replaced = replaced.replace("Encantamientos", "Encantamentos", ignoreCase = true)
        replaced = replaced.replace("Parche", "Patch", ignoreCase = true)

        replaced = replaced.replace("Vida Máxima", "Vida Máxima", ignoreCase = true)
        replaced = replaced.replace("Daño de Ataque", "Dano de Ataque", ignoreCase = true)
        replaced = replaced.replace("Poder de Habilidad", "Poder de Habilidade", ignoreCase = true)
        replaced = replaced.replace("Velocidad de Ataque", "Velocidade de Ataque", ignoreCase = true)
        replaced = replaced.replace("Velocidad de Movimiento", "Velocidade de Movimento", ignoreCase = true)
        replaced = replaced.replace("Aceleración de Habilidad", "Aceleração de Habilidade", ignoreCase = true)
        replaced = replaced.replace("Probabilidad de Crítico", "Chance de Crítico", ignoreCase = true)
        replaced = replaced.replace("Daño Crítico", "Dano Crítico", ignoreCase = true)
        replaced = replaced.replace("Penetración de Armadura", "Penetração de Armadura", ignoreCase = true)
        replaced = replaced.replace("Penetración Mágica", "Penetração Mágica", ignoreCase = true)
        replaced = replaced.replace("Resistencia Mágica", "Resistência Mágica", ignoreCase = true)
        replaced = replaced.replace("Armadura", "Armadura", ignoreCase = true)
        replaced = replaced.replace("Robo de Vida", "Roubo de Vida", ignoreCase = true)
        replaced = replaced.replace("Omnivampirismo", "Omnivamp", ignoreCase = true)
        replaced = replaced.replace("Vampirismo", "Vamp", ignoreCase = true)
        replaced = replaced.replace("Daño Verdadero", "Dano Verdadeiro", ignoreCase = true)
        replaced = replaced.replace("Curación", "Cura", ignoreCase = true)
        replaced = replaced.replace("Escudo", "Escudo", ignoreCase = true)
        replaced = replaced.replace("Enfriamiento", "Tempo de Recarga", ignoreCase = true)
        replaced = replaced.replace("Pasiva", "Passiva", ignoreCase = true)
        replaced = replaced.replace("Habilidad", "Habilidade", ignoreCase = true)
        replaced = replaced.replace("Definitiva", "Ultimate", ignoreCase = true)
        replaced = replaced.replace("Inflige", "Causa", ignoreCase = true)
        replaced = replaced.replace("Aumenta", "Aumenta", ignoreCase = true)
        replaced = replaced.replace("Reduce", "Reduz", ignoreCase = true)
        replaced = replaced.replace("Otorga", "Concede", ignoreCase = true)
"""
if start_idx != -1 and end_idx != -1:
    content = content[:start_idx] + new_content + content[end_idx:]
    with open('app/src/main/java/com/example/util/Translator.kt', 'w') as f:
        f.write(content)
