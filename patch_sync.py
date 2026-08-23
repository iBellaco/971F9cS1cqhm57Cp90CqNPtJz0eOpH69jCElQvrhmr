import re

with open("app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt", "r") as f:
    content = f.read()

target = """                // Supabase dynamic data fetching will be implemented here"""

replacement = """                // Supabase dynamic data fetching will be implemented here
                com.example.data.supabase.WildRiftSupabaseRepository.syncAllFromSupabase(context)
                
                // Recargar desde la cache recien sincronizada
                com.example.data.local.WildRiftLocalCache.loadFromLocalCache(context)
"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt", "w") as f:
        f.write(content)
    print("PATCH APPLIED")
else:
    print("TARGET NOT FOUND")
