package com.omni.featur_search.di

import com.omni.domain.usecases.SearchUseCase
import com.omni.featur_search.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchFeatureModule = module {
    viewModel { provideSearchViewModel(get()) }
}

fun provideSearchViewModel(useCase :SearchUseCase) = SearchViewModel(useCase)