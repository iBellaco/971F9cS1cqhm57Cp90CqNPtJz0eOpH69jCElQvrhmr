package com.example.data

import com.example.model.WildRiftItem

data class SituationalItemInfo(
    val name: String,
    val iconUrl: String,
    val categoryName: String,
    val purpose: String,
    val bestAgainst: List<String>,
    val keyEffect: String,
    val recommendationTip: String
)

object SituationalItemAdvisor {

    private val adviceMap = mapOf(
        "Cota de Espinas" to SituationalItemInfo(
            name = "Cota de Espinas",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png",
            categoryName = "Anti-Curación & Armadura",
            purpose = "Mitiga curaciones y robo de vida de atacantes físicos continuos mientras refleja daño mágico.",
            bestAgainst = listOf("Aatrox", "Warwick", "Maestro Yi", "Tryndamere", "Jinx", "Yone", "Olaf", "Irelia"),
            keyEffect = "Aplica 40% de Heridas Graves al recibir ataques de los rivales e inmovilizarlos.",
            recommendationTip = "Cómpralo si el equipo rival tiene 2 o más campeones basados en robo de vida o duelistas AD."
        ),
        "Recordatorio Mortal" to SituationalItemInfo(
            name = "Recordatorio Mortal",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png",
            categoryName = "Anti-Curación & Penetración AD",
            purpose = "Destruye tanques y anula la regeneración masiva de curadores enemigos para tiradores y asesinos.",
            bestAgainst = listOf("Soraka", "Yuumi", "Dr. Mundo", "Vladimir", "Swain", "Sion", "Aatrox", "Volibear"),
            keyEffect = "Otorga 30% de Penetración de Armadura y 40% de Heridas Graves con golpes físicos.",
            recommendationTip = "Prioridad absoluta en ADCs si el soporte rival es de sanación (Soraka/Yuumi) o hay un coloso imparable."
        ),
        "Morellonomicón" to SituationalItemInfo(
            name = "Morellonomicón",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png",
            categoryName = "Anti-Curación AP",
            purpose = "Aplica reducción de sanación a múltiples enemigos simultáneamente con daño mágico en área.",
            bestAgainst = listOf("Vladimir", "Soraka", "Swain", "Warwick", "Yuumi", "Samira", "Ekko"),
            keyEffect = "Insignia Maldita: Infligir daño mágico aplica 40% de Heridas Graves por 3 segundos.",
            recommendationTip = "Imprescindible para magos de poke o daño en área (Ziggs, Brand, Lux, Morgana) frente a healers."
        ),
        "Colmillo de Serpiente" to SituationalItemInfo(
            name = "Colmillo de Serpiente",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6695.png",
            categoryName = "Anti-Escudos (Letalidad)",
            purpose = "Destruye y reduce drásticamente la potencia de los escudos defensivos enemigos.",
            bestAgainst = listOf("Sett", "Karma", "Lulu", "Shen", "Janna", "Braum", "Sion", "Lux", "Riven"),
            keyEffect = "Destructor de Escudos: Reduce la ganancia de escudos en 50% (35% a distancia) y drena escudos activos.",
            recommendationTip = "Compra obligatoria contra composiciones 'Proteger al Carry' con soportes de escudos dobles o Sterak."
        ),
        "Presagio de Randuin" to SituationalItemInfo(
            name = "Presagio de Randuin",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png",
            categoryName = "Anti-Crítico & Velocidad de Ataque",
            purpose = "Reduce el impacto de los impactos críticos y drena la velocidad de ataque enemiga.",
            bestAgainst = listOf("Yasuo", "Yone", "Jinx", "Caitlyn", "Tristana", "Tryndamere", "Lucian"),
            keyEffect = "Humildad: Reduce el daño crítico recibido en 16% y reduce la velocidad de ataque del agresor.",
            recommendationTip = "El mejor objeto defensivo frente a composiciones con tiradores hiper-carry o hermanos de viento."
        ),
        "Fuerza de la Naturaleza" to SituationalItemInfo(
            name = "Fuerza de la Naturaleza",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4401.png",
            categoryName = "Anti-Daño Mágico Continuo",
            purpose = "Otorga la máxima resistencia mágica y reducción porcentual frente a magos de daño en el tiempo.",
            bestAgainst = listOf("Brand", "Swain", "Aurelion Sol", "Teemo", "Gwen", "Katarina", "Kassadin", "Lillia"),
            keyEffect = "Absorción: Acumula stacks al recibir daño mágico hasta otorgar 25% de reducción de daño mágico.",
            recommendationTip = "Cómpralo cuando los rivales tengan 2 o más fuentes de daño AP continuo o quemaduras."
        ),
        "Reloj de Arena de Zhonya" to SituationalItemInfo(
            name = "Reloj de Arena de Zhonya",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png",
            categoryName = "Inmunidad / Estasis Activa",
            purpose = "Permite esquivar habilidades definitivas fatales y combos explosivos de eliminación rápida.",
            bestAgainst = listOf("Zed", "Syndra", "Fizz", "Kayn", "Katarina", "Talon", "Malphite", "Nocturne"),
            keyEffect = "Estasis: Te vuelve invulnerable e inalcanzable durante 2.5 segundos (no puedes moverte ni atacar).",
            recommendationTip = "Usa el encanto de Zhonya justo cuando el asesino lance su definitiva sobre ti para anularla por completo."
        ),
        "Danza de la Muerte" to SituationalItemInfo(
            name = "Danza de la Muerte",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png",
            categoryName = "Anti-Burst AD & Supervivencia",
            purpose = "Convierte el daño de ráfaga físico en un sangrado retrasado y cura un porcentaje de vida en derribos.",
            bestAgainst = listOf("Zed", "Kha'Zix", "Rengar", "Talon", "Pantheon", "Jayce", "Draven"),
            keyEffect = "Ignorar Dolor: 35% del daño físico recibido se difiere en 3 segundos; cura 12% de vida máxima al matar.",
            recommendationTip = "Clave para luchadores y asesinos cuando necesites entrar a la pelea sin ser evaporado al instante."
        ),
        "Rencor de Serylda" to SituationalItemInfo(
            name = "Rencor de Serylda",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png",
            categoryName = "Penetración & Ralentización",
            purpose = "Otorga penetración de armadura porcentual y hace que todas las habilidades dañinas ralenticen.",
            bestAgainst = listOf("Ornn", "Malphite", "Nautilus", "Garen", "Nasus", "Darius", "K'Sante"),
            keyEffect = "Frío Intenso: Las habilidades dañinas ralentizan un 30% a los enemigos durante 1 segundo.",
            recommendationTip = "Excelente para tiradores de habilidades (Ezreal, Varus, Jhin) o asesinos para kitear a tanques lentos."
        ),
        "Ángel Guardián" to SituationalItemInfo(
            name = "Ángel Guardián",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png",
            categoryName = "Resurrección en Combate",
            purpose = "Otorga una segunda oportunidad de vida en las peleas de equipo decisivas de juego tardío.",
            bestAgainst = listOf("Composiciones de dive agresivo", "Asesinos con combos all-in (Diana, Akali, Zed, Kayn)"),
            keyEffect = "Renacimiento: Al recibir daño letal, resucitas tras 4s con 50% de vida base y 30% de maná.",
            recommendationTip = "Armar como 4to o 5to objeto en Carries para poder jugar agresivo sin temor a ser cazado antes del Barón."
        ),
        "Fauces de Malmortius" to SituationalItemInfo(
            name = "Fauces de Malmortius",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3156.png",
            categoryName = "Escudo Salvavidas Anti-Mágico",
            purpose = "Genera un escudo gigantesco contra daño mágico al bajar de 35% de vida para resistir ejecuciones.",
            bestAgainst = listOf("Evelynn", "Akali", "Syndra", "Veigar", "Fizz", "Ekko", "Kassadin"),
            keyEffect = "Línea de Vida: Otorga escudo mágico equivalente a 200 + 20% de vida máxima y omnivampirismo.",
            recommendationTip = "La mejor alternativa frente a asesinos AP para campeones basados en daño de ataque (AD)."
        ),
        "Báculo del Vacío" to SituationalItemInfo(
            name = "Báculo del Vacío",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png",
            categoryName = "Penetración Mágica Porcentual",
            purpose = "Ignora un porcentaje masivo de la resistencia mágica enemiga para que tus hechizos no pierdan daño.",
            bestAgainst = listOf("Galio", "Mundo", "Ornn", "Malphite", "Shen", "Alistar", "Braum"),
            keyEffect = "Disolución: Otorga 45% de Penetración Mágica porcentual.",
            recommendationTip = "Obligatorio como 3er o 4to objeto para cualquier mago si el equipo enemigo construye Resistencia Mágica."
        ),
        "Coraza del Muerto" to SituationalItemInfo(
            name = "Coraza del Muerto",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3742.png",
            categoryName = "Movilidad & Iniciación",
            purpose = "Otorga velocidad de rotación rápida por el mapa y ralentiza al primer objetivo golpeado.",
            bestAgainst = listOf("Composiciones de poke móvil", "Tiradores sin dash (Jhin, Ashe, Miss Fortune)"),
            keyEffect = "Naufragador: Aumenta velocidad de movimiento hasta +50 y descarga daño adicional con ralentización.",
            recommendationTip = "Ideal para tanques e iniciadores que necesitan flanquear o cazar rivales desposicionados."
        ),
        "Protector Pétreo" to SituationalItemInfo(
            name = "Protector Pétreo",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3193.png",
            categoryName = "Megashield de Equipo (Activa)",
            purpose = "Multiplica drásticamente la resistencia para absorber el foco de daño de 5 enemigos en teamfights.",
            bestAgainst = listOf("Teamfights masivas 5v5", "Iniciaciones directas contra composiciones de alto daño combinado"),
            keyEffect = "Pétreo: Otorga un escudo del 30% de tu vida máxima (aumentado a 100% si hay 3+ enemigos cerca).",
            recommendationTip = "Actívalo justo después de entrar con tu iniciación principal para sobrevivir al contraataque rival."
        ),
        "Corazón de Hielo" to SituationalItemInfo(
            name = "Corazón de Hielo",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3110.png",
            categoryName = "Aura Reductora de Ataque",
            purpose = "Reduce de forma pasiva continua la velocidad de ataque de todos los enemigos en un radio cercano.",
            bestAgainst = listOf("Maestro Yi", "Jinx", "Tryndamere", "Tristana", "Maestro Yi", "Vayne", "Kai'Sa"),
            keyEffect = "Aura Helada: Reduce la velocidad de ataque de los enemigos cercanos en un 20%.",
            recommendationTip = "Fantástico en tanques de primera línea cuando los rivales dependen de acumular autoataques."
        ),
        "Velo de la Banshee" to SituationalItemInfo(
            name = "Velo de la Banshee",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3102.png",
            categoryName = "Escudo Anti-Hechizos AP",
            purpose = "Bloquea automáticamente la próxima habilidad enemiga para evitar ser cazado o estuneado.",
            bestAgainst = listOf("Blitzcrank", "Malphite", "Nautilus", "Thresh", "Ahri", "Lux", "Morgana"),
            keyEffect = "Anular: Otorga un escudo de hechizos que bloquea la siguiente habilidad hostil (recarga en 40s).",
            recommendationTip = "Vital para magos inmóviles que no pueden permitirse recibir un gancho o CC antes de una pelea."
        ),
        "Filo de la Noche" to SituationalItemInfo(
            name = "Filo de la Noche",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png",
            categoryName = "Escudo Anti-Hechizos AD",
            purpose = "Otorga letalidad, vida y un escudo de hechizos para asegurar que los asesinos culminen su combo.",
            bestAgainst = listOf("Lulu", "Vayne", "Syndra", "Vex", "Poppy", "Gragas"),
            keyEffect = "Anular: Escudo de hechizos que bloquea la siguiente habilidad enemiga.",
            recommendationTip = "Permite a los asesinos saltar sobre el Carry sin ser interrumpidos por habilidades de desenganche."
        ),
        "Protección Gemela de Amaranth" to SituationalItemInfo(
            name = "Protección Gemela de Amaranth",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389236_amaranths-twinguard.webp",
            categoryName = "Resistencia Híbrida & Tenacidad",
            purpose = "Aumenta un 30% la armadura y resistencia mágica en combate prolongado, otorgando además tenacidad masiva.",
            bestAgainst = listOf("Composiciones de daño mixto (AD + AP)", "Peleas grupales largas 5v5"),
            keyEffect = "Resistencia: A cargas máximas otorga +30% Armadura, +30% Resistencia Mágica y +20% Tenacidad.",
            recommendationTip = "El mejor objeto defensivo de late-game para tanques e iniciadores frente a daño variado."
        ),
        "Rookern Kaénico" to SituationalItemInfo(
            name = "Rookern Kaénico",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389228_kaenic-rookern.webp",
            categoryName = "Anti-Mágico Puro & Escudo AP",
            purpose = "Genera un escudo de absorción mágica masivo fuera de combate que mitiga por completo el daño de ráfaga AP.",
            bestAgainst = listOf("Syndra", "Veigar", "Zoe", "Evelynn", "Kassadin", "Lux", "Brand"),
            keyEffect = "Ruina de Magos: Tras 12s sin daño mágico, otorga un escudo mágico del 14% de tu vida máxima.",
            recommendationTip = "Prioridad absoluta contra composiciones de doble mago o hipercarry mágico."
        ),
        "Manto de la Duodécima Hora" to SituationalItemInfo(
            name = "Manto de la Duodécima Hora",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389204_mantle-of-the-twelfth-hour.webp",
            categoryName = "Supervivencia Crítica & Desenganche",
            purpose = "Otorga una inyección masiva de vida adicional y velocidad al caer por debajo del 35% de vida.",
            bestAgainst = listOf("Asesinos de ejecución rápida", "Peleas cerradas al límite de vida"),
            keyEffect = "Línea de Vida: Otorga hasta 45% de vida adicional y 50% de resistencia a ralentizaciones al bajar del 35% HP.",
            recommendationTip = "Perfecto para colosos e iniciadores que se sumergen en la línea trasera enemiga."
        ),
        "Corona Abrasadora" to SituationalItemInfo(
            name = "Corona Abrasadora",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389203_searing-crown.webp",
            categoryName = "Quemadura Porcentual para Tanques",
            purpose = "Quema a los enemigos por porcentaje de su vida máxima con cada ataque y habilidad.",
            bestAgainst = listOf("Sion", "Dr. Mundo", "Cho'Gath", "Ornn", "Heartsteel Users"),
            keyEffect = "Toque Ardiente: Inflige 1.4% de la vida máxima del objetivo como daño mágico por segundo.",
            recommendationTip = "Excelente en tanques para derretir a otros colosos con mucha vida sin sacrificar defensas."
        ),
        "Tridente de Oceánida" to SituationalItemInfo(
            name = "Tridente de Oceánida",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388583_oceanids-trident.webp",
            categoryName = "Anti-Escudos para Magos (AP)",
            purpose = "Destruye y reduce los escudos enemigos al infligir daño mágico con habilidades de área o impacto individual.",
            bestAgainst = listOf("Karma", "Lulu", "Sett", "Shen", "Janna", "Lux", "Braum"),
            keyEffect = "Arma Letal: Reduce la potencia de los escudos enemigos hasta un 60% (45% en área).",
            recommendationTip = "Imprescindible para magos cuando el rival cuenta con soportes de escudos o Sterak/Arcoescudo."
        ),
        "Espada Sierra Quimopunk" to SituationalItemInfo(
            name = "Espada Sierra Quimopunk",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6609.png",
            categoryName = "Anti-Curación para Luchadores (AD)",
            purpose = "Otorga daño, vida y aceleración de habilidad mientras aplica reducción de curación continua.",
            bestAgainst = listOf("Aatrox", "Warwick", "Vladimir", "Soraka", "Dr. Mundo", "Olaf"),
            keyEffect = "Heridas Graves: Aplica 40% de reducción de curación a campeones enemigos al golpearlos con daño físico.",
            recommendationTip = "El objeto anti-sanación óptimo para luchadores que necesitan durabilidad y daño balanceado."
        ),
        "Cimitarra Mercurial" to SituationalItemInfo(
            name = "Cimitarra Mercurial",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783568239_3139_11zon.webp",
            categoryName = "Purificación de CC para Carries AD",
            purpose = "Elimina todo el control de masas inmediatamente y otorga tenacidad para reposicionarse.",
            bestAgainst = listOf("Veigar", "Skarner", "Warwick", "Ashe", "Leona", "Twisted Fate"),
            keyEffect = "Fajín de Mercurio: Limpia todo el CC activo y otorga 30% de tenacidad durante 1.5s.",
            recommendationTip = "Indispensable para tiradores cuando el rival tiene aturdimientos o supresiones directas."
        )
    )

