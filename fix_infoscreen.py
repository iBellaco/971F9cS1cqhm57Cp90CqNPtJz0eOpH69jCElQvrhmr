with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "r") as f:
    lines = f.readlines()

new_lines = []
skip = False
for i, line in enumerate(lines):
    if "InfoSectionHeader(" in line and "Opciones de Administrador" in "".join(lines[i:i+5]):
        skip = True
    if skip and "Spacer(modifier = Modifier.height(30.dp))" in line:
        skip = False
        continue
    if not skip:
        new_lines.append(line)

with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "w") as f:
    f.writelines(new_lines)
