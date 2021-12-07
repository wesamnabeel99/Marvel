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
        function: (input: I) -> ENTITY
    ): List<ENTITY> {
        return list.map {
            function(it)
        }
    }

    fun <I, DOMAIN> mapToDomain(
        list: List<I>,
        function: (input: I) -> DOMAIN
    ): List<DOMAIN> {
        return list.map {
            function(it)
        }
    }
}