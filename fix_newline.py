import re

file_path = "app/src/main/java/com/example/data/WildRiftItemsData.kt"

with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Fix multiline passive that should be \n
# We'll just fix Tear of the goddess since we know it broke
content = content.replace('Asombro: se reembolsa el 10% del maná gastado.\nCarga de maná:', 'Asombro: se reembolsa el 10% del maná gastado.\\nCarga de maná:')

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

