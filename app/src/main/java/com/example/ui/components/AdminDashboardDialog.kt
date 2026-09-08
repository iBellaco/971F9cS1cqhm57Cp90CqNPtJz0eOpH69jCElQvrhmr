package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.util.AuthManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardDialog(
    onDismiss: () -> Unit
) {
    val userRole by com.example.util.SubscriptionManager.userRole.collectAsState()
    val isAdmin = userRole == "admin" || AuthManager.isCurrentUserAdmin()
    
    if (!isAdmin) {
        LaunchedEffect(Unit) { onDismiss() }
        return
    }

    var showReportsPanel by remember { mutableStateOf(false) }
    var showSupportReportsPanel by remember { mutableStateOf(false) }

    if (showReportsPanel) {
        AdminFeedbackBottomSheet(
            onDismiss = { showReportsPanel = false }
        )
    }

    if (showSupportReportsPanel) {
        AdminSupportReportsDialog(
            onDismiss = { showSupportReportsPanel = false }
        )
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Cabecera
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Panel de Administración",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, "Cerrar", tint = MaterialTheme.colorScheme.onSurface)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { showReportsPanel = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Ver Reportes OCR", fontSize = 12.sp)
                    }
                    Button(
                        onClick = { showSupportReportsPanel = true },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                    ) {
                        Text("Soporte/Feedback", fontSize = 12.sp)
                    }
                }

                Box(modifier = Modifier.fillMaxSize().weight(1f)) {
                    UserManagementPanel()
                }
            }
        }
    }
}

@Composable
fun UserManagementPanel() {
    var users by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    
    LaunchedEffect(Unit) {
        try {
            val snapshot = FirebaseFirestore.getInstance().collection("users").get().await()
            val userList = snapshot.documents.map { doc ->
                val data = doc.data?.toMutableMap() ?: mutableMapOf()
                data["uid"] = doc.id
                data
            }
            users = userList
        } catch (e: Exception) {
            errorMessage = "Error al cargar usuarios: ${e.message}. Asegúrate de que las reglas de Firestore permiten la lectura."
        } finally {
            isLoading = false
        }
    }
    
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
            Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error, textAlign = TextAlign.Center)
        }
    } else if (users.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No se encontraron usuarios.")
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(users) { user ->
                UserAdminCard(user = user, onUserUpdated = { updatedUser ->
                    users = users.map { if (it["uid"] == updatedUser["uid"]) updatedUser else it }
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun UserAdminCard(user: Map<String, Any>, onUserUpdated: (Map<String, Any>) -> Unit) {
    val uid = user["uid"] as? String ?: ""
    val name = user["name"] as? String ?: "Sin Nombre"
    val role = user["role"] as? String ?: "free"
    val isBanned = user["banned"] as? Boolean ?: false
    
    var showDialog by remember { mutableStateOf(false) }
    
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(text = "UID: $uid", style = MaterialTheme.typography.bodySmall)
                Text(text = "Rol: $role", style = MaterialTheme.typography.bodySmall, color = if (role == "admin" || role == "premium") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
                if (isBanned) {
                    Text(text = "BANEADO", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall)
                }
            }
            Button(onClick = { showDialog = true }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text("Gestionar", fontSize = 12.sp)
            }
        }
    }
    
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Gestionar a $name") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = {
                            val newRole = if (role == "premium") "free" else "premium"
                            updateUserField(uid, "role", newRole) {
                                onUserUpdated(user.toMutableMap().apply { put("role", newRole) })
                                showDialog = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (role == "premium") "Quitar Premium" else "Hacer Premium")
                    }
                    Button(
                        onClick = {
                            val newRole = if (role == "admin") "free" else "admin"
                            updateUserField(uid, "role", newRole) {
                                onUserUpdated(user.toMutableMap().apply { put("role", newRole) })
                                showDialog = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (role == "admin") "Quitar Admin" else "Hacer Admin")
                    }
                    Button(
                        onClick = {
                            val newBanned = !isBanned
                            updateUserField(uid, "banned", newBanned) {
                                onUserUpdated(user.toMutableMap().apply { put("banned", newBanned) })
                                showDialog = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = if (isBanned) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
                    ) {
                        Text(if (isBanned) "Desbanear Usuario" else "Banear Usuario")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) { Text("Cerrar") }
            }
        )
    }
}

private fun updateUserField(uid: String, field: String, value: Any, onSuccess: () -> Unit) {
    FirebaseFirestore.getInstance().collection("users").document(uid)
        .update(field, value)
        .addOnSuccessListener { onSuccess() }
}
