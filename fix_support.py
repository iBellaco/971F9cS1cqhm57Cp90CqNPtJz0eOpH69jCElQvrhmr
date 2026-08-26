import re
with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    data = f.read()

def replace_item(data, item_id, new_block):
    # match from id = "item_id" to iconUrl = "..."
    pattern = r'id\s*=\s*"' + item_id + r'".*?iconUrl\s*=\s*"[^"]+"'
    return re.sub(pattern, new_block, data, flags=re.DOTALL)

rs = """id = "relic_shield",
            name = "Escudo de reliquia",
            nameEn = "Relic Shield",
            category = "Artículos Básicos",
            goldCost = 500,
            stats = "+125 <font color='#1aff00'>salud máxima</font>",
            statsEn = "+125 Max Health",
            passive = "Tributo: Gana 1 orbe de energía circundante cada 30 segundos (máximo 3 orbes). Mientras estés cerca de un aliado, las habilidades o ataques que dañen campeones o estructuras, o que maten a súbditos por debajo del 65% de salud, consumirán un orbe. Esto te otorgará oro y restaurará tu salud y la de tu aliado.\\nCentinela: Inflige daño extra a los centinelas revelados.\\nMisión: Gana 750 de oro para evolucionar este objeto a Baluarte de la Montaña.",
            passiveEn = "Tribute: Gain 1 encircling energy orb(s) every 30 seconds (max 3 orbs). While near an ally, actions will trigger Tribute to grant you 65 gold and restore your Health.\\nQuest: After earning 750 gold, this item upgrades into Bulwark of the Mountain.",
            coachTip = "El objeto ideal para los soportes de tipo tanque. Úsalo para curarte a ti y a tu aliado mientras ejecutas súbditos de la línea, acelerando tu ganancia de oro y manteniendo la vida alta.",
            coachTipEn = "The ideal item for tank supports. Use it to heal yourself and your ally while executing minions, accelerating your gold income and keeping your health high.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp"
"""

ss = """id = "spectral_sickle",
            name = "Hoz espectral",
            nameEn = "Spectral Sickle",
            category = "Artículos Básicos",
            goldCost = 500,
            stats = "+10 <font color='#ffa500'>daño de ataque</font> o +20 <font color='#9370db'>poder de habilidad</font> (Adaptable)",
            statsEn = "+10 Attack Damage or +20 Ability Power (Adaptive)",
            passive = "Versátil: Otorga daño de ataque o poder de habilidad.\\nTributo: Gana 1 orbe de energía giratorio cada 30 s (hasta 3). Atacar campeones o estructuras consume orbes para darte oro y curación.\\nCentinela: Daño extra a guardianes.\\nMisión: Gana 750 de oro para evolucionar a Guadaña de la Niebla Negra.",
            passiveEn = "Versatile: Gain 10 Attack Damage or 20 Ability Power (Adaptive).\\nTribute: Gain 1 encircling energy orb(s) every 30 seconds (max 3 orbs). While near an ally, actions trigger Tribute granting gold.\\nQuest: Earn 750 gold to transform into Black Mist Scythe.",
            coachTip = "Ideal para soportes basados en daño de ataque. Acosa a tus enemigos constantemente en fase de líneas para acumular oro rápidamente y mejorar tu objeto.",
            coachTipEn = "Ideal for AD-based supports. Harass your enemies constantly in the laning phase to quickly stack gold and upgrade your item.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753390656_spectral-sickle.webp"
"""

rr = """id = "ring_of_revelation",
            name = "Anillo de revelación",
            nameEn = "Ring of Revelation",
            category = "Artículos Básicos",
            goldCost = 500,
            stats = "+10 <font color='#ffa500'>daño de ataque</font> o +20 <font color='#9370db'>poder de habilidad</font> (Adaptable)",
            statsEn = "+10 Attack Damage or +20 Ability Power (Adaptive)",
            passive = "Versátil: Otorga daño de ataque o poder de habilidad.\\nTributo: Obtén orbes de energía. Usa habilidades o ataques para dañar campeones o estructuras y consumir orbes, ganando oro y vida.\\nCentinela: Daño extra a guardianes.\\nMisión: Gana 750 de oro para evolucionar este objeto a Espejo de Espejismo.",
            passiveEn = "Versatile: Gain 10 Attack Damage or 20 Ability Power (Adaptive).\\nTribute: Gain 1 encircling energy orb(s) every 30 seconds.\\nQuest: Earn 750 gold with this item to transform it.",
            coachTip = "El mejor objeto para soportes hechiceros. Asegúrate de usar tus habilidades para hostigar al enemigo y así completar tu misión lo más rápido posible.",
            coachTipEn = "The best item for enchanter/mage supports. Make sure to use your abilities to poke the enemy so you can complete your quest as quickly as possible.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753390605_ring-of-revelation.webp"
"""

data = replace_item(data, 'relic_shield', rs.strip())
data = replace_item(data, 'spectral_sickle', ss.strip())
data = replace_item(data, 'ring_of_revelation', rr.strip())

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w', encoding='utf-8') as f:
    f.write(data)
print("done")
