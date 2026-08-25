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
import com.example.model.WildRiftItem
import com.example.ui.components.AppAssetImage
import com.example.ui.theme.*
import com.example.util.tr
import kotlinx.coroutines.launch
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AdminItemEditorTab() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var itemToEdit by remember { mutableStateOf<WildRiftItem?>(null) }
    var isCreatingNew by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<WildRiftItem?>(null) }

    val items = WildRiftRepository.items.filter { selectedCategory.isNullOrBlank() || it.category.equals(selectedCategory, ignoreCase=true) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(tr("Base de Datos de Objetos"), color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Button(
                onClick = { 
                    itemToEdit = WildRiftItem(id = "", name = "", category = "Básicos", goldCost = 500, stats = "", passive = "", iconUrl = "")
                    isCreatingNew = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = HextechCyan)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(tr("Nuevo Objeto"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            val dynamicCats = WildRiftRepository.items.map { it.category }.distinct()
            items(dynamicCats) { cat ->
                FilterChip(
                    selected = selectedCategory?.equals(cat) == true,
                    onClick = { selectedCategory = if (selectedCategory == cat) null else cat },
                    label = { Text(cat, fontSize = 11.5.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HextechCyan,
                        selectedLabelColor = HextechDarkBg
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { 
                        itemToEdit = item
                        isCreatingNew = false
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppAssetImage(
                            url = item.iconUrl,
                            contentDescription = item.name,
fallbackText = item.name,
                            modifier = Modifier.size(48.dp).clip(RoundedCornerShape(8.dp)).border(1.dp, HextechGold, RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(tr(item.name), color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text("${item.goldCost} 💰", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                        IconButton(onClick = { itemToEdit = item; isCreatingNew = false }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit", tint = HextechCyan, modifier = Modifier.size(20.dp))
                        }
                        IconButton(onClick = { itemToDelete = item }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = DangerRed, modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }
        }
    }

    itemToEdit?.let { currentItem ->
        var editId by remember { mutableStateOf(currentItem.id) }
        var editName by remember { mutableStateOf(currentItem.name) }
        var editCategory by remember { mutableStateOf(currentItem.category) }
        var editCost by remember { mutableStateOf(currentItem.goldCost.toString()) }
        var editStats by remember { mutableStateOf(currentItem.stats) }
        var editPassive by remember { mutableStateOf(currentItem.passive) }
        var editIconUrl by remember { mutableStateOf(currentItem.iconUrl) }
        var isSaving by remember { mutableStateOf(false) }

        Dialog(onDismissRequest = { if (!isSaving) itemToEdit = null }) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = BorderStroke(1.dp, HextechGold)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp).verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(if (isCreatingNew) tr("Crear Nuevo Objeto") else tr("Editar Objeto"), color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        IconButton(onClick = { if (!isSaving) itemToEdit = null }) {
                            Icon(Icons.Default.Close, contentDescription = "Close", tint = TextMuted)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = editId,
                        onValueChange = { if (isCreatingNew) editId = it },
                        readOnly = !isCreatingNew,
                        label = { Text("ID Único (ej. boots_of_speed_basic)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechCyan, unfocusedBorderColor = HextechCardBorder)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text("Nombre del Ítem (ES / EN)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechCyan, unfocusedBorderColor = HextechCardBorder)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editCategory,
                        onValueChange = { editCategory = it },
                        label = { Text("Categoría (ej. Básicos, Defensa, Daño Físico)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechCyan, unfocusedBorderColor = HextechCardBorder)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editCost,
                        onValueChange = { editCost = it.filter { c -> c.isDigit() } },
                        label = { Text("Coste en Oro", fontSize = 11.sp) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechCyan, unfocusedBorderColor = HextechCardBorder)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editStats,
                        onValueChange = { editStats = it },
                        label = { Text("Estadísticas (ej. +25 Move Speed, +40 Armor)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechCyan, unfocusedBorderColor = HextechCardBorder)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editPassive,
                        onValueChange = { editPassive = it },
                        label = { Text("Descripción de Pasiva / Activa", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechCyan, unfocusedBorderColor = HextechCardBorder)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

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
                                category = editCategory.trim().ifBlank { "Básicos" },
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
