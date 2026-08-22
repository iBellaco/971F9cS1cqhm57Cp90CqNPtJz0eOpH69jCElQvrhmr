import re

with open('app/src/main/java/com/example/ui/components/WelcomePatchDialog.kt', 'r') as f:
    content = f.read()

target = r'(// Patch Features List[\s\S]*?Column\([\s\S]*?\}\n)'

replacement = """// Patch Features List
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0C1322), RoundedCornerShape(12.dp))
                            .border(0.8.dp, Color(0xFF1E293B), RoundedCornerShape(12.dp))
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        PatchFeatureRow(
                            icon = Icons.Default.RocketLaunch,
                            tint = HextechCyan,
                            title = tr("Wild Rift Coach v2.11") + " (" + tr("Actual") + ")",
                            description = tr("Renombre oficial, contadores dinámicos en catálogos y evaluación táctica de tu pick en tiempo real.")
                        )
                        HorizontalDivider(color = Color(0xFF1E293B), thickness = 0.5.dp)
                        PatchFeatureRow(
                            icon = Icons.Default.History,
                            tint = HextechGold,
                            title = tr("Wild Rift Coach v2.0"),
                            description = tr("Lanzamiento del asistente táctico flotante, catálogo de objetos completo y soporte multi-idioma.")
                        )
                        HorizontalDivider(color = Color(0xFF1E293B), thickness = 0.5.dp)
                        PatchFeatureRow(
                            icon = Icons.Default.Sync,
                            tint = Color(0xFF10B981),
                            title = tr("Sincronización Oficial"),
                            description = tr("Base de datos de campeones actualizada al parche del juego: ") + tr(patchVersion)
                        )
                    }
"""

content = re.sub(target, replacement, content, count=1)

with open('app/src/main/java/com/example/ui/components/WelcomePatchDialog.kt', 'w') as f:
    f.write(content)
