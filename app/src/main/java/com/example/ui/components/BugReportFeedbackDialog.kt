package com.example.ui.components

import com.example.ui.theme.HextechGoldLight
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.WildRiftItemsData
import com.example.data.WildRiftRepository
import com.example.data.WildRiftSpellsAndRunes
import com.example.data.supabase.FeedbackRepository
import com.example.model.Champion
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.model.WildRiftItem
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.util.tr
import kotlinx.coroutines.launch

enum class FeedbackType(
    val title: String,
    val icon: ImageVector,
    val label: String
) {
    BUG("Reportar Bug", Icons.Default.BugReport, "Bug / Error"),
    SUGGESTION("Sugerencia", Icons.Default.Lightbulb, "Idea / Sugerencia"),
    BUILD_SUGGESTION("Sugerir Build", Icons.Default.SportsEsports, "Sugerir Build")
}

@Composable
fun BugReportFeedbackDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var selectedType by remember { mutableStateOf(FeedbackType.BUG) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    
    // Build Suggestion graphical states
    var selectedChampionObj by remember { mutableStateOf<Champion?>(null) }
    var suggestedChampion by remember { mutableStateOf("") }
    var suggestedRole by remember { mutableStateOf("Mid") }
    
    val selectedCoreItems = remember { mutableStateListOf<WildRiftItem>() }
    val selectedSituationalItems = remember { mutableStateListOf<WildRiftItem>() }
    val selectedAltSituationalItems = remember { mutableStateListOf<WildRiftItem>() }
    var selectedBootsItem by remember { mutableStateOf<WildRiftItem?>(null) }
    var selectedKeystoneRune by remember { mutableStateOf<RuneItem?>(null) }
    val selectedSecondaryRunes = remember { mutableStateListOf<RuneItem>() }
    val selectedSpells = remember { mutableStateListOf<SummonerSpellItem>() }
    
    // Dialog pickers
    var showChampionPicker by remember { mutableStateOf(false) }
    var showItemPickerType by remember { mutableStateOf<String?>(null) } // "core", "situational", "boots"
    var showRunePickerType by remember { mutableStateOf<String?>(null) } // "keystone", "secondary"
    var showSpellPicker by remember { mutableStateOf(false) }

    var isSubmitting by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf<String?>(null) }
    var selectedImages by remember { mutableStateOf<List<String>>(emptyList()) }
    var showAdminPanel by remember { mutableStateOf(false) }

    if (showAdminPanel) {
        AdminFeedbackBottomSheet(
            onDismiss = { showAdminPanel = false }
        )
    }
    
    val successMsg = tr("Imagen adjuntada correctamente")
    val errorMsg = tr("Error al procesar la imagen")
    val limitMsg = tr("La imagen excede el límite de 2 MB")
    
    val imagePickerLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia(maxItems = 3)
    ) { uris ->
        if (uris.isNotEmpty()) {
            scope.launch {
                val newImages = mutableListOf<String>()
                for (uri in uris) {
                    try {
                        val cursor = context.contentResolver.query(uri, null, null, null, null)
                        var sizeInBytes: Long = 0
                        if (cursor != null && cursor.moveToFirst()) {
                            val sizeIndex = cursor.getColumnIndex(android.provider.OpenableColumns.SIZE)
                            if (sizeIndex != -1) {
                                sizeInBytes = cursor.getLong(sizeIndex)
                            }
                            cursor.close()
                        }
                        
                        if (sizeInBytes > 2 * 1024 * 1024) {
                            Toast.makeText(context, limitMsg, Toast.LENGTH_LONG).show()
                            continue
                        }
                        
                        val base64 = com.example.util.ImageUtils.uriToBase64(context, uri)
                        if (base64 != null) {
                            newImages.add(base64)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                
                if (newImages.isNotEmpty()) {
                    val combined = (selectedImages + newImages).take(3)
                    selectedImages = combined
                    Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val isEmailValid = email.isBlank() || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val hasBuildContent = selectedChampionObj != null || suggestedChampion.isNotBlank()
    val canPublish = if (selectedType == FeedbackType.BUILD_SUGGESTION) {
        hasBuildContent && (selectedCoreItems.isNotEmpty() || title.isNotBlank() || description.isNotBlank()) && isEmailValid
    } else {
        title.trim().isNotBlank() && description.trim().isNotBlank() && isEmailValid
    }

    val sendFeedbackMessage: () -> Unit = {
        if (canPublish) {
            isSubmitting = true
            statusMessage = null
            scope.launch {
                val effectiveChampName = selectedChampionObj?.name ?: suggestedChampion.ifBlank { "Campeón General" }
                var finalTitle = title.ifBlank { "Sugerencia de Build para $effectiveChampName ($suggestedRole)" }
                var finalDesc = description
                if (selectedType == FeedbackType.BUILD_SUGGESTION) {
                    val coreItemsStr = selectedCoreItems.joinToString(" • ") { it.name }
                    val situItemsStr = selectedSituationalItems.joinToString(" • ") { it.name }
                    val altSituItemsStr = selectedAltSituationalItems.joinToString(" • ") { it.name }
                    val bootsStr = selectedBootsItem?.name ?: ""
                    val currentKeystone = selectedKeystoneRune
                    val runesStr = buildString {
                        if (currentKeystone != null) {
                            append("Clave: ${currentKeystone.name}")
                        }
                        if (selectedSecondaryRunes.isNotEmpty()) {
                            if (isNotEmpty()) append(" | Secundarias: ")
                            append(selectedSecondaryRunes.joinToString(", ") { it.name })
                        }
                    }
                    val spellsStr = selectedSpells.joinToString(" + ") { it.name }

                    val buildDetails = buildString {
                        appendLine("--- SUGERENCIA DE BUILD DE COMUNIDAD (CATÁLOGO) ---")
                        appendLine("• Campeón: $effectiveChampName")
                        appendLine("• Rol/Línea: $suggestedRole")
                        if (coreItemsStr.isNotBlank()) appendLine("• Objetos Core (1-6): $coreItemsStr")
                        if (situItemsStr.isNotBlank()) appendLine("• Objetos Situacionales (7-8): $situItemsStr")
                        if (altSituItemsStr.isNotBlank()) appendLine("• Alternativas Situacionales (vs diferente composición para 7 y 8): $altSituItemsStr")
                        if (bootsStr.isNotBlank()) appendLine("• Botas / Encantamiento: $bootsStr")
                        if (runesStr.isNotBlank()) appendLine("• Runas: $runesStr")
                        if (spellsStr.isNotBlank()) appendLine("• Hechizos: $spellsStr")
                        if (description.isNotBlank()) {
                            appendLine("\n• Notas / Explicación Táctica:")
                            appendLine(description)
                        }
                    }
                    finalDesc = buildDetails
                } else {
                    if (suggestedChampion.isNotBlank()) finalDesc += "\n\nCampeón Sugerido: $suggestedChampion"
                    if (suggestedRole.isNotBlank()) finalDesc += "\nRol Sugerido: $suggestedRole"
                }
                
                val result = FeedbackRepository.submitFeedback(
                    type = selectedType.name,
                    title = finalTitle,
                    description = finalDesc,
                    email = email.trim().takeIf { it.isNotEmpty() },
                    imagesBase64 = if (selectedType == FeedbackType.BUILD_SUGGESTION) emptyList() else selectedImages,
                    retentionDays = 7
                )
                isSubmitting = false
                if (result.isSuccess) {
                    Toast.makeText(context, "✅ ¡Sugerencia/Reporte enviado con éxito!", Toast.LENGTH_LONG).show()
                    onDismiss()
                } else {
                    val err = result.exceptionOrNull()?.message ?: "Error desconocido"
                    statusMessage = "❌ Error al enviar: $err"
                    Toast.makeText(context, "Error: $err", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            val msg = if (selectedType == FeedbackType.BUILD_SUGGESTION) {
                "Por favor selecciona un campeón e indica la build o título"
            } else {
                "Por favor completa el título y la descripción"
            }
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.testTag("bug_report_dialog"),
        containerColor = HextechDarkBg,
        shape = RoundedCornerShape(16.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.BugReport,
                        contentDescription = null,
                        tint = HextechGold,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = tr("Buzón de Reportes & Ideas"),
                        color = TextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Default.Close, contentDescription = tr("Cerrar"), tint = TextMuted)
                    }
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Selector de Tipo de Reporte
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechSurfaceVariant)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FeedbackType.entries.forEach { type ->
                        val isSelected = selectedType == type
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(6.dp))
                                .background(
                                    if (isSelected) HextechGold.copy(alpha = 0.2f)
                                    else Color.Transparent
                                )
                                .border(
                                    width = if (isSelected) 1.dp else 0.dp,
                                    color = if (isSelected) HextechGold else Color.Transparent,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .clickable { selectedType = type }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = type.icon,
                                    contentDescription = null,
                                    tint = if (isSelected) HextechGold else TextMuted,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = tr(type.label),
                                    color = if (isSelected) HextechGold else TextMuted,
                                    fontSize = 10.5.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }

                // ==========================================
                // CAMPOS ESPECÍFICOS PARA SUGERIR BUILD (CATÁLOGO GRÁFICO)
                // ==========================================
                if (selectedType == FeedbackType.BUILD_SUGGESTION) {
                    // 1. SELECTOR GRÁFICO DE CAMPEÓN
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = tr("1. Selecciona Campeón:"),
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        
                        val currentChamp = selectedChampionObj
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(HextechSurfaceVariant)
                                .border(1.dp, if (currentChamp != null) HextechGold else HextechCardBorder, RoundedCornerShape(10.dp))
                                .clickable { showChampionPicker = true }
                                .padding(10.dp)
                        ) {
                            if (currentChamp != null) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        ChampionAvatar(
                                            champion = currentChamp,
                                            size = 40.dp,
                                            showTierBadge = false
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column {
                                            Text(
                                                text = currentChamp.name,
                                                color = HextechGold,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "${currentChamp.primaryRole.shortName} • Tier ${currentChamp.tier}",
                                                color = TextMuted,
                                                fontSize = 11.sp
                                            )
                                        }
                                    }
                                    Text(
                                        text = tr("Cambiar"),
                                        color = HextechCyan,
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            } else {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(40.dp)
                                                .clip(CircleShape)
                                                .background(HextechSurface)
                                                .border(1.dp, HextechGold.copy(alpha = 0.5f), CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.SportsEsports,
                                                contentDescription = null,
                                                tint = HextechGold,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = tr("Toca para elegir del catálogo..."),
                                            color = TextMuted,
                                            fontSize = 12.5.sp
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = null,
                                        tint = HextechCyan,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }

                    // 2. SELECTOR DE ROL / LÍNEA
                    Column {
                        Text(
                            text = tr("2. Rol / Línea:"),
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        val roles = listOf("Solo / Baron", "Jungla", "Mid", "Dúo / ADC", "Soporte")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            roles.forEach { r ->
                                val isRSelected = suggestedRole == r
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (isRSelected) HextechCyan.copy(alpha = 0.25f) else HextechSurface)
                                        .border(1.dp, if (isRSelected) HextechCyan else HextechCardBorder, RoundedCornerShape(6.dp))
                                        .clickable { suggestedRole = r }
                                        .padding(vertical = 6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = r.split("/").last().trim(),
                                        color = if (isRSelected) HextechCyan else TextMuted,
                                        fontSize = 10.sp,
                                        fontWeight = if (isRSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            }
                        }
                    }

                    // 3. OBJETOS CORE (1 al 6) - SELECCIÓN GRÁFICA
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = tr("3. Objetos Core (1 al 6) [${selectedCoreItems.size}/6]:"),
                                color = HextechGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (selectedCoreItems.size < 6) {
                                Text(
                                    text = "+ " + tr("Añadir Objeto"),
                                    color = HextechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.clickable { showItemPickerType = "core" }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(selectedCoreItems) { item ->
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurfaceVariant)
                                        .border(1.5.dp, HextechGold, RoundedCornerShape(8.dp))
                                        .clickable { selectedCoreItems.remove(item) },
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(item.iconUrl)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = item.name,
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .size(16.dp)
                                            .background(Color.Black.copy(alpha = 0.7f), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Quitar",
                                            tint = Color.Red,
                                            modifier = Modifier.size(10.dp)
                                        )
                                    }
                                }
                            }
                            if (selectedCoreItems.size < 6) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(HextechSurface.copy(alpha = 0.6f))
                                            .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                                            .clickable { showItemPickerType = "core" },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Añadir Core",
                                            tint = HextechCyan,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 4. OBJETOS SITUACIONALES (7 y 8) Y BOTAS
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                            .border(1.dp, HextechCardBorder, RoundedCornerShape(10.dp))
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            // Situacionales Base (7 y 8)
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = tr("Situacionales (7 y 8):"),
                                    color = HextechGold,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    items(selectedSituationalItems) { item ->
                                        Box(
                                            modifier = Modifier
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(HextechSurfaceVariant)
                                                .border(1.5.dp, HextechCyan, RoundedCornerShape(8.dp))
                                                .clickable { selectedSituationalItems.remove(item) },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data(item.iconUrl)
                                                    .crossfade(true)
                                                    .build(),
                                                contentDescription = item.name,
                                                modifier = Modifier.fillMaxSize(),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                    }
                                    if (selectedSituationalItems.size < 2) {
                                        item {
                                            Box(
                                                modifier = Modifier
                                                    .size(42.dp)
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .background(HextechSurface)
                                                    .border(1.dp, HextechCyan.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                                    .clickable { showItemPickerType = "situational" },
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(Icons.Default.Add, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp))
                                            }
                                        }
                                    }
                                }
                            }

                            // Botas y Encantamiento
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = tr("Botas / Mejora:"),
                                    color = HextechGold,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                val boots = selectedBootsItem
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (boots != null) {
                                        Box(
                                            modifier = Modifier
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(HextechSurfaceVariant)
                                                .border(1.5.dp, HextechGold, RoundedCornerShape(8.dp))
                                                .clickable { selectedBootsItem = null },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data(boots.iconUrl)
                                                    .crossfade(true)
                                                    .build(),
                                                contentDescription = boots.name,
                                                modifier = Modifier.fillMaxSize(),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                    } else {
                                        Box(
                                            modifier = Modifier
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(HextechSurface)
                                                .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                                                .clickable { showItemPickerType = "boots" },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(Icons.Default.Add, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = boots?.name ?: tr("Elegir botas"),
                                        color = if (boots != null) TextPrimary else TextMuted,
                                        fontSize = 10.5.sp,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }

                        // Alternativas Situacionales para 7 y 8 (vs composición rival)
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = tr("Alternativas Situacionales (vs diferente composición para 7 y 8):"),
                                    color = HextechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                items(selectedAltSituationalItems) { item ->
                                    Box(
                                        modifier = Modifier
                                            .size(42.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(HextechSurfaceVariant)
                                            .border(1.5.dp, HextechGoldLight, RoundedCornerShape(8.dp))
                                            .clickable { selectedAltSituationalItems.remove(item) },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(item.iconUrl)
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = item.name,
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                                if (selectedAltSituationalItems.size < 2) {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(HextechSurface)
                                                .border(1.dp, HextechGoldLight.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                                .clickable { showItemPickerType = "situational_alt" },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(Icons.Default.Add, contentDescription = null, tint = HextechGoldLight, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 5. RUNAS (CLAVE + SECUNDARIAS)
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = tr("5. Runas (Clave + 4 Secundarias):"),
                                color = HextechGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        val currentKeystone = selectedKeystoneRune
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Keystone
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(HextechSurfaceVariant)
                                    .border(2.dp, if (currentKeystone != null) HextechGold else HextechCardBorder, CircleShape)
                                    .clickable { showRunePickerType = "keystone" },
                                contentAlignment = Alignment.Center
                            ) {
                                if (currentKeystone != null) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(currentKeystone.iconUrl)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = currentKeystone.name,
                                        modifier = Modifier.size(38.dp)
                                    )
                                } else {
                                    Icon(Icons.Default.Star, contentDescription = "Keystone", tint = HextechGold, modifier = Modifier.size(20.dp))
                                }
                            }

                            // 4 Secundarias
                            for (i in 0 until 4) {
                                val rune = selectedSecondaryRunes.getOrNull(i)
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(HextechSurface)
                                        .border(1.dp, if (rune != null) HextechCyan else HextechCardBorder, CircleShape)
                                        .clickable {
                                            if (rune != null) {
                                                selectedSecondaryRunes.remove(rune)
                                            } else {
                                                showRunePickerType = "secondary"
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (rune != null) {
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(rune.iconUrl)
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = rune.name,
                                            modifier = Modifier.size(28.dp)
                                        )
                                    } else {
                                        Icon(Icons.Default.Add, contentDescription = "Añadir", tint = HextechCyan.copy(alpha = 0.6f), modifier = Modifier.size(16.dp))
                                    }
                                }
                            }
                        }
                    }

                    // 6. HECHIZOS DE INVOCADOR
                    Column {
                        Text(
                            text = tr("6. Hechizos de Invocador (2):"),
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            for (i in 0 until 2) {
                                val spell = selectedSpells.getOrNull(i)
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurfaceVariant)
                                        .border(1.5.dp, if (spell != null) HextechCyan else HextechCardBorder, RoundedCornerShape(8.dp))
                                        .clickable {
                                            if (spell != null) {
                                                selectedSpells.remove(spell)
                                            } else {
                                                showSpellPicker = true
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (spell != null) {
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(spell.iconUrl)
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = spell.name,
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    } else {
                                        Icon(Icons.Default.FlashOn, contentDescription = "Hechizo", tint = HextechCyan, modifier = Modifier.size(20.dp))
                                    }
                                }
                            }
                            if (selectedSpells.isNotEmpty()) {
                                Text(
                                    text = selectedSpells.joinToString(" + ") { it.name },
                                    color = TextPrimary,
                                    fontSize = 11.5.sp,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                }

                // Campo Título
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(if (selectedType == FeedbackType.BUILD_SUGGESTION) tr("Título o resumen de la build") else tr("Título del reporte o sugerencia"), fontSize = 12.sp) },
                    placeholder = {
                        Text(
                            when (selectedType) {
                                FeedbackType.BUG -> tr("Ej: El overlay no detecta la pantalla de selección")
                                FeedbackType.SUGGESTION -> tr("Ej: Agregar temporizador de dragones con audio")
                                FeedbackType.BUILD_SUGGESTION -> tr("Ej: Build de Burst Letal para Midlane")
                            },
                            fontSize = 11.5.sp,
                            color = TextMuted
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("feedback_title_input"),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedLabelColor = HextechGold,
                        unfocusedLabelColor = TextMuted
                    )
                )

                // Campo Descripción
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(if (selectedType == FeedbackType.BUILD_SUGGESTION) tr("Justificación táctica / Matchups (Opcional)") else tr("Descripción detallada"), fontSize = 12.sp) },
                    placeholder = {
                        Text(
                            when (selectedType) {
                                FeedbackType.BUG -> tr("Describe qué sucedió o cómo reproducir el error...")
                                FeedbackType.SUGGESTION -> tr("Describe tu idea o mejora para la aplicación...")
                                FeedbackType.BUILD_SUGGESTION -> tr("Explica contra qué composición usar esta build, power spikes...")
                            },
                            fontSize = 11.5.sp,
                            color = TextMuted
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .testTag("feedback_desc_input"),
                    maxLines = 4,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedLabelColor = HextechGold,
                        unfocusedLabelColor = TextMuted
                    )
                )

                // Campo Correo Electrónico (Opcional)
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(tr("Correo electrónico (Opcional)"), fontSize = 12.sp) },
                    placeholder = {
                        Text(
                            tr("Para contactarte si necesitamos más detalles..."),
                            fontSize = 11.5.sp,
                            color = TextMuted
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("feedback_email_input"),
                    singleLine = true,
                    isError = email.isNotBlank() && !isEmailValid,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedLabelColor = HextechGold,
                        unfocusedLabelColor = TextMuted
                    )
                )
                
                if (email.isNotBlank() && !isEmailValid) {
                    Text(
                        text = tr("Formato de correo inválido"),
                        color = Color(0xFFFF5252),
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                    )
                }

                // Subir Imágenes (Max 3) - DESHABILITADO PARA SUGERENCIAS DE BUILD
                if (selectedType != FeedbackType.BUILD_SUGGESTION) {
                    if (selectedImages.size < 3) {
                        OutlinedButton(
                            onClick = {
                                imagePickerLauncher.launch(
                                    androidx.activity.result.PickVisualMediaRequest(
                                        androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = null,
                                tint = HextechCyan,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = tr("Adjuntar Captura") + " (${selectedImages.size}/3)",
                                color = HextechCyan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    
                    if (selectedImages.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        selectedImages.forEachIndexed { index, base64 ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                                    .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                                    .padding(bottom = if (index < selectedImages.size - 1) 8.dp else 0.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Image,
                                        contentDescription = null,
                                        tint = HextechGold,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = tr("Imagen subida") + " " + (index + 1),
                                        color = TextPrimary,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        val newList = selectedImages.toMutableList()
                                        newList.removeAt(index)
                                        selectedImages = newList
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = tr("Eliminar imagen"),
                                        tint = Color(0xFFFF5252)
                                    )
                                }
                            }
                        }
                    }
                }

                if (statusMessage != null) {
                    Text(
                        text = statusMessage!!,
                        color = Color(0xFFFF5252),
                        fontSize = 11.sp
                    )
                }

                // Botón para acceder directamente al Panel de Gestión
                OutlinedButton(
                    onClick = { showAdminPanel = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f)),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = HextechSurface)
                ) {
                    Icon(
                        imageVector = Icons.Default.AdminPanelSettings,
                        contentDescription = null,
                        tint = HextechGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = tr("Ver Panel de Reportes & Sugerencias"),
                        color = HextechGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Diagnóstico del sistema
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechSurfaceVariant.copy(alpha = 0.6f))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "${tr("📱 Dispositivo:")} ${Build.MODEL} • Android ${Build.VERSION.RELEASE} • ${tr(WildRiftRepository.CURRENT_PATCH_VERSION)}",
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                }
            }
        },
        confirmButton = {
            val buttonText = when (selectedType) {
                FeedbackType.BUG -> tr("Enviar reporte")
                FeedbackType.SUGGESTION -> tr("Enviar sugerencia")
                FeedbackType.BUILD_SUGGESTION -> tr("Enviar sugerencia de build")
            }

            Button(
                onClick = sendFeedbackMessage,
                enabled = canPublish && !isSubmitting,
                colors = ButtonDefaults.buttonColors(
                    containerColor = HextechGold,
                    disabledContainerColor = HextechGold.copy(alpha = 0.25f),
                    disabledContentColor = TextMuted
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("submit_feedback_button")
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(
                        color = HextechDarkBg,
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = tr("Enviando mensaje..."),
                        color = HextechDarkBg,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = null,
                            tint = if (canPublish) Color.Black else TextMuted,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = buttonText,
                            color = if (canPublish) Color.Black else TextMuted,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.5.sp
                        )
                    }
                }
            }
        },
        dismissButton = {}
    )

    // =========================================================================
    // MODALES Y DIÁLOGOS DE SELECCIÓN GRÁFICA DE CATÁLOGO
    // =========================================================================
    
    // 1. Selector de Campeón
    if (showChampionPicker) {
        ChampionCatalogSelectionDialog(
            onDismiss = { showChampionPicker = false },
            onSelect = { champion ->
                selectedChampionObj = champion
                suggestedChampion = champion.name
                showChampionPicker = false
            }
        )
    }

    // 2. Selector de Objeto
    if (showItemPickerType != null) {
        ItemCatalogSelectionDialog(
            type = showItemPickerType!!,
            onDismiss = { showItemPickerType = null },
            onSelect = { item ->
                when (showItemPickerType) {
                    "core" -> {
                        if (selectedCoreItems.size < 6 && !selectedCoreItems.contains(item)) {
                            selectedCoreItems.add(item)
                        }
                    }
                    "situational" -> {
                        if (selectedSituationalItems.size < 2 && !selectedSituationalItems.contains(item)) {
                            selectedSituationalItems.add(item)
                        }
                    }
                    "situational_alt" -> {
                        if (selectedAltSituationalItems.size < 2 && !selectedAltSituationalItems.contains(item)) {
                            selectedAltSituationalItems.add(item)
                        }
                    }
                    "boots" -> {
                        selectedBootsItem = item
                    }
                }
                showItemPickerType = null
            }
        )
    }

    // 3. Selector de Runas
    if (showRunePickerType != null) {
        val pickerType = showRunePickerType!!
        RuneCatalogSelectionDialog(
            isKeystone = pickerType == "keystone",
            onDismiss = { showRunePickerType = null },
            onSelect = { rune ->
                if (pickerType == "keystone") {
                    selectedKeystoneRune = rune
                } else {
                    if (selectedSecondaryRunes.size < 4 && !selectedSecondaryRunes.contains(rune)) {
                        selectedSecondaryRunes.add(rune)
                    }
                }
                showRunePickerType = null
            }
        )
    }

    // 4. Selector de Hechizos
    if (showSpellPicker) {
        SpellCatalogSelectionDialog(
            onDismiss = { showSpellPicker = false },
            onSelect = { spell ->
                if (selectedSpells.size < 2 && !selectedSpells.contains(spell)) {
                    selectedSpells.add(spell)
                }
                showSpellPicker = false
            }
        )
    }
}

@Composable
private fun ChampionCatalogSelectionDialog(
    onDismiss: () -> Unit,
    onSelect: (Champion) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedRoleFilter by remember { mutableStateOf("Todos") }
    val roles = listOf("Todos", "Solo", "Jungla", "Mid", "ADC", "Soporte")

    val champions = remember(searchQuery, selectedRoleFilter) {
        WildRiftRepository.champions.filter { champ ->
            val matchesSearch = champ.name.contains(searchQuery, ignoreCase = true)
            val roleStr = champ.primaryRole.displayName
            val matchesRole = if (selectedRoleFilter == "Todos") true else {
                when (selectedRoleFilter) {
                    "Solo" -> roleStr.contains("Solo", ignoreCase = true) || roleStr.contains("Barón", ignoreCase = true) || roleStr.contains("Top", ignoreCase = true)
                    "Jungla" -> roleStr.contains("Jungla", ignoreCase = true)
                    "Mid" -> roleStr.contains("Mid", ignoreCase = true) || roleStr.contains("Central", ignoreCase = true)
                    "ADC" -> roleStr.contains("Dúo", ignoreCase = true) || roleStr.contains("Dragón", ignoreCase = true) || roleStr.contains("ADC", ignoreCase = true)
                    "Soporte" -> roleStr.contains("Soporte", ignoreCase = true)
                    else -> true
                }
            }
            matchesSearch && matchesRole
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = HextechDarkBg,
        shape = RoundedCornerShape(16.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tr("Seleccionar Campeón"),
                    color = HextechGold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 450.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text(tr("Buscar campeón..."), fontSize = 12.sp, color = TextMuted) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechGold) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechCyan,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    )
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(roles) { r ->
                        val isSel = selectedRoleFilter == r
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSel) HextechCyan.copy(alpha = 0.25f) else HextechSurface)
                                .border(1.dp, if (isSel) HextechCyan else HextechCardBorder, RoundedCornerShape(6.dp))
                                .clickable { selectedRoleFilter = r }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = tr(r),
                                color = if (isSel) HextechCyan else TextMuted,
                                fontSize = 11.sp,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth().weight(1f, fill = false)
                ) {
                    items(champions) { champ ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                                .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                                .clickable { onSelect(champ) }
                                .padding(6.dp)
                        ) {
                            ChampionAvatar(champion = champ, size = 44.dp, showTierBadge = false)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = champ.name,
                                color = TextPrimary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {}
    )
}

@Composable
private fun ItemCatalogSelectionDialog(
    type: String,
    onDismiss: () -> Unit,
    onSelect: (WildRiftItem) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCat by remember { mutableStateOf("Todos") }
    
    val categories = listOf("Todos", "Físico", "Magia", "Defensa", "Botas")

    val items = remember(searchQuery, selectedCat, type) {
        WildRiftItemsData.list.filter { item ->
            val matchesSearch = item.name.contains(searchQuery, ignoreCase = true) || item.nameEn.contains(searchQuery, ignoreCase = true)
            val isBootOrEnchant = item.category.contains("bota", ignoreCase = true) || item.category.contains("encantamiento", ignoreCase = true)
            
            if (type == "boots") {
                matchesSearch && (isBootOrEnchant || item.name.contains("bota", ignoreCase = true) || item.name.contains("gloria", ignoreCase = true) || item.name.contains("estasis", ignoreCase = true))
            } else {
                val matchesCat = when (selectedCat) {
                    "Físico" -> item.category.contains("físico", ignoreCase = true) || item.category.contains("ataque", ignoreCase = true)
                    "Magia" -> item.category.contains("magia", ignoreCase = true) || item.category.contains("mágico", ignoreCase = true)
                    "Defensa" -> item.category.contains("defensa", ignoreCase = true) || item.category.contains("tanque", ignoreCase = true)
                    "Botas" -> isBootOrEnchant
                    else -> true
                }
                matchesSearch && matchesCat
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = HextechDarkBg,
        shape = RoundedCornerShape(16.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (type) {
                        "boots" -> tr("Seleccionar Botas / Encantamiento")
                        "situational" -> tr("Seleccionar Objeto Situacional (7 y 8)")
                        "situational_alt" -> tr("Seleccionar Alternativa Situacional (vs Rival)")
                        else -> tr("Seleccionar Objeto Core")
                    },
                    color = HextechGold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 450.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text(tr("Buscar objeto en catálogo..."), fontSize = 12.sp, color = TextMuted) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechCyan,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    )
                )

                if (type != "boots") {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(categories) { c ->
                            val isSel = selectedCat == c
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isSel) HextechGold.copy(alpha = 0.25f) else HextechSurface)
                                    .border(1.dp, if (isSel) HextechGold else HextechCardBorder, RoundedCornerShape(6.dp))
                                    .clickable { selectedCat = c }
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = tr(c),
                                color = if (isSel) HextechGold else TextMuted,
                                fontSize = 11.sp,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().weight(1f, fill = false)
            ) {
                items(items) { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurfaceVariant.copy(alpha = 0.6f))
                            .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                            .clickable { onSelect(item) }
                            .padding(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(HextechSurface),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(item.iconUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = item.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.name,
                            color = TextPrimary,
                            fontSize = 9.5.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    },
    confirmButton = {}
)
}

@Composable
private fun RuneCatalogSelectionDialog(
    isKeystone: Boolean,
    onDismiss: () -> Unit,
    onSelect: (RuneItem) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    
    val runes = remember(searchQuery, isKeystone) {
        WildRiftSpellsAndRunes.runes.filter { r ->
            val matchesSearch = r.name.contains(searchQuery, ignoreCase = true)
            val matchesType = if (isKeystone) r.category.equals("Clave", ignoreCase = true) else !r.category.equals("Clave", ignoreCase = true)
            matchesSearch && matchesType
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = HextechDarkBg,
        shape = RoundedCornerShape(16.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isKeystone) tr("Seleccionar Runa Clave") else tr("Seleccionar Runa Secundaria"),
                    color = HextechGold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 450.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text(tr("Buscar runa..."), fontSize = 12.sp, color = TextMuted) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechGold) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    )
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth().weight(1f, fill = false)
                ) {
                    items(runes) { rune ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(HextechSurfaceVariant.copy(alpha = 0.6f))
                                .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                                .clickable { onSelect(rune) }
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(HextechSurface),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(rune.iconUrl)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = rune.name,
                                    modifier = Modifier.size(34.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = rune.name,
                                color = TextPrimary,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {}
    )
}

@Composable
private fun SpellCatalogSelectionDialog(
    onDismiss: () -> Unit,
    onSelect: (SummonerSpellItem) -> Unit
) {
    val spells = WildRiftSpellsAndRunes.summonerSpells

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = HextechDarkBg,
        shape = RoundedCornerShape(16.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tr("Seleccionar Hechizo de Invocador"),
                    color = HextechGold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                }
            }
        },
        text = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().heightIn(max = 350.dp)
            ) {
                items(spells) { spell ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurfaceVariant.copy(alpha = 0.6f))
                            .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                            .clickable { onSelect(spell) }
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(HextechSurface),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(spell.iconUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = spell.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = spell.name,
                            color = TextPrimary,
                            fontSize = 10.5.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        },
        confirmButton = {}
    )
}
