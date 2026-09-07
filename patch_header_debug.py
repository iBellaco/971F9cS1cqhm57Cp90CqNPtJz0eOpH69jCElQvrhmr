import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """                                // Botón Limpiar Draft
                                IconButton("""

replacement = """                                // Botón Debug Visual
                                IconButton(
                                    onClick = {
                                        showVisionDebugger.value = !showVisionDebugger.value
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    val isDebug = showVisionDebugger.collectAsStateWithLifecycle().value
                                    Text(
                                        text = "🐛",
                                        fontSize = 14.sp,
                                        modifier = Modifier.alpha(if (isDebug) 1f else 0.5f)
                                    )
                                }

                                // Botón Limpiar Draft
                                IconButton("""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
