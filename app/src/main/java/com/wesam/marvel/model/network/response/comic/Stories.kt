package com.wesam.marvel.model.network.response.comic


import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<ItemX>? = null,
    @SerializedName("returned")
    val returned: Int? = null
)