package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.remote.model.FeedbackReport
import com.example.data.supabase.FeedbackRepository
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGreen
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.util.tr
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminFeedbackBottomSheet(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var reports by remember { mutableStateOf<List<FeedbackReport>>(emptyList()) }
    var completedIds by remember { mutableStateOf<Set<String>>(emptySet()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("ALL") }
    var reportToDelete by remember { mutableStateOf<FeedbackReport?>(null) }
    var isDeleting by remember { mutableStateOf(false) }

    fun loadReports() {
        isLoading = true
        errorMessage = null
        completedIds = FeedbackRepository.getCompletedFeedbackIds(context)
        scope.launch {
            val result = FeedbackRepository.getAllFeedbacks()
            isLoading = false
            if (result.isSuccess) {
                reports = result.getOrDefault(emptyList())
            } else {
                errorMessage = result.exceptionOrNull()?.message ?: "Error al cargar reportes"
            }
        }
    }

    LaunchedEffect(Unit) {
        loadReports()
    }

    val pendingCount = remember(reports, completedIds) {
        reports.count { rep ->
            !FeedbackRepository.isReportCompleted(rep, completedIds)
        }
    }
    val completedCount = remember(reports, completedIds) {
        reports.count { rep ->
            FeedbackRepository.isReportCompleted(rep, completedIds)
        }
    }
    val bugCount = remember(reports) { reports.count { it.type.equals("BUG", ignoreCase = true) } }
    val ideaCount = remember(reports) { reports.count { it.type.equals("SUGGESTION", ignoreCase = true) } }

    // Filtrado de reportes
    val filteredReports = remember(reports, searchQuery, selectedFilter, completedIds) {
        reports.filter { item ->
            val isItemCompleted = FeedbackRepository.isReportCompleted(item, completedIds)

            val matchFilter = when (selectedFilter) {
                "PENDING" -> !isItemCompleted
                "COMPLETED" -> isItemCompleted
                "BUG" -> item.type.equals("BUG", ignoreCase = true)
                "SUGGESTION" -> item.type.equals("SUGGESTION", ignoreCase = true)
                else -> true
            }
            val matchSearch = if (searchQuery.isBlank()) true else {
                item.title.contains(searchQuery, ignoreCase = true) ||
                item.description.contains(searchQuery, ignoreCase = true) ||
                item.deviceInfo.contains(searchQuery, ignoreCase = true) ||
                item.appVersion.contains(searchQuery, ignoreCase = true)
            }
            matchFilter && matchSearch
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechDarkBg,
        dragHandle = null,
        modifier = Modifier
            .fillMaxHeight(0.92f)
            .testTag("admin_feedback_panel")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0F1726),
                            HextechDarkBg
                        )
                    )
                )
        ) {
            // Header del Panel de Administrador
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HextechSurface.copy(alpha = 0.85f))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(HextechGold.copy(alpha = 0.2f))
                            .border(1.dp, HextechGold, RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AdminPanelSettings,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Column {
                        Text(
                            text = tr("Panel de Administrador"),
                            color = HextechGold,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = tr("Gestión de Mensajes & Reportes"),
                            color = TextMuted,
                            fontSize = 11.5.sp
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { loadReports() },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Recargar",
                            tint = HextechCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = TextMuted,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Métricas Rápidas y Filtros (Píldoras animadas)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                MetricPill(
                    label = tr("Total"),
                    count = reports.size,
                    color = HextechGold,
                    modifier = Modifier.weight(1f),
                    isSelected = selectedFilter == "ALL",
                    onClick = { selectedFilter = "ALL" }
                )
                MetricPill(
                    label = tr("Por resolver"),
                    count = pendingCount,
                    color = Color(0xFFFFB300),
                    icon = Icons.Default.HourglassEmpty,
                    modifier = Modifier.weight(1.2f),
                    isSelected = selectedFilter == "PENDING",
                    onClick = { selectedFilter = "PENDING" }
                )
                MetricPill(
                    label = tr("Completados"),
                    count = completedCount,
                    color = HextechGreen,
                    icon = Icons.Default.CheckCircle,
                    modifier = Modifier.weight(1.2f),
                    isSelected = selectedFilter == "COMPLETED",
                    onClick = { selectedFilter = "COMPLETED" }
                )
                MetricPill(
                    label = tr("Bugs"),
                    count = bugCount,
                    color = DangerRed,
                    icon = Icons.Default.BugReport,
                    modifier = Modifier.weight(1f),
                    isSelected = selectedFilter == "BUG",
                    onClick = { selectedFilter = "BUG" }
                )
                MetricPill(
                    label = tr("Ideas"),
                    count = ideaCount,
                    color = Color(0xFFFFB74D),
                    icon = Icons.Default.Lightbulb,
                    modifier = Modifier.weight(1f),
                    isSelected = selectedFilter == "SUGGESTION",
                    onClick = { selectedFilter = "SUGGESTION" }
                )
            }

            // Barra de Búsqueda y Purga
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text(tr("Buscar por título, contenido o modelo..."), fontSize = 12.sp, color = TextMuted) },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = HextechGold, modifier = Modifier.size(18.dp))
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }, modifier = Modifier.size(24.dp)) {
                                Icon(Icons.Default.Close, contentDescription = "Limpiar", tint = TextMuted, modifier = Modifier.size(16.dp))
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedContainerColor = HextechSurface,
                        unfocusedContainerColor = HextechSurface
                    )
                )

                // Botón de purga rápida
                OutlinedButton(
                    onClick = {
                        scope.launch {
                            val res = FeedbackRepository.purgeOldReports(days = 7)
                            if (res.isSuccess) {
                                Toast.makeText(context, "✅ Purga de >7 días completada", Toast.LENGTH_SHORT).show()
                                loadReports()
                            } else {
                                Toast.makeText(context, "Error: ${res.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = HextechSurface
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                    modifier = Modifier.height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CleaningServices,
                        contentDescription = "Limpiar antiguos",
                        tint = HextechCyan,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Lista de Reportes
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 6.dp)
            ) {
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = HextechGold, modifier = Modifier.size(36.dp), strokeWidth = 3.dp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = tr("Cargando reportes..."),
                            color = TextSecondary,
                            fontSize = 13.sp
                        )
                    }
                } else if (errorMessage != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.BugReport, contentDescription = null, tint = DangerRed, modifier = Modifier.size(40.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = tr("Error al cargar:"), color = DangerRed, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = errorMessage!!, color = TextMuted, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(14.dp))
                        Button(
                            onClick = { loadReports() },
                            colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(tr("Reintentar"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                        }
                    }
                } else if (filteredReports.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.Inbox, contentDescription = null, tint = TextMuted, modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = if (searchQuery.isNotBlank() || selectedFilter != "ALL") 
                                tr("No hay reportes en esta categoría") 
                            else 
                                tr("No hay reportes registrados"),
                            color = TextSecondary,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = tr("Los nuevos mensajes enviados por los usuarios aparecerán aquí."),
                            color = TextMuted,
                            fontSize = 12.sp
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(filteredReports, key = { it.id ?: it.hashCode().toString() }) { report ->
                            val isCompleted = FeedbackRepository.isReportCompleted(report, completedIds)

                            ReportItemCard(
                                report = report,
                                isCompleted = isCompleted,
                                onToggleCompleted = {
                                    val newStatus = !isCompleted
                                    FeedbackRepository.setFeedbackCompleted(context, report, newStatus)
                                    completedIds = FeedbackRepository.getCompletedFeedbackIds(context)
                                    
                                    // Sincronizar en Supabase si tiene ID en la nube
                                    val reportId = report.id
                                    if (!reportId.isNullOrBlank()) {
                                        scope.launch {
                                            FeedbackRepository.updateFeedbackStatusInCloud(reportId, newStatus)
                                        }
                                    }

                                    val msg = if (newStatus) "✅ Marcado como Completado" else "⏳ Marcado como Por resolver"
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                },
                                onDelete = { reportToDelete = report },
                                onCopy = {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    val textToCopy = """
                                        [${report.type}] ${report.title}
                                        Estado: ${if (isCompleted) "COMPLETADO" else "POR RESOLVER"}
                                        Descripción: ${report.description}
                                        Versión: ${report.appVersion}
                                        Dispositivo: ${report.deviceInfo}
                                        Fecha: ${report.createdAt ?: "N/A"}
                                    """.trimIndent()
                                    clipboard.setPrimaryClip(ClipData.newPlainText("Feedback Report", textToCopy))
                                    Toast.makeText(context, "📋 Reporte copiado al portapapeles", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    // Diálogo de Confirmación de Eliminación
    if (reportToDelete != null) {
        val rep = reportToDelete!!
        AlertDialog(
            onDismissRequest = { if (!isDeleting) reportToDelete = null },
            containerColor = HextechDarkBg,
            shape = RoundedCornerShape(14.dp),
            title = {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = DangerRed, modifier = Modifier.size(22.dp))
                    Text(tr("¿Eliminar este reporte?"), color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column {
                    Text(
                        text = tr("Esta acción borrará permanentemente el reporte:"),
                        color = TextSecondary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "\"${rep.title}\"",
                        color = HextechGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val id = rep.id
                        if (id != null) {
                            isDeleting = true
                            scope.launch {
                                val res = FeedbackRepository.deleteFeedback(id)
                                isDeleting = false
                                if (res.isSuccess) {
                                    Toast.makeText(context, "🗑️ Reporte eliminado", Toast.LENGTH_SHORT).show()
                                    reportToDelete = null
                                    loadReports()
                                } else {
                                    Toast.makeText(context, "Error: ${res.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(context, "Error: ID no disponible", Toast.LENGTH_SHORT).show()
                            reportToDelete = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isDeleting
                ) {
                    if (isDeleting) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                    } else {
                        Text(tr("Eliminar"), color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { reportToDelete = null }, enabled = !isDeleting) {
                    Text(tr("Cancelar"), color = TextMuted)
                }
            }
        )
    }
}

@Composable
private fun MetricPill(
    label: String,
    count: Int,
    color: Color,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                if (isSelected) color.copy(alpha = 0.25f)
                else HextechSurface
            )
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) color else HextechCardBorder,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 7.dp, horizontal = 3.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                if (icon != null) {
                    Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(12.dp))
                }
                Text(
                    text = count.toString(),
                    color = color,
                    fontWeight = FontWeight.Black,
                    fontSize = 13.5.sp
                )
            }
            Text(
                text = label,
                color = if (isSelected) TextPrimary else TextMuted,
                fontSize = 9.5.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun ReportItemCard(
    report: FeedbackReport,
    isCompleted: Boolean,
    onToggleCompleted: () -> Unit,
    onDelete: () -> Unit,
    onCopy: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val (typeColor, typeIcon, typeLabel) = when {
        report.type.equals("BUG", ignoreCase = true) -> Triple(DangerRed, Icons.Default.BugReport, "BUG")
        else -> Triple(Color(0xFFFFB74D), Icons.Default.Lightbulb, "SUGERENCIA")
    }

    val cardBorderColor by animateColorAsState(
        targetValue = if (isCompleted) HextechGreen.copy(alpha = 0.6f) else typeColor.copy(alpha = 0.35f),
        label = "border_color"
    )

    val formattedDate = remember(report.createdAt) {
        formatReportDate(report.createdAt)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, cardBorderColor, RoundedCornerShape(12.dp))
            .animateContentSize(animationSpec = tween(200)),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) HextechSurface.copy(alpha = 0.75f) else HextechSurface.copy(alpha = 0.95f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Fila Superior: Badges (Tipo + Estado) + Fecha + Acciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Badge de Tipo (BUG / SUGERENCIA)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(typeColor.copy(alpha = 0.18f))
                            .border(1.dp, typeColor.copy(alpha = 0.75f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(typeIcon, contentDescription = null, tint = typeColor, modifier = Modifier.size(11.dp))
                            Text(text = typeLabel, color = typeColor, fontSize = 9.5.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Badge de Estado Interactivo (Completado / Por resolver)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (isCompleted) HextechGreen.copy(alpha = 0.2f)
                                else Color(0xFFFFB300).copy(alpha = 0.18f)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isCompleted) HextechGreen else Color(0xFFFFB300).copy(alpha = 0.8f),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable(onClick = onToggleCompleted)
                            .padding(horizontal = 7.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(
                                imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.HourglassEmpty,
                                contentDescription = null,
                                tint = if (isCompleted) HextechGreen else Color(0xFFFFB300),
                                modifier = Modifier.size(11.dp)
                            )
                            Text(
                                text = if (isCompleted) tr("Completado") else tr("Por resolver"),
                                color = if (isCompleted) HextechGreen else Color(0xFFFFB300),
                                fontSize = 9.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Text(
                        text = formattedDate,
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onCopy,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copiar", tint = TextMuted, modifier = Modifier.size(15.dp))
                    }
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = DangerRed.copy(alpha = 0.8f), modifier = Modifier.size(15.dp))
                    }
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = if (expanded) "Contraer" else "Expandir",
                            tint = HextechGold,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Título
            Text(
                text = report.title,
                color = if (isCompleted) TextPrimary.copy(alpha = 0.85f) else TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { expanded = !expanded }
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Descripción (acortada si no está expandido)
            Text(
                text = report.description,
                color = if (isCompleted) TextSecondary.copy(alpha = 0.8f) else TextSecondary,
                fontSize = 12.5.sp,
                lineHeight = 17.sp,
                maxLines = if (expanded) Int.MAX_VALUE else 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable { expanded = !expanded }
            )

            // Botón rápido de acción de estado al final de la tarjeta
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón interactivo para cambiar estado con un toque
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            if (isCompleted) HextechSurfaceVariant.copy(alpha = 0.8f)
                            else HextechGreen.copy(alpha = 0.15f)
                        )
                        .border(
                            width = 0.8.dp,
                            color = if (isCompleted) TextMuted else HextechGreen,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable(onClick = onToggleCompleted)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = if (isCompleted) Icons.Default.Replay else Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = if (isCompleted) TextMuted else HextechGreen,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = if (isCompleted) tr("Reabrir (Marcar por resolver)") else tr("Marcar como Completado"),
                            color = if (isCompleted) TextMuted else HextechGreen,
                            fontSize = 10.5.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                if (!expanded) {
                    Text(
                        text = tr("Ver detalles ▾"),
                        color = HextechGold.copy(alpha = 0.7f),
                        fontSize = 10.5.sp,
                        modifier = Modifier.clickable { expanded = true }
                    )
                }
            }

            // Detalles Expandibles (Diagnóstico de teléfono y versión)
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(tween(150)),
                exit = fadeOut(tween(150))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechDarkBg.copy(alpha = 0.85f))
                        .border(0.5.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Default.Smartphone, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(14.dp))
                        Text(
                            text = "${tr("Dispositivo:")} ${report.deviceInfo}",
                            color = HextechCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Default.Tune, contentDescription = null, tint = HextechGold, modifier = Modifier.size(14.dp))
                        Text(
                            text = "${tr("Versión App:")} ${report.appVersion}",
                            color = HextechGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (report.id != null) {
                        Text(
                            text = "UUID: ${report.id}",
                            color = TextMuted,
                            fontSize = 9.5.sp
                        )
                    }
                }
            }
        }
    }
}

private fun formatReportDate(dateString: String?): String {
    if (dateString.isNullOrBlank()) return "Reciente"
    return try {
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val cleanDate = dateString.substringBefore(".").substringBefore("+").substringBefore("Z")
        val date = isoFormat.parse(cleanDate)
        if (date != null) {
            val localFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            localFormat.format(date)
        } else {
            dateString.take(16).replace("T", " ")
        }
    } catch (e: Exception) {
        dateString.take(16).replace("T", " ")
    }
}
