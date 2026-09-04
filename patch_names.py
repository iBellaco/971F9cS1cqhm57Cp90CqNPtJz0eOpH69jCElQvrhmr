file_path = 'app/src/main/java/com/example/data/WildRiftItemsData.kt'
with open(file_path, 'r') as f:
    content = f.read()

replacements = {
    'name = "Borde infinito"': 'name = "Filo Infinito"',
    'name = "sanguinario"': 'name = "La Sanguinaria"',
    'name = "Guantelete de Sterak"': 'name = "Calibrador de Sterak"',
    'name = "El coleccionista"': 'name = "Coleccionista"',
}

for old, new in replacements.items():
    content = content.replace(old, new)

with open(file_path, 'w') as f:
    f.write(content)

