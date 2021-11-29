package com.aleksandrinastreltsova.application.repository

import com.aleksandrinastreltsova.application.data.network.Api
import com.aleksandrinastreltsova.application.entity.User
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getUsers(): NetworkResponse<List<User>, Unit> =
        api.getUsers()
}