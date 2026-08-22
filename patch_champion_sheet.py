import re

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    content = f.read()

# Locate the situationalItems block
situational_target = """                    if (roleProfile.situationalItems.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(tr("Objetos Situacionales:"), color = HextechGoldLight, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Text(tr("⚡ Toca para ver info"), color = HextechCyan, fontSize = 10.5.sp)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            roleProfile.situationalItems.forEachIndexed { idx, rawName ->
                                val iconUrl = roleProfile.situationalItemsIcons.getOrNull(idx) ?: ""
                                val itemName = tr(rawName)
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFF0C1929))
                                        .border(1.dp, HextechCyan.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                        .clickable { selectedSituationalItem = rawName }
                                        .padding(horizontal = 8.dp, vertical = 5.dp)
                                ) {
                                    AppAssetImage(
                                        url = iconUrl,
                                        contentDescription = itemName,
                                        fallbackText = itemName,
                                        modifier = Modifier.size(24.dp),
                                        borderColor = HextechCyan,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(itemName, color = HextechCyan, fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold)
                                }
                            }
                        }
                    }"""
situational_replacement = """                    if (roleProfile.itemSwaps.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(tr("CAMBIOS SITUACIONALES"), color = HextechGoldLight, fontSize = 12.sp, fontWeight = FontWeight.Black)
                        Spacer(modifier = Modifier.height(10.dp))
                        
                        roleProfile.itemSwaps.forEach { swap ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                                    .border(1.dp, HextechCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF07121A)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Text(tr(swap.reasonTitle), color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Black)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        // Core Item
                                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                                            AppAssetImage(
                                                url = swap.coreItemIcon,
                                                contentDescription = tr(swap.coreItem),
                                                fallbackText = tr(swap.coreItem),
                                                modifier = Modifier.size(42.dp),
                                                borderColor = HextechGold,
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(tr(swap.coreItem), color = TextMuted, fontSize = 10.sp, textAlign = TextAlign.Center, lineHeight = 12.sp)
                                        }
                                        
                                        // Arrow
                                        Box(
                                            modifier = Modifier
                                                .padding(horizontal = 8.dp)
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(HextechCyan.copy(alpha = 0.2f))
                                                .border(1.dp, HextechCyan, RoundedCornerShape(6.dp))
                                                .padding(6.dp)
                                        ) {
                                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Swap", tint = HextechGold, modifier = Modifier.size(16.dp))
                                        }
                                        
                                        // Alt Item
                                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                                            AppAssetImage(
                                                url = swap.altItemIcon,
                                                contentDescription = tr(swap.altItem),
                                                fallbackText = tr(swap.altItem),
                                                modifier = Modifier.size(42.dp),
                                                borderColor = HextechCyan,
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(tr(swap.altItem), color = TextPrimary, fontSize = 10.sp, textAlign = TextAlign.Center, lineHeight = 12.sp)
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(tr(swap.reasonDesc), color = TextPrimary, fontSize = 12.sp, lineHeight = 16.sp)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(tr("Bueno contra:") + " " + tr(swap.againstWho), color = HextechGoldLight, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                                }
                            }
                        }
                    }"""

content = content.replace(situational_target, situational_replacement)

# Ensure Icons import exists
if 'import androidx.compose.material.icons.automirrored.filled.ArrowForward' not in content:
    content = content.replace('import androidx.compose.material.icons.Icons', 'import androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.automirrored.filled.ArrowForward')

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(content)

