package com.wesam.marvel.model.network.response.character

import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<Item>? = null,
    @SerializedName("returned")
    val returned: Int? = null
)
