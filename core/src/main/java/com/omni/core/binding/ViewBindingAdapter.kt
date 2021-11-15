package com.omni.core.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["isVisible"])
fun View.isVisible(isVisible: Boolean?) {
    this.visibility = isVisible?.let {
        if (it) View.VISIBLE else View.GONE
    } ?: View.GONE
}

@BindingAdapter(value = ["isInVisible"])
fun View.isInVisible(isInVisible: Boolean?) {
    this.visibility = isInVisible?.let {
        if (it) View.GONE else View.VISIBLE
    } ?: View.VISIBLE
}