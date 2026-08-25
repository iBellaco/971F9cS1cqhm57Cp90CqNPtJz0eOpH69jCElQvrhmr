import re

file = 'app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt'
with open(file, 'r', encoding='utf-8') as f:
    content = f.read()

replacement = """
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val dynamicCats = WildRiftRepository.items.map { it.category }.distinct()
            dynamicCats.forEach { cat ->
                val isSelected = selectedCategory == cat
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (isSelected) HextechCyan else HextechSurface)
                        .clickable { onCategoryChange(if (isSelected) null else cat) }
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = com.example.util.tr(cat),
                        color = if (isSelected) HextechDarkBg else TextMuted,
                        fontSize = 9.5.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
"""

content = re.sub(r'FlowRow.*?\{.*?ItemCategory\.entries\.forEach \{ cat ->.*?text = tr\(cat\.displayName\).*?FontWeight\.Normal\s*\)\s*\}', replacement, content, flags=re.DOTALL)

with open(file, 'w', encoding='utf-8') as f:
    f.write(content)

