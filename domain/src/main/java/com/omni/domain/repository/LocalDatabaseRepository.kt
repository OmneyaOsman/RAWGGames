package com.omni.domain.repository


interface LocalDatabaseRepository {
    suspend fun clearALl()
}