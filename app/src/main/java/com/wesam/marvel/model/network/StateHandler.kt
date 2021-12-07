package com.wesam.marvel.model.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object StateHandler {
    suspend fun <T> handleResponseState(function: suspend () -> Response<T>): Flow<State<T>> {
        return flow {
            emit(State.Loading)
            try {
                val response = function()
                if (response.isSuccessful) {
                    val isResponseEmpty = checkResponseBody(response)
                    if (isResponseEmpty) {
                        emit(State.Error("EMPTY JSON DUE TO BUG IN API"))
                        handleResponseState(function)
                    } else {
                        emit(State.Success(response.body()!!))
                    }
                } else {
                    emit(State.Error(response.message()))
                }

            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }
    }

    private fun <T> checkResponseBody(response: Response<T>) = response.body().toString() == ""

}