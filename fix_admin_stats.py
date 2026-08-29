import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

target = """                // Push Notifications Button
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { showPushDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = HextechGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Enviar Notificación Push", color = TextPrimary)
                }"""

replacement = """                // Push Notifications Button
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { showPushDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = HextechGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Enviar Notificación Push", color = TextPrimary)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                PushNotificationStatsPanel()"""

if target in text:
    text = text.replace(target, replacement)
    with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
        f.write(text)
    print("Replaced button section successfully")
else:
    print("Target button section not found.")

