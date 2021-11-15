package com.omni.data.repository

import com.google.gson.Gson
import com.omni.core.wrapper.ResponseWrapper
import com.omni.core.wrapper.ResponseWrapperHandler
import com.omni.data.mapper.generes.GenereModelToGenereEntityMapper
import com.omni.data.remote.api.GeneresAPI
import com.omni.domain.entities.generes.GenereEntity
import com.omni.domain.repository.GeneresRepository

class GeneresRepositoryImp(
    private val generesAPI: GeneresAPI,
    private val mapper: GenereModelToGenereEntityMapper,
    private val gson: Gson
) : GeneresRepository {
//    override suspend fun getGeneres(): List<GenereEntity> =
//        generesAPI.getGeneres().results?.map {
//            mapper.mapToDomainModel(it)
//        } ?: emptyList()

    override suspend fun getGeneres(): ResponseWrapper =
        ResponseWrapperHandler.handleListResponse(
            serviceMethod = { generesAPI.getGeneres() }, mapper, gson = gson
        )
}