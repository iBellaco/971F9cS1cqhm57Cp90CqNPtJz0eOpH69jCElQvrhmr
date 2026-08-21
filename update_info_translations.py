import re
file_path = "app/src/main/java/com/example/util/Translator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

pt_add = """
        "Estado del Servidor (Supabase)" to "Status do Servidor (Supabase)",
        "Módulo de sincronización de la base de datos en la nube. Pulsa el botón para probar la conexión con Supabase." to "Módulo de sincronização do banco de dados na nuvem. Pressione o botão para testar a conexão com Supabase.",
        "Conectando con Supabase..." to "Conectando ao Supabase...",
        "✅ Conexión exitosa. Cliente inicializado." to "✅ Conexão bem-sucedida. Cliente inicializado.",
        "❌ Error de conexión: " to "❌ Erro de conexão: ",
        "Verificar Conexión Supabase" to "Verificar Conexão Supabase",
"""

en_add = """
        "Estado del Servidor (Supabase)" to "Server Status (Supabase)",
        "Módulo de sincronización de la base de datos en la nube. Pulsa el botón para probar la conexión con Supabase." to "Cloud database synchronization module. Press the button to test the connection with Supabase.",
        "Conectando con Supabase..." to "Connecting to Supabase...",
        "✅ Conexión exitosa. Cliente inicializado." to "✅ Successful connection. Client initialized.",
        "❌ Error de conexión: " to "❌ Connection error: ",
        "Verificar Conexión Supabase" to "Verify Supabase Connection",
"""

content = re.sub(r'("pt" to mapOf\()', r'\1' + pt_add, content)
content = re.sub(r'("en" to mapOf\()', r'\1' + en_add, content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
