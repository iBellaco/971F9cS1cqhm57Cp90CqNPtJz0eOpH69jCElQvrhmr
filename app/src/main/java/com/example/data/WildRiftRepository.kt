package com.example.data

import com.example.model.Champion
import com.example.model.DamageType
import com.example.model.DraftAnalysisResult
import com.example.model.DraftRecommendation
import com.example.model.LaneRole

object WildRiftRepository {

    val champions: List<Champion> = listOf(
        Champion(
            id = "morgana",
            name = "Morgana",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT, LaneRole.JUNGLE),
            tier = "S+",
            winrate = 54.2,
            pickRate = 12.8,
            banRate = 18.4,
            damageType = DamageType.MAGIC,
            summary = "Su Escudo Negro anula el CC de iniciadores rivales, blindando a los carries aliados y asegurando control de mapa.",
            advantageAgainst = listOf("Rell", "Vi", "Sett", "Caitlyn", "Braum", "Alistar", "Pyke"),
            counteredBy = listOf("Zed", "Yasuo", "Yone", "Karma"),
            synergies = listOf("Viego", "Vayne", "Cho'Gath", "Jinx"),
            tacticalAdvice = "Morgana: Su Escudo Negro anula el CC de Vi, Sett y Rakan, protegiendo a Vayne y anulando la iniciación rival.",
            recommendedRunes = "Cometa Arcano / Primer Golpe",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Tormento de Liandry", "Orbe del Infinito", "Corona de la Reina", "Sombrero Mortal de Rabadon"),
            skillOrder = "Max Q > W > E",
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "viego",
            name = "Viego",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.TOP),
            tier = "S+",
            winrate = 54.1,
            pickRate = 16.5,
            banRate = 24.1,
            damageType = DamageType.PHYSICAL,
            summary = "Posesión de cuerpos enemigos al conseguir bajas, curación masiva, inmunidad transitoria y reinicios infinitos de su definitiva.",
            advantageAgainst = listOf("Caitlyn", "Vi", "Sett", "Cho'Gath", "Lee Sin"),
            counteredBy = listOf("Rammus", "Jax", "Lulu", "Morgana"),
            synergies = listOf("Vayne", "Morgana", "Sett", "Nautilus"),
            tacticalAdvice = "Viego: Reinicios en cadena en peleas grupales. Aprovecha el CC aliado para asegurar la primera baja y dominar el campo.",
            recommendedRunes = "Conquistador",
            recommendedSpells = listOf("Destello", "Castigo"),
            coreItems = listOf("Fuerza de la Trinidad", "Hoja del Rey Arruinado", "Desgarrador Divino", "Ángel Guardián"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "nautilus",
            name = "Nautilus",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE),
            tier = "S",
            winrate = 53.1,
            pickRate = 11.2,
            banRate = 9.8,
            damageType = DamageType.MAGIC,
            summary = "Mayor cantidad de CC asegurado en objetivo individual con su R teledirigida y pasiva.",
            advantageAgainst = listOf("Vayne", "Yasuo", "Katarina", "Zed", "Pyke"),
            counteredBy = listOf("Morgana", "Olaf", "Fiora", "Janna"),
            synergies = listOf("Kai'Sa", "Samira", "Yasuo", "Miss Fortune"),
            tacticalAdvice = "Nautilus: Inicia sobre objetivos prioritarios sin posibilidad de esquive con su R definitiva.",
            recommendedRunes = "Reverberacción",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Coraza del Muerto", "Malla de Espinas", "Protector Pétreo", "Fuerza de la Naturaleza"),
            skillOrder = "Max Q > W > E",
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "yasuo",
            name = "Yasuo",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.ADC),
            tier = "S",
            winrate = 51.8,
            pickRate = 18.2,
            banRate = 22.0,
            damageType = DamageType.PHYSICAL,
            summary = "Muro de Viento (W) bloquea todos los proyectiles del juego y daño crítico continuo.",
            advantageAgainst = listOf("Caitlyn", "Ahri", "Lux", "Syndra", "Miss Fortune"),
            counteredBy = listOf("Renekton", "Pantheon", "Sett", "Malphite"),
            synergies = listOf("Malphite", "Nautilus", "Diana", "Rakan", "Gragas"),
            tacticalAdvice = "Yasuo: El Muro de Viento anula el daño del ADC rival en peleas de dragón y Barón.",
            recommendedRunes = "Cadencia Letal",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Filo del Infinito", "Bailarín Espectral", "Arcoescudo Inmortal", "Sanguinaria"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = false
        ),
        Champion(
            id = "sett",
            name = "Sett",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.SUPPORT, LaneRole.MID),
            tier = "S+",
            winrate = 53.7,
            pickRate = 15.1,
            banRate = 19.5,
            damageType = DamageType.PHYSICAL,
            summary = "Daño verdadero monumental con su W cargada al máximo y definitiva que destruye agrupaciones enemigas lanzando al tanque.",
            advantageAgainst = listOf("Yasuo", "Irelia", "Riven", "Cho'Gath", "Sion"),
            counteredBy = listOf("Vayne", "Fiora", "Kennen", "Aatrox"),
            synergies = listOf("Orianna", "Miss Fortune", "Morgana", "Viego"),
            tacticalAdvice = "Sett: Absorbe el daño inicial del rival y contraataca en el centro de la batalla con daño verdadero puro.",
            recommendedRunes = "Conquistador",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Rompecascos", "Corazón de Acero", "Hidra Titánica", "Guantelete de Sterak"),
            skillOrder = "Max W > Q > E",
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "aatrox",
            name = "Aatrox",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "S+",
            winrate = 53.4,
            pickRate = 14.8,
            banRate = 17.3,
            damageType = DamageType.PHYSICAL,
            summary = "Curación absurda en combate sostenido y control de área con el filo de las 3 fases de su Q.",
            advantageAgainst = listOf("Sett", "Darius", "Garen", "Sion", "Renekton"),
            counteredBy = listOf("Fiora", "Irelia", "Vayne", "Ignite"),
            synergies = listOf("Lulu", "Yuumi", "Morgana", "Sejuani"),
            tacticalAdvice = "Aatrox: Golpea los bordes de la Q para maximizar el derribo aéreo y curación sostenida.",
            recommendedRunes = "Conquistador",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Cuchilla Negra", "Baile de la Muerte", "Eclipse", "Rencor de Serylda"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "darius",
            name = "Darius",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "S",
            winrate = 52.6,
            pickRate = 13.0,
            banRate = 16.0,
            damageType = DamageType.PHYSICAL,
            summary = "Hemorragia acumulable al 5to golpe activa Fuerza Noxiana y reinicios instantáneos con Guillotina Noxiana (R).",
            advantageAgainst = listOf("Garen", "Nasus", "Malphite", "Shen"),
            counteredBy = listOf("Vayne", "Kennen", "Aatrox", "Teemo"),
            synergies = listOf("Ghost", "Lulu", "Thresh", "Nautilus"),
            tacticalAdvice = "Darius: Prioriza acumular las 5 marcas rápidamente antes de ejecutar con la R.",
            recommendedRunes = "Conquistador",
            recommendedSpells = listOf("Destello", "Fantasma"),
            coreItems = listOf("Fuerza de la Trinidad", "Coraza del Muerto", "Guantelete de Sterak", "Malla de Espinas"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "fiora",
            name = "Fiora",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 53.8,
            pickRate = 11.5,
            banRate = 18.0,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Daño verdadero por porcentaje de vida máxima y Estocada (W) que refleja aturdimientos enemigos.",
            advantageAgainst = listOf("Aatrox", "Darius", "Sett", "Cho'Gath", "Ornn"),
            counteredBy = listOf("Malphite", "Wukong", "Bramble Vest"),
            synergies = listOf("Twisted Fate", "Galio", "Shen"),
            tacticalAdvice = "Fiora: Guarda la W para bloquear la habilidad con mayor CC del rival (ej. Gancho de Thresh o W de Sett).",
            recommendedRunes = "Garras del Inmortal",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Desgarrador Divino", "Rompecascos", "Baile de la Muerte", "Fauces de Malmortius"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = false
        ),
        Champion(
            id = "janna",
            name = "Janna",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S",
            winrate = 52.9,
            pickRate = 9.4,
            banRate = 4.1,
            damageType = DamageType.MAGIC,
            summary = "Proporciona un gran desengage, escudos masivos y curación de área con Monzón (R).",
            advantageAgainst = listOf("Sett", "Vi", "Rakan", "Leona", "Alistar"),
            counteredBy = listOf("Blitzcrank", "Pyke", "Nautilus", "Senna"),
            synergies = listOf("Vayne", "Jinx", "Caitlyn", "Tristana"),
            tacticalAdvice = "Janna: Proporciona un gran desengage y escudo para proteger a los tiradores frente a clavados rivales.",
            recommendedRunes = "Aery",
            recommendedSpells = listOf("Destello", "Curación"),
            coreItems = listOf("Incensario Ardiente", "Ecos de Helia", "Mandato Imperial", "Redención"),
            skillOrder = "Max E > W > Q",
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "lulu",
            name = "Lulu",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S+",
            winrate = 53.5,
            pickRate = 12.1,
            banRate = 14.5,
            damageType = DamageType.MAGIC,
            summary = "Su polimorfismo y definitiva son vitales para transformar hiper-carries en bestias imparables.",
            advantageAgainst = listOf("Zed", "Katarina", "Master Yi", "Viego", "Vi"),
            counteredBy = listOf("Blitzcrank", "Brand", "Lux", "Zyra"),
            synergies = listOf("Jinx", "Vayne", "Twitch", "Tristana"),
            tacticalAdvice = "Lulu: Su polimorfismo y definitiva son vitales para neutralizar a los asesinos y potenciar a Vayne.",
            recommendedRunes = "Aery",
            recommendedSpells = listOf("Destello", "Extenuación"),
            coreItems = listOf("Incensario Ardiente", "Redención", "Bastón de Aguas Fluidas", "Corona de la Reina"),
            skillOrder = "Max E > W > Q",
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "chogath",
            name = "Cho'Gath",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S",
            winrate = 52.4,
            pickRate = 8.5,
            banRate = 5.2,
            damageType = DamageType.MAGIC,
            summary = "Escalado de vida infinito con Festín (R) y silencio de área que interrumpe combos enemigos.",
            advantageAgainst = listOf("Riven", "Yasuo", "Katarina", "Akali"),
            counteredBy = listOf("Fiora", "Vayne", "Gwen"),
            synergies = listOf("Yasuo", "Orianna", "Miss Fortune"),
            tacticalAdvice = "Cho'Gath: Asegura objetivos como Dragones con el daño verdadero instantáneo de su R Festín.",
            recommendedRunes = "Garras del Inmortal",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Corazón de Acero", "Malla de Espinas", "Corona de la Reina", "Fuerza de la Naturaleza"),
            skillOrder = "Max E > W > Q",
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "vayne",
            name = "Vayne",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "S+",
            winrate = 53.9,
            pickRate = 17.1,
            banRate = 20.2,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Hiperescalado letal con Proyectiles de Plata (W) de daño verdadero por % de vida y movilidad con invisibilidad.",
            advantageAgainst = listOf("Cho'Gath", "Sion", "Sett", "Dr. Mundo", "Braum"),
            counteredBy = listOf("Caitlyn", "Draven", "Lucian", "Teemo"),
            synergies = listOf("Lulu", "Janna", "Morgana", "Nami", "Thresh"),
            tacticalAdvice = "Vayne: Posiciónate detrás de Morgana y Cho'Gath para derretir la primera línea rival sin riesgo.",
            recommendedRunes = "Cadencia Letal",
            recommendedSpells = listOf("Destello", "Barrera"),
            coreItems = listOf("Hoja del Rey Arruinado", "Cañón de Fuego Rápido", "Filo del Infinito", "Arcoescudo Inmortal"),
            skillOrder = "Max W > Q > E",
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "caitlyn",
            name = "Caitlyn",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 51.9,
            pickRate = 16.0,
            banRate = 11.4,
            damageType = DamageType.PHYSICAL,
            summary = "El mayor rango básico de tirador en el juego, control de trampas y cabezazos devastadores.",
            advantageAgainst = listOf("Vayne", "Kai'Sa", "Short-range ADCs"),
            counteredBy = listOf("Yasuo", "Nautilus", "Vi", "Zed"),
            synergies = listOf("Lux", "Morgana", "Thresh", "Pyke"),
            tacticalAdvice = "Caitlyn: Utiliza tu rango para castigar bajo torre y colocar trampas en los cuellos de botella.",
            recommendedRunes = "Primer Golpe",
            recommendedSpells = listOf("Destello", "Barrera"),
            coreItems = listOf("Filo del Infinito", "Cañón de Fuego Rápido", "Recaudadora", "Recordatorio Mortal"),
            skillOrder = "Max Q > W > E",
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "vi",
            name = "Vi",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "S",
            winrate = 52.5,
            pickRate = 12.4,
            banRate = 8.9,
            damageType = DamageType.PHYSICAL,
            summary = "Iniciación bloqueada imparable con su R y trituración de armadura porcentual.",
            advantageAgainst = listOf("Caitlyn", "Jinx", "Twitch", "Lux"),
            counteredBy = listOf("Morgana", "Janna", "Jax", "Lulu"),
            synergies = listOf("Yasuo", "Ahri", "Orianna", "Miss Fortune"),
            tacticalAdvice = "Vi: Fija al carry más vulnerable con tu R imparable para aislarlo de su equipo.",
            recommendedRunes = "Conquistador",
            recommendedSpells = listOf("Destello", "Castigo"),
            coreItems = listOf("Fuerza de la Trinidad", "Danza de la Muerte", "Guantelete de Sterak", "Malla de Espinas"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "rell",
            name = "Rell",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "S",
            winrate = 52.8,
            pickRate = 7.6,
            banRate = 6.4,
            damageType = DamageType.MAGIC,
            summary = "Iniciación en combo devastador que rompe escudos enemigos y agrupa a todos los rivales.",
            advantageAgainst = listOf("Sett", "Tahm Kench", "Shen"),
            counteredBy = listOf("Janna", "Morgana", "Thresh", "Vayne"),
            synergies = listOf("Samira", "Miss Fortune", "Katarina"),
            tacticalAdvice = "Rell: Rompe los escudos masivos de Sett o Morgana antes de activar la definitiva magnética.",
            recommendedRunes = "Reverberación",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Promesa del Caballero", "Convergencia de Zeke", "Protector Pétreo"),
            skillOrder = "Max W > E > Q",
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "ahri",
            name = "Ahri",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 52.2,
            pickRate = 14.3,
            banRate = 7.5,
            damageType = DamageType.MAGIC,
            summary = "Alta movilidad con 3 cargas de R y Encanto (E) que asegura eliminaciones rápidas.",
            advantageAgainst = listOf("Lux", "Kassadin", "Twisted Fate"),
            counteredBy = listOf("Yasuo", "Zed", "Sylas"),
            synergies = listOf("Vi", "Lee Sin", "Jarvan IV"),
            tacticalAdvice = "Ahri: Flanquea en peleas grupales para encantar al carry enemigo fuera de posición.",
            recommendedRunes = "Electrocutar",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Eco de Luden", "Orbe del Infinito", "Sombrero Mortal de Rabadon"),
            skillOrder = "Max Q > W > E",
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "zed",
            name = "Zed",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "S",
            winrate = 51.5,
            pickRate = 15.8,
            banRate = 26.3,
            damageType = DamageType.PHYSICAL,
            summary = "Asesino sombrío con burst letal mediante Marca de la Muerte (R) y gran capacidad de escape.",
            advantageAgainst = listOf("Lux", "Veigar", "Ashe", "Jinx"),
            counteredBy = listOf("Lulu", "Zhonya", "Malphite", "Sett"),
            synergies = listOf("Nautilus", "Vi", "Leona"),
            tacticalAdvice = "Zed: Espera a que el equipo rival gaste sus habilidades defensivas antes de entrar.",
            recommendedRunes = "Electrocutar",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Filo Fantasma de Youmuu", "Colmillo de Serpiente", "Rencor de Serylda"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = false
        ),
        Champion(
            id = "kaisa",
            name = "Kai'Sa",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S+",
            winrate = 53.6,
            pickRate = 18.9,
            banRate = 15.2,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Evolución de habilidades (Q, W, E), daño híbrido masivo y reposicionamiento con escudo con Instinto Asesino (R).",
            advantageAgainst = listOf("Ezreal", "Jhin", "Sivir"),
            counteredBy = listOf("Caitlyn", "Draven", "Nautilus"),
            synergies = listOf("Nautilus", "Thresh", "Leona", "Alistar"),
            tacticalAdvice = "Kai'Sa: Salta con tu R a objetivos marcados por CC aliado para rematarlos con lluvia de Icathia.",
            recommendedRunes = "Cadencia Letal",
            recommendedSpells = listOf("Destello", "Curación"),
            coreItems = listOf("Verdugo de Krakens", "Bailarín Espectral", "Filo del Infinito", "Diente de Nashor"),
            skillOrder = "Max Q > E > W",
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "yunara",
            name = "Yunara",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "S",
            winrate = 52.7,
            pickRate = 9.1,
            banRate = 8.0,
            damageType = DamageType.MAGIC,
            summary = "Control de oleadas místico, escudos cinéticos y amplificación de daño de área.",
            advantageAgainst = listOf("Zed", "Katarina", "Vi"),
            counteredBy = listOf("Yasuo", "Fiora"),
            synergies = listOf("Viego", "Vayne", "Cho'Gath"),
            tacticalAdvice = "Yunara: Mantén distancia y encadena tus zonas mágicas cuando el rival intente iniciar.",
            recommendedRunes = "Cometa Arcano",
            recommendedSpells = listOf("Destello", "Barrera"),
            coreItems = listOf("Vara de las Edades", "Corona de la Reina", "Orbe del Infinito"),
            skillOrder = "Max Q > W > E",
            isRanged = true,
            isFrontline = false
        )
    )

    fun getChampionById(id: String): Champion? = champions.find { it.id.equals(id, ignoreCase = true) }

    fun getChampionsByRole(role: LaneRole): List<Champion> {
        return champions.filter { it.primaryRole == role || it.secondaryRoles.contains(role) }
    }

    fun analyzeDraft(
        myRole: LaneRole,
        allies: List<Champion>,
        enemies: List<Champion>
    ): DraftAnalysisResult {
        val totalAllies = allies.size
        var physCount = 0
        var magicCount = 0
        var hybridCount = 0
        var frontlineCount = 0

        allies.forEach { champ ->
            when (champ.damageType) {
                DamageType.PHYSICAL -> physCount++
                DamageType.MAGIC -> magicCount++
                DamageType.TRUE_HYBRID -> {
                    physCount++
                    magicCount++
                    hybridCount++
                }
            }
            if (champ.isFrontline) frontlineCount++
        }

        val totalScore = (physCount + magicCount).coerceAtLeast(1)
        val physPercent = if (totalAllies == 0) 50 else ((physCount.toDouble() / totalScore) * 100).toInt()
        val magicPercent = 100 - physPercent
        val truePercent = if (hybridCount > 0) 15 else 0

        val frontlineStatus = when {
            frontlineCount >= 2 -> "Sólida: Primera línea y absorción asegurada"
            frontlineCount == 1 -> "Moderada: Requiere posicionamiento defensivo"
            else -> "⚠️ Falta Vanguardia: Se recomienda elegir tanque o luchador con aguante"
        }

        // Identify enemy threats and direct lane rival
        val enemyRival = enemies.firstOrNull { it.primaryRole == myRole } ?: enemies.firstOrNull()
        val enemyRivalName = enemyRival?.name ?: "el rival directo"

        val directWarning = if (enemyRival != null) {
            "Mejor contra tu rival directo ($enemyRivalName):"
        } else null

        // Calculate recommendations for the user's selected role
        val roleCandidates = champions.filter { it.primaryRole == myRole || it.secondaryRoles.contains(myRole) }
            .filter { champ -> allies.none { it.id == champ.id } && enemies.none { it.id == champ.id } }

        val recommendations = roleCandidates.map { champ ->
            var score = champ.winrate
            val countersEnemy = enemies.count { enemy -> champ.advantageAgainst.contains(enemy.name) }
            val synergizesAlly = allies.count { ally -> champ.synergies.contains(ally.name) }
            
            score += countersEnemy * 3.5 + synergizesAlly * 2.0
            if (champ.tier == "S+") score += 2.0

            val advantageMatches = enemies.filter { champ.advantageAgainst.contains(it.name) }.map { it.name }
            val advantageBadge = if (advantageMatches.isNotEmpty()) {
                "Ventaja vs ${advantageMatches.joinToString("/")}"
            } else {
                "Tier ${champ.tier} Meta"
            }

            val synergyNames = allies.filter { champ.synergies.contains(it.name) }.map { it.name }
            val synergyText = if (synergyNames.isNotEmpty()) "Excelente sinergia con ${synergyNames.joinToString(", ")}. " else ""

            val reason = "Su kit contrarresta directamente a ${if (advantageMatches.isNotEmpty()) advantageMatches.joinToString(", ") else "la composición rival"}. $synergyText${champ.summary}"

            DraftRecommendation(
                champion = champ,
                estimatedWinrate = (champ.winrate + (countersEnemy * 2.8)).coerceIn(48.0, 72.5),
                advantageBadge = advantageBadge,
                tacticalReason = reason,
                runes = champ.recommendedRunes
            )
        }.sortedByDescending { it.estimatedWinrate }
        .take(5)

        val bestDirect = recommendations.firstOrNull()?.let {
            "${it.champion.name} (${it.champion.tier}): El kit de ${it.champion.name} contrarresta el estilo de juego de $enemyRivalName neutralizando sus mayores ventajas tácticas."
        }

        return DraftAnalysisResult(
            physicalDamagePercent = physPercent,
            magicDamagePercent = magicPercent,
            trueDamagePercent = truePercent,
            frontlineStatus = frontlineStatus,
            directMatchupWarning = directWarning,
            directCounterBestPick = bestDirect,
            recommendations = recommendations
        )
    }
}
