import os

file_path = "app/src/main/java/com/example/util/Translator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

translations = """
    "Draft" to mapOf(
        "en" to "Draft",
        "pt" to "Seleção"
    ),
    "Objetivos" to mapOf(
        "en" to "Objectives",
        "pt" to "Objetivos"
    ),
    "Objetos" to mapOf(
        "en" to "Items",
        "pt" to "Itens"
    ),
    "Runas" to mapOf(
        "en" to "Runes",
        "pt" to "Runas"
    ),
    "Detener Asistente" to mapOf(
        "en" to "Stop Assistant",
        "pt" to "Parar Assistente"
    ),
    "Minimizar HUD" to mapOf(
        "en" to "Minimize HUD",
        "pt" to "Minimizar HUD"
    ),
    "1er Pick (Seguro)" to mapOf(
        "en" to "1st Pick (Safe)",
        "pt" to "1º Escolha (Segura)"
    ),
    "MEJOR OPCIÓN" to mapOf(
        "en" to "BEST PICK",
        "pt" to "MELHOR OPÇÃO"
    ),
    "Blind Pick" to mapOf(
        "en" to "Blind Pick",
        "pt" to "Escolha às Cegas"
    ),
    "Counter Pick" to mapOf(
        "en" to "Counter Pick",
        "pt" to "Counter Pick"
    ),
    "Objetos clave para" to mapOf(
        "en" to "Core items for",
        "pt" to "Itens essenciais para"
    ),
    "Runas de" to mapOf(
        "en" to "Runes for",
        "pt" to "Runas para"
    ),
    "Ver otro" to mapOf(
        "en" to "View other",
        "pt" to "Ver outro"
    ),
    "Build" to mapOf(
        "en" to "Build",
        "pt" to "Build"
    ),
    "1. Compatibilidad y Parche Oficial" to mapOf(
        "en" to "1. Compatibility and Official Patch",
        "pt" to "1. Compatibilidade e Patch Oficial"
    ),
    "2. Modo de Uso de la Aplicación" to mapOf(
        "en" to "2. How to Use the Application",
        "pt" to "2. Como Usar o Aplicativo"
    ),
    "4. Opciones de Administrador" to mapOf(
        "en" to "4. Administrator Options",
        "pt" to "4. Opções de Administrador"
    ),
    "Paso 1: Configura tus Líneas de Juego" to mapOf(
        "en" to "Step 1: Set Your Lanes",
        "pt" to "Passo 1: Configure Suas Rotas"
    ),
    "En la pantalla principal, selecciona tu 'Línea Main', 'Segunda Línea' y 'Rol Autofill' tocando cada tarjeta." to mapOf(
        "en" to "On the main screen, select your 'Main Lane', 'Secondary Lane', and 'Autofill Role' by tapping each card.",
        "pt" to "Na tela principal, selecione sua 'Rota Principal', 'Segunda Rota' e 'Função Autofill' tocando em cada cartão."
    ),
    "Paso 2: Activa el Asistente Flotante" to mapOf(
        "en" to "Step 2: Activate Floating Assistant",
        "pt" to "Passo 2: Ative o Assistente Flutuante"
    ),
    "Pulsa el botón central 'ACTIVAR'. Se desplegará la burbuja flotante en pantalla para acompañarte en tu partida." to mapOf(
        "en" to "Press the central 'ACTIVATE' button. The floating bubble will appear on screen to accompany you in your match.",
        "pt" to "Pressione o botão central 'ATIVAR'. A bolha flutuante aparecerá na tela para acompanhá-lo em sua partida."
    ),
    "Paso 3: Selección de Campeones (Champ Select)" to mapOf(
        "en" to "Step 3: Champion Selection (Champ Select)",
        "pt" to "Passo 3: Seleção de Campeões"
    ),
    "Abre Wild Rift y entra a la fase de selección. Toca el botón flotante en cualquier momento para ver recomendaciones, counters y sinergias tácticas en directo." to mapOf(
        "en" to "Open Wild Rift and enter champ select. Tap the floating button at any time to see recommendations, counters, and tactical synergies live.",
        "pt" to "Abra o Wild Rift e entre na seleção de campeões. Toque no botão flutuante a qualquer momento para ver recomendações, counters e sinergias táticas ao vivo."
    ),
    "Paso 4: Consulta de Builds y Runas" to mapOf(
        "en" to "Step 4: Check Builds and Runes",
        "pt" to "Passo 4: Consulte Builds e Runas"
    ),
    "Revisa los consejos tácticos, orden de habilidades móviles (Pasiva, 1, 2, 3, Definitiva) y armado de objetos recomendado para tu línea." to mapOf(
        "en" to "Review tactical advice, mobile skill order (Passive, 1, 2, 3, Ultimate), and recommended item build for your lane.",
        "pt" to "Revise os conselhos táticos, ordem de habilidades (Passiva, 1, 2, 3, Ultimate) e a build de itens recomendada para a sua rota."
    ),"""

content = content.replace("val dictionary = mapOf<String, Map<String, String>>(", "val dictionary = mapOf<String, Map<String, String>>(" + translations)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
print("Translator patched again!")
