package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.local.entities.SearchResultEntity
import com.wesam.marvel.model.network.MarvelService
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
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

    override suspend fun searchForCharacter(name: String) {
        try {
            val response = apiService.searchForCharacter(name).body()?.data?.results
            val searchResult = response?.map {
                SearchResultEntity(name, it.id!!.toLong())
            }
            val characters = response?.map {
                mapper.mapDtoToEntity(it)
            }
            characters?.let {
                characterDao.insertCharacter(it)
            }

            searchResult?.let {
                characterDao.insertSearchResult(it)
            }
        } catch (throwable: Throwable) {

        }
    }

    override suspend fun getResultsFromDatabase(): List<Character> {
        return characterDao.getSearchResults().map {
            return characterDao.getCharacterById(it.resultId).map {
                mapper.mapEntityToDomain(it)
            }
        }
    }

}