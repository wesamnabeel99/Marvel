package com.wesam.marvel.model.repositories

import android.util.Log
import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.database.MarvelDatabase
import com.wesam.marvel.model.network.Api
import com.wesam.marvel.model.network.Api.apiService
import com.wesam.marvel.model.network.State
import kotlinx.coroutines.flow.*

object MarvelRepositoryImpl : MarvelRepository {
    private val apiService = Api.apiService
    private val characterDao = MarvelDatabase.getInstanceWithoutContext().marvelDao()
    private val mapper = CharacterMapper()

    override suspend fun getCharacters(): List<Character> {
        return characterDao.getCharacter().map {
            mapper.mapEntityToDomain(it)
        }
    }


    override suspend fun refreshCharacters() {
        try {
            Log.i("TEST", "make request")

            val character = apiService.getCharacters()
                .body()?.data?.results?.map {
                    Log.i("TEST", "i'm mapping $it")
                    mapper.mapDtoToEntity(it)
                }

            character?.let {
                Log.i("TEST", "inserting $it into database")
                characterDao.insertCharacter(it)
            }
        } catch (throwable: Throwable) {

        }

    }

}