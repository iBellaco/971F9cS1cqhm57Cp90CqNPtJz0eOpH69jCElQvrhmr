import re

file_path = "app/src/main/java/com/example/util/Translator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

pt_additions = """
        "Acerca De" to "Sobre",
        "Asistente Táctico Wild Rift" to "Assistente Tático Wild Rift",
        "Guía en Tiempo Real para Selección de Campeones" to "Guia em Tempo Real para Seleção de Campeões",
        "1. Acerca del Asistente" to "1. Sobre o Assistente",
        "Edición Móvil" to "Edição Móvel",
        "• Compatibilidad: Diseñado exclusivamente para Wild Rift (habilidades móviles, runas de Wild Rift, balance y objetos móviles)." to "• Compatibilidade: Projetado exclusivamente para o Wild Rift (habilidades móveis, runas do Wild Rift, balanceamento e itens móveis).",
        "• Parche del juego: " to "• Patch do jogo: ",
        "sincronizado con fuentes de balance." to "sincronizado com fontes de balanceamento.",
        "• Motor Hextech: Botón de activación directa con cálculo de composiciones, counters y sinergias." to "• Motor Hextech: Botão de ativação direta com cálculo de composições, counters e sinergias.",
        "• Sistema Flotante: Ventana superpuesta en tela con controles táctiles para la fase de selección." to "• Sistema Flutuante: Janela sobreposta na tela com controles sensíveis ao toque para a fase de seleção.",
        "• Sistema Flotante: Ventana superpuesta en pantalla con controles táctiles para la fase de selección." to "• Sistema Flutuante: Janela sobreposta na tela com controles sensíveis ao toque para a fase de seleção.",
        "2. Modo de Uso de la Aplicación" to "2. Modo de Uso do Aplicativo",
        "Paso 1: Configura tus Líneas de Juego" to "Passo 1: Configure suas Rotas de Jogo",
        "En la pantalla principal, selecciona tu 'Línea Main', 'Segunda Línea' y 'Rol Autofill' tocando cada tarjeta." to "Na tela principal, selecione sua 'Rota Principal', 'Segunda Rota' e 'Função de Preenchimento Automático' tocando em cada cartão.",
        "Paso 2: Activa el Asistente Flotante" to "Passo 2: Ative o Assistente Flutuante",
        "Pulsa el botón central 'ACTIVAR'. Se desplegará la burbuja flotante en pantalla para acompañarte en tu partida." to "Pressione o botão central 'ATIVAR'. A bolha flutuante será exibida na tela para acompanhá-lo em sua partida.",
        "Paso 3: Selección de Campeones (Champ Select)" to "Passo 3: Seleção de Campeões (Champ Select)",
        "Abre Wild Rift y entra a la fase de selección. Toca el botón flotante en cualquier momento para ver recomendaciones, counters y sinergias tácticas en directo." to "Abra o Wild Rift e entre na fase de seleção. Toque no botão flutuante a qualquer momento para ver recomendações, counters e sinergias táticas ao vivo.",
        "Paso 4: Filtros Automáticos" to "Passo 4: Filtros Automáticos",
        "El asistente prioriza campeones según los roles que hayas configurado en el Paso 1, permitiendo una selección más rápida antes de que se acabe el tiempo." to "O assistente prioriza campeões de acordo com as funções que você configurou no Passo 1, permitindo uma seleção mais rápida antes que o tempo acabe.",
        "3. Interfaz del Asistente (Overlay HUD)" to "3. Interface do Assistente (Overlay HUD)",
        "Pestaña Draft (Icono Lista)" to "Guia Draft (Ícone Lista)",
        "Recomendaciones de picks seguros, counter picks directos y sinergias basándose en tu rol." to "Recomendações de picks seguros, counter picks diretos e sinergias baseadas na sua função.",
        "Pestaña Objetivos (Icono Mapa)" to "Guia Objetivos (Ícone Mapa)",
        "Tiempos de reaparición (spawn) de los monstruos neutrales épicos." to "Tempos de reaparecimento (spawn) dos monstros neutros épicos.",
        "Pestaña Objetos (Icono Espada)" to "Guia Itens (Ícone Espada)",
        "Builds sugeridas para el campeón seleccionado (Core items y situacionales)." to "Builds sugeridas para o campeão selecionado (Core items e situacionais).",
        "Pestaña Runas (Icono Magia)" to "Guia Runas (Ícone Magia)",
        "Página de runas meta óptimas para el campeón sugerido." to "Página de runas meta ideais para o campeão sugerido.",
        "Deslizar para Minimizar" to "Deslizar para Minimizar",
        "Puedes deslizar el panel hacia abajo en cualquier momento para ocultarlo temporalmente sin detener la herramienta." to "Você pode deslizar o painel para baixo a qualquer momento para ocultá-lo temporariamente sem interromper a ferramenta.",
        "Versión del Parche de Wild Rift:" to "Versão do Patch do Wild Rift:",
        "Build y Runas de" to "Build e Runas de",
"""

