package com.wesam.marvel.model.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.local.entities.SearchResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: List<CharacterEntity>)

    @Query("SELECT * FROM CHARACTER_TABLE")
    suspend fun getCharacter(): List<CharacterEntity>

    @Query("SELECT * FROM CHARACTER_TABLE WHERE name = :name ORDER BY name")
    fun searchForCharacterByNameInDatabase(name: String): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchResult(searchResult: List<SearchResultEntity>)

    @Query("SELECT * FROM SEARCH_RESULTS_TABLE")
    suspend fun getSearchResults(): List<SearchResultEntity>
}