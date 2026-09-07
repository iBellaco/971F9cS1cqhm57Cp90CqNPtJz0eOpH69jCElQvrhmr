package com.example.util

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedOutputStream
import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

object AssetExporter {

    suspend fun exportAssetsToZip(context: Context): Result<String> = withContext(Dispatchers.IO) {
        try {
            val fileName = "WildRiftCoach_Assets_Export_${System.currentTimeMillis()}.zip"
            
            // Usamos MediaStore para guardar en la carpeta de Descargas (Android 10+)
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "application/zip")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }

            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
                ?: return@withContext Result.failure(Exception("No se pudo crear el archivo en Descargas"))

            resolver.openOutputStream(uri)?.use { outputStream ->
                ZipOutputStream(BufferedOutputStream(outputStream)).use { zipOut ->
                    val assetManager = context.assets
                    
                    // Carpetas que queremos exportar
                    val foldersToExport = listOf("champions", "items", "runes", "spells")
                    
                    for (folder in foldersToExport) {
                        exportFolder(context, folder, zipOut)
                    }

                    // Archivos individuales que también queremos (ej. jsons, metadata)
                    val files = assetManager.list("") ?: emptyArray()
                    for (file in files) {
                        if (file.endsWith(".json")) {
                            addFileToZip(context, file, zipOut)
                        }
                    }
                }
            }
            Result.success("Exportado exitosamente a Descargas: $fileName")
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private fun exportFolder(context: Context, folderPath: String, zipOut: ZipOutputStream) {
        val assetManager = context.assets
        try {
            val files = assetManager.list(folderPath) ?: emptyArray()
            if (files.isEmpty()) return

            for (file in files) {
                val fullPath = "$folderPath/$file"
                // Si es un directorio, list() devolvería elementos, pero en assets los directorios
                // suelen manejarse diferente. Haremos addFileToZip que captura FileNotFoundException
                // si resultara ser un subdirectorio.
                addFileToZip(context, fullPath, zipOut)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun addFileToZip(context: Context, filePath: String, zipOut: ZipOutputStream) {
        try {
            context.assets.open(filePath).use { inputStream ->
                val zipEntry = ZipEntry(filePath)
                zipOut.putNextEntry(zipEntry)
                
                val buffer = ByteArray(1024)
                var length: Int
                while (inputStream.read(buffer).also { length = it } >= 0) {
                    zipOut.write(buffer, 0, length)
                }
                zipOut.closeEntry()
            }
        } catch (e: Exception) {
            // Podría ser un subdirectorio u otro error
        }
    }
}
