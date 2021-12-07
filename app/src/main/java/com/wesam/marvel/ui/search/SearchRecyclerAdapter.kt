package com.wesam.marvel.ui.search

import com.wesam.marvel.R
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.ui.base.BaseRecyclerAdapter
import com.wesam.marvel.ui.home.HomeInteractionListener

class SearchRecyclerAdapter(
    list: List<Character>,
    listener: SearchInteractionListener
) : BaseRecyclerAdapter<Character>(list,listener) {
    override val layoutId = R.layout.item_character

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ): Boolean {
        return true
    }
}
