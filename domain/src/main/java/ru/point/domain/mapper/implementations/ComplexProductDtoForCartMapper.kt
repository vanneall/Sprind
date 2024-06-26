package ru.point.domain.mapper.implementations

import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.address.AddressVo
import ru.point.domain.entity.view.cart.CartEmptyVo
import ru.point.domain.entity.view.cart.CartHeaderVo
import ru.point.domain.entity.view.cart.CartProductVo
import ru.point.domain.entity.view.cart.CartPromocodeVo
import ru.point.domain.entity.view.cart.CartSummaryVo
import ru.point.domain.utils.StringFormatter
import ru.point.domain.utils.UtilsConst.PICTURE_NOT_FOUND

object ComplexProductDtoForCartMapper {

    fun map(complexDto: ComplexProductDto): List<ViewObject> {
        val productFeedDto = complexDto.productDto

        if (productFeedDto.isEmpty()) return listOf(CartEmptyVo())

        val products = productFeedDto.map { dto ->

            val photoUrl = if (dto.photosUrl.isNotEmpty()) dto.photosUrl.first()
            else PICTURE_NOT_FOUND

            CartProductVo(
                id = dto.id,
                name = dto.name,
                url = photoUrl,
                price = StringFormatter.formatPrice(dto.price),
                isFavorite = dto.isFavorite
            )
        }

        val deliveryPrice = Price() //TODO make delivery
        val productsPrice = sumPrice(productFeedDto)
        val discountPrice = Price() //TODO make discount
        val promocodePrice = Price() //TODO make promocode
        val summaryPrice = deliveryPrice + productsPrice + discountPrice + promocodePrice

        return listOf(
            CartHeaderVo(AddressVo(StringFormatter.formatAddress(complexDto.addressDto)))
        ) + products + listOf(
            CartPromocodeVo(),
            CartSummaryVo(
                delivery = StringFormatter.formatPrice(deliveryPrice),
                products = StringFormatter.formatPrice(productsPrice),
                discount = StringFormatter.formatPrice(discountPrice),
                promocode = StringFormatter.formatPrice(promocodePrice),
                summary = StringFormatter.formatPrice(summaryPrice)
            )
        )
    }
}

fun sumPrice(collection: Collection<*>): Price {
    var price = Price()
    collection.forEach { value ->
        (value as? ProductFeedDto)?.let { price += value.price }
    }
    return price
}