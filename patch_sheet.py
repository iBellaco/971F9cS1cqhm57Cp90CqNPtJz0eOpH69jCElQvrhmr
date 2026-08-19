import os

file_path = "app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Replace generateTacticalAnalysis call
content = content.replace(
    "CoachingGenerator.generateTacticalAnalysis(champion, currentLang)",
    "CoachingGenerator.generateTacticalAnalysis(champion, selectedRole, currentLang)"
)

# Fix remember dependency for generateTacticalAnalysis to recompute when selectedRole changes
content = content.replace(
    "val fullAnalysis = remember(champion.id, currentLang) { CoachingGenerator.generateTacticalAnalysis(champion, selectedRole, currentLang) }",
    "val fullAnalysis = remember(champion.id, currentLang, selectedRole) { CoachingGenerator.generateTacticalAnalysis(champion, selectedRole, currentLang) }"
)

# Replace generateMatchupReason call
content = content.replace(
    "val descText = CoachingGenerator.generateMatchupReason(champion, target, type, com.example.util.LocalLanguage.current)",
    "val descText = CoachingGenerator.generateMatchupReason(champion, selectedRole, target, type, com.example.util.LocalLanguage.current)"
)

# Replace generateTacticalAdvice call if exists (let's check if it exists in another place)
content = content.replace(
    "CoachingGenerator.generateTacticalAdvice(champion, ",
    "CoachingGenerator.generateTacticalAdvice(champion, selectedRole, "
)


with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("ChampionDetailSheet patched!")
