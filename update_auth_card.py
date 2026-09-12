import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    content = f.read()

# We need to find the entire Card(...) block and replace it.
start_str = "// Premium Status Card & Expiration Indicator"
end_str = "// Expiring soon alert banner"

start_idx = content.find(start_str)
end_idx = content.find(end_str)

if start_idx != -1 and end_idx != -1:
    new_card = """// Premium Status Card & Expiration Indicator
            val infiniteTransition = rememberInfiniteTransition(label = "rolePulse")
            val rolePulseAlpha by infiniteTransition.animateFloat(
                initialValue = 0.6f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1200, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "rolePulseAlpha"
            )
            
            val roleContainerColor = when {
                userRole == "admin" || userRole == "moderador" -> com.example.ui.theme.HextechGold.copy(alpha = 0.12f)
                isExpiringSoon -> com.example.ui.theme.DangerRed.copy(alpha = 0.12f)
                isPremium -> com.example.ui.theme.HextechGold.copy(alpha = 0.1f)
                else -> com.example.ui.theme.HextechSurfaceVariant.copy(alpha = 0.5f)
            }
            
            val roleBorderColor = when {
                userRole == "admin" || userRole == "moderador" -> com.example.ui.theme.HextechGold.copy(alpha = rolePulseAlpha)
                isExpiringSoon -> com.example.ui.theme.DangerRed
                isPremium -> com.example.ui.theme.HextechGold.copy(alpha = rolePulseAlpha)
                else -> com.example.ui.theme.TextMuted.copy(alpha = 0.5f)
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = roleContainerColor),
                border = BorderStroke(1.2.dp, roleBorderColor),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (isExpiringSoon) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = com.example.ui.theme.DangerRed,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                }
                                Text(
                                    text = when {
                                        userRole == "admin" -> "👑 Administrador"
                                        userRole == "moderador" -> "🛡️ Moderador"
                                        userRole == "streamer" || userRole == "river" -> "📹 Streamer"
                                        userRole == "creador_vip" -> "✨ Creador VIP"
                                        userRole == "creador" -> "🎨 Creador"
                                        isExpiringSoon -> "⚠️ Suscripción por Vencer"
                                        isPremium -> "🌟 Suscripción Activa"
                                        else -> "Plan Gratuito"
                                    },
                                    color = when {
                                        isExpiringSoon -> com.example.ui.theme.DangerRed
                                        isPremium || userRole == "admin" || userRole == "moderador" || userRole == "creador" -> com.example.ui.theme.HextechGold
                                        else -> com.example.ui.theme.TextPrimary
                                    },
                                    fontSize = 15.sp,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = when {
                                    userRole == "admin" -> "Acceso vitalicio ilimitado a todas las funciones"
                                    userRole == "moderador" -> "Acceso a panel de soporte y OCR"
                                    isPremium -> "⏳ $remainingFormatted"
                                    else -> "Funciones básicas limitadas"
                                },
                                color = if (isExpiringSoon) com.example.ui.theme.DangerRed.copy(alpha = 0.9f) else com.example.ui.theme.TextSecondary,
                                fontSize = 12.sp,
                                fontWeight = if (isExpiringSoon) androidx.compose.ui.text.font.FontWeight.SemiBold else androidx.compose.ui.text.font.FontWeight.Normal
                            )
                        }
                        
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    when {
                                        userRole == "admin" || userRole == "moderador" -> com.example.ui.theme.DangerRed.copy(alpha = rolePulseAlpha)
                                        isExpiringSoon -> com.example.ui.theme.DangerRed
                                        isPremium -> com.example.ui.theme.HextechGold.copy(alpha = rolePulseAlpha)
                                        else -> com.example.ui.theme.HextechSurface
                                    }
                                )
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isExpiringSoon) "EXPIRA PRONTO" else userRole.replace("_", " ").uppercase(),
                                color = if (userRole == "free") com.example.ui.theme.TextPrimary else com.example.ui.theme.HextechDarkBg,
                                fontSize = 10.5.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold
                            )
                        }
                    }
                    """
    
    content = content[:start_idx] + new_card + content[end_idx:]
    with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
        f.write(content)
else:
    print("Could not find bounds")
