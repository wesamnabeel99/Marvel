package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.base.Mapper
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelService,
    private val mapper: Mapper,
    private val characterDao: MarvelDao,
) : MarvelRepository {

    override suspend fun searchForCharacter(name: String): Flow<List<CharacterEntity>?> {
        return flow {
            handleResponse { apiService.searchForCharacter(name) }.collect { state ->
                if (state is State.Success) {

                    val entity = state.toData()?.data?.results?.map {
                        mapper.characterDtoToEntity.map(it)
                    }

                    entity?.let { e ->
                        cacheEntity(e) {
                            characterDao.insertCharacter(e)
                        }
                    }
                }
            }
        }
    }

    private suspend fun <T> cacheEntity(
        entity: List<T>,
        insertEntity: suspend (c: List<T>) -> Unit
    ) {
        insertEntity(entity)
    }

    private fun <T, E> mapToEntity(
        list: List<T>,
        function: () -> E
    ): List<E> {
        return list.map {
            function()
        }
    }


    private suspend fun <T> handleResponse(function: suspend () -> Response<T>): Flow<State<T>> {
        return flow {
            try {
                val response = function()
                if (response.isSuccessful) {
                    val isResponseEmpty = checkResponseBody(response)
                    if (isResponseEmpty) {
                        emit(State.Error("EMPTY JSON DUE TO BUG IN API"))
                        handleResponse(function)
                    } else {
                        emit(State.Success(response.body()!!))
                    }
                } else {
                    emit(State.Error(response.message()))
                }

            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }
    }

    private fun <T> checkResponseBody(response: Response<T>) = response.body().toString() != ""

}
