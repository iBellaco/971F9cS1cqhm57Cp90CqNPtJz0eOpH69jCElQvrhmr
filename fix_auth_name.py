import re
with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

target = """    val isPremium by SubscriptionManager.isPremium.collectAsState()
    val userRole by SubscriptionManager.userRole.collectAsState()"""

replacement = """    val isPremium by SubscriptionManager.isPremium.collectAsState()
    val userRole by SubscriptionManager.userRole.collectAsState()
    val savedUserName by SubscriptionManager.userName.collectAsState()"""

text = text.replace(target, replacement)

target2 = """            val displayName = user.displayName?.takeIf { it.isNotBlank() } ?: user.email?.substringBefore("@") ?: "Usuario"
            Text(
                text = displayName,"""

replacement2 = """            val displayName = savedUserName.takeIf { it.isNotBlank() } ?: user.displayName?.takeIf { it.isNotBlank() } ?: user.email?.substringBefore("@") ?: "Usuario"
            Text(
                text = displayName,"""

text = text.replace(target2, replacement2)

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
print("Updated AuthScreen displayName")
