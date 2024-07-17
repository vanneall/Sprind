package ru.point.domain.usecase.implementation.category

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.product.toProductFeedVo
import ru.point.domain.entity.dto.shop.toShopVo
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.domain.repository.CategoryRepository
import ru.point.domain.usecase.interfaces.category.GetCategoryProductsPageUseCase

class GetCategoryProductsPageUseCaseImpl(
    private val repository: CategoryRepository
) : GetCategoryProductsPageUseCase {
    override fun handle(categoryId: Long): RxPagingSource<Int, ViewObject> {
        return object : RxPagingSource<Int, ViewObject>() {
            override fun getRefreshKey(state: PagingState<Int, ViewObject>): Int? = null

            override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
                val startPage = params.key ?: 0
                val pageSize = params.loadSize

                return repository.getProductsByCategoryId(startPage, pageSize, categoryId)
                    .map<LoadResult<Int, ViewObject>> { response ->
                        val prevKey = if (startPage == 0) null else startPage - pageSize
                        val nextKey = if (response.size < pageSize) null else pageSize

                        var shops = listOf<ViewObject>()
                        if (prevKey == null) {
                            val shop = repository
                                .getCategoryInfo(categoryId = categoryId)
                                .blockingGet().shops

                            shops = listOf(
                                NestedRecyclerViewVo(
                                    viewObjects = shop.map { shopResponse ->
                                        shopResponse.toShopVo()
                                    }
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
    }
}