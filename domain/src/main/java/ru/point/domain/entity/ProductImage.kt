package ru.point.domain.entity

@JvmInline
value class ProductImage(
    val url: String,
) : ListView

data class ProductTitle(
    val title: String,
    val price: Price,
    val rating: String = "5.0"
): ListView

data class ProductDescription(
    val description: String
): ListView