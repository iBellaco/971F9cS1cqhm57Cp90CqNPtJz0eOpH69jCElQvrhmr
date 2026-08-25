import re
import glob

files = glob.glob('app/src/main/java/com/example/**/*.kt', recursive=True)
for file in files:
    with open(file, 'r', encoding='utf-8') as f:
        content = f.read()

    # WildRiftItemsData.kt replacements
    if 'WildRiftItemsData.kt' in file:
        content = content.replace('category = ItemCategory.BASIC', 'category = "Básicos"')
        content = content.replace('category = ItemCategory.MID_TIER', 'category = "Nivel Medio"')
        content = content.replace('category = ItemCategory.PHYSICAL', 'category = "Daño Físico"')
        content = content.replace('category = ItemCategory.MAGIC', 'category = "Daño Mágico"')
        content = content.replace('category = ItemCategory.DEFENSE', 'category = "Defensa"')
        content = content.replace('category = ItemCategory.SUPPORT', 'category = "Soporte"')
        content = content.replace('category = ItemCategory.BOOTS_T2', 'category = "Botas N2"')
        content = content.replace('category = ItemCategory.BOOTS_T3', 'category = "Botas N3"')
        content = content.replace('category = ItemCategory.ACTIVE', 'category = "Encantamientos"')

    # WildRiftRemoteDtos.kt
    if 'WildRiftRemoteDtos.kt' in file:
        content = re.sub(
            r'fun fromModel\(model: WildRiftItem\): WrItemDto \{\s*return WrItemDto\(\s*id = model\.id,\s*name = model\.name,\s*category = model\.category\.name,',
            r'fun fromModel(model: WildRiftItem): WrItemDto {\n            return WrItemDto(\n                id = model.id,\n                name = model.name,\n                category = model.category,',
            content
        )
        content = re.sub(r'category = model\.category\.name', r'category = model.category', content)
        content = re.sub(
            r'val cat = try \{.*?\}\s*return WildRiftItem\(\s*id = id,\s*name = name,\s*category = cat,',
            r'return WildRiftItem(\n            id = id,\n            name = name,\n            category = category,',
            content, flags=re.DOTALL
        )

    # UI Screens
    content = content.replace('it.category.displayName', 'it.category')
    content = content.replace('model.category.displayName', 'model.category')
    content = content.replace('item.category.displayName', 'item.category')
    
    # MetaAndDraftScreen
    if 'MetaAndDraftScreen.kt' in file:
        content = content.replace('selectedCategory: ItemCategory?', 'selectedCategory: String?')
        content = content.replace('var selectedCategory by remember { mutableStateOf<ItemCategory?>(null) }', 'var selectedCategory by remember { mutableStateOf<String?>(null) }')
        
        # Replace the header iteration
        content = re.sub(
            r'ItemCategory\.entries\.forEach \{ category ->\s*val categoryItems = allItems\.filter \{ it\.category == category \}',
            r'val grouped = allItems.groupBy { it.category }\n                grouped.forEach { (category, categoryItems) ->',
            content
        )
        content = content.replace('item(key = "header_${category.name}")', 'item(key = "header_${category}")')
        content = content.replace('text = tr(category.sectionTitle)', 'text = tr(category.uppercase())')
        
        content = re.sub(
            r'LazyRow\(.*?\).*?items\(ItemCategory\.entries\).*?\{ cat ->.*?FilterChip\(.*?selected = selectedCategory == cat,.*?onClick = \{.*?\},.*?label = \{ Text\("\$\{cat\.iconEmoji\} \$\{cat\.displayName\}"\).*?\}\).*?\}',
            r'LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {\n                    val dynamicCats = allItems.map { it.category }.distinct()\n                    items(dynamicCats) { cat ->\n                        FilterChip(\n                            selected = selectedCategory == cat,\n                            onClick = { selectedCategory = if (selectedCategory == cat) null else cat },\n                            label = { Text(cat, fontSize = 11.5.sp) }\n                        )\n                    }\n                }',
            content, flags=re.DOTALL
        )
    
    # AdminItemEditor
    if 'AdminItemEditor.kt' in file:
        content = content.replace('var editCategory by remember { mutableStateOf(ItemCategory.BASIC) }', 'var editCategory by remember { mutableStateOf("Básicos") }')
        
        content = re.sub(
            r'LazyRow\(\s*horizontalArrangement = Arrangement\.spacedBy\(4\.dp\),\s*modifier = Modifier\.fillMaxWidth\(\)\s*\) \{\s*items\(ItemCategory\.entries\) \{ cat ->\s*FilterChip\(\s*selected = editCategory == cat,\s*onClick = \{ editCategory = cat \},\s*label = \{ Text\("\$\{cat\.iconEmoji\} \$\{cat\.displayName\}", fontSize = 10\.5\.sp\) \}\s*\)\s*\}\s*\}',
            r'OutlinedTextField(value = editCategory, onValueChange = { editCategory = it }, label = { Text("Categoría") }, modifier = Modifier.fillMaxWidth())',
            content, flags=re.DOTALL
        )
        content = content.replace('category = editCategory,', 'category = editCategory.trim(),')
        
    # FloatingAssistantOverlay
    if 'FloatingAssistantOverlay.kt' in file:
        content = content.replace('it.category == ItemCategory.BOOTS_T2 || it.category == ItemCategory.BOOTS_T3', 'it.category.contains("Botas", ignoreCase = true)')
        
    with open(file, 'w', encoding='utf-8') as f:
        f.write(content)

