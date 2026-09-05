import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

target = """                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ally Avatar
                    DraftAvatarBox(
                        slot = allySlot,
                        isEnemy = false,
                        isMyRole = activeUserRole == role,
                        onClick = { onPickChampionForRole(true, role) },
                        onRemove = { onRemoveChampionForRole(true, role) }
                    )

                    // Center Role
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(40.dp)) {
                        Icon(painterResource(id = iconRes), contentDescription = label, tint = HextechGold, modifier = Modifier.size(22.dp))
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(label, color = TextSecondary, fontSize = 8.sp, fontWeight = FontWeight.SemiBold)
                    }

                    // Enemy Avatar
                    DraftAvatarBox(
                        slot = enemySlot,
                        isEnemy = true,
                        isMyRole = false,
                        onClick = { onPickChampionForRole(false, role) },
                        onRemove = { onRemoveChampionForRole(false, role) }
                    )
                }"""

replacement = """                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ally Avatar
                    DraftAvatarBox(
                        slot = allySlot,
                        isEnemy = false,
                        isMyRole = activeUserRole == role,
                        onClick = { onPickChampionForRole(true, role) },
                        onRemove = { onRemoveChampionForRole(true, role) }
                    )

                    // Center Role (Horizontal layout to save vertical space)
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.width(60.dp)
                    ) {
                        Icon(painterResource(id = iconRes), contentDescription = label, tint = HextechGold, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(label, color = TextSecondary, fontSize = 9.sp, fontWeight = FontWeight.SemiBold)
                    }

                    // Enemy Avatar
                    DraftAvatarBox(
                        slot = enemySlot,
                        isEnemy = true,
                        isMyRole = false,
                        onClick = { onPickChampionForRole(false, role) },
                        onRemove = { onRemoveChampionForRole(false, role) }
                    )
                }"""

text = text.replace(target, replacement)

# Reduce DraftAvatarBox size slightly to ensure it fits without scrolling
text = text.replace(".size(38.dp)", ".size(32.dp)")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

