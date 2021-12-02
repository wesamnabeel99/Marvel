package com.wesam.marvel.model.network.response.comic


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("type")
    val type: String? = null
)