package com.wesam.marvel.model.network.response.character


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("offset")
    val offset: Int? = null,
    @SerializedName("results")
    val results: List<Result>? = null,
    @SerializedName("total")
    val total: Int? = null
)