package com.wesam.marvel.model.domain.mapper.character

import com.wesam.marvel.model.domain.mapper.base.BaseMapper
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.response.character.CharacterDto
import javax.inject.Inject

class CharacterDtoToEntity @Inject constructor() : BaseMapper<CharacterDto, CharacterEntity> {
    override fun map(input: CharacterDto): CharacterEntity {
        return CharacterEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            description = input.description ?: "",
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}