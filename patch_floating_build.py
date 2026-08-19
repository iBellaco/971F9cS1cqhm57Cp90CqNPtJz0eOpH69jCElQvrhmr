import re

file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Change selectedTab = 3 to selectedTab = 2 in the draft section
content = content.replace("lockedChampion = pick.champion\n                                                selectedTab = 3", "lockedChampion = pick.champion\n                                                selectedTab = 2")

# Change tabs list
content = content.replace('val tabs = listOf(tr("Draft"), tr("Objetivos"), tr("Objetos"), tr("Runas"))', 'val tabs = listOf(tr("Draft"), tr("Objetivos"), tr("Build"))')

# Now modify the when(selectedTab) to only have 0, 1, 2. Tab 2 will show Runes and Items.
old_tab_2_and_3 = r"2 -> \{\s*// OBJETOS TAB.*?\}\s*3 -> \{\s*// RUNAS TAB.*?\n\s*\}\s*\}"
import re
new_tab_2 = """2 -> {
                            // BUILD TAB (Objetos + Runas)
                            val currentChamp = lockedChampion ?: topPick?.champion ?: WildRiftRepository.champions.first()
                            val itemsToShow = currentChamp.coreItems.take(3) + currentChamp.situationalItems.take(2)
                            val champItems = itemsToShow.mapNotNull { itemName -> 
                                WildRiftRepository.items.find { it.name == itemName } 
                            }.takeIf { it.isNotEmpty() } ?: WildRiftRepository.items.take(4)

                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(tr("Build y Runas de") + " ${currentChamp.name}:", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                                    Text(tr("Ver otro"), color = HextechCyan, fontSize = 10.sp, modifier = Modifier.clickable { selectedTab = 0 })
                                }
                                
                                Text(
                                    text = tr("Runas") + ": " + currentChamp.recommendedRunes,
                                    color = HextechCyan,
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))

                                champItems.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(HextechSurface)
                                            .padding(6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        AppAssetImage(
                                            url = item.iconUrl,
                                            contentDescription = item.name,
                                            fallbackText = item.name,
                                            modifier = Modifier.size(30.dp),
                                            borderColor = HextechGold,
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(item.name, color = HextechGoldLight, fontSize = 11.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                            Text(item.passive, color = TextMuted, fontSize = 9.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                        }
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("${item.goldCost}g", color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }"""

content = re.sub(old_tab_2_and_3, new_tab_2, content, flags=re.DOTALL)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Patched FloatingAssistantService.kt for build tab")
