import re

with open("app/src/main/java/com/example/util/ChampionRoleAdapter.kt", "r") as f:
    content = f.read()

# Replace Option 1 definitions
content = content.replace(
    'subtitle = "WildRiftFire • BestBuildWR"',
    'subtitle = "Equilibrada & Confiable"'
).replace(
    'source = "WildRiftFire / BestBuildWR"',
    'source = "Core Meta"'
).replace(
    'tacticalReason = "Build estándar de referencia oficial con mayor tasa de victoria equilibrada en el meta actual de Wild Rift. Proporciona una transición suave entre el juego temprano y las peleas por el Dragón."',
    'tacticalReason = "Build estándar equilibrada en el meta actual. Proporciona una transición suave entre el juego temprano y las peleas por el Dragón."'
)

# Replace Option 2 definitions
content = content.replace(
    'subtitle = "WildRiftCore • High Elo Pro"',
    'subtitle = "Ofensiva Temprana"'
).replace(
    'source = "WildRiftCore"',
    'source = "Ofensiva"'
).replace(
    'tacticalReason = "Orientada a dominar los primeros 8 minutos y conseguir ventajas decisivas de oro. Maximiza daño de ráfaga y letalidad/AP crítico para eliminar al carry enemigo al instante."',
    'tacticalReason = "Orientada a dominar los primeros 8 minutos y conseguir ventajas decisivas de oro. Maximiza daño de ráfaga y letalidad/AP crítico para eliminar amenazas al instante."'
)


# Replace Option 3 Block completely using regex
opt3_regex = re.compile(
    r'(?s)// =========================================================================\s*// OPCIÓN 3: ANTI-TANQUES & COLOSOS \(Coach Challenger\)\s*// =========================================================================.*?val opt3 = ChampionBuildOption\([^)]+\)'
)

opt3_replacement = """// =========================================================================
        // OPCIÓN 3: BUILD CREATIVA 1 (Coach Challenger)
        // =========================================================================
        val opt3Title = when {
            isSupport && !isSpecialDamageSupport -> "Peel & Utilidad Pura"
            isAp -> "Artillería Mágica (Poke & Desgaste)"
            isMarksman -> "DPS Sostenido (Hypercarry)"
            isTank -> "Frontline Inamovible (Heavy Mitigation)"
            else -> "Cazador (One-Shot & Deletreo)"
        }
        val opt3Items: List<String> = when {
            isSupport && !isSpecialDamageSupport -> listOf(
                "Guadaña de la Niebla Negra", "Tridente de oceánida", "Mandato imperial",
                "Morellonomicón", "Incensario ardiente", "Bastón de aguas fluidas", "Redención", "Bendición de Mikael"
            )
            isAp -> listOf(
                "Tormento de Liandry", "Hacedor de grietas", "Bastón del vacío",
                "Sombrero mortal de Rabadon", "Cetro de cristal de Rylai", "Morellonomicón", "Impulso cósmico", "Reloj de arena de Zhonya"
            )
            isMarksman -> listOf(
                "Espada del Rey Arruinado", "Cuchilla negra", "Saludos de Dominik",
                "Recordatorio mortal", "Borde infinito", "Al filo de la cordura", "Sanguinario", "Ángel custodio"
            )
            isTank -> listOf(
                "Corazón de acero", "Égida de fuego solar", "Malla de espinas",
                "Corona abrasadora", "Fuerza de la naturaleza", "Presagio de Randuin", "Guardia gemela de amaranto", "Relicario de los Solari de Hierro"
            )
            else -> listOf(
                "Cuchilla negra", "Espada del Rey Arruinado", "El rencor de Serylda",
                "La danza de la muerte", "Recordatorio mortal", "Guantelete de Sterak", "Fuerza de la Trinidad", "Ángel custodio"
            )
        }
        val opt3Runes = listOf("Conquistador", "Verdugo de gigantes", "Impacto repentino", "Cazador titánico")
        val opt3BootBase = getBaseTier2Boot(opt3Items, champ.damageType, isTank, champ.isRanged, role)
        val opt3BootUpgrade = getTier3BootUpgrade(opt3BootBase)
        val opt3Spells = ensureUniqueSpells(
            when (role) {
                LaneRole.JUNGLE -> listOf("Castigo", "Destello")
                LaneRole.SUPPORT -> listOf("Destello", "Extenuación")
                else -> listOf("Destello", "Extenuación")
            },
            role
        )
        val opt3SpellsIcons = opt3Spells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }

        val opt3Reason = when {
            isSupport && !isSpecialDamageSupport -> "Diseñada para mantener vivo al carry con potentes escudos, curaciones y control de masas, anulando cualquier intento de diveo enemigo."
            isAp -> "Maximiza el daño de desgaste desde una distancia segura. Ideal contra composiciones enemigas que carecen de iniciación dura."
            isMarksman -> "Enfocada en destrozar a múltiples objetivos en peleas de equipo prolongadas. Convierte tu campeón en una máquina de DPS indetenible."
            isTank -> "Convierte a tu campeón en una muralla infranqueable. Combina regeneración masiva, vida máxima y resistencias puras para absorber todo el daño enemigo."
            else -> "Build de asesinato puro orientada a aislar objetivos frágiles (ADC/Mago) y eliminarlos instantáneamente del mapa antes de que puedan reaccionar."
        }

        val opt3 = ChampionBuildOption(
            optionNumber = 3,
            title = "Opción 3: $opt3Title",
            subtitle = "Coach Táctico Challenger",
            source = "Coach Táctico",
            badge = "SITUACIONAL",
            tacticalReason = opt3Reason,
            items = opt3Items,
            bootBase = opt3BootBase,
            bootUpgrade = opt3BootUpgrade,
            runes = opt3Runes,
            spells = opt3Spells,
            spellsIcons = opt3SpellsIcons
        )"""
