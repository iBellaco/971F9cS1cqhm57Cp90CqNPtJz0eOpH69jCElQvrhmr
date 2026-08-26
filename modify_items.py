import re

file_path = "app/src/main/java/com/example/data/WildRiftItemsData.kt"

with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

items_to_modify = {
    "tear_of_the_goddess_wild_rift": {
        "name": "Lágrima de la diosa",
        "stats": "+200 <font color='#00BFFF'>maná máximo</font> • +5 <font color='#FFA500'>celeridad de habilidad</font>",
        "passive": "Asombro: se reembolsa el 10% del maná gastado.\\nCarga de maná: aumenta el maná máximo en 6 cada vez que se gasta maná. Tiene un límite de 700 maná adicional. Se activa hasta 3 veces cada 10 segundos. Solo puedes llevar un objeto Lágrima de la Diosa a la vez.",
        "coachTip": "Compra este objeto al principio si usas un campeón que depende mucho del maná, ya que necesitas tiempo para cargarlo completamente y evolucionarlo."
    },
    "shimmering_spark": {
        "name": "Chispa fulgurante",
        "stats": "+50 <font color='#4CAF50'>vida máxima</font>",
        "passive": "Inmolar: Inflige 5 - 10 de daño mágico a los enemigos cercanos por segundo.",
        "coachTip": "Excelente opción temprana para ayudar a los tanques con su limpieza de súbditos o monstruos de la jungla."
    },
    "null_magic_mantle": {
        "name": "Manto de anulación de magia",
        "stats": "+20 <font color='#E0B0FF'>resistencia mágica</font>",
        "passive": "",
        "coachTip": "Objeto básico para reducir el daño de campeones con poder de habilidad. Ideal en los primeros niveles contra magos."
    },
    "ruby_crystal": {
        "name": "Cristal de rubí",
        "stats": "+150 <font color='#4CAF50'>vida máxima</font>",
        "passive": "",
        "coachTip": "Una de las mejores compras básicas de supervivencia, te da vida plana útil contra cualquier tipo de daño."
    },
    "cloth_armor": {
        "name": "Armadura de tela",
        "stats": "+20 <font color='#FFD700'>armadura</font>",
        "passive": "",
        "coachTip": "El componente defensivo ideal si te enfrentas a atacantes físicos (AD) en el juego temprano."
    },
    "surging_scales_wild_rift": {
        "name": "Escamas aluviales",
        "stats": "+25 <font color='#FFD700'>armadura</font>",
        "passive": "Fervor: Gana 5% de velocidad de movimiento en combate.",
        "coachTip": "Otorga armadura y movilidad en combate, útil para campeones que necesitan persecución o reposicionamiento."
    },
    "glacial_shroud_wild_rift": {
        "name": "Sudario glacial",
        "stats": "+20 <font color='#FFD700'>armadura</font> • +150 <font color='#00BFFF'>maná máximo</font> • +10 <font color='#FFA500'>celeridad de habilidad</font>",
        "passive": "",
        "coachTip": "Gran objeto intermedio para tanques que usan mucho maná y dependen de sus habilidades."
    },
    "negatron_cloak_wild_rift": {
        "name": "Capa negatrón",
        "stats": "+45 <font color='#E0B0FF'>resistencia mágica</font>",
        "passive": "",
        "coachTip": "Pico de resistencia mágica esencial. Cómpralo cuando enfrentes ráfagas severas de daño mágico."
    },
    "winged_moonplate_wild_rift": {
        "name": "Armadura lunar alada",
        "stats": "+150 <font color='#4CAF50'>vida máxima</font>",
        "passive": "Vuelo: +5% de velocidad de movimiento.",
        "coachTip": "Mejora tu capacidad de supervivencia y te da movilidad extra, excelente para tanques que necesitan alcanzar objetivos."
    },
    "jaurim_s_fist_wild_rift": {
        "name": "Puño de Jaurim",
        "stats": "+15 <font color='#FF4500'>daño de ataque</font> • +175 <font color='#4CAF50'>vida máxima</font>",
        "passive": "",
        "coachTip": "Componente equilibrado para luchadores que necesitan durabilidad sin sacrificar su presión de daño."
    },
    "bramble_vest_wild_rift": {
        "name": "Chaleco de zarzas",
        "stats": "+30 <font color='#FFD700'>armadura</font>",
        "passive": "Espinas: Al ser atacado, refleja 3 de daño mágico + 8% de armadura adicional y aplica 50% de Heridas Graves por 3 segundos.",
        "coachTip": "Objeto defensivo clave si te enfrentas a campeones con mucha curación o robo de vida. ¡Cómpralo rápido contra ellos!"
    },
    "chain_vest_wild_rift": {
        "name": "Chaleco de cadenas",
        "stats": "+40 <font color='#FFD700'>armadura</font>",
        "passive": "",
        "coachTip": "Gran bloque de armadura sólida. Perfecto para mitigar daño de asesinos AD o tiradores."
    },
    "warden_s_mail_wild_rift": {
        "name": "Malla del guardián",
        "stats": "+35 <font color='#FFD700'>armadura</font>",
        "passive": "Acero frío: Reduce la velocidad de ataque de quienes te golpean con ataques básicos un 15% durante 1.5s.",
        "coachTip": "El counter definitivo para campeones que dependen de su velocidad de ataque (como Jinx, Vayne o Yasuo)."
    },
    "giant_s_belt_wild_rift": {
        "name": "Cinturón de gigante",
        "stats": "+325 <font color='#4CAF50'>vida máxima</font>",
        "passive": "",
        "coachTip": "Una inyección enorme de vida, excelente contra daño verdadero y todo tipo de daño mixto."
    },
    "kindlegem_wild_rift": {
        "name": "Gema avivadora",
        "stats": "+175 <font color='#4CAF50'>vida máxima</font> • +10 <font color='#FFA500'>celeridad de habilidad</font>",
        "passive": "",
        "coachTip": "Estadísticas muy eficientes. Te permite lanzar hechizos con mayor frecuencia mientras te hace más resistente."
    },
    "spectre_s_cowl_wild_rift": {
        "name": "Hábito del espectro",
        "stats": "+175 <font color='#4CAF50'>vida máxima</font> • +25 <font color='#E0B0FF'>resistencia mágica</font>",
        "passive": "Espectral: Recibir daño de un campeón otorga 150% de regeneración de vida básica por 10s.",
        "coachTip": "Fantástico para sostenerte en línea contra campeones de daño mágico que te hacen daño de desgaste (poke)."
    },
    "bami_s_cinder_wild_rift": {
        "name": "Rescoldo de Bami",
        "stats": "+225 <font color='#4CAF50'>vida máxima</font>",
        "passive": "Inmolar: Inflige 12 - 25 de daño mágico por segundo a enemigos cercanos. Inflige 25% extra a súbditos y monstruos.",
        "coachTip": "Vital para tanques de la jungla o top, ya que aumenta dramáticamente tu capacidad para limpiar oleadas o campamentos."
    },
    "lol_wild_rift_seeker_s_armguard": {
        "name": "Brazalete de la buscadora",
        "stats": "+30 <font color='#FFD700'>armadura</font> • +30 <font color='#00FFFF'>poder de habilidad</font>",
        "passive": "Éstasis (Activa): Te vuelve invulnerable e inalcanzable por 2.5s, pero sin poder moverte ni usar habilidades (reutilización 120s).",
        "coachTip": "La activa del estasis es la clave. Úsalo para esquivar ráfagas mortales (como la ultimate de Zed o Fizz)."
    },
    "sheen_wild_rift": {
        "name": "Brillo",
        "stats": "+10 <font color='#FFA500'>celeridad de habilidad</font>",
        "passive": "Espada Encantada: Tras usar una habilidad, tu siguiente ataque en 10s inflige 100% de Daño de Ataque básico como daño físico adicional (reutilización 1.5s).",
        "coachTip": "Un gran aumento de daño para campeones que intercalan habilidades con ataques básicos (como Ezreal o Camille)."
    },
    "catalyst_of_aeons_wild_rift": {
        "name": "Catalizador de eones",
        "stats": "+200 <font color='#4CAF50'>vida máxima</font> • +250 <font color='#00BFFF'>maná máximo</font>",
        "passive": "Eternidad: Restaura maná equivalente al 15% del daño recibido. Gastar maná restaura 20% del costo en vida, hasta 15 por hechizo.",
        "coachTip": "Otorga excelente sustento en línea. Mientras más maná gastes, más vida recuperas, y viceversa."
    },
    "verdant_barrier": {
        "name": "Barrera frondosa",
        "stats": "+25 <font color='#00FFFF'>poder de habilidad</font> • +25 <font color='#E0B0FF'>resistencia mágica</font>",
        "passive": "Gracia: Cada 60s, ganas escudo antihechizos que bloquea la siguiente habilidad.",
        "coachTip": "Una opción defensiva clave para magos contra composiciones con mucho daño mágico o un enganche clave (como la Q de Blitzcrank)."
    },
    "quicksilver_sash": {
        "name": "Fajín de mercurio",
        "stats": "+25 <font color='#E0B0FF'>resistencia mágica</font>",
        "passive": "Mercurio (Activa): Elimina todos los efectos de control de masas (excepto levantamientos y empujes) y otorga 50% de velocidad de movimiento por 1.5s. (reutilización 60s).",
        "coachTip": "Cómpralo estrictamente cuando te enfrentes a un control de masas letal (como la ultimate de Malzahar, Leona o Ashe)."
    }
}

