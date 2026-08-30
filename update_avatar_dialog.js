const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

const replacementFunc = `fun getRarityBorderBrush(rarity: String): Brush {
    val rarityLower = rarity.lowercase()
    val isCommon = rarityLower == "común" || rarityLower == "comun" || rarityLower == "clásico"
    return when {
        rarityLower.contains("mítico") || rarityLower.contains("mitico") -> Brush.sweepGradient(listOf(Color(0xFFC4B5FD), Color(0xFF7C3AED), Color(0xFF5B21B6), Color(0xFFC4B5FD)))
        rarityLower.contains("legendario") -> Brush.sweepGradient(listOf(Color(0xFFFFD700), Color(0xFFB91C1C), Color(0xFF991B1B), Color(0xFFFFD700)))
        rarityLower.contains("épico") || rarityLower.contains("epico") -> Brush.sweepGradient(listOf(Color(0xFFE9D5FF), Color(0xFF9333EA), Color(0xFFE9D5FF)))
        rarityLower.contains("raro") -> Brush.linearGradient(listOf(Color(0xFF93C5FD), Color(0xFF2563EB), Color(0xFF93C5FD)))
        else -> Brush.linearGradient(listOf(HextechCardBorder, HextechCardBorder))
    }
}`;

code = code.replace(/fun getRarityBorderBrush\([\s\S]*?\n\}/m, replacementFunc);
fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', code);
