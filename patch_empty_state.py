import re

with open('app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt', 'r') as f:
    text = f.read()

target_old = """                            text = if (draftsList.isEmpty()) tr("No tienes drafts guardados aún") else tr("No se encontraron partidas con ese filtro"),
                            color = TextPrimary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (draftsList.isEmpty())
                                tr("Cuando analices o elijas una composición en Selección de Campeones, toca 'Guardar Draft' para registrarla aquí y evaluar tu progreso.")
                            else
                                tr("Intenta cambiar el término de búsqueda o restablecer los filtros de resultado y rol."),"""

target_new = """                            text = if (draftsList.isEmpty()) tr("Tu historial está limpio.") else tr("No se encontraron partidas con ese filtro"),
                            color = TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (draftsList.isEmpty())
                                tr("Ve al Asistente de Draft, crea tu primera composición y guárdala para analizarla después.")
                            else
                                tr("Intenta cambiar el término de búsqueda o restablecer los filtros de resultado y rol."),"""

text = text.replace(target_old, target_new)

# replace icon
text = text.replace('Icons.Default.History', 'androidx.compose.material.icons.filled.Description')

# make icon size larger
text = text.replace('modifier = Modifier.size(72.dp)', 'modifier = Modifier.size(100.dp)')
text = text.replace('modifier = Modifier.size(36.dp)', 'modifier = Modifier.size(48.dp)')

with open('app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt', 'w') as f:
    f.write(text)
