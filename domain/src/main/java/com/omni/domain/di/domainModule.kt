package com.omni.domain.di

import com.omni.domain.repository.GenerePreferencesSource
import com.omni.domain.repository.GeneresRepository
import com.omni.domain.repository.LocalDatabaseRepository
import com.omni.domain.repository.RAWGGamesRepository
import com.omni.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    single { provideGamesListUseCase(get()) }
    single { provideGetGeneresUseCase(get()) }
    single { provideSetFavoriteGenereUseCase(get()) }
    single { provideGetFavoriteGenereUseCase(get()) }
    single { provideSearchUseCase(get()) }
}

fun provideGamesListUseCase(repository: RAWGGamesRepository): GetGamesUseCase =
    GetGamesUseCase(repository)

fun provideGetGeneresUseCase(repository: GeneresRepository): GetGeneresUseCase =
    GetGeneresUseCase(repository)

fun provideSetFavoriteGenereUseCase(repository: GenerePreferencesSource): SetFavoriteGenereUseCase =
    SetFavoriteGenereUseCase(repository)

fun provideGetFavoriteGenereUseCase(repository: GenerePreferencesSource): GetFavoriteGenereUseCase =
    GetFavoriteGenereUseCase(repository)

fun provideSearchUseCase(repository: LocalDatabaseRepository): SearchUseCase =
    SearchUseCase(repository)

