package com.wesam.marvel.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.model.repositories.MarvelRepository
import com.wesam.marvel.ui.base.BaseViewModel
import com.wesam.marvel.ui.home.HomeInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(),
    HomeInteractionListener {
    val testLiveData = MutableLiveData<List<Character>?>()
    val searchText = MutableLiveData<String>()


    fun getData(name: String) {
        viewModelScope.launch {
            repository.searchForCharacterByNameInDatabase(name).collect { list ->
                if (list.isEmpty()) {
                    repository.searchForCharacter(name)
                    repository.searchForCharacterByNameInDatabase(name).collect {
                        testLiveData.postValue(it)
                    }
                    Log.i("TEST", "inside if:" + testLiveData.value.toString())
                } else {
                    testLiveData.postValue(list)
                    Log.i("TEST", "inside else:" + testLiveData.value.toString())
                }
            }

        }

    }


    override fun onCharacterClicked() {

    }


}