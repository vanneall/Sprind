package ru.point.domain.entity.response.mappers

import ru.point.domain.entity.complex.ComplexProductFeedVo
import ru.point.domain.entity.response.address.AddressInfoResponse
import ru.point.domain.entity.response.cart.SummaryPriceInfoResponse
import ru.point.domain.entity.response.category.CategoryItemResponse
import ru.point.domain.entity.response.complex.ComplexProductResponse
import ru.point.domain.entity.response.order.OrderResponse
import ru.point.domain.entity.response.product.FeedProductResponse
import ru.point.domain.entity.response.product.ProductInfoResponse
import ru.point.domain.entity.response.review.ReviewResponse
import ru.point.domain.entity.response.shop.ShopResponse
import ru.point.domain.entity.utils.Currency
import ru.point.domain.entity.utils.price.Price
import ru.point.domain.entity.view.address.AddressVo
import ru.point.domain.entity.view.cart.CartProductVo
import ru.point.domain.entity.view.cart.CartSummaryVo
import ru.point.domain.entity.view.category.CategoryVo
import ru.point.domain.entity.view.order.OrderVo
import ru.point.domain.entity.view.product.card.FeedProductVo
import ru.point.domain.entity.view.product.info.ImageUrlVo
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.domain.entity.view.product.info.ProductCharacteristicsVo
import ru.point.domain.entity.view.product.info.ProductDescriptionVo
import ru.point.domain.entity.view.product.info.ProductReviewVo
import ru.point.domain.entity.view.product.info.ProductTitleVo
import ru.point.domain.entity.view.review.ReviewVo
import ru.point.domain.entity.view.shop.ShopVo
import ru.point.domain.utils.StringFormatter
import ru.point.domain.utils.UtilsConst.PICTURE_NOT_FOUND_URL

fun SummaryPriceInfoResponse.toCartSummaryVo(): CartSummaryVo {
    return CartSummaryVo(
        delivery = StringFormatter.formatPrice(Price(delivery.toDouble())),
        products = StringFormatter.formatPrice(Price(products.toDouble())),
        discount = StringFormatter.formatPrice(Price(discount.toDouble())),
        promocode = StringFormatter.formatPrice(Price(promocode.toDouble())),
        summary = StringFormatter.formatPrice(Price(summary.toDouble())),
    )
}

fun ComplexProductResponse.toComplexProductFeedVo(): ComplexProductFeedVo {
    val address = addressInfoResponse.toAddressVo()

    return ComplexProductFeedVo(
        addressVo = address
    )
}

fun OrderResponse.toOrderVo(): OrderVo {
    return OrderVo(
        id = id, deliveryPrice = StringFormatter.formatPrice(
            Price(
                money = deliveryCost, currency = Currency.RUR
            )
        ), productsPrice = StringFormatter.formatPrice(
            Price(
                money = productsCost, currency = Currency.RUR
            )
        ), summaryPrice = StringFormatter.formatPrice(
            Price(
                money = summaryCost, currency = Currency.RUR
            )
        )
    )
}

fun FeedProductResponse.toProductFeedVo(): FeedProductVo {
    return FeedProductVo(
        id = id,
        name = name,
        price = StringFormatter.formatPrice(price),
        isFavorite = isFavorite,
        inCart = isInCart,
        description = description,
        rating = rating,
        imagesUrl = if (imagesUrl.isNotEmpty()) imagesUrl.map(::ImageUrlVo)
        else listOf(ImageUrlVo(PICTURE_NOT_FOUND_URL))
    )
}

fun FeedProductResponse.toCartProductVo(): CartProductVo {
    return CartProductVo(
        id = id,
        name = name,
        price = StringFormatter.formatPrice(price),
        isFavorite = isFavorite,
        imageUrl = if (imagesUrl.isNotEmpty()) ImageUrlVo(imagesUrl.first())
        else ImageUrlVo(PICTURE_NOT_FOUND_URL)
    )
}

fun ProductInfoResponse.toNestedRecyclerViewVo(): NestedRecyclerViewVo {
    return NestedRecyclerViewVo(viewObjects = photosUrl.map { url -> ImageUrlVo(url) })
}

fun ProductInfoResponse.toProductTitleVo(): ProductTitleVo {
    return ProductTitleVo(
        title = name,
        isFavorite = isFavorite,
        isInCart = isInCart,
        price = StringFormatter.formatPrice(price),
        rating = rating.toString()
    )
}

fun ProductInfoResponse.toProductDescriptionVo(): ProductDescriptionVo {
    return ProductDescriptionVo(
        description = description ?: "", shopName = shopDto.name, categoryName = categoryDto.name
    )
}

fun ProductInfoResponse.toAllCharacteristicsVo(): ProductCharacteristicsVo {
    return ProductCharacteristicsVo(
        characteristicsResponse = productCharacteristicResponses.toList()
    )
}

fun ProductInfoResponse.toProductReviewVo(): ProductReviewVo {
    return ProductReviewVo()
}

fun ReviewResponse.toReviewVo(): ReviewVo {
    return ReviewVo(
        id = id,
        username = username,
        rating = rating,
        description = description,
        advantages = advantage,
        disadvantages = disadvantage
    )
}

fun ShopResponse.toShopVo(): ShopVo {
    return ShopVo(
        id = id,
        name = name,
        photoUrl = ImageUrlVo(photoUrl ?: PICTURE_NOT_FOUND_URL)
    )
}

fun CategoryItemResponse.toCategoryVo(): CategoryVo {
    return CategoryVo(
        id = id,
        name = name,
        photoUrl = ImageUrlVo(photoUrl ?: PICTURE_NOT_FOUND_URL)
    )
}

fun AddressInfoResponse?.toAddressVo(): AddressVo {
    return AddressVo(StringFormatter.formatAddress(addressInfoResponse = this))
}