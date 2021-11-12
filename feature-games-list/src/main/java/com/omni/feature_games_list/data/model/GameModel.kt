package com.omni.feature_games_list.data.model


import com.google.gson.annotations.SerializedName

data class GameModel(
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("dominant_color")
    val dominantColor: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("rating_top")
    val ratingTop: Int?,
    @SerializedName("saturated_color")
    val saturatedColor: String?,
)