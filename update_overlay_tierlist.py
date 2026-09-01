import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

tierlist_ui = '''                            Spacer(modifier = Modifier.height(6.dp))
                            
                            Text(
                                text = "🏆 " + tr("Tier List Meta (S+) para ${tr(activeRole.shortName)}"),
                                color = HextechGold,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            val topTierChamps = remember(activeRole) {
                                WildRiftRepository.champions.filter { 
                                    it.primaryRole == activeRole && it.tier == "S+" 
                                }.take(3)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                topTierChamps.forEach { champ ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.weight(1f)
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(HextechSurface)
                                            .clickable { selectedChampionDetail = champ }
                                            .padding(4.dp)
                                    ) {
                                        ChampionAvatar(champion = champ, size = 32.dp)
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(champ.name, color = TextPrimary, fontSize = 8.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                    }
                                }
                            }
'''

# Insert it before the "Ver Tier List Completa" button
content = content.replace(
    '                            // Botón de Tier List',
    tierlist_ui + '\n                            // Botón de Tier List'
)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
