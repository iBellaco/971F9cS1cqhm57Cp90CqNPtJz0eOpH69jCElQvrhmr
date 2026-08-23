import re

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    text = f.read()

translations = [
    ("Empowered Ultimate Ability", "Tu habilidad definitiva obtiene un 10% de daño, curación y escudo adicionales (5% para daño en área). Las bajas reducen su enfriamiento un 7%."),
    ("Increase Mana", "Golpear a un campeón enemigo con una habilidad o ataque potenciado aumenta tu maná máximo en 30, hasta 300."),
    ("Empowered plant effects", "Destruir una planta otorga 10 de oro extra y potencia los efectos de los frutos de miel, brotes del vidente y piñas explosivas."),
    ("Gain short-range movement while Flash is on cooldown", "Mientras Destello está en enfriamiento, es reemplazado por Hextello. Permite canalizar para un pequeño salto."),
    ("Reduces ability cooldowns", "Al nivel 1 obtienes +5 aceleración de habilidad. Al nivel 5, +5 adicional. Al nivel 9, los impactos de habilidades reducen su propio enfriamiento."),
    ("Increase Movement Speed", "Otorga +2% de velocidad de movimiento base y aumenta las bonificaciones de velocidad un 7%."),
    ("Gain Attack Damage/Ability Power at high Health", "Mientras tengas más del 65% de vida, obtienes daño de ataque o poder de habilidad adicional adaptable."),
    ("Abilities deal bonus damage", "Tus habilidades queman a los enemigos, infligiendo daño mágico adicional al cabo de 1 segundo (8s enfriamiento)."),
    ("Spells increase Movement Speed", "Tras usar un hechizo de invocador, ganas entre un 10% y 40% de velocidad de movimiento por 3 segundos."),
    ("Increase Attack Damage/Ability Power over time", "A partir del minuto 6, obtienes daño adaptable progresivo a lo largo de la partida."),
    ("Plant fruits after destroying one", "Destruir una planta te otorga una semilla para plantar otra planta artificial después (reemplaza tu baratija temporalmente)."),
    ("Triggers when in stealth or dashing", "Inflige daño verdadero adicional tras usar un deslizamiento, salto, teleportación o al salir de sigilo."),
    ("Targets movement-impaired enemies", "Infliges daño verdadero adicional a campeones enemigos que tengan su movimiento impedido."),
    ("Kills increase Attack Damage/Ability Power", "Los derribos de campeones y monstruos épicos otorgan daño adaptable adicional, acumulable hasta 8 veces."),
    ("Vision control increases Attack Damage/Ability Power", "Derribar centinelas enemigos otorga daño adaptable adicional y genera un centinela zombi."),
    ("Kills increase Item Ability Haste", "Otorgar derribos a campeones y monstruos épicos aumenta tu aceleración de objetos, acumulable hasta 5 veces."),
    ("Increase damage when low in Health", "Las bajas de campeones restauran 10% de tu vida y maná perdidos, y otorgan velocidad de movimiento."),
    ("Increase damage to low Health enemies", "Tus ataques infligen un 8% de daño adaptable adicional a campeones con menos del 40% de vida."),
    ("When health is lower than 60%", "Cuando tu vida baja del 60%, tus ataques infligen más daño (máximo al 30% de vida)."),
    ("Increase bonus Attack Speed", "Otorgar derribos aumenta tu velocidad de ataque adicional, acumulable permanentemente."),
    ("Increase Omnivamp", "Otorgar derribos aumenta tu omnivampirismo de manera permanente."),
    ("Increase Tenacity and Slow Resist", "Ganas tenacidad y resistencia a ralentizaciones, con más acumulaciones por derribos."),
    ("Attacks deal on-hit damage", "Tus ataques infligen daño mágico extra al impactar a campeones enemigos."),
    ("Anti-Burst Damage", "Al recibir daño de un campeón, las próximas habilidades o ataques entrantes infligen menos daño durante 1.5s."),
    ("Increase sustain", "Otorga +5 vida cada 5s. Tras recibir daño de campeón, regeneras vida extra según la que te falte."),
    ("Increase max Health", "Por cada 3 súbditos o monstruos asesinados cerca, ganas vida máxima permanente."),
    ("Team Heal", "Tus ataques y habilidades curan a tu campeón y al aliado cercano más herido."),
    ("Destroy turrets faster", "Estar cerca de una torreta carga un golpe que inflige daño físico masivo a la estructura."),
    ("Increase survivability when crowd controlled", "Ganas tenacidad base. Al ser inmovilizado ganas armadura y resistencia mágica temporales."),
    ("Empowered heals and shields", "Tus curaciones y escudos son un 5% más fuertes (o 15% si el objetivo tiene menos del 40% de vida).")
]

for eng_snippet, span in translations:
    # replace any string that starts with eng_snippet (or contains it) with span
    # basically replace the whole description value
    text = re.sub(r'description = "' + re.escape(eng_snippet) + r'[^"]*"', r'description = "' + span + '"', text)

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'w') as f:
    f.write(text)
