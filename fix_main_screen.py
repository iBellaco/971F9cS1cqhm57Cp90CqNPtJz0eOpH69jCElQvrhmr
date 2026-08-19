with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    c = f.read()

import re
c = re.sub(
    r'onAutofillRoleChange: \(LaneRole\) -> Unit\n\)',
    '''onAutofillRoleChange: (LaneRole) -> Unit,
    currentLanguage: String = "es",
    onLanguageChange: (String) -> Unit = {}
)''', c)

# Now add dropdown menu logic
actions_replacement = '''actions = {
                        var expandedLang by remember { mutableStateOf(false) }
                        val currentFlag = when(currentLanguage) {
                            "en" -> "🇺🇸/🇬🇧"
                            "pt" -> "🇧🇷/🇵🇹"
                            else -> "🇪🇸"
                        }
                        Box {
                            TextButton(
                                onClick = { expandedLang = true },
                                modifier = Modifier
                                    .padding(end = 6.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                            ) {
                                Text(currentFlag, fontSize = 16.sp)
                            }
                            androidx.compose.material3.DropdownMenu(
                                expanded = expandedLang,
                                onDismissRequest = { expandedLang = false },
                                modifier = Modifier.background(HextechSurface)
                            ) {
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { Text("🇪🇸 Español", color = TextPrimary) },
                                    onClick = { 
                                        onLanguageChange("es")
                                        expandedLang = false 
                                    }
                                )
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { Text("🇺🇸/🇬🇧 English", color = TextPrimary) },
                                    onClick = { 
                                        onLanguageChange("en")
                                        expandedLang = false 
                                    }
                                )
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { Text("🇧🇷/🇵🇹 Português", color = TextPrimary) },
                                    onClick = { 
                                        onLanguageChange("pt")
                                        expandedLang = false 
                                    }
                                )
                            }
                        }

                        IconButton(
                            onClick = { showBugReportDialog = true },'''

c = c.replace('actions = {\n                        IconButton(\n                            onClick = { showBugReportDialog = true },', actions_replacement)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(c)
