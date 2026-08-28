import json, re

# Load items to get icon urls
with open('app/src/main/java/com/example/data/WildRiftItemsData.kt') as f:
    item_text = f.read()

item_matches = re.findall(r'WildRiftItem\s*\(\s*id\s*=\s*\"([^\"]+)\",\s*name\s*=\s*\"([^\"]+)\",\s*nameEn\s*=\s*\"([^\"]+)\",.*?iconUrl\s*=\s*\"([^\"]+)\"', item_text, re.DOTALL)

item_icon_map = {}
for mid, name, nameEn, icon in item_matches:
    item_icon_map[name.strip().lower()] = icon
    item_icon_map[nameEn.strip().lower()] = icon
    item_icon_map[mid.strip().lower()] = icon

def get_item_icon(name):
    clean = name.strip().lower()
    if clean in item_icon_map:
        return item_icon_map[clean]
    for k, v in item_icon_map.items():
        if k in clean or clean in k:
            return v
    return "https://ddragon.leagueoflegends.com/cdn/14.16.1/img/item/3078.png"

SPELL_ICONS = {
    "Destello": "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es",
    "Prender": "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345",
    "Curación": "https://static.wikia.nocookie.net/leagueoflegends/images/6/6e/Heal.png/revision/latest?cb=20180514003344",
    "Extenuación": "https://static.wikia.nocookie.net/leagueoflegends/images/4/4a/Exhaust.png/revision/latest?cb=20180514003344",
    "Fantasmal": "https://static.wikia.nocookie.net/leagueoflegends/images/a/ab/Ghost.png/revision/latest?cb=20180514003345",
    "Barrera": "https://static.wikia.nocookie.net/leagueoflegends/images/c/cc/Barrier.png/revision/latest?cb=20180514003344",
    "Castigo": "https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003346",
    "Teleportación": "https://static.wikia.nocookie.net/leagueoflegends/images/d/da/Teleport.png/revision/latest?cb=20180514003346"
}

RUNE_ICONS = {
    "Conquistador": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
    "Electrocutar": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
    "Aery": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
    "Cometa Arcano": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
    "Garras del Inmortal": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
    "Réplica": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/aftershock/aftershock.png",
    "Compás Letal": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/lethaltempo/lethaltempo.png",
    "Primer Golpe": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
    "Fase Veloz": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/phaserush/phaserush.png",
    "Estrategia Ofensiva": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presstheattack/presstheattack.png",
    "Cosecha Oscura": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/darkharvest/darkharvest.png",
    "Pies Veloces": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/fleetfootwork/fleetfootwork.png",
    "Aumento Glacial": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/glacialaugment/glacialaugment.png"
}

