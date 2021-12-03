package com.wesam.marvel.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.local.entities.CharacterEntity
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import com.wesam.marvel.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    val testLiveData : LiveData<State<List<Character>?>> = MarvelRepositoryImpl.getCharacter().asLiveData(Dispatchers.IO)

    init {
        viewModelScope.launch {
            MarvelRepositoryImpl.refreshCharacters()
        }
    }

    fun log() {
        viewModelScope.launch {
            testLiveData.asFlow().collect {
                if(it is State.Success) {
                    Log.i("TEST", it.toData()?.get(0)?.name.toString())
                }
            }
        }

    }
}