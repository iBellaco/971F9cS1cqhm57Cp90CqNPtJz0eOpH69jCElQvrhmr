import re
with open("app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt", "r") as f:
    text = f.read()

text = re.sub(r'com\.example\.data\.supabase\.WildRiftSupabaseRepository\.syncAllFromSupabase\(context\)', '//com.example.data.supabase.WildRiftSupabaseRepository.syncAllFromSupabase(context)', text)

with open("app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt", "w") as f:
    f.write(text)
print("Patched meta sync")
