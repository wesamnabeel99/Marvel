package com.wesam.marvel.di

import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import com.wesam.marvel.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    fun provideHomeViewModel(
        apiService: MarvelService,
        characterMapper: CharacterMapper,
        characterDao: MarvelDao
    ) = HomeViewModel(
        MarvelRepositoryImpl(
            apiService = apiService,
            mapper = characterMapper,
            characterDao = characterDao,
            )
    )

}