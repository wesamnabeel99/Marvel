package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.base.BaseResponse
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun refreshCharacters()
    fun getCharacter() : Flow<List<Character>?>
}