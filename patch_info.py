import re

file_path = "app/src/main/java/com/example/ui/screens/InfoScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

replacement = """                // SECCIÓN 3: FUENTES WEB DEL META (EN LA APP)
                SectionHeader(
                    icon = Icons.Default.Sync,
                    title = tr("3. Fuentes Web del Meta (Visor en la App)"),
                    color = HextechCyan
                )
                Text(
                    text = tr("Toca cualquier fuente para consultar sus datos directamente dentro de la aplicación:"),
                    color = TextMuted,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                WildRiftRepository.metaSources.forEach { source ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                activeWebUrl = source.url
                                activeWebTitle = source.name
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Language,
                                    contentDescription = null,
                                    tint = HextechCyan,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = source.name,
                                        color = TextPrimary,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = tr(source.focusArea), // TRANSLATION ADDED HERE
                                        color = TextMuted,
                                        fontSize = 11.5.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = source.url,
                                        color = HextechCyan.copy(alpha = 0.7f),
                                        fontSize = 10.sp
                                    )
                                }
                            }
                            
                            Box(
                                modifier = Modifier
                                    .border(1.dp, HextechCardBorder, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(tr("Abrir"), color = HextechGold, fontSize = 12.sp, fontWeight = FontWeight.Bold) // TRANSLATION ADDED HERE
                            }
                        }
                    }
                }"""

# Using regex to replace the whole section safely
# We know it starts at "// SECCIÓN 3: FUENTES WEB DEL META" and ends right before "// SECCIÓN 4"

content = re.sub(
    r'// SECCIÓN 3: FUENTES WEB DEL META \(EN LA APP\).*?(?=// SECCIÓN 4:)',
    replacement + '\n\n                ',
    content,
    flags=re.DOTALL
)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
print("InfoScreen patched!")
