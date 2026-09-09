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
import androidx.compose.material.icons.filled.Message
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

@Composable
fun AdminPrivateMessageDialog(
    userUid: String,
    onDismiss: () -> Unit,
    onSuccess: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isProcessing by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF0F172A),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF59E0B))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Message, contentDescription = null, tint = Color(0xFFF59E0B))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Mensaje Privado", color = Color(0xFFF59E0B), fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título", color = Color.Gray) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFFF59E0B)
                    )
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Mensaje", color = Color.Gray) },
                    modifier = Modifier.height(120.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFFF59E0B)
                    )
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
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
                                val messageId = UUID.randomUUID().toString()
                                val messageData = hashMapOf<String, Any>(
                                    "id" to messageId,
                                    "title" to title,
                                    "content" to content,
                                    "timestamp" to System.currentTimeMillis(),
                                    "isRead" to false
                                )
                                
                                val db = FirebaseFirestore.getInstance()
                                val userDocRef = db.collection("users").document(userUid)
                                
                                userDocRef.collection("messages").document(messageId)
                                    .set(messageData)
                                    .addOnSuccessListener {
                                        userDocRef.update(
                                            "hasUnreadMessages", true,
                                            "unreadMessagesCount", com.google.firebase.firestore.FieldValue.increment(1),
                                            "privateMessages", com.google.firebase.firestore.FieldValue.arrayUnion(messageData)
                                        ).addOnCompleteListener {
                                            isProcessing = false
                                            Toast.makeText(context, "¡Mensaje privado enviado con éxito!", Toast.LENGTH_SHORT).show()
                                            onSuccess()
                                            onDismiss()
                                        }
                                    }
                                    .addOnFailureListener { e ->
                                        // Intento directo en el documento del usuario por si las reglas bloquean la subcolección
                                        userDocRef.update(
                                            "hasUnreadMessages", true,
                                            "unreadMessagesCount", com.google.firebase.firestore.FieldValue.increment(1),
                                            "privateMessages", com.google.firebase.firestore.FieldValue.arrayUnion(messageData)
                                        ).addOnSuccessListener {
                                            isProcessing = false
                                            Toast.makeText(context, "¡Mensaje privado enviado con éxito!", Toast.LENGTH_SHORT).show()
                                            onSuccess()
                                            onDismiss()
                                        }.addOnFailureListener { e2 ->
                                            isProcessing = false
                                            Toast.makeText(context, "Error al enviar mensaje: ${e.localizedMessage ?: e2.localizedMessage ?: "Error desconocido"}", Toast.LENGTH_LONG).show()
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
