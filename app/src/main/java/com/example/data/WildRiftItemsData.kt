package com.example.data

import com.example.model.ItemCategory
import com.example.model.WildRiftItem

object WildRiftItemsData {
    private const val CDN_VERSION = "14.24.1"
    private const val ITEM_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/item"

    val list: List<WildRiftItem> = listOf(
        // ==========================================
        // OBJETOS FÍSICOS (AD / CRÍTICO / LUCHADOR / ASESINO)
        // ==========================================
        WildRiftItem(
            id = "infinity_edge",
            name = "Filo del Infinito",
            category = ItemCategory.PHYSICAL,
            goldCost = 3400,
            stats = "+55 Daño de Ataque, +25% Prob. de Golpe Crítico",
            passive = "Pasiva - Infinito: Los golpes críticos infligen un 205% de daño en lugar del estándar 175%.",
            iconUrl = "$ITEM_IMG/3031.png"
        ),
        WildRiftItem(
            id = "blade_ruined_king",
            name = "Hoja del Rey Arruinado",
            category = ItemCategory.PHYSICAL,
            goldCost = 3200,
            stats = "+20 Daño de Ataque, +35% Velocidad de Ataque, +10% Vampirismo Físico",
            passive = "Pasiva - Hoja de la Niebla: Ataques infligen 9% (cuerpo a cuerpo) / 6% (a distancia) de la vida actual del objetivo como daño físico adicional y roban velocidad de movimiento tras 3 impactos.",
            iconUrl = "$ITEM_IMG/3153.png"
        ),
        WildRiftItem(
            id = "trinity_force",
            name = "Fuerza de la Trinidad",
            category = ItemCategory.PHYSICAL,
            goldCost = 3533,
            stats = "+30 Daño de Ataque, +30% Velocidad de Ataque, +250 Vida Máxima, +25 Aceleración de Habilidad, +5% Velocidad Mov.",
            passive = "Pasiva - Hoja Encantada: Usar una habilidad potencia tu siguiente ataque básico con un 200% de daño base adicional.",
            iconUrl = "$ITEM_IMG/3078.png"
        ),
        WildRiftItem(
            id = "black_cleaver",
            name = "Cuchilla Negra",
            category = ItemCategory.PHYSICAL,
            goldCost = 3000,
            stats = "+40 Daño de Ataque, +350 Vida Máxima, +20 Aceleración de Habilidad",
            passive = "Pasiva - Hendir: Infligir daño físico reduce la armadura del objetivo un 4% durante 6s (acumulable hasta 24%). Otorga velocidad de movimiento al dañar.",
            iconUrl = "$ITEM_IMG/3071.png"
        ),
        WildRiftItem(
            id = "immortal_shieldbow",
            name = "Arcoescudo Inmortal",
            category = ItemCategory.PHYSICAL,
            goldCost = 3200,
            stats = "+40 Daño de Ataque, +25% Prob. Crítico, +10% Vampirismo Físico",
            passive = "Pasiva - Salvavidas: Al recibir daño que reduciría tu vida por debajo del 35%, otorga un escudo de 200-650 de absorción y +10% de velocidad de ataque durante 5 segundos.",
            iconUrl = "$ITEM_IMG/6673.png"
        ),
        WildRiftItem(
            id = "deaths_dance",
            name = "Baile de la Muerte",
            category = ItemCategory.PHYSICAL,
            goldCost = 3000,
            stats = "+35 Daño de Ataque, +40 Armadura, +15 Aceleración de Habilidad",
            passive = "Pasiva - Digerir: El 35% del daño físico recibido se almacena y se sufre como daño continuo durante 3s. Los derribos purgan el daño acumulado y restauran 10% de vida máxima.",
            iconUrl = "$ITEM_IMG/6333.png"
        ),
        WildRiftItem(
            id = "the_collector",
            name = "La Recaudadora",
            category = ItemCategory.PHYSICAL,
            goldCost = 2900,
            stats = "+40 Daño de Ataque, +25% Prob. Crítico, +10 Letalidad",
            passive = "Pasiva - Muerte y Tributo: Infligir daño que deje a un campeón enemigo por debajo del 5% de su vida máxima lo ejecuta instantáneamente y te otorga 25 de oro adicional.",
            iconUrl = "$ITEM_IMG/6676.png"
        ),
        WildRiftItem(
            id = "rapid_firecannon",
            name = "Cañón de Fuego Rápido",
            category = ItemCategory.PHYSICAL,
            goldCost = 2800,
            stats = "+25% Prob. Crítico, +35% Velocidad de Ataque, +5% Velocidad Mov.",
            passive = "Pasiva - Francotirador: Moverse y atacar genera cargas energizadas. Al máximo, tu siguiente ataque tiene +150 de alcance adicional e inflige 50-120 de daño mágico.",
            iconUrl = "$ITEM_IMG/3094.png"
        ),
        WildRiftItem(
            id = "phantom_dancer",
            name = "Bailarín Espectral",
            category = ItemCategory.PHYSICAL,
            goldCost = 2800,
            stats = "+25 Daño de Ataque, +25% Prob. Crítico, +30% Velocidad de Ataque, +7% Velocidad Mov.",
            passive = "Pasiva - Vals Espectral: Los ataques otorgan efecto fantasmal y hasta un 30% de velocidad de ataque adicional tras 4 ataques consecutivos.",
            iconUrl = "$ITEM_IMG/3046.png"
        ),
        WildRiftItem(
            id = "mortal_reminder",
            name = "Recordatorio Mortal",
            category = ItemCategory.PHYSICAL,
            goldCost = 3000,
            stats = "+30 Daño de Ataque, +25% Prob. Crítico, +30% Penetración de Armadura",
            passive = "Pasiva - Sepulturero: Infligir daño físico a campeones enemigos aplica Heridas Graves (50%) durante 3s para anular curaciones masivas.",
            iconUrl = "$ITEM_IMG/3033.png"
        ),
        WildRiftItem(
            id = "seryldas_grudge",
            name = "Rencor de Serylda",
            category = ItemCategory.PHYSICAL,
            goldCost = 3000,
            stats = "+40 Daño de Ataque, +15 Aceleración de Habilidad, +30% Penetración de Armadura",
            passive = "Pasiva - Frío Amargo: Las habilidades dañinas ralentizan a los enemigos un 30% durante 1s.",
            iconUrl = "$ITEM_IMG/6694.png"
        ),
        WildRiftItem(
            id = "serpents_fang",
            name = "Colmillo de Serpiente",
            category = ItemCategory.PHYSICAL,
            goldCost = 2800,
            stats = "+50 Daño de Ataque, +12 Letalidad, +10 Aceleración de Habilidad",
            passive = "Pasiva - Destructora de Escudos: Dañar a un campeón reduce sus escudos entrantes un 50% (cuerpo a cuerpo) / 35% (a distancia) y destruye escudos activos.",
            iconUrl = "$ITEM_IMG/6695.png"
        ),
        WildRiftItem(
            id = "youmuus_ghostblade",
            name = "Espada Fantasma de Youmuu",
            category = ItemCategory.PHYSICAL,
            goldCost = 2900,
            stats = "+55 Daño de Ataque, +15 Letalidad, +15 Aceleración de Habilidad",
            passive = "Pasiva - Impulso: Moverse acumula velocidad de movimiento fuera de combate (hasta +40). A 100 cargas otorga +25% de velocidad de ataque en tu siguiente impacto.",
            iconUrl = "$ITEM_IMG/3142.png"
        ),
        WildRiftItem(
            id = "edge_of_night",
            name = "Filo de la Noche",
            category = ItemCategory.PHYSICAL,
            goldCost = 3150,
            stats = "+50 Daño de Ataque, +250 Vida Máxima, +10 Letalidad",
            passive = "Pasiva - Anulación: Otorga un escudo de hechizos pasivo que bloquea la siguiente habilidad enemiga (enfriamiento de 35s).",
            iconUrl = "$ITEM_IMG/3814.png"
        ),
        WildRiftItem(
            id = "hullbreaker",
            name = "Rompecascos",
            category = ItemCategory.PHYSICAL,
            goldCost = 3000,
            stats = "+55 Daño de Ataque, +325 Vida Máxima, +5% Velocidad Mov.",
            passive = "Pasiva - Grupo de Abordaje: Si no hay aliados cerca, obtienes hasta 60 de armadura y resistencia mágica y tus súbditos cercanos aumentan un 200% su daño a torretas.",
            iconUrl = "$ITEM_IMG/3181.png"
        ),
        WildRiftItem(
            id = "divine_sunderer",
            name = "Desgarrador Divino",
            category = ItemCategory.PHYSICAL,
            goldCost = 3450,
            stats = "+25 Daño de Ataque, +400 Vida Máxima, +20 Aceleración de Habilidad",
            passive = "Pasiva - Hoja Encantada: Tras usar una habilidad, tu siguiente ataque inflige 10% de la vida máxima del enemigo como daño físico y te cura un 7% de tu vida máxima.",
            iconUrl = "$ITEM_IMG/6632.png"
        ),
        WildRiftItem(
            id = "steraks_gage",
            name = "Guantelete de Sterak",
            category = ItemCategory.PHYSICAL,
            goldCost = 3100,
            stats = "+400 Vida Máxima",
            passive = "Pasiva - Furia de Sterak: Otorga 50% de daño de ataque base como daño adicional. Al caer por debajo del 35% de vida otorga un escudo del 75% de tu vida adicional y tenacidad.",
            iconUrl = "$ITEM_IMG/3053.png"
        ),
        WildRiftItem(
            id = "eclipse",
            name = "Eclipse",
            category = ItemCategory.PHYSICAL,
            goldCost = 2900,
            stats = "+55 Daño de Ataque, +20 Aceleración de Habilidad",
            passive = "Pasiva - Luna Creciente: Impactar a un campeón con 2 ataques o habilidades individuales en 1.5s inflige 6% de su vida máxima y te otorga un escudo de 160 (+40% de AD extra).",
            iconUrl = "$ITEM_IMG/6692.png"
        ),
        WildRiftItem(
            id = "manamune",
            name = "Manamune / Muramana",
            category = ItemCategory.PHYSICAL,
            goldCost = 2700,
            stats = "+25 Daño de Ataque, +1000 Maná Máximo, +10 Aceleración de Habilidad",
            passive = "Pasiva - Asombro: Otorga daño de ataque adicional equivalente al 1.5% de tu maná máximo. Los ataques y habilidades consumen maná para infligir daño adicional por impacto.",
            iconUrl = "$ITEM_IMG/3004.png"
        ),

        // ==========================================
        // OBJETOS MÁGICOS (PODER DE HABILIDAD / AP)
        // ==========================================
        WildRiftItem(
            id = "rabadons_deathcap",
            name = "Sombrero Mortal de Rabadon",
            category = ItemCategory.MAGIC,
            goldCost = 3400,
            stats = "+120 Poder de Habilidad",
            passive = "Pasiva - Opus Mágico: Aumenta tu Poder de Habilidad total en un 40-45% adicional.",
            iconUrl = "$ITEM_IMG/3089.png"
        ),
        WildRiftItem(
            id = "ludens_echo",
            name = "Eco de Luden",
            category = ItemCategory.MAGIC,
            goldCost = 3000,
            stats = "+85 Poder de Habilidad, +300 Maná Máximo, +20 Aceleración de Habilidad, +7% Velocidad Mov.",
            passive = "Pasiva - Tiro Eco: Moverse y lanzar hechizos genera cargas; al llegar a 100, la siguiente habilidad inflige 100 (+10% AP) de daño mágico a 4 objetivos cercanos.",
            iconUrl = "https://lolwildriftbuild.com/wp-content/uploads/2020/10/Luden_sEcho.jpg"
        ),
        WildRiftItem(
            id = "infinity_orb",
            name = "Orbe del Infinito",
            category = ItemCategory.MAGIC,
            goldCost = 3150,
            stats = "+85 Poder de Habilidad, +5% Velocidad de Movimiento, +15 Penetración Mágica",
            passive = "Pasiva - Destino Inevitable: Tus habilidades y ataques potenciados asestan golpes críticos mágicos (120% de daño) contra enemigos con menos del 35% de vida.",
            iconUrl = "$ITEM_IMG/4628.png"
        ),
        WildRiftItem(
            id = "liandrys_torment",
            name = "Tormento de Liandry",
            category = ItemCategory.MAGIC,
            goldCost = 3000,
            stats = "+70 Poder de Habilidad, +250 Vida Máxima",
            passive = "Pasiva - Tormento: Las habilidades queman a los enemigos infligiendo un 2% de su vida máxima como daño mágico cada segundo durante 3s (daño duplicado si están inmovilizados).",
            iconUrl = "$ITEM_IMG/6653.png"
        ),
        WildRiftItem(
            id = "crown_shattered_queen",
            name = "Corona de la Reina Ahogada",
            category = ItemCategory.MAGIC,
            goldCost = 3000,
            stats = "+70 Poder de Habilidad, +200 Vida, +200 Maná, +15 Aceleración de Habilidad",
            passive = "Pasiva - Salvaguardia: Otorga un escudo que reduce un 70% el daño recibido durante 1.5s al ser atacado por campeones. Otorga +20 AP mientras persista.",
            iconUrl = "$ITEM_IMG/4644.png"
        ),
        WildRiftItem(
            id = "void_staff",
            name = "Báculo del Vacío",
            category = ItemCategory.MAGIC,
            goldCost = 2800,
            stats = "+70 Poder de Habilidad, +40% Penetración Mágica",
            passive = "Pasiva - Disolución: Ignora el 40% de la resistencia mágica total de los objetivos.",
            iconUrl = "$ITEM_IMG/3135.png"
        ),
        WildRiftItem(
            id = "lich_bane",
            name = "Perdición del Liche",
            category = ItemCategory.MAGIC,
            goldCost = 2950,
            stats = "+80 Poder de Habilidad, +15 Aceleración de Habilidad, +7% Velocidad Mov.",
            passive = "Pasiva - Hoja Encantada: Tras usar una habilidad, tu siguiente ataque inflige 75 (+50% AP) de daño mágico adicional por impacto.",
            iconUrl = "$ITEM_IMG/3100.png"
        ),
        WildRiftItem(
            id = "nashors_tooth",
            name = "Diente de Nashor",
            category = ItemCategory.MAGIC,
            goldCost = 3000,
            stats = "+70 Poder de Habilidad, +45% Velocidad de Ataque, +15 Aceleración de Habilidad",
            passive = "Pasiva - Mordisco de Icor: Los ataques básicos infligen 15 (+20% AP) de daño mágico adicional en cada impacto.",
            iconUrl = "$ITEM_IMG/3115.png"
        ),
        WildRiftItem(
            id = "rylais_crystal_scepter",
            name = "Cetro de Cristal de Rylai",
            category = ItemCategory.MAGIC,
            goldCost = 2700,
            stats = "+70 Poder de Habilidad, +350 Vida Máxima",
            passive = "Pasiva - Helada Fría: Las habilidades mágicas ralentizan la velocidad de movimiento de los enemigos un 30% durante 1s.",
            iconUrl = "$ITEM_IMG/3116.png"
        ),
        WildRiftItem(
            id = "morellonomicon",
            name = "Morellonomicón",
            category = ItemCategory.MAGIC,
            goldCost = 2600,
            stats = "+75 Poder de Habilidad, +150 Vida, +15 Penetración Mágica",
            passive = "Pasiva - Afligido: Infligir daño mágico aplica Heridas Graves (50%) a los campeones enemigos durante 3 segundos para cortar curaciones.",
            iconUrl = "$ITEM_IMG/3165.png"
        ),

        // ==========================================
        // OBJETOS DEFENSIVOS (ARMADURA / RESISTENCIA MÁGICA / TANQUES)
        // ==========================================
        WildRiftItem(
            id = "heartsteel",
            name = "Corazón de Acero",
            category = ItemCategory.DEFENSE,
            goldCost = 3000,
            stats = "+700 Vida Máxima, +150% Regeneración de Vida, +20 Aceleración de Habilidad",
            passive = "Pasiva - Consumo Colosal: Carga un golpe devastador contra campeones enemigos cercanos cada 30s que inflige daño según tu vida e incrementa tu vida máxima de forma permanente.",
            iconUrl = "$ITEM_IMG/3084.png"
        ),
        WildRiftItem(
            id = "sunfire_aegis",
            name = "Égida de Fuego Solar",
            category = ItemCategory.DEFENSE,
            goldCost = 3000,
            stats = "+450 Vida Máxima, +40 Armadura, +15 Aceleración de Habilidad",
            passive = "Pasiva - Inmolar: Inflige daño mágico por segundo a enemigos cercanos. Al acumular 6 cargas, tus ataques básicos queman a los enemigos con daño continuo.",
            iconUrl = "$ITEM_IMG/3068.png"
        ),
        WildRiftItem(
            id = "thornmail",
            name = "Malla de Espinas",
            category = ItemCategory.DEFENSE,
            goldCost = 2700,
            stats = "+200 Vida Máxima, +75 Armadura",
            passive = "Pasiva - Espinas: Al recibir un ataque básico, devuelve daño mágico al atacante y le aplica Heridas Graves (50%) durante 3s.",
            iconUrl = "$ITEM_IMG/3075.png"
        ),
        WildRiftItem(
            id = "dead_mans_plate",
            name = "Coraza del Muerto",
            category = ItemCategory.DEFENSE,
            goldCost = 2800,
            stats = "+250 Vida Máxima, +50 Armadura, +5% Velocidad Mov.",
            passive = "Pasiva - Naufragio: Acumula hasta 50 de velocidad de movimiento al desplazarte. Al máximo, tu siguiente ataque inflige daño adicional y ralentiza un 50% al objetivo.",
            iconUrl = "$ITEM_IMG/3742.png"
        ),
        WildRiftItem(
            id = "randuins_omen",
            name = "Presagio de Randuin",
            category = ItemCategory.DEFENSE,
            goldCost = 2800,
            stats = "+400 Vida Máxima, +55 Armadura",
            passive = "Pasiva - Templanza: Reduce el daño de los golpes críticos recibidos un 16% y ralentiza la velocidad de ataque del agresor un 15% durante 1.5s.",
            iconUrl = "$ITEM_IMG/3143.png"
        ),
        WildRiftItem(
            id = "force_of_nature",
            name = "Fuerza de la Naturaleza",
            category = ItemCategory.DEFENSE,
            goldCost = 2850,
            stats = "+350 Vida Máxima, +50 Resistencia Mágica, +5% Velocidad Mov.",
            passive = "Pasiva - Inamovible: Recibir daño mágico acumula cargas de Firmeza. Al llegar a 6 cargas, ganas 10% de velocidad y reduces un 25% todo el daño mágico recibido.",
            iconUrl = "$ITEM_IMG/4401.png"
        ),
        WildRiftItem(
            id = "kaenic_rookern",
            name = "Rookern Kaénico",
            category = ItemCategory.DEFENSE,
            goldCost = 2800,
            stats = "+350 Vida Máxima, +100% Regeneración de Vida, +85 Resistencia Mágica",
            passive = "Pasiva - Perdición de Mago: Tras no recibir daño mágico durante 12s, obtienes un escudo mágico que absorbe daño igual a 50-150 + 14% de tu Vida Máxima.",
            iconUrl = "$ITEM_IMG/2504.png"
        ),
        WildRiftItem(
            id = "frozen_heart",
            name = "Corazón de Hielo",
            category = ItemCategory.DEFENSE,
            goldCost = 2700,
            stats = "+80 Armadura, +400 Maná Máximo, +20 Aceleración de Habilidad",
            passive = "Pasiva - Abrazo Invernal: Reduce la velocidad de ataque de todos los enemigos cercanos un 20% y reduce el daño recibido por impactos básicos.",
            iconUrl = "$ITEM_IMG/3110.png"
        ),

        // ==========================================
        // OBJETOS DE SOPORTE & UTILIDAD
        // ==========================================
        WildRiftItem(
            id = "spectral_sickle",
            name = "Hoz Espectral",
            category = ItemCategory.SUPPORT,
            goldCost = 500,
            stats = "+10 Poder de Habilidad o +6 AD, +5 Aceleración Habilidad",
            passive = "Pasiva - Tributo: Golpear campeones o estructuras enemigas con ataques o habilidades otorga oro. Se transforma en Garra de la Media Luna tras acumular 500 de oro.",
            iconUrl = "$ITEM_IMG/3854.png"
        ),
        WildRiftItem(
            id = "relic_shield",
            name = "Escudo Reliquia",
            category = ItemCategory.SUPPORT,
            goldCost = 500,
            stats = "+100 Vida Máxima, +5 Aceleración de Habilidad",
            passive = "Pasiva - Botín de Guerra: Ejecuta súbditos con menos del 50% de vida otorgando el oro completo tanto a ti como al aliado más cercano. Evoluciona a Broquel de Targon.",
            iconUrl = "$ITEM_IMG/3858.png"
        ),
        WildRiftItem(
            id = "ardent_censer",
            name = "Incensario Ardiente",
            category = ItemCategory.SUPPORT,
            goldCost = 2800,
            stats = "+60 Poder de Habilidad, +250 Vida Máxima, +10 Aceleración de Habilidad, +5% Velocidad Mov.",
            passive = "Pasiva - Fervor: Curar o escudar a un aliado le otorga entre 10-30% de velocidad de ataque y sus ataques infligen 16-30 de daño mágico adicional al impactar.",
            iconUrl = "$ITEM_IMG/3504.png"
        ),
        WildRiftItem(
            id = "staff_flowing_water",
            name = "Bastón de Agua Fluyente",
            category = ItemCategory.SUPPORT,
            goldCost = 2500,
            stats = "+65 Poder de Habilidad, +350 Maná, +20 Aceleración de Habilidad",
            passive = "Pasiva - Rápidos: Curar o escudar a un aliado os otorga a ambos 20 de aceleración de habilidad y entre 20-40 de poder de habilidad adicional durante 4 segundos.",
            iconUrl = "$ITEM_IMG/6616.png"
        ),
        WildRiftItem(
            id = "redemption_support",
            name = "Redención",
            category = ItemCategory.SUPPORT,
            goldCost = 2600,
            stats = "+250 Vida Máxima, +15 Aceleración de Habilidad, +100% Regeneración Maná",
            passive = "Pasiva - Bendición: Invoca un rayo de luz celestial tras 2.5s que cura 200-400 a todos los aliados e inflige 10% de daño verdadero de vida máxima a los enemigos.",
            iconUrl = "$ITEM_IMG/3107.png"
        ),
        WildRiftItem(
            id = "knights_vow",
            name = "Promesa del Caballero",
            category = ItemCategory.SUPPORT,
            goldCost = 2700,
            stats = "+300 Vida Máxima, +40 Armadura, +10 Aceleración de Habilidad",
            passive = "Pasiva - Sacrificio: Designa a un Aliado Digno. Mientras esté cerca, redirige el 15% del daño que reciba hacia ti y te curas un porcentaje del daño que él inflija.",
            iconUrl = "$ITEM_IMG/3109.png"
        ),

        // ==========================================
        // BOTAS & ENCANTAMIENTOS ACTIVOS (EXCLUSIVOS DE WILD RIFT)
        // ==========================================
        WildRiftItem(
            id = "plated_steelcaps",
            name = "Punteras de Acero",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 1000,
            stats = "+40 Velocidad de Movimiento, +15 Armadura",
            passive = "Pasiva - Bloqueo: Reduce el daño de los ataques básicos enemigos un 12%.",
            iconUrl = "$ITEM_IMG/3047.png"
        ),
        WildRiftItem(
            id = "mercurys_treads",
            name = "Botas de Mercurio",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 1000,
            stats = "+40 Velocidad de Movimiento, +15 Resistencia Mágica",
            passive = "Pasiva - Tenacidad: Reduce la duración de aturdimientos, ralentizaciones, provocaciones, silencios y ceguera en un 35%.",
            iconUrl = "$ITEM_IMG/3111.png"
        ),
        WildRiftItem(
            id = "zhonya_enchant",
            name = "Encantamiento de Reloj de Arena de Zhonya",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 800,
            stats = "Mejora activa para cualquier bota mejorada",
            passive = "Activa - Éxtasis: Te vuelve invulnerable e inalcanzable durante 2.5s, pero te impide moverte, atacar o lanzar habilidades.",
            iconUrl = "$ITEM_IMG/3157.png"
        ),
        WildRiftItem(
            id = "quicksilver_enchant",
            name = "Encantamiento de Fajín de Mercurio",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 800,
            stats = "Mejora activa para cualquier bota mejorada",
            passive = "Activa - Mercurio: Elimina al instante todos los efectos de control de masas activos en tu campeón y otorga inmunidad a CC durante 0.75s.",
            iconUrl = "$ITEM_IMG/3140.png"
        ),
        WildRiftItem(
            id = "gargoyle_enchant",
            name = "Encantamiento de Gárgola",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 800,
            stats = "Mejora activa para cualquier bota mejorada",
            passive = "Activa - Piel Pétrea: Otorga un escudo masivo equivalente al 20% de tu vida máxima (aumentado al 40% si hay 3+ enemigos cerca).",
            iconUrl = "$ITEM_IMG/3193.png"
        ),
        WildRiftItem(
            id = "teleport_enchant",
            name = "Encantamiento de Teletransporte",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 800,
            stats = "Mejora activa para cualquier bota mejorada",
            passive = "Activa - Teletransporte: Tras canalizar durante 3.5s, te teletransportas a una estructura aliada, centinela o súbdito en cualquier parte del mapa.",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/assets/items/icons2d/8000_summoner_teleport.png"
        ),
        WildRiftItem(
            id = "protobelt_enchant",
            name = "Encantamiento de Cinturón Cohete",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 800,
            stats = "Mejora activa para cualquier bota mejorada",
            passive = "Activa - Impulso Ígneo: Te deslizas hacia adelante disparando una andanada de misiles que infligen daño mágico en cono.",
            iconUrl = "$ITEM_IMG/3152.png"
        )
    )
}
