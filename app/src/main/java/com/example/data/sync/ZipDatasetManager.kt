package com.example.data.sync

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.service.screen.LocalVisionAnalyzer
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

data class ZipDatasetStatus(
    val isImporting: Boolean = false,
    val progressMessage: String = "Listo para cargar ZIP de campeones",
    val championsCount: Int = 0,
    val totalImagesCount: Int = 0,
    val lastZipName: String? = null,
    val isReady: Boolean = false,
    val logs: List<String> = emptyList()
)

object ZipDatasetManager {
    private const val TAG = "ZipDatasetManager"

    private val _status = MutableStateFlow(ZipDatasetStatus())
    val status: StateFlow<ZipDatasetStatus> = _status.asStateFlow()

    fun getDatasetDir(context: Context): File {
        val dir = File(context.filesDir, "dataset")
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    private fun addLog(logs: MutableList<String>, msg: String) {
        logs.add(msg)
        if (logs.size > 200) logs.removeAt(0)
        _status.value = _status.value.copy(logs = logs.toList())
    }

    /**
     * Cuenta cuántos campeones y variantes hay actualmente en el almacenamiento del dataset.
     */
    fun refreshStats(context: Context) {
        val datasetDir = getDatasetDir(context)
        val champDirs = datasetDir.listFiles { f -> f.isDirectory } ?: emptyArray()
        var totalImages = 0
        for (cDir in champDirs) {
            val imgs = cDir.listFiles { f -> f.isFile && (f.name.endsWith(".png", true) || f.name.endsWith(".jpg", true) || f.name.endsWith(".jpeg", true) || f.name.endsWith(".webp", true)) }
            totalImages += (imgs?.size ?: 0)
        }
        val ready = champDirs.isNotEmpty() && totalImages > 0
        _status.value = _status.value.copy(
            championsCount = champDirs.size,
            totalImagesCount = totalImages,
            isReady = ready,
            progressMessage = if (ready) "Dataset ZIP Activo: ${champDirs.size} campeones ($totalImages imágenes)" else "Sin dataset ZIP cargado"
        )
    }

    /**
     * Busca automáticamente archivos ZIP en Descargas o almacenamiento interno y los importa.
     */
    suspend fun autoDetectAndImportFromDownloads(context: Context): Boolean = withContext(Dispatchers.IO) {
        val candidateDirs = mutableListOf<File>()
        try {
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)?.let { candidateDirs.add(it) }
        } catch (_: Throwable) {}
        try {
            context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.let { candidateDirs.add(it) }
            context.getExternalFilesDir(null)?.let { candidateDirs.add(it) }
            candidateDirs.add(File("/sdcard/Download"))
        } catch (_: Throwable) {}

        val foundZips = mutableListOf<File>()
        for (dir in candidateDirs) {
            if (dir.exists() && dir.isDirectory) {
                val zips = dir.listFiles { f -> f.isFile && f.name.endsWith(".zip", true) }
                if (zips != null) {
                    foundZips.addAll(zips)
                }
            }
        }

        if (foundZips.isEmpty()) return@withContext false

        // Elegir el archivo zip más reciente
        val targetZip = foundZips.maxByOrNull { it.lastModified() } ?: return@withContext false
        return@withContext importZipFile(context, targetZip)
    }

