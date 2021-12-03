package com.wesam.marvel.model.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wesam.marvel.model.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: List<CharacterEntity>)

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getCharacter() : Flow<List<CharacterEntity>>
}