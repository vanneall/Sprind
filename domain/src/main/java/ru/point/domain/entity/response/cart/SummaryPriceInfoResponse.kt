package ru.point.domain.entity.response.cart

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem

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