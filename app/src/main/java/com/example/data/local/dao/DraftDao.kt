package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.SavedDraftEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftDao {

    @Query("SELECT * FROM saved_drafts ORDER BY timestamp DESC")
    fun getAllDrafts(): Flow<List<SavedDraftEntity>>

    @Query("SELECT * FROM saved_drafts WHERE accountProfileId = :profileId ORDER BY timestamp DESC")
    fun getDraftsByProfile(profileId: String): Flow<List<SavedDraftEntity>>

    @Query("SELECT * FROM saved_drafts WHERE id = :id")
    suspend fun getDraftById(id: Long): SavedDraftEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDraft(draft: SavedDraftEntity): Long

    @Update
    suspend fun updateDraft(draft: SavedDraftEntity)

    @Query("UPDATE saved_drafts SET matchResult = :result WHERE id = :id")
    suspend fun updateMatchResult(id: Long, result: String)

    @Query("UPDATE saved_drafts SET notes = :notes WHERE id = :id")
    suspend fun updateNotes(id: Long, notes: String)

    @Query("UPDATE saved_drafts SET accountProfileId = :profileId, accountProfileName = :profileName WHERE id = :id")
    suspend fun updateAccountProfile(id: Long, profileId: String, profileName: String)

    @Query("DELETE FROM saved_drafts WHERE id = :id")
    suspend fun deleteDraftById(id: Long)

    @Query("DELETE FROM saved_drafts WHERE accountProfileId = :profileId")
    suspend fun clearDraftsByProfile(profileId: String)

    @Query("DELETE FROM saved_drafts")
    suspend fun clearAllDrafts()
}
