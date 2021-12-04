package com.wesam.marvel.di

import android.content.Context
import androidx.room.Room
import com.wesam.marvel.model.local.database.MarvelDao
import com.wesam.marvel.model.local.database.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMarvelDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            MarvelDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(marvelDatabase:MarvelDatabase):MarvelDao {
        return marvelDatabase.marvelDao()
    }
}