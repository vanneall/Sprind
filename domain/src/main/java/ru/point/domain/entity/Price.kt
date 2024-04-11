package ru.point.domain.entity

import com.google.gson.annotations.SerializedName
import ru.point.domain.enums.Currency

data class Price(
    @SerializedName("money")
    val money: Double,
    @SerializedName("currency")
    val currency: Currency,
)
