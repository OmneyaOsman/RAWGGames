package com.omni.data.remote.api

import com.omni.data.model.generes.GeneresResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface GeneresAPI {

    @GET("genres")
    suspend fun getGeneres(): Response<String>
}