# Specific data definitions for each champion ID
DATA = {
    # 1. AATROX
    "aatrox": {
        "advantageAgainst": ["Sion", "Dr. Mundo", "Nasus"],
        "counteredBy": ["Fiora", "Irelia", "Camille"],
        "synergies": ["Yasuo", "Malphite", "Diana"],
        "coreItems": ["Black Cleaver", "Guantelete de Sterak", "La danza de la muerte"],
        "situationalItems": ["Fauces de Malmortius", "malla de espinas", "Fuerza de la naturaleza"],
        "recommendedRunes": "Conquistador (Precisión)",
        "runeTreeDetails": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Sobrecrecimiento",
        "primaryRune": "Conquistador",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Conecta los filos de tu Q en el carril superior para castigar campeones inmóviles. Usa tu E para reposicionar la espada durante la animación."
    },
    # 2. AHRI
    "ahri": {
        "advantageAgainst": ["Lux", "Veigar", "Orianna"],
        "counteredBy": ["Yasuo", "Fizz", "Zed"],
        "synergies": ["Jarvan IV", "Vi", "Lee Sin"],
        "coreItems": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "situationalItems": ["El reloj de arena de Zhonya", "Morellonomicón", "Velo del bardo"],
        "recommendedRunes": "Electrocutar (Dominación)",
        "runeTreeDetails": "Dominación: Impacto Repentino • Cazador de Ojos • Ingenio Cazador • Trascendencia",
        "primaryRune": "Electrocutar",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Guarda tu Encanto (E) para interrumpir las entradas del rival o asegurar una rotación de carril con tu jungla."
    },
    # 3. AKALI
    "akali": {
        "advantageAgainst": ["Kassadin", "Zed", "Katarina"],
        "counteredBy": ["Galio", "Annie", "Pantheon"],
        "synergies": ["Diana", "Amumu", "Rakan"],
        "coreItems": ["Hacedor de grietas", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "situationalItems": ["El reloj de arena de Zhonya", "Morellonomicón", "Brizna de la noche"],
        "recommendedRunes": "Conquistador (Precisión)",
        "runeTreeDetails": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "primaryRune": "Conquistador",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Entra y sal de tu Velo del Crepúsculo (W) para activar tu pasiva repetidamente y maximizar tu daño sostenido."
    },
    # 4. AKSHAN
    "akshan": {
        "advantageAgainst": ["Twisted Fate", "Aurelion Sol", "Kassadin"],
        "counteredBy": ["Yasuo", "Zed", "Irelia"],
        "synergies": ["Nautilus", "Leona", "Braum"],
        "coreItems": ["Espada del Rey Arruinado", "El cuchillo fantasma de Youmuu", "Borde infinito"],
        "situationalItems": ["Ángel custodio", "Recordatorio mortal", "Fauces de Malmortius"],
        "recommendedRunes": "Estrategia Ofensiva (Precisión)",
        "runeTreeDetails": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Revestimiento de Hueso",
        "primaryRune": "Estrategia Ofensiva",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Busca ángulos limpios con tu Gancho de Hereje (E) para rodear al enemigo y reiniciar tu cuerda tras cada derribo."
    },
    # 5. ALISTAR (SUPPORT TANK)
    "alistar": {
        "advantageAgainst": ["Leona", "Rakan", "Nautilus"],
        "counteredBy": ["Janna", "Morgana", "Vayne"],
        "synergies": ["Yasuo", "Samira", "Kai'Sa"],
        "coreItems": ["Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke"],
        "situationalItems": ["Sudario del alba", "Relicario de los Solari de Hierro", "Malla de espinas"],
        "recommendedRunes": "Réplica (Valor)",
        "runeTreeDetails": "Valor: Fuente de Vida • Revestimiento de Hueso • Sobrecrecimiento • Pionero",
        "primaryRune": "Réplica",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Ejecuta el combo W+Q para levantar enemigos en cadena. En peleas de equipo, usa tu Definitiva (R) para absorber el daño masivo inicial."
    },
    # 6. AMBESSA
    "ambessa": {
        "advantageAgainst": ["Sion", "Nasus", "Darius"],
        "counteredBy": ["Fiora", "Jax", "Poppy"],
        "synergies": ["Diana", "Orianna", "Malphite"],
        "coreItems": ["Fuerza trinitaria", "Black Cleaver", "La danza de la muerte"],
        "situationalItems": ["Guantelete de Sterak", "Fauces de Malmortius", "Malla de espinas"],
        "recommendedRunes": "Conquistador (Precisión)",
        "runeTreeDetails": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Segundo Aire",
        "primaryRune": "Conquistador",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Encadena los deslizamientos de tu pasiva tras cada habilidad para mantener el control de distancia y castigar en intercambios cortos."
    },
    # 7. AMUMU (JUNGLE)
    "amumu": {
        "advantageAgainst": ["Maestro Yi", "Kha'Zix", "Rengar"],
        "counteredBy": ["Olaf", "Lee Sin", "Morgana"],
        "synergies": ["Miss Fortune", "Kennen", "Samira"],
        "coreItems": ["Égida del fuego solar", "Corazón de acero", "Tormento de Liandry"],
        "situationalItems": ["Malla de espinas", "Fuerza de la naturaleza", "El reloj de arena de Zhonya"],
        "recommendedRunes": "Réplica (Valor)",
        "runeTreeDetails": "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Golpe de Gracia",
        "primaryRune": "Réplica",
        "recommendedSpells": ["Destello", "Castigo"],
        "tacticalAdvice": "Guarda tu segunda carga de Vendaje (Q) para cuando el enemigo gaste su Destello o salto. Tu R es la mejor iniciación en equipo."
    },
    # 8. ANNIE (MID / SUPPORT)
    "annie": {
        "advantageAgainst": ["Yasuo", "Katarina", "Irelia"],
        "counteredBy": ["Orianna", "Syndra", "Lux"],
        "synergies": ["Jarvan IV", "Amumu", "Malphite"],
        "coreItems": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "situationalItems": ["El reloj de arena de Zhonya", "Morellonomicón", "Velo del bardo"],
        "recommendedRunes": "Electrocutar (Dominación)",
        "runeTreeDetails": "Dominación: Impacto Repentino • Cazador de Ojos • Ingenio Cazador • Banda de Maná",
        "primaryRune": "Electrocutar",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Mantén 3 cargas de pasiva para sorprender con Escudo (E) instantáneo + Tibbers con aturdimiento en área."
    },
    # 9. ASHE (ADC)
    "ashe": {
        "advantageAgainst": ["Vayne", "Kai'Sa", "Samira"],
        "counteredBy": ["Draven", "Blitzcrank", "Nautilus"],
        "synergies": ["Braum", "Seraphine", "Lulu"],
        "coreItems": ["Espada del Rey Arruinado", "Borde infinito", "Huracán de Runaan"],
        "situationalItems": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "recommendedRunes": "Compás Letal (Precisión)",
        "runeTreeDetails": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Entrega de Galletas",
        "primaryRune": "Compás Letal",
        "recommendedSpells": ["Destello", "Fantasmal"],
        "tacticalAdvice": "Usa tu Flecha de Cristal Encantada (R) para iniciar peleas a larga distancia o cazar enemigos desposicionados en el mapa."
    },
    # 10. AURELION SOL (MID)
    "aurelion_sol": {
        "advantageAgainst": ["Malzahar", "Veigar", "Vladimir"],
        "counteredBy": ["Fizz", "Kassadin", "Akali"],
        "synergies": ["Jarvan IV", "Amumu", "Galio"],
        "coreItems": ["Tormento de Liandry", "Vara de las edades", "Sombrero mortífero de Rabadon"],
        "situationalItems": ["Cetros de cristal de Rylai", "Bastón del Vacío", "El reloj de arena de Zhonya"],
        "recommendedRunes": "Cometa Arcano (Brujería)",
        "runeTreeDetails": "Brujería: Banda de Maná • Trascendencia • Tormenta Creciente • Sobrecrecimiento",
        "primaryRune": "Cometa Arcano",
        "recommendedSpells": ["Destello", "Barrera"],
        "tacticalAdvice": "Prioriza farmear con Singularidad (E) para recolectar polvo estelar rápidamente y desatar un Cataclismo masivo en el juego tardío."
    },
    # 11. AURORA (MID / TOP)
    "aurora": {
        "advantageAgainst": ["Darius", "Garen", "Sion"],
        "counteredBy": ["Zed", "Kha'Zix", "Irelia"],
        "synergies": ["Amumu", "Diana", "Malphite"],
        "coreItems": ["Tormento de Liandry", "Impulso cósmico", "Sombrero mortífero de Rabadon"],
        "situationalItems": ["El reloj de arena de Zhonya", "Bastón del Vacío", "Morellonomicón"],
        "recommendedRunes": "Electrocutar (Dominación)",
        "runeTreeDetails": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Trascendencia",
        "primaryRune": "Electrocutar",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Atrapa a los rivales dentro de tu Dominio Espiritual (R) y salta entre los extremos para esquivar habilidades y desgastar."
    },
    # 12. BARDO (SUPPORT UTILITY)
    "bard": {
        "advantageAgainst": ["Braum", "Alistar", "Sona"],
        "counteredBy": ["Blitzcrank", "Nautilus", "Pyke"],
        "synergies": ["Jhin", "Caitlyn", "Ezreal"],
        "coreItems": ["Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water"],
        "situationalItems": ["Redención", "La convergencia de Zeke", "Relicario de los Solari de Hierro"],
        "recommendedRunes": "Aumento Glacial (Inspiración)",
        "runeTreeDetails": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Fuente de Vida",
        "primaryRune": "Aumento Glacial",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Recolecta campanas en el mapa cuando tu tirador esté seguro bajo torre. Usa tu Destino Templado (R) para congelar torres u objetivos neutrales."
    },
    # 13. BLITZCRANK (SUPPORT HOOK)
    "blitzcrank": {
        "advantageAgainst": ["Sona", "Soraka", "Janna"],
        "counteredBy": ["Morgana", "Nautilus", "Sivir"],
        "synergies": ["Jinx", "Draven", "Samira"],
        "coreItems": ["Escudo de reliquia", "La convergencia de Zeke", "Voto de caballero"],
        "situationalItems": ["Corazón congelado", "Sudario del alba", "Relicario de los Solari de Hierro"],
        "recommendedRunes": "Aumento Glacial (Inspiración)",
        "runeTreeDetails": "Inspiración: Destello Hextech • Entrega de Galletas • Perspicacia Cósmica • Revestimiento de Hueso",
        "primaryRune": "Aumento Glacial",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Elige momentos sin súbditos enfrente para amenazar con el Gancho (Q). Tu silencio en área con la R destruye escudos enemigos al instante."
    },
    # 14. BRAND (SUPPORT DAMAGE / MID)
    "brand": {
        "advantageAgainst": ["Braum", "Alistar", "Leona"],
        "counteredBy": ["Blitzcrank", "Pyke", "Nautilus"],
        "synergies": ["Amumu", "Miss Fortune", "Jarvan IV"],
        "coreItems": ["Guadaña de la Niebla Negra", "Tormento de Liandry", "Cetros de cristal de Rylai"],
        "situationalItems": ["Morellonomicón", "Sombrero mortífero de Rabadon", "El reloj de arena de Zhonya"],
        "recommendedRunes": "Cometa Arcano (Brujería)",
        "runeTreeDetails": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "primaryRune": "Cometa Arcano",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Aplica tu pasiva ardiente sobre tanques agrupados para activar detonaciones en cadena durante las peleas por el dragón."
    },
    # 15. BRAUM (SUPPORT TANK PEEL)
    "braum": {
        "advantageAgainst": ["Miss Fortune", "Ornn", "Ezreal"],
        "counteredBy": ["Morgana", "Brand", "Zyra"],
        "synergies": ["Lucian", "Ashe", "Kai'Sa"],
        "coreItems": ["Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke"],
        "situationalItems": ["Presagio de Randuin", "Malla de espinas", "Relicario de los Solari de Hierro"],
        "recommendedRunes": "Garras del Inmortal (Valor)",
        "runeTreeDetails": "Valor: Fuente de Vida • Revestimiento de Hueso • Sobrecrecimiento • Lealtad",
        "primaryRune": "Garras del Inmortal",
        "recommendedSpells": ["Destello", "Extenuación"],
        "tacticalAdvice": "Levanta tu Escudo (E) para bloquear proyectiles clave enemigos (ultis de Miss Fortune, Ornn, Ezreal). Protege a tu tirador con W."
    },
    # 16. CAITLYN (ADC)
    "caitlyn": {
        "advantageAgainst": ["Vayne", "Kai'Sa", "Short-Range"],
        "counteredBy": ["Draven", "Tristana", "Nautilus"],
        "synergies": ["Morgana", "Lux", "Thresh"],
        "coreItems": ["Borde infinito", "El recaudador", "Cañón de fuego rápido"],
        "situationalItems": ["Recordatorio mortal", "Ángel custodio", "Sanguinario"],
        "recommendedRunes": "Compás Letal (Precisión)",
        "runeTreeDetails": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "primaryRune": "Compás Letal",
        "recommendedSpells": ["Destello", "Barrera"],
        "tacticalAdvice": "Aprovecha tu rango superior de 650 para castigar al rival bajo torre y coloca trampas (W) en los cuellos de botella de la jungla."
    },
    # 17. CAMILLE (TOP)
    "camille": {
        "advantageAgainst": ["Garen", "Sion", "Nasus"],
        "counteredBy": ["Jax", "Fiora", "Renekton"],
        "synergies": ["Galio", "Sejuani", "Orianna"],
        "coreItems": ["Fuerza trinitaria", "Guantelete de Sterak", "La danza de la muerte"],
        "situationalItems": ["Fauces de Malmortius", "Malla de espinas", "Ángel custodio"],
        "recommendedRunes": "Garras del Inmortal (Valor)",
        "runeTreeDetails": "Valor: Golpe de Escudo • Revestimiento de Hueso • Sobrecrecimiento • Golpe de Gracia",
        "primaryRune": "Garras del Inmortal",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Espera a cargar el segundo golpe de tu Q para infligir daño verdadero demoledor. Tu R aísla al tirador oponente sin escapatoria."
    },
    # 18. CHO'GATH (TOP / MID)
    "cho_gath": {
        "advantageAgainst": ["Malphite", "Singed", "Sion"],
        "counteredBy": ["Vayne", "Fiora", "Gwen"],
        "synergies": ["Yasuo", "Kennen", "Orianna"],
        "coreItems": ["Corazón de acero", "Égida del fuego solar", "Malla de espinas"],
        "situationalItems": ["Fuerza de la naturaleza", "Presagio de Randuin", "Protector pétreo"],
        "recommendedRunes": "Garras del Inmortal (Valor)",
        "runeTreeDetails": "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Golpe de Gracia",
        "primaryRune": "Garras del Inmortal",
        "recommendedSpells": ["Destello", "Prender"],
        "tacticalAdvice": "Asegura dragones y barones sincronizando tu Festín (R) con el Castigo de tu jungla para infligir daño verdadero instantáneo."
    },
    # 19. CORKI (MID / ADC)
    "corki": {
        "advantageAgainst": ["Veigar", "Twisted Fate", "Azir"],
        "counteredBy": ["Yasuo", "Zed", "Akali"],
        "synergies": ["Leona", "Nautilus", "Amumu"],
        "coreItems": ["Manamune", "Fuerza trinitaria", "Cañón de fuego rápido"],
        "situationalItems": ["Sombrero mortífero de Rabadon", "Bastón del Vacío", "Ángel custodio"],
        "recommendedRunes": "Primer Golpe (Inspiración)",
        "runeTreeDetails": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "primaryRune": "Primer Golpe",
        "recommendedSpells": ["Destello", "Barrera"],
        "tacticalAdvice": "Recoge el Paquete en base antes de pelear por dragones para partir al equipo rival en dos con Entrega Especial."
    },
    # 20. DARIUS (TOP)
    "darius": {
        "advantageAgainst": ["Garen", "Sion", "Nasus"],
        "counteredBy": ["Vayne", "Fiora", "Teemo"],
        "synergies": ["Ghost", "Yuumi", "Lulu"],
        "coreItems": ["Fuerza trinitaria", "Guantelete de Sterak", "La danza de la muerte"],
        "situationalItems": ["Malla de espinas", "Fuerza de la naturaleza", "Presagio de Randuin"],
        "recommendedRunes": "Conquistador (Precisión)",
        "runeTreeDetails": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "primaryRune": "Conquistador",
        "recommendedSpells": ["Destello", "Fantasmal"],
        "tacticalAdvice": "Acumula 5 marcas de Hemorragia para desatar Furia Noxiana y encadenar ejecuciones con tu Guillotina (R)."
    }
}

print("Loaded initial dict with", len(DATA), "champions")
