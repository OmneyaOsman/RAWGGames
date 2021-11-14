package com.omni.core.base

interface Mapper<in FROM, out TO> {
    fun mapToDomainModel(from: FROM): TO
}