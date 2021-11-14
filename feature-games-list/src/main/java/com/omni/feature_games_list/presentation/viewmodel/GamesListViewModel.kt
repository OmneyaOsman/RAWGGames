package com.omni.feature_games_list.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omni.domain.entities.GameEntity
import com.omni.domain.usecases.GetGamesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class GamesListViewModel(private val useCase: GetGamesUseCase) : ViewModel() {

    private var resultFlow: Flow<PagingData<GameEntity>>? = null
    val isEmptyListEvent = MutableSharedFlow<Boolean>()
    val dataLoading = MutableLiveData<Boolean>()

    fun getGames(): Flow<PagingData<GameEntity>> {
        val newResult: Flow<PagingData<GameEntity>> = useCase()
            .cachedIn(viewModelScope)
        resultFlow = newResult
        return newResult
    }
}