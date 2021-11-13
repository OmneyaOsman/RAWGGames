package com.omni.data.di

import android.content.Context
import androidx.room.Room
import com.omni.data.local.db.GameDao
import com.omni.data.local.db.GamesRemoteKeyDao
import com.omni.data.local.db.RawgGamesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val GamesDatabaseModule = module {
    single { provideRawgGamesDatabase(androidContext()) }
    factory { get<RawgGamesDatabase>().gamesDao() } bind GameDao::class
    factory { get<RawgGamesDatabase>().remoteKeysDao() } bind GamesRemoteKeyDao::class
}

fun provideRawgGamesDatabase(context: Context): RawgGamesDatabase =
    Room.databaseBuilder(
        context, RawgGamesDatabase::class.java, "rawg.db"
    ).fallbackToDestructiveMigration().build()