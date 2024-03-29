package ru.point.domain.repository

import ru.point.domain.entity.Product

interface ProductRepository {
    fun getProducts(): List<Product>
}