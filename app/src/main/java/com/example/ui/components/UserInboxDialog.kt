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
    var messages by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userUid).collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error == null && snapshot != null) {
                    val msgs = snapshot.documents.mapNotNull { it.data?.plus("id" to it.id) }
                    messages = msgs
                }
                isLoading = false
            }
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
                                    FirebaseFirestore.getInstance().collection("users").document(userUid)
                                        .collection("messages").document(id)
                                        .update("isRead", true)
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
                                            FirebaseFirestore.getInstance().collection("users").document(userUid)
                                                .collection("messages").document(id)
                                                .delete()
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
