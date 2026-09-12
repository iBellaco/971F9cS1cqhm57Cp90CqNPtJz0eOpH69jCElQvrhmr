import re

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'r') as f:
    content = f.read()

# Update isPrem mapping
old_isPrem = """                    val isPrem = when {
                        isAdminClaim || role == "admin" -> true
                        role == "premium" -> {
                            until == null || until == 0L || until > System.currentTimeMillis()
                        }
                        else -> false
                    }"""

new_isPrem = """                    val isPrem = when {
                        isAdminClaim || role == "admin" || role == "moderador" -> true
                        role == "premium" || role == "creador_vip" || role == "streamer" || role == "river" -> {
                            until == null || until == 0L || until > System.currentTimeMillis()
                        }
                        else -> false
                    }"""

content = content.replace(old_isPrem, new_isPrem)

old_admin_check = """        if (_isPremium.value || _userRole.value == "admin") return true"""
new_admin_check = """        if (_isPremium.value || _userRole.value == "admin" || _userRole.value == "moderador") return true"""

content = content.replace(old_admin_check, new_admin_check)

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'w') as f:
    f.write(content)
