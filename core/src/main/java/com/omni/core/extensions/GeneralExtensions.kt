package com.omni.core.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(message: String) {
    this.let { Toast.makeText(it, message, Toast.LENGTH_SHORT).show() }
}

fun Context.showToast(@StringRes stringRes: Int) {
    this.let { Toast.makeText(it, stringRes, Toast.LENGTH_SHORT).show() }
}

fun View?.showSnackBar(@StringRes stringRes: Int) {
    this?.let {
        Snackbar.make(it, stringRes, Snackbar.LENGTH_LONG)
            .show()
    }
}

fun View?.showSnackBar(message: String) {
    this?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_LONG)
            .show()
    }
}