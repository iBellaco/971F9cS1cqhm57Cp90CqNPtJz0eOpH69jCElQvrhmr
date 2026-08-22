import re

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    text = f.read()

# Fix "Runas Meta" -> "Runas"
text = text.replace('text = "${tr("Runas Meta")} • ${selectedRole.shortName}",', 
                    'text = "${tr("Runas")} • ${selectedRole.shortName}",')
text = text.replace('text = "${tr("Runas Meta")} • ",', 
                    'text = "${tr("Runas")} • ",')

# Fix "Toca situacionales" text, make it clickable or just change the title
# Actually, the user says "al presionar ítem situacionales no pasa nada".
# Let's make the Swap cards clickable:
target_card = """                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                                    .border(1.dp, HextechCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF07121A)),
                                shape = RoundedCornerShape(12.dp)
                            ) {"""
replacement_card = """                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                                    .border(1.dp, HextechCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                                    .clickable {
                                        matchupExplanationTarget = swap.altItem
                                        matchupExplanationType = "Situacional"
                                    },
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF07121A)),
                                shape = RoundedCornerShape(12.dp)
                            ) {"""
text = text.replace(target_card, replacement_card)

# Also let's make the "Objetos Básicos" (Core Items) say "Build Completa (6 Objetos)"
text = text.replace('Text(tr("Objetos Básicos:"), color = HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)',
                    'Text(tr("Build Completa (6 Objetos):"), color = HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)')

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(text)
print("Updated UI")
