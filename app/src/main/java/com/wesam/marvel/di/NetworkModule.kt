package com.wesam.marvel.di

import com.wesam.marvel.BuildConfig
import com.wesam.marvel.model.domain.mapper.CharacterMapper
import com.wesam.marvel.model.local.database.MarvelDatabase
import com.wesam.marvel.model.network.AuthInterceptor
import com.wesam.marvel.model.network.MarvelService
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideMarvelService(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): MarvelService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(MarvelService::class.java)
    }

    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ) = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideAuthInterceptor() = AuthInterceptor()


}
