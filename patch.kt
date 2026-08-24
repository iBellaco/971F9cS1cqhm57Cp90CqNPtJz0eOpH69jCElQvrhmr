    suspend fun forceReseedSpells(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val allSpells = WildRiftRepository.summonerSpells
            val spellDtos = allSpells.map { WrSpellDto.fromModel(it) }
            if (spellDtos.isNotEmpty()) {
                try {
                    postgrest.from(TABLE_SPELLS).delete { filter { isNotNull("id") } }
                } catch (e: Exception) {
                    Log.w(TAG, "No se pudieron borrar hechizos antiguos, continuando...")
                }
                postgrest.from(TABLE_SPELLS).upsert(spellDtos)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error en forceReseedSpells: ${e.message}", e)
            Result.failure(e)
        }
    }
}
