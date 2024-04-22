package ru.point.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.FeedProductDto
import ru.point.domain.entity.Price
import ru.point.domain.entity.ProductCategoryDto
import ru.point.domain.entity.ProductDto
import ru.point.domain.entity.ProductShopDto
import ru.point.domain.enums.Currency
import ru.point.domain.repository.ProductRepository
import javax.inject.Inject


class FakeRepository @Inject constructor() : ProductRepository {
    override fun getProducts(): Observable<List<FeedProductDto>> {
        return Observable.create {
            it.onNext(
                listOf(
                    FeedProductDto(
                        id = 1,
                        name = "pixel",
                        price = Price(1234.0, Currency.RUR),
                        "",
                        photosUrl = listOf("https://avatars.mds.yandex.net/get-mpic/11385384/2a0000018c584064d574e2164cc429553aa7/600x800")
                    )
                )
            )
        }
    }

    override fun getProductsByName(name: String): Observable<List<FeedProductDto>> {
        TODO("Not yet implemented")
    }

    override fun getProductById(id: Long): Single<ProductDto> {
        return Single.create {
            it.onSuccess(
                ProductDto(
                    id = 1,
                    name = "google",
                    count = 3,
                    price = Price(1337.0, Currency.RUR),
                    description = "some google phone",
                    characteristics = mapOf(),
                    photosUrl = listOf("https://avatars.mds.yandex.net/get-mpic/11385384/2a0000018c584064d574e2164cc429553aa7/600x800"),
                    shopDto = ProductShopDto(
                        id = 1, name = "Google store", description = "some"
                    ),
                    categoryDto = ProductCategoryDto(
                        id = 1,
                        name = "phones",
                    )
                )
            )
        }
    }
}