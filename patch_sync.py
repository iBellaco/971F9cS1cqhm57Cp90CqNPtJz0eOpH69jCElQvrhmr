import re
file_path = "app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Comment out the firestoreManager block
old_block = """                // Pull actual dynamic data from Firestore to override local defaults
                val firestoreManager = com.example.data.sync.FirestoreManager()
                
                val cloudChampions = firestoreManager.getChampionsFromFirestore()
                if (cloudChampions.isNotEmpty()) {
                    WildRiftRepository.champions = cloudChampions
                }
                val cloudSpells = firestoreManager.getSpellsFromFirestore()
                if (cloudSpells.isNotEmpty()) {
                    WildRiftRepository.summonerSpells = cloudSpells
                }
                val cloudRunes = firestoreManager.getRunesFromFirestore()
                if (cloudRunes.isNotEmpty()) {
                    WildRiftRepository.runes = cloudRunes
                }
                val cloudItems = firestoreManager.getItemsFromFirestore()
                if (cloudItems.isNotEmpty()) {
                    WildRiftRepository.items = cloudItems
                }
                val cloudObjectives = firestoreManager.getObjectivesFromFirestore()
                if (cloudObjectives.isNotEmpty()) {
                    WildRiftRepository.mapObjectives = cloudObjectives
                }"""

new_block = """                // Pull actual dynamic data from Firestore to override local defaults
                // DISABLED temporarily to prevent GoogleApiManager errors on emulator without GMS
                /*
                val firestoreManager = com.example.data.sync.FirestoreManager()
                
                val cloudChampions = firestoreManager.getChampionsFromFirestore()
                if (cloudChampions.isNotEmpty()) {
                    WildRiftRepository.champions = cloudChampions
                }
                val cloudSpells = firestoreManager.getSpellsFromFirestore()
                if (cloudSpells.isNotEmpty()) {
                    WildRiftRepository.summonerSpells = cloudSpells
                }
                val cloudRunes = firestoreManager.getRunesFromFirestore()
                if (cloudRunes.isNotEmpty()) {
                    WildRiftRepository.runes = cloudRunes
                }
                val cloudItems = firestoreManager.getItemsFromFirestore()
                if (cloudItems.isNotEmpty()) {
                    WildRiftRepository.items = cloudItems
                }
                val cloudObjectives = firestoreManager.getObjectivesFromFirestore()
                if (cloudObjectives.isNotEmpty()) {
                    WildRiftRepository.mapObjectives = cloudObjectives
                }
                */"""

if old_block in content:
    content = content.replace(old_block, new_block)
else:
    print("Block not found!")

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
