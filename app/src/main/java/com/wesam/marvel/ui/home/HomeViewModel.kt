package com.wesam.marvel.ui.home

import androidx.lifecycle.*
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import com.wesam.marvel.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel(), HomeInteractionListener {
    val testLiveData = MutableLiveData<List<Character>>()

    init {
        viewModelScope.launch {
            getData()
            if(testLiveData.value?.isEmpty() == true) {
                MarvelRepositoryImpl.refreshCharacters()
                getData()
            }
        }
    }

    fun getData() {
        viewModelScope.launch {
            testLiveData.postValue(MarvelRepositoryImpl.getCharacters())
        }

    }

    override fun onCharacterClicked() {

    }
}