package com.example.data

import com.example.model.RuneItem
import com.example.model.SummonerSpellItem

object WildRiftSpellsAndRunes {
    const val SPELL_FLASH = "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"
    const val SPELL_IGNITE = "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"
    const val SPELL_SMITE = "https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641"
    const val SPELL_BARRIER = "https://static.wikia.nocookie.net/leagueoflegends/images/c/cc/Barrier.png/revision/latest?cb=20180514002510"
    const val SPELL_EXHAUST = "https://static.wikia.nocookie.net/leagueoflegends/images/4/4a/Exhaust.png/revision/latest?cb=20180514003128"
    const val SPELL_GHOST = "https://static.wikia.nocookie.net/leagueoflegends/images/a/ab/Ghost.png/revision/latest?cb=20180514003209"
    const val SPELL_HEAL = "https://static.wikia.nocookie.net/leagueoflegends/images/6/6e/Heal.png/revision/latest?cb=20180514003319"
    const val SPELL_CLARITY = "https://static.wikia.nocookie.net/leagueoflegends/images/7/71/Claridad.png/revision/latest?cb=20141013024826&path-prefix=es"
    const val SPELL_MARK = "https://static.wikia.nocookie.net/leagueoflegends/images/5/55/Marca.png/revision/latest?cb=20150802150053&path-prefix=es"
    const val SPELL_TELEPORT = "https://wr-meta.com/uploads/posts/2025-07/1753389748_teleport-enchant.webp"

    fun getSpellIconByName(name: String): String {
        return when (name.trim().lowercase()) {
            "destello", "flash" -> SPELL_FLASH
            "prender", "ignición", "ignite", "ignicion", "incendiar" -> SPELL_IGNITE
            "castigo", "smite" -> SPELL_SMITE
            "barrera", "barrier" -> SPELL_BARRIER
            "extenuación", "extenuacion", "exhaust" -> SPELL_EXHAUST
            "fantasma", "ghost" -> SPELL_GHOST
            "curar", "curación", "curacion", "heal" -> SPELL_HEAL
            "claridad", "clarity" -> SPELL_CLARITY
            "marca", "marca / lanzamiento", "mark", "snowball" -> SPELL_MARK
            "teleportación", "teletransporte", "teleport" -> SPELL_TELEPORT
            else -> SPELL_FLASH
        }
    }

    fun getRuneIconByName(name: String): String {
        val clean = name.trim()
        if (clean.isEmpty()) return "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png"
        val exact = runes.find { it.name.equals(clean, ignoreCase = true) }
        if (exact != null) return exact.iconUrl
        val partial = runes.find { clean.contains(it.name, ignoreCase = true) || it.name.contains(clean, ignoreCase = true) }
        return partial?.iconUrl ?: "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png"
    }

    fun getRuneByName(name: String): RuneItem? {
        val clean = name.trim()
        if (clean.isEmpty()) return null
        return runes.find { it.name.equals(clean, ignoreCase = true) || clean.contains(it.name, ignoreCase = true) || it.name.contains(clean, ignoreCase = true) }
    }

