package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechGold
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PurchaseRecord(
    val id: String,
    val userEmail: String,
    val itemTitle: String,
    val amount: String,
    val timestamp: Long,
    val status: String = "Completado"
)

@Composable
fun PurchaseHistoryDialog(
    isAdmin: Boolean,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(if (isAdmin) 0.85f else 0.55f),
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF0F172A),
            border = BorderStroke(1.5.dp, if (isAdmin) HextechGold else DangerRed)
        ) {
            if (!isAdmin) {
                // Vista restringida para usuarios estándar (Fuera de Servicio)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(DangerRed.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = DangerRed,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Historial de Compras",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = DangerRed.copy(alpha = 0.2f),
                        border = BorderStroke(1.dp, DangerRed.copy(alpha = 0.5f))
                    ) {
                        Text(
                            text = "FUERA DE SERVICIO",
                            color = DangerRed,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "El módulo de historial de compras se encuentra temporalmente fuera de servicio por mantenimiento técnico. Tus compras y saldo de Esencias Azules continúan seguros y sincronizados.",
                        color = Color.LightGray,
                        fontSize = 13.5.sp,
                        lineHeight = 19.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
                        border = BorderStroke(1.dp, Color(0xFF334155)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Entendido", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                // Vista completa de Administrador (Sin restricciones)
                var records by remember {
                    mutableStateOf<List<PurchaseRecord>>(
                        listOf(
                            PurchaseRecord("TX-9841", "admin@wildrift.gg", "Pack 10,000 Esencia Azul", "$9.99 USD", System.currentTimeMillis() - 3600000L),
                            PurchaseRecord("TX-9840", "challenger_wr@gmail.com", "Pase Coach Élite Mensual", "$4.99 USD", System.currentTimeMillis() - 86400000L),
                            PurchaseRecord("TX-9839", "jinx_main@riot.es", "Pack 5,000 Esencia Azul", "$5.49 USD", System.currentTimeMillis() - 172800000L),
                            PurchaseRecord("TX-9838", "support_wr@gg.com", "Pack 2,500 Esencia Azul", "$2.99 USD", System.currentTimeMillis() - 259200000L),
                            PurchaseRecord("TX-9837", "aatrox_king@wr.latam", "Pase Coach Élite Anual", "$39.99 USD", System.currentTimeMillis() - 432000000L)
                        )
                    )
                }
                var isLoading by remember { mutableStateOf(false) }
                val scope = rememberCoroutineScope()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.ReceiptLong, contentDescription = null, tint = HextechGold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    "Historial de Compras",
                                    fontWeight = FontWeight.Bold,
                                    color = HextechGold,
                                    fontSize = 17.sp
                                )
                                Text(
                                    "Acceso de Administrador (Sin Restricciones)",
                                    color = Color.Gray,
                                    fontSize = 11.sp
                                )
                            }
                        }
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.Gray)
                        }
                    }

                    Divider(color = Color(0xFF1E293B), modifier = Modifier.padding(vertical = 8.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(records, key = { it.id }) { rec ->
                            val dateStr = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(rec.timestamp))
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFF1E293B),
                                border = BorderStroke(1.dp, Color(0xFF334155))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                rec.itemTitle,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White,
                                                fontSize = 13.5.sp
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Surface(
                                                shape = RoundedCornerShape(4.dp),
                                                color = Color(0xFF10B981).copy(alpha = 0.2f)
                                            ) {
                                                Text(
                                                    rec.status,
                                                    color = Color(0xFF10B981),
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            "${rec.userEmail} • ID: ${rec.id}",
                                            color = Color.Gray,
                                            fontSize = 11.sp
                                        )
                                        Text(
                                            dateStr,
                                            color = Color.DarkGray,
                                            fontSize = 10.5.sp
                                        )
                                    }

                                    Text(
                                        rec.amount,
                                        fontWeight = FontWeight.Bold,
                                        color = HextechCyan,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
