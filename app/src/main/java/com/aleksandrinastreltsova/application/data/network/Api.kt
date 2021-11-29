package com.aleksandrinastreltsova.application.data.network

import com.aleksandrinastreltsova.application.data.network.request.CreateProfileRequest
import com.aleksandrinastreltsova.application.data.network.request.RefreshAuthTokensRequest
import com.aleksandrinastreltsova.application.data.network.request.SignInWithEmailRequest
import com.aleksandrinastreltsova.application.data.network.responce.VerificationTokenResponse
import com.aleksandrinastreltsova.application.data.network.responce.error.*
import com.aleksandrinastreltsova.application.entity.AuthTokens
import com.aleksandrinastreltsova.application.entity.User
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.*

interface Api {

    @GET("users")
    suspend fun getUsers(): NetworkResponse<List<User>, Unit>

    @POST("auth/sign-in-email")
    suspend fun signInWithEmail(
        @Body request: SignInWithEmailRequest
    ): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse>

    @POST("auth/refresh-tokens")
    suspend fun refreshAuthTokens(
        @Body request: RefreshAuthTokensRequest
    ): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse>

    @POST("registration/verification-code/send")
    suspend fun sendRegistrationVerificationCode(
        @Query("email") email: String,
    ): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse>

    @POST("registration/verification-code/verify")
    suspend fun verifyRegistrationCode(
        @Query("code") code: String,
        @Query("email") email: String
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse>

    @PUT("registration/create-profile")
    suspend fun createProfile(
        @Body request: CreateProfileRequest
    ): NetworkResponse<AuthTokens, CreateProfileErrorResponse>
}