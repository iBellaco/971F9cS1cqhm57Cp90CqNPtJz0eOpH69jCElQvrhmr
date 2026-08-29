package com.example.ui.components


import androidx.compose.ui.text.AnnotatedString

import com.google.firebase.auth.FirebaseAuth

import android.widget.Toast

import android.util.Log

import androidx.compose.foundation.background

import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.*

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp

import androidx.compose.ui.window.Dialog

import androidx.compose.ui.window.DialogProperties

import com.example.ui.theme.*

import com.example.util.SubscriptionManager

import com.example.util.tr

import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.tasks.await

import kotlinx.coroutines.launch

data class UserRecord(
    val uid: String,
    val email: String,
    val role: String,
    val lastActive: Long,
    val name: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AdminDashboardDialog(
    onDismiss: () -> Unit
) {
    val userRole by SubscriptionManager.userRole.collectAsState()
    if (userRole != "admin") {
        LaunchedEffect(Unit) { onDismiss() }
        return
    }

    val scope = rememberCoroutineScope()
    val context = androidx.compose.ui.platform.LocalContext.current
    var users by remember { mutableStateOf<List<UserRecord>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    
    var showReportsPanel by remember { mutableStateOf(false) }
    var showPushDialog by remember { mutableStateOf(false) }


    fun loadUsers() {
        isLoading = true
        scope.launch {
            try {
                val snapshot = FirebaseFirestore.getInstance().collection("users").get().await()
                val list = snapshot.documents.mapNotNull { doc ->
                    val email = doc.getString("email") ?: "Sin email"
                    val role = doc.getString("role") ?: "free"
                    val lastActive = doc.getLong("last_active") ?: 0L
                    UserRecord(doc.id, email, role, lastActive)
                }.sortedBy { it.email }
                users = list
            } catch (e: Exception) {
                Log.e("AdminDashboard", "Error loading users", e)
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadUsers()
    }

    
    if (showPushDialog) {
        var pushTitle by remember { mutableStateOf("") }
        var pushBody by remember { mutableStateOf("") }
        var pushTarget by remember { mutableStateOf("all") }
        var isSendingPush by remember { mutableStateOf(false) }
        
        AlertDialog(
            onDismissRequest = { if (!isSendingPush) showPushDialog = false },
            title = { Text("Nueva Notificación Push", color = HextechGold) },
            text = {
                Column {
                    OutlinedTextField(
                        value = pushTitle,
                        onValueChange = { pushTitle = it },
                        label = { Text("Título") },
                        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedBorderColor = HextechGold
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = pushBody,
                        onValueChange = { pushBody = it },
                        label = { Text("Mensaje (Ej. Meta actualizado)") },
                        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedBorderColor = HextechGold
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Destinatarios:", color = TextMuted)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = pushTarget == "all", 
                            onClick = { pushTarget = "all" },
                            colors = androidx.compose.material3.RadioButtonDefaults.colors(selectedColor = HextechCyan)
                        )
                        Text("Todos los usuarios", color = TextPrimary)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = pushTarget == "premium", 
                            onClick = { pushTarget = "premium" },
                            colors = androidx.compose.material3.RadioButtonDefaults.colors(selectedColor = HextechGold)
                        )
                        Text("Solo Premium", color = HextechGold)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (pushTitle.isBlank() || pushBody.isBlank()) return@TextButton
                        isSendingPush = true
                        scope.launch {
                            try {
                                val data = hashMapOf(
                                    "title" to pushTitle,
                                    "body" to pushBody,
                                    "target" to pushTarget,
                                    "createdAt" to System.currentTimeMillis()
                                )
                                FirebaseFirestore.getInstance().collection("global_notifications").add(data).await()
                                Toast.makeText(context, "Notificación enviada", Toast.LENGTH_SHORT).show()
                                showPushDialog = false
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                            } finally {
                                isSendingPush = false
                            }
                        }
                    },
                    enabled = !isSendingPush
                ) {
                    Text(if (isSendingPush) "Enviando..." else "Enviar", color = HextechCyan)
                }
            },
            dismissButton = {
                TextButton(onClick = { showPushDialog = false }, enabled = !isSendingPush) {
                    Text("Cancelar", color = TextMuted)
                }
            },
            containerColor = HextechSurface,
            titleContentColor = HextechGold
        )
    }

    if (showReportsPanel) {

        AdminFeedbackBottomSheet(
            onDismiss = { showReportsPanel = false }
        )
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
            shape = RoundedCornerShape(24.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AdminPanelSettings,
                            contentDescription = null,
                            tint = DangerRed,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Panel de Control Admin",
                            color = HextechGold,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))

                // Reports Button
                Button(
                    onClick = { showReportsPanel = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.BugReport, contentDescription = null, tint = HextechGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Abrir Buzón de Reportes y Sugerencias", color = TextPrimary)
                }

                
                // Push Notifications Button
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { showPushDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = HextechGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Enviar Notificación Push", color = TextPrimary)
                }

                Spacer(modifier = Modifier.height(24.dp))

                
                // --- ADMIN STATS DASHBOARD ---
                val totalUsers = users.size
                val premiumUsers = users.count { it.role == "premium" }
                // Active in the last 15 minutes (15 * 60 * 1000 ms)
                val onlineUsers = users.count { System.currentTimeMillis() - it.lastActive < 900_000 }
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AdminStatCard(
                        modifier = Modifier.weight(1f),
                        title = "Total",
                        value = totalUsers.toString(),
                        icon = Icons.Default.Group,
                        color = HextechCyan
                    )
                    AdminStatCard(
                        modifier = Modifier.weight(1f),
                        title = "En Línea",
                        value = onlineUsers.toString(),
                        icon = Icons.Default.Person,
                        color = Color(0xFF00FF7F) // Zaun Green style
                    )
                    AdminStatCard(
                        modifier = Modifier.weight(1f),
                        title = "Premium",
                        value = premiumUsers.toString(),
                        icon = Icons.Default.Star,
                        color = HextechGold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "Gestión de Usuarios (${users.size})",
                    color = HextechCyan,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                HorizontalDivider(color = TextMuted.copy(alpha = 0.2f))

                if (isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = HextechGold)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(users) { user ->
                            val context = androidx.compose.ui.platform.LocalContext.current
                            val clipboard = androidx.compose.ui.platform.LocalClipboardManager.current
                            UserManagementCard(
                                context = context,
                                clipboard = clipboard,
                                user = user,
                                onRoleChange = { newRole ->
                                    scope.launch {
                                        try {
                                            FirebaseFirestore.getInstance().collection("users")
                                                .document(user.uid)
                                                .update("role", newRole)
                                                .await()
                                            loadUsers() // Reload to reflect changes
                                        } catch (e: Exception) {
                                            Log.e("AdminDashboard", "Error updating role", e)
                                        }
                                    }
                                },
                                onNameChange = { newName ->
                                    scope.launch {
                                        try {
                                            FirebaseFirestore.getInstance().collection("users")
                                                .document(user.uid)
                                                .update("name", newName)
                                                .await()
                                            loadUsers()
                                        } catch (e: Exception) {
                                            Log.e("AdminDashboard", "Error updating name", e)
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun UserManagementCard(
    user: UserRecord,
    context: android.content.Context,
    clipboard: androidx.compose.ui.platform.ClipboardManager,
    onRoleChange: (String) -> Unit,
    onNameChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, TextMuted.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var showNameEdit by remember { mutableStateOf(false) }
            if (showNameEdit) {
                var newName by remember { mutableStateOf(user.name) }
                AlertDialog(
                    onDismissRequest = { showNameEdit = false },
                    title = { Text("Cambiar Nombre de Usuario", color = HextechCyan) },
                    text = {
                        OutlinedTextField(
                            value = newName,
                            onValueChange = { newName = it },
                            label = { Text("Nombre de Usuario") }
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = { 
                            onNameChange(newName)
                            showNameEdit = false
                        }) {
                            Text("Guardar", color = HextechGold)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showNameEdit = false }) {
                            Text("Cancelar", color = TextMuted)
                        }
                    },
                    containerColor = HextechSurface
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                if (user.name.isNotEmpty()) {
                    Text(text = user.name, color = HextechGold, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Text(
                    text = user.email,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val roleColor = when(user.role) {
                        "admin" -> DangerRed
                        "premium" -> HextechGold
                        "banned" -> Color.Gray
                        else -> TextMuted
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(roleColor.copy(alpha = 0.2f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = user.role.uppercase(),
                            color = roleColor,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ID: ${user.uid.take(6)}...",
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                }
            }
            
            // Dropdown Menu for Roles
            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.Edit, contentDescription = "Cambiar Rol", tint = HextechCyan)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(HextechSurface)
                ) {
                    DropdownMenuItem(
                        text = { Text("Copiar ID de Usuario", color = TextSecondary) },
                        onClick = { 
                            clipboard.setText(AnnotatedString(user.uid))
                            Toast.makeText(context, "ID copiado", Toast.LENGTH_SHORT).show()
                            expanded = false 
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Copiar Correo", color = TextSecondary) },
                        onClick = { 
                            clipboard.setText(AnnotatedString(user.email))
                            Toast.makeText(context, "Correo copiado", Toast.LENGTH_SHORT).show()
                            expanded = false 
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Cambiar Nombre de Usuario", color = HextechGold) },
                        onClick = { 
                            showNameEdit = true
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Restablecer Contraseña", color = HextechCyan) },
                        onClick = { 
                            FirebaseAuth.getInstance().sendPasswordResetEmail(user.email)
                            Toast.makeText(context, "Correo enviado", Toast.LENGTH_SHORT).show()
                            expanded = false 
                        }
                    )
                    HorizontalDivider(color = TextMuted.copy(alpha = 0.2f))
                    DropdownMenuItem(
                        text = { Text("Asignar GRATIS", color = TextPrimary) },
                        onClick = { 
                            onRoleChange("free")
                            expanded = false 
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Asignar PREMIUM", color = HextechGold) },
                        onClick = { 
                            onRoleChange("premium")
                            expanded = false 
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Suspender Usuario (BAN)", color = DangerRed) },
                        onClick = { 
                            onRoleChange("banned")
                            expanded = false 
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun AdminStatCard(modifier: Modifier, title: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = title, color = TextMuted, fontSize = 10.sp, maxLines = 1)
        }
    }
}
