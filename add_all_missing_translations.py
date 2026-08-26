import json

with open("app/src/main/assets/translations_en.json") as f:
    en_dict = json.load(f)

with open("app/src/main/assets/translations_pt.json") as f:
    pt_dict = json.load(f)

new_translations = {
    "Buzón de Reportes & Ideas": {"en": "Reports & Ideas Mailbox", "pt": "Caixa de Relatórios e Ideias"},
    "CAMBIOS SITUACIONALES": {"en": "SITUATIONAL SWAPS", "pt": "MUDANÇAS SITUACIONAIS"},
    "CD Tracker:": {"en": "CD Tracker:", "pt": "Rastreador de CD:"},
    "CDs": {"en": "CDs", "pt": "CDs"},
    "Caché:": {"en": "Cache:", "pt": "Cache:"},
    "Calculadora de Daño & Penetración": {"en": "Damage & Penetration Calculator", "pt": "Calculadora de Dano & Penetração"},
    "Cambiar Tema": {"en": "Change Theme", "pt": "Mudar Tema"},
    "Cambiar línea": {"en": "Change lane", "pt": "Mudar rota"},
    "Cargando reportes...": {"en": "Loading reports...", "pt": "Carregando relatórios..."},
    "Catálogo": {"en": "Catalog", "pt": "Catálogo"},
    "Cerrando...": {"en": "Closing...", "pt": "Fechando..."},
    "Clasificación por": {"en": "Sort by", "pt": "Classificar por"},
    "Completado": {"en": "Completed", "pt": "Concluído"},
    "Completados": {"en": "Completed", "pt": "Concluídos"},
    "Composición rival de pokeo y rango": {"en": "Enemy poke & range composition", "pt": "Composição inimiga de poke e alcance"},
    "Composición rival pesada/tanque": {"en": "Heavy tank enemy composition", "pt": "Composição inimiga pesada/tanque"},
    "Configuración para Segundo Plano": {"en": "Background Settings", "pt": "Configurações em Segundo Plano"},
    "Counter Pick": {"en": "Counter Pick", "pt": "Counter Pick"},
    "Cuadrícula": {"en": "Grid", "pt": "Grade"},
    "DETENER": {"en": "STOP", "pt": "PARAR"},
    "Daño": {"en": "Damage", "pt": "Dano"},
    "Descarga Pausada": {"en": "Download Paused", "pt": "Download Pausado"},
    "Descargando": {"en": "Downloading", "pt": "Baixando"},
    "Descargando Recursos": {"en": "Downloading Resources", "pt": "Baixando Recursos"},
    "Descargar Recursos": {"en": "Download Resources", "pt": "Baixar Recursos"},
    "Descargar e Instalar": {"en": "Download and Install", "pt": "Baixar e Instalar"},
    "Describe qué sucedió o cómo reproducir el error...": {"en": "Describe what happened or how to reproduce the bug...", "pt": "Descreva o que aconteceu ou como reproduzir o erro..."},
    "Describe tu idea o mejora para la aplicación...": {"en": "Describe your idea or enhancement for the app...", "pt": "Descreva sua ideia ou melhoria para o aplicativo..."},
    "Descripción detallada": {"en": "Detailed description", "pt": "Descrição detalhada"},
    "Detallado": {"en": "Detailed", "pt": "Detalhado"},
    "Detener Asistente": {"en": "Stop Assistant", "pt": "Parar Assistente"},
    "Detener Flotante": {"en": "Stop Floating Assistant", "pt": "Parar Flutuante"},
    "Dispositivo:": {"en": "Device:", "pt": "Dispositivo:"},
    "Donaciones": {"en": "Donations", "pt": "Doações"},
    "Débil Contra:": {"en": "Weak Against:", "pt": "Fraco Contra:"},
    "ELEGIR": {"en": "CHOOSE", "pt": "ESCOLHER"},
    "Efectivo contra:": {"en": "Effective against:", "pt": "Eficaz contra:"},
    "Efecto / Pasiva:": {"en": "Effect / Passive:", "pt": "Efeito / Passiva:"},
    "Efecto clave:": {"en": "Key effect:", "pt": "Efeito chave:"},
    "Ej: Agregar temporizador de dragones con audio": {"en": "Eg: Add audio timer for dragons", "pt": "Ex: Adicionar temporizador de dragões com áudio"},
    "Ej: El overlay no detecta la pantalla de selección": {"en": "Eg: The overlay does not detect champion select", "pt": "Ex: A sobreposição não detecta a seleção de campeões"},
    "El enemigo es predominantemente daño Físico (AD).": {"en": "The enemy is predominantly Physical damage (AD).", "pt": "O inimigo é predominantemente Dano Físico (AD)."},
    "El enemigo es predominantemente daño Mágico (AP).": {"en": "The enemy is predominantly Magic damage (AP).", "pt": "O inimigo é predominantemente Dano Mágico (AP)."},
    "Elegir como mi Pick": {"en": "Choose as my Pick", "pt": "Escolher como meu Pick"},
    "Elige tu estética visual y barra de navegación": {"en": "Choose your visual theme and navigation bar", "pt": "Escolha seu tema visual e barra de navegação"},
    "Eliminar": {"en": "Delete", "pt": "Excluir"},
    "En la pantalla principal, selecciona tu 'Línea Main', 'Segunda Línea' y 'Rol Autofill' tocando cada tarjeta.": {
        "en": "On the main screen, select your 'Main Lane', 'Secondary Lane', and 'Autofill Role' by tapping each card.",
        "pt": "Na tela principal, selecione sua 'Rota Principal', 'Segunda Rota' e 'Função Autofill' tocando em cada cartão."
    },
    "En vivo:": {"en": "Live:", "pt": "Ao vivo:"},
    "Entendido": {"en": "Understood", "pt": "Entendido"},
    "Enviando mensaje...": {"en": "Sending message...", "pt": "Enviando mensagem..."},
    "Enviar reporte": {"en": "Send report", "pt": "Enviar relatório"},
    "Enviar sugerencia": {"en": "Send suggestion", "pt": "Enviar sugestão"},
    "Envía tus reportes de fallos o sugerencias para seguir mejorando la aplicación:": {
        "en": "Send your bug reports or suggestions to keep improving the app:",
        "pt": "Envie seus relatórios de erros ou sugestões para continuar melhorando o aplicativo:"
    },
    "Error al cargar:": {"en": "Error loading:", "pt": "Erro ao carregar:"},
    "Esta acción borrará permanentemente el reporte:": {"en": "This action will permanently delete the report:", "pt": "Esta ação apagará permanentemente o relatório:"},
    "Estadísticas:": {"en": "Stats:", "pt": "Estatísticas:"},
    "Evita asedios lentos. Requiere hard engage, emboscada o flanqueos rápidos.": {
        "en": "Avoid slow sieges. Requires hard engage, ambushes, or rapid flanks.",
        "pt": "Evite cercos lentos. Requer iniciação forte, emboscadas ou flancos rápidos."
    },
    "Exceso de Daño Físico aliado (AD).": {"en": "Excess ally Physical Damage (AD).", "pt": "Excesso de Dano Físico aliado (AD)."},
    "Exceso de Daño Mágico aliado (AP).": {"en": "Excess ally Magic Damage (AP).", "pt": "Excesso de Dano Mágico aliado (AP)."},
    "Fijado": {"en": "Locked", "pt": "Fixado"},
    "Flex en ": {"en": "Flex in ", "pt": "Flex em "},
    "Flex: ": {"en": "Flex: ", "pt": "Flex: "},
    "Gastado": {"en": "On Cooldown", "pt": "Gasto"},
    "Gestión de Mensajes & Reportes": {"en": "Message & Report Management", "pt": "Gerenciamento de Mensagens e Relatórios"},
    "Guardar en Dispositivo": {"en": "Save to Device", "pt": "Salvar no Dispositivo"},
    "Hechizos & Orden de Habilidades": {"en": "Spells & Ability Max Order", "pt": "Feitiços e Ordem de Habilidades"},
    "Hechizos de": {"en": "Spells of", "pt": "Feitiços de"},
    "Hechizos de Invocador Recomendados": {"en": "Recommended Summoner Spells", "pt": "Feitiços de Invocador Recomendados"},
    "Ideas": {"en": "Ideas", "pt": "Ideias"},
    "Imprescindible Zhonya/Estasis y CC garantizado (Lulu, Nautilus).": {
        "en": "Zhonya/Stasis and guaranteed hard CC (Lulu, Nautilus) are essential.",
        "pt": "Zhonya/Estase e controle de grupo garantido (Lulu, Nautilus) são essenciais."
    },
    "Inicio": {"en": "Home", "pt": "Início"},
    "Instantáneo 24/7": {"en": "Instant 24/7", "pt": "Instantâneo 24/7"},
    "Lanzar Flotante en Juego": {"en": "Launch Floating HUD in Game", "pt": "Iniciar HUD Flutuante no Jogo"},
    "Los nuevos mensajes enviados por los usuarios aparecerán aquí.": {
        "en": "New messages sent by users will appear here.",
        "pt": "Novas mensagens enviadas pelos usuários aparecerão aqui."
    },
    "MEJOR OPCIÓN": {"en": "BEST CHOICE", "pt": "MELHOR OPÇÃO"},
    "Marcar como Completado": {"en": "Mark as Completed", "pt": "Marcar como Concluído"},
    "Maxeo:": {"en": "Max Order:", "pt": "Ordem de Evolução:"},
    "Mejores Sinergias:": {"en": "Best Synergies:", "pt": "Melhores Sinergias:"},
    "Minimizar HUD": {"en": "Minimize HUD", "pt": "Minimizar HUD"},
    "No hay reportes en esta categoría": {"en": "No reports in this category", "pt": "Nenhum relatório nesta categoria"},
    "No hay reportes registrados": {"en": "No reports registered", "pt": "Nenhum relatório registrado"},
    "Notas del Nuevo Parche": {"en": "New Patch Notes", "pt": "Notas do Novo Patch"},
    "Novedades de la actualización:": {"en": "What's new in this update:", "pt": "Novidades da atualização:"},
    "Nuestra composición es full Daño Físico (AD). El enemigo acumulará armadura.": {
        "en": "Our composition is full Physical Damage (AD). The enemy will stack armor.",
        "pt": "Nossa composição é full Dano Físico (AD). O inimigo acumulará armadura."
    },
    "Nuestra composición es full Daño Mágico (AP). El enemigo acumulará resistencia mágica.": {
        "en": "Our composition is full Magic Damage (AP). The enemy will stack magic resistance.",
        "pt": "Nossa composição é full Dano Mágico (AP). O inimigo acumulará resistência mágica."
    },
    "ONLINE": {"en": "ONLINE", "pt": "ONLINE"},
    "Obj": {"en": "Obj", "pt": "Obj"},
    "Objetos Clave (Build Recomendada)": {"en": "Core Items (Recommended Build)", "pt": "Itens Principais (Build Recomendada)"},
    "Objetos Oficiales": {"en": "Official Items", "pt": "Itens Oficiais"},
    "Objetos Situacionales Recomendados:": {"en": "Recommended Situational Items:", "pt": "Itens Situacionais Recomendados:"},
    "Ordenar:": {"en": "Sort:", "pt": "Ordenar:"},
    "Organización: Coach de Élite": {"en": "Organization: Elite Coach", "pt": "Organização: Coach de Elite"},
    "Otras Opciones Viables para": {"en": "Other Viable Options for", "pt": "Outras Opções Viáveis para"},
    "Panel de Administrador": {"en": "Admin Panel", "pt": "Painel de Administrador"},
    "Para descargar y guardar capturas.": {"en": "To download and save screenshots.", "pt": "Para baixar e salvar capturas."},
    "Para evitar que Android cierre la app.": {"en": "To prevent Android from killing the app.", "pt": "Para evitar que o Android encerre o app."},
    "Para que el asistente flote en tiempo real sobre tu partida de Wild Rift sin que Android cierre el proceso por consumo de memoria:": {
        "en": "For the assistant to float in real time over your Wild Rift match without Android killing the process for memory usage:",
        "pt": "Para que o assistente flutue em tempo real sobre sua partida de Wild Rift sem que o Android encerre o processo por uso de memória:"
    },
    "Paso 1: Configura tus Líneas de Juego": {"en": "Step 1: Configure your Game Lanes", "pt": "Passo 1: Configure suas Rotas de Jogo"},
    "Paso 2: Activa el Asistente Flotante": {"en": "Step 2: Activate Floating Assistant", "pt": "Passo 2: Ative o Assistente Flutuante"},
    "Paso 3: Selección de Campeones": {"en": "Step 3: Champion Select", "pt": "Passo 3: Seleção de Campeões"},
    "Paso 4: Consulta de Builds y Runas": {"en": "Step 4: Check Builds and Runes", "pt": "Passo 4: Consulta de Builds e Runas"},
    "Peligro de asesinos de burst": {"en": "Danger of burst assassins", "pt": "Perigo de assassinos de explosão"},
    "Personaliza el aspecto de la aplicación seleccionando entre múltiples temas inspirados en las regiones de Runaterra y configurando la paleta de colores de la barra de navegación.": {
        "en": "Customize the look and feel of the app by selecting from multiple themes inspired by the regions of Runeterra and configuring the navigation bar color palette.",
        "pt": "Personalize o visual do aplicativo escolhendo entre vários temas inspirados nas regiões de Runeterra e configurando a paleta de cores da barra de navegação."
    },
    "Personalización de Temas": {"en": "Theme Customization", "pt": "Personalização de Temas"},
    "Por resolver": {"en": "Pending", "pt": "Pendente"},
    "Prioridad de Habilidades": {"en": "Ability Priority", "pt": "Prioridade de Habilidades"},
    "Pulsa el botón central 'ACTIVAR'. Se desplegará la burbuja flotante en pantalla para acompañarte en tu partida.": {
        "en": "Press the central 'ACTIVATE' button. The floating bubble will deploy on screen to assist you in your match.",
        "pt": "Pressione o botão central 'ATIVAR'. A bolha flutuante aparecerá na tela para acompanhar sua partida."
    },
    "Página de Runas": {"en": "Rune Page", "pt": "Página de Runas"},
    "Reabrir (Marcar por resolver)": {"en": "Reopen (Mark as Pending)", "pt": "Reabrir (Marcar como Pendente)"},
    "Recomendaciones de Rendimiento": {"en": "Performance Recommendations", "pt": "Recomendações de Desempenho"},
    "Recomendaciones para funcionamiento óptimo sobre Wild Rift": {
        "en": "Recommendations for optimal performance over Wild Rift",
        "pt": "Recomendações para funcionamento ideal sobre o Wild Rift"
    },
    "Recordarme más tarde": {"en": "Remind me later", "pt": "Lembrar mais tarde"},
    "Reiniciar Todos": {"en": "Reset All", "pt": "Reiniciar Todos"},
    "Reintentar": {"en": "Retry", "pt": "Tentar novamente"},
    "Rendimiento en Segundo Plano": {"en": "Background Performance", "pt": "Desempenho em Segundo Plano"},
    "Requerido para usar el panel flotante sobre el juego.": {"en": "Required to use the floating panel over the game.", "pt": "Necessário para usar o painel flutuante sobre o jogo."},
    "Requiere daño verdadero, % vida máxima y penetración.": {"en": "Requires true damage, % max HP damage, and penetration.", "pt": "Requer dano verdadeiro, % de vida máxima e penetração."},
    "Revisa los consejos tácticos, orden de habilidades móviles (Pasiva, 1, 2, 3, Definitiva) y armado de objetos recomendado para tu línea.": {
        "en": "Check tactical tips, ability max order (Passive, 1, 2, 3, Ultimate), and recommended item builds for your lane.",
        "pt": "Confira dicas táticas, ordem de habilidades (Passiva, 1, 2, 3, Ultimate) e builds de itens recomendadas para sua rota."
    },
    "Rival": {"en": "Enemy", "pt": "Inimigo"},
    "Runa Clave Recomendada": {"en": "Recommended Keystone Rune", "pt": "Runa Principal Recomendada"},
    "Runas Oficiales": {"en": "Official Runes", "pt": "Runas Oficiais"},
    "Runas de": {"en": "Runes of", "pt": "Runas de"},
    "SELECCIONAR MI PICK PARA": {"en": "SELECT MY PICK FOR", "pt": "SELECIONAR MINHA ESCOLHA PARA"},
    "Selecciona daño físico (AD) para balancear": {"en": "Select physical damage (AD) to balance", "pt": "Selecione dano físico (AD) para equilibrar"},
    "Selecciona daño mágico (AP) para balancear": {"en": "Select magic damage (AP) to balance", "pt": "Selecione dano mágico (AP) para equilibrar"},
    "Selecciona tu Línea para esta Partida": {"en": "Select your Lane for this Match", "pt": "Selecione sua Rota para esta Partida"},
    "Selecciona un campeón para ver su página de runas óptima": {"en": "Select a champion to view their optimal rune page", "pt": "Selecione um campeão para ver sua página de runas ideal"},
    "Selecciona un campeón para ver sus hechizos de invocador recomendados": {"en": "Select a champion to view their recommended summoner spells", "pt": "Selecione um campeão para ver seus feitiços de invocador recomendados"},
    "Selecciona un color para la barra inferior:": {"en": "Select a color for the bottom bar:", "pt": "Selecione uma cor para a barra inferior:"},
    "Selecciona un tema para transformar la paleta de colores, fondos y acentos de toda la aplicación.": {
        "en": "Select a theme to transform the color palette, backgrounds, and accents of the entire app.",
        "pt": "Selecione um tema para transformar a paleta de cores, fundos e destaques de todo o aplicativo."
    },
    "Sincronizando...": {"en": "Syncing...", "pt": "Sincronizando..."},
    "TU ELECCIÓN EN": {"en": "YOUR PICK IN", "pt": "SUA ESCOLHA EM"},
    "Tema actual:": {"en": "Current theme:", "pt": "Tema atual:"},
    "Toca para iniciar": {"en": "Tap to start", "pt": "Toque para iniciar"},
    "Toca para ver build completa": {"en": "Tap to view full build", "pt": "Toque para ver a build completa"},
    "Todas las Líneas": {"en": "All Lanes", "pt": "Todas as Rotas"},
    "Total": {"en": "Total", "pt": "Total"},
    "Título del reporte o sugerencia": {"en": "Report or suggestion title", "pt": "Título do relatório ou sugestão"},
    "Ventaja Contra:": {"en": "Advantage Against:", "pt": "Vantagem Contra:"},
    "Ver detalles ▾": {"en": "View details ▾", "pt": "Ver detalhes ▾"},
    "Versión App:": {"en": "App Version:", "pt": "Versão do App:"},
    "Versión del Parche de Wild Rift:": {"en": "Wild Rift Patch Version:", "pt": "Versão do Patch do Wild Rift:"},
    "Vista previa en tiempo real": {"en": "Real-time preview", "pt": "Pré-visualização em tempo real"},
    "Volver": {"en": "Back", "pt": "Voltar"},
    "WR": {"en": "WR", "pt": "WR"},
    "Winrate Est.:": {"en": "Est. Winrate:", "pt": "Winrate Est.:"},
    "apilar armadura (Corazón Helado/Malla de Espinas)": {"en": "stack armor (Frozen Heart / Thornmail)", "pt": "acumular armadura (Coração Congelado / Armadura de Espinhos)"},
    "apilar resistencia (Fuerza de la Naturaleza)": {"en": "stack magic resist (Force of Nature)", "pt": "acumular resistência mágica (Força da Natureza)"},
    "release_notes_fallback": {
        "en": "• Performance optimizations in the smart assistant.\n• Updated synergies and counters for the current Wild Rift patch.\n• Improvements in UI and fast navigation.",
        "pt": "• Otimizações de desempenho no assistente inteligente.\n• Atualização de sinergias e counters para o patch atual do Wild Rift.\n• Melhorias na interface e navegação rápida."
    },
    "restante": {"en": "remaining", "pt": "restante"},
    "sincronizado con fuentes de balance.": {"en": "synchronized with official balance sources.", "pt": "sincronizado com fontes de balanceamento oficiais."},
    "¡Alerta en Top! Enfrentas a un rival con rango/ADC (${opponent.name}). Prioriza sustain (Segundo Aire), control de oleada y espera al jungla.": {
        "en": "Top Lane Alert! Facing a ranged/ADC opponent. Prioritize sustain (Second Wind), wave control, and wait for your jungler.",
        "pt": "Alerta no Top! Enfrentando oponente com alcance/ADC. Priorize sustentação (Ventos Revigorantes), controle de onda e espere o caçador."
    },
    "¡Bienvenidos!": {"en": "Welcome!", "pt": "Bem-vindos!"},
    "¡Listo para usar!": {"en": "Ready to use!", "pt": "Pronto para usar!"},
    "¡Nueva Versión Disponible!": {"en": "New Version Available!", "pt": "Nova Versão Disponível!"},
    "¿Eliminar este reporte?": {"en": "Delete this report?", "pt": "Excluir este relatório?"},
    "¿Estás seguro de que deseas cerrar Wild Rift Coach? Se cerrarán los asistentes activos.": {
        "en": "Are you sure you want to close Wild Rift Coach? Active overlays will be closed.",
        "pt": "Tem certeza de que deseja fechar o Wild Rift Coach? Os assistentes ativos serão encerrados."
    },
    "¿Por qué comprar este objeto?": {"en": "Why buy this item?", "pt": "Por que comprar este item?"},
    "¿Salir de la aplicación?": {"en": "Exit the application?", "pt": "Sair do aplicativo?"},
    "• Ajustes y corrección de íconos rúnicos.": {"en": "• Fixes and corrections for rune icons.", "pt": "• Ajustes e correções nos ícones de runas."},
    "• Análisis táctico actualizado con las últimas estadísticas del meta.\n": {
        "en": "• Tactical analysis updated with latest meta statistics.\n",
        "pt": "• Análise tática atualizada com as estatísticas mais recentes do meta.\n"
    },
    "• Compatibilidad: Diseñado exclusivamente para Wild Rift (habilidades móviles, runas de Wild Rift, balance y objetos móviles).": {
        "en": "• Compatibility: Exclusively tailored for Wild Rift (mobile skills, Wild Rift runes, balance, and mobile items).",
        "pt": "• Compatibilidade: Projetado exclusivamente para Wild Rift (habilidades móveis, runas do Wild Rift, balanceamento e itens móveis)."
    },
    "• Motor Hextech: Botón de activación directa con cálculo de composiciones, counters y sinergias.": {
        "en": "• Hextech Engine: Direct activation button with team composition, counter, and synergy calculations.",
        "pt": "• Motor Hextech: Botão de ativação direta com cálculo de composições, counters e sinergias."
    },
    "• Parche del juego: ": {"en": "• Game patch: ", "pt": "• Patch do jogo: "},
    "• Sincronización automática con: ": {"en": "• Automatic synchronization with: ", "pt": "• Sincronização automática com: "},
    "• Sistema Flotante: Ventana superpuesta en pantalla con controles táctiles para la fase de selección.": {
        "en": "• Floating System: On-screen overlay window with touch controls for champion select.",
        "pt": "• Sistema Flutuante: Janela sobreposta na tela com controles táteis para a fase de seleção."
    },
    "↓ Desliza para cerrar": {"en": "↓ Swipe down to close", "pt": "↓ Deslize para fechar"},
    "★ Mejor Opción según tu Equipo y el Rival": {"en": "★ Best Choice according to your Team & Enemy", "pt": "★ Melhor Opção de acordo com sua Equipe e Inimigo"},
    "★ Mejor Primer Pick Seguro para": {"en": "★ Best Safe Blind Pick for", "pt": "★ Melhor Primeira Escolha Segura para"},
    "⚠️ Considera cambiarlo": {"en": "⚠️ Consider swapping", "pt": "⚠️ Considere trocar"},
    "⚡ Favorable en carril": {"en": "⚡ Favorable in lane", "pt": "⚡ Favorável na rota"},
    "✅ Buena elección para tu línea": {"en": "✅ Good choice for your lane", "pt": "✅ Boa escolha para sua rota"},
    "✔ Concedido (Ventana flotante habilitada)": {"en": "✔ Granted (Floating window enabled)", "pt": "✔ Concedido (Janela flutuante ativada)"},
    "✔ Sin restricciones (No se cerrará en segundo plano)": {"en": "✔ Unrestricted (Will not close in background)", "pt": "✔ Sem restrições (Não fechará em segundo plano)"},
    "✘ Optimizado: Toca para quitar restricción": {"en": "✘ Optimized: Tap to remove restriction", "pt": "✘ Otimizado: Toque para remover restrição"},
    "✘ Pendiente: Toca para autorizar": {"en": "✘ Pending: Tap to authorize", "pt": "✘ Pendente: Toque para autorizar"},
    "👑 #1 MEJOR ELECCIÓN TÁCTICA": {"en": "👑 #1 BEST TACTICAL CHOICE", "pt": "👑 #1 MELHOR ESCOLHA TÁTICA"},
    "👑 #1 RECOMENDACIÓN BLIND PICK": {"en": "👑 #1 BLIND PICK RECOMMENDATION", "pt": "👑 #1 RECOMENDAÇÃO BLIND PICK"},
    "💡 Si tu dispositivo muestra 'conflicto con un paquete', desinstala la versión anterior de tu teléfono una sola vez e instala la nueva APK (ocurre por cambio de firma de depurado a producción).": {
        "en": "💡 If your device shows 'package conflict', uninstall the previous version from your phone once and install the new APK.",
        "pt": "💡 Se o seu dispositivo mostrar 'conflito com um pacote', desinstale a versão anterior do telefone uma única vez e instale o novo APK."
    },
    "📱 Dispositivo:": {"en": "📱 Device:", "pt": "📱 Dispositivo:"},
    "🔮 Runas:": {"en": "🔮 Runes:", "pt": "🔮 Runas:"}
}

for k, v in new_translations.items():
    en_dict[k] = v["en"]
    pt_dict[k] = v["pt"]

with open("app/src/main/assets/translations_en.json", "w") as f:
    json.dump(en_dict, f, ensure_ascii=False, indent=2)

with open("app/src/main/assets/translations_pt.json", "w") as f:
    json.dump(pt_dict, f, ensure_ascii=False, indent=2)

print(f"Added all new translations successfully. Total EN keys: {len(en_dict)}, Total PT keys: {len(pt_dict)}")
