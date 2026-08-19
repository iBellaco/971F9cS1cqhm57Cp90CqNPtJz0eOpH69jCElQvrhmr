with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('Text(text = log', 'Text(text = log')

with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'w') as f:
    f.write(content)
