const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/UserAvatarView.kt', 'utf8');

if (!code.includes('import androidx.compose.ui.draw.drawWithCache')) {
    code = code.replace('import androidx.compose.ui.draw.clip', 'import androidx.compose.ui.draw.clip\nimport androidx.compose.ui.draw.drawWithCache\nimport androidx.compose.ui.graphics.drawscope.Stroke\nimport androidx.compose.ui.geometry.Offset\nimport androidx.compose.ui.geometry.Size\nimport androidx.compose.ui.graphics.PathEffect\nimport androidx.compose.animation.core.*\nimport androidx.compose.runtime.getValue\nimport androidx.compose.ui.graphics.drawscope.rotate');
}

const customModifier = `
fun Modifier.premiumBorderPainter(rarity: String, isMythic: Boolean, isLegendary: Boolean): Modifier {
    if (!isMythic && !isLegendary) return this
    
    return this.drawWithCache {
        val strokeWidth = if (isMythic) 4.dp.toPx() else 3.dp.toPx()
        val glowWidth = if (isMythic) 12.dp.toPx() else 8.dp.toPx()
        
        val primaryColor = if (isMythic) Color(0xFFC4B5FD) else Color(0xFFFFD700)
        val secondaryColor = if (isMythic) Color(0xFF7C3AED) else Color(0xFFB91C1C)
        val darkColor = if (isMythic) Color(0xFF4C1D95) else Color(0xFF7F1D1D)
        
        val brush = Brush.sweepGradient(
            listOf(primaryColor, secondaryColor, darkColor, secondaryColor, primaryColor)
        )
        
        val glowBrush = Brush.radialGradient(
            colors = listOf(primaryColor.copy(alpha = 0.5f), Color.Transparent),
            center = Offset(size.width / 2, size.height / 2),
            radius = size.width / 2 + glowWidth
        )
        
        onDrawWithContent {
            // Glow effect behind
            drawCircle(
                brush = glowBrush,
                radius = size.width / 2 + glowWidth / 2,
                center = Offset(size.width / 2, size.height / 2)
            )
            
            drawContent()
            
            // Extravagant ring on top
            drawCircle(
                brush = brush,
                radius = size.width / 2,
                center = Offset(size.width / 2, size.height / 2),
                style = Stroke(
                    width = strokeWidth,
                    pathEffect = if (isMythic) PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f) else null
                )
            )
            
            if (isMythic) {
                // Additional inner ring for mythic
                drawCircle(
                    color = Color(0xFFE9D5FF),
                    radius = size.width / 2 - strokeWidth,
                    center = Offset(size.width / 2, size.height / 2),
                    style = Stroke(width = 1.dp.toPx())
                )
            }
        }
    }
}
`;

const thenBlockRegex = /\.then\(\s*if \(actualShowBorder\) \{\s*Modifier\.border\([\s\S]*?\)\s*\} else Modifier\s*\)/m;
const thenBlockReplacement = `.then(
                if (actualShowBorder) {
                    if (rarityLower.contains("mítico") || rarityLower.contains("mitico") || rarityLower.contains("legendario")) {
                        Modifier.premiumBorderPainter(
                            rarity = rarityLower,
                            isMythic = rarityLower.contains("mítico") || rarityLower.contains("mitico"),
                            isLegendary = rarityLower.contains("legendario")
                        ).padding(4.dp)
                    } else {
                        Modifier.border(
                            width = borderWidth,
                            brush = runicBorderBrush,
                            shape = CircleShape
                        )
                    }
                } else Modifier
            )`;

code = code.replace(thenBlockRegex, thenBlockReplacement);
code = code + "\n" + customModifier;

fs.writeFileSync('app/src/main/java/com/example/ui/components/UserAvatarView.kt', code);
