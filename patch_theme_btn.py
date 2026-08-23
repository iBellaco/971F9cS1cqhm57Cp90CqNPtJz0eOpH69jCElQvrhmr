import re

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    content = f.read()

import_str = "import androidx.compose.material.icons.filled.Info\nimport androidx.compose.material.icons.filled.LightMode\nimport androidx.compose.material.icons.filled.DarkMode"
content = content.replace("import androidx.compose.material.icons.filled.Info", import_str)

nav_icon_old = """                            IconButton(
                                onClick = onNavigateToInfo,
                                modifier = Modifier
                                    .padding(start = 6.dp)
                                    .clip(CircleShape)
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.6f), CircleShape)
                                    .size(38.dp)
                                    .testTag("nav_about_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Acerca De",
                                    tint = HextechGold,
                                    modifier = Modifier.size(22.dp)
                                )
                            }"""

nav_icon_new = """                            IconButton(
                                onClick = onNavigateToInfo,
                                modifier = Modifier
                                    .padding(start = 6.dp)
                                    .clip(CircleShape)
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.6f), CircleShape)
                                    .size(38.dp)
                                    .testTag("nav_about_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Acerca De",
                                    tint = HextechGold,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(
                                onClick = { isLightAppTheme = !isLightAppTheme },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.6f), CircleShape)
                                    .size(38.dp)
                                    .testTag("nav_theme_button")
                            ) {
                                Icon(
                                    imageVector = if (isLightAppTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                                    contentDescription = "Toggle Theme",
                                    tint = HextechGold,
                                    modifier = Modifier.size(22.dp)
                                )
                            }"""

content = content.replace(nav_icon_old, nav_icon_new)

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(content)
