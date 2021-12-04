package com.wesam.marvel.di

import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.local.database.MarvelDatabase
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.repositories.MarvelRepository
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesRepository(
        characterMapper: CharacterMapper,
        apiService: MarvelService,
        characterDao: MarvelDao
    ): MarvelRepository {
        return MarvelRepositoryImpl(
            characterDao = characterDao,
            apiService = apiService,
            mapper = characterMapper
        )
    }

    @Provides
    fun provideCharacterMapper() = CharacterMapper()

    @Provides
    fun provideMarvelDao() = MarvelDatabase.getInstanceWithoutContext().marvelDao()

}