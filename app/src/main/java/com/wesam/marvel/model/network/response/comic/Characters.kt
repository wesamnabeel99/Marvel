package com.wesam.marvel.model.network.response.comic


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<Any>? = null,
    @SerializedName("returned")
    val returned: Int? = null
)