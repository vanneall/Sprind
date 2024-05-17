package ru.point.domain.entity.utils

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.view.address.AddressVo
import ru.point.domain.utils.StringFormatter

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

fun AddressDto?.toAddressVo(): AddressVo {
    return AddressVo(StringFormatter.formatAddress(addressDto = this))
}