package com.wesam.marvel.model.network.response.comic


import com.google.gson.annotations.SerializedName

data class Variant(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null
)