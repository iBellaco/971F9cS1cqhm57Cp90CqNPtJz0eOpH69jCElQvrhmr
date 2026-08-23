import re

with open('app/src/main/java/com/example/util/Translator.kt', 'r') as f:
    text = f.read()

fallback_logic_old = """    // Dynamic patch replacement fallback
    if (lang == "en" || lang == "pt") {
        if (key.startsWith("Parche ")) {
            return "Patch " + key.substring("Parche ".length)
        } else if (key.startsWith("parche ")) {
            return "patch " + key.substring("parche ".length)
        } else if (key == "Parche") {
            return "Patch"
        } else if (key == "parche") {
            return "patch"
        }
    }
    return key"""

fallback_logic_new = """    // Dynamic text replacement for untranslated lore/stats
    var replaced = key
    if (lang == "en") {
        replaced = replaced.replace("Parche", "Patch", ignoreCase = true)
        replaced = replaced.replace("Vida Máxima", "Max Health", ignoreCase = true)
        replaced = replaced.replace("Daño de Ataque", "Attack Damage", ignoreCase = true)
        replaced = replaced.replace("Daño Físico", "Physical Damage", ignoreCase = true)
        replaced = replaced.replace("Daño Mágico", "Magic Damage", ignoreCase = true)
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
    } else if (lang == "pt") {
        replaced = replaced.replace("Parche", "Patch", ignoreCase = true)
        replaced = replaced.replace("Vida Máxima", "Vida Máxima", ignoreCase = true)
        replaced = replaced.replace("Daño de Ataque", "Dano de Ataque", ignoreCase = true)
        replaced = replaced.replace("Daño Físico", "Dano Físico", ignoreCase = true)
        replaced = replaced.replace("Daño Mágico", "Dano Mágico", ignoreCase = true)
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
        replaced = replaced.replace("Omnivampirismo", "Vampirismo Universal", ignoreCase = true)
        replaced = replaced.replace("Vampirismo", "Vampirismo", ignoreCase = true)
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
    }
    return replaced"""

text = text.replace(fallback_logic_old, fallback_logic_new)

with open('app/src/main/java/com/example/util/Translator.kt', 'w') as f:
    f.write(text)

