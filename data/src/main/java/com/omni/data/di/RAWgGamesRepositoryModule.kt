package com.omni.data.di

import androidx.paging.ExperimentalPagingApi
import com.omni.data.local.db.RawgGamesDatabase
import com.omni.data.local.paging.PagedKeyRemoteMediator
import com.omni.data.mapper.GameModelToGameEntityMapper
import com.omni.data.remote.api.GamesAPI
import com.omni.data.repository.RAWgGamesRepositoryImp
import com.omni.domain.repository.RAWGGamesRepository
import org.koin.dsl.module

val RAWgGamesRepositoryModule = module {
    single { provideGameModelToGameEntityMapper() }
    single { provideRAWgGamesRepositoryImp(get(), get(), get()) }
}

fun provideGameModelToGameEntityMapper(): GameModelToGameEntityMapper =
    GameModelToGameEntityMapper()


fun provideRAWgGamesRepositoryImp(
    db: RawgGamesDatabase,
    api: GamesAPI, mapper: GameModelToGameEntityMapper
): RAWGGamesRepository = RAWgGamesRepositoryImp(db, api, mapper)