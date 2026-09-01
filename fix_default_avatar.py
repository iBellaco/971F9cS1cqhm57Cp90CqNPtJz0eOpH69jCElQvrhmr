import re

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'r') as f:
    content = f.read()

default_avatar_str = '''    val DEFAULT_AVATAR = AvatarItem(
        id = "default_poro",
        name = "Poro Guardián",
        title = "Espíritu de la Grieta",
        region = "Poro",
        rarity = "Clásico",
        imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/588.png",
        borderHex = "#C8AA6E",
        description = "El leal compañero de todo Invocador de Wild Rift.",
        isDefault = true
    )'''

content = content.replace('    val DEFAULT_AVATAR =', default_avatar_str)

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'w') as f:
    f.write(content)
