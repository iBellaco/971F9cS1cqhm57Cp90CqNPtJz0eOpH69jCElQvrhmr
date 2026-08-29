with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r') as f:
    text = f.read()

# Let's fix the brackets and replace BrokenImage with Warning
# Also we need to make sure the function AppAssetImage is closed properly.
target = """        } else {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.BrokenImage,
                contentDescription = "Missing Image",
                tint = borderColor.copy(alpha = 0.5f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}"""

replacement = """        } else {
            androidx.compose.material3.Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Warning,
                contentDescription = "Missing Image",
                tint = borderColor.copy(alpha = 0.5f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
"""

if target in text:
    text = text.replace(target, replacement)
    
    with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w') as f:
        f.write(text)
    print("Fixed Avatar")
else:
    print("Could not find target")
