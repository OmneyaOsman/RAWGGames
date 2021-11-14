package com.omni.data.repository

import androidx.paging.*
import com.omni.data.local.db.RawgGamesDatabase
import com.omni.data.local.paging.PagedKeyRemoteMediator
import com.omni.data.mapper.GameModelToGameEntityMapper
import com.omni.data.model.GameModel
import com.omni.data.remote.api.GamesAPI
import com.omni.domain.entities.GameEntity
import com.omni.domain.repository.RAWGGamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class RAWgGamesRepositoryImp(
    val db: RawgGamesDatabase,
    private val gamesAPI: GamesAPI,
    private val mapper: GameModelToGameEntityMapper
) : RAWGGamesRepository {

    @OptIn(ExperimentalPagingApi::class)
    fun getGames(genere: String, pageSize: Int): Flow<PagingData<GameModel>> = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = PagedKeyRemoteMediator(db, gamesAPI, genere)
    ) {
        db.gamesDao().getGamesPagingList()
    }.flow

    override fun gamesOfGenere(genere: String, pageSize: Int): Flow<PagingData<GameEntity>> =
        getGames(genere, pageSize).mapLatest { pagingData ->
            pagingData.map { model ->
                mapper.mapToDomainModel(model)
            }
        }
}