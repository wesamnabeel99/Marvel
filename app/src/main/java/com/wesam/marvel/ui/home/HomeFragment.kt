package com.wesam.marvel.ui.home

import android.os.Bundle
import android.view.View
import com.wesam.marvel.R
import com.wesam.marvel.databinding.FragmentHomeBinding
import com.wesam.marvel.ui.base.BaseFragment
import com.wesam.marvel.ui.base.InstantsFragments

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = HomeRecyclerAdapter(
            viewModel.testLiveData.value ?: emptyList(), viewModel
        )
    }

    companion object : InstantsFragments<HomeFragment>(HomeFragment::class.java)

}


