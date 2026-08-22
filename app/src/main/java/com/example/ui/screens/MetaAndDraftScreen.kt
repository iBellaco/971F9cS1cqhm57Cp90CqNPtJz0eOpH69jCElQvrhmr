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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.DamageType
import com.example.model.DraftAnalysisResult
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

    // Draft State
    val allyChampions = remember {
        mutableStateListOf(
            WildRiftRepository.getChampionById("chogath") ?: WildRiftRepository.champions[8],
            WildRiftRepository.getChampionById("vayne") ?: WildRiftRepository.champions[3],
            WildRiftRepository.getChampionById("janna") ?: WildRiftRepository.champions[4],
            WildRiftRepository.getChampionById("viego") ?: WildRiftRepository.champions[1]
        )
    }

    val enemyChampions = remember {
        mutableStateListOf(
            WildRiftRepository.getChampionById("sett") ?: WildRiftRepository.champions[2],
            WildRiftRepository.getChampionById("vi") ?: WildRiftRepository.champions[7],
            WildRiftRepository.getChampionById("caitlyn") ?: WildRiftRepository.champions[6],
            WildRiftRepository.getChampionById("nautilus") ?: WildRiftRepository.champions[5]
        )
    }

    // Modal Champion Picker & Detail State
    var pickingForTeam by remember { mutableStateOf<String?>(null) } // "ALLY", "ENEMY"
    var selectedDetailChampion by remember { mutableStateOf<Champion?>(null) }
    var isFirstPick by remember { mutableStateOf(false) }

    val analysis = remember(activeRole, isFirstPick, allyChampions.toList(), enemyChampions.toList(), lang) {
        WildRiftRepository.analyzeDraft(
            myRole = activeRole,
            allies = allyChampions,
            enemies = enemyChampions,
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
                            text = tr("Tier List & Catálogo de Campeones"),
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
                tr("Objetivos"),
                tr("⏱️ CD Tracker"),
                tr("🛡️ Math Daño")
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
                            activeRole = activeRole,
                            allies = allyChampions,
                            enemies = enemyChampions,
                            analysis = analysis,
                            isFirstPick = isFirstPick,
                            onToggleFirstPick = { isFirstPick = !isFirstPick },
                            onChangeRole = { showRoleChangeDialog = true },
                            onAddAlly = { pickingForTeam = "ALLY" },
                            onAddEnemy = { pickingForTeam = "ENEMY" },
                            onRemoveAlly = { allyChampions.remove(it) },
                            onRemoveEnemy = { enemyChampions.remove(it) },
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
                    7 -> {
                        // SECCIÓN: CD TRACKER (TEMPORIZADORES EN TIEMPO REAL)
                        CooldownTrackerPanel(modifier = Modifier.fillMaxSize())
                    }
                    8 -> {
                        // SECCIÓN: CALCULADORA DE DAÑO Y PENETRACIÓN
                        DamagePenetrationCalculator(modifier = Modifier.fillMaxSize())
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
            alreadySelected = (allyChampions + enemyChampions).map { it.id },
            onChampionPicked = { champ ->
                if (pickingForTeam == "ALLY" && allyChampions.size < 5) {
                    allyChampions.add(champ)
                } else if (pickingForTeam == "ENEMY" && enemyChampions.size < 5) {
                    enemyChampions.add(champ)
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
    var searchQuery by remember { mutableStateOf("") }
    var selectedRoleFilter by remember { mutableStateOf<LaneRole?>(null) }
    var selectedTierFilter by remember { mutableStateOf<String?>(null) }

    val filteredChampions = remember(searchQuery, selectedRoleFilter, selectedTierFilter) {
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
                                Text(
                                    text = "WR: ${champion.winrate}%",
                                    color = HextechGold,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
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
                                Text(tr("WR") + ": ${champ.winrate}%", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
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
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF10B981), CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "wr-meta.com / items",
                    color = HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${filteredItems.size} ${tr("Objetos")}",
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
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RunesTab() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("TODOS") }

    val filterOptions = listOf(
        "TODOS" to tr("Todos"),
        "KEYSTONE" to tr("Runas Clave"),
        "DOMINATION" to tr("Dominación"),
        "PRECISION" to tr("Precisión"),
        "RESOLVE" to tr("Valor"),
        "INSPIRATION" to tr("Inspiración"),
        "SORCERY" to tr("Brujería")
    )

    val filteredRunes = remember(searchQuery, selectedFilter) {
        WildRiftRepository.runes.filter { rune ->
            val matchesCategory = when (selectedFilter) {
                "TODOS" -> true
                "KEYSTONE" -> rune.category.contains("Clave", ignoreCase = true) || rune.category.contains("Keystone", ignoreCase = true)
                "DOMINATION" -> rune.category.contains("Dominación", ignoreCase = true) || rune.category.contains("Domination", ignoreCase = true)
                "PRECISION" -> rune.category.contains("Precisión", ignoreCase = true) || rune.category.contains("Precision", ignoreCase = true)
                "RESOLVE" -> rune.category.contains("Valor", ignoreCase = true) || rune.category.contains("Resolve", ignoreCase = true)
                "INSPIRATION" -> rune.category.contains("Inspiración", ignoreCase = true) || rune.category.contains("Inspiration", ignoreCase = true)
                "SORCERY" -> rune.category.contains("Brujería", ignoreCase = true) || rune.category.contains("Sorcery", ignoreCase = true)
                else -> true
            }
            val matchesSearch = searchQuery.isBlank() ||
                    rune.name.contains(searchQuery, ignoreCase = true) ||
                    rune.description.contains(searchQuery, ignoreCase = true) ||
                    rune.category.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(tr("Buscar runa..."), color = TextMuted, fontSize = 13.sp) },
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
                    label = { Text(label, fontSize = 11.5.sp) },
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color(0xFF10B981), CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "wr-meta.com / runes",
                            color = HextechGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        "${filteredRunes.size} ${tr("Runas")} • ${tr(WildRiftRepository.CURRENT_PATCH_VERSION)}",
                        color = HextechCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            items(filteredRunes) { rune ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
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
                            modifier = Modifier.size(42.dp),
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
                                    Text(rune.category, color = HextechCyan, fontSize = 10.5.sp, fontWeight = FontWeight.SemiBold)
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

// ====================================================================
// TAB 5: CATÁLOGO EXCLUSIVO DE HECHIZOS DE INVOCADOR
// ====================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SpellsTab() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("TODOS") }

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
                             spell.name.contains("Teleport", true)
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

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(tr("Buscar hechizo..."), color = TextMuted, fontSize = 13.sp) },
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
                    label = { Text(label, fontSize = 11.5.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechGold,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color(0xFF10B981), CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "wr-meta.com / spells",
                            color = HextechGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        "${filteredSpells.size} ${tr("Hechizos")} • ${tr("CDs Oficiales WR")}",
                        color = HextechCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            items(filteredSpells) { spell ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
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
    activeRole: LaneRole,
    allies: List<Champion>,
    enemies: List<Champion>,
    analysis: DraftAnalysisResult,
    isFirstPick: Boolean,
    onToggleFirstPick: () -> Unit,
    onChangeRole: () -> Unit,
    onAddAlly: () -> Unit,
    onAddEnemy: () -> Unit,
    onRemoveAlly: (Champion) -> Unit,
    onRemoveEnemy: (Champion) -> Unit,
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
                        Text(tr("Línea:"), color = TextMuted, fontSize = 10.5.sp)
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

        // Team Drafting Slots (Allies & Enemies)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Allies Column
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tr("Equipo Aliado") + " (${allies.size}/5)",
                    color = AllyBlue,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                allies.forEach { champ ->
                    TeamChampionSlot(
                        champion = champ,
                        isEnemy = false,
                        onRemove = { onRemoveAlly(champ) },
                        onClick = { onSelectChampion(champ) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                if (allies.size < 5) {
                    AddChampionSlotButton(isEnemy = false, onClick = onAddAlly)
                }
            }

            // Enemies Column
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tr("Equipo Rival") + " (${enemies.size}/5)",
                    color = DangerRed,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                enemies.forEach { champ ->
                    TeamChampionSlot(
                        champion = champ,
                        isEnemy = true,
                        onRemove = { onRemoveEnemy(champ) },
                        onClick = { onSelectChampion(champ) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                if (enemies.size < 5) {
                    AddChampionSlotButton(isEnemy = true, onClick = onAddEnemy)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Warnings & Matchup
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
                        Text(tr("Alerta de Composición Rival"), color = DangerRed, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        Text(analysis.directMatchupWarning, color = TextPrimary, fontSize = 12.sp, lineHeight = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
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

                    Text(
                        text = tr("🔮 Runas recomendadas:") + " ${topPick.champion.recommendedRunes} • " + tr("Toca para ver build"),
                        color = HextechGoldLight,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
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
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun TeamChampionSlot(
    champion: Champion,
    isEnemy: Boolean,
    onRemove: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, if (isEnemy) DangerRed.copy(alpha = 0.6f) else AllyBlue.copy(alpha = 0.6f))
    ) {
        Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ChampionAvatar(champion = champion, size = 36.dp, showTierBadge = false)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(champion.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
                    Text(com.example.util.tr(champion.primaryRole.shortName), color = if (isEnemy) DangerRed else AllyBlue, fontSize = 10.5.sp)
                }
            }
            IconButton(onClick = onRemove, modifier = Modifier.size(24.dp)) {
                Icon(Icons.Default.Close, contentDescription = tr("Eliminar"), tint = TextMuted, modifier = Modifier.size(16.dp))
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
    alreadySelected: List<String>,
    onChampionPicked: (Champion) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var search by remember { mutableStateOf("") }
    var selectedRoleFilter by remember { mutableStateOf<LaneRole?>(null) }

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
                text = if (team == "ALLY") tr("Seleccionar Campeón Aliado") else tr("Seleccionar Campeón Rival"),
                color = if (team == "ALLY") AllyBlue else DangerRed,
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
                            .clickable { onChampionPicked(champ) }
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
