package ru.point.domain.entity.view

import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.dto.Characteristic

@JvmInline
value class ProductImageViewObject(
    val url: String,
) : ViewObject

data class ProductTitleViewObject(
    val title: String,
    val price: Price,
    val rating: String = "5.0",
) : ViewObject

data class ProductDescriptionViewObject(
    val description: String,
    val shopName: String,
    val categoryName: String,
) : ViewObject

data class AllCharacteristicsViewObject(
    val characteristics: List<Characteristic>,
) : ViewObject

data class CharacteristicDescriptionViewObject(
    val name: String,
    val description: String,
) : ViewObject

data class CharacteristicTitleViewObject(
    val title: String
): ViewObject