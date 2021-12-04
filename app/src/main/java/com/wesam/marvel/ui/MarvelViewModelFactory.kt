package com.wesam.marvel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MarvelViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        listOfViewModels.forEach { viewModelClass ->
            if (modelClass.isAssignableFrom(viewModelClass)) {
                return viewModelClass.newInstance() as T
            }
        }
        throw IllegalArgumentException("View Model Class Not Found")
    }

    companion object {
        val listOfViewModels = mutableListOf<Class<*>>()
    }
}