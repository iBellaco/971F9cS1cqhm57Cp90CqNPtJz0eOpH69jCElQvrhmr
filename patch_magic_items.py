import re

file_path = "app/src/main/java/com/example/data/WildRiftItemsData.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

updates = {
    "ring_of_revelation": {
        "name": "Anillo de la revelación",
        "category": "Objetos de Nivel Medio",
        "stats": "+10 <font color='#FFA500'>daño de ataque</font> o +20 <font color='#9370db'>poder de habilidad</font> (Adaptable)",
        "passive": "Versátil: Otorga daño de ataque o poder de habilidad.\\nTributo: Obtén orbes de energía. Usa habilidades o ataques para dañar campeones o estructuras y consumir orbes, ganando oro y vida.\\nCentinela: Daño extra a guardianes.\\nMisión: Gana 750 de oro para evolucionar este objeto a Espejo de Espejismo.",
        "coachTip": "El mejor objeto inicial para soportes hechiceros. Asegúrate de usar tus habilidades para hostigar al enemigo constantemente y así completar tu misión lo más rápido posible."
    },
    "amplifying_tome": {
        "name": "Tomo amplificador",
        "category": "Objetos de Nivel Medio",
        "stats": "+20 <font color='#9370db'>poder de habilidad</font>",
        "passive": "",
        "coachTip": "Objeto mágico básico para cualquier mago o asesino de AP. Aporta daño crudo para fortalecer tus habilidades en la fase de líneas temprana."
    },
    "stinger_wild_rift": {
        "name": "Aguijón",
        "category": "Objetos de Nivel Medio",
        "stats": "+30% <font color='#FFFF00'>velocidad de ataque</font> • +10 <font color='#FFA500'>celeridad de habilidad</font>",
        "passive": "",
        "coachTip": "Componente esencial para atacantes rápidos que también necesitan celeridad para sus hechizos (como Jax, Teemo o Gwen). Mejora fluidamente tus intercambios largos."
    },
    "nashor_s_talon_wild_rift": {
        "name": "Garra de Nashor",
        "category": "Objetos de Nivel Medio",
        "stats": "+15 <font color='#FFA500'>daño de ataque</font> o +30 <font color='#9370db'>poder de habilidad</font> (Adaptable)",
        "passive": "Aguja mágica: Obtienes daño de ataque o poder de habilidad adaptable según tus demás objetos.",
        "coachTip": "Un componente muy versátil que escala según lo que necesites, dándote un poder flexible antes de completar el Diente de Nashor."
    },
    "forbidden_idol_wild_rift": {
        "name": "Ídolo prohibido",
        "category": "Objetos de Nivel Medio",
        "stats": "+10 <font color='#FFA500'>celeridad de habilidad</font>",
        "passive": "Poder curativo: +5% a la potencia de curaciones y escudos otorgados.",
        "coachTip": "La mejor opción para soportes de utilidad. Incrementa significativamente el impacto de tus escudos y curaciones para proteger a tu tirador en peleas cerradas."
    },
    "hextech_alternator_wild_rift": {
        "name": "Alternador hextech",
        "category": "Objetos de Nivel Medio",
        "stats": "+25 <font color='#9370db'>poder de habilidad</font> • +150 <font color='#1AFF00'>vida máxima</font>",
        "passive": "Revoluciones: Las habilidades dañinas y ataques contra campeones infligen de 25 a 60 de daño mágico adicional. (40s de enfriamiento)",
        "coachTip": "Añade un estallido de daño significativo a tu combo. Ideal para magos de ráfaga y asesinos AP, ayudándolos a asegurar asesinatos durante el juego temprano."
    },
    "void_amethyst": {
        "name": "Amatista del Vacío",
        "category": "Objetos de Nivel Medio",
        "stats": "+25 <font color='#9370db'>poder de habilidad</font>",
        "passive": "Disolver: +20% de penetración mágica.",
        "coachTip": "Un pico de poder brutal de bajo costo. Proporciona la penetración necesaria para derretir a los tanques o campeones que empiezan a acumular resistencia mágica."
    },
    "lol_wild_rift_seeker_s_armguard": {
        "name": "Brazalete de la buscadora",
        "category": "Objetos de Nivel Medio",
        "stats": "+30 <font color='#FFD700'>armadura</font> • +15 <font color='#9370db'>poder de habilidad</font>",
        "passive": "",
        "coachTip": "Objeto de supervivencia fantástico para magos. Cómpralo sin dudarlo en la fase de líneas si te enfrentas a un asesino de daño físico abrumador (como Zed o Yasuo)."
    },
    "oblivion_orb_wild_rift": {
        "name": "Orbe del olvido",
        "category": "Objetos de Nivel Medio",
        "stats": "+30 <font color='#9370db'>poder de habilidad</font>",
        "passive": "Maldición: Dañar campeones enemigos con habilidades mágicas aplica un 50% de Heridas Graves por 3 segundos.",
        "coachTip": "Tu respuesta prioritaria contra campeones con alta regeneración o curación (como Soraka, Dr. Mundo). Cómpralo temprano para cortar de raíz su sustento."
    },
    "sheen_wild_rift": {
        "name": "Brillo",
        "category": "Objetos de Nivel Medio",
        "stats": "+10 <font color='#FFA500'>celeridad de habilidad</font>",
        "passive": "Hoja encantada: Tras usar una habilidad, tu siguiente ataque dentro de los siguientes 10s infligirá daño físico adicional equivalente al 100% de tu daño de ataque básico.",
        "coachTip": "Una mejora de daño excelente para campeones que pueden intercalar fácilmente ataques básicos con sus habilidades (como Ezreal o Camille)."
    },
    "catalyst_of_aeons_wild_rift": {
        "name": "Catalizador de eones",
        "category": "Objetos de Nivel Medio",
        "stats": "+150 <font color='#1AFF00'>vida máxima</font> • +200 <font color='#00BFFF'>maná máximo</font>",
        "passive": "Eternidad: Restaura maná al recibir daño de campeones y recupera vida tras gastar maná (hasta cierto límite).",
        "coachTip": "Otorga una increíble sustentabilidad para el juego temprano, dándote los recursos necesarios para mantenerte agresivo en tu línea sin tener que regresar a base."
    },
    "haunting_guise_wild_rift": {
        "name": "Disfraz encantado",
        "category": "Objetos de Nivel Medio",
        "stats": "+150 <font color='#1AFF00'>vida máxima</font> • +30 <font color='#9370db'>poder de habilidad</font>",
        "passive": "Locura: En combate contra campeones enemigos, aumenta el daño infligido un 1,2% cada segundo, hasta un máximo del 6%.",
        "coachTip": "Perfecto para magos de daño sostenido y luchadores AP. Cuanto más logres alargar el enfrentamiento, más daño infligirás."
    },
    "needlessly_large_rod_wild_rift": {
        "name": "Vara innecesariamente grande",
        "category": "Objetos de Nivel Medio",
        "stats": "+65 <font color='#9370db'>poder de habilidad</font>",
        "passive": "",
        "coachTip": "Un pico de poder bruto enorme. Ahorrar para este objeto vale completamente la pena, ya que incrementa de forma masiva el daño de todo tu kit."
    },
    "blasting_wand_wild_rift": {
        "name": "Vara explosiva",
        "category": "Objetos de Nivel Medio",
        "stats": "+45 <font color='#9370db'>poder de habilidad</font>",
        "passive": "",
        "coachTip": "Una sólida inyección de daño en el juego medio. Otorga poder constante para construir casi cualquier objeto mágico avanzado que requieras."
    },
    "fiendish_codex_wild_rift": {
        "name": "Códice diabólico",
        "category": "Objetos de Nivel Medio",
        "stats": "+35 <font color='#9370db'>poder de habilidad</font> • +10 <font color='#FFA500'>celeridad de habilidad</font>",
        "passive": "",
        "coachTip": "La pieza ideal si lo que necesitas es rotar tus habilidades más rápido manteniendo al mismo tiempo tu presión ofensiva en la línea."
    },
    "lost_chapter_wild_rift": {
        "name": "Capítulo perdido",
        "category": "Objetos de Nivel Medio",
        "stats": "+30 <font color='#9370db'>poder de habilidad</font> • +150 <font color='#00BFFF'>maná máximo</font> • +10 <font color='#FFA500'>celeridad de habilidad</font>",
        "passive": "",
        "coachTip": "El componente estrella para los magos hambrientos de maná. Resolverá tus problemas de energía permitiéndote acosar incesantemente a tus rivales."
    },
    "aether_wisp_wild_rift": {
        "name": "Brisa de éter",
        "category": "Objetos de Nivel Medio",
        "stats": "+30 <font color='#9370db'>poder de habilidad</font>",
        "passive": "Flotar: Otorga +5% de velocidad de movimiento.",
        "coachTip": "Te ofrece el poder necesario con un sutil pero valioso toque de movilidad extra, excelente para rotar por el mapa o esquivar tiros de habilidad."
    },
    "mejai_s_soulstealer_wild_rift": {
        "name": "Robaalmas de Mejai",
        "category": "Objetos de Nivel Medio",
        "stats": "+150 <font color='#1AFF00'>vida máxima</font> • +30 <font color='#9370db'>poder de habilidad</font> • +7% <font color='#00FFFF'>penetración mágica</font>",
        "passive": "Gloria: Acumulas 4 cargas por asesinato y 2 por asistencia. Otorgan poder de habilidad extra y a las 15 cargas ganas 10% de velocidad de movimiento adicional.",
        "coachTip": "¡Una apuesta de alto riesgo y alta recompensa! Solo deberías comprar este objeto si tu ventaja es amplia y confías plenamente en que no morirás."
    },
    "fated_ashes": {
        "name": "Cenizas predestinadas",
        "category": "Objetos de Nivel Medio",
        "stats": "+40 <font color='#9370db'>poder de habilidad</font>",
        "passive": "Inflamación: Dañar con habilidades causa que los enemigos ardan por daño mágico durante 3 segundos. Hace daño adicional a los monstruos.",
        "coachTip": "Un componente asombroso para magos jungleros o para el desgaste en línea, acelerando drásticamente tu capacidad de limpieza de oleadas."
    },
    "verdant_barrier": {
        "name": "Barrera frondosa",
        "category": "Objetos de Nivel Medio",
        "stats": "+20 <font color='#9370db'>poder de habilidad</font> • +25 <font color='#E0B0FF'>resistencia mágica</font>",
        "passive": "Anular: Otorga un escudo protector que bloquea por completo la próxima habilidad enemiga. (60s de enfriamiento)",
        "coachTip": "La mejor protección mágica temprana contra magos de ráfaga y asesinos AP (como Akali o Fizz), forzándolos a quemar recursos antes de intentar matarte."
    }
}

