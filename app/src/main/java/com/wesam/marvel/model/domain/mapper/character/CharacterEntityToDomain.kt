package com.wesam.marvel.model.domain.mapper.character

import com.wesam.marvel.model.domain.mapper.base.BaseMapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.entities.CharacterEntity
import javax.inject.Inject

class CharacterEntityToDomain @Inject constructor() : BaseMapper<CharacterEntity, Character> {
    override fun map(input: CharacterEntity): Character {
        return Character(
            id = input.id,
            name = input.name,
            imageUrl = input.imageUrl,
            description = input.description
        )
    }
}