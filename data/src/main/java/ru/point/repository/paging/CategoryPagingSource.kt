package ru.point.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.mappers.toProductFeedVo
import ru.point.domain.entity.response.mappers.toShopVo
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.retrofit.api.CategoryApi

class CategoryPagingSource(
    private val categoryId: Long,
    private val api: CategoryApi
) : RxPagingSource<Int, ViewObject>() {
    override fun getRefreshKey(state: PagingState<Int, ViewObject>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return api.getCategoryProductsById(categoryId, startPage, pageSize)
            .map<LoadResult<Int, ViewObject>> { response ->
                val prevKey = if (startPage == 0) null else startPage - pageSize
                val nextKey = if (response.size < pageSize) null else startPage + pageSize

                var shops = listOf<ViewObject>()
                if (prevKey == null) {
                    val shop = api
                        .getCategoryInfoById(id = categoryId)
                        .blockingGet().shops

                    shops = listOf(
                        NestedRecyclerViewVo(
                            viewObjects = shop.map { responseItem ->
                                responseItem.toShopVo()
                            },
                            represented = NestedRecyclerViewVo.Represented.SHOPS
                        )
                    )
                }

                val resultPageData = shops + response.map { it.toProductFeedVo() }

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