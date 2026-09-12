import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Remove the BugReport button
bug_button_regex = r"// Botón Insecto \(Depuración y Calibrador de Escáner\).*?// Botón Minimizar \(a Burbuja flotante\)"
new_content = re.sub(bug_button_regex, "// Botón Minimizar (a Burbuja flotante)", content, flags=re.DOTALL)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(new_content)