    val summonerSpells: List<SummonerSpellItem> = listOf(
        SummonerSpellItem(
            id = "flash",
            name = "Destello",
            cooldown = "150s",
            iconUrl = SPELL_FLASH,
            description = "Teletransporta a tu campeón una corta distancia hacia la ubicación objetivo. El hechizo universal imprescindible en Wild Rift para esquivar habilidades, reposicionarse o realizar jugadas ofensivas sorpresa."
        ),
        SummonerSpellItem(
            id = "ignite",
            name = "Prender",
            cooldown = "100s",
            iconUrl = SPELL_IGNITE,
            description = "Prende fuego a un campeón enemigo infligiendo 72-420 de daño verdadero durante 5s y aplicando Heridas Graves (60%) que reducen drásticamente todas las curaciones y regeneraciones."
        ),
        SummonerSpellItem(
            id = "smite",
            name = "Castigo",
            cooldown = "45s",
            iconUrl = SPELL_SMITE,
            description = "Inflige daño verdadero masivo a monstruos de la jungla y súbditos. Evoluciona a Castigo Desafiante o Helador tras asegurar 4 campamentos grandes, ralentizando o reduciendo el daño del campeón rival."
        ),
        SummonerSpellItem(
            id = "barrier",
            name = "Barrera",
            cooldown = "110s",
            iconUrl = SPELL_BARRIER,
            description = "Otorga un escudo temporal de 115-465 de absorción de daño durante 2 segundos. Ideal para tiradores (ADC) y magos de carril central para sobrevivir a ráfagas de daño de asesinos."
        ),
        SummonerSpellItem(
            id = "exhaust",
            name = "Extenuación",
            cooldown = "105s",
            iconUrl = SPELL_EXHAUST,
            description = "Ralentiza a un campeón enemigo un 60% y reduce su daño infligido un 40% durante 2.5s. Esencial para soportes y carrileros contra asesinos, duelistas e hipercarries enemigos."
        ),
        SummonerSpellItem(
            id = "ghost",
            name = "Fantasma",
            cooldown = "90s",
            iconUrl = SPELL_GHOST,
            description = "Otorga una aceleración masiva de velocidad de movimiento (hasta +45%) e inmunidad a colisiones de unidades durante 6s. Cada derribo de campeón reinicia su duración."
        ),
        SummonerSpellItem(
            id = "heal",
            name = "Curar",
            cooldown = "120s",
            iconUrl = SPELL_HEAL,
            description = "Restaura vida inmediatamente a tu campeón y al aliado más cercano con menor salud, otorgando +30% de velocidad de movimiento durante 1s a ambos."
        ),
        SummonerSpellItem(
            id = "teleport",
            name = "Teleportación",
            cooldown = "180s",
            iconUrl = SPELL_TELEPORT,
            description = "Tras canalizar durante 4s, teletransporta a tu campeón hacia una torreta, súbdito o centinela aliado. Permite split-pushing global y presencia instantánea en objetivos neutrales."
        ),
        SummonerSpellItem(
            id = "clarity",
            name = "Claridad",
            cooldown = "90s",
            iconUrl = SPELL_CLARITY,
            description = "Restaura el 50% del maná máximo a tu campeón y el 25% del maná a todos los aliados cercanos en el área de efecto (disponible en modos especiales y ARAM)."
        ),
        SummonerSpellItem(
            id = "mark_dash",
            name = "Marca / Lanzamiento",
            cooldown = "80s",
            iconUrl = SPELL_MARK,
            description = "Lanza una bola de nieve en línea recta; si impacta a un enemigo, inflige daño verdadero y permite reactivar el hechizo para deslizarse instantáneamente hacia él."
        )
    )

