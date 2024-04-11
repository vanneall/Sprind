package ru.point.repository.retrofit

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import ru.point.domain.entity.Product

interface ProductApi {
    @GET("product")
    fun getProductDto(): Observable<List<Product>>
}