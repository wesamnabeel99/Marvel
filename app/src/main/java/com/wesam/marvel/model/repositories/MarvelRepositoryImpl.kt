package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDatabase
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.Api
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.character.toCharacterEntitity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MarvelRepositoryImpl : MarvelRepository {
    private val apiService = Api.apiService
    private val characterDao = MarvelDatabase.getInstanceWithoutContext().marvelDao()
    private val mapper = CharacterMapper()

    override suspend fun refreshCharacters() {
        val response = apiService.getCharacters()
        val items = response.body()?.data?.results
        items?.let {
            characterDao.insertCharacter(it.map { dao->
                mapper.mapToEntitiy(dao)
            })
        }


    }

    override fun getCharacter(): Flow<State<List<Character>?>> {
        return flow {
            emit(State.Loading)
            try {
                val characters =
                    apiService.getCharacters().body()?.data?.results?.map { characterDto ->
                        mapper.mapToDomain(characterDto)
                    }
                emit(State.Success(characters))
            } catch (throwable: Throwable) {
                emit(State.Error(throwable.message.toString()))
            }

        }
    }
}