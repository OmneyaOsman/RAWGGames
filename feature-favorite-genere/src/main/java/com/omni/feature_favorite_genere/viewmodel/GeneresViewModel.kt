package com.omni.feature_favorite_genere.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omni.domain.entities.generes.GenereEntity
import com.omni.domain.usecases.GetGeneresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GeneresViewModel(private val useCase: GetGeneresUseCase) : ViewModel() {
    private val _result: MutableStateFlow<List<GenereEntity>?> = MutableStateFlow(null)
    val result = _result.asStateFlow()

    init {
        viewModelScope.launch {
            _result.value = useCase()
        }
    }

}