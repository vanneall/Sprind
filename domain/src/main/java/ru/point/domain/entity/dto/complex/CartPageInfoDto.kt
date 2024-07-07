package ru.point.domain.entity.dto.complex

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.AddressInfoResponse
import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.utils.ResponseItem
import ru.point.domain.entity.view.cart.CartSummaryVo
import ru.point.domain.utils.StringFormatter

data class CartPageInfoDto(
    @SerializedName("address")
    val addressInfoResponse: AddressInfoResponse?,
    @SerializedName("summaryPrice")
    val summaryPriceResponse: SummaryPriceInfoResponse?
)

data class SummaryPriceInfoResponse(
    @SerializedName("delivery")
    val delivery: Int,
    @SerializedName("products")
    val products: Int,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("promocode")
    val promocode: Int,
    @SerializedName("summary")
    val summary: Int,
): ResponseItem

fun SummaryPriceInfoResponse.toCartSummaryVo(): CartSummaryVo {
    return CartSummaryVo(
        delivery = StringFormatter.formatPrice(Price(delivery.toDouble())),
        products = StringFormatter.formatPrice(Price(products.toDouble())),
        discount = StringFormatter.formatPrice(Price(discount.toDouble())),
        promocode = StringFormatter.formatPrice(Price(promocode.toDouble())),
        summary = StringFormatter.formatPrice(Price(summary.toDouble())),
    )
}
