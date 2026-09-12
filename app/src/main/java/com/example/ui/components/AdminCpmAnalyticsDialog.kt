package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.AppNotice
import com.example.data.AppNoticeAnalyticsManager
import com.example.data.AppNoticeManager
import com.example.data.NoticeMetrics
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminCpmAnalyticsDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val notices by AppNoticeManager.notices.collectAsState()
    val metricsMap by AppNoticeAnalyticsManager.metricsMap.collectAsState()
    val baseCpmRate by AppNoticeAnalyticsManager.baseCpmRate.collectAsState()
    val startDateMs by AppNoticeAnalyticsManager.trackingStartDate.collectAsState()
    val isSyncing by AppNoticeAnalyticsManager.isSyncing.collectAsState()
    val lastSyncTime by AppNoticeAnalyticsManager.lastSyncTime.collectAsState()

    LaunchedEffect(Unit) {
        AppNoticeAnalyticsManager.syncFromCloud(context)
        AppNoticeManager.syncFromCloud(context)
    }

    var showEditCpmDialog by remember { mutableStateOf(false) }
    var showResetConfirmDialog by remember { mutableStateOf(false) }
    var showRecommendationInfoDialog by remember { mutableStateOf(false) }
    var cpmInputText by remember { mutableStateOf(String.format(Locale.US, "%.2f", baseCpmRate)) }
    var selectedTagFilter by remember { mutableStateOf("TODAS") }

    val totalImpressions = remember(metricsMap) { AppNoticeAnalyticsManager.getTotalImpressions() }
    val totalClicks = remember(metricsMap) { AppNoticeAnalyticsManager.getTotalClicks() }
    val totalFullscreen = remember(metricsMap) { AppNoticeAnalyticsManager.getTotalFullscreenViews() }
    val totalRevenue = remember(totalImpressions, baseCpmRate) { AppNoticeAnalyticsManager.getTotalRevenue(baseCpmRate) }
    val overallCtr = remember(totalImpressions, totalClicks) { AppNoticeAnalyticsManager.getOverallCtr() }

    // Recomendación dinámica inteligente recalculada en tiempo real
    val dynamicRec = remember(metricsMap, totalImpressions, totalClicks, totalFullscreen, overallCtr) {
        AppNoticeAnalyticsManager.calculateRecommendedCpm()
    }

    val formattedStartDate = remember(startDateMs) {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        sdf.format(Date(startDateMs))
    }

    val clipboardManager = remember { context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager }

    fun copyReport() {
        val report = AppNoticeAnalyticsManager.generateSummaryReport(notices)
        val clip = ClipData.newPlainText("Reporte CPM Wild Rift Coach", report)
        clipboardManager?.setPrimaryClip(clip)
        Toast.makeText(context, "Reporte CPM copiado al portapapeles", Toast.LENGTH_SHORT).show()
    }

    if (showRecommendationInfoDialog) {
        AlertDialog(
            onDismissRequest = { showRecommendationInfoDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Algoritmo de CPM Recomendado", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "El precio sugerido se calcula y actualiza dinámicamente según tus métricas reales y benchmarks globales de apps de eSports/gaming:",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        color = HextechSurfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.4f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text("• Nivel / Calificación: ${dynamicRec.tierName}", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(3.dp))
                            Text("• CPM Recomendado Actual: $${String.format(Locale.US, "%.2f", dynamicRec.recommendedCpm)} USD", color = Color(0xFF00FF66), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(3.dp))
                            Text("• Rango sugerido de venta: $${String.format(Locale.US, "%.2f", dynamicRec.suggestedPriceRange.first)} - $${String.format(Locale.US, "%.2f", dynamicRec.suggestedPriceRange.second)} USD", color = HextechGold, fontSize = 11.5.sp)
                            Spacer(modifier = Modifier.height(3.dp))
                            Text("• Benchmark Mercado Gaming: $${String.format(Locale.US, "%.2f", dynamicRec.marketBenchmarkMin)} - $${String.format(Locale.US, "%.2f", dynamicRec.marketBenchmarkMax)} USD", color = TextMuted, fontSize = 11.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text("💡 Criterio del Sistema:", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(dynamicRec.reasoning, color = TextPrimary, fontSize = 11.5.sp, lineHeight = 15.sp)
                    
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("📅 Proyección de Precios Fijos (Sponsor):", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Surface(
                        color = HextechSurfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("1 Día", color = TextSecondary, fontSize = 11.sp)
                                Text("$${String.format(Locale.US, "%.2f", dynamicRec.price1Day)} USD", color = Color(0xFF00FF66), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("3 Días", color = TextSecondary, fontSize = 11.sp)
                                Text("$${String.format(Locale.US, "%.2f", dynamicRec.price3Days)} USD", color = Color(0xFF00FF66), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("1 Semana", color = TextSecondary, fontSize = 11.sp)
                                Text("$${String.format(Locale.US, "%.2f", dynamicRec.price1Week)} USD", color = Color(0xFF00FF66), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("1 Mes", color = TextSecondary, fontSize = 11.sp)
                                Text("$${String.format(Locale.US, "%.2f", dynamicRec.price1Month)} USD", color = Color(0xFF00FF66), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("1 Año", color = TextSecondary, fontSize = 11.sp)
                                Text("$${String.format(Locale.US, "%.2f", dynamicRec.price1Year)} USD", color = Color(0xFF00FF66), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        AppNoticeAnalyticsManager.setBaseCpm(context, dynamicRec.recommendedCpm)
                        showRecommendationInfoDialog = false
                        Toast.makeText(context, "Tarifa fijada al precio recomendado: $${dynamicRec.recommendedCpm} USD", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                ) {
                    Text("Aplicar Recomendado ($${String.format(Locale.US, "%.2f", dynamicRec.recommendedCpm)})", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 11.5.sp)
                }
            },
            dismissButton = {
                TextButton(onClick = { showRecommendationInfoDialog = false }) {
                    Text("Cerrar", color = TextSecondary)
                }
            },
            containerColor = HextechDarkBg
        )
    }

    if (showResetConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showResetConfirmDialog = false },
            title = {
                Text("¿Reiniciar Métricas de Anuncios?", color = HextechGold, fontWeight = FontWeight.Bold)
            },
            text = {
                Text(
                    "Esta acción restablecerá a 0 las impresiones y clics únicos diarios de todos los avisos para iniciar un nuevo período de campaña o facturación.",
                    color = TextSecondary,
                    fontSize = 13.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        AppNoticeAnalyticsManager.resetMetrics(context)
                        showResetConfirmDialog = false
                        Toast.makeText(context, "Métricas restablecidas a cero", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935))
                ) {
                    Text("Reiniciar", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirmDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            },
            containerColor = HextechDarkBg
        )
    }

    if (showEditCpmDialog) {
        AlertDialog(
            onDismissRequest = { showEditCpmDialog = false },
            title = {
                Text("Tarifa CPM Base (USD)", color = HextechGold, fontWeight = FontWeight.Bold)
            },
            text = {
                Column {
                    Text(
                        "Configura el costo por cada 1,000 impresiones para calcular los ingresos estimados generados por tus anuncios y patrocinios:",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Dynamic recommendation shortcut badge
                    Surface(
                        color = HextechGold.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                cpmInputText = String.format(Locale.US, "%.2f", dynamicRec.recommendedCpm)
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text("Recomendación Automática de la IA:", color = HextechGold, fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
                                    Text(dynamicRec.tierName, color = HextechCyan, fontSize = 9.5.sp)
                                }
                            }
                            Text(
                                text = "Usar $${String.format(Locale.US, "%.2f", dynamicRec.recommendedCpm)} ↗",
                                color = Color(0xFF00FF66),
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.5.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = cpmInputText,
                        onValueChange = { cpmInputText = it },
                        label = { Text("CPM en USD ($ por 1k vistas)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        leadingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null, tint = HextechGold) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechCyan.copy(alpha = 0.5f),
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(1.00, 2.50, dynamicRec.recommendedCpm, 5.00, 10.00).distinct().take(4).forEach { rate ->
                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        cpmInputText = String.format(Locale.US, "%.2f", rate)
                                    },
                                color = HextechSurfaceVariant,
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.3f))
                            ) {
                                Text(
                                    text = "$$rate",
                                    color = if (rate == dynamicRec.recommendedCpm) Color(0xFF00FF66) else HextechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val parsed = cpmInputText.replace(',', '.').toDoubleOrNull() ?: 2.50
                        AppNoticeAnalyticsManager.setBaseCpm(context, parsed)
                        showEditCpmDialog = false
                        Toast.makeText(context, "Tarifa CPM actualizada: $$parsed USD", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                ) {
                    Text("Guardar", color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditCpmDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            },
            containerColor = HextechDarkBg
        )
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            color = HextechDarkBg
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
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
                                .size(38.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Brush.linearGradient(listOf(Color(0xFF00FF66), Color(0xFF009933)))),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.TrendingUp,
                                contentDescription = null,
                                tint = HextechDarkBg,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Métricas de Monetización & CPM",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF00FF66)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .background(Color(0xFF00FF66), CircleShape)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = if (isSyncing) "Sincronizando con la nube..." else "Sincronizado en tiempo real • Multidispositivo",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (isSyncing) HextechGold else TextSecondary,
                                    fontSize = 10.5.sp
                                )
                            }
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        IconButton(
                            onClick = {
                                AppNoticeAnalyticsManager.syncFromCloud(context) { success ->
                                    if (success) {
                                        Toast.makeText(context, "Métricas sincronizadas en tiempo real", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                AppNoticeManager.syncFromCloud(context)
                            },
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color.White.copy(alpha = 0.05f), CircleShape)
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "Actualizar métricas",
                                tint = if (isSyncing) HextechGold else HextechCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color.White.copy(alpha = 0.05f), CircleShape)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextPrimary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Dynamic Recommended CPM Intelligence Banner
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showRecommendationInfoDialog = true },
                    color = HextechSurfaceVariant,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.2.dp, Brush.horizontalGradient(listOf(HextechGold, Color(0xFF00FF66))))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(HextechGold.copy(alpha = 0.15f))
                                    .border(1.dp, HextechGold, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = HextechGold,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "CPM Recomendado: $${String.format(Locale.US, "%.2f", dynamicRec.recommendedCpm)} USD",
                                        color = Color(0xFF00FF66),
                                        fontSize = 12.5.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        color = Color(0xFF00FF66).copy(alpha = 0.15f),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = "Auto-Actualizado",
                                            color = Color(0xFF00FF66),
                                            fontSize = 8.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "${dynamicRec.tierName} • Rango: $${String.format(Locale.US, "%.2f", dynamicRec.suggestedPriceRange.first)} - $${String.format(Locale.US, "%.2f", dynamicRec.suggestedPriceRange.second)} USD (Toca para ver criterio)",
                                    color = TextSecondary,
                                    fontSize = 10.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // KPI Overview Banner Cards
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.2.dp, HextechCyan.copy(alpha = 0.6f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.MonetizationOn, contentDescription = null, tint = HextechGold, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Rendimiento Publicitario Global", color = HextechGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }

                            Surface(
                                modifier = Modifier.clickable {
                                    cpmInputText = String.format(Locale.US, "%.2f", baseCpmRate)
                                    showEditCpmDialog = true
                                },
                                color = HextechGold.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("CPM Actual: $${String.format(Locale.US, "%.2f", baseCpmRate)}/1k ✎", color = HextechGold, fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // 4 KPI Mini-Cards
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Ingresos Estimados
                            KpiCard(
                                modifier = Modifier.weight(1f),
                                label = "Ingresos Est.",
                                value = "$${String.format(Locale.US, "%.2f", totalRevenue)}",
                                subtext = "USD con CPM actual",
                                accentColor = Color(0xFF00FF66),
                                icon = Icons.Default.AttachMoney
                            )

                            // Impresiones Únicas
                            KpiCard(
                                modifier = Modifier.weight(1f),
                                label = "Impresiones Únicas",
                                value = String.format(Locale.US, "%,d", totalImpressions),
                                subtext = "1 x disp / día",
                                accentColor = HextechCyan,
                                icon = Icons.Default.Visibility
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Clics Únicos y CTR
                            KpiCard(
                                modifier = Modifier.weight(1f),
                                label = "Clics Únicos",
                                value = "${totalClicks} únicos",
                                subtext = "${String.format(Locale.US, "%.2f", overallCtr)}% CTR",
                                accentColor = HextechGold,
                                icon = Icons.Default.TouchApp
                            )

                            // Fullscreen
                            KpiCard(
                                modifier = Modifier.weight(1f),
                                label = "Pantalla Completa",
                                value = "${totalFullscreen}",
                                subtext = "Ampliaciones",
                                accentColor = Color(0xFFCC66FF),
                                icon = Icons.Default.Fullscreen
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Action Toolbar (Copiar reporte / Reset / Info)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { copyReport() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechCyan.copy(alpha = 0.25f)),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.6f))
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copiar Reporte", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { showResetConfirmDialog = true },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935).copy(alpha = 0.2f)),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color(0xFFE53935).copy(alpha = 0.5f))
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null, tint = Color(0xFFFF6666), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Reiniciar", color = Color(0xFFFF6666), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // TAG NAVIGATION BAR (Scrollable Navigation Chips / Tabs)
                val allTags = remember(notices) {
                    val rawTags = notices.map { it.tag.trim().ifBlank { "General" } }.distinct()
                    listOf("TODAS") + rawTags
                }

                ScrollableTabRow(
                    selectedTabIndex = allTags.indexOf(selectedTagFilter).coerceAtLeast(0),
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.Transparent,
                    contentColor = HextechGold,
                    edgePadding = 0.dp,
                    divider = {}
                ) {
                    allTags.forEach { tagItem ->
                        val isSelected = selectedTagFilter == tagItem
                        val countInTag = if (tagItem == "TODAS") notices.size else notices.count { it.tag.trim().ifBlank { "General" }.equals(tagItem, ignoreCase = true) }
                        
                        Tab(
                            selected = isSelected,
                            onClick = { selectedTagFilter = tagItem },
                            text = {
                                Surface(
                                    color = if (isSelected) HextechGold.copy(alpha = 0.2f) else HextechSurfaceVariant,
                                    shape = RoundedCornerShape(16.dp),
                                    border = BorderStroke(
                                        width = if (isSelected) 1.5.dp else 1.dp,
                                        color = if (isSelected) HextechGold else HextechCyan.copy(alpha = 0.3f)
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if (tagItem != "TODAS") {
                                            Box(
                                                modifier = Modifier
                                                    .size(6.dp)
                                                    .clip(CircleShape)
                                                    .background(if (isSelected) HextechGold else HextechCyan)
                                            )
                                            Spacer(modifier = Modifier.width(5.dp))
                                        }
                                        Text(
                                            text = if (tagItem == "TODAS") "TODAS ($countInTag)" else "${tagItem.uppercase()} ($countInTag)",
                                            fontSize = 11.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelected) HextechGold else TextSecondary
                                        )
                                    }
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // List of notices grouped by Tag or filtered by tag navigation
                val filteredNotices = remember(notices, selectedTagFilter) {
                    if (selectedTagFilter == "TODAS") notices
                    else notices.filter { it.tag.trim().ifBlank { "General" }.equals(selectedTagFilter, ignoreCase = true) }
                }

                if (filteredNotices.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No hay anuncios para la etiqueta seleccionada", color = TextSecondary, fontSize = 12.sp)
                    }
                } else {
                    val groupedNotices = remember(filteredNotices) {
                        filteredNotices.groupBy { it.tag.trim().ifBlank { "General" } }
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        groupedNotices.forEach { (tag, noticesInTag) ->
                            item(key = "header_$tag") {
                                val tagTotalImps = noticesInTag.sumOf { (metricsMap[it.id]?.impressions ?: 0L) }
                                val tagTotalClicks = noticesInTag.sumOf { (metricsMap[it.id]?.clicks ?: 0L) }
                                val tagTotalFullscreen = noticesInTag.sumOf { (metricsMap[it.id]?.fullscreenViews ?: 0L) }
                                val tagRevenue = (tagTotalImps.toDouble() / 1000.0) * baseCpmRate
                                val tagCtr = if (tagTotalImps > 0) (tagTotalClicks.toDouble() / tagTotalImps.toDouble()) * 100.0 else 0.0

                                val tagColor = when {
                                    tag.contains("importante", ignoreCase = true) -> HextechGold
                                    tag.contains("publicidad", ignoreCase = true) -> Color(0xFF00FF66)
                                    tag.contains("oferta", ignoreCase = true) -> HextechCyan
                                    tag.contains("mantenimiento", ignoreCase = true) -> Color(0xFFFF3333)
                                    tag.contains("noticia", ignoreCase = true) -> Color(0xFFCC66FF)
                                    tag.contains("streamer", ignoreCase = true) -> Color(0xFFFF66CC)
                                    else -> HextechCyan
                                }

                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = tagColor.copy(alpha = 0.12f),
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, tagColor.copy(alpha = 0.5f))
                                ) {
                                    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Box(
                                                    modifier = Modifier
                                                        .size(8.dp)
                                                        .clip(CircleShape)
                                                        .background(tagColor)
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text(
                                                    text = tag.uppercase(),
                                                    color = tagColor,
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text(
                                                    text = "(${noticesInTag.size} anuncios)",
                                                    color = TextMuted,
                                                    fontSize = 10.sp
                                                )
                                            }

                                            Text(
                                                text = "Total: $${String.format(Locale.US, "%.2f", tagRevenue)} USD",
                                                color = Color(0xFF00FF66),
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(3.dp))
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = "👁️ ${String.format(Locale.US, "%,d", tagTotalImps)} imp. únicas",
                                                color = HextechCyan,
                                                fontSize = 9.5.sp
                                            )
                                            Text(
                                                text = "🖱️ $tagTotalClicks clics únicos (${String.format(Locale.US, "%.1f", tagCtr)}%)",
                                                color = HextechGold,
                                                fontSize = 9.5.sp
                                            )
                                            Text(
                                                text = "📱 $tagTotalFullscreen full",
                                                color = Color(0xFFCC66FF),
                                                fontSize = 9.5.sp
                                            )
                                        }
                                    }
                                }
                            }

                            items(noticesInTag, key = { it.id }) { notice ->
                                val metrics = metricsMap[notice.id] ?: NoticeMetrics(notice.id)
                                NoticeAnalyticsItemCard(
                                    notice = notice,
                                    metrics = metrics,
                                    baseCpm = baseCpmRate
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun KpiCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    subtext: String,
    accentColor: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Surface(
        modifier = modifier,
        color = HextechSurfaceVariant,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.35f))
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(label, color = TextSecondary, fontSize = 10.sp, fontWeight = FontWeight.Medium)
                Icon(icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(14.dp))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, color = accentColor, fontSize = 14.5.sp, fontWeight = FontWeight.Bold)
            Text(subtext, color = TextMuted, fontSize = 9.5.sp)
        }
    }
}

@Composable
private fun NoticeAnalyticsItemCard(
    notice: AppNotice,
    metrics: NoticeMetrics,
    baseCpm: Double
) {
    val revenue = metrics.calculateRevenue(baseCpm)

    fun getTagColor(tag: String): Color {
        val l = tag.lowercase(Locale.ROOT)
        return when {
            l.contains("importante") -> HextechGold
            l.contains("publicidad") -> Color(0xFF00FF66)
            l.contains("oferta") -> HextechCyan
            l.contains("mantenimiento") -> Color(0xFFFF3333)
            l.contains("noticia") -> Color(0xFFCC66FF)
            l.contains("streamer") -> Color(0xFFFF66CC)
            else -> HextechCyan
        }
    }

    val tagColor = getTagColor(notice.tag)

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.95f)),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, if (notice.isEnabled) tagColor.copy(alpha = 0.4f) else Color.Gray.copy(alpha = 0.2f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        color = tagColor.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(1.dp, tagColor.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = notice.tag.uppercase(),
                            color = tagColor,
                            fontSize = 8.5.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Text(
                        text = notice.title,
                        color = TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Surface(
                    color = if (notice.isEnabled) Color(0xFF00FF66).copy(alpha = 0.15f) else Color.Gray.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = if (notice.isEnabled) "Activo" else "Inactivo",
                        color = if (notice.isEnabled) Color(0xFF00FF66) else TextMuted,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Metrics Grid for this notice
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Impresiones
                Column(horizontalAlignment = Alignment.Start) {
                    Text("Imp. Únicas", color = TextMuted, fontSize = 9.sp)
                    Text(
                        String.format(Locale.US, "%,d", metrics.impressions),
                        color = HextechCyan,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Clics / CTR
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Únicos / Totales", color = TextMuted, fontSize = 9.sp)
                    Text(
                        "${metrics.clicks} / ${metrics.totalRawClicks}",
                        color = HextechGold,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Pantalla Completa
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Fullscreen", color = TextMuted, fontSize = 9.sp)
                    Text(
                        "${metrics.fullscreenViews}",
                        color = Color(0xFFCC66FF),
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Ingresos Generados
                Column(horizontalAlignment = Alignment.End) {
                    Text("Generado", color = TextMuted, fontSize = 9.sp)
                    Text(
                        "$${String.format(Locale.US, "%.2f", revenue)}",
                        color = Color(0xFF00FF66),
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
