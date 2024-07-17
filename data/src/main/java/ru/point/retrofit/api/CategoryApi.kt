package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.point.domain.entity.dto.category.CategoryResponse

interface CategoryApi {
    @GET("categories/available")
    fun getAvailableCategories(): Single<List<CategoryResponse>>
}