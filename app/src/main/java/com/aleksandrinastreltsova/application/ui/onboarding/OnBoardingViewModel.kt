package com.aleksandrinastreltsova.application.ui.onboarding

import androidx.lifecycle.viewModelScope
import com.aleksandrinastreltsova.application.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnBoardingViewModel : BaseViewModel() {
    private val _viewState = MutableStateFlow<Long>(0)
    val viewState: Flow<Long> get() = _viewState.asStateFlow()

    var lastScrollTime: Long = 0
    private val minStillnessInterval: Long = 4000

    init {
        lastScrollTime = System.currentTimeMillis()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                while (true) {
                    if (System.currentTimeMillis() - lastScrollTime > minStillnessInterval) {
                        lastScrollTime = System.currentTimeMillis()
                        _viewState.emit(lastScrollTime)
                    }
                    Thread.sleep(minStillnessInterval)
                }
            }
        }
    }
}