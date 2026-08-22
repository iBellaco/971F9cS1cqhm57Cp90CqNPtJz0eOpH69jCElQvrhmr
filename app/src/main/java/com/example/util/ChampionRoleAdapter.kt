package com.example.util

import com.example.data.WildRiftSpellsAndRunes
import com.example.model.Champion
import com.example.model.DamageType
import com.example.model.LaneRole

data class ChampionRoleProfile(
    val role: LaneRole,
    val winrate: Double,
    val pickRate: Double,
    val banRate: Double,
    val tier: String,
    val coreItems: List<String>,
    val coreItemsIcons: List<String>,
    val situationalItems: List<String>,
    val situationalItemsIcons: List<String>,
    val itemSwaps: List<com.example.model.ItemSwap>,
    val recommendedRunes: String,
    val runeTreeDetails: String,
    val primaryRuneIconUrl: String,
    val recommendedSpells: List<String>,
    val spellsIcons: List<String>,
    val advantageAgainst: List<String>,
    val counteredBy: List<String>,
    val synergies: List<String>,
    val tacticalAdvice: String
)

object ChampionRoleAdapter {

    fun getProfile(champion: Champion, targetRole: LaneRole): ChampionRoleProfile {
        val resolvedSpellsIcons = if (champion.recommendedSpells.isNotEmpty()) {
            champion.recommendedSpells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }
        } else {
            champion.spellsIcons
        }

        val isPrimary = targetRole == champion.primaryRole

        if (isPrimary) {
            return ChampionRoleProfile(
                role = targetRole,
                winrate = champion.winrate,
                pickRate = champion.pickRate,
                banRate = champion.banRate,
                tier = champion.tier,
                coreItems = champion.coreItems,
                coreItemsIcons = champion.coreItemsIcons,
                situationalItems = champion.situationalItems,
                situationalItemsIcons = champion.situationalItemsIcons,
                itemSwaps = champion.itemSwaps,
                recommendedRunes = champion.recommendedRunes,
                runeTreeDetails = champion.runeTreeDetails,
                primaryRuneIconUrl = champion.primaryRuneIconUrl,
                recommendedSpells = champion.recommendedSpells,
                spellsIcons = resolvedSpellsIcons,
                advantageAgainst = champion.advantageAgainst,
                counteredBy = champion.counteredBy,
                synergies = champion.synergies,
                tacticalAdvice = champion.tacticalAdvice
            )
        }

