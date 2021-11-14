package com.omni.feature_favorite_genere.di

import com.omni.domain.usecases.GetGeneresUseCase
import com.omni.feature_favorite_genere.viewmodel.GeneresViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteGenereFeatureModule = module {
    viewModel {
        provideGeneresViewModel(get())
    }
}

fun provideGeneresViewModel(useCase: GetGeneresUseCase) = GeneresViewModel(useCase)



