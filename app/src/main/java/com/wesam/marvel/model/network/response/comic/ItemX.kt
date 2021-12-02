package com.wesam.marvel.model.network.response.comic


import com.google.gson.annotations.SerializedName

data class ItemX(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("type")
    val type: String? = null
)