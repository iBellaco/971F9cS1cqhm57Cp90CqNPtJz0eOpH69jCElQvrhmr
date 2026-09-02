import sys

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

target = """data class UserRecord(
    val uid: String,
    val email: String,
    val role: String,
    val lastActive: Long,
    val name: String = "",
    val avatarId: String = "default_poro",
    val unlockedAvatars: List<String> = emptyList(),
    val premiumUntil: Long? = null
)"""

replacement = """data class UserRecord(
    val uid: String,
    val email: String,
    val role: String,
    val lastActive: Long,
    val name: String = "",
    val avatarId: String = "default_poro",
    val unlockedAvatars: List<String> = emptyList(),
    val premiumUntil: Long? = null,
    val registeredDevices: List<String> = emptyList()
)"""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)
