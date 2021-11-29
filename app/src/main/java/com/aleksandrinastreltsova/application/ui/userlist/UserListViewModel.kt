package com.aleksandrinastreltsova.application.ui.userlist

import androidx.lifecycle.viewModelScope
import com.aleksandrinastreltsova.application.BuildConfig
import com.aleksandrinastreltsova.application.data.network.Api
import com.aleksandrinastreltsova.application.data.network.MockApi
import com.aleksandrinastreltsova.application.entity.User
import com.aleksandrinastreltsova.application.interactor.UsersInteractor
import com.aleksandrinastreltsova.application.ui.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: Flow<ViewState> get() = _viewState.asStateFlow()


    init {
        loadUsers()
    }


    private fun loadUsers() {
        viewModelScope.launch {
            _viewState.emit(ViewState.Loading)
            when (val response = usersInteractor.loadUsers()) {
                is NetworkResponse.Success -> {
                    _viewState.emit(ViewState.Data(response.body))
                }
                else -> {

                }
            }
        }
    }

    private fun provideApi(): Api =
        if (BuildConfig.USE_MOCK_BACKEND_API) {
            MockApi()
        } else {
            Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
                .build()
                .create(Api::class.java)
        }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }


    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

}