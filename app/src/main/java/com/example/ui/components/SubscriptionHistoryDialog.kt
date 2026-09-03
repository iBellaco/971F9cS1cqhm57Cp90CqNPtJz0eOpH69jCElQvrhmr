package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
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
import com.example.model.SubscriptionRecord
import com.example.ui.theme.*
import com.example.util.SubscriptionHistoryManager
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SubscriptionHistoryDialog(onDismiss: () -> Unit) {
    var history by remember { mutableStateOf<List<SubscriptionRecord>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        history = SubscriptionHistoryManager.getHistory()
        isLoading = false
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.8f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(HextechSurface)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.History, contentDescription = null, tint = HextechCyan)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Historial de Suscripciones",
                            color = HextechCyan,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextSecondary)
                    }
                }
                Divider(color = HextechCardBorder)

                if (isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = HextechGold)
                    }
                } else if (history.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No hay historial de suscripciones", color = TextSecondary)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(history) { record ->
                            SubscriptionHistoryItem(record)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubscriptionHistoryItem(record: SubscriptionRecord) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val dateString = dateFormat.format(Date(record.timestamp))

    val statusColor = if (record.status.contains("Completado", true)) Color(0xFF00FF7F) else TextSecondary

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(HextechSurfaceVariant)
            .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = record.planName.ifEmpty { "Suscripción Premium" },
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Text(
                text = record.amount,
                color = HextechGold,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        val isRevocation = record.planName.contains("Revocación", ignoreCase = true)
        val isGift = record.planName.contains("Regalo Admin", ignoreCase = true) || record.planName.contains("Asignación Manual", ignoreCase = true)

        if (isRevocation) {
            Text(
                text = "Fecha de cancelación: $dateString",
                color = com.example.ui.theme.DangerRed,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            val endDateStr = if (record.durationMillis == 0L) "Para siempre (Vitalicio)" else dateFormat.format(Date(record.timestamp + record.durationMillis))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Activado: $dateString",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "Vence: $endDateStr",
                        color = HextechGoldLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                if (isGift) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "🎁 Obsequiado por el Administrador",
                        color = HextechCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(androidx.compose.foundation.shape.CircleShape)
                    .background(statusColor)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = record.status,
                color = statusColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private fun formatDurationLocal(millis: Long): String {
    val days = millis / (1000L * 60 * 60 * 24)
    if (days >= 365) return "${days / 365} Año(s)"
    if (days > 0) return "$days Día(s)"
    return "Personalizado"
}
