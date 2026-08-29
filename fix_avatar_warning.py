with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r') as f:
    text = f.read()

target = """            androidx.compose.material3.Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Warning,
                contentDescription = "Missing Image",
                tint = borderColor.copy(alpha = 0.5f),
                modifier = Modifier.size(24.dp)
            )"""

replacement = """            androidx.compose.material3.Icon(
                painter = androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_dialog_alert),
                contentDescription = "Missing Image",
                tint = borderColor.copy(alpha = 0.5f),
                modifier = Modifier.size(24.dp)
            )"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w') as f:
    f.write(text)
print("Replaced Warning Icon")
