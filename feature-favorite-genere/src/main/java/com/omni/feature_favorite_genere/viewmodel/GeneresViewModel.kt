package com.omni.feature_favorite_genere.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omni.domain.entities.generes.GenereEntity
import com.omni.domain.repository.LocalDatabaseRepository
import com.omni.domain.usecases.GetGeneresUseCase
import com.omni.domain.usecases.SetFavoriteGenereUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GeneresViewModel(
    private val useCase: GetGeneresUseCase,
    private val setFavoriteGenereUseCase: SetFavoriteGenereUseCase,
    private val localDatabaseRepository: LocalDatabaseRepository
) : ViewModel() {
    private val _result: MutableStateFlow<List<GenereEntity>?> = MutableStateFlow(null)
    val result = _result.asStateFlow()

    private val _navigateToGames: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0)
    val navigateToGames = _navigateToGames.asSharedFlow()

    init {
        viewModelScope.launch {
            _result.value = useCase()
        }
    }

    fun setFavoriteGenere(genere: String) {
        viewModelScope.launch {
            setFavoriteGenereUseCase(genere).also {
                localDatabaseRepository.clearALl()
                _navigateToGames.emit(Unit)
            }
        }
    }

}