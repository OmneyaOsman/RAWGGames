package com.omni.core.extensions

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.omni.core.base.BaseViewModel
import kotlinx.coroutines.flow.collect

fun Fragment.commonObserveViewModelFunctions(
    viewModel: BaseViewModel,
    binding: ViewDataBinding
) {
    lifecycleScope.launchWhenStarted {
        viewModel.toastMessageEvent.collect { (binding.root.context.showToast(it)) }
    }

    lifecycleScope.launchWhenStarted {
        viewModel.toastResourceMessageEvent.collect { binding.root.context.showToast(it) }
    }

    lifecycleScope.launchWhenStarted {
        viewModel.snackBarResourceMessageEvent.collect { binding.root.showSnackBar(it) }
    }

    lifecycleScope.launchWhenStarted {
        viewModel.snackBarMessageEvent.collect { binding.root.showSnackBar(it) }
    }
}