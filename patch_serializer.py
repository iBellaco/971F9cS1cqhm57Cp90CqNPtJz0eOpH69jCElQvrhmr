with open("app/src/main/java/com/example/data/supabase/SupabaseClientManager.kt", "r") as f:
    text = f.read()

import_statement = "import kotlinx.serialization.json.Json\nimport io.github.jan.supabase.serializer.KotlinXSerializer\n"
if "import kotlinx.serialization.json.Json" not in text:
    text = text.replace("import io.github.jan.supabase.postgrest.Postgrest", import_statement + "import io.github.jan.supabase.postgrest.Postgrest")

target = """                val newClient = createSupabaseClient(
                    supabaseUrl = url,
                    supabaseKey = key
                ) {
                    install(Postgrest)
                    install(Auth)
                }"""

replacement = """                val newClient = createSupabaseClient(
                    supabaseUrl = url,
                    supabaseKey = key
                ) {
                    install(Postgrest)
                    install(Auth)
                    defaultSerializer = KotlinXSerializer(Json {
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    })
                }"""

if target in text:
    text = text.replace(target, replacement)
    with open("app/src/main/java/com/example/data/supabase/SupabaseClientManager.kt", "w") as f:
        f.write(text)
    print("Patched serializer successfully")
else:
    print("Target not found")
