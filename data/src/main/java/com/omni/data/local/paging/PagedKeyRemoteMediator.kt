package com.omni.data.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.omni.data.local.db.RawgGamesDatabase
import com.omni.data.model.GameModel
import com.omni.data.model.GameRemoteKeysModel
import com.omni.data.model.GamesResponseModel
import com.omni.data.remote.api.GamesAPI
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PagedKeyRemoteMediator(
    private val db: RawgGamesDatabase,
    private val gamesAPI: GamesAPI,
    private val gener: String = "4" //todo get gener from datastore
) : RemoteMediator<Int, GameModel>() {

    private val gamesDao = db.gamesDao()
    private val remoteKeyDao = db.remoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameModel>
    ): MediatorResult {
        try {
            // Get the closest item from PagingState that we want to load data around.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    // Query DB for SubredditRemoteKey for the subreddit.
                    // SubredditRemoteKey is a wrapper object we use to keep track of page keys we
                    // receive from the Reddit API to fetch the next or previous page.
                    val remoteKey = db.withTransaction {
                        remoteKeyDao.getGameRemoteKey()
                    }

                    // We must explicitly check if the page key is null when appending, since the
                    // Reddit API informs the end of the list by returning null for page key, but
                    // passing a null key to Reddit API will fetch the initial page.
                    if (remoteKey.nextPageKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextPageKey
                }
            }

            val data: GamesResponseModel = gamesAPI.getGamesList(
                geners = gener,
                nextPage = loadKey,
                limit = when (loadType) {
                    LoadType.REFRESH -> state.config.initialLoadSize
                    else -> state.config.pageSize
                }
            )

            val items: List<GameModel> = data.results ?: emptyList()


            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    gamesDao.deleteGames()
                    remoteKeyDao.delete()
                }

                remoteKeyDao.insert(GameRemoteKeysModel(gener, data.next))
                gamesDao.insertAll(items)
            }

            return MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}