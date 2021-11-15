package com.omni.data.di

import android.content.Context
import androidx.room.Room
import com.omni.data.local.LocalDatabaseRepositoryImp
import com.omni.data.local.db.dao.GameDao
import com.omni.data.local.db.dao.GamesRemoteKeyDao
import com.omni.data.local.db.RawgGamesDatabase
import com.omni.domain.repository.LocalDatabaseRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single { provideRawgGamesDatabase(androidContext()) }
    factory { get<RawgGamesDatabase>().gamesDao() } bind GameDao::class
    factory { get<RawgGamesDatabase>().remoteKeysDao() } bind GamesRemoteKeyDao::class
    single { provideLocalDatabaseRepository(get()) }
}

fun provideRawgGamesDatabase(context: Context): RawgGamesDatabase =
    Room.databaseBuilder(
        context, RawgGamesDatabase::class.java, "rawg.db"
    ).build()

fun provideLocalDatabaseRepository(db: RawgGamesDatabase): LocalDatabaseRepository = LocalDatabaseRepositoryImp(db)