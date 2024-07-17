package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.point.domain.entity.dto.category.CategoryItemResponse
import ru.point.domain.entity.dto.category.CategoryResponse
import ru.point.domain.entity.dto.product.FeedProductResponse

interface CategoryApi {
    @GET("categories/available")
    fun getAvailableCategories(): Single<List<CategoryItemResponse>>

    @GET("categories/{id}/info")
    fun getCategoryInfoById(@Path(value = "id") id: Long): Single<CategoryResponse>

    @GET("categories/{id}")
    fun getCategoryProductsById(
        @Path(value = "id") id: Long,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Single<List<FeedProductResponse>>
}