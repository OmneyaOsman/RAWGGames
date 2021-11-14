package com.omni.domain.usecases

import com.omni.domain.repository.RAWGGamesRepository

class GetGamesUseCase(private val repository: RAWGGamesRepository) {
    operator fun invoke() = repository.gamesOfGenere("4", 10)
}