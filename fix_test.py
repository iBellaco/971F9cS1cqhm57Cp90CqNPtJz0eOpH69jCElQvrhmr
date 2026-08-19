with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'r') as f:
    content = f.read()

# Change modifier on LazyColumn to weight(1f) to ensure it renders correctly when pushed down
content = content.replace('modifier = Modifier\n                .fillMaxSize()', 'modifier = Modifier\n                .weight(1f)\n                .fillMaxWidth()')

with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'w') as f:
    f.write(content)
