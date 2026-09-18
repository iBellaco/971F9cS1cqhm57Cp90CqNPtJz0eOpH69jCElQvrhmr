package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppNotice
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AdAvailabilityCalendarPanel(
    allNotices: List<AppNotice>,
    modifier: Modifier = Modifier
) {
    val now = System.currentTimeMillis()

    // Filtrar anuncios publicitarios activos y vigentes que ocupan espacio en el calendario
    val activePublicityAds = remember(allNotices, now) {
        allNotices.filter { notice ->
            val isPubTag = notice.tag.equals("Publicidad", ignoreCase = true) ||
                           notice.tag.equals("Ads", ignoreCase = true) ||
                           notice.tag.equals("PUBLICIDAD", ignoreCase = true)
            val isLive = notice.isApproved && notice.isEnabled && (notice.expiresAtMillis == 0L || notice.expiresAtMillis > now)
            isPubTag && isLive
        }
    }

    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header del Calendario de Disponibilidad
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.EventAvailable, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Calendario de Disponibilidad Publicitaria",
                        color = HextechGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = (if (activePublicityAds.isNotEmpty()) Color(0xFFF59E0B) else Color(0xFF10B981)).copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, if (activePublicityAds.isNotEmpty()) Color(0xFFF59E0B) else Color(0xFF10B981))
                ) {
                    Text(
                        text = if (activePublicityAds.isNotEmpty()) "${activePublicityAds.size} Espacios Ocupados" else "Espacios Libres",
                        color = if (activePublicityAds.isNotEmpty()) Color(0xFFF59E0B) else Color(0xFF10B981),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Text(
                text = "Consulte las fechas, horas, días y semanas ocupadas antes de programar su anuncio publicitario para asegurar disponibilidad exacta.",
                color = TextSecondary,
                fontSize = 11.sp
            )

            // Resumen de ranuras ocupadas vs libres
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Tarjeta de Ocupados
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechDarkBg)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Anuncios Activos", color = TextSecondary, fontSize = 10.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${activePublicityAds.size}",
                            color = HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                // Tarjeta de Estado del Sistema
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechDarkBg)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Estado de Red", color = TextSecondary, fontSize = 10.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (activePublicityAds.size >= 5) "Alta Demanda" else "Disponible",
                            color = if (activePublicityAds.size >= 5) Color(0xFFF59E0B) else Color(0xFF10B981),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // Lista detallada de franjas horarias / días / semanas / meses ocupados
            if (activePublicityAds.isNotEmpty()) {
                Text(
                    text = "Franjas Horarias y Fechas Ocupadas:",
                    color = HextechCyan,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(HextechDarkBg, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    activePublicityAds.forEach { ad ->
                        val startStr = if (ad.approvedAtMillis > 0L) dateFormat.format(Date(ad.approvedAtMillis)) else "Inmediato"
                        val endStr = if (ad.expiresAtMillis > 0L) dateFormat.format(Date(ad.expiresAtMillis)) else "Indefinido"
                        val unitLabel = when (ad.durationUnit.lowercase(Locale.ROOT)) {
                            "hour", "hours", "hora", "horas" -> "Horas"
                            "day", "days", "dia", "dias", "día", "días" -> "Días"
                            "week", "weeks", "semana", "semanas" -> "Semanas"
                            "month", "months", "mes", "meses" -> "Meses"
                            else -> ad.durationUnit
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(HextechSurfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = ad.title,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    maxLines = 1
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Prog: ${ad.durationValue} $unitLabel",
                                    color = HextechGold,
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = "Desde: $startStr\nHasta: $endStr",
                                    color = TextSecondary,
                                    fontSize = 9.sp
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = DangerRed.copy(alpha = 0.2f)
                            ) {
                                Text(
                                    text = "OCUPADO",
                                    color = DangerRed,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(HextechDarkBg, RoundedCornerShape(8.dp))
                        .padding(14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF10B981), modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Todas las fechas, horas, días y semanas están disponibles para programar su anuncio.",
                            color = Color(0xFF10B981),
                            fontSize = 11.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
