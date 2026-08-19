import re

file_path = "app/src/main/java/com/example/util/CoachingGenerator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I will leave CoachingGenerator intact because the prompt doesn't complain about the generator itself, but I want to make sure it handles 'pt' properly if possible. Wait, the user said "revisa el proyecto y que esté funcionando en todas las traducciones español inglés y portugués". It's better to add PT to CoachingGenerator as well.
