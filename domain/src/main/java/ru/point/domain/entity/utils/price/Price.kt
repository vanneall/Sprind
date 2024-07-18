package ru.point.domain.entity.utils.price

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.Currency

data class Price(
    @SerializedName("money")
    val money: Double = 0.0,
    @SerializedName("currency")
    val currency: Currency = Currency.RUR,
) {
    operator fun plus(other: Price): Price {
        require(other.currency == currency) {
            throw MultiplyCurrencyException("Required $currency but ${other.currency} provided")
        }

        return Price(other.money + money, currency)
    }
}
