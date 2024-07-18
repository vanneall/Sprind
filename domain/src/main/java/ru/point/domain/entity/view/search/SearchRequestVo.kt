package ru.point.domain.entity.view.search

import ru.point.domain.entity.view.ViewObject

data class SearchRequestVo(
    val id: Long,
    val text: String,
) : ViewObject
