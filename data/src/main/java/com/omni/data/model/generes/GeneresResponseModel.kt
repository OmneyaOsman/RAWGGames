package com.omni.data.model.generes


import com.google.gson.annotations.SerializedName

data class GeneresResponseModel(
    @SerializedName("results")
    val results: List<GenereModel>?
)