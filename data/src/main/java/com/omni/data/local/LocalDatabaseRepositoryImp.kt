package com.omni.data.local

import com.omni.data.local.db.RawgGamesDatabase
import com.omni.data.mapper.GameModelToGameEntityMapper
import com.omni.domain.entities.GameEntity
import com.omni.domain.repository.LocalDatabaseRepository
import timber.log.Timber

class LocalDatabaseRepositoryImp(
    val db: RawgGamesDatabase,
    private val mapper: GameModelToGameEntityMapper
) : LocalDatabaseRepository {

    private val remoteKeyDao = db.remoteKeysDao()
    private val gamesDao = db.gamesDao()

    override suspend fun clearALl() {
        remoteKeyDao.delete()
        gamesDao.deleteGames()
    }

    override suspend fun searchGames(searchQuery: String): List<GameEntity> {
        val list = gamesDao.searchGames(searchQuery)
        Timber.d(list.toString())
        return gamesDao.searchGames(searchQuery).map {
            mapper.mapToDomainModel(it)
        }
    }
}