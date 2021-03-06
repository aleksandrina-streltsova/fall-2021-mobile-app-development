package com.aleksandrinastreltsova.application.data.network.responce

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VerificationTokenResponse(
    @Json(name = "verification_token") val verificationToken: String
)