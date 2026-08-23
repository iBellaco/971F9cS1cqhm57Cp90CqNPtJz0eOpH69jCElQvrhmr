const fs = require('fs');

let ktCode = fs.readFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'utf-8');

// Replace constants
ktCode = ktCode.replace(/const val SPELL_FLASH = .*$/m, 'const val SPELL_FLASH = "https://i.postimg.cc/6qHRh6Gt/1691694210-flash.webp"');
ktCode = ktCode.replace(/const val SPELL_IGNITE = .*$/m, 'const val SPELL_IGNITE = "https://i.postimg.cc/4y8t14hN/1691695236-ignite.webp"');
ktCode = ktCode.replace(/const val SPELL_BARRIER = .*$/m, 'const val SPELL_BARRIER = "https://i.postimg.cc/2yHvxjBV/1691695152-barrier.webp"');
ktCode = ktCode.replace(/const val SPELL_EXHAUST = .*$/m, 'const val SPELL_EXHAUST = "https://i.postimg.cc/gjMRKc6r/1691695333-exhaust.webp"');
ktCode = ktCode.replace(/const val SPELL_GHOST = .*$/m, 'const val SPELL_GHOST = "https://i.postimg.cc/RhPfTCnS/1691694862-ghost.webp"');
ktCode = ktCode.replace(/const val SPELL_HEAL = .*$/m, 'const val SPELL_HEAL = "https://i.postimg.cc/d3Wd9QT3/1691695008-heal.webp"');
ktCode = ktCode.replace(/const val SPELL_TELEPORT = .*$/m, 'const val SPELL_TELEPORT = "https://i.postimg.cc/J0TJQ7B7/1611110740-teleport-enchant.png"\n    const val SPELL_CLEANSE = "https://i.postimg.cc/kGj8yMt5/1735511112-cleanse.webp"\n    const val SPELL_CHILLING_SMITE = "https://i.postimg.cc/NFNTxGrg/1691695722-chilling-smite.png"');

