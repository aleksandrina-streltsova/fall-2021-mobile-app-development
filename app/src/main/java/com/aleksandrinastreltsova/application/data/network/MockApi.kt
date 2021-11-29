package com.aleksandrinastreltsova.application.data.network

import com.aleksandrinastreltsova.application.data.network.request.CreateProfileRequest
import com.aleksandrinastreltsova.application.data.network.request.RefreshAuthTokensRequest
import com.aleksandrinastreltsova.application.data.network.request.SignInWithEmailRequest
import com.aleksandrinastreltsova.application.data.network.responce.VerificationTokenResponse
import com.aleksandrinastreltsova.application.data.network.responce.error.*
import com.aleksandrinastreltsova.application.entity.AuthTokens
import com.aleksandrinastreltsova.application.entity.Post
import com.aleksandrinastreltsova.application.entity.User
import com.haroldadmin.cnradapter.NetworkResponse

class MockApi : Api {

    override suspend fun getUsers(): NetworkResponse<List<User>, Unit> {
        return NetworkResponse.Success(
            body = listOf(User(
                id = 7,
                username = "rediska",
                firstName = "Маша",
                lastName = "Иванов",
                groupName = "Б10.МКН",
                avatarUrl = "https://reqres.in/img/faces/3-image.jpg"
            )),
            code = 200
        )
    }

    override suspend fun signInWithEmail(request: SignInWithEmailRequest): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse> {
        return NetworkResponse.Success(
            AuthTokens(
                accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                accessTokenExpiration = 1640871771000,
                refreshTokenExpiration = 1640871771000,
            ),
            code = 200
        )
    }

    override suspend fun refreshAuthTokens(request: RefreshAuthTokensRequest): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun sendRegistrationVerificationCode(email: String): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyRegistrationCode(
        code: String,
        email: String
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun createProfile(request: CreateProfileRequest): NetworkResponse<AuthTokens, CreateProfileErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getPosts(): NetworkResponse<List<Post>, Unit> {
        TODO("Not yet implemented")
    }
}