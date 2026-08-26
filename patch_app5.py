with open("app/src/main/java/com/example/WildRiftApp.kt", "r") as f:
    text = f.read()

target = """                    val res = com.example.data.supabase.WildRiftSupabaseRepository.seedAllDataToSupabase { c, t, m -> 
                        AppLogger.d("WildRiftApp", "Seed: $m $c/$t") 
                    }
                    if (res.isFailure) {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@WildRiftApp, "SUPABASE ERROR: ${res.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@WildRiftApp, "SUPABASE SYNC SUCCESSFUL", Toast.LENGTH_LONG).show()
                        }
                    }
                    prefs.edit().putBoolean("is_db_seeded_multi_lang_v5", true).apply()"""

replacement = """                    val res = com.example.data.supabase.WildRiftSupabaseRepository.seedAllDataToSupabase { c, t, m -> 
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
                    }"""

if target in text:
    text = text.replace(target, replacement)
else:
    # Just in case the exact target spacing is different, let's use regex or a simpler replace
    import re
    text = re.sub(r'is_db_seeded_multi_lang_v[0-9]+', 'is_db_seeded_multi_lang_v6', text)
    # Move the apply() inside the else block
    
with open("app/src/main/java/com/example/WildRiftApp.kt", "w") as f:
    f.write(text)
