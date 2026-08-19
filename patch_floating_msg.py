import re

file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

new_content = """        }

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

        Spacer(modifier = Modifier.height(4.dp))

        // Expanded Panel"""

content = content.replace("        }\n\n        Spacer(modifier = Modifier.height(4.dp))\n\n        // Expanded Panel", new_content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("FloatingAssistantService message patched!")
