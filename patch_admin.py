import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

# 1. Update UserRecord
text = text.replace(
    'data class UserRecord(\n    val uid: String,\n    val email: String,\n    val role: String,\n    val lastActive: Long\n)',
    'data class UserRecord(\n    val uid: String,\n    val email: String,\n    val role: String,\n    val lastActive: Long,\n    val name: String = ""\n)'
)

# 2. Update the query mapped in loadUsers()
query_old = """                                val lastActive = doc.getLong("last_active") ?: 0L
                                UserRecord(uid, email, role, lastActive)"""
query_new = """                                val lastActive = doc.getLong("last_active") ?: 0L
                                val name = doc.getString("name") ?: ""
                                UserRecord(uid, email, role, lastActive, name)"""
text = text.replace(query_old, query_new)

# 3. Add onNameChange callback in UserManagementCard
card_sig_old = """@Composable
fun UserManagementCard(
    user: UserRecord,
    context: android.content.Context,
    clipboard: androidx.compose.ui.platform.ClipboardManager,
    onRoleChange: (String) -> Unit
) {"""
card_sig_new = """@Composable
fun UserManagementCard(
    user: UserRecord,
    context: android.content.Context,
    clipboard: androidx.compose.ui.platform.ClipboardManager,
    onRoleChange: (String) -> Unit,
    onNameChange: (String) -> Unit
) {"""
text = text.replace(card_sig_old, card_sig_new)

# 4. Supply onNameChange when calling UserManagementCard
call_old = """                                onRoleChange = { newRole ->
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
                                }"""
call_new = """                                onRoleChange = { newRole ->
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
                                }"""
text = text.replace(call_old, call_new)

# 5. Display name in UI and add Edit Name Dialog logic
display_old = """            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.email,"""

display_new = """            var showNameEdit by remember { mutableStateOf(false) }
            if (showNameEdit) {
                var newName by remember { mutableStateOf(user.name) }
                AlertDialog(
                    onDismissRequest = { showNameEdit = false },
                    title = { Text("Cambiar Nombre", color = HextechCyan) },
                    text = {
                        OutlinedTextField(
                            value = newName,
                            onValueChange = { newName = it },
                            label = { Text("Nombre") }
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
                    text = user.email,"""

text = text.replace(display_old, display_new)

# 6. Add Cambiar Nombre option to Dropdown Menu
dropdown_old = """                    DropdownMenuItem(
                        text = { Text("Restablecer Contraseña", color = HextechCyan) },"""
dropdown_new = """                    DropdownMenuItem(
                        text = { Text("Cambiar Nombre", color = HextechGold) },
                        onClick = { 
                            showNameEdit = true
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Restablecer Contraseña", color = HextechCyan) },"""
text = text.replace(dropdown_old, dropdown_new)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
