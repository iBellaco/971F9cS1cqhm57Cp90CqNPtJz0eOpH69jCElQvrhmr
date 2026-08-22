import re
import os

with open('app/src/main/java/com/example/model/Champion.kt', 'r') as f:
    content = f.read()

# Add ItemSwap class
if 'data class ItemSwap' not in content:
    swap_class = """
@Serializable
data class ItemSwap(
    val coreItem: String,
    val coreItemIcon: String,
    val altItem: String,
    val altItemIcon: String,
    val reasonTitle: String,
    val reasonDesc: String,
    val againstWho: String
)

@Serializable
data class Champion("""
    content = content.replace('@Serializable\ndata class Champion(', swap_class)

# Add itemSwaps to Champion
if 'val itemSwaps: List<ItemSwap> = emptyList()' not in content:
    content = content.replace('val situationalItemsIcons: List<String> = emptyList(),', 'val situationalItemsIcons: List<String> = emptyList(),\n    val itemSwaps: List<ItemSwap> = emptyList(),')

with open('app/src/main/java/com/example/model/Champion.kt', 'w') as f:
    f.write(content)
