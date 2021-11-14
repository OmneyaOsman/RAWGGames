package com.omni.core.extensions

import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.omni.core.R

fun SwipeRefreshLayout.initializeToView(functionWhenRefresh: () -> Unit) {
    setColorSchemeColors(
        ContextCompat.getColor(context!!, R.color.green_018064),
        ContextCompat.getColor(context!!, R.color.green_018064),
        ContextCompat.getColor(context!!, R.color.green_018064),
        ContextCompat.getColor(context!!, android.R.color.white)
    )
    setProgressBackgroundColorSchemeColor(ContextCompat.getColor(context!!, android.R.color.white))
    setOnRefreshListener {
        functionWhenRefresh()
    }
}
