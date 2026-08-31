import re

def fix_auth(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    content = content.replace("androidx.compose.foundation.androidx.compose.foundation.", "androidx.compose.foundation.")
    content = content.replace("androidx.compose.foundation.BorderStroke", "BorderStroke")
    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

fix_auth('app/src/main/java/com/example/ui/auth/AuthScreen.kt')
fix_auth('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt')

def fix_avatar_dialog(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Fix customHextechCardBorder -> customBorderColor
    content = content.replace("customHextechCardBorder", "customBorderColor")
    content = content.replace("androidx.compose.foundation.BorderStroke", "BorderStroke")
    
    # Also I duplicated rankBorder = border!
    # "rankBorder = border,\n                                            showBorder = false"
    # Wait, earlier I saw:
    # "avatarId = currentAvatarId,
    # rankBorder = currentRankBorder,
    # size = 64.dp,
    # rankBorder = border,"
    content = content.replace("rankBorder = currentRankBorder,\n                                            size = 64.dp,\n                                            rankBorder = border",
                              "size = 64.dp,\n                                            rankBorder = border")

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

fix_avatar_dialog('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt')

def fix_sub_manager(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    # changeRankBorder has `borderId` but I used `rankBorder` in the hashmap or something?
    # wait, "rankBorder" to borderId ... wait, the error was "Unresolved reference 'rankBorder'".
    content = content.replace('"rankBorder" to rankBorder', '"rankBorder" to borderId')
    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

fix_sub_manager('app/src/main/java/com/example/util/SubscriptionManager.kt')
