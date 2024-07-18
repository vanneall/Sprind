package ru.point.repository.remote

import androidx.paging.PagingConfig

val pagerConfig
    get() = PagingConfig(
        pageSize = 25,
        prefetchDistance = 10,
        maxSize = 45,
        enablePlaceholders = false
    )