package ru.point.domain.entity.response.address

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem

data class AddressInfoResponse(
    @SerializedName(value = "city")
    val city: String?,
    @SerializedName(value = "street")
    val street: String?,
    @SerializedName(value = "house")
    val house: String?,
    @SerializedName(value = "flat")
    val flat: String?,
) : ResponseItem