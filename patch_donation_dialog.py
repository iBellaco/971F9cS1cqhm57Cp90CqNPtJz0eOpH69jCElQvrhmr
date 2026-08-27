import re

with open('app/src/main/java/com/example/ui/components/DonationDialog.kt', 'r') as f:
    content = f.read()

combined_card_def = """@Composable
private fun DonationPixCombinedCard() {
    val context = androidx.compose.ui.platform.LocalContext.current
    var selectedOption by androidx.compose.runtime.remember { androidx.compose.runtime.mutableIntStateOf(1) }
    var showQRModal by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }

    val pixCode1 = "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b5552040000530398654045.275802BR5917BRLA DIGITAL LTDA6009Sao Paulo62290525c898e88196a346fa968d9eada6304654C"
    val pixCode2 = "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b55520400005303986540526.455802BR5917BRLA DIGITAL LTDA6009Sao Paulo622905258e3dc64ffc0c48fab562857a5630478FA"
    
    val currentPixCode = if (selectedOption == 1) pixCode1 else pixCode2
    val currentAmountText = if (selectedOption == 1) "R$ 5.27" else "R$ 26.45"
    
    val qrCodeUrl = androidx.compose.runtime.remember(currentPixCode) {
        val encoded = android.net.Uri.encode(currentPixCode)
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
            
            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(12.dp))
            
            // Selector de opciones
            androidx.compose.foundation.layout.Row(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
            ) {
                val isOption1 = selectedOption == 1
                androidx.compose.material3.OutlinedButton(
                    onClick = { selectedOption = 1 },
                    modifier = androidx.compose.ui.Modifier.weight(1f),
                    colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isOption1) androidx.compose.ui.graphics.Color(0xFF32BCAD).copy(alpha = 0.2f) else androidx.compose.ui.graphics.Color.Transparent,
                        contentColor = if (isOption1) androidx.compose.ui.graphics.Color(0xFF32BCAD) else TextSecondary
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isOption1) androidx.compose.ui.graphics.Color(0xFF32BCAD) else TextSecondary.copy(alpha = 0.5f))
                ) {
                    androidx.compose.material3.Text("Opción 1", fontSize = 12.sp, maxLines = 1)
                }
                
                val isOption2 = selectedOption == 2
                androidx.compose.material3.OutlinedButton(
                    onClick = { selectedOption = 2 },
                    modifier = androidx.compose.ui.Modifier.weight(1f),
                    colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isOption2) androidx.compose.ui.graphics.Color(0xFF32BCAD).copy(alpha = 0.2f) else androidx.compose.ui.graphics.Color.Transparent,
                        contentColor = if (isOption2) androidx.compose.ui.graphics.Color(0xFF32BCAD) else TextSecondary
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isOption2) androidx.compose.ui.graphics.Color(0xFF32BCAD) else TextSecondary.copy(alpha = 0.5f))
                ) {
                    androidx.compose.material3.Text("Opción 2", fontSize = 12.sp, maxLines = 1)
                }
            }

            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(12.dp))
            
            androidx.compose.material3.Text(
                text = "Sao Paulo • $currentAmountText",
                color = HextechGold,
                fontSize = 13.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
            )
            
            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
            androidx.compose.material3.Text(
                text = tr("QR Code e Pix Copia e Cola"),
                color = TextMuted,
                fontSize = 12.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

            // QR code centrado
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
                onClick = { copyToClipboard(context, currentPixCode, "Código Pix Copia e Cola") },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = HextechBlue),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(0.8f)
            ) {
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.ContentCopy,
                    contentDescription = "Copy",
                    modifier = androidx.compose.ui.Modifier.size(16.dp)
                )
                androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                androidx.compose.material3.Text("Copiar Código Pix", fontSize = 13.sp)
            }
        }
    }
    
    if (showQRModal) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showQRModal = false },
            containerColor = DarkSurface,
            title = {
                androidx.compose.material3.Text(
                    text = "Escanea el Código QR", 
                    color = TextPrimary, 
                    fontSize = 16.sp, 
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            },
            text = {
                androidx.compose.foundation.layout.Column(
                    modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    androidx.compose.foundation.layout.Box(
                        modifier = androidx.compose.ui.Modifier
                            .size(240.dp)
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                            .background(androidx.compose.ui.graphics.Color.White)
                            .padding(12.dp)
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
                    androidx.compose.material3.Text(
                        text = "Valor: $currentAmountText",
                        color = HextechGold,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
                    androidx.compose.material3.Text(
                        text = "Usa la opción 'Pix Copia e Cola' o escanea el QR en tu app de banco.",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            },
            confirmButton = {
                androidx.compose.material3.TextButton(onClick = { showQRModal = false }) {
                    androidx.compose.material3.Text("Cerrar", color = HextechGold)
                }
            }
        )
    }
}
"""

# Replace the two Option cards with just the combined card in the UI
old_options = """                    // Option 1: Pix (Brasil) 1
                    DonationPixCard(
                        title = "Pix (Brasil) Opción 1",
                        amountText = "Sao Paulo • R$ 5.27",
                        pixCode = "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b5552040000530398654045.275802BR5917BRLA DIGITAL LTDA6009Sao Paulo62290525c898e88196a346fa968d9eada6304654C",
                        onCopy = {
                            copyToClipboard(context, "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b5552040000530398654045.275802BR5917BRLA DIGITAL LTDA6009Sao Paulo62290525c898e88196a346fa968d9eada6304654C", "Código Pix Copia e Cola")
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Option 2: Pix (Brasil) 2
                    DonationPixCard(
                        title = "Pix (Brasil) Opción 2",
                        amountText = "Sao Paulo • R$ 26.45",
                        pixCode = "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b55520400005303986540526.455802BR5917BRLA DIGITAL LTDA6009Sao Paulo622905258e3dc64ffc0c48fab562857a5630478FA",
                        onCopy = {
                            copyToClipboard(context, "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b55520400005303986540526.455802BR5917BRLA DIGITAL LTDA6009Sao Paulo622905258e3dc64ffc0c48fab562857a5630478FA", "Código Pix Copia e Cola")
                        }
                    )"""

new_options = """                    // Pix (Brasil) - Opciones Combinadas
                    DonationPixCombinedCard()"""

content = content.replace(old_options, new_options)

# Replace the DonationPixCard function with DonationPixCombinedCard
# Find where @Composable\nprivate fun DonationPixCard starts
pattern = r'@Composable\nprivate fun DonationPixCard\(.*?\}\n\n'
# It might end with just }
import re
# We'll just replace `@Composable\nprivate fun DonationPixCard` with our new one, maybe the old one can stay or we can remove it. Let's just append the new one and let the old one be unused, or try to replace the old one.

content = content + "\n\n" + combined_card_def

with open('app/src/main/java/com/example/ui/components/DonationDialog.kt', 'w') as f:
    f.write(content)
