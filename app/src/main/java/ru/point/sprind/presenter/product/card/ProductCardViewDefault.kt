package ru.point.sprind.presenter.product.card

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.NavigableView
import ru.point.sprind.presenter.general.UsingDefaultAdapterView

interface ProductCardViewDefault : ConnectionRequiredView, UsingDefaultAdapterView, NavigableView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayProductInCartButtonGroup(show: Boolean)
}