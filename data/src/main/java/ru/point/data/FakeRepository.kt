package ru.point.data


import ru.point.domain.entity.ImageURL
import ru.point.domain.entity.Product
import ru.point.domain.repository.ProductRepository
import javax.inject.Inject

class FakeRepository @Inject constructor() : ProductRepository {
    override fun getProducts(): List<Product> {
        return listOf(
            Product(
                1,
                "Google Pixel 8",
                "59 999",
                listOf(ImageURL("https://avatars.mds.yandex.net/get-marketpic/1780364/pic7d48e7dc3f9f5f4f17b706ed174b8880/optimize"))
            ),
            Product(
                1,
                "Google Pixel 8",
                "59 999",
                listOf(ImageURL("https://avatars.mds.yandex.net/get-marketpic/1780364/pic7d48e7dc3f9f5f4f17b706ed174b8880/optimize"))
            ),
            Product(
                1,
                "Google Pixel 8",
                "59 999",
                listOf(ImageURL("https://avatars.mds.yandex.net/get-marketpic/1780364/pic7d48e7dc3f9f5f4f17b706ed174b8880/optimize"))
            ),
            Product(
                1,
                "Google Pixel 8",
                "59 999",
                listOf(ImageURL("https://avatars.mds.yandex.net/get-marketpic/1780364/pic7d48e7dc3f9f5f4f17b706ed174b8880/optimize"))
            ),
            Product(
                1,
                "Google Pixel 8",
                "59 999",
                listOf(ImageURL("https://avatars.mds.yandex.net/get-marketpic/1780364/pic7d48e7dc3f9f5f4f17b706ed174b8880/optimize"))
            ),
            Product(
                1,
                "Google Pixel 8",
                "59 999",
                listOf(ImageURL("https://avatars.mds.yandex.net/get-marketpic/1780364/pic7d48e7dc3f9f5f4f17b706ed174b8880/optimize"))
            )
        )
    }
}