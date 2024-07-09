package ru.point.sprind.utils

import androidx.paging.PagingConfig

val pagerConfig
    get() = PagingConfig(
        pageSize = 25,
        prefetchDistance = 10,
        maxSize = 45,
        enablePlaceholders = false
    )

val <T> List<T>.firstIndex: Int
    get() {
        return if (isNotEmpty()) 0
        else -1
    }