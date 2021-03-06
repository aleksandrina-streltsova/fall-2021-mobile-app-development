package com.aleksandrinastreltsova.application.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
        @Json(name = "id") val id: Long,
        @Json(name = "first_name") val firstName: String,
        @Json(name = "last_name") val lastName: String,
        @Json(name = "username") val username: String,
        @Json(name = "avatar") val avatarUrl: String?,
        @Json(name = "group_name") val groupName: String?
)
