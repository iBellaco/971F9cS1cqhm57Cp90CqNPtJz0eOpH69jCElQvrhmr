import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

# Add states
states = """
    var showPushDialog by remember { mutableStateOf(false) }
    var showScraperDialog by remember { mutableStateOf(false) }
    var scraperProgress by remember { mutableStateOf("") }
"""
text = re.sub(r'var showPushDialog by remember \{ mutableStateOf\(false\) \}', states, text, count=1)

# Add button
scraper_btn = """
                // Scraper Button
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { showScraperDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Download, contentDescription = null, tint = HextechCyan)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ejecutar BestBuildWR Scraper", color = TextPrimary)
                }

                Spacer(modifier = Modifier.height(24.dp))
"""
text = re.sub(r'Spacer\(modifier = Modifier.height\(24.dp\)\)', scraper_btn, text, count=1)

# Add Dialog logic
scraper_dialog = """
    if (showScraperDialog) {
        var isScraping by remember { mutableStateOf(false) }

        AlertDialog(
            onDismissRequest = { if (!isScraping) showScraperDialog = false },
            title = { Text("Web Scraper (BestBuildWR)", color = HextechCyan) },
            text = {
                Column {
                    Text(
                        "Al ejecutar este proceso, la aplicación extraerá en tiempo real las builds y campeones actualizados y guardará los archivos (JSON, CSV, URLS) en la carpeta de Descargas de tu dispositivo.",
                        color = TextPrimary,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (isScraping || scraperProgress.isNotEmpty()) {
                        Text(
                            text = scraperProgress,
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isScraping = true
                        scope.launch {
                            val success = com.example.util.BestBuildScraper.runScraper(context) { progress ->
                                scraperProgress = progress
                            }
                            if (success) {
                                isScraping = false
                                // we keep the dialog open to show success or let the user close it
                            } else {
                                isScraping = false
                            }
                        }
                    },
                    enabled = !isScraping
                ) {
                    Text(if (isScraping) "Extrayendo..." else "Ejecutar", color = HextechCyan)
                }
            },
            dismissButton = {
                TextButton(onClick = { showScraperDialog = false }, enabled = !isScraping) {
                    Text("Cerrar", color = TextMuted)
                }
            },
            containerColor = HextechSurface,
            titleContentColor = HextechCyan
        )
    }

    if (showPushDialog) {
"""
text = re.sub(r'if \(showPushDialog\) \{', scraper_dialog, text, count=1)

# Ensure Icons.Default.Download is imported. Well, we can use Icons.Default.Download directly if androidx.compose.material.icons.filled.Download is imported, or we can use Icons.Default.Refresh or whatever.
import_dl = "import androidx.compose.material.icons.filled.Download\nimport androidx.compose.material.icons.filled.Refresh"
text = text.replace('import androidx.compose.material.icons.filled.Close', 'import androidx.compose.material.icons.filled.Close\n' + import_dl)


with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
