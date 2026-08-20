with open("app/src/main/java/com/example/util/Translator.kt", "r") as f:
    content = f.read()

pt_add = '''
        "Estás utilizando la versión más reciente" to "Você está usando a versão mais recente",
        "release_notes_fallback" to "• Atualização do novo ícone emblemático.\\n• Compatibilidade de instalação sem conflito de versão.\\n• Melhorias gerais de estabilidade e assistente ao vivo.",
        "✔ Sin restricciones (No se cerrará en segundo plano)" to "✔ Sem restrições (Não fechará em segundo plano)",
        "✘ Optimizado: Toca para quitar restricción" to "✘ Otimizado: Toque para remover restrição",
        "✔ Concedido (Ventana flotante habilitada)" to "✔ Concedido (Janela flutuante ativada)",
        "✘ Pendiente: Toca para autorizar" to "✘ Pendente: Toque para autorizar",
        "Configuración para Segundo Plano" to "Configurações de Segundo Plano",
        "Recomendaciones para funcionamiento óptimo sobre Wild Rift" to "Recomendações para operação ideal sobre o Wild Rift",
        "Para que el asistente flote en tiempo real sobre tu partida de Wild Rift sin que Android cierre el proceso por consumo de memoria:" to "Para que o assistente flutue em tempo real sobre o seu jogo Wild Rift sem que o Android feche o processo devido ao consumo de memória:",
        "1. Permiso de Superposición" to "1. Permissão de Sobreposição",
        "2. Desactivar Ahorro de Batería" to "2. Desativar Economia de Bateria",
        "Conceder Permiso" to "Conceder Permissão",
        "Detener Flotante" to "Parar Flutuante",
        "Lanzar Flotante en Juego" to "Iniciar Flutuante no Jogo",
'''

en_add = '''
        "Estás utilizando la versión más reciente" to "You are using the latest version",
        "release_notes_fallback" to "• Update of the new flagship icon.\\n• Installation compatibility without version conflict.\\n• General stability improvements and live assistant.",
        "✔ Sin restricciones (No se cerrará en segundo plano)" to "✔ Unrestricted (Will not close in background)",
        "✘ Optimizado: Toca para quitar restricción" to "✘ Optimized: Tap to remove restriction",
        "✔ Concedido (Ventana flotante habilitada)" to "✔ Granted (Floating window enabled)",
        "✘ Pendiente: Toca para autorizar" to "✘ Pending: Tap to authorize",
        "Configuración para Segundo Plano" to "Background Settings",
        "Recomendaciones para funcionamiento óptimo sobre Wild Rift" to "Recommendations for optimal operation over Wild Rift",
        "Para que el asistente flote en tiempo real sobre tu partida de Wild Rift sin que Android cierre el proceso por consumo de memoria:" to "For the assistant to float in real time over your Wild Rift game without Android closing the process due to memory consumption:",
        "1. Permiso de Superposición" to "1. Overlay Permission",
        "2. Desactivar Ahorro de Batería" to "2. Disable Battery Saver",
        "Conceder Permiso" to "Grant Permission",
        "Detener Flotante" to "Stop Floating",
        "Lanzar Flotante en Juego" to "Launch Floating in Game",
'''

# insert into pt dictionary
content = content.replace('val pt = mapOf(', 'val pt = mapOf(' + pt_add)
content = content.replace('val en = mapOf(', 'val en = mapOf(' + en_add)

with open("app/src/main/java/com/example/util/Translator.kt", "w") as f:
    f.write(content)
