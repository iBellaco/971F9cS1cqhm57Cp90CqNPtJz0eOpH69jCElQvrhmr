import re

file_path = "app/src/main/java/com/example/util/Translator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

pt_add = """
        "Envía fallos o sugerencias. Se guardarán directamente en la base de datos (con retención automática de 7 días):" to "Envie falhas ou sugestões. Elas serão salvas diretamente no banco de dados (com retenção automática de 7 dias):",
        "Enviar a Base de Datos" to "Enviar para o Banco de Dados",
        "Enviando a Base de Datos..." to "Enviando para o Banco de Dados...",
        "Limpiar Reportes Antiguos (> 7 días)" to "Limpar Relatórios Antigos (> 7 dias)",
        "Módulo de sincronización de la base de datos en la nube. Pulsa el botón para probar la conexión con Supabase o gestionar la retención de reportes." to "Módulo de sincronização do banco de dados na nuvem. Pressione o botão para testar a conexão com Supabase ou gerenciar a retenção de relatórios.",
"""

en_add = """
        "Envía fallos o sugerencias. Se guardarán directamente en la base de datos (con retención automática de 7 días):" to "Submit bugs or suggestions. They will be saved directly to the database (with automatic 7-day retention):",
        "Enviar a Base de Datos" to "Send to Database",
        "Enviando a Base de Datos..." to "Sending to Database...",
        "Limpiar Reportes Antiguos (> 7 días)" to "Clean Old Reports (> 7 days)",
        "Módulo de sincronización de la base de datos en la nube. Pulsa el botón para probar la conexión con Supabase o gestionar la retención de reportes." to "Cloud database synchronization module. Press the button to test the connection with Supabase or manage report retention.",
"""

content = re.sub(r'("pt" to mapOf\()', r'\1' + pt_add, content)
content = re.sub(r'("en" to mapOf\()', r'\1' + en_add, content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Updated Translator.kt successfully")
