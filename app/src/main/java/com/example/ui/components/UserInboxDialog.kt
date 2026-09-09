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

        // 2. Escuchar campo privateMessages en el documento del usuario (por si las reglas de subcolección bloquearon o viceversa)
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
                
                Divider(color = Color(0xFF1E293B))
                Spacer(modifier = Modifier.height(8.dp))
                
                if (isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF0EA5E9))
                    }
                } else if (messages.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No tienes mensajes nuevos.", color = Color.Gray)
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
                            val timestamp = msg["timestamp"] as? Long ?: 0L
                            val isRead = msg["isRead"] as? Boolean ?: false
                            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                            val dateStr = sdf.format(Date(timestamp))

                            LaunchedEffect(isRead) {
                                if (!isRead) {
                                    val db = FirebaseFirestore.getInstance()
                                    val uRef = db.collection("users").document(userUid)
                                    uRef.collection("messages").document(id)
                                        .update("isRead", true)
                                    uRef.get().addOnSuccessListener { snap ->
                                        @Suppress("UNCHECKED_CAST")
                                        val pMsgs = snap.get("privateMessages") as? List<Map<String, Any>>
                                        if (pMsgs != null) {
                                            val updated = pMsgs.map { m ->
                                                if (m["id"] == id) m.toMutableMap().apply { put("isRead", true) } else m
                                            }
                                            uRef.update("privateMessages", updated, "hasUnreadMessages", false)
                                        }
                                    }
                                }
                            }

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
                                        Text(title, color = if (!isRead) Color(0xFF0EA5E9) else Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                        IconButton(onClick = {
                                            val db = FirebaseFirestore.getInstance()
                                            val uRef = db.collection("users").document(userUid)
                                            uRef.collection("messages").document(id)
                                                .delete()
                                            uRef.get().addOnSuccessListener { snap ->
                                                @Suppress("UNCHECKED_CAST")
                                                val pMsgs = snap.get("privateMessages") as? List<Map<String, Any>>
                                                if (pMsgs != null) {
                                                    val updated = pMsgs.filter { it["id"] != id }
                                                    uRef.update("privateMessages", updated)
                                                }
                                            }
                                        }) {
                                            Icon(Icons.Default.Delete, contentDescription = "Borrar", tint = Color.Gray, modifier = Modifier.size(18.dp))
                                        }
                                    }
                                    Text(dateStr, color = Color.Gray, fontSize = 11.sp)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(content, color = Color.LightGray, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
