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
            val allRunes = WildRiftRepository.runes
            val runeDtos = allRunes.map { WrRuneDto.fromModel(it) }
            if (runeDtos.isNotEmpty()) {
                postgrest.from(TABLE_RUNES).upsert(runeDtos)
            }
            Result.success(runeDtos.size)
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun seedSpellsToSupabase(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val spellDtos = WildRiftRepository.summonerSpells.map { WrSpellDto.fromModel(it) }
            if (spellDtos.isNotEmpty()) {
                try {
                    postgrest.from(TABLE_SPELLS).delete { filter { neq("id", "invalid_placeholder") } }
                } catch(e: Exception) { }
                postgrest.from(TABLE_SPELLS).upsert(spellDtos)
            }
            Result.success(spellDtos.size)
        } catch (e: Exception) { Result.failure(e) }
    }
