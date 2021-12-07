package com.wesam.marvel.di

import com.wesam.marvel.model.domain.mapper.base.Mapper
import com.wesam.marvel.model.domain.mapper.character.CharacterDtoToDomain
import com.wesam.marvel.model.domain.mapper.character.CharacterDtoToEntity
import com.wesam.marvel.model.domain.mapper.character.CharacterEntityToDomain
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.repositories.MarvelRepository
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesRepository(
        mapper: Mapper,
        apiService: MarvelService,
        characterDao: MarvelDao
    ): MarvelRepository {
        return MarvelRepositoryImpl(
            characterDao = characterDao,
            apiService = apiService,
            mapper = mapper
        )
    }

    @Provides
    fun provideBaseMapper(
        characterDtoTODomain: CharacterDtoToDomain,
        characterDtoToEntity: CharacterDtoToEntity,
        characterEntityToDomain: CharacterEntityToDomain,
    ): Mapper = Mapper(
        characterDtoTODomain = characterDtoTODomain,
        characterDtoToEntity = characterDtoToEntity,
        characterEntityToDomain = characterEntityToDomain,
    )

}