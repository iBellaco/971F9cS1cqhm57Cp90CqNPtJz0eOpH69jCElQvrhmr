package com.example.ui.components.admin

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
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
import com.example.model.ItemCategory
import com.example.model.WildRiftItem
import com.example.ui.components.AppAssetImage
import com.example.ui.theme.*
import com.example.util.tr
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminItemEditorTab() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ItemCategory?>(null) }
    var itemToEdit by remember { mutableStateOf<WildRiftItem?>(null) }
    var isCreatingNew by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<WildRiftItem?>(null) }
    var isSaving by remember { mutableStateOf(false) }

    val allItems = WildRiftRepository.items
    val filteredItems = remember(allItems, searchQuery, selectedCategory) {
        allItems.filter { item ->
            val matchCat = selectedCategory == null || item.category == selectedCategory
            val matchSearch = searchQuery.isBlank() ||
                    item.name.contains(searchQuery, ignoreCase = true) ||
                    item.id.contains(searchQuery, ignoreCase = true) ||
                    item.stats.contains(searchQuery, ignoreCase = true)
            matchCat && matchSearch
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Actions bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${filteredItems.size} ${tr("Objetos registrados")}",
                color = HextechCyan,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    isCreatingNew = true
                    itemToEdit = WildRiftItem(
                        id = "item_${System.currentTimeMillis()}",
                        name = "",
                        category = selectedCategory ?: ItemCategory.BASIC,
                        goldCost = 500,
                        stats = "",
                        passive = "",
                        iconUrl = ""
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(tr("Nuevo Ítem"), color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(tr("Buscar objeto para editar..."), color = TextMuted, fontSize = 13.sp) },
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

        // Category Filter chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                    label = { Text("${tr("Todos")} (${allItems.size})", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
            items(ItemCategory.entries) { cat ->
                val count = allItems.count { it.category == cat }
                FilterChip(
                    selected = selectedCategory == cat,
                    onClick = { selectedCategory = if (selectedCategory == cat) null else cat },
                    label = { Text("${cat.iconEmoji} ${tr(cat.displayName)} ($count)", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Items list
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredItems, key = { it.id }) { item ->
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
                            url = item.iconUrl,
                            contentDescription = item.name,
                            fallbackText = item.name,
                            modifier = Modifier.size(46.dp),
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
                                Text(
                                    text = tr(item.name),
                                    color = TextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.5.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "🟡 ${item.goldCost} G",
                                    color = HextechGold,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                            Text(
                                text = "${item.category.iconEmoji} ${tr(item.category.displayName)} • ID: ${item.id}",
                                color = HextechCyan,
                                fontSize = 10.5.sp
                            )
                            if (item.stats.isNotBlank()) {
                                Text(
                                    text = tr(item.stats),
                                    color = TextPrimary,
                                    fontSize = 11.5.sp,
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
                                    itemToEdit = item
                                },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = "Editar", tint = HextechCyan, modifier = Modifier.size(18.dp))
                            }
                            IconButton(
                                onClick = { itemToDelete = item },
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

    // Edit/Create Item Dialog
    itemToEdit?.let { currentItem ->
        var editId by remember { mutableStateOf(currentItem.id) }
        var editName by remember { mutableStateOf(currentItem.name) }
        var editCategory by remember { mutableStateOf(currentItem.category) }
        var editCost by remember { mutableStateOf(currentItem.goldCost.toString()) }
        var editStats by remember { mutableStateOf(currentItem.stats) }
        var editPassive by remember { mutableStateOf(currentItem.passive) }
        var editIconUrl by remember { mutableStateOf(currentItem.iconUrl) }

        Dialog(onDismissRequest = { if (!isSaving) itemToEdit = null }) {
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
                            text = if (isCreatingNew) tr("➕ Crear Nuevo Objeto") else tr("✏️ Editar Objeto"),
                            color = HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        IconButton(onClick = { itemToEdit = null }, enabled = !isSaving) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // ID Field
                    OutlinedTextField(
                        value = editId,
                        onValueChange = { if (isCreatingNew) editId = it },
                        readOnly = !isCreatingNew,
                        label = { Text("ID Único (ej. boots_of_speed_basic)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Name Field
                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text("Nombre del Ítem (ES / EN)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Category Selector
                    Text("Categoría:", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(ItemCategory.entries) { cat ->
                            FilterChip(
                                selected = editCategory == cat,
                                onClick = { editCategory = cat },
                                label = { Text("${cat.iconEmoji} ${cat.displayName}", fontSize = 10.5.sp) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Cost Field
                    OutlinedTextField(
                        value = editCost,
                        onValueChange = { editCost = it.filter { c -> c.isDigit() } },
                        label = { Text("Coste en Oro", fontSize = 11.sp) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Stats Field
                    OutlinedTextField(
                        value = editStats,
                        onValueChange = { editStats = it },
                        label = { Text("Estadísticas (ej. +25 Move Speed, +40 Armor)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Passive Field
                    OutlinedTextField(
                        value = editPassive,
                        onValueChange = { editPassive = it },
                        label = { Text("Descripción de Pasiva / Activa", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Image Picker / Uploader / URL Field
                    AdminImagePickerUploader(
                        label = "Ícono del Objeto (Galería o URL)",
                        imageUrl = editIconUrl,
                        onImageUrlChange = { editIconUrl = it },
                        imagePrefix = "item_${editId.ifBlank { "new" }}",
                        accentColor = HextechGold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (editId.isBlank() || editName.isBlank()) {
                                Toast.makeText(context, "El ID y Nombre son obligatorios", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            isSaving = true
                            val updatedItem = WildRiftItem(
                                id = editId.trim(),
                                name = editName.trim(),
                                category = editCategory,
                                goldCost = editCost.toIntOrNull() ?: 500,
                                stats = editStats.trim(),
                                passive = editPassive.trim(),
                                iconUrl = editIconUrl.trim()
                            )
                            scope.launch {
                                val result = WildRiftSupabaseRepository.saveItem(updatedItem)
                                isSaving = false
                                if (result.isSuccess) {
                                    WildRiftLocalCache.saveToLocalCache(context, items = WildRiftRepository.items)
                                    Toast.makeText(context, "¡Ítem guardado en Supabase y localmente!", Toast.LENGTH_SHORT).show()
                                    itemToEdit = null
                                } else {
                                    Toast.makeText(context, "Guardado localmente. (Error Supabase: ${result.exceptionOrNull()?.message})", Toast.LENGTH_LONG).show()
                                    WildRiftLocalCache.saveToLocalCache(context, items = WildRiftRepository.items)
                                    itemToEdit = null
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

    // Delete confirmation dialog
    itemToDelete?.let { item ->
        AlertDialog(
            onDismissRequest = { itemToDelete = null },
            title = { Text(tr("¿Eliminar Objeto?"), color = DangerRed, fontWeight = FontWeight.Bold) },
            text = { Text("¿Deseas eliminar permanentemente '${item.name}' (${item.id}) de la base de datos de Supabase y local?", color = TextPrimary) },
            confirmButton = {
                Button(
                    onClick = {
                        val id = item.id
                        itemToDelete = null
                        scope.launch {
                            WildRiftSupabaseRepository.deleteItem(id)
                            WildRiftLocalCache.saveToLocalCache(context, items = WildRiftRepository.items)
                            Toast.makeText(context, "Ítem eliminado", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                ) {
                    Text(tr("Eliminar"), color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { itemToDelete = null }) {
                    Text(tr("Cancelar"), color = TextMuted)
                }
            },
            containerColor = HextechDarkBg
        )
    }
}
