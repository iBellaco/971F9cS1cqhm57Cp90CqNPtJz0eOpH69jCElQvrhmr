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

enum class FeedbackType(
    val title: String,
    val icon: ImageVector,
    val label: String
) {
    BUG("Reportar Bug", Icons.Default.BugReport, "Bug / Error"),
    SUGGESTION("Sugerencia", Icons.Default.Lightbulb, "Idea / Sugerencia")
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
    val errorMsg = "Error al procesar la imagen"

    val imagePickerLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia(maxItems = 3)
    ) { uris ->
        if (uris.isNotEmpty()) {
            scope.launch {
                val newImages = mutableListOf<String>()
                for (uri in uris) {
                    val base64 = com.example.util.ImageUtils.uriToBase64(context, uri)
                    if (base64 != null) {
                        newImages.add(base64)
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

    val canPublish = title.trim().isNotBlank() && description.trim().isNotBlank() && selectedImages.isNotEmpty()

    val sendFeedbackMessage: () -> Unit = {
        if (canPublish) {
            isSubmitting = true
            statusMessage = null
            scope.launch {
                val result = FeedbackRepository.submitFeedback(
                    type = selectedType.name,
                    title = title,
                    description = description,
                    imagesBase64 = selectedImages,
                    retentionDays = 7
                )
                isSubmitting = false
                if (result.isSuccess) {
                    Toast.makeText(context, "✅ ¡Mensaje enviado con éxito!", Toast.LENGTH_LONG).show()
                    onDismiss()
                } else {
                    val err = result.exceptionOrNull()?.message ?: "Error desconocido"
                    statusMessage = "❌ Error al enviar: $err"
                    Toast.makeText(context, "Error: $err", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(context, "Por favor completa el título y la descripción", Toast.LENGTH_SHORT).show()
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

                // Campo Título
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(tr("Título del reporte o sugerencia"), fontSize = 12.sp) },
                    placeholder = {
                        Text(
                            when (selectedType) {
                                FeedbackType.BUG -> tr("Ej: El overlay no detecta la pantalla de selección")
                                FeedbackType.SUGGESTION -> tr("Ej: Agregar temporizador de dragones con audio")
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
                    label = { Text(tr("Descripción detallada"), fontSize = 12.sp) },
                    placeholder = {
                        Text(
                            when (selectedType) {
                                FeedbackType.BUG -> tr("Describe qué sucedió o cómo reproducir el error...")
                                FeedbackType.SUGGESTION -> tr("Describe tu idea o mejora para la aplicación...")
                            },
                            fontSize = 11.5.sp,
                            color = TextMuted
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .testTag("feedback_desc_input"),
                    maxLines = 5,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedLabelColor = HextechGold,
                        unfocusedLabelColor = TextMuted
                    )
                )

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

