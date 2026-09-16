package com.example.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.example.data.WildRiftRepository
import com.example.model.Champion
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

/**
 * Gestor de exportación y descarga de avatares oficiales de campeones de Wild Rift.
 * Guarda los 141 avatares en la carpeta de Descargas pública del dispositivo (Download/WildRift_Avatares)
 * con nombres legibles y normalizados.
 */
object ChampionAvatarExporter {

    private const val TAG = "ChampionAvatarExporter"
    const val FOLDER_NAME = "WildRift_Avatares"

    data class ExportState(
        val isExporting: Boolean = false,
        val currentCount: Int = 0,
        val totalCount: Int = 0,
        val currentChampionName: String = "",
        val currentChampionId: String = "",
        val isComplete: Boolean = false,
        val savedCount: Int = 0,
        val errorCount: Int = 0,
        val targetPath: String = "Download/$FOLDER_NAME",
        val errorMessage: String? = null
    ) {
        val progressPercent: Float
            get() = if (totalCount > 0) (currentCount.toFloat() / totalCount.toFloat()).coerceIn(0f, 1f) else 0f
    }

    private val _exportState = MutableStateFlow(ExportState())
    val exportState: StateFlow<ExportState> = _exportState.asStateFlow()

    private var exportJob: Job? = null

    /**
     * Inicia la descarga/exportación calmada de los 141 campeones a la carpeta de descargas.
     */
    fun startExport(context: Context) {
        if (_exportState.value.isExporting) return

        exportJob?.cancel()
        exportJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                if (WildRiftRepository.champions.isEmpty()) {
                    WildRiftRepository.initChampions(context)
                }

                val championsList = WildRiftRepository.champions.toList().ifEmpty {
                    // Fallback directo a lista de assets
                    try {
                        context.assets.list("champions")?.mapNotNull { fileName ->
                            if (fileName.endsWith(".png")) {
                                val id = fileName.removeSuffix(".png")
                                Champion(id = id, name = id.replace("_", " ").replaceFirstChar { it.uppercase() })
                            } else null
                        } ?: emptyList()
                    } catch (_: Exception) {
                        emptyList()
                    }
                }

                val total = championsList.size
                if (total == 0) {
                    _exportState.value = ExportState(
                        isExporting = false,
                        errorMessage = "No se encontraron campeones en el catálogo local."
                    )
                    return@launch
                }

                _exportState.value = ExportState(
                    isExporting = true,
                    currentCount = 0,
                    totalCount = total,
                    currentChampionName = "Preparando descarga...",
                    targetPath = "Download/$FOLDER_NAME"
                )

                var saved = 0
                var errors = 0

                for ((index, champ) in championsList.withIndex()) {
                    val safeName = sanitizeFileName(champ.name.ifBlank { champ.id })
                    val fileName = "$safeName.png"

                    _exportState.value = _exportState.value.copy(
                        isExporting = true,
                        currentCount = index + 1,
                        totalCount = total,
                        currentChampionName = champ.name.ifBlank { champ.id },
                        currentChampionId = champ.id
                    )

                    val success = saveChampionAvatar(context, champ.id, fileName)
                    if (success) {
                        saved++
                    } else {
                        errors++
                    }

                    // Descarga pausada / calmada (60ms) para no congelar el IO del almacenamiento ni la UI
                    delay(60)
                }

                _exportState.value = ExportState(
                    isExporting = false,
                    currentCount = total,
                    totalCount = total,
                    isComplete = true,
                    savedCount = saved,
                    errorCount = errors,
                    targetPath = "Download/$FOLDER_NAME",
                    currentChampionName = "¡Completado!"
                )
                AppLogger.d(TAG, "Exportación finalizada: $saved guardados, $errors errores en Download/$FOLDER_NAME")
            } catch (e: CancellationException) {
                _exportState.value = _exportState.value.copy(
                    isExporting = false,
                    currentChampionName = "Descarga cancelada"
                )
            } catch (e: Exception) {
                AppLogger.e(TAG, "Error durante la exportación de avatares", e)
                _exportState.value = _exportState.value.copy(
                    isExporting = false,
                    errorMessage = "Error: ${e.localizedMessage ?: "Fallo de almacenamiento"}"
                )
            }
        }
    }

    /**
     * Cancela la descarga en curso.
     */
    fun cancelExport() {
        exportJob?.cancel()
        exportJob = null
        _exportState.value = _exportState.value.copy(
            isExporting = false,
            currentChampionName = "Descarga cancelada"
        )
    }

    /**
     * Reinicia el estado del diálogo.
     */
    fun resetState() {
        if (!_exportState.value.isExporting) {
            _exportState.value = ExportState()
        }
    }

    /**
     * Guarda el bitmap del avatar en la carpeta pública de descargas del usuario.
     */
    private fun saveChampionAvatar(context: Context, champId: String, outFileName: String): Boolean {
        var outputStream: OutputStream? = null
        try {
            val assetPath = "champions/$champId.png"
            val bitmap = context.assets.open(assetPath).use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            } ?: return false

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val resolver = context.contentResolver
                val contentValues = ContentValues().apply {
                    put(MediaStore.Downloads.DISPLAY_NAME, outFileName)
                    put(MediaStore.Downloads.MIME_TYPE, "image/png")
                    put(MediaStore.Downloads.RELATIVE_PATH, "${Environment.DIRECTORY_DOWNLOADS}/$FOLDER_NAME")
                    put(MediaStore.Downloads.IS_PENDING, 1)
                }

                // Borrar previo si ya existe
                val collectionUri: Uri = MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                val itemUri = resolver.insert(collectionUri, contentValues) ?: return false

                outputStream = resolver.openOutputStream(itemUri)
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.flush()
                }

                contentValues.clear()
                contentValues.put(MediaStore.Downloads.IS_PENDING, 0)
                resolver.update(itemUri, contentValues, null, null)
            } else {
                val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val targetDir = File(downloadsDir, FOLDER_NAME)
                if (!targetDir.exists()) {
                    targetDir.mkdirs()
                }
                val outFile = File(targetDir, outFileName)
                outputStream = FileOutputStream(outFile)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
            }

            try { bitmap.recycle() } catch (_: Throwable) {}
            return true
        } catch (e: Exception) {
            AppLogger.w(TAG, "No se pudo guardar avatar para $champId: ${e.message}")
            return false
        } finally {
            try { outputStream?.close() } catch (_: Throwable) {}
        }
    }

    /**
     * Limpia caracteres no válidos en nombres de archivos.
     */
    private fun sanitizeFileName(name: String): String {
        return name.replace("/", "_")
            .replace("\\", "_")
            .replace(":", "_")
            .replace("*", "_")
            .replace("?", "_")
            .replace("\"", "_")
            .replace("<", "_")
            .replace(">", "_")
            .replace("|", "_")
            .trim()
    }
}
