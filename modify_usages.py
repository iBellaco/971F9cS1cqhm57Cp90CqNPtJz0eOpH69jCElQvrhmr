import os
import re

def add_border_to_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    if "val currentRankBorder by SubscriptionManager.currentRankBorder.collectAsState()" not in content:
        content = content.replace("val currentAvatarId by SubscriptionManager.currentAvatarId.collectAsState()",
                                  "val currentAvatarId by SubscriptionManager.currentAvatarId.collectAsState()\n    val currentRankBorder by SubscriptionManager.currentRankBorder.collectAsState()")
    
    # We want to replace `UserAvatarView(\n avatarId = currentAvatarId,` with adding `rankBorder = currentRankBorder`
    content = content.replace('avatarId = currentAvatarId,', 'avatarId = currentAvatarId,\n                                    rankBorder = currentRankBorder,')
    
    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

add_border_to_file('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt')
add_border_to_file('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt')
add_border_to_file('app/src/main/java/com/example/ui/auth/AuthScreen.kt')
