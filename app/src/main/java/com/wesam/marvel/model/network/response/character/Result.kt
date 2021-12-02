package com.wesam.marvel.model.network.response.character


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("comics")
    val comics: Comics? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("events")
    val events: Events? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("series")
    val series: Series? = null,
    @SerializedName("stories")
    val stories: Stories? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("urls")
    val urls: List<Url>? = null
)