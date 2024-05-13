package ru.point.domain.utils

import ru.point.domain.entity.utils.Currency
import ru.point.domain.entity.utils.Price

object StringFormatter {

    fun formatPrice(price: Price): String {
        var priceInt = price.money.toInt()
        val stringPriceBuilder = StringBuilder()

        var counter = 1
        val delimiter = 3
        while (priceInt > 0) {
            if (counter % (delimiter + 1) == 0) stringPriceBuilder.append(" ")
            stringPriceBuilder.append(priceInt % 10)

            priceInt /= 10
            counter++
        }

        val formattedPrice = stringPriceBuilder.reverse().toString()
        val formattedCurrency = currencyFormatter(price.currency)
        return "$formattedPrice $formattedCurrency"
    }


    private fun currencyFormatter(currency: Currency): String {
        return when (currency) {
            Currency.RUR -> "₽"
            Currency.EUR -> "€"
            Currency.KZT -> "₸"
            Currency.RUB -> "Br"
            Currency.USD -> "$"
        }
    }

}