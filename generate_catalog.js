const fs = require('fs');
const champs = fs.readFileSync('generated_avatars.txt', 'utf8');

const code = `package com.example.data

import com.example.model.AvatarItem

object AvatarCatalog {
    val DEFAULT_AVATAR = AvatarItem(
        id = "default_poro",
        name = "Poro Guardián",
        title = "Espíritu de la Grieta",
        region = "Mascotas",
        rarity = "Clásico",
        imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/588.png",
        borderHex = "#C8AA6E",
        description = "El leal compañero de todo Invocador de Wild Rift.",
        isDefault = true
    )

    val avatars: List<AvatarItem> = listOf(
        DEFAULT_AVATAR,
${champs.trimEnd().replace(/,\s*$/, '')}
    )

    fun getAvatarById(id: String): AvatarItem {
        return avatars.find { it.id.equals(id, ignoreCase = true) } ?: DEFAULT_AVATAR
    }
}
`;

fs.writeFileSync('app/src/main/java/com/example/data/AvatarCatalog.kt', code);
console.log("AvatarCatalog generated.");