new_blocks = []
items_replaced = 0

for item_id, props in updates.items():
    # Regex to match the whole WildRiftItem block for this id
    pattern = r'(WildRiftItem\(\s*id\s*=\s*"' + re.escape(item_id) + r'".*?iconUrl\s*=\s*"([^"]+)"\s*\))'
    match = re.search(pattern, content, re.DOTALL)
    
    if match:
        old_block = match.group(1)
        icon_url = match.group(2)
        
        # Build new block
        new_block = f"""WildRiftItem(
            id = "{item_id}",
            name = "{props['name']}",
            nameEn = "{props['name']}",
            category = "{props['category']}",
            goldCost = {1000},
            stats = "{props['stats']}",
            statsEn = "",
            passive = "{props['passive']}",
            passiveEn = "",
            coachTip = "{props['coachTip']}",
            coachTipEn = "",
            iconUrl = "{icon_url}"
        )"""
        
        # We don't want to hardcode goldCost unless we extract it. Let's extract the old goldCost.
        cost_match = re.search(r'goldCost\s*=\s*(\d+)', old_block)
        if cost_match:
            new_block = new_block.replace("goldCost = 1000,", f"goldCost = {cost_match.group(1)},")
            
        content = content.replace(old_block, new_block)
        items_replaced += 1
    else:
        print(f"Could not find block for: {item_id}")

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print(f"Replaced {items_replaced} items.")
