import os

file_path = "app/src/main/java/com/example/data/WildRiftRepository.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

content = content.replace(
    "CoachingGenerator.generateTacticalAdvice(champ, lang)",
    "CoachingGenerator.generateTacticalAdvice(champ, myRole, lang)"
)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
