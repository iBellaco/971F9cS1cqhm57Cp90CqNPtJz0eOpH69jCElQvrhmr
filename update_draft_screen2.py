import re

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

about_btn_code = '''                // Botón "Acerca De" situado en la parte superior
                androidx.compose.material3.OutlinedButton(
                    onClick = onNavigateToInfo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .testTag("btn_about_top"),
                    shape = RoundedCornerShape(12.dp),
                    colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                        containerColor = HextechSurface.copy(alpha = 0.9f),
                        contentColor = HextechGold
                    ),
                    border = BorderStroke(1.2.dp, HextechGold.copy(alpha = 0.7f))
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = HextechGold,
                        modifier = Modifier.size(19.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = tr("Acerca De") + " • " + tr("Guía & Metodología Coach"),
                        color = HextechGold,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }'''

new_btns_code = '''                // Botones Superiores
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    androidx.compose.material3.OutlinedButton(
                        onClick = onNavigateToInfo,
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("btn_about_top"),
                        shape = RoundedCornerShape(12.dp),
                        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                            containerColor = HextechSurface.copy(alpha = 0.9f),
                            contentColor = HextechGold
                        ),
                        border = BorderStroke(1.2.dp, HextechGold.copy(alpha = 0.7f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = tr("Info"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                    
                    androidx.compose.material3.OutlinedButton(
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
                            imageVector = Icons.Default.MenuBook,
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
                    }
                }'''

content = content.replace(about_btn_code, new_btns_code)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
