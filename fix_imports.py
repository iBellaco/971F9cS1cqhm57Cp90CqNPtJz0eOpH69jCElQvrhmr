with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.compose.foundation.layout.widthIn', 'import androidx.compose.foundation.layout.widthIn\nimport androidx.compose.foundation.layout.heightIn')

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
