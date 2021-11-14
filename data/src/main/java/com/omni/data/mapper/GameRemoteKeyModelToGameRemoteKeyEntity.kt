package com.omni.data.mapper

import com.omni.core.base.Mapper
import com.omni.data.model.GameRemoteKeysModel
import com.omni.domain.entities.GameRemoteKeysEntity

class GameRemoteKeyModelToGameRemoteKeyEntity : Mapper<GameRemoteKeysModel, GameRemoteKeysEntity> {
    override fun mapToDomainModel(from: GameRemoteKeysModel): GameRemoteKeysEntity =
        GameRemoteKeysEntity(
            from.gener, from.nextPageKey
        )
}