package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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

enum class FeedbackCategoryTab(val titleKey: String, val icon: ImageVector) {
    ALL("Todos", Icons.Default.Inbox),
    BUGS("Reportes", Icons.Default.BugReport),
    SUGGESTIONS("Sugerencias", Icons.Default.Lightbulb)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminFeedbackBottomSheet(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var currentCategoryTab by remember { mutableStateOf(FeedbackCategoryTab.ALL) }
    var selectedSubFilter by remember { mutableStateOf("ALL") }
    var searchQuery by remember { mutableStateOf("") }

    var reports by remember { mutableStateOf<List<FeedbackReport>>(emptyList()) }
    val statusMap = remember { mutableStateMapOf<String, String>() }

    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var reportToDelete by remember { mutableStateOf<FeedbackReport?>(null) }
    var showClearAllConfirm by remember { mutableStateOf(false) }
    var isDeleting by remember { mutableStateOf(false) }
    var isPurging by remember { mutableStateOf(false) }
    var previewImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    fun refreshStatusMap(list: List<FeedbackReport>) {
        statusMap.clear()
        for (item in list) {
            val key = item.id ?: "${item.title}_${item.createdAt}"
            val status = FeedbackRepository.getReportStatus(context, item)
            statusMap[key] = status
        }
    }

    fun loadReports() {
        isLoading = true
        errorMessage = null
        scope.launch {
            val result = FeedbackRepository.getAllFeedbacks()
            isLoading = false
            if (result.isSuccess) {
                val list = result.getOrDefault(emptyList())
                reports = list
                refreshStatusMap(list)
            } else {
                errorMessage = result.exceptionOrNull()?.message ?: "Error al cargar reportes"
            }
        }
    }

    LaunchedEffect(Unit) {
        loadReports()
    }

    // Contadores
    val totalCount = reports.size
    val bugList = remember(reports) { reports.filter { it.type.equals("BUG", ignoreCase = true) } }
    val suggestionList = remember(reports) { reports.filter { it.type.equals("SUGGESTION", ignoreCase = true) } }

    val pendingCount = remember(reports, statusMap.toMap()) {
        reports.count {
            val key = it.id ?: "${it.title}_${it.createdAt}"
            (statusMap[key] ?: FeedbackRepository.STATUS_PENDING) == FeedbackRepository.STATUS_PENDING
        }
    }

    val solvedCount = remember(reports, statusMap.toMap()) {
        reports.count {
            val key = it.id ?: "${it.title}_${it.createdAt}"
            val s = statusMap[key]
            s == FeedbackRepository.STATUS_SOLVED || s == FeedbackRepository.STATUS_COMPLETED
        }
    }

    val readCount = remember(reports, statusMap.toMap()) {
        reports.count {
            val key = it.id ?: "${it.title}_${it.createdAt}"
            statusMap[key] == FeedbackRepository.STATUS_READ
        }
    }

    val acceptedCount = remember(reports, statusMap.toMap()) {
        reports.count {
            val key = it.id ?: "${it.title}_${it.createdAt}"
            statusMap[key] == FeedbackRepository.STATUS_ACCEPTED
        }
    }

    val rejectedCount = remember(reports, statusMap.toMap()) {
        reports.count {
            val key = it.id ?: "${it.title}_${it.createdAt}"
            statusMap[key] == FeedbackRepository.STATUS_REJECTED
        }
    }

    // Filtrado de reportes
    val filteredReports = remember(reports, currentCategoryTab, selectedSubFilter, searchQuery, statusMap.toMap()) {
        reports.filter { item ->
            val key = item.id ?: "${item.title}_${item.createdAt}"
            val currentStatus = statusMap[key] ?: FeedbackRepository.STATUS_PENDING
            val isBug = item.type.equals("BUG", ignoreCase = true)
            val isSuggestion = item.type.equals("SUGGESTION", ignoreCase = true)

            // Filtro por pestaña principal
            val matchCategory = when (currentCategoryTab) {
                FeedbackCategoryTab.ALL -> true
                FeedbackCategoryTab.BUGS -> isBug
                FeedbackCategoryTab.SUGGESTIONS -> isSuggestion
            }

            // Filtro por subestado
            val matchSubFilter = when (selectedSubFilter) {
                "ALL" -> true
                "PENDING" -> currentStatus == FeedbackRepository.STATUS_PENDING
                "READ" -> currentStatus == FeedbackRepository.STATUS_READ
                "SOLVED" -> currentStatus == FeedbackRepository.STATUS_SOLVED || currentStatus == FeedbackRepository.STATUS_COMPLETED
                "ACCEPTED" -> currentStatus == FeedbackRepository.STATUS_ACCEPTED
                "REJECTED" -> currentStatus == FeedbackRepository.STATUS_REJECTED
                else -> true
            }

            // Filtro por texto de búsqueda
            val matchSearch = if (searchQuery.isBlank()) true else {
                item.title.contains(searchQuery, ignoreCase = true) ||
                item.description.contains(searchQuery, ignoreCase = true) ||
                item.deviceInfo.contains(searchQuery, ignoreCase = true) ||
                item.appVersion.contains(searchQuery, ignoreCase = true)
            }

            matchCategory && matchSubFilter && matchSearch
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechDarkBg,
        dragHandle = null,
        modifier = Modifier
            .fillMaxHeight(0.94f)
            .testTag("admin_feedback_panel")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0D1424),
                            HextechDarkBg
                        )
                    )
                )
        ) {
            // Header del Panel
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HextechSurface.copy(alpha = 0.9f))
                    .border(0.5.dp, HextechCardBorder.copy(alpha = 0.5f))
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
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(HextechGold.copy(alpha = 0.18f))
                            .border(1.2.dp, HextechGold, RoundedCornerShape(10.dp)),
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
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = tr("Panel de Reportes & Sugerencias"),
                                color = HextechGold,
                                fontSize = 15.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechCyan.copy(alpha = 0.2f))
                                    .border(0.8.dp, HextechCyan.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 6.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "$totalCount",
                                    color = HextechCyan,
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Text(
                            text = tr("Gestión, revisión de bugs y evaluación de ideas"),
                            color = TextMuted,
                            fontSize = 11.5.sp
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    IconButton(
                        onClick = { loadReports() },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = tr("Recargar"),
                            tint = HextechCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = tr("Cerrar"),
                            tint = TextMuted,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Pestañas Principales (Todos / Reportes / Sugerencias)
            TabRow(
                selectedTabIndex = currentCategoryTab.ordinal,
                containerColor = HextechSurface,
                contentColor = HextechGold,
                indicator = { tabPositions ->
                    if (currentCategoryTab.ordinal in tabPositions.indices) {
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[currentCategoryTab.ordinal]),
                            color = HextechGold
                        )
                    }
                },
                divider = {}
            ) {
                FeedbackCategoryTab.entries.forEach { tab ->
                    val isSelected = currentCategoryTab == tab
                    val count = when (tab) {
                        FeedbackCategoryTab.ALL -> totalCount
                        FeedbackCategoryTab.BUGS -> bugList.size
                        FeedbackCategoryTab.SUGGESTIONS -> suggestionList.size
                    }
                    Tab(
                        selected = isSelected,
                        onClick = {
                            currentCategoryTab = tab
                            selectedSubFilter = "ALL"
                        },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp),
                                    tint = if (isSelected) HextechGold else TextMuted
                                )
                                Text(
                                    text = "${tr(tab.titleKey)} ($count)",
                                    color = if (isSelected) HextechGold else TextMuted,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    )
                }
            }

            // Barra de Subfiltros Dinámicos según la categoría
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HextechDarkBg.copy(alpha = 0.95f))
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    StatusFilterChip(
                        label = tr("Todos"),
                        count = when (currentCategoryTab) {
                            FeedbackCategoryTab.ALL -> totalCount
                            FeedbackCategoryTab.BUGS -> bugList.size
                            FeedbackCategoryTab.SUGGESTIONS -> suggestionList.size
                        },
                        isSelected = selectedSubFilter == "ALL",
                        color = HextechGold,
                        onClick = { selectedSubFilter = "ALL" }
                    )
                }

                item {
                    StatusFilterChip(
                        label = tr("⏳ Pendientes"),
                        count = when (currentCategoryTab) {
                            FeedbackCategoryTab.ALL -> pendingCount
                            FeedbackCategoryTab.BUGS -> bugList.count { (statusMap[it.id ?: "${it.title}_${it.createdAt}"] ?: FeedbackRepository.STATUS_PENDING) == FeedbackRepository.STATUS_PENDING }
                            FeedbackCategoryTab.SUGGESTIONS -> suggestionList.count { (statusMap[it.id ?: "${it.title}_${it.createdAt}"] ?: FeedbackRepository.STATUS_PENDING) == FeedbackRepository.STATUS_PENDING }
                        },
                        isSelected = selectedSubFilter == "PENDING",
                        color = Color(0xFFFFB300),
                        onClick = { selectedSubFilter = "PENDING" }
                    )
                }

                if (currentCategoryTab == FeedbackCategoryTab.ALL || currentCategoryTab == FeedbackCategoryTab.BUGS) {
                    item {
                        StatusFilterChip(
                            label = tr("👁️ Leídos"),
                            count = when (currentCategoryTab) {
                                FeedbackCategoryTab.ALL -> readCount
                                FeedbackCategoryTab.BUGS -> bugList.count { statusMap[it.id ?: "${it.title}_${it.createdAt}"] == FeedbackRepository.STATUS_READ }
                                FeedbackCategoryTab.SUGGESTIONS -> suggestionList.count { statusMap[it.id ?: "${it.title}_${it.createdAt}"] == FeedbackRepository.STATUS_READ }
                            },
                            isSelected = selectedSubFilter == "READ",
                            color = HextechCyan,
                            onClick = { selectedSubFilter = "READ" }
                        )
                    }

                    item {
                        StatusFilterChip(
                            label = tr("✅ Solucionados"),
                            count = when (currentCategoryTab) {
                                FeedbackCategoryTab.ALL -> solvedCount
                                FeedbackCategoryTab.BUGS -> bugList.count { val s = statusMap[it.id ?: "${it.title}_${it.createdAt}"]; s == FeedbackRepository.STATUS_SOLVED || s == FeedbackRepository.STATUS_COMPLETED }
                                FeedbackCategoryTab.SUGGESTIONS -> suggestionList.count { val s = statusMap[it.id ?: "${it.title}_${it.createdAt}"]; s == FeedbackRepository.STATUS_SOLVED || s == FeedbackRepository.STATUS_COMPLETED }
                            },
                            isSelected = selectedSubFilter == "SOLVED",
                            color = HextechGreen,
                            onClick = { selectedSubFilter = "SOLVED" }
                        )
                    }
                }

                if (currentCategoryTab == FeedbackCategoryTab.ALL || currentCategoryTab == FeedbackCategoryTab.SUGGESTIONS) {
                    item {
                        StatusFilterChip(
                            label = tr("✨ Aceptadas"),
                            count = when (currentCategoryTab) {
                                FeedbackCategoryTab.ALL -> acceptedCount
                                FeedbackCategoryTab.BUGS -> bugList.count { statusMap[it.id ?: "${it.title}_${it.createdAt}"] == FeedbackRepository.STATUS_ACCEPTED }
                                FeedbackCategoryTab.SUGGESTIONS -> suggestionList.count { statusMap[it.id ?: "${it.title}_${it.createdAt}"] == FeedbackRepository.STATUS_ACCEPTED }
                            },
                            isSelected = selectedSubFilter == "ACCEPTED",
                            color = HextechGold,
                            onClick = { selectedSubFilter = "ACCEPTED" }
                        )
                    }

                    item {
                        StatusFilterChip(
                            label = tr("❌ Rechazadas"),
                            count = when (currentCategoryTab) {
                                FeedbackCategoryTab.ALL -> rejectedCount
                                FeedbackCategoryTab.BUGS -> bugList.count { statusMap[it.id ?: "${it.title}_${it.createdAt}"] == FeedbackRepository.STATUS_REJECTED }
                                FeedbackCategoryTab.SUGGESTIONS -> suggestionList.count { statusMap[it.id ?: "${it.title}_${it.createdAt}"] == FeedbackRepository.STATUS_REJECTED }
                            },
                            isSelected = selectedSubFilter == "REJECTED",
                            color = DangerRed,
                            onClick = { selectedSubFilter = "REJECTED" }
                        )
                    }
                }
            }

            // Barra de Búsqueda y Acciones de Mantenimiento
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text(tr("Buscar por título, contenido o modelo..."), fontSize = 11.5.sp, color = TextMuted) },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = HextechGold, modifier = Modifier.size(17.dp))
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }, modifier = Modifier.size(24.dp)) {
                                Icon(Icons.Default.Close, contentDescription = tr("Limpiar"), tint = TextMuted, modifier = Modifier.size(16.dp))
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp),
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

                // Purga rápida > 7 días
                OutlinedButton(
                    onClick = {
                        if (!isPurging) {
                            isPurging = true
                            scope.launch {
                                val res = FeedbackRepository.purgeOldReports(days = 7)
                                isPurging = false
                                if (res.isSuccess) {
                                    Toast.makeText(context, "🧹 Purga de reportes >7 días completada", Toast.LENGTH_SHORT).show()
                                    loadReports()
                                } else {
                                    Toast.makeText(context, "Error al purgar: ${res.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    modifier = Modifier.height(46.dp),
                    enabled = !isPurging
                ) {
                    if (isPurging) {
                        CircularProgressIndicator(color = HextechCyan, modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                    } else {
                        Icon(Icons.Default.CleaningServices, contentDescription = tr("Purgar >7 días"), tint = HextechCyan, modifier = Modifier.size(18.dp))
                    }
                }

                // Borrar todos
                OutlinedButton(
                    onClick = { showClearAllConfirm = true },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, DangerRed.copy(alpha = 0.5f)),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    modifier = Modifier.height(46.dp)
                ) {
                    Icon(Icons.Default.DeleteSweep, contentDescription = tr("Borrar todo"), tint = DangerRed, modifier = Modifier.size(18.dp))
                }
            }

            // Lista de Contenido
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 4.dp)
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
                            text = tr("Cargando reportes y sugerencias..."),
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
                        Text(text = tr("Error de conexión:"), color = DangerRed, fontWeight = FontWeight.Bold, fontSize = 14.sp)
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
                            text = if (searchQuery.isNotBlank() || selectedSubFilter != "ALL" || currentCategoryTab != FeedbackCategoryTab.ALL) 
                                tr("No hay resultados en esta vista") 
                            else 
                                tr("No hay reportes ni sugerencias registradas"),
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
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(filteredReports, key = { it.id ?: "${it.title}_${it.createdAt}_${it.hashCode()}" }) { report ->
                            val key = report.id ?: "${report.title}_${report.createdAt}"
                            val currentStatus = statusMap[key] ?: FeedbackRepository.STATUS_PENDING

                            ComprehensiveFeedbackCard(
                                report = report,
                                currentStatus = currentStatus,
                                onSelectStatus = { newStatus ->
                                    statusMap[key] = newStatus
                                    FeedbackRepository.setFeedbackStatus(context, report, newStatus)
                                    val reportId = report.id
                                    if (!reportId.isNullOrBlank()) {
                                        scope.launch {
                                            FeedbackRepository.updateFeedbackStatusInCloud(reportId, newStatus)
                                        }
                                    }
                                    val msg = when (newStatus) {
                                        FeedbackRepository.STATUS_SOLVED -> "✅ Marcado como Solucionado"
                                        FeedbackRepository.STATUS_READ -> "👁️ Marcado como Leído"
                                        FeedbackRepository.STATUS_ACCEPTED -> "✨ Sugerencia Aceptada"
                                        FeedbackRepository.STATUS_REJECTED -> "❌ Sugerencia Rechazada"
                                        else -> "⏳ Marcado como Pendiente"
                                    }
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                },
                                onDelete = { reportToDelete = report },
                                onCopy = {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    val textToCopy = """
                                        [${report.type}] ${report.title}
                                        Estado: $currentStatus
                                        Descripción: ${report.description}
                                        Versión: ${report.appVersion}
                                        Dispositivo: ${report.deviceInfo}
                                        Fecha: ${report.createdAt ?: "N/A"}
                                    """.trimIndent()
                                    clipboard.setPrimaryClip(ClipData.newPlainText("Feedback Report", textToCopy))
                                    Toast.makeText(context, "📋 Reporte copiado al portapapeles", Toast.LENGTH_SHORT).show()
                                },
                                onOpenImage = { bmp -> previewImageBitmap = bmp }
                            )
                        }
                    }
                }
            }
        }
    }

    // Diálogo de Confirmación de Eliminación Individual
    if (reportToDelete != null) {
        val rep = reportToDelete!!
        AlertDialog(
            onDismissRequest = { if (!isDeleting) reportToDelete = null },
            containerColor = HextechDarkBg,
            shape = RoundedCornerShape(14.dp),
            title = {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = DangerRed, modifier = Modifier.size(22.dp))
                    Text(tr("¿Eliminar este elemento?"), color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column {
                    Text(
                        text = tr("Esta acción borrará permanentemente de Supabase el reporte:"),
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
                                    Toast.makeText(context, "🗑️ Elemento eliminado", Toast.LENGTH_SHORT).show()
                                    reportToDelete = null
                                    loadReports()
                                } else {
                                    Toast.makeText(context, "Error: ${res.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(context, "Eliminado localmente", Toast.LENGTH_SHORT).show()
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

    // Diálogo de Confirmación Borrar Todo
    if (showClearAllConfirm) {
        AlertDialog(
            onDismissRequest = { if (!isDeleting) showClearAllConfirm = false },
            containerColor = HextechDarkBg,
            shape = RoundedCornerShape(14.dp),
            title = {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.DeleteSweep, contentDescription = null, tint = DangerRed, modifier = Modifier.size(24.dp))
                    Text(tr("¿Borrar todos los reportes?"), color = DangerRed, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Text(
                    text = tr("Esta acción eliminará todos los reportes y sugerencias registrados en la nube y el dispositivo de forma irreversible."),
                    color = TextSecondary,
                    fontSize = 13.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        isDeleting = true
                        scope.launch {
                            val res = FeedbackRepository.clearAllFeedbacks()
                            isDeleting = false
                            showClearAllConfirm = false
                            if (res.isSuccess) {
                                Toast.makeText(context, "🗑️ Todos los reportes fueron eliminados", Toast.LENGTH_SHORT).show()
                                loadReports()
                            } else {
                                Toast.makeText(context, "Error: ${res.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isDeleting
                ) {
                    if (isDeleting) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                    } else {
                        Text(tr("Sí, Borrar Todo"), color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearAllConfirm = false }, enabled = !isDeleting) {
                    Text(tr("Cancelar"), color = TextMuted)
                }
            }
        )
    }

    // Diálogo de Vista Previa de Imagen
    if (previewImageBitmap != null) {
        AlertDialog(
            onDismissRequest = { previewImageBitmap = null },
            containerColor = HextechDarkBg,
            shape = RoundedCornerShape(14.dp),
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Default.Image, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                        Text(tr("Captura Adjunta"), color = HextechGold, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                    IconButton(onClick = { previewImageBitmap = null }, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Default.Close, contentDescription = tr("Cerrar"), tint = TextMuted)
                    }
                }
            },
            text = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 420.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        bitmap = previewImageBitmap!!.asImageBitmap(),
                        contentDescription = "Vista previa",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { previewImageBitmap = null },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                ) {
                    Text(tr("Cerrar"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}

@Composable
private fun StatusFilterChip(
    label: String,
    count: Int,
    isSelected: Boolean,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected) color.copy(alpha = 0.22f)
                else HextechSurface
            )
            .border(
                width = if (isSelected) 1.5.dp else 0.8.dp,
                color = if (isSelected) color else HextechCardBorder.copy(alpha = 0.6f),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = label,
                color = if (isSelected) color else TextPrimary,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (isSelected) color.copy(alpha = 0.3f) else HextechDarkBg)
                    .padding(horizontal = 4.dp, vertical = 1.dp)
            ) {
                Text(
                    text = count.toString(),
                    color = if (isSelected) color else TextMuted,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun ComprehensiveFeedbackCard(
    report: FeedbackReport,
    currentStatus: String,
    onSelectStatus: (String) -> Unit,
    onDelete: () -> Unit,
    onCopy: () -> Unit,
    onOpenImage: (Bitmap) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val isBug = report.type.equals("BUG", ignoreCase = true)

    // Parsear imágenes base64 si existen
    val attachedBitmaps = remember(report.deviceInfo, report.description) {
        extractBase64Images(report.deviceInfo + "\n" + report.description)
    }

    // Información del tipo
    val (typeColor, typeIcon, typeLabel) = if (isBug) {
        Triple(DangerRed, Icons.Default.BugReport, "BUG / ERROR")
    } else {
        Triple(Color(0xFFFFB74D), Icons.Default.Lightbulb, "SUGERENCIA")
    }

    // Información del estado visual actual
    val (statusLabel, statusColor, statusIcon) = when (currentStatus) {
        FeedbackRepository.STATUS_SOLVED, FeedbackRepository.STATUS_COMPLETED -> {
            Triple(tr("Solucionado"), HextechGreen, Icons.Default.CheckCircle)
        }
        FeedbackRepository.STATUS_READ -> {
            Triple(tr("Leído"), HextechCyan, Icons.Default.Visibility)
        }
        FeedbackRepository.STATUS_ACCEPTED -> {
            Triple(tr("Aceptada"), HextechGold, Icons.Default.Star)
        }
        FeedbackRepository.STATUS_REJECTED -> {
            Triple(tr("Rechazada"), DangerRed, Icons.Default.Cancel)
        }
        else -> {
            Triple(tr("Pendiente"), Color(0xFFFFB300), Icons.Default.HourglassEmpty)
        }
    }

    val cardBorderColor by animateColorAsState(
        targetValue = statusColor.copy(alpha = 0.45f),
        label = "card_border"
    )

    val formattedDate = remember(report.createdAt) {
        formatReportDate(report.createdAt)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, cardBorderColor, RoundedCornerShape(12.dp))
            .animateContentSize(animationSpec = tween(180)),
        colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.95f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Fila Superior: Badges + Fecha + Acciones (Copiar, Borrar, Expandir)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Badge de Tipo
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(typeColor.copy(alpha = 0.18f))
                            .border(1.dp, typeColor.copy(alpha = 0.8f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            Icon(typeIcon, contentDescription = null, tint = typeColor, modifier = Modifier.size(11.dp))
                            Text(text = typeLabel, color = typeColor, fontSize = 9.5.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Badge de Estado Actual
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(statusColor.copy(alpha = 0.18f))
                            .border(1.dp, statusColor.copy(alpha = 0.85f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            Icon(statusIcon, contentDescription = null, tint = statusColor, modifier = Modifier.size(11.dp))
                            Text(text = statusLabel, color = statusColor, fontSize = 9.5.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Text(
                        text = formattedDate,
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onCopy, modifier = Modifier.size(26.dp)) {
                        Icon(Icons.Default.ContentCopy, contentDescription = tr("Copiar"), tint = TextMuted, modifier = Modifier.size(15.dp))
                    }
                    IconButton(onClick = onDelete, modifier = Modifier.size(26.dp)) {
                        Icon(Icons.Default.Delete, contentDescription = tr("Eliminar"), tint = DangerRed.copy(alpha = 0.8f), modifier = Modifier.size(15.dp))
                    }
                    IconButton(onClick = { expanded = !expanded }, modifier = Modifier.size(26.dp)) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = if (expanded) tr("Contraer") else tr("Expandir"),
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
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { expanded = !expanded }
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Descripción
            val cleanDescription = remember(report.description) {
                cleanDescriptionText(report.description)
            }
            Text(
                text = cleanDescription,
                color = TextSecondary,
                fontSize = 12.5.sp,
                lineHeight = 17.sp,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable { expanded = !expanded }
            )

            // Miniaturas de Imágenes Adjuntas
            if (attachedBitmaps.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(attachedBitmaps) { bmp ->
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, HextechGold.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                .clickable { onOpenImage(bmp) }
                        ) {
                            Image(
                                bitmap = bmp.asImageBitmap(),
                                contentDescription = "Captura adjunta",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            // CONTROLES DE ESTADO (Requisitos de selección para el usuario/admin)
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(HextechDarkBg.copy(alpha = 0.7f))
                    .border(0.6.dp, HextechCardBorder.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Column {
                    Text(
                        text = tr("Marcar estado:"),
                        color = TextMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if (isBug) {
                        // Opciones de Reportes: Pendiente | Leído | Solucionado
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            StatusActionButton(
                                label = tr("Pendiente"),
                                icon = Icons.Default.HourglassEmpty,
                                isSelected = currentStatus == FeedbackRepository.STATUS_PENDING,
                                activeColor = Color(0xFFFFB300),
                                modifier = Modifier.weight(1f),
                                onClick = { onSelectStatus(FeedbackRepository.STATUS_PENDING) }
                            )
                            StatusActionButton(
                                label = tr("Leído"),
                                icon = Icons.Default.Visibility,
                                isSelected = currentStatus == FeedbackRepository.STATUS_READ,
                                activeColor = HextechCyan,
                                modifier = Modifier.weight(1f),
                                onClick = { onSelectStatus(FeedbackRepository.STATUS_READ) }
                            )
                            StatusActionButton(
                                label = tr("Solucionado"),
                                icon = Icons.Default.CheckCircle,
                                isSelected = currentStatus == FeedbackRepository.STATUS_SOLVED || currentStatus == FeedbackRepository.STATUS_COMPLETED,
                                activeColor = HextechGreen,
                                modifier = Modifier.weight(1.1f),
                                onClick = { onSelectStatus(FeedbackRepository.STATUS_SOLVED) }
                            )
                        }
                    } else {
                        // Opciones de Sugerencias: Pendiente | Aceptada | Rechazada
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            StatusActionButton(
                                label = tr("Pendiente"),
                                icon = Icons.Default.HourglassEmpty,
                                isSelected = currentStatus == FeedbackRepository.STATUS_PENDING,
                                activeColor = Color(0xFFFFB300),
                                modifier = Modifier.weight(1f),
                                onClick = { onSelectStatus(FeedbackRepository.STATUS_PENDING) }
                            )
                            StatusActionButton(
                                label = tr("Aceptada"),
                                icon = Icons.Default.Check,
                                isSelected = currentStatus == FeedbackRepository.STATUS_ACCEPTED,
                                activeColor = HextechGold,
                                modifier = Modifier.weight(1f),
                                onClick = { onSelectStatus(FeedbackRepository.STATUS_ACCEPTED) }
                            )
                            StatusActionButton(
                                label = tr("Rechazada"),
                                icon = Icons.Default.Close,
                                isSelected = currentStatus == FeedbackRepository.STATUS_REJECTED,
                                activeColor = DangerRed,
                                modifier = Modifier.weight(1f),
                                onClick = { onSelectStatus(FeedbackRepository.STATUS_REJECTED) }
                            )
                        }
                    }
                }
            }

            // Diagnóstico y metadatos expandibles
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(tween(140)),
                exit = fadeOut(tween(140))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechDarkBg.copy(alpha = 0.9f))
                        .border(0.5.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val cleanDeviceInfo = remember(report.deviceInfo) {
                        cleanDeviceInfoText(report.deviceInfo)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Default.Smartphone, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(13.dp))
                        Text(
                            text = "${tr("Dispositivo:")} $cleanDeviceInfo",
                            color = HextechCyan,
                            fontSize = 10.5.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Default.Tune, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                        Text(
                            text = "${tr("Versión:")} ${report.appVersion}",
                            color = HextechGold,
                            fontSize = 10.5.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (!report.id.isNullOrBlank()) {
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

@Composable
private fun StatusActionButton(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    activeColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(
                if (isSelected) activeColor.copy(alpha = 0.25f)
                else HextechSurface
            )
            .border(
                width = if (isSelected) 1.2.dp else 0.6.dp,
                color = if (isSelected) activeColor else HextechCardBorder.copy(alpha = 0.5f),
                shape = RoundedCornerShape(6.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) activeColor else TextMuted,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                color = if (isSelected) activeColor else TextPrimary,
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                maxLines = 1
            )
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

private fun cleanDescriptionText(text: String): String {
    return text.substringBefore("[IMAGE_BASE64]").trim()
}

private fun cleanDeviceInfoText(text: String): String {
    return text.substringBefore("[IMAGE_BASE64]").trim()
}

private fun extractBase64Images(rawText: String): List<Bitmap> {
    val results = mutableListOf<Bitmap>()
    if (!rawText.contains("[IMAGE_BASE64]")) return results
    val parts = rawText.split("[IMAGE_BASE64]")
    for (i in 1 until parts.size) {
        val segment = parts[i].trim().substringBefore("\n\n").substringBefore("[IMAGE_BASE64]").trim()
        if (segment.isNotEmpty()) {
            try {
                val cleanBase64 = if (segment.contains(",")) segment.substringAfter(",") else segment
                val bytes = Base64.decode(cleanBase64, Base64.DEFAULT)
                val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                if (bmp != null) {
                    results.add(bmp)
                }
            } catch (e: Exception) {
                // Ignore corrupted image
            }
        }
    }
    return results
}
