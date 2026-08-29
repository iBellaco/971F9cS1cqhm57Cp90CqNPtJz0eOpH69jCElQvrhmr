package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsKabaddi
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.WildRiftRepository
import com.example.data.local.entity.SavedDraftEntity
import com.example.data.repository.DraftHistoryRepository
import com.example.model.DraftSlot
import com.example.model.LaneRole
import com.example.ui.components.ChampionAvatar
import com.example.ui.theme.DangerRed
import com.example.ui.theme.DangerRedSurface
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.util.tr
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DraftHistoryScreen(
    onNavigateBack: () -> Unit,
    onLoadDraft: (allies: List<DraftSlot>, enemies: List<DraftSlot>, role: LaneRole, isFirstPick: Boolean) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val draftsFlow = remember(context) { DraftHistoryRepository.getAllDrafts(context) }
    val draftsList by draftsFlow.collectAsStateWithLifecycle(initialValue = emptyList())

    var searchQuery by remember { mutableStateOf("") }
    var selectedResultFilter by remember { mutableStateOf<String?>(null) } // null = ALL, "VICTORY", "DEFEAT"
    var selectedRoleFilter by remember { mutableStateOf<LaneRole?>(null) }
    var selectedDraftForDetail by remember { mutableStateOf<SavedDraftEntity?>(null) }
    var draftToDelete by remember { mutableStateOf<SavedDraftEntity?>(null) }
    var showClearAllConfirm by remember { mutableStateOf(false) }

    val filteredDrafts = remember(draftsList, searchQuery, selectedResultFilter, selectedRoleFilter) {
        draftsList.filter { draft ->
            val matchesQuery = searchQuery.isBlank() ||
                    draft.title.contains(searchQuery, ignoreCase = true) ||
                    draft.myChampionName.contains(searchQuery, ignoreCase = true) ||
                    draft.enemyLaneOpponentName.contains(searchQuery, ignoreCase = true) ||
                    draft.notes.contains(searchQuery, ignoreCase = true)

            val matchesResult = selectedResultFilter == null || draft.matchResult.equals(selectedResultFilter, ignoreCase = true)

            val matchesRole = selectedRoleFilter == null || draft.userRole.equals(selectedRoleFilter?.name, ignoreCase = true)

            matchesQuery && matchesResult && matchesRole
        }
    }

    val totalCount = draftsList.size
    val victoriesCount = draftsList.count { it.matchResult.equals("VICTORY", ignoreCase = true) }
    val defeatsCount = draftsList.count { it.matchResult.equals("DEFEAT", ignoreCase = true) }
    val totalFinished = victoriesCount + defeatsCount
    val winRate = if (totalFinished > 0) (victoriesCount.toDouble() / totalFinished * 100).toInt() else 0

    Scaffold(
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = tr("Historial de Drafts"),
                            color = TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.testTag("draft_history_title")
                        )
                        Text(
                            text = "${draftsList.size} " + tr("partidas guardadas"),
                            color = HextechCyan,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("history_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = tr("Volver"),
                            tint = HextechGold
                        )
                    }
                },
                actions = {
                    if (draftsList.isNotEmpty()) {
                        IconButton(
                            onClick = { showClearAllConfirm = true },
                            modifier = Modifier.testTag("history_clear_all_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = tr("Limpiar Historial"),
                                tint = DangerRed.copy(alpha = 0.85f),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(6.dp))

            // Stats Summary Card
            if (draftsList.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = tr("Rendimiento en Partidas"),
                                color = HextechGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Text("🏆 $victoriesCount " + tr("Vic."), color = Color(0xFF81C784), fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                                Text("💀 $defeatsCount " + tr("Derr."), color = DangerRed, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        if (totalFinished > 0) {
                            Surface(
                                color = if (winRate >= 50) Color(0xFF81C784).copy(alpha = 0.15f) else DangerRed.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, if (winRate >= 50) Color(0xFF81C784) else DangerRed)
                            ) {
                                Column(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "$winRate%",
                                        color = if (winRate >= 50) Color(0xFF81C784) else DangerRed,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                    Text(
                                        text = tr("Winrate"),
                                        color = TextMuted,
                                        fontSize = 9.5.sp
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Search Box
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("history_search_input"),
                    placeholder = { Text(tr("Buscar por campeón, rival o nota..."), color = TextMuted, fontSize = 12.5.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(18.dp)) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = tr("Limpiar"), tint = TextMuted, modifier = Modifier.size(16.dp))
                            }
                        }
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechCyan,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedContainerColor = HextechSurface,
                        unfocusedContainerColor = HextechSurface
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Result Filters
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FilterChip(
                        selected = selectedResultFilter == null,
                        onClick = { selectedResultFilter = null },
                        label = { Text(tr("Todos") + " ($totalCount)", fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = HextechCyan,
                            selectedLabelColor = HextechDarkBg
                        )
                    )
                    FilterChip(
                        selected = selectedResultFilter == "VICTORY",
                        onClick = { selectedResultFilter = if (selectedResultFilter == "VICTORY") null else "VICTORY" },
                        label = { Text("🏆 " + tr("Victorias") + " ($victoriesCount)", fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF81C784),
                            selectedLabelColor = Color.Black
                        )
                    )
                    FilterChip(
                        selected = selectedResultFilter == "DEFEAT",
                        onClick = { selectedResultFilter = if (selectedResultFilter == "DEFEAT") null else "DEFEAT" },
                        label = { Text("💀 " + tr("Derrotas") + " ($defeatsCount)", fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = DangerRed,
                            selectedLabelColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }

            if (filteredDrafts.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = HextechSurface,
                            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f)),
                            modifier = Modifier.size(100.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Description,
                                    contentDescription = null,
                                    tint = HextechGold,
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = if (draftsList.isEmpty()) tr("Tu historial está limpio.") else tr("No se encontraron partidas con ese filtro"),
                            color = TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (draftsList.isEmpty())
                                tr("Ve al Asistente de Draft, crea tu primera composición y guárdala para analizarla después.")
                            else
                                tr("Intenta cambiar el término de búsqueda o restablecer los filtros de resultado y rol."),
                            color = TextMuted,
                            fontSize = 12.5.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 17.sp
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredDrafts, key = { it.id }) { draft ->
                        SavedDraftCard(
                            draft = draft,
                            onClick = { selectedDraftForDetail = draft },
                            onLoad = {
                                val allies = DraftHistoryRepository.parseDraftSlots(draft.allyPicksJson)
                                val enemies = DraftHistoryRepository.parseDraftSlots(draft.enemyPicksJson)
                                val role = try { LaneRole.valueOf(draft.userRole) } catch (_: Exception) { LaneRole.MID }
                                onLoadDraft(allies, enemies, role, draft.isFirstPick)
                            },
                            onUpdateResult = { newResult ->
                                coroutineScope.launch {
                                    DraftHistoryRepository.updateMatchResult(context, draft.id, newResult)
                                }
                            },
                            onDelete = { draftToDelete = draft }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        }
    }

    // Detail Bottom Sheet
    if (selectedDraftForDetail != null) {
        DraftDetailBottomSheet(
            draft = selectedDraftForDetail!!,
            onDismiss = { selectedDraftForDetail = null },
            onLoad = {
                val draft = selectedDraftForDetail!!
                val allies = DraftHistoryRepository.parseDraftSlots(draft.allyPicksJson)
                val enemies = DraftHistoryRepository.parseDraftSlots(draft.enemyPicksJson)
                val role = try { LaneRole.valueOf(draft.userRole) } catch (_: Exception) { LaneRole.MID }
                selectedDraftForDetail = null
                onLoadDraft(allies, enemies, role, draft.isFirstPick)
            },
            onSaveNotes = { newNotes ->
                coroutineScope.launch {
                    DraftHistoryRepository.updateNotes(context, selectedDraftForDetail!!.id, newNotes)
                    selectedDraftForDetail = selectedDraftForDetail?.copy(notes = newNotes)
                }
            }
        )
    }

    // Confirm Delete Dialog
    if (draftToDelete != null) {
        AlertDialog(
            onDismissRequest = { draftToDelete = null },
            title = { Text(tr("Eliminar partida"), color = TextPrimary, fontWeight = FontWeight.Bold) },
            text = { Text(tr("¿Deseas eliminar este registro del historial? Esta acción no se puede deshacer."), color = TextSecondary, fontSize = 13.sp) },
            confirmButton = {
                Button(
                    onClick = {
                        val id = draftToDelete!!.id
                        draftToDelete = null
                        coroutineScope.launch {
                            DraftHistoryRepository.deleteDraft(context, id)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                ) {
                    Text(tr("Eliminar"), color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { draftToDelete = null }) {
                    Text(tr("Cancelar"), color = TextMuted)
                }
            },
            containerColor = HextechSurface,
            shape = RoundedCornerShape(16.dp)
        )
    }

    // Confirm Clear All Dialog
    if (showClearAllConfirm) {
        AlertDialog(
            onDismissRequest = { showClearAllConfirm = false },
            title = { Text(tr("Borrar todo el historial"), color = DangerRed, fontWeight = FontWeight.Bold) },
            text = { Text(tr("¿Estás seguro de vaciar todas las partidas y composiciones guardadas?"), color = TextSecondary, fontSize = 13.sp) },
            confirmButton = {
                Button(
                    onClick = {
                        showClearAllConfirm = false
                        coroutineScope.launch {
                            DraftHistoryRepository.clearAllDrafts(context)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                ) {
                    Text(tr("Borrar Todo"), color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearAllConfirm = false }) {
                    Text(tr("Cancelar"), color = TextMuted)
                }
            },
            containerColor = HextechSurface,
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
private fun SavedDraftCard(
    draft: SavedDraftEntity,
    onClick: () -> Unit,
    onLoad: () -> Unit,
    onUpdateResult: (String) -> Unit,
    onDelete: () -> Unit
) {
    val allies = remember(draft.allyPicksJson) { DraftHistoryRepository.parseDraftSlots(draft.allyPicksJson) }
    val enemies = remember(draft.enemyPicksJson) { DraftHistoryRepository.parseDraftSlots(draft.enemyPicksJson) }
    val formattedDate = remember(draft.timestamp) {
        val sdf = SimpleDateFormat("dd MMM yyyy • HH:mm", Locale.getDefault())
        sdf.format(Date(draft.timestamp))
    }

    var resultMenuExpanded by remember { mutableStateOf(false) }

    val roleObj = try { LaneRole.valueOf(draft.userRole) } catch (_: Exception) { LaneRole.MID }

    val resultBg = when (draft.matchResult.uppercase()) {
        "VICTORY" -> Color(0xFF81C784).copy(alpha = 0.18f)
        "DEFEAT" -> DangerRed.copy(alpha = 0.18f)
        else -> HextechGold.copy(alpha = 0.18f)
    }
    val resultBorder = when (draft.matchResult.uppercase()) {
        "VICTORY" -> Color(0xFF81C784)
        "DEFEAT" -> DangerRed
        else -> HextechGold
    }
    val resultLabel = when (draft.matchResult.uppercase()) {
        "VICTORY" -> "🏆 " + tr("Victoria")
        "DEFEAT" -> "💀 " + tr("Derrota")
        else -> "⏳ " + tr("Pendiente")
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .testTag("saved_draft_card_${draft.id}"),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header Row: Date & Result Switcher
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = HextechGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = tr(roleObj.displayName),
                        color = HextechGold,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "•  $formattedDate",
                        color = TextMuted,
                        fontSize = 10.5.sp
                    )
                }

                // Interactive Result Badge with dropdown
                Box {
                    Surface(
                        color = resultBg,
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, resultBorder),
                        modifier = Modifier.clickable { resultMenuExpanded = true }
                    ) {
                        Text(
                            text = resultLabel,
                            color = resultBorder,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = resultMenuExpanded,
                        onDismissRequest = { resultMenuExpanded = false },
                        modifier = Modifier.background(HextechSurface)
                    ) {
                        DropdownMenuItem(
                            text = { Text("🏆 " + tr("Victoria"), color = Color(0xFF81C784), fontWeight = FontWeight.Bold) },
                            onClick = {
                                onUpdateResult("VICTORY")
                                resultMenuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("💀 " + tr("Derrota"), color = DangerRed, fontWeight = FontWeight.Bold) },
                            onClick = {
                                onUpdateResult("DEFEAT")
                                resultMenuExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Champion matchup headline
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = draft.title,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${draft.estimatedWinrate}% " + tr("WR Est."),
                    color = HextechCyan,
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 5v5 Team Avatar Visualizer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(HextechDarkBg.copy(alpha = 0.5f))
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Allies
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    allies.take(5).forEach { slot ->
                        Box(contentAlignment = Alignment.BottomEnd) {
                            ChampionAvatar(
                                champion = slot.champion,
                                size = 32.dp
                            )
                        }
                    }
                }

                // VS Badge
                Text(
                    text = "VS",
                    color = DangerRed,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                // Enemies
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    enemies.take(5).forEach { slot ->
                        ChampionAvatar(
                            champion = slot.champion,
                            size = 32.dp
                        )
                    }
                }
            }

            // Notes preview if present
            if (draft.notes.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "📝 ${draft.notes}",
                    color = TextSecondary,
                    fontSize = 11.5.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Action Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onClick,
                    contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Icon(Icons.Default.SportsKabaddi, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(tr("Ver Análisis"), color = HextechCyan, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = tr("Eliminar"), tint = TextMuted, modifier = Modifier.size(16.dp))
                    }

                    Button(
                        onClick = onLoad,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HextechGold,
                            contentColor = HextechDarkBg
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tr("Cargar Draft"), fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DraftDetailBottomSheet(
    draft: SavedDraftEntity,
    onDismiss: () -> Unit,
    onLoad: () -> Unit,
    onSaveNotes: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val allies = remember(draft.allyPicksJson) { DraftHistoryRepository.parseDraftSlots(draft.allyPicksJson) }
    val enemies = remember(draft.enemyPicksJson) { DraftHistoryRepository.parseDraftSlots(draft.enemyPicksJson) }

    var userNotes by remember(draft.notes) { mutableStateOf(draft.notes) }
    var isEditingNotes by remember { mutableStateOf(false) }

    val roleObj = try { LaneRole.valueOf(draft.userRole) } catch (_: Exception) { LaneRole.MID }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechSurfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = draft.title,
                        color = TextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = tr("Línea:") + " ${com.example.util.tr(roleObj.displayName)} • " + if (draft.isFirstPick) tr("Primer Pick") else tr("Counter Pick"),
                        color = HextechGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Button(
                    onClick = onLoad,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HextechGold,
                        contentColor = HextechDarkBg
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(tr("Cargar en Selección"), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Equipo Aliado
            Text(tr("Tu Equipo Aliado"), color = HextechCyan, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                allies.forEach { slot ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (slot.assignedRole == roleObj) HextechGold.copy(alpha = 0.15f) else HextechSurface)
                            .border(1.dp, if (slot.assignedRole == roleObj) HextechGold else HextechCardBorder, RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ChampionAvatar(champion = slot.champion, size = 36.dp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(slot.champion.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    if (slot.assignedRole == roleObj) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("(TÚ)", color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Black)
                                    }
                                }
                                Text(
                                    text = tr(slot.assignedRole.displayName) + " • Tier ${slot.champion.tier}",
                                    color = HextechCyan,
                                    fontSize = 11.sp
                                )
                            }
                        }
                        Text(
                            text = "${slot.champion.winrate}% WR",
                            color = HextechGold,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Equipo Rival
            Text(tr("Equipo Rival"), color = DangerRed, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                enemies.forEach { slot ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (slot.assignedRole == roleObj) DangerRedSurface else HextechSurface)
                            .border(1.dp, if (slot.assignedRole == roleObj) DangerRed else HextechCardBorder, RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ChampionAvatar(champion = slot.champion, size = 36.dp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(slot.champion.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    if (slot.assignedRole == roleObj) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("(" + tr("Rival Directo") + ")", color = DangerRed, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                                Text(
                                    text = tr(slot.assignedRole.displayName) + " • Tier ${slot.champion.tier}",
                                    color = TextMuted,
                                    fontSize = 11.sp
                                )
                            }
                        }
                        Text(
                            text = "${slot.champion.winrate}% WR",
                            color = HextechGold,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Damage Balance
            Text(tr("Distribución de Daño Aliado"), color = HextechGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))) {
                if (draft.allyDamagePhysical > 0) Box(modifier = Modifier.weight(draft.allyDamagePhysical.toFloat()).fillMaxHeight().background(Color(0xFFE57373)))
                if (draft.allyDamageMagic > 0) Box(modifier = Modifier.weight(draft.allyDamageMagic.toFloat()).fillMaxHeight().background(Color(0xFF64B5F6)))
                if (draft.allyDamageTrue > 0) Box(modifier = Modifier.weight(draft.allyDamageTrue.toFloat()).fillMaxHeight().background(Color.White))
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("${draft.allyDamagePhysical}% " + tr("Físico"), color = Color(0xFFE57373), fontSize = 9.5.sp)
                Text("${draft.allyDamageMagic}% " + tr("Mágico"), color = Color(0xFF64B5F6), fontSize = 9.5.sp)
                Text("${draft.allyDamageTrue}% " + tr("Verdadero"), color = Color.White, fontSize = 9.5.sp)
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Notes Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(tr("Notas Personales / Lecciones"), color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                if (!isEditingNotes) {
                    TextButton(onClick = { isEditingNotes = true }) {
                        Icon(Icons.Default.Edit, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tr("Editar"), color = HextechCyan, fontSize = 11.sp)
                    }
                }
            }

            if (isEditingNotes) {
                OutlinedTextField(
                    value = userNotes,
                    onValueChange = { userNotes = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(tr("Escribe qué funcionó, errores o notas tácticas..."), color = TextMuted, fontSize = 12.sp) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedContainerColor = HextechSurface,
                        unfocusedContainerColor = HextechSurface
                    ),
                    shape = RoundedCornerShape(8.dp),
                    minLines = 2
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = { isEditingNotes = false }) {
                        Text(tr("Cancelar"), color = TextMuted, fontSize = 11.5.sp)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            onSaveNotes(userNotes)
                            isEditingNotes = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(tr("Guardar Nota"), fontWeight = FontWeight.Bold, fontSize = 11.5.sp)
                    }
                }
            } else {
                Text(
                    text = if (draft.notes.isNotBlank()) draft.notes else tr("Sin notas adicionales registradas."),
                    color = if (draft.notes.isNotBlank()) TextPrimary else TextMuted,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
