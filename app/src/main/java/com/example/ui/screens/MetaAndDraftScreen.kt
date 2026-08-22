package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.example.util.tr
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.WildRiftRepository
import com.example.data.sync.ChineseMetaSyncService
import com.example.data.sync.ChineseSyncState
import com.example.data.sync.TencentRankTier
import kotlinx.coroutines.launch
import com.example.model.Champion
import com.example.model.DamageType
import com.example.model.DraftAnalysisResult
import com.example.model.DraftSlot
import com.example.model.ItemCategory
import com.example.model.LaneRole
import com.example.model.MapObjectiveItem
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.model.WildRiftItem
import com.example.ui.components.AppAssetImage
import com.example.ui.components.ChampionAvatar
import com.example.ui.components.CooldownTrackerPanel
import com.example.ui.components.DamagePenetrationCalculator
import com.example.ui.theme.AllyBlue
import com.example.ui.theme.DangerRed
import com.example.ui.theme.DangerRedSurface
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextCyan
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TierAColor
import com.example.ui.theme.TierSColor
import com.example.ui.theme.TierSPlusColor

import com.example.util.LocalLanguage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MetaAndDraftScreen(
    userMainRole: LaneRole,
    onNavigateBack: () -> Unit
) {
    val lang = LocalLanguage.current
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var activeRole by remember { mutableStateOf(userMainRole) }
    var showRoleChangeDialog by remember { mutableStateOf(false) }

    // Draft State con asignación explícita de línea
    val allySlots = remember {
        mutableStateListOf(
            DraftSlot(WildRiftRepository.getChampionById("chogath") ?: WildRiftRepository.champions[8], LaneRole.TOP),
            DraftSlot(WildRiftRepository.getChampionById("viego") ?: WildRiftRepository.champions[1], LaneRole.JUNGLE),
            DraftSlot(WildRiftRepository.getChampionById("vayne") ?: WildRiftRepository.champions[3], LaneRole.ADC),
            DraftSlot(WildRiftRepository.getChampionById("janna") ?: WildRiftRepository.champions[4], LaneRole.SUPPORT)
        )
    }

    val enemySlots = remember {
        mutableStateListOf(
            DraftSlot(WildRiftRepository.getChampionById("sett") ?: WildRiftRepository.champions[2], LaneRole.TOP),
            DraftSlot(WildRiftRepository.getChampionById("vi") ?: WildRiftRepository.champions[7], LaneRole.JUNGLE),
            DraftSlot(WildRiftRepository.getChampionById("caitlyn") ?: WildRiftRepository.champions[6], LaneRole.ADC),
            DraftSlot(WildRiftRepository.getChampionById("nautilus") ?: WildRiftRepository.champions[5], LaneRole.SUPPORT)
        )
    }

    // Modal Champion Picker & Detail State
    var pickingForTeam by remember { mutableStateOf<String?>(null) } // "ALLY", "ENEMY", "MYSELF"
    var suggestedPickingRole by remember { mutableStateOf<LaneRole?>(null) }
    var selectedDetailChampion by remember { mutableStateOf<Champion?>(null) }
    var isFirstPick by remember { mutableStateOf(false) }

    // Sincronización contextual automática: Mi campeón es el aliado en mi línea activa
    val myChampion = allySlots.find { it.assignedRole == activeRole }?.champion
    val enemyLaneOpponent = enemySlots.find { it.assignedRole == activeRole }?.champion

    val analysis = remember(activeRole, isFirstPick, allySlots.toList(), enemySlots.toList(), lang) {
        WildRiftRepository.analyzeDraft(
            myRole = activeRole,
            allies = allySlots.map { it.champion },
            enemies = enemySlots.map { it.champion },
            enemyLaneOpponent = enemyLaneOpponent,
            isFirstPick = isFirstPick,
            lang = lang
        )
    }

    // Manejo inteligente del botón Atrás dentro de la pantalla de Catálogo / Drafting
    BackHandler {
        when {
            selectedDetailChampion != null -> {
                selectedDetailChampion = null
            }
            pickingForTeam != null -> {
                pickingForTeam = null
            }
            showRoleChangeDialog -> {
                showRoleChangeDialog = false
            }
            selectedTabIndex != 0 -> {
                selectedTabIndex = 0
            }
            else -> {
                onNavigateBack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = tr("Wild Rift Coach"),
                            color = TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = tr(WildRiftRepository.CURRENT_PATCH_VERSION),
                            color = HextechCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("draft_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = tr("Volver"),
                            tint = HextechGold
                        )
                    }
                },
                actions = {
                    // Botón superior derecho retirado según solicitud
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = HextechDarkBg)
            )
        },
        containerColor = HextechDarkBg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Scrollable Tab Row with all requested sections
            val tabs = listOf(
                tr("Drafting"),
                tr("Campeones"),
                tr("Tier List"),
                tr("Objetos"),
                tr("Runas"),
                tr("Hechizos"),
                tr("Objetivos")
            )

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = HextechSurface,
                contentColor = HextechCyan,
                edgePadding = 12.dp,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = HextechCyan,
                        height = 3.dp
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                color = if (selectedTabIndex == index) HextechCyan else TextMuted,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 13.sp
                            )
                        }
                    )
                }
            }

            AnimatedContent(
                targetState = selectedTabIndex,
                transitionSpec = {
                    if (targetState > initialState) {
                        (slideInHorizontally { width -> width } + fadeIn()).togetherWith(slideOutHorizontally { width -> -width } + fadeOut())
                    } else {
                        (slideInHorizontally { width -> -width } + fadeIn()).togetherWith(slideOutHorizontally { width -> width } + fadeOut())
                    }
                },
                label = "tab_animation"
            ) { targetIndex ->
                when (targetIndex) {
                    0 -> {
                        // SECCIÓN: ANÁLISIS DE DRAFTING & COUNTERS
                        DraftAnalysisTab(
                            myChampion = myChampion,
                            activeRole = activeRole,
                            allySlots = allySlots,
                            enemySlots = enemySlots,
                            analysis = analysis,
                            isFirstPick = isFirstPick,
                            enemyLaneOpponent = enemyLaneOpponent,
                            onToggleFirstPick = { isFirstPick = !isFirstPick },
                            onChangeRole = { showRoleChangeDialog = true },
                            onAddMyChampion = {
                                suggestedPickingRole = activeRole
                                pickingForTeam = "MYSELF"
                            },
                            onRemoveMyChampion = {
                                val idx = allySlots.indexOfFirst { it.assignedRole == activeRole }
                                if (idx >= 0) allySlots.removeAt(idx)
                            },
                            onAddAlly = {
                                val freeRole = LaneRole.entries.firstOrNull { r -> !allySlots.any { it.assignedRole == r } }
                                suggestedPickingRole = freeRole
                                pickingForTeam = "ALLY"
                            },
                            onAddEnemy = {
                                val freeRole = LaneRole.entries.firstOrNull { r -> !enemySlots.any { it.assignedRole == r } }
                                suggestedPickingRole = freeRole
                                pickingForTeam = "ENEMY"
                            },
                            onRemoveAllySlot = { slot -> allySlots.remove(slot) },
                            onRemoveEnemySlot = { slot -> enemySlots.remove(slot) },
                            onChangeAllyRole = { slot, newRole ->
                                val idx = allySlots.indexOf(slot)
                                if (idx >= 0) {
                                    allySlots[idx] = slot.copy(assignedRole = newRole)
                                }
                            },
                            onChangeEnemyRole = { slot, newRole ->
                                val idx = enemySlots.indexOf(slot)
                                if (idx >= 0) {
                                    enemySlots[idx] = slot.copy(assignedRole = newRole)
                                }
                            },
                            onPickRecommendation = { champ ->
                                val existingIndex = allySlots.indexOfFirst { it.assignedRole == activeRole }
                                if (existingIndex >= 0) {
                                    allySlots[existingIndex] = DraftSlot(champ, activeRole)
                                } else {
                                    if (allySlots.size >= 5) {
                                        allySlots.removeAt(allySlots.size - 1)
                                    }
                                    allySlots.add(0, DraftSlot(champ, activeRole))
                                }
                            },
                            onSelectChampion = { selectedDetailChampion = it }
                        )
                    }
                    1 -> {
                        // SECCIÓN: CATÁLOGO DE CAMPEONES
                        ChampionsCatalogTab(
                            onSelectChampion = { selectedDetailChampion = it }
                        )
                    }
                    2 -> {
                        // SECCIÓN: TIER LIST OFICIAL WILD RIFT
                        TierListTab(
                            onSelectChampion = { selectedDetailChampion = it }
                        )
                    }
                    3 -> {
                        // SECCIÓN: OBJETOS (ITEMS) DE WILD RIFT
                        ItemsCatalogTab()
                    }
                    4 -> {
                        // SECCIÓN SEPARADA: RUNAS DE WILD RIFT
                        RunesTab()
                    }
                    5 -> {
                        // SECCIÓN SEPARADA: HECHIZOS DE INVOCADOR
                        SpellsTab()
                    }
                    6 -> {
                        // SECCIÓN: OBJETIVOS DE MAPA (MONSTRUOS ÉPICOS)
                        MapObjectivesTab()
                    }
                }
            }
        }
    }

    // Modal Champion Detail Sheet
    if (selectedDetailChampion != null) {
        ChampionDetailSheet(
            champion = selectedDetailChampion,
            onDismiss = { selectedDetailChampion = null }
        )
    }

    // Modal Champion Picker for Draft
    if (pickingForTeam != null) {
        DraftChampionPickerSheet(
            team = pickingForTeam!!,
            suggestedRole = suggestedPickingRole,
            alreadySelected = (allySlots + enemySlots).map { it.champion.id },
            onChampionPicked = { champ, chosenRole ->
                when (pickingForTeam) {
                    "MYSELF" -> {
                        val idx = allySlots.indexOfFirst { it.assignedRole == activeRole }
                        if (idx >= 0) {
                            allySlots[idx] = DraftSlot(champ, activeRole)
                        } else {
                            if (allySlots.size >= 5) allySlots.removeAt(allySlots.size - 1)
                            allySlots.add(0, DraftSlot(champ, activeRole))
                        }
                    }
                    "ALLY" -> {
                        if (allySlots.size < 5) {
                            allySlots.add(DraftSlot(champ, chosenRole))
                        }
                    }
                    "ENEMY" -> {
                        if (enemySlots.size < 5) {
                            enemySlots.add(DraftSlot(champ, chosenRole))
                        }
                    }
                }
                pickingForTeam = null
            },
            onDismiss = { pickingForTeam = null }
        )
    }

    // Role Switch Dialog
    if (showRoleChangeDialog) {
        RoleChangeBottomSheet(
            currentRole = activeRole,
            onRoleSelected = {
                activeRole = it
                showRoleChangeDialog = false
            },
            onDismiss = { showRoleChangeDialog = false }
        )
    }
}

