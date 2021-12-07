package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.base.Mapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.base.BaseResponse
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelService,
    private val mapper: Mapper,
    private val characterDao: MarvelDao,
) : MarvelRepository {

    override suspend fun searchForCharacter(
        name: String,
    ): Flow<State<List<Character>?>> {
        return flow {
            StateHandler.handleRequestState { apiService.searchForCharacter(name) }
                .collect { responseBody ->
                    when (responseBody) {
                        is State.Success -> {
                            emit(State.Success(getCharacterDomain(responseBody)))
                        }
                        is State.Error -> {
                            emit(State.Error(responseBody.toString()))
                        }
                        else -> {
                            emit(State.Loading)
                        }
                    }
                }
        }
    }

    private suspend fun getCharacterDomain(responseBody: State<BaseResponse<CharacterDto>?>): List<Character>? {
        val entity: List<CharacterEntity>? =
            responseBody.toData()?.data?.results?.let { list ->
                mapper.mapToEntity(list) { dto ->
                    mapper.characterDtoToEntity.map(dto)
                }
            }

        entity?.let {
            insertEntityIntoDatabase(
                entity
            ) { characterDao.insertCharacter(entity) }
        }

        return entity?.let { list ->
            mapper.mapToDomain(list) {
                mapper.characterEntityToDomain.map(it)
            }
        }
    }


    override suspend fun <ENTITY> insertEntityIntoDatabase(
        entity: List<ENTITY>?,
        daoInsertFunction: suspend (List<ENTITY?>) -> Unit,
    ) {

        entity?.let { entityList ->
            daoInsertFunction(entityList)
        }
    }

}
