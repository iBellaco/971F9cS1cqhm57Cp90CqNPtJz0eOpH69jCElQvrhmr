package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.util.tr
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.R
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.ItemCategory
import com.example.model.LaneRole
import com.example.ui.theme.AllyBlue
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TierSColor
import com.example.ui.theme.TierSPlusColor
import kotlin.math.roundToInt

enum class OverlayTab(val title: String, val icon: @Composable () -> Unit) {
    DRAFT("Draft", { Icon(Icons.Default.FlashOn, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    OBJECTIVES("Objetivos", { Icon(Icons.Default.Alarm, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    ITEMS("Objetos", { Icon(Icons.Default.Shield, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    RUNES("Runas", { Icon(Icons.Default.AutoFixHigh, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    SPELLS("Hechizos", { Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    CD_TRACKER("CD Tracker", { Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    DAMAGE_MATH("Daño", { Icon(Icons.Default.FlashOn, contentDescription = null, modifier = Modifier.size(16.dp)) })
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FloatingAssistantOverlay(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    initialRole: LaneRole = LaneRole.MID,
    modifier: Modifier = Modifier
) {
    if (!isVisible) return

    val density = LocalDensity.current
    var dragOffsetY by remember { mutableFloatStateOf(0f) }
    var dragOffsetX by remember { mutableFloatStateOf(0f) }
    var showSpeechBubble by remember { mutableStateOf(true) }
    var selectedTab by remember { mutableStateOf(OverlayTab.DRAFT) }

    // Draft State
    var activeRole by remember { mutableStateOf(initialRole) }
    var isFirstPick by remember { mutableStateOf(false) }
    val scannedAllies = remember {
        mutableStateListOf<Champion>().apply {
            WildRiftRepository.getChampionById("vayne")?.let { add(it) }
            WildRiftRepository.getChampionById("janna")?.let { add(it) }
            WildRiftRepository.getChampionById("viego")?.let { add(it) }
        }
    }
    val scannedEnemies = remember {
        mutableStateListOf<Champion>().apply {
            WildRiftRepository.getChampionById("sett")?.let { add(it) }
            WildRiftRepository.getChampionById("vi")?.let { add(it) }
            WildRiftRepository.getChampionById("caitlyn")?.let { add(it) }
        }
    }

    // Selected / Locked-in champion for Runes view
    var lockedChampion by remember { mutableStateOf<Champion?>(null) }

    // Search query for items / runes champion picker
    var itemSearchQuery by remember { mutableStateOf("") }
    var itemSelectedCategory by remember { mutableStateOf<ItemCategory?>(null) }
    var runeSearchQuery by remember { mutableStateOf("") }

    // Real-time analysis computation
    val draftAnalysis = remember(activeRole, isFirstPick, scannedAllies.toList(), scannedEnemies.toList()) {
        WildRiftRepository.analyzeDraft(
            myRole = activeRole,
            allies = scannedAllies,
            enemies = scannedEnemies,
            isFirstPick = isFirstPick
        )
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .testTag("floating_assistant_overlay_root"),
        contentAlignment = Alignment.Center
    ) {
        val screenWidthPx = with(density) { maxWidth.toPx() }
        val screenHeightPx = with(density) { maxHeight.toPx() }

        val maxSafeHorizontalOffset = (screenWidthPx * 0.38f)
        val maxSafeVerticalOffset = (screenHeightPx * 0.38f)

        // Semi-transparent backdrop when advice panel is expanded
        if (showSpeechBubble) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.45f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        showSpeechBubble = false
                    }
            )
        }

        // Draggable container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .offset {
                    IntOffset(
                        dragOffsetX.coerceIn(-maxSafeHorizontalOffset, maxSafeHorizontalOffset).roundToInt(),
                        dragOffsetY.coerceIn(-maxSafeVerticalOffset, maxSafeVerticalOffset).roundToInt()
                    )
                }
                .testTag("floating_assistant_container"),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // ==========================================
                // 1. SUPERPOSICIÓN EXPANDIBLE HEXTECH
                // ==========================================
                AnimatedVisibility(
                    visible = showSpeechBubble,
                    enter = fadeIn() + scaleIn(initialScale = 0.92f) + slideInVertically(initialOffsetY = { -30 }),
                    exit = fadeOut() + scaleOut(targetScale = 0.92f) + slideOutVertically(targetOffsetY = { -30 })
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 420.dp)
                            .shadow(24.dp, RoundedCornerShape(18.dp))
                            .border(1.5.dp, HextechGold, RoundedCornerShape(18.dp))
                            .testTag("tactical_advice_card"),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = HextechDarkBg.copy(alpha = 0.98f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            // Header Row (Drag Handle)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .pointerInput(Unit) {
                                        detectDragGestures(
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                dragOffsetX += dragAmount.x
                                                dragOffsetY += dragAmount.y
                                            }
                                        )
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(HextechCyan.copy(alpha = 0.15f))
                                            .border(1.dp, HextechCyan, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.AutoAwesome,
                                            contentDescription = null,
                                            tint = HextechCyan,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = "Wild Rift HUD Inteligente",
                                            color = HextechGold,
                                            fontSize = 13.5.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Superposición en Directo • ${activeRole.shortName}",
                                            color = HextechCyan,
                                            fontSize = 10.sp
                                        )
                                    }
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = { showSpeechBubble = false },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Minimizar",
                                            tint = TextMuted,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Navigation Tabs in Overlay
                            TabRow(
                                selectedTabIndex = selectedTab.ordinal,
                                containerColor = HextechSurface,
                                contentColor = HextechCyan,
                                indicator = { tabPositions ->
                                    TabRowDefaults.SecondaryIndicator(
                                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab.ordinal]),
                                        color = HextechGold,
                                        height = 2.5.dp
                                    )
                                }
                            ) {
                                OverlayTab.entries.forEach { tab ->
                                    val isSelected = selectedTab == tab
                                    Tab(
                                        selected = isSelected,
                                        onClick = { selectedTab = tab },
                                        text = {
                                            Text(
                                                text = tab.title,
                                                fontSize = 11.sp,
                                                maxLines = 1,
                                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                color = if (isSelected) HextechGold else TextMuted
                                            )
                                        }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // ==========================================
                            // TAB CONTENT DISPATCH
                            // ==========================================
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 260.dp, max = 360.dp)
                            ) {
                                when (selectedTab) {
                                    OverlayTab.DRAFT -> {
                                        OverlayDraftTabContent(
                                            activeRole = activeRole,
                                            onRoleChange = { activeRole = it },
                                            isFirstPick = isFirstPick,
                                            onFirstPickToggle = { isFirstPick = it },
                                            analysis = draftAnalysis,
                                            onLockChampion = { champ ->
                                                lockedChampion = champ
                                                selectedTab = OverlayTab.RUNES
                                            },
                                            onSimulateScan = {
                                                // Randomize a realistic enemy draft scenario
                                                scannedEnemies.clear()
                                                val candidates = WildRiftRepository.champions.shuffled().take(4)
                                                scannedEnemies.addAll(candidates)
                                            }
                                        )
                                    }

                                    OverlayTab.OBJECTIVES -> {
                                        OverlayObjectivesTabContent()
                                    }

                                    OverlayTab.ITEMS -> {
                                        OverlayItemsTabContent(
                                            searchQuery = itemSearchQuery,
                                            onSearchChange = { itemSearchQuery = it },
                                            selectedCategory = itemSelectedCategory,
                                            onCategoryChange = { itemSelectedCategory = it }
                                        )
                                    }

                                    OverlayTab.RUNES -> {
                                        OverlayRunesTabContent(
                                            lockedChampion = lockedChampion,
                                            searchQuery = runeSearchQuery,
                                            onSearchChange = { runeSearchQuery = it },
                                            onSelectChampion = { lockedChampion = it },
                                            onClearChampion = { lockedChampion = null }
                                        )
                                    }

                                    OverlayTab.SPELLS -> {
                                        OverlaySpellsTabContent(
                                            lockedChampion = lockedChampion,
                                            searchQuery = runeSearchQuery,
                                            onSearchChange = { runeSearchQuery = it },
                                            onSelectChampion = { lockedChampion = it },
                                            onClearChampion = { lockedChampion = null }
                                        )
                                    }
                                    OverlayTab.CD_TRACKER -> {
                                        CooldownTrackerPanel(modifier = Modifier.fillMaxSize(), isCompactOverlay = true)
                                    }
                                    OverlayTab.DAMAGE_MATH -> {
                                        DamagePenetrationCalculator(modifier = Modifier.fillMaxSize())
                                    }
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            // Bottom Action Row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = tr("Detener Asistente"),
                                    color = DangerRed,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .clickable { onDismiss() }
                                        .padding(8.dp)
                                )
                                Text(
                                    text = tr("Minimizar HUD"),
                                    color = HextechCyan,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .clickable { showSpeechBubble = false }
                                        .padding(8.dp)
                                )
                            }
                        }
                    }
                }

                // ==========================================
                // 2. ORBE FLOTANTE DE CÁMARA TÁCTICA
                // ==========================================
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(16.dp, CircleShape)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                listOf(HextechCyan, Color(0xFF00B4D8), Color(0xFF0077B6))
                            )
                        )
                        .border(2.5.dp, HextechGold, CircleShape)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragEnd = {
                                    if (dragOffsetY > maxSafeVerticalOffset - 60f) {
                                        onDismiss() // Close if dragged to very bottom
                                    }
                                },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    dragOffsetX += dragAmount.x
                                    dragOffsetY += dragAmount.y
                                    // Minimize if dragged down sharply while open
                                    if (showSpeechBubble && dragAmount.y > 20f) {
                                        showSpeechBubble = false
                                    }
                                }
                            )
                        }
                        .clickable {
                            showSpeechBubble = !showSpeechBubble
                        }
                        .testTag("floating_camera_bubble"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Escanear Draft & Overlay",
                        tint = HextechDarkBg,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

// ====================================================================
// OVERLAY SUB-TAB 1: DRAFT & RECOMENDACIONES EN VIVO
// ====================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OverlayDraftTabContent(
    activeRole: LaneRole,
    onRoleChange: (LaneRole) -> Unit,
    isFirstPick: Boolean,
    onFirstPickToggle: (Boolean) -> Unit,
    analysis: com.example.model.DraftAnalysisResult,
    onLockChampion: (Champion) -> Unit,
    onSimulateScan: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Controls Row: Role Selector & First Pick Switch
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(HextechSurface)
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // First Pick Toggle
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = tr("1er Pick") + ":",
                    color = HextechGold,
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Switch(
                    checked = isFirstPick,
                    onCheckedChange = onFirstPickToggle,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = HextechGold,
                        checkedTrackColor = HextechGold.copy(alpha = 0.3f),
                        uncheckedThumbColor = TextMuted,
                        uncheckedTrackColor = HextechSurfaceVariant
                    ),
                    modifier = Modifier.size(36.dp)
                )
            }

            // Quick Scan Button
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(HextechCyan.copy(alpha = 0.15f))
                    .border(1.dp, HextechCyan.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                    .clickable { onSimulateScan() }
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(13.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(tr("Escanear"), color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Role Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val roleIcons = mapOf(
                LaneRole.TOP to R.drawable.ic_wr_role_solo,
                LaneRole.JUNGLE to R.drawable.ic_wr_role_jungle,
                LaneRole.MID to R.drawable.ic_wr_role_mid,
                LaneRole.ADC to R.drawable.ic_wr_role_duo,
                LaneRole.SUPPORT to R.drawable.ic_wr_role_support
            )
            LaneRole.entries.forEach { role ->
                val isSelected = activeRole == role
                val iconRes = roleIcons[role] ?: R.drawable.ic_wr_role_mid
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (isSelected) HextechCyan else HextechSurface)
                        .clickable { onRoleChange(role) }
                        .padding(vertical = 4.dp, horizontal = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = role.shortName,
                        tint = if (isSelected) HextechDarkBg else HextechGold,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = tr(role.shortName),
                        color = if (isSelected) HextechDarkBg else TextMuted,
                        fontSize = 9.5.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // #1 BEST OPTION HERO CARD
        val topRec = analysis.recommendations.firstOrNull()
        if (topRec != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.2.dp, HextechGold, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (isFirstPick) "★ " + tr("1ª Elección Segura") else "★ " + tr("MEJOR OPCIÓN ABSOLUTA"),
                            color = HextechGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "WR Est.: ${topRec.estimatedWinrate}%",
                            color = HextechCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ChampionAvatar(champion = topRec.champion, size = 44.dp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = topRec.champion.name,
                                    color = TextPrimary,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Tier ${topRec.champion.tier}",
                                    color = TierSPlusColor,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = tr(topRec.advantageBadge),
                                color = HextechCyan,
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = topRec.tacticalReason,
                        color = TextPrimary.copy(alpha = 0.9f),
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Button: Lock In Champion & View Runes
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechGold.copy(alpha = 0.15f))
                            .border(1.dp, HextechGold, RoundedCornerShape(8.dp))
                            .clickable { onLockChampion(topRec.champion) }
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = HextechGold, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = tr("Fijar") + " ${topRec.champion.name} " + tr("y Ver Runas / Build"),
                            color = HextechGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Secondary Options
        Text(
            text = tr("Otras Alternativas en ") + tr(activeRole.shortName) + ":",
            color = HextechCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))

        analysis.recommendations.drop(1).take(3).forEach { rec ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                    .clickable { onLockChampion(rec.champion) }
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ChampionAvatar(champion = rec.champion, size = 32.dp, showTierBadge = false)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(rec.champion.name, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(tr(rec.advantageBadge), color = TextMuted, fontSize = 10.sp)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("WR: ${rec.estimatedWinrate}%", color = HextechGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.NavigateNext, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

// ====================================================================
// OVERLAY SUB-TAB 2: OBJETIVOS DE MAPA (TIEMPOS & TÁCTICA)
// ====================================================================
@Composable
private fun OverlayObjectivesTabContent() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(WildRiftRepository.mapObjectives) { obj ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(obj.name, color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(HextechCyan.copy(alpha = 0.15f))
                                .border(0.5.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(obj.spawnTime, color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 10.5.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text("${tr("Reaparición")}: ${obj.respawnTime}", color = TextMuted, fontSize = 9.5.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(obj.buffDescription, color = TextPrimary.copy(alpha = 0.9f), fontSize = 11.sp, lineHeight = 15.sp)
                }
            }
        }
    }
}

// ====================================================================
// OVERLAY SUB-TAB 3: OBJETOS (ITEMS & COUNTERS SITUACIONALES)
// ====================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OverlayItemsTabContent(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedCategory: ItemCategory?,
    onCategoryChange: (ItemCategory?) -> Unit
) {
    val filteredItems = remember(searchQuery, selectedCategory) {
        WildRiftRepository.items.filter { item ->
            val matchCategory = selectedCategory == null || item.category == selectedCategory
            val matchQuery = searchQuery.isBlank() ||
                    item.name.contains(searchQuery, ignoreCase = true) ||
                    item.passive.contains(searchQuery, ignoreCase = true) ||
                    item.stats.contains(searchQuery, ignoreCase = true)
            matchCategory && matchQuery
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Mini search field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(tr("Buscar objeto situacional (ej. Heridas, Zhonya)..."), color = TextMuted, fontSize = 11.5.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp)) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = HextechCyan,
                unfocusedBorderColor = HextechCardBorder,
                focusedContainerColor = HextechSurface,
                unfocusedContainerColor = HextechSurface
            ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Quick Category Chips
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ItemCategory.entries.forEach { cat ->
                val isSelected = selectedCategory == cat
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (isSelected) HextechCyan else HextechSurface)
                        .clickable { onCategoryChange(if (isSelected) null else cat) }
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = tr(cat.displayName),
                        color = if (isSelected) HextechDarkBg else TextMuted,
                        fontSize = 9.5.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(filteredItems) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        AppAssetImage(
                            url = item.iconUrl,
                            contentDescription = item.name,
                            fallbackText = item.name,
                            modifier = Modifier.size(36.dp),
                            borderColor = HextechGold,
                            shape = RoundedCornerShape(6.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(item.name, color = HextechGoldLight, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                Text("${item.goldCost} ${tr("Oro")}", color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                            Text(tr(item.stats), color = HextechCyan, fontSize = 10.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(tr(item.passive), color = TextPrimary.copy(alpha = 0.85f), fontSize = 10.sp, lineHeight = 13.sp)
                        }
                    }
                }
            }
        }
    }
}

// ====================================================================
// OVERLAY SUB-TAB 4: RUNAS META (EXCLUSIVO RUNAS)
// ====================================================================
@Composable
private fun OverlayRunesTabContent(
    lockedChampion: Champion?,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onSelectChampion: (Champion) -> Unit,
    onClearChampion: () -> Unit
) {
    if (lockedChampion == null) {
        val matchingChampions = remember(searchQuery) {
            WildRiftRepository.champions.filter { champ ->
                searchQuery.isBlank() || champ.name.contains(searchQuery, ignoreCase = true)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tr("Selecciona un campeón para ver su página de runas óptima"),
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(tr("Buscar campeón fijado..."), color = TextMuted, fontSize = 11.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp)) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = HextechCyan,
                    unfocusedBorderColor = HextechCardBorder,
                    focusedContainerColor = HextechSurface,
                    unfocusedContainerColor = HextechSurface
                ),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(matchingChampions) { champ ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurface)
                            .clickable { onSelectChampion(champ) }
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ChampionAvatar(champion = champ, size = 32.dp, showTierBadge = false)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(champ.name, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Text("${tr(champ.primaryRole.shortName)} • ${tr("Runas")}: ${champ.recommendedRunes}", color = HextechGold, fontSize = 10.sp)
                            }
                        }
                        Icon(Icons.Default.Check, contentDescription = "Seleccionar", tint = HextechCyan, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Selected Champion Banner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechCyan, RoundedCornerShape(10.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ChampionAvatar(champion = lockedChampion, size = 36.dp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "${lockedChampion.name} (${tr("Fijado")})",
                            color = HextechCyan,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${tr(lockedChampion.primaryRole.displayName)} • ${tr("Página de Runas")}",
                            color = HextechGoldLight,
                            fontSize = 10.sp
                        )
                    }
                }

                IconButton(
                    onClick = onClearChampion,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cambiar campeón", tint = TextMuted, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Runes Breakdown Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.6f))
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "🔮 " + tr("Runa Clave Recomendada"),
                        color = HextechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = lockedChampion.recommendedRunes,
                        color = HextechGoldLight,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                    if (lockedChampion.runeTreeDetails.isNotBlank()) {
                        val parsedRunes = lockedChampion.runeTreeDetails
                            .replace(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+:\\s*"), "")
                            .split("•")
                            .map { it.trim() }
                            .filter { it.isNotEmpty() }
                        
                        if (parsedRunes.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            @OptIn(ExperimentalLayoutApi::class)
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                parsedRunes.forEach { rName ->
                                    val allRunes = com.example.data.WildRiftSpellsAndRunes.runes
                                    val foundRune = allRunes.find { r -> r.name.equals(rName, ignoreCase = true) || rName.contains(r.name) }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        if (foundRune != null) {
                                            AppAssetImage(
                                                url = foundRune.iconUrl,
                                                contentDescription = foundRune.name,
                                                fallbackText = "",
                                                modifier = Modifier.size(18.dp),
                                                shape = CircleShape
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                        } else {
                                            Box(modifier = Modifier.size(4.dp).background(HextechCyan, CircleShape))
                                            Spacer(modifier = Modifier.width(4.dp))
                                        }
                                        Text(rName, color = TextPrimary.copy(alpha = 0.9f), fontSize = 11.sp)
                                    }
                                }
                            }
                        } else {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = lockedChampion.runeTreeDetails,
                                color = TextPrimary.copy(alpha = 0.9f),
                                fontSize = 11.sp,
                                lineHeight = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

// ====================================================================
// OVERLAY SUB-TAB 5: HECHIZOS DE INVOCADOR & HABILIDADES (EXCLUSIVO)
// ====================================================================
@Composable
private fun OverlaySpellsTabContent(
    lockedChampion: Champion?,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onSelectChampion: (Champion) -> Unit,
    onClearChampion: () -> Unit
) {
    if (lockedChampion == null) {
        val matchingChampions = remember(searchQuery) {
            WildRiftRepository.champions.filter { champ ->
                searchQuery.isBlank() || champ.name.contains(searchQuery, ignoreCase = true)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tr("Selecciona un campeón para ver sus hechizos de invocador recomendados"),
                    color = HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(tr("Buscar campeón..."), color = TextMuted, fontSize = 11.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp)) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = HextechGold,
                    unfocusedBorderColor = HextechCardBorder,
                    focusedContainerColor = HextechSurface,
                    unfocusedContainerColor = HextechSurface
                ),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(matchingChampions) { champ ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurface)
                            .clickable { onSelectChampion(champ) }
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ChampionAvatar(champion = champ, size = 32.dp, showTierBadge = false)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(champ.name, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Text("${tr(champ.primaryRole.shortName)} • ${tr("Hechizos")}: ${champ.recommendedSpells.joinToString("+")}", color = HextechGold, fontSize = 10.sp)
                            }
                        }
                        Icon(Icons.Default.Check, contentDescription = "Seleccionar", tint = HextechGold, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Selected Champion Banner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechGold, RoundedCornerShape(10.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ChampionAvatar(champion = lockedChampion, size = 36.dp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "${lockedChampion.name} (${tr("Fijado")})",
                            color = HextechGold,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${tr(lockedChampion.primaryRole.displayName)} • ${tr("Hechizos & Orden de Habilidades")}",
                            color = HextechCyan,
                            fontSize = 10.sp
                        )
                    }
                }

                IconButton(
                    onClick = onClearChampion,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cambiar campeón", tint = TextMuted, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Recommended Spells & Skill Order Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "⚡ " + tr("Hechizos de Invocador Recomendados"),
                        color = HextechGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        lockedChampion.recommendedSpells.forEach { spell ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurfaceVariant)
                                    .border(1.dp, HextechGold, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Text(
                                    text = tr(spell),
                                    color = HextechGoldLight,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = HextechCardBorder, thickness = 0.5.dp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "🎯 " + tr("Prioridad de Habilidades"),
                        color = HextechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = tr("Maxeo:") + " ${lockedChampion.skillOrder}",
                        color = TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
