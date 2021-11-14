package com.omni.domain.usecases

import com.omni.domain.repository.GeneresRepository

class GetGeneresUseCase(private val repository: GeneresRepository) {
    suspend operator fun invoke() = repository.getGeneres()
}