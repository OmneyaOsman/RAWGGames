package com.omni.domain.di

import com.omni.domain.repository.GeneresRepository
import com.omni.domain.repository.RAWGGamesRepository
import com.omni.domain.usecases.GetGamesUseCase
import com.omni.domain.usecases.GetGeneresUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        provideGamesListUseCase(get())
        provideGetGeneresUseCase(get())
    }
}

fun provideGamesListUseCase( repository: RAWGGamesRepository) = GetGamesUseCase(repository)
fun provideGetGeneresUseCase( repository: GeneresRepository) = GetGeneresUseCase(repository)

