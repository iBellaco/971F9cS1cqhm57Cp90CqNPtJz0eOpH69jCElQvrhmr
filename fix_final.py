import re

# FloatingAssistantOverlay.kt
file = 'app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt'
with open(file, 'r', encoding='utf-8') as f:
    content = f.read()
# Find any remaining ItemCategory.BOOTS_T2 etc
content = re.sub(r'it\.category == ItemCategory\.[A-Z_0-9]+', 'it.category.contains("Botas", ignoreCase=true)', content)
with open(file, 'w', encoding='utf-8') as f:
    f.write(content)

# AdminItemEditor.kt
file = 'app/src/main/java/com/example/ui/components/admin/AdminItemEditor.kt'
with open(file, 'r', encoding='utf-8') as f:
    content = f.read()
content = content.replace('iconUrl = item.iconUrl,', 'url = item.iconUrl,')
content = content.replace('name = item.name,', 'contentDescription = item.name,\nfallbackText = item.name,')
with open(file, 'w', encoding='utf-8') as f:
    f.write(content)

# MetaAndDraftScreen.kt
file = 'app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt'
with open(file, 'r', encoding='utf-8') as f:
    content = f.read()
content = re.sub(r'it\.category == ItemCategory\.[A-Z_0-9]+', 'it.category.contains("Botas", ignoreCase=true)', content)
content = re.sub(r'selectedCategory == ItemCategory\.[A-Z_0-9]+', 'selectedCategory == "Botas N2"', content)
content = re.sub(r'selectedCategory = ItemCategory\.[A-Z_0-9]+', 'selectedCategory = "Botas N2"', content)
with open(file, 'w', encoding='utf-8') as f:
    f.write(content)

