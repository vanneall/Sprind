package ru.point.domain.repository

import io.reactivex.rxjava3.core.Single

interface RequestRepository {
    fun insert(request: String)

    fun getAll(): Single<List<String>>
}