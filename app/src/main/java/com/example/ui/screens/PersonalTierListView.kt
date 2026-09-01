package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsKabaddi
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.analytics.PersonalChampionStats
import com.example.data.analytics.PersonalTierListManager
import com.example.data.analytics.PersonalTierListResult
import com.example.data.analytics.TierGrade
import com.example.data.local.entity.SavedDraftEntity
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.ui.components.AppAssetImage
import com.example.ui.components.ChampionAvatar
import com.example.ui.components.FormattedWildRiftText
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
import com.example.ui.theme.TextSecondary
import com.example.util.LocalLanguage
import com.example.util.tr

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PersonalTierListView(
    draftsList: List<SavedDraftEntity>,
    onSelectDraftForDetail: (SavedDraftEntity) -> Unit
) {
    val currentLang = LocalLanguage.current
    var selectedRoleFilter by remember { mutableStateOf<LaneRole?>(null) }
    var selectedChampionStats by remember { mutableStateOf<PersonalChampionStats?>(null) }
    var viewMode by remember { mutableStateOf("TIERS") } // "TIERS" or "TABLE"

    val tierData: PersonalTierListResult = remember(draftsList, selectedRoleFilter, currentLang) {
        PersonalTierListManager.calculatePersonalTierList(
            drafts = draftsList,
            roleFilter = selectedRoleFilter,
            lang = currentLang
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("personal_tier_list_view")
    ) {
        // Selector de Líneas / Rol
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            FilterChip(
                selected = selectedRoleFilter == null,
                onClick = { selectedRoleFilter = null },
                label = { Text(tr("Todas las Líneas"), fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HextechGold,
                    selectedLabelColor = HextechDarkBg
                )
            )

            LaneRole.entries.forEach { role ->
                FilterChip(
                    selected = selectedRoleFilter == role,
                    onClick = { selectedRoleFilter = if (selectedRoleFilter == role) null else role },
                    label = { Text(tr(role.displayName), fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (tierData.allRankedChampions.isEmpty()) {
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
                        modifier = Modifier.size(90.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Leaderboard,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(44.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = tr("Aún no tienes partidas registradas para este filtro"),
                        color = TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = tr("Guarda tus selecciones de campeones y registra si ganaste o perdiste para construir tu Tier List Personal con estadísticas de rendimiento."),
                        color = TextMuted,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 17.sp
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Header Summary Card (Signature pick, best role, global WR)
                item {
                    PersonalOverviewCard(overview = tierData.overview)
                }

                // Selector de modo de visualización: Matriz de Tiers vs Lista Analítica
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (selectedRoleFilter != null) {
                                "🏆 Tier List Personal: ${selectedRoleFilter?.displayName}"
                            } else {
                                "🏆 Tier List Personal (Todos los Campeones)"
                            },
                            color = HextechGold,
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(HextechSurface)
                                .border(1.dp, HextechGold.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                .padding(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (viewMode == "TIERS") HextechGold else Color.Transparent)
                                    .clickable { viewMode = "TIERS" }
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = tr("Tiers"),
                                    color = if (viewMode == "TIERS") HextechDarkBg else TextMuted,
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (viewMode == "TABLE") HextechGold else Color.Transparent)
                                    .clickable { viewMode = "TABLE" }
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = tr("Detallado"),
                                    color = if (viewMode == "TABLE") HextechDarkBg else TextMuted,
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                if (viewMode == "TIERS") {
                    // TIER S+
                    if (tierData.tierSPlus.isNotEmpty()) {
                        item {
                            TierRowVisual(
                                grade = TierGrade.S_PLUS,
                                champions = tierData.tierSPlus,
                                badgeColor = Color(0xFFFFD700),
                                headerGradient = Brush.horizontalGradient(
                                    listOf(Color(0xFF6A4E00), HextechDarkBg)
                                ),
                                onChampionClick = { selectedChampionStats = it }
                            )
                        }
                    }

                    // TIER S
                    if (tierData.tierS.isNotEmpty()) {
                        item {
                            TierRowVisual(
                                grade = TierGrade.S,
                                champions = tierData.tierS,
                                badgeColor = Color(0xFFE5A93B),
                                headerGradient = Brush.horizontalGradient(
                                    listOf(Color(0xFF4A3800), HextechDarkBg)
                                ),
                                onChampionClick = { selectedChampionStats = it }
                            )
                        }
                    }

                    // TIER A
                    if (tierData.tierA.isNotEmpty()) {
                        item {
                            TierRowVisual(
                                grade = TierGrade.A,
                                champions = tierData.tierA,
                                badgeColor = HextechCyan,
                                headerGradient = Brush.horizontalGradient(
                                    listOf(Color(0xFF0D47A1), HextechDarkBg)
                                ),
                                onChampionClick = { selectedChampionStats = it }
                            )
                        }
                    }

                    // TIER B
                    if (tierData.tierB.isNotEmpty()) {
                        item {
                            TierRowVisual(
                                grade = TierGrade.B,
                                champions = tierData.tierB,
                                badgeColor = Color(0xFFBA68C8),
                                headerGradient = Brush.horizontalGradient(
                                    listOf(Color(0xFF38154D), HextechDarkBg)
                                ),
                                onChampionClick = { selectedChampionStats = it }
                            )
                        }
                    }

                    // TIER C
                    if (tierData.tierC.isNotEmpty()) {
                        item {
                            TierRowVisual(
                                grade = TierGrade.C,
                                champions = tierData.tierC,
                                badgeColor = DangerRed,
                                headerGradient = Brush.horizontalGradient(
                                    listOf(Color(0xFF4A1010), HextechDarkBg)
                                ),
                                onChampionClick = { selectedChampionStats = it }
                            )
                        }
                    }
                } else {
                    // Vista Analítica Detallada (Lista completa de tarjetas de campeón)
                    items(tierData.allRankedChampions, key = { it.championId + it.primaryRole.name }) { stats ->
                        PersonalChampionDetailedCard(
                            stats = stats,
                            onClick = { selectedChampionStats = stats }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }

    // Modal de Análisis Estadístico Profundo del Campeón
    if (selectedChampionStats != null) {
        PersonalChampionDetailModal(
            stats = selectedChampionStats!!,
            onDismiss = { selectedChampionStats = null },
            onSelectDraft = { draft ->
                selectedChampionStats = null
                onSelectDraftForDetail(draft)
            }
        )
    }
}

@Composable
private fun PersonalOverviewCard(overview: com.example.data.analytics.PersonalOverviewStats) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("personal_overview_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.2.dp, HextechGold.copy(alpha = 0.6f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(HextechGold.copy(alpha = 0.2f))
                            .border(1.dp, HextechGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = HextechGold, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(tr("Mi Perfil de Desempeño"), color = HextechGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Text("${overview.totalGames} " + tr("partidas analizadas"), color = TextMuted, fontSize = 11.sp)
                    }
                }

                val wrColor = if (overview.overallWinRate >= 50.0) Color(0xFF81C784) else DangerRed
                Surface(
                    color = wrColor.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, wrColor)
                ) {
                    Text(
                        text = "WR Global: ${overview.overallWinRate.toInt()}%",
                        color = wrColor,
                        fontWeight = FontWeight.Black,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 3 Column Metrics (Victorias / Derrotas / Mejor Rol)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(HextechSurfaceVariant)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(tr("Victorias"), color = TextMuted, fontSize = 10.sp)
                        Text(
                            text = "${overview.totalWins}W - ${overview.totalLosses}L",
                            color = Color(0xFF81C784),
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1.2f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(HextechSurfaceVariant)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(tr("Mejor Línea"), color = TextMuted, fontSize = 10.sp)
                        Text(
                            text = if (overview.bestRole != null) "${overview.bestRole.displayName} (${overview.bestRoleWinRate.toInt()}%)" else "N/A",
                            color = HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1.2f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(HextechSurfaceVariant)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(tr("Signature Pick"), color = TextMuted, fontSize = 10.sp)
                        Text(
                            text = overview.signatureChampion?.championName ?: "N/A",
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TierRowVisual(
    grade: TierGrade,
    champions: List<PersonalChampionStats>,
    badgeColor: Color,
    headerGradient: Brush,
    onChampionClick: (PersonalChampionStats) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("tier_row_${grade.name}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, badgeColor.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header del Tier
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(headerGradient)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(badgeColor)
                            .border(1.dp, HextechGoldLight, RoundedCornerShape(6.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = grade.label,
                            color = HextechDarkBg,
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = tr(grade.description),
                        color = TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Text(
                    text = "${champions.size} " + tr("campeones"),
                    color = TextMuted,
                    fontSize = 11.sp
                )
            }

            // Grid de Campeones en este Tier
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                champions.forEach { champStats ->
                    ChampionTierPill(
                        stats = champStats,
                        badgeColor = badgeColor,
                        onClick = { onChampionClick(champStats) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChampionTierPill(
    stats: PersonalChampionStats,
    badgeColor: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .testTag("tier_pill_${stats.championId}"),
        color = HextechSurfaceVariant,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, badgeColor.copy(alpha = 0.4f))
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                if (stats.avatarUrl.isNotBlank()) {
                    AppAssetImage(
                        url = stats.avatarUrl,
                        contentDescription = stats.championName,
                        fallbackText = stats.championName,
                        modifier = Modifier.size(46.dp),
                        borderColor = badgeColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                } else {
                    ChampionAvatar(
                        champion = Champion(name = stats.championName, ddragonId = stats.championId),
                        size = 46.dp
                    )
                }

                // Mini Role Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(4.dp))
                        .background(HextechDarkBg.copy(alpha = 0.85f))
                        .border(0.5.dp, HextechCyan, RoundedCornerShape(4.dp))
                        .padding(horizontal = 3.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = stats.primaryRole.shortName,
                        color = HextechCyan,
                        fontSize = 8.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stats.championName,
                color = TextPrimary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            // Winrate Badge
            val wrColor = if (stats.winRate >= 50.0) Color(0xFF81C784) else DangerRed
            Text(
                text = "${stats.winRate.toInt()}% (${stats.wins}W-${stats.losses}L)",
                color = wrColor,
                fontSize = 9.5.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun PersonalChampionDetailedCard(
    stats: PersonalChampionStats,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .testTag("personal_champ_card_${stats.championId}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (stats.avatarUrl.isNotBlank()) {
                        AppAssetImage(
                            url = stats.avatarUrl,
                            contentDescription = stats.championName,
                            fallbackText = stats.championName,
                            modifier = Modifier.size(44.dp),
                            borderColor = HextechGold,
                            shape = RoundedCornerShape(10.dp)
                        )
                    } else {
                        ChampionAvatar(
                            champion = Champion(name = stats.championName, ddragonId = stats.championId),
                            size = 44.dp
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stats.championName,
                                color = TextPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(HextechCyan.copy(alpha = 0.15f))
                                    .padding(horizontal = 5.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = stats.primaryRole.displayName,
                                    color = HextechCyan,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Text(
                            text = "${stats.totalGames} " + tr("partidas") + " • ${stats.wins}V - ${stats.losses}D",
                            color = TextMuted,
                            fontSize = 11.sp
                        )
                    }
                }

                // Tier Pill
                val tierBg = when (stats.tier) {
                    TierGrade.S_PLUS -> Color(0xFFFFD700)
                    TierGrade.S -> Color(0xFFE5A93B)
                    TierGrade.A -> HextechCyan
                    TierGrade.B -> Color(0xFFBA68C8)
                    TierGrade.C -> DangerRed
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(tierBg.copy(alpha = 0.2f))
                        .border(1.dp, tierBg, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "TIER ${stats.tier.label} (${stats.winRate.toInt()}%)",
                        color = tierBg,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Barra de Winrate
            LinearProgressIndicator(
                progress = { (stats.winRate / 100.0).toFloat().coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = if (stats.winRate >= 50.0) Color(0xFF81C784) else DangerRed,
                trackColor = HextechDarkBg
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stats.coachVerdict,
                color = TextSecondary,
                fontSize = 11.5.sp,
                lineHeight = 15.5.sp
            )
        }
    }
}

@Composable
private fun PersonalChampionDetailModal(
    stats: PersonalChampionStats,
    onDismiss: () -> Unit,
    onSelectDraft: (SavedDraftEntity) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (stats.avatarUrl.isNotBlank()) {
                        AppAssetImage(
                            url = stats.avatarUrl,
                            contentDescription = stats.championName,
                            fallbackText = stats.championName,
                            modifier = Modifier.size(40.dp),
                            borderColor = HextechGold,
                            shape = RoundedCornerShape(8.dp)
                        )
                    } else {
                        ChampionAvatar(
                            champion = Champion(name = stats.championName, ddragonId = stats.championId),
                            size = 40.dp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = stats.championName,
                            color = HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Tier ${stats.tier.label} • ${stats.primaryRole.displayName}",
                            color = HextechCyan,
                            fontSize = 11.5.sp
                        )
                    }
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Card de Resumen de Victorias
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(HextechDarkBg)
                        .border(1.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(tr("Tasa de Victoria Real"), color = TextMuted, fontSize = 11.sp)
                            Text(
                                text = "${stats.winRate.toInt()}% Win Rate",
                                color = if (stats.winRate >= 50.0) Color(0xFF81C784) else DangerRed,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Black
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(tr("Balance"), color = TextMuted, fontSize = 11.sp)
                            Text(
                                text = "${stats.wins} " + tr("Vic") + " - ${stats.losses} " + tr("Derr"),
                                color = TextPrimary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Desglose por Líneas / Roles
                if (stats.roleBreakdown.isNotEmpty()) {
                    Column {
                        Text(
                            text = tr("Rendimiento por Línea:"),
                            color = HextechGoldLight,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        stats.roleBreakdown.forEach { r ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(r.role.displayName, color = TextPrimary, fontSize = 11.5.sp)
                                Text(
                                    text = "${r.winRate.toInt()}% (${r.wins}W - ${r.losses}L)",
                                    color = if (r.winRate >= 50.0) Color(0xFF81C784) else DangerRed,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // Matchups Directos (Rivales en línea)
                if (stats.matchups.isNotEmpty()) {
                    Column {
                        Text(
                            text = tr("Enfrentamientos en Línea (Matchups):"),
                            color = HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        stats.matchups.take(4).forEach { m ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurfaceVariant)
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("vs ${m.opponentName}", color = TextPrimary, fontSize = 11.5.sp, fontWeight = FontWeight.Medium)
                                Text(
                                    text = "${m.wins}W - ${m.losses}L (${m.winRate.toInt()}%)",
                                    color = if (m.winRate >= 50.0) Color(0xFF81C784) else DangerRed,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                        }
                    }
                }

                // Veredicto del Coach Challenger
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechSurfaceVariant)
                        .border(1.dp, HextechGold.copy(alpha = 0.35f), RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = "🏆 Veredicto del Coach:",
                            color = HextechGold,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        FormattedWildRiftText(
                            text = stats.coachVerdict,
                            color = TextPrimary,
                            fontSize = 11.5.sp,
                            lineHeight = 15.5.sp
                        )
                    }
                }

                // Lista de partidas jugadas con este campeón (accesos directos)
                if (stats.draftMatches.isNotEmpty()) {
                    Column {
                        Text(
                            text = tr("Partidas Registradas con este Campeón:"),
                            color = HextechGoldLight,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        stats.draftMatches.take(3).forEach { draft ->
                            val isWin = draft.matchResult.equals("VICTORY", ignoreCase = true)
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .clickable { onSelectDraft(draft) },
                                color = HextechSurfaceVariant,
                                border = BorderStroke(0.8.dp, if (isWin) Color(0xFF81C784).copy(alpha = 0.4f) else DangerRed.copy(alpha = 0.4f)),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(draft.title, color = TextPrimary, fontSize = 11.sp, maxLines = 1)
                                        Text(draft.userRole, color = HextechCyan, fontSize = 9.5.sp)
                                    }
                                    Text(
                                        text = if (isWin) " Victoria ➔" else " Derrota ➔",
                                        color = if (isWin) Color(0xFF81C784) else DangerRed,
                                        fontSize = 10.5.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(tr("Cerrar"), color = HextechCyan, fontWeight = FontWeight.Bold)
            }
        },
        containerColor = HextechSurface,
        titleContentColor = HextechGold,
        textContentColor = TextPrimary
    )
}
