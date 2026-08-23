import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

target = """        // Damage distribution
        if (enemySlots.isNotEmpty()) {
            Text(tr("Balance de Daño Rival"), color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)"""

replacement = """        // Ally Damage distribution
        if (allySlots.isNotEmpty()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(tr("Balance de Daño Aliado"), color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                if (analysis.allyCompositionWarning != null) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(Icons.Default.Warning, contentDescription = null, tint = DangerRed, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(analysis.allyCompositionWarning, color = DangerRed, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp))) {
                if (analysis.allyPhysicalDamagePercent > 0) {
                    Box(modifier = Modifier.weight(analysis.allyPhysicalDamagePercent.toFloat()).fillMaxHeight().background(Color(0xFFE57373)))
                }
                if (analysis.allyMagicDamagePercent > 0) {
                    Box(modifier = Modifier.weight(analysis.allyMagicDamagePercent.toFloat()).fillMaxHeight().background(Color(0xFF64B5F6)))
                }
                if (analysis.allyTrueDamagePercent > 0) {
                    Box(modifier = Modifier.weight(analysis.allyTrueDamagePercent.toFloat()).fillMaxHeight().background(Color.White))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Damage distribution
        if (enemySlots.isNotEmpty()) {
            Text(tr("Balance de Daño Rival"), color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)"""

content = content.replace(target, replacement)
with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
print("Done meta draft")
