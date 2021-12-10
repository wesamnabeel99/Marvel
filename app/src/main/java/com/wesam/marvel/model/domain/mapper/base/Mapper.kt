package com.wesam.marvel.model.domain.mapper.base

import com.wesam.marvel.model.domain.mapper.character.CharacterDtoToDomain
import com.wesam.marvel.model.domain.mapper.character.CharacterDtoToEntity
import com.wesam.marvel.model.domain.mapper.character.CharacterEntityToDomain
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.base.BaseResponse
import retrofit2.Response
import javax.inject.Inject

class Mapper @Inject constructor(
    val characterDtoTODomain: CharacterDtoToDomain,
    val characterDtoToEntity: CharacterDtoToEntity,
    val characterEntityToDomain: CharacterEntityToDomain
) {
    fun <T, E> mapResponseToEntity(
        dto: List<T>?,
        dtoToDomainMapper: (T) -> E
    ): List<E>? {
        return dto?.map {
            dtoToDomainMapper(it)
        }
    }

    fun <T, D> mapResponseToDomain(
        dto: List<T>?,
        dtoToDomainMapper: (T) -> D
    ): List<D>? {
        return dto?.map {
             dtoToDomainMapper(it)
        }
    }

    fun <E, D> mapEntityToDomain(
        entity: List<E>?,
        entityToDomainMapper: (E) -> D
    ): List<D>? {
        return entity?.map {
            entityToDomainMapper(it)
        }
    }
}