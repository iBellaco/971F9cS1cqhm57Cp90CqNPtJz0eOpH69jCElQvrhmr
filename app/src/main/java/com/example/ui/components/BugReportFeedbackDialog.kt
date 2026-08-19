package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.util.tr
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

enum class FeedbackType(
    val title: String,
    val icon: ImageVector,
    val label: String,
    val githubLabel: String,
    val prefix: String
) {
    BUG("Reportar Bug", Icons.Default.BugReport, "Bug / Error", "bug", "[BUG] "),
    SUGGESTION("Sugerencia", Icons.Default.Lightbulb, "Idea / Mejora", "enhancement", "[SUGERENCIA] "),
    META_CHAMPION("Campeón/Meta", Icons.Default.SportsEsports, "Meta / Campeón", "gameplay", "[META] ")
}

@Composable
fun BugReportFeedbackDialog(
    onDismiss: () -> Unit,
    defaultRepo: String = "iBellaco/Wild-Rift-Drafting-"
) {
    val context = LocalContext.current

    var selectedType by remember { mutableStateOf(FeedbackType.BUG) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var githubRepo by remember { mutableStateOf(defaultRepo) }
    var isCopied by remember { mutableStateOf(false) }

    val buildReportMarkdown: () -> String = {
        """
        ## ${selectedType.prefix}${title.ifBlank { "Sin título especificado" }}
        
        **Tipo:** ${selectedType.title}
        **Fecha:** ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date())}
        
        ### 📝 Descripción
        ${description.ifBlank { "No se proporcionó descripción detallada." }}
        
        ---
        ### 📱 Información del Entorno
        - **App:** Wild Rift Drafting Assistant
        - **Versión de Parche:** ${WildRiftRepository.CURRENT_PATCH_VERSION}
        - **Dispositivo:** ${Build.MANUFACTURER} ${Build.MODEL} (Android ${Build.VERSION.RELEASE}, API ${Build.VERSION.SDK_INT})
        """.trimIndent()
    }

    val canPublish = title.trim().isNotBlank() && description.trim().isNotBlank()
    val canCopy = title.trim().isNotBlank() || description.trim().isNotBlank()

    val openGitHubIssue: () -> Unit = {
        if (canPublish) {
            try {
                val formattedTitle = "${selectedType.prefix}${title.trim()}"
                val formattedBody = buildReportMarkdown()
                val cleanRepo = githubRepo.trim().removePrefix("https://github.com/").removeSuffix("/")

                val encodedTitle = URLEncoder.encode(formattedTitle, StandardCharsets.UTF_8.toString())
                val encodedBody = URLEncoder.encode(formattedBody, StandardCharsets.UTF_8.toString())
                val encodedLabels = URLEncoder.encode(selectedType.githubLabel, StandardCharsets.UTF_8.toString())

                val githubUrl = "https://github.com/$cleanRepo/issues/new?title=$encodedTitle&body=$encodedBody&labels=$encodedLabels"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl)).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
                Toast.makeText(context, "Abriendo GitHub Issues...", Toast.LENGTH_SHORT).show()
                onDismiss()
            } catch (e: Exception) {
                Toast.makeText(context, "Error al abrir GitHub: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Por favor completa el título y la descripción", Toast.LENGTH_SHORT).show()
        }
    }

    val copyToClipboard: () -> Unit = {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Wild Rift Bug Report", buildReportMarkdown())
        clipboard.setPrimaryClip(clip)
        isCopied = true
        Toast.makeText(context, "Reporte copiado al portapapeles ✓", Toast.LENGTH_SHORT).show()
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
                        text = tr("Reportes & Sugerencias"),
                        color = TextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Close, contentDescription = tr("Cerrar"), tint = TextMuted)
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
                    text = tr("Reporta fallos o envía sugerencias para mejorar el asistente:"),
                    color = TextMuted,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )

                // Selector de Tipo de Feedback
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
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
                                .padding(vertical = 8.dp, horizontal = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = type.icon,
                                    contentDescription = null,
                                    tint = if (isSelected) HextechGold else TextMuted,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = tr(type.label),
                                    color = if (isSelected) HextechGold else TextPrimary,
                                    fontSize = 10.sp,
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
                                FeedbackType.BUG -> tr("Ej: El overlay no detecta la pantalla")
                                FeedbackType.SUGGESTION -> tr("Ej: Agregar temporizador de dragones con audio")
                                FeedbackType.META_CHAMPION -> tr("Ej: Actualizar build recomendada de Veigar")
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
                            tr("Describe qué sucedió, cómo reproducirlo o tu idea para mejorar la app..."),
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

                // Diagnóstico del sistema
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechSurfaceVariant.copy(alpha = 0.6f))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "${tr("📱 Diagnóstico:")} ${Build.MODEL} • Android ${Build.VERSION.RELEASE} • ${WildRiftRepository.CURRENT_PATCH_VERSION}",
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = openGitHubIssue,
                enabled = canPublish,
                colors = ButtonDefaults.buttonColors(
                    containerColor = HextechGold,
                    disabledContainerColor = HextechGold.copy(alpha = 0.25f),
                    disabledContentColor = TextMuted
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.testTag("submit_github_issue_button")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                        contentDescription = null,
                        tint = if (canPublish) Color.Black else TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = tr("Publicar en GitHub"),
                        color = if (canPublish) Color.Black else TextMuted,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = copyToClipboard,
                enabled = canCopy,
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (canCopy) HextechCardBorder else HextechCardBorder.copy(alpha = 0.3f)
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isCopied) Icons.Default.Check else Icons.Default.ContentCopy,
                        contentDescription = null,
                        tint = if (isCopied) HextechCyan else if (canCopy) TextPrimary else TextMuted,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isCopied) tr("Copiado") else tr("Copiar"),
                        color = if (canCopy) TextPrimary else TextMuted,
                        fontSize = 12.sp
                    )
                }
            }
        }
    )
}
