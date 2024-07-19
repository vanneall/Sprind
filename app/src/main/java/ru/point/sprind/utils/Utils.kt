package ru.point.sprind.utils

val <T> List<T>.firstIndex: Int
    get() {
        return if (isNotEmpty()) 0
        else -1
    }