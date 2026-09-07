import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """            Button(
                onClick = onToggleDebug,
                modifier = Modifier.weight(0.7f).height(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = com.example.ui.theme.HextechSurfaceVariant),
                border = BorderStroke(1.dp, com.example.ui.theme.HextechCyan.copy(alpha = 0.5f)),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
            ) {
                Text("🐛", fontSize = 10.sp)
            }
            
            Button("""

replacement = """            Button("""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
