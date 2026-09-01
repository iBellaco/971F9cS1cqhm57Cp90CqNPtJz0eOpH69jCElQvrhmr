import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Fix WomboCombo properties
content = content.replace(
    'text = "🔵 ${wombo.name}: ${wombo.description}"',
    'text = "🔵 ${wombo.title}: ${wombo.description}"'
)
content = content.replace(
    'text = "🔴 ${wombo.name}: ${wombo.description}"',
    'text = "🔴 ${wombo.title}: ${wombo.description}"'
)

# Add WomboCombo import
content = content.replace(
    'import com.example.ui.components.WomboComboSynergyDetector',
    'import com.example.ui.components.WomboComboSynergyDetector\nimport com.example.ui.components.WomboCombo'
)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
