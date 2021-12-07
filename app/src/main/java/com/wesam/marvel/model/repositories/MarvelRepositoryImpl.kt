package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.base.Mapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.network.State
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
            StateHandler.handleRequestState { apiService.searchForCharacter(name) }.collect {
                if (it is State.Success) {
                    val entity: List<CharacterEntity>? =
                        it.toData()?.data?.results?.let { list ->
                            mapper.mapToEntity(list) { dto ->
                                mapper.characterDtoToEntity.map(dto)
                            }
                        }

                    entity?.let {
                        insertEntityIntoDatabase(
                            entity
                        ) { characterDao.insertCharacter(entity) }
                    }

                    val domain = entity?.let { list ->
                        mapper.mapToDomain(list) {
                            mapper.characterEntityToDomain.map(it)
                        }
                    }
                    emit(State.Success(domain))
                } else if (it is State.Error) {
                    emit(State.Error(it.toString()))
                } else {
                    emit(State.Loading)
                }
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
