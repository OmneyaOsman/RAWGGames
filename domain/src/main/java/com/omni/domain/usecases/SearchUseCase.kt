package com.omni.domain.usecases

import com.omni.domain.repository.LocalDatabaseRepository

class SearchUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {
    suspend operator fun invoke(query: String) = localDatabaseRepository.searchGames(query)
}