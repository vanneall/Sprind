package ru.point.domain.entity

@JvmInline
value class ImageURL(
    val url: String
)

data class Product(
    val skuId: Long,
    val name: String,
    val price: String,
    val url: List<ImageURL>
): ListView
