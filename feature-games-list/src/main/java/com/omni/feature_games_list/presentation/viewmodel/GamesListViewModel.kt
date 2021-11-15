package com.omni.feature_games_list.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omni.domain.entities.GameEntity
import com.omni.domain.usecases.GetFavoriteGenereUseCase
import com.omni.domain.usecases.GetGamesUseCase
import com.omni.domain.usecases.SetFavoriteGenereUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class GamesListViewModel(
    private val useCase: GetGamesUseCase,
    private val getFavoriteGenereUseCase: GetFavoriteGenereUseCase,
) : ViewModel() {

    private var resultFlow: Flow<PagingData<GameEntity>>? = null
    val isEmptyListEvent = MutableSharedFlow<Boolean>()
    val dataLoading = MutableLiveData<Boolean>()

    val genereState = MutableStateFlow("")

    init {
        getGenere()
    }

    private fun getGenere() {

        viewModelScope.launch {
            getFavoriteGenereUseCase().collect {
                genereState.value = it
                Timber.e("Games$it")
            }
        }
    }

    suspend fun getGames(genere: String): Flow<PagingData<GameEntity>> {
        val newResult: Flow<PagingData<GameEntity>> = useCase(genere)
            .cachedIn(viewModelScope)
        resultFlow = newResult
        return newResult
    }
}