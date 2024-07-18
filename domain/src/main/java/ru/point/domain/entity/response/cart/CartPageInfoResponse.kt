package ru.point.domain.entity.response.cart

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem
import ru.point.domain.entity.response.address.AddressInfoResponse

data class CartPageInfoResponse(
    @SerializedName("address")
    val addressInfoResponse: AddressInfoResponse?,
    @SerializedName("summaryPrice")
    val summaryPriceResponse: SummaryPriceInfoResponse?
): ResponseItem