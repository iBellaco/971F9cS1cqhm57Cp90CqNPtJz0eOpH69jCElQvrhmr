package com.example.ui.components

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Videocam
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
import com.example.data.local.ItemBuildEntry
import com.example.data.local.RuneBuildEntry
import com.example.data.local.SpellBuildEntry
import com.example.model.Champion
import com.example.model.WildRiftItem
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.ui.theme.*
import com.example.util.SubscriptionManager

class EditableItemEntry(
    val name: String,
    val iconUrl: String,
    initialDesc: String = ""
) {
    var description by mutableStateOf(initialDesc)
}

class EditableRuneEntry(
    val name: String,
    val iconUrl: String,
    initialDesc: String = ""
) {
    var description by mutableStateOf(initialDesc)
}

class EditableSpellEntry(
    val name: String,
    val iconUrl: String,
    initialDesc: String = ""
) {
    var description by mutableStateOf(initialDesc)
}

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
    
    val coreItems = remember { mutableStateListOf<EditableItemEntry>() }
    val situationalItems = remember { mutableStateListOf<EditableItemEntry>() }
    val coreRunes = remember { mutableStateListOf<EditableRuneEntry>() }
    val situationalRunes = remember { mutableStateListOf<EditableRuneEntry>() }
    val coreSpells = remember { mutableStateListOf<EditableSpellEntry>() }
    val situationalSpells = remember { mutableStateListOf<EditableSpellEntry>() }
    var gameplayVideoUri by remember { mutableStateOf<String?>(null) }

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val size = inputStream?.available() ?: 0
                inputStream?.close()
                if (size > 20 * 1024 * 1024) {
                    Toast.makeText(context, "El video supera el límite máximo de 20MB", Toast.LENGTH_SHORT).show()
                } else {
                    gameplayVideoUri = uri.toString()
                    Toast.makeText(context, "Gameplay MP4 adjuntado con éxito", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error al adjuntar video", Toast.LENGTH_SHORT).show()
            }
        }
    }

    var showChampionPicker by remember { mutableStateOf(false) }
    var showItemPickerForCore by remember { mutableStateOf(false) }
    var showItemPickerForSituational by remember { mutableStateOf(false) }
    var showRunePickerForCore by remember { mutableStateOf(false) }
    var showRunePickerForSituational by remember { mutableStateOf(false) }
    var showSpellPickerForCore by remember { mutableStateOf(false) }
    var showSpellPickerForSituational by remember { mutableStateOf(false) }
    var searchFilterQuery by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f),
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
                            Text("Configuración avanzada con imágenes y descripciones", color = TextSecondary, fontSize = 11.sp)
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
                    verticalArrangement = Arrangement.spacedBy(14.dp)
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

                    // 3. Objetos Core (Con descripción obligatoria)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("3. Objetos Core (${coreItems.size}/6) *Desc. Obligatoria", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        TextButton(onClick = { showItemPickerForCore = true }) {
                            Text("+ Añadir Objeto Core", color = HextechGold, fontSize = 11.sp)
                        }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        coreItems.forEachIndexed { index, entry ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
                            ) {
                                Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            AppAssetImage(
                                                url = entry.iconUrl,
                                                contentDescription = entry.name,
                                                fallbackText = entry.name.take(2),
                                                modifier = Modifier.size(28.dp).clip(RoundedCornerShape(4.dp))
                                            )
                                            Text("${index + 1}. ${entry.name}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        }
                                        IconButton(
                                            onClick = { coreItems.remove(entry) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(Icons.Default.Close, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                    OutlinedTextField(
                                        value = entry.description,
                                        onValueChange = { entry.description = it },
                                        placeholder = { Text("Descripción obligatoria del objeto core...", color = TextSecondary) },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = HextechGold,
                                            unfocusedBorderColor = HextechSurfaceVariant,
                                            focusedTextColor = Color.White,
                                            unfocusedTextColor = Color.White
                                        )
                                    )
                                }
                            }
                        }
                        if (coreItems.isEmpty()) {
                            Text("Ningún objeto core añadido.", color = TextSecondary, fontSize = 11.sp)
                        }
                    }

                    // 4. Objetos Situacionales (Con descripción obligatoria por cada uno)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("4. Objetos Situacionales *Desc. Obligatoria", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        TextButton(onClick = { showItemPickerForSituational = true }) {
                            Text("+ Añadir Situacional", color = HextechGold, fontSize = 11.sp)
                        }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        situationalItems.forEachIndexed { index, entry ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
                            ) {
                                Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            AppAssetImage(
                                                url = entry.iconUrl,
                                                contentDescription = entry.name,
                                                fallbackText = entry.name.take(2),
                                                modifier = Modifier.size(28.dp).clip(RoundedCornerShape(4.dp))
                                            )
                                            Text("Sit. ${index + 1}. ${entry.name}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        }
                                        IconButton(
                                            onClick = { situationalItems.remove(entry) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(Icons.Default.Close, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                    OutlinedTextField(
                                        value = entry.description,
                                        onValueChange = { entry.description = it },
                                        placeholder = { Text("Descripción obligatoria del objeto situacional...", color = TextSecondary) },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = HextechGold,
                                            unfocusedBorderColor = HextechSurfaceVariant,
                                            focusedTextColor = Color.White,
                                            unfocusedTextColor = Color.White
                                        )
                                    )
                                }
                            }
                        }
                        if (situationalItems.isEmpty()) {
                            Text("Ningún objeto situacional añadido.", color = TextSecondary, fontSize = 11.sp)
                        }
                    }

                    // 5. Runas Core (Imágenes con descripción obligatoria)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("5. Runas Core (Imágenes) *Desc. Obligatoria", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        TextButton(onClick = { showRunePickerForCore = true }) {
                            Text("+ Añadir Runa Core", color = HextechGold, fontSize = 11.sp)
                        }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        coreRunes.forEachIndexed { index, entry ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
                            ) {
                                Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            AppAssetImage(
                                                url = entry.iconUrl,
                                                contentDescription = entry.name,
                                                fallbackText = entry.name.take(2),
                                                modifier = Modifier.size(28.dp).clip(RoundedCornerShape(4.dp))
                                            )
                                            Text("Runa ${index + 1}. ${entry.name}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        }
                                        IconButton(
                                            onClick = { coreRunes.remove(entry) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(Icons.Default.Close, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                    OutlinedTextField(
                                        value = entry.description,
                                        onValueChange = { entry.description = it },
                                        placeholder = { Text("Descripción obligatoria de la runa...", color = TextSecondary) },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = HextechGold,
                                            unfocusedBorderColor = HextechSurfaceVariant,
                                            focusedTextColor = Color.White,
                                            unfocusedTextColor = Color.White
                                        )
                                    )
                                }
                            }
                        }
                        if (coreRunes.isEmpty()) {
                            Text("Ninguna runa core añadida.", color = TextSecondary, fontSize = 11.sp)
                        }
                    }

                    // Runas Situacionales (Opcionales con descripción obligatoria si se añaden)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Runas Situacionales (Opcional)", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        TextButton(onClick = { showRunePickerForSituational = true }) {
                            Text("+ Añadir Sit. Runa", color = HextechGold, fontSize = 11.sp)
                        }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        situationalRunes.forEachIndexed { index, entry ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
                            ) {
                                Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            AppAssetImage(
                                                url = entry.iconUrl,
                                                contentDescription = entry.name,
                                                fallbackText = entry.name.take(2),
                                                modifier = Modifier.size(28.dp).clip(RoundedCornerShape(4.dp))
                                            )
                                            Text("Sit. Runa ${index + 1}. ${entry.name}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        }
                                        IconButton(
                                            onClick = { situationalRunes.remove(entry) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(Icons.Default.Close, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                    OutlinedTextField(
                                        value = entry.description,
                                        onValueChange = { entry.description = it },
                                        placeholder = { Text("Descripción obligatoria de runa situacional...", color = TextSecondary) },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = HextechGold,
                                            unfocusedBorderColor = HextechSurfaceVariant,
                                            focusedTextColor = Color.White,
                                            unfocusedTextColor = Color.White
                                        )
                                    )
                                }
                            }
                        }
                    }

                    // 6. Hechizos Core (Imágenes con descripción obligatoria)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("6. Hechizos Core (Imágenes) *Desc. Obligatoria", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        TextButton(onClick = { showSpellPickerForCore = true }) {
                            Text("+ Añadir Hechizo Core", color = HextechGold, fontSize = 11.sp)
                        }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        coreSpells.forEachIndexed { index, entry ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
                            ) {
                                Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            AppAssetImage(
                                                url = entry.iconUrl,
                                                contentDescription = entry.name,
                                                fallbackText = entry.name.take(2),
                                                modifier = Modifier.size(28.dp).clip(RoundedCornerShape(4.dp))
                                            )
                                            Text("Hechizo ${index + 1}. ${entry.name}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        }
                                        IconButton(
                                            onClick = { coreSpells.remove(entry) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(Icons.Default.Close, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                    OutlinedTextField(
                                        value = entry.description,
                                        onValueChange = { entry.description = it },
                                        placeholder = { Text("Descripción obligatoria del hechizo...", color = TextSecondary) },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = HextechGold,
                                            unfocusedBorderColor = HextechSurfaceVariant,
                                            focusedTextColor = Color.White,
                                            unfocusedTextColor = Color.White
                                        )
                                    )
                                }
                            }
                        }
                        if (coreSpells.isEmpty()) {
                            Text("Ningún hechizo core añadido.", color = TextSecondary, fontSize = 11.sp)
                        }
                    }

                    // Hechizos Situacionales (Opcionales con descripción obligatoria si se añaden)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Hechizos Situacionales (Opcional)", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        TextButton(onClick = { showSpellPickerForSituational = true }) {
                            Text("+ Añadir Sit. Hechizo", color = HextechGold, fontSize = 11.sp)
                        }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        situationalSpells.forEachIndexed { index, entry ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
                            ) {
                                Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            AppAssetImage(
                                                url = entry.iconUrl,
                                                contentDescription = entry.name,
                                                fallbackText = entry.name.take(2),
                                                modifier = Modifier.size(28.dp).clip(RoundedCornerShape(4.dp))
                                            )
                                            Text("Sit. Hechizo ${index + 1}. ${entry.name}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        }
                                        IconButton(
                                            onClick = { situationalSpells.remove(entry) },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(Icons.Default.Close, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                    OutlinedTextField(
                                        value = entry.description,
                                        onValueChange = { entry.description = it },
                                        placeholder = { Text("Descripción obligatoria de hechizo situacional...", color = TextSecondary) },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = HextechGold,
                                            unfocusedBorderColor = HextechSurfaceVariant,
                                            focusedTextColor = Color.White,
                                            unfocusedTextColor = Color.White
                                        )
                                    )
                                }
                            }
                        }
                    }

                    // 7. Subir Gameplay MP4 (Máximo 20MB)
                    Text("7. Gameplay Demostrativo (MP4, Máx 20MB)", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Button(
                        onClick = { videoPickerLauncher.launch("video/mp4") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.7f)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Default.Videocam, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (gameplayVideoUri != null) "✓ Gameplay MP4 Adjuntado" else "Seleccionar archivo MP4 (Máx 20MB)",
                            color = if (gameplayVideoUri != null) HextechGold else Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
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
                            if (coreItems.isEmpty()) {
                                Toast.makeText(context, "Debes añadir al menos un objeto core", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (coreItems.any { it.description.trim().isBlank() }) {
                                Toast.makeText(context, "Todos los objetos core deben tener su descripción obligatoria", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (situationalItems.any { it.description.trim().isBlank() }) {
                                Toast.makeText(context, "Todos los objetos situacionales deben tener su descripción obligatoria", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (coreRunes.isEmpty()) {
                                Toast.makeText(context, "Debes añadir al menos una runa core", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (coreRunes.any { it.description.trim().isBlank() }) {
                                Toast.makeText(context, "Todas las runas core deben tener su descripción obligatoria", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (situationalRunes.any { it.description.trim().isBlank() }) {
                                Toast.makeText(context, "Todas las runas situacionales deben tener su descripción obligatoria", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (coreSpells.isEmpty()) {
                                Toast.makeText(context, "Debes añadir al menos un hechizo core", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (coreSpells.any { it.description.trim().isBlank() }) {
                                Toast.makeText(context, "Todos los hechizos core deben tener su descripción obligatoria", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (situationalSpells.any { it.description.trim().isBlank() }) {
                                Toast.makeText(context, "Todos los hechizos situacionales deben tener su descripción obligatoria", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            val record = CustomChampionBuildRecord(
                                championId = champ.id,
                                championName = champ.name,
                                buildTitle = buildTitle.trim(),
                                role = champ.primaryRole.displayName,
                                coreItems = coreItems.map { it.name },
                                situationalItems = situationalItems.map { it.name },
                                runes = coreRunes.joinToString(", ") { it.name },
                                spells = coreSpells.map { it.name },
                                coreItemsWithDesc = coreItems.map { ItemBuildEntry(it.name, it.description.trim()) },
                                situationalItemsWithDesc = situationalItems.map { ItemBuildEntry(it.name, it.description.trim()) },
                                coreRunes = coreRunes.map { RuneBuildEntry(it.name, it.iconUrl, it.description.trim()) },
                                situationalRunes = situationalRunes.map { RuneBuildEntry(it.name, it.iconUrl, it.description.trim()) },
                                coreSpells = coreSpells.map { SpellBuildEntry(it.name, it.iconUrl, it.description.trim()) },
                                situationalSpells = situationalSpells.map { SpellBuildEntry(it.name, it.iconUrl, it.description.trim()) },
                                gameplayVideoUri = gameplayVideoUri,
                                creatorName = if (creatorName.isBlank()) "Creador Oficial" else creatorName
                            )

                            CustomChampionBuildsManager.addBuild(context, record)
                            Toast.makeText(context, "¡Build avanzada de ${champ.name} creada y publicada con éxito!", Toast.LENGTH_SHORT).show()
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
                Column(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Seleccionar Campeón", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    OutlinedTextField(
                        value = searchFilterQuery,
                        onValueChange = { searchFilterQuery = it },
                        placeholder = { Text("Buscar...", color = TextSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
                        singleLine = true
                    )
                    val filtered = remember(searchFilterQuery, champions) {
                        if (searchFilterQuery.isBlank()) champions else champions.filter { it.name.contains(searchFilterQuery, ignoreCase = true) }
                    }
                    LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        items(filtered) { champ ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedChampion = champ
                                        showChampionPicker = false
                                        searchFilterQuery = ""
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

    // Modal Selector de Objetos (Core o Situacional)
    val showItemPicker = showItemPickerForCore || showItemPickerForSituational
    if (showItemPicker) {
        Dialog(onDismissRequest = {
            showItemPickerForCore = false
            showItemPickerForSituational = false
            searchFilterQuery = ""
        }) {
            Card(
                modifier = Modifier.fillMaxWidth().height(500.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Column(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = if (showItemPickerForCore) "Seleccionar Objeto Core" else "Seleccionar Objeto Situacional",
                        color = HextechGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    OutlinedTextField(
                        value = searchFilterQuery,
                        onValueChange = { searchFilterQuery = it },
                        placeholder = { Text("Buscar objeto...", color = TextSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
                        singleLine = true
                    )
                    val filteredItems = remember(searchFilterQuery, items) {
                        if (searchFilterQuery.isBlank()) items else items.filter { it.name.contains(searchFilterQuery, ignoreCase = true) }
                    }
                    LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        items(filteredItems) { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        if (showItemPickerForCore) {
                                            coreItems.add(EditableItemEntry(item.name, item.iconUrl))
                                            showItemPickerForCore = false
                                        } else {
                                            situationalItems.add(EditableItemEntry(item.name, item.iconUrl))
                                            showItemPickerForSituational = false
                                        }
                                        searchFilterQuery = ""
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

    // Modal Selector de Runas (Core o Situacional)
    val showRunePicker = showRunePickerForCore || showRunePickerForSituational
    if (showRunePicker) {
        Dialog(onDismissRequest = {
            showRunePickerForCore = false
            showRunePickerForSituational = false
            searchFilterQuery = ""
        }) {
            Card(
                modifier = Modifier.fillMaxWidth().height(500.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Column(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = if (showRunePickerForCore) "Seleccionar Runa Core" else "Seleccionar Runa Situacional",
                        color = HextechGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    OutlinedTextField(
                        value = searchFilterQuery,
                        onValueChange = { searchFilterQuery = it },
                        placeholder = { Text("Buscar runa...", color = TextSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
                        singleLine = true
                    )
                    val filteredRunes = remember(searchFilterQuery, runes) {
                        if (searchFilterQuery.isBlank()) runes else runes.filter { it.name.contains(searchFilterQuery, ignoreCase = true) }
                    }
                    LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        items(filteredRunes) { rune ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        if (showRunePickerForCore) {
                                            coreRunes.add(EditableRuneEntry(rune.name, rune.iconUrl))
                                            showRunePickerForCore = false
                                        } else {
                                            situationalRunes.add(EditableRuneEntry(rune.name, rune.iconUrl))
                                            showRunePickerForSituational = false
                                        }
                                        searchFilterQuery = ""
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                AppAssetImage(
                                    url = rune.iconUrl,
                                    contentDescription = rune.name,
                                    fallbackText = rune.name.take(2),
                                    modifier = Modifier.size(28.dp).clip(RoundedCornerShape(4.dp))
                                )
                                Column {
                                    Text(rune.name, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    Text("Categoría: ${rune.category}", color = HextechGold, fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal Selector de Hechizos (Core o Situacional)
    val showSpellPicker = showSpellPickerForCore || showSpellPickerForSituational
    if (showSpellPicker) {
        Dialog(onDismissRequest = {
            showSpellPickerForCore = false
            showSpellPickerForSituational = false
            searchFilterQuery = ""
        }) {
            Card(
                modifier = Modifier.fillMaxWidth().height(500.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Column(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = if (showSpellPickerForCore) "Seleccionar Hechizo Core" else "Seleccionar Hechizo Situacional",
                        color = HextechGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    OutlinedTextField(
                        value = searchFilterQuery,
                        onValueChange = { searchFilterQuery = it },
                        placeholder = { Text("Buscar hechizo...", color = TextSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
                        singleLine = true
                    )
                    val filteredSpells = remember(searchFilterQuery, spells) {
                        if (searchFilterQuery.isBlank()) spells else spells.filter { it.name.contains(searchFilterQuery, ignoreCase = true) }
                    }
                    LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        items(filteredSpells) { spell ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        if (showSpellPickerForCore) {
                                            coreSpells.add(EditableSpellEntry(spell.name, spell.iconUrl))
                                            showSpellPickerForCore = false
                                        } else {
                                            situationalSpells.add(EditableSpellEntry(spell.name, spell.iconUrl))
                                            showSpellPickerForSituational = false
                                        }
                                        searchFilterQuery = ""
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                AppAssetImage(
                                    url = spell.iconUrl,
                                    contentDescription = spell.name,
                                    fallbackText = spell.name.take(2),
                                    modifier = Modifier.size(28.dp).clip(RoundedCornerShape(4.dp))
                                )
                                Column {
                                    Text(spell.name, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    Text("CD: ${spell.cooldown}", color = HextechGold, fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
