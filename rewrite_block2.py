import re

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r", encoding="utf-8") as f:
    content = f.read()

pattern = re.compile(
    r'var isIgnoringBatteryOpt by remember \{ mutableStateOf\(SystemPermissionHelper\.isIgnoringBatteryOptimizations\(context\)\) \}.*?// Recomendación de Segundo Plano y Batería\s+Card\([^\{]+\{[^\}]+\}\s*\}\s*\}\s*\}\s*\}', 
    re.DOTALL
)

# wait, the Card has multiple nested blocks.
# Let's find the exact string.
start_str = "var isIgnoringBatteryOpt by remember { mutableStateOf(SystemPermissionHelper.isIgnoringBatteryOptimizations(context)) }"
idx1 = content.find(start_str)

if idx1 != -1:
    end_str = "                                Text(tr(\"Ajustes\"), fontSize = 11.sp, fontWeight = FontWeight.Bold)\n                            }\n                        }\n                    }\n                }"
    idx2 = content.find(end_str, idx1) + len(end_str)
    
    if idx2 > idx1:
        replacement = """var isIgnoringBatteryOpt by remember { mutableStateOf(SystemPermissionHelper.isIgnoringBatteryOptimizations(context)) }
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
                                        Text(text = tr("Requerido para usar el panel flotante sobre el juego."), color = TextSecondary, fontSize = 10.5.sp)
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
                }"""
        
        new_content = content[:idx1] + replacement + content[idx2:]
        if "import androidx.compose.material.icons.filled.Warning" not in new_content:
            new_content = new_content.replace("import androidx.compose.material.icons.filled.BatteryChargingFull", "import androidx.compose.material.icons.filled.BatteryChargingFull\nimport androidx.compose.material.icons.filled.Warning")
        with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w", encoding="utf-8") as f:
            f.write(new_content)
        print("Success")
    else:
        print("Failed to find end index")
else:
    print("Failed to find start index")
