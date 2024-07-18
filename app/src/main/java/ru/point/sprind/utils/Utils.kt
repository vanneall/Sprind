package ru.point.sprind.utils

import androidx.paging.PagingConfig



val <T> List<T>.firstIndex: Int
    get() {
        return if (isNotEmpty()) 0
        else -1
    }