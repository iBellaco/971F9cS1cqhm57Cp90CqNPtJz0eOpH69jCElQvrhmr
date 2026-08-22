import re

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'r') as f:
    content = f.read()

# 1. Update ChampionRoleProfile data class
profile_target = """    val situationalItemsIcons: List<String>,
    val recommendedRunes: String,"""
profile_replacement = """    val situationalItemsIcons: List<String>,
    val itemSwaps: List<com.example.model.ItemSwap>,
    val recommendedRunes: String,"""
content = content.replace(profile_target, profile_replacement)

# 2. Update isPrimary block
primary_target = """                situationalItems = champion.situationalItems,
                situationalItemsIcons = champion.situationalItemsIcons,"""
primary_replacement = """                situationalItems = champion.situationalItems,
                situationalItemsIcons = champion.situationalItemsIcons,
                itemSwaps = champion.itemSwaps,"""
content = content.replace(primary_target, primary_replacement)

# 3. Update alt role block
alt_target = """                situationalItems = champion.situationalItems,
                situationalItemsIcons = champion.situationalItemsIcons,"""
alt_replacement = """                situationalItems = champion.situationalItems,
                situationalItemsIcons = champion.situationalItemsIcons,
                itemSwaps = champion.itemSwaps,"""
# Since primary_replacement will match both because they are the same string, wait. Let's do a simple replace.
content = content.replace(alt_target, alt_replacement)

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'w') as f:
    f.write(content)
