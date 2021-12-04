package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.network.MarvelService

class MarvelRepositoryImpl(
    private val apiService: MarvelService,
    private val mapper: CharacterMapper,
    private val characterDao: MarvelDao,
) : MarvelRepository {

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