with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

if "import androidx.compose.ui.res.painterResource" not in text:
    text = text.replace("import androidx.compose.ui.res.painterResource", "") # Clear if any weird match
    text = text.replace(
        "import androidx.compose.ui.graphics.Color",
        "import androidx.compose.ui.graphics.Color\nimport androidx.compose.ui.res.painterResource"
    )

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
