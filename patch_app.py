with open("app/src/main/java/com/example/WildRiftApp.kt", "r") as f:
    text = f.read()

target = """        CoroutineScope(Dispatchers.IO + handler).launch {
            try {
                com.example.data.supabase.WildRiftSupabaseRepository.syncAllFromSupabase(this@WildRiftApp)"""

replacement = """        CoroutineScope(Dispatchers.IO + handler).launch {
            try {
                val prefs = getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE)
                if (!prefs.getBoolean("is_db_seeded_multi_lang_v2", false)) {
                    AppLogger.d("WildRiftApp", "Iniciando población de base de datos multi-idioma (una sola vez)...")
                    com.example.data.supabase.WildRiftSupabaseRepository.seedAllDataToSupabase { c, t, m -> 
                        AppLogger.d("WildRiftApp", "Seed: $m $c/$t") 
                    }
                    prefs.edit().putBoolean("is_db_seeded_multi_lang_v2", true).apply()
                    AppLogger.d("WildRiftApp", "Población de base de datos completada.")
                }
                com.example.data.supabase.WildRiftSupabaseRepository.syncAllFromSupabase(this@WildRiftApp)"""

if target in text:
    text = text.replace(target, replacement)
    with open("app/src/main/java/com/example/WildRiftApp.kt", "w") as f:
        f.write(text)
    print("Patched successfully")
else:
    print("Target not found")
