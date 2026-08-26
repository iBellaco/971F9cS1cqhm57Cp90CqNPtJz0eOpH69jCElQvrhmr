with open("app/src/main/java/com/example/WildRiftApp.kt", "r") as f:
    text = f.read()

text = text.replace('is_db_seeded_multi_lang_v2', 'is_db_seeded_multi_lang_v3')

import_toast = "import android.widget.Toast\nimport android.os.Handler\nimport android.os.Looper"
if "import android.widget.Toast" not in text:
    text = text.replace("import android.app.Application", "import android.app.Application\n" + import_toast)

target = """                    com.example.data.supabase.WildRiftSupabaseRepository.seedAllDataToSupabase { c, t, m -> 
                        AppLogger.d("WildRiftApp", "Seed: $m $c/$t") 
                    }"""

replacement = """                    val res = com.example.data.supabase.WildRiftSupabaseRepository.seedAllDataToSupabase { c, t, m -> 
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
                    }"""

if target in text:
    text = text.replace(target, replacement)
    with open("app/src/main/java/com/example/WildRiftApp.kt", "w") as f:
        f.write(text)
    print("Patched v3 successfully")
else:
    print("Target not found")
