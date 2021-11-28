package com.aleksandrinastreltsova.application.ui

import com.aleksandrinastreltsova.application.repository.AuthRepository
import com.aleksandrinastreltsova.application.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class MainViewModel : BaseViewModel() {

    val isAuthorizedFlow: Flow<Boolean> = AuthRepository.isAuthorizedFlow
}