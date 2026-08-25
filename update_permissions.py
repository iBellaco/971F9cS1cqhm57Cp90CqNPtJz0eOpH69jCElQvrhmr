import re

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r", encoding="utf-8") as f:
    content = f.read()

target = """                var isIgnoringBatteryOpt by remember { mutableStateOf(SystemPermissionHelper.isIgnoringBatteryOptimizations(context)) }
                DisposableEffect(lifecycleOwner) {
                    val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
                        if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                            isIgnoringBatteryOpt = SystemPermissionHelper.isIgnoringBatteryOptimizations(context)
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }

                // Recomendación de Segundo Plano y Batería
                Card("""

replacement = """                var isIgnoringBatteryOpt by remember { mutableStateOf(SystemPermissionHelper.isIgnoringBatteryOptimizations(context)) }
                var hasOverlayPermission by remember { mutableStateOf(SystemPermissionHelper.hasOverlayPermission(context)) }

                DisposableEffect(lifecycleOwner) {
                    val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
                        if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                            isIgnoringBatteryOpt = SystemPermissionHelper.isIgnoringBatteryOptimizations(context)
                            hasOverlayPermission = SystemPermissionHelper.hasOverlayPermission(context)
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }

                if (!isIgnoringBatteryOpt || !hasOverlayPermission) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .border(1.dp, HextechGold.copy(alpha = 0.45f), RoundedCornerShape(14.dp)),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.9f))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Warning, contentDescription = null, tint = HextechGold, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = tr("Recomendaciones de Rendimiento"),
                                    color = HextechGold,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            if (!hasOverlayPermission) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(text = tr("Permiso de Superposición"), color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        Text(text = tr("Para usar el panel flotante sobre el juego."), color = TextSecondary, fontSize = 10.5.sp)
                                    }
                                    OutlinedButton(
                                        onClick = { SystemPermissionHelper.openOverlaySettings(context) },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechCyan),
                                        border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.7f)),
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                        modifier = Modifier.height(30.dp)
                                    ) {
                                        Text(tr("Activar"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                            if (!isIgnoringBatteryOpt) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(text = tr("Rendimiento en Segundo Plano"), color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        Text(text = tr("Para evitar que Android cierre la app."), color = TextSecondary, fontSize = 10.5.sp)
                                    }
                                    OutlinedButton(
                                        onClick = { SystemPermissionHelper.requestIgnoreBatteryOptimization(context) },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechCyan),
                                        border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.7f)),
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                        modifier = Modifier.height(30.dp)
                                    ) {
                                        Text(tr("Ajustes"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
"""
                
# Remove the old card
end_pattern = """                        }
                    }
                }"""
                
idx_start = content.find(target)
if idx_start != -1:
    idx_end = content.find(end_pattern, idx_start + len(target)) + len(end_pattern)
    new_content = content[:idx_start] + replacement + content[idx_end:]
    
    # Also add "Warning" import if not there
    if "import androidx.compose.material.icons.filled.Warning" not in new_content:
        new_content = new_content.replace("import androidx.compose.material.icons.filled.BatteryChargingFull", "import androidx.compose.material.icons.filled.BatteryChargingFull\nimport androidx.compose.material.icons.filled.Warning")
    
    with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w", encoding="utf-8") as f:
        f.write(new_content)
    print("Updated MainDraftingScreen")
else:
    print("Could not find target block")

