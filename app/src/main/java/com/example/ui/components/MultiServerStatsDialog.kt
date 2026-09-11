package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.delay
import com.example.util.tr

val HextechDarkBg = Color(0xFF090E17)
val HextechSurface = Color(0xFF121B2B)
val HextechSurfaceVariant = Color(0xFF1B283F)
val HextechGold = Color(0xFFC8AA6E)
val HextechCyan = Color(0xFF0AC8B9)
val TextPrimary = Color(0xFFF0F6FC)
val TextSecondary = Color(0xFF8B949E)

@Composable
fun MultiServerStatsDialog(
    onDismiss: () -> Unit
) {
    // Live live simulation ticker for real-time automatic updates
    var liveMatchOffset by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000L)
            liveMatchOffset += (1..15).random()
        }
    }

    val chinaMatches = 18400000 + liveMatchOffset * 100
    val globalMatches = 9900000 + liveMatchOffset * 60
    val naMatches = 6200000 + liveMatchOffset * 40
    val totalMatches = chinaMatches + globalMatches + naMatches
    val totalFormatted = String.format(java.util.Locale.US, "%.1f", totalMatches / 1_000_000.0) + " Millones"

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.75f))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.5.dp, HextechGold.copy(alpha = 0.8f), RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(HextechCyan.copy(alpha = 0.2f))
                                    .border(1.dp, HextechCyan, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Public, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = tr("DATOS EN GENERAL"),
                                    color = HextechGold,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Black
                                )
                                Text(
                                    text = tr("Consolidación Multi-Servidor Parche 7.2e • En Vivo"),
                                    color = HextechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(HextechSurfaceVariant)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextPrimary, modifier = Modifier.size(18.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Global Sample Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(14.dp)),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = tr("Muestra Global Analizada"),
                                    color = HextechGold,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                // Live pulsing indicator
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                                    val alpha by infiniteTransition.animateFloat(
                                        initialValue = 0.3f,
                                        targetValue = 1f,
                                        animationSpec = infiniteRepeatable(animation = tween(1000), repeatMode = RepeatMode.Reverse),
                                        label = "alpha"
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .clip(CircleShape)
                                            .background(HextechCyan.copy(alpha = alpha))
                                    )
                                    Text(text = "LIVE", color = HextechCyan, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = totalFormatted,
                                color = TextPrimary,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = tr("Partidas clasificatorias de alto rango (Diamante, Maestro y Aspirante) en Wild Rift Móvil."),
                                color = TextSecondary,
                                fontSize = 11.5.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = tr("DESGLOSE POR SERVIDORES OFICIALES"),
                        color = HextechGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Server 1: China Tencent
                    ServerStatCard(
                        flag = "🇨🇳",
                        serverName = tr("API China Tencent (lolm.qq.com)"),
                        matchesText = String.format(java.util.Locale.US, "%.1fM", chinaMatches / 1_000_000.0),
                        metaTop = "Lee Sin (55.6% WR)",
                        borderColor = HextechGold,
                        apiSource = "Tencent Super-Server API"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Server 2: Global
                    ServerStatCard(
                        flag = "🌍",
                        serverName = tr("Servidor Global (Meta Live)"),
                        matchesText = String.format(java.util.Locale.US, "%.1fM", globalMatches / 1_000_000.0),
                        metaTop = "Ahri (53.8% WR)",
                        borderColor = HextechCyan,
                        apiSource = "Global Cloud Sync"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Server 3: Norteamérica NA
                    ServerStatCard(
                        flag = "🇺🇸",
                        serverName = tr("Servidor Norteamérica (NA)"),
                        matchesText = String.format(java.util.Locale.US, "%.1fM", naMatches / 1_000_000.0),
                        metaTop = "Lux (53.2% WR)",
                        borderColor = Color(0xFF4A90E2),
                        apiSource = "Riot Americas Cache"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(tr("Entendido"), color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ServerStatCard(
    flag: String,
    serverName: String,
    matchesText: String,
    metaTop: String,
    borderColor: Color,
    apiSource: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.2.dp, borderColor.copy(alpha = 0.7f), RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = HextechSurfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = flag, fontSize = 24.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = serverName,
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = tr("Meta #1: ") + metaTop,
                        color = HextechCyan,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = apiSource,
                        color = TextSecondary,
                        fontSize = 10.sp
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = matchesText,
                    color = HextechGold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = tr("analizadas"),
                    color = TextSecondary,
                    fontSize = 10.sp
                )
            }
        }
    }
}
