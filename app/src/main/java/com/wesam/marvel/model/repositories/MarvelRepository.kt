package com.wesam.marvel.model.repositories

import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun refreshCharacters()
    suspend fun getCharacter(): Flow<State<CharacterDto>>
}