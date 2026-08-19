import re

file_path = "app/src/main/java/com/example/util/CoachingGenerator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

new_content = content.replace(
    "fun generateMatchupReason(champion: Champion, target: String, type: String, lang: String): String {",
    "fun generateMatchupReason(champion: Champion, activeRole: LaneRole, target: String, type: String, lang: String): String {"
).replace(
    "val sourceRole = champion.primaryRole",
    "val sourceRole = activeRole"
).replace(
    "fun generateTacticalAnalysis(champion: Champion, lang: String): String {",
    "fun generateTacticalAnalysis(champion: Champion, activeRole: LaneRole, lang: String): String {"
).replace(
    "val roleStr = champion.primaryRole.displayName",
    "val roleStr = activeRole.displayName"
).replace(
    "fun generateTacticalAdvice(champion: Champion, lang: String): String {",
    "fun generateTacticalAdvice(champion: Champion, activeRole: LaneRole, lang: String): String {"
).replace(
    "return when (champion.primaryRole) {",
    "return when (activeRole) {"
)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(new_content)

print("CoachingGenerator updated successfully!")
