import re

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

# I want to remove the `if (!isIgnoringBatteryOpt) {` condition but keep the Card.
old_block = '''                if (!isIgnoringBatteryOpt) {
                    // Recomendación de Segundo Plano y Batería
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .border(1.dp, HextechGold.copy(alpha = 0.45f), RoundedCornerShape(14.dp)),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.9f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(HextechGold.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.BatteryChargingFull,
                                    contentDescription = null,
                                    tint = HextechGold,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = tr("Rendimiento en Segundo Plano"),
                                    color = HextechGold,
                                    fontSize = 12.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = tr("Para un funcionamiento óptimo, permite la actividad en segundo plano y desactiva el ahorro de batería en esta app."),
                                    color = TextSecondary,
                                    fontSize = 11.sp,
                                    lineHeight = 14.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            OutlinedButton(
                                onClick = {
                                    SystemPermissionHelper.requestIgnoreBatteryOptimization(context)
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechCyan),
                                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.7f)),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                modifier = Modifier.height(32.dp)
                            ) {
                                Text(tr("Ajustes"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }'''

new_block = '''                // Recomendación de Segundo Plano y Batería
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .border(1.dp, HextechGold.copy(alpha = 0.45f), RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.9f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(HextechGold.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.BatteryChargingFull,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = tr("Rendimiento en Segundo Plano"),
                                color = HextechGold,
                                fontSize = 12.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (isIgnoringBatteryOpt) tr("✔ Sin restricciones (Optimizado para segundo plano)") else tr("Para un funcionamiento óptimo, permite la actividad en segundo plano y desactiva el ahorro de batería en esta app."),
                                color = if (isIgnoringBatteryOpt) HextechCyan else TextSecondary,
                                fontSize = 11.sp,
                                lineHeight = 14.sp
                            )
                        }
                        
                        if (!isIgnoringBatteryOpt) {
                            Spacer(modifier = Modifier.width(8.dp))
                            OutlinedButton(
                                onClick = {
                                    SystemPermissionHelper.requestIgnoreBatteryOptimization(context)
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechCyan),
                                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.7f)),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                modifier = Modifier.height(32.dp)
                            ) {
                                Text(tr("Ajustes"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))'''

content = content.replace(old_block, new_block)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
