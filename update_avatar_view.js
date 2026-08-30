const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/UserAvatarView.kt', 'utf8');

const oldBorderCode = `    val runicBorderBrush = Brush.sweepGradient(
        listOf(
            parsedBorderColor,
            HextechGoldLight,
            HextechCyan.copy(alpha = 0.8f),
            parsedBorderColor,
            HextechGoldLight,
            parsedBorderColor
        )
    )

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(
                Brush.radialGradient(
                    listOf(
                        Color(0xFF1E293B),
                        Color(0xFF0F172A),
                        HextechDarkBg
                    )
                )
            )
            .then(
                if (showBorder) {
                    Modifier.border(
                        width = if (size > 60.dp) 2.5.dp else 1.5.dp,
                        brush = runicBorderBrush,
                        shape = CircleShape
                    )
                } else Modifier
            ),`;

const newBorderCode = `    val isCommon = avatar.rarity.equals("común", true) || avatar.rarity.equals("comun", true)
    
    val runicBorderBrush = when (avatar.rarity.lowercase()) {
        "legendario" -> Brush.sweepGradient(listOf(parsedBorderColor, Color(0xFFFCA5A5), Color(0xFFB91C1C), parsedBorderColor))
        "mítico", "mitico" -> Brush.linearGradient(listOf(parsedBorderColor, Color(0xFF8B5CF6), parsedBorderColor))
        "épico", "epico" -> Brush.radialGradient(listOf(parsedBorderColor, Color(0xFFD8B4FE), Color(0xFF7E22CE)))
        else -> Brush.sweepGradient(listOf(parsedBorderColor, HextechGoldLight, HextechCyan.copy(alpha = 0.8f), parsedBorderColor))
    }

    val actualShowBorder = showBorder && !isCommon

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(
                Brush.radialGradient(
                    listOf(
                        Color(0xFF1E293B),
                        Color(0xFF0F172A),
                        HextechDarkBg
                    )
                )
            )
            .then(
                if (actualShowBorder) {
                    Modifier.border(
                        width = if (size > 60.dp) 2.5.dp else 1.5.dp,
                        brush = runicBorderBrush,
                        shape = CircleShape
                    )
                } else Modifier
            ),`;

code = code.replace(oldBorderCode, newBorderCode);
fs.writeFileSync('app/src/main/java/com/example/ui/components/UserAvatarView.kt', code);
console.log("Replaced!");
