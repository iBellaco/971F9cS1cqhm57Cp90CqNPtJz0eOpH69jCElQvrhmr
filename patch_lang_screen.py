import re

file_path = "app/src/main/java/com/example/ui/screens/LanguageSelectionScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I want to remove the "Automático (Sistema)" option which is around line 85
pattern = r"LanguageOptionCard\(\s*title = tr\(\"Automático \(Sistema\)\"\),[\s\S]*?isSelected = currentLang == \"auto\",[\s\S]*?onClick = \{ onChangeLanguage\(\"auto\"\)\s*\}\s*\)\s*Spacer\(modifier = Modifier\.height\(12\.dp\)\)"
content = re.sub(pattern, "", content)

# I should also make sure the default selection isn't "auto". It's probably handled elsewhere but just in case:
# Let's see if there are other occurrences of "auto"

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Patched LanguageSelectionScreen.kt to remove auto")
