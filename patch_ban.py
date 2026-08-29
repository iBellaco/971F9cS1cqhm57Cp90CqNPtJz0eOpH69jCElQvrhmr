import re

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'r') as f:
    text = f.read()

# Add isBanned flow
if 'val isBanned: StateFlow<Boolean>' not in text:
    ban_flow = """    private val _isBanned = MutableStateFlow(false)
    val isBanned: StateFlow<Boolean> = _isBanned.asStateFlow()

"""
    text = text.replace('    private val _isPremium = MutableStateFlow(false)', ban_flow + '    private val _isPremium = MutableStateFlow(false)')

# Handle logout/null user
null_user_logic = """            _userRole.value = "free"
            _isPremium.value = false
            _isBanned.value = false"""
text = re.sub(r'_userRole\.value = "free"\s*_isPremium\.value = false(?![\s\S]*unsubscribeFromTopic)', null_user_logic, text)

# Handle listener
listener_logic = """                    val role = listenSnapshot.getString("role") ?: "free"
                    val banned = listenSnapshot.getBoolean("banned") ?: false
                    _userRole.value = role
                    _isBanned.value = banned
                    val isPrem = role == "premium" || role == "admin"
                    _isPremium.value = isPrem"""
text = re.sub(r'val role = listenSnapshot\.getString\("role"\) \?: "free"\s*_userRole\.value = role\s*val isPrem = role == "premium" \|\| role == "admin"\s*_isPremium\.value = isPrem', listener_logic, text)

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'w') as f:
    f.write(text)
