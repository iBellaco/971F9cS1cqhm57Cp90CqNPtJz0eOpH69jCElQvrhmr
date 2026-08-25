package com.example.data.supabase

import android.content.Context
import android.util.Log
import com.example.data.WildRiftRepository
import com.example.data.local.WildRiftLocalCache
import com.example.data.supabase.model.*
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object WildRiftSupabaseRepository {
    private const val TAG = "WildRiftSupabaseRepo"
    private const val TABLE_PATCHES = "wr_patches"
    private const val TABLE_ITEMS = "wr_items"
    private const val TABLE_CHAMPIONS = "wr_champions"
    private const val TABLE_RUNES = "wr_runes"
    private const val TABLE_SPELLS = "wr_spells"

    private val postgrest get() = SupabaseClientManager.client.postgrest

    suspend fun syncAllFromSupabase(context: Context): Result<String> = withContext(Dispatchers.IO) {
        try {
            val items = postgrest.from(TABLE_ITEMS).select().decodeList<WrItemDto>().map { it.toModel() }
            val champions = postgrest.from(TABLE_CHAMPIONS).select().decodeList<WrChampionDto>().map { it.toModel() }
            val runes = postgrest.from(TABLE_RUNES).select().decodeList<WrRuneDto>().map { it.toModel() }
            val spells = postgrest.from(TABLE_SPELLS).select().decodeList<WrSpellDto>().map { it.toModel() }

            val validItems = items.filter { it.id.isNotBlank() && it.name.isNotBlank() }
            val validChamps = champions.filter { it.id.isNotBlank() && it.name.isNotBlank() }
            val validRunes = runes.filter { it.id.isNotBlank() && it.name.isNotBlank() }
            val validSpells = spells.filter { it.id.isNotBlank() && it.name.isNotBlank() }

            if (validItems.isNotEmpty()) {
                val spellIds = com.example.data.WildRiftSpellsAndRunes.summonerSpells.map { it.id }.toSet()
                val canonicalMap = com.example.data.WildRiftItemsData.list.associateBy { it.id }
                val nameMap = com.example.data.WildRiftItemsData.list.associateBy { it.name.lowercase().trim() }
                
                val filteredItems = validItems.filter { item ->
                    val isSpell = item.id.endsWith("_basic") && item.id.replace("_basic", "") in spellIds.map { it.replace("spell_", "") }
                    !isSpell && !item.id.startsWith("spell_")
                }.map { item ->
                    val canonical = canonicalMap[item.id] ?: nameMap[item.name.lowercase().trim()]
                    if (canonical != null) {
                        item.copy(
                            iconUrl = if (item.iconUrl.isBlank() || !item.iconUrl.startsWith("http") || item.iconUrl.contains("placeholder")) canonical.iconUrl else item.iconUrl,
                            name = if (item.name.isBlank()) canonical.name else item.name,
                            stats = if (item.stats.isBlank()) canonical.stats else item.stats,
                            passive = if (item.passive.isBlank()) canonical.passive else item.passive,
                            category = if (item.category.isBlank()) canonical.category else item.category
                        )
                    } else {
                        item
                    }
                }
                val merged = (WildRiftRepository.items.associateBy { it.id } + filteredItems.associateBy { it.id }).values.toList()
                WildRiftRepository.items = merged
            }

            if (validChamps.isNotEmpty()) {
                val canonicalChamps = (com.example.data.champions.BaronLaneChampions.list + 
                    com.example.data.champions.JungleChampions.list + 
                    com.example.data.champions.MidLaneChampions.list + 
                    com.example.data.champions.DragonLaneChampions.list + 
                    com.example.data.champions.SupportChampions.list).distinctBy { it.id }
                val canonicalMap = canonicalChamps.associateBy { it.id }
                val nameMap = canonicalChamps.associateBy { it.name.lowercase().trim() }
                
                val mappedChamps = validChamps.map { champ ->
                    val canonical = canonicalMap[champ.id] ?: nameMap[champ.name.lowercase().trim()]
                    if (canonical != null) {
                        canonical.copy(
                            tier = champ.tier,
                            winrate = champ.winrate,
                            pickRate = champ.pickRate,
                            banRate = champ.banRate,
                            damageType = champ.damageType,
                            primaryRole = champ.primaryRole,
                            secondaryRoles = if (champ.secondaryRoles.isNotEmpty()) champ.secondaryRoles else canonical.secondaryRoles,
                            avatarUrl = if (champ.avatarUrl.isNotBlank()) champ.avatarUrl else canonical.avatarUrl,
                            counteredBy = if (champ.counteredBy.isNotEmpty()) champ.counteredBy else canonical.counteredBy,
                            synergies = if (champ.synergies.isNotEmpty()) champ.synergies else canonical.synergies,
                            coreItems = if (champ.coreItems.isNotEmpty()) champ.coreItems else canonical.coreItems,
                            situationalItems = if (champ.situationalItems.isNotEmpty()) champ.situationalItems else canonical.situationalItems,
                            isRanged = champ.isRanged,
                            isFrontline = champ.isFrontline
                        )
                    } else {
                        champ
                    }
                }
                val merged = (WildRiftRepository.champions.associateBy { it.id } + mappedChamps.associateBy { it.id }).values.toList()
                WildRiftRepository.champions = merged
            }

            if (validRunes.isNotEmpty()) {
                val canonicalMap = com.example.data.WildRiftSpellsAndRunes.runes.associateBy { it.id }
                val canonicalIds = canonicalMap.keys
                val filteredRunes = validRunes.filter { it.id.startsWith("rune_") || it.id in canonicalIds }.map { rune ->
                    val canonical = canonicalMap[rune.id]
                    if (canonical != null) {
                        rune.copy(
                            name = if (rune.name.isBlank()) canonical.name else rune.name,
                            description = if (rune.description.isBlank()) canonical.description else rune.description,
                            category = if (rune.category.isBlank()) canonical.category else rune.category,
                            iconUrl = if (rune.iconUrl.isBlank()) canonical.iconUrl else rune.iconUrl
                        )
                    } else {
                        rune
                    }
                }
                val merged = (com.example.data.WildRiftSpellsAndRunes.runes.associateBy { it.id } + filteredRunes.associateBy { it.id }).values.toList()
                WildRiftRepository.runes = merged
            }

            if (validSpells.isNotEmpty()) {
                val canonicalMap = com.example.data.WildRiftSpellsAndRunes.summonerSpells.associateBy { it.id }
                val canonicalIds = canonicalMap.keys
                val filteredSpells = validSpells.filter { it.id.startsWith("spell_") || it.id in canonicalIds }.map { spell ->
                    val canonical = canonicalMap[spell.id]
                    if (canonical != null) {
                        spell.copy(
                            name = if (spell.name.isBlank()) canonical.name else spell.name,
                            description = if (spell.description.isBlank()) canonical.description else spell.description,
                            cooldown = if (spell.cooldown.isBlank()) canonical.cooldown else spell.cooldown,
                            iconUrl = if (spell.iconUrl.isBlank()) canonical.iconUrl else spell.iconUrl
                        )
                    } else {
                        spell
                    }
                }
                val merged = (com.example.data.WildRiftSpellsAndRunes.summonerSpells.associateBy { it.id } + filteredSpells.associateBy { it.id }).values.toList()
                WildRiftRepository.summonerSpells = merged
            }

            WildRiftLocalCache.saveToLocalCache(
                context,
                WildRiftRepository.items,
                WildRiftRepository.champions,
                WildRiftRepository.runes,
                WildRiftRepository.summonerSpells,
                objectives = WildRiftRepository.mapObjectives,
                patchVersion = WildRiftRepository.CURRENT_PATCH_VERSION
            )

            Result.success("Sincronización completada. ${items.size} obj, ${champions.size} camp, ${runes.size} runas, ${spells.size} hechizos.")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncAllFromSupabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun seedAllDataToSupabase(
        onProgress: (current: Int, total: Int, message: String) -> Unit
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val allItems = WildRiftRepository.items
            val allChamps = WildRiftRepository.champions
            val allRunes = com.example.data.WildRiftSpellsAndRunes.runes
            val allSpells = WildRiftRepository.summonerSpells
            val total = allItems.size + allChamps.size + allRunes.size + allSpells.size
            var current = 0

            val itemDtos = allItems.map { WrItemDto.fromModel(it) }
            itemDtos.chunked(25).forEach { 
                postgrest.from(TABLE_ITEMS).upsert(it)
                current += it.size
                onProgress(current, total, "Subiendo objetos...")
            }

            val champDtos = allChamps.map { WrChampionDto.fromModel(it) }
            champDtos.chunked(20).forEach {
                postgrest.from(TABLE_CHAMPIONS).upsert(it)
                current += it.size
                onProgress(current, total, "Subiendo campeones...")
            }

            try { postgrest.from(TABLE_RUNES).delete { filter { neq("id", "invalid_placeholder") } } } catch(e: Exception) { }
            val runeDtos = allRunes.map { WrRuneDto.fromModel(it) }
            if (runeDtos.isNotEmpty()) {
                postgrest.from(TABLE_RUNES).upsert(runeDtos)
                current += runeDtos.size
                onProgress(current, total, "Subiendo runas...")
            }

            val spellDtos = allSpells.map { WrSpellDto.fromModel(it) }
            if (spellDtos.isNotEmpty()) {
                try {
                    postgrest.from(TABLE_SPELLS).delete { filter { neq("id", "invalid_placeholder") } }
                } catch(e: Exception) { }
                postgrest.from(TABLE_SPELLS).upsert(spellDtos)
                current += spellDtos.size
                onProgress(current, total, "Subiendo hechizos...")
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error seedAllDataToSupabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun forceReseedSpells(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val spellDtos = WildRiftRepository.summonerSpells.map { WrSpellDto.fromModel(it) }
            if (spellDtos.isNotEmpty()) {
                try {
                    postgrest.from(TABLE_SPELLS).delete { filter { neq("id", "invalid_placeholder") } }
                } catch(e: Exception) { }
                postgrest.from(TABLE_SPELLS).upsert(spellDtos)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchCurrentPatch(): Result<WrPatchDto?> = withContext(Dispatchers.IO) {
        try {
            val list = postgrest.from(TABLE_PATCHES).select().decodeList<WrPatchDto>()
            val current = list.find { it.id == "current" } ?: list.firstOrNull()
            Result.success(current)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun publishPatch(version: String, notes: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val patch = WrPatchDto("current", version, notes)
            postgrest.from(TABLE_PATCHES).upsert(patch)
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun fetchAllItems(): Result<List<com.example.model.WildRiftItem>> = withContext(Dispatchers.IO) {
        try {
            Result.success(postgrest.from(TABLE_ITEMS).select().decodeList<WrItemDto>().map { it.toModel() })
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun saveItem(item: com.example.model.WildRiftItem): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_ITEMS).upsert(WrItemDto.fromModel(item))
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun deleteItem(itemId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_ITEMS).delete { filter { eq("id", itemId) } }
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun fetchAllChampions(): Result<List<com.example.model.Champion>> = withContext(Dispatchers.IO) {
        try {
            Result.success(postgrest.from(TABLE_CHAMPIONS).select().decodeList<WrChampionDto>().map { it.toModel() })
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun saveChampion(champion: com.example.model.Champion): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_CHAMPIONS).upsert(WrChampionDto.fromModel(champion))
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun deleteChampion(championId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_CHAMPIONS).delete { filter { eq("id", championId) } }
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun saveAllChampionsToSupabase(champions: List<com.example.model.Champion>): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val dtos = champions.map { WrChampionDto.fromModel(it) }
            dtos.chunked(20).forEach { postgrest.from(TABLE_CHAMPIONS).upsert(it) }
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun fetchAllRunes(): Result<List<com.example.model.RuneItem>> = withContext(Dispatchers.IO) {
        try {
            Result.success(postgrest.from(TABLE_RUNES).select().decodeList<WrRuneDto>().map { it.toModel() })
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun saveRune(rune: com.example.model.RuneItem): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_RUNES).upsert(WrRuneDto.fromModel(rune))
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun deleteRune(runeId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_RUNES).delete { filter { eq("id", runeId) } }
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun fetchAllSpells(): Result<List<com.example.model.SummonerSpellItem>> = withContext(Dispatchers.IO) {
        try {
            Result.success(postgrest.from(TABLE_SPELLS).select().decodeList<WrSpellDto>().map { it.toModel() })
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun saveSpell(spell: com.example.model.SummonerSpellItem): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_SPELLS).upsert(WrSpellDto.fromModel(spell))
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun deleteSpell(spellId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            postgrest.from(TABLE_SPELLS).delete { filter { eq("id", spellId) } }
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun seedItemsToSupabase(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val allItems = WildRiftRepository.items
            val itemDtos = allItems.map { WrItemDto.fromModel(it) }
            itemDtos.chunked(25).forEach { postgrest.from(TABLE_ITEMS).upsert(it) }
            Result.success(itemDtos.size)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun seedChampionsToSupabase(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val allChamps = WildRiftRepository.champions
            val champDtos = allChamps.map { WrChampionDto.fromModel(it) }
            champDtos.chunked(20).forEach { postgrest.from(TABLE_CHAMPIONS).upsert(it) }
            Result.success(champDtos.size)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun seedRunesToSupabase(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val allRunes = com.example.data.WildRiftSpellsAndRunes.runes
            try { postgrest.from(TABLE_RUNES).delete { filter { neq("id", "invalid_placeholder") } } } catch(e: Exception) { }
            val runeDtos = allRunes.map { WrRuneDto.fromModel(it) }
            if (runeDtos.isNotEmpty()) {
                runeDtos.chunked(25).forEach { chunk ->
                    postgrest.from(TABLE_RUNES).upsert(chunk)
                }
            }
            Result.success(runeDtos.size)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun seedSpellsToSupabase(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val spellDtos = WildRiftRepository.summonerSpells.map { WrSpellDto.fromModel(it) }
            if (spellDtos.isNotEmpty()) {
                spellDtos.chunked(25).forEach { chunk ->
                    postgrest.from(TABLE_SPELLS).upsert(chunk)
                }
            }
            Result.success(spellDtos.size)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun diagnoseItemIcons(logToLogcat: Boolean = true): Result<SupabaseItemDiagnostics.SupabaseItemDiagnosticReport> {
        return SupabaseItemDiagnostics.diagnoseItemIconUrls(logToLogcat)
    }
}
