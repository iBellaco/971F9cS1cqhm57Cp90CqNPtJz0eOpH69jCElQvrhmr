import re

with open("app/src/main/java/com/example/util/ChampionRoleAdapter.kt", "r") as f:
    content = f.read()

# Buscamos desde OPCIÓN 1 hasta return listOf(opt1, opt2, opt3, opt4)
start_str = "        // =========================================================================\n        // OPCIÓN 1: META CORE ESTÁNDAR"
end_str = "return listOf(opt1, opt2, opt3, opt4)"

if start_str in content and end_str in content:
    start_idx = content.find(start_str)
    end_idx = content.find(end_str) + len(end_str)
    
    replacement = """        // =========================================================================
        // OPCIÓN 1: META CORE ESTÁNDAR
        // =========================================================================
        val opt1 = ChampionBuildOption(
            optionNumber = 1,
            title = "Opción 1: Core Meta Estándar",
            subtitle = "",
            source = "",
            badge = "ESTÁNDAR",
            tacticalReason = "Build estándar más consistente para este campeón en el meta actual. Ofrece un equilibrio perfecto para la mayoría de las composiciones y proporciona el pico de poder óptimo para las peleas por el primer Dragón.",
            items = defaultBuild8,
            bootBase = defaultBootBase,
            bootUpgrade = defaultBootUpgrade,
            runes = opt1Runes,
            spells = resolvedSpells1,
            spellsIcons = resolvedSpellsIcons1
        )

        // =========================================================================
        // OPCIÓN 2, 3 y 4: COACHING ADAPTATIVO
        // =========================================================================
        val opt2Title: String
        val opt2Reason: String
        val opt2Items: List<String>
        val opt2Badge: String

        val opt3Title: String
        val opt3Reason: String
        val opt3Items: List<String>
        val opt3Badge: String

        val opt4Title: String
        val opt4Reason: String
        val opt4Items: List<String>
        val opt4Badge: String
        
        when {
            isSupport && !isSpecialDamageSupport -> {
                // Soportes de Utilidad/Pokeo
                opt2Title = "Opción 2: Pokeo & Desgaste Constante"
                opt2Reason = "Maximiza la recuperación de maná y reducción de enfriamiento para hostigar sin parar en fase de líneas. Ideal para dominar el carril y forzar el recall del ADC enemigo."
                opt2Badge = "POKEO"
                opt2Items = listOf("Hoz espectral", "Eco armónico", "Mandato imperial", "Bastón de aguas fluidas", "Tridente de oceánida", "Incensario ardiente", "Corona de la Reina Fragmentada", "Sombrero mortal de Rabadon")

                opt3Title = "Opción 3: Supervivencia & Peel al Carry"
                opt3Reason = "Build 100% enfocada en mantener a tu ADC con vida. Úsala cuando el equipo enemigo tenga asesinos que hagan focus o mucho daño en ráfaga (Ej. Zed, Kha'Zix, Akali)."
                opt3Badge = "PEEL / ANTI-DAÑO"
                opt3Items = listOf("Hoz espectral", "Relicario de los Solari de Hierro", "Bendición de Mikael", "Redención", "Convergencia de Zeke", "Protector pétreo de gárgola", "Juramento del protector", "Corazón de hielo")
                
                opt4Title = "Opción 4: Escalado Mágico Completo"
                opt4Reason = "Para partidas que se alargan demasiado. Sacrificas algo de utilidad temprana por daño mágico y curaciones masivas que decidirán peleas de equipo en late game."
                opt4Badge = "LATE GAME"
                opt4Items = listOf("Hoz espectral", "Eco de Luden", "Sombrero mortal de Rabadon", "Bastón del vacío", "Orbe infinito", "Báculo del arcángel", "Impulso cósmico", "Despertar del robaalmas")
            }
            isAp && !isTank && !isSupport -> {
                // Magos Mid/Top/Sup de Daño
                opt2Title = "Opción 2: Burst Letal / One-Shot"
                opt2Reason = "Prioriza penetración mágica plana y AP bruto para desintegrar a los campeones frágiles del enemigo con un solo combo."
                opt2Badge = "DELETEO"
                opt2Items = listOf("Eco de Luden", "Orbe infinito", "Sombrero mortal de Rabadon", "Bastón del vacío", "Impulso cósmico", "Llama sombría", "Despertar del robaalmas", "Antorcha de fuego negro")

                opt3Title = "Opción 3: Desgaste Prolongado (Anti-Tanques)"
                opt3Reason = "Equípate para quemar a los tanques. El daño porcentual sostenido destrozará composiciones con mucha Vida (HP). Escencial contra Dr. Mundo, Sion o Sett."
                opt3Badge = "ANTI-TANQUE"
                opt3Items = listOf("Tormento de Liandry", "Hacedor de grietas", "Bastón del vacío", "Sombrero mortal de Rabadon", "Cetro de cristal de Rylai", "Morellonomicón", "Impulso cósmico", "Tormenta de Luden")

                opt4Title = "Opción 4: Mago de Batalla / Supervivencia"
                opt4Reason = "Cuando eres el focus principal del enemigo. Ofrece escudos mágicos y estasis para sobrevivir a la ráfaga de los asesinos sin perder tu impacto en la pelea."
                opt4Badge = "ANTI-BURST"
                opt4Items = listOf("Báculo del arcángel", "Corona de la Reina Fragmentada", "Torreón de Kaenic", "Reloj de arena de Zhonya", "Sombrero mortal de Rabadon", "Bastón del vacío", "Morellonomicón", "Velo de alma en pena")
            }
            isTank && !isSupport -> {
                // Tanques Top/Jg
                opt2Title = "Opción 2: Inmortalidad & Supervivencia Extrema"
                opt2Reason = "Conviértete en una muralla de mitigación masiva. Esta configuración bloquea el daño híbrido de hyper-carrys y te permite hacer el engage inicial absorbiendo habilidades definitivas."
                opt2Badge = "ANTI-DAÑO"
                opt2Items = listOf("Corazón de acero", "Égida de fuego solar", "Guardia gemela de amaranto", "Corazón de hielo", "Fuerza de la naturaleza", "Protector pétreo de gárgola", "Placa del hombre muerto", "Presagio de Randuin")

                opt3Title = "Opción 3: Utilidad Pesada & Control (CDR)"
                opt3Reason = "Diseñada para aplicar Control de Masas continuamente y ser la molestia táctica del equipo. Excelente enfriamiento (CDR) para lanzar tus habilidades de inmovilización repetidas veces."
                opt3Badge = "CONTROL / UTILIDAD"
                opt3Items = listOf("Guantelete del hijo del hielo", "Llegada del invierno", "Malla de espinas", "Máscara abisal", "Corazón de hielo", "Juramento del protector", "Gloria encantada", "Cota de espinas")

                opt4Title = "Opción 4: Coloso Ofensivo (Daño Bruiser)"
                opt4Reason = "Para partidas en las que tu equipo no tiene suficiente daño sostenido y estás ganando tu línea. Transforma tu tanque en una amenaza duelista capaz de deletear squishies."
                opt4Badge = "BRUISER"
                opt4Items = listOf("Cuchilla negra", "Égida de fuego solar", "Corona abrasadora", "Guantelete de Sterak", "Corazón de acero", "Cielo desgarrado", "Malla de espinas", "Fuerza de la Trinidad")
            }
            isMarksman && !isSupport -> {
                // ADCs
                opt2Title = "Opción 2: Hiper-Carry DPS (Crítico al máximo)"
                opt2Reason = "La senda del hyper-carry clásico. Ofrece daño crítico implacable y limpieza de área rápida para derretir equipos en peleas grupales."
                opt2Badge = "HYPER-CARRY"
                opt2Items = listOf("Borde infinito", "Cañón de fuego rápido", "El coleccionista", "Saludos de Dominik", "Sanguinario", "Bailarina fantasma", "El huracán de Runaan", "Ángel custodio")

                opt3Title = "Opción 3: Caza-Tanques & Perforación"
                opt3Reason = "Destruye a los colosos enemigos mediante daño físico porcentual a la Vida y penetración de armadura pesada. Ninguna armadura aguantará tus básicos."
                opt3Badge = "ANTI-TANQUE"
                opt3Items = listOf("Espada del Rey Arruinado", "Saludos de Dominik", "Cuchilla negra", "Recordatorio mortal", "Al filo de la cordura", "Terminus", "Sanguinario", "Ángel custodio")

                opt4Title = "Opción 4: Kiteo y Auto-Supervivencia"
                opt4Reason = "Ideal contra composiciones de engage o Asesinos con movilidad (Ej. Yone, Akali, Lee Sin). Prioriza robo de vida, escudos y evasión para asegurar tu posición táctica."
                opt4Badge = "SUPERVIVENCIA"
                opt4Items = listOf("Sanguinario", "Arcoescudo inmortal", "Al filo de la cordura", "Fajín de mercurio", "Recordatorio mortal", "Ángel custodio", "Filo de la noche", "La danza de la muerte")
            }
            else -> {
                // Asesinos y Peleadores / Bruisers AD
                opt2Title = "Opción 2: Burst Letal / Deleteo Rápido"
                opt2Reason = "Build enfocada en maximizar tu letalidad al máximo. Entra a la pelea, asesina al ADC o Mago frágil en 0.5 segundos y sal ileso."
                opt2Badge = "DELETEO"
                opt2Items = listOf("El cuchillo fantasma de Youmuu", "Draktharr", "El coleccionista", "Arco axial", "Filo de la noche", "El rencor de Serylda", "Cielo desgarrado", "Ángel custodio")

                opt3Title = "Opción 3: Duelista & Split-Push Constante"
                opt3Reason = "Diseñada para dominar los duelos 1 contra 1 en los carriles laterales. Tira torres, atrae la presión enemiga y asegura victorias en combates aislados."
                opt3Badge = "DUELISTA 1v1"
                opt3Items = listOf("Fuerza de la Trinidad", "Espada del Rey Arruinado", "Cuchilla negra", "Rompecascos", "Guantelete de Sterak", "La danza de la muerte", "Cielo desgarrado", "Hidra voraz")

                opt4Title = "Opción 4: Frente de Batalla Híbrido (Resistencia)"
                opt4Reason = "Para batallas frontales largas donde recibes daño mixto. Incorpora mitigación y regeneración (Bruiser), manteniéndote como un peligro físico duradero en la teamfight."
                opt4Badge = "FRONTLINE"
                opt4Items = listOf("Cuchilla negra", "La danza de la muerte", "Fauces de Malmortius", "Guantelete de Sterak", "Cielo desgarrado", "Ángel custodio", "Apariencia espiritual", "Égida de fuego solar")
            }
        }

        val opt2BootBase = getBaseTier2Boot(opt2Items, champ.damageType, isTank, champ.isRanged, role)
        val opt3BootBase = getBaseTier2Boot(opt3Items, champ.damageType, isTank, champ.isRanged, role)
        val opt4BootBase = getBaseTier2Boot(opt4Items, champ.damageType, isTank, champ.isRanged, role)

        val opt2 = ChampionBuildOption(
            optionNumber = 2,
            title = opt2Title,
            subtitle = "",
            source = "",
            badge = opt2Badge,
            tacticalReason = opt2Reason,
            items = opt2Items,
            bootBase = opt2BootBase,
            bootUpgrade = getTier3BootUpgrade(opt2BootBase),
            runes = opt1Runes,
            spells = resolvedSpells1,
            spellsIcons = resolvedSpellsIcons1
        )

        val opt3 = ChampionBuildOption(
            optionNumber = 3,
            title = opt3Title,
            subtitle = "",
            source = "",
            badge = opt3Badge,
            tacticalReason = opt3Reason,
            items = opt3Items,
            bootBase = opt3BootBase,
            bootUpgrade = getTier3BootUpgrade(opt3BootBase),
            runes = opt1Runes,
            spells = resolvedSpells1,
            spellsIcons = resolvedSpellsIcons1
        )

        val opt4 = ChampionBuildOption(
            optionNumber = 4,
            title = opt4Title,
            subtitle = "",
            source = "",
            badge = opt4Badge,
            tacticalReason = opt4Reason,
            items = opt4Items,
            bootBase = opt4BootBase,
            bootUpgrade = getTier3BootUpgrade(opt4BootBase),
            runes = opt1Runes,
            spells = resolvedSpells1,
            spellsIcons = resolvedSpellsIcons1
        )

        return listOf(opt1, opt2, opt3, opt4)"""
    new_content = content[:start_idx] + replacement + content[end_idx:]
    with open("app/src/main/java/com/example/util/ChampionRoleAdapter.kt", "w") as f:
        f.write(new_content)
    print("Replace success")
else:
    print("Strings not found")