content = opt3_regex.sub(opt3_replacement, content)


# Replace Option 4 Block completely using regex
opt4_regex = re.compile(
    r'(?s)// =========================================================================\s*// OPCIÓN 4: ANTI-MAGOS & SUPERVIVENCIA AP \(Coach Challenger\)\s*// =========================================================================.*?val opt4 = ChampionBuildOption\([^)]+\)'
)

opt4_replacement = """// =========================================================================
        // OPCIÓN 4: BUILD CREATIVA 2 (Coach Challenger)
        // =========================================================================
        val opt4Title = when {
            isSupport && !isSpecialDamageSupport -> "Presión Constante & Roaming"
            isAp -> "Mago de Batalla (Supervivencia & CDR)"
            isMarksman -> "Autosuficiencia & Anti-Ráfaga"
            isTank -> "Engage & Disrupt (Iniciador de Teamfights)"
            else -> "Duelista & Split-Push (1v1)"
        }
        val opt4Items: List<String> = when {
            isSupport && !isSpecialDamageSupport -> listOf(
                "Guadaña de la Niebla Negra", "Bendición de Mikael", "Bastón de aguas fluidas",
                "Eco armónico", "Velo de alma en pena", "Torreón de Kaenic", "Relicario de los Solari de Hierro", "Redención"
            )
            isAp -> listOf(
                "Báculo del arcángel", "Velo de alma en pena", "Torreón de Kaenic",
                "Sombrero mortal de Rabadon", "Reloj de arena de Zhonya", "Bastón del vacío", "Abrazo del serafín", "Morellonomicón"
            )
            isMarksman -> listOf(
                "Al filo de la cordura", "Fauces de Malmortius", "Borde infinito",
                "Filo de la noche", "Sanguinario", "Saludos de Dominik", "Ángel custodio", "Fajín de mercurio"
            )
            isTank -> listOf(
                "Torreón de Kaenic", "Fuerza de la naturaleza", "Máscara abisal",
                "Corazón de acero", "Malla de espinas", "Guardia gemela de amaranto", "Presagio de Randuin", "Relicario de los Solari de Hierro"
            )
            else -> listOf(
                "Al filo de la cordura", "Fauces de Malmortius", "Guantelete de Sterak",
                "Cuchilla negra", "Torreón de Kaenic", "La danza de la muerte", "Filo de la noche", "Ángel custodio"
            )
        }
        val opt4Runes = listOf("Garras del inmortal", "Orbe anulador", "Segundo aire", "Sobrecrecimiento")
        val opt4BootBase = "Botas de mercurio"
        val opt4BootUpgrade = "Trituradoras encadenadas"
        val opt4Spells = ensureUniqueSpells(
            when (role) {
                LaneRole.JUNGLE -> listOf("Castigo", "Destello")
                LaneRole.ADC -> listOf("Destello", "Barrera")
                else -> listOf("Destello", "Barrera")
            },
            role
        )
        val opt4SpellsIcons = opt4Spells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }
        
        val opt4Reason = when {
            isSupport && !isSpecialDamageSupport -> "Enfocada en movilidad extrema y control del mapa. Excelente para rotar con el jungla, asegurar visión profunda y emboscar otras líneas."
            isAp -> "Equilibra daño sostenido con resistencias clave. Te permite sobrevivir al diveo de asesinos y seguir castigando a quemarropa sin morir."
            isMarksman -> "Build defensiva para asegurar supervivencia si no cuentas con peel de tu soporte. Escudos y robo de vida pesados para ganar duelos difíciles."
            isTank -> "Especializada en causar caos e iniciar peleas 5v5. Gran mitigación, utilidad de área y herramientas para irrumpir y desorganizar la formación rival."
            else -> "Transforma a tu campeón en el rey de la línea lateral (Split-Push). Permite destruir torres rápidamente e imponerse en duelos aislados contra cualquier oponente."
        }

        val opt4 = ChampionBuildOption(
            optionNumber = 4,
            title = "Opción 4: $opt4Title",
            subtitle = "Coach Táctico Challenger",
            source = "Coach Táctico",
            badge = "ESTRATÉGICO",
            tacticalReason = opt4Reason,
            items = opt4Items,
            bootBase = opt4BootBase,
            bootUpgrade = opt4BootUpgrade,
            runes = opt4Runes,
            spells = opt4Spells,
            spellsIcons = opt4SpellsIcons
        )"""
content = opt4_regex.sub(opt4_replacement, content)

with open("app/src/main/java/com/example/util/ChampionRoleAdapter.kt", "w") as f:
    f.write(content)
print("Done")
