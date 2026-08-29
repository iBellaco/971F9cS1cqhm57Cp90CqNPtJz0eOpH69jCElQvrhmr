import re

with open("app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt", "r") as f:
    text = f.read()

replacement = """
                var fetchedVersion: String? = null
                try {
                    // Timeout de seguridad en Corrutinas
                    kotlinx.coroutines.withTimeout(15000L) {
                        // Cliente HTTP con Timeouts estrictos configurados
                        val client = OkHttpClient.Builder()
                            .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                            .readTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                            .writeTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                            .build()
                            
                        val request = Request.Builder().url(url).build()
                        client.newCall(request).execute().use { response ->
                            if (response.isSuccessful) {
                                val html = response.body?.string() ?: ""
                                val match = regex.find(html)
                                if (match != null) {
                                    fetchedVersion = match.groupValues[1]
                                }
                            }
                        }
                    }
                } catch (e: kotlinx.coroutines.TimeoutCancellationException) {
                    Log.e(TAG, "Timeout de seguridad excedido al buscar parche: tardó más de 15 segundos", e)
                } catch (e: java.net.SocketTimeoutException) {
                    Log.e(TAG, "Timeout de red al conectar con LeagueOfLegends.com", e)
                } catch (e: java.io.IOException) {
                    Log.e(TAG, "Fallo de conexión o red (IOException) al hacer scraping del parche", e)
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to scrape patch version (Error desconocido)", e)
                }
"""

text = re.sub(r'var fetchedVersion: String\? = null\s*try \{.*?\} catch \(e: Exception\) \{\s*Log\.e\(TAG, "Failed to scrape patch version", e\)\s*\}', replacement.strip(), text, flags=re.DOTALL)

with open("app/src/main/java/com/example/data/sync/MetaCrawlerSyncService.kt", "w") as f:
    f.write(text)
