import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    content = f.read()

# Update isUserPremium
content = content.replace(
    'val isUserPremium = isPremium || userRole == "admin" || AuthManager.isCurrentUserAdmin()',
    'val isUserPremium = isPremium || userRole == "admin" || userRole == "moderador" || userRole == "streamer" || userRole == "creador_vip" || userRole == "river" || AuthManager.isCurrentUserAdmin()'
)

# Update the Card
content = content.replace(
    'userRole == "admin" -> com.example.ui.theme.HextechGold.copy(alpha = 0.12f)',
    'userRole == "admin" || userRole == "moderador" || userRole == "streamer" || userRole == "creador_vip" || userRole == "river" || userRole == "creador" -> com.example.ui.theme.HextechGold.copy(alpha = 0.12f)'
)

# We will just write a python script to do this more carefully.
