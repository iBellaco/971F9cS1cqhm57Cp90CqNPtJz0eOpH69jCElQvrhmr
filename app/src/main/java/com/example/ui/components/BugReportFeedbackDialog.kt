package com.example.ui.components

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lightbulb
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.WildRiftRepository
import com.example.data.supabase.FeedbackRepository
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

import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

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
    var suggestedChampion by remember { mutableStateOf("") }
    var championSearchQuery by remember { mutableStateOf("") }
    var showChampionDropdown by remember { mutableStateOf(false) }
    var suggestedRole by remember { mutableStateOf("Mid") }
    var suggestedRunes by remember { mutableStateOf("") }
    var suggestedSpells by remember { mutableStateOf("") }
    var suggestedCoreItems by remember { mutableStateOf("") }
    var suggestedSituationalItems by remember { mutableStateOf("") }
    var suggestedBoots by remember { mutableStateOf("") }

    var isSubmitting by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf<String?>(null) }
    var selectedImageUri by remember { mutableStateOf<android.net.Uri?>(null) }
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
    val canPublish = if (selectedType == FeedbackType.BUILD_SUGGESTION) {
        suggestedChampion.isNotBlank() && (suggestedCoreItems.isNotBlank() || title.isNotBlank() || description.isNotBlank()) && isEmailValid
    } else {
        title.trim().isNotBlank() && description.trim().isNotBlank() && isEmailValid
    }

    val sendFeedbackMessage: () -> Unit = {
        if (canPublish) {
            isSubmitting = true
            statusMessage = null
            scope.launch {
                var finalTitle = title.ifBlank { "Sugerencia de Build para $suggestedChampion ($suggestedRole)" }
                var finalDesc = description
                if (selectedType == FeedbackType.BUILD_SUGGESTION) {
                    val buildDetails = buildString {
                        appendLine("--- SUGERENCIA DE BUILD DE COMUNIDAD ---")
                        appendLine("• Campeón: $suggestedChampion")
                        appendLine("• Rol/Línea: $suggestedRole")
                        if (suggestedCoreItems.isNotBlank()) appendLine("• Objetos Core (1-6): $suggestedCoreItems")
                        if (suggestedSituationalItems.isNotBlank()) appendLine("• Objetos Situacionales (7-8): $suggestedSituationalItems")
                        if (suggestedBoots.isNotBlank()) appendLine("• Botas y Mejora: $suggestedBoots")
                        if (suggestedRunes.isNotBlank()) appendLine("• Runas: $suggestedRunes")
                        if (suggestedSpells.isNotBlank()) appendLine("• Hechizos: $suggestedSpells")
                        if (description.isNotBlank()) {
                            appendLine("\n• Notas / Explicación Táctica:")
                            appendLine(description)
                        }
                    }
                    finalDesc = buildDetails
                } else {
                    if (suggestedChampion.isNotBlank()) finalDesc += "\n\nCampeón Sugerido: $suggestedChampion"
                    if (suggestedRole.isNotBlank()) finalDesc += "\nRol Sugerido: $suggestedRole"
                    if (suggestedRunes.isNotBlank()) finalDesc += "\nRunas Sugeridas: $suggestedRunes"
                    if (suggestedSpells.isNotBlank()) finalDesc += "\nHechizos Sugeridos: $suggestedSpells"
                }
                
                val result = FeedbackRepository.submitFeedback(
                    type = selectedType.name,
                    title = finalTitle,
                    description = finalDesc,
                    email = email.trim().takeIf { it.isNotEmpty() },
                    imagesBase64 = selectedImages,
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
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = tr("Envía tus reportes de fallos o sugerencias para seguir mejorando la aplicación:"),
                    color = TextMuted,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )

                // Selector de Tipo de Feedback (Bug o Sugerencia)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FeedbackType.values().forEach { type ->
                        val isSelected = selectedType == type
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (isSelected) HextechGold.copy(alpha = 0.25f)
                                    else HextechSurface
                                )
                                .border(
                                    width = if (isSelected) 1.5.dp else 1.dp,
                                    color = if (isSelected) HextechGold else HextechCardBorder,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { selectedType = type }
                                .padding(vertical = 8.dp, horizontal = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = type.icon,
                                    contentDescription = null,
                                    tint = if (isSelected) HextechGold else TextMuted,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = tr(type.label),
                                    color = if (isSelected) HextechGold else TextPrimary,
                                    fontSize = 11.5.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }

                // ==========================================
                // CAMPOS ESPECÍFICOS PARA SUGERIR BUILD
                // ==========================================
                if (selectedType == FeedbackType.BUILD_SUGGESTION) {
                    // Selector de Campeón
                    Column {
                        Text(
                            text = tr("Campeón específico:"),
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = if (suggestedChampion.isNotEmpty()) suggestedChampion else championSearchQuery,
                            onValueChange = {
                                championSearchQuery = it
                                suggestedChampion = it
                                showChampionDropdown = it.isNotEmpty()
                            },
                            placeholder = { Text(tr("Escribe el nombre del campeón (ej. Ahri, Zed, Yasuo)..."), fontSize = 11.5.sp, color = TextMuted) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = HextechCyan,
                                unfocusedBorderColor = HextechCardBorder,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            )
                        )
                        
                        // Lista de sugerencias de campeones
                        val filteredChamps = remember(championSearchQuery) {
                            if (championSearchQuery.isBlank()) emptyList<com.example.model.Champion>()
                            else WildRiftRepository.champions.filter {
                                it.name.contains(championSearchQuery, ignoreCase = true)
                            }.take(6)
                        }

                        if (filteredChamps.isNotEmpty() && suggestedChampion != filteredChamps.firstOrNull()?.name) {
                            Spacer(modifier = Modifier.height(4.dp))
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(filteredChamps) { champ ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(HextechSurfaceVariant)
                                            .border(1.dp, HextechGold, RoundedCornerShape(6.dp))
                                            .clickable {
                                                suggestedChampion = champ.name
                                                championSearchQuery = champ.name
                                            }
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(champ.name, color = HextechGold, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                                    }
                                }
                            }
                        }
                    }

                    // Selector de Rol / Línea
                    Column {
                        Text(
                            text = tr("Rol / Línea:"),
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

                    // Objetos Core (1 al 6)
                    OutlinedTextField(
                        value = suggestedCoreItems,
                        onValueChange = { suggestedCoreItems = it },
                        label = { Text(tr("Objetos Core 1 al 6"), fontSize = 11.sp) },
                        placeholder = { Text(tr("Ej: Eco de Luden, Sombrero de Rabadon, Bastón del Vacío..."), fontSize = 11.sp, color = TextMuted) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )

                    // Objetos Situacionales (7 y 8)
                    OutlinedTextField(
                        value = suggestedSituationalItems,
                        onValueChange = { suggestedSituationalItems = it },
                        label = { Text(tr("Objetos Situacionales (7 y 8)"), fontSize = 11.sp) },
                        placeholder = { Text(tr("Ej: 7. Morellonomicón • 8. Velo de Banshee"), fontSize = 11.sp, color = TextMuted) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )

                    // Botas y Mejora
                    OutlinedTextField(
                        value = suggestedBoots,
                        onValueChange = { suggestedBoots = it },
                        label = { Text(tr("Botas y Encantamiento / Mejora"), fontSize = 11.sp) },
                        placeholder = { Text(tr("Ej: Botas de dinamismo -> Avance magnético"), fontSize = 11.sp, color = TextMuted) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )

                    // Runas (Principal y Secundarias)
                    OutlinedTextField(
                        value = suggestedRunes,
                        onValueChange = { suggestedRunes = it },
                        label = { Text(tr("Runas Sugeridas (Principal + 4 Secundarias)"), fontSize = 11.sp) },
                        placeholder = { Text(tr("Ej: Electrocutar • Impacto súbito, Golpe de gracia, Colección de ojos, Trascendencia"), fontSize = 11.sp, color = TextMuted) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )

                    // Hechizos
                    OutlinedTextField(
                        value = suggestedSpells,
                        onValueChange = { suggestedSpells = it },
                        label = { Text(tr("Hechizos de Invocador"), fontSize = 11.sp) },
                        placeholder = { Text(tr("Ej: Destello + Prender / Barrera"), fontSize = 11.sp, color = TextMuted) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )
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
                        .height(100.dp)
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

                // Subir Imágenes (Max 3)
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
                            imageVector = androidx.compose.material.icons.Icons.Default.Image,
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
                                    imageVector = androidx.compose.material.icons.Icons.Default.Image,
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
}

