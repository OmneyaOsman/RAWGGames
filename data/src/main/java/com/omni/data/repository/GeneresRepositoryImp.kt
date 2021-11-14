package com.omni.data.repository

import com.omni.data.mapper.generes.GenereModelToGenereEntityMapper
import com.omni.data.remote.api.GeneresAPI
import com.omni.domain.entities.generes.GenereEntity
import com.omni.domain.repository.GeneresRepository

class GeneresRepositoryImp(
    private val generesAPI: GeneresAPI,
    private val mapper: GenereModelToGenereEntityMapper
) : GeneresRepository {
    override suspend fun getGeneres(): List<GenereEntity> =
        generesAPI.getGeneres().results?.map {
            mapper.mapToDomainModel(it)
        } ?: emptyList()
}