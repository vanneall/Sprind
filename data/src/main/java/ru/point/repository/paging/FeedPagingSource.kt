package ru.point.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.mappers.toCategoryVo
import ru.point.domain.entity.response.mappers.toProductFeedVo
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.retrofit.api.CategoryApi
import ru.point.retrofit.api.ProductApi

class FeedPagingSource(
    private val productApi: ProductApi,
    private val categoryApi: CategoryApi
) : RxPagingSource<Int, ViewObject>() {
    override fun getRefreshKey(state: PagingState<Int, ViewObject>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return productApi.getProducts(startPage, pageSize, null)
            .map<LoadResult<Int, ViewObject>> { response ->
                val prevKey = if (startPage == 0) null else startPage - pageSize
                val nextKey = if (response.size < pageSize) null else startPage + pageSize

                var categories = listOf<ViewObject>()
                if (prevKey == null) {
                    categories = listOf(
                        NestedRecyclerViewVo(
                            viewObjects = categoryApi.getAvailableCategories()
                                .blockingGet()
                                .map { categoryResponse -> categoryResponse.toCategoryVo() },
                            represented = NestedRecyclerViewVo.Represented.CATEGORIES
                        )
                    )
                }

                val resultPageData = categories +
                        response.map { productResponse ->
                            productResponse.toProductFeedVo()
                        }

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