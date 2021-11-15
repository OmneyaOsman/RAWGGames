package com.omni.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omni.data.local.db.dao.GameDao
import com.omni.data.local.db.dao.GamesRemoteKeyDao
import com.omni.data.model.GameModel
import com.omni.data.model.GameRemoteKeysModel

@Database(
    entities = [GameModel::class, GameRemoteKeysModel::class],
    version = 1,
    exportSchema = false
)
abstract class RawgGamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GameDao
    abstract fun remoteKeysDao(): GamesRemoteKeyDao
}