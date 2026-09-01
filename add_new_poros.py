import re

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'r') as f:
    content = f.read()

new_poros = '''        DEFAULT_AVATAR,
        AvatarItem(
            id = "poro_wukong",
            name = "Poro Wukong",
            title = "El Rey Mono",
            region = "Poro",
            rarity = "Común",
            imageUrl = "https://i.postimg.cc/LXS8Tbv5/1788243501865.jpg",
            borderHex = "#A0A0A0",
            description = "Un poro listo para la batalla con su bastón mágico."
        ),
        AvatarItem(
            id = "poro_volibear",
            name = "Poro Volibear",
            title = "El Rugido del Trueno",
            region = "Poro",
            rarity = "Raro",
            imageUrl = "https://i.postimg.cc/LXV6030W/1788243804693.jpg",
            borderHex = "#00BFFF",
            description = "Un poro imbuido con el poder de la tormenta."
        ),
        AvatarItem(
            id = "poro_nashor",
            name = "Poro Nashor",
            title = "El Rey de la Grieta",
            region = "Poro",
            rarity = "Épico",
            imageUrl = "https://i.postimg.cc/bJRNF9FF/1788243975279.jpg",
            borderHex = "#8A2BE2",
            description = "El poro más temible del río."
        ),
        AvatarItem(
            id = "poro_ahri",
            name = "Poro Ahri",
            title = "La Mujer Zorro",
            region = "Poro",
            rarity = "Épico",
            imageUrl = "https://i.postimg.cc/gJKkBHBt/1788244165659.jpg",
            borderHex = "#8A2BE2",
            description = "Un poro con un encanto irresistible."
        ),
        AvatarItem(
            id = "poro_kaisa",
            name = "Poro Kai'Sa",
            title = "Hija del Vacío",
            region = "Poro",
            rarity = "Épico",
            imageUrl = "https://i.postimg.cc/NMxfPkPV/1788244288372.jpg",
            borderHex = "#8A2BE2",
            description = "Un poro que sobrevivió al vacío."
        ),
        AvatarItem(
            id = "poro_irelia",
            name = "Poro Irelia",
            title = "La Danza de las Cuchillas",
            region = "Poro",
            rarity = "Común",
            imageUrl = "https://i.postimg.cc/QtqxnJyG/1788244351208.jpg",
            borderHex = "#A0A0A0",
            description = "Un poro que danza en el campo de batalla."
        ),'''

content = content.replace('        DEFAULT_AVATAR,', new_poros)

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'w') as f:
    f.write(content)
