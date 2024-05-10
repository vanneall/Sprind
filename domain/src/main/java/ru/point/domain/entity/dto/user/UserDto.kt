package ru.point.domain.entity.dto.user

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("secondName")
    val secondName: String,
)