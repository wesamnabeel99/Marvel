package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.local.entities.SearchResultEntity
import com.wesam.marvel.model.network.MarvelService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelService,
    private val mapper: CharacterMapper,
    private val characterDao: MarvelDao,
) : MarvelRepository {

    override fun searchForCharacterByNameInDatabase(name: String): Flow<List<Character>> {
        return flow {
            characterDao.searchForCharacterByNameInDatabase(name).collect {
                emit(it.map {
                    mapper.mapEntityToDomain(it)
                })
            }
        }
    }


    override suspend fun searchForCharacter(name: String) {
        try {

            val character = apiService.searchForCharacter(name)
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