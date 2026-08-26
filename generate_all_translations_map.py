import json, os, re

# Load existing
with open("app/src/main/assets/translations_en.json") as f:
    en_dict = json.load(f)

with open("app/src/main/assets/translations_pt.json") as f:
    pt_dict = json.load(f)

ui_translations = {
    # Main & Navigation
    "Wild Rift Coach": {"en": "Wild Rift Coach", "pt": "Wild Rift Coach"},
    "Wild Rift Assistant": {"en": "Wild Rift Assistant", "pt": "Assistente Wild Rift"},
    "Wild Rift HUD Inteligente": {"en": "Smart Wild Rift HUD", "pt": "HUD Inteligente Wild Rift"},
    "Superposición en vivo sobre Wild Rift • Toca para abrir": {"en": "Live overlay on Wild Rift • Tap to open", "pt": "Sobreposição ao vivo no Wild Rift • Toque para abrir"},
    "ACTIVAR ASISTENTE FLOTANTE": {"en": "ACTIVATE FLOATING ASSISTANT", "pt": "ATIVAR ASSISTENTE FLUTUANTE"},
    "ACTIVAR": {"en": "ACTIVATE", "pt": "ATIVAR"},
    "ACTIVO": {"en": "ACTIVE", "pt": "ATIVO"},
    "DESACTIVAR": {"en": "DEACTIVATE", "pt": "DESATIVAR"},
    "DESACTIVADO": {"en": "DISABLED", "pt": "DESATIVADO"},
    "AUTO": {"en": "AUTO", "pt": "AUTO"},
    "MANUAL": {"en": "MANUAL", "pt": "MANUAL"},
    "ASISTENTE ACTIVO": {"en": "ASSISTANT ACTIVE", "pt": "ASSISTENTE ATIVO"},
    "ASISTENTE DESACTIVADO": {"en": "ASSISTANT DISABLED", "pt": "ASSISTENTE DESATIVADO"},
    "El asistente está listo en segundo plano": {"en": "The assistant is ready in the background", "pt": "O assistente está pronto em segundo plano"},
    "Abre Wild Rift y usa el botón flotante": {"en": "Open Wild Rift and use the floating button", "pt": "Abra o Wild Rift e use o botão flutuante"},
    "Botón temporalmente desactivado": {"en": "Button temporarily disabled", "pt": "Botão temporariamente desativado"},
    "Acerca De": {"en": "About", "pt": "Sobre"},
    "Ajustes": {"en": "Settings", "pt": "Configurações"},
    "Idioma": {"en": "Language", "pt": "Idioma"},
    "Tema": {"en": "Theme", "pt": "Tema"},
    "Temas": {"en": "Themes", "pt": "Temas"},
    "Temas (13)": {"en": "Themes (13)", "pt": "Temas (13)"},
    "Personalizar Tema": {"en": "Customize Theme", "pt": "Personalizar Tema"},
    "Barra Inferior": {"en": "Bottom Bar", "pt": "Barra Inferior"},
    "Barra:": {"en": "Bar:", "pt": "Barra:"},
    "Base:": {"en": "Base:", "pt": "Base:"},
    "Cerrar": {"en": "Close", "pt": "Fechar"},
    "Cancelar": {"en": "Cancel", "pt": "Cancelar"},
    "Aceptar": {"en": "Accept", "pt": "Aceitar"},
    "Guardar": {"en": "Save", "pt": "Salvar"},
    "Confirmar": {"en": "Confirm", "pt": "Confirmar"},
    "Continuar": {"en": "Continue", "pt": "Continuar"},
    "Elige tu idioma": {"en": "Choose your language", "pt": "Escolha seu idioma"},
    "Selecciona el idioma del asistente": {"en": "Select the assistant language", "pt": "Selecione o idioma do assistente"},
    "Español": {"en": "Spanish", "pt": "Espanhol"},
    "English": {"en": "English", "pt": "Inglês"},
    "Português": {"en": "Portuguese", "pt": "Português"},
    "Salir": {"en": "Exit", "pt": "Sair"},
    "¿Deseas salir de la aplicación?": {"en": "Do you want to exit the application?", "pt": "Deseja sair do aplicativo?"},
    "Presiona confirmar para cerrar Wild Rift Coach": {"en": "Press confirm to close Wild Rift Coach", "pt": "Pressione confirmar para fechar o Wild Rift Coach"},
    
    # Permissions & Battery Dialog
    "Permiso de Superposición": {"en": "Overlay Permission", "pt": "Permissão de Sobreposição"},
    "Para que el asistente inteligente funcione en segundo plano sobre Wild Rift, Android requiere habilitar 'Aparecer encima' (Superposición).": {
        "en": "For the smart assistant to work in the background over Wild Rift, Android requires enabling 'Display over other apps' (Overlay).",
        "pt": "Para que o assistente inteligente funcione em segundo plano sobre o Wild Rift, o Android requer a ativação de 'Exibir sobre outros aplicativos' (Sobreposição)."
    },
    "PermisoSuperposicionTexto": {
        "en": "1. Tap on 'Grant Permission'.\n2. Enable the toggle for Wild Rift Coach.\n3. Return to the app and press ACTIVATE.",
        "pt": "1. Toque em 'Conceder Permissão'.\n2. Ative a chave para o Wild Rift Coach.\n3. Volte ao aplicativo e pressione ATIVAR."
    },
    "Conceder Permiso": {"en": "Grant Permission", "pt": "Conceder Permissão"},
    "1. Permiso de Superposición": {"en": "1. Overlay Permission", "pt": "1. Permissão de Sobreposição"},
    "2. Desactivar Ahorro de Batería": {"en": "2. Disable Battery Saver", "pt": "2. Desativar Economia de Bateria"},
    "2. Modo de Uso de la Aplicación": {"en": "2. How to Use the App", "pt": "2. Modo de Uso do Aplicativo"},
    "1. Compatibilidad y Parche Oficial": {"en": "1. Compatibility & Official Patch", "pt": "1. Compatibilidade e Patch Oficial"},
    "3. Temas y Barra de Navegación": {"en": "3. Themes and Navigation Bar", "pt": "3. Temas e Barra de Navegação"},
    "Abre Wild Rift y entra a la fase de selección. Toca el botón flotante en cualquier momento para ver recomendaciones, counters y sinergias tácticas en directo.": {
        "en": "Open Wild Rift and enter champion select. Tap the floating button at any time to view live tactical recommendations, counters, and synergies.",
        "pt": "Abra o Wild Rift e entre na seleção de campeões. Toque no botão flutuante a qualquer momento para ver recomendações táticas, counters e sinergias ao vivo."
    },
    "Para evitar que Android cierre el asistente flotante en medio de la partida, ve a Ajustes > Batería > Wild Rift Coach y selecciona 'Sin restricciones'.": {
        "en": "To prevent Android from killing the floating assistant during matches, go to Settings > Battery > Wild Rift Coach and select 'Unrestricted'.",
        "pt": "Para evitar que o Android feche o assistente flutuante durante as partidas, vá em Configurações > Bateria > Wild Rift Coach e selecione 'Sem restrições'."
    },
    "Desactivar Ahorro": {"en": "Disable Battery Saver", "pt": "Desativar Economia"},
    "Configurar Batería": {"en": "Configure Battery", "pt": "Configurar Bateria"},
    
    # Roles & Positions
    "Rol Principal:": {"en": "Primary Role:", "pt": "Função Principal:"},
    "Rol Secundario:": {"en": "Secondary Role:", "pt": "Função Secundária:"},
    "Comodín / Autofill:": {"en": "Fill / Autofill:", "pt": "Preenchimento Automático:"},
    "Línea de Barón (Top)": {"en": "Baron Lane (Top)", "pt": "Rota do Barão (Top)"},
    "Jungla": {"en": "Jungle", "pt": "Selva"},
    "Línea del Medio (Mid)": {"en": "Mid Lane", "pt": "Rota do Meio (Mid)"},
    "Línea de Dragón (ADC)": {"en": "Dragon Lane (ADC)", "pt": "Rota do Dragão (ADC)"},
    "Soporte": {"en": "Support", "pt": "Suporte"},
    "Línea de Barón": {"en": "Baron Lane", "pt": "Rota do Barão"},
    "Línea de Dragón": {"en": "Dragon Lane", "pt": "Rota do Dragão"},
    "Línea del Medio": {"en": "Mid Lane", "pt": "Rota do Meio"},
    "TOP": {"en": "TOP", "pt": "TOP"},
    "JUNGLA": {"en": "JUNGLE", "pt": "SELVA"},
    "MID": {"en": "MID", "pt": "MID"},
    "DÚO": {"en": "DUO", "pt": "DUO"},
    "ADC": {"en": "ADC", "pt": "ADC"},
    "SOPORTE": {"en": "SUPPORT", "pt": "SUPORTE"},
    "Cambiar Línea / Rol Activo:": {"en": "Change Active Lane / Role:", "pt": "Mudar Rota / Função Ativa:"},
    "Estadísticas, hechizos, runas y build adaptadas a": {"en": "Stats, spells, runes, and build adapted to", "pt": "Estatísticas, feitiços, runas e build adaptados para"},
    "Main": {"en": "Main", "pt": "Principal"},
    "Flex": {"en": "Flex", "pt": "Flex"},
    "👑 TÚ": {"en": "👑 YOU", "pt": "👑 VOCÊ"},
    "Aliado": {"en": "Ally", "pt": "Aliado"},
    "Enemigo": {"en": "Enemy", "pt": "Inimigo"},
    "Equipo Aliado": {"en": "Ally Team", "pt": "Equipe Aliada"},
    "Equipo Rival": {"en": "Enemy Team", "pt": "Equipe Inimiga"},
    
    # Meta, Tier list & Draft
    "Tier List": {"en": "Tier List", "pt": "Tier List"},
    "Meta & Tier List": {"en": "Meta & Tier List", "pt": "Meta & Tier List"},
    "Meta": {"en": "Meta", "pt": "Meta"},
    "Draft": {"en": "Draft", "pt": "Draft"},
    "Drafting": {"en": "Drafting", "pt": "Drafting"},
    "Selección de Campeones": {"en": "Champion Select", "pt": "Seleção de Campeões"},
    "Todos": {"en": "All", "pt": "Todos"},
    "Campeones": {"en": "Champions", "pt": "Campeões"},
    "Objetos": {"en": "Items", "pt": "Itens"},
    "Runas": {"en": "Runes", "pt": "Runas"},
    "Hechizos": {"en": "Spells", "pt": "Feitiços"},
    "Objetivos": {"en": "Objectives", "pt": "Objetivos"},
    "Calculadora": {"en": "Calculator", "pt": "Calculadora"},
    "Calculadora de Daño y Penetración": {"en": "Damage & Penetration Calculator", "pt": "Calculadora de Dano e Penetração"},
    "Tasa de Victoria": {"en": "Win Rate", "pt": "Taxa de Vitória"},
    "Tasa de Selección": {"en": "Pick Rate", "pt": "Taxa de Escolha"},
    "Tasa de Bloqueo": {"en": "Ban Rate", "pt": "Taxa de Banimento"},
    "Winrate": {"en": "Winrate", "pt": "Winrate"},
    "Pick": {"en": "Pick", "pt": "Pick"},
    "Ban": {"en": "Ban", "pt": "Ban"},
    "Tendencia en Vivo": {"en": "Live Trend", "pt": "Tendência ao Vivo"},
    "Estadísticas del Meta Oficial": {"en": "Official Meta Stats", "pt": "Estatísticas do Meta Oficial"},
    "Análisis Táctico en Wild Rift": {"en": "Tactical Analysis in Wild Rift", "pt": "Análise Tática no Wild Rift"},
    "1ª Elección Segura": {"en": "1st Safe Pick", "pt": "1ª Escolha Segura"},
    "Elección Segura": {"en": "Safe Pick", "pt": "Escolha Segura"},
    "Recomendación de Picks": {"en": "Pick Recommendations", "pt": "Recomendações de Escolha"},
    "Mejores Sinergias": {"en": "Best Synergies", "pt": "Melhores Sinergias"},
    "Counters Fuertes": {"en": "Strong Counters", "pt": "Counters Fortes"},
    "Bueno contra:": {"en": "Good against:", "pt": "Bom contra:"},
    "Débil contra:": {"en": "Weak against:", "pt": "Fraco contra:"},
    "Sinergia con:": {"en": "Synergy with:", "pt": "Sinergia com:"},
    "Ventaja": {"en": "Advantage", "pt": "Vantagem"},
    "Debilidad": {"en": "Threat / Weakness", "pt": "Ameaça / Fraqueza"},
    "Sinergia": {"en": "Synergy", "pt": "Sinergia"},
    "Situacional": {"en": "Situational", "pt": "Situacional"},
    "Alerta Táctica de Matchup": {"en": "Tactical Matchup Alert", "pt": "Alerta Tático de Confronto"},
    "Balance de Daño Aliado": {"en": "Ally Damage Balance", "pt": "Balanço de Dano Aliado"},
    "Balance de Daño Rival": {"en": "Enemy Damage Balance", "pt": "Balanço de Dano Inimigo"},
    "Daño Físico": {"en": "Physical Damage", "pt": "Dano Físico"},
    "Daño Mágico": {"en": "Magic Damage", "pt": "Dano Mágico"},
    "Daño Verdadero": {"en": "True Damage", "pt": "Dano Verdadeiro"},
    "Físico": {"en": "Physical", "pt": "Físico"},
    "Mágico": {"en": "Magic", "pt": "Mágico"},
    "Verdadero": {"en": "True", "pt": "Verdadeiro"},
    "Mixto": {"en": "Mixed", "pt": "Misto"},
    "Tanquear": {"en": "Tank", "pt": "Tanque"},
    
    # Skills, Items, Runes, Spells Details
    "Habilidades de": {"en": "Abilities of", "pt": "Habilidades de"},
    "Habilidades": {"en": "Abilities", "pt": "Habilidades"},
    "Pasiva": {"en": "Passive", "pt": "Passiva"},
    "Pasiva:": {"en": "Passive:", "pt": "Passiva:"},
    "Habilidad": {"en": "Ability", "pt": "Habilidade"},
    "Definitiva": {"en": "Ultimate", "pt": "Ultimate"},
    "Definitiva:": {"en": "Ultimate:", "pt": "Ultimate:"},
    "Build de": {"en": "Build of", "pt": "Build de"},
    "Build": {"en": "Build", "pt": "Build"},
    "Build Completa (6 Objetos):": {"en": "Full Build (6 Items):", "pt": "Build Completa (6 Itens):"},
    "Objetos Situacionales / Alternativos:": {"en": "Situational / Alternative Items:", "pt": "Itens Situacionais / Alternativos:"},
    "Reemplazos Situacionales Recomendados:": {"en": "Recommended Situational Swaps:", "pt": "Substituições Situacionais Recomendadas:"},
    "Reemplaza": {"en": "Replaces", "pt": "Substitui"},
    "por": {"en": "with", "pt": "por"},
    "Hechizos de Invocador": {"en": "Summoner Spells", "pt": "Feitiços de Invocador"},
    "Hechizos Recomendados para esta Línea:": {"en": "Recommended Spells for this Lane:", "pt": "Feitiços Recomendados para esta Rota:"},
    "Configuración de Runas para esta Línea:": {"en": "Rune Setup for this Lane:", "pt": "Configuração de Runas para esta Rota:"},
    "Runa Principal (Keystone)": {"en": "Keystone Rune", "pt": "Runa Principal (Keystone)"},
    "Rama Secundaria": {"en": "Secondary Tree", "pt": "Ramo Secundário"},
    "Consejo del Coach:": {"en": "Coach Tip:", "pt": "Dica do Coach:"},
    "Consejo Táctico de Línea:": {"en": "Lane Tactical Tip:", "pt": "Dica Tática de Rota:"},
    "💡 Toca situacionales": {"en": "💡 Tap situational items", "pt": "💡 Toque nos itens situacionais"},
    "Toca para ver build y runas": {"en": "Tap to view build and runes", "pt": "Toque para ver build e runas"},
    "Buscar campeón...": {"en": "Search champion...", "pt": "Buscar campeão..."},
    "Buscar campeón fijado...": {"en": "Search locked champion...", "pt": "Buscar campeão fixado..."},
    "Buscar objeto por nombre o estadísticas...": {"en": "Search item by name or stats...", "pt": "Buscar item por nome ou estatísticas..."},
    "Buscar runa (ej. Conquistador, Banda de Flujo)...": {"en": "Search rune (e.g. Conqueror, Manaflow)...", "pt": "Buscar runa (ex. Conquistador, Faixa de Fluxo)..."},
    "Buscar hechizo (ej. Destello, Prender, Castigo)...": {"en": "Search spell (e.g. Flash, Ignite, Smite)...", "pt": "Buscar feitiço (ex. Flash, Incendiar, Golpear)..."},
    "Buscar por título, contenido o modelo...": {"en": "Search by title, content or model...", "pt": "Buscar por título, conteúdo ou modelo..."},
    
    # Calculator
    "Defensa Efectiva": {"en": "Effective Defense", "pt": "Defesa Efetiva"},
    "Reducción de Daño": {"en": "Damage Reduction", "pt": "Redução de Dano"},
    "Daño Real Infligido:": {"en": "Actual Damage Dealt:", "pt": "Dano Real Causado:"},
    "Veredicto del Coach de Élite:": {"en": "Elite Coach Verdict:", "pt": "Veredito do Coach de Elite:"},
    "Armadura": {"en": "Armor", "pt": "Armadura"},
    "Resistencia Mágica": {"en": "Magic Resist", "pt": "Resistência Mágica"},
    "Penetración Porcentual (%)": {"en": "Percentage Penetration (%)", "pt": "Penetração Porcentual (%)"},
    "Penetración Plana (Letalidad / Flat)": {"en": "Flat Penetration (Lethality / Flat)", "pt": "Penetração Plana (Letalidade / Flat)"},
    "Daño Base de la Habilidad / Ataque": {"en": "Base Ability / Attack Damage", "pt": "Dano Base da Habilidade / Ataque"},
    
    # Cooldowns & Overlays
    "Rastreador de Enfriamientos": {"en": "Cooldown Tracker", "pt": "Rastreador de Tempos de Recarga"},
    "Destello (Flash)": {"en": "Flash", "pt": "Flash"},
    "Ignición (Ignite)": {"en": "Ignite", "pt": "Incendiar"},
    "Curar (Heal)": {"en": "Heal", "pt": "Curar"},
    "Barrera (Barrier)": {"en": "Barrier", "pt": "Barreira"},
    "Extenuación (Exhaust)": {"en": "Exhaust", "pt": "Exaustão"},
    "Fantasmal (Ghost)": {"en": "Ghost", "pt": "Fantasma"},
    "Castigo (Smite)": {"en": "Smite", "pt": "Golpear"},
    "Definitiva (R)": {"en": "Ultimate (R)", "pt": "Ultimate (R)"},
    "Enfriamiento:": {"en": "Cooldown:", "pt": "Tempo de Recarga:"},
    "Listo": {"en": "Ready", "pt": "Pronto"},
    
    # Objectives & Timers
    "Dragón Infernal": {"en": "Infernal Dragon", "pt": "Dragão Infernal"},
    "Dragón de la Montaña": {"en": "Mountain Dragon", "pt": "Dragão da Montanha"},
    "Dragón de los Océanos": {"en": "Ocean Dragon", "pt": "Dragão do Oceano"},
    "Dragón de las Nubes": {"en": "Cloud Dragon", "pt": "Dragão das Nuvens"},
    "Dragón de Hielo": {"en": "Ice Dragon", "pt": "Dragão do Gelo"},
    "Dragón Ancestral": {"en": "Elder Dragon", "pt": "Dragão Ancião"},
    "Heraldo de la Grieta": {"en": "Rift Herald", "pt": "Arauto do Vale"},
    "Barón Nashor": {"en": "Baron Nashor", "pt": "Barão Nashor"},
    "T-Hex Mecha": {"en": "T-Hex Mecha", "pt": "T-Hex Mecha"},
    
    # Feedback & Bugs
    "Reportar Error / Sugerencia": {"en": "Report Bug / Suggestion", "pt": "Reportar Erro / Sugestão"},
    "Bugs": {"en": "Bugs", "pt": "Erros"},
    "Sugerencias": {"en": "Suggestions", "pt": "Sugestões"},
    "Enviar Reporte": {"en": "Send Report", "pt": "Enviar Relatório"},
    "Feedback Report": {"en": "Feedback Report", "pt": "Relatório de Feedback"},
    "Añadir": {"en": "Add", "pt": "Adicionar"},
    
    # Information & Patch Notes
    "Información": {"en": "Information", "pt": "Informação"},
    "Parche Actual:": {"en": "Current Patch:", "pt": "Patch Atual:"},
    "Notas del Parche": {"en": "Patch Notes", "pt": "Notas do Patch"},
    "Versión:": {"en": "Version:", "pt": "Versão:"},
    "Compilación:": {"en": "Build:", "pt": "Compilação:"},
    "Desarrollado con ❤️ para la comunidad de Wild Rift": {
        "en": "Developed with ❤️ for the Wild Rift community",
        "pt": "Desenvolvido com ❤️ para a comunidade de Wild Rift"
    }
}

for k, trans in ui_translations.items():
    en_dict[k] = trans["en"]
    pt_dict[k] = trans["pt"]

with open("app/src/main/assets/translations_en.json", "w") as f:
    json.dump(en_dict, f, ensure_ascii=False, indent=2)

with open("app/src/main/assets/translations_pt.json", "w") as f:
    json.dump(pt_dict, f, ensure_ascii=False, indent=2)

print(f"Updated translations_en.json (Total keys: {len(en_dict)})")
print(f"Updated translations_pt.json (Total keys: {len(pt_dict)})")
