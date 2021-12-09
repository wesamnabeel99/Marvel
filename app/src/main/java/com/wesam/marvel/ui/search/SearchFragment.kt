package com.wesam.marvel.ui.search

import android.os.Bundle
import android.view.View
import com.wesam.marvel.R
import com.wesam.marvel.databinding.FragmentSearchBinding
import com.wesam.marvel.ui.base.BaseFragment
import com.wesam.marvel.utils.isKeyPressed

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutId = R.layout.fragment_search
    override val viewModelClass = SearchViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = SearchRecyclerAdapter(
            viewModel.searchResults.value?.toData() ?: emptyList(), viewModel
        )

        binding.apply {
            editText.setOnKeyListener { _, _, keyEvent ->
                if (keyEvent.isKeyPressed()) {
                    viewModel?.search(editText.text.toString())
                }
                false
            }
        }


    }
}