        return getSpecificRoleConfig(champion, targetRole)
    }

    private fun getSpecificRoleConfig(champ: Champion, role: LaneRole): ChampionRoleProfile {
        val isAp = champ.damageType == DamageType.MAGIC
        val isTank = champ.isFrontline || champ.primaryRole == LaneRole.SUPPORT || champ.primaryRole == LaneRole.TOP

        return when (role) {
            LaneRole.JUNGLE -> {
                if (isAp) {
                    ChampionRoleProfile(
                        role = LaneRole.JUNGLE,
                        winrate = adjustRate(champ.winrate, -0.6),
                        pickRate = adjustRate(champ.pickRate * 0.5, 0.6),
                        banRate = champ.banRate,
                        tier = if (champ.tier == "S+") "S" else champ.tier,
                        coreItems = listOf("Eco de Luden", "Orbe del Infinito", "Sombrero Mortal de Rabadon"),
                        coreItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4637.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png"
                        ),
                        situationalItems = listOf("Reloj de Arena de Zhonya", "Báculo del Vacío", "Morellonomicón"),
                        situationalItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"
                        ),
                        itemSwaps = emptyList(),
                        recommendedRunes = "Electrocutar (Impacto Repentino • Cazador Titánico • Capa del Nimbo)",
                        runeTreeDetails = "Limpieza explosiva de campamentos en jungla y daño de emboscada rápido.",
                        primaryRuneIconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png",
                        recommendedSpells = listOf("Castigo", "Destello"),
                        spellsIcons = listOf(
                            WildRiftSpellsAndRunes.SPELL_SMITE,
                            WildRiftSpellsAndRunes.SPELL_FLASH
                        ),
                        advantageAgainst = listOf("Amumu", "Shyvana", "Master Yi", "Evelynn"),
                        counteredBy = listOf("Lee Sin", "Xin Zhao", "Kha'Zix", "Olaf"),
                        synergies = listOf("Yasuo", "Orianna", "Galio", "Malphite"),
                        tacticalAdvice = "Empieza con Castigo en campamentos múltiples (Pájaros/Lobos) y embosca líneas empujadas."
                    )
                } else {
                    ChampionRoleProfile(
                        role = LaneRole.JUNGLE,
                        winrate = adjustRate(champ.winrate, -0.4),
                        pickRate = adjustRate(champ.pickRate * 0.6, 0.8),
                        banRate = champ.banRate,
                        tier = if (champ.tier == "S+") "S" else champ.tier,
                        coreItems = listOf("Fuerza de la Trinidad", "Danza de la Muerte", "Cuchilla Negra"),
                        coreItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png"
                        ),
                        situationalItems = listOf("Coraza del Muerto", "Malla de Espinas", "Ángel Guardián"),
                        situationalItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3742.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"
                        ),
                        itemSwaps = emptyList(),
                        recommendedRunes = "Conquistador (Triunfo • Cazador Titánico • Pionero)",
                        runeTreeDetails = "Optimizado para escaramuzas tempranas por los Cangrejos del Río y control de Dragones.",
                        primaryRuneIconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png",
                        recommendedSpells = listOf("Castigo", "Destello"),
                        spellsIcons = listOf(
                            WildRiftSpellsAndRunes.SPELL_SMITE,
                            WildRiftSpellsAndRunes.SPELL_FLASH
                        ),
                        advantageAgainst = listOf("Master Yi", "Kayn", "Wukong", "Vi"),
                        counteredBy = listOf("Lee Sin", "Olaf", "Warwick", "Xin Zhao"),
                        synergies = listOf("Orianna", "Lulu", "Yasuo", "Ahri"),
                        tacticalAdvice = "Realiza la ruta Rojo -> Pájaros -> Azul y asegura visión profunda en la jungla rival."
                    )
                }
            }
            LaneRole.SUPPORT -> {
                if (isAp) {
                    ChampionRoleProfile(
                        role = LaneRole.SUPPORT,
                        winrate = adjustRate(champ.winrate, -0.8),
                        pickRate = adjustRate(champ.pickRate * 0.4, 0.5),
                        banRate = champ.banRate,
                        tier = "A",
                        coreItems = listOf("Hoz Espectral", "Eco de Luden", "Morellonomicón"),
                        coreItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3860.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"
                        ),
                        situationalItems = listOf("Reloj de Arena de Zhonya", "Velo de la Banshee", "Báculo del Vacío"),
                        situationalItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3102.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"
                        ),
                        itemSwaps = emptyList(),
                        recommendedRunes = "Primer Golpe (Quemadura • Trascendencia • Banda de Flujo de Maná)",
                        runeTreeDetails = "Hostigamiento a distancia y generación de oro acelerada para la botlane.",
                        primaryRuneIconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3860.png",
                        recommendedSpells = listOf("Extenuación", "Destello"),
                        spellsIcons = listOf(
                            WildRiftSpellsAndRunes.SPELL_EXHAUST,
                            WildRiftSpellsAndRunes.SPELL_FLASH
                        ),
                        advantageAgainst = listOf("Braum", "Alistar", "Sona", "Yuumi"),
                        counteredBy = listOf("Blitzcrank", "Nautilus", "Pyke", "Leona"),
                        synergies = listOf("Jhin", "Caitlyn", "Varus", "Ezreal"),
                        tacticalAdvice = "Aprovecha el rango para pokear a los rivales desde los arbustos y guarda Extenuación para el all-in."
                    )
                } else {
                    ChampionRoleProfile(
                        role = LaneRole.SUPPORT,
                        winrate = adjustRate(champ.winrate, -1.0),
                        pickRate = adjustRate(champ.pickRate * 0.35, 0.4),
                        banRate = champ.banRate,
                        tier = "A",
                        coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Protector Pétreo"),
                        coreItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3858.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3742.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3193.png"
                        ),
                        situationalItems = listOf("Malla de Espinas", "Presagio de Randuin", "Fuerza de la Naturaleza"),
                        situationalItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4401.png"
                        ),
                        itemSwaps = emptyList(),
                        recommendedRunes = "Réplica (Fuente de Vida • Condicionamiento • Demolición)",
                        runeTreeDetails = "Iniciación resistente y protección directa para el tirador aliado.",
                        primaryRuneIconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3858.png",
                        recommendedSpells = listOf("Ignición", "Destello"),
                        spellsIcons = listOf(
                            WildRiftSpellsAndRunes.SPELL_IGNITE,
                            WildRiftSpellsAndRunes.SPELL_FLASH
                        ),
                        advantageAgainst = listOf("Pyke", "Rakan", "Yuumi", "Sona"),
                        counteredBy = listOf("Morgana", "Janna", "Lulu", "Thresh"),
                        synergies = listOf("Samira", "Kai'Sa", "Draven", "Tristana"),
                        tacticalAdvice = "Busca el engage a nivel 2 o 3 con Ignición para forzar los hechizos del tirador rival."
                    )
                }
            }
            LaneRole.MID -> {
                if (isAp) {
                    ChampionRoleProfile(
                        role = LaneRole.MID,
                        winrate = adjustRate(champ.winrate, +0.3),
                        pickRate = adjustRate(champ.pickRate * 0.75, 1.2),
                        banRate = champ.banRate,
                        tier = champ.tier,
                        coreItems = listOf("Eco de Luden", "Orbe del Infinito", "Sombrero Mortal de Rabadon"),
                        coreItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4637.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png"
                        ),
                        situationalItems = listOf("Reloj de Arena de Zhonya", "Báculo del Vacío", "Morellonomicón"),
                        situationalItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"
                        ),
                        itemSwaps = emptyList(),
                        recommendedRunes = "Electrocutar (Impacto Repentino • Golpe de Gracia • Trascendencia)",
                        runeTreeDetails = "Maximiza el daño de ráfaga y rotaciones veloces hacia las líneas laterales.",
                        primaryRuneIconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png",
                        recommendedSpells = listOf("Ignición", "Destello"),
                        spellsIcons = listOf(
                            WildRiftSpellsAndRunes.SPELL_IGNITE,
                            WildRiftSpellsAndRunes.SPELL_FLASH
                        ),
                        advantageAgainst = listOf("Katarina", "Akali", "Yasuo", "Zed"),
                        counteredBy = listOf("Orianna", "Syndra", "Vex", "Galio"),
                        synergies = listOf("Jarvan IV", "Vi", "Wukong", "Malphite"),
                        tacticalAdvice = "Limpia la oleada rápido con tus habilidades principales y rota al Dragón o botlane para ganks."
                    )
                } else {
                    ChampionRoleProfile(
                        role = LaneRole.MID,
                        winrate = adjustRate(champ.winrate, +0.2),
                        pickRate = adjustRate(champ.pickRate * 0.7, 1.0),
                        banRate = champ.banRate,
                        tier = champ.tier,
                        coreItems = listOf("Espada Fantasma de Youmuu", "El Recolector", "Filo del Infinito"),
                        coreItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6676.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png"
                        ),
                        situationalItems = listOf("Colmillo de Serpiente", "Danza de la Muerte", "Filo de la Noche"),
                        situationalItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6695.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"
                        ),
                        itemSwaps = emptyList(),
                        recommendedRunes = "Electrocutar (Impacto Repentino • Verdugo de Gigantes • Capa del Nimbo)",
                        runeTreeDetails = "Eliminación letal de campeones débiles en 1 segundo mediante combos sorpresa.",
                        primaryRuneIconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png",
                        recommendedSpells = listOf("Ignición", "Destello"),
                        spellsIcons = listOf(
                            WildRiftSpellsAndRunes.SPELL_IGNITE,
                            WildRiftSpellsAndRunes.SPELL_FLASH
                        ),
                        advantageAgainst = listOf("Veigar", "Lux", "Ziggs", "Aurelion Sol"),
                        counteredBy = listOf("Pantheon", "Malphite", "Vex", "Lissandra"),
                        synergies = listOf("Diana", "Lee Sin", "Nautilus", "Amumu"),
                        tacticalAdvice = "Aprovecha la ventaja de movilidad para castigar al jungla rival en su propio territorio."
                    )
                }
            }
            LaneRole.TOP -> {
                ChampionRoleProfile(
                    role = LaneRole.TOP,
                    winrate = adjustRate(champ.winrate, +0.1),
                    pickRate = adjustRate(champ.pickRate * 0.8, 1.2),
                    banRate = champ.banRate,
                    tier = champ.tier,
                    coreItems = if (isAp) {
                        listOf("Creador de Grietas", "Diente de Nashor", "Sombrero Mortal de Rabadon")
                    } else {
                        listOf("Cuchilla Negra", "Corazón de Acero", "Hidra Titánica")
                    },
                    coreItemsIcons = if (isAp) {
                        listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3115.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png"
                        )
                    } else {
                        listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3084.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3748.png"
                        )
                    },
                    situationalItems = listOf("Malla de Espinas", "Fuerza de la Naturaleza", "Presagio de Randuin"),
                    situationalItemsIcons = listOf(
                        "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png",
                        "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4401.png",
                        "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"
                    ),
                        itemSwaps = emptyList(),
                    recommendedRunes = "Agarre del Perpetuo (Revestimiento de Huesos • Sobrecrecimiento • Demolición)",
                    runeTreeDetails = "Resistencia superior en el 1v1 aislado, demolición de torretas y escalado de vida máxima.",
                    primaryRuneIconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png",
                    recommendedSpells = listOf("Ignición", "Destello"),
                    spellsIcons = listOf(
                        WildRiftSpellsAndRunes.SPELL_IGNITE,
                        WildRiftSpellsAndRunes.SPELL_FLASH
                    ),
                    advantageAgainst = listOf("Jax", "Irelia", "Tryndamere", "Riven"),
                    counteredBy = listOf("Fiora", "Gwen", "Darius", "Mordekaiser"),
                    synergies = listOf("Orianna", "Lulu", "Seraphine", "Yuumi"),
                    tacticalAdvice = "Gestiona la congelación de oleada frente a tu torre. Rota al Heraldo de la Grieta al minuto 5:00."
                )
            }
            LaneRole.ADC -> {
                if (isAp) {
                    ChampionRoleProfile(
                        role = LaneRole.ADC,
                        winrate = adjustRate(champ.winrate, -0.3),
                        pickRate = adjustRate(champ.pickRate * 0.45, 0.7),
                        banRate = champ.banRate,
                        tier = "A",
                        coreItems = listOf("Tormento de Liandry", "Eco de Luden", "Sombrero Mortal de Rabadon"),
                        coreItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3151.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png"
                        ),
                        situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón", "Báculo del Vacío"),
                        situationalItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"
                        ),
                        itemSwaps = emptyList(),
                        recommendedRunes = "Primer Golpe (Trascendencia • Quemadura • Banda de Flujo de Maná)",
                        runeTreeDetails = "Daño mágico masivo continuo y poke opresivo en la línea de Dragón.",
                        primaryRuneIconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3151.png",
                        recommendedSpells = listOf("Barrera", "Destello"),
                        spellsIcons = listOf(
                            WildRiftSpellsAndRunes.SPELL_BARRIER,
                            WildRiftSpellsAndRunes.SPELL_FLASH
                        ),
                        advantageAgainst = listOf("Jinx", "Vayne", "Kai'Sa", "Ashe"),
                        counteredBy = listOf("Draven", "Tristana", "Samira", "Lucian"),
                        synergies = listOf("Nautilus", "Leona", "Pyke", "Thresh"),
                        tacticalAdvice = "Empuja oleadas y hostiga bajo torre obligando al tirador rival a perder súbditos."
                    )
                } else {
                    ChampionRoleProfile(
                        role = LaneRole.ADC,
                        winrate = adjustRate(champ.winrate, -0.4),
                        pickRate = adjustRate(champ.pickRate * 0.6, 0.8),
                        banRate = champ.banRate,
                        tier = "A",
                        coreItems = listOf("Filo del Infinito", "Bailarín Espectral", "Arcoescudo Inmortal"),
                        coreItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3046.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6673.png"
                        ),
                        situationalItems = listOf("Recordatorio Mortal", "Ángel Guardián", "Rencor de Serylda"),
                        situationalItemsIcons = listOf(
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png",
                            "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png"
                        ),
                        itemSwaps = emptyList(),
                        recommendedRunes = "Cadencia Letal (Triunfo • Golpe de Gracia • Dulces Frutos)",
                        runeTreeDetails = "Potencia el rango efectivo de autoataques y el daño por segundo continuo en peleas.",
                        primaryRuneIconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png",
                        recommendedSpells = listOf("Barrera", "Destello"),
                        spellsIcons = listOf(
                            WildRiftSpellsAndRunes.SPELL_BARRIER,
                            WildRiftSpellsAndRunes.SPELL_FLASH
                        ),
                        advantageAgainst = listOf("Jinx", "Ashe", "Varus", "Miss Fortune"),
                        counteredBy = listOf("Draven", "Caitlyn", "Samira", "Tristana"),
                        synergies = listOf("Lulu", "Thresh", "Nami", "Braum"),
                        tacticalAdvice = "Mantén siempre el espaciado y kitea a los enemigos manteniéndote detrás de la primera línea de tanques."
                    )
                }
            }
        }
    }

    private fun adjustRate(base: Double, delta: Double): Double {
        val result = (base + delta)
        return Math.round(result * 10.0) / 10.0
    }
}
