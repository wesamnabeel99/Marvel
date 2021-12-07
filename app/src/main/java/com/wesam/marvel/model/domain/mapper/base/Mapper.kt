package com.wesam.marvel.model.domain.mapper.base

import com.wesam.marvel.model.domain.mapper.character.CharacterDtoToDomain
import com.wesam.marvel.model.domain.mapper.character.CharacterDtoToEntity
import com.wesam.marvel.model.domain.mapper.character.CharacterEntityToDomain
import javax.inject.Inject

class Mapper @Inject constructor(
    val characterDtoTODomain: CharacterDtoToDomain,
    val characterDtoToEntity: CharacterDtoToEntity,
    val characterEntityToDomain: CharacterEntityToDomain
) {
    fun <I, ENTITY> mapToEntity(
        list: List<I>,
        mapperFunction: (I) -> ENTITY
    ): List<ENTITY> {
        return list.map {
            mapperFunction(it)
        }
    }

    fun <DTO, DOMAIN> mapToDomain(
        list: List<DTO>,
        mapFunction: (DTO) -> DOMAIN
    ): List<DOMAIN> {
        return list.map {
            mapFunction(it)
        }
    }

}