// ====================================================================
// TAB 1: CATÁLOGO DE CAMPEONES (BUSCADOR, FILTROS, IMÁGENES Y METAS)
// ====================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChampionsCatalogTab(
    onSelectChampion: (Champion) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val syncState by ChineseMetaSyncService.syncState.collectAsStateWithLifecycle()
    val currentTier by ChineseMetaSyncService.currentTier.collectAsStateWithLifecycle()

    // Sincronización automática instantánea en cuanto el usuario entra al catálogo
    LaunchedEffect(Unit) {
        ChineseMetaSyncService.syncChineseMeta(context, currentTier, forceRefresh = true)
    }

    var searchQuery by remember { mutableStateOf("") }
    var selectedRoleFilter by remember { mutableStateOf<LaneRole?>(null) }
    var selectedTierFilter by remember { mutableStateOf<String?>(null) }
    var isGridView by remember { mutableStateOf(true) }

    val filteredChampions = remember(searchQuery, selectedRoleFilter, selectedTierFilter, syncState) {
        val list = WildRiftRepository.champions.filter { champ ->
            val matchesQuery = searchQuery.isBlank() ||
                    champ.name.contains(searchQuery, ignoreCase = true) ||
                    champ.title.contains(searchQuery, ignoreCase = true) ||
                    champ.summary.contains(searchQuery, ignoreCase = true) ||
                    champ.primaryRole.displayName.contains(searchQuery, ignoreCase = true) ||
                    champ.primaryRole.shortName.contains(searchQuery, ignoreCase = true) ||
                    champ.secondaryRoles.any { it.shortName.contains(searchQuery, ignoreCase = true) || it.displayName.contains(searchQuery, ignoreCase = true) }
            val matchesRole = selectedRoleFilter == null ||
                    champ.primaryRole == selectedRoleFilter ||
                    champ.secondaryRoles.contains(selectedRoleFilter)
            val matchesTier = selectedTierFilter == null || champ.tier == selectedTierFilter
            matchesQuery && matchesRole && matchesTier
        }
        if (selectedRoleFilter != null) {
            list.sortedWith(
                compareByDescending<Champion> { it.primaryRole == selectedRoleFilter }
                    .thenByDescending { it.tier == "S+" }
                    .thenByDescending { it.tier == "S" }
                    .thenByDescending { it.tier == "A+" }
                    .thenByDescending { it.winrate }
            )
        } else {
            list.sortedWith(
                compareByDescending<Champion> { it.tier == "S+" }
                    .thenByDescending { it.tier == "S" }
                    .thenByDescending { it.tier == "A+" }
                    .thenByDescending { it.winrate }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // PANEL DE ESTADÍSTICAS Y METAGAME OFICIAL EN VIVO
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            shape = RoundedCornerShape(12.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.TrendingUp, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text(
                                text = tr("Meta Oficial en Vivo"),
                                color = HextechGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = tr("Win Rate, Pick, Ban y Variación Diaria (Vs. Ayer)"),
                                color = HextechCyan,
                                fontSize = 10.sp
                            )
                        }
                    }

                    // Botón Sincronizar en Vivo
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                ChineseMetaSyncService.syncChineseMeta(context, currentTier, forceRefresh = true)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (syncState is ChineseSyncState.Syncing) HextechSurfaceVariant else HextechGold
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        enabled = syncState !is ChineseSyncState.Syncing,
                        modifier = Modifier.height(32.dp)
                    ) {
                        if (syncState is ChineseSyncState.Syncing) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(14.dp),
                                color = HextechCyan,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(tr("Sincronizando..."), color = HextechCyan, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        } else {
                            Icon(Icons.Default.Sync, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(tr("Sincronizar"), color = HextechDarkBg, fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Selector de Rango de Elo Oficial
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TencentRankTier.entries.forEach { tier ->
                        val isSelected = currentTier == tier
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(6.dp))
                                .background(
                                    if (isSelected) HextechCyan.copy(alpha = 0.25f) else HextechSurfaceVariant.copy(alpha = 0.4f)
                                )
                                .border(
                                    width = if (isSelected) 1.dp else 0.5.dp,
                                    color = if (isSelected) HextechCyan else HextechCardBorder,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .clickable {
                                    coroutineScope.launch {
                                        ChineseMetaSyncService.syncChineseMeta(context, tier, forceRefresh = true)
                                    }
                                }
                                .padding(vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tr(tier.shortName),
                                color = if (isSelected) HextechCyan else TextMuted,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                maxLines = 1
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Estado de sincronización instantánea en vivo
                val lastSyncInfo = remember(syncState) { ChineseMetaSyncService.getLastSyncInfo(context) }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = when (val s = syncState) {
                            is ChineseSyncState.Syncing -> tr("⚡ Sincronizando estadísticas oficiales...")
                            is ChineseSyncState.Success -> "${tr("🟢 En vivo:")} ${s.timestamp} (${tr(s.tier.displayName)})"
                            is ChineseSyncState.Error -> "${tr("⚠️ Datos en caché local:")} ${lastSyncInfo.second}"
                            ChineseSyncState.Idle -> "🟢 ${lastSyncInfo.second}"
                        },
                        color = when (syncState) {
                            is ChineseSyncState.Syncing -> HextechCyan
                            is ChineseSyncState.Success -> Color(0xFF4CAF50)
                            is ChineseSyncState.Error -> Color(0xFFFFA726)
                            ChineseSyncState.Idle -> TextMuted
                        },
                        fontSize = 9.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = tr("Instantáneo 24/7"),
                        color = HextechGold,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Total count
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F1522), RoundedCornerShape(8.dp))
                .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${filteredChampions.size} " + tr("Campeones"),
                color = HextechCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "${tr("Rango Activo:")} ${tr(currentTier.displayName)}",
                color = HextechGoldLight,
                fontSize = 10.sp
            )
        }


        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("champions_search_input"),
            placeholder = { Text(tr("Buscar campeón por nombre o habilidad..."), color = TextMuted, fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Limpiar", tint = TextMuted)
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

        // Role Filter Chips
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            FilterChip(
                selected = selectedRoleFilter == null,
                onClick = { selectedRoleFilter = null },
                label = { Text(tr("Todos los Roles"), fontSize = 11.5.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HextechCyan,
                    selectedLabelColor = HextechDarkBg
                )
            )
            LaneRole.entries.forEach { role ->
                FilterChip(
                    selected = selectedRoleFilter == role,
                    onClick = { selectedRoleFilter = if (selectedRoleFilter == role) null else role },
                    label = { Text(tr(role.shortName), fontSize = 11.5.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Champions List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredChampions) { champion ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .clickable { onSelectChampion(champion) }
                        .testTag("champion_item_${champion.id}"),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ChampionAvatar(champion = champion, size = 58.dp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = champion.name,
                                    color = TextPrimary,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    val winDelta = champion.winrateDelta
                                    val winDeltaText = if (winDelta >= 0) "+${winDelta}%" else "${winDelta}%"
                                    val winDeltaColor = if (winDelta >= 0) Color(0xFF4CAF50) else DangerRed
                                    Text(
                                        text = if (winDelta >= 0) "▲ $winDeltaText" else "▼ $winDeltaText",
                                        color = winDeltaColor,
                                        fontSize = 10.5.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "WR: ${champion.winrate}%",
                                        color = HextechGold,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            val roleFilter = selectedRoleFilter
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                if (roleFilter != null && champion.primaryRole != roleFilter) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(HextechGold.copy(alpha = 0.2f))
                                            .border(1.dp, HextechGold.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
                                            .padding(horizontal = 5.dp, vertical = 1.dp)
                                    ) {
                                        Text(
                                            text = "⭐ " + tr("Flex en ") + tr(roleFilter.shortName),
                                            color = HextechGold,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        text = "Principal: ${com.example.util.tr(champion.primaryRole.shortName)} • ${com.example.util.tr(champion.damageType.displayName)}",
                                        color = HextechCyan,
                                        fontSize = 11.sp
                                    )
                                } else {
                                    Text(
                                        text = "${com.example.util.tr(champion.primaryRole.displayName)} • ${com.example.util.tr(champion.damageType.displayName)}",
                                        color = HextechCyan,
                                        fontSize = 11.5.sp
                                    )
                                    if (champion.secondaryRoles.isNotEmpty()) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(HextechCyan.copy(alpha = 0.15f))
                                                .border(0.5.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                                                .padding(horizontal = 4.dp, vertical = 1.dp)
                                        ) {
                                            val lang = com.example.util.LocalLanguage.current
                                            Text(
                                                text = tr("Flex: ") + champion.secondaryRoles.joinToString("/") { com.example.util.translations[lang]?.get(it.shortName) ?: it.shortName },
                                                color = HextechCyan,
                                                fontSize = 9.5.sp,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = champion.summary,
                                color = TextMuted,
                                fontSize = 11.sp,
                                maxLines = 2,
                                lineHeight = 15.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            // Skill icons preview
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                champion.skills.take(5).forEach { skill ->
                                    AppAssetImage(
                                        url = skill.iconUrl,
                                        contentDescription = skill.name,
                                        fallbackText = skill.slot,
                                        modifier = Modifier.size(20.dp),
                                        borderColor = HextechCyan.copy(alpha = 0.6f),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Toca para ver build y runas",
                                    color = HextechGoldLight,
                                    fontSize = 10.5.sp
                                )
                            }
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

// ====================================================================
// TAB 2: TIER LIST OFICIAL WILD RIFT (POR LÍNEAS Y TIERS)
// ====================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TierListTab(
    onSelectChampion: (Champion) -> Unit
) {
    var selectedLane by remember { mutableStateOf<LaneRole?>(null) }

    val championsToDisplay = remember(selectedLane) {
        if (selectedLane == null) WildRiftRepository.champions
        else WildRiftRepository.getChampionsByRole(selectedLane!!)
    }

    val tierSPlus = championsToDisplay.filter { it.tier == "S+" }
    val tierS = championsToDisplay.filter { it.tier == "S" }
    val tierA = championsToDisplay.filter { it.tier == "A+" || it.tier == "A" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Role Filter
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            FilterChip(
                selected = selectedLane == null,
                onClick = { selectedLane = null },
                label = { Text(tr("Todas las Líneas"), fontSize = 11.5.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HextechCyan,
                    selectedLabelColor = HextechDarkBg
                )
            )
            LaneRole.entries.forEach { role ->
                FilterChip(
                    selected = selectedLane == role,
                    onClick = { selectedLane = if (selectedLane == role) null else role },
                    label = { Text(tr(role.shortName), fontSize = 11.5.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Tier S+
            if (tierSPlus.isNotEmpty()) {
                item {
                    TierSectionCard(
                        tierName = "TIER S+ (Dominantes / Prioridad Pick & Ban)",
                        tierColor = TierSPlusColor,
                        champions = tierSPlus,
                        onSelectChampion = onSelectChampion
                    )
                }
            }

            // Tier S
            if (tierS.isNotEmpty()) {
                item {
                    TierSectionCard(
                        tierName = "TIER S",
                        tierColor = TierSColor,
                        champions = tierS,
                        onSelectChampion = onSelectChampion
                    )
                }
            }

            // Tier A
            if (tierA.isNotEmpty()) {
                item {
                    TierSectionCard(
                        tierName = "TIER A (Opciones Sólidas y Balanceadas)",
                        tierColor = TierAColor,
                        champions = tierA,
                        onSelectChampion = onSelectChampion
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun TierSectionCard(
    tierName: String,
    tierColor: Color,
    champions: List<Champion>,
    onSelectChampion: (Champion) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, tierColor.copy(alpha = 0.8f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(tierColor)
                )
                Text(
                    text = tierName,
                    color = tierColor,
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                champions.forEach { champ ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurfaceVariant.copy(alpha = 0.6f))
                            .clickable { onSelectChampion(champ) }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ChampionAvatar(champion = champ, size = 44.dp, showTierBadge = false)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(champ.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text("${com.example.util.tr(champ.primaryRole.shortName)} • ${com.example.util.tr(champ.damageType.displayName)}", color = HextechCyan, fontSize = 11.sp)
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.End) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    val winDelta = champ.winrateDelta
                                    val winDeltaText = if (winDelta >= 0) "+${winDelta}%" else "${winDelta}%"
                                    val winDeltaColor = if (winDelta >= 0) Color(0xFF4CAF50) else DangerRed
                                    Text(
                                        text = if (winDelta >= 0) "▲ $winDeltaText" else "▼ $winDeltaText",
                                        color = winDeltaColor,
                                        fontSize = 9.5.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(tr("WR") + ": ${champ.winrate}%", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
                                }
                                Text("Pick: ${champ.pickRate}%", color = TextMuted, fontSize = 10.5.sp)
                            }
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
        }
    }
}

// ====================================================================
// TAB 3: CATÁLOGO DE OBJETOS (ITEMS) DE WILD RIFT
// ====================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ItemsCatalogTab() {
    var selectedCategory by remember { mutableStateOf<ItemCategory?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var isGridView by remember { mutableStateOf(true) }
    var itemForDetail by remember { mutableStateOf<WildRiftItem?>(null) }

    val allItems = WildRiftRepository.items
    val filteredItems = remember(selectedCategory, searchQuery) {
        allItems.filter { item ->
            val matchesCategory = selectedCategory == null || item.category == selectedCategory
            val matchesSearch = searchQuery.isBlank() ||
                    item.name.contains(searchQuery, ignoreCase = true) ||
                    item.stats.contains(searchQuery, ignoreCase = true) ||
                    item.passive.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // WR-Meta Database Status Banner & View Switcher
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F1522), RoundedCornerShape(8.dp))
                .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Empty or something else if needed. We can just remove the whole left side.
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${filteredItems.size} ${tr("Ítems")}",
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                // View mode toggle
                Row(
                    modifier = Modifier
                        .background(HextechSurface, RoundedCornerShape(6.dp))
                        .border(0.5.dp, HextechCardBorder, RoundedCornerShape(6.dp))
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isGridView) HextechCyan else Color.Transparent)
                            .clickable { isGridView = true }
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = tr("Cuadrícula"),
                            color = if (isGridView) HextechDarkBg else TextMuted,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (!isGridView) HextechCyan else Color.Transparent)
                            .clickable { isGridView = false }
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = tr("Detallado"),
                            color = if (!isGridView) HextechDarkBg else TextMuted,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(tr("Buscar objeto por nombre o estadísticas..."), color = TextMuted, fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear", tint = TextMuted)
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

        // Category Filter Chips
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            FilterChip(
                selected = selectedCategory == null,
                onClick = { selectedCategory = null },
                label = { Text("${tr("Todos")} (${allItems.size})", fontSize = 11.5.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HextechCyan,
                    selectedLabelColor = HextechDarkBg
                )
            )
            ItemCategory.entries.forEach { cat ->
                val count = allItems.count { it.category == cat }
                FilterChip(
                    selected = selectedCategory == cat,
                    onClick = { selectedCategory = if (selectedCategory == cat) null else cat },
                    label = { Text("${cat.iconEmoji} ${com.example.util.tr(cat.displayName)} ($count)", fontSize = 11.5.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // When "Todos" is selected and no search query, show grouped sections matching wr-meta
        val isGroupedView = selectedCategory == null && searchQuery.isBlank()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (isGroupedView) {
                ItemCategory.entries.forEach { category ->
                    val categoryItems = allItems.filter { it.category == category }
                    if (categoryItems.isNotEmpty()) {
                        item(key = "header_${category.name}") {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(HextechSurfaceVariant, RoundedCornerShape(8.dp))
                                    .border(1.dp, HextechGold.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(category.iconEmoji, fontSize = 14.sp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = tr(category.sectionTitle),
                                        color = HextechGoldLight,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.5.sp
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .background(HextechCyan.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "${categoryItems.size}",
                                        color = HextechCyan,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        if (isGridView) {
                            // Grid rows (3 items per row)
                            val chunkedItems = categoryItems.chunked(3)
                            items(chunkedItems) { rowItems ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    for (item in rowItems) {
                                        Box(modifier = Modifier.weight(1f)) {
                                            ItemGridCard(item = item, onClick = { itemForDetail = item })
                                        }
                                    }
                                    // Filler boxes for incomplete rows
                                    for (i in 0 until (3 - rowItems.size)) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        } else {
                            items(categoryItems) { item ->
                                ItemListCard(item = item, onClick = { itemForDetail = item })
                            }
                        }
                    }
                }
            } else {
                if (isGridView) {
                    val chunkedItems = filteredItems.chunked(3)
                    items(chunkedItems) { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            for (item in rowItems) {
                                Box(modifier = Modifier.weight(1f)) {
                                    ItemGridCard(item = item, onClick = { itemForDetail = item })
                                }
                            }
                            for (i in 0 until (3 - rowItems.size)) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                } else {
                    items(filteredItems) { item ->
                        ItemListCard(item = item, onClick = { itemForDetail = item })
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

    // Item Detail Modal Dialog
    itemForDetail?.let { item ->
        androidx.compose.ui.window.Dialog(onDismissRequest = { itemForDetail = null }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, HextechGold)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppAssetImage(
                        url = item.iconUrl,
                        contentDescription = item.name,
                        fallbackText = item.name,
                        modifier = Modifier.size(72.dp),
                        borderColor = HextechGold,
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = tr(item.name),
                        color = HextechGoldLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .background(HextechCyan.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "${item.category.iconEmoji} ${tr(item.category.displayName)}",
                                color = HextechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(HextechGold.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "🟡 ${item.goldCost} ${tr("Oro")}",
                                color = HextechGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    if (item.stats.isNotBlank()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = tr("Estadísticas:"),
                            color = HextechGoldLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = tr(item.stats),
                            color = TextPrimary,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    if (item.passive.isNotBlank()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = tr("Efecto / Pasiva:"),
                            color = HextechGoldLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = tr(item.passive),
                            color = TextMuted,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechCyan)
                            .clickable { itemForDetail = null }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(tr("Cerrar"), color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemGridCard(
    item: WildRiftItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppAssetImage(
                url = item.iconUrl,
                contentDescription = item.name,
                fallbackText = item.name,
                modifier = Modifier.size(52.dp),
                borderColor = HextechGold.copy(alpha = 0.7f),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = tr(item.name),
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 13.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFF141926), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "${item.goldCost} G",
                    color = HextechGold,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun ItemListCard(
    item: WildRiftItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            AppAssetImage(
                url = item.iconUrl,
                contentDescription = item.name,
                fallbackText = item.name,
                modifier = Modifier.size(52.dp),
                borderColor = HextechGold,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(tr(item.name), color = HextechGoldLight, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("🟡 ${item.goldCost} G", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 11.5.sp)
                }
                Text("${item.category.iconEmoji} ${tr(item.category.displayName)}", color = HextechCyan, fontSize = 11.sp)
                if (item.stats.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(tr(item.stats), color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
                if (item.passive.isNotBlank()) {
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(tr(item.passive), color = TextMuted, fontSize = 11.5.sp, lineHeight = 15.sp, maxLines = 3, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

// ====================================================================
// TAB 4: CATÁLOGO EXCLUSIVO DE RUNAS (BÚSQUEDA Y RAMAS)
// ====================================================================
// TAB 4: CATÁLOGO EXCLUSIVO DE RUNAS
// ====================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RunesTab() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("TODOS") }
    var isGridView by remember { mutableStateOf(true) }
    var selectedRune by remember { mutableStateOf<RuneItem?>(null) }

    val filterOptions = listOf(
        "TODOS" to tr("Todos"),
        "KEYSTONE" to tr("Runas Clave"),
        "SORCERY" to tr("Brujería"),
        "DOMINATION" to tr("Dominación"),
        "PRECISION" to tr("Precisión"),
        "RESOLVE" to tr("Valor"),
        "INSPIRATION" to tr("Inspiración")
    )

    val filteredRunes = remember(searchQuery, selectedFilter) {
        WildRiftRepository.runes.filter { rune ->
            val matchesCategory = when (selectedFilter) {
                "TODOS" -> true
                "KEYSTONE" -> rune.category.contains("Clave", ignoreCase = true) || rune.category.contains("Keystone", ignoreCase = true)
                "SORCERY" -> rune.category.contains("Brujería", ignoreCase = true) || rune.category.contains("Sorcery", ignoreCase = true)
                "DOMINATION" -> rune.category.contains("Dominación", ignoreCase = true) || rune.category.contains("Domination", ignoreCase = true)
                "PRECISION" -> rune.category.contains("Precisión", ignoreCase = true) || rune.category.contains("Precision", ignoreCase = true)
                "RESOLVE" -> rune.category.contains("Valor", ignoreCase = true) || rune.category.contains("Resolve", ignoreCase = true)
                "INSPIRATION" -> rune.category.contains("Inspiración", ignoreCase = true) || rune.category.contains("Inspiration", ignoreCase = true)
                else -> true
            }
            val matchesSearch = searchQuery.isBlank() ||
                    rune.name.contains(searchQuery, ignoreCase = true) ||
                    rune.description.contains(searchQuery, ignoreCase = true) ||
                    rune.category.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    val treeCategories = remember(filteredRunes) {
        listOf("Runa Clave", "Brujería", "Dominación", "Precisión", "Valor", "Inspiración")
            .mapNotNull { cat ->
                val list = filteredRunes.filter { it.category.equals(cat, ignoreCase = true) }
                if (list.isNotEmpty()) cat to list else null
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        // Status & View Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F1522), RoundedCornerShape(8.dp))
                .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${filteredRunes.size} " + tr("Runas Oficiales"),
                color = HextechCyan,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.SemiBold
            )
            
            // View mode toggle
            Row(
                modifier = Modifier
                    .background(HextechSurface, RoundedCornerShape(6.dp))
                    .border(0.5.dp, HextechCardBorder, RoundedCornerShape(6.dp))
                    .padding(2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isGridView) HextechCyan else Color.Transparent)
                        .clickable { isGridView = true }
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = tr("Árbol / Grid"),
                        color = if (isGridView) HextechDarkBg else TextMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (!isGridView) HextechCyan else Color.Transparent)
                        .clickable { isGridView = false }
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = tr("Detallado"),
                        color = if (!isGridView) HextechDarkBg else TextMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(tr("Buscar runa (ej. Conquistador, Banda de Flujo)..."), color = TextMuted, fontSize = 12.5.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = tr("Cerrar"), tint = TextMuted)
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

        // Category Filter Chips
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            filterOptions.forEach { (key, label) ->
                FilterChip(
                    selected = selectedFilter == key,
                    onClick = { selectedFilter = key },
                    label = { Text(label, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (isGridView) {
            // GRID / TREE VIEW LIKE WR-META
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                treeCategories.forEach { (categoryName, runesInCat) ->
                    item(key = categoryName) {
                        val (iconPrefix, catColor) = when (categoryName.lowercase()) {
                            "brujería" -> "🌀" to Color(0xFF6C75F0)
                            "runa clave" -> "💎" to HextechGold
                            "dominación" -> "🗡️" to Color(0xFFE84057)
                            "precisión" -> "⚔️" to Color(0xFFF3C258)
                            "valor" -> "🛡️" to Color(0xFF4AC27E)
                            else -> "🔮" to HextechCyan
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.95f)),
                            border = androidx.compose.foundation.BorderStroke(1.dp, catColor.copy(alpha = 0.35f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                ) {
                                    Text(iconPrefix, fontSize = 14.sp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = categoryName.uppercase(),
                                        color = catColor,
                                        fontWeight = FontWeight.Black,
                                        fontSize = 13.5.sp,
                                        letterSpacing = 1.sp
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "(${runesInCat.size})",
                                        color = TextMuted,
                                        fontSize = 11.sp
                                    )
                                }

                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    runesInCat.forEach { rune ->
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .width(68.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .clickable { selectedRune = rune }
                                                .background(Color(0xFF0C1322).copy(alpha = 0.6f))
                                                .border(0.5.dp, HextechCardBorder.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                                                .padding(6.dp)
                                        ) {
                                            AppAssetImage(
                                                url = rune.iconUrl,
                                                contentDescription = rune.name,
                                                fallbackText = rune.name,
                                                modifier = Modifier.size(42.dp),
                                                borderColor = catColor,
                                                shape = CircleShape
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = rune.name,
                                                color = TextPrimary,
                                                fontSize = 9.5.sp,
                                                fontWeight = FontWeight.Medium,
                                                maxLines = 2,
                                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                                lineHeight = 11.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        } else {
            // DETAILED LIST VIEW
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredRunes) { rune ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedRune = rune },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            AppAssetImage(
                                url = rune.iconUrl,
                                contentDescription = rune.name,
                                fallbackText = rune.name,
                                modifier = Modifier.size(44.dp),
                                borderColor = HextechCyan,
                                shape = CircleShape
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(rune.name, color = HextechGoldLight, fontWeight = FontWeight.Bold, fontSize = 13.5.sp)
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(HextechCyan.copy(alpha = 0.12f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(rune.category, color = HextechCyan, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(rune.description, color = TextPrimary.copy(alpha = 0.9f), fontSize = 12.sp, lineHeight = 16.sp)
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }

    // Rune Detail Dialog
    selectedRune?.let { rune ->
        AlertDialog(
            onDismissRequest = { selectedRune = null },
            containerColor = HextechSurface,
            shape = RoundedCornerShape(16.dp),
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppAssetImage(
                        url = rune.iconUrl,
                        contentDescription = rune.name,
                        fallbackText = rune.name,
                        modifier = Modifier.size(48.dp),
                        borderColor = HextechGold,
                        shape = CircleShape
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = rune.name,
                            color = HextechGoldLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Rama: ${rune.category}",
                            color = HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0C1322), RoundedCornerShape(8.dp))
                            .border(0.5.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = rune.description,
                            color = TextPrimary,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "💡 Consejo del Coach:",
                        color = HextechGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = when (rune.category.lowercase()) {
                            "brujería" -> "Excelente para magos, soportes de utilidad y campeones que requieren aceleración de habilidades y maná continuo en fase de líneas."
                            "runa clave" -> "Pilar fundamental de tu estilo de juego. Elige según busques intercambios cortos (Electrocutar/Primer Golpe) o peleas largas (Conquistador/Cadencia Letal)."
                            "dominación" -> "Ideal para amplificar daño explosivo, penetración rápida y acumulación de daño por bajas o visión."
                            "precisión" -> "Máxima eficiencia en tiradores (ADC) y duelistas para potenciar daño sostenido, velocidad de ataque y remate de objetivos."
                            "valor" -> "Imprescindible para tanques y luchadores contra líneas difíciles para absorber daño y escalar vida máxima."
                            else -> "Aporta versatilidad, aceleración de hechizos de invocador y movilidad táctica por el mapa."
                        },
                        color = TextMuted,
                        fontSize = 11.5.sp,
                        lineHeight = 16.sp
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { selectedRune = null },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan, contentColor = HextechDarkBg),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(tr("Cerrar"), fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}


// ====================================================================
// TAB 5: CATÁLOGO EXCLUSIVO DE HECHIZOS DE INVOCADOR
// ====================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SpellsTab() {
    var searchQuery by remember { mutableStateOf("") }
    var isGridView by remember { mutableStateOf(true) }
    var selectedFilter by remember { mutableStateOf("TODOS") }
    var selectedSpell by remember { mutableStateOf<SummonerSpellItem?>(null) }

    val filterOptions = listOf(
        "TODOS" to tr("Todos"),
        "COMBAT" to tr("Combate & Daño"),
        "UTILITY" to tr("Movilidad & Utilidad")
    )

    val filteredSpells = remember(searchQuery, selectedFilter) {
        WildRiftRepository.summonerSpells.filter { spell ->
            val matchesFilter = when (selectedFilter) {
                "TODOS" -> true
                "COMBAT" -> spell.name.contains("Prender", true) || 
                            spell.name.contains("Ignición", true) || 
                            spell.name.contains("Castigo", true) || 
                            spell.name.contains("Extenuación", true) || 
                            spell.name.contains("Curar", true) || 
                            spell.name.contains("Barrera", true)
                "UTILITY" -> spell.name.contains("Destello", true) || 
                             spell.name.contains("Fantasma", true) || 
                             spell.name.contains("Teleport", true) ||
                             spell.name.contains("Claridad", true) ||
                             spell.name.contains("Marca", true)
                else -> true
            }
            val matchesSearch = searchQuery.isBlank() ||
                    spell.name.contains(searchQuery, ignoreCase = true) ||
                    spell.description.contains(searchQuery, ignoreCase = true)
            matchesFilter && matchesSearch
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        // Status Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F1522), RoundedCornerShape(8.dp))
                .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${filteredSpells.size} " + tr("Hechizos de Invocador"),
                color = HextechGold,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.SemiBold
            )
            
            // View mode toggle
            Row(
                modifier = Modifier
                    .background(HextechSurface, RoundedCornerShape(6.dp))
                    .border(0.5.dp, HextechCardBorder, RoundedCornerShape(6.dp))
                    .padding(2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isGridView) HextechGold else Color.Transparent)
                        .clickable { isGridView = true }
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = tr("Cuadrícula"),
                        color = if (isGridView) HextechDarkBg else TextMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (!isGridView) HextechGold else Color.Transparent)
                        .clickable { isGridView = false }
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = tr("Detallado"),
                        color = if (!isGridView) HextechDarkBg else TextMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(tr("Buscar hechizo (ej. Destello, Prender, Castigo)..."), color = TextMuted, fontSize = 12.5.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechGold) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = tr("Cerrar"), tint = TextMuted)
                    }
                }
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = HextechGold,
                unfocusedBorderColor = HextechCardBorder,
                focusedContainerColor = HextechSurface,
                unfocusedContainerColor = HextechSurface
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Filter Chips
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            filterOptions.forEach { (key, label) ->
                FilterChip(
                    selected = selectedFilter == key,
                    onClick = { selectedFilter = key },
                    label = { Text(label, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechGold,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (isGridView) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        filteredSpells.forEach { spell ->
                            Card(
                                modifier = Modifier
                                    .width(105.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { selectedSpell = spell },
                                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    AppAssetImage(
                                        url = spell.iconUrl,
                                        contentDescription = spell.name,
                                        fallbackText = spell.name,
                                        modifier = Modifier.size(50.dp),
                                        borderColor = HextechGold,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = spell.name,
                                        color = HextechGoldLight,
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                        maxLines = 1
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "CD ${spell.cooldown}",
                                        color = HextechCyan,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredSpells) { spell ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedSpell = spell },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            AppAssetImage(
                                url = spell.iconUrl,
                                contentDescription = spell.name,
                                fallbackText = spell.name,
                                modifier = Modifier.size(44.dp),
                                borderColor = HextechGold,
                                shape = RoundedCornerShape(8.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(spell.name, color = HextechGoldLight, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(HextechGold.copy(alpha = 0.15f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text("CD: ${spell.cooldown}", color = HextechGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(spell.description, color = TextPrimary.copy(alpha = 0.9f), fontSize = 12.sp, lineHeight = 16.sp)
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }

    // Spell Detail Dialog
    selectedSpell?.let { spell ->
        AlertDialog(
            onDismissRequest = { selectedSpell = null },
            containerColor = HextechSurface,
            shape = RoundedCornerShape(16.dp),
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppAssetImage(
                        url = spell.iconUrl,
                        contentDescription = spell.name,
                        fallbackText = spell.name,
                        modifier = Modifier.size(48.dp),
                        borderColor = HextechGold,
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = spell.name,
                            color = HextechGoldLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Enfriamiento: ${spell.cooldown}",
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0C1322), RoundedCornerShape(8.dp))
                            .border(0.5.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = spell.description,
                            color = TextPrimary,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "💡 Recomendación de Invocador:",
                        color = HextechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = when (spell.id) {
                            "flash" -> "Imprescindible en el 99% de las partidas para reposicionarse, iniciar peleas de equipo o escapar por encima de muros."
                            "ignite" -> "Clave para asesinos y soportes agresivos para asegurar asesinatos en juego temprano y anular curaciones de campeones como Aatrox, Soraka o Dr. Mundo."
                            "smite" -> "Obligatorio para el rol de Jungla para asegurar monstruos épicos (Dragones, Heraldo, Barón) y farmear eficientemente."
                            "exhaust" -> "Vital para neutralizar a hipercarries o asesinos rivales en peleas grupales reduciendo su daño y movilidad drásticamente."
                            "barrier" -> "Excelente para tiradores o magos de ráfaga para resistir emboscadas o burst sorpresa en línea."
                            "ghost" -> "Ideal para campeones con movilidad continua como Darius, Olaf, Singed o Gwen para evitar que los enemigos escapen."
                            "teleport" -> "Potente para campeones de carril de Barón para mantener presión dividida y unirse inmediatamente a peleas de objetivos."
                            else -> "Uso situacional según la composición y mapa."
                        },
                        color = TextMuted,
                        fontSize = 11.5.sp,
                        lineHeight = 16.sp
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { selectedSpell = null },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(tr("Cerrar"), fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}

// ====================================================================
// TAB 5: OBJETIVOS DE MAPA (MONSTRUOS ÉPICOS DE WILD RIFT)
// ====================================================================
@Composable
private fun MapObjectivesTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(tr("Monstruos Épicos & Tiempos de Aparición"), color = HextechGold, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Text(tr("Conocer los tiempos exactos de aparición en Wild Rift asegura la victoria de tu equipo:"), color = TextMuted, fontSize = 11.5.sp)
        Spacer(modifier = Modifier.height(10.dp))

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            WildRiftRepository.mapObjectives.forEach { obj ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(obj.name, color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 14.5.sp)
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechCyan.copy(alpha = 0.15f))
                                    .border(1.dp, HextechCyan.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(obj.spawnTime, color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 11.5.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(tr("Reaparición:") + " ${obj.respawnTime}", color = TextMuted, fontSize = 11.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(tr("Mejora:") + " ${obj.buffDescription}", color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(tr("Táctica:") + " ${obj.tactics}", color = HextechGoldLight.copy(alpha = 0.9f), fontSize = 11.5.sp, lineHeight = 15.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

// ====================================================================
// TAB 0: ANÁLISIS DE DRAFTING & COUNTERS
// ====================================================================
@Composable
private fun DraftAnalysisTab(
    myChampion: Champion?,
    activeRole: LaneRole,
    allySlots: List<DraftSlot>,
    enemySlots: List<DraftSlot>,
    analysis: DraftAnalysisResult,
    isFirstPick: Boolean,
    enemyLaneOpponent: Champion?,
    onToggleFirstPick: () -> Unit,
    onChangeRole: () -> Unit,
    onAddMyChampion: () -> Unit,
    onRemoveMyChampion: () -> Unit,
    onAddAlly: () -> Unit,
    onAddEnemy: () -> Unit,
    onRemoveAllySlot: (DraftSlot) -> Unit,
    onRemoveEnemySlot: (DraftSlot) -> Unit,
    onChangeAllyRole: (DraftSlot, LaneRole) -> Unit,
    onChangeEnemyRole: (DraftSlot, LaneRole) -> Unit,
    onPickRecommendation: (Champion) -> Unit,
    onSelectChampion: (Champion) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Role active pill & First Pick Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Role active pill
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechGold, RoundedCornerShape(12.dp))
                    .clickable { onChangeRole() }
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Shield, contentDescription = null, tint = HextechGold, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text(tr("Mi Línea:"), color = TextMuted, fontSize = 10.5.sp)
                        Text(com.example.util.tr(activeRole.displayName), color = HextechGold, fontSize = 12.5.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Text(tr("Cambiar"), color = HextechCyan, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            }

            // First Pick / Blind Pick Mode Switch Pill
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isFirstPick) HextechGold.copy(alpha = 0.18f) else HextechSurface)
                    .border(1.dp, if (isFirstPick) HextechGold else HextechCardBorder, RoundedCornerShape(12.dp))
                    .clickable { onToggleFirstPick() }
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(tr("1er Pick"), color = if (isFirstPick) HextechGold else TextMuted, fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
                    Text(if (isFirstPick) tr("Blind Pick") else tr("Counter"), color = if (isFirstPick) HextechCyan else TextMuted, fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.width(4.dp))
                Switch(
                    checked = isFirstPick,
                    onCheckedChange = { onToggleFirstPick() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = HextechGold,
                        checkedTrackColor = HextechGold.copy(alpha = 0.35f),
                        uncheckedThumbColor = TextMuted,
                        uncheckedTrackColor = HextechSurfaceVariant
                    ),
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Team Drafting Slots (Allies & Enemies con roles y slots editables)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Allies Column
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tr("Equipo Aliado") + " (${allySlots.size}/5)",
                    color = AllyBlue,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                allySlots.forEach { slot ->
                    val isMyPick = slot.assignedRole == activeRole
                    TeamChampionSlot(
                        slot = slot,
                        isEnemy = false,
                        isMyPick = isMyPick,
                        onRoleChanged = { newRole -> onChangeAllyRole(slot, newRole) },
                        onRemove = { onRemoveAllySlot(slot) },
                        onClick = { onSelectChampion(slot.champion) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                if (allySlots.size < 5) {
                    AddChampionSlotButton(isEnemy = false, onClick = onAddAlly)
                }
            }

            // Enemies Column
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tr("Equipo Rival") + " (${enemySlots.size}/5)",
                    color = DangerRed,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                enemySlots.forEach { slot ->
                    TeamChampionSlot(
                        slot = slot,
                        isEnemy = true,
                        isMyPick = false,
                        onRoleChanged = { newRole -> onChangeEnemyRole(slot, newRole) },
                        onRemove = { onRemoveEnemySlot(slot) },
                        onClick = { onSelectChampion(slot.champion) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                if (enemySlots.size < 5) {
                    AddChampionSlotButton(isEnemy = true, onClick = onAddEnemy)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Warnings & Matchup Directo
        if (analysis.directMatchupWarning != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = DangerRedSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, DangerRed)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = DangerRed, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(tr("Alerta Táctica de Matchup"), color = DangerRed, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        Text(analysis.directMatchupWarning, color = TextPrimary, fontSize = 12.sp, lineHeight = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Damage distribution
        if (enemySlots.isNotEmpty()) {
            Text(tr("Balance de Daño Rival"), color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp))) {
                if (analysis.physicalDamagePercent > 0) {
                    Box(modifier = Modifier.weight(analysis.physicalDamagePercent.toFloat()).fillMaxHeight().background(Color(0xFFE57373)))
                }
                if (analysis.magicDamagePercent > 0) {
                    Box(modifier = Modifier.weight(analysis.magicDamagePercent.toFloat()).fillMaxHeight().background(Color(0xFF64B5F6)))
                }
                if (analysis.trueDamagePercent > 0) {
                    Box(modifier = Modifier.weight(analysis.trueDamagePercent.toFloat()).fillMaxHeight().background(Color.White))
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("${analysis.physicalDamagePercent}% " + tr("Físico"), color = Color(0xFFE57373), fontSize = 10.sp)
                Text("${analysis.magicDamagePercent}% " + tr("Mágico"), color = Color(0xFF64B5F6), fontSize = 10.sp)
                Text("${analysis.trueDamagePercent}% " + tr("Verdadero"), color = Color.White, fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.height(14.dp))
        }

        // My Champion Evaluation (Sincronizado automáticamente sin selecciones duplicadas)
        if (myChampion != null) {
            val myChamp = myChampion
            val myEval = WildRiftRepository.evaluateChampion(
                champ = myChamp,
                myRole = activeRole,
                allies = allySlots.map { it.champion },
                enemies = enemySlots.map { it.champion },
                enemyLaneOpponent = enemyLaneOpponent,
                lang = "es"
            )
            val shouldChange = myEval.estimatedWinrate < 49.0 || myEval.advantageBadge.contains("PELIGRO")
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.5.dp, if (shouldChange) DangerRed else HextechGold, RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = tr("TU ELECCIÓN EN") + " ${com.example.util.tr(activeRole.displayName).uppercase()}",
                            color = if (shouldChange) DangerRed else HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = tr("Winrate Est.:") + " ${myEval.estimatedWinrate}%",
                            color = if (shouldChange) DangerRed else HextechCyan,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ChampionAvatar(champion = myEval.champion, size = 50.dp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(myEval.champion.name, color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Tier ${myEval.champion.tier}",
                                    color = HextechGold,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = if (shouldChange) tr("⚠️ Considera cambiarlo") else tr("✅ Buena elección para tu línea"),
                                color = if (shouldChange) DangerRed else Color(0xFF81C784),
                                fontSize = 12.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        IconButton(onClick = onRemoveMyChampion, modifier = Modifier.size(28.dp)) {
                            Icon(Icons.Default.Close, contentDescription = tr("Eliminar"), tint = TextMuted, modifier = Modifier.size(18.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(myEval.tacticalReason, color = TextPrimary.copy(alpha = 0.9f), fontSize = 12.sp, lineHeight = 16.sp)
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = tr("🔮 Runas:") + " ${myEval.champion.recommendedRunes} • " + tr("Toca para ver build completa"),
                        color = HextechGoldLight,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        } else {
            Button(
                onClick = onAddMyChampion,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = HextechGold.copy(alpha = 0.15f), contentColor = HextechGold),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = tr("SELECCIONAR MI PICK PARA") + " ${com.example.util.tr(activeRole.displayName).uppercase()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Live Recommendations Header
        Text(
            text = if (isFirstPick) tr("★ Mejor Primer Pick Seguro para") + " ${com.example.util.tr(activeRole.displayName)}" else tr("★ Mejor Opción según tu Equipo y el Rival"),
            color = HextechGold,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // #1 Best Pick Hero Card
        val topPick = analysis.bestOverallPick ?: analysis.recommendations.firstOrNull()
        if (topPick != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { onSelectChampion(topPick.champion) }
                    .border(1.5.dp, HextechGold, RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (isFirstPick) tr("👑 #1 RECOMENDACIÓN BLIND PICK") else tr("👑 #1 MEJOR ELECCIÓN TÁCTICA"),
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = tr("Winrate Est.:") + " ${topPick.estimatedWinrate}%",
                            color = HextechCyan,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ChampionAvatar(champion = topPick.champion, size = 56.dp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = topPick.champion.name,
                                    color = TextPrimary,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Tier ${topPick.champion.tier}",
                                    color = TierSPlusColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = topPick.advantageBadge,
                                color = HextechCyan,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = topPick.tacticalReason,
                        color = TextPrimary.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = tr("🔮 Runas:") + " ${topPick.champion.recommendedRunes}",
                            color = HextechGoldLight,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = { onPickRecommendation(topPick.champion) },
                            colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(tr("Elegir como mi Pick"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Secondary Recommendations
        val otherRecs = analysis.recommendations.filter { it.champion.id != topPick?.champion?.id }
        if (otherRecs.isNotEmpty()) {
            Text(
                text = tr("Otras Opciones Viables para") + " ${com.example.util.tr(activeRole.displayName)}:",
                color = HextechCyan,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))

            otherRecs.forEach { rec ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onSelectChampion(rec.champion) },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ChampionAvatar(champion = rec.champion, size = 46.dp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(rec.champion.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text("WR: ${rec.estimatedWinrate}%", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                            }
                            Text(rec.advantageBadge, color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(rec.tacticalReason, color = TextMuted, fontSize = 11.sp, lineHeight = 14.sp)
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        IconButton(
                            onClick = { onPickRecommendation(rec.champion) },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = tr("Elegir como mi Pick"),
                                tint = HextechCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun TeamChampionSlot(
    slot: DraftSlot,
    isEnemy: Boolean,
    isMyPick: Boolean = false,
    onRoleChanged: (LaneRole) -> Unit,
    onRemove: () -> Unit,
    onClick: () -> Unit
) {
    var showRoleMenu by remember { mutableStateOf(false) }
    val isOffMeta = slot.assignedRole != slot.champion.primaryRole && !slot.champion.secondaryRoles.contains(slot.assignedRole)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isMyPick) HextechGold.copy(alpha = 0.12f) else HextechSurface
        ),
        border = androidx.compose.foundation.BorderStroke(
            if (isMyPick) 1.5.dp else 1.dp,
            when {
                isMyPick -> HextechGold
                isEnemy -> DangerRed.copy(alpha = 0.7f)
                else -> AllyBlue.copy(alpha = 0.7f)
            }
        )
    ) {
        Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                ChampionAvatar(champion = slot.champion, size = 38.dp, showTierBadge = false)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = slot.champion.name,
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (isMyPick) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "👑 TÚ",
                                color = HextechGold,
                                fontSize = 9.5.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    
                    // Chip interactivo para cambiar la línea asignada de este campeón
                    Box {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    if (isMyPick) HextechGold.copy(alpha = 0.2f)
                                    else if (isEnemy) DangerRed.copy(alpha = 0.15f)
                                    else AllyBlue.copy(alpha = 0.15f)
                                )
                                .clickable { showRoleMenu = true }
                                .padding(horizontal = 5.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val roleLabel = com.example.util.tr(slot.assignedRole.shortName) +
                                    if (isOffMeta) " [${com.example.util.tr(slot.champion.primaryRole.shortName)}]" else ""
                            Text(
                                text = roleLabel,
                                color = if (isMyPick) HextechGold else if (isEnemy) DangerRed else AllyBlue,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = tr("Cambiar línea"),
                                tint = if (isMyPick) HextechGold else if (isEnemy) DangerRed else AllyBlue,
                                modifier = Modifier.size(14.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = showRoleMenu,
                            onDismissRequest = { showRoleMenu = false },
                            modifier = Modifier.background(HextechSurfaceVariant)
                        ) {
                            LaneRole.entries.forEach { role ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = com.example.util.tr(role.displayName),
                                            color = if (role == slot.assignedRole) HextechCyan else TextPrimary,
                                            fontWeight = if (role == slot.assignedRole) FontWeight.Bold else FontWeight.Normal,
                                            fontSize = 12.sp
                                        )
                                    },
                                    onClick = {
                                        onRoleChanged(role)
                                        showRoleMenu = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            IconButton(onClick = onRemove, modifier = Modifier.size(24.dp)) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = tr("Eliminar"),
                    tint = TextMuted,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun AddChampionSlotButton(
    isEnemy: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(HextechSurface)
            .border(
                1.dp,
                if (isEnemy) DangerRed.copy(alpha = 0.4f) else AllyBlue.copy(alpha = 0.4f),
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Add, contentDescription = null, tint = if (isEnemy) DangerRed else AllyBlue, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(tr("Añadir"), color = if (isEnemy) DangerRed else AllyBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun DraftChampionPickerSheet(
    team: String,
    suggestedRole: LaneRole?,
    alreadySelected: List<String>,
    onChampionPicked: (Champion, LaneRole) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var search by remember { mutableStateOf("") }
    var selectedRoleFilter by remember { mutableStateOf<LaneRole?>(suggestedRole) }

    val availableChamps = remember(search, alreadySelected, selectedRoleFilter) {
        val list = WildRiftRepository.champions.filter { champ ->
            val notSelected = !alreadySelected.contains(champ.id)
            val matchesQuery = search.isBlank() ||
                    champ.name.contains(search, ignoreCase = true) ||
                    champ.summary.contains(search, ignoreCase = true)
            val matchesRole = selectedRoleFilter == null ||
                    champ.primaryRole == selectedRoleFilter ||
                    champ.secondaryRoles.contains(selectedRoleFilter)
            notSelected && matchesQuery && matchesRole
        }
        if (selectedRoleFilter != null) {
            list.sortedWith(
                compareByDescending<Champion> { it.primaryRole == selectedRoleFilter }
                    .thenByDescending { it.tier == "S+" }
                    .thenByDescending { it.tier == "S" }
                    .thenByDescending { it.winrate }
            )
        } else {
            list.sortedWith(
                compareByDescending<Champion> { it.tier == "S+" }
                    .thenByDescending { it.tier == "S" }
                    .thenByDescending { it.winrate }
            )
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechSurfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = when (team) {
                    "MYSELF" -> tr("Seleccionar Mi Campeón") + if (suggestedRole != null) " (${com.example.util.tr(suggestedRole.displayName)})" else ""
                    "ALLY" -> tr("Seleccionar Campeón Aliado")
                    else -> tr("Seleccionar Campeón Rival")
                },
                color = when (team) {
                    "MYSELF" -> HextechGold
                    "ALLY" -> AllyBlue
                    else -> DangerRed
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(tr("Buscar campeón..."), color = TextMuted, fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Quick Role Filters
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                FilterChip(
                    selected = selectedRoleFilter == null,
                    onClick = { selectedRoleFilter = null },
                    label = { Text(tr("Todos"), fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
                LaneRole.entries.forEach { role ->
                    FilterChip(
                        selected = selectedRoleFilter == role,
                        onClick = { selectedRoleFilter = if (selectedRoleFilter == role) null else role },
                        label = { Text(com.example.util.tr(role.shortName), fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = HextechCyan,
                            selectedLabelColor = HextechDarkBg
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 64.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(availableChamps) { champ ->
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                val assignedRole = selectedRoleFilter ?: suggestedRole ?: champ.primaryRole
                                onChampionPicked(champ, assignedRole)
                            }
                            .padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ChampionAvatar(champion = champ, size = 56.dp, showTierBadge = false)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = champ.name,
                            color = TextPrimary,
                            fontSize = 10.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RoleChangeBottomSheet(
    currentRole: LaneRole,
    onRoleSelected: (LaneRole) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechSurfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(tr("Selecciona tu Línea para esta Partida"), color = HextechGold, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            LaneRole.entries.forEach { role ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (role == currentRole) HextechCyan.copy(alpha = 0.2f) else HextechSurface)
                        .border(1.dp, if (role == currentRole) HextechCyan else HextechCardBorder, RoundedCornerShape(10.dp))
                        .clickable { onRoleSelected(role) }
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(com.example.util.tr(role.displayName), color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    if (role == currentRole) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = HextechCyan)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ChampionGridCard(
    champion: Champion,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("champion_item_${champion.id}"),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChampionAvatar(champion = champion, size = 52.dp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = champion.name,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 13.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFF141926), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "WR: ${champion.winrate}%",
                    color = HextechGold,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun RuneGridCard(
    rune: RuneItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppAssetImage(
                url = rune.iconUrl,
                contentDescription = rune.name,
                fallbackText = rune.name,
                modifier = Modifier.size(52.dp),
                shape = CircleShape,
                borderColor = HextechGold.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = tr(rune.name),
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 13.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFF141926), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = tr(rune.category),
                    color = HextechCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SpellGridCard(
    spell: SummonerSpellItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppAssetImage(
                url = spell.iconUrl,
                contentDescription = spell.name,
                fallbackText = spell.name,
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(8.dp),
                borderColor = HextechGold.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = tr(spell.name),
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 13.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFF141926), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = spell.cooldown,
                    color = HextechGold,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
