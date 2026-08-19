import re

file_path = "app/src/main/java/com/example/ui/screens/InfoScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Match from SECCIÓN 3: FUENTES WEB DEL META up to right before InfoSectionHeader for Admin
pattern = re.compile(r"// ==========================================\s*// SECCIÓN 3: FUENTES WEB DEL META \(EN LA APP\).*?(?=InfoSectionHeader\(\s*icon = Icons\.Default\.Sync,\s*title = tr\(\"4\. Opciones de Administrador\"\))", re.DOTALL)

content = pattern.sub("", content)
content = content.replace('title = tr("4. Opciones de Administrador")', 'title = tr("3. Opciones de Administrador")')

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("InfoScreen meta sources removed!")
