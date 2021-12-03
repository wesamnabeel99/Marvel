package com.wesam.marvel.model.domain.mapper

import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.response.character.CharacterDto
import com.wesam.marvel.utils.toCharacter
import com.wesam.marvel.utils.toCharacterEntitity

class CharacterMapper: Mapper<CharacterDto,Character,CharacterEntity> {
    override fun mapToDomain(input: CharacterDto): Character {
        return input.toCharacter()
    }

    override fun mapToEntitiy(input: CharacterDto): CharacterEntity {
        return input.toCharacterEntitity()
    }

}