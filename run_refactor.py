import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# Add the new composable at the bottom
new_composable = """

@Composable
private fun TierSelectionPanel(
    currentTier: TencentRankTier,
    syncState: ChineseSyncState,
    context: android.content.Context,
    coroutineScope: kotlinx.coroutines.CoroutineScope
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                TencentRankTier.entries.forEach { tier ->
                    val isSelected = currentTier == tier
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 34.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (isSelected) HextechCyan.copy(alpha = 0.25f) else HextechSurfaceVariant.copy(alpha = 0.4f)
                            )
                            .border(
                                width = if (isSelected) 1.dp else 0.5.dp,
                                color = if (isSelected) HextechCyan else HextechCardBorder,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable {
                                coroutineScope.launch {
                                    ChineseMetaSyncService.syncChineseMeta(context, tier, forceRefresh = true)
                                }
                            }
                            .padding(horizontal = 2.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tr(tier.shortName),
                            color = if (isSelected) HextechCyan else TextMuted,
                            fontSize = 8.5.sp,
                            lineHeight = 10.5.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            maxLines = 2
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            val lastSyncInfo = remember(syncState) { ChineseMetaSyncService.getLastSyncInfo(context) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (val s = syncState) {
                        is ChineseSyncState.Syncing -> tr("Sincronizando...")
                        is ChineseSyncState.Success -> "🟢 ${tr("En vivo:")} ${s.timestamp} (${tr(s.tier.displayName)})"
                        is ChineseSyncState.Error -> "⚠️ ${tr("Caché:")} ${lastSyncInfo.second}"
                        ChineseSyncState.Idle -> "🟢 ${lastSyncInfo.second}"
                    },
                    color = when (syncState) {
                        is ChineseSyncState.Syncing -> HextechCyan
                        is ChineseSyncState.Success -> Color(0xFF4CAF50)
                        is ChineseSyncState.Error -> Color(0xFFFFA726)
                        ChineseSyncState.Idle -> TextMuted
                    },
                    fontSize = 9.5.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = tr("Instantáneo 24/7"),
                    color = HextechGold,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
"""

if "fun TierSelectionPanel" not in text:
    text += new_composable

# Replace in TierListTab
# First, let's find the entire Card block in TierListTab
tier_card_regex = r'// PANEL DE ESTADÍSTICAS Y METAGAME OFICIAL EN VIVO EN TIER LIST\s*Card\(.*?Instantáneo 24/7"\),\s*color = HextechGold,\s*fontSize = 9\.sp,\s*fontWeight = FontWeight\.Bold\s*\)\s*\}\s*\}\s*\}'
text = re.sub(tier_card_regex, 'TierSelectionPanel(currentTier, syncState, context, coroutineScope)', text, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
