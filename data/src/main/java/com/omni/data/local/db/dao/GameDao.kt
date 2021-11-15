package com.omni.data.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omni.data.model.GameModel

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( games: List<GameModel>)

    @Query("SELECT * FROM game")
    fun getGamesPagingList(): PagingSource<Int, GameModel>

    @Query("SELECT * FROM game")
    suspend fun getGames(): List<GameModel>

    @Query("DELETE FROM game")
    suspend fun deleteGames()

}