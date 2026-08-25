import re

with open('app/src/main/java/com/example/ui/components/BlurredMeshBackground.kt', 'r', encoding='utf-8') as f:
    content = f.read()

if "import androidx.compose.ui.draw.blur" not in content:
    content = content.replace("import androidx.compose.ui.Modifier", "import androidx.compose.ui.Modifier\nimport androidx.compose.ui.draw.blur\nimport androidx.compose.ui.unit.dp")

if ".blur(" not in content:
    content = content.replace("Canvas(modifier = Modifier.fillMaxSize())", "Canvas(modifier = Modifier.fillMaxSize().blur(100.dp))")

with open('app/src/main/java/com/example/ui/components/BlurredMeshBackground.kt', 'w', encoding='utf-8') as f:
    f.write(content)

