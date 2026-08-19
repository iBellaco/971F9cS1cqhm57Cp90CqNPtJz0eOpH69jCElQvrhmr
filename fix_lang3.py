with open('app/src/main/java/com/example/ui/screens/LanguageSelectionScreen.kt', 'r') as f:
    c = f.read()

c = c.replace('onClick = { onLanguageSelected(selectedLang) }',
'''onClick = { 
    if(selectedLang == "en") com.example.util.AppLogger.d("LANG", "English Selected")
    onLanguageSelected(selectedLang) 
}''')

with open('app/src/main/java/com/example/ui/screens/LanguageSelectionScreen.kt', 'w') as f:
    f.write(c)
