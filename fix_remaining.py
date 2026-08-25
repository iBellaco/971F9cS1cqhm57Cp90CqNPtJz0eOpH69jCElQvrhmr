import re

# FloatingAssistantOverlay.kt
file = 'app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt'
with open(file, 'r', encoding='utf-8') as f:
    content = f.read()
content = content.replace('it.category == ItemCategory.BOOTS_T2 || it.category == ItemCategory.BOOTS_T3', 'it.category.contains("Botas", ignoreCase = true)')
content = content.replace('it.category == ItemCategory.ACTIVE', 'it.category.contains("Encanta", ignoreCase = true)')
with open(file, 'w', encoding='utf-8') as f:
    f.write(content)

# AdminItemEditor.kt
file = 'app/src/main/java/com/example/ui/components/admin/AdminItemEditor.kt'
with open(file, 'r', encoding='utf-8') as f:
    content = f.read()
content = content.replace('val items = WildRiftRepository.items.filter { editCategory == null || it.category == editCategory }', 'val items = WildRiftRepository.items.filter { editCategory.isBlank() || it.category.equals(editCategory, ignoreCase=true) }')
content = content.replace('var editCategory by remember { mutableStateOf<ItemCategory?>(null) }', 'var editCategory by remember { mutableStateOf("") }')
content = content.replace('category = editCategory ?: ItemCategory.BASIC', 'category = editCategory.ifBlank { "Básicos" }')
content = content.replace('val items = WildRiftRepository.items.filter { selectedCategory == null || it.category == selectedCategory }', 'val items = WildRiftRepository.items.filter { selectedCategory.isNullOrBlank() || it.category.equals(selectedCategory, ignoreCase=true) }')
content = content.replace('var selectedCategory by remember { mutableStateOf<ItemCategory?>(null) }', 'var selectedCategory by remember { mutableStateOf<String?>(null) }')
content = content.replace('selectedCategory == cat', 'selectedCategory?.equals(cat) == true')
content = content.replace('editCategory = item.category', 'editCategory = item.category')
content = content.replace('editCategory = cat', 'editCategory = cat')
content = content.replace('it.category == editCategory', 'it.category == editCategory')

content = re.sub(
    r'LazyRow.*?items\(ItemCategory\.entries\).*?cat ->.*?FilterChip.*?\}',
    r'OutlinedTextField(value = editCategory, onValueChange = { editCategory = it }, label = { Text("Categoría") }, modifier = Modifier.fillMaxWidth())',
    content, flags=re.DOTALL
)

with open(file, 'w', encoding='utf-8') as f:
    f.write(content)

# MetaAndDraftScreen.kt
file = 'app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt'
with open(file, 'r', encoding='utf-8') as f:
    content = f.read()
content = content.replace('it.category == selectedCategory', 'it.category.equals(selectedCategory, ignoreCase=true)')
content = content.replace('selectedCategory == cat', 'selectedCategory == cat')
content = content.replace('selectedCategory = if (selectedCategory == cat) null else cat', 'selectedCategory = if (selectedCategory == cat) null else cat')
with open(file, 'w', encoding='utf-8') as f:
    f.write(content)

