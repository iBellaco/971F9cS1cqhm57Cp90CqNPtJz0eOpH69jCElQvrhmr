import re

file_path = "app/src/main/java/com/example/WildRiftApp.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

new_sync = """            try {
                com.example.data.supabase.SupabaseClientManager.fetchCurrentPatchVersion()
                AppLogger.d("WildRiftApp", "Parche sincronizado desde Supabase.")
            } catch (e: Exception) {
                AppLogger.e("WildRiftApp", "Error sincronizando parche desde Supabase", e)
            }
            try {
                ChineseMetaSyncService.syncChineseMeta(this@WildRiftApp, forceRefresh = true)"""

content = re.sub(r'try \{\s*AppLogger\.d\("WildRiftApp", "Sincronización con Supabase deshabilitada para uso 100% local\."\)\s*\} catch \(e: Exception\) \{\s*AppLogger\.e\("WildRiftApp", "Error sincronizando desde Supabase", e\)\s*\}\s*try \{\s*ChineseMetaSyncService\.syncChineseMeta\(this@WildRiftApp, forceRefresh = true\)', new_sync, content, flags=re.DOTALL)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
