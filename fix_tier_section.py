import sys

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

target = """                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ChampionAvatar(champion = champ, size = 44.dp, showTierBadge = false)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {"""

replacement = """                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f).padding(end = 4.dp)) {
                            ChampionAvatar(champion = champ, size = 44.dp, showTierBadge = false)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {"""

content = content.replace(target, replacement)

target2 = """                                    Text(
                                        text = if (winDelta >= 0) "▲ $winDeltaText" else "▼ $winDeltaText",
                                        color = winDeltaColor,
                                        fontSize = 9.5.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(tr("WR") + ": ${String.format(java.util.Locale.US, "%.2f", champ.winrate)}%", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
                                }
                                Text("Pick: ${String.format(java.util.Locale.US, "%.2f", champ.pickRate)}% • Ban: ${String.format(java.util.Locale.US, "%.2f", champ.banRate)}%", color = TextMuted, fontSize = 10.sp)
                            }"""

replacement2 = """                                    if (!isOverlay) {
                                        Text(
                                            text = if (winDelta >= 0) "▲ $winDeltaText" else "▼ $winDeltaText",
                                            color = winDeltaColor,
                                            fontSize = 9.5.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(tr("WR") + ": ${String.format(java.util.Locale.US, "%.2f", champ.winrate)}%", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
                                }
                                if (!isOverlay) {
                                    Text("Pick: ${String.format(java.util.Locale.US, "%.2f", champ.pickRate)}% • Ban: ${String.format(java.util.Locale.US, "%.2f", champ.banRate)}%", color = TextMuted, fontSize = 10.sp)
                                }
                            }"""
                            
content = content.replace(target2, replacement2)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
