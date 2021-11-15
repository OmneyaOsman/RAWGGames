package com.omni.domain.di

import com.omni.domain.repository.GenerePreferencesSource
import com.omni.domain.repository.GeneresRepository
import com.omni.domain.repository.RAWGGamesRepository
import com.omni.domain.usecases.GetFavoriteGenereUseCase
import com.omni.domain.usecases.GetGamesUseCase
import com.omni.domain.usecases.GetGeneresUseCase
import com.omni.domain.usecases.SetFavoriteGenereUseCase
import org.koin.dsl.module

val domainModule = module {
    single { provideGamesListUseCase(get()) }
    single { provideGetGeneresUseCase(get()) }
    single { provideSetFavoriteGenereUseCase(get()) }
    single { provideGetFavoriteGenereUseCase(get()) }
}

fun provideGamesListUseCase(repository: RAWGGamesRepository): GetGamesUseCase =
    GetGamesUseCase(repository)

fun provideGetGeneresUseCase(repository: GeneresRepository): GetGeneresUseCase =
    GetGeneresUseCase(repository)

fun provideSetFavoriteGenereUseCase(repository: GenerePreferencesSource): SetFavoriteGenereUseCase =
    SetFavoriteGenereUseCase(repository)

fun provideGetFavoriteGenereUseCase(repository: GenerePreferencesSource): GetFavoriteGenereUseCase =
    GetFavoriteGenereUseCase(repository)

