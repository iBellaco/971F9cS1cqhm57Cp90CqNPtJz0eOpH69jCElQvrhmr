package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.SupportReplyManager
import com.example.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun SupportReplyDialog(
    reportId: String,
    reportTitle: String,
    reportDescription: String,
    userEmail: String,
    userName: String = "",
    initialReply: String = "",
    isFirestoreDoc: Boolean = false,
    onDismiss: () -> Unit,
    onReplySent: (replyText: String, markedAsRead: Boolean) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var markAsRead by remember { mutableStateOf(true) }
    var isSending by remember { mutableStateOf(false) }

    val authUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
    val responderName = authUser?.displayName?.takeIf { it.isNotBlank() }
        ?: authUser?.email?.substringBefore("@")?.takeIf { it.isNotBlank() }
        ?: "Administrador"

    val displayUserName = when {
        !userName.isBlank() && !userName.contains("@") -> userName
        !userEmail.isBlank() -> userEmail.substringBefore("@")
        else -> "Usuario"
    }

    val quickTemplates = listOf(
        "👋 Hola $displayUserName, soy $responderName del equipo de soporte de Coach. Gracias por escribirnos, hemos recibido tu mensaje y estamos para ayudarte a la brevedad.",
        "✅ ¡Problema solucionado! Esta incidencia fue corregida en la última actualización de Coach. Te sugerimos actualizar tu app.",
        "🔄 Te sugerimos cerrar sesión, reiniciar la app y volver a ingresar para sincronizar tus configuraciones de forma óptima.",
        "🛡️ Hemos verificado la configuración de tu cuenta y optimizado tus datos. Por favor confirma si el problema persiste.",
        "🔍 Tu reporte está siendo analizado detalladamente por nuestro equipo técnico prioritario. Te notificaremos cualquier avance.",
        "💡 Recuerda que puedes consultar la sección de guías y optimización en el menú principal para aprovechar al máximo las funciones de Coach."
    )

    var replyText by remember { mutableStateOf(initialReply.ifBlank { quickTemplates[0] }) }

    Dialog(
        onDismissRequest = { if (!isSending) onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(HextechDarkBg.copy(alpha = 0.9f))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 680.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = BorderStroke(1.5.dp, HextechCyan)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Encabezado
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechCyan.copy(alpha = 0.15f))
                                    .border(1.dp, HextechCyan, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Reply,
                                    contentDescription = null,
                                    tint = HextechCyan,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Responder Mensaje",
                                    color = HextechGold,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Atención y soporte directo al usuario",
                                    color = TextSecondary,
                                    fontSize = 11.sp
                                )
                            }
                        }

                        IconButton(
                            onClick = onDismiss,
                            enabled = !isSending,
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tarjeta con información del mensaje original
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(HextechSurface)
                            .border(1.dp, HextechCardBorder, RoundedCornerShape(10.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = reportTitle.ifBlank { "Ticket de soporte" },
                                    color = HextechCyan,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                                if (userEmail.isNotBlank()) {
                                    Text(
                                        text = userEmail,
                                        color = TextMuted,
                                        fontSize = 10.5.sp,
                                        maxLines = 1
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = reportDescription.take(220) + if (reportDescription.length > 220) "..." else "",
                                color = TextSecondary,
                                fontSize = 11.5.sp,
                                lineHeight = 15.5.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Plantillas rápidas de respuesta
                    Text(
                        text = "Plantillas rápidas de respuesta:",
                        color = HextechGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        quickTemplates.forEachIndexed { index, tpl ->
                            val label = when (index) {
                                0 -> "👋 Saludo"
                                1 -> "✅ Solucionado"
                                2 -> "🔄 Reinicio"
                                3 -> "🛡️ Cuenta"
                                4 -> "🔍 Revisión"
                                else -> "💡 Guía"
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurfaceVariant)
                                    .border(0.8.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                                    .clickable {
                                        replyText = tpl
                                    }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = label,
                                    color = HextechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Campo de redacción de respuesta
                    Text(
                        text = "Escribe tu respuesta:",
                        color = HextechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = replyText,
                        onValueChange = { replyText = it },
                        placeholder = {
                            Text(
                                text = "Escribe aquí la respuesta que se enviará y mostrará al usuario...",
                                fontSize = 12.sp,
                                color = TextMuted
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp, max = 200.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedContainerColor = HextechSurface,
                            unfocusedContainerColor = HextechSurface
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Checkbox para marcar como leído / solucionado
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { markAsRead = !markAsRead },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = markAsRead,
                            onCheckedChange = { markAsRead = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = HextechCyan,
                                checkmarkColor = HextechDarkBg
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Marcar mensaje como leído / atendido automáticamente",
                            color = TextPrimary,
                            fontSize = 11.5.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Botones de acción
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Si hay correo disponible, botón adicional para abrir app de email
                        if (userEmail.isNotBlank()) {
                            OutlinedButton(
                                onClick = {
                                    val cleanReply = replyText.trim()
                                    if (cleanReply.isNotBlank()) {
                                        try {
                                            val intent = SupportReplyManager.createEmailReplyIntent(
                                                email = userEmail,
                                                title = reportTitle,
                                                replyText = cleanReply
                                            )
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            Toast.makeText(context, "No se encontró aplicación de correo", Toast.LENGTH_SHORT).show()
                                        }
                                    } else {
                                        Toast.makeText(context, "Escribe una respuesta antes de enviar por correo", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.6f)),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 4.dp)
                            ) {
                                Icon(Icons.Default.Email, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Vía Correo", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }

                        // Botón de guardar y enviar respuesta
                        Button(
                            onClick = {
                                val cleanText = replyText.trim()
                                if (cleanText.isBlank()) {
                                    Toast.makeText(context, "La respuesta no puede estar vacía", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }
                                isSending = true
                                coroutineScope.launch {
                                    val ok = SupportReplyManager.sendSupportReply(
                                        context = context,
                                        reportId = reportId,
                                        replyText = cleanText,
                                        author = "Equipo Coach",
                                        userEmail = userEmail,
                                        reportTitle = reportTitle,
                                        isFirestoreDoc = isFirestoreDoc,
                                        markAsRead = markAsRead
                                    )
                                    isSending = false
                                    if (ok) {
                                        Toast.makeText(context, "Respuesta guardada y sincronizada exitosamente", Toast.LENGTH_LONG).show()
                                        onReplySent(cleanText, markAsRead)
                                        onDismiss()
                                    } else {
                                        Toast.makeText(context, "Se guardó localmente la respuesta", Toast.LENGTH_SHORT).show()
                                        onReplySent(cleanText, markAsRead)
                                        onDismiss()
                                    }
                                }
                            },
                            enabled = !isSending,
                            modifier = Modifier
                                .weight(1.3f)
                                .height(42.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            if (isSending) {
                                CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                            } else {
                                Icon(Icons.Default.Send, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Enviar Respuesta", color = HextechDarkBg, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
