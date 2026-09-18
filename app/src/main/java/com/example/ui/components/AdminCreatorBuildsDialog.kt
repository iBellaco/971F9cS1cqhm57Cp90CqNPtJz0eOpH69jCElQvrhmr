package com.example.ui.components

import android.widget.Toast
import com.example.data.WildRiftRepository
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.filled.Favorite
import com.example.data.local.AppDatabase
import com.example.data.local.CustomChampionBuildRecord
import com.example.data.local.CustomChampionBuildsManager
import com.example.ui.theme.*

enum class BuildsFilterTab {
    ALL,
    FAVORITES
}

@Composable
fun AdminCreatorBuildsDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val customBuilds by CustomChampionBuildsManager.customBuilds.collectAsStateWithLifecycle()
    var showBuildCreator by remember { mutableStateOf(false) }
    var buildToEdit by remember { mutableStateOf<CustomChampionBuildRecord?>(null) }
    var selectedBuildForDetail by remember { mutableStateOf<CustomChampionBuildRecord?>(null) }

    val favoriteDao = remember { AppDatabase.getDatabase(context).favoriteBuildsDao() }
    val favorites by favoriteDao.getAllFavorites().collectAsStateWithLifecycle(initialValue = emptyList())
    var selectedFilter by remember { mutableStateOf(BuildsFilterTab.ALL) }

    val filteredBuilds = remember(customBuilds, favorites, selectedFilter) {
        when (selectedFilter) {
            BuildsFilterTab.ALL -> customBuilds
            BuildsFilterTab.FAVORITES -> {
                val favIds = favorites.map { it.buildId }.toSet()
                customBuilds.filter { it.id in favIds }
            }
        }
    }

    LaunchedEffect(Unit) {
        CustomChampionBuildsManager.init(context)
    }

    if (showBuildCreator || buildToEdit != null) {
        ChampionBuildCreatorDialog(
            existingRecord = buildToEdit,
            onDismiss = {
                showBuildCreator = false
                buildToEdit = null
            }
        )
    }

    if (selectedBuildForDetail != null) {
        CustomBuildDetailDialog(
            record = selectedBuildForDetail!!,
            onDismiss = { selectedBuildForDetail = null }
        )
    }

    androidx.activity.compose.BackHandler {
        if (selectedBuildForDetail != null) {
            selectedBuildForDetail = null
        } else if (showBuildCreator || buildToEdit != null) {
            showBuildCreator = false
            buildToEdit = null
        } else {
            onDismiss()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = HextechSurface
    ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = HextechGold.copy(alpha = 0.2f)
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = HextechGold, modifier = Modifier.padding(6.dp).size(20.dp))
                        }
                        Column {
                            Text("Panel de Creador (Admin)", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Gestión de builds y creadores registrados", color = TextSecondary, fontSize = 11.sp)
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.White)
                    }
                }

                HorizontalDivider(color = HextechCardBorder)

                // Action Button: Crear Build
                Button(
                    onClick = { showBuildCreator = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Crear Nueva Build Oficial", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterChip(
                        selected = selectedFilter == BuildsFilterTab.ALL,
                        onClick = { selectedFilter = BuildsFilterTab.ALL },
                        label = { Text("Todas (${customBuilds.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = HextechGold.copy(alpha = 0.2f),
                            selectedLabelColor = HextechGold
                        )
                    )
                    FilterChip(
                        selected = selectedFilter == BuildsFilterTab.FAVORITES,
                        onClick = { selectedFilter = BuildsFilterTab.FAVORITES },
                        label = { Text("Mis Favoritos (${favorites.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = DangerRed.copy(alpha = 0.2f),
                            selectedLabelColor = DangerRed
                        ),
                        leadingIcon = {
                            Icon(Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(16.dp), tint = DangerRed)
                        }
                    )
                }

                // Builds List
                if (filteredBuilds.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(if (selectedFilter == BuildsFilterTab.FAVORITES) "No tienes builds favoritas guardadas." else "No hay builds creadas todavía.", color = TextSecondary, fontSize = 13.sp)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredBuilds) { record ->
                            val champObj = remember(record.championId) {
                                WildRiftRepository.champions.find { it.id.equals(record.championId, ignoreCase = true) }
                            }
                            val avgRating = if (record.voteCount > 0) record.ratingSum / record.voteCount else 0.0

                            Card(
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechCardBorder),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedBuildForDetail = record }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            if (champObj != null) {
                                                ChampionAvatar(champion = champObj, size = 40.dp, showTierBadge = false)
                                            }
                                            Column {
                                                Text(
                                                    text = "${record.championName} - ${record.buildTitle}",
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 13.sp
                                                )
                                                Text(
                                                    text = "Creador: ${record.creatorName} | Rol: ${record.role}",
                                                    color = HextechGold,
                                                    fontSize = 11.sp
                                                )
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                                ) {
                                                    Icon(Icons.Default.Star, contentDescription = null, tint = HextechGold, modifier = Modifier.size(12.dp))
                                                    Text(
                                                        text = "${String.format("%.1f", avgRating)} (${record.voteCount} votos)",
                                                        color = TextSecondary,
                                                        fontSize = 10.sp
                                                    )
                                                }
                                            }
                                        }

                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            IconButton(
                                                onClick = { buildToEdit = record },
                                                modifier = Modifier.size(32.dp)
                                            ) {
                                                Icon(Icons.Default.Edit, contentDescription = "Editar", tint = HextechGold, modifier = Modifier.size(16.dp))
                                            }
                                            IconButton(
                                                onClick = {
                                                    CustomChampionBuildsManager.deleteBuild(context, record.id)
                                                    Toast.makeText(context, "Build eliminada", Toast.LENGTH_SHORT).show()
                                                },
                                                modifier = Modifier.size(32.dp)
                                            ) {
                                                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(16.dp))
                                            }
                                        }
                                    }

                                    // Animated text prompting to tap
                                    val infiniteTransition = rememberInfiniteTransition()
                                    val alpha by infiniteTransition.animateFloat(
                                        initialValue = 0.3f,
                                        targetValue = 1f,
                                        animationSpec = infiniteRepeatable(
                                            animation = tween(1000),
                                            repeatMode = RepeatMode.Reverse
                                        )
                                    )
                                    Text(
                                        text = "Presiona para ver completo",
                                        color = HextechGold.copy(alpha = alpha),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
