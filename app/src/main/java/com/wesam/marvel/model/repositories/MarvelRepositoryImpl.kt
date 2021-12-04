package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDatabase
import com.wesam.marvel.model.network.Api

object MarvelRepositoryImpl : MarvelRepository {
    private val apiService = Api.apiService
    private val characterDao = MarvelDatabase.getInstanceWithoutContext().marvelDao()
    private val mapper = CharacterMapper()

    override suspend fun getCharacters(): List<Character> {
        return characterDao.getCharacter().map {
            mapper.mapEntityToDomain(it)
        }
    }


    override suspend fun refreshCharacters() {
        try {

            val character = apiService.getCharacters()
                .body()?.data?.results?.map {
                    mapper.mapDtoToEntity(it)
                }

            character?.let {
                characterDao.insertCharacter(it)
            }
        } catch (throwable: Throwable) {

        }

    }

}