    fun getAdvice(itemName: String): SituationalItemInfo {
        // Direct search or partial match
        val matched = adviceMap.entries.firstOrNull { 
            it.key.equals(itemName, ignoreCase = true) || 
            itemName.contains(it.key, ignoreCase = true) ||
            it.key.contains(itemName, ignoreCase = true)
        }
        
        val baseAdvice = matched?.value ?: run {
            val itemData = WildRiftItemsData.getItemByName(itemName)
            val effectText = if (!itemData?.passive.isNullOrBlank()) itemData!!.passive else "Objeto estratégico seleccionado para contrarrestar amenazas específicas de la composición rival."
            val statsText = if (!itemData?.stats.isNullOrBlank()) itemData!!.stats else "Mejora estadísticas y pasivas críticas para neutralizar las condiciones de victoria del rival."
            SituationalItemInfo(
                name = itemData?.name ?: itemName,
                iconUrl = itemData?.iconUrl ?: WildRiftItemsData.getItemIconByName(itemName),
                categoryName = itemData?.category ?: "Objeto Situacional Adaptativo",
                purpose = effectText,
                bestAgainst = listOf("Composiciones rivales especializadas", "Amenazas prioritarias de la partida"),
                keyEffect = statsText,
                recommendationTip = "Constrúyelo según el estado de la partida para contrarrestar el daño o mecánicas del enemigo."
            )
        }

        // Ensure iconUrl is populated from WildRiftItemsData if blank or placeholder
        val finalIcon = if (baseAdvice.iconUrl.isBlank() || baseAdvice.iconUrl.contains("1001.png")) {
            val repoIcon = WildRiftItemsData.getItemIconByName(baseAdvice.name)
            if (repoIcon.isNotBlank()) repoIcon else baseAdvice.iconUrl
        } else {
            baseAdvice.iconUrl
        }

        return baseAdvice.copy(iconUrl = finalIcon)
    }
}
