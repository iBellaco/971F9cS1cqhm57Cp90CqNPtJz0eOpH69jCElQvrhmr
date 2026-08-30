const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/UserAvatarView.kt', 'utf8');

const replacement = `    val rarityLower = avatar.rarity.lowercase()
    val isCommon = rarityLower == "común" || rarityLower == "comun" || rarityLower == "clásico"

    val borderWidth = when {
        rarityLower.contains("mítico") || rarityLower.contains("mitico") -> if (size > 60.dp) 3.5.dp else 2.5.dp
        rarityLower.contains("legendario") -> if (size > 60.dp) 3.dp else 2.dp
        rarityLower.contains("épico") || rarityLower.contains("epico") -> if (size > 60.dp) 2.5.dp else 1.5.dp
        rarityLower.contains("raro") -> if (size > 60.dp) 2.dp else 1.5.dp
        else -> 1.dp
    }

    val runicBorderBrush = when {
        rarityLower.contains("mítico") || rarityLower.contains("mitico") -> Brush.sweepGradient(listOf(Color(0xFFC4B5FD), Color(0xFF7C3AED), Color(0xFF5B21B6), Color(0xFFC4B5FD)))
        rarityLower.contains("legendario") -> Brush.sweepGradient(listOf(Color(0xFFFFD700), Color(0xFFB91C1C), Color(0xFF991B1B), Color(0xFFFFD700)))
        rarityLower.contains("épico") || rarityLower.contains("epico") -> Brush.sweepGradient(listOf(Color(0xFFE9D5FF), Color(0xFF9333EA), Color(0xFFE9D5FF)))
        rarityLower.contains("raro") -> Brush.linearGradient(listOf(Color(0xFF93C5FD), Color(0xFF2563EB), Color(0xFF93C5FD)))
        else -> Brush.linearGradient(listOf(parsedBorderColor, parsedBorderColor))
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
                        width = borderWidth,
                        brush = runicBorderBrush,
                        shape = CircleShape
                    )
                } else Modifier
            ),`;

code = code.replace(/val isCommon = [\s\S]*?else Modifier\n            \),/m, replacement);
fs.writeFileSync('app/src/main/java/com/example/ui/components/UserAvatarView.kt', code);
