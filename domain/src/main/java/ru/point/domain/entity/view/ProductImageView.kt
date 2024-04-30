package ru.point.domain.entity.view

import ru.point.domain.entity.Price
import ru.point.domain.entity.dto.Characteristic

@JvmInline
value class ProductImageView(
    val url: String,
) : ListView

data class ProductTitleView(
    val title: String,
    val price: Price,
    val rating: String = "5.0",
) : ListView

data class ProductDescriptionView(
    val description: String,
    val shopName: String,
    val categoryName: String,
) : ListView

data class AllCharacteristicsView(
    val characteristics: List<Characteristic>,
) : ListView

data class CharacteristicDescriptionView(
    val name: String,
    val description: String,
) : ListView

data class CharacteristicTitleView(
    val title: String
): ListView