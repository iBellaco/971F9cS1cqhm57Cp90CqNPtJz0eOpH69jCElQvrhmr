import re

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('Text("Buscando composiciones', 'Text(com.example.util.tr("Buscando composiciones')
content = content.replace('Text("Activar Asistente (Superposición)"', 'Text(com.example.util.tr("Activar Asistente (Superposición)")')
content = content.replace('Text("Analizando Draft..."', 'Text(com.example.util.tr("Analizando Draft...")')
content = content.replace('Text("Permiso de Superposición"', 'Text(com.example.util.tr("Permiso de Superposición")')
content = content.replace('Text("Para que el asistente inteligente', 'Text(com.example.util.tr("Para que el asistente inteligente')
content = content.replace('Text("El asistente proporciona lecturas', 'Text(com.example.util.tr("El asistente proporciona lecturas')
content = content.replace('Text("Conceder Permiso"', 'Text(com.example.util.tr("Conceder Permiso")')
content = content.replace('Text("Cancelar"', 'Text(com.example.util.tr("Cancelar")')
content = content.replace('Text("Asistente Wild Rift"', 'Text(com.example.util.tr("Asistente Wild Rift")')
content = content.replace('Text("Cerrar"', 'Text(com.example.util.tr("Cerrar")')

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
