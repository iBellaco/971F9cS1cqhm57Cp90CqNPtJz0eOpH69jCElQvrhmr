import re

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'r') as f:
    text = f.read()

listener_logic_old = """                    val role = listenSnapshot.getString("role") ?: "free"
                    val banned = listenSnapshot.getBoolean("banned") ?: false
                    _userRole.value = role
                    _isBanned.value = banned
                    val isPrem = role == "premium" || role == "admin"
                    _isPremium.value = isPrem"""

listener_logic_new = """                    val role = listenSnapshot.getString("role") ?: "free"
                    val banned = listenSnapshot.getBoolean("banned") ?: false
                    _userRole.value = role
                    _isBanned.value = (role == "banned" || banned)
                    val isPrem = role == "premium" || role == "admin"
                    _isPremium.value = isPrem"""

text = text.replace(listener_logic_old, listener_logic_new)

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'w') as f:
    f.write(text)
