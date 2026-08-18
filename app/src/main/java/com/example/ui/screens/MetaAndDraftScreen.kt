package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Sync
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.model.MetaDataSource
import com.example.ui.components.ChampionAvatar
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
import com.example.ui.theme.TierSPlusColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MetaAndDraftScreen(
    userMainRole: LaneRole,
    onNavigateBack: () -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var activeRole by remember { mutableStateOf(userMainRole) }
    var showRoleChangeDialog by remember { mutableStateOf(false) }

    // Draft State with initial realistic champions from video
    val allyChampions = remember {
        mutableStateListOf(
            WildRiftRepository.getChampionById("chogath") ?: WildRiftRepository.champions[10],
            WildRiftRepository.getChampionById("vayne") ?: WildRiftRepository.champions[11],
            WildRiftRepository.getChampionById("janna") ?: WildRiftRepository.champions[8],
            WildRiftRepository.getChampionById("viego") ?: WildRiftRepository.champions[1]
        )
    }

    val enemyChampions = remember {
        mutableStateListOf(
            WildRiftRepository.getChampionById("sett") ?: WildRiftRepository.champions[4],
            WildRiftRepository.getChampionById("vi") ?: WildRiftRepository.champions[13],
            WildRiftRepository.getChampionById("caitlyn") ?: WildRiftRepository.champions[12],
            WildRiftRepository.getChampionById("rell") ?: WildRiftRepository.champions[14]
        )
    }

    // Modal Champion Picker State
    var pickingForTeam by remember { mutableStateOf<String?>(null) } // "ALLY", "ENEMY"
    var selectedDetailChampion by remember { mutableStateOf<Champion?>(null) }

    val analysis = remember(activeRole, allyChampions.toList(), enemyChampions.toList()) {
        WildRiftRepository.analyzeDraft(
            myRole = activeRole,
            allies = allyChampions,
            enemies = enemyChampions
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Wild Rift Meta & Draft",
                            color = TextPrimary,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Sincronizado: ${WildRiftRepository.CURRENT_PATCH_VERSION}",
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
                            contentDescription = "Volver",
                            tint = HextechGold
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            // Reset / reload sample draft
                            allyChampions.clear()
                            allyChampions.addAll(
                                listOfNotNull(
                                    WildRiftRepository.getChampionById("chogath"),
                                    WildRiftRepository.getChampionById("vayne"),
                                    WildRiftRepository.getChampionById("janna"),
                                    WildRiftRepository.getChampionById("viego")
                                )
                            )
                            enemyChampions.clear()
                            enemyChampions.addAll(
                                listOfNotNull(
                                    WildRiftRepository.getChampionById("sett"),
                                    WildRiftRepository.getChampionById("vi"),
                                    WildRiftRepository.getChampionById("caitlyn"),
                                    WildRiftRepository.getChampionById("rell")
                                )
                            )
                        },
                        modifier = Modifier.testTag("refresh_draft_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Reiniciar Draft",
                            tint = HextechCyan
                        )
                    }
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
            // Tabs
            val tabs = listOf("Análisis de Draft", "Builds & Tier List", "Fuentes Meta (4)")
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = HextechSurface,
                contentColor = HextechCyan,
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

            when (selectedTabIndex) {
                0 -> {
                    // Tab 1: Análisis de Draft
                    DraftAnalysisTab(
                        activeRole = activeRole,
                        allies = allyChampions,
                        enemies = enemyChampions,
                        analysis = analysis,
                        onChangeRole = { showRoleChangeDialog = true },
                        onAddAlly = { pickingForTeam = "ALLY" },
                        onAddEnemy = { pickingForTeam = "ENEMY" },
                        onRemoveAlly = { allyChampions.remove(it) },
                        onRemoveEnemy = { enemyChampions.remove(it) },
                        onChampionClick = { selectedDetailChampion = it }
                    )
                }
                1 -> {
                    // Tab 2: Builds & Tier List
                    BuildsAndTierListTab(
                        onSelectChampion = { selectedDetailChampion = it }
                    )
                }
                2 -> {
                    // Tab 3: Fuentes Meta y Sincronización Automática
                    StatsAndSourcesTab()
                }
            }
        }
    }

    // Modal Champion Picker for Draft Slots
    if (pickingForTeam != null) {
        ChampionPickerSheet(
            title = if (pickingForTeam == "ALLY") "Añadir Campeón Aliado" else "Añadir Campeón Rival",
            onDismiss = { pickingForTeam = null },
            onChampionSelected = { selected ->
                if (pickingForTeam == "ALLY") {
                    if (allyChampions.none { it.id == selected.id } && enemyChampions.none { it.id == selected.id }) {
                        if (allyChampions.size < 4) allyChampions.add(selected)
                    }
                } else {
                    if (enemyChampions.none { it.id == selected.id } && allyChampions.none { it.id == selected.id }) {
                        if (enemyChampions.size < 5) enemyChampions.add(selected)
                    }
                }
                pickingForTeam = null
            }
        )
    }

    // Role Selection Dialog
    if (showRoleChangeDialog) {
        RoleChangeSheet(
            currentRole = activeRole,
            onDismiss = { showRoleChangeDialog = false },
            onSelect = {
                activeRole = it
                showRoleChangeDialog = false
            }
        )
    }

    // Champion Detail Sheet
    ChampionDetailSheet(
        champion = selectedDetailChampion,
        onDismiss = { selectedDetailChampion = null }
    )
}

