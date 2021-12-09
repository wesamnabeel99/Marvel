package com.wesam.marvel.utils

import android.view.KeyEvent
import android.view.View
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.wesam.marvel.R
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

fun <T> View.handleEmptyState(state: State<T>?) = if (state is State.Empty) {
    this.visibility = View.VISIBLE
} else {
    this.visibility = View.GONE
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

fun KeyEvent?.isKeyPressed() = this?.keyCode != null && this.keyCode == KeyEvent.KEYCODE_ENTER

fun onNavDestinationSelected(
    itemId: Int,
    navController: NavController
): Boolean {
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
    if (navController.currentDestination!!.parent!!.findNode(itemId) is ActivityNavigator.Destination) {
        builder.setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
    } else {
        builder.setEnterAnim(R.animator.nav_default_enter_anim)
            .setExitAnim(R.animator.nav_default_exit_anim)
            .setPopEnterAnim(R.animator.nav_default_pop_enter_anim)
            .setPopExitAnim(R.animator.nav_default_pop_exit_anim)
    }

    builder.setPopUpTo(itemId, true)
    val options = builder.build()
    return try {

        navController.navigate(itemId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}

