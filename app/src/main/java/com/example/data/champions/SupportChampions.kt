package com.example.data.champions

import com.example.model.Champion
import com.example.model.ChampionSkill
import com.example.model.DamageType
import com.example.model.LaneRole
import com.example.model.ItemSwap

object SupportChampions {
    val list: List<Champion> = listOf(
        Champion(
            id = "alistar",
            name = "Alistar",
            title = "El Minotauro",
            ddragonId = "Alistar",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Alistar.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "C",
            winrate = 51.16,
            pickRate = 3.38,
            banRate = 0.67,
            damageType = DamageType.MAGIC,
            summary = "Alistar, un poderoso guerrero con una reputación temible, busca venganza por la muerte de su clan a manos del imperio noxiano. Aunque fue esclavizado y forzado a vivir como gladiador, fue su voluntad inquebrantable lo que le impidió convertirse en una...",
            advantageAgainst = listOf("Leona", "Nautilus", "Blitzcrank"),
            counteredBy = listOf("Janna", "Lulu", "Morgana"),
            synergies = listOf("Yasuo", "Samira", "Tristana"),
            tacticalAdvice = "Aprovecha el escalado y combos de Alistar en SUPPORT. Coordina el uso de su Voluntad inquebrantable para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Baluarte de la Montaña", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf(
                "https://wr-meta.com/uploads/posts/2025-07/1753389518_bulwark-of-the-mountain.webp",
                "https://wr-meta.com/uploads/posts/2025-07/1753389106_dead-mans-plate.webp",
                "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp",
                "https://wr-meta.com/uploads/posts/2025-07/1753389651_plated-steelcaps.webp",
                "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp",
                "https://wr-meta.com/uploads/posts/2025-07/1753389153_force-of-nature.webp"
            ),
            situationalItems = listOf("Cota de Espinas", "Presagio de Randuin", "Protector Pétreo", "Protección Gemela de Amaranth"),
            situationalItemsIcons = listOf(
                "https://wr-meta.com/uploads/posts/2025-07/1753389035_thornmail.webp",
                "https://wr-meta.com/uploads/posts/2025-07/1753389031_randuins-omen.webp",
                "https://wr-meta.com/uploads/posts/2025-07/1753389735_stoneplate-enchant.webp",
                "https://wr-meta.com/uploads/posts/2025-07/1753389236_amaranths-twinguard.webp"
            ),
            itemSwaps = listOf(
                ItemSwap(
                    coreItem = "Fuerza de la Naturaleza",
                    coreItemIcon = "https://wr-meta.com/uploads/posts/2025-07/1753389153_force-of-nature.webp",
                    altItem = "Cota de Espinas",
                    altItemIcon = "https://wr-meta.com/uploads/posts/2025-07/1753389035_thornmail.webp",
                    reasonTitle = "ANTI-CURACIÓN & ARMADURA",
                    reasonDesc = "Aplica Heridas Graves para mitigar curaciones masivas y devuelve daño mágico a atacantes físicos.",
                    againstWho = "Aatrox, Warwick, Soraka, Yuumi, Samira"
                ),
                ItemSwap(
                    coreItem = "Coraza del Muerto",
                    coreItemIcon = "https://wr-meta.com/uploads/posts/2025-07/1753389106_dead-mans-plate.webp",
                    altItem = "Presagio de Randuin",
                    altItemIcon = "https://wr-meta.com/uploads/posts/2025-07/1753389031_randuins-omen.webp",
                    reasonTitle = "ANTI-CRÍTICO",
                    reasonDesc = "Reduce el impacto de los golpes críticos y ralentiza la velocidad de ataque enemiga.",
                    againstWho = "Yasuo, Yone, Jinx, Tristana, Caitlyn"
                ),
                ItemSwap(
                    coreItem = "Manto del Amanecer",
                    coreItemIcon = "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp",
                    altItem = "Protector Pétreo",
                    altItemIcon = "https://wr-meta.com/uploads/posts/2025-07/1753389735_stoneplate-enchant.webp",
                    reasonTitle = "ESCUDO MASIVO EN TEAMFIGHT",
                    reasonDesc = "Otorga un escudo enorme tras iniciar con combo W+Q para resistir el foco del equipo rival.",
                    againstWho = "Composiciones con alto daño combinado en 5v5"
                )
            ),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Rugido triunfal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Alistar_E.png",
                    description = "Alistar carga su rugido al aturdir o desplazar a campeones enemigos o cuando los enemigos cercanos mueren. Cuando está cargado al máximo, se cura a sí mismo y a los campeones aliados cercanos.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Pulverización",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Pulverize.png",
                    description = "Alistar golpea con fuerza el suelo, lo que inflige daño a los enemigos cercanos y los lanza por los aires.",
                    cooldown = "14/13/12/11/10s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Testarazo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Headbutt.png",
                    description = "Alistar propina un cabezazo al objetivo, dañándolo y haciéndolo retroceder.",
                    cooldown = "14/13/12/11/10s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Pisotear",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AlistarE.png",
                    description = "Alistar pisotea a las unidades enemigas cercanas, ignora la colisión con unidades y obtiene acumulaciones si daña a un campeón enemigo. Con el máximo de acumulaciones, el siguiente ataque básico de Alistar contra un campeón enemigo inflige daño mágico adicional y lo aturde.",
                    cooldown = "12/11.5/11/10.5/10s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Voluntad inquebrantable",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FerociousHowl.png",
                    description = "Alistar profiere un gran rugido con el que elimina todos los efectos de control de adversario que le afectan y reduce el daño físico y mágico recibido mientras dura el efecto.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/alistar",
            wrMetaUrl = "https://wr-meta.com/champion/alistar/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/alistar/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/alistar"
        ),
        Champion(
            id = "bard",
            name = "Bardo",
            title = "El Guardián Errante",
            ddragonId = "Bard",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Bard.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 52.6,
            pickRate = 10.0,
            banRate = 10.5,
            damageType = DamageType.MAGIC,
            summary = "Bardo, un viajero de más allá de las estrellas, es un agente de la serendipia que lucha para mantener un equilibrio en el que la vida pueda soportar la indiferencia del caos. Muchos habitantes de Runaterra cantan canciones que hablan de su naturaleza...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Leona", "Nautilus", "Blitzcrank"),
            synergies = listOf("Caitlyn", "Jhin", "Ezreal"),
            tacticalAdvice = "Aprovecha el escalado y combos de Bardo en SUPPORT. Coordina el uso de su Destino maleable para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Llamada del viajero",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Bard_Passive.png",
                    description = "Meeps: Bardo atrae a unos espíritus menores que aumentan la potencia de sus ataques básicos para infligir daño mágico adicional. Cuando Bardo haya recogido suficientes campanas, sus meeps también infligirán daño en área y ralentizarán a los enemigos golpeados.Campanas: Aparecen campanas antiguas al azar que Bardo puede recoger. Otorgan experiencia, restauran maná y otorgan velocidad de movimiento fuera de combate.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Cadenas cósmicas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BardQ.png",
                    description = "Bardo dispara un proyectil que ralentiza al primer enemigo alcanzado, antes de seguir su trayectoria. A partir de ahí, si golpea un muro, el objetivo inicial queda aturdido. Si golpea a otro enemigo, los dos sufren el efecto.",
                    cooldown = "11/10/9/8/7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Santuario del protector",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BardW.png",
                    description = "Hace aparecer un santuario de vida. El santuario, que tarda unos segundos en cargarse a plena potencia, desaparece después de curar y aumentar la velocidad al primer aliado que lo toca.",
                    cooldown = "0s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Periplo mágico",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BardE.png",
                    description = "Bardo abre un portal en un obstáculo del terreno cercano. Tanto sus aliados como sus enemigos pueden atravesarlo para cruzar al otro lado, pero solo funciona en un sentido.",
                    cooldown = "22/20.5/19/17.5/16s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Destino maleable",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BardR.png",
                    description = "Bardo lanza su energía espiritual a un objetivo, dejando en estasis a todas las unidades y torretas de la zona durante breve tiempo.",
                    cooldown = "110/95/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/bard",
            wrMetaUrl = "https://wr-meta.com/champion/bard/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/bard/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/bard"
        ),
        Champion(
            id = "blitzcrank",
            name = "Blitzcrank",
            title = "El Gran Gólem de Vapor",
            ddragonId = "Blitzcrank",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Blitzcrank.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.JUNGLE, LaneRole.MID),
            tier = "B",
            winrate = 49.89,
            pickRate = 9.87,
            banRate = 4.09,
            damageType = DamageType.MAGIC,
            summary = "Blitzcrank es un autómata enorme, casi indestructible, creado originalmente para el tratamiento de residuos tóxicos. Sin embargo, este propósito original le parecía demasiado restrictivo, así que se automodificó para servir mejor a los débiles del...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Leona", "Alistar", "Braum"),
            synergies = listOf("Jinx", "Draven", "Caitlyn"),
            tacticalAdvice = "Aprovecha el escalado y combos de Blitzcrank en SUPPORT. Coordina el uso de su Campo estático para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf("https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp", "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Barrera de maná",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Blitzcrank_ManaBarrier.png",
                    description = "Cuando le queda poca vida, Blitzcrank obtiene un escudo en función de su maná.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Agarre misil",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RocketGrab.png",
                    description = "Blitzcrank dispara su mano derecha para apresar a un rival que encuentre en su camino, le inflige daño y lo atrae hacia él.",
                    cooldown = "20/19/18/17/16s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Sobrecarga",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Overdrive.png",
                    description = "Blitzcrank se sobrecarga para aumentar drásticamente su velocidad de movimiento y su velocidad de ataque. Se ve ralentizado temporalmente cuando termina el efecto.",
                    cooldown = "15s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Puño de poder",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PowerFist.png",
                    description = "Blitzcrank carga su puño para que su siguiente ataque cause el doble de daño y lance al objetivo por los aires.",
                    cooldown = "9/8/7/6/5s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Campo estático",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/StaticField.png",
                    description = "Los enemigos a los que Blitzcrank ataca quedan marcados y reciben una descarga eléctrica después de 1 s. Además, Blitzcrank puede activar esta habilidad para eliminar los escudos de los enemigos cercanos, infligirles daño y silenciarlos durante un breve periodo.",
                    cooldown = "60/40/20s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/blitzcrank",
            wrMetaUrl = "https://wr-meta.com/champion/blitzcrank/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/blitzcrank/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/blitzcrank"
        ),
        Champion(
            id = "braum",
            name = "Braum",
            title = "El Corazón de Freljord",
            ddragonId = "Braum",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Braum.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "C",
            winrate = 51.72,
            pickRate = 3.67,
            banRate = 1.17,
            damageType = DamageType.MAGIC,
            summary = "Bendecido con bíceps enormes y un corazón aún más grande, Braum es un héroe muy apreciado en Freljord. Todas las tabernas al norte del Fuerte Helado brindan por su fuerza legendaria. Se dice que taló un bosque de robles en una sola noche y convirtió una...",
            advantageAgainst = listOf("Leona", "Nautilus", "Blitzcrank"),
            counteredBy = listOf("Lulu", "Janna", "Nami"),
            synergies = listOf("Lucian", "Ashe", "Ezreal"),
            tacticalAdvice = "Aprovecha el escalado y combos de Braum en SUPPORT. Coordina el uso de su Fisura glacial para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf("https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp", "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Golpes conmocionantes",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Braum_Passive.png",
                    description = "Los ataques básicos de Braum aplican Golpes conmocionantes. Cuando se aplique la primera acumulación, los ataques básicos de los aliados también acumulan Golpes conmocionantes. Al llegar a 4 acumulaciones, el objetivo queda aturdido y recibe daño mágico. Durante los siguientes segundos no puede recibir acumulaciones, pero recibe daño mágico adicional de los ataques de Braum.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Mordisco invernal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BraumQ.png",
                    description = "Braum lanza un chorro de gélido hielo desde el escudo que ralentiza y causa daño mágico.Aplica una acumulación de Golpes conmocionantes.",
                    cooldown = "8/7.5/7/6.5/6s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Detrás de mí",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BraumW.png",
                    description = "Braum salta hacia un campeón o súbdito aliado. Al alcanzarlo, ambos obtienen armadura y resistencia mágica durante unos segundos.",
                    cooldown = "12/11/10/9/8s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Inquebrantable",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BraumE.png",
                    description = "Braum alza su escudo en una dirección durante varios segundos e intercepta todos los proyectiles, que lo golpean y son destruidos. Anula por completo el daño del primero y reduce el de los siguientes que llegan desde la misma dirección.",
                    cooldown = "16/14/12/10/8s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Fisura glacial",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BraumRWrapper.png",
                    description = "Braum golpea el suelo y lanza por los aires a los enemigos cercanos y situados en una línea delante de él. A lo largo de esta línea se abre una fisura que ralentiza a los enemigos.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/braum",
            wrMetaUrl = "https://wr-meta.com/champion/braum/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/braum/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/braum"
        ),
        Champion(
            id = "janna",
            name = "Janna",
            title = "La Furia de la Tormenta",
            ddragonId = "Janna",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Janna.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 47.22,
            pickRate = 1.18,
            banRate = 0.17,
            damageType = DamageType.MAGIC,
            summary = "Janna, armada con el poder de los vendavales de Runaterra, es un misterioso espíritu elemental que aprovecha el viento para proteger a los más desfavorecidos de Zaun. Hay quien cree que surgió de los ruegos de los marineros de Runaterra, que rezaban por...",
            advantageAgainst = listOf("Leona", "Alistar", "Rakan"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Jinx", "Vayne", "Tristana"),
            tacticalAdvice = "Aprovecha el escalado y combos de Janna en SUPPORT. Coordina el uso de su Monzón para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Empuje",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/JannaP.png",
                    description = "Los aliados de Janna obtienen velocidad de movimiento al avanzar hacia ella.Janna inflige parte de la velocidad de movimiento adicional como daño mágico adicional al golpear y con Céfiro.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Temporal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HowlingGale.png",
                    description = "Cambiando puntualmente la presión y la temperatura, Janna logra crear una pequeña tormenta que aumenta de tamaño con el tiempo. Se puede activar de nuevo el hechizo para lanzar la tormenta. Al lanzarla, esta tormenta se desplaza hacia la dirección en que fue arrojada, lo que inflige daño y lanza por los aires a los enemigos que se encuentren en su camino.",
                    cooldown = "14s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Céfiro",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SowTheWind.png",
                    description = "Janna invoca un elemental de aire que aumenta de forma pasiva su velocidad de movimiento y le permite atravesar unidades. También puede activar esta habilidad para infligir daño y reducir la velocidad de movimiento de un enemigo.",
                    cooldown = "8/7.5/7/6.5/6s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Ojo de la tormenta",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EyeOfTheStorm.png",
                    description = "Janna conjura un vendaval defensivo que protege a una torreta o un campeón aliado del daño y aumenta su daño de ataque.",
                    cooldown = "16/15/14/13/12s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Monzón",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ReapTheWhirlwind.png",
                    description = "Janna se envuelve con una tormenta mágica que repele a sus enemigos. Una vez pasada la tormenta, unos vientos reparadores curan a los aliados cercanos mientras la habilidad se encuentra activa.",
                    cooldown = "130/115/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/janna",
            wrMetaUrl = "https://wr-meta.com/champion/janna/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/janna/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/janna"
        ),
        Champion(
            id = "karma",
            name = "Karma",
            title = "La Iluminada",
            ddragonId = "Karma",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Karma.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "C",
            winrate = 51.07,
            pickRate = 2.77,
            banRate = 0.36,
            damageType = DamageType.MAGIC,
            summary = "No hay mortal que ejemplifique las tradiciones espirituales de Jonia mejor que Karma. Es la personificación de un alma antigua reencarnada un sinfín de veces, que acumula en cada vida sucesiva los recuerdos de las vidas pasadas y que ha sido bendecida...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Ezreal", "Caitlyn", "Ashe"),
            tacticalAdvice = "Aprovecha el escalado y combos de Karma en SUPPORT. Coordina el uso de su Mantra para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Fuego reunificador",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Karma_Passive.png",
                    description = "Las habilidades de daño de Karma reducen el enfriamiento de Mantra.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Llama interior",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KarmaQ.png",
                    description = "Karma lanza una bola de energía espiritual que explota e inflige daño al golpear a una unidad enemiga.Bonificación de Mantra: Además de la explosión, Mantra aumenta el poder destructivo de Llama interior, lo que crea una onda abrasiva que inflige daño tras unos instantes.",
                    cooldown = "9/8/7/6/5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Resolución concentrada",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KarmaSpiritBind.png",
                    description = "Karma crea un vínculo entre el objetivo enemigo y ella que inflige daño y lo revela. Si no se rompe el vínculo, el objetivo queda inmovilizado y vuelve a recibir daño.Bonificación de Mantra: Karma fortalece el vínculo, lo que, además de curarla, amplía la duración de la inmovilización.",
                    cooldown = "12s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Inspiración",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KarmaSolKimShield.png",
                    description = "Karma invoca un escudo protector que absorbe el daño recibido y aumenta la velocidad de movimiento del aliado protegido.Bonificación de Mantra: El objetivo irradia energía, lo que refuerza el escudo inicial y aplica Inspiración a los campeones aliados cercanos.",
                    cooldown = "10/9.5/9/8.5/8s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Mantra",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KarmaMantra.png",
                    description = "Karma potencia su siguiente habilidad para conseguir un efecto adicional. Mantra está disponible al nivel 1 y no necesita puntos de habilidad.",
                    cooldown = "40/38/36/34s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/karma",
            wrMetaUrl = "https://wr-meta.com/champion/karma/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/karma/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/karma"
        ),
        Champion(
            id = "leona",
            name = "Leona",
            title = "El Amanecer Radiante",
            ddragonId = "Leona",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Leona.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE),
            tier = "A",
            winrate = 51.13,
            pickRate = 11.53,
            banRate = 5.89,
            damageType = DamageType.MAGIC,
            summary = "Imbuida del fuego del sol, Leona es una guerrera sagrada de los Solari que defiende el Monte Targon con su Hoja del cénit y su Escudo del amanecer. Su piel brilla como las estrellas y sus ojos resplandecen con el poder del aspecto celestial de su...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Alistar", "Braum", "Janna"),
            synergies = listOf("Samira", "Miss Fortune", "Jhin"),
            tacticalAdvice = "Aprovecha el escalado y combos de Leona en SUPPORT. Coordina el uso de su Llamarada solar para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf("https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp", "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Luz solar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/LeonaSunlight.png",
                    description = "Los hechizos de daño aplican al objetivo Luz solar durante 1,5 s. Cuando los campeones aliados infligen daño a esos objetivos, consumen Luz solar para infligir daño mágico adicional.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Escudo del amanecer",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeonaShieldOfDaybreak.png",
                    description = "Leona usa su escudo para realizar su siguiente ataque básico, infligiendo daño mágico adicional y aturdiendo al objetivo.",
                    cooldown = "5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Eclipse",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeonaSolarBarrier.png",
                    description = "Leona alza su escudo, lo que le otorga armadura, resistencia mágica y reducción de daño. Cuando finaliza el efecto por primera vez, si hay enemigos cerca, les inflige daño mágico y prolonga la duración del efecto.",
                    cooldown = "14/13/12/11/10s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Hoja del cénit",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeonaZenithBlade.png",
                    description = "Leona proyecta una imagen solar de su espada, infligiendo daño mágico a todos los enemigos en línea recta. Cuando la imagen desaparece, el último campeón enemigo alcanzado por ella se quedará inmovilizado brevemente, y Leona irá rápidamente hacia él.",
                    cooldown = "12/10.5/9/7.5/6s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Llamarada solar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeonaSolarFlare.png",
                    description = "Leona invoca un rayo de energía solar que inflige daño a los enemigos en un área. Los enemigos en el centro del área se ven aturdidos, mientras que los situados en la franja exterior se ven ralentizados.",
                    cooldown = "90/75/60s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/leona",
            wrMetaUrl = "https://wr-meta.com/champion/leona/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/leona/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/leona"
        ),
        Champion(
            id = "lulu",
            name = "Lulu",
            title = "El Hada Hechicera",
            ddragonId = "Lulu",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Lulu.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 48.94,
            pickRate = 6.06,
            banRate = 2.27,
            damageType = DamageType.MAGIC,
            summary = "Lulu, la yordle maga, es conocida por invocar ilusiones oníricas y criaturas imaginarias en sus viajes por Runaterra con su hada compañera, Pix. Lulu le da forma a la realidad a su antojo, transforma el tejido del mundo y de lo que ve como los límites...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Jinx", "Vayne", "Tristana"),
            tacticalAdvice = "Aprovecha el escalado y combos de Lulu en SUPPORT. Coordina el uso de su Crecimiento salvaje para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Pix, el hada compañera",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Lulu_PixFaerieCompanion.png",
                    description = "Pix lanza rayos de energía mágicos cuando el campeón al que está siguiendo ataca a otra unidad enemiga. Estos rayos son certeros, pero pueden ser interceptados por otras unidades.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Lanza reluciente",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuluQ.png",
                    description = "Pix y Lulu lanzan un rayo de energía mágica que inflige daño y ralentiza en gran medida a todos los enemigos a los que alcanza.",
                    cooldown = "7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Banal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuluW.png",
                    description = "Si se usa sobre un aliado, le otorga velocidad de ataque y velocidad de movimiento durante un breve periodo de tiempo. Si se usa sobre un enemigo, lo convierte en un animalillo adorable que no puede atacar ni lanzar hechizos.",
                    cooldown = "17/16.5/16/15.5/15s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "¡Ayuda, Pix!",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuluE.png",
                    description = "Si se lanza sobre un aliado, ordena a Pix que salte sobre él y protegerle. Entonces le sigue y asiste sus ataques. Si se lanza sobre un enemigo, ordena a Pix que salte sobre él e infligirle daño. Entonces le sigue, revelándolo.",
                    cooldown = "8s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Crecimiento salvaje",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuluR.png",
                    description = "Lulu hace crecer a un aliado. Los enemigos cercanos salen despedidos y el aliado afectado consigue gran cantidad de vida adicional. Durante los siguientes segundos, el aliado obtiene además un aura que ralentiza a los enemigos próximos.",
                    cooldown = "100/90/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lulu",
            wrMetaUrl = "https://wr-meta.com/champion/lulu/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/lulu/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/lulu"
        ),
        Champion(
            id = "lux",
            name = "Lux",
            title = "La Dama Luminosa",
            ddragonId = "Lux",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Lux.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "S+",
            winrate = 50.43,
            pickRate = 12.9,
            banRate = 18.17,
            damageType = DamageType.MAGIC,
            summary = "Luxanna Crownguard procede de Demacia, un reino insular en el que las habilidades mágicas se observan con temor y suspicacia. Capaz de manipular la luz a su voluntad, creció temiendo que la descubriesen y la exiliaran, por lo que se vio obligada a...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Caitlyn", "Ezreal", "Ashe"),
            tacticalAdvice = "Aprovecha el escalado y combos de Lux en MID. Coordina el uso de su Chispa final para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Iluminación",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/LuxIlluminatingFraulein.png",
                    description = "Las habilidades de Lux que infligen daño cargan de energía al objetivo durante unos segundos. Su siguiente ataque desata la energía e inflige daño mágico adicional (según el nivel de Lux) al objetivo.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Enlace de luz",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuxLightBinding.png",
                    description = "Lux lanza una esfera de luz que inmoviliza y causa daño a un máximo de 2 unidades enemigas.",
                    cooldown = "11/10.5/10/9.5/9s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Barrera prismática",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuxPrismaticWave.png",
                    description = "Lux lanza su varita y concentra la luz alrededor de los objetivos aliados a los que alcanza, protegiéndolos de cualquier daño.",
                    cooldown = "14/13/12/11/10s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Singularidad brillante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuxLightStrikeKugel.png",
                    description = "Lanza una anomalía de luz entrelazada a un área, que ralentiza a los enemigos cercanos. Lux puede activarla para dañar a los enemigos dentro del área de efecto.",
                    cooldown = "10/9.5/9/8.5/8s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Chispa final",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuxR.png",
                    description = "Tras reunir la energía necesaria, Lux lanza un rayo de luz que inflige daño a todos los objetivos de la zona. Además, activa la pasiva de Lux y reinicia la duración de la debilitación de Iluminación.",
                    cooldown = "60/50/40s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lux",
            wrMetaUrl = "https://wr-meta.com/champion/lux/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/lux/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/lux"
        ),
        Champion(
            id = "maokai",
            name = "Maokai",
            title = "El Treant Retorcido",
            ddragonId = "Maokai",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Maokai.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE),
            tier = "C",
            winrate = 49.97,
            pickRate = 2.42,
            banRate = 0.56,
            damageType = DamageType.MAGIC,
            summary = "Maokai es un imponente y feroz treant que lucha contra los horrores antinaturales de las Islas de la Sombra. Las ansias de venganza le inundaron después de que un cataclismo mágico destruyera su hogar, y sobrevive a la podredumbre únicamente por las...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Leona", "Alistar", "Braum"),
            synergies = listOf("Samira", "Jhin", "Miss Fortune"),
            tacticalAdvice = "Aprovecha el escalado y combos de Maokai en SUPPORT. Coordina el uso de su Garras de la naturaleza para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf("https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp", "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Absorción de magia",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Maokai_Passive.png",
                    description = "Los ataques básicos de Maokai también lo curan e infligen daño adicional tras un enfriamiento moderado. Cada vez que Maokai lanza un hechizo o es golpeado por un hechizo enemigo, este enfriamiento se reduce.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Zarzal opresor",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MaokaiQ.png",
                    description = "Maokai derriba a los enemigos cercanos con una onda de choque que les causa daño mágico y los ralentiza.",
                    cooldown = "7/6.5/6/5.5/5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Avance retorcido",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MaokaiW.png",
                    description = "Maokai se retuerce para convertirse en una masa de raíces móviles, se vuelve invulnerable y se lanza hacia el objetivo. Al llegar, inmoviliza al objetivo.",
                    cooldown = "14/13/12/11/10s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Lanzamiento de pimpollo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MaokaiE.png",
                    description = "Maokai lanza un pimpollo a la zona seleccionada para que la vigile. Es más efectivo en la maleza.",
                    cooldown = "18/17/16/15/14s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Garras de la naturaleza",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MaokaiR.png",
                    description = "Maokai invoca un muro de zarzas y pinchos enorme que avanza lentamente hacia delante e inmoviliza y daña a los enemigos en su camino.",
                    cooldown = "130/110/90s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/maokai",
            wrMetaUrl = "https://wr-meta.com/champion/maokai/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/maokai/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/maokai"
        ),
        Champion(
            id = "milio",
            name = "Milio",
            title = "la Llama Serena",
            ddragonId = "Milio",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Milio.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 52.0,
            pickRate = 8.3,
            banRate = 9.1,
            damageType = DamageType.MAGIC,
            summary = "Milio es un jovencito amable de Ixtal que, a pesar de su corta edad, ha conseguido dominar el axioma ígneo y ha descubierto las llamas de la calma. Con este nuevo poder, Milio pretende ayudar a su familia y poner fin a su exilio uniéndose a los Yun Tal...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Jinx", "Lucian", "Varus"),
            tacticalAdvice = "Aprovecha el escalado y combos de Milio en SUPPORT. Coordina el uso de su Aliento vital para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "¡Al calorcito!",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Milio_P.png",
                    description = "Las habilidades de Milio hechizan a sus aliados al tocarlos, lo que hace que la próxima vez que inflijan daño también generen una explosión de daño adicional y quemen a su objetivo.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Ultramegapatada ardiente",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MilioQ.png",
                    description = "Patea una bola que empuja a un enemigo. La bola sale disparada hacia arriba y cae sobre el enemigo, lo que inflige daño y ralentiza a los enemigos de la zona al impactar.",
                    cooldown = "10s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Hoguera reconfortante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MilioW.png",
                    description = "Crea un área de potenciación que cura a los aliados y aumenta el alcance de quienes se encuentran en su interior. El área sigue al aliado más cercano al punto de lanzamiento.",
                    cooldown = "29/27/25/23/21s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Abrazos cálidos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MilioE.png",
                    description = "Milio lanza un escudo a un aliado, lo que aumenta temporalmente su velocidad de movimiento. Esta habilidad tiene 2 cargas.",
                    cooldown = "0.5s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Aliento vital",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MilioR.png",
                    description = "Milio lanza una ola de llamas reconfortantes que cura y elimina los efectos de control de adversario de los aliados que se encuentren a su alcance.",
                    cooldown = "160/145/130s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/milio",
            wrMetaUrl = "https://wr-meta.com/champion/milio/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/milio/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/milio"
        ),
        Champion(
            id = "morgana",
            name = "Morgana",
            title = "la Caída",
            ddragonId = "Morgana",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Morgana.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.JUNGLE),
            tier = "S",
            winrate = 52.09,
            pickRate = 5.9,
            banRate = 21.01,
            damageType = DamageType.MAGIC,
            summary = "En vistas del conflicto entre su naturaleza celestial y su naturaleza mortal, Morgana decidió atarse las alas para aceptar la humanidad y deja caer el peso de su dolor y rencor sobre los deshonestos y los corruptos. Se opone a las leyes y tradiciones...",
            advantageAgainst = listOf("Leona", "Nautilus", "Blitzcrank"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Caitlyn", "Jhin", "Ashe"),
            tacticalAdvice = "Aprovecha el escalado y combos de Morgana en SUPPORT. Coordina el uso de su Grilletes del alma para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Absorbealmas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/FallenAngel_Empathize.png",
                    description = "Morgana absorbe el espíritu de sus enemigos y se cura cuando sus hechizos dañan a campeones, súbditos grandes y monstruos gigantes y medianos.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Hechizo oscuro",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MorganaQ.png",
                    description = "Morgana atrapa y detiene con magia negra a un enemigo, lo hace experimentar todo el dolor que ha causado y le inflige daño mágico.",
                    cooldown = "10s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Sombra atormentada",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MorganaW.png",
                    description = "Morgana invoca a una sombra maldita en una zona e inflige daño a todos los enemigos que osen caminar sobre su círculo de oscuridad. Reciben daño mágico prologando que aumenta en función de la vida que les falte.",
                    cooldown = "12s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Escudo negro",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MorganaE.png",
                    description = "Morgana escuda a un aliado con una barrera protectora de fuego estelar que absorbe daño mágico e impide que se apliquen efectos incapacitantes hasta que se haya roto.",
                    cooldown = "26/23.5/21/18.5/16s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Grilletes del alma",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MorganaR.png",
                    description = "Morgana desata todo el potencial de su poder celestial, se suelta las alas y flota sobre el suelo. Lanza cadenas de oscuro dolor hacia los campeones enemigos, lo que le otorga velocidad de movimiento. Las cadenas ralentizan e infligen daño inicialmente y, tras unos instantes, aturden a todos aquellos que no hayan conseguido liberarse de ellas.",
                    cooldown = "120/110/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/morgana",
            wrMetaUrl = "https://wr-meta.com/champion/morgana/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/morgana/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/morgana"
        ),
        Champion(
            id = "nami",
            name = "Nami",
            title = "La Invocadora de Mareas",
            ddragonId = "Nami",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Nami.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 51.11,
            pickRate = 3.28,
            banRate = 0.26,
            damageType = DamageType.MAGIC,
            summary = "Nami, una joven y testaruda vastaya marina, fue la primera de la tribu marai en abandonar las olas y aventurarse en tierra firme cuando se rompió el ancestral acuerdo de su tribu con los targonianos. A falta de otra opción, Nami se encargó de completar...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Sona", "Soraka", "Janna"),
            synergies = listOf("Lucian", "Jhin", "Vayne"),
            tacticalAdvice = "Aprovecha el escalado y combos de Nami en SUPPORT. Coordina el uso de su Maremoto para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Oleaje",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/NamiPassive.png",
                    description = "Cuando las habilidades de Nami impactan sobre campeones aliados, obtienen velocidad de movimiento durante unos instantes.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Prisión de agua",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NamiQ.png",
                    description = "Lanza una burbuja a la zona objetivo que inflige daño y aturde a todos los enemigos al impactar.",
                    cooldown = "12/11/10/9/8s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Resaca y oleada",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NamiW.png",
                    description = "Libera una corriente de agua que rebota entre campeones aliados y enemigos, de forma que cura a los aliados e inflige daño a los enemigos.",
                    cooldown = "10s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Bendición de la marea",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NamiE.png",
                    description = "Potencia a un campeón aliado durante un breve periodo de tiempo. Los hechizos y ataques básicos del aliado infligen daño mágico adicional y ralentizan al objetivo.",
                    cooldown = "11s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Maremoto",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NamiR.png",
                    description = "Invoca un gigantesco maremoto que derriba, ralentiza e inflige daño a los enemigos. Los aliados alcanzados se benefician del efecto de Oleaje multiplicado por dos.",
                    cooldown = "120/110/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nami",
            wrMetaUrl = "https://wr-meta.com/champion/nami/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/nami/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/nami"
        ),
        Champion(
            id = "nautilus",
            name = "Nautilus",
            title = "El Titán Abisal",
            ddragonId = "Nautilus",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Nautilus.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE),
            tier = "S",
            winrate = 49.33,
            pickRate = 14.02,
            banRate = 3.58,
            damageType = DamageType.MAGIC,
            summary = "El gigante acorazado Nautilus, una leyenda solitaria tan antigua como los pecios de Aguas Estancadas, recorre las turbias aguas que rodean las costas de las Islas de la Llama Azul. Impulsado por una traición olvidada, ataca con su enorme ancla sin...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Alistar", "Braum", "Morgana"),
            synergies = listOf("Samira", "Kai'Sa", "Jhin"),
            tacticalAdvice = "Aprovecha el escalado y combos de Nautilus en SUPPORT. Coordina el uso de su Carga de profundidad para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf("https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp", "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Golpe maestro",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Nautilus_StaggeringBlow.png",
                    description = "El primer ataque de Nautilus contra un objetivo inflige daño físico adicional y lo inmoviliza brevemente.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Línea de dragado",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NautilusAnchorDrag.png",
                    description = "Nautilus arroja su ancla. Si impacta con un enemigo, arrastra a ambos hasta una posición intermedia e inflige daño mágico. Si impacta contra un obstáculo, Nautilus se propulsa hacia él.",
                    cooldown = "14/13/12/11/10s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Ira del titán",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NautilusPiercingGaze.png",
                    description = "Nautilus obtiene un escudo temporal. Mientras está activo, sus ataques infligen daño prolongado a su objetivo y a los enemigos cercanos.",
                    cooldown = "12s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Aguas revueltas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NautilusSplashZone.png",
                    description = "Nautilus crea tres ondas de explosiones a su alrededor. Cada explosión inflige daño y ralentiza a los enemigos.",
                    cooldown = "7/6.5/6/5.5/5s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Carga de profundidad",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NautilusGrandLine.png",
                    description = "Nautilus lanza una onda expansiva al suelo que persigue al rival. Esta onda expansiva hace pedazos la tierra, lo que provoca que los enemigos salten por los aires. Cuando alcanza al enemigo, la onda expansiva estalla y los enemigos saltan por los aires y quedan aturdidos.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nautilus",
            wrMetaUrl = "https://wr-meta.com/champion/nautilus/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/nautilus/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/nautilus"
        ),
        Champion(
            id = "norra",
            name = "Norra",
            title = "la Maestra de los Portales",
            ddragonId = "norra",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "D",
            winrate = 48.07,
            pickRate = 1.47,
            banRate = 5.71,
            damageType = DamageType.MAGIC,
            summary = "Norra abre fisuras hacia la Ciudad de Bandle para dotar de hipermovilidad a su equipo y desorientar a los adversarios con proyectiles cósmicos.",
            advantageAgainst = listOf("Yasuo", "Katarina", "Akali"),
            counteredBy = listOf("Syndra", "Orianna", "Ziggs"),
            synergies = listOf("Jhin", "Caitlyn", "Ashe"),
            tacticalAdvice = "Aprovecha el escalado y combos de Norra en MID. Coordina el uso de su Puerta de Bandle para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Portales del Reino Espiritual",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png",
                    description = "Al lanzar hechizos crea vórtices efímeros que potencian las curaciones, escudos y movilidad del equipo aliado.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Proyectil Astral",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png",
                    description = "Lanza una esfera de energía espiritual que rebota entre enemigos infligiendo daño mágico creciente.",
                    cooldown = "7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Distorsión Dimensional",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png",
                    description = "Desplaza a un objetivo aliado o a sí misma a través de una grieta hacia una posición segura con velocidad extra.",
                    cooldown = "14s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Vínculo de Bandle",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png",
                    description = "Enlaza a un aliado otorgándole maná continuo y daño mágico adicional en sus siguientes impactos.",
                    cooldown = "10s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Puerta de Bandle",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png",
                    description = "Abre un portal cósmico masivo que succiona a los enemigos hacia el vórtice central, aturdiéndolos e infligiendo daño masivo.",
                    cooldown = "75s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/norra",
            wrMetaUrl = "https://wr-meta.com/champion/norra/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/norra/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/norra"
        ),
        Champion(
            id = "pyke",
            name = "Pyke",
            title = "el Destripador de los Puertos",
            ddragonId = "Pyke",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Pyke.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "C",
            winrate = 48.64,
            pickRate = 6.15,
            banRate = 2.59,
            damageType = DamageType.PHYSICAL,
            summary = "A Pyke, un conocido arponero de los muelles del matadero de Aguas Estancadas, le esperaba la muerte en el estómago de una gigantesca criatura marina... y sin embargo, regresó. Desde entonces acecha en los fríos y húmedos callejones y caminos de la que...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Leona", "Alistar", "Braum"),
            synergies = listOf("Draven", "Jhin", "Samira"),
            tacticalAdvice = "Aprovecha el escalado y combos de Pyke en SUPPORT. Coordina el uso de su Muerte de las profundidades para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Promesa de Caballero", "Convergencia de Zeke", "Relicario de los Solari de Hierro", "Botas Jonias de la Lucidez", "Promesa de Caballero", "Convergencia de Zeke"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3109.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3050.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3190.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3158.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3109.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3050.png"),
            situationalItems = listOf("Cota de Espinas", "Protector Pétreo"),
            itemSwaps = listOf(
ItemSwap(coreItem="Convergencia de Zeke", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3050.png", altItem="Pebetero Ardiente", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3504.png", reasonTitle="MEJORA DE ATAQUE", reasonDesc="Otorga velocidad de ataque a tus aliados.", againstWho="Ideal para ADCs de autoataques (Jinx, Vayne)"),
ItemSwap(coreItem="Promesa de Caballero", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3109.png", altItem="Relicario de los Solari", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3190.png", reasonTitle="ESCUDO EN ÁREA", reasonDesc="Protege contra ráfagas de daño de área.", againstWho="Kennen, Katarina, Fiddlesticks")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3193.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Bendición de los ahogados",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/PykePassive.png",
                    description = "Cuando Pyke se oculta de los enemigos, regenera el daño que haya sufrido recientemente de campeones. Además, en vez de obtener vida máxima extra de cualquier tipo, gana DA adicional.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Espetón de huesos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PykeQ.png",
                    description = "Pyke apuñala a un enemigo que tenga delante o lo arrastra hacia él.",
                    cooldown = "10/9.5/9/8.5/8s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Inmersión espectromarina",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PykeW.png",
                    description = "Pyke entra en estado de camuflaje y obtiene una buena cantidad de velocidad de movimiento que disminuye a lo largo del tiempo.",
                    cooldown = "14/13/12/11/10s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Corriente fantasma",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PykeE.png",
                    description = "Pyke se desliza y deja un espíritu tras él que, cuando vuelve a su cuerpo, aturde a los campeones enemigos que encuentra a su paso.",
                    cooldown = "15/14/13/12/11s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Muerte de las profundidades",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PykeR.png",
                    description = "Pyke aparece junto a enemigos con poca vida y los ejecuta, lo que le permite lanzar de nuevo su hechizo y otorgar oro adicional al aliado que le asista.",
                    cooldown = "100/85/70s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/pyke",
            wrMetaUrl = "https://wr-meta.com/champion/pyke/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/pyke/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/pyke"
        ),
        Champion(
            id = "rakan",
            name = "Rakan",
            title = "El Encantador",
            ddragonId = "Rakan",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Rakan.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 49.95,
            pickRate = 2.52,
            banRate = 0.41,
            damageType = DamageType.MAGIC,
            summary = "Con un carácter tan impulsivo como encantador, Rakan es un infame alborotador vastaya y el mejor bailarín de batalla de la historia tribal de Lhotlan. Para los humanos de las montañas de Jonia, su nombre siempre ha sido sinónimo de festivales...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Leona", "Alistar", "Janna"),
            synergies = listOf("Xayah", "Yasuo", "Miss Fortune"),
            tacticalAdvice = "Aprovecha el escalado y combos de Rakan en SUPPORT. Coordina el uso de su La premura para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf("https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp", "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Plumas encantadas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Rakan_P.png",
                    description = "Rakan obtiene un escudo de forma periódica.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Péndola reluciente",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RakanQ.png",
                    description = "Lanza una pluma mágica que inflige daño mágico. Al golpear a un campeón o a un monstruo épico, Rakan puede curar a sus aliados.",
                    cooldown = "11/10/9/8/7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Entrada grandiosa",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RakanW.png",
                    description = "Se desliza hacia una ubicación y lanza por los aires a los enemigos cercanos al llegar.",
                    cooldown = "16/14.5/13/11.5/10s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Danza de batalla",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RakanE.png",
                    description = "Vuela hacia un campeón aliado y le otorga un escudo. Se puede lanzar de nuevo sin coste alguno durante un periodo corto de tiempo.",
                    cooldown = "0s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "La premura",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RakanR.png",
                    description = "Obtiene velocidad de movimiento y hechiza e inflige daño mágico a los enemigos que toque.",
                    cooldown = "130/110/90s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/rakan",
            wrMetaUrl = "https://wr-meta.com/champion/rakan/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/rakan/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/rakan"
        ),
        Champion(
            id = "rell",
            name = "Rell",
            title = "la Dama de Hierro",
            ddragonId = "Rell",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Rell.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "C",
            winrate = 51.88,
            pickRate = 2.38,
            banRate = 1.27,
            damageType = DamageType.MAGIC,
            summary = "Rell, producto de crueles experimentos a manos de la Rosa Negra, es ahora una intrépida arma viviente decidida a hacer caer Noxus. Su infancia estuvo marcada por la desdicha y el horror, pues se vio sometida a espeluznantes tratamientos para...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Alistar", "Braum", "Janna"),
            synergies = listOf("Samira", "Yasuo", "Miss Fortune"),
            tacticalAdvice = "Aprovecha el escalado y combos de Rell en SUPPORT. Coordina el uso de su Tormenta magnética para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf("https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp", "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Rompemoldes",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/RellP.png",
                    description = "Los ataques y habilidades de Rell roban armadura y resistencia mágica al impactar.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Golpe demoledor",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RellQ.png",
                    description = "Rell inflige daño mágico a unidades en una línea, lo que rompe sus escudos y los aturde.",
                    cooldown = "11/10.5/10/9.5/9s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Ferromancia - Caída",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RellW_Dismount.png",
                    description = "Montada: Rell se desmonta y cae con fuerza con su armadura, lanza por los aires a los enemigos y obtiene un gran escudo. Cuando está desmontada obtiene armadura, resistencia mágica, velocidad de ataque y alcance de ataque, pero se ve ralentizada.Desmontada: Rell forma su montura, obtiene una mejora de velocidad y lanza por los aires al siguiente enemigo al que ataca.",
                    cooldown = "11s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Justa",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RellE.png",
                    description = "Pasiva: Rell gana velocidad de movimiento fuera de combate.Activa: Rell y un aliado obtienen velocidad de movimiento, que se duplica cuando avanzan el uno hacia el otro y hacia enemigos. Su próximo ataque explotará e infligirá daño mágico.",
                    cooldown = "15s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Tormenta magnética",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RellR.png",
                    description = "Rell genera una furiosa explosión magnética y atrae violentamente a los enemigos cercanos. Después, Rell atrae de forma constante a los enemigos cercanos hacia ella durante un breve periodo de tiempo, lo que inflige daño mágico a lo largo del tiempo.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/rell",
            wrMetaUrl = "https://wr-meta.com/champion/rell/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/rell/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/rell"
        ),
        Champion(
            id = "senna",
            name = "Senna",
            title = "la Redentora",
            ddragonId = "Senna",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Senna.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "B",
            winrate = 52.46,
            pickRate = 4.9,
            banRate = 0.84,
            damageType = DamageType.PHYSICAL,
            summary = "Maldita desde que era apenas una niña y perseguida por la Niebla Negra, Senna se unió a una orden sagrada conocida como los Centinelas de la Luz y pasó años luchando sin descanso hasta que fue asesinada y encerrada en el interior de la linterna del...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Lucian", "Jhin", "Braum"),
            tacticalAdvice = "Aprovecha el escalado y combos de Senna en ADC. Coordina el uso de su Sombra del amanecer para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Compás Letal (Precisión)",
            runeTreeDetails = "Precisión: Triunfo • Leyenda: Presteza • Golpe de Gracia • Cazador de Titanes",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/lethaltempo/lethaltempotemp.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "Cañón de Fuego Rápido", "Punteras Revestidas", "Filo del Infinito", "Recuerdos de Lord Dominik"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3036.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ángel Guardián"),
            itemSwaps = listOf(
ItemSwap(coreItem="Recuerdos de Lord Dominik", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3036.png", altItem="Recordatorio Mortal", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3033.png", reasonTitle="PENETRACIÓN Y ANTI-CURACIÓN", reasonDesc="Aplica cortacuras a los enemigos tanque.", againstWho="Soraka, Dr. Mundo, Vladimir"),
ItemSwap(coreItem="Filo del Infinito", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3031.png", altItem="Colmillo de Serpiente", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/6695.png", reasonTitle="ANTI-ESCUDOS", reasonDesc="Reduce enormemente los escudos.", againstWho="Karma, Janna, Sett, Lulu")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Absolución",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Senna_Passive.png",
                    description = "Cuando mueren unidades cerca de Senna, la Niebla Negra atrapa sus almas en intervalos periódicos. Senna puede atacar a estas almas para liberarlas y absorber la niebla que las retiene. La Niebla Negra aumenta el poder de su cañón reliquia: daño de ataque, alcance y probabilidad de impacto crítico aumentados. Los ataques del cañón reliquia de Senna tardan más en dispararse, infligen daño adicional y le otorgan una parte de la velocidad de movimiento de su objetivo durante un breve periodo de tiempo.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Oscuridad lacerante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SennaQ.png",
                    description = "Senna lanza un rayo de luz y sombra de los cañones gemelos de su arma reliquia que atraviesa la zona objetivo, cura a los aliados e inflige daño a los enemigos.",
                    cooldown = "15s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Último abrazo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SennaW.png",
                    description = "Senna lanza una ola de Niebla Negra. Si golpea a un enemigo, se queda enganchada a él, lo inmoviliza y, poco después, inmoviliza a todas las unidades cercanas.",
                    cooldown = "11s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Maldición de la Niebla Negra",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SennaE.png",
                    description = "Senna reúne la niebla que ha acumulado en su arma e invoca una tormenta a su alrededor, entregándose a la oscuridad y convirtiéndose en un espectro. Los aliados que entran en la zona quedan camuflados y adoptan la apariencia de espectros mientras están envueltos en niebla. Los espectros aumentan su velocidad de movimiento, no se pueden seleccionar como objetivos y ocultan su identidad.",
                    cooldown = "26/24.5/23/21.5/20s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Sombra del amanecer",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SennaR.png",
                    description = "Senna invoca la fuerza de las reliquias de los Centinelas caídos y su cañón despliega una mezcla sagrada de luz y oscuridad. Entonces, dispara un rayo global que otorga un escudo a sus aliados e inflige daño a los enemigos que estén en el centro del impacto.",
                    cooldown = "140/120/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/senna",
            wrMetaUrl = "https://wr-meta.com/champion/senna/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/senna/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/senna"
        ),
        Champion(
            id = "seraphine",
            name = "Seraphine",
            title = "la Cantante Soñadora",
            ddragonId = "Seraphine",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Seraphine.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.ADC),
            tier = "A",
            winrate = 50.44,
            pickRate = 11.99,
            banRate = 2.66,
            damageType = DamageType.MAGIC,
            summary = "Seraphine, de padres zaunitas, nació en Piltover y es capaz de escuchar las almas de los demás. El mundo le canta y ella le devuelve la canción. Aunque esos sonidos le resultaban abrumadores cuando era pequeña, ahora le sirven de inspiración, y...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Ashe", "Miss Fortune", "Caitlyn"),
            tacticalAdvice = "Aprovecha el escalado y combos de Seraphine en SUPPORT. Coordina el uso de su Bis para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Presencia escénica",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Seraphine_Passive.png",
                    description = "Cada tercera habilidad básica que utilice Seraphine se lanzará dos veces. Además, lanzar hechizos cerca de los aliados otorga daño mágico adicional y alcance en su siguiente ataque básico.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Nota alta",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SeraphineQ.png",
                    description = "Seraphine inflige daño en un área.",
                    cooldown = "8/7.5/7/6.5/6s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Sonido envolvente",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SeraphineW.png",
                    description = "Seraphine otorga un escudo y acelera a los aliados cercanos. Si ella ya tiene un escudo, también cura a los aliados cercanos.",
                    cooldown = "22s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Clímax musical",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SeraphineE.png",
                    description = "Seraphine inflige daño e inmoviliza a los enemigos en una línea.",
                    cooldown = "11/10.5/10/9.5/9s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Bis",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SeraphineR.png",
                    description = "Seraphine inflige daño y hechiza a los enemigos golpeados. Restaura el alcance con cada campeón aliado o enemigo que alcanza.",
                    cooldown = "160/140/120s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/seraphine",
            wrMetaUrl = "https://wr-meta.com/champion/seraphine/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/seraphine/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/seraphine"
        ),
        Champion(
            id = "sona",
            name = "Sona",
            title = "La Virtuosa de las Cuerdas",
            ddragonId = "Sona",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Sona.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 50.88,
            pickRate = 2.94,
            banRate = 0.42,
            damageType = DamageType.MAGIC,
            summary = "Sona es la artista más virtuosa de Demacia con el etwahl de cuerda y solo se comunica a través de sus elegantes acordes y vibrantes melodías. Esta refinada actitud le ha hecho ganarse el favor de la alta sociedad, aunque los hay que sospechan que sus...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Blitzcrank", "Nautilus", "Pyke"),
            synergies = listOf("Ashe", "Miss Fortune", "Ezreal"),
            tacticalAdvice = "Aprovecha el escalado y combos de Sona en SUPPORT. Coordina el uso de su Crescendo para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Energía acorde",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Sona_Passive_Charged.png",
                    description = "Accelerando: Sona obtiene velocidad de habilidades no definitivas de forma permanente al usar sus habilidades bien, hasta un límite. A partir de ese límite, los usos con éxito reducen el enfriamiento restante de su definitiva.Energía acorde: Cada pocos lanzamientos de hechizos, el siguiente ataque de Sona inflige daño mágico adicional, además de tener un efecto adicional según la última habilidad básica que haya activado Sona.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Himno del valor",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SonaQ.png",
                    description = "Sona toca el Himno del valor, que lanza rayos sónicos que infligen daño mágico a dos enemigos cercanos (prioriza a campeones y monstruos). Además, obtiene un aura temporal que aumenta el daño causado por los aliados situados en la zona en su siguiente ataque contra enemigos.",
                    cooldown = "8s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Aria de la perseverancia",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SonaW.png",
                    description = "Sona toca el Aria de la perseverancia y sus melodías protectoras la curan tanto a ella como a un aliado herido cercano. Además, obtiene un aura temporal que otorga un escudo temporal a todos los aliados situados en la zona.",
                    cooldown = "10s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Canción de la celeridad",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SonaE.png",
                    description = "Sona toca la Canción de la celeridad, que otorga a los aliados cercanos velocidad de movimiento adicional. Además, obtiene un aura temporal que otorga a los campeones aliados afectados por la zona velocidad de movimiento adicional.",
                    cooldown = "14s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Crescendo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SonaR.png",
                    description = "Sona toca su acorde definitivo, que además de obligar a los campeones enemigos a bailar, les inflige daño mágico. El enfriamiento básico de las habilidades básicas de Sona se reduce con cada nivel.",
                    cooldown = "140/120/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/sona",
            wrMetaUrl = "https://wr-meta.com/champion/sona/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/sona/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/sona"
        ),
        Champion(
            id = "soraka",
            name = "Soraka",
            title = "La Hija de las Estrellas",
            ddragonId = "Soraka",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Soraka.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 51.5,
            pickRate = 4.37,
            banRate = 1.28,
            damageType = DamageType.MAGIC,
            summary = "Soraka, una nómada proveniente de las dimensiones celestiales más allá del monte Targon, dejó a un lado su inmortalidad para proteger a las razas mortales de sus propios instintos asesinos. Su cometido es difundir las virtudes de la compasión y la...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Blitzcrank", "Nautilus", "Pyke"),
            synergies = listOf("Vayne", "Jinx", "Tristana"),
            tacticalAdvice = "Aprovecha el escalado y combos de Soraka en SUPPORT. Coordina el uso de su Plegaria para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Salvación",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Soraka_Passive.png",
                    description = "Soraka corre más rápido hacia los aliados con poca vida.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Invocación estelar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SorakaQ.png",
                    description = "Cae una estrella del cielo en la ubicación seleccionada que inflige daño mágico y ralentiza a los enemigos. Si Soraka alcanza a un campeón enemigo con Invocación estelar, recupera vida.",
                    cooldown = "8/7/6/5/4s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Inyección astral",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SorakaW.png",
                    description = "Soraka sacrifica parte de su vida para curar a otro campeón aliado.",
                    cooldown = "6/5/4/3/2s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Equinoccio",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SorakaE.png",
                    description = "Crea una zona que silencia a todos los enemigos situados en su interior. Una vez que expira el efecto, los afectados quedan inmovilizados.",
                    cooldown = "20/19/18/17/16s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Plegaria",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SorakaR.png",
                    description = "Soraka infunde esperanza a sus aliados, lo que cura instantáneamente a los campeones aliados (incluida ella).",
                    cooldown = "150/135/120s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/soraka",
            wrMetaUrl = "https://wr-meta.com/champion/soraka/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/soraka/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/soraka"
        ),
        Champion(
            id = "thresh",
            name = "Thresh",
            title = "El Carcelero Implacable",
            ddragonId = "Thresh",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Thresh.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 50.93,
            pickRate = 13.1,
            banRate = 8.94,
            damageType = DamageType.MAGIC,
            summary = "Thresh, un ser sádico y astuto, es un ambicioso y trastornado espíritu de las Islas de la Sombra. Otrora guardián de innumerables secretos arcanos, acabó sucumbiendo a un poder por encima de la vida y la muerte. Ahora sobrevive torturando a sus víctimas...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Morgana", "Zyra", "Brand"),
            synergies = listOf("Varus", "Jinx", "Draven"),
            tacticalAdvice = "Aprovecha el escalado y combos de Thresh en SUPPORT. Coordina el uso de su La caja para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf("https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp", "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Condenación",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Thresh_Passive.png",
                    description = "Thresh puede cosechar las almas de los enemigos que mueran cerca de él; al hacerlo, obtiene armadura y poder de habilidad permanentes.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Sentencia de muerte",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ThreshQ.png",
                    description = "Thresh atrapa a un enemigo con su cadena y lo atrae hacia él. Si se activa esta habilidad una segunda vez, Thresh se lanza hacia el enemigo.",
                    cooldown = "19/16.5/14/11.5/9s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Camino oscuro",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ThreshW.png",
                    description = "Thresh lanza una linterna que otorga un escudo a los campeones aliados cercanos. Los aliados pueden hacer clic en la linterna para deslizarse hacia Thresh.",
                    cooldown = "21/20/19/18/17s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Despellejar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ThreshE.png",
                    description = "Thresh va preparando los ataques, e inflige más daño cuanto más tiempo espere entre ellos. Al activarla, Thresh barre la zona con su cadena y empuja a todos los enemigos impactados en la dirección del golpe.",
                    cooldown = "13/12.25/11.5/10.75/10s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "La caja",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ThreshRPenta.png",
                    description = "Una prisión de muros que ralentizan e infligen daño al romperlos.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/thresh",
            wrMetaUrl = "https://wr-meta.com/champion/thresh/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/thresh/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/thresh"
        ),
        Champion(
            id = "yuumi",
            name = "Yuumi",
            title = "la Gata Mágica",
            ddragonId = "Yuumi",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "B",
            winrate = 45.13,
            pickRate = 7.5,
            banRate = 19.15,
            damageType = DamageType.MAGIC,
            summary = "Yuumi, una gata mágica de Ciudad de Bandle, fue antaño la compañera de una hechicera yordle, Norra. Tras la misteriosa desaparición de su maestra, Yuumi se convirtió en la guardiana del Libro de umbrales de Norra, un objeto sintiente cuyas páginas...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Blitzcrank", "Nautilus", "Pyke"),
            synergies = listOf("Zeri", "Ezreal", "Twitch"),
            tacticalAdvice = "Aprovecha el escalado y combos de Yuumi en SUPPORT. Coordina el uso de su Capítulo final para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Amistad felina",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/YuumiP2.png",
                    description = "De vez en cuando, al golpear a un campeón con un ataque básico o una habilidad, Yuumi recupera vida para ella y el aliado al que está vinculada.Mientras está vinculada, Yuumi crea un vínculo especial con sus aliados. El aliado con el vínculo más estrecho mejora las habilidades de Yuumi cuando esta se vincula.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Misil acechador",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YuumiQ.png",
                    description = "Yuumi dispara un misil que inflige daño y ralentiza al primer objetivo golpeado. El misil inflige daño adicional y ralentiza más si tarda al menos 1,35 s en alcanzar a su objetivo. Mientras está con su mejor amigui, la ralentización siempre aumenta y le otorga a su aliado daño adicional al golpear.Mientras está vinculada, el misil puede controlarse con el cursor durante un breve periodo de tiempo.",
                    cooldown = "6.5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "¡Yupi!",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YuumiW.png",
                    description = "Yuumi se desliza hasta un aliado y solo las torretas pueden marcarla como objetivo. Mientras está con su mejor amigui, obtiene poder de curaciones y escudos, además de otorgarle a su aliado curación al golpear.",
                    cooldown = "0s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Presteza gatuna",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YuumiE.png",
                    description = "Protege a Yuumi y potencia la velocidad de movimiento y la velocidad de ataque. Si está vinculada, es el aliado quien se beneficia del aumento.",
                    cooldown = "10s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Capítulo final",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YuumiR.png",
                    description = "Yuumi canaliza cinco oleadas que dañan a los enemigos y curan a los aliados. Yuumi puede moverse, vincularse y lanzar Presteza gatuna durante la canalización. Mientras esté con su mejor amigui, esta habilidad sigue el cursor del ratón.",
                    cooldown = "120/110/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/yuumi",
            wrMetaUrl = "https://wr-meta.com/champion/yuumi/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/yuumi/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/yuumi"
        ),
        Champion(
            id = "zyra",
            name = "Zyra",
            title = "La Dama de Espinas",
            ddragonId = "Zyra",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Zyra.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.JUNGLE),
            tier = "S",
            winrate = 52.52,
            pickRate = 4.6,
            banRate = 23.81,
            damageType = DamageType.MAGIC,
            summary = "Nacida gracias a un fenómeno sobrenatural hace mucho tiempo, Zyra encarna la ira de la naturaleza: un seductor híbrido entre planta y humano que da lugar a nueva vida a cada paso. Ve a los mortales de Valoran como poco más que presas para su progenie, y...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Jhin", "Ashe", "Caitlyn"),
            tacticalAdvice = "Aprovecha el escalado y combos de Zyra en SUPPORT. Coordina el uso de su Tuercespinas para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Jardín de espinas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/ZyraP.png",
                    description = "Aparecen semillas alrededor de Zyra periódicamente; lo hacen más rápido cuanto mayor es el nivel. Zyra puede lanzar Espinas mortales o Raíces atenazadoras cerca de las semillas para hacer crecer una planta que luchará por ella.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Espinas mortales",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZyraQ.png",
                    description = "Salen unas gruesas vides del suelo que explotan y lanzan espinas, las cuales infligen daño mágico a los enemigos en las proximidades. Si Espinas mortales se lanza cerca de una semilla, crece una Escupespinas que dispara a los enemigos desde lejos.",
                    cooldown = "7/6.5/6/5.5/5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Crecimiento desenfrenado",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZyraW.png",
                    description = "Zyra planta una semilla, que dura hasta 60 s. Si lanza Espinas mortales o Raíces atenazadoras cerca de las semillas, se convertirán en plantas que lucharán para Zyra. Zyra puede acumular varias semillas a la vez. Asesinar enemigos reduce el tiempo de recarga de Crecimiento desenfrenado.",
                    cooldown = "0s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Raíces atenazadoras",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZyraE.png",
                    description = "Zyra lanza vides por el suelo para envolver a sus enemigos, que infligen daño e inmovilizan a los que alcancen. Si se lanza cerca de una semilla, Raíces atenazadoras genera Azotavides, cuyos ataques a corta distancia reducen la velocidad de movimiento del enemigo.",
                    cooldown = "12s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Tuercespinas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZyraR.png",
                    description = "Zyra invoca un matorral retorcido en la ubicación objetivo, que inflige daño a los enemigos cuando se expande y los levanta por los aires al contraerse. Las plantas que se encuentren dentro del matorral se enfurecen.",
                    cooldown = "110/100/90s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/zyra",
            wrMetaUrl = "https://wr-meta.com/champion/zyra/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/zyra/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/zyra"
        )
    )
}
