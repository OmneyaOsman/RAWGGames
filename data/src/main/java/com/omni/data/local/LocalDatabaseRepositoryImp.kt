package com.omni.data.local

import com.omni.data.local.db.RawgGamesDatabase
import com.omni.domain.repository.LocalDatabaseRepository

class LocalDatabaseRepositoryImp(
    val db: RawgGamesDatabase,
) : LocalDatabaseRepository {

    private val remoteKeyDao = db.remoteKeysDao()
    private val gamesDao = db.gamesDao()

    override suspend fun clearALl(){
        remoteKeyDao.delete()
        gamesDao.deleteGames()
    }
}