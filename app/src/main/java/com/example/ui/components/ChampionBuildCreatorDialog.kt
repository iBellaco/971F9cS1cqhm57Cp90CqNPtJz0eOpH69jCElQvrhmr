package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
import com.example.data.WildRiftRepository
import com.example.data.local.CustomChampionBuildRecord
import com.example.data.local.CustomChampionBuildsManager
import com.example.model.Champion
import com.example.model.WildRiftItem
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.ui.theme.*
import com.example.util.SubscriptionManager

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChampionBuildCreatorDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val champions = remember { WildRiftRepository.champions }
    val items = remember { WildRiftRepository.items }
    val runes = remember { WildRiftRepository.runes }
    val spells = remember { WildRiftRepository.summonerSpells }
    val creatorName by SubscriptionManager.userName.collectAsStateWithLifecycle()

    var selectedChampion by remember { mutableStateOf<Champion?>(champions.firstOrNull()) }
    var buildTitle by remember { mutableStateOf("") }
    
    val selectedCoreItems = remember { mutableStateListOf<String>() }
    val selectedSituationalItems = remember { mutableStateListOf<String>() }
    var runesInput by remember { mutableStateOf("Conquistador, Triunfo, Titán, Demolición") }
    val selectedSpells = remember { mutableStateListOf<String>() }

    var showChampionPicker by remember { mutableStateOf(false) }
    var showItemPickerForCore by remember { mutableStateOf(false) }
    var showItemPickerForSituational by remember { mutableStateOf(false) }
    var itemSearchQuery by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = BorderStroke(1.dp, HextechGold)
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
                            Icon(Icons.Default.Add, contentDescription = null, tint = HextechGold, modifier = Modifier.padding(6.dp).size(20.dp))
                        }
                        Column {
                            Text("Creador de Builds Oficiales", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Diseña configuraciones para el catálogo de campeones", color = TextSecondary, fontSize = 11.sp)
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.White)
                    }
                }

                HorizontalDivider(color = HextechCardBorder)

                // Form Content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // 1. Selector de Campeón
                    Text("1. Seleccionar Campeón", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showChampionPicker = true },
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                        border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val champ = selectedChampion ?: champions.firstOrNull()
                                if (champ != null) {
                                    ChampionAvatar(champion = champ, size = 36.dp)
                                }
                                Column {
                                    Text(
                                        text = champ?.name ?: "Seleccionar campeón...",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                    Text(
                                        text = "Rol: ${champ?.primaryRole?.displayName ?: "-"}",
                                        color = TextSecondary,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                            Text("Cambiar >", color = HextechGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    // 2. Título de la Build
                    Text("2. Título de la Build", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    OutlinedTextField(
                        value = buildTitle,
                        onValueChange = { buildTitle = it },
                        placeholder = { Text("Ej: Build DPS Absoluto, Tanque Imparable...", color = TextSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechSurfaceVariant,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )

                    // 3. Objetos Core (Núcleo)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("3. Objetos Core (${selectedCoreItems.size}/6)", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        TextButton(onClick = { showItemPickerForCore = true }) {
                            Text("+ Añadir Objeto Core", color = HextechGold, fontSize = 11.sp)
                        }
                    }
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        selectedCoreItems.forEach { itemName ->
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = HextechSurfaceVariant,
                                border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(itemName, color = Color.White, fontSize = 11.sp)
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Eliminar",
                                        tint = DangerRed,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .clickable { selectedCoreItems.remove(itemName) }
                                    )
                                }
                            }
                        }
                        if (selectedCoreItems.isEmpty()) {
                            Text("Ningún objeto core seleccionado.", color = TextSecondary, fontSize = 11.sp)
                        }
                    }

                    // 4. Objetos Situacionales
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("4. Objetos Situacionales", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        TextButton(onClick = { showItemPickerForSituational = true }) {
                            Text("+ Añadir Situacional", color = HextechGold, fontSize = 11.sp)
                        }
                    }
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        selectedSituationalItems.forEach { itemName ->
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = HextechSurfaceVariant,
                                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(itemName, color = Color.White, fontSize = 11.sp)
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Eliminar",
                                        tint = DangerRed,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .clickable { selectedSituationalItems.remove(itemName) }
                                    )
                                }
                            }
                        }
                        if (selectedSituationalItems.isEmpty()) {
                            Text("Ningún objeto situacional añadido.", color = TextSecondary, fontSize = 11.sp)
                        }
                    }

                    // 5. Runas Recomendadas
                    Text("5. Runas y Árbol Recomendado", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    OutlinedTextField(
                        value = runesInput,
                        onValueChange = { runesInput = it },
                        placeholder = { Text("Ej: Conquistador, Triunfo, Titan...", color = TextSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechSurfaceVariant,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )

                    // 6. Hechizos de Invocador
                    Text("6. Hechizos Recomendados", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(spells) { spell ->
                            val isSelected = selectedSpells.contains(spell.name)
                            Surface(
                                modifier = Modifier
                                    .clickable {
                                        if (isSelected) selectedSpells.remove(spell.name)
                                        else if (selectedSpells.size < 2) selectedSpells.add(spell.name)
                                    },
                                shape = RoundedCornerShape(6.dp),
                                color = if (isSelected) HextechGold.copy(alpha = 0.3f) else HextechSurfaceVariant,
                                border = BorderStroke(1.dp, if (isSelected) HextechGold else HextechCardBorder)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    if (isSelected) Icon(Icons.Default.Check, contentDescription = null, tint = HextechGold, modifier = Modifier.size(12.dp))
                                    Text(spell.name, color = if (isSelected) HextechGold else Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                // Footer Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar", color = TextSecondary)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            val champ = selectedChampion ?: champions.firstOrNull()
                            if (champ == null) {
                                Toast.makeText(context, "Selecciona un campeón", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (buildTitle.trim().isBlank()) {
                                Toast.makeText(context, "Ingresa un título para la build", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            val record = CustomChampionBuildRecord(
                                championId = champ.id,
                                championName = champ.name,
                                buildTitle = buildTitle.trim(),
                                role = champ.primaryRole.displayName,
                                coreItems = selectedCoreItems.toList(),
                                situationalItems = selectedSituationalItems.toList(),
                                runes = runesInput.trim(),
                                spells = selectedSpells.toList(),
                                creatorName = if (creatorName.isBlank()) "Creador Oficial" else creatorName
                            )

                            CustomChampionBuildsManager.addBuild(context, record)
                            Toast.makeText(context, "¡Build de ${champ.name} creada y publicada con éxito!", Toast.LENGTH_SHORT).show()
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                    ) {
                        Text("Publicar Build", color = HextechDarkBg, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    // Modal de selección de campeón
    if (showChampionPicker) {
        Dialog(onDismissRequest = { showChampionPicker = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Seleccionar Campeón", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    var query by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        placeholder = { Text("Buscar...", color = TextSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
                        singleLine = true
                    )
                    val filtered = remember(query, champions) {
                        if (query.isBlank()) champions else champions.filter { it.name.contains(query, ignoreCase = true) }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(filtered) { champ ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedChampion = champ
                                        showChampionPicker = false
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                ChampionAvatar(champion = champ, size = 32.dp)
                                Text(champ.name, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal de selección de objetos (Core o Situacional)
    val showItemPicker = showItemPickerForCore || showItemPickerForSituational
    if (showItemPicker) {
        Dialog(onDismissRequest = {
            showItemPickerForCore = false
            showItemPickerForSituational = false
        }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = if (showItemPickerForCore) "Seleccionar Objeto Core" else "Seleccionar Objeto Situacional",
                        color = HextechGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    OutlinedTextField(
                        value = itemSearchQuery,
                        onValueChange = { itemSearchQuery = it },
                        placeholder = { Text("Buscar objeto...", color = TextSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
                        singleLine = true
                    )
                    val filteredItems = remember(itemSearchQuery, items) {
                        if (itemSearchQuery.isBlank()) items else items.filter { it.name.contains(itemSearchQuery, ignoreCase = true) }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(filteredItems) { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        if (showItemPickerForCore) {
                                            if (!selectedCoreItems.contains(item.name)) selectedCoreItems.add(item.name)
                                            showItemPickerForCore = false
                                        } else {
                                            if (!selectedSituationalItems.contains(item.name)) selectedSituationalItems.add(item.name)
                                            showItemPickerForSituational = false
                                        }
                                        itemSearchQuery = ""
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                AppAssetImage(
                                    url = item.iconUrl,
                                    contentDescription = item.name,
                                    fallbackText = item.name.take(2),
                                    modifier = Modifier.size(28.dp).clip(RoundedCornerShape(4.dp))
                                )
                                Column {
                                    Text(item.name, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    Text("Oro: ${item.goldCost}", color = HextechGold, fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
