package com.wesam.marvel.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wesam.marvel.model.local.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
@TypeConverters(Convertor::class)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao

    companion object {
        @Volatile
        private var instance: MarvelDatabase? = null

        fun getInstance(context: Context): MarvelDatabase =
            instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }

        fun getInstanceWithoutContext(): MarvelDatabase {
            return instance!!
        }

        private const val DATABASE_NAME = "NotesDatabase"
        private fun buildDatabase(context: Context): MarvelDatabase {
            return Room.databaseBuilder(context, MarvelDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }

}