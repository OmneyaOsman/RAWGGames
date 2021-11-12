package com.omni.feature_games_list.data.remote

import com.omni.feature_games_list.data.model.GamesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesAPI {
    @GET("games")
    suspend fun getGamesList(@Query("geners") geners: String): GamesResponseModel
}