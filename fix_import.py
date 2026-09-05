with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

import_statement = "import androidx.compose.foundation.layout.fillMaxWidth\nimport androidx.compose.foundation.layout.fillMaxHeight"

text = text.replace("import androidx.compose.foundation.layout.fillMaxWidth", import_statement)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
