package ru.point.domain.entity.dto.user

import com.google.gson.annotations.SerializedName

data class RegisterUserDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("secondName")
    val secondName: String,
    @SerializedName("telephone")
    val telephone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("password")
    val password: String
)
