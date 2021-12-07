package com.wesam.marvel.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.repositories.MarvelRepository
import com.wesam.marvel.ui.base.BaseViewModel
import com.wesam.marvel.ui.home.HomeInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(),
    HomeInteractionListener {
    val testLiveData = MutableLiveData<List<Character>>()
    val searchText = MutableLiveData<String>()


    fun getData(name: String) {
        viewModelScope.launch {
            repository.searchForCharacter(name)
            testLiveData.postValue(repository.getResultsFromDatabase())
        }

    }

    override fun onCharacterClicked() {

    }


}