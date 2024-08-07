package ru.point.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.mappers.toProductFeedVo
import ru.point.domain.entity.view.ViewObject
import ru.point.retrofit.api.ProductApi

class NamedProductsPagingSource(
    private val request: String,
    private val api: ProductApi
) : RxPagingSource<Int, ViewObject>() {
    override fun getRefreshKey(state: PagingState<Int, ViewObject>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return api.getProducts(startPage, pageSize, request)
            .map<LoadResult<Int, ViewObject>> { response ->
                val prevKey = if (startPage == 0) null else startPage - pageSize
                val nextKey = if (response.size < pageSize) null else startPage + pageSize

                val resultPageData = response.map { responseItem -> responseItem.toProductFeedVo() }

                LoadResult.Page(
                    data = resultPageData,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            .onErrorReturn { throwable ->
                LoadResult.Error(throwable)
            }
    }
}