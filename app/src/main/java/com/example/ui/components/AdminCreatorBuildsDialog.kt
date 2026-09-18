package com.example.ui.components

import android.widget.Toast
import com.example.data.WildRiftRepository
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
import com.example.data.local.CustomChampionBuildRecord
import com.example.data.local.CustomChampionBuildsManager
import com.example.ui.theme.*

@Composable
fun AdminCreatorBuildsDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val customBuilds by CustomChampionBuildsManager.customBuilds.collectAsStateWithLifecycle()
    var showBuildCreator by remember { mutableStateOf(false) }
    var buildToEdit by remember { mutableStateOf<CustomChampionBuildRecord?>(null) }
    var selectedBuildForDetail by remember { mutableStateOf<CustomChampionBuildRecord?>(null) }

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

    androidx.activity.compose.BackHandler { onDismiss() }

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

                Text("Builds Publicadas y Usuarios Creadores (${customBuilds.size})", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                // Builds List
                if (customBuilds.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No hay builds creadas todavía.", color = TextSecondary, fontSize = 13.sp)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(customBuilds) { record ->
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

                                    if (record.coreItems.isNotEmpty()) {
                                        Text(
                                            text = "Core: ${record.coreItems.joinToString(", ")}",
                                            color = TextSecondary,
                                            fontSize = 10.sp
                                        )
                                    }
                                    if (record.situationalItems.isNotEmpty()) {
                                        Text(
                                            text = "Situacionales: ${record.situationalItems.joinToString(", ")}",
                                            color = TextSecondary,
                                            fontSize = 10.sp
                                        )
                                    }
                                    if (record.runes.isNotBlank()) {
                                        Text(
                                            text = "Runas: ${record.runes}",
                                            color = TextSecondary,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
