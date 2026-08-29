import re

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    text = f.read()

# 1. Patch Item verification
item_target = """                            val iconUrl = dbItem?.iconUrl ?: WildRiftItemsData.getItemIconByName(rawName)
                            val itemName = dbItem?.name?.let { tr(it) } ?: tr(rawName)"""

item_replacement = """                            val iconUrl = dbItem?.iconUrl ?: WildRiftItemsData.getItemIconByName(rawName)
                            val itemName = dbItem?.name?.let { tr(it) } ?: tr(rawName)
                            val isResolved = iconUrl.isNotBlank() && iconUrl.startsWith("http")
                            val finalBorderColor = if (!isResolved) com.example.ui.theme.DangerRed else if (isSituational) HextechCyan.copy(alpha = 0.8f) else HextechGold"""

text = text.replace(item_target, item_replacement)

item_border_target = """                                        .border(
                                            width = 1.5.dp,
                                            color = if (isSituational) HextechCyan.copy(alpha = 0.8f) else HextechGold,
                                            shape = RoundedCornerShape(10.dp)
                                        )"""

item_border_replacement = """                                        .border(
                                            width = 1.5.dp,
                                            color = finalBorderColor,
                                            shape = RoundedCornerShape(10.dp)
                                        )"""
text = text.replace(item_border_target, item_border_replacement)

# 2. Patch Rune verification
rune_target = """                            val iconUrl = foundRune?.iconUrl ?: com.example.data.WildRiftSpellsAndRunes.getRuneIconByName(rName)

                            Box("""

rune_replacement = """                            val iconUrl = foundRune?.iconUrl ?: com.example.data.WildRiftSpellsAndRunes.getRuneIconByName(rName)
                            val isResolved = iconUrl.isNotBlank() && iconUrl.startsWith("http")
                            val finalRuneBorderColor = if (!isResolved) com.example.ui.theme.DangerRed else if (isKeystone) HextechGold else HextechCyan.copy(alpha = 0.6f)

                            Box("""
text = text.replace(rune_target, rune_replacement)

rune_border_target = """                                    .border(
                                        width = if (isKeystone) 2.dp else 1.dp,
                                        color = if (isKeystone) HextechGold else HextechCyan.copy(alpha = 0.6f),
                                        shape = CircleShape
                                    )"""

rune_border_replacement = """                                    .border(
                                        width = if (isKeystone) 2.dp else 1.dp,
                                        color = finalRuneBorderColor,
                                        shape = CircleShape
                                    )"""
text = text.replace(rune_border_target, rune_border_replacement)

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(text)
print("Patched ChampionDetailSheet with data-check mechanism")
