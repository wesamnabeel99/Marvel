package com.wesam.marvel.model.network

import com.wesam.marvel.model.network.response.base.BaseResponse
import com.wesam.marvel.model.network.response.character.CharacterDto
import retrofit2.Response
import retrofit2.http.GET

interface MarvelService {
    @GET("/v1/public/characters")
    suspend fun getCharacters() : Response<BaseResponse<CharacterDto>?>
}