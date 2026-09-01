import re

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

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
                            text = tr("Tutorial"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    }"""

content = content.replace(bad_str, "")

# And spacer between Info and Tutorial
content = content.replace("Spacer(modifier = Modifier.width(12.dp))", "")

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
