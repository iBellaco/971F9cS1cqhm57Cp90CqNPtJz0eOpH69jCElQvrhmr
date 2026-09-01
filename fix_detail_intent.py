with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

bad_str = """                                        if (champ.synergies.isNotEmpty()) {
                                            Text(
                                                text = "🤝 Sinergias: " + champ.synergies.joinToString(", "),"""

good_str = """                                        if (champ.synergies.isNotEmpty()) {
                                            Text(
                                                text = "🤝 Sinergias: " + champ.synergies.joinToString(", "),
                                                color = AllyBlue,
                                                fontSize = 8.5.sp,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Button(
                                            onClick = {
                                                val intent = android.content.Intent(context, com.example.MainActivity::class.java).apply {
                                                    flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
                                                    putExtra("OPEN_CHAMPION_DETAIL", champ.id)
                                                }
                                                context.startActivity(intent)
                                            },
                                            modifier = Modifier.fillMaxWidth().height(26.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                                            contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                                        ) {
                                            Text("Ver Build Completa", color = HextechDarkBg, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                        }
                                        if (champ.synergies.isNotEmpty() && false) { // disable old block
                                            Text(
                                                text = "🤝 Sinergias: " + champ.synergies.joinToString(", "),"""

content = content.replace(bad_str, good_str)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
