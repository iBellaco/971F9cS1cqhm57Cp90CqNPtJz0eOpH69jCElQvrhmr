import re

with open("app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt", "r") as f:
    content = f.read()

firebase_block = r'''                // Pull actual dynamic data from Firestore to override local defaults
                // ENABLED
                val firestoreManager = com\.example\.data\.sync\.FirestoreManager\(\)
                
                val cloudChampions = firestoreManager\.getChampionsFromFirestore\(\)
                if \(cloudChampions\.isNotEmpty\(\)\) \{
                    WildRiftRepository\.champions = cloudChampions
                \}

                val cloudSpells = firestoreManager\.getSpellsFromFirestore\(\)
                if \(cloudSpells\.isNotEmpty\(\)\) \{
                    WildRiftRepository\.summonerSpells = cloudSpells
                \}

                val cloudRunes = firestoreManager\.getRunesFromFirestore\(\)
                if \(cloudRunes\.isNotEmpty\(\)\) \{
                    WildRiftRepository\.runes = cloudRunes
                \}

                val cloudItems = firestoreManager\.getItemsFromFirestore\(\)
                if \(cloudItems\.isNotEmpty\(\)\) \{
                    WildRiftRepository\.items = cloudItems
                \}

                val cloudObjectives = firestoreManager\.getObjectivesFromFirestore\(\)
                if \(cloudObjectives\.isNotEmpty\(\)\) \{
                    WildRiftRepository\.mapObjectives = cloudObjectives
                \}'''

content = re.sub(firebase_block, '                // Supabase dynamic data fetching will be implemented here', content)

with open("app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt", "w") as f:
    f.write(content)
