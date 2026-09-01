import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

bad_block = '''                            // 4. Sinergias (Aliadas y Enemigas)
                            val allyWombos = remember(allies.toList()) {
                                com.example.ui.components.WomboComboSynergyDetector.detectWombos(allies)
                            }
                            val enemyWombos = remember(enemies.toList()) {
                                com.example.ui.components.WomboComboSynergyDetector.detectWombos(enemies)
                            }

                            if (allyWombos.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(tr("🔵 Sinergias Aliadas Detectadas:"), color = AllyBlue, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                allyWombos.forEach { wombo ->
                                    Text("• ${wombo.title}: ${wombo.champ1.name} + ${wombo.champ2.name}", color = TextPrimary, fontSize = 9.sp)
                                }
                            }

                            if (enemyWombos.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(tr("🔴 Peligro: Sinergias Enemigas:"), color = DangerRed, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                enemyWombos.forEach { wombo ->
                                    Text("• ${wombo.title}: ${wombo.champ1.name} + ${wombo.champ2.name}", color = TextPrimary, fontSize = 9.sp)
                                }
                            }'''

content = content.replace(bad_block, '')

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
