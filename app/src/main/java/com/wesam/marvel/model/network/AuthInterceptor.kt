package com.wesam.marvel.model.network

import com.wesam.marvel.BuildConfig
import com.wesam.marvel.utils.md5
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val timeStamp = System.currentTimeMillis().toString()
        val publicApiKey = BuildConfig.PUBLIC_API_KEY
        val privateApiKey = BuildConfig.PRIVATE_API_KEY
        val hash = "${timeStamp}${privateApiKey}${publicApiKey}".md5()

        with(chain.request()) {
            url.newBuilder().apply {
                addQueryParameter(API_KEY_PARAM, publicApiKey)
                addQueryParameter(TIME_STAMP_PARAM, timeStamp)
                addQueryParameter(HASH_PARAM, hash)
            }.build().also {
                return chain.proceed(this.newBuilder().url(it).build())
            }

        }

    }

    companion object {
        private const val API_KEY_PARAM = "apikey"
        private const val TIME_STAMP_PARAM = "ts"
        private const val HASH_PARAM = "hash"
    }
}