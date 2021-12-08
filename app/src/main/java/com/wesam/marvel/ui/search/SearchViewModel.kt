package com.wesam.marvel.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.network.State
import com.wesam.marvel.model.repositories.MarvelRepository
import com.wesam.marvel.ui.base.BaseViewModel
import com.wesam.marvel.ui.home.HomeInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(),
    HomeInteractionListener {
    val testLiveData = MutableLiveData<State<List<Character>?>>(State.Empty)
    val searchText = MutableLiveData<String>()


    fun getData(name: String?) {
        viewModelScope.launch {
            name?.let {
                repository.searchForCharacter(it.toString()).collect {
                    testLiveData.postValue(it)
                }

            }
        }
    }


    override fun onCharacterClicked() {

    }


}