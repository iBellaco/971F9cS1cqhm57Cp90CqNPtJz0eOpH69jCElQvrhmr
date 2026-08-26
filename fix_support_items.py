import re
with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    data = f.read()

# Escudo de reliquia
data = re.sub(
    r'id = "relic_shield".*?iconUrl = "[^"]+"',
    '''id = "relic_shield",
            name = "Escudo reliquia",
            nameEn = "Relic Shield",
            category = "Artículos Básicos",
            goldCost = 500,
            stats = "+125 <font color='#1aff00'>salud máxima</font>",
            statsEn = "+125 Max Health",
            passive = "Tributo: Obtén 1 orbe de energía giratorio cada 30 s (máx. 3). Cuando estás cerca de un aliado, las habilidades dañinas y ataques consumen un orbe para otorgar oro y curación.\\nCentinela: Daño extra a los guardianes.\\nMisión: Gana oro para que se transforme en Baluarte de la Montaña.",
            passiveEn = "Tribute: Gain 1 encircling energy orb(s) every 30 seconds (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Health 20-80:1. Using abilities or attacks to damage enemy champions or structures.2. Attacking minions below 65% Health. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.Sentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.Restraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.\\nQuest: After earning 750 gold, this item upgrades into Bulwark of the Mountain and binds you and the ally with the most Tribute stacks as Perfect Partners.",
            coachTip = "El objeto ideal para los soportes de tipo tanque. Úsalo para curarte a ti y a tu aliado mientras ejecutas súbditos de la línea, acelerando tu ganancia de oro y manteniendo la vida alta.",
            coachTipEn = "The ideal item for tank supports. Use it to heal yourself and your ally while executing minions, accelerating your gold income and keeping your health high.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp"''',
    data, flags=re.DOTALL
)

# Hoz espectral
data = re.sub(
    r'id = "spectral_sickle".*?iconUrl = "[^"]+"',
    '''id = "spectral_sickle",
            name = "Hoz espectral",
            nameEn = "Spectral Sickle",
            category = "Artículos Básicos",
            goldCost = 500,
            stats = "+10 <font color='#ffa500'>daño de ataque</font> o +20 <font color='#9370db'>poder de habilidad</font> (Adaptable)",
            statsEn = "Versatile: Gain 10 Attack Damage or 20 Ability Power (Adaptive).",
            passive = "Tributo: Obtén 1 orbe de energía giratorio cada 30 s (máx. 3). Atacar campeones enemigos o estructuras consume orbes para darte oro y vida.\\nCentinela: Daño extra a guardianes.\\nMisión: Gana oro para que se transforme en Guadaña de la Niebla Negra.",
            passiveEn = "Versatile: Gain 10 Attack Damage or 20 Ability Power (Adaptive).\\nTribute: Gain 1 encircling energy orb(s) every 30 seconds (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Health 20-80:1. Using abilities or attacks to damage enemy champions or structures.2. Attacking minions below 65% Health. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.Sentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.Restraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.Quest: Earn 750 gold with this item to transform it into Black Mist Scythe and bind you and the ally with the most Tribute stacks as Perfect Partners.",
            coachTip = "Ideal para soportes basados en daño de ataque. Acosa a tus enemigos constantemente en fase de líneas para acumular oro rápidamente y mejorar tu objeto.",
            coachTipEn = "Ideal for AD-based supports. Harass your enemies constantly in the laning phase to quickly stack gold and upgrade your item.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753390656_spectral-sickle.webp"''',
    data, flags=re.DOTALL
)

# Anillo de revelación
data = re.sub(
    r'id = "ring_of_revelation".*?iconUrl = "[^"]+"',
    '''id = "ring_of_revelation",
            name = "Anillo de revelación",
            nameEn = "Ring of Revelation",
            category = "Artículos Básicos",
            goldCost = 500,
            stats = "+10 <font color='#ffa500'>daño de ataque</font> o +20 <font color='#9370db'>poder de habilidad</font> (Adaptable)",
            statsEn = "Versatile: Gain 10 Attack Damage or 20 Ability Power (Adaptive).",
            passive = "Tributo: Obtén orbes de energía. Usa habilidades o ataques para dañar campeones o estructuras y consumir orbes, ganando oro y vida.\\nCentinela: Daño extra a guardianes.\\nMisión: Gana 750 de oro para evolucionar a un objeto que permite encantar.",
            passiveEn = "Tribute: Gain 1 encircling energy orb(s) every 30 seconds (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Health 20-80:1. Using abilities or attacks to damage enemy champions or structures.2. Attacking minions below 65% Health. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.Sentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.Restraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.Quest: Earn 750 gold with this item to transform it into Black Mist Scythe and bind you and the ally with the most Tribute stacks as Perfect Partners.",
            coachTip = "El mejor objeto para soportes hechiceros. Asegúrate de usar tus habilidades para hostigar al enemigo y así completar tu misión lo más rápido posible.",
            coachTipEn = "The best item for enchanter/mage supports. Make sure to use your abilities to poke the enemy so you can complete your quest as quickly as possible.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753390605_ring-of-revelation.webp"''',
    data, flags=re.DOTALL
)

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w', encoding='utf-8') as f:
    f.write(data)
print("Updated support items.")
