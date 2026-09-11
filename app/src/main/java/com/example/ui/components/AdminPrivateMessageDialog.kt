package com.example.ui.components

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue
import java.util.UUID

enum class MessageTag(
    val id: String,
    val label: String,
    val emoji: String,
    val badgeBg: Color,
    val textColor: Color
) {
    AVISO("aviso", "Aviso", "📢", Color(0xFF3B82F6), Color.White),
    IMPORTANTE("importante", "Importante", "🚨", Color(0xFFEF4444), Color.White),
    MANTENIMIENTO("mantenimiento", "Mantenimiento", "🛠️", Color(0xFFF97316), Color.White),
    OFERTA("oferta", "Oferta", "💎", Color(0xFFEAB308), Color.Black),
    PRUEBA("prueba", "Prueba", "🧪", Color(0xFF06B6D4), Color.Black);

    companion object {
        fun fromId(id: String?): MessageTag {
            return values().firstOrNull { it.id.equals(id, ignoreCase = true) } ?: AVISO
        }
    }
}

enum class MessageAudienceTarget(val label: String) {
    SINGLE_USER("Este usuario"),
    ALL_USERS("Todos los usuarios"),
    PREMIUM_ONLY("Solo Exclusivos")
}

@Composable
fun AdminPrivateMessageDialog(
    userUid: String,
    onDismiss: () -> Unit,
    onSuccess: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedTag by remember { mutableStateOf(MessageTag.AVISO) }
    var targetAudience by remember { mutableStateOf(MessageAudienceTarget.SINGLE_USER) }
    var isProcessing by remember { mutableStateOf(false) }
    var statusText by remember { mutableStateOf("") }
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = Color(0xFF0F172A),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF59E0B)),
            modifier = Modifier.fillMaxWidth(0.95f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Message, contentDescription = null, tint = Color(0xFFF59E0B))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Enviar Mensaje / Comunicado", color = Color(0xFFF59E0B), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                // Selector de Etiquetas (Mantenimiento, Importante, Prueba, Oferta, Aviso)
                Text("Etiqueta del Mensaje:", color = Color.LightGray, fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    MessageTag.values().forEach { tag ->
                        val isSelected = selectedTag == tag
                        Button(
                            onClick = { selectedTag = tag },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 2.dp, vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) tag.badgeBg else tag.badgeBg.copy(alpha = 0.15f)
                            )
                        ) {
                            Text(
                                "${tag.emoji} ${tag.label}",
                                color = if (isSelected) tag.textColor else tag.badgeBg,
                                fontSize = 9.5.sp,
                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                maxLines = 1
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text("Destinatarios:", color = Color.LightGray, fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    MessageAudienceTarget.values().forEach { target ->
                        val isSelected = targetAudience == target
                        val btnColor = when (target) {
                            MessageAudienceTarget.SINGLE_USER -> Color(0xFF0EA5E9)
                            MessageAudienceTarget.ALL_USERS -> Color(0xFF8B5CF6)
                            MessageAudienceTarget.PREMIUM_ONLY -> Color(0xFFF59E0B)
                        }
                        Button(
                            onClick = { targetAudience = target },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) btnColor else btnColor.copy(alpha = 0.15f)
                            )
                        ) {
                            val icon = when (target) {
                                MessageAudienceTarget.SINGLE_USER -> Icons.Default.Person
                                MessageAudienceTarget.ALL_USERS -> Icons.Default.People
                                MessageAudienceTarget.PREMIUM_ONLY -> Icons.Default.Star
                            }
                            Icon(icon, contentDescription = null, tint = if (isSelected) Color.White else btnColor, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                target.label,
                                color = if (isSelected) Color.White else btnColor,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                maxLines = 1
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(10.dp))
                
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título (ej: Nueva Actualización)", color = Color.Gray) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFFF59E0B)
                    )
                )
                
                Spacer(modifier = Modifier.height(6.dp))
                
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Mensaje del comunicado...", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFFF59E0B)
                    )
                )
                
                if (statusText.isNotBlank()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(statusText, color = Color(0xFF38BDF8), fontSize = 11.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss, enabled = !isProcessing) {
                        Text("Cancelar", color = Color.Gray)
                    }
                    Button(
                        onClick = {
                            if (title.isNotBlank() && content.isNotBlank()) {
                                isProcessing = true
                                statusText = "Enviando mensaje..."
                                val db = FirebaseFirestore.getInstance()

                                when (targetAudience) {
                                    MessageAudienceTarget.SINGLE_USER -> {
                                        val messageId = UUID.randomUUID().toString()
                                        val messageData = hashMapOf<String, Any>(
                                            "id" to messageId,
                                            "title" to title.trim(),
                                            "content" to content.trim(),
                                            "tag" to selectedTag.id,
                                            "timestamp" to System.currentTimeMillis(),
                                            "isRead" to false
                                        )
                                        val userDocRef = db.collection("users").document(userUid)
                                        userDocRef.collection("messages").document(messageId)
                                            .set(messageData)
                                            .addOnSuccessListener {
                                                userDocRef.update(
                                                    "hasUnreadMessages", true,
                                                    "unreadMessagesCount", FieldValue.increment(1),
                                                    "privateMessages", FieldValue.arrayUnion(messageData)
                                                ).addOnCompleteListener {
                                                    isProcessing = false
                                                    Toast.makeText(context, "¡Mensaje enviado con éxito!", Toast.LENGTH_SHORT).show()
                                                    onSuccess()
                                                    onDismiss()
                                                }
                                            }
                                            .addOnFailureListener {
                                                userDocRef.update(
                                                    "hasUnreadMessages", true,
                                                    "unreadMessagesCount", FieldValue.increment(1),
                                                    "privateMessages", FieldValue.arrayUnion(messageData)
                                                ).addOnCompleteListener {
                                                    isProcessing = false
                                                    Toast.makeText(context, "¡Mensaje enviado!", Toast.LENGTH_SHORT).show()
                                                    onSuccess()
                                                    onDismiss()
                                                }
                                            }
                                    }

                                    MessageAudienceTarget.ALL_USERS,
                                    MessageAudienceTarget.PREMIUM_ONLY -> {
                                        db.collection("users").get().addOnSuccessListener { snapshot ->
                                            val now = System.currentTimeMillis()
                                            val targetDocs = snapshot.documents.filter { doc ->
                                                if (targetAudience == MessageAudienceTarget.PREMIUM_ONLY) {
                                                    val role = doc.getString("role") ?: "free"
                                                    val until = doc.getLong("premiumUntil")
                                                    role == "admin" || (role == "premium" && (until == null || until == 0L || until > now))
                                                } else {
                                                    true
                                                }
                                            }

                                            if (targetDocs.isEmpty()) {
                                                isProcessing = false
                                                Toast.makeText(context, "No se encontraron usuarios destinatarios.", Toast.LENGTH_SHORT).show()
                                                return@addOnSuccessListener
                                            }

                                            var completedCount = 0
                                            val total = targetDocs.size
                                            statusText = "Entregando a $total usuarios..."

                                            for (doc in targetDocs) {
                                                val messageId = UUID.randomUUID().toString()
                                                val messageData = hashMapOf<String, Any>(
                                                    "id" to messageId,
                                                    "title" to title.trim(),
                                                    "content" to content.trim(),
                                                    "tag" to selectedTag.id,
                                                    "timestamp" to System.currentTimeMillis(),
                                                    "isRead" to false
                                                )
                                                val uRef = doc.reference
                                                uRef.collection("messages").document(messageId).set(messageData)
                                                uRef.update(
                                                    "hasUnreadMessages", true,
                                                    "unreadMessagesCount", FieldValue.increment(1),
                                                    "privateMessages", FieldValue.arrayUnion(messageData)
                                                ).addOnCompleteListener {
                                                    completedCount++
                                                    if (completedCount >= total) {
                                                        isProcessing = false
                                                        Toast.makeText(context, "¡Comunicado enviado a $total usuario(s)!", Toast.LENGTH_LONG).show()
                                                        onSuccess()
                                                        onDismiss()
                                                    }
                                                }
                                            }
                                        }.addOnFailureListener { e ->
                                            isProcessing = false
                                            Toast.makeText(context, "Error obteniendo usuarios: ${e.message}", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            }
                        },
                        enabled = !isProcessing && title.isNotBlank() && content.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF59E0B))
                    ) {
                        if (isProcessing) {
                            CircularProgressIndicator(modifier = Modifier.size(16.dp), color = Color.White)
                        } else {
                            Text("Enviar")
                        }
                    }
                }
            }
        }
    }
}
