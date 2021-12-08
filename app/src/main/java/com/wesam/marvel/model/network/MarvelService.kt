package com.wesam.marvel.model.network

import com.wesam.marvel.model.network.response.base.BaseResponse
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(): Response<BaseResponse<CharacterDto>?>

    @GET("characters")
    suspend fun searchForCharacter(
        @Query("name", encoded = false) characterName: String
    ): Response<BaseResponse<CharacterDto>?>
}