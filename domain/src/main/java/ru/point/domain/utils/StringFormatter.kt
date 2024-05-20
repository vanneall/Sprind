package ru.point.domain.utils

import ru.point.domain.entity.utils.AddressDto
import ru.point.domain.entity.utils.Currency
import ru.point.domain.entity.utils.Price

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

    fun formatAddress(addressDto: AddressDto?): String? {
        if (addressDto == null || isAnyFromAddressIsNull(addressDto)) return null
        return "${addressDto.street?.replaceFirstChar { char -> char.uppercase() }}, д.${addressDto.house}, кв.${addressDto.flat}"
    }

    fun checkStringsNotEmpty(vararg values: String): Boolean {
        return values.all { value -> value.isNotEmpty() }
    }

    private fun isAnyFromAddressIsNull(addressDto: AddressDto): Boolean {
        return addressDto.city == null || addressDto.street == null || addressDto.house == null || addressDto.flat == null
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