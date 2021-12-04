package com.wesam.marvel.ui.home

import androidx.lifecycle.*
import com.wesam.marvel.di.DependencyContainer
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import com.wesam.marvel.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MarvelRepositoryImpl = DependencyContainer().repository
) : BaseViewModel(),
    HomeInteractionListener {
    val testLiveData = MutableLiveData<List<Character>>()

    init {
        viewModelScope.launch {
            getData()
            if (testLiveData.value == null) {
                repository.refreshCharacters()
                getData()
            }
        }
    }

    fun getData() {
        viewModelScope.launch {
            testLiveData.postValue(repository.getCharacters())
        }

    }

    override fun onCharacterClicked() {

    }
}