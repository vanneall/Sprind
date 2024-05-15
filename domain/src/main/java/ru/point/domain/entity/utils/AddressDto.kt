package ru.point.domain.entity.utils

import com.google.gson.annotations.SerializedName

data class AddressDto(
    @SerializedName(value = "city")
    val city: String?,
    @SerializedName(value = "street")
    val street: String?,
    @SerializedName(value = "house")
    val house: String?,
    @SerializedName(value = "flat")
    val flat: String?,
)