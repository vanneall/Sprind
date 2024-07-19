package ru.point.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.mappers.toReviewVo
import ru.point.domain.entity.view.ViewObject
import ru.point.retrofit.api.ReviewApi

class ProductReviewPagingSource(
    private val productId: Long,
    private val api: ReviewApi
) : RxPagingSource<Int, ViewObject>() {
    override fun getRefreshKey(state: PagingState<Int, ViewObject>) = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return api.getProductsReview(productId = productId, offset = startPage, limit = pageSize)
            .map<LoadResult<Int, ViewObject>> { response ->
                val prevKey = if (startPage == 0) null else startPage - pageSize
                val nextKey = if (response.size < pageSize) null else startPage + pageSize

                val handledResponse = response.map { responseItem -> responseItem.toReviewVo() }

                LoadResult.Page(
                    data = handledResponse,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            .onErrorReturn { throwable ->
                LoadResult.Error(throwable)
            }
    }
}