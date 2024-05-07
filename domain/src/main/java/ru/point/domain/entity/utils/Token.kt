package ru.point.domain.entity.utils

import com.google.gson.annotations.SerializedName

@JvmInline
value class Token(
    @SerializedName("token")
    val value: String
)