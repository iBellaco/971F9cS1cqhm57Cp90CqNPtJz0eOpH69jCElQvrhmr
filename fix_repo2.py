import re

file_path = "app/src/main/java/com/example/data/WildRiftRepository.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Fix the missing parenthesis from the previous patch
content = content.replace('        )\n    // ==========================================\n    // ROSTER INTEGRAL DE CAMPEONES DE WILD RIFT\n', '        )\n    )\n\n    // ==========================================\n    // ROSTER INTEGRAL DE CAMPEONES DE WILD RIFT\n')

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Fixed WildRiftRepository.kt missing parenthesis!")
