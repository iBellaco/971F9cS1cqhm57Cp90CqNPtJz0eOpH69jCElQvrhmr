package com.example.data

import com.example.model.RuneItem
import com.example.model.SummonerSpellItem

object WildRiftSpellsAndRunes {
    private const val CDN_VERSION = "16.16.1"
    private const val SPELL_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/spell"
    private const val CDRAGON_PERKS = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles"

    val summonerSpells: List<SummonerSpellItem> = listOf(
        SummonerSpellItem(
            id = "flash",
            name = "Destello (Flash)",
            cooldown = "150s",
            iconUrl = "$SPELL_IMG/SummonerFlash.png",
            description = "Teletransporta a tu campeón una corta distancia hacia la ubicación objetivo. El hechizo universal imprescindible en Wild Rift."
        ),
        SummonerSpellItem(
            id = "ignite",
            name = "Prender (Ignite)",
            cooldown = "100s",
            iconUrl = "$SPELL_IMG/SummonerDot.png",
            description = "Prende fuego a un campeón enemigo infligiendo daño verdadero durante 5s y aplicando Heridas Graves (60%) que reducen drásticamente las curaciones."
        ),
        SummonerSpellItem(
            id = "smite",
            name = "Castigo (Smite)",
            cooldown = "45s",
            iconUrl = "$SPELL_IMG/SummonerSmite.png",
            description = "Inflige daño verdadero masivo a monstruos de la jungla y súbditos. Evoluciona a Castigo Desafiante o Helador tras 4 campamentos grandes."
        ),
        SummonerSpellItem(
            id = "barrier",
            name = "Barrera (Barrier)",
            cooldown = "110s",
            iconUrl = "$SPELL_IMG/SummonerBarrier.png",
            description = "Otorga un escudo temporal de 115-465 de absorción de daño durante 2 segundos. Ideal para tiradores y magos contra ráfagas."
        ),
        SummonerSpellItem(
            id = "exhaust",
            name = "Extenuación (Exhaust)",
            cooldown = "105s",
            iconUrl = "$SPELL_IMG/SummonerExhaust.png",
            description = "Ralentiza a un campeón enemigo un 60% y reduce su daño infligido un 40% durante 2.5s. Esencial contra asesinos e hipercarries."
        ),
        SummonerSpellItem(
            id = "ghost",
            name = "Fantasma (Ghost)",
            cooldown = "90s",
            iconUrl = "$SPELL_IMG/SummonerHaste.png",
            description = "Otorga una ráfaga de velocidad de movimiento masiva (hasta +45%) e ignorar colisiones durante 6s. Cada baja reinicia la duración."
        ),
        SummonerSpellItem(
            id = "heal",
            name = "Curar (Heal)",
            cooldown = "120s",
            iconUrl = "$SPELL_IMG/SummonerHeal.png",
            description = "Restaura vida a tu campeón y al aliado más cercano con menor salud, otorgando +30% de velocidad de movimiento durante 1s."
        ),
        SummonerSpellItem(
            id = "clarity",
            name = "Claridad (Clarity - Modos/ARAM)",
            cooldown = "90s",
            iconUrl = "$SPELL_IMG/SummonerMana.png",
            description = "Restaura el 50% del maná máximo a tu campeón y el 25% del maná a todos los aliados cercanos en el área."
        ),
        SummonerSpellItem(
            id = "mark_dash",
            name = "Marca / Lanzamiento (Snowball - ARAM)",
            cooldown = "80s",
            iconUrl = "$SPELL_IMG/SummonerSnowball.png",
            description = "Lanza una bola de nieve en línea recta; si impacta a un enemigo, permite reactivarlo para deslizarse instantáneamente hacia él."
        )
    )

