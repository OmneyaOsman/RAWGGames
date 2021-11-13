package com.omni.data.model


import com.google.gson.annotations.SerializedName
import com.omni.data.model.GameModel

data class GamesResponseModel(
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<GameModel>?,
)