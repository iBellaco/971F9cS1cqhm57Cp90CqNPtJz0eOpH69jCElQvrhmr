import re

with open("app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt", "r") as f:
    content = f.read()

# Replace tabLabels and tabIcons
old_tabs_block = """                val tabLabels = listOf(
                    "1. Meta Core",
                    "2. Ráfaga",
                    "3. Anti-Tanques",
                    "4. Anti-Magos"
                )
                val tabIcons = listOf(
                    "⚡", "🔥", "🛡️", "🔮"
                )"""

new_tabs_block = """                // Los títulos se extraerán directamente de opt.title"""
content = content.replace(old_tabs_block, new_tabs_block)

old_label_emoji_logic = """                    val label = tabLabels.getOrElse(idx) { "Opción ${idx + 1}" }
                    val emoji = tabIcons.getOrElse(idx) { "⚔️" }"""

new_label_emoji_logic = """                    val rawTitle = opt.title.replace(Regex("^Opción \\\\d: "), "")
                    val label = "${idx + 1}. $rawTitle\""""
content = content.replace(old_label_emoji_logic, new_label_emoji_logic)

old_row_content = """                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = emoji,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(end = 5.dp)
                            )
                            Text(
                                text = label,
                                color = if (isSelected) HextechGold else TextMuted,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }"""
                        
new_row_content = """                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = label,
                                color = if (isSelected) HextechGold else TextMuted,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }"""
content = content.replace(old_row_content, new_row_content)

# Fix fallback titles
content = content.replace('"subtitle = "WildRiftFire • BestBuildWR"', 'subtitle = "Equilibrada & Confiable"')
content = content.replace('"source = "WildRiftFire / BestBuildWR"', 'source = "Core Meta"')

with open("app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt", "w") as f:
    f.write(content)
print("Done")
