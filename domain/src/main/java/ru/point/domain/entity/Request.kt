package ru.point.domain.entity

data class Request(
    val id: Long,
    val text: String,
    var onClick: ((String) -> Unit)? = null
): ListView
