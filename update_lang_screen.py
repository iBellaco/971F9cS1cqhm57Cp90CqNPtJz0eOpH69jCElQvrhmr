with open('app/src/main/java/com/example/ui/screens/LanguageSelectionScreen.kt', 'r') as f:
    c = f.read()

c = c.replace(
'''        LanguageOption(
            title = tr("Inglés"),
            subtitle = "Inglés",
            isSelected = selectedLang == "en",
            onClick = { selectedLang = "en" }
        )''',
'''        LanguageOption(
            title = tr("Inglés"),
            subtitle = "English",
            isSelected = selectedLang == "en",
            onClick = { selectedLang = "en" }
        )
        
        LanguageOption(
            title = "Português",
            subtitle = "Portuguese",
            isSelected = selectedLang == "pt",
            onClick = { selectedLang = "pt" }
        )'''
)

with open('app/src/main/java/com/example/ui/screens/LanguageSelectionScreen.kt', 'w') as f:
    f.write(c)
