with open('app/src/main/java/com/example/util/Translator.kt', 'r') as f:
    c = f.read()

c = c.replace('        "Buscando composiciones..." to "Searching comps...",',
'''        "Buscando composiciones..." to "Searching comps...",
        "PermisoSuperposicionTexto" to "1. Tap 'Grant Permission'.\\n2. Enable the switch for Wild Rift Drafting.\\n3. Return to the app and press ACTIVATE.",
        "Línea Main" to "Main Role",
        "Segunda Línea" to "Secondary Role",
        "Rol Autofill" to "Autofill Role",
        "Meta & Catálogo de Campeones" to "Meta & Champion Catalog",
        "Tier list, counters, sinergias, runas y objetos" to "Tier list, counters, synergies, runes, and items",
        "Asistente Hextech Activo • Toca la cámara flotante" to "Hextech Assistant Active • Tap the floating camera",
        "Presiona ACTIVAR para iniciar el Asistente Flotante" to "Press ACTIVATE to start the Floating Assistant",
''')

with open('app/src/main/java/com/example/util/Translator.kt', 'w') as f:
    f.write(c)
