package com.example.data.supabase

import android.content.Context
import android.util.Log
import com.example.data.WildRiftRepository
import com.example.data.local.WildRiftLocalCache
import com.example.data.supabase.model.WrChampionDto
import com.example.data.supabase.model.WrItemDto
import com.example.data.supabase.model.WrPatchDto
import com.example.data.supabase.model.WrRuneDto
import com.example.data.supabase.model.WrSpellDto
import com.example.model.Champion
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.model.WildRiftItem
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object WildRiftSupabaseRepository {

    private const val TAG = "WildRiftSupabaseRepo"
    private const val TABLE_PATCHES = "wr_patches"
    private const val TABLE_ITEMS = "wr_items"
    private const val TABLE_CHAMPIONS = "wr_champions"
    private const val TABLE_RUNES = "wr_runes"
    private const val TABLE_SPELLS = "wr_spells"

    private val postgrest get() = SupabaseClientManager.client.postgrest

    // =========================================================================
    // 1. GESTIÓN DE PARCHES
    // =========================================================================

    suspend fun fetchCurrentPatch(): Result<WrPatchDto?> = withContext(Dispatchers.IO) {
        try {
            val list = postgrest.from(TABLE_PATCHES).select().decodeList<WrPatchDto>()
            val current = list.find { it.id == "current" } ?: list.firstOrNull()
            Result.success(current)
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo parche de Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun publishPatch(version: String, notes: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
            val patchDto = WrPatchDto(
                id = "current",
                version = version.trim(),
                notes = notes.trim(),
                updatedAt = sdf.format(Date())
            )
            postgrest.from(TABLE_PATCHES).upsert(patchDto)
            WildRiftRepository.CURRENT_PATCH_VERSION = version.trim()
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error publicando parche en Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    // =========================================================================
    // 2. GESTIÓN DE OBJETOS / ITEMS
    // =========================================================================

    suspend fun fetchAllItems(): Result<List<WildRiftItem>> = withContext(Dispatchers.IO) {
        try {
            val dtoList = postgrest.from(TABLE_ITEMS).select().decodeList<WrItemDto>()
            val items = dtoList.map { it.toModel() }
            Result.success(items)
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo ítems de Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun saveItem(item: WildRiftItem): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val dto = WrItemDto.fromModel(item)
            postgrest.from(TABLE_ITEMS).upsert(dto)
            
            // Actualizar lista en memoria
            val current = WildRiftRepository.items.toMutableList()
            val index = current.indexOfFirst { it.id == item.id }
            if (index != -1) {
                current[index] = item
            } else {
                current.add(0, item)
            }
            WildRiftRepository.items = current
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error guardando ítem en Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun deleteItem(itemId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_ITEMS).delete {
                filter { eq("id", itemId) }
            }
            WildRiftRepository.items = WildRiftRepository.items.filterNot { it.id == itemId }
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error eliminando ítem de Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    // =========================================================================
    // 3. GESTIÓN DE CAMPEONES
    // =========================================================================

    suspend fun fetchAllChampions(): Result<List<Champion>> = withContext(Dispatchers.IO) {
        try {
            val dtoList = postgrest.from(TABLE_CHAMPIONS).select().decodeList<WrChampionDto>()
            val fallbackMap = WildRiftRepository.champions.associateBy { it.id }
            val champions = dtoList.map { dto ->
                dto.toModel(fallbackMap[dto.id])
            }
            Result.success(champions)
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo campeones de Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun saveChampion(champion: Champion): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val dto = WrChampionDto.fromModel(champion)
            postgrest.from(TABLE_CHAMPIONS).upsert(dto)

            val current = WildRiftRepository.champions.toMutableList()
            val index = current.indexOfFirst { it.id == champion.id }
            if (index != -1) {
                current[index] = champion
            } else {
                current.add(0, champion)
            }
            WildRiftRepository.champions = current
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error guardando campeón en Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun deleteChampion(championId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_CHAMPIONS).delete {
                filter { eq("id", championId) }
            }
            WildRiftRepository.champions = WildRiftRepository.champions.filterNot { it.id == championId }
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error eliminando campeón de Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    // =========================================================================
    // 4. GESTIÓN DE RUNAS Y HECHIZOS
    // =========================================================================

    suspend fun fetchAllRunes(): Result<List<RuneItem>> = withContext(Dispatchers.IO) {
        try {
            val dtoList = postgrest.from(TABLE_RUNES).select().decodeList<WrRuneDto>()
            Result.success(dtoList.map { it.toModel() })
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo runas de Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun saveRune(rune: RuneItem): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val dto = WrRuneDto.fromModel(rune)
            postgrest.from(TABLE_RUNES).upsert(dto)
            val current = WildRiftRepository.runes.toMutableList()
            val index = current.indexOfFirst { it.id == rune.id }
            if (index != -1) current[index] = rune else current.add(rune)
            WildRiftRepository.runes = current
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteRune(runeId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_RUNES).delete { filter { eq("id", runeId) } }
            WildRiftRepository.runes = WildRiftRepository.runes.filterNot { it.id == runeId }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchAllSpells(): Result<List<SummonerSpellItem>> = withContext(Dispatchers.IO) {
        try {
            val dtoList = postgrest.from(TABLE_SPELLS).select().decodeList<WrSpellDto>()
            Result.success(dtoList.map { it.toModel() })
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo hechizos de Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun saveSpell(spell: SummonerSpellItem): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val dto = WrSpellDto.fromModel(spell)
            postgrest.from(TABLE_SPELLS).upsert(dto)
            val current = WildRiftRepository.summonerSpells.toMutableList()
            val index = current.indexOfFirst { it.id == spell.id }
            if (index != -1) current[index] = spell else current.add(spell)
            WildRiftRepository.summonerSpells = current
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // =========================================================================
    // 5. SINCRONIZACIÓN COMPLETA (CLOUD -> APP -> CACHÉ LOCAL)
    // =========================================================================

    suspend fun syncAllFromSupabase(context: Context): Result<String> = withContext(Dispatchers.IO) {
        try {
            var syncedItemsCount = 0
            var syncedChampsCount = 0
            var syncedRunesCount = 0
            var syncedSpellsCount = 0
            var patchVersion = WildRiftRepository.CURRENT_PATCH_VERSION

            // 1. Sincronizar parche
            try {
                val patchResult = fetchCurrentPatch()
                patchResult.getOrNull()?.let { patch ->
                    if (patch.version.isNotBlank()) {
                        patchVersion = patch.version
                        WildRiftRepository.CURRENT_PATCH_VERSION = patch.version
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error sincronizando parche: ${e.message}")
            }

            // 2. Sincronizar ítems
            try {
                val itemsResult = fetchAllItems()
                itemsResult.getOrNull()?.takeIf { it.isNotEmpty() }?.let { items ->
                    WildRiftRepository.items = items
                    syncedItemsCount = items.size
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error sincronizando items: ${e.message}")
            }

            // 3. Sincronizar campeones
            try {
                val champsResult = fetchAllChampions()
                champsResult.getOrNull()?.takeIf { it.isNotEmpty() }?.let { champs ->
                    WildRiftRepository.champions = champs
                    syncedChampsCount = champs.size
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error sincronizando campeones: ${e.message}")
            }

            // 4. Sincronizar runas
            try {
                val runesResult = fetchAllRunes()
                runesResult.getOrNull()?.takeIf { it.isNotEmpty() }?.let { runes ->
                    WildRiftRepository.runes = runes
                    syncedRunesCount = runes.size
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error sincronizando runas: ${e.message}")
            }

            // 5. Sincronizar hechizos
            try {
                val spellsResult = fetchAllSpells()
                spellsResult.getOrNull()?.takeIf { it.isNotEmpty() }?.let { spells ->
                    WildRiftRepository.summonerSpells = spells
                    syncedSpellsCount = spells.size
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error sincronizando hechizos: ${e.message}")
            }

            // Guardar en la caché local persistente
            WildRiftLocalCache.saveToLocalCache(
                context = context,
                items = WildRiftRepository.items,
                champions = WildRiftRepository.champions,
                runes = WildRiftRepository.runes,
                spells = WildRiftRepository.summonerSpells,
                patchVersion = WildRiftRepository.CURRENT_PATCH_VERSION
            )

            val summary = "Sincronización completada con éxito: $syncedItemsCount objetos, $syncedChampsCount campeones, $syncedRunesCount runas, $syncedSpellsCount hechizos ($patchVersion)."
            WildRiftRepository.LAST_SYNC_STATUS = "Sincronizado con Supabase (${SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())})"
            Result.success(summary)
        } catch (e: Exception) {
            Log.e(TAG, "Fallo en la sincronización total: ${e.message}", e)
            Result.failure(e)
        }
    }

    // =========================================================================
    // 6. SIEMBRA INICIAL / SEED DATA (SUBIR TODO EL CATÁLOGO A SUPABASE)
    // =========================================================================

    suspend fun seedAllDataToSupabase(
        onProgress: (current: Int, total: Int, message: String) -> Unit
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val allItems = WildRiftRepository.items
            val allChamps = WildRiftRepository.champions
            val allRunes = WildRiftRepository.runes
            val allSpells = WildRiftRepository.summonerSpells
            val totalOperations = allItems.size + allChamps.size + allRunes.size + allSpells.size + 1
            var currentOp = 0

            // 1. Subir Parche
            onProgress(currentOp++, totalOperations, "Subiendo información del parche (${WildRiftRepository.CURRENT_PATCH_VERSION})...")
            publishPatch(WildRiftRepository.CURRENT_PATCH_VERSION, "Sincronización inicial del catálogo completo de Wild Rift.")

            // 2. Subir Ítems en lotes
            val itemDtos = allItems.map { WrItemDto.fromModel(it) }
            itemDtos.chunked(25).forEach { batch ->
                postgrest.from(TABLE_ITEMS).upsert(batch)
                currentOp += batch.size
                onProgress(currentOp.coerceAtMost(totalOperations), totalOperations, "Subiendo objetos ($currentOp/${allItems.size})...")
            }

            // 3. Subir Campeones en lotes
            val champDtos = allChamps.map { WrChampionDto.fromModel(it) }
            champDtos.chunked(20).forEach { batch ->
                postgrest.from(TABLE_CHAMPIONS).upsert(batch)
                currentOp += batch.size
                onProgress(currentOp.coerceAtMost(totalOperations), totalOperations, "Subiendo campeones ($currentOp/$totalOperations)...")
            }

            // 4. Subir Runas
            val runeDtos = allRunes.map { WrRuneDto.fromModel(it) }
            if (runeDtos.isNotEmpty()) {
                postgrest.from(TABLE_RUNES).upsert(runeDtos)
                currentOp += runeDtos.size
                onProgress(currentOp.coerceAtMost(totalOperations), totalOperations, "Subiendo catálogo de runas...")
            }

            // 5. Subir Hechizos
            val spellDtos = allSpells.map { WrSpellDto.fromModel(it) }
            if (spellDtos.isNotEmpty()) {
                postgrest.from(TABLE_SPELLS).upsert(spellDtos)
                currentOp += spellDtos.size
                onProgress(currentOp.coerceAtMost(totalOperations), totalOperations, "Subiendo hechizos de invocador...")
            }

            onProgress(totalOperations, totalOperations, "¡Catálogo completo sembrado en Supabase con éxito!")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error en seedAllDataToSupabase: ${e.message}", e)
            Result.failure(e)
        }
    }
}
