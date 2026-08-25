import re

file = 'app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt'
with open(file, 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace('selectedCategory: ItemCategory?', 'selectedCategory: String?')
content = content.replace('onCategoryChange: (ItemCategory?) -> Unit', 'onCategoryChange: (String?) -> Unit')
content = content.replace('var selectedCategory by remember { mutableStateOf<ItemCategory?>(null) }', 'var selectedCategory by remember { mutableStateOf<String?>(null) }')
content = content.replace('item.category == selectedCategory', 'item.category.equals(selectedCategory, ignoreCase=true)')
content = content.replace('val dynamicCats = WildRiftRepository.items.map { it.category }.distinct()', 'val dynamicCats = WildRiftRepository.items.map { it.category }.distinct()')

# Also fix the FilterChip in OverlayItemSearch
replacement = """
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            val dynamicCats = WildRiftRepository.items.map { it.category }.distinct()
            items(dynamicCats) { cat ->
                FilterChip(
                    selected = selectedCategory == cat,
                    onClick = { onCategoryChange(if (selectedCategory == cat) null else cat) },
                    label = { Text(cat, fontSize = 10.sp) },
                    colors = FilterChipDefaults.filterChipColors(selectedContainerColor = HextechCyan, selectedLabelColor = HextechDarkBg)
                )
            }
        }
"""
content = re.sub(r'LazyRow.*?items\(ItemCategory\.entries\).*?cat ->.*?FilterChip.*?\}', replacement, content, flags=re.DOTALL)

with open(file, 'w', encoding='utf-8') as f:
    f.write(content)

