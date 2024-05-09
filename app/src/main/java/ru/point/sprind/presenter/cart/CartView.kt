package ru.point.sprind.presenter.cart

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.UsingAdapterView

interface CartView : ConnectionRequiredView, UsingAdapterView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPayButton()
    @StateStrategyType(SkipStrategy::class)
    fun requireAuthorization()
}