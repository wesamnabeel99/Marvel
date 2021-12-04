package com.wesam.marvel.di

import com.wesam.marvel.BuildConfig
import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.local.database.MarvelDatabase
import com.wesam.marvel.model.network.AuthInterceptor
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DependencyContainer {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val characterDao = MarvelDatabase.getInstanceWithoutContext().marvelDao()

    private val apiService = retrofit.create(MarvelService::class.java)

    private val characterMapper = CharacterMapper()

    val repository = MarvelRepositoryImpl(apiService,characterMapper,characterDao )


}