package com.omni.domain.repository

import com.omni.domain.entities.GameEntity


interface LocalDatabaseRepository {
    suspend fun clearALl()
    suspend fun searchGames(searchQuery :String): List<GameEntity>
}