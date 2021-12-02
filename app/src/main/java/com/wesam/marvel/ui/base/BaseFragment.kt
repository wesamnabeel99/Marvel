package com.wesam.marvel.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wesam.marvel.BR

abstract class BaseFragment <VDB: ViewDataBinding , VM : ViewModel> : Fragment() {

    open val useActivityViewModel: Boolean = true

    abstract val layoutId: Int
    lateinit var viewModel: VM
    abstract val viewModelClass: Class<VM>
    private lateinit var _binding: VDB
    val binding: VDB
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        initViewModel()
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding.apply {
            setVariable(BR.viewModel,this@BaseFragment.viewModel)
            lifecycleOwner = this@BaseFragment
            return root
        }
    }

    private fun initViewModel() {
        viewModel = if (useActivityViewModel) {
            ViewModelProvider(requireActivity())[viewModelClass]
        } else {
            ViewModelProvider(this)[viewModelClass]
        }
    }

}