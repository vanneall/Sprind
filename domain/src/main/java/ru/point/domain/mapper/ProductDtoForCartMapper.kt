package ru.point.domain.mapper

import ru.point.domain.entity.Price
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.entity.view.CartEmptyVo
import ru.point.domain.entity.view.CartProductVo
import ru.point.domain.entity.view.CartPromocodeVo
import ru.point.domain.entity.view.CartSummaryVo
import ru.point.domain.entity.view.ViewObject

class ProductDtoForCartMapper : FeedProductListViewMapper {
    override fun map(feedProductDto: List<FeedProductDto>): List<ViewObject> {

        if (feedProductDto.isEmpty()) return listOf(CartEmptyVo())

        val products = feedProductDto.map { dto ->
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
    fun map(feedProductDto: List<FeedProductDto>): List<ViewObject>
}

fun sumPrice(collection: Collection<*>): Price {
    var price = Price()
    collection.forEach { value ->
        (value as? CartProductVo)?.let { price += value.price }
    }
    return price
}