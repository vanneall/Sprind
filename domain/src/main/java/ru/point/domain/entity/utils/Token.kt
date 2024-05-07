package ru.point.domain.entity.utils

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class Token(
    @SerializedName("token")
    val value: String
)