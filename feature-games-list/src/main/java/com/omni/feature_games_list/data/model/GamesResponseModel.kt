package com.omni.feature_games_list.data.model


import com.google.gson.annotations.SerializedName

data class GamesResponseModel(
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<GameModel>?,
)