import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """                                // Botón Debug Visual
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
                                }"""

replacement = """                                // Botón Debug Visual
                                val isDebug = showVisionDebugger.collectAsStateWithLifecycle().value
                                IconButton(
                                    onClick = {
                                        showVisionDebugger.value = !isDebug
                                    },
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(
                                            color = if (isDebug) Color.Red.copy(alpha = 0.3f) else Color.Transparent,
                                            shape = CircleShape
                                        )
                                        .border(
                                            width = if (isDebug) 1.dp else 0.dp,
                                            color = if (isDebug) Color.Red else Color.Transparent,
                                            shape = CircleShape
                                        )
                                ) {
                                    Text(
                                        text = "🐛",
                                        fontSize = if (isDebug) 16.sp else 14.sp,
                                        modifier = Modifier.alpha(if (isDebug) 1f else 0.5f)
                                    )
                                }"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
