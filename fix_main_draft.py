import re

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

# Remove onNavigateToTutorial parameter
content = content.replace("    onNavigateToTutorial: () -> Unit,\n", "")

# Fix the button row: The original "Info" button had weight(1f), and next to it was the Tutorial button.
# If I just remove the tutorial button, Info will take the full width which is fine, or I can leave it.
# Actually, I should remove the tutorial button completely.

bad_str = """                    androidx.compose.material3.OutlinedButton(
                        onClick = onNavigateToTutorial,
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("btn_tutorial_top"),
                        shape = RoundedCornerShape(12.dp),
                        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                            containerColor = HextechSurface.copy(alpha = 0.9f),
                            contentColor = HextechCyan
                        ),
                        border = BorderStroke(1.2.dp, HextechCyan.copy(alpha = 0.7f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = HextechCyan,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = tr("Guía"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    }"""

content = content.replace(bad_str, "")

# Some spacer might be between them
content = re.sub(r'Spacer\(modifier = Modifier\.width\(12\.dp\)\)\s*$', '', content, flags=re.MULTILINE)
# Wait, let's look at the spacer before the tutorial button
