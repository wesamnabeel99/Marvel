package com.wesam.marvel.model.network.response.character


import com.google.gson.annotations.SerializedName
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.entities.CharacterEntity

data class CharacterDto(
    @SerializedName("comics")
    val comics: Collection? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("events")
    val events: Collection? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("series")
    val series: Collection? = null,
    @SerializedName("stories")
    val stories: Collection? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("urls")
    val urls: List<Url>? = null
)

