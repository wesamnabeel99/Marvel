package com.wesam.marvel

import android.app.Application
import com.wesam.marvel.di.DependencyContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelApplication : Application() {
    val container = DependencyContainer()
}