for item_id, data in items_to_modify.items():
    # Regex to find the item block
    pattern = r'(id\s*=\s*"' + item_id + r'".*?coachTip\s*=\s*")(.*?)(".*?\n\s*\))'
    
    def replacer(match):
        block = content[match.start():match.end()]
        
        # Replace name
        block = re.sub(r'(name\s*=\s*").*?(")', r'\g<1>' + data["name"] + r'\g<2>', block)
        
        # Replace stats
        block = re.sub(r'(stats\s*=\s*").*?(")', r'\g<1>' + data["stats"] + r'\g<2>', block)
        
        # Replace passive
        block = re.sub(r'(passive\s*=\s*").*?(")', r'\g<1>' + data["passive"] + r'\g<2>', block)
        
        # Replace coachTip
        block = re.sub(r'(coachTip\s*=\s*").*?(")', r'\g<1>' + data["coachTip"] + r'\g<2>', block)
        
        return block
    
    # We need to compile the pattern with re.DOTALL to match across lines
    pattern = re.compile(r'(id\s*=\s*"' + item_id + r'".*?\)(?=\s*,|\s*\]|\s*\n))', re.DOTALL)
    
    def block_replacer(match):
        block = match.group(1)
        block = re.sub(r'(name\s*=\s*").*?(")', r'\g<1>' + data["name"] + r'\g<2>', block, flags=re.DOTALL)
        block = re.sub(r'(stats\s*=\s*").*?(")', r'\g<1>' + data["stats"] + r'\g<2>', block, flags=re.DOTALL)
        block = re.sub(r'(passive\s*=\s*").*?(")', r'\g<1>' + data["passive"] + r'\g<2>', block, flags=re.DOTALL)
        block = re.sub(r'(coachTip\s*=\s*").*?(")', r'\g<1>' + data["coachTip"] + r'\g<2>', block, flags=re.DOTALL)
        return block

    content = pattern.sub(block_replacer, content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Items modified.")
