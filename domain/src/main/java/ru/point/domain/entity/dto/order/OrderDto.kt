package ru.point.domain.entity.dto.order

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.Currency
import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.view.order.OrderVo
import ru.point.domain.utils.StringFormatter

data class OrderDto(
    @SerializedName(value = "id")
    val id: Long,
    @SerializedName(value = "deliveryCost")
    val deliveryCost: Double,
    @SerializedName(value = "productsCost")
    val productsCost: Double,
    @SerializedName(value = "summaryCost")
    val summaryCost: Double,
)

fun OrderDto.toOrderVo(): OrderVo {
    return OrderVo(
        id = id,
        deliveryCost = StringFormatter.formatPrice(Price(money = deliveryCost, currency = Currency.RUR)),
        productsCost = StringFormatter.formatPrice(Price(money = productsCost, currency = Currency.RUR)),
        summaryCost = StringFormatter.formatPrice(Price(money = summaryCost, currency = Currency.RUR))
    )
}
