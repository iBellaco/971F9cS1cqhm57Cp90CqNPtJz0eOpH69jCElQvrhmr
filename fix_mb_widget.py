import re

with open('app/src/main/java/com/example/ui/components/DownloadProgressWidget.kt', 'r') as f:
    content = f.read()

if 'val downloadedMb by ImagePrefetcher.downloadedMb.collectAsState()' not in content:
    content = content.replace(
        'val isFullyDownloaded by ImagePrefetcher.isFullyDownloaded.collectAsState()',
        'val isFullyDownloaded by ImagePrefetcher.isFullyDownloaded.collectAsState()\n    val downloadedMb by ImagePrefetcher.downloadedMb.collectAsState()'
    )

old_text = '''                        Text(
                            text = "${(progress * 100).toInt()}%",
                            color = TextCyan,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )'''

new_text = '''                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${String.format("%.1f", downloadedMb)} MB",
                                color = TextSecondary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "${(progress * 100).toInt()}%",
                                color = TextCyan,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }'''

content = content.replace(old_text, new_text)

with open('app/src/main/java/com/example/ui/components/DownloadProgressWidget.kt', 'w') as f:
    f.write(content)
