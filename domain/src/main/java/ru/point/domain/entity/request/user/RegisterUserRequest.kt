package ru.point.domain.entity.request.user

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("secondName")
    val secondName: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("email")
    val email: String
)
