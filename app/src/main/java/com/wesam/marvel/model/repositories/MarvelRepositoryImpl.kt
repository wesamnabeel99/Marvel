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
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelService,
    private val mapper: Mapper,
    private val characterDao: MarvelDao,
) : MarvelRepository {

    override fun searchForCharacter(
        name: String,
    ): Flow<State<List<Character>?>> {

        return flow {
            emit(State.Loading)
            characterDao.searchForCharacterByNameInDatabase(name)
                .collect { cachedData ->

                    if (cachedData.isEmpty()) {
                        try {
                            val response = apiService.searchForCharacter(name)

                            val isResponseEmpty = checkResponseBody(response)
                            if (isResponseEmpty) {
                                emit(State.Error("EMPTY JSON BODY DUE TO BUG IN API"))
                                return@collect
                            }

                            cacheCharacterResponse(response)
                            val domain = cachedData.map {
                                mapper.characterEntityToDomain.map(it)
                            }
                            emit(State.Success(domain))


                        } catch (throwable: Throwable) {
                            emit(State.Error(throwable.message.toString()))
                        }

                    } else {
                        val domain = cachedData.map {
                            mapper.characterEntityToDomain.map(it)
                        }
                        emit(State.Success(domain))
                    }

                }
        }
    }


    private suspend fun cacheCharacterResponse(dto: Response<BaseResponse<CharacterDto>?>) {
        val entity: List<CharacterEntity>? = dto.body()?.data?.results?.map {
            mapper.characterDtoToEntity.map(it)
        }

        entity?.let {
            characterDao.insertCharacter(it)
        }
    }

    override fun <T> checkResponseBody(response: Response<T>) = response.body().toString() == ""


}





