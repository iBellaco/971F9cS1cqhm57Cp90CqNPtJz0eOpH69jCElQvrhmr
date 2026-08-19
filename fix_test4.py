with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'r') as f:
    content = f.read()

# wait, we'll force recomposition or just use simpler rendering
content = content.replace('val logs by AppLogger.logs.collectAsState(initial = emptyList())', 'val logs by com.example.util.AppLogger.logs.collectAsState()')

with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'w') as f:
    f.write(content)
