package com.aleksandrinastreltsova.application.interactor

import com.aleksandrinastreltsova.application.entity.User
import com.aleksandrinastreltsova.application.repository.UsersRepository
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun loadUsers(): NetworkResponse<List<User>, Unit> =
        usersRepository.getUsers()
}