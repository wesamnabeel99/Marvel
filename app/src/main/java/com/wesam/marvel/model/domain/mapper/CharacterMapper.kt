package com.wesam.marvel.model.domain.mapper

import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.network.response.character.CharacterDto
import com.wesam.marvel.model.network.response.character.toCharacter

class CharacterMapper: Mapper<CharacterDto,Character> {
    override fun map(input: CharacterDto): Character {
        return input.toCharacter()
    }

}