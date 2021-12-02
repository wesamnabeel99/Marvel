package com.wesam.marvel.model.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private const val baseUrl = "https://gateway.marvel.com/"

    private val client = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(MarvelService::class.java)

}

object ApiWrapper {
    fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<State<T?>> = flow {
        emit(State.Loading)
        try {
            val result = function()
            if (result.isSuccessful) {
                emit(State.Success(result.body()))
            } else {
                Log.i("WESAM","error: " +result.toString())
                emit(State.Error(result.message()))
            }
        } catch (e: Exception) {
            Log.i("WESAM","exception: " + e.message.toString())

            emit(State.Error(e.message.toString()))
        }
    }
}




