import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

# 1. Replace loadUsers() and LaunchedEffect with DisposableEffect
load_users_pattern = re.compile(r'    fun loadUsers\(\) \{.*?\n    \}[\s]*LaunchedEffect\(Unit\) \{[\s]*loadUsers\(\)[\s]*\}', re.DOTALL)

replacement = """    DisposableEffect(Unit) {
        isLoading = true
        val listener = FirebaseFirestore.getInstance().collection("users")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("AdminDashboard", "Error loading users", error)
                    isLoading = false
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val list = snapshot.documents.mapNotNull { doc ->
                        val email = doc.getString("email") ?: "Sin email"
                        val role = doc.getString("role") ?: "free"
                        val lastActive = doc.getLong("last_active") ?: 0L
                        val name = doc.getString("name") ?: ""
                        val avatarId = doc.getString("avatarId") ?: "default_poro"
                        val premiumUntil = doc.getLong("premiumUntil")
                        @Suppress("UNCHECKED_CAST")
                        val unlocked = doc.get("unlockedAvatars") as? List<String> ?: listOf("default_poro")
                        UserRecord(doc.id, email, role, lastActive, name, avatarId, unlocked, premiumUntil)
                    }.sortedWith(compareByDescending<UserRecord> { it.role == "admin" }
                        .thenByDescending { it.role == "premium" }
                        .thenBy { it.name.ifEmpty { it.email } })
                    users = list
                    isLoading = false
                }
            }
        
        onDispose {
            listener.remove()
        }
    }"""

content = load_users_pattern.sub(replacement, content)

# 2. Fix the refresh button onClick
content = content.replace("onClick = { loadUsers() },", "onClick = { /* Auto-updating */ },")

# 3. Fix the onRoleChange loadUsers() call
content = content.replace(".await()\n                                                    loadUsers()", ".await()")

# 4. Fix the onRefresh call
content = content.replace("onRefresh = { loadUsers() }", "onRefresh = { }")

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)

print("Patch applied.")
