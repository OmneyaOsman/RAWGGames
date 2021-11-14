package com.omni.domain.di

import com.omni.domain.repository.RAWGGamesRepository
import com.omni.domain.usecases.GetGamesUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        provideGamesListUseCase(get())
    }
}

fun provideGamesListUseCase( repository: RAWGGamesRepository) = GetGamesUseCase(repository)

