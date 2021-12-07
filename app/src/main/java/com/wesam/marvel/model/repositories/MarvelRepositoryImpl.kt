package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.base.Mapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.network.MarvelService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelService,
    private val mapper: Mapper,
    private val characterDao: MarvelDao,
) : MarvelRepository {

    override suspend fun searchForCharacter(name: String) {
        try {
            val responseBody = apiService.searchForCharacter(name).body()

            //7 December 2021 - a bug in the api, sometimes it returns empty json, make request again to handle this bug
            if (responseBody.toString() == "") {
                searchForCharacter(name)
            }
            val entities = responseBody?.data?.results?.map { dto ->
                mapper.characterDtoToEntity.map(dto)
            }

            entities?.let { list ->
                characterDao.insertCharacter(list)
            }
        } catch (throwable: Throwable) {

        }
    }

    override fun searchForCharacterByNameInDatabase(name: String): Flow<List<Character>> {
        return flow {
            characterDao.searchForCharacterByNameInDatabase(name).collect { list ->
                val domain = list.map { entity ->
                    mapper.characterEntityToDomain.map(entity)
                }
                emit(domain)
            }
        }
    }


}
