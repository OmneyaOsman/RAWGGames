package com.omni.domain.usecases

import com.omni.domain.repository.GenerePreferencesSource

class SetFavoriteGenereUseCase(private val generePreferencesSource: GenerePreferencesSource) {
    suspend operator fun invoke(genereId: String?) = generePreferencesSource.setGenere(genereId)
}