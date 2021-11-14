package com.omni.data.remote.api

import com.omni.data.model.GamesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesAPI {
    @GET("games")
    suspend fun getGamesList(
        @Query("geners") geners: String,
        @Query("page") nextPage: String?,
        @Query("page_size") limit: Int,
    ): GamesResponseModel
}