package com.omni.domain.usecases

import com.omni.domain.repository.RAWGGamesRepository

class GetGamesUseCase(private val repository: RAWGGamesRepository) {
    operator fun invoke(genere:String) = repository.gamesOfGenere(genere, 10)
}