with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r", encoding="utf-8") as f:
    lines = f.readlines()

out = []
in_block = False
for i, line in enumerate(lines):
    if "var isIgnoringBatteryOpt by remember { mutableStateOf(SystemPermissionHelper.isIgnoringBatteryOptimizations(context)) }" in line:
        in_block = True
        
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
                }\n"""
        out.append(replacement)
        continue

    if in_block:
        if "Spacer(modifier = Modifier.height(16.dp))" in line and "Top Meta Sinergias" not in line:
            in_block = False
            # Wait, how many Spacers are there? Let's be careful. Let's just count closing brackets.
        
        # Let's use a better approach: regex on the whole file string.
