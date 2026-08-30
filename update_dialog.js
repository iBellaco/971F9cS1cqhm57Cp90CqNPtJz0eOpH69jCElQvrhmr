const fs = require('fs');

let content = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

// 1. Dynamic filters
const oldFilters = `    val filterOptions = listOf(
        "Todos",
        "Jonia",
        "Zaun / Piltóver",
        "Demacia / Noxus",
        "Freljord / Shurima",
        "Runaterra / Varios"
    )

    val groupedAvatars = remember(selectedFilter) {
        val filtered = when (selectedFilter) {
            "Jonia" -> AvatarCatalog.avatars.filter { it.region.equals("Jonia", ignoreCase = true) }
            "Zaun / Piltóver" -> AvatarCatalog.avatars.filter {
                it.region.contains("Zaun", ignoreCase = true) || it.region.contains("Piltóver", ignoreCase = true)
            }
            "Demacia / Noxus" -> AvatarCatalog.avatars.filter {
                it.region.contains("Demacia", ignoreCase = true) || it.region.contains("Noxus", ignoreCase = true)
            }
            "Freljord / Shurima" -> AvatarCatalog.avatars.filter {
                it.region.contains("Freljord", ignoreCase = true) || it.region.contains("Shurima", ignoreCase = true)
            }
            "Runaterra / Varios" -> AvatarCatalog.avatars.filter {
                it.region.contains("Runaterra", ignoreCase = true) || it.region.contains("Islas", ignoreCase = true) || it.region.contains("Targon", ignoreCase = true) || it.region.contains("Aguas", ignoreCase = true) || it.region.contains("Vacío", ignoreCase = true) || it.region.contains("Oscuros", ignoreCase = true) || it.region.contains("Bandle", ignoreCase = true)
            }
            else -> AvatarCatalog.avatars
        }
        filtered.groupBy { it.region }.toSortedMap()
    }`;

const newFilters = `    val filterOptions = remember {
        listOf("Todos") + AvatarCatalog.avatars.map { it.region }.distinct().sorted()
    }

    val groupedAvatars = remember(selectedFilter) {
        val filtered = if (selectedFilter == "Todos") {
            AvatarCatalog.avatars
        } else {
            AvatarCatalog.avatars.filter { it.region.equals(selectedFilter, ignoreCase = true) }
        }
        filtered.groupBy { it.region }.toSortedMap()
    }`;

content = content.replace(oldFilters, newFilters);

// 2. Add rarity helper function inside AvatarSelectionBottomSheet (or at top)
// I will put it right before @Composable fun AvatarSelectionBottomSheet
const oldComposable = `@Composable
fun AvatarSelectionBottomSheet(`;

const newComposable = `fun getRarityColor(rarity: String): Color {
    return when (rarity.lowercase()) {
        "común", "comun" -> Color(0xFF9E9E9E)
        "raro" -> Color(0xFF3B82F6) // HextechCyan-like
        "épico", "epico" -> Color(0xFFA855F7) // Purple
        "legendario" -> Color(0xFFEF4444) // Red
        "mítico", "mitico" -> Color(0xFFEC4899) // Pink
        else -> HextechGold
    }
}

@Composable
fun AvatarSelectionBottomSheet(`;

content = content.replace(oldComposable, newComposable);

// 3. Update the avatar card border and text to use rarity color
// Find:
// val parsedBorder = try {
//     Color(android.graphics.Color.parseColor(avatar.borderHex))
// } catch (e: Exception) {
//     HextechGold
// }

const oldParsedBorder = `                    val parsedBorder = try {
                        Color(android.graphics.Color.parseColor(avatar.borderHex))
                    } catch (e: Exception) {
                        HextechGold
                    }`;

const newParsedBorder = `                    val rarityColor = getRarityColor(avatar.rarity)
                    val parsedBorder = rarityColor`;

content = content.replace(oldParsedBorder, newParsedBorder);

// We need to also change the Card border when isEquipped to use rarityColor? 
// Current border:
// color = if (isEquipped) HextechGold else if (canEquip) parsedBorder.copy(alpha = 0.6f) else HextechCardBorder.copy(alpha = 0.4f),
// I will change it to:
// color = if (isEquipped) rarityColor else if (canEquip) rarityColor.copy(alpha = 0.6f) else HextechCardBorder.copy(alpha = 0.4f),

const oldBorderColor = `color = if (isEquipped) HextechGold else if (canEquip) parsedBorder.copy(alpha = 0.6f) else HextechCardBorder.copy(alpha = 0.4f),`;
const newBorderColor = `color = if (isEquipped) rarityColor else if (canEquip) rarityColor.copy(alpha = 0.8f) else HextechCardBorder.copy(alpha = 0.4f),`;
content = content.replace(oldBorderColor, newBorderColor);

// 4. Also use rarityColor for background slightly?
// Current:
// containerColor = if (isEquipped) HextechGold.copy(alpha = 0.12f) else if (!canEquip) HextechSurface.copy(alpha = 0.5f) else HextechSurface
// I will change it to:
const oldBgColor = `containerColor = if (isEquipped) HextechGold.copy(alpha = 0.12f) else if (!canEquip) HextechSurface.copy(alpha = 0.5f) else HextechSurface`;
const newBgColor = `containerColor = if (isEquipped) rarityColor.copy(alpha = 0.15f) else if (!canEquip) HextechSurface.copy(alpha = 0.5f) else HextechSurface`;
content = content.replace(oldBgColor, newBgColor);

// 5. Apply rarity color to the avatar rarity text.
const oldRarityText = `                                    text = avatar.rarity.uppercase(),
                                    color = if (avatar.rarity.equals("Mítico", true)) Color(0xFFEC4899)
                                    else if (avatar.rarity.equals("Legendario", true)) Color(0xFFEF4444)
                                    else if (isGifted && !avatar.isDefault) Color(0xFF10B981)
                                    else if (canEquip) HextechCyan
                                    else HextechGold.copy(alpha = 0.8f),`;

const newRarityText = `                                    text = avatar.rarity.uppercase(),
                                    color = if (!canEquip && !isGifted) rarityColor.copy(alpha = 0.6f) else rarityColor,`;
content = content.replace(oldRarityText, newRarityText);

fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', content);
console.log("Updated AvatarSelectionDialog");
