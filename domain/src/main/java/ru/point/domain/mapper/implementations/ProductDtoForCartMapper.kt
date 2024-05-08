package ru.point.domain.mapper.implementations

import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.entity.view.cart.CartEmptyVo
import ru.point.domain.entity.view.cart.CartProductVo
import ru.point.domain.entity.view.cart.CartPromocodeVo
import ru.point.domain.entity.view.cart.CartSummaryVo
import ru.point.domain.entity.view.ViewObject

class ProductDtoForCartMapper : FeedProductListViewMapper {
    override fun map(productFeedDto: List<ProductFeedDto>): List<ViewObject> {

        if (productFeedDto.isEmpty()) return listOf(CartEmptyVo())

        val products = productFeedDto.map { dto ->
            CartProductVo(
                id = dto.id,
                name = dto.name,
                url = dto.photosUrl[0],
                price = dto.price
            )
        }

        val deliveryPrice = 0 //TODO make delivery
        val productsPrice = sumPrice(products)
        val discountPrice = 0 //TODO make discount
        val promocodePrice = 0 //TODO make promocode
        val summaryPrice = deliveryPrice + productsPrice.money + discountPrice + promocodePrice

        // TODO make price formatter
        return products + listOf(
            CartPromocodeVo(),

            CartSummaryVo(
                delivery = deliveryPrice.toString(),
                products = productsPrice.money.toString(),
                discount = discountPrice.toString(),
                promocode = promocodePrice.toString(),
                summary = summaryPrice.toString()
            )
        )
    }
}

interface FeedProductListViewMapper {
    fun map(productFeedDto: List<ProductFeedDto>): List<ViewObject>
}

fun sumPrice(collection: Collection<*>): Price {
    var price = Price()
    collection.forEach { value ->
        (value as? CartProductVo)?.let { price += value.price }
    }
    return price
}