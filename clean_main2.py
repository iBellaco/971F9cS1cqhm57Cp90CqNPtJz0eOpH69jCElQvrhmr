with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

bad_str = """    onNavigateToInfo: () -> Unit,
    onNavigateToTutorial: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToTutorial: () -> Unit,"""

good_str = """    onNavigateToInfo: () -> Unit,
    onNavigateToTutorial: () -> Unit,
    onNavigateToLogin: () -> Unit,"""

content = content.replace(bad_str, good_str)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
