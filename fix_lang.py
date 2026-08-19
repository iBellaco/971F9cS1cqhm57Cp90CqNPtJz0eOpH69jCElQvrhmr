with open('app/src/main/java/com/example/ui/screens/LanguageSelectionScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('title = "Automático (Sistema)",', 'title = tr("Automático (Sistema)"),')
content = content.replace('title = "Español",', 'title = tr("Español"),')
content = content.replace('title = "English",', 'title = tr("Inglés"),')
content = content.replace('Text(text = "Elige tu idioma"', 'Text(text = tr("Elige tu idioma")')

with open('app/src/main/java/com/example/ui/screens/LanguageSelectionScreen.kt', 'w') as f:
    f.write(content)
