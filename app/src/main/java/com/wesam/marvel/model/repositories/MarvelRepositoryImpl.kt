package com.wesam.marvel.model.repositories

import android.util.Log
import com.wesam.marvel.model.domain.mapper.base.Mapper
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.StateHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelService,
    private val mapper: Mapper,
    private val characterDao: MarvelDao,
) : MarvelRepository {

    override fun searchForCharacter(name: String): Flow<List<CharacterEntity>?> {
        return flow {
            StateHandler.handleResponseState { apiService.searchForCharacter(name) }
                .collect { state ->
                    if (state is State.Success) {
                        val response = state.toData()?.data?.results

                        val entity = mapper.mapResponseToEntity(response) { dto ->
                            mapper.characterDtoToEntity.map(dto)
                        }

                        insertEntityIntoDatabase(entity) {
                            entity?.let { entityList ->
                                characterDao.insertCharacter(entityList)
                            }
                        }


                    } else {
                        if (state !is State.Loading) {
                            Log.i("TEST", "not successful")

                        }
                    }
                }
        }
    }


    private suspend fun <ENTITY> insertEntityIntoDatabase(
        entity: List<ENTITY>?,
        daoInsertFunction: suspend (entity: List<ENTITY?>) -> Unit,
    ) {
        entity?.let { entityList ->
            cacheEntity(entityList) {
                daoInsertFunction(entityList)
            }
        }
    }

    private suspend fun <ENTITY> cacheEntity(
        entity: List<ENTITY>,
        insertEntity: suspend (entityList: List<ENTITY>) -> Unit
    ) {
        insertEntity(entity)
    }
}