    val runes: List<RuneItem> = listOf(
        // RUNAS CLAVE (KEYSTONES)
        RuneItem(
            id = "conqueror",
            name = "Conquistador (Conqueror)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/precision/conqueror/conqueror.png",
            description = "Acumula Fuerza Adaptable al impactar ataques y habilidades a campeones (hasta 6 cargas). Al máximo otorga omnivampirismo y daño extra."
        ),
        RuneItem(
            id = "kraken_slayer",
            name = "Matakrakens (Kraken Slayer)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/precision/presstheattack/presstheattack.png",
            description = "Cada 3 ataques básicos consecutivos inflige daño verdadero adicional creciente a campeones enemigos."
        ),
        RuneItem(
            id = "lethal_tempo",
            name = "Cadencia Letal (Lethal Tempo)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/precision/lethaltempo/lethaltempotemp.png",
            description = "Atacar a un campeón enemigo otorga velocidad de ataque acumulable hasta 6 veces. A cargas máximas aumenta el rango de ataque básico."
        ),
        RuneItem(
            id = "electrocute",
            name = "Electrocutar (Electrocute)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/domination/electrocute/electrocute.png",
            description = "Golpear a un campeón con 3 ataques o habilidades individuales en un lapso de 3s desata un rayo con daño adaptativo explosivo."
        ),
        RuneItem(
            id = "first_strike",
            name = "Primer Golpe (First Strike)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/inspiration/firststrike/firststrike.png",
            description = "Iniciar combate antes de recibir daño otorga 9% de daño verdadero adicional durante 3s y genera oro equivalente al daño infligido."
        ),
        RuneItem(
            id = "phase_rush",
            name = "Irrupción de Fase (Phase Rush)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/sorcery/phaserush/phaserush.png",
            description = "Golpear a un campeón con 3 ataques o habilidades separadas otorga hasta 60% de velocidad de movimiento y 75% de resistencia a ralentizaciones."
        ),
        RuneItem(
            id = "grasp_undying",
            name = "Agarre del Perpetuo (Grasp)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/resolve/graspoftheundying/graspoftheundying.png",
            description = "Cada 4s en combate, tu siguiente ataque básico inflige daño mágico adicional basado en tu vida máxima, te cura y aumenta tu vida permanentemente."
        ),
        RuneItem(
            id = "aftershock",
            name = "Réplica (Aftershock)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/resolve/veteranaftershock/veteranaftershock.png",
            description = "Inmovilizar a un campeón enemigo otorga +35 armadura y resistencia mágica durante 2.5s, detonando luego una explosión mágica en área."
        ),
        RuneItem(
            id = "aery",
            name = "Invocar a Aery (Aery)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/sorcery/summonaery/summonaery.png",
            description = "Tus ataques y habilidades envían a Aery a dañar al enemigo o a otorgar un escudo protector al aliado seleccionado."
        ),
        RuneItem(
            id = "glacial_augment",
            name = "Aumento Glacial (Glacial Augment)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/inspiration/glacialaugment/glacialaugment.png",
            description = "Inmovilizar a un campeón enemigo crea 3 líneas de escarcha que ralentizan a los enemigos y reducen su daño infligido un 15%."
        ),
        RuneItem(
            id = "arcane_comet",
            name = "Cometa Arcano (Arcane Comet)",
            category = "Runa Clave",
            iconUrl = "$CDRAGON_PERKS/sorcery/arcanecomet/arcanecomet.png",
            description = "Dañar a un campeón con una habilidad dispara un cometa hacia su posición que inflige daño mágico adaptativo en área."
        ),

        // DOMINACIÓN (DOMINATION)
        RuneItem(
            id = "sudden_impact",
            name = "Impacto Repentino (Sudden Impact)",
            category = "Dominación",
            iconUrl = "$CDRAGON_PERKS/domination/suddenimpact/suddenimpact.png",
            description = "Usar un deslizamiento, salto, teletransporte o salir de sigilo otorga penetración de armadura y penetración mágica adicionales durante 4s."
        ),
        RuneItem(
            id = "scorch",
            name = "Quemadura (Scorch)",
            category = "Dominación",
            iconUrl = "$CDRAGON_PERKS/sorcery/scorch/scorch.png",
            description = "Tu siguiente impacto con habilidad quema a los campeones enemigos infligiendo daño mágico adaptativo adicional tras 1s."
        ),
        RuneItem(
            id = "eyeball_collection",
            name = "Colección de Ojos (Eyeball Collection)",
            category = "Dominación",
            iconUrl = "$CDRAGON_PERKS/domination/eyeballcollection/eyeballcollection.png",
            description = "Consigue ojos por cada derribo de campeón enemigo, acumulando hasta +30 de daño de ataque o +60 de poder de habilidad permanentemente."
        ),
        RuneItem(
            id = "zombie_ward",
            name = "Centinela Zombi (Zombie Ward)",
            category = "Dominación",
            iconUrl = "$CDRAGON_PERKS/domination/zombieward/zombieward.png",
            description = "Destruir centinelas enemigos engendra un centinela zombi aliado en su lugar que otorga visión de mapa extendida."
        ),

        // PRECISIÓN (PRECISION)
        RuneItem(
            id = "triumph",
            name = "Triunfo (Triumph)",
            category = "Precisión",
            iconUrl = "$CDRAGON_PERKS/precision/triumph.png",
            description = "Los derribos de campeones restauran un 10% de la vida faltante e infligen 5% de daño adicional a enemigos con menos del 35% de vida."
        ),
        RuneItem(
            id = "coup_de_grace",
            name = "Golpe de Gracia (Coup de Grace)",
            category = "Precisión",
            iconUrl = "$CDRAGON_PERKS/precision/coupdegrace/coupdegrace.png",
            description = "Inflige un 7% de daño adicional a campeones enemigos que tengan menos del 40% de vida máxima."
        ),
        RuneItem(
            id = "last_stand",
            name = "Último Esfuerzo (Last Stand)",
            category = "Precisión",
            iconUrl = "$CDRAGON_PERKS/precision/laststand/laststand.png",
            description = "Inflige entre 5% y 11% de daño adicional a campeones mientras estés por debajo del 60% de tu propia vida."
        ),
        RuneItem(
            id = "legend_alacrity",
            name = "Leyenda: Celeridad (Alacrity)",
            category = "Precisión",
            iconUrl = "$CDRAGON_PERKS/precision/legendalacrity/legendalacrity.png",
            description = "Gana velocidad de ataque permanente al derribar súbditos, monstruos y campeones enemigos."
        ),

        // VALOR (RESOLVE)
        RuneItem(
            id = "bone_plating",
            name = "Revestimiento de Huesos (Bone Plating)",
            category = "Valor",
            iconUrl = "$CDRAGON_PERKS/resolve/boneplating/boneplating.png",
            description = "Tras recibir daño de un campeón enemigo, los siguientes 3 ataques o habilidades enemigas infligen entre 35-65 menos de daño."
        ),
        RuneItem(
            id = "second_wind",
            name = "Segundo Aire (Second Wind)",
            category = "Valor",
            iconUrl = "$CDRAGON_PERKS/resolve/secondwind/secondwind.png",
            description = "Tras recibir daño de un campeón, regenera 6 (+2% de tu vida faltante) a lo largo de 5 segundos."
        ),
        RuneItem(
            id = "conditioning",
            name = "Condicionamiento (Conditioning)",
            category = "Valor",
            iconUrl = "$CDRAGON_PERKS/resolve/conditioning/conditioning.png",
            description = "A partir del minuto 3 de la partida, otorga +8 de armadura y resistencia mágica adicionales y aumenta tus resistencias un 5%."
        ),
        RuneItem(
            id = "overgrowth",
            name = "Sobrecrecimiento (Overgrowth)",
            category = "Valor",
            iconUrl = "$CDRAGON_PERKS/resolve/overgrowth/overgrowth.png",
            description = "Absorbe la esencia de súbditos o monstruos que mueran cerca de ti para aumentar permanentemente tu vida máxima."
        ),
        RuneItem(
            id = "font_of_life",
            name = "Fuente de Vida (Font of Life)",
            category = "Valor",
            iconUrl = "$CDRAGON_PERKS/resolve/fontoflife/fontoflife.png",
            description = "Ralentizar o inmovilizar a un campeón enemigo lo marca. Los aliados que ataquen al enemigo marcado se curan con el tiempo."
        ),

        // INSPIRACIÓN & BRUJERÍA (INSPIRATION & SORCERY)
        RuneItem(
            id = "sweet_tooth",
            name = "Dulces Frutos (Sweet Tooth)",
            category = "Inspiración",
            iconUrl = "$CDRAGON_PERKS/inspiration/sweettooth/sweettooth.png",
            description = "Aumenta la curación de los Frutos de Miel en un 25% y otorga 20 de oro extra por cada fruto consumido."
        ),
        RuneItem(
            id = "demolish",
            name = "Demolición (Demolish)",
            category = "Inspiración",
            iconUrl = "$CDRAGON_PERKS/resolve/demolish/demolish.png",
            description = "Carga un ataque demoledor contra estructuras enemigas cuando estás a 550 de distancia durante 3s, infligiendo daño masivo a torretas."
        ),
        RuneItem(
            id = "manaflow_band",
            name = "Banda de Flujo de Maná (Manaflow Band)",
            category = "Brujería",
            iconUrl = "$CDRAGON_PERKS/sorcery/manaflowband/manaflowband.png",
            description = "Golpear a un campeón con una habilidad aumenta permanentemente tu maná máximo en 30 (hasta 300) y restaura maná faltante."
        ),
        RuneItem(
            id = "transcendence",
            name = "Trascendencia (Transcendence)",
            category = "Brujería",
            iconUrl = "$CDRAGON_PERKS/sorcery/transcendence/transcendence.png",
            description = "Otorga +6 de aceleración de habilidad al nivel 1, +6 adicional al nivel 6, y derribar campeones reduce un 15% los enfriamientos activos."
        ),
        RuneItem(
            id = "nimbus_cloak",
            name = "Capa del Nimbo (Nimbus Cloak)",
            category = "Brujería",
            iconUrl = "$CDRAGON_PERKS/sorcery/nimbuscloak/nimbuscloak.png",
            description = "Lanzar un Hechizo de Invocador otorga una ráfaga de hasta 25% de velocidad de movimiento e ignorar colisión de unidades durante 2.5s."
        )
    )
}
