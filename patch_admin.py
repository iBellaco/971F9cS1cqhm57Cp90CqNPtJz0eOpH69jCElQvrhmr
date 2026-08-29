import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

text = text.replace('Text("Cambiar Nombre", color = HextechCyan)', 'Text("Cambiar Nombre de Usuario", color = HextechCyan)')
text = text.replace('Text("Cambiar Nombre", color = HextechGold)', 'Text("Cambiar Nombre de Usuario", color = HextechGold)')
text = text.replace('label = { Text("Nombre") }', 'label = { Text("Nombre de Usuario") }')

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
