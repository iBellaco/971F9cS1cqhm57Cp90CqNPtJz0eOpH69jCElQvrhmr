import re

file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I will replace `currentChamp.coreItems.take(3) + currentChamp.situationalItems.take(2)`
# with `currentChamp.coreItems + currentChamp.situationalItems`
content = content.replace("currentChamp.coreItems.take(3) + currentChamp.situationalItems.take(2)", "currentChamp.coreItems + currentChamp.situationalItems")

# Ensure champItems maps all of them, not taking 4
# .takeIf { it.isNotEmpty() } ?: WildRiftRepository.items.take(4)
# I can leave the fallback just in case.

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Patched FloatingAssistantService for all items")
