import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'text = "🛡️ ${champ.name} • ${tr("Runas y Core")}",',
    'text = "🛡️ ${champ.name} • ${tr("Build Core")}",'
)
# Hide the Runa Clave and runeTreeDetails from the overlay
old_rune_clave = '''                                        Text(
                                            text = "${tr("Runa Clave")}: ${champ.recommendedRunes}",
                                            color = HextechCyan,
                                            fontSize = 9.5.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        if (champ.runeTreeDetails.isNotBlank()) {
                                            Text(
                                                text = champ.runeTreeDetails,
                                                color = TextPrimary,
                                                fontSize = 8.5.sp,
                                                lineHeight = 11.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(3.dp))'''
content = content.replace(old_rune_clave, '')

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
