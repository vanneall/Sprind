package ru.point.domain.entity.request.user

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("secondName")
    val secondName: String,
)