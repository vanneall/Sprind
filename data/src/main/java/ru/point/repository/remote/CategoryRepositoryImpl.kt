package ru.point.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.response.category.CategoryResponse
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.repository.CategoryRepository
import ru.point.repository.paging.CategoryPagingSource
import ru.point.retrofit.api.CategoryApi

class CategoryRepositoryImpl(
    private val api: CategoryApi
) : CategoryRepository {
    override fun getProductsByCategoryId(categoryId: Long): Observable<PagingData<ViewObject>> {
        return Pager(
            config = pagerConfig,
            pagingSourceFactory = { CategoryPagingSource(categoryId, api) }
        ).observable
            .subscribeOn(Schedulers.io())
    }

    override fun getCategoryInfo(categoryId: Long): Single<CategoryResponse> {
        return api.getCategoryInfoById(id = categoryId).subscribeOn(Schedulers.io())
    }
}