@Composable
private fun DraftAnalysisTab(
    activeRole: LaneRole,
    allies: List<Champion>,
    enemies: List<Champion>,
    analysis: com.example.model.DraftAnalysisResult,
    onChangeRole: () -> Unit,
    onAddAlly: () -> Unit,
    onAddEnemy: () -> Unit,
    onRemoveAlly: (Champion) -> Unit,
    onRemoveEnemy: (Champion) -> Unit,
    onChampionClick: (Champion) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Tu Rol a Elegir Banner
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Tu Rol a Elegir: ",
                        color = TextMuted,
                        fontSize = 13.sp
                    )
                    Text(
                        text = activeRole.displayName,
                        color = HextechCyan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechCyan.copy(alpha = 0.15f))
                        .border(1.dp, HextechCyan, RoundedCornerShape(8.dp))
                        .clickable { onChangeRole() }
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "CAMBIAR",
                        color = HextechCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Slots Aliados vs Rivales
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Allies Column
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Aliados (${allies.size}/4)",
                    color = AllyBlue,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                allies.forEach { champ ->
                    DraftSlotItem(
                        champion = champ,
                        isAlly = true,
                        onRemove = { onRemoveAlly(champ) },
                        onClick = { onChampionClick(champ) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                if (allies.size < 4) {
                    AddSlotButton(label = "+ Añadir Aliado", isAlly = true, onClick = onAddAlly)
                }
            }

            // Enemies Column
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Rivales (${enemies.size}/4)",
                    color = DangerRed,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                enemies.forEach { champ ->
                    DraftSlotItem(
                        champion = champ,
                        isAlly = false,
                        onRemove = { onRemoveEnemy(champ) },
                        onClick = { onChampionClick(champ) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                if (enemies.size < 4) {
                    AddSlotButton(label = "+ Añadir Rival", isAlly = false, onClick = onAddEnemy)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Composición del Equipo Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Composición del Equipo",
                    color = HextechGold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Damage Balance Progress
                Text(
                    text = "Daño: ${analysis.physicalDamagePercent}% Físico / ${analysis.magicDamagePercent}% Mágico",
                    color = TextPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF1E293B))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(analysis.physicalDamagePercent / 100f)
                            .height(8.dp)
                            .background(DangerRed)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(HextechCyan)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Vanguardia: ${analysis.frontlineStatus}",
                    color = if (analysis.frontlineStatus.startsWith("⚠️")) HextechGold else AllyBlue,
                    fontSize = 12.5.sp,
                    fontWeight = FontWeight.Medium
                )

                // Direct matchup highlight
                if (analysis.directCounterBestPick != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(DangerRedSurface)
                            .border(1.dp, DangerRed.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = DangerRed,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = analysis.directMatchupWarning ?: "Aviso Táctico:",
                                    color = DangerRed,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = analysis.directCounterBestPick,
                                color = HextechGoldLight,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Top Recomendaciones Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = HextechCyan,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Top Recomendaciones (${activeRole.shortName})",
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(HextechCyan.copy(alpha = 0.12f))
                    .border(1.dp, HextechCyan.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "Auto-Meta Sync",
                    color = HextechCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        analysis.recommendations.forEach { rec ->
            RecommendationCard(
                recommendation = rec,
                onClick = { onChampionClick(rec.champion) }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun RecommendationCard(
    recommendation: com.example.model.DraftRecommendation,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("rec_card_${recommendation.champion.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ChampionAvatar(champion = recommendation.champion, size = 48.dp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = recommendation.champion.name,
                                color = TextPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(HextechCyan.copy(alpha = 0.15f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = recommendation.advantageBadge,
                                    color = HextechCyan,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Text(
                            text = "Winrate Est: ${String.format("%.1f", recommendation.estimatedWinrate)}%",
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recommendation.tacticalReason,
                color = HextechGoldLight,
                fontSize = 12.5.sp,
                lineHeight = 17.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Runas Óptimas: ",
                    color = TextMuted,
                    fontSize = 11.5.sp
                )
                Text(
                    text = recommendation.runes,
                    color = HextechCyan,
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun DraftSlotItem(
    champion: Champion,
    isAlly: Boolean,
    onRemove: () -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (isAlly) HextechSurface else DangerRedSurface)
            .border(
                1.dp,
                if (isAlly) AllyBlue.copy(alpha = 0.4f) else DangerRed.copy(alpha = 0.4f),
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ChampionAvatar(champion = champion, size = 34.dp, showTierBadge = false)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = champion.name,
                        color = TextPrimary,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = champion.primaryRole.shortName,
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                }
            }

            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Quitar",
                    tint = TextMuted,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun AddSlotButton(
    label: String,
    isAlly: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(HextechSurface.copy(alpha = 0.5f))
            .border(
                1.dp,
                if (isAlly) AllyBlue.copy(alpha = 0.3f) else DangerRed.copy(alpha = 0.3f),
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = if (isAlly) AllyBlue else DangerRed,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun BuildsAndTierListTab(
    onSelectChampion: (Champion) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedRoleFilter by remember { mutableStateOf<LaneRole?>(null) }
    var selectedTierFilter by remember { mutableStateOf<String?>(null) }

    val filteredChampions = remember(searchQuery, selectedRoleFilter, selectedTierFilter) {
        WildRiftRepository.champions.filter { champ ->
            val matchesSearch = champ.name.contains(searchQuery, ignoreCase = true)
            val matchesRole = selectedRoleFilter == null || champ.primaryRole == selectedRoleFilter || champ.secondaryRoles.contains(selectedRoleFilter)
            val matchesTier = selectedTierFilter == null || champ.tier == selectedTierFilter
            matchesSearch && matchesRole && matchesTier
        }.sortedByDescending { it.winrate }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar campeón de Wild Rift...", color = TextMuted, fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechGold) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = HextechSurface,
                unfocusedContainerColor = HextechSurface,
                focusedBorderColor = HextechCyan,
                unfocusedBorderColor = HextechCardBorder,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            modifier = Modifier.fillMaxWidth().testTag("champion_search_field")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Role Filter Chips Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            FilterChip(
                selected = selectedRoleFilter == null,
                onClick = { selectedRoleFilter = null },
                label = { Text("Todos", fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HextechCyan,
                    selectedLabelColor = HextechDarkBg,
                    containerColor = HextechSurface,
                    labelColor = TextMuted
                )
            )
            LaneRole.entries.forEach { role ->
                FilterChip(
                    selected = selectedRoleFilter == role,
                    onClick = { selectedRoleFilter = if (selectedRoleFilter == role) null else role },
                    label = { Text(role.shortName, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg,
                        containerColor = HextechSurface,
                        labelColor = TextMuted
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredChampions) { champion ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectChampion(champion) }
                        .testTag("tier_item_${champion.id}"),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ChampionAvatar(champion = champion, size = 48.dp)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = champion.name,
                                        color = TextPrimary,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "• ${champion.primaryRole.shortName}",
                                        color = HextechCyan,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                Text(
                                    text = "WR: ${champion.winrate}% • Pick: ${champion.pickRate}% • Ban: ${champion.banRate}%",
                                    color = TextMuted,
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(HextechCyan.copy(alpha = 0.15f))
                                .border(1.dp, HextechCyan, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "VER BUILD",
                                color = HextechCyan,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatsAndSourcesTab() {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Sync Status Banner
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.2.dp, HextechCyan)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(HextechCyan.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Sync,
                        contentDescription = null,
                        tint = HextechCyan,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Sincronización Automática Activa",
                            color = HextechCyan,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "${WildRiftRepository.CURRENT_PATCH_VERSION} • Base de datos consolidada con 4 fuentes oficiales",
                        color = TextMuted,
                        fontSize = 11.5.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Portales Web Oficiales del Meta",
            color = HextechGold,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "La aplicación consulta y sincroniza automáticamente las runas, campeones, parches y builds de estos sitios:",
            color = TextMuted,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 4 Official Portals
        WildRiftRepository.metaSources.forEach { source ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        try {
                            uriHandler.openUri(source.url)
                        } catch (_: Exception) {}
                    }
                    .testTag("source_card_${source.id}"),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = null,
                                tint = HextechCyan,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = source.name,
                                color = TextPrimary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(HextechGold.copy(alpha = 0.15f))
                                .border(1.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = source.badge,
                                color = HextechGold,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = source.description,
                        color = HextechGoldLight,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Especialidad: ${source.focusArea}",
                            color = HextechCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Visitar web",
                                color = TextMuted,
                                fontSize = 11.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                contentDescription = null,
                                tint = TextMuted,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Control de Objetivos Clave Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Control de Objetivos Clave (Wild Rift)",
                    color = HextechCyan,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "• Dragón Elemental: Minuto 4:00 (Mayor prioridad si tu ADC tiene ventaja de rango).\n" +
                           "• Heraldo de la Grieta: Minuto 5:00 (Prioridad para abrir primera torre).\n" +
                           "• Barón Nashor y Dragón Anciano: Minuto 12:00 (Decisivos para cerrar partidas).",
                    color = TextMuted,
                    fontSize = 12.5.sp,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChampionPickerSheet(
    title: String,
    onDismiss: () -> Unit,
    onChampionSelected: (Champion) -> Unit
) {
    var search by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val list = remember(search) {
        WildRiftRepository.champions.filter { it.name.contains(search, ignoreCase = true) }
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
                text = title,
                color = HextechGold,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                placeholder = { Text("Buscar...", color = TextMuted) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(list) { champ ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurface)
                            .clickable { onChampionSelected(champ) }
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ChampionAvatar(champion = champ, size = 40.dp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = champ.name, color = TextPrimary, fontWeight = FontWeight.Bold)
                            Text(text = "${champ.primaryRole.displayName} • Tier ${champ.tier}", color = HextechCyan, fontSize = 11.sp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RoleChangeSheet(
    currentRole: LaneRole,
    onDismiss: () -> Unit,
    onSelect: (LaneRole) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechSurfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Cambiar tu Rol para el Draft",
                color = HextechGold,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(14.dp))

            LaneRole.entries.forEach { role ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (role == currentRole) HextechSurface else Color.Transparent)
                        .clickable { onSelect(role) }
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = role.displayName,
                        color = if (role == currentRole) HextechCyan else TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    if (role == currentRole) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = HextechCyan)
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
