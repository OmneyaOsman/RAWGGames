package com.omni.domain.repository

import com.omni.core.wrapper.ResponseWrapper
import com.omni.domain.entities.generes.GenereEntity

interface GeneresRepository {
    suspend fun getGeneres(): ResponseWrapper

}