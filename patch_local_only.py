with open("app/src/main/java/com/example/WildRiftApp.kt", "r") as f:
    text = f.read()

target = """                val prefs = getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE)
                if (!prefs.getBoolean("is_db_seeded_multi_lang_v5", false)) {
                    AppLogger.d("WildRiftApp", "Iniciando población de base de datos multi-idioma (una sola vez)...")
                    val res = com.example.data.supabase.WildRiftSupabaseRepository.seedAllDataToSupabase { c, t, m -> 
                        AppLogger.d("WildRiftApp", "Seed: $m $c/$t") 
                    }
                    if (res.isFailure) {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@WildRiftApp, "SUPABASE ERROR: ${res.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        prefs.edit().putBoolean("is_db_seeded_multi_lang_v6", true).apply()
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@WildRiftApp, "SUPABASE SYNC SUCCESSFUL", Toast.LENGTH_LONG).show()
                        }
                    }
                    AppLogger.d("WildRiftApp", "Población de base de datos completada.")
                }
                com.example.data.supabase.WildRiftSupabaseRepository.syncAllFromSupabase(this@WildRiftApp)
"""

replacement = """                AppLogger.d("WildRiftApp", "Sincronización con Supabase deshabilitada para uso 100% local.")
"""

if target in text:
    text = text.replace(target, replacement)
    with open("app/src/main/java/com/example/WildRiftApp.kt", "w") as f:
        f.write(text)
    print("Patched successfully for local only")
else:
    print("Target not found. Doing regex replace.")
    import re
    # We will just comment out the sync calls
    text = re.sub(r'com\.example\.data\.supabase\.WildRiftSupabaseRepository\.seedAllDataToSupabase', '//com.example.data.supabase.WildRiftSupabaseRepository.seedAllDataToSupabase', text)
    text = re.sub(r'com\.example\.data\.supabase\.WildRiftSupabaseRepository\.syncAllFromSupabase', '//com.example.data.supabase.WildRiftSupabaseRepository.syncAllFromSupabase', text)
    with open("app/src/main/java/com/example/WildRiftApp.kt", "w") as f:
        f.write(text)
    print("Patched via regex")
