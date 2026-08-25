import re

with open("app/src/main/java/com/example/util/ImagePrefetcher.kt", "r", encoding="utf-8") as f:
    content = f.read()

# Replace the loop body catching
old_catch = """                } catch (e: Exception) {
                    AppLogger.w("ImagePrefetcher", "Error downloading ${item.name}: ${e.message}")
                    addLog("Reintentando: ${item.name}")
                }"""

new_catch = """                } catch (e: kotlinx.coroutines.CancellationException) {
                    throw e
                } catch (e: Exception) {
                    AppLogger.w("ImagePrefetcher", "Error downloading ${item.name}: ${e.message}")
                    addLog("Reintentando: ${item.name}")
                }"""

content = content.replace(old_catch, new_catch)

# Replace the end logic to only mark fully downloaded if it wasn't cancelled
# We wrap the whole `uniqueItems.forEachIndexed` block and beyond in a try/catch for CancellationException?
# Or better, since `withContext` is suspend, if `throw e` is called, it will exit `prefetchAllImages`.
# But wait, does it crash? We should just catch it outside the loop, or check `isActive`.

old_end = """            val endSize = diskCache?.size ?: 0L
            val totalDownloadedMb = (endSize - startSize) / (1024.0 * 1024.0)
            
            if (totalDownloadedMb > 0) {
                addLog("Total descargado: ${String.format("%.2f", totalDownloadedMb)} MB")
            }
            addLog("✅ Descarga completada con éxito. (${downloaded} nuevos, ${skipped} en caché)")
            _isDownloading.value = false
            _isFullyDownloaded.value = true
            try {
                getPrefs(context).edit().putBoolean(KEY_DOWNLOADED, true).apply()
            } catch (e: Exception) {
                // ignore
            }"""

new_end = """            val endSize = diskCache?.size ?: 0L
            val totalDownloadedMb = (endSize - startSize) / (1024.0 * 1024.0)
            
            if (kotlinx.coroutines.isActive) {
                if (totalDownloadedMb > 0) {
                    addLog("Total descargado: ${String.format("%.2f", totalDownloadedMb)} MB")
                }
                addLog("✅ Descarga completada con éxito. (${downloaded} nuevos, ${skipped} en caché)")
                _isDownloading.value = false
                _isFullyDownloaded.value = true
                try {
                    getPrefs(context).edit().putBoolean(KEY_DOWNLOADED, true).apply()
                } catch (e: Exception) {
                    // ignore
                }
            } else {
                _isDownloading.value = false
                addLog("🚫 Descarga pausada por el usuario.")
            }"""

content = content.replace(old_end, new_end)

with open("app/src/main/java/com/example/util/ImagePrefetcher.kt", "w", encoding="utf-8") as f:
    f.write(content)
