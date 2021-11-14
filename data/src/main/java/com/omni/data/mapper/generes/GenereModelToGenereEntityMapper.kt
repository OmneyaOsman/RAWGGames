package com.omni.data.mapper.generes

import com.omni.core.base.Mapper
import com.omni.data.model.generes.GenereModel
import com.omni.domain.entities.generes.GenereEntity

class GenereModelToGenereEntityMapper : Mapper<GenereModel, GenereEntity> {
    override fun mapToDomainModel(from: GenereModel): GenereEntity = GenereEntity(
        id = from.id, from.imageBackground, from.name
    )
}