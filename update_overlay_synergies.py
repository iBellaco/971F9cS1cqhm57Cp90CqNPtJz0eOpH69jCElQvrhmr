import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Add wombo combos detector imports if needed
content = content.replace(
    'import com.example.model.LaneRole',
    'import com.example.model.LaneRole\nimport com.example.model.WomboComboSynergyDetector'
)

# We want to display wombos right after analysis recommendations or before it.
wombo_ui = '''                            // Sinergias (Wombos)
                            val allyWombos = remember(allies.toList()) { WomboComboSynergyDetector.detectWombos(allies.toList()) }
                            val enemyWombos = remember(enemies.toList()) { WomboComboSynergyDetector.detectWombos(enemies.toList()) }

                            if (allyWombos.isNotEmpty() || enemyWombos.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    allyWombos.forEach { wombo ->
                                        Card(
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha=0.6f)),
                                            border = androidx.compose.foundation.BorderStroke(0.5.dp, AllyBlue)
                                        ) {
                                            Text(text = "🔵 ${wombo.name}: ${wombo.description}", color = AllyBlue, fontSize = 9.sp, modifier = Modifier.padding(4.dp))
                                        }
                                    }
                                    enemyWombos.forEach { wombo ->
                                        Card(
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha=0.6f)),
                                            border = androidx.compose.foundation.BorderStroke(0.5.dp, DangerRed)
                                        ) {
                                            Text(text = "🔴 ${wombo.name}: ${wombo.description}", color = DangerRed, fontSize = 9.sp, modifier = Modifier.padding(4.dp))
                                        }
                                    }
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(6.dp))
                            
                            // Botón de Tier List
                            Button(
                                onClick = {
                                    // TODO: Emit intent to open Tier List in main app or show simple modal
                                    val intent = android.content.Intent(this@FloatingAssistantService, com.example.MainActivity::class.java).apply {
                                        flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
                                        putExtra("OPEN_TIER_LIST", true)
                                    }
                                    startActivity(intent)
                                },
                                modifier = Modifier.fillMaxWidth().height(28.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                            ) {
                                Text("Ver Tier List Completa", color = HextechDarkBg, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
'''

content = content.replace(
    '                            // 3. MEJORES PICKS RECOMENDADOS POR EL COACH',
    wombo_ui + '\n                            // 3. MEJORES PICKS RECOMENDADOS POR EL COACH'
)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
