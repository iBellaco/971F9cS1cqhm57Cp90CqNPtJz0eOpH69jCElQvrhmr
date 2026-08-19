import re

file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# First, undo previous patch to avoid mess
content = re.sub(
    r'        if \(!isExpanded && !isScanning\) \{\n            Spacer\(modifier = Modifier.height\(4.dp\)\)\n            Box\(\n                modifier = Modifier\n                    \.background\(Color.Black.copy\(alpha = 0.65f\), RoundedCornerShape\(6.dp\)\)\n                    \.padding\(horizontal = 6.dp, vertical = 3.dp\)\n            \) \{\n                Text\(\n                    text = tr\("↓ Desliza para cerrar"\),\n                    color = Color.White,\n                    fontSize = 9.5.sp,\n                    fontWeight = FontWeight.Bold\n                \)\n            \}\n        \}\n\n        Spacer\(modifier = Modifier.height\(4.dp\)\)\n',
    '        Spacer(modifier = Modifier.height(4.dp))\n',
    content
)

# Then inject it properly
bubble_pattern = """        var dragDownY by remember { mutableFloatStateOf(0f) }
        var isScanning by remember { mutableStateOf(false) }

        // Floating Bubble Button (Arastrable y clicable)
        Box("""

new_bubble_pattern = """        var dragDownY by remember { mutableFloatStateOf(0f) }
        var isScanning by remember { mutableStateOf(false) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Floating Bubble Button (Arastrable y clicable)
            Box("""

content = content.replace(bubble_pattern, new_bubble_pattern)

end_bubble_pattern = """        }

        Spacer(modifier = Modifier.height(4.dp))

        // Expanded Panel"""

new_end_bubble_pattern = """        }

            if (!isExpanded && !isScanning) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.65f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = tr("↓ Desliza para cerrar"),
                        color = Color.White,
                        fontSize = 9.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Expanded Panel"""

content = content.replace(end_bubble_pattern, new_end_bubble_pattern)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("FloatingAssistantService message properly patched!")
