import re
with open("app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt", "r") as f:
    text = f.read()

text = re.sub(r'com\.example\.data\.supabase\.WildRiftSupabaseRepository\.saveAllChampionsToSupabase\(updatedChampions\)', '//com.example.data.supabase.WildRiftSupabaseRepository.saveAllChampionsToSupabase(updatedChampions)', text)

with open("app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt", "w") as f:
    f.write(text)
print("Patched chinese sync")
