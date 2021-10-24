package com.aleksandrinastreltsova.application.ui

import com.aleksandrinastreltsova.application.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : BaseViewModel() {

    val isAuthorizedFlow: Flow<Boolean> = MutableStateFlow(false)
}