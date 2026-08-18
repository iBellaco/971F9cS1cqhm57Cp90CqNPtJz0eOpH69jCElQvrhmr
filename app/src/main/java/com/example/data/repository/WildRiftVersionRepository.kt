package com.example.data.repository

import com.example.data.remote.api.ITunesApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Excepción lanzada cuando la API no retorna resultados.
 */
class NoAppFoundException(message: String = "No se encontraron resultados para Wild Rift en iTunes Lookup") : Exception(message)

/**
 * Excepción lanzada cuando el resultado no contiene un campo de versión válido.
 */
class VersionNotFoundException(message: String = "La respuesta no incluye el campo 'version'") : Exception(message)

interface WildRiftVersionRepository {
    /**
     * Obtiene la versión pública más reciente de Wild Rift retornando un Result<String>.
     */
    suspend fun getLatestVersion(bundleId: String = ITunesApiService.WILD_RIFT_BUNDLE_ID): Result<String>

    /**
     * Emite el resultado como un Flow reactivo.
     */
    fun getLatestVersionStream(bundleId: String = ITunesApiService.WILD_RIFT_BUNDLE_ID): Flow<Result<String>>
}

class WildRiftVersionRepositoryImpl(
    private val apiService: ITunesApiService = ITunesApiService.create(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : WildRiftVersionRepository {

    override suspend fun getLatestVersion(bundleId: String): Result<String> = withContext(ioDispatcher) {
        runCatching {
            val response = apiService.lookupAppByBundleId(bundleId)
            
            if (response.resultCount == 0 || response.results.isEmpty()) {
                throw NoAppFoundException("No se encontró la aplicación con bundleId: $bundleId")
            }

            val firstResult = response.results.first()
            val version = firstResult.version

            if (version.isNullOrBlank()) {
                throw VersionNotFoundException("El campo 'version' está vacío o ausente en el payload.")
            }

            version
        }
    }

    override fun getLatestVersionStream(bundleId: String): Flow<Result<String>> = flow {
        emit(getLatestVersion(bundleId))
    }.flowOn(ioDispatcher)
}
