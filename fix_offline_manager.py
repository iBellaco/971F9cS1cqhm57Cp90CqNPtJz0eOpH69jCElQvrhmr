import re

file_path = 'app/src/main/java/com/example/data/sync/OfflineResourceManager.kt'

with open(file_path, 'r') as f:
    content = f.read()

# Make sure we import async, awaitAll
if 'kotlinx.coroutines.async' not in content:
    content = content.replace('import kotlinx.coroutines.launch', 'import kotlinx.coroutines.launch\nimport kotlinx.coroutines.async\nimport kotlinx.coroutines.awaitAll')

new_loop = """
                val concurrency = 15 // Descargar de a 15 a la vez para mayor velocidad
                while (currentIdx < totalSize) {
                    if (isPaused) {
                        _downloadState.value = DownloadState.PAUSED
                        prefs.edit().putInt(KEY_INDEX, currentIdx).apply()
                        return@launch
                    }
                    
                    val endIndex = minOf(currentIdx + concurrency, totalSize)
                    val batch = urlsToDownload.subList(currentIdx, endIndex)
                    
                    batch.map { url ->
                        async {
                            try {
                                val request = ImageRequest.Builder(context)
                                    .data(url)
                                    .memoryCachePolicy(CachePolicy.DISABLED)
                                    .diskCachePolicy(CachePolicy.ENABLED)
                                    .build()
                                imageLoader.execute(request)
                            } catch (e: Exception) {
                                if (e is CancellationException) throw e
                            }
                        }
                    }.awaitAll()
                    
                    currentIdx = endIndex
                    _downloadedCount.value = currentIdx
                    _progress.value = currentIdx.toFloat() / totalSize.toFloat()
                    _downloadedMB.value = (currentIdx * ESTIMATED_BYTES_PER_RESOURCE) / (1024f * 1024f)
                    
                    prefs.edit().putInt(KEY_INDEX, currentIdx).apply()
                }"""

# Replace old while loop logic
old_loop_pattern = re.compile(r'while\s*\(currentIdx\s*<\s*totalSize\).*?delay\(20\)\s*\}', re.DOTALL)
content = old_loop_pattern.sub(new_loop, content)

with open(file_path, 'w') as f:
    f.write(content)

