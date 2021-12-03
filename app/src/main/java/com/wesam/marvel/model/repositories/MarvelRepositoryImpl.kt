package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.network.Api
import com.wesam.marvel.model.network.ApiWrapper
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.base.BaseResponse
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow

object MarvelRepositoryImpl {
    val apiService = Api.apiService
//    val characterDao = MarvelDatabase.getInstanceWithoutContext().marvelDao()

     suspend fun refreshCharacters()  {

       // items?.let { characterDao.insertCharacter(it) }

    }

     suspend fun getCharacter() :  Flow<State<BaseResponse<CharacterDto>?>> {
        return ApiWrapper.wrapWithFlow { apiService.getCharacters() }
    }
}