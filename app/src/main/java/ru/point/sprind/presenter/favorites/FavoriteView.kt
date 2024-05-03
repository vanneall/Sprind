package ru.point.sprind.presenter.favorites

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.view.ViewObject

interface FavoriteView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAdapter(view: List<ViewObject>)

    @StateStrategyType(SkipStrategy::class)
    fun openCard(id: Long)
}