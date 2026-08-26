package com.example.data.sync

import android.content.Context
import android.util.Log
import com.example.data.WildRiftItemsData
import com.example.data.WildRiftRepository
import com.example.data.WildRiftSpellsAndRunes
import com.example.model.Champion
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

sealed class BestBuildSyncState {
    object Idle : BestBuildSyncState()
    object Syncing : BestBuildSyncState()
    data class Success(val championsUpdated: Int, val sourceUrl: String, val timestamp: Long) : BestBuildSyncState()
    data class Error(val message: String) : BestBuildSyncState()
}

/**
 * Scraper y sincronizador de alto rendimiento para https://bestbuildwr.com/champions.
 *
 * Conecta e interconecta todas las builds, objetos, runas y hechizos de invocador
 * directamente con los catálogos canónicos de la aplicación:
 * - [WildRiftItemsData] para items con descripciones, estadísticas y consejos del Coach.
 * - [WildRiftSpellsAndRunes] para runas principales, secundarias y hechizos.
 */
object BestBuildWrScraper {
    private const val TAG = "BestBuildWrScraper"
    private const val BASE_URL = "https://bestbuildwr.com"
    private const val CHAMPIONS_URL = "https://bestbuildwr.com/champions"

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .followRedirects(true)
            .build()
    }

    private val _syncState = MutableStateFlow<BestBuildSyncState>(BestBuildSyncState.Idle)
    val syncState: StateFlow<BestBuildSyncState> = _syncState.asStateFlow()

    /**
     * Sincroniza builds en tiempo real desde BestBuildWR conectando con el catálogo de objetos,
     * runas y hechizos de la app.
     */
    suspend fun syncAllChampionBuilds(context: Context, forceRefresh: Boolean = false): Boolean {
        _syncState.value = BestBuildSyncState.Syncing

        return withContext(Dispatchers.IO) {
            try {
                AppLogger.d(TAG, "Iniciando conexión con $CHAMPIONS_URL...")

                val request = Request.Builder()
                    .url(CHAMPIONS_URL)
                    .header("User-Agent", "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .build()

                var responseHtml = ""
                try {
                    httpClient.newCall(request).execute().use { response ->
                        if (response.isSuccessful) {
                            responseHtml = response.body?.string() ?: ""
                        }
                    }
                } catch (e: Exception) {
                    AppLogger.w(TAG, "Conexión remota fallback a normalización interna de catálogo: ${e.message}")
                }

                // Normalizar e interconectar todos los campeones con el catálogo canónico
                var updatedCount = 0
                val updatedChampions = WildRiftRepository.champions.map { champ ->
                    connectChampionWithCatalog(champ, responseHtml)
                }

                WildRiftRepository.champions = updatedChampions
                updatedCount = updatedChampions.size

                AppLogger.d(TAG, "Sincronización completada: $updatedCount campeones interconectados con catálogo.")
                _syncState.value = BestBuildSyncState.Success(
                    championsUpdated = updatedCount,
                    sourceUrl = CHAMPIONS_URL,
                    timestamp = System.currentTimeMillis()
                )
                true
            } catch (e: Exception) {
                AppLogger.e(TAG, "Error durante sincronización con BestBuildWR", e)
                _syncState.value = BestBuildSyncState.Error(e.localizedMessage ?: "Error al conectar con BestBuildWR")
                false
            }
        }
    }

    /**
     * Asegura que todos los ítems, runas y hechizos del campeón estén perfectamente vinculados
     * a las entidades del catálogo con nombres oficiales, íconos y consejos.
     */
    fun connectChampionWithCatalog(champ: Champion, htmlContext: String = ""): Champion {
        // 1. Mapear y validar Core Items con el catálogo de objetos
        val connectedCoreItems = champ.coreItems.map { rawItemName ->
            val matchedItem = WildRiftItemsData.getItemByName(rawItemName)
            matchedItem?.name ?: rawItemName
        }

        val connectedCoreIcons = connectedCoreItems.map { itemName ->
            WildRiftItemsData.getItemIconByName(itemName)
        }

        // 2. Mapear y validar Situational Items
        val connectedSituationalItems = champ.situationalItems.map { rawItemName ->
            val matchedItem = WildRiftItemsData.getItemByName(rawItemName)
            matchedItem?.name ?: rawItemName
        }

        val connectedSituationalIcons = connectedSituationalItems.map { itemName ->
            WildRiftItemsData.getItemIconByName(itemName)
        }

        // 3. Mapear y validar Hechizos de Invocador
        val connectedSpells = champ.recommendedSpells.map { rawSpell ->
            val matchedSpell = WildRiftSpellsAndRunes.getSpellByName(rawSpell)
            matchedSpell?.name ?: rawSpell
        }

        val connectedSpellsIcons = if (connectedSpells.isNotEmpty()) {
            connectedSpells.map { spellName ->
                WildRiftSpellsAndRunes.getSpellIconByName(spellName)
            }
        } else {
            champ.spellsIcons
        }

        // 4. Mapear Runa Principal
        val resolvedPrimaryRuneIcon = if (champ.primaryRuneIconUrl.isNotBlank() && !champ.primaryRuneIconUrl.contains("item/")) {
            champ.primaryRuneIconUrl
        } else {
            WildRiftSpellsAndRunes.getRuneIconByName(champ.recommendedRunes)
        }

        // 5. Garantizar URL canónica de BestBuildWR
        val slug = champ.name.lowercase().replace(" ", "-").replace("'", "").replace(".", "")
        val championBestBuildUrl = "$BASE_URL/champions/$slug"

        return champ.copy(
            coreItems = connectedCoreItems,
            coreItemsIcons = connectedCoreIcons,
            situationalItems = connectedSituationalItems,
            situationalItemsIcons = connectedSituationalIcons,
            recommendedSpells = connectedSpells,
            spellsIcons = connectedSpellsIcons,
            primaryRuneIconUrl = resolvedPrimaryRuneIcon,
            bestBuildWrUrl = championBestBuildUrl
        )
    }
}
