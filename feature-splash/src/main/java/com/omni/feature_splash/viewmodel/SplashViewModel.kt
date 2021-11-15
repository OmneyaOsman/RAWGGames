package com.omni.feature_splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omni.domain.usecases.GetFavoriteGenereUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(private val getFavoriteGenereUseCase: GetFavoriteGenereUseCase) :
    ViewModel() {
    fun getGenere() = getFavoriteGenereUseCase()

}