with open('app/src/main/java/com/example/util/Translator.kt', 'r') as f:
    content = f.read()

start_idx = content.find('    } else if (effectiveLang == "es") {')
end_idx = content.find('    return replaced\n}')

new_content = """    } else if (effectiveLang == "es") {
        // English -> Spanish (since stats might be in English)
        val itemNameEs = itemNamesEnToEs[key]
        if (itemNameEs != null) return itemNameEs

        // Categories
        replaced = replaced.replace("Physical Damage", "Daño Físico", ignoreCase = true)
        replaced = replaced.replace("Magic Damage", "Daño Mágico", ignoreCase = true)
        replaced = replaced.replace("Defense", "Defensa", ignoreCase = true)
        replaced = replaced.replace("Magic", "Magia", ignoreCase = true)
        replaced = replaced.replace("Boots", "Botas", ignoreCase = true)
        replaced = replaced.replace("Basic", "Básicos", ignoreCase = true)
        replaced = replaced.replace("Mid Tier", "Nivel Medio", ignoreCase = true)
        replaced = replaced.replace("Enchants", "Encantamientos", ignoreCase = true)
        replaced = replaced.replace("Patch", "Parche", ignoreCase = true)
        
        replaced = replaced.replace("Max Health", "Vida Máxima", ignoreCase = true)
        replaced = replaced.replace("Attack Damage", "Daño de Ataque", ignoreCase = true)
        replaced = replaced.replace("Ability Power", "Poder de Habilidad", ignoreCase = true)
        replaced = replaced.replace("Attack Speed", "Velocidad de Ataque", ignoreCase = true)
        replaced = replaced.replace("Movement Speed", "Veloc. de Movimiento", ignoreCase = true)
        replaced = replaced.replace("Ability Haste", "Aceleración de Hab.", ignoreCase = true)
        replaced = replaced.replace("Critical Rate", "Probabilidad de Crítico", ignoreCase = true)
        replaced = replaced.replace("Critical Chance", "Prob. de Crítico", ignoreCase = true)
        replaced = replaced.replace("Critical Damage", "Daño Crítico", ignoreCase = true)
        replaced = replaced.replace("Armor Penetration", "Penetración de Armadura", ignoreCase = true)
        replaced = replaced.replace("Magic Penetration", "Penetración Mágica", ignoreCase = true)
        replaced = replaced.replace("Magic Resist", "Resistencia Mágica", ignoreCase = true)
        replaced = replaced.replace("Armor", "Armadura", ignoreCase = true)
        replaced = replaced.replace("Life Steal", "Robo de Vida", ignoreCase = true)
        replaced = replaced.replace("Omnivamp", "Omnivampirismo", ignoreCase = true)
        replaced = replaced.replace("Vamp", "Vampirismo", ignoreCase = true)
        replaced = replaced.replace("True Damage", "Daño Verdadero", ignoreCase = true)
        replaced = replaced.replace("Healing", "Curación", ignoreCase = true)
        replaced = replaced.replace("Shield", "Escudo", ignoreCase = true)
        replaced = replaced.replace("Cooldown", "Enfriamiento", ignoreCase = true)
        replaced = replaced.replace("Passive", "Pasiva", ignoreCase = true)
        replaced = replaced.replace("Ability", "Habilidad", ignoreCase = true)
        replaced = replaced.replace("Ultimate", "Definitiva", ignoreCase = true)
        replaced = replaced.replace("Deals", "Inflige", ignoreCase = true)
        replaced = replaced.replace("Increases", "Aumenta", ignoreCase = true)
        replaced = replaced.replace("Reduces", "Reduce", ignoreCase = true)
        replaced = replaced.replace("Grants", "Otorga", ignoreCase = true)
"""
if start_idx != -1 and end_idx != -1:
    content = content[:start_idx] + new_content + content[end_idx:]
    with open('app/src/main/java/com/example/util/Translator.kt', 'w') as f:
        f.write(content)
