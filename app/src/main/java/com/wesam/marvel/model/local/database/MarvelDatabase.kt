package com.wesam.marvel.model.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wesam.marvel.model.local.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
@TypeConverters(Convertor::class)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun marvelDao(): MarvelDao

    companion object {
        const val DATABASE_NAME = "NotesDatabase"
    }

}