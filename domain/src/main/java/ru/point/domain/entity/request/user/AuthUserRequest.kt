package ru.point.domain.entity.request.user

import com.google.gson.annotations.SerializedName

data class AuthUserRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)
