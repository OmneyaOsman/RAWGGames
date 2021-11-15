package com.omni.featur_search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omni.domain.entities.GameEntity
import com.omni.domain.usecases.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val useCaseSearchUseCase: SearchUseCase) : ViewModel() {

    private val _searchResult = MutableStateFlow<List<GameEntity>?>(null)
    val searchResult = _searchResult.asStateFlow()

    val noSearchResultFoundVisibility = MutableLiveData(false)

     fun fetchGamesByName(query: String) {
         viewModelScope.launch {
             val list = useCaseSearchUseCase(query)
             noSearchResultFoundVisibility.value = list.isNullOrEmpty()
             _searchResult.value = list
         }
    }

}