const fs = require('fs');
let content = fs.readFileSync('app/src/main/java/com/example/data/AvatarCatalog.kt', 'utf8');

const newAvatars = `
        AvatarItem(
            id = "poro_dragon_fuego",
            name = "Poro Dragón de Fuego",
            title = "Alma Infernal",
            region = "Poros",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/3457.png",
            borderHex = "#C8AA6E",
            description = "Un poro incandescente que habita en las grietas infernales."
        ),
        AvatarItem(
            id = "poro_dragon_aire",
            name = "Poro Dragón de Aire",
            title = "Alma de las Nubes",
            region = "Poros",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/3458.png",
            borderHex = "#C8AA6E",
            description = "Ligero como el viento, este poro vuela entre las nubes."
        ),
        AvatarItem(
            id = "poro_dragon_tierra",
            name = "Poro Dragón de Tierra",
            title = "Alma de la Montaña",
            region = "Poros",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/3459.png",
            borderHex = "#C8AA6E",
            description = "Robusto e inamovible."
        ),
        AvatarItem(
            id = "poro_dragon_hielo",
            name = "Poro Dragón de Hielo",
            title = "Alma Glacial",
            region = "Poros",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/3460.png",
            borderHex = "#C8AA6E",
            description = "Frío al tacto, pero con un corazón cálido."
        ),
        AvatarItem(
            id = "dragon_ancestral",
            name = "Dragón Ancestral",
            title = "Poder Definitivo",
            region = "Monstruos Épicos",
            rarity = "Mítico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/4433.png",
            borderHex = "#C8AA6E",
            description = "El gobernante supremo de los cielos de la Grieta."
        ),
        AvatarItem(
            id = "heraldo_grieta",
            name = "Heraldo de la Grieta",
            title = "Ojo del Vacío",
            region = "Monstruos Épicos",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/3534.png",
            borderHex = "#C8AA6E",
            description = "El terror de las torretas."
        ),
        AvatarItem(
            id = "baron_nashor",
            name = "Barón Nashor",
            title = "Rey de la Grieta",
            region = "Monstruos Épicos",
            rarity = "Mítico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/3535.png",
            borderHex = "#C8AA6E",
            description = "La criatura más temible que acecha en el río."
        ),
        AvatarItem(
            id = "kda_akali",
            name = "Akali K/DA",
            title = "Estrella del Pop",
            region = "K/DA",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/3588.png",
            borderHex = "#C8AA6E",
            description = "Brillando en el escenario mundial."
        ),
        AvatarItem(
            id = "flor_espiritual",
            name = "Flor Espiritual",
            title = "Vínculo del más allá",
            region = "Flor Espiritual",
            rarity = "Mítico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/4570.png",
            borderHex = "#C8AA6E",
            description = "Una flor que florece solo para aquellos que recuerdan."
        ),
        AvatarItem(
            id = "celestial_sol",
            name = "Aurelion Celestial",
            title = "Forjador de Estrellas",
            region = "Celestial",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/1337.png",
            borderHex = "#C8AA6E",
            description = "El vasto universo en la palma de tu mano."
        )
`;

content = content.replace('    )\n\n    fun getAvatarById', newAvatars + '    )\n\n    fun getAvatarById');
fs.writeFileSync('app/src/main/java/com/example/data/AvatarCatalog.kt', content);
console.log("Appended new avatars");
