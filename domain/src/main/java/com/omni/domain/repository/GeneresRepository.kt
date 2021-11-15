package com.omni.domain.repository

import com.omni.domain.entities.generes.GenereEntity

interface GeneresRepository {
    suspend fun getGeneres(): List<GenereEntity>

}