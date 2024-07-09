package ru.point.sprind.presenter.product.result

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.NavigableView
import ru.point.sprind.presenter.general.UsingPagingAdapter

interface ResultProductFeedView: ConnectionRequiredView, UsingPagingAdapter, NavigableView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAddress(address: String?)
}