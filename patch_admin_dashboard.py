import sys

file_path = "app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt"
with open(file_path, "r") as f:
    content = f.read()

# Replace listener
old_listener = """    DisposableEffect(Unit) {
        isLoading = true
        val listener = FirebaseFirestore.getInstance().collection("users")
            .addSnapshotListener { snapshot, error ->"""
new_listener = """    DisposableEffect(Unit) {
        isLoading = false
        val listener = object { fun remove() {} }
        /* Backend requerido para leer usuarios
        val listener = FirebaseFirestore.getInstance().collection("users")
            .addSnapshotListener { snapshot, error ->"""
content = content.replace(old_listener, new_listener)

old_listener_end = """                    users = groupedUsers.sortedWith(
                        compareByDescending<UserRecord> { it.role == "admin" }
                            .thenByDescending { it.role == "premium" }
                            .thenByDescending { it.isRealTimeOnline(System.currentTimeMillis()) }
                            .thenBy { it.name.ifEmpty { it.email } }
                    )
                    isLoading = false
                }
            }
        
        onDispose {"""
new_listener_end = """                    users = groupedUsers.sortedWith(
                        compareByDescending<UserRecord> { it.role == "admin" }
                            .thenByDescending { it.role == "premium" }
                            .thenByDescending { it.isRealTimeOnline(System.currentTimeMillis()) }
                            .thenBy { it.name.ifEmpty { it.email } }
                    )
                    isLoading = false
                }
            }
        */
        onDispose {"""
content = content.replace(old_listener_end, new_listener_end)

old_userRole = """    val userRole by SubscriptionManager.userRole.collectAsState()
    if (userRole != "admin") {"""
new_userRole = """    val isAdmin = com.example.util.AuthManager.isCurrentUserAdmin()
    if (!isAdmin) {"""
content = content.replace(old_userRole, new_userRole)

# Add require backend message
old_ui = """                        item {
                            SearchAndFilterBar(
                                searchQuery = searchQuery,
                                onSearchQueryChange = { searchQuery = it },
                                selectedRole = selectedRoleFilter,
                                onRoleSelect = { selectedRoleFilter = it }
                            )
                        }

                        if (filteredUsers.isEmpty()) {"""
new_ui = """                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Requiere Backend", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onErrorContainer)
                                    Text("El listado de usuarios requiere Cloud Functions y el Admin SDK de Firebase. La lectura global de usuarios ha sido deshabilitada por seguridad.", color = MaterialTheme.colorScheme.onErrorContainer, fontSize = 14.sp)
                                }
                            }
                        }
                        item {
                            SearchAndFilterBar(
                                searchQuery = searchQuery,
                                onSearchQueryChange = { searchQuery = it },
                                selectedRole = selectedRoleFilter,
                                onRoleSelect = { selectedRoleFilter = it }
                            )
                        }

                        if (filteredUsers.isEmpty()) {"""
content = content.replace(old_ui, new_ui)

with open(file_path, "w") as f:
    f.write(content)
