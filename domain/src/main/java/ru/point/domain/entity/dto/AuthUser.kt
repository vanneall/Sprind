package ru.point.domain.entity.dto

import com.google.gson.annotations.SerializedName

data class AuthUser(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)
