package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.FavoriteBuildEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBuildsDao {
    @Query("SELECT * FROM favorite_builds ORDER BY timestamp DESC")
    fun getAllFavorites(): Flow<List<FavoriteBuildEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteBuildEntity)

    @Query("DELETE FROM favorite_builds WHERE buildId = :buildId")
    suspend fun deleteFavorite(buildId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_builds WHERE buildId = :buildId)")
    fun isFavorite(buildId: String): Flow<Boolean>
}
