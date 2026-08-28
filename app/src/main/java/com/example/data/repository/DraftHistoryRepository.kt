package com.example.data.repository

import android.content.Context
import com.example.data.WildRiftRepository
import com.example.data.local.AppDatabase
import com.example.data.local.entity.SavedDraftEntity
import com.example.data.local.entity.SavedDraftSlotData
import com.example.model.DraftAnalysisResult
import com.example.model.DraftSlot
import com.example.model.LaneRole
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object DraftHistoryRepository {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    fun getAllDrafts(context: Context): Flow<List<SavedDraftEntity>> {
        return AppDatabase.getDatabase(context).draftDao().getAllDrafts()
    }

    suspend fun getDraftById(context: Context, id: Long): SavedDraftEntity? {
        return AppDatabase.getDatabase(context).draftDao().getDraftById(id)
    }

    suspend fun saveDraft(
        context: Context,
        myRole: LaneRole,
        isFirstPick: Boolean,
        allies: List<DraftSlot>,
        enemies: List<DraftSlot>,
        analysis: DraftAnalysisResult,
        title: String? = null,
        notes: String = ""
    ): Long {
        val allyDataList = allies.map {
            SavedDraftSlotData(
                championId = it.champion.id,
                championName = it.champion.name,
                role = it.assignedRole.name,
                avatarUrl = it.champion.avatarUrl
            )
        }

        val enemyDataList = enemies.map {
            SavedDraftSlotData(
                championId = it.champion.id,
                championName = it.champion.name,
                role = it.assignedRole.name,
                avatarUrl = it.champion.avatarUrl
            )
        }

        val myChampion = allies.find { it.assignedRole == myRole }?.champion
        val enemyLaneOpponent = enemies.find { it.assignedRole == myRole }?.champion

        val bestPick = analysis.bestOverallPick ?: analysis.recommendations.firstOrNull()
        val estimatedWr = if (myChampion != null) {
            val eval = WildRiftRepository.evaluateChampion(
                champ = myChampion,
                myRole = myRole,
                allies = allies.map { it.champion },
                enemies = enemies.map { it.champion },
                enemyLaneOpponent = enemyLaneOpponent
            )
            eval.estimatedWinrate
        } else {
            bestPick?.estimatedWinrate ?: 50.0
        }

        val defaultTitle = if (myChampion != null && enemyLaneOpponent != null) {
            "${myChampion.name} vs ${enemyLaneOpponent.name} (${myRole.displayName})"
        } else if (myChampion != null) {
            "${myChampion.name} (${myRole.displayName})"
        } else {
            "Draft en ${myRole.displayName}"
        }

        val entity = SavedDraftEntity(
            title = title?.ifBlank { defaultTitle } ?: defaultTitle,
            timestamp = System.currentTimeMillis(),
            userRole = myRole.name,
            isFirstPick = isFirstPick,
            allyPicksJson = json.encodeToString(allyDataList),
            enemyPicksJson = json.encodeToString(enemyDataList),
            myChampionId = myChampion?.id ?: "",
            myChampionName = myChampion?.name ?: "",
            enemyLaneOpponentName = enemyLaneOpponent?.name ?: "",
            estimatedWinrate = estimatedWr,
            matchResult = "PENDING",
            allyDamagePhysical = analysis.allyPhysicalDamagePercent,
            allyDamageMagic = analysis.allyMagicDamagePercent,
            allyDamageTrue = analysis.allyTrueDamagePercent,
            enemyDamagePhysical = analysis.physicalDamagePercent,
            enemyDamageMagic = analysis.magicDamagePercent,
            enemyDamageTrue = analysis.trueDamagePercent,
            winConditionNotes = analysis.directMatchupWarning ?: analysis.allyCompositionWarning ?: "",
            directMatchupWarning = analysis.directMatchupWarning ?: "",
            notes = notes
        )

        return AppDatabase.getDatabase(context).draftDao().insertDraft(entity)
    }

    suspend fun updateMatchResult(context: Context, id: Long, result: String) {
        AppDatabase.getDatabase(context).draftDao().updateMatchResult(id, result)
    }

    suspend fun updateNotes(context: Context, id: Long, notes: String) {
        AppDatabase.getDatabase(context).draftDao().updateNotes(id, notes)
    }

    suspend fun deleteDraft(context: Context, id: Long) {
        AppDatabase.getDatabase(context).draftDao().deleteDraftById(id)
    }

    suspend fun clearAllDrafts(context: Context) {
        AppDatabase.getDatabase(context).draftDao().clearAllDrafts()
    }

    fun parseDraftSlots(jsonString: String): List<DraftSlot> {
        return try {
            val dataList = json.decodeFromString<List<SavedDraftSlotData>>(jsonString)
            dataList.mapNotNull { data ->
                val champ = WildRiftRepository.getChampionById(data.championId)
                    ?: WildRiftRepository.champions.find { it.name.equals(data.championName, ignoreCase = true) }
                val role = try {
                    LaneRole.valueOf(data.role)
                } catch (_: Exception) {
                    champ?.primaryRole ?: LaneRole.MID
                }
                if (champ != null) {
                    DraftSlot(champ, role)
                } else null
            }
        } catch (_: Exception) {
            emptyList()
        }
    }
}
