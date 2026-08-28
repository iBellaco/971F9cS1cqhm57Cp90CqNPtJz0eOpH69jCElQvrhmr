import json, re

# Load item icons
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
    "Castigo": "https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003346"
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
    "Aumento Glacial": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/glacialaugment/glacialaugment.png"
}

# Accurate bespoke dictionary for champions
SPECS = {
    "aatrox": {
        "adv": ["Sion", "Dr. Mundo", "Nasus"], "counter": ["Fiora", "Irelia", "Camille"], "syn": ["Yasuo", "Malphite", "Diana"],
        "core": ["Black Cleaver", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["Fauces de Malmortius", "malla de espinas", "Fuerza de la naturaleza"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Sobrecrecimiento",
        "spells": ["Destello", "Prender"], "tip": "Conecta los filos de tu Q en el carril superior para castigar campeones inmóviles. Usa tu E para reposicionar la espada durante la animación."
    },
    "ahri": {
        "adv": ["Lux", "Veigar", "Orianna"], "counter": ["Yasuo", "Fizz", "Zed"], "syn": ["Jarvan IV", "Vi", "Lee Sin"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Morellonomicón", "Velo de alma en pena"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Banda de Maná",
        "spells": ["Destello", "Prender"], "tip": "Guarda tu Encanto (E) para interrumpir las entradas del rival o asegurar una rotación de carril con tu jungla."
    },
    "akali": {
        "adv": ["Kassadin", "Zed", "Katarina"], "counter": ["Galio", "Annie", "Pantheon"], "syn": ["Diana", "Amumu", "Rakan"],
        "core": ["Hacedor de grietas", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Morellonomicón", "Diente de Nashor"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Entra y sal de tu Velo del Crepúsculo (W) para activar tu pasiva repetidamente y maximizar tu daño sostenido."
    },
    "akshan": {
        "adv": ["Twisted Fate", "Aurelion Sol", "Kassadin"], "counter": ["Yasuo", "Zed", "Irelia"], "syn": ["Nautilus", "Leona", "Braum"],
        "core": ["Espada del Rey Arruinado", "El cuchillo fantasma de Youmuu", "Borde infinito"],
        "sit": ["Ángel custodio", "Recordatorio mortal", "Fauces de Malmortius"],
        "rune": "Estrategia Ofensiva", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Revestimiento de Hueso",
        "spells": ["Destello", "Prender"], "tip": "Busca ángulos limpios con tu Gancho de Hereje (E) para rodear al enemigo y reiniciar tu cuerda tras cada derribo."
    },
    "alistar": {
        "adv": ["Leona", "Rakan", "Nautilus"], "counter": ["Janna", "Morgana", "Vayne"], "syn": ["Yasuo", "Samira", "Kai'Sa"],
        "core": ["Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke"],
        "sit": ["Sudario del alba", "Relicario de los Solari de Hierro", "malla de espinas"],
        "rune": "Réplica", "rune_desc": "Valor: Fuente de Vida • Revestimiento de Hueso • Sobrecrecimiento • Pionero",
        "spells": ["Destello", "Prender"], "tip": "Ejecuta el combo W+Q para levantar enemigos en cadena. En peleas de equipo, usa tu Definitiva (R) para absorber el daño masivo inicial."
    },
    "ambessa": {
        "adv": ["Sion", "Nasus", "Darius"], "counter": ["Fiora", "Jax", "Poppy"], "syn": ["Diana", "Orianna", "Malphite"],
        "core": ["Fuerza trinitaria", "Black Cleaver", "La danza de la muerte"],
        "sit": ["Guantelete de Sterak", "Fauces de Malmortius", "malla de espinas"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Encadena los deslizamientos de tu pasiva tras cada habilidad para mantener el control de distancia y castigar en intercambios cortos."
    },
    "amumu": {
        "adv": ["Maestro Yi", "Kha'Zix", "Rengar"], "counter": ["Olaf", "Lee Sin", "Morgana"], "syn": ["Miss Fortune", "Kennen", "Samira"],
        "core": ["Égida del fuego solar", "Corazón de acero", "Tormento de Liandry"],
        "sit": ["malla de espinas", "Fuerza de la naturaleza", "El reloj de arena de Zhonya"],
        "rune": "Réplica", "rune_desc": "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Golpe de Gracia",
        "spells": ["Destello", "Castigo"], "tip": "Guarda tu segunda carga de Vendaje (Q) para cuando el enemigo gaste su Destello o salto. Tu R es la mejor iniciación en equipo."
    },
    "annie": {
        "adv": ["Yasuo", "Katarina", "Irelia"], "counter": ["Orianna", "Syndra", "Lux"], "syn": ["Jarvan IV", "Amumu", "Malphite"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Morellonomicón", "Velo de alma en pena"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Banda de Maná",
        "spells": ["Destello", "Prender"], "tip": "Mantén 3 cargas de pasiva para sorprender con Escudo (E) instantáneo + Tibbers con aturdimiento en área."
    },
    "ashe": {
        "adv": ["Vayne", "Kai'Sa", "Samira"], "counter": ["Draven", "Blitzcrank", "Nautilus"], "syn": ["Braum", "Seraphine", "Lulu"],
        "core": ["Espada del Rey Arruinado", "Borde infinito", "El huracán de Runaan"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Entrega de Galletas",
        "spells": ["Destello", "Fantasmal"], "tip": "Usa tu Flecha de Cristal Encantada (R) para iniciar peleas a larga distancia o cazar enemigos desposicionados en el mapa."
    },
    "aurelion_sol": {
        "adv": ["Malzahar", "Veigar", "Vladimir"], "counter": ["Fizz", "Kassadin", "Akali"], "syn": ["Jarvan IV", "Amumu", "Galio"],
        "core": ["Tormento de Liandry", "Vara de las edades", "Sombrero mortífero de Rabadon"],
        "sit": ["Cetros de cristal de Rylai", "Bastón del Vacío", "El reloj de arena de Zhonya"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Tormenta Creciente • Sobrecrecimiento",
        "spells": ["Destello", "Barrera"], "tip": "Prioriza farmear con Singularidad (E) para recolectar polvo estelar rápidamente y desatar un Cataclismo masivo en el juego tardío."
    },
    "aurora": {
        "adv": ["Darius", "Garen", "Sion"], "counter": ["Zed", "Kha'Zix", "Irelia"], "syn": ["Amumu", "Diana", "Malphite"],
        "core": ["Tormento de Liandry", "Impulso cósmico", "Sombrero mortífero de Rabadon"],
        "sit": ["El reloj de arena de Zhonya", "Bastón del Vacío", "Morellonomicón"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Trascendencia",
        "spells": ["Destello", "Prender"], "tip": "Atrapa a los rivales dentro de tu Dominio Espiritual (R) y salta entre los extremos para esquivar habilidades y desgastar."
    },
    "bard": {
        "adv": ["Braum", "Alistar", "Sona"], "counter": ["Blitzcrank", "Nautilus", "Pyke"], "syn": ["Jhin", "Caitlyn", "Ezreal"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water"],
        "sit": ["Redención", "La convergencia de Zeke", "Relicario de los Solari de Hierro"],
        "rune": "Aumento Glacial", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Fuente de Vida",
        "spells": ["Destello", "Prender"], "tip": "Recolecta campanas en el mapa cuando tu tirador esté seguro bajo torre. Usa tu Destino Templado (R) para congelar torres u objetivos neutrales."
    },
    "blitzcrank": {
        "adv": ["Sona", "Soraka", "Janna"], "counter": ["Morgana", "Nautilus", "Sivir"], "syn": ["Jinx", "Draven", "Samira"],
        "core": ["Escudo de reliquia", "La convergencia de Zeke", "Voto de caballero"],
        "sit": ["Corazón congelado", "Sudario del alba", "Relicario de los Solari de Hierro"],
        "rune": "Aumento Glacial", "rune_desc": "Inspiración: Destello Hextech • Entrega de Galletas • Perspicacia Cósmica • Revestimiento de Hueso",
        "spells": ["Destello", "Prender"], "tip": "Elige momentos sin súbditos enfrente para amenazar con el Gancho (Q). Tu silencio en área con la R destruye escudos enemigos al instante."
    },
    "brand": {
        "adv": ["Braum", "Alistar", "Leona"], "counter": ["Blitzcrank", "Pyke", "Nautilus"], "syn": ["Amumu", "Miss Fortune", "Jarvan IV"],
        "core": ["Guadaña de la Niebla Negra", "Tormento de Liandry", "Cetros de cristal de Rylai"],
        "sit": ["Morellonomicón", "Sombrero mortífero de Rabadon", "El reloj de arena de Zhonya"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Prender"], "tip": "Aplica tu pasiva ardiente sobre tanques agrupados para activar detonaciones en cadena durante las peleas por el dragón."
    },
    "braum": {
        "adv": ["Miss Fortune", "Ornn", "Ezreal"], "counter": ["Morgana", "Brand", "Zyra"], "syn": ["Lucian", "Ashe", "Kai'Sa"],
        "core": ["Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke"],
        "sit": ["El presagio de Randuin", "malla de espinas", "Relicario de los Solari de Hierro"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Fuente de Vida • Revestimiento de Hueso • Sobrecrecimiento • Lealtad",
        "spells": ["Destello", "Extenuación"], "tip": "Levanta tu Escudo (E) para bloquear proyectiles clave enemigos (ultis de Miss Fortune, Ornn, Ezreal). Protege a tu tirador con W."
    },
    "caitlyn": {
        "adv": ["Vayne", "Kai'Sa", "Samira"], "counter": ["Draven", "Tristana", "Nautilus"], "syn": ["Morgana", "Lux", "Thresh"],
        "core": ["Borde infinito", "El recaudador", "Cañón de fuego rápido"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "sanguinario"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Barrera"], "tip": "Aprovecha tu rango superior de 650 para castigar al rival bajo torre y coloca trampas (W) en los cuellos de botella de la jungla."
    },
    "camille": {
        "adv": ["Garen", "Sion", "Nasus"], "counter": ["Jax", "Fiora", "Renekton"], "syn": ["Galio", "Diana", "Orianna"],
        "core": ["Fuerza trinitaria", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["Fauces de Malmortius", "malla de espinas", "Ángel custodio"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Golpe de Escudo • Revestimiento de Hueso • Sobrecrecimiento • Golpe de Gracia",
        "spells": ["Destello", "Prender"], "tip": "Espera a cargar el segundo golpe de tu Q para infligir daño verdadero demoledor. Tu R aísla al tirador oponente sin escapatoria."
    },
    "cho_gath": {
        "adv": ["Malphite", "Singed", "Sion"], "counter": ["Vayne", "Fiora", "Gwen"], "syn": ["Yasuo", "Kennen", "Orianna"],
        "core": ["Corazón de acero", "Égida del fuego solar", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Golpe de Gracia",
        "spells": ["Destello", "Prender"], "tip": "Asegura dragones y barones sincronizando tu Festín (R) con el Castigo de tu jungla para infligir daño verdadero instantáneo."
    },
    "corki": {
        "adv": ["Veigar", "Twisted Fate", "Azir"], "counter": ["Yasuo", "Zed", "Akali"], "syn": ["Leona", "Nautilus", "Amumu"],
        "core": ["Manamune", "Fuerza trinitaria", "Cañón de fuego rápido"],
        "sit": ["Sombrero mortífero de Rabadon", "Bastón del Vacío", "Ángel custodio"],
        "rune": "Primer Golpe", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "spells": ["Destello", "Barrera"], "tip": "Recoge el Paquete en base antes de pelear por dragones para partir al equipo rival en dos con Entrega Especial."
    },
    "darius": {
        "adv": ["Garen", "Sion", "Nasus"], "counter": ["Vayne", "Fiora", "Teemo"], "syn": ["Ghost", "Yuumi", "Lulu"],
        "core": ["Fuerza trinitaria", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["malla de espinas", "Fuerza de la naturaleza", "El presagio de Randuin"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Fantasmal"], "tip": "Acumula 5 marcas de Hemorragia para desatar Furia Noxiana y encadenar ejecuciones con tu Guillotina (R)."
    },
    "diana": {
        "adv": ["Katarina", "Kassadin", "Zed"], "counter": ["Galio", "Mordekaiser", "Pantheon"], "syn": ["Yasuo", "Orianna", "Amumu"],
        "core": ["Diente de Nashor", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Velo de alma en pena"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Impacto Repentino",
        "spells": ["Destello", "Castigo"], "tip": "Acierta Impacto Creciente (Q) sobre múltiples objetivos para reiniciar tu Impulso Lunar (E) y agrupar al equipo enemigo con la R."
    },
    "dr_mundo": {
        "adv": ["Malphite", "Singed", "Teemo"], "counter": ["Vayne", "Fiora", "Gwen"], "syn": ["Yuumi", "Lulu", "Seraphine"],
        "core": ["Corazón de acero", "Égida del fuego solar", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Segundo Aire • Sobrecrecimiento • Inquebrantable • Triunfo",
        "spells": ["Destello", "Fantasmal"], "tip": "Recoge el bote químico de tu pasiva para recuperar vida y mantenerte inmune al primer control de masas que te lancen."
    },
    "draven": {
        "adv": ["Vayne", "Kai'Sa", "Ezreal"], "counter": ["Caitlyn", "Varus", "Nautilus"], "syn": ["Thresh", "Nautilus", "Leona"],
        "core": ["sanguinario", "Borde infinito", "El recaudador"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Cazador de Ojos",
        "spells": ["Destello", "Curación"], "tip": "Mantén girando dos hachas antes de comenzar el intercambio para activar Adoración y cobrar el oro de tu pasiva con una muerte temprana."
    },
    "ekko": {
        "adv": ["Lux", "Veigar", "Ziggs"], "counter": ["Kassadin", "Pantheon", "Galio"], "syn": ["Diana", "Amumu", "Malphite"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["Diente de Nashor", "El reloj de arena de Zhonya", "Morellonomicón"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Trascendencia",
        "spells": ["Destello", "Castigo"], "tip": "Predice el movimiento del enemigo para colocar Convergencia Paralela (W) desde la niebla de guerra y conseguir un aturdimiento masivo."
    },
    "evelynn": {
        "adv": ["Master Yi", "Twitch", "Kindred"], "counter": ["Lee Sin", "Rengar", "Kha'Zix"], "syn": ["Shen", "Twisted Fate", "Galio"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["Morellonomicón", "El reloj de arena de Zhonya", "Velo de alma en pena"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Banda de Maná",
        "spells": ["Destello", "Castigo"], "tip": "Llega por la espalda con camuflaje demoníaco nivel 5+, aplica la marca de Encanto (W) y sal ilesa ejecutando con Última Caricia (R)."
    },
    "ezreal": {
        "adv": ["Jinx", "Miss Fortune", "Ashe"], "counter": ["Draven", "Samira", "Tristana"], "syn": ["Yuumi", "Karma", "Lux"],
        "core": ["Manamune", "Fuerza trinitaria", "El rencor de Serylda"],
        "sit": ["Espada del Rey Arruinado", "Ángel custodio", "Fauces de Malmortius"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Banda de Maná",
        "spells": ["Destello", "Curación"], "tip": "Mantén las 5 cargas de tu pasiva golpeando con tu Q antes de pelear. Guarda tu Desplazamiento Arcano (E) para esquivar iniciaciones clave."
    },
    "fiddlesticks": {
        "adv": ["Master Yi", "Kha'Zix", "Viego"], "counter": ["Lee Sin", "Olaf", "Xin Zhao"], "syn": ["Amumu", "Miss Fortune", "Kennen"],
        "core": ["Tormento de Liandry", "Cetros de cristal de Rylai", "Sombrero mortífero de Rabadon"],
        "sit": ["El reloj de arena de Zhonya", "Bastón del Vacío", "Morellonomicón"],
        "rune": "Primer Golpe", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "spells": ["Destello", "Castigo"], "tip": "Canaliza Tormenta de Cuervos (R) fuera de la visión enemiga para aterrorizar a todo el equipo rival simultáneamente."
    },
    "fiora": {
        "adv": ["Aatrox", "Sion", "K'Sante"], "counter": ["Malphite", "Jax", "Kennen"], "syn": ["Shen", "Galio", "Twisted Fate"],
        "core": ["Fuerza trinitaria", "Espada del Rey Arruinado", "La danza de la muerte"],
        "sit": ["Guantelete de Sterak", "Fauces de Malmortius", "malla de espinas"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Usa Estocada (W) para bloquear el control de masas crucial de tu oponente y aturdirlo de vuelta."
    },
    "fizz": {
        "adv": ["Twisted Fate", "Veigar", "Syndra"], "counter": ["Galio", "Kassadin", "Pantheon"], "syn": ["Jarvan IV", "Amumu", "Vi"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Morellonomicón", "Diente de Nashor"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Triunfo",
        "spells": ["Destello", "Prender"], "tip": "Lanza Carnaza para Tiburones (R) a máxima distancia para maximizar el tamaño del tiburón y esquiva el contraataque con Juguetón/Gamberro (E)."
    },
    "galio": {
        "adv": ["Katarina", "Akali", "Diana"], "counter": ["Lucian", "Tristana", "Jayce"], "syn": ["Camille", "Jarvan IV", "Diana"],
        "core": ["Vara de las edades", "Sombrero mortífero de Rabadon", "Corazón de acero"],
        "sit": ["Égida del fuego solar", "El reloj de arena de Zhonya", "Fuerza de la naturaleza"],
        "rune": "Réplica", "rune_desc": "Valor: Fuente de Vida • Revestimiento de Hueso • Sobrecrecimiento • Cazador de Ojos",
        "spells": ["Destello", "Prender"], "tip": "Utiliza tu Entrada Heroica (R) sobre el iniciador de tu equipo para otorgar escudo mágico y noquear a los rivales agrupados."
    },
    "garen": {
        "adv": ["Riven", "Jax", "Irelia"], "counter": ["Vayne", "Teemo", "Darius"], "syn": ["Yuumi", "Lulu", "Orianna"],
        "core": ["Fuerza trinitaria", "Black Cleaver", "La danza de la muerte"],
        "sit": ["malla de espinas", "Fuerza de la naturaleza", "Guantelete de Sterak"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Silencia al enemigo con Golpe Decisivo (Q) para impedir que use habilidades defensivas antes de girar con Juicio (E) y rematar con R."
    },
    "gnar": {
        "adv": ["Darius", "Garen", "Sett"], "counter": ["Irelia", "Yasuo", "Wukong"], "syn": ["Jarvan IV", "Orianna", "Malphite"],
        "core": ["Fuerza trinitaria", "Black Cleaver", "Guantelete de Sterak"],
        "sit": ["La danza de la muerte", "malla de espinas", "Fuerza de la naturaleza"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Fuente de Vida • Revestimiento de Hueso • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Prender"], "tip": "Gestiona tu barra de Furia para entrar en forma Mega Gnar justo cuando inicie la pelea en el río y estampar a los rivales contra el muro."
    },
    "gragas": {
        "adv": ["Katarina", "Jax", "Riven"], "counter": ["Morgana", "Olaf", "Vayne"], "syn": ["Yasuo", "Samira", "Diana"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Fase Veloz", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Triunfo",
        "spells": ["Destello", "Castigo"], "tip": "Lanza Tonel Explosivo (R) para desarmar la formación enemiga o empujar al tirador rival hacia tu equipo."
    },
    "graves": {
        "adv": ["Lee Sin", "Kha'Zix", "Xin Zhao"], "counter": ["Rammus", "Evelynn", "Amumu"], "syn": ["Leona", "Nautilus", "Galio"],
        "core": ["El recaudador", "Borde infinito", "sanguinario"],
        "sit": ["Recordatorio mortal", "La danza de la muerte", "Ángel custodio"],
        "rune": "Fase Veloz", "rune_desc": "Brujería: Capa del Nimbo • Celeridad • Caminar sobre Agua • Triunfo",
        "spells": ["Destello", "Castigo"], "tip": "Acumula Cierto Valor (E) con monstruos de la jungla para ganar hasta 40+ de armadura pasiva antes de invadir."
    },
    "gwen": {
        "adv": ["Sion", "Dr. Mundo", "Cho'Gath"], "counter": ["Fiora", "Jax", "Riven"], "syn": ["Jarvan IV", "Diana", "Amumu"],
        "core": ["Hacedor de grietas", "Diente de Nashor", "Sombrero mortífero de Rabadon"],
        "sit": ["Bastón del Vacío", "El reloj de arena de Zhonya", "Fuerza de la naturaleza"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Segundo Aire",
        "spells": ["Destello", "Fantasmal"], "tip": "Usa Niebla Sagrada (W) para volverte inmune a los proyectiles y habilidades de los tiradores enemigos fuera de la zona."
    },
    "hecarim": {
        "adv": ["Kha'Zix", "Kindred", "Master Yi"], "counter": ["Rammus", "Morgana", "Thresh"], "syn": ["Orianna", "Lulu", "Yuumi"],
        "core": ["Fuerza trinitaria", "Black Cleaver", "La danza de la muerte"],
        "sit": ["Guantelete de Sterak", "Fuerza de la naturaleza", "malla de espinas"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Celeridad",
        "spells": ["Fantasmal", "Castigo"], "tip": "Carga tu Carga Devastadora (E) con Fantasmal activo para embestir a toda velocidad y aterrorizar con tu estampida R."
    },
    "heimerdinger": {
        "adv": ["Garen", "Darius", "Nasus"], "counter": ["Syndra", "Sion", "Ziggs"], "syn": ["Amumu", "Jarvan IV", "Nautilus"],
        "core": ["Tormento de Liandry", "Cetros de cristal de Rylai", "Sombrero mortífero de Rabadon"],
        "sit": ["El reloj de arena de Zhonya", "Bastón del Vacío", "Morellonomicón"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Prender"], "tip": "Coloca tu nido de tres torretas en triángulo y utiliza tu Granada (E) mejorada con R para aturdir en área."
    },
    "irelia": {
        "adv": ["Aatrox", "Gnar", "Jayce"], "counter": ["Jax", "Fiora", "Volibear"], "syn": ["Lulu", "Diana", "Malphite"],
        "core": ["Espada del Rey Arruinado", "Fuerza trinitaria", "La danza de la muerte"],
        "sit": ["Guantelete de Sterak", "Fauces de Malmortius", "Ángel custodio"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Acumula las 4 cargas de Fervor Jonio saltando con Embate de Espada (Q) a los súbditos bajos de vida antes de iniciar el duelo."
    },
    "janna": {
        "adv": ["Leona", "Alistar", "Rell"], "counter": ["Blitzcrank", "Nami", "Sona"], "syn": ["Jinx", "Caitlyn", "Twitch"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water"],
        "sit": ["Incensario Ardiente", "Redención", "Relicario de los Solari de Hierro"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Extenuación"], "tip": "Carga tu Vendaval (Q) oculto en la maleza para cortar los saltos de iniciación enemigos. Tu R expulsa a los asesinos y cura a todo tu equipo."
    },
    "jarvan_iv": {
        "adv": ["Jinx", "Twisted Fate", "Miss Fortune"], "counter": ["Vayne", "Ezreal", "Olaf"], "syn": ["Orianna", "Miss Fortune", "Yasuo"],
        "core": ["Black Cleaver", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["Fuerza de la naturaleza", "malla de espinas", "Ángel custodio"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Castigo"], "tip": "Encierra a campeones sin salto dentro de tu Cataclismo (R) y combínalo con E+Q para un derribo aéreo garantizado."
    },
    "jax": {
        "adv": ["Master Yi", "Tryndamere", "Irelia"], "counter": ["Malphite", "Garen", "Gragas"], "syn": ["Yuumi", "Lulu", "Orianna"],
        "core": ["Fuerza trinitaria", "Espada del Rey Arruinado", "La danza de la muerte"],
        "sit": ["Guantelete de Sterak", "Fuerza de la naturaleza", "malla de espinas"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Revestimiento de Hueso",
        "spells": ["Destello", "Prender"], "tip": "Activa Contraataque (E) para esquivar todos los ataques básicos y reactívalo para aturdir al instante."
    },
    "jayce": {
        "adv": ["Darius", "Garen", "Sett"], "counter": ["Irelia", "Wukong", "Camille"], "syn": ["Jarvan IV", "Vi", "Lee Sin"],
        "core": ["El cuchillo fantasma de Youmuu", "Manamune", "El rencor de Serylda"],
        "sit": ["La danza de la muerte", "Fauces de Malmortius", "Ángel custodio"],
        "rune": "Primer Golpe", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "spells": ["Destello", "Prender"], "tip": "Dispara Descarga de Choque (Q) y coloca la Puerta de Aceleración (E) justo frente a tu cañón para un disparo veloz e impredecible."
    },
    "jhin": {
        "adv": ["Ashe", "Senna", "Miss Fortune"], "counter": ["Draven", "Tristana", "Nautilus"], "syn": ["Leona", "Morgana", "Thresh"],
        "core": ["El recaudador", "Borde infinito", "Cañón de fuego rápido"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Pies Veloces", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Capa del Nimbo",
        "spells": ["Destello", "Curación"], "tip": "Guarda tu 4º disparo para ejecutar objetivos con poca vida. Tu Florecer Mortal (W) atrapa a cualquier enemigo dañado por tus aliados."
    },
    "jinx": {
        "adv": ["Aphelios", "Varus", "Ashe"], "counter": ["Draven", "Blitzcrank", "Zed"], "syn": ["Lulu", "Thresh", "Janna"],
        "core": ["Borde infinito", "El huracán de Runaan", "sanguinario"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Fantasmal"], "tip": "Aprovecha ¡Comienza a Emocionarte! tras cada derribo para reposicionarte a máxima velocidad y limpiar la pelea con Carapescado."
    },
    "k_sante": {
        "adv": ["Sion", "Dr. Mundo", "Malphite"], "counter": ["Fiora", "Gwen", "Vayne"], "syn": ["Samira", "Yasuo", "Diana"],
        "core": ["Guantelete de hielo", "Égida del fuego solar", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "Protector pétreo", "Corazón congelado"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Golpe de Escudo • Revestimiento de Hueso • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Fantasmal"], "tip": "Arrastra al carry rival a través del muro con Todo Fuera (R) para convertirte en duelista y rematarlo en 1v1."
    },
    "kai_sa": {
        "adv": ["Vayne", "Ezreal", "Twitch"], "counter": ["Draven", "Caitlyn", "Lucian"], "syn": ["Nautilus", "Leona", "Alistar"],
        "core": ["Manamune", "Diente de Nashor", "Borde infinito"],
        "sit": ["El reloj de arena de Zhonya", "Recordatorio mortal", "Ángel custodio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Banda de Maná",
        "spells": ["Destello", "Curación"], "tip": "Evoluciona tu Q rápidamente con AD temprano. Usa tu Instinto Asesino (R) para reposicionarte tras la retaguardia rival."
    },
    "kalista": {
        "adv": ["Jinx", "Sivir", "Ashe"], "counter": ["Ashe", "Nasus", "Nautilus"], "syn": ["Thresh", "Alistar", "Braum"],
        "core": ["Espada del Rey Arruinado", "El huracán de Runaan", "Borde infinito"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "sanguinario"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Cazador de Ojos",
        "spells": ["Destello", "Curación"], "tip": "Acumula lanzas con Desgarrar (E) para asegurar monstruos neutrales por encima del daño del Castigo rival."
    },
    "karma": {
        "adv": ["Leona", "Braum", "Alistar"], "counter": ["Blitzcrank", "Pyke", "Nautilus"], "syn": ["Ezreal", "Caitlyn", "Lucian"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water"],
        "sit": ["Incensario Ardiente", "Tormento de Liandry", "Relicario de los Solari de Hierro"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Prender"], "tip": "Usa Mantra (R) + Escudo (E) en peleas de equipo para acelerar y proteger a todos tus aliados a la vez."
    },
    "kassadin": {
        "adv": ["Aurelion Sol", "Veigar", "Katarina"], "counter": ["Lucian", "Tristana", "Zed"], "syn": ["Amumu", "Malphite", "Jarvan IV"],
        "core": ["Vara de las edades", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Velo de alma en pena"],
        "rune": "Primer Golpe", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "spells": ["Destello", "Prender"], "tip": "Alcanza el nivel 13+ con 4 cargas de Ruptura en el Camino (R) para eliminar a los objetivos frágiles en menos de un segundo."
    },
    "katarina": {
        "adv": ["Veigar", "Lux", "Ziggs"], "counter": ["Galio", "Pantheon", "Malzahar"], "syn": ["Amumu", "Diana", "Leona"],
        "core": ["Diente de Nashor", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Impacto Repentino",
        "spells": ["Destello", "Prender"], "tip": "Espera a que el rival gaste sus controles de masa principales antes de saltar y canalizar Loto Mortal (R)."
    },
    "kayle": {
        "adv": ["Garen", "Sion", "Singed"], "counter": ["Irelia", "Jax", "Riven"], "syn": ["Lulu", "Yuumi", "Morgana"],
        "core": ["Diente de Nashor", "Fuerza trinitaria", "Sombrero mortífero de Rabadon"],
        "sit": ["Bastón del Vacío", "El reloj de arena de Zhonya", "Ángel custodio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Sobrecrecimiento",
        "spells": ["Destello", "Fantasmal"], "tip": "Juega seguro hasta el nivel 5 (rango) y nivel 10 (ondas de fuego). Tu Juicio Divino (R) vuelve invulnerable al carry aliado o a ti misma."
    },
    "kayn": {
        "adv": ["Jinx", "Twitch", "Lux"], "counter": ["Master Yi", "Graves", "Lee Sin"], "syn": ["Yuumi", "Diana", "Orianna"],
        "core": ["El cuchillo fantasma de Youmuu", "El rencor de Serylda", "La danza de la muerte"],
        "sit": ["Black Cleaver", "Fauces de Malmortius", "Ángel custodio"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Impacto Repentino",
        "spells": ["Destello", "Castigo"], "tip": "Elige Forma Asesina Sombría contra escuadras frágiles o Rhaast contra composiciones con más de 2 tanques/luchadores."
    },
    "kennen": {
        "adv": ["Darius", "Garen", "Sett"], "counter": ["Irelia", "Jayce", "Malphite"], "syn": ["Amumu", "Diana", "Samira"],
        "core": ["Cinturón de cohetes hextech", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Trascendencia",
        "spells": ["Destello", "Prender"], "tip": "Flanquea con Impulso del Rayo (E) + Destello + Tempestad Cercenante (R) y activa Zhonya en el centro del equipo enemigo."
    },
    "kha_zix": {
        "adv": ["Master Yi", "Kindred", "Twitch"], "counter": ["Rammus", "Lee Sin", "Evelynn"], "syn": ["Yuumi", "Shen", "Diana"],
        "core": ["El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr", "El rencor de Serylda"],
        "sit": ["Colmillo de serpiente", "Ángel custodio", "Fauces de Malmortius"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Ingenio Cazador • Triunfo",
        "spells": ["Destello", "Castigo"], "tip": "Evoluciona tus Garras (Q) primero para daño masivo y busca siempre objetivos aislados para triplicar tu daño."
    },
    "kindred": {
        "adv": ["Sion", "Dr. Mundo", "Cho'Gath"], "counter": ["Lee Sin", "Kha'Zix", "Rengar"], "syn": ["Taric", "Galio", "Lulu"],
        "core": ["Espada del Rey Arruinado", "Borde infinito", "Huracán de Runaan"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Cazador de Ojos",
        "spells": ["Destello", "Castigo"], "tip": "Caza las marcas de la Muerte en campamentos de la jungla y usa Alivio de Cordero (R) para anular el daño explosivo enemigo."
    },
    "kog_maw": {
        "adv": ["Sion", "Cho'Gath", "Dr. Mundo"], "counter": ["Blitzcrank", "Nautilus", "Zed"], "syn": ["Lulu", "Milio", "Braum"],
        "core": ["Espada del Rey Arruinado", "El huracán de Runaan", "Borde infinito"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Fantasmal"], "tip": "Activa Andanada Bioarcana (W) para derretir la barra de vida de cualquier tanque a máxima distancia."
    },
    "lee_sin": {
        "adv": ["Kha'Zix", "Master Yi", "Evelynn"], "counter": ["Poppy", "Rammus", "Jax"], "syn": ["Yasuo", "Orianna", "Ahri"],
        "core": ["Black Cleaver", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["Fauces de Malmortius", "malla de espinas", "Ángel custodio"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Impacto Repentino",
        "spells": ["Destello", "Castigo"], "tip": "Aprovecha el Insec (Q + W centinela + R) para patear al tirador enemigo hacia la boca de tu equipo."
    },
    "leona": {
        "adv": ["Sona", "Soraka", "Yuumi"], "counter": ["Morgana", "Janna", "Thresh"], "syn": ["Samira", "Tristana", "Miss Fortune"],
        "core": ["Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke"],
        "sit": ["Sudario del alba", "Relicario de los Solari de Hierro", "malla de espinas"],
        "rune": "Réplica", "rune_desc": "Valor: Fuente de Vida • Revestimiento de Hueso • Sobrecrecimiento • Lealtad",
        "spells": ["Destello", "Prender"], "tip": "Encadena Espada del Cénit (E) + Q + Llamarada Solar (R) para mantener inmovilizado al objetivo durante más de 3 segundos."
    },
    "lillia": {
        "adv": ["Sion", "Darius", "Dr. Mundo"], "counter": ["Master Yi", "Kha'Zix", "Jarvan IV"], "syn": ["Amumu", "Miss Fortune", "Yasuo"],
        "core": ["Tormento de Liandry", "Cetros de cristal de Rylai", "Sombrero mortífero de Rabadon"],
        "sit": ["El reloj de arena de Zhonya", "Fuerza de la naturaleza", "Morellonomicón"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Celeridad",
        "spells": ["Destello", "Castigo"], "tip": "Mantén las 4 cargas de velocidad con tus habilidades para bailar alrededor de las peleas y dormir con tu Nana (R)."
    },
    "lissandra": {
        "adv": ["Zed", "Katarina", "Yasuo"], "counter": ["Kassadin", "Orianna", "Syndra"], "syn": ["Amumu", "Jarvan IV", "Diana"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Banda de Maná",
        "spells": ["Destello", "Prender"], "tip": "Lánzate con Camino Glacial (E) al corazón de la pelea enemiga y usa Tumba Congelada (R) sobre ti misma para invulnerabilidad y daño en área."
    },
    "lucian": {
        "adv": ["Kassadin", "Kayle", "Twisted Fate"], "counter": ["Draven", "Caitlyn", "Vayne"], "syn": ["Nami", "Braum", "Yuumi"],
        "core": ["Espada del Rey Arruinado", "Borde infinito", "El recaudador"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Estrategia Ofensiva", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Cazador de Ojos",
        "spells": ["Destello", "Prender"], "tip": "Intercala un ataque doble de tu pasiva entre cada habilidad para reiniciar rápidamente tu Deslizamiento (E)."
    },
    "lulu": {
        "adv": ["Zed", "Katarina", "Master Yi"], "counter": ["Blitzcrank", "Pyke", "Soraka"], "syn": ["Jinx", "Kog'Maw", "Twitch"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Incensario Ardiente"],
        "sit": ["Staff of Flowing Water", "Redención", "Relicario de los Solari de Hierro"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Extenuación"], "tip": "Usa Polimorfia (W) sobre los asesinos enemigos cuando se lancen a por tu carry para neutralizarlos al instante."
    },
    "lux": {
        "adv": ["Veigar", "Annie", "Twisted Fate"], "counter": ["Yasuo", "Zed", "Fizz"], "syn": ["Caitlyn", "Jhin", "Ezreal"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Morellonomicón", "Velo de alma en pena"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Barrera"], "tip": "Acierta Hechizo Luminoso (Q) a través de un súbdito para atrapar al objetivo y desatar Chispa Final (R) inmediatamente."
    },
    "malphite": {
        "adv": ["Tryndamere", "Jax", "Yasuo"], "counter": ["Sylas", "Gwen", "Mordekaiser"], "syn": ["Yasuo", "Orianna", "Miss Fortune"],
        "core": ["Corazón de acero", "Égida del fuego solar", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Golpe de Escudo • Revestimiento de Hueso • Sobrecrecimiento • Banda de Maná",
        "spells": ["Destello", "Prender"], "tip": "Espera el momento exacto para lanzar Fuerza Imparable (R) sobre 3 o más rivales agrupados en los objetivos de la jungla."
    },
    "maokai": {
        "adv": ["Sona", "Soraka", "Yuumi"], "counter": ["Morgana", "Olaf", "Vayne"], "syn": ["Miss Fortune", "Samira", "Jhin"],
        "core": ["Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke"],
        "sit": ["Sudario del alba", "malla de espinas", "Relicario de los Solari de Hierro"],
        "rune": "Réplica", "rune_desc": "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Prender"], "tip": "Lanza Garras de la Naturaleza (R) desde la maleza para bloquear una línea completa durante el asedio a torretas."
    },
    "master_yi": {
        "adv": ["Sion", "Dr. Mundo", "Cho'Gath"], "counter": ["Rammus", "Jax", "Malzahar"], "syn": ["Lulu", "Yuumi", "Taric"],
        "core": ["Espada del Rey Arruinado", "Borde infinito", "El huracán de Runaan"],
        "sit": ["La danza de la muerte", "Fajín de mercurio", "Ángel custodio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Cazador de Ojos",
        "spells": ["Destello", "Castigo"], "tip": "Guarda tu Golpe Alfa (Q) para esquivar habilidades de control o proyectiles letales del rival."
    },
    "mel": {
        "adv": ["Veigar", "Lux", "Orianna"], "counter": ["Zed", "Fizz", "Kassadin"], "syn": ["Amumu", "Jarvan IV", "Diana"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Barrera"], "tip": "Aprovecha tus habilidades de control a distancia para sellar los pasos estrechos antes de pelear por objetivos."
    },
    "milio": {
        "adv": ["Leona", "Nautilus", "Amumu"], "counter": ["Blitzcrank", "Pyke", "Soraka"], "syn": ["Caitlyn", "Jinx", "Kog'Maw"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Incensario Ardiente"],
        "sit": ["Staff of Flowing Water", "Redención", "Relicario de los Solari de Hierro"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Extenuación"], "tip": "Usa Fuego Reconfortante (W) para extender el rango de ataque de tu tirador y tu R para purgar controles de masa en área."
    },
    "miss_fortune": {
        "adv": ["Vayne", "Kai'Sa", "Ashe"], "counter": ["Braum", "Yasuo", "Samira"], "syn": ["Amumu", "Leona", "Sona"],
        "core": ["El recaudador", "Borde infinito", "El rencor de Serylda"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "sanguinario"],
        "rune": "Primer Golpe", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "spells": ["Destello", "Curación"], "tip": "Rebota Doble Bala (Q) sobre súbditos moribundos para infligir daño crítico garantizado al campeón enemigo detrás."
    },
    "mordekaiser": {
        "adv": ["Sion", "Dr. Mundo", "Malphite"], "counter": ["Fiora", "Vayne", "Olaf"], "syn": ["Diana", "Orianna", "Amumu"],
        "core": ["Hacedor de grietas", "Cetros de cristal de Rylai", "Sombrero mortífero de Rabadon"],
        "sit": ["malla de espinas", "Fuerza de la naturaleza", "El reloj de arena de Zhonya"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Fantasmal"], "tip": "Lleva al jungla o carry rival al Reino de las Sombras (R) para robar sus estadísticas y asegurar el Dragón o Barón."
    },
    "morgana": {
        "adv": ["Blitzcrank", "Nautilus", "Leona"], "counter": ["Sivir", "Karma", "Lulu"], "syn": ["Caitlyn", "Jhin", "Samira"],
        "core": ["Guadaña de la Niebla Negra", "Tormento de Liandry", "Cetros de cristal de Rylai"],
        "sit": ["El reloj de arena de Zhonya", "Morellonomicón", "Sombrero mortífero de Rabadon"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Prender"], "tip": "Coloca Escudo Negro (E) sobre tu tirador cuando reciba ganchos o aturdimientos para anular completamente el control de masas."
    },
    "nami": {
        "adv": ["Leona", "Braum", "Alistar"], "counter": ["Blitzcrank", "Pyke", "Nautilus"], "syn": ["Lucian", "Jinx", "Miss Fortune"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Mandato imperial"],
        "sit": ["Incensario Ardiente", "Staff of Flowing Water", "Redención"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Prender"], "tip": "Aplica Bendición de la Marea (E) sobre Lucian o tu tirador para ralentizar al rival y facilitar el acierto de la Prisión de Agua (Q)."
    },
    "nasus": {
        "adv": ["Tryndamere", "Jax", "Irelia"], "counter": ["Darius", "Garen", "Vayne"], "syn": ["Yuumi", "Lulu", "Seraphine"],
        "core": ["Fuerza trinitaria", "Corazón de acero", "Égida del fuego solar"],
        "sit": ["malla de espinas", "Fuerza de la naturaleza", "El presagio de Randuin"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Segundo Aire • Sobrecrecimiento • Inquebrantable • Triunfo",
        "spells": ["Destello", "Fantasmal"], "tip": "Farmea pacientemente con Golpe Absorbente (Q) para llegar a 300+ cargas antes del minuto 12 y destruir torretas en split-push."
    },
    "nautilus": {
        "adv": ["Sona", "Soraka", "Yuumi"], "counter": ["Morgana", "Olaf", "Janna"], "syn": ["Samira", "Kai'Sa", "Yasuo"],
        "core": ["Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke"],
        "sit": ["Sudario del alba", "malla de espinas", "Relicario de los Solari de Hierro"],
        "rune": "Aumento Glacial", "rune_desc": "Inspiración: Destello Hextech • Entrega de Galletas • Perspicacia Cósmica • Revestimiento de Hueso",
        "spells": ["Destello", "Prender"], "tip": "Usa tu Línea de Dragado (Q) contra el terreno para escapar rápidamente o inicia con Carga de las Profundidades (R) imparable."
    },
    "nidalee": {
        "adv": ["Master Yi", "Kindred", "Twitch"], "counter": ["Rammus", "Lee Sin", "Kha'Zix"], "syn": ["Renekton", "Pantheon", "Leona"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["Diente de Nashor", "El reloj de arena de Zhonya", "Morellonomicón"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Triunfo",
        "spells": ["Destello", "Castigo"], "tip": "Acierta tu Jabalina (Q) a distancia para activar Cacería y transformarte en puma con Golpe Rematador potenciado."
    },
    "nilah": {
        "adv": ["Draven", "Lucian", "Samira"], "counter": ["Caitlyn", "Ashe", "Morgana"], "syn": ["Taric", "Sona", "Yuumi"],
        "core": ["sanguinario", "Borde infinito", "El recaudador"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "La danza de la muerte"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Segundo Aire",
        "spells": ["Destello", "Curación"], "tip": "Usa Velo de Alegría (W) para esquivar todos los ataques básicos y compartir inmunidad con tu soporte en el intercambio."
    },
    "nocturne": {
        "adv": ["Twisted Fate", "Lux", "Veigar"], "counter": ["Olaf", "Rammus", "Jax"], "syn": ["Shen", "Galio", "Twisted Fate"],
        "core": ["El cuchillo fantasma de Youmuu", "Black Cleaver", "La danza de la muerte"],
        "sit": ["Guantelete de Sterak", "Fauces de Malmortius", "Ángel custodio"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Cazador de Ojos",
        "spells": ["Destello", "Castigo"], "tip": "Oscurece la visión global con Paranoia (R) y lánzate directo sobre el tirador desposicionado sin que puedan reaccionar."
    },
    "norra": {
        "adv": ["Veigar", "Lux", "Orianna"], "counter": ["Zed", "Fizz", "Kassadin"], "syn": ["Amumu", "Diana", "Malphite"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Barrera"], "tip": "Invoca portales dimensionales para sorprender con habilidades cruzadas desde ángulos ciegos de la jungla."
    },
    "nunu_willump": {
        "adv": ["Master Yi", "Kindred", "Amumu"], "counter": ["Olaf", "Vayne", "Morgana"], "syn": ["Yasuo", "Miss Fortune", "Orianna"],
        "core": ["Égida del fuego solar", "Corazón de acero", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Fase Veloz", "rune_desc": "Brujería: Capa del Nimbo • Celeridad • Caminar sobre Agua • Triunfo",
        "spells": ["Destello", "Castigo"], "tip": "Haz bolas de nieve gigantes (W) desde el río para emboscar a gran velocidad y asegura dragones con Voracidad (Q) + Castigo."
    },
    "olaf": {
        "adv": ["Morgana", "Leona", "Amumu"], "counter": ["Fiora", "Vayne", "Master Yi"], "syn": ["Yuumi", "Lulu", "Karma"],
        "core": ["Fuerza trinitaria", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["malla de espinas", "Fuerza de la naturaleza", "Presagio de Randuin"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Fantasmal"], "tip": "Activa Ragnarok (R) para volverte imparable e ignorar cualquier control de masas mientras persigues al tirador enemigo."
    },
    "orianna": {
        "adv": ["Veigar", "Annie", "Twisted Fate"], "counter": ["Zed", "Fizz", "Yasuo"], "syn": ["Malphite", "Jarvan IV", "Diana"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Fase Veloz", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Barrera"], "tip": "Coloca la esfera sobre tu iniciador con Proteger (E) y desata Onda de Choque (R) cuando entre al medio de los enemigos."
    },
    "ornn": {
        "adv": ["Sion", "Dr. Mundo", "Malphite"], "counter": ["Fiora", "Vayne", "Gwen"], "syn": ["Yasuo", "Miss Fortune", "Samira"],
        "core": ["Corazón de acero", "Égida del fuego solar", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Fantasmal"], "tip": "Forja objetos en cualquier lugar de la línea y coordina la llamada del Dios de la Forja (R) para noquear a múltiples enemigos."
    },
    "pantheon": {
        "adv": ["Yasuo", "Katarina", "Kassadin"], "counter": ["Malphite", "Darius", "Olaf"], "syn": ["Samira", "Kai'Sa", "Taliyah"],
        "core": ["El cuchillo fantasma de Youmuu", "Black Cleaver", "La danza de la muerte"],
        "sit": ["Guantelete de Sterak", "Fauces de Malmortius", "Ángel custodio"],
        "rune": "Estrategia Ofensiva", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Carga tu Salto con Escudo (W) con 5 cargas de pasiva para aturdir y aplicar tres golpes instantáneos. Rota con Gran Descarga Celestial (R)."
    },
    "poppy": {
        "adv": ["Riven", "Irelia", "Yasuo"], "counter": ["Darius", "Olaf", "Mordekaiser"], "syn": ["Vayne", "Anivia", "Gnar"],
        "core": ["Guantelete de hielo", "Égida del fuego solar", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Golpe de Escudo • Revestimiento de Hueso • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Prender"], "tip": "Activa Presencia Inquebrantable (W) para bloquear todos los saltos y deslizamientos de los asesinos rivales."
    },
    "pyke": {
        "adv": ["Sona", "Soraka", "Janna"], "counter": ["Morgana", "Nautilus", "Leona"], "syn": ["Samira", "Draven", "Jhin"],
        "core": ["Guadaña de la Niebla Negra", "El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr"],
        "sit": ["Colmillo de serpiente", "Ángel custodio", "Fauces de Malmortius"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Pionero",
        "spells": ["Destello", "Prender"], "tip": "Ejecuta con Muerte de las Profundidades (R) para repartir oro duplicado a tu tirador y reiniciar el salto inmediatamente."
    },
    "rakan": {
        "adv": ["Leona", "Braum", "Alistar"], "counter": ["Thresh", "Morgana", "Janna"], "syn": ["Xayah", "Samira", "Yasuo"],
        "core": ["Escudo de reliquia", "La convergencia de Zeke", "Eco armónico"],
        "sit": ["Voto de caballero", "Relicario de los Solari de Hierro", "Sudario del alba"],
        "rune": "Aumento Glacial", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Fuente de Vida",
        "spells": ["Destello", "Prender"], "tip": "Entra con La Gran Entrada (W) + Presteza (R) para encantar a los 5 enemigos y regresa a salvo con Danza de Batalla (E) a tu tirador."
    },
    "rammus": {
        "adv": ["Master Yi", "Tryndamere", "Jinx"], "counter": ["Morgana", "Olaf", "Gwen"], "syn": ["Yasuo", "Samira", "Kassadin"],
        "core": ["malla de espinas", "Égida del fuego solar", "Corazón de acero"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Réplica", "rune_desc": "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Castigo"], "tip": "Activa Posición Defensiva (W) antes de provocar al tirador enemigo con E para que se destruya a sí mismo con el daño devuelto."
    },
    "rell": {
        "adv": ["Sona", "Soraka", "Yuumi"], "counter": ["Janna", "Morgana", "Thresh"], "syn": ["Samira", "Miss Fortune", "Yasuo"],
        "core": ["Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke"],
        "sit": ["Sudario del alba", "malla de espinas", "Relicario de los Solari de Hierro"],
        "rune": "Aumento Glacial", "rune_desc": "Inspiración: Destello Hextech • Entrega de Galletas • Perspicacia Cósmica • Revestimiento de Hueso",
        "spells": ["Destello", "Prender"], "tip": "Destruye los escudos enemigos con Rompemauras (Q) y atrae a todo el equipo contrario con Tormenta Magnética (R)."
    },
    "renekton": {
        "adv": ["Yasuo", "Irelia", "Riven"], "counter": ["Illaoi", "Garen", "Quinn"], "syn": ["Nidalee", "Elise", "Taliyah"],
        "core": ["Black Cleaver", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["Fauces de Malmortius", "malla de espinas", "Fuerza de la naturaleza"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Entra con 50+ de furia para romper escudos y aturdir durante 1.5s con Depredador Implacable (W) potenciado."
    },
    "rengar": {
        "adv": ["Jinx", "Twitch", "Lux"], "counter": ["Rammus", "Lee Sin", "Poppy"], "syn": ["Ivern", "Yuumi", "Shen"],
        "core": ["El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr", "El rencor de Serylda"],
        "sit": ["Colmillo de serpiente", "Ángel custodio", "La danza de la muerte"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Triunfo",
        "spells": ["Destello", "Castigo"], "tip": "Salta desde la maleza con 4 acumulaciones de Ferocidad para disparar Fiereza (Q) potenciada y borrar al tirador en milisegundos."
    },
    "riven": {
        "adv": ["Yasuo", "Aatrox", "Nasus"], "counter": ["Renekton", "Poppy", "Garen"], "syn": ["Diana", "Orianna", "Malphite"],
        "core": ["Black Cleaver", "La danza de la muerte", "Guantelete de Sterak"],
        "sit": ["Fauces de Malmortius", "malla de espinas", "Ángel custodio"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Revestimiento de Hueso",
        "spells": ["Destello", "Prender"], "tip": "Cancela las animaciones de Alas Rotas (Q) con ataques básicos (Fast Q combo) para duplicar tu velocidad de daño."
    },
    "rumble": {
        "adv": ["Darius", "Garen", "Sion"], "counter": ["Irelia", "Yone", "Aatrox"], "syn": ["Jarvan IV", "Amumu", "Diana"],
        "core": ["Tormento de Liandry", "Cetros de cristal de Rylai", "Sombrero mortífero de Rabadon"],
        "sit": ["El reloj de arena de Zhonya", "Bastón del Vacío", "Morellonomicón"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Triunfo",
        "spells": ["Destello", "Prender"], "tip": "Mantén la barra de calor en la Zona Peligrosa (50-90) para potenciar tu Lanzallamas y traza El Equilibrador (R) en cuellos de botella."
    },
    "ryze": {
        "adv": ["Malzahar", "Veigar", "Galio"], "counter": ["Cassiopeia", "Anivia", "Zed"], "syn": ["Jarvan IV", "Amumu", "Nautilus"],
        "core": ["Vara de las edades", "Manamune", "Sombrero mortífero de Rabadon"],
        "sit": ["El reloj de arena de Zhonya", "Bastón del Vacío", "Morellonomicón"],
        "rune": "Fase Veloz", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Tormenta Creciente • Triunfo",
        "spells": ["Destello", "Barrera"], "tip": "Descarga la rotación E+W+Q para atrapar instantáneamente y activar la velocidad de movimiento de Fase Veloz."
    },
    "samira": {
        "adv": ["Miss Fortune", "Ashe", "Ezreal"], "counter": ["Nautilus", "Leona", "Alistar"], "syn": ["Nautilus", "Rell", "Leona"],
        "core": ["El recaudador", "Borde infinito", "sanguinario"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "La danza de la muerte"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Segundo Aire",
        "spells": ["Destello", "Extenuación"], "tip": "Encadena ataques y habilidades alternadas para subir de rango E a S rápidamente y activar Gatillo Infernal (R) mientras bloqueas proyectiles con W."
    },
    "senna": {
        "adv": ["Vayne", "Braum", "Alistar"], "counter": ["Blitzcrank", "Pyke", "Nautilus"], "syn": ["Tahm Kench", "Sion", "Nasus"],
        "core": ["Guadaña de la Niebla Negra", "Black Cleaver", "Manamune"],
        "sit": ["Recordatorio mortal", "Fajín de mercurio", "Ángel custodio"],
        "rune": "Pies Veloces", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Barrera"], "tip": "Recoge las almas de la niebla para ganar rango, daño y probabilidad de crítico infinito sin necesidad de farmear súbditos."
    },
    "seraphine": {
        "adv": ["Sona", "Soraka", "Braum"], "counter": ["Blitzcrank", "Pyke", "Nautilus"], "syn": ["Miss Fortune", "Amumu", "Ashe"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water"],
        "sit": ["Tormento de Liandry", "Cetros de cristal de Rylai", "Relicario de los Solari de Hierro"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Curación"], "tip": "Lanza Bis (R) a través de tus aliados para extender el alcance del hechizo hasta el fondo de las filas enemigas."
    },
    "sett": {
        "adv": ["Irelia", "Yasuo", "Riven"], "counter": ["Vayne", "Teemo", "Darius"], "syn": ["Diana", "Orianna", "Yuumi"],
        "core": ["Fuerza trinitaria", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["Corazón de acero", "malla de espinas", "Fuerza de la naturaleza"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Guarda Rompecaras (W) para cuando tu barra de Agallas esté al máximo y desata el puñetazo de daño verdadero central."
    },
    "shen": {
        "adv": ["Master Yi", "Tryndamere", "Jax"], "counter": ["Darius", "Mordekaiser", "Gwen"], "syn": ["Nocturne", "Twitch", "Kha'Zix"],
        "core": ["Égida del fuego solar", "Corazón de acero", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Fuente de Vida • Revestimiento de Hueso • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Prender"], "tip": "Usa Refugio Espiritual (W) para anular los ataques básicos enemigos y salva a tu aliado con Mantener la Postura (R) a través del mapa."
    },
    "shyvana": {
        "adv": ["Master Yi", "Amumu", "Rammus"], "counter": ["Lee Sin", "Olaf", "Kha'Zix"], "syn": ["Jarvan IV", "Diana", "Orianna"],
        "core": ["Fuerza trinitaria", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["Tormento de Liandry", "malla de espinas", "Fuerza de la naturaleza"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Cazador de Ojos",
        "spells": ["Destello", "Castigo"], "tip": "Asegura todos los dragones elementales para potenciar pasivamente tus estadísticas y habilidades dracónicas."
    },
    "singed": {
        "adv": ["Jax", "Tryndamere", "Riven"], "counter": ["Teemo", "Vayne", "Kayle"], "syn": ["Yuumi", "Lulu", "Zilean"],
        "core": ["Cetros de cristal de Rylai", "Tormento de Liandry", "Égida del fuego solar"],
        "sit": ["malla de espinas", "Fuerza de la naturaleza", "Corazón de acero"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Fantasmal"], "tip": "Haz proxy farming tras las torres enemigas para atraer al jungla y corre dejando tu Rastro de Veneno (Q) activo."
    },
    "sion": {
        "adv": ["Malphite", "Singed", "Nasus"], "counter": ["Aatrox", "Fiora", "Vayne"], "syn": ["Samira", "Yasuo", "Diana"],
        "core": ["Corazón de acero", "Égida del fuego solar", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Demolición • Condicionamiento • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Fantasmal"], "tip": "Carga Impacto Descendente (Q) desde la maleza para no ser visto y embiste torretas enemigas con Imparable (R)."
    },
    "sivir": {
        "adv": ["Caitlyn", "Blitzcrank", "Thresh"], "counter": ["Draven", "Tristana", "Twitch"], "syn": ["Yuumi", "Lulu", "Karma"],
        "core": ["Borde infinito", "El huracán de Runaan", "sanguinario"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Banda de Maná",
        "spells": ["Destello", "Curación"], "tip": "Usa Escudo de Hechizos (E) para bloquear ganchos y habilidades definitivas y activa A la Caza (R) para iniciar asedios con tu equipo."
    },
    "skarner": {
        "adv": ["Master Yi", "Kha'Zix", "Kindred"], "counter": ["Olaf", "Morgana", "Vayne"], "syn": ["Yasuo", "Orianna", "Samira"],
        "core": ["Corazón de acero", "Égida del fuego solar", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "El presagio de Randuin", "Protector pétreo"],
        "rune": "Réplica", "rune_desc": "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Castigo"], "tip": "Atraviesa muros con tu E y arrastra al carry enemigo hacia tu torre con Empalar (R)."
    },
    "smolder": {
        "adv": ["Sion", "Dr. Mundo", "Cho'Gath"], "counter": ["Draven", "Lucian", "Tristana"], "syn": ["Braum", "Thresh", "Nami"],
        "core": ["Manamune", "Fuerza trinitaria", "Borde infinito"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Pies Veloces", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Barrera"], "tip": "Acumula 225 cargas de Dracomanía para desbloquear daño verdadero en área y ejecución en su Aliento de Fuego (Q)."
    },
    "sona": {
        "adv": ["Braum", "Alistar", "Taric"], "counter": ["Blitzcrank", "Nautilus", "Pyke"], "syn": ["Miss Fortune", "Ezreal", "Seraphine"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water"],
        "sit": ["Incensario Ardiente", "Redención", "Relicario de los Solari de Hierro"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Curación"], "tip": "Rota constantemente tus tres auras en peleas de equipo y usa Crescendo (R) para cortar las canalizaciones rivales."
    },
    "soraka": {
        "adv": ["Braum", "Alistar", "Brand"], "counter": ["Blitzcrank", "Nautilus", "Pyke"], "syn": ["Jinx", "Vayne", "Tristana"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water"],
        "sit": ["Incensario Ardiente", "Redención", "Relicario de los Solari de Hierro"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Barrera"], "tip": "Acierta Invocación Estelar (Q) para regenerar vida antes de curar a tu aliado con W a coste cero de vida. Cura globalmente con Plegaria (R)."
    },
    "swain": {
        "adv": ["Yasuo", "Katarina", "Akali"], "counter": ["Sylas", "Vayne", "Brand"], "syn": ["Amumu", "Diana", "Samira"],
        "core": ["Tormento de Liandry", "Cetros de cristal de Rylai", "Sombrero mortífero de Rabadon"],
        "sit": ["El reloj de arena de Zhonya", "malla de espinas", "Morellonomicón"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Fantasmal"], "tip": "Mantén Ascensión Demoníaca (R) activa manteniéndote en rango de los campeones enemigos mientras drenas vida perpetuamente."
    },
    "syndra": {
        "adv": ["Veigar", "Annie", "Orianna"], "counter": ["Fizz", "Zed", "Kassadin"], "syn": ["Jarvan IV", "Vi", "Lee Sin"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Banda de Maná",
        "spells": ["Destello", "Prender"], "tip": "Acumula esferas oscuras con tu Q en el suelo antes de dispersar a los enemigos con Dispersar a los Débiles (E) y desatar Poder Desatado (R)."
    },
    "taliyah": {
        "adv": ["Lee Sin", "Kha'Zix", "Zed"], "counter": ["Fizz", "Kassadin", "Ekko"], "syn": ["Pantheon", "Renekton", "Nautilus"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Primer Golpe", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "spells": ["Destello", "Castigo"], "tip": "Coloca Tierra Deshilachada (E) para castigar cualquier salto enemigo y corta el mapa con Muro de la Tejedora (R)."
    },
    "talon": {
        "adv": ["Twisted Fate", "Lux", "Veigar"], "counter": ["Pantheon", "Galio", "Malphite"], "syn": ["Diana", "Amumu", "Jarvan IV"],
        "core": ["El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr", "El rencor de Serylda"],
        "sit": ["Colmillo de serpiente", "Ángel custodio", "La danza de la muerte"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Ingenio Cazador • Triunfo",
        "spells": ["Destello", "Prender"], "tip": "Salta los muros del mapa con Sendero del Asesino (E) para emboscar desde ángulos ciegos y activar tu pasiva de sangrado."
    },
    "teemo": {
        "adv": ["Garen", "Darius", "Nasus"], "counter": ["Malphite", "Irelia", "Sion"], "syn": ["Cassiopeia", "Singed", "Lillia"],
        "core": ["Tormento de Liandry", "Diente de Nashor", "Cetros de cristal de Rylai"],
        "sit": ["Sombrero mortífero de Rabadon", "Bastón del Vacío", "Morellonomicón"],
        "rune": "Estrategia Ofensiva", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Banda de Maná",
        "spells": ["Destello", "Prender"], "tip": "Ciega con Dardo Cegador (Q) a los atacantes automáticos y siembra hongos en las entradas de dragones y barones."
    },
    "thresh": {
        "adv": ["Leona", "Rakan", "Tristana"], "counter": ["Morgana", "Blitzcrank", "Sivir"], "syn": ["Kalista", "Jinx", "Draven"],
        "core": ["Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke"],
        "sit": ["Sudario del alba", "malla de espinas", "Relicario de los Solari de Hierro"],
        "rune": "Aumento Glacial", "rune_desc": "Inspiración: Destello Hextech • Entrega de Galletas • Perspicacia Cósmica • Revestimiento de Hueso",
        "spells": ["Destello", "Prender"], "tip": "Lanza tu Linterna (W) para rescatar a un aliado en peligro o acercar a tu jungla para una emboscada sorpresa."
    },
    "tristana": {
        "adv": ["Vayne", "Kai'Sa", "Ezreal"], "counter": ["Draven", "Caitlyn", "Nautilus"], "syn": ["Leona", "Nautilus", "Lulu"],
        "core": ["Borde infinito", "sanguinario", "Cañón de fuego rápido"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Curación"], "tip": "Carga la Carga Explosiva (E) con 4 disparos para reiniciar tu Salto Cohete (W) y encadenar derribos."
    },
    "tryndamere": {
        "adv": ["Garen", "Sion", "Nasus"], "counter": ["Jax", "Malphite", "Teemo"], "syn": ["Yuumi", "Lulu", "Zilean"],
        "core": ["Espada del Rey Arruinado", "Borde infinito", "sanguinario"],
        "sit": ["Recordatorio mortal", "La danza de la muerte", "Fajín de mercurio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Segundo Aire",
        "spells": ["Destello", "Fantasmal"], "tip": "Gestiona Furia Inmortal (R) en el último instante para absorber todo el daño enemigo antes de girar con E hacia la seguridad."
    },
    "twisted_fate": {
        "adv": ["Veigar", "Kassadin", "Twitch"], "counter": ["Yasuo", "Fizz", "Zed"], "syn": ["Nocturne", "Shen", "Camille"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Cañón de fuego rápido"],
        "sit": ["El reloj de arena de Zhonya", "Bastón del Vacío", "Morellonomicón"],
        "rune": "Primer Golpe", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "spells": ["Destello", "Prender"], "tip": "Selecciona la Carta Dorada de Escoge una Carta (W) antes de teletransportarte con Destino (R) para un aturdimiento ineludible."
    },
    "twitch": {
        "adv": ["Jinx", "Ashe", "Varus"], "counter": ["Draven", "Caitlyn", "Nautilus"], "syn": ["Yuumi", "Lulu", "Shen"],
        "core": ["Espada del Rey Arruinado", "El huracán de Runaan", "Borde infinito"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "sanguinario"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Fantasmal"], "tip": "Entra en sigilo con Emboscada (Q) y abre fuego con Vacíalo Todo (R) desde la retaguardia para atravesar a todo el equipo rival."
    },
    "urgot": {
        "adv": ["Garen", "Sion", "Nasus"], "counter": ["Fiora", "Vayne", "Jax"], "syn": ["Diana", "Orianna", "Malphite"],
        "core": ["Black Cleaver", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["malla de espinas", "Fuerza de la naturaleza", "El presagio de Randuin"],
        "rune": "Estrategia Ofensiva", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Gira alrededor del objetivo para disparar todas las escopetas de tus piernas con Purga (W) y ejecuta con Miedo de Ultratumba (R)."
    },
    "varus": {
        "adv": ["Jinx", "Miss Fortune", "Ashe"], "counter": ["Samira", "Draven", "Yasuo"], "syn": ["Nautilus", "Leona", "Braum"],
        "core": ["El cuchillo fantasma de Youmuu", "Manamune", "El rencor de Serylda"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "Fajín de mercurio"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Barrera"], "tip": "Detona las 3 marcas de Infección (W) disparando una Flecha Penetrante (Q) totalmente cargada para un daño letal a distancia."
    },
    "vayne": {
        "adv": ["Sion", "Dr. Mundo", "Cho'Gath"], "counter": ["Caitlyn", "Draven", "Teemo"], "syn": ["Lulu", "Milio", "Janna"],
        "core": ["Espada del Rey Arruinado", "Borde infinito", "El huracán de Runaan"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "sanguinario"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Segundo Aire",
        "spells": ["Destello", "Curación"], "tip": "Usa Voltereta (Q) durante Hora Final (R) para volverte invisible durante 1s y estampar enemigos contra la pared con Condena (E)."
    },
    "veigar": {
        "adv": ["Annie", "Twisted Fate", "Malzahar"], "counter": ["Kassadin", "Fizz", "Zed"], "syn": ["Jarvan IV", "Amumu", "Diana"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Velo de alma en pena"],
        "rune": "Primer Golpe", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "spells": ["Destello", "Barrera"], "tip": "Atrapa a los rivales con Horizonte de Sucesos (E) y destruye a su mago/tirador con Explosión Primordial (R)."
    },
    "vel_koz": {
        "adv": ["Veigar", "Annie", "Lux"], "counter": ["Zed", "Fizz", "Kassadin"], "syn": ["Amumu", "Jarvan IV", "Diana"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Tormento de Liandry"],
        "sit": ["El reloj de arena de Zhonya", "Bastón del Vacío", "Morellonomicón"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Barrera"], "tip": "Calcula el ángulo de 90 grados de Fisión de Plasma (Q) para desgastar desde fuera de la pantalla antes de desintegrar con R."
    },
    "vex": {
        "adv": ["Yasuo", "Katarina", "Irelia"], "counter": ["Syndra", "Orianna", "Lux"], "syn": ["Amumu", "Diana", "Jarvan IV"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Banda de Maná",
        "spells": ["Destello", "Prender"], "tip": "Interrumpe los saltos y deslizamientos rivales con la pasiva Penumbra y encadena lanzamientos de Oleada Sombra (R)."
    },
    "vi": {
        "adv": ["Jinx", "Twisted Fate", "Kassadin"], "counter": ["Olaf", "Rammus", "Morgana"], "syn": ["Yasuo", "Ahri", "Orianna"],
        "core": ["Fuerza trinitaria", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["malla de espinas", "Fuerza de la naturaleza", "Ángel custodio"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Impacto Repentino",
        "spells": ["Destello", "Castigo"], "tip": "Fija al tirador o mago enemigo con Asalto y Lesiones (R) imparable y encadena Rompemuros (Q) para derretir su armadura."
    },
    "viego": {
        "adv": ["Master Yi", "Amumu", "Kha'Zix"], "counter": ["Jax", "Rammus", "Lee Sin"], "syn": ["Diana", "Orianna", "Lulu"],
        "core": ["Espada del Rey Arruinado", "Fuerza trinitaria", "Guantelete de Sterak"],
        "sit": ["La danza de la muerte", "Fauces de Malmortius", "Ángel custodio"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Cazador de Ojos",
        "spells": ["Destello", "Castigo"], "tip": "Posee los cuerpos de los enemigos caídos para obtener sus habilidades, curarte e invocar Rompecorazones (R) sin coste."
    },
    "viktor": {
        "adv": ["Veigar", "Annie", "Twisted Fate"], "counter": ["Kassadin", "Fizz", "Zed"], "syn": ["Jarvan IV", "Amumu", "Diana"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Primer Golpe", "rune_desc": "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Cazador de Ojos",
        "spells": ["Destello", "Barrera"], "tip": "Aumenta tu Rayo de la Muerte (E) para limpiar oleadas de un solo tiro y corta canalizaciones con Tormenta del Caos (R)."
    },
    "vladimir": {
        "adv": ["Malphite", "Sion", "Singed"], "counter": ["Kassadin", "Anivia", "Ryze"], "syn": ["Amumu", "Diana", "Jarvan IV"],
        "core": ["Impulso cósmico", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Fase Veloz", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Tormenta Creciente • Triunfo",
        "spells": ["Destello", "Fantasmal"], "tip": "Carga tu Transfusión (Q) para desatar el mordisco potenciado y usa Charco de Sangre (W) para esquivar definitivas enemigas."
    },
    "volibear": {
        "adv": ["Sion", "Darius", "Nasus"], "counter": ["Jax", "Fiora", "Vayne"], "syn": ["Yasuo", "Samira", "Diana"],
        "core": ["Fuerza trinitaria", "Guantelete de Sterak", "malla de espinas"],
        "sit": ["Fuerza de la naturaleza", "Corazón de acero", "La danza de la muerte"],
        "rune": "Garras del Inmortal", "rune_desc": "Valor: Fuente de Vida • Revestimiento de Hueso • Sobrecrecimiento • Triunfo",
        "spells": ["Destello", "Prender"], "tip": "Inhabilita torretas enemigas con Tormenta del Heraldo (R) para asegurar zambullidas (dives) sin recibir daño de torre."
    },
    "warwick": {
        "adv": ["Master Yi", "Kha'Zix", "Sion"], "counter": ["Olaf", "Rammus", "Vayne"], "syn": ["Yuumi", "Lulu", "Seraphine"],
        "core": ["Espada del Rey Arruinado", "Fuerza trinitaria", "malla de espinas"],
        "sit": ["Guantelete de Sterak", "Fuerza de la naturaleza", "La danza de la muerte"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Castigo"], "tip": "Sigue el rastro de Cacería de Sangre (W) para emboscar enemigos bajos de vida y suprime con Opresión Infinita (R)."
    },
    "wukong": {
        "adv": ["Jax", "Tryndamere", "Aatrox"], "counter": ["Garen", "Darius", "Renekton"], "syn": ["Yasuo", "Orianna", "Diana"],
        "core": ["Fuerza trinitaria", "Black Cleaver", "Guantelete de Sterak"],
        "sit": ["La danza de la muerte", "malla de espinas", "Fauces de Malmortius"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Tenacidad • Segundo Aire",
        "spells": ["Destello", "Prender"], "tip": "Lanza tu clon con Guerrero Pícaro (W) antes de activar Ciclón (R) para levantar al equipo enemigo dos veces seguidas."
    },
    "xayah": {
        "adv": ["Samira", "Kai'Sa", "Vayne"], "counter": ["Caitlyn", "Varus", "Draven"], "syn": ["Rakan", "Nautilus", "Leona"],
        "core": ["Borde infinito", "El recaudador", "Cañón de fuego rápido"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "sanguinario"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Curación"], "tip": "Esquiva habilidades mortales saltando con Tormenta de Plumas (R) y atrae todas las plumas con Invocadora de Cuchillas (E) para inmovilizar."
    },
    "xin_zhao": {
        "adv": ["Kha'Zix", "Master Yi", "Kindred"], "counter": ["Jax", "Rammus", "Lee Sin"], "syn": ["Yasuo", "Orianna", "Diana"],
        "core": ["Fuerza trinitaria", "Guantelete de Sterak", "La danza de la muerte"],
        "sit": ["malla de espinas", "Fauces de Malmortius", "Ángel custodio"],
        "rune": "Conquistador", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Impacto Repentino",
        "spells": ["Destello", "Castigo"], "tip": "Activa Guardia Creciente (R) para repeler a los rivales y volverte inmune al daño de todos los enemigos fuera de tu círculo."
    },
    "yasuo": {
        "adv": ["Lux", "Miss Fortune", "Twisted Fate"], "counter": ["Renekton", "Pantheon", "Darius"], "syn": ["Malphite", "Diana", "Alistar"],
        "core": ["Borde infinito", "Espada del Rey Arruinado", "La danza de la muerte"],
        "sit": ["Guantelete de Sterak", "Recordatorio mortal", "Ángel custodio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Revestimiento de Hueso",
        "spells": ["Destello", "Prender"], "tip": "Bloquea proyectiles clave con Muro de Viento (W) y espera a que tus aliados levanten a los rivales para conectar Último Aliento (R)."
    },
    "yone": {
        "adv": ["Veigar", "Lux", "Kassadin"], "counter": ["Renekton", "Pantheon", "Jax"], "syn": ["Yasuo", "Diana", "Orianna"],
        "core": ["Borde infinito", "Espada del Rey Arruinado", "La danza de la muerte"],
        "sit": ["Guantelete de Sterak", "Recordatorio mortal", "Ángel custodio"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Revestimiento de Hueso",
        "spells": ["Destello", "Prender"], "tip": "Sal en Alma Desatada (E) para comerciar daño seguro y regresa a tu cuerpo limpiando ralentizaciones tras ejecutar Destino Sellado (R)."
    },
    "yunara": {
        "adv": ["Vayne", "Kai'Sa", "Samira"], "counter": ["Caitlyn", "Draven", "Nautilus"], "syn": ["Thresh", "Lulu", "Braum"],
        "core": ["Borde infinito", "El recaudador", "Cañón de fuego rápido"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "sanguinario"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Curación"], "tip": "Mantén la distancia máxima de hostigamiento y aprovecha tus ráfagas potenciadas para desgastar a los tiradores rivales."
    },
    "yuumi": {
        "adv": ["Braum", "Alistar", "Taric"], "counter": ["Blitzcrank", "Pyke", "Nautilus"], "syn": ["Jinx", "Twitch", "Zeri"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water"],
        "sit": ["Incensario Ardiente", "Redención", "Relicario de los Solari de Hierro"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Prender", "Extenuación"], "tip": "Sáltate botas y Destello; acompaña a tu carry con ¡Tú y Yo! (W) y cura continuamente con ¡Presteza Gatuna! (E)."
    },
    "zed": {
        "adv": ["Lux", "Veigar", "Twisted Fate"], "counter": ["Malphite", "Pantheon", "Lissandra"], "syn": ["Diana", "Jarvan IV", "Vi"],
        "core": ["El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr", "El rencor de Serylda"],
        "sit": ["Colmillo de serpiente", "Ángel custodio", "Fauces de Malmortius"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Ingenio Cazador • Triunfo",
        "spells": ["Destello", "Prender"], "tip": "Coloca tu Sombra Viviente (W) de forma lateral para conectar una doble Shuriken (Q) y asegura la eliminación con Marca de la Muerte (R)."
    },
    "zeri": {
        "adv": ["Vayne", "Kai'Sa", "Ashe"], "counter": ["Draven", "Tristana", "Nautilus"], "syn": ["Yuumi", "Lulu", "Janna"],
        "core": ["Espada del Rey Arruinado", "Borde infinito", "El huracán de Runaan"],
        "sit": ["Recordatorio mortal", "Ángel custodio", "sanguinario"],
        "rune": "Compás Letal", "rune_desc": "Precisión: Triunfo • Golpe de Gracia • Leyenda: Linaje • Entrega de Galletas",
        "spells": ["Destello", "Fantasmal"], "tip": "Deslízate sobre los muros con Sobrecarga (E) y desata Choque Eléctrico (R) para ganar velocidad de movimiento infinita mientras disparas."
    },
    "ziggs": {
        "adv": ["Veigar", "Annie", "Malzahar"], "counter": ["Zed", "Fizz", "Yasuo"], "syn": ["Jarvan IV", "Amumu", "Diana"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Tormento de Liandry"],
        "sit": ["El reloj de arena de Zhonya", "Bastón del Vacío", "Morellonomicón"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Barrera"], "tip": "Ejecuta torretas bajas de vida instantáneamente con Carga Concentrada (W) y bombardea peleas lejanas con Megabomba Infernal (R)."
    },
    "zilean": {
        "adv": ["Alistar", "Braum", "Sett"], "counter": ["Blitzcrank", "Pyke", "Nautilus"], "syn": ["Darius", "Hecarim", "Master Yi"],
        "core": ["Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water"],
        "sit": ["Incensario Ardiente", "Redención", "Relicario de los Solari de Hierro"],
        "rune": "Aery", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
        "spells": ["Destello", "Extenuación"], "tip": "Acelera a tus luchadores aliados con Distorsión Temporal (E) y resucita al carry con Cambio Temporal (R)."
    },
    "zoe": {
        "adv": ["Lux", "Veigar", "Twisted Fate"], "counter": ["Yasuo", "Fizz", "Zed"], "syn": ["Jarvan IV", "Amumu", "Diana"],
        "core": ["Eco de Luden", "Sombrero mortífero de Rabadon", "Bastón del Vacío"],
        "sit": ["El reloj de arena de Zhonya", "Tormento de Liandry", "Morellonomicón"],
        "rune": "Electrocutar", "rune_desc": "Dominación: Impacto Repentino • Cazador de Ojos • Cazador Voraz • Banda de Maná",
        "spells": ["Destello", "Prender"], "tip": "Lanza Burbuja Dormilona (E) a través de muros para multiplicar su alcance y conecta una Estrella Fugaz (Q) a máxima distancia."
    },
    "zyra": {
        "adv": ["Braum", "Alistar", "Leona"], "counter": ["Blitzcrank", "Pyke", "Nautilus"], "syn": ["Miss Fortune", "Jhin", "Ashe"],
        "core": ["Guadaña de la Niebla Negra", "Tormento de Liandry", "Cetros de cristal de Rylai"],
        "sit": ["Morellonomicón", "Sombrero mortífero de Rabadon", "El reloj de arena de Zhonya"],
        "rune": "Cometa Arcano", "rune_desc": "Brujería: Banda de Maná • Trascendencia • Piroláser • Golpe de Gracia",
        "spells": ["Destello", "Prender"], "tip": "Siembra semillas con W antes de lanzar Q o E para hacer brotar plantas escupidoras y enfurécelas con Tuercespinas (R)."
    }
}

# Update function for champion JSON lists
def update_champion_list(champs):
    updated = []
    for c in champs:
        cid = c.get("id", "").lower().strip()
        spec = SPECS.get(cid)
        if not spec:
            # Fallback if id variation
            print(f"Warning: spec not found for {cid}")
            updated.append(c)
            continue
        
        c["advantageAgainst"] = spec["adv"]
        c["counteredBy"] = spec["counter"]
        c["synergies"] = spec["syn"]
        c["coreItems"] = spec["core"]
        c["coreItemsIcons"] = [get_item_icon(name) for name in spec["core"]]
        c["situationalItems"] = spec["sit"]
        c["situationalItemsIcons"] = [get_item_icon(name) for name in spec["sit"]]
        c["recommendedRunes"] = spec["rune"] + " (" + spec["rune_desc"].split(":")[0] + ")"
        c["runeTreeDetails"] = spec["rune_desc"]
        c["primaryRuneIconUrl"] = RUNE_ICONS.get(spec["rune"], RUNE_ICONS["Conquistador"])
        c["recommendedSpells"] = spec["spells"]
        c["spellsIcons"] = [SPELL_ICONS.get(s, SPELL_ICONS["Destello"]) for s in spec["spells"]]
        c["tacticalAdvice"] = spec["tip"]
        updated.append(c)
    return updated

# Process part 1
with open('app/src/main/res/raw/champions_part1.json') as f:
    p1 = json.load(f)
p1_updated = update_champion_list(p1)
with open('app/src/main/res/raw/champions_part1.json', 'w') as f:
    json.dump(p1_updated, f, ensure_ascii=False, indent=2)

# Process part 2
with open('app/src/main/res/raw/champions_part2.json') as f:
    p2 = json.load(f)
p2_updated = update_champion_list(p2)
with open('app/src/main/res/raw/champions_part2.json', 'w') as f:
    json.dump(p2_updated, f, ensure_ascii=False, indent=2)

print(f"Successfully updated Part 1 ({len(p1_updated)} champs) and Part 2 ({len(p2_updated)} champs).")
