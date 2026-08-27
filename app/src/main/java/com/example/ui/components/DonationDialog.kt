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
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.request.ImageRequest
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

                    // Option: Pix (Brasil)
                    DonationPixCard(
                        pixCode = "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b55520400005303986540526.455802BR5917BRLA DIGITAL LTDA6009Sao Paulo622905258e3dc64ffc0c48fab562857a5630478FA",
                        onCopy = {
                            copyToClipboard(context, "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b55520400005303986540526.455802BR5917BRLA DIGITAL LTDA6009Sao Paulo622905258e3dc64ffc0c48fab562857a5630478FA", "Código Pix Copia e Cola")
                        }
                    )

                    // Option 1: PayPal
                    DonationMethodCard(
                        icon = Icons.Default.Payment,
                        title = "PayPal",
                        subtitle = tr("Donación internacional rápida"),
                        accentColor = Color(0xFF0079C1),
                        actionText = tr("Abrir PayPal"),
                        onAction = {
                            openUrl(context, "https://www.paypal.com/donate/?business=barbachavezdiego@gmail.com")
                        }
                    )

                    // Option 3: Crypto (USDT - Tron TRC20)
                    DonationCryptoCard(
                        title = "USDT (TRC-20)",
                        network = "TRC20 (Tron)",
                        address = "TPwZJSMizLPVAx67Je7YB2eoK4VUBEh7QH",
                        onCopy = {
                            copyToClipboard(context, "TPwZJSMizLPVAx67Je7YB2eoK4VUBEh7QH", "USDT")
                        }
                    )

                    // Option 4: Bitcoin (BTC)
                    DonationCryptoCard(
                        title = "Bitcoin (BTC)",
                        network = "Red Bitcoin Nativa (BTC)",
                        address = "13fox2wPLWPSmC1AvbYHazXnjU4tSrRETu",
                        onCopy = {
                            copyToClipboard(context, "13fox2wPLWPSmC1AvbYHazXnjU4tSrRETu", "BTC")
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

@Composable
private fun DonationPixCard(
    pixCode: String,
    onCopy: () -> Unit
) {
    val context = LocalContext.current
    var showQRModal by remember { mutableStateOf(false) }
    // Generar URL del código QR codificado para el estándar Pix
    val qrCodeUrl = remember(pixCode) {
        val encoded = Uri.encode(pixCode)
        "https://api.qrserver.com/v1/create-qr-code/?size=400x400&data=$encoded&bgcolor=ffffff&color=000000&margin=2"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, Color(0xFF32BCAD).copy(alpha = 0.6f))
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF32BCAD).copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.QrCode,
                            contentDescription = null,
                            tint = Color(0xFF32BCAD),
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Pix (Brasil)",
                                color = TextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFF32BCAD).copy(alpha = 0.2f))
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "INSTANTÁNEO",
                                    color = Color(0xFF32BCAD),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                        Text(
                            text = tr("QR Code e Pix Copia e Cola"),
                            color = TextMuted,
                            fontSize = 11.sp
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Button(
                        onClick = { showQRModal = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF32BCAD)),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(Icons.Default.QrCode2, contentDescription = "Ver QR", modifier = Modifier.size(15.dp), tint = Color.Black)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tr("Ver QR"), color = Color.Black, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = onCopy,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechGold),
                        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.7f)),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copiar Pix", modifier = Modifier.size(14.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Vista previa del QR interactiva y código copiable
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(HextechDarkBg)
                    .clickable { showQRModal = true }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Miniatura QR
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.White)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(qrCodeUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "QR Pix",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = tr("Toca para ampliar el QR o copia la clave:"),
                        color = HextechCyan,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = pixCode,
                        color = TextSecondary,
                        fontSize = 10.sp,
                        maxLines = 2
                    )
                }
            }
        }
    }

    // Modal para visualizar el código QR en grande
    if (showQRModal) {
        Dialog(onDismissRequest = { showQRModal = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.5.dp, Color(0xFF32BCAD), RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.QrCode2, contentDescription = null, tint = Color(0xFF32BCAD))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Pix QR Code",
                                color = TextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        IconButton(onClick = { showQRModal = false }, modifier = Modifier.size(28.dp)) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // QR Card Blanco de alta visibilidad
                    Box(
                        modifier = Modifier
                            .size(240.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(2.dp, Color(0xFF32BCAD), RoundedCornerShape(12.dp))
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(qrCodeUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Pix QR Ampliado",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Beneficiario: BRLA DIGITAL LTDA",
                        color = HextechGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Sao Paulo • R$ 26.45",
                        color = TextSecondary,
                        fontSize = 11.5.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            onCopy()
                            showQRModal = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF32BCAD)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(tr("Copiar Código Pix"), color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
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
