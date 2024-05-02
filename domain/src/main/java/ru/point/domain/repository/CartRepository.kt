package ru.point.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.FeedProductDto

interface CartRepository {

    fun getProducts(): Observable<List<FeedProductDto>>

    fun addProduct(id: Long)

}