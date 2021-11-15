package com.omni.domain.usecases

import com.omni.domain.repository.GenerePreferencesSource

class GetFavoriteGenereUseCase(private val generePreferencesSource: GenerePreferencesSource) {
    operator fun invoke() = generePreferencesSource.getGenere()
}