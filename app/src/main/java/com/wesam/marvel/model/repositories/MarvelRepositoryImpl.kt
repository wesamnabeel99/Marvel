package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.mapper.base.Mapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.State.Error
import com.wesam.marvel.model.network.response.base.BaseResponse
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


    suspend fun <T> getCharacter(): Flow<State<List<Character>?>> {
        return flow {
            makeRequest { apiService.getCharacters() }.collect { responseState ->
                when (responseState) {
                    is State.Success -> {

                        val entityList = mapper.mapResponseToEntity(responseState.toData()?.body()?.data?.results) {
                            mapper.characterDtoToEntity.map(it)
                        }

                        cacheEntity(entityList = entityList) {
                            characterDao.insertCharacter(it)
                        }

                        val characterEntities = characterDao.getCharacter()

                        val domain = mapper.mapEntityToDomain(characterEntities) {
                            mapper.characterEntityToDomain.map(it)
                        }

                        emit(State.Success(domain))
                    }


                    State.Empty -> emit(State.Empty)
                    is Error -> emit(State.Error(responseState.message))
                    State.Loading ->  emit(State.Loading)
                }

            }
        }

    }

    private suspend fun <T> makeRequest(requestFunction: suspend () -> Response<BaseResponse<T>?>): Flow<State<Response<BaseResponse<T>?>>> {
        return StateHandler.wrapWithFlow { requestFunction() }
    }


    private suspend fun <E> cacheEntity(
        entityList: List<E>?,
        daoInsertFunction: suspend (List<E>) -> Unit
    ) {
        entityList?.let {
            daoInsertFunction(it)
        }
    }


}





