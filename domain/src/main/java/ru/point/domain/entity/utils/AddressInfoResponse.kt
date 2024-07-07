package ru.point.domain.entity.utils

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.address.AddressVo
import ru.point.domain.utils.StringFormatter

interface ResponseItem : ViewObject

data class AddressInfoResponse(
    @SerializedName(value = "city")
    val city: String?,
    @SerializedName(value = "street")
    val street: String?,
    @SerializedName(value = "house")
    val house: String?,
    @SerializedName(value = "flat")
    val flat: String?,
): ResponseItem

fun AddressInfoResponse?.toAddressVo(): AddressVo {
    return AddressVo(StringFormatter.formatAddress(addressInfoResponse = this))
}