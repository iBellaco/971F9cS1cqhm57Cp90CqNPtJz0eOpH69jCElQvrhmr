package com.example.ui.components.admin

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.WildRiftRepository
import com.example.data.local.WildRiftLocalCache
import com.example.data.supabase.WildRiftSupabaseRepository
import com.example.model.MapObjectiveItem
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.ui.components.AppAssetImage
import com.example.ui.theme.*
import com.example.util.tr
import kotlinx.coroutines.launch

private enum class SubSection {
    RUNES, SPELLS, OBJECTIVES
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminRunesSpellsEditorTab() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var activeSubSection by remember { mutableStateOf(SubSection.RUNES) }
    var searchQuery by remember { mutableStateOf("") }

    // State for Rune Editing
    var runeToEdit by remember { mutableStateOf<RuneItem?>(null) }
    var isCreatingRune by remember { mutableStateOf(false) }
    var runeToDelete by remember { mutableStateOf<RuneItem?>(null) }

    // State for Spell Editing
    var spellToEdit by remember { mutableStateOf<SummonerSpellItem?>(null) }
    var isCreatingSpell by remember { mutableStateOf(false) }
    var spellToDelete by remember { mutableStateOf<SummonerSpellItem?>(null) }

    // State for Objective Editing
    var objectiveToEdit by remember { mutableStateOf<MapObjectiveItem?>(null) }

    var isSaving by remember { mutableStateOf(false) }

