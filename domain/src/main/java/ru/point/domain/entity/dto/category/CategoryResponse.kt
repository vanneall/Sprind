package ru.point.domain.entity.dto.category

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.ResponseItem
import ru.point.domain.entity.view.category.CategoryVo

data class CategoryResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo_url")
    val photoUrl: String?
) : ResponseItem

fun CategoryResponse.toCategoryVo(): CategoryVo {
    return CategoryVo(
        id = id,
        name = name,
        photoUrl = photoUrl
    )
}