// Replace the summonerSpells list. We need to extract everything from `val summonerSpells: List<SummonerSpellItem> = listOf(` up to `)` before `val runes:`
let newSpellsList = `val summonerSpells: List<SummonerSpellItem> = listOf(
        SummonerSpellItem(
            id = "flash",
            name = "Destello",
            cooldown = "150s",
            iconUrl = SPELL_FLASH,
            description = "Teletransporta una corta distancia hacia adelante o hacia la dirección apuntada."
        ),
        SummonerSpellItem(
            id = "ghost",
            name = "Fantasma",
            cooldown = "90s",
            iconUrl = SPELL_GHOST,
            description = "Obtienes un gran impulso de velocidad de movimiento, que decae a un 25% de velocidad de movimiento adicional durante 8 segundos. Con cada derribo, la duración de Fantasma se extiende 6 segundos, reiniciando sus efectos, hasta la cantidad original."
        ),
        SummonerSpellItem(
            id = "heal",
            name = "Curar",
            cooldown = "100s",
            iconUrl = SPELL_HEAL,
            description = "Restaura 110 de Vida (110-400) a ti y al campeón aliado cercano más herido, y les otorga a ambos un 30% de Velocidad de Movimiento adicional durante 2 segundo(s). La curación se reduce a la mitad en campeones afectados recientemente por Curar."
        ),
        SummonerSpellItem(
            id = "barrier",
            name = "Barrera",
            cooldown = "100s",
            iconUrl = SPELL_BARRIER,
            description = "Obtienes un escudo que absorbe 120 (120-560) de daño durante 2.5 segundos."
        ),
        SummonerSpellItem(
            id = "ignite",
            name = "Prender",
            cooldown = "100s",
            iconUrl = SPELL_IGNITE,
            description = "Prende fuego al campeón enemigo objetivo, infligiendo 72 de daño verdadero (72-380) durante 5s y aplicando un 60% de Heridas Graves durante ese tiempo. Las Heridas Graves reducen la efectividad de las curaciones y regeneraciones."
        ),
        SummonerSpellItem(
            id = "exhaust",
            name = "Extenuación",
            cooldown = "100s",
            iconUrl = SPELL_EXHAUST,
            description = "Extenúa al campeón enemigo objetivo, reduciendo su Velocidad de Movimiento un 35% y su daño infligido un 40% durante 2.5 segundos."
        ),
        SummonerSpellItem(
            id = "smite",
            name = "Castigo",
            cooldown = "10s",
            iconUrl = SPELL_SMITE,
            description = "Inflige 600 de daño verdadero a monstruos, monstruos épicos o súbditos enemigos. Lanzar Castigo a un monstruo restaura 127 de Vida (70 + 10% Vida). Castigo se mejora a Castigo Helador después de 3 usos.\\n\\nEspecialización en Jungla:\\nGanas 20% de oro y XP adicional de los monstruos, pero ganas 60% menos de oro y XP de súbditos temporalmente.\\nInfliges 15% más de daño de ataque y 30% más de daño de habilidad contra monstruos.\\nRestaura 40 de Vida durante 5 segundos al infligir daño a monstruos.\\nRestaura 4 de Maná por segundo en la jungla o el río.\\nA partir del minuto 11:00, los monstruos no darán oro adicional.\\nAl minuto 2:00, la bonificación de daño contra monstruos empieza a decaer y al 5:00 desaparece.\\nGana una carga cada 45 segundos, hasta un máximo de 2."
        ),
        SummonerSpellItem(
            id = "chilling_smite",
            name = "Castigo Helador",
            cooldown = "10s",
            iconUrl = SPELL_CHILLING_SMITE,
            description = "Inflige 1000 de daño verdadero a un monstruo grande, épico o súbdito. Lanzar Castigo a monstruos restaura 152 de Vida (70 + 10% Vida).\\n\\nContra campeones: Inflige 40 de daño verdadero a Campeones enemigos y roba el 25% de su Velocidad de Movimiento durante 2 segundos."
        ),
        SummonerSpellItem(
            id = "cleanse",
            name = "Purificación",
            cooldown = "110s",
            iconUrl = SPELL_CLEANSE,
            description = "Elimina inmovilizaciones (incluyendo debilitaciones de hechizos) que afecten a tu campeón y otorga inmunidad a inmovilizaciones por 0.25 segundos."
        ),
        SummonerSpellItem(
            id = "teleport",
            name = "Teleportación",
            cooldown = "150s",
            iconUrl = SPELL_TELEPORT,
            description = "Después de canalizar durante 3.5 segundos, teletransporta a tu campeón a un campeón, estructura o centinela aliado (excluye áreas dentro del alcance de inhibidores enemigos). Solo puedes teletransportarte a estructuras durante los primeros 6 minutos de la partida."
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
    )`;

ktCode = ktCode.replace(/val summonerSpells: List<SummonerSpellItem> = listOf\([\s\S]*?\n    \)(?=\s*val runes:)/, newSpellsList + '\n    ');

// Also update `getSpellIconByName` to map properly
let newGetSpellIcon = `fun getSpellIconByName(name: String): String {
        return when (name.trim().lowercase()) {
            "destello", "flash" -> SPELL_FLASH
            "prender", "ignición", "ignite", "ignicion", "incendiar" -> SPELL_IGNITE
            "castigo", "smite" -> SPELL_SMITE
            "castigo helador", "chilling smite" -> SPELL_CHILLING_SMITE
            "barrera", "barrier" -> SPELL_BARRIER
            "extenuación", "extenuacion", "exhaust" -> SPELL_EXHAUST
            "fantasma", "ghost" -> SPELL_GHOST
            "curar", "curación", "curacion", "heal" -> SPELL_HEAL
            "claridad", "clarity" -> SPELL_CLARITY
            "purificación", "purificacion", "cleanse" -> SPELL_CLEANSE
            "marca", "marca / lanzamiento", "mark", "snowball" -> SPELL_MARK
            "teleportación", "teletransporte", "teleport" -> SPELL_TELEPORT
            else -> SPELL_FLASH // fallback
        }
    }`;

ktCode = ktCode.replace(/fun getSpellIconByName\(name: String\): String \{[\s\S]*?else -> SPELL_FLASH \/\/ fallback\n        \}\n    \}/, newGetSpellIcon);

fs.writeFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', ktCode);
