package com.wesam.marvel.ui.home

import com.wesam.marvel.R
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.ui.base.BaseRecyclerAdapter

class HomeRecyclerAdapter(
    list: List<Character>,
    listener: HomeInteractionListener
) : BaseRecyclerAdapter<Character>(list,listener) {
    override val layoutId = R.layout.item_character

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ): Boolean {
    return false
    }
}