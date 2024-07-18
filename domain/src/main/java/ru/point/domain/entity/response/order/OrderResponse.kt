package ru.point.domain.entity.response.order

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem

data class OrderResponse(
    @SerializedName(value = "id")
    val id: Long,
    @SerializedName(value = "deliveryCost")
    val deliveryCost: Double,
    @SerializedName(value = "productsCost")
    val productsCost: Double,
    @SerializedName(value = "summaryCost")
    val summaryCost: Double,
): ResponseItem