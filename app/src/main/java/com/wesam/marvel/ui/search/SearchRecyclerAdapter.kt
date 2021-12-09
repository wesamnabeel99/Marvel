package com.wesam.marvel.ui.search

import com.wesam.marvel.R
import com.wesam.marvel.model.domain.models.Character
import com.wesam.marvel.ui.base.BaseRecyclerAdapter

class SearchRecyclerAdapter(
    list: List<Character>,
    listener: SearchInteractionListener
) : BaseRecyclerAdapter<Character>(list, listener) {
    override val layoutId = R.layout.item_character

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ): Boolean {
        return (newItems[newItemPosition] as Character).id == (newItems[oldItemPosition] as Character).id
    }

    override fun areContentSame(
        oldPosition: Int,
        newPosition: Int,
        newList: List<Character>
    ): Boolean {
        return false
    }
}
