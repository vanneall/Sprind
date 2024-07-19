package ru.point.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.ResponseItem
import ru.point.domain.entity.response.address.AddressInfoResponse
import ru.point.domain.entity.response.cart.SummaryPriceInfoResponse
import ru.point.domain.entity.response.mappers.toAddressVo
import ru.point.domain.entity.response.mappers.toCartProductVo
import ru.point.domain.entity.response.mappers.toCartSummaryVo
import ru.point.domain.entity.response.mappers.toProductFeedVo
import ru.point.domain.entity.response.product.FeedProductResponse
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.cart.CartEmptyVo
import ru.point.domain.entity.view.cart.CartHeaderVo
import ru.point.domain.entity.view.cart.CartPromocodeVo
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.domain.exceptions.ViewObjectMapRuleNotFoundException
import ru.point.domain.factory.interfaces.EmptyAddressResponseFactory
import ru.point.retrofit.api.CartApi
import ru.point.retrofit.api.FavoriteApi

class CartPagingSource(
    private val api: CartApi,
    private val favoriteApi: FavoriteApi,
    private val factory: EmptyAddressResponseFactory
) : RxPagingSource<Int, ViewObject>() {
    override fun getRefreshKey(state: PagingState<Int, ViewObject>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return api.getProductsFromCart(startPage, pageSize)
            .map<LoadResult<Int, ViewObject>> { response ->
                val prevKey = if (startPage == 0) null else startPage - pageSize
                val nextKey = if (response.size < pageSize) null else pageSize

                val headerItems = mutableListOf<ResponseItem>()
                val footerItems = mutableListOf<ResponseItem>()

                if (response.isNotEmpty()) {
                    getPageInfoResponse(
                        isAddressRequired = prevKey == null,
                        isOrderSummaryRequired = nextKey == null,
                        addressItemContainer = headerItems,
                        summaryItemContainer = footerItems
                    )
                }

                val resultPageData = if (response.isEmpty()) {
                    getEmptyCartViewObjects()
                } else {
                    val firstPart = (headerItems + response).map { responseItem ->
                        mapResponseItem(responseItem = responseItem)
                    }
                    val secondPart =
                        if (footerItems.isNotEmpty()) listOf(CartPromocodeVo()) else listOf()

                    firstPart + secondPart + footerItems.map { responseItem ->
                        mapResponseItem(responseItem)
                    }
                }

                LoadResult.Page(
                    data = resultPageData,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            .onErrorReturn { throwable -> LoadResult.Error(throwable) }
    }

    private fun getEmptyCartViewObjects(): List<ViewObject> {
        val header = CartEmptyVo()
        val favorites = NestedRecyclerViewVo(
            represented = NestedRecyclerViewVo.Represented.FAVORITE,
            viewObjects = favoriteApi.getFavorites(0, 10)
                .blockingGet()
                .map { response -> response.toProductFeedVo() }
        )
        return listOf(header, favorites)
    }

    private fun getPageInfoResponse(
        isAddressRequired: Boolean,
        isOrderSummaryRequired: Boolean,
        addressItemContainer: MutableList<ResponseItem>,
        summaryItemContainer: MutableList<ResponseItem>
    ) {
        val pageInfoResponse = api.getPageInfo().blockingGet()

        if (isAddressRequired && pageInfoResponse.summaryPriceResponse != null)
            addressItemContainer.add(pageInfoResponse.addressInfoResponse ?: factory.create())

        if (isOrderSummaryRequired)
            summaryItemContainer.addNotNull(pageInfoResponse.summaryPriceResponse)
    }

    private fun mapResponseItem(responseItem: ResponseItem): ViewObject {
        return when (responseItem) {
            is AddressInfoResponse -> CartHeaderVo(responseItem.toAddressVo())
            is FeedProductResponse -> responseItem.toCartProductVo()
            is SummaryPriceInfoResponse -> responseItem.toCartSummaryVo()
            else -> throw ViewObjectMapRuleNotFoundException("Mapper for $responseItem not found")
        }
    }

    private fun <T> MutableList<T>.addNotNull(element: T?) {
        if (element != null) add(element)
    }
}

