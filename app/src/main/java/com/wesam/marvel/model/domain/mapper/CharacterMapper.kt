package com.wesam.marvel.model.domain.mapper

import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.response.character.CharacterDto

class CharacterMapper: Mapper<CharacterDto,CharacterEntity,Character> {
    override fun mapDtoToDomain(input: CharacterDto): Character {
        return Character(
            id = input.id,
            name = input.name,
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }

    override fun mapDtoToEntity(input: CharacterDto): CharacterEntity {
        return CharacterEntity(
            id=input.id!!,
            name = input.name!!,
            description = input.description!!,
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }

    override fun mapEntityToDomain(input: CharacterEntity): Character {
        return Character(
            id = input.id,
            name = input.name,
            imageUrl = input.imageUrl,
        )
    }

}