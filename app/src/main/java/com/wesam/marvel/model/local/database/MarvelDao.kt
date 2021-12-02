package com.wesam.marvel.model.local.database

import androidx.room.Dao
import androidx.room.Insert
import com.wesam.marvel.model.local.entities.CharacterEntity

@Dao
interface MarvelDao {

    @Insert
    suspend fun insertCharacter(character: List<CharacterEntity>)
}