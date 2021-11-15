package com.omni.feature_games_list.di

import com.omni.domain.usecases.GetFavoriteGenereUseCase
import com.omni.domain.usecases.GetGamesUseCase
import com.omni.feature_games_list.presentation.viewmodel.GamesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gamesListFeatureModule = module {
    viewModel {
        provideGamesListViewModel(get(),get())
    }
}

fun provideGamesListViewModel(
    useCase: GetGamesUseCase,
    getFavoriteGenereUseCase: GetFavoriteGenereUseCase
) = GamesListViewModel(useCase , getFavoriteGenereUseCase)



