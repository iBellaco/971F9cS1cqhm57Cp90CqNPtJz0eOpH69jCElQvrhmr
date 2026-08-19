with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'r') as f:
    content = f.read()

# Only add imports if needed
if 'import com.example.util.tr' not in content:
    content = content.replace('import androidx.compose.runtime.*', 'import androidx.compose.runtime.*\nimport com.example.util.tr')

content = content.replace('Text(text = log', 'Text(text = log') # this is dynamic
content = content.replace('Text("Continuar a la App")', 'Text(tr("Continuar a la App"))')
content = content.replace('Text("Copy Logs")', 'Text(tr("Copy Logs"))')
content = content.replace('Text("Add Data")', 'Text(tr("Añadir Datos"))')
content = content.replace('Text("Read Data")', 'Text(tr("Leer Datos"))')
content = content.replace('Text("Delete Data")', 'Text(tr("Borrar Datos"))')
content = content.replace('Text("Logs:"', 'Text(tr("Logs:")')
content = content.replace('text = "Firestore & Logs Test Panel"', 'text = tr("Firestore & Logs Test Panel")')

with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'w') as f:
    f.write(content)
