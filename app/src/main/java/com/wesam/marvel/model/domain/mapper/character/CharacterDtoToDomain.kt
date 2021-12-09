package com.wesam.marvel.model.domain.mapper.character

import com.wesam.marvel.model.domain.mapper.base.BaseMapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.network.response.character.CharacterDto
import javax.inject.Inject

class CharacterDtoToDomain @Inject constructor() : BaseMapper<CharacterDto, Character> {
    override fun map(input: CharacterDto): Character {
        return Character(
            id = input.id ?: 0,
            name = input.name ?: "",
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}",
            description = input.description ?:"",
        )
    }
}