    /**
     * Importa y descomprime un archivo ZIP desde un Uri (File Picker).
     */
    suspend fun importFromUri(context: Context, uri: Uri, fileName: String = "dataset_141.zip"): Boolean = withContext(Dispatchers.IO) {
        try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return@withContext false
            return@withContext extractZipStream(context, inputStream, fileName)
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error abriendo Uri de ZIP", e)
            return@withContext false
        }
    }

    /**
     * Importa y descomprime un archivo ZIP local.
     */
    suspend fun importZipFile(context: Context, file: File): Boolean = withContext(Dispatchers.IO) {
        try {
            val inputStream = file.inputStream()
            return@withContext extractZipStream(context, inputStream, file.name)
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error leyendo archivo ZIP", e)
            return@withContext false
        }
    }

    private suspend fun extractZipStream(context: Context, inputStream: InputStream, zipName: String): Boolean {
        val logs = mutableListOf<String>()
        _status.value = _status.value.copy(
            isImporting = true,
            progressMessage = "Extrayendo ZIP de campeones: $zipName...",
            lastZipName = zipName,
            logs = emptyList()
        )
        addLog(logs, "Iniciando extracción y descompresión de $zipName...")

        val datasetDir = getDatasetDir(context)
        var extractedCount = 0
        val detectedChampions = mutableSetOf<String>()

        try {
            val zis = ZipInputStream(inputStream)
            var entry: ZipEntry? = zis.nextEntry
            val buffer = ByteArray(8192)

            while (entry != null) {
                if (!entry.isDirectory) {
                    val entryName = entry.name.replace("\\", "/")
                    val ext = entryName.substringAfterLast('.', "").lowercase()
                    if (ext in listOf("png", "jpg", "jpeg", "webp")) {
                        val parts = entryName.split('/')
                        val fileName = parts.last()
                        
                        // Determinar el nombre del campeón inspeccionando las carpetas o el nombre de archivo
                        var champName = ""
                        // 1. Buscar en las partes de carpeta si alguna coincide con campeón o no es genérica
                        for (i in (parts.size - 2) downTo 0) {
                            val candidateFolder = parts[i].trim()
                            val lower = candidateFolder.lowercase()
                            if (lower !in listOf("dataset", "campeones", "champions", "images", "assets", "wildrift", "archive", "__macosx") && !lower.startsWith(".")) {
                                champName = candidateFolder
                                break
                            }
                        }
                        
                        // 2. Si no se encontró en carpetas, extraer del nombre de archivo (ej: Volibear_blur.png o Volibear.png)
                        if (champName.isBlank()) {
                            val nameWithoutExt = fileName.substringBeforeLast('.')
                            champName = when {
                                nameWithoutExt.contains('_') -> nameWithoutExt.substringBeforeLast('_')
                                nameWithoutExt.contains('-') -> nameWithoutExt.substringBeforeLast('-')
                                else -> nameWithoutExt
                            }.trim()
                        }

                        if (champName.isNotBlank() && !champName.startsWith(".")) {
                            val champFolder = File(datasetDir, champName)
                            if (!champFolder.exists()) champFolder.mkdirs()

                            val destFile = File(champFolder, fileName)
                            FileOutputStream(destFile).use { fos ->
                                var len: Int
                                while (zis.read(buffer).also { len = it } > 0) {
                                    fos.write(buffer, 0, len)
                                }
                            }
                            extractedCount++
                            detectedChampions.add(champName)

                            if (extractedCount % 40 == 0) {
                                addLog(logs, "Extraídas $extractedCount variantes (${detectedChampions.size} campeones)...")
                                _status.value = _status.value.copy(
                                    progressMessage = "Extrayendo... ($extractedCount variantes, ${detectedChampions.size} campeones)",
                                    championsCount = detectedChampions.size,
                                    totalImagesCount = extractedCount
                                )
                            }
                        }
                    }
                }
                zis.closeEntry()
                entry = zis.nextEntry
            }
            zis.close()

            addLog(logs, "Extracción de ZIP finalizada con éxito.")
            addLog(logs, "Total: $extractedCount variantes de ${detectedChampions.size} campeones extraídas en el dataset.")

            _status.value = _status.value.copy(
                isImporting = false,
                progressMessage = "Dataset ZIP cargado: ${detectedChampions.size} campeones ($extractedCount variantes)",
                championsCount = detectedChampions.size,
                totalImagesCount = extractedCount,
                isReady = detectedChampions.isNotEmpty()
            )

            // Recargar inmediatamente el motor de análisis visual del 10º pick
            LocalVisionAnalyzer.reloadFromExtractedDataset(context)
            addLog(logs, "Motor de visión del 10º pick sincronizado al 100% con el dataset del ZIP.")

            return true
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error extrayendo ZIP", e)
            addLog(logs, "ERROR: ${e.message}")
            _status.value = _status.value.copy(
                isImporting = false,
                progressMessage = "Error al extraer ZIP: ${e.message}"
            )
            return false
        }
    }
}
