import re

def fix_imports(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    if "import androidx.compose.foundation.BorderStroke" not in content:
        content = content.replace("import androidx.compose.ui.Modifier", "import androidx.compose.ui.Modifier\nimport androidx.compose.foundation.BorderStroke")
    
    content = content.replace("import BorderStroke", "")

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

fix_imports('app/src/main/java/com/example/ui/auth/AuthScreen.kt')
fix_imports('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt')
