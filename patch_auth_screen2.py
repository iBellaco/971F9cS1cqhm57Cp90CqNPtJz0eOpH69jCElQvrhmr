import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    content = f.read()

# isUserPremium
content = content.replace(
    'val isUserPremium = isPremium || userRole == "admin" || AuthManager.isCurrentUserAdmin()',
    'val isUserPremium = isPremium || userRole == "admin" || userRole == "moderador" || userRole == "creador_vip" || userRole == "streamer" || userRole == "river" || AuthManager.isCurrentUserAdmin()'
)

# Admin button logic
old_admin_btn = """                        if (userRole == "admin") {
                val adminInteractionSource = remember { MutableInteractionSource() }"""

new_admin_btn = """                        if (userRole == "admin" || userRole == "moderador") {
                val adminInteractionSource = remember { MutableInteractionSource() }"""

content = content.replace(old_admin_btn, new_admin_btn)

# Add Compose animation imports if missing
if "import androidx.compose.animation.core.*" not in content:
    content = content.replace("import androidx.compose.animation.animateColorAsState", "import androidx.compose.animation.animateColorAsState\nimport androidx.compose.animation.core.*")

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(content)
