package com.wesam.marvel.model.repositories

import android.util.Log
import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDatabase
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.Api
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.character.toCharacterEntitity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

object MarvelRepositoryImpl : MarvelRepository {
    private val apiService = Api.apiService
    private val characterDao = MarvelDatabase.getInstanceWithoutContext().marvelDao()
    private val mapper = CharacterMapper()

    override suspend fun refreshCharacters() {
        val response = apiService.getCharacters()
        val items = response.body()?.data?.results?.map {
            mapper.mapToEntitiy(it)
        }

        items?.let {
            characterDao.insertCharacter(it)
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
                Log.i("TEST", "got em!")
                emit(State.Success(characters))
            } catch (throwable: Throwable) {
                Log.i("TEST", "exception ${throwable.message}")
                emit(State.Error(throwable.message.toString()))
            }

        }
    }
}