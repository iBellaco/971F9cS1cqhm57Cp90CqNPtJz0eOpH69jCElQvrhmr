const fs = require('fs');
let ktCode = fs.readFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'utf-8');

const spellMark = `,
        SummonerSpellItem(
            id = "mark",
            name = "Marca / Lanzamiento",
            cooldown = "80s",
            iconUrl = SPELL_MARK,
            description = "Lanza una bola de nieve en línea recta. Si golpea a un enemigo, lo marca. Puedes reactivar esta habilidad (Lanzamiento) para desplazarte hacia el enemigo marcado (Exclusivo del modo ARAM)."
        )
    )`;

ktCode = ktCode.replace(
    '        )',
    '        )' // Just to find the closing parentheses of clarity, wait this is too generic.
);

// Better replace using specific target
ktCode = ktCode.replace(
    /SummonerSpellItem\(\s*id = "clarity"[\s\S]*?\)\n    \)/,
    `SummonerSpellItem(
            id = "clarity",
            name = "Claridad",
            cooldown = "90s",
            iconUrl = SPELL_CLARITY,
            description = "Restaura el 50% del maná máximo a tu campeón y el 25% del maná a todos los aliados cercanos en el área de efecto (disponible en modos especiales y ARAM)."
        )${spellMark}`
);

fs.writeFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', ktCode);
