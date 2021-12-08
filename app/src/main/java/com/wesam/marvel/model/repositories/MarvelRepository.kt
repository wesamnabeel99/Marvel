package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.base.BaseResponse
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MarvelRepository {
    fun searchForCharacter(name: String): Flow<State<List<Character>?>>


    fun <T> checkResponseBody(response: Response<T>): Boolean
}