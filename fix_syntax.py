def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Fix the double parameter in AvatarSelectionDialog
    content = content.replace(
        "avatarId = currentAvatarId,                                    rankBorder = currentRankBorder,\n                                            size = 64.dp,\n                                            rankBorder = border,",
        "avatarId = currentAvatarId,\n                                            size = 64.dp,\n                                            rankBorder = border,"
    )
    
    # Fix AuthScreen and MainDraftingScreen
    content = content.replace("avatarId = currentAvatarId,                                    rankBorder = currentRankBorder,", "avatarId = currentAvatarId,\nrankBorder = currentRankBorder,")
    
    # Fix undefined in AvatarSelectionDialog
    content = content.replace("SurfaceDark", "HextechDarkBg")
    content = content.replace("BorderStroke(", "androidx.compose.foundation.BorderStroke(")
    content = content.replace("BorderColor", "HextechCardBorder")
    
    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

fix_file('app/src/main/java/com/example/ui/auth/AuthScreen.kt')
fix_file('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt')
fix_file('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt')
