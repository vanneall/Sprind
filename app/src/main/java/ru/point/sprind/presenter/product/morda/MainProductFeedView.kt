package ru.point.sprind.presenter.product.morda

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.NavigableView
import ru.point.sprind.presenter.general.UsingPagingAdapter


interface MainProductFeedView : ConnectionRequiredView, UsingPagingAdapter, NavigableView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAddress(address: String?)
}