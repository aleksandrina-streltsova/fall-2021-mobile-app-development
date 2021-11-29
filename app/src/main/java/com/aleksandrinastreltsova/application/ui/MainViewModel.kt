package com.aleksandrinastreltsova.application.ui

import com.aleksandrinastreltsova.application.interactor.AuthInteractor
import com.aleksandrinastreltsova.application.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : BaseViewModel() {

    suspend fun isAuthorizedFlow(): Flow<Boolean> = authInteractor.isAuthorized()
}