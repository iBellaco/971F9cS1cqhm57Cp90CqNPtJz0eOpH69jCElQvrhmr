const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/UserAvatarView.kt', 'utf8');

// replace the custom modifier entirely
const regexCustomMod = /fun Modifier\.premiumBorderPainter[\s\S]*/;
const newCustomMod = `
fun Modifier.premiumBorderPainter(rarity: String, isMythic: Boolean, isLegendary: Boolean): Modifier {
    if (!isMythic && !isLegendary) return this
    
    return this.drawWithCache {
        val strokeWidth = if (isMythic) 4.dp.toPx() else 3.dp.toPx()
        
        val primaryColor = if (isMythic) Color(0xFFC4B5FD) else Color(0xFFFFD700)
        val secondaryColor = if (isMythic) Color(0xFF7C3AED) else Color(0xFFB91C1C)
        val darkColor = if (isMythic) Color(0xFF4C1D95) else Color(0xFF7F1D1D)
        
        val brush = Brush.sweepGradient(
            listOf(primaryColor, secondaryColor, darkColor, secondaryColor, primaryColor)
        )
        
        onDrawWithContent {
            drawContent()
            
            // Extravagant ring on top, inside the bounds
            drawCircle(
                brush = brush,
                radius = size.width / 2 - strokeWidth / 2,
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
code = code.replace(regexCustomMod, newCustomMod.trim());

// remove .padding(4.dp)
code = code.replace(/\)\.padding\(4\.dp\)/g, ')');

fs.writeFileSync('app/src/main/java/com/example/ui/components/UserAvatarView.kt', code);
