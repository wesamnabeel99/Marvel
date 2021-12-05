package com.wesam.marvel.ui.home

import android.os.Bundle
import android.view.View
import com.wesam.marvel.R
import com.wesam.marvel.databinding.FragmentHomeBinding
import com.wesam.marvel.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(
    private val homeRecyclerAdapter: HomeRecyclerAdapter
) : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = homeRecyclerAdapter
    }


}


