package ru.point.sprind.presenter.cart

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.view.ListView

interface CartView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAdapter(view: List<ListView>)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPayButton()
}