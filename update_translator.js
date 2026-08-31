const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/util/Translator.kt', 'utf8');

const ptTranslations = `
        "Análisis de Enfrentamiento" to "Análise de Confronto",
        "Sinergias y Macro (Wild Rift)" to "Sinergias e Macro (Wild Rift)",
        "Condición de Victoria Móvil:" to "Condição de Vitória Mobile:",
        "Aprovecha que las rotaciones en Wild Rift son rápidas. Prioriza rotar al Dragón antes del minuto 5. Si %s rota primero, castiga su torre por placas." to "Aproveite que as rotações no Wild Rift são rápidas. Priorize rotacionar para o Dragão antes dos 5 minutos. Se %s rotacionar primeiro, puna sua torre por barricadas.",
        "Tienes la ventaja de campeón. Mantén el control de la oleada y usa los arbustos para rotar y emboscar (roam) a otras líneas." to "Você tem a vantagem do campeão. Mantenha o controle da onda e use os arbustos para rotacionar e emboscar (roam) outras rotas.",
        "Mantén la calma y no cedas oro. En Wild Rift el juego tardío llega rápido; agrupa con tu equipo tan pronto caiga la primera torre." to "Mantenha a calma e não ceda ouro. No Wild Rift o late game chega rápido; agrupe com sua equipe assim que a primeira torre cair.",
        "Duelo equilibrado. Mantén visión en el río con Lente Revelador antes de los objetivos y castiga cuando use habilidades en la oleada." to "Duelo equilibrado. Mantenha a visão no rio com a Lente Detectora antes dos objetivos e puna quando ele usar habilidades na rota.",`;

const enTranslations = `
        "Análisis de Enfrentamiento" to "Matchup Analysis",
        "Sinergias y Macro (Wild Rift)" to "Synergies & Macro (Wild Rift)",
        "Condición de Victoria Móvil:" to "Mobile Win Condition:",
        "Aprovecha que las rotaciones en Wild Rift son rápidas. Prioriza rotar al Dragón antes del minuto 5. Si %s rota primero, castiga su torre por placas." to "Take advantage of fast rotations in Wild Rift. Prioritize roaming to Dragon before minute 5. If %s roams first, punish their turret for plates.",
        "Tienes la ventaja de campeón. Mantén el control de la oleada y usa los arbustos para rotar y emboscar (roam) a otras líneas." to "You have the champion advantage. Control the wave and use brushes to roam and gank other lanes.",
        "Mantén la calma y no cedas oro. En Wild Rift el juego tardío llega rápido; agrupa con tu equipo tan pronto caiga la primera torre." to "Stay calm and don't give up gold. Late game arrives quickly in Wild Rift; group with your team as soon as the first turret falls.",
        "Duelo equilibrado. Mantén visión en el río con Lente Revelador antes de los objetivos y castiga cuando use habilidades en la oleada." to "Even matchup. Keep river vision with Sweeping Lens before objectives and punish when they use skills on the wave.",`;

code = code.replace(/val ptDict = mapOf<String, String>\(/, 'val ptDict = mapOf<String, String>(' + ptTranslations);
code = code.replace(/val enDict = mapOf<String, String>\(/, 'val enDict = mapOf<String, String>(' + enTranslations);

fs.writeFileSync('app/src/main/java/com/example/util/Translator.kt', code);
