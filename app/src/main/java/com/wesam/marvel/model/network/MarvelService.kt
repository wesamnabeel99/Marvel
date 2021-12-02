package com.wesam.marvel.model.network

import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface MarvelService {
    @GET("/v1/public/characters/1011334")
    suspend fun getCharacters() : Response<CharacterDto?>
}