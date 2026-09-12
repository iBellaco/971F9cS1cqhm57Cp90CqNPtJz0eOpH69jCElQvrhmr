import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

old_fun = """private fun AdminDashboardHeader(
    onClose: () -> Unit,
    onOpenFeedbackAndSupport: () -> Unit,
    onOpenBroadcast: () -> Unit,
    onOpenNotice: () -> Unit,
    onOpenCpmAnalytics: () -> Unit = {}
) {"""

new_fun = """private fun AdminDashboardHeader(
    onClose: () -> Unit,
    onOpenFeedbackAndSupport: () -> Unit,
    onOpenBroadcast: () -> Unit,
    onOpenNotice: () -> Unit,
    onOpenCpmAnalytics: () -> Unit = {},
    isFullAdmin: Boolean = true
) {"""

content = content.replace(old_fun, new_fun)

old_buttons = """                // Botón Broadcast
                Button(
                    onClick = onOpenBroadcast,
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
                    border = BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Icon(Icons.Default.Campaign, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text("Broadcast", fontSize = 10.sp, color = HextechGold, fontWeight = FontWeight.Medium, maxLines = 1)
                }

                // Botón Configuración de Anuncios
                Button(
                    onClick = onOpenNotice,
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
                    border = BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Icon(Icons.AutoMirrored.Filled.Announcement, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text("Anuncio", fontSize = 10.sp, color = HextechGold, fontWeight = FontWeight.Medium, maxLines = 1)
                }
                
                // Botón Analíticas CPM
                Button(
                    onClick = onOpenCpmAnalytics,
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
                    border = BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text("CPM", fontSize = 10.sp, color = HextechGold, fontWeight = FontWeight.Medium, maxLines = 1)
                }"""

new_buttons = """                if (isFullAdmin) {
                    // Botón Broadcast
                    Button(
                        onClick = onOpenBroadcast,
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
                        border = BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Icon(Icons.Default.Campaign, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("Broadcast", fontSize = 10.sp, color = HextechGold, fontWeight = FontWeight.Medium, maxLines = 1)
                    }

                    // Botón Configuración de Anuncios
                    Button(
                        onClick = onOpenNotice,
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
                        border = BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Announcement, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("Anuncio", fontSize = 10.sp, color = HextechGold, fontWeight = FontWeight.Medium, maxLines = 1)
                    }
                    
                    // Botón Analíticas CPM
                    Button(
                        onClick = onOpenCpmAnalytics,
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
                        border = BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("CPM", fontSize = 10.sp, color = HextechGold, fontWeight = FontWeight.Medium, maxLines = 1)
                    }
                }"""

content = content.replace(old_buttons, new_buttons)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)
