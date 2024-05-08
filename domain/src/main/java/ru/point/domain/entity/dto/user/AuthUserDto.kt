package ru.point.domain.entity.dto.user

import com.google.gson.annotations.SerializedName

data class AuthUserDto(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)
