package com.wesam.marvel.ui.home

import com.wesam.marvel.R
import com.wesam.marvel.databinding.FragmentHomeBinding
import com.wesam.marvel.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {
    override val layoutId = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java
}