import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    content = f.read()

old_btn = """                        if (userRole == "admin" || userRole == "moderador") {
                val adminInteractionSource = remember { MutableInteractionSource() }
                val adminPressed by adminInteractionSource.collectIsPressedAsState()
                val adminScale by animateFloatAsState(
                    targetValue = if (adminPressed) 0.95f else 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    label = "adminBtnScale"
                )
                Button(
                    onClick = { showAdminDashboard = true },
                    colors = ButtonDefaults.buttonColors(containerColor = com.example.ui.theme.DangerRed),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .scale(adminScale),
                    interactionSource = adminInteractionSource,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(imageVector = Icons.Default.AdminPanelSettings, contentDescription = null, tint = com.example.ui.theme.HextechDarkBg)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Panel de Administración", color = com.example.ui.theme.HextechDarkBg, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                }"""

new_btn = """                        if (userRole == "admin" || userRole == "moderador") {
                val adminInteractionSource = remember { MutableInteractionSource() }
                val adminPressed by adminInteractionSource.collectIsPressedAsState()
                val adminScale by animateFloatAsState(
                    targetValue = if (adminPressed) 0.95f else 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    label = "adminBtnScale"
                )
                Button(
                    onClick = { showAdminDashboard = true },
                    colors = ButtonDefaults.buttonColors(containerColor = if (userRole == "admin") com.example.ui.theme.DangerRed else com.example.ui.theme.HextechCyan),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .scale(adminScale),
                    interactionSource = adminInteractionSource,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(imageVector = if (userRole == "admin") Icons.Default.AdminPanelSettings else Icons.Default.SupportAgent, contentDescription = null, tint = com.example.ui.theme.HextechDarkBg)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(if (userRole == "admin") "Panel de Administración" else "Panel de Moderador", color = com.example.ui.theme.HextechDarkBg, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                }"""

if old_btn in content:
    content = content.replace(old_btn, new_btn)
else:
    print("Could not find the old button block.")

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(content)
