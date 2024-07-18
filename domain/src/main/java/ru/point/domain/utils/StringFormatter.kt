package ru.point.domain.utils

import ru.point.domain.entity.response.address.AddressInfoResponse
import ru.point.domain.entity.utils.Currency
import ru.point.domain.entity.utils.price.Price

object StringFormatter {

    fun formatPrice(price: Price): String {
        var priceInt = price.money.toInt()
        if (priceInt == 0) return "0 ${currencyFormatter(price.currency)}"

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

    fun formatAddress(addressInfoResponse: AddressInfoResponse?): String? {
        if (addressInfoResponse == null || isAnyFromAddressIsNull(addressInfoResponse)) return null
        return "${addressInfoResponse.street?.replaceFirstChar { char -> char.uppercase() }}, д.${addressInfoResponse.house}, кв.${addressInfoResponse.flat}"
    }

    fun checkStringsNotEmpty(vararg values: String): Boolean {
        return values.all { value -> value.isNotEmpty() }
    }

    private fun isAnyFromAddressIsNull(addressInfoResponse: AddressInfoResponse): Boolean {
        return addressInfoResponse.city == null ||
                addressInfoResponse.street == null ||
                addressInfoResponse.house == null ||
                addressInfoResponse.flat == null
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