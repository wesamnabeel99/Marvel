package com.wesam.marvel.utils

import android.view.View
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.character.CharacterDto
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.math.BigInteger
import java.security.MessageDigest

fun <T> View.handleErrorState(state: State<T>?) = if (state is State.Error) {
    this.visibility = View.VISIBLE
} else {
    this.visibility = View.GONE
}

fun <T> View.handleLoadingState(state: State<T>?) = if (state is State.Loading) {
    this.visibility = View.VISIBLE
} else {
    this.visibility = View.GONE
}

fun <T> View.handleSuccessState(state: State<T>?) = if (state is State.Success) {
    this.visibility = View.VISIBLE
} else {
    this.visibility = View.GONE
}


fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

