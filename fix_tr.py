with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

import re

# Need to translate more literal strings there that were hardcoded
content = re.sub(r'text\s*=\s*"Meta & Catálogo de Campeones"', 'text = tr("Meta & Catálogo de Campeones")', content)
content = re.sub(r'text\s*=\s*"Tier list, counters, sinergias, runas y objetos"', 'text = tr("Tier list, counters, sinergias, runas y objetos")', content)

content = re.sub(r'text\s*=\s*if\s*\(\s*isAssistantActive\s*\)\s*"Asistente Hextech Activo • Toca la cámara flotante"\s*else\s*"Presiona ACTIVAR para iniciar el Asistente Flotante"', 
                 'text = if (isAssistantActive) tr("Asistente Hextech Activo • Toca la cámara flotante") else tr("Presiona ACTIVAR para iniciar el Asistente Flotante")', 
                 content)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
