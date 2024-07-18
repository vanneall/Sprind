package ru.point.domain.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.ResponseItem
import ru.point.domain.entity.response.address.AddressInfoResponse
import ru.point.domain.entity.response.cart.SummaryPriceInfoResponse
import ru.point.domain.entity.response.mappers.toAddressVo
import ru.point.domain.entity.response.mappers.toCartProductVo
import ru.point.domain.entity.response.mappers.toCartSummaryVo
import ru.point.domain.entity.response.product.FeedProductResponse
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.cart.CartHeaderVo
import ru.point.domain.exceptions.ViewObjectMapRuleNotFoundException
import ru.point.domain.factory.interfaces.EmptyAddressResponseFactory
import ru.point.domain.repository.CartRepository

class CartPagingSource(
    private val repository: CartRepository,
    private val factory: EmptyAddressResponseFactory
) : RxPagingSource<Int, ViewObject>() {
    override fun getRefreshKey(state: PagingState<Int, ViewObject>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return repository.getProducts(startPage, pageSize)
            .map<LoadResult<Int, ViewObject>> { response ->
                val prevKey = if (startPage == 0) null else startPage - pageSize
                val nextKey = if (response.size < pageSize) null else pageSize

                val headerItems = mutableListOf<ResponseItem>()
                val footerItems = mutableListOf<ResponseItem>()

                if (prevKey == null || nextKey == null) {
                    getPageInfoResponse(
                        isAddressRequired = prevKey == null,
                        isOrderSummaryRequired = nextKey == null,
                        addressItemContainer = headerItems,
                        summaryItemContainer = footerItems
                    )
                }

                val resultPageData = (headerItems + response + footerItems).map { responseItem ->
                    mapResponseItem(responseItem)
                }

                LoadResult.Page(
                    data = resultPageData,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            .onErrorReturn { throwable -> LoadResult.Error(throwable) }
    }

    private fun getPageInfoResponse(
        isAddressRequired: Boolean,
        isOrderSummaryRequired: Boolean,
        addressItemContainer: MutableList<ResponseItem>,
        summaryItemContainer: MutableList<ResponseItem>
    ) {
        val pageInfoResponse = repository.getPageInfo().blockingGet()

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

