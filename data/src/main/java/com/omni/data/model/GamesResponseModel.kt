package com.omni.data.model


import com.google.gson.annotations.SerializedName

data class GamesResponseModel(
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<GameModel>?,
)