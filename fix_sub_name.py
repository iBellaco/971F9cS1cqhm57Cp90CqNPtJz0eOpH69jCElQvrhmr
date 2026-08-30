import re
with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'r') as f:
    text = f.read()

text = re.sub(r'val userRole: StateFlow<String> = _userRole\.asStateFlow\(\)\n', 
              'val userRole: StateFlow<String> = _userRole.asStateFlow()\n    private val _userName = MutableStateFlow("")\n    val userName: StateFlow<String> = _userName.asStateFlow()\n', text)

target = """                if (listenSnapshot != null && listenSnapshot.exists()) {
                    
                    
                    
                    val role = listenSnapshot.getString("role") ?: "free" """

replacement = """                if (listenSnapshot != null && listenSnapshot.exists()) {
                    val name = listenSnapshot.getString("name") ?: (user.displayName ?: "")
                    _userName.value = name
                    
                    
                    val role = listenSnapshot.getString("role") ?: "free" """

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'w') as f:
    f.write(text)
print("Added userName to SubscriptionManager")
