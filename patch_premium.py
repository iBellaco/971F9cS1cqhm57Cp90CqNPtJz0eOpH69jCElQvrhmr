import sys
with open("app/src/main/java/com/example/util/SubscriptionManager.kt", "r") as f:
    content = f.read()

old_logic = """                    var role = listenSnapshot.getString("role") ?: "free"
                    val isAdminClaim = AuthManager.isCurrentUserAdmin()
                    if (isAdminClaim) {
                        role = "admin"
                    }"""

new_logic = """                    var role = listenSnapshot.getString("role") ?: "free"
                    val isAdminClaim = AuthManager.isCurrentUserAdmin()
                    if (isAdminClaim || role == "admin") {
                        role = "admin"
                    }"""

old_prem = """                    val isPrem = when {
                        isAdminClaim -> true
                        role == "premium" -> {
                            until == null || until == 0L || until > System.currentTimeMillis()
                        }
                        else -> false
                    }"""

new_prem = """                    val isPrem = when {
                        isAdminClaim || role == "admin" -> true
                        role == "premium" -> {
                            until == null || until == 0L || until > System.currentTimeMillis()
                        }
                        else -> false
                    }"""

content = content.replace(old_logic, new_logic)
content = content.replace(old_prem, new_prem)

with open("app/src/main/java/com/example/util/SubscriptionManager.kt", "w") as f:
    f.write(content)

