package com.wesam.marvel.model.network

import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.network.response.base.BaseResponse
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters() : Response<BaseResponse<CharacterDto>?>
}