package com.omni.feature_splash.di

import com.omni.domain.usecases.GetFavoriteGenereUseCase
import com.omni.feature_splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashFeatureModule  = module {
    viewModel { provideSplashViewModel(get()) }
}

fun provideSplashViewModel(useCase: GetFavoriteGenereUseCase) = SplashViewModel(useCase)