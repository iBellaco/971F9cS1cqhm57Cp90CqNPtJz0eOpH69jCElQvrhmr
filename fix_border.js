const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

const replacementBorderModifier = `.border(
                                width = if (isEquipped) 2.5.dp else 1.5.dp,
                                brush = getRarityBorderBrush(avatar.rarity),
                                shape = RoundedCornerShape(12.dp)
                            ),`;

code = code.replace(`.border(
                                width = if (isEquipped) 2.dp else 1.dp,
                                color = rarityColor.copy(alpha = if (isEquipped) 1f else 0.8f),
                                shape = RoundedCornerShape(12.dp)
                            ),`, replacementBorderModifier);

// Define getRarityBorderBrush
const addBorderBrushFunc = `
fun getRarityBorderBrush(rarity: String): Brush {
    val isCommon = rarity.equals("común", true) || rarity.equals("comun", true)
    return when (rarity.lowercase()) {
        "legendario" -> Brush.sweepGradient(listOf(Color(0xFFFCA5A5), Color(0xFFB91C1C), Color(0xFF991B1B), Color(0xFFFCA5A5)))
        "mítico", "mitico" -> Brush.linearGradient(listOf(Color(0xFFC4B5FD), Color(0xFF7C3AED), Color(0xFF5B21B6), Color(0xFFC4B5FD)))
        "épico", "epico" -> Brush.radialGradient(listOf(Color(0xFFE9D5FF), Color(0xFF9333EA), Color(0xFF6B21A8)))
        else -> if (isCommon) Brush.linearGradient(listOf(HextechCardBorder, HextechCardBorder)) else Brush.sweepGradient(listOf(HextechGoldLight, HextechCyan, HextechGoldLight))
    }
}
`;

// Insert it somewhere at the bottom of the file
if (!code.includes('fun getRarityBorderBrush')) {
    code = code + '\n' + addBorderBrushFunc;
}

fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', code);
console.log("Updated AvatarSelectionDialog");
