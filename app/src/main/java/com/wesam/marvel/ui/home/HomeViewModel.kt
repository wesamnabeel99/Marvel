package com.wesam.marvel.ui.home

import androidx.lifecycle.*
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.repositories.MarvelRepositoryImpl
import com.wesam.marvel.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MarvelRepositoryImpl
) : BaseViewModel(),
    HomeInteractionListener {
    val testLiveData = MutableLiveData<List<Character>>()


    fun getData() {


    }

    override fun onCharacterClicked() {

    }
}