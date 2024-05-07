package ru.point.sprind.presenter.cart

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.view.ViewObject

interface CartView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAdapter(view: List<ViewObject>)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPayButton()
    @StateStrategyType(SkipStrategy::class)
    fun requireAuthorization()
}