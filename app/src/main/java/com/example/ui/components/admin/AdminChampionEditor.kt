package com.example.ui.components.admin

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.WildRiftRepository
import com.example.data.local.WildRiftLocalCache
import com.example.data.supabase.WildRiftSupabaseRepository
import com.example.model.Champion
import com.example.model.DamageType
import com.example.model.LaneRole
import com.example.ui.components.AppAssetImage
import com.example.ui.theme.*
import com.example.util.tr
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminChampionEditorTab() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var searchQuery by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf<LaneRole?>(null) }
    var champToEdit by remember { mutableStateOf<Champion?>(null) }
    var isCreatingNew by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }
    var champToDelete by remember { mutableStateOf<Champion?>(null) }

    val allChamps = WildRiftRepository.champions
    val filteredChamps = remember(allChamps, searchQuery, selectedRole) {
        allChamps.filter { champ ->
            val matchRole = selectedRole == null || champ.primaryRole == selectedRole || champ.secondaryRoles.contains(selectedRole)
            val matchSearch = searchQuery.isBlank() ||
                    champ.name.contains(searchQuery, ignoreCase = true) ||
                    champ.id.contains(searchQuery, ignoreCase = true) ||
                    champ.title.contains(searchQuery, ignoreCase = true)
            matchRole && matchSearch
        }.sortedWith(
            compareByDescending<Champion> { it.tier == "S+" }
                .thenByDescending { it.tier == "S" }
                .thenByDescending { it.winrate }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${filteredChamps.size} ${tr("Campeones registrados")}",
                color = HextechCyan,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    isCreatingNew = true
                    champToEdit = Champion(
                        id = "champ_${System.currentTimeMillis()}",
                        name = "",
                        title = "",
                        primaryRole = selectedRole ?: LaneRole.TOP,
                        secondaryRoles = emptyList(),
                        tier = "A",
                        winrate = 50.0,
                        pickRate = 5.0,
                        banRate = 2.0,
                        damageType = DamageType.PHYSICAL,
                        avatarUrl = "",
                        counteredBy = emptyList(),
                        synergies = emptyList()
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(tr("Nuevo Campeón"), color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(tr("Buscar campeón para editar meta/build..."), color = TextMuted, fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = null, tint = TextMuted)
                    }
                }
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = HextechCyan,
                unfocusedBorderColor = HextechCardBorder,
                focusedContainerColor = HextechSurface,
                unfocusedContainerColor = HextechSurface,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                FilterChip(
                    selected = selectedRole == null,
                    onClick = { selectedRole = null },
                    label = { Text("${tr("Todos")} (${allChamps.size})", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
            items(LaneRole.entries) { role ->
                val count = allChamps.count { it.primaryRole == role || it.secondaryRoles.contains(role) }
                FilterChip(
                    selected = selectedRole == role,
                    onClick = { selectedRole = if (selectedRole == role) null else role },
                    label = { Text("${role.displayName} ($count)", fontSize = 11.sp) },
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
            items(filteredChamps, key = { it.id }) { champ ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppAssetImage(
                            url = champ.avatarUrl,
                            contentDescription = champ.name,
                            fallbackText = champ.name,
                            modifier = Modifier.size(46.dp),
                            borderColor = if (champ.tier == "S+" || champ.tier == "S") HextechGold else HextechCyan,
                            shape = RoundedCornerShape(8.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = champ.name,
                                    color = HextechGoldLight,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.5.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "Tier ${champ.tier}",
                                    color = if (champ.tier.startsWith("S")) HextechGold else HextechCyan,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.5.sp
                                )
                            }
                            Text(
                                text = "${champ.primaryRole.displayName} • WR: ${champ.winrate}% | PR: ${champ.pickRate}%",
                                color = TextPrimary,
                                fontSize = 11.sp
                            )
                            if (champ.counteredBy.isNotEmpty()) {
                                Text(
                                    text = "Counters: ${champ.counteredBy.take(3).joinToString(", ")}",
                                    color = TextMuted,
                                    fontSize = 10.5.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Row {
                            IconButton(
                                onClick = {
                                    isCreatingNew = false
                                    champToEdit = champ
                                },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = "Editar", tint = HextechCyan, modifier = Modifier.size(18.dp))
                            }
                            IconButton(
                                onClick = { champToDelete = champ },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Borrar", tint = DangerRed, modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    // Dialog for Editing/Creating Champion
    champToEdit?.let { currentChamp ->
        var editId by remember { mutableStateOf(currentChamp.id) }
        var editName by remember { mutableStateOf(currentChamp.name) }
        var editTitle by remember { mutableStateOf(currentChamp.title) }
        var editRole by remember { mutableStateOf(currentChamp.primaryRole) }
        var editTier by remember { mutableStateOf(currentChamp.tier) }
        var editWinrate by remember { mutableStateOf(currentChamp.winrate.toString()) }
        var editPickrate by remember { mutableStateOf(currentChamp.pickRate.toString()) }
        var editBanrate by remember { mutableStateOf(currentChamp.banRate.toString()) }
        var editDamageType by remember { mutableStateOf(currentChamp.damageType) }
        var editAvatarUrl by remember { mutableStateOf(currentChamp.avatarUrl) }
        var editCounters by remember { mutableStateOf(currentChamp.counteredBy.joinToString(", ")) }
        var editSynergies by remember { mutableStateOf(currentChamp.synergies.joinToString(", ")) }
        var editCoreItems by remember { mutableStateOf(currentChamp.coreItems.joinToString(", ")) }

        Dialog(onDismissRequest = { if (!isSaving) champToEdit = null }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = BorderStroke(1.5.dp, HextechGold)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isCreatingNew) tr("➕ Crear Nuevo Campeón") else tr("✏️ Editar Campeón"),
                            color = HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        IconButton(onClick = { champToEdit = null }, enabled = !isSaving) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = editId,
                        onValueChange = { if (isCreatingNew) editId = it },
                        readOnly = !isCreatingNew,
                        label = { Text("ID Único (ej. aatrox)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text("Nombre del Campeón", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Role Selector
                    Text("Rol Primario:", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(LaneRole.entries) { r ->
                            FilterChip(
                                selected = editRole == r,
                                onClick = { editRole = r },
                                label = { Text(r.displayName, fontSize = 10.5.sp) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Tier Selector
                    Text("Tier del Meta:", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    val tiers = listOf("S+", "S", "A", "B", "C", "D")
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(tiers) { t ->
                            FilterChip(
                                selected = editTier == t,
                                onClick = { editTier = t },
                                label = { Text(t, fontSize = 10.5.sp) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Winrate, Pickrate, Banrate
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        OutlinedTextField(
                            value = editWinrate,
                            onValueChange = { editWinrate = it },
                            label = { Text("Winrate %", fontSize = 10.sp) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = editPickrate,
                            onValueChange = { editPickrate = it },
                            label = { Text("Pickrate %", fontSize = 10.sp) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = editBanrate,
                            onValueChange = { editBanrate = it },
                            label = { Text("Banrate %", fontSize = 10.sp) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editCounters,
                        onValueChange = { editCounters = it },
                        label = { Text("Counters (separados por coma)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editSynergies,
                        onValueChange = { editSynergies = it },
                        label = { Text("Sinergias (separadas por coma)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editCoreItems,
                        onValueChange = { editCoreItems = it },
                        label = { Text("Ítems Core Recomendados (separados por coma)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editAvatarUrl,
                        onValueChange = { editAvatarUrl = it },
                        label = { Text("Avatar URL", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (editId.isBlank() || editName.isBlank()) {
                                Toast.makeText(context, "ID y Nombre son obligatorios", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            isSaving = true
                            val updatedChamp = currentChamp.copy(
                                id = editId.trim(),
                                name = editName.trim(),
                                title = editTitle.trim(),
                                primaryRole = editRole,
                                tier = editTier,
                                winrate = editWinrate.toDoubleOrNull() ?: 50.0,
                                pickRate = editPickrate.toDoubleOrNull() ?: 5.0,
                                banRate = editBanrate.toDoubleOrNull() ?: 2.0,
                                damageType = editDamageType,
                                avatarUrl = editAvatarUrl.trim(),
                                counteredBy = editCounters.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                                synergies = editSynergies.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                                coreItems = editCoreItems.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                            )
                            scope.launch {
                                val result = WildRiftSupabaseRepository.saveChampion(updatedChamp)
                                isSaving = false
                                if (result.isSuccess) {
                                    WildRiftLocalCache.saveToLocalCache(context, champions = WildRiftRepository.champions)
                                    Toast.makeText(context, "¡Campeón guardado en Supabase y localmente!", Toast.LENGTH_SHORT).show()
                                    champToEdit = null
                                } else {
                                    Toast.makeText(context, "Guardado localmente. (Error Supabase: ${result.exceptionOrNull()?.message})", Toast.LENGTH_LONG).show()
                                    WildRiftLocalCache.saveToLocalCache(context, champions = WildRiftRepository.champions)
                                    champToEdit = null
                                }
                            }
                        },
                        enabled = !isSaving,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        if (isSaving) {
                            CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(tr("Guardar en Supabase y Local"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    // Delete dialog
    champToDelete?.let { champ ->
        AlertDialog(
            onDismissRequest = { champToDelete = null },
            title = { Text(tr("¿Eliminar Campeón?"), color = DangerRed, fontWeight = FontWeight.Bold) },
            text = { Text("¿Deseas eliminar permanentemente '${champ.name}' (${champ.id})?", color = TextPrimary) },
            confirmButton = {
                Button(
                    onClick = {
                        val id = champ.id
                        champToDelete = null
                        scope.launch {
                            WildRiftSupabaseRepository.deleteChampion(id)
                            WildRiftLocalCache.saveToLocalCache(context, champions = WildRiftRepository.champions)
                            Toast.makeText(context, "Campeón eliminado", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                ) {
                    Text(tr("Eliminar"), color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { champToDelete = null }) {
                    Text(tr("Cancelar"), color = TextMuted)
                }
            },
            containerColor = HextechDarkBg
        )
    }
}
