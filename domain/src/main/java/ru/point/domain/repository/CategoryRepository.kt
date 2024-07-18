package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.category.CategoryResponse
import ru.point.domain.entity.view.ViewObject

interface CategoryRepository {
    fun getProductsByCategoryId(categoryId: Long): Observable<PagingData<ViewObject>>

    fun getCategoryInfo(categoryId: Long): Single<CategoryResponse>
}