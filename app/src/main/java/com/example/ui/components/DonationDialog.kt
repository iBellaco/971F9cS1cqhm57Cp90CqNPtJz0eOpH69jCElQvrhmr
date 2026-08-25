package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.*
import com.example.util.tr

@Composable
fun DonationDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.85f)
                .clip(RoundedCornerShape(20.dp))
                .border(1.5.dp, HextechGold, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
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
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(HextechGold.copy(alpha = 0.2f))
                                .border(1.dp, HextechGold, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = tr("Apoyar el Proyecto"),
                                color = TextPrimary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(
                                text = tr("Donaciones y Comunidad"),
                                color = HextechCyan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Motivation Banner
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.4f))
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = HextechCyan,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = tr("Wild Rift Coach es 100% gratuito y sin anuncios molestos. Si la app te ayuda a subir de elo y ganar partidas, tu donación permite mantener los servidores y actualizaciones constantes."),
                                color = TextSecondary,
                                fontSize = 12.sp,
                                lineHeight = 16.5.sp
                            )
                        }
                    }

                    Text(
                        text = tr("Métodos de Donación Disponibles:"),
                        color = HextechGold,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Option 1: PayPal
                    DonationMethodCard(
                        icon = Icons.Default.Payment,
                        title = "PayPal",
                        subtitle = tr("Donación internacional rápida"),
                        accentColor = Color(0xFF0079C1),
                        actionText = tr("Abrir PayPal"),
                        onAction = {
                            openUrl(context, "https://paypal.me/wildriftcoach")
                        }
                    )

                    // Option 2: Ko-fi / Buy Me a Coffee
                    DonationMethodCard(
                        icon = Icons.Default.Coffee,
                        title = "Ko-fi / Café",
                        subtitle = tr("Invita un café al desarrollador"),
                        accentColor = Color(0xFFFF5E5B),
                        actionText = tr("Abrir Ko-fi"),
                        onAction = {
                            openUrl(context, "https://ko-fi.com/wildriftcoach")
                        }
                    )

                    // Option 3: Crypto (USDT - Tron TRC20 / BEP20)
                    DonationCryptoCard(
                        title = "USDT (TRC-20 / BEP-20)",
                        network = "TRC20 / BEP20 / Polygon",
                        address = "TX9vW2Z5XQ7hRk4mNpLjA8sD9yE1uC3bF6",
                        onCopy = {
                            copyToClipboard(context, "TX9vW2Z5XQ7hRk4mNpLjA8sD9yE1uC3bF6", "USDT")
                        }
                    )

                    // Option 4: Bitcoin (BTC)
                    DonationCryptoCard(
                        title = "Bitcoin (BTC)",
                        network = "Red Bitcoin Nativa",
                        address = "bc1qxy2kgdygjrsqtzq2n0yrf2493p83kkfjhx0wlh",
                        onCopy = {
                            copyToClipboard(context, "bc1qxy2kgdygjrsqtzq2n0yrf2493p83kkfjhx0wlh", "BTC")
                        }
                    )

                    val aliasLabel = tr("Alias")
                    // Option 5: Alias / Pix / Mercado Pago
                    DonationCryptoCard(
                        title = tr("Transferencia / Alias / Pix"),
                        network = tr("Latinoamérica / Internacional"),
                        address = "wildrift.coach.donaciones",
                        onCopy = {
                            copyToClipboard(context, "wildrift.coach.donaciones", aliasLabel)
                        }
                    )

                    // Thank you note
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = HextechSurfaceVariant.copy(alpha = 0.5f)),
                        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.3f))
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "✨ " + tr("¡Gracias por apoyar a la comunidad!"),
                                color = HextechGold,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = tr("Cada aporte cuenta para seguir mejorando el asistente táctico, las builds y la precisión de análisis en tiempo real."),
                                color = TextMuted,
                                fontSize = 11.5.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 15.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HextechGold,
                        contentColor = HextechDarkBg
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(tr("Entendido / Cerrar"), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
private fun DonationMethodCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    accentColor: Color,
    actionText: String,
    onAction: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(accentColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(title, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(subtitle, color = TextMuted, fontSize = 11.sp)
                }
            }

            Button(
                onClick = onAction,
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                modifier = Modifier.height(34.dp)
            ) {
                Text(actionText, color = Color.White, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun DonationCryptoCard(
    title: String,
    network: String,
    address: String,
    onCopy: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(title, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 13.5.sp)
                    Text(network, color = HextechCyan, fontSize = 10.5.sp, fontWeight = FontWeight.Medium)
                }
                OutlinedButton(
                    onClick = onCopy,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechGold),
                    border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.7f)),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = "Copiar", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(tr("Copiar"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(HextechDarkBg)
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .clickable { onCopy() }
            ) {
                Text(
                    text = address,
                    color = TextSecondary,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
    }
}

private fun openUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    } catch (e: Exception) {
        copyToClipboard(context, url, "Enlace")
    }
}

private fun copyToClipboard(context: Context, text: String, label: String) {
    try {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copiado al portapapeles: $label", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        // ignore
    }
}
