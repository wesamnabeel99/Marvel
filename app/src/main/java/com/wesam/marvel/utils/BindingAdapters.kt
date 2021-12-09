package com.wesam.marvel.utils

import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wesam.marvel.model.network.State
import com.wesam.marvel.ui.base.BaseRecyclerAdapter

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?) {
    view.handleLoadingState(state)
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, state: State<T>?) {
    view.handleErrorState(state)
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, state: State<T>?) {
    view.handleSuccessState(state)
}

@BindingAdapter(value = ["app:showWhenEmpty"])
fun <T> showWhenEmpty(view: View, state: State<T>?) {
    view.handleEmptyState(state)
}


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseRecyclerAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:showWhenListIsEmpty"])
fun <T> showWhenListIsEmpty(view: View, state: State<T>?) {
    if (state is State.Success) {
        val items = state.toData() as List<T>?
        items?.let { it ->
            if (it.isEmpty()) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:loadImage"])
fun loadImage(view: ImageView, imageUrl: String) {
    if (view.drawable == null) {

    }
    view.load(imageUrl)
}

