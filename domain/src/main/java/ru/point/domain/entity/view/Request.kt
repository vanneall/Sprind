package ru.point.domain.entity.view

import ru.point.domain.entity.view.ListView

data class Request(
    val id: Long,
    val text: String,
): ListView
