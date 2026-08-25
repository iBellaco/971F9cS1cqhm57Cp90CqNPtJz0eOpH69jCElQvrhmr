import re

file = 'app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt'
with open(file, 'r', encoding='utf-8') as f:
    content = f.read()

# I will replace the ItemCategory.entries.forEach with a dynamic iteration
replacement = """
            val dynamicCats = allItems.map { it.category }.distinct()
            dynamicCats.forEach { cat ->
                val count = allItems.count { it.category == cat }
                FilterChip(
                    selected = selectedCategory == cat,
                    onClick = { selectedCategory = if (selectedCategory == cat) null else cat },
                    label = { Text("${com.example.util.tr(cat)} ($count)", fontSize = 11.5.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
"""

content = re.sub(r'ItemCategory\.entries\.forEach \{ cat ->.*?selectedLabelColor = HextechDarkBg\s*\)\s*\)\s*\}', replacement, content, flags=re.DOTALL)

with open(file, 'w', encoding='utf-8') as f:
    f.write(content)

