package com.wesam.marvel.di

import com.wesam.marvel.ui.home.HomeRecyclerAdapter
import com.wesam.marvel.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RecyclerAdapterProvider {
    @Provides
    fun provideHomeRecyclerAdapter(viewModel : HomeViewModel) = HomeRecyclerAdapter(
        viewModel.testLiveData.value ?: emptyList(), viewModel
    )
}