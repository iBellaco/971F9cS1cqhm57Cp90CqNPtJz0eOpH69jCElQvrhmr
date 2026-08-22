import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

# Replace in ItemsCatalogTab
old_banner_items = """            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF10B981), CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "wr-meta.com / items",
                    color = HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${filteredItems.size} ${tr("Objetos")}",
                    color = HextechCyan,"""

new_banner_items = """            Row(verticalAlignment = Alignment.CenterVertically) {
                // Empty or something else if needed. We can just remove the whole left side.
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${filteredItems.size} ${tr("Ítems")}",
                    color = HextechCyan,"""

content = content.replace(old_banner_items, new_banner_items)


# Let's also fix ChampionsCatalogTab, which we just generated with:
#                 Text(
#                     text = "${filteredChampions.size} " + tr("Campeones"),
old_banner_champions = """            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${filteredChampions.size} " + tr("Campeones"),
                    color = HextechCyan,"""

new_banner_champions = """            Row(verticalAlignment = Alignment.CenterVertically) {
                // Left side empty
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${filteredChampions.size} " + tr("Campeones"),
                    color = HextechCyan,"""

content = content.replace(old_banner_champions, new_banner_champions)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
