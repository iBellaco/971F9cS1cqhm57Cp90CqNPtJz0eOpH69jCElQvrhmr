import re

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'r') as f:
    content = f.read()

new_borders = '''        DEFAULT_AVATAR,
        AvatarItem(
            id = "borde_1",
            name = "Borde Épico 1",
            title = "Marco de Perfil",
            region = "Bordes",
            rarity = "Épico",
            imageUrl = "https://i.postimg.cc/D0kLqTMV/2212-w054-n005-245B-p1-245.jpg",
            borderHex = "#8A2BE2",
            description = "Borde decorativo para tu perfil."
        ),
        AvatarItem(
            id = "borde_2",
            name = "Borde Épico 2",
            title = "Marco de Perfil",
            region = "Bordes",
            rarity = "Épico",
            imageUrl = "https://i.postimg.cc/XqSdwbDR/2301-w032-n002-761B-p15-761.jpg",
            borderHex = "#8A2BE2",
            description = "Borde decorativo para tu perfil."
        ),
        AvatarItem(
            id = "borde_3",
            name = "Borde Mítico",
            title = "Marco de Perfil",
            region = "Bordes",
            rarity = "Mítico",
            imageUrl = "https://i.postimg.cc/Rh5KcBbg/8b19b0a1-9918-46b6-9e58-26f5b2f5ae09.jpg",
            borderHex = "#FF4500",
            description = "Borde legendario para destacar."
        ),'''

content = content.replace('        DEFAULT_AVATAR,', new_borders)

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'w') as f:
    f.write(content)