    val runes: List<RuneItem> = listOf(
        // =========================================================================
        // 1. RUNAS CLAVE (KEYSTONES)
        // =========================================================================
        RuneItem(
            id = "conqueror",
            name = "Conquistador",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            description = "Acumula Fuerza Adaptable al golpear con ataques y habilidades a campeones (hasta 6 cargas). Al máximo de cargas, otorga un bonus sustancial de daño adaptativo y omnivampirismo adicional en peleas prolongadas."
        ),
        RuneItem(
            id = "kraken_slayer",
            name = "Matakrakens",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presstheattack/presstheattack.png",
            description = "Cada 3 ataques básicos consecutivos inflige daño verdadero adicional creciente a campeones enemigos. Ideal para tiradores y duelistas de alta velocidad de ataque."
        ),
        RuneItem(
            id = "lethal_tempo",
            name = "Cadencia Letal",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/lethaltempo/lethaltempotemp.png",
            description = "Atacar a un campeón enemigo otorga velocidad de ataque acumulable hasta 6 veces. A cargas máximas, rompe el límite de velocidad de ataque y aumenta el rango de alcance básico."
        ),
        RuneItem(
            id = "fleet_footwork",
            name = "Sobre la Marcha",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/fleetfootwork/fleetfootwork.png",
            description = "Moverse y atacar genera cargas de energía. A 100 cargas, tu siguiente ataque restaura vida y otorga una bonificación de +20% de velocidad de movimiento durante 1s."
        ),
        RuneItem(
            id = "press_the_attack",
            name = "Estrategia Ofensiva",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presstheattack/presstheattack.png",
            description = "Golpear a un campeón con 3 ataques consecutivos inflige daño adaptativo adicional y expone al objetivo, haciendo que reciba un 8% de daño adicional de todas las fuentes durante 6s."
        ),
        RuneItem(
            id = "electrocute",
            name = "Electrocutar",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            description = "Golpear a un campeón con 3 ataques o habilidades individuales en un lapso de 3s desata un rayo con daño adaptativo explosivo. La runa por excelencia para asesinos y magos de ráfaga."
        ),
        RuneItem(
            id = "first_strike",
            name = "Primer Golpe",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            description = "Iniciar combate antes de recibir daño otorga 9% de daño verdadero adicional durante 3s y genera oro equivalente al 100% (70% a distancia) del daño adicional infligido."
        ),
        RuneItem(
            id = "phase_rush",
            name = "Irrupción de Fase",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/phaserush/stormraiderssurgeruneicon2.png",
            description = "Golpear a un campeón con 3 ataques o habilidades separadas en 4s otorga hasta 60% de velocidad de movimiento y 75% de resistencia a ralentizaciones durante 3s."
        ),
        RuneItem(
            id = "aery",
            name = "Invocar a Aery",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            description = "Tus ataques y habilidades envían a Aery hacia un objetivo, dañando a enemigos o proporcionando un escudo protector a los aliados seleccionados."
        ),
        RuneItem(
            id = "arcane_comet",
            name = "Cometa Arcano",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            description = "Dañar a un campeón con una habilidad dispara un cometa hacia su posición que inflige daño mágico adaptativo en área. Dañar con habilidades reduce el enfriamiento del cometa."
        ),
        RuneItem(
            id = "grasp_undying",
            name = "Agarre del Perpetuo",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            description = "Cada 4s en combate, tu siguiente ataque básico inflige daño mágico adicional basado en tu vida máxima, te cura un porcentaje de tu salud y aumenta permanentemente tu vida máxima."
        ),
        RuneItem(
            id = "aftershock",
            name = "Réplica",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            description = "Inmovilizar a un campeón enemigo otorga +35 armadura y +35 resistencia mágica durante 2.5s, detonando luego una explosión mágica en área que inflige daño adaptativo."
        ),
        RuneItem(
            id = "glacial_augment",
            name = "Aumento Glacial",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/glacialaugment/glacialaugment.png",
            description = "Inmovilizar a un campeón enemigo crea 3 rayos congelados que ralentizan a los enemigos un 20% (+2% por cada 10 AP/AD) y reducen su daño infligido a tus aliados un 15%."
        ),
        RuneItem(
            id = "dark_harvest",
            name = "Cosecha Oscura",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/darkharvest/darkharvest.png",
            description = "Dañar a un campeón con menos del 50% de vida inflige daño adaptativo y cosecha su alma, aumentando permanentemente el daño de Cosecha Oscura en 5 por cada alma recolectada."
        ),

        // =========================================================================
        // 2. BRUJERÍA (SORCERY) - ÁRBOL COMPLETO DE WR-META
        // =========================================================================
        RuneItem(
            id = "transcendence",
            name = "Trascendencia",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/transcendence/transcendence.png",
            description = "Otorga bonificaciones al alcanzar ciertos niveles: nivel 1 (+6 aceleración de habilidad), nivel 6 (+6 aceleración adicional). Al nivel 11, conseguir derribos reduce un 15% los enfriamientos activos de habilidades básicas."
        ),
        RuneItem(
            id = "manaflow_band",
            name = "Banda de Flujo de Maná",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/manaflowband/manaflowband.png",
            description = "Golpear a un campeón enemigo con una habilidad aumenta permanentemente tu maná máximo en 30 (hasta un límite de 300 de maná extra). Al alcanzar el máximo, regenera un 1% de maná faltante por segundo."
        ),
        RuneItem(
            id = "sweet_tooth",
            name = "Dulces Frutos",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/biscuitdelivery/biscuitdelivery.png",
            description = "Aumenta la curación proporcionada por los Frutos de Miel en un 25% y otorga 20 de oro adicional por cada fruto consumido por ti o por un aliado cercano."
        ),
        RuneItem(
            id = "hextech_flashtraption",
            name = "Acelerador Hextech / Destello Hextech",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/hextechflashtraption/hextechflashtraption.png",
            description = "Cuando Destello está en enfriamiento, se reemplaza por Destello Hextech: tras canalizar durante 1.5s, te teletransportas hacia una nueva ubicación estratégica para emboscadas."
        ),
        RuneItem(
            id = "presence_of_mind",
            name = "Claridad Mental / Mente Presente",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presenceofmind/presenceofmind.png",
            description = "Dañar a un campeón enemigo aumenta tu regeneración de maná o energía durante 4s. Conseguir un derribo restaura inmediatamente el 15% de tu maná o energía máxima."
        ),
        RuneItem(
            id = "waterwalking",
            name = "Caminante del Agua",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/waterwalking/waterwalking.png",
            description = "En el río, obtienes una bonificación de +25 de velocidad de movimiento y ganas hasta +18 de daño de ataque o +36 de poder de habilidad adaptable para pelear objetivos neutrales."
        ),
        RuneItem(
            id = "absolute_focus",
            name = "Concentración Absoluta",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/absolutefocus/absolutefocus.png",
            description = "Mientras te encuentres por encima del 70% de vida, obtienes una bonificación adaptativa de hasta +16 de daño de ataque o +32 de poder de habilidad adicional."
        ),
        RuneItem(
            id = "scorch",
            name = "Quemadura",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/scorch/scorch.png",
            description = "Tu siguiente impacto con habilidad quema a los campeones enemigos, infligiendo entre 28 y 42 de daño mágico adaptativo adicional tras 1 segundo (enfriamiento: 8s)."
        ),
        RuneItem(
            id = "nimbus_cloak",
            name = "Capa del Nimbo",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/nimbuscloak/6361.png",
            description = "Lanzar cualquier Hechizo de Invocador otorga una ráfaga inmediata de hasta +25% de velocidad de movimiento e ignorar colisiones de unidades durante 2.5s."
        ),
        RuneItem(
            id = "gathering_storm",
            name = "Tormenta Creciente",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/gatheringstorm/gatheringstorm.png",
            description = "Cada 3 minutos de partida transcurridos, obtienes daño de ataque o poder de habilidad adaptativo creciente. Conviértete en una amenaza imparable en el juego tardío."
        ),
        RuneItem(
            id = "time_warp_tonic",
            name = "Tónico de Distorsión Temporal",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/timewarptonic/timewarptonic.png",
            description = "Consumir una poción o fruto restaura inmediatamente el 50% de la vida y maná que otorga, y concede un +5% de velocidad de movimiento mientras el efecto esté activo."
        ),

        // =========================================================================
        // 3. DOMINACIÓN (DOMINATION)
        // =========================================================================
        RuneItem(
            id = "sudden_impact",
            name = "Impacto Repentino",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/suddenimpact/suddenimpact.png",
            description = "Usar un deslizamiento, salto, destello, teletransporte o salir de sigilo otorga +10 de penetración de armadura y +10 de penetración mágica adicionales durante 4 segundos."
        ),
        RuneItem(
            id = "cheap_shot",
            name = "Golpe Bajo",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/cheapshot/cheapshot.png",
            description = "Dañar a campeones con movimiento o acciones reducidas (ralentizados, inmovilizados, aturdidos) inflige 10-45 de daño verdadero adicional."
        ),
        RuneItem(
            id = "taste_of_blood",
            name = "Sabor a Sangre",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/tasteofblood/greenterror_tasteofblood.png",
            description = "Cúrate entre 18-35 (+20% AD extra / +10% AP) de vida cuando infliges daño a un campeón enemigo (enfriamiento: 20s)."
        ),
        RuneItem(
            id = "eyeball_collection",
            name = "Colección de Ojos",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/eyeballcollection/eyeballcollection.png",
            description = "Consigue ojos por cada derribo de campeón enemigo (hasta 10 cargas). Otorga +2 AD o +4 AP por carga, y un bono adicional de +10 AD / +20 AP al completar la colección."
        ),
        RuneItem(
            id = "zombie_ward",
            name = "Centinela Zombi",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/zombieward/zombieward.png",
            description = "Destruir centinelas enemigos engendra un centinela zombi aliado visible en su lugar que otorga visión extendida en el mapa y fuerza adaptativa."
        ),
        RuneItem(
            id = "ghost_poro",
            name = "Poro Fantasma",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/ghostporo/ghostporo.png",
            description = "Cuando tus centinelas expiran, dejan atrás un Poro Fantasma que otorga visión del sector hasta que un campeón enemigo lo espante."
        ),
        RuneItem(
            id = "ingenious_hunter",
            name = "Cazador Ingenioso",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/ingenioushunter/ingenioushunter.png",
            description = "Otorga aceleración de objetos activos y pasivas de equipamiento, reduciendo sustancialmente los enfriamientos de Éxtasis, Fajín, Corona y similares."
        ),
        RuneItem(
            id = "ultimate_hunter",
            name = "Cazador Supremo",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/ultimatehunter/ultimatehunter.png",
            description = "Tu habilidad definitiva obtiene +6 de aceleración de habilidad, más +5 de aceleración adicional por cada derribo único de campeón enemigo."
        ),
        RuneItem(
            id = "shield_breaker",
            name = "Rompeescudos",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/relentlesshunter/relentlesshunter.png",
            description = "Inflige un 15% de daño adicional a enemigos que posean escudos activos y destruye rápidamente defensas temporales enemigas."
        ),

        // =========================================================================
        // 4. PRECISIÓN (PRECISION)
        // =========================================================================
        RuneItem(
            id = "triumph",
            name = "Triunfo",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/triumph.png",
            description = "Los derribos de campeones restauran un 10% de la vida faltante e infligen un 5% de daño adicional a campeones enemigos que tengan menos del 35% de vida."
        ),
        RuneItem(
            id = "coup_de_grace",
            name = "Golpe de Gracia",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/coupdegrace/coupdegrace.png",
            description = "Inflige un 7% de daño adicional a campeones enemigos que tengan menos del 40% de vida máxima. La mejor opción para rematar objetivos frágiles."
        ),
        RuneItem(
            id = "last_stand",
            name = "Último Esfuerzo",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/laststand/laststand.png",
            description = "Inflige entre 5% y 11% de daño adicional a campeones enemigos mientras estés por debajo del 60% de tu propia vida (máximo al 30% de salud)."
        ),
        RuneItem(
            id = "giant_slayer",
            name = "Cazagigantes",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/cutdown/cutdown.png",
            description = "Inflige hasta un 14% de daño físico y mágico adicional contra campeones enemigos que tengan mayor vida máxima adicional que tú."
        ),
        RuneItem(
            id = "legend_alacrity",
            name = "Leyenda: Celeridad",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/legendalacrity/legendalacrity.png",
            description = "Otorga un 3% de velocidad de ataque más un 1.5% adicional por cada carga de Leyenda (hasta +18% de velocidad de ataque total)."
        ),
        RuneItem(
            id = "legend_bloodline",
            name = "Leyenda: Linaje",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/legendbloodline/legendbloodline.png",
            description = "Otorga omnivampirismo y robo de vida permanente al acumular derribos de campeones, monstruos grandes y súbditos (hasta +7% de robo de vida)."
        ),
        RuneItem(
            id = "legend_tenacity",
            name = "Leyenda: Tenacidad",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/legendtenacity/legendtenacity.png",
            description = "Otorga un 5% de tenacidad más un 2.5% adicional por cada carga de Leyenda (hasta +20% de tenacidad y resistencia a ralentizaciones)."
        ),
        RuneItem(
            id = "brutal",
            name = "Brutalidad / Brutal",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presstheattack/presstheattack.png",
            description = "Los ataques básicos infligen 12-19 de daño adaptativo adicional al impacto contra campeones enemigos."
        ),

        // =========================================================================
        // 5. VALOR (RESOLVE)
        // =========================================================================
        RuneItem(
            id = "bone_plating",
            name = "Revestimiento de Huesos",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/boneplating/boneplating.png",
            description = "Tras recibir daño de un campeón enemigo, los siguientes 3 ataques o habilidades del enemigo infligen entre 35-65 menos de daño durante 1.5s (enfriamiento: 35s)."
        ),
        RuneItem(
            id = "second_wind",
            name = "Segundo Aire",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/secondwind/secondwind.png",
            description = "Tras recibir daño de un campeón rival, regenera 6 (+2% de tu vida faltante) a lo largo de 5 segundos. Se duplica para campeones cuerpo a cuerpo."
        ),
        RuneItem(
            id = "conditioning",
            name = "Condicionamiento",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/conditioning/conditioning.png",
            description = "A partir del minuto 3:00 de partida, otorga +8 de armadura y +8 de resistencia mágica adicionales y aumenta tus resistencias totales un 5% permanentemente."
        ),
        RuneItem(
            id = "overgrowth",
            name = "Sobrecrecimiento",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/overgrowth/overgrowth.png",
            description = "Absorbe la esencia vital de súbditos o monstruos que mueran cerca de ti para aumentar permanentemente tu vida máxima en 3. Al alcanzar 120 súbditos, ganas +3.5% de vida máxima adicional."
        ),
        RuneItem(
            id = "font_of_life",
            name = "Fuente de Vida",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/fontoflife/fontoflife.png",
            description = "Ralentizar o inmovilizar a un campeón enemigo lo marca durante 3s. Los aliados que ataquen al enemigo marcado se curan un porcentaje de tu vida máxima a lo largo de 2 segundos."
        ),
        RuneItem(
            id = "demolish",
            name = "Demolición",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/demolish/demolish.png",
            description = "Carga un ataque demoledor contra estructuras enemigas cuando estás a menos de 550 de distancia durante 3s, infligiendo 200 (+30% de tu vida máxima) como daño físico demoledor a torretas."
        ),
        RuneItem(
            id = "perseverance",
            name = "Perseverancia",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/unflinching/unflinching.png",
            description = "Otorga +10% de tenacidad pasiva. Aumenta hasta +20% de tenacidad adicional y resistencia a ralentizaciones según la vida faltante de tu campeón."
        ),
        RuneItem(
            id = "revitalize",
            name = "Revitalizar",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/revitalize/revitalize.png",
            description = "Las curaciones y escudos que lanzas o recibes son un 5% más potentes. Aumenta un 10% adicional sobre objetivos con menos del 40% de vida."
        ),
        RuneItem(
            id = "loyalty",
            name = "Lealtad",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/guardian/guardian.png",
            description = "Ganas +2 de armadura y +5 de resistencia mágica. Tu aliado más cercano obtiene +5 de armadura y +2 de resistencia mágica adicionales."
        ),

        // =========================================================================
        // 6. INSPIRACIÓN (INSPIRATION)
        // =========================================================================
        RuneItem(
            id = "pathfinder",
            name = "Pionero / Explorador",
            category = "Inspiración",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/pathfinder.png",
            description = "Ganas un +9% de velocidad de movimiento fuera de combate en el río, la jungla y maleza. Restaura un 1% de tu vida o maná faltante por segundo en estos terrenos."
        ),
        RuneItem(
            id = "cosmic_insight",
            name = "Perspicacia Cósmica",
            category = "Inspiración",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/cosmicinsight/cosmicinsight.png",
            description = "Otorga +18 de aceleración para Hechizos de Invocador y +10 de aceleración de objetos, permitiendo tener Destello, Prender o Castigo listos mucho más rápido."
        ),
        RuneItem(
            id = "future_market",
            name = "Mercado del Futuro",
            category = "Inspiración",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/futuresmarket/futuresmarket.png",
            description = "Puedes endeudarte para comprar objetos en la tienda de la base antes de contar con el oro total requerido (límite de deuda de hasta 250 de oro tras 2 minutos)."
        )
    )
}
