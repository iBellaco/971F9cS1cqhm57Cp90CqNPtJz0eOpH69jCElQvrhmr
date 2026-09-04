import re

file_path = 'app/src/main/java/com/example/data/WildRiftItemsData.kt'
with open(file_path, 'r', encoding='utf-8') as f:
    content = f.read()

new_items_code = """
        WildRiftItem(
            id = "kraken_slayer",
            name = "Matakrakens",
            nameEn = "Kraken Slayer",
            category = "Objetos con Daños Físicos",
            goldCost = 2800,
            stats = "+40 Daño de Ataque • +30% Velocidad de Ataque",
            statsEn = "+40 Attack Damage • +30% Attack Speed",
            passive = "Paso de nube: +5% de velocidad de movimiento.\\nDerríbalo: Cada tercer ataque inflige de 120 a 160 de daño físico adicional (aumenta según la vida faltante del objetivo).",
            passiveEn = "Cloud Stride: +5% Move Speed.\\nBring it Down: Every third attack deals 120-160 bonus physical damage, increased by missing health.",
            coachTip = "Este objeto potencia tus ataques básicos proporcionando daño adicional, velocidad de ataque y una mejora de movilidad. Periódicamente tus golpes infligen daño adicional que escala con la salud faltante del objetivo, haciéndolo fuerte tanto contra tanques como para rematar enemigos con poca vida.",
            coachTipEn = "This item boosts your basic attacks by providing extra damage, attack speed, and a mobility bonus for better positioning. Periodically your hits deal bonus damage that scales with the target’s missing health.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630263_kraken-slayer.webp"
        ),
        WildRiftItem(
            id = "stridebreaker",
            name = "Cortasendas",
            nameEn = "Stridebreaker",
            category = "Objetos con Daños Físicos",
            goldCost = 3100,
            stats = "+400 Vida Máxima • +40 Daño de Ataque • +25% Velocidad de Ataque",
            statsEn = "+400 Max Health • +40 Attack Damage • +25% Attack Speed",
            passive = "Onda de choque rompedora (Activa): Actívalo para deslizarte una corta distancia y ralentizar a los enemigos.\\nZancada: Otorga velocidad de movimiento adicional.",
            passiveEn = "Breaking Shockwave (Active): Activate to dash a short distance and slow enemies.\\nStride: Gain Movement Speed when dealing physical damage.",
            coachTip = "Este objeto combina movilidad, daño y control de masas, facilitando mantenerse sobre los objetivos. Su activa te permite realizar un deslizamiento corto y ralentizar enormemente a los enemigos cercanos.",
            coachTipEn = "This item combines mobility, damage, and crowd control, making it easier to stick to your targets. Its active lets you dash a short distance, damage nearby enemies, and heavily slow them.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_stridebreaker.webp"
        ),
        WildRiftItem(
            id = "goredrinker",
            name = "Chupasangre",
            nameEn = "Goredrinker",
            category = "Objetos con Daños Físicos",
            goldCost = 3100,
            stats = "+350 Vida Máxima • +40 Daño de Ataque • +15 Aceleración de Habilidad",
            statsEn = "+350 Max Health • +40 Attack Damage • +15 Ability Haste",
            passive = "Chupasangre: Otorga 8% de Omnivampirismo.\\nCorte sediento (Activa): Inflige daño a enemigos cercanos y restaura vida por cada campeón golpeado.",
            passiveEn = "Goredrink: Gain 8% Omni Vamp.\\nThirsting Slash (Active): Deal physical damage to nearby enemies. Restore Health for each enemy champion hit.",
            coachTip = "Este objeto es ideal para luchadores en combates prolongados. Otorga Omnivampirismo y su activa inflige daño de área y restaura salud en base a la cantidad de campeones enemigos golpeados.",
            coachTipEn = "This item is perfect for fighters who excel in extended combat. It grants Omni Vamp, while its active ability deals area physical damage and restores health based on the number of enemy champions hit.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389878_goredrinker.webp"
        ),
        WildRiftItem(
            id = "guinsoo_s_rageblade",
            name = "Hoja de furia de Guinsoo",
            nameEn = "Guinsoo's Rageblade",
            category = "Objetos con Daños Físicos",
            goldCost = 3100,
            stats = "+30% Velocidad de Ataque",
            statsEn = "+30% Attack Speed",
            passive = "Oleada: Otorga velocidad de movimiento.\\nCaos: Daño adaptable.\\nIra: Convierte prob. de crítico en daño mágico.\\nGolpe ferviente: Acumula velocidad de ataque e impactos adicionales.",
            passiveEn = "Surge: Gain Move Speed.\\nChaos: Gain Adaptive Force.\\nWrath: Attacks deal magic damage based on crit.\\nSeething Strike: Attacks grant stacking Attack Speed and trigger extra on-hit effects.",
            coachTip = "Perfecto para builds de impactos (on-hit) y alta velocidad de ataque. Convierte estadísticas de críticos en daño mágico consistente y aumenta gradualmente tu velocidad de ataque.",
            coachTipEn = "Perfect for on-hit and high-attack-speed builds. It converts crit-focused stats into consistent on-hit magic damage, provides a powerful ramp of attack speed and stacking bursts.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1768000022_abb24fd724faa77b82baf985dea956b8eae9f31a-512x512.webp"
        ),
        WildRiftItem(
            id = "overlord_s_bloodmail",
            name = "Cota de sangre del soberano",
            nameEn = "Overlord's Bloodmail",
            category = "Objetos con Daños Físicos",
            goldCost = 3200,
            stats = "+450 Vida Máxima • +30 Daño de Ataque",
            statsEn = "+450 Max Health • +30 Attack Damage",
            passive = "Tiranía: Obtienes Daño de Ataque adicional igual al 2.5% de tu vida extra.\\nRepresalia: Aumenta tu daño según la vida que te falte.",
            passiveEn = "Tyranny: Gain Attack Damage equal to 2.5% of your bonus Health.\\nRetribution: Gain up to 9% increased Attack Damage based on missing Health.",
            coachTip = "Convierte la vida adicional en poder de ataque y potencia tu daño cuando tu salud baja a niveles peligrosos. Es ideal para luchadores agresivos que priorizan objetos de vida.",
            coachTipEn = "Converts bonus health into attack power and ramps up your damage when you drop into dangerous HP ranges. It suits bruisers and solo laners who stack health and embrace high-risk skirmishes.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630274_overlords-bloodmail.webp"
        ),
        WildRiftItem(
            id = "mercurial_scimitar",
            name = "Cimitarra mercurial",
            nameEn = "Mercurial Scimitar",
            category = "Objetos con Daños Físicos",
            goldCost = 3100,
            stats = "+45 Daño de Ataque • +10% Vampirismo Físico • +40 Resistencia Mágica",
            statsEn = "+45 Attack Damage • +10% Physical Vamp • +40 Magic Resistance",
            passive = "Fajín de mercurio (Activa): Elimina todas las mermas de control de masas.\\nPerseverancia: Tras usar el fajín, otorga tenacidad temporal.",
            passiveEn = "Quicksilver Sash (Active): Removes all crowd control debuffs.\\nPerseverance: Grants temporary Tenacity after use.",
            coachTip = "Diseñado para contrarrestar el control de masas. Su activa elimina efectos inhabilitantes y otorga brevemente inmunidad a los mismos. Fundamental para tiradores y asesinos contra composiciones de alto CC.",
            coachTipEn = "Designed to counter crowd control. Its active removes most disabling effects and briefly grants immunity to follow-up control. Excellent for marksmen and assassins against heavy-CC teams.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783568239_3139_11zon.webp"
        ),
        WildRiftItem(
            id = "galeforce",
            name = "Viento huracanado",
            nameEn = "Galeforce",
            category = "Objetos con Daños Físicos",
            goldCost = 3100,
            stats = "+50 Daño de Ataque • +25% Tasa Crítica • +15% Velocidad de Ataque • +5% Vel. de Movimiento",
            statsEn = "+50 Attack Damage • +25% Critical Rate • +15% Attack Speed • +5% Move Speed",
            passive = "Borrasca (Activa): Te deslizas en la dirección del objetivo y disparas misiles al enemigo con menos vida.",
            passiveEn = "Cloudburst (Active): Dash in a target direction and fire missiles at the lowest Health enemy.",
            coachTip = "Mejora significativamente la movilidad de los tiradores otorgando un deslizamiento que dispara proyectiles. Perfecto para rematar objetivos y esquivar habilidades vitales.",
            coachTipEn = "Greatly improves the mobility of marksmen by granting a dash that also fires projectiles at the lowest-health nearby target. Perfect for finishing enemies and repositioning.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_galeforce.webp"
        ),"""

# Let's find the closing `    )` of the list
match = re.search(r'iconUrl = "https://wr-meta\.com/uploads/posts/2025-07/1753390656_spectral-sickle\.webp"\n\s*\),*\n(\s*\))', content)
if match:
    idx = match.start(1)
    new_content = content[:idx] + new_items_code + '\n' + content[idx:]
    with open(file_path, 'w', encoding='utf-8') as f:
        f.write(new_content)
    print("Items injected successfully at the end of the list.")
else:
    print("Could not find the end of the list using regex.")
