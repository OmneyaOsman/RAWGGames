package com.omni.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Game")
data class GameModel(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double,
)