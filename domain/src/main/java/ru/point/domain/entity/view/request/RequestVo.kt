package ru.point.domain.entity.view.request

import ru.point.domain.entity.view.ViewObject

data class RequestVo(
    val id: Long,
    val text: String,
): ViewObject
