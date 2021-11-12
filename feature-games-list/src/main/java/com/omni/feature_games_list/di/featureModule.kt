package com.omni.feature_games_list.di

import com.omni.feature_games_list.data.remote.GamesAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val gamesListFeatureModule = module {
    factory { provideGamesAPI(get()) }
}

fun provideGamesAPI(retrofit: Retrofit): GamesAPI = retrofit.create(GamesAPI::class.java)