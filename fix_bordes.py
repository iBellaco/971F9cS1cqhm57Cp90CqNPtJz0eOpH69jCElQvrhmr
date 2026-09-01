import re

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'setOf("Aguas Esturbias", "Ciudad de Bandle", "Demacia", "El Vacío", "Freljord", "Islas de la Sombra", "Jonia", "Ixtal", "Noxus", "Piltóver", "Runaterra", "Shurima", "Targon", "Zaun", "Poro", "Bordes")',
    'setOf("Aguas Esturbias", "Ciudad de Bandle", "Demacia", "El Vacío", "Freljord", "Islas de la Sombra", "Jonia", "Ixtal", "Noxus", "Piltóver", "Runaterra", "Shurima", "Targon", "Zaun", "Poro")'
)

# And remove the else block that has the old borders logic since selectedCategory is always "Avatares"
# Actually we can just remove the selectedCategory check completely to clean it up.
# Let's just remove 'Bordes' from validRegions first.

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'w') as f:
    f.write(content)
