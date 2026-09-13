package com.example.ui.components

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UserInboxDialog(
    userUid: String,
    onDismiss: () -> Unit
) {
    var subcollectionMessages by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var arrayMessages by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(userUid) {
        val db = FirebaseFirestore.getInstance()
        val userDoc = db.collection("users").document(userUid)
        
        // 1. Escuchar subcolección messages
        userDoc.collection("messages")
            .addSnapshotListener { snapshot, error ->
                if (error == null && snapshot != null) {
                    val msgs = snapshot.documents.mapNotNull { doc ->
                        doc.data?.plus("id" to doc.id)
                    }
                    subcollectionMessages = msgs
                }
                isLoading = false
            }

        // 2. Escuchar campo privateMessages en el documento del usuario
        userDoc.addSnapshotListener { snapshot, error ->
            if (error == null && snapshot != null && snapshot.exists()) {
                @Suppress("UNCHECKED_CAST")
                val pMsgs = snapshot.get("privateMessages") as? List<Map<String, Any>>
                if (pMsgs != null) {
                    arrayMessages = pMsgs
                }
            }
            isLoading = false
        }
    }

    // Unir mensajes de ambas fuentes eliminando duplicados por id
    val messages = remember(subcollectionMessages, arrayMessages) {
        val all = mutableMapOf<String, Map<String, Any>>()
        for (m in arrayMessages) {
            val id = m["id"] as? String ?: continue
            all[id] = m
        }
        for (m in subcollectionMessages) {
            val id = m["id"] as? String ?: continue
            all[id] = m
        }
        all.values.sortedByDescending { (it["timestamp"] as? Long) ?: 0L }
    }

    // Si la bandeja está vacía y se terminó de cargar, limpiar automáticamente el badge pendiente en Firestore
    LaunchedEffect(isLoading, messages.isEmpty()) {
        if (!isLoading && messages.isEmpty()) {
            val db = FirebaseFirestore.getInstance()
            val uRef = db.collection("users").document(userUid)
            uRef.update(
                "hasUnreadMessages", false,
                "unreadMessagesCount", 0
            )
        }
    }

    fun markMessageAsRead(id: String) {
        val db = FirebaseFirestore.getInstance()
        val uRef = db.collection("users").document(userUid)
        uRef.collection("messages").document(id).update("isRead", true)
        uRef.get().addOnSuccessListener { snap ->
            @Suppress("UNCHECKED_CAST")
            val pMsgs = snap.get("privateMessages") as? List<Map<String, Any>>
            if (pMsgs != null) {
                val updated = pMsgs.map { m ->
                    if (m["id"] == id) m.toMutableMap().apply { put("isRead", true) } else m
                }
                val remainingUnread = updated.count { (it["isRead"] as? Boolean) == false }
                uRef.update(
                    "privateMessages", updated,
                    "hasUnreadMessages", remainingUnread > 0,
                    "unreadMessagesCount", remainingUnread
                )
            } else {
                uRef.update(
                    "hasUnreadMessages", false,
                    "unreadMessagesCount", 0
                )
            }
        }
    }

    fun markAllAsRead() {
        val db = FirebaseFirestore.getInstance()
        val uRef = db.collection("users").document(userUid)
        for (m in messages) {
            val id = m["id"] as? String ?: continue
            uRef.collection("messages").document(id).update("isRead", true)
        }
        uRef.get().addOnSuccessListener { snap ->
            @Suppress("UNCHECKED_CAST")
            val pMsgs = snap.get("privateMessages") as? List<Map<String, Any>>
            val updated = pMsgs?.map { m ->
                m.toMutableMap().apply { put("isRead", true) }
            } ?: emptyList()
            uRef.update(
                "privateMessages", updated,
                "hasUnreadMessages", false,
                "unreadMessagesCount", 0
            )
        }
    }

    fun deleteMessage(id: String) {
        val db = FirebaseFirestore.getInstance()
        val uRef = db.collection("users").document(userUid)
        uRef.collection("messages").document(id).delete()
        uRef.get().addOnSuccessListener { snap ->
            @Suppress("UNCHECKED_CAST")
            val pMsgs = snap.get("privateMessages") as? List<Map<String, Any>>
            if (pMsgs != null) {
                val updated = pMsgs.filter { it["id"] != id }
                val remainingUnread = updated.count { (it["isRead"] as? Boolean) == false }
                uRef.update(
                    "privateMessages", updated,
                    "hasUnreadMessages", remainingUnread > 0,
                    "unreadMessagesCount", remainingUnread
                )
            } else {
                uRef.update(
                    "hasUnreadMessages", false,
                    "unreadMessagesCount", 0
                )
            }
        }
    }

    var showSupportDialog by remember { mutableStateOf(false) }
    if (showSupportDialog) {
        SupportReportDialog(onDismiss = { showSupportDialog = false })
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.85f),
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF0F172A),
            border = BorderStroke(1.5.dp, Color(0xFF0EA5E9))
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Message, contentDescription = null, tint = Color(0xFF0EA5E9))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Bandeja de Entrada", fontWeight = FontWeight.Bold, color = Color(0xFF0EA5E9), fontSize = 18.sp)
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                OutlinedButton(
                    onClick = { showSupportDialog = true },
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF0EA5E9)),
                    border = BorderStroke(1.dp, Color(0xFF0EA5E9).copy(alpha = 0.6f)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.HeadsetMic, contentDescription = null, tint = Color(0xFF0EA5E9), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Enviar Reporte o Mensaje de Soporte", color = Color(0xFF0EA5E9), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))

                val unreadCount = messages.count { (it["isRead"] as? Boolean) == false }
                if (messages.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            if (unreadCount > 0) "$unreadCount no leído(s)" else "Todos leídos",
                            color = if (unreadCount > 0) Color(0xFF38BDF8) else Color.Gray,
                            fontSize = 12.sp
                        )
                        if (unreadCount > 0) {
                            TextButton(
                                onClick = { markAllAsRead() },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Icon(Icons.Default.MarkEmailRead, contentDescription = null, tint = Color(0xFF38BDF8), modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Marcar todos leídos", color = Color(0xFF38BDF8), fontSize = 12.sp)
                            }
                        }
                    }
                }
                
                Divider(color = Color(0xFF1E293B))
                Spacer(modifier = Modifier.height(8.dp))
                
                if (isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF0EA5E9))
                    }
                } else if (messages.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Message, contentDescription = null, tint = Color.DarkGray, modifier = Modifier.size(48.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("No tienes mensajes en tu bandeja.", color = Color.Gray, fontSize = 14.sp)
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(messages, key = { it["id"] as String }) { msg ->
                            val id = msg["id"] as String
                            val title = msg["title"] as? String ?: "Sin título"
                            val content = msg["content"] as? String ?: ""
                            val rawTag = msg["tag"] as? String
                            val messageTag = MessageTag.fromId(rawTag)
                            val timestamp = msg["timestamp"] as? Long ?: 0L
                            val isRead = msg["isRead"] as? Boolean ?: false
                            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                            val dateStr = sdf.format(Date(timestamp))

                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                                shape = RoundedCornerShape(8.dp),
                                border = if (!isRead) BorderStroke(1.dp, Color(0xFF0EA5E9)) else null,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(
                                            modifier = Modifier.weight(1f),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            if (!isRead) {
                                                Surface(
                                                    shape = RoundedCornerShape(4.dp),
                                                    color = Color(0xFF0EA5E9).copy(alpha = 0.2f),
                                                    modifier = Modifier.padding(end = 6.dp)
                                                ) {
                                                    Text(
                                                        "NUEVO",
                                                        color = Color(0xFF38BDF8),
                                                        fontSize = 9.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                                    )
                                                }
                                            }

                                            // Badge de Etiqueta del Mensaje (Mantenimiento, Importante, Oferta, etc.)
                                            Surface(
                                                shape = RoundedCornerShape(4.dp),
                                                color = messageTag.badgeBg.copy(alpha = 0.2f),
                                                border = BorderStroke(0.5.dp, messageTag.badgeBg),
                                                modifier = Modifier.padding(end = 6.dp)
                                            ) {
                                                Text(
                                                    "${messageTag.emoji} ${messageTag.label.uppercase()}",
                                                    color = if (messageTag.textColor == Color.Black) messageTag.badgeBg else messageTag.textColor,
                                                    fontSize = 8.5.sp,
                                                    fontWeight = FontWeight.ExtraBold,
                                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                                )
                                            }

                                            Text(title, color = if (!isRead) Color(0xFF0EA5E9) else Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                        }

                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            if (!isRead) {
                                                IconButton(
                                                    onClick = { markMessageAsRead(id) },
                                                    modifier = Modifier.size(28.dp)
                                                ) {
                                                    Icon(Icons.Default.MarkEmailRead, contentDescription = "Marcar como leído", tint = Color(0xFF0EA5E9), modifier = Modifier.size(18.dp))
                                                }
                                                Spacer(modifier = Modifier.width(4.dp))
                                            }
                                            IconButton(
                                                onClick = { deleteMessage(id) },
                                                modifier = Modifier.size(28.dp)
                                            ) {
                                                Icon(Icons.Default.Delete, contentDescription = "Borrar", tint = Color.Gray, modifier = Modifier.size(18.dp))
                                            }
                                        }
                                    }
                                    Text(dateStr, color = Color.Gray, fontSize = 11.sp)
                                    Spacer(modifier = Modifier.height(8.dp))
                                                                         val sender = msg["sender"] as? String ?: ""
                                     val repliedBy = msg["repliedBy"] as? String ?: ""
                                     val adminReply = msg["adminReply"] as? String ?: ""
                                     if (sender.isNotBlank()) {
                                         Text("Enviado por: $sender", color = Color(0xFF94A3B8), fontSize = 11.sp, fontWeight = FontWeight.Medium)
                                         Spacer(modifier = Modifier.height(4.dp))
                                     }
                                     Text(content, color = Color.LightGray, fontSize = 13.sp)
                                     if (adminReply.isNotBlank() || repliedBy.isNotBlank()) {
                                         Spacer(modifier = Modifier.height(8.dp))
                                         Surface(
                                             shape = RoundedCornerShape(6.dp),
                                             color = Color(0xFF0F172A),
                                             border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f)),
                                             modifier = Modifier.fillMaxWidth()
                                         ) {
                                             Column(modifier = Modifier.padding(8.dp)) {
                                                 Text(
                                                     "Respuesta de Soporte${if (repliedBy.isNotBlank()) " ($repliedBy)" else ""}:",
                                                     color = Color(0xFF38BDF8),
                                                     fontSize = 11.sp,
                                                     fontWeight = FontWeight.Bold
                                                 )
                                                 Spacer(modifier = Modifier.height(2.dp))
                                                 Text(adminReply, color = Color.White, fontSize = 12.sp)
                                             }
                                         }
                                     }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
