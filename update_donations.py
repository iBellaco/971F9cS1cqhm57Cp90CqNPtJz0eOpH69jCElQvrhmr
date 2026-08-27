import re

with open('app/src/main/java/com/example/ui/components/DonationDialog.kt', 'r') as f:
    content = f.read()

# We need to replace `private fun DonationPixCard(` to the end of the file except the utility functions.
# Let's find the `DonationPixCard` start and the `private fun openUrl(` start.
start_idx = content.find('private fun DonationPixCard(')
end_idx = content.find('private fun openUrl(')

new_pix_card = """private fun DonationPixCard(
    pixCode: String,
    onCopy: () -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    var showQRModal by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    
    val qrCodeUrl = androidx.compose.runtime.remember(pixCode) {
        val encoded = android.net.Uri.encode(pixCode)
        "https://api.qrserver.com/v1/create-qr-code/?size=400x400&data=$encoded&bgcolor=ffffff&color=000000&margin=2"
    }

    androidx.compose.material3.Card(
        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, androidx.compose.ui.graphics.Color(0xFF32BCAD).copy(alpha = 0.5f))
    ) {
        androidx.compose.foundation.layout.Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            androidx.compose.foundation.layout.Row(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.QrCode,
                    contentDescription = null,
                    tint = androidx.compose.ui.graphics.Color(0xFF32BCAD),
                    modifier = androidx.compose.ui.Modifier.size(20.dp)
                )
                androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                androidx.compose.material3.Text(
                    text = "Pix (Brasil)",
                    color = TextPrimary,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = 15.sp
                )
                androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                androidx.compose.foundation.layout.Box(
                    modifier = androidx.compose.ui.Modifier
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
                        .background(androidx.compose.ui.graphics.Color(0xFF32BCAD).copy(alpha = 0.2f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    androidx.compose.material3.Text(
                        text = "INSTANTÁNEO",
                        color = androidx.compose.ui.graphics.Color(0xFF32BCAD),
                        fontSize = 9.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Black
                    )
                }
            }
            
            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(6.dp))
            androidx.compose.material3.Text(
                text = tr("QR Code e Pix Copia e Cola"),
                color = TextMuted,
                fontSize = 12.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

            // QR code centrado y más grande
            androidx.compose.foundation.layout.Box(
                modifier = androidx.compose.ui.Modifier
                    .size(160.dp)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                    .background(androidx.compose.ui.graphics.Color.White)
                    .clickable { showQRModal = true }
                    .border(2.dp, androidx.compose.ui.graphics.Color(0xFF32BCAD).copy(alpha = 0.3f), androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                    .padding(8.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                coil.compose.AsyncImage(
                    model = coil.request.ImageRequest.Builder(context)
                        .data(qrCodeUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "QR Pix",
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    contentScale = androidx.compose.ui.layout.ContentScale.Fit
                )
            }

            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

            androidx.compose.material3.Button(
                onClick = { showQRModal = true },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color(0xFF32BCAD)),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                modifier = androidx.compose.ui.Modifier.fillMaxWidth()
            ) {
                androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.Default.QrCode2, contentDescription = "Ver QR", modifier = androidx.compose.ui.Modifier.size(18.dp), tint = androidx.compose.ui.graphics.Color.Black)
                androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                androidx.compose.material3.Text(tr("Ver QR Ampliado"), color = androidx.compose.ui.graphics.Color.Black, fontSize = 13.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            }
        }
    }

    if (showQRModal) {
        androidx.compose.ui.window.Dialog(onDismissRequest = { showQRModal = false }) {
            androidx.compose.material3.Card(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth(0.95f)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
                    .border(1.5.dp, androidx.compose.ui.graphics.Color(0xFF32BCAD), androidx.compose.foundation.shape.RoundedCornerShape(16.dp)),
                colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = HextechDarkBg)
            ) {
                androidx.compose.foundation.layout.Column(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    androidx.compose.foundation.layout.Row(
                        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        androidx.compose.foundation.layout.Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                            androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.Default.QrCode2, contentDescription = null, tint = androidx.compose.ui.graphics.Color(0xFF32BCAD))
                            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                            androidx.compose.material3.Text(
                                text = "Pix QR Code",
                                color = TextPrimary,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        androidx.compose.material3.IconButton(onClick = { showQRModal = false }, modifier = androidx.compose.ui.Modifier.size(28.dp)) {
                            androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                        }
                    }
                    
                    androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
                    
                    androidx.compose.foundation.layout.Box(
                        modifier = androidx.compose.ui.Modifier
                            .size(260.dp)
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                            .background(androidx.compose.ui.graphics.Color.White)
                            .border(2.dp, androidx.compose.ui.graphics.Color(0xFF32BCAD), androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                            .padding(12.dp),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        coil.compose.AsyncImage(
                            model = coil.request.ImageRequest.Builder(context)
                                .data(qrCodeUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Pix QR Ampliado",
                            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                            contentScale = androidx.compose.ui.layout.ContentScale.Fit
                        )
                    }
                    
                    androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
                    
                    androidx.compose.material3.Text(
                        text = "Beneficiario: BRLA DIGITAL LTDA",
                        color = HextechGold,
                        fontSize = 13.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    androidx.compose.material3.Text(
                        text = "Sao Paulo • R$ 26.45",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    
                    androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(20.dp))
                    
                    androidx.compose.material3.Button(
                        onClick = {
                            onCopy()
                            showQRModal = false
                        },
                        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color(0xFF32BCAD)),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                    ) {
                        androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.Default.ContentCopy, contentDescription = null, tint = androidx.compose.ui.graphics.Color.Black, modifier = androidx.compose.ui.Modifier.size(16.dp))
                        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                        androidx.compose.material3.Text(tr("Copiar Código Pix"), color = androidx.compose.ui.graphics.Color.Black, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 13.sp)
                    }

                    androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))

                    androidx.compose.material3.OutlinedButton(
                        onClick = { downloadQr(context, qrCodeUrl) },
                        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(contentColor = androidx.compose.ui.graphics.Color(0xFF32BCAD)),
                        border = androidx.compose.foundation.BorderStroke(1.dp, androidx.compose.ui.graphics.Color(0xFF32BCAD).copy(alpha = 0.6f)),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                    ) {
                        androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.Default.Download, contentDescription = null, tint = androidx.compose.ui.graphics.Color(0xFF32BCAD), modifier = androidx.compose.ui.Modifier.size(16.dp))
                        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                        androidx.compose.material3.Text(tr("Descargar QR"), color = androidx.compose.ui.graphics.Color(0xFF32BCAD), fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

"""

new_content = content[:start_idx] + new_pix_card + content[end_idx:]

with open('app/src/main/java/com/example/ui/components/DonationDialog.kt', 'w') as f:
    f.write(new_content)
