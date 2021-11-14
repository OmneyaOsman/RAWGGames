package com.omni.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omni.data.model.GameRemoteKeysModel

@Dao
interface GamesRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keys: GameRemoteKeysModel)

    @Query("SELECT * FROM remote_keys")
    suspend fun getGameRemoteKey(): GameRemoteKeysModel

    @Query("DELETE FROM remote_keys")
    suspend fun delete()
}