package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.local.database.MarvelDatabase
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.Api
import com.wesam.marvel.model.network.ApiWrapper
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow

object MarvelRepositoryImpl {
    val apiService = Api.apiService
//    val characterDao = MarvelDatabase.getInstanceWithoutContext().marvelDao()

     suspend fun refreshCharacters()  {
        val items = apiService.getCharacters().body()?.data?.results?.map {
            CharacterEntity(
                id = it.id!!,
                name = it.name!!,
                description = it.description!!,
                imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}"
            )
        }
       // items?.let { characterDao.insertCharacter(it) }

    }

     suspend fun getCharacter() : Flow<State<CharacterDto?>> {
        return ApiWrapper.wrapWithFlow { apiService.getCharacters() }
    }
}