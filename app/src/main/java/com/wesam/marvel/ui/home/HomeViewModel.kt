package com.wesam.marvel.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.network.response.base.BaseResponse
import com.wesam.marvel.model.network.response.character.CharacterDto
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import com.wesam.marvel.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    val testLiveData = MutableLiveData<State<BaseResponse<CharacterDto>?>>()


    fun update() {
        viewModelScope.launch {
           MarvelRepositoryImpl.getCharacter().collect {
               Log.i("TEST", it.toData()?.data?.results?.get(0)?.name.toString())
                testLiveData.postValue(it)
            }
        }
    }
}