en_additions = """
        "Acerca De" to "About",
        "Asistente Táctico Wild Rift" to "Wild Rift Tactical Assistant",
        "Guía en Tiempo Real para Selección de Campeones" to "Real-Time Guide for Champion Selection",
        "1. Acerca del Asistente" to "1. About the Assistant",
        "Edición Móvil" to "Mobile Edition",
        "• Compatibilidad: Diseñado exclusivamente para Wild Rift (habilidades móviles, runas de Wild Rift, balance y objetos móviles)." to "• Compatibility: Designed exclusively for Wild Rift (mobile skills, Wild Rift runes, balance, and mobile items).",
        "• Parche del juego: " to "• Game patch: ",
        "sincronizado con fuentes de balance." to "synchronized with balance sources.",
        "• Motor Hextech: Botón de activación directa con cálculo de composiciones, counters y sinergias." to "• Hextech Engine: Direct activation button calculating comps, counters, and synergies.",
        "• Sistema Flotante: Ventana superpuesta en pantalla con controles táctiles para la fase de selección." to "• Floating System: Overlay window with touch controls for the drafting phase.",
        "2. Modo de Uso de la Aplicación" to "2. How to Use the App",
        "Paso 1: Configura tus Líneas de Juego" to "Step 1: Configure Your Roles",
        "En la pantalla principal, selecciona tu 'Línea Main', 'Segunda Línea' y 'Rol Autofill' tocando cada tarjeta." to "On the main screen, select your 'Main Role', 'Secondary Role', and 'Autofill Role' by tapping each card.",
        "Paso 2: Activa el Asistente Flotante" to "Step 2: Activate Floating Assistant",
        "Pulsa el botón central 'ACTIVAR'. Se desplegará la burbuja flotante en pantalla para acompañarte en tu partida." to "Press the central 'ACTIVATE' button. The floating bubble will appear on screen to assist you in your match.",
        "Paso 3: Selección de Campeones (Champ Select)" to "Step 3: Champion Selection (Champ Select)",
        "Abre Wild Rift y entra a la fase de selección. Toca el botón flotante en cualquier momento para ver recomendaciones, counters y sinergias tácticas en directo." to "Open Wild Rift and enter champ select. Tap the floating button anytime to see live tactical recommendations, counters, and synergies.",
        "Paso 4: Filtros Automáticos" to "Step 4: Automatic Filters",
        "El asistente prioriza campeones según los roles que hayas configurado en el Paso 1, permitiendo una selección más rápida antes de que se acabe el tiempo." to "The assistant prioritizes champions based on the roles configured in Step 1, allowing faster selection before time runs out.",
        "3. Interfaz del Asistente (Overlay HUD)" to "3. Assistant Interface (Overlay HUD)",
        "Pestaña Draft (Icono Lista)" to "Draft Tab (List Icon)",
        "Recomendaciones de picks seguros, counter picks directos y sinergias basándose en tu rol." to "Safe pick recommendations, direct counter picks, and synergies based on your role.",
        "Pestaña Objetivos (Icono Mapa)" to "Objectives Tab (Map Icon)",
        "Tiempos de reaparición (spawn) de los monstruos neutrales épicos." to "Spawn times of epic neutral monsters.",
        "Pestaña Objetos (Icono Espada)" to "Items Tab (Sword Icon)",
        "Builds sugeridas para el campeón seleccionado (Core items y situacionales)." to "Suggested builds for the selected champion (Core items and situational).",
        "Pestaña Runas (Icono Magia)" to "Runes Tab (Magic Icon)",
        "Página de runas meta óptimas para el campeón sugerido." to "Optimal meta rune page for the suggested champion.",
        "Deslizar para Minimizar" to "Swipe to Minimize",
        "Puedes deslizar el panel hacia abajo en cualquier momento para ocultarlo temporalmente sin detener la herramienta." to "You can swipe the panel down anytime to temporarily hide it without stopping the tool.",
        "Versión del Parche de Wild Rift:" to "Wild Rift Patch Version:",
        "Build y Runas de" to "Build and Runes for",
"""

content = content.replace('"pt" to mapOf(', '"pt" to mapOf(\n' + pt_additions)
content = content.replace('"en" to mapOf(', '"en" to mapOf(\n' + en_additions)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Translations added for InfoScreen")
