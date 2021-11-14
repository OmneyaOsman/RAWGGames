package com.omni.domain.repository

import androidx.paging.PagingData
import com.omni.domain.entities.GameEntity
import kotlinx.coroutines.flow.Flow

interface RAWGGamesRepository {
    fun gamesOfGenere(genere: String, pageSize: Int): Flow<PagingData<GameEntity>>
}