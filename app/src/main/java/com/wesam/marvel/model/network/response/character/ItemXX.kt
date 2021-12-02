package com.wesam.marvel.model.network.response.character


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null
)