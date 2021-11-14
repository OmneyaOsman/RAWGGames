package com.omni.data.model.generes


import com.google.gson.annotations.SerializedName

data class GenereModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_background")
    val imageBackground: String?,
    @SerializedName("name")
    val name: String?,
)