    val allRunes = WildRiftRepository.runes
    val allSpells = WildRiftRepository.summonerSpells
    val allObjectives = WildRiftRepository.mapObjectives

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Sub-tabs switch (Runas / Hechizos / Objetivos)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = activeSubSection == SubSection.RUNES,
                onClick = { activeSubSection = SubSection.RUNES },
                label = { Text("✨ Runas (${allRunes.size})", fontSize = 11.5.sp, fontWeight = FontWeight.Bold) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HextechGold,
                    selectedLabelColor = HextechDarkBg
                ),
                modifier = Modifier.weight(1f)
            )
            FilterChip(
                selected = activeSubSection == SubSection.SPELLS,
                onClick = { activeSubSection = SubSection.SPELLS },
                label = { Text("⚡ Hechizos (${allSpells.size})", fontSize = 11.5.sp, fontWeight = FontWeight.Bold) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HextechCyan,
                    selectedLabelColor = HextechDarkBg
                ),
                modifier = Modifier.weight(1f)
            )
            FilterChip(
                selected = activeSubSection == SubSection.OBJECTIVES,
                onClick = { activeSubSection = SubSection.OBJECTIVES },
                label = { Text("🐉 Objetivos (${allObjectives.size})", fontSize = 11.5.sp, fontWeight = FontWeight.Bold) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = TierSPlusColor,
                    selectedLabelColor = HextechDarkBg
                ),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Action / Add New bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = when (activeSubSection) {
                    SubSection.RUNES -> tr("Edición de Runas e Íconos")
                    SubSection.SPELLS -> tr("Edición de Hechizos e Íconos")
                    SubSection.OBJECTIVES -> tr("Edición de Objetivos e Íconos")
                },
                color = HextechGoldLight,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            if (activeSubSection == SubSection.RUNES) {
                Button(
                    onClick = {
                        isCreatingRune = true
                        runeToEdit = RuneItem(
                            id = "rune_${System.currentTimeMillis()}",
                            name = "",
                            category = "Clave",
                            iconUrl = "",
                            description = ""
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(tr("Nueva Runa"), color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                }
            } else if (activeSubSection == SubSection.SPELLS) {
                Button(
                    onClick = {
                        isCreatingSpell = true
                        spellToEdit = SummonerSpellItem(
                            id = "spell_${System.currentTimeMillis()}",
                            name = "",
                            cooldown = "120s",
                            iconUrl = "",
                            description = ""
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(tr("Nuevo Hechizo"), color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(tr("Buscar por nombre o ID..."), color = TextMuted, fontSize = 12.sp) },
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

        // Content Lists
        when (activeSubSection) {
            SubSection.RUNES -> {
                val filteredRunes = remember(allRunes, searchQuery) {
                    if (searchQuery.isBlank()) allRunes
                    else allRunes.filter { it.name.contains(searchQuery, true) || it.id.contains(searchQuery, true) || it.category.contains(searchQuery, true) }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredRunes, key = { it.id }) { rune ->
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
                                    url = rune.iconUrl,
                                    contentDescription = rune.name,
                                    fallbackText = rune.name,
                                    modifier = Modifier.size(44.dp),
                                    borderColor = HextechGold,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = rune.name,
                                        color = HextechGoldLight,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.5.sp
                                    )
                                    Text(
                                        text = "Rama: ${rune.category} • ID: ${rune.id}",
                                        color = HextechCyan,
                                        fontSize = 10.5.sp
                                    )
                                    if (rune.description.isNotBlank()) {
                                        Text(
                                            text = rune.description,
                                            color = TextPrimary,
                                            fontSize = 11.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                                Row {
                                    IconButton(
                                        onClick = {
                                            isCreatingRune = false
                                            runeToEdit = rune
                                        },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(Icons.Default.Edit, contentDescription = "Editar", tint = HextechCyan, modifier = Modifier.size(18.dp))
                                    }
                                    IconButton(
                                        onClick = { runeToDelete = rune },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                        }
                    }
                    item { Spacer(modifier = Modifier.height(24.dp)) }
                }
            }

            SubSection.SPELLS -> {
                val filteredSpells = remember(allSpells, searchQuery) {
                    if (searchQuery.isBlank()) allSpells
                    else allSpells.filter { it.name.contains(searchQuery, true) || it.id.contains(searchQuery, true) }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredSpells, key = { it.id }) { spell ->
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
                                    url = spell.iconUrl,
                                    contentDescription = spell.name,
                                    fallbackText = spell.name,
                                    modifier = Modifier.size(44.dp),
                                    borderColor = HextechCyan,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = spell.name,
                                            color = HextechGoldLight,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.5.sp
                                        )
                                        Text(
                                            text = "⏱️ ${spell.cooldown}",
                                            color = HextechCyan,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        text = "ID: ${spell.id}",
                                        color = TextMuted,
                                        fontSize = 10.5.sp
                                    )
                                    if (spell.description.isNotBlank()) {
                                        Text(
                                            text = spell.description,
                                            color = TextPrimary,
                                            fontSize = 11.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                                Row {
                                    IconButton(
                                        onClick = {
                                            isCreatingSpell = false
                                            spellToEdit = spell
                                        },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(Icons.Default.Edit, contentDescription = "Editar", tint = HextechCyan, modifier = Modifier.size(18.dp))
                                    }
                                    IconButton(
                                        onClick = { spellToDelete = spell },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                        }
                    }
                    item { Spacer(modifier = Modifier.height(24.dp)) }
                }
            }

            SubSection.OBJECTIVES -> {
                val filteredObjectives = remember(allObjectives, searchQuery) {
                    if (searchQuery.isBlank()) allObjectives
                    else allObjectives.filter { it.name.contains(searchQuery, true) || it.id.contains(searchQuery, true) }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredObjectives, key = { it.id }) { obj ->
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
                                    url = obj.iconUrl,
                                    contentDescription = obj.name,
                                    fallbackText = obj.name,
                                    modifier = Modifier.size(44.dp),
                                    borderColor = TierSPlusColor,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = obj.name,
                                        color = HextechGoldLight,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.5.sp
                                    )
                                    Text(
                                        text = "Aparición: ${obj.spawnTime} | Respawn: ${obj.respawnTime}",
                                        color = HextechCyan,
                                        fontSize = 10.5.sp
                                    )
                                    if (obj.buffDescription.isNotBlank()) {
                                        Text(
                                            text = obj.buffDescription,
                                            color = TextPrimary,
                                            fontSize = 11.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                                IconButton(
                                    onClick = { objectiveToEdit = obj },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar", tint = HextechCyan, modifier = Modifier.size(18.dp))
                                }
                            }
                        }
                    }
                    item { Spacer(modifier = Modifier.height(24.dp)) }
                }
            }
        }
    }

    // ==========================================
    // DIALOG: EDIT / CREATE RUNE
    // ==========================================
    runeToEdit?.let { currentRune ->
        var editId by remember { mutableStateOf(currentRune.id) }
        var editName by remember { mutableStateOf(currentRune.name) }
        var editCategory by remember { mutableStateOf(currentRune.category) }
        var editIconUrl by remember { mutableStateOf(currentRune.iconUrl) }
        var editDescription by remember { mutableStateOf(currentRune.description) }

        Dialog(onDismissRequest = { if (!isSaving) runeToEdit = null }) {
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
                            text = if (isCreatingRune) tr("➕ Crear Nueva Runa") else tr("✏️ Editar Runa"),
                            color = HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        IconButton(onClick = { runeToEdit = null }, enabled = !isSaving) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Live Icon Preview
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(HextechSurface, RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppAssetImage(
                            url = editIconUrl.trim(),
                            contentDescription = editName,
                            fallbackText = editName.ifBlank { "RN" },
                            modifier = Modifier.size(50.dp),
                            borderColor = HextechGold,
                            shape = RoundedCornerShape(8.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Vista Previa del Ícono", color = HextechGoldLight, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Text(
                                text = if (editIconUrl.isBlank()) "Sin URL (se usa monograma)" else "Cargando desde URL remota",
                                color = TextMuted,
                                fontSize = 10.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editId,
                        onValueChange = { if (isCreatingRune) editId = it },
                        readOnly = !isCreatingRune,
                        label = { Text("ID de Runa (ej. conqueror, kraken_slayer)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text("Nombre de la Runa", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Category selection
                    Text("Rama de la Runa:", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    val categories = listOf("Clave", "Dominación", "Precisión", "Valor", "Inspiración")
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(categories) { cat ->
                            FilterChip(
                                selected = editCategory == cat,
                                onClick = { editCategory = cat },
                                label = { Text(cat, fontSize = 10.5.sp) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editIconUrl,
                        onValueChange = { editIconUrl = it },
                        label = { Text("URL del Ícono (WebP / PNG / HTTPS)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editDescription,
                        onValueChange = { editDescription = it },
                        label = { Text("Efecto / Descripción de la Runa", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (editId.isBlank() || editName.isBlank()) {
                                Toast.makeText(context, "ID y Nombre son obligatorios", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            isSaving = true
                            val updatedRune = RuneItem(
                                id = editId.trim(),
                                name = editName.trim(),
                                category = editCategory.trim(),
                                iconUrl = editIconUrl.trim(),
                                description = editDescription.trim()
                            )
                            scope.launch {
                                val result = WildRiftSupabaseRepository.saveRune(updatedRune)
                                isSaving = false
                                if (result.isSuccess) {
                                    WildRiftLocalCache.saveToLocalCache(context, runes = WildRiftRepository.runes)
                                    Toast.makeText(context, "¡Runa guardada en Supabase y localmente!", Toast.LENGTH_SHORT).show()
                                    runeToEdit = null
                                } else {
                                    Toast.makeText(context, "Guardada localmente (Error Supabase: ${result.exceptionOrNull()?.message})", Toast.LENGTH_LONG).show()
                                    WildRiftLocalCache.saveToLocalCache(context, runes = WildRiftRepository.runes)
                                    runeToEdit = null
                                }
                            }
                        },
                        enabled = !isSaving,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        if (isSaving) {
                            CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(tr("Guardar Runa en Supabase y Local"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    // ==========================================
    // DIALOG: EDIT / CREATE SPELL
    // ==========================================
    spellToEdit?.let { currentSpell ->
        var editId by remember { mutableStateOf(currentSpell.id) }
        var editName by remember { mutableStateOf(currentSpell.name) }
        var editCooldown by remember { mutableStateOf(currentSpell.cooldown) }
        var editIconUrl by remember { mutableStateOf(currentSpell.iconUrl) }
        var editDescription by remember { mutableStateOf(currentSpell.description) }

        Dialog(onDismissRequest = { if (!isSaving) spellToEdit = null }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = BorderStroke(1.5.dp, HextechCyan)
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
                            text = if (isCreatingSpell) tr("➕ Crear Nuevo Hechizo") else tr("✏️ Editar Hechizo"),
                            color = HextechCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        IconButton(onClick = { spellToEdit = null }, enabled = !isSaving) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Live Icon Preview
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(HextechSurface, RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppAssetImage(
                            url = editIconUrl.trim(),
                            contentDescription = editName,
                            fallbackText = editName.ifBlank { "SP" },
                            modifier = Modifier.size(50.dp),
                            borderColor = HextechCyan,
                            shape = RoundedCornerShape(8.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Vista Previa del Ícono", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Text(
                                text = if (editIconUrl.isBlank()) "Sin URL (se usa monograma)" else "Cargando desde URL remota",
                                color = TextMuted,
                                fontSize = 10.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editId,
                        onValueChange = { if (isCreatingSpell) editId = it },
                        readOnly = !isCreatingSpell,
                        label = { Text("ID de Hechizo (ej. flash, ignite, smite)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text("Nombre del Hechizo", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editCooldown,
                        onValueChange = { editCooldown = it },
                        label = { Text("Enfriamiento (ej. 150s, 90s)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editIconUrl,
                        onValueChange = { editIconUrl = it },
                        label = { Text("URL del Ícono (WebP / PNG / HTTPS)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editDescription,
                        onValueChange = { editDescription = it },
                        label = { Text("Descripción del Hechizo", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (editId.isBlank() || editName.isBlank()) {
                                Toast.makeText(context, "ID y Nombre son obligatorios", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            isSaving = true
                            val updatedSpell = SummonerSpellItem(
                                id = editId.trim(),
                                name = editName.trim(),
                                cooldown = editCooldown.trim(),
                                iconUrl = editIconUrl.trim(),
                                description = editDescription.trim()
                            )
                            scope.launch {
                                val result = WildRiftSupabaseRepository.saveSpell(updatedSpell)
                                isSaving = false
                                if (result.isSuccess) {
                                    WildRiftLocalCache.saveToLocalCache(context, spells = WildRiftRepository.summonerSpells)
                                    Toast.makeText(context, "¡Hechizo guardado en Supabase y localmente!", Toast.LENGTH_SHORT).show()
                                    spellToEdit = null
                                } else {
                                    Toast.makeText(context, "Guardado localmente (Error Supabase: ${result.exceptionOrNull()?.message})", Toast.LENGTH_LONG).show()
                                    WildRiftLocalCache.saveToLocalCache(context, spells = WildRiftRepository.summonerSpells)
                                    spellToEdit = null
                                }
                            }
                        },
                        enabled = !isSaving,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        if (isSaving) {
                            CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(tr("Guardar Hechizo en Supabase y Local"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    // ==========================================
    // DIALOG: EDIT MAP OBJECTIVE
    // ==========================================
    objectiveToEdit?.let { currentObj ->
        var editName by remember { mutableStateOf(currentObj.name) }
        var editSpawn by remember { mutableStateOf(currentObj.spawnTime) }
        var editRespawn by remember { mutableStateOf(currentObj.respawnTime) }
        var editIconUrl by remember { mutableStateOf(currentObj.iconUrl) }
        var editBuff by remember { mutableStateOf(currentObj.buffDescription) }
        var editTactics by remember { mutableStateOf(currentObj.tactics) }

        Dialog(onDismissRequest = { objectiveToEdit = null }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = BorderStroke(1.5.dp, TierSPlusColor)
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
                            text = tr("✏️ Editar Objetivo del Mapa"),
                            color = TierSPlusColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        IconButton(onClick = { objectiveToEdit = null }) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Live Icon Preview
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(HextechSurface, RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppAssetImage(
                            url = editIconUrl.trim(),
                            contentDescription = editName,
                            fallbackText = editName.ifBlank { "OBJ" },
                            modifier = Modifier.size(50.dp),
                            borderColor = TierSPlusColor,
                            shape = RoundedCornerShape(8.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Vista Previa del Ícono", color = TierSPlusColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Text(
                                text = if (editIconUrl.isBlank()) "Sin URL (se usa monograma)" else "Cargando desde URL remota",
                                color = TextMuted,
                                fontSize = 10.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text("Nombre del Objetivo", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = editSpawn,
                            onValueChange = { editSpawn = it },
                            label = { Text("Aparición (ej. 4:00 min)", fontSize = 11.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = editRespawn,
                            onValueChange = { editRespawn = it },
                            label = { Text("Respawn (ej. 5:00 min)", fontSize = 11.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editIconUrl,
                        onValueChange = { editIconUrl = it },
                        label = { Text("URL del Ícono", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editBuff,
                        onValueChange = { editBuff = it },
                        label = { Text("Descripción de Buff / Recompensa", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editTactics,
                        onValueChange = { editTactics = it },
                        label = { Text("Estrategia / Táctica de Control", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val updatedObj = currentObj.copy(
                                name = editName.trim(),
                                spawnTime = editSpawn.trim(),
                                respawnTime = editRespawn.trim(),
                                iconUrl = editIconUrl.trim(),
                                buffDescription = editBuff.trim(),
                                tactics = editTactics.trim()
                            )
                            val list = WildRiftRepository.mapObjectives.toMutableList()
                            val idx = list.indexOfFirst { it.id == currentObj.id }
                            if (idx != -1) list[idx] = updatedObj else list.add(updatedObj)
                            WildRiftRepository.mapObjectives = list

                            WildRiftLocalCache.saveToLocalCache(context, objectives = list)
                            Toast.makeText(context, "¡Objetivo guardado en la caché local!", Toast.LENGTH_SHORT).show()
                            objectiveToEdit = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = TierSPlusColor),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(tr("Guardar Objetivo Localmente"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    // Delete Rune Confirmation Dialog
    runeToDelete?.let { rune ->
        AlertDialog(
            onDismissRequest = { runeToDelete = null },
            title = { Text(tr("¿Eliminar Runa?"), color = DangerRed, fontWeight = FontWeight.Bold) },
            text = { Text("¿Deseas eliminar permanentemente '${rune.name}' de Supabase y localmente?", color = TextPrimary) },
            confirmButton = {
                Button(
                    onClick = {
                        val id = rune.id
                        runeToDelete = null
                        scope.launch {
                            WildRiftSupabaseRepository.deleteRune(id)
                            WildRiftLocalCache.saveToLocalCache(context, runes = WildRiftRepository.runes)
                            Toast.makeText(context, "Runa eliminada", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                ) {
                    Text(tr("Eliminar"), color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { runeToDelete = null }) {
                    Text(tr("Cancelar"), color = TextMuted)
                }
            },
            containerColor = HextechDarkBg
        )
    }

    // Delete Spell Confirmation Dialog
    spellToDelete?.let { spell ->
        AlertDialog(
            onDismissRequest = { spellToDelete = null },
            title = { Text(tr("¿Eliminar Hechizo?"), color = DangerRed, fontWeight = FontWeight.Bold) },
            text = { Text("¿Deseas eliminar permanentemente '${spell.name}' de Supabase y localmente?", color = TextPrimary) },
            confirmButton = {
                Button(
                    onClick = {
                        val id = spell.id
                        spellToDelete = null
                        scope.launch {
                            WildRiftSupabaseRepository.deleteSpell(id)
                            WildRiftLocalCache.saveToLocalCache(context, spells = WildRiftRepository.summonerSpells)
                            Toast.makeText(context, "Hechizo eliminado", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                ) {
                    Text(tr("Eliminar"), color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { spellToDelete = null }) {
                    Text(tr("Cancelar"), color = TextMuted)
                }
            },
            containerColor = HextechDarkBg
        )
    }
}
