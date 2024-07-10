package ru.point.sprind.presenter.cart

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.NavigableView
import ru.point.sprind.presenter.general.UsingPagingAdapter

interface CartView : ConnectionRequiredView, UsingPagingAdapter, NavigableView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPayButton()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hidePayButton()
    @StateStrategyType(SkipStrategy::class)
    fun changeAddress()
}