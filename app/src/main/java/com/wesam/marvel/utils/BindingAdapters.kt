package com.wesam.marvel.utils

import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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

@BindingAdapter(value = ["app:operateSearch"])
fun <T> operateSearch(view: EditText, flag: (String) -> Void) {

}

fun KeyEvent?.isKeyPressed() = this?.keyCode != null && this.keyCode == KeyEvent.KEYCODE_ENTER

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseRecyclerAdapter<T>?)?.setItems(items ?: emptyList())
}