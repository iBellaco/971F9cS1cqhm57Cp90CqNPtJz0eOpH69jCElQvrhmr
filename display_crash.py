import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    text = f.read()

target = """                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {"""
                
replacement = """                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    var crashLog by remember { mutableStateOf<String?>(null) }
                    LaunchedEffect(Unit) {
                        try {
                            val file = java.io.File(context.filesDir, "crash_log.txt")
                            if (file.exists()) {
                                crashLog = file.readText()
                            }
                        } catch (e: Exception) {}
                    }
                    if (crashLog != null) {
                        androidx.compose.material3.AlertDialog(
                            onDismissRequest = { crashLog = null },
                            title = { Text("Crash Detectado") },
                            text = { 
                                androidx.compose.foundation.lazy.LazyColumn {
                                    item { Text(crashLog ?: "", fontSize = 10.sp) }
                                }
                            },
                            confirmButton = {
                                androidx.compose.material3.TextButton(onClick = { 
                                    try { java.io.File(context.filesDir, "crash_log.txt").delete() } catch(e:Exception){}
                                    crashLog = null 
                                }) {
                                    Text("Limpiar")
                                }
                            }
                        )
                    }"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(text)

