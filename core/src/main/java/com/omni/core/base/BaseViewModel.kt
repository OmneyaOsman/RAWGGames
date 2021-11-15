package com.omni.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

open class BaseViewModel : ViewModel() {
    val dataLoading = MutableLiveData<Boolean>()

    var toastMessageEvent = MutableSharedFlow<String>()
    var toastResourceMessageEvent = MutableSharedFlow<Int>()
    var snackBarMessageEvent = MutableSharedFlow<String>()
    var snackBarResourceMessageEvent = MutableSharedFlow<Int>()

    val showNoNetworkScreenEvent = MutableSharedFlow<Boolean>()
    val showServerIssueEvent = MutableSharedFlow<Boolean>()
    val showNoListScreenEvent = MutableSharedFlow<Boolean>()
}