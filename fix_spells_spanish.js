const fs = require('fs');
let ktCode = fs.readFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'utf-8');

const newSpells = `val summonerSpells: List<SummonerSpellItem> = listOf(
        SummonerSpellItem(
            id = "heal",
            name = "Curar",
            cooldown = "100s",
            iconUrl = "",
            description = "Restaura 110 de vida (de 110 a 400) y te otorga un 30% de velocidad de movimiento adicional durante 2 s a ti y al campeón aliado cercano más herido."
        ),
        SummonerSpellItem(
            id = "ghost",
            name = "Fantasmal",
            cooldown = "90s",
            iconUrl = "",
            description = "Obtiene una gran mejora de velocidad de movimiento que decrece hasta un 25% de velocidad de movimiento adicional durante 8 s. La duración de Fantasmal aumenta en 6 s con cada asesinato o asistencia, lo que reinicia su efecto hasta la cifra inicial."
        ),
        SummonerSpellItem(
            id = "barrier",
            name = "Barrera",
            cooldown = "100s",
            iconUrl = "",
            description = "Otorga un escudo que absorbe 120 de daño (120 - 560) durante 2,5 s."
        ),
        SummonerSpellItem(
            id = "exhaust",
            name = "Extenuación",
            cooldown = "100s",
            iconUrl = "",
            description = "Extenúa al campeón enemigo objetivo, lo ralentiza un 35% y reduce su daño un 40% durante 2,5 s. La ralentización decrece mientras dura el efecto."
        ),
        SummonerSpellItem(
            id = "cleanse",
            name = "Limpiar",
            cooldown = "110s",
            iconUrl = "",
            description = "Elimina las inhabilitaciones (incluidas las de los hechizos) que afectan a tu campeón y le otorga inmunidad a todas las inhabilitaciones durante 0,25 s."
        ),
        SummonerSpellItem(
            id = "flash",
            name = "Destello",
            cooldown = "150s",
            iconUrl = "",
            description = "Teleporta una breve distancia hacia la dirección en la que apunta."
        ),
        SummonerSpellItem(
            id = "ignite",
            name = "Prender",
            cooldown = "100s",
            iconUrl = "",
            description = "Prende fuego a un campeón enemigo, lo que inflige 72 de daño verdadero (72 - 380) durante 5 s, aplica heridas graves al objetivo y lo revela."
        ),
        SummonerSpellItem(
            id = "smite",
            name = "Aplastar",
            cooldown = "10s",
            iconUrl = "",
            description = "Inflige 600 de daño verdadero a los monstruos, monstruos épicos o súbditos enemigos. Al utilizar Aplastar contra monstruos, recuperas 127 de vida (70 + 10%). Aplastar se convierte en Aplastamiento desalentador tras usarlo 3 veces."
        )
    )`;

const startIdx = ktCode.indexOf('val summonerSpells: List<SummonerSpellItem> = listOf(');
const endIdx = ktCode.indexOf('val runes: List<RuneItem> = listOf(');

if (startIdx !== -1 && endIdx !== -1) {
    ktCode = ktCode.substring(0, startIdx) + newSpells + '\n\n    ' + ktCode.substring(endIdx);
    fs.writeFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', ktCode);
    console.log("Success");
} else {
    console.log("Could not find blocks");
}
