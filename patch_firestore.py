import re

file_path = "app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I will uncomment the block
content = content.replace("/* DISABLED temporarily to prevent GoogleApiManager errors on emulator without GMS", "// ENABLED")
content = content.replace("WildRiftRepository.mapObjectives = cloudObjectives\n                }\n                */", "WildRiftRepository.mapObjectives = cloudObjectives\n                }")

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
print("Uncommented Firestore syncing in MetaCrawler")
