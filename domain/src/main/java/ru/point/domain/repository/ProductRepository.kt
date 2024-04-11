package ru.point.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.Product

interface ProductRepository {
    fun getProducts(): Observable<List<Product>>
}