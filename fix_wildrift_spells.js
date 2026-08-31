const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'utf8');

// Remove Teleport from summoner spells array
const teleportBlock = `        SummonerSpellItem(
            id = "teleport",
            name = "Teleportar",
            cooldown = "150s",
            iconUrl = "https://i.postimg.cc/gJdSFvPs/1611110740-teleport-enchant.png",
            description = "Mapa aplicable: Grieta\\n\\nTras canalizar durante 3,5 s, te teleportas a una estructura, campeón o guardián aliado (excepto en las áreas al alcance de los inhibidores enemigos).\\nSolo puedes teleportarte a estructuras durante 6 min al comienzo de la partida.",
            category = "Movilidad & Utilidad"
        ),`;

if (code.includes(teleportBlock)) {
    code = code.replace(teleportBlock, '');
} else {
    // maybe match with regex if exact match fails
    const regex = /SummonerSpellItem\(\s*id = "teleport"[\s\S]*?\),/m;
    code = code.replace(regex, '');
}

fs.writeFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', code);
