package com.wesam.marvel.ui.search

import android.os.Bundle
import android.view.View
import com.wesam.marvel.R
import com.wesam.marvel.databinding.FragmentSearchBinding
import com.wesam.marvel.ui.base.BaseFragment
import com.wesam.marvel.ui.home.HomeRecyclerAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutId = R.layout.fragment_search
    override val viewModelClass = SearchViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = HomeRecyclerAdapter(
            viewModel.testLiveData.value?.toData() ?: emptyList(), viewModel
        )
        binding.button.setOnClickListener {
            viewModel.getData(name = binding.editText.text.toString())
        }
    }
}