package com.wesam.marvel.model.network.response.character


import com.google.gson.annotations.SerializedName

data class ItemX(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null
)