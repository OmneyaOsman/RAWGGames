package com.omni.data.mapper

import com.omni.core.base.Mapper
import com.omni.data.model.GameModel
import com.omni.domain.entities.GameEntity

class GameModelToGameEntityMapper : Mapper<GameModel, GameEntity> {
    override fun mapToDomainModel(from: GameModel): GameEntity  =
        GameEntity(
            from.id , from.backgroundImage , from.name , from.rating
        )
}