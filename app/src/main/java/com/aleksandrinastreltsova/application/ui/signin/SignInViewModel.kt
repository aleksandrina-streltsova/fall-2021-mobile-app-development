package com.aleksandrinastreltsova.application.ui.signin

import androidx.lifecycle.viewModelScope
import com.aleksandrinastreltsova.application.repository.AuthRepository
import com.aleksandrinastreltsova.application.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel : BaseViewModel() {
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            AuthRepository.signIn(email, password)
        }
    }
}