import re

with open('app/src/main/java/com/example/data/champions/SupportChampions.kt', 'r') as f:
    text = f.read()

# Define the new coreItems and their URLs
core_items = [
    "Escudo Reliquia",
    "Coraza del Muerto",
    "Manto del Amanecer",
    "Botas de Placas de Acero",
    "Convergencia de Zeke",
    "Fuerza de la Naturaleza"
]

core_items_icons = [
    "https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp",
    "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp",
    "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp",
    "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp",
    "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp",
    "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp"
]

old_core = r'coreItems = listOf\("Creagrietas", "Égida de Fuego Solar", "Rostro Espiritual", "Botas de Hechicero", "Sombrero Mortífero de Rabadon", "Báculo del Vacío"\)'
new_core = 'coreItems = listOf("Escudo Reliquia", "Coraza del Muerto", "Manto del Amanecer", "Botas de Placas de Acero", "Convergencia de Zeke", "Fuerza de la Naturaleza")'

old_icons = r'coreItemsIcons = listOf\("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png"\)'
new_icons = 'coreItemsIcons = listOf("https://wr-meta.com/uploads/posts/2025-07/1753390612_relic-shield.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331165_dead-mans-plate.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp", "https://wr-meta.com/uploads/posts/2025-07/1753390145_plated-steelcaps.webp", "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp", "https://wr-meta.com/uploads/posts/2025-07/1753331238_force-of-nature.webp")'

old_runes = r'recommendedRunes = "Reverberacción \(Valor\)"'
new_runes = 'recommendedRunes = "Réplica"'
old_tree = r'runeTreeDetails = "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Golpe de Escudo"'
new_tree = 'runeTreeDetails = "Valor: Fuente de Vida • Revestimiento de Huesos • Sobrecrecimiento • Dulces Frutos"'

text = re.sub(old_core, new_core, text)
text = re.sub(old_icons, new_icons, text)
text = re.sub(old_runes, new_runes, text)
text = re.sub(old_tree, new_tree, text)

with open('app/src/main/java/com/example/data/champions/SupportChampions.kt', 'w') as f:
    f.write(text)
print("Updated Alistar")
