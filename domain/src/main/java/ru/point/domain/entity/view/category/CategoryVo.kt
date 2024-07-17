package ru.point.domain.entity.view.category

import ru.point.domain.entity.view.ViewObject

data class CategoryVo(
    val id: Long,
    val name: String,
    val photoUrl: String?
): ViewObject