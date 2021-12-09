package com.wesam.marvel.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.wesam.marvel.R
import com.wesam.marvel.databinding.ActivityMainBinding
import com.wesam.marvel.ui.base.BaseActivity
import com.wesam.marvel.utils.onNavDestinationSelected
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val viewID = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.fragment_container)

        binding.bottomNavigation.addBubbleListener { id ->
            onNavDestinationSelected(id , navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.setSelectedWithId(destination.id, false)
        }
    }


}