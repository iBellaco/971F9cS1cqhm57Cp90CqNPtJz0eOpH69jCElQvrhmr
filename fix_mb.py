import re

with open('app/src/main/java/com/example/util/ImagePrefetcher.kt', 'r') as f:
    content = f.read()

# Add downloadedMb StateFlow
if 'val downloadedMb:' not in content:
    content = content.replace(
        'val downloadProgress: StateFlow<Float> = _downloadProgress',
        'val downloadProgress: StateFlow<Float> = _downloadProgress\n    \n    private val _downloadedMb = MutableStateFlow(0f)\n    val downloadedMb: StateFlow<Float> = _downloadedMb'
    )

# Reset on start
if '_downloadedMb.value = 0f' not in content:
    content = content.replace(
        '_downloadProgress.value = 0f',
        '_downloadProgress.value = 0f\n        _downloadedMb.value = 0f'
    )

# Update downloadedMb during loop
old_loop_mb = '''                        if (diff > 0) {
                            val diffMb = diff / (1024.0 * 1024.0)
                            if (index % 4 == 0) {
                                addLog("Descargado: ${String.format("%.1f", diffMb)} MB")
                            }
                        }'''
new_loop_mb = '''                        if (diff > 0) {
                            val diffMb = diff / (1024.0 * 1024.0)
                            _downloadedMb.value = diffMb.toFloat()
                            if (index % 4 == 0) {
                                addLog("Descargado: ${String.format("%.1f", diffMb)} MB")
                            }
                        }'''
content = content.replace(old_loop_mb, new_loop_mb)

with open('app/src/main/java/com/example/util/ImagePrefetcher.kt', 'w') as f:
    f.write(content)
