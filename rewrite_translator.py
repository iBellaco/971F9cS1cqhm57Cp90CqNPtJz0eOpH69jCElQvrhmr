import re

with open('app/src/main/java/com/example/util/Translator.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# We want to rewrite trStr and tr.
new_trStr = """
@Composable
fun tr(key: String): String {
    val lang = LocalLanguage.current
    return trStr(if (lang == "auto") "es" else lang, key)
}

fun trStr(lang: String, key: String): String {
    val effectiveLang = if (lang == "auto") "es" else lang
    
    val direct = translations[effectiveLang]?.get(key)
    if (direct != null) return direct
    
    val dynamic = DynamicTranslations.get(effectiveLang, key)
    if (dynamic != null) return dynamic

    var replaced = key
    
    if (effectiveLang == "en") {
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
        // Categories
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

        // English -> Portuguese (since stats might be in English)
        replaced = replaced.replace("Max Health", "Vida Máxima", ignoreCase = true)
        replaced = replaced.replace("Attack Damage", "Dano de Ataque", ignoreCase = true)
        replaced = replaced.replace("Ability Power", "Poder de Habilidade", ignoreCase = true)
        replaced = replaced.replace("Attack Speed", "Velocidade de Ataque", ignoreCase = true)
        replaced = replaced.replace("Movement Speed", "Velocidade de Movimento", ignoreCase = true)
        replaced = replaced.replace("Ability Haste", "Aceleração de Hab.", ignoreCase = true)
        replaced = replaced.replace("Critical Rate", "Chance de Crítico", ignoreCase = true)
        replaced = replaced.replace("Critical Chance", "Chance de Crítico", ignoreCase = true)
        replaced = replaced.replace("Critical Damage", "Dano Crítico", ignoreCase = true)
        replaced = replaced.replace("Armor Penetration", "Penetração de Armadura", ignoreCase = true)
        replaced = replaced.replace("Magic Penetration", "Penetração Mágica", ignoreCase = true)
        replaced = replaced.replace("Magic Resist", "Resistência Mágica", ignoreCase = true)
        replaced = replaced.replace("Armor", "Armadura", ignoreCase = true)
        replaced = replaced.replace("Physical Vamp", "Vamp. Físico", ignoreCase = true)
        replaced = replaced.replace("Omni Vamp", "Vamp. Universal", ignoreCase = true)
        replaced = replaced.replace("Magic Vamp", "Vamp. Mágico", ignoreCase = true)
        replaced = replaced.replace("Vamp", "Vampirismo", ignoreCase = true)
        replaced = replaced.replace("True Damage", "Dano Verdadeiro", ignoreCase = true)
        replaced = replaced.replace("Healing", "Cura", ignoreCase = true)
        replaced = replaced.replace("Shield", "Escudo", ignoreCase = true)
        replaced = replaced.replace("Cooldown", "Tempo de Recarga", ignoreCase = true)
        replaced = replaced.replace("Passive", "Passiva", ignoreCase = true)
        replaced = replaced.replace("Ability", "Habilidade", ignoreCase = true)
        replaced = replaced.replace("Ultimate", "Ultimate", ignoreCase = true)
        replaced = replaced.replace("Deals", "Causa", ignoreCase = true)
        replaced = replaced.replace("Increases", "Aumenta", ignoreCase = true)
        replaced = replaced.replace("Reduces", "Reduz", ignoreCase = true)
        replaced = replaced.replace("Grants", "Concede", ignoreCase = true)
        
    } else if (effectiveLang == "es") {
        // English -> Spanish (since stats might be in English)
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
        replaced = replaced.replace("Physical Vamp", "Vamp. Físico", ignoreCase = true)
        replaced = replaced.replace("Omni Vamp", "Omnivampirismo", ignoreCase = true)
        replaced = replaced.replace("Magic Vamp", "Vamp. Mágico", ignoreCase = true)
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
    }
    
    return replaced
}
"""

content = re.sub(r'@Composable\s+fun tr\(key: String\): String \{.*', new_trStr, content, flags=re.DOTALL)

with open('app/src/main/java/com/example/util/Translator.kt', 'w', encoding='utf-8') as f